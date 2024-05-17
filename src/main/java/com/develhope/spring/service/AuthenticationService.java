package com.develhope.spring.service;

import com.develhope.spring.DTOs.auth.JwtDto;
import com.develhope.spring.DTOs.auth.LoginDto;
import com.develhope.spring.entity.enums.TipoUtente;
import com.develhope.spring.exception.AuthenticationException;
import com.develhope.spring.repository.AcquirenteRepository;
import com.develhope.spring.repository.AmministratoreRepository;
import com.develhope.spring.repository.VenditoreRepository;
import com.develhope.spring.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

    @Autowired
    private AmministratoreRepository amministratoreRepository;

    public String login(LoginDto loginDto, TipoUtente tipoUtente) throws AuthenticationException {
        JwtDto jwtDto = switch (tipoUtente) {
            case AMMINISTRATORE:
                var amministratore = amministratoreRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
                if (amministratore == null) {
                    throw new AuthenticationException("Invalid credentials");
                }

                yield JwtDto.builder()
                        .nome(amministratore.getNome())
                        .cognome(amministratore.getCognome())
                        .email(amministratore.getEmail())
                        .tipoUtente(TipoUtente.AMMINISTRATORE.name())
                        .build();
            case ACQUIRENTE:
                var acquirente = acquirenteRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
                if (acquirente == null) {
                    throw new AuthenticationException("Invalid credentials");
                }

                yield JwtDto.builder()
                        .nome(acquirente.getNome())
                        .cognome(acquirente.getCognome())
                        .email(acquirente.getEmail())
                        .tipoUtente(TipoUtente.ACQUIRENTE.name())
                        .build();

            case VENDITORE:
                var venditore = venditoreRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
                if (venditore == null) {
                    throw new AuthenticationException("Invalid credentials");
                }

                yield JwtDto.builder()
                        .nome(venditore.getNome())
                        .cognome(venditore.getCognome())
                        .email(venditore.getEmail())
                        .tipoUtente(TipoUtente.VENDITORE.name())
                        .build();
        };

        return jwtUtils.createJwtToken(jwtDto);

    }


}

package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Amministratore.CreateAmministratoreRequest;
import com.develhope.spring.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.DTOs.auth.JwtDto;
import com.develhope.spring.DTOs.auth.LoginDto;
import com.develhope.spring.entity.enums.TipoUtente;
import com.develhope.spring.exception.AuthenticationException;
import com.develhope.spring.security.JWTUtils;
import com.develhope.spring.service.AcquirenteService;
import com.develhope.spring.service.AmministratoreService;
import com.develhope.spring.service.AuthenticationService;
import com.develhope.spring.service.VenditoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagerController {

    @Autowired
    private AmministratoreService amministratoreService;

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private VenditoreService venditoreService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationService authenticationService;

    /// region registrazione

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody CreateAmministratoreRequest request) {
        validateRequest(request);
        var amministratore = amministratoreService.createAmministratore(request);

        var tipoUtente = TipoUtente.AMMINISTRATORE;

        var jwtDto = JwtDto.builder()
                .nome(amministratore.getNome())
                .cognome(amministratore.getCognome())
                .email(amministratore.getEmail())
                .tipoUtente(tipoUtente.name())
                .build();

        return ResponseEntity.ok(jwtUtils.createJwtToken(jwtDto));

    }

    @PostMapping("/registerAcquirente")
    public ResponseEntity<String> registerAcquirente(@RequestBody CreateAcquirenteRequest request) {
        validateRequest(request);
        var acquirente = acquirenteService.createAcquirente(request);

        var tipoUtente = TipoUtente.ACQUIRENTE;
        var jwtDto = JwtDto.builder()
                .nome(acquirente.getNome())
                .cognome(acquirente.getCognome())
                .email(acquirente.getEmail())
                .tipoUtente(tipoUtente.name())
                .build();

        return ResponseEntity.ok(jwtUtils.createJwtToken(jwtDto));
    }

    @PostMapping("/registerVenditore")
    public ResponseEntity<String> registerVenditore(@RequestBody CreateVenditoreRequest request) {
        validateRequest(request);
        var venditore = venditoreService.createVenditore(request);

        var tipoUtente = TipoUtente.VENDITORE;

        var jwtDto = JwtDto.builder()
                .nome(venditore.getNome())
                .cognome(venditore.getCognome())
                .email(venditore.getEmail())
                .tipoUtente(tipoUtente.name())
                .build();

        return ResponseEntity.ok(jwtUtils.createJwtToken(jwtDto));
    }

    /// endregion

    @PostMapping("/loginAmministratore")
    public ResponseEntity<String> loginAmministratore(@RequestBody LoginDto loginDto) {
        var tipo = TipoUtente.AMMINISTRATORE;
        String jwt;
        try {
            jwt = authenticationService.login(loginDto, tipo);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/loginAcquirente")
    public ResponseEntity<String> loginAcquirente(@RequestBody LoginDto loginDto) {
        var tipo = TipoUtente.ACQUIRENTE;
        String jwt;
        try {
            jwt = authenticationService.login(loginDto, tipo);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/loginVenditore")
    public ResponseEntity<String> loginVenditore(@RequestBody LoginDto loginDto) {
        var tipo = TipoUtente.VENDITORE;
        String jwt;
        try {
            jwt = authenticationService.login(loginDto, tipo);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

        return ResponseEntity.ok(jwt);
    }

    private void validateRequest(CreateAmministratoreRequest request) {
        if (request.getNome() == null || request.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if (request.getCognome() == null || request.getCognome().isEmpty()) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email non valida");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password non valida");
        }
    }

    private void validateRequest(CreateAcquirenteRequest request) {
        if (request.getNome() == null || request.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if (request.getCognome() == null || request.getCognome().isEmpty()) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email non valida");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password non valida");
        }
        if (request.getTelefono() == null || request.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("Telefono non valido");
        }
    }

    private void validateRequest(CreateVenditoreRequest request) {
        if (request.getNome() == null || request.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if (request.getCognome() == null || request.getCognome().isEmpty()) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email non valida");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password non valida");
        }
        if (request.getTelefono() == null || request.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("Telefono non valido");
        }
    }

}

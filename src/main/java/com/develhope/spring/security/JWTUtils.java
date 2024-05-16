package com.develhope.spring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.develhope.spring.DTOs.auth.JwtDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String createJwtToken(JwtDto jwtDto) {
        return JWT.create()
                .withIssuer("Dealer")
                .withSubject("User Details")
                .withClaim("nome", jwtDto.getNome())
                .withClaim("cognome", jwtDto.getCognome())
                .withClaim("email", jwtDto.getEmail())
                .withClaim("tipoUtente", jwtDto.getTipoUtente())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(getAlgorithm());
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public JwtDto verifyToken(String jwt) {
        var decodedJwt = JWT.require(getAlgorithm())
                .build()
                .verify(jwt);

        var nome = decodedJwt.getClaim("nome").asString();
        var cognome = decodedJwt.getClaim("cognome").asString();
        var email = decodedJwt.getClaim("email").asString();
        var tipoUtente = decodedJwt.getClaim("tipoUtente").asString();

        return JwtDto.builder()
                .nome(nome)
                .cognome(cognome)
                .email(email)
                .tipoUtente(tipoUtente)
                .build();

    }
}

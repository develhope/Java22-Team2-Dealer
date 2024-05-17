package com.develhope.spring.DTOs.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDto {

    private String nome;
    private String cognome;
    private String email;
    private String tipoUtente;

}

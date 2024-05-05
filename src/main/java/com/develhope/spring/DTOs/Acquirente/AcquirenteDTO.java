package com.develhope.spring.DTOs.Acquirente;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class AcquirenteDTO {

    private Long acquirenteId;
    private String nome;
    private String cognome;
    private long telefono;
    private String email;
    private String password;
}


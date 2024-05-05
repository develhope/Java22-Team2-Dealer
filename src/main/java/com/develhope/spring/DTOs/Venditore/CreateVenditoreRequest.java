package com.develhope.spring.DTOs.Venditore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateVenditoreRequest {

    private String nome;
    private String cognome;
    private long telefono;
    private String email;
    private String password;
}

package com.develhope.spring.DTOs.Venditore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VenditoreDTO {

    private Long venditoreId;
    private String nome;
    private String cognome;
    private long telefono;
    private String email;
    private String password;
}
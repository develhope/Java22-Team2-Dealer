package com.develhope.spring.Features.DTOs.Venditore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVenditoreRequest {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

}

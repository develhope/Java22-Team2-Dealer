package com.develhope.spring.Features.DTOs.Acquirente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcquirenteRequest {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

}

package com.develhope.spring.Features.DTOs.Acquirente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAcquirenteRequest {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

}

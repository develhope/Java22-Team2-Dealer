package com.develhope.spring.Features.DTOs.Amministratore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAmministratoreRequest {

    private String nome;
    private String cognome;
    private String email;
    private String password;

}

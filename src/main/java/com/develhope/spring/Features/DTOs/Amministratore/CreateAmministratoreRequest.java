package com.develhope.spring.Features.DTOs.Amministratore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAmministratoreRequest {

    private String nome;
    private String cognome;
    private String email;
    private String password;

}

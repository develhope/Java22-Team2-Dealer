package com.develhope.spring.Features.DTOs.Acquirente;

import jakarta.persistence.Column;
import lombok.*;

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

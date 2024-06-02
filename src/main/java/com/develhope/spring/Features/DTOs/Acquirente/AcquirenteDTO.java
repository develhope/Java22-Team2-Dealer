package com.develhope.spring.Features.DTOs.Acquirente;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquirenteDTO {

    private Long acquirenteId;

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

}

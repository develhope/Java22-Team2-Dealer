package com.develhope.spring.Features.DTOs.Amministratore;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmministratoreDTO {

    private Long amministratoreId;

    private String nome;
    private String cognome;
    private String email;
    private String password;

}

package com.develhope.spring.Features.Autentication.DTO.Request;

import com.develhope.spring.Features.User.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    private Role role;

}

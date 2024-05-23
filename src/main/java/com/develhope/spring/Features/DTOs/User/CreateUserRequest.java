package com.develhope.spring.Features.DTOs.User;

import com.develhope.spring.Features.Entity.User.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    private Role role;

}

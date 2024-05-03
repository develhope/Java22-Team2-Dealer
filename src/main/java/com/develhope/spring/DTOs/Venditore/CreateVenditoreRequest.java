package com.develhope.spring.DTOs.Venditore;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class CreateVenditoreRequest {
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "telefono", nullable = false, unique = true)
    private long telefono;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}

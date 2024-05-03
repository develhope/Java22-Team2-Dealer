package com.develhope.spring.DTOs.Amministratore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class AmministratoreDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amministratoreId;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}

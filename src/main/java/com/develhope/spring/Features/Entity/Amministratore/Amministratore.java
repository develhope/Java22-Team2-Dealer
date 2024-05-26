package com.develhope.spring.Features.Entity.Amministratore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "amministratore")
public class Amministratore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amministratore_id", nullable = false)
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

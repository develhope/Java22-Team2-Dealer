package com.develhope.spring.Features.Entity.Venditore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venditore")
public class Venditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venditore_id", nullable = false)
    private Long venditoreId;

    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

}

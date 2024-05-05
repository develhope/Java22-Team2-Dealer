package com.develhope.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venditore")
@Getter
@Setter
public class Venditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venditore_id", nullable = false)
    private Long id;
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
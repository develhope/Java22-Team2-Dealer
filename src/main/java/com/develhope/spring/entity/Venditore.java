package com.develhope.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venditore")
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
    private String telefono;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "venditore")
    private List<Noleggio> noleggi;

    @OneToMany
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private List<Vehicle> vehicles;

}

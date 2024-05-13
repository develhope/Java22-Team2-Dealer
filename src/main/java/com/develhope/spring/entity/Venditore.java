package com.develhope.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "venditore")
public class Venditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venditore_id", nullable = false)
    @NonNull
    private Long id;
    @NonNull
    @Column(name = "nome", nullable = false)
    private String nome;
    @NonNull
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @NonNull
    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;
    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "venditore")
    private List<Noleggio> noleggi;

    @OneToMany
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private List<Vehicle> vehicles;

}

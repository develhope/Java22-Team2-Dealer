package com.develhope.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modello;
    private String cilindrata;
    private String colore;
    private String potenza;
    private String tipoDiCambio;
    private String annoImmatricolazione;
    private String alimentazione;
    private BigDecimal prezzo;
    private String accessori;
    private Boolean nuovoUsato;
    private Boolean ordinabile;
    private Boolean acquistabile;
    private Boolean nonDisponibile;

}
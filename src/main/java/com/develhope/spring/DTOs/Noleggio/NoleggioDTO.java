package com.develhope.spring.DTOs.Noleggio;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class NoleggioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noleggio_id")
    private Long noleggioId;

    @Column(name = "data_inizio", insertable = false, updatable = false)
    private OffsetDateTime dataInizio;
    @Column(name = "data_fine")
    private OffsetDateTime dataFine;
    @Column(name = "costo_giornaliero")
    private BigDecimal costoGiornaliero;
    @Column(name = "costo_totale")
    private BigDecimal costoTotale;
    @Column(name = "flag_pagato")
    private Boolean flagPagato;
    @Column(name = "id_veicolo_noleggiato")
    private Long idVeicoloNoleggiato;

    @ManyToOne
    @JoinColumn(name = "acquirente_id", nullable = false)
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "venditore_id", nullable = false)
    private Venditore venditore;
}

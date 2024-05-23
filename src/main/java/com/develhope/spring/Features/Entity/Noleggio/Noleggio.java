package com.develhope.spring.Features.Entity.Noleggio;

import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "noleggio")
public class Noleggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noleggio_id")
    private Long noleggioId;

    @Column(name = "data_inizio", updatable = false)
    private OffsetDateTime dataInizio;
    @Column(name = "data_fine", nullable = false)
    private OffsetDateTime dataFine;
    @Column(name = "costo_giornaliero", nullable = false)
    private BigDecimal costoGiornaliero;
    @Column(name = "costo_totale", nullable = false)
    private BigDecimal costoTotale;
    @Column(name = "flag_pagato", nullable = false)
    private Boolean flagPagato;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleId;

}
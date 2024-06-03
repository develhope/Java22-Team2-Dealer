package com.develhope.spring.Features.Entity.OrdineAcquisto;

import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "ordine_acquisto")
public class OrdineAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_acquisto_id", nullable = false)
    private Long ordineAcquistoId;

    @Column(name = "data_ordine_acquisto", nullable = false)
    private OffsetDateTime dataOrdineAcquisto;
    @Column(name = "data_consegna", nullable = false)
    private OffsetDateTime dataConsegna;
    @Column(name = "anticipo", nullable = false)
    private BigDecimal anticipo;
    @Column(name = "costo_totale", nullable = false)
    private BigDecimal costoTotale;
    @Column(name = "flag_pagato", nullable = false)
    private Boolean flagPagato;
    @Column(name = "stato_ordine_acquisto", nullable = false)
    private StatoOrdineAcquisto statoOrdineAcquisto;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}

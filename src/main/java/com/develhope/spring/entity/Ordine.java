package com.develhope.spring.entity;

import com.develhope.spring.entity.enums.StatoOrdine;
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
@Table(name = "Ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_id", nullable = false, unique = true)
    private Long ordineId;
    @Column(name = "data_ordine", nullable = false, unique = true)
    private OffsetDateTime dataOrdine;
    @Column(name = "data_consegna", nullable = false, unique = true)
    private OffsetDateTime dataConsegna;
    @Column(name = "anticipo", nullable = false)
    private BigDecimal anticipo;
    @Column(name = "flagPagato", nullable = false)
    private Boolean flagPagato;
    @Column(name = "statoOrdine", nullable = false)
    private StatoOrdine statoOrdine;

    @ManyToOne
    @JoinColumn(name = "acquirente_id", referencedColumnName = "acquirente_id", nullable = false)
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "venditore_id", referencedColumnName = "venditore_id", nullable = false)
    private Venditore venditore;
}

package com.develhope.spring.Noleggio;

import com.develhope.spring.Acquirente.Acquirente;
import com.develhope.spring.Veicolo.Veicolo;
import com.develhope.spring.Venditore.Venditore;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table
public class Noleggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noleggioId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataInizio;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataFine;
    @Column(nullable = false)
    private BigDecimal costoGiornaliero;
    @Column(nullable = false)
    private BigDecimal costoTotale;
    @Column(nullable = false)
    private Boolean flagPagato;
    @Column(nullable = false)
    private Long idVeicoloNoleggiato;

    @ManyToOne
    @JoinColumn(name = "acquirente_id")
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    @ManyToOne
    @JoinColumn(name = "venditore_id")
    private Venditore venditore;

}
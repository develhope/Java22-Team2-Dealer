package com.develhope.spring.Ordine;

import com.develhope.spring.Acquirente.Acquirente;
import com.develhope.spring.Veicolo.Veicolo;
import com.develhope.spring.Venditore.Venditore;
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
@Table
public class OrdineAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordineId;

    @Column(nullable = false)
    private OffsetDateTime dataOrdineAcquisto;
    @Column(nullable = false)
    private BigDecimal anticipo;
    @Column(nullable = false)
    private Boolean pagato;
    @Enumerated(value = EnumType.STRING)
    private StatoOrdine statoOrdine;
    @Column(nullable = false)
    private Long idVeicoloOrdinato;

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

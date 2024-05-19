package com.develhope.spring.entity;

import com.develhope.spring.entity.enums.StatoOrdineAcquisto;
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
@Table(name = "OrdineAcquisto")
public class OrdineAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_acquisto_id", nullable = false, unique = true)
    private Long ordineAcquistoId;

    @Column(name = "data_ordine", nullable = false, unique = true)
    private OffsetDateTime dataOrdineAcquisto;
    @Column(name = "data_consegna", nullable = false, unique = true)
    private OffsetDateTime dataConsegna;
    @Column(name = "anticipo", nullable = false)
    private BigDecimal anticipo;
    @Column(name = "costo_totale", nullable = false)
    private BigDecimal costoTotale;
    @Column(name = "flagPagato", nullable = false)
    private Boolean flagPagato;
    @Column(name = "statoOrdineAcquisto", nullable = false)
    private StatoOrdineAcquisto statoOrdineAcquisto;

    @ManyToOne
    @JoinColumn(name = "acquirente_id", referencedColumnName = "acquirente_id", nullable = false)
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "venditore_id", referencedColumnName = "venditore_id", nullable = false)
    private Venditore venditore;

    public OrdineAcquisto(OrdineAcquisto ordineAcquisto) {
        this.ordineAcquistoId = ordineAcquisto.getOrdineAcquistoId();
        this.dataOrdineAcquisto = ordineAcquisto.getDataOrdineAcquisto();
        this.dataConsegna = ordineAcquisto.getDataConsegna();
        this.anticipo = ordineAcquisto.getAnticipo();
        this.costoTotale = ordineAcquisto.getCostoTotale();
        this.flagPagato = ordineAcquisto.getFlagPagato();
        this.statoOrdineAcquisto = ordineAcquisto.getStatoOrdineAcquisto();
        this.acquirente = ordineAcquisto.getAcquirente();
        this.vehicle = ordineAcquisto.getVehicle();
        this.venditore = ordineAcquisto.getVenditore();
    }

}

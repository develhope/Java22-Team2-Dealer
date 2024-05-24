package com.develhope.spring.Features.Entity.Vehicle;

import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "marca", nullable = false)
    private String marca;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoVeicolo", nullable = false)
    private TipoVeicolo tipoVeicolo;
    @Column(name = "modello", nullable = false)
    private String modello;
    @Column(name = "cilindrata", nullable = false)
    private int cilindrata;
    @Column(name = "colore", nullable = false)
    private String colore;
    @Column(name = "potenza", nullable = false)
    private int potenza;
    @Column(name = "tipoDiCambio", nullable = false)
    private String tipoDiCambio;
    @Column(name = "annoImmatricolazione", nullable = false)
    private int annoImmatricolazione;
    @Column(name = "alimentazione", nullable = false)
    private String alimentazione;
    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;
    @Enumerated(EnumType.STRING)
    @Column(name = "allestimento", nullable = false, updatable = false)
    private Allestimento allestimento;
    @Column(name = "accessori", nullable = false)
    private String accessori;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicleCondition", nullable = false)
    private VehicleCondition vehicleCondition;
    @Column(name = "tipoOrdine", nullable = false)
    private TipoOrdineAcquisto tipoOrdineAcquisto;

}
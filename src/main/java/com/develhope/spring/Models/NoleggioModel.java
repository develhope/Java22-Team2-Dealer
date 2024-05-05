package com.develhope.spring.Models;

import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Setter
@Getter

public class NoleggioModel {

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

    public NoleggioModel(Long noleggioId, OffsetDateTime dataInizio, OffsetDateTime dataFine, BigDecimal costoGiornaliero, BigDecimal costoTotale, Boolean flagPagato, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {
        this.noleggioId = noleggioId;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costoGiornaliero = costoGiornaliero;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public NoleggioModel(OffsetDateTime dataInizio, OffsetDateTime dataFine, BigDecimal costoGiornaliero, BigDecimal costoTotale, Boolean flagPagato, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costoGiornaliero = costoGiornaliero;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public static NoleggioDTO modelToDto(NoleggioModel noleggioModel) {
        return new NoleggioDTO(noleggioModel.getNoleggioId(), noleggioModel.getDataInizio(),noleggioModel.getDataFine(),noleggioModel.getCostoGiornaliero(),noleggioModel.getCostoTotale(),noleggioModel.getFlagPagato(),noleggioModel.getAcquirente(),noleggioModel.getVehicle(),noleggioModel.getVenditore());
    }

    public static Noleggio modelToEntity(NoleggioModel noleggioModel) {
        return new Noleggio(noleggioModel.getNoleggioId(), noleggioModel.getDataInizio(),noleggioModel.getDataFine(),noleggioModel.getCostoGiornaliero(),noleggioModel.getCostoTotale(),noleggioModel.getFlagPagato(),noleggioModel.getAcquirente(),noleggioModel.getVehicle(),noleggioModel.getVenditore());
    }

    public static NoleggioModel entityToModel(Noleggio noleggio) {
        return new NoleggioModel(noleggio.getNoleggioId(), noleggio.getDataInizio(),noleggio.getDataFine(),noleggio.getCostoGiornaliero(),noleggio.getCostoTotale(),noleggio.getFlagPagato(),noleggio.getAcquirente(),noleggio.getVehicle(),noleggio.getVenditore());
    }

    public static NoleggioModel dtoToModel(NoleggioDTO noleggioDTO) {
        return new NoleggioModel(noleggioDTO.getNoleggioId(), noleggioDTO.getDataInizio(),noleggioDTO.getDataFine(),noleggioDTO.getCostoGiornaliero(),noleggioDTO.getCostoTotale(),noleggioDTO.getFlagPagato(),noleggioDTO.getAcquirente(),noleggioDTO.getVehicle(),noleggioDTO.getVenditore());
    }
}

package com.develhope.spring.Models;


import com.develhope.spring.DTOs.Ordine.OrdineAcquistoDTO;
import com.develhope.spring.entity.*;
import com.develhope.spring.entity.enums.StatoOrdineAcquisto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor


public class OrdineAcquistoModel {

    private Long ordineId;
    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdineAcquisto statoOrdineAcquisto;
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;

    public OrdineAcquistoModel(Long ordineId, OffsetDateTime dataOrdine, OffsetDateTime dataConsegna, BigDecimal anticipo, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.ordineId = ordineId;
        this.dataOrdine=dataOrdine;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public OrdineAcquistoModel(OffsetDateTime dataOrdine, OffsetDateTime dataConsegna, BigDecimal anticipo, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.dataOrdine=dataOrdine;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public static OrdineAcquistoDTO modelToDto(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquistoDTO(ordineAcquistoModel.getDataOrdine(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getOrdineId(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getAcquirente(), ordineAcquistoModel.getVehicle(), ordineAcquistoModel.getVenditore());
    }

    public static OrdineAcquisto modelToEntity(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquisto(ordineAcquistoModel.getOrdineId(), ordineAcquistoModel.getDataOrdine(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getAcquirente(), ordineAcquistoModel.getVehicle(), ordineAcquistoModel.getVenditore());
    }

    public static OrdineAcquistoModel entityToModel(OrdineAcquisto ordineAcquisto) {
        return new OrdineAcquistoModel(ordineAcquisto.getOrdineAcquistoId(), ordineAcquisto.getDataOrdineAcquisto(), ordineAcquisto.getDataConsegna(), ordineAcquisto.getAnticipo(), ordineAcquisto.getFlagPagato(), ordineAcquisto.getStatoOrdineAcquisto(), ordineAcquisto.getAcquirente(), ordineAcquisto.getVehicle(), ordineAcquisto.getVenditore());
    }

    public static OrdineAcquistoModel dtoToModel(OrdineAcquistoDTO ordineAcquistoDTO) {
        return new OrdineAcquistoModel(ordineAcquistoDTO.getOrdineId(), ordineAcquistoDTO.getDataOrdine(), ordineAcquistoDTO.getDataConsegna(), ordineAcquistoDTO.getAnticipo(), ordineAcquistoDTO.getFlagPagato(), ordineAcquistoDTO.getStatoOrdineAcquisto(), ordineAcquistoDTO.getAcquirente(), ordineAcquistoDTO.getVehicle(), ordineAcquistoDTO.getVenditore());
    }
}


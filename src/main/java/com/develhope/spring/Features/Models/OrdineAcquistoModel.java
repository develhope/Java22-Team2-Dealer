package com.develhope.spring.Features.Models;


import com.develhope.spring.Features.DTOs.OrdineAcquisto.OrdineAcquistoDTO;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class OrdineAcquistoModel {

    private Long ordineAcquistoId;

    private OffsetDateTime dataOrdineAcquisto;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private StatoOrdineAcquisto statoOrdineAcquisto;
    private Vehicle vehicle;

    public OrdineAcquistoModel(Long ordineAcquistoId, OffsetDateTime dataOrdineAcquisto, OffsetDateTime dataConsegna, BigDecimal anticipo, BigDecimal costoTotale, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Vehicle vehicle) {
        this.ordineAcquistoId = ordineAcquistoId;
        this.dataOrdineAcquisto = dataOrdineAcquisto;
        this.dataConsegna = dataConsegna;
        this.anticipo = anticipo;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.vehicle = vehicle;
    }

    public OrdineAcquistoModel(OffsetDateTime dataOrdineAcquisto, OffsetDateTime dataConsegna, BigDecimal anticipo, BigDecimal costoTotale, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Vehicle vehicle) {
        this.dataOrdineAcquisto = dataOrdineAcquisto;
        this.dataConsegna = dataConsegna;
        this.anticipo = anticipo;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.vehicle = vehicle;
    }

    public static OrdineAcquistoDTO modelToDto(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquistoDTO(ordineAcquistoModel.getOrdineAcquistoId(), ordineAcquistoModel.getDataOrdineAcquisto(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getCostoTotale(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getVehicle());
    }

    public static OrdineAcquisto modelToEntity(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquisto(ordineAcquistoModel.getOrdineAcquistoId(), ordineAcquistoModel.getDataOrdineAcquisto(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getCostoTotale(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getVehicle());
    }

    public static OrdineAcquistoModel entityToModel(OrdineAcquisto ordineAcquisto) {
        return new OrdineAcquistoModel(ordineAcquisto.getOrdineAcquistoId(), ordineAcquisto.getDataOrdineAcquisto(), ordineAcquisto.getDataConsegna(), ordineAcquisto.getAnticipo(), ordineAcquisto.getCostoTotale(), ordineAcquisto.getFlagPagato(), ordineAcquisto.getStatoOrdineAcquisto(), ordineAcquisto.getVehicle());
    }

    public static OrdineAcquistoModel dtoToModel(OrdineAcquistoDTO ordineAcquistoDTO) {
        return new OrdineAcquistoModel(ordineAcquistoDTO.getOrdineAcquistoId(), ordineAcquistoDTO.getDataOrdineAcquisto(), ordineAcquistoDTO.getDataConsegna(), ordineAcquistoDTO.getAnticipo(), ordineAcquistoDTO.getCostoTotale(), ordineAcquistoDTO.getFlagPagato(), ordineAcquistoDTO.getStatoOrdineAcquisto(), ordineAcquistoDTO.getVehicle());
    }
}

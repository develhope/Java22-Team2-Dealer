package com.develhope.spring.Models;


import com.develhope.spring.DTOs.OrdineAcquisto.OrdineAcquistoDTO;
import com.develhope.spring.entity.*;
import com.develhope.spring.entity.enums.StatoOrdineAcquisto;
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
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;

    public OrdineAcquistoModel(Long ordineAcquistoId, OffsetDateTime dataOrdineAcquisto, OffsetDateTime dataConsegna, BigDecimal anticipo, BigDecimal costoTotale, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.ordineAcquistoId = ordineAcquistoId;
        this.dataOrdineAcquisto = dataOrdineAcquisto;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public OrdineAcquistoModel(OffsetDateTime dataOrdineAcquisto, OffsetDateTime dataConsegna, BigDecimal anticipo, BigDecimal costoTotale, Boolean flagPagato, StatoOrdineAcquisto statoOrdineAcquisto, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.dataOrdineAcquisto = dataOrdineAcquisto;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.statoOrdineAcquisto = statoOrdineAcquisto;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public static OrdineAcquistoDTO modelToDto(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquistoDTO(ordineAcquistoModel.getOrdineAcquistoId(), ordineAcquistoModel.getDataOrdineAcquisto(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getCostoTotale(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getAcquirente(), ordineAcquistoModel.getVehicle(), ordineAcquistoModel.getVenditore());
    }

    public static OrdineAcquisto modelToEntity(OrdineAcquistoModel ordineAcquistoModel) {
        return new OrdineAcquisto(ordineAcquistoModel.getOrdineAcquistoId(), ordineAcquistoModel.getDataOrdineAcquisto(), ordineAcquistoModel.getDataConsegna(), ordineAcquistoModel.getAnticipo(), ordineAcquistoModel.getCostoTotale(), ordineAcquistoModel.getFlagPagato(), ordineAcquistoModel.getStatoOrdineAcquisto(), ordineAcquistoModel.getAcquirente(), ordineAcquistoModel.getVehicle(), ordineAcquistoModel.getVenditore());
    }

    public static OrdineAcquistoModel entityToModel(OrdineAcquisto ordineAcquisto) {
        return new OrdineAcquistoModel(ordineAcquisto.getOrdineAcquistoId(), ordineAcquisto.getDataOrdineAcquisto(), ordineAcquisto.getDataConsegna(), ordineAcquisto.getAnticipo(), ordineAcquisto.getCostoTotale(), ordineAcquisto.getFlagPagato(), ordineAcquisto.getStatoOrdineAcquisto(), ordineAcquisto.getAcquirente(), ordineAcquisto.getVehicle(), ordineAcquisto.getVenditore());
    }

    public static OrdineAcquistoModel dtoToModel(OrdineAcquistoDTO ordineAcquistoDTO) {
        return new OrdineAcquistoModel(ordineAcquistoDTO.getOrdineAcquistoId(), ordineAcquistoDTO.getDataOrdine(), ordineAcquistoDTO.getDataConsegna(), ordineAcquistoDTO.getAnticipo(), ordineAcquistoDTO.getCostoTotale(), ordineAcquistoDTO.getFlagPagato(), ordineAcquistoDTO.getStatoOrdineAcquisto(), ordineAcquistoDTO.getAcquirente(), ordineAcquistoDTO.getVehicle(), ordineAcquistoDTO.getVenditore());
    }
}


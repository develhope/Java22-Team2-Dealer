package com.develhope.spring.Models;


import com.develhope.spring.DTOs.Ordine.OrdineDTO;
import com.develhope.spring.entity.*;
import com.develhope.spring.entity.enums.StatoOrdine;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor


public class OrdineModel {

    private Long ordineId;
    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;

    public OrdineModel(Long ordineId,OffsetDateTime dataOrdine, OffsetDateTime dataConsegna, BigDecimal anticipo, Boolean flagPagato, StatoOrdine statoOrdine, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.ordineId = ordineId;
        this.dataOrdine=dataOrdine;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdine = statoOrdine;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public OrdineModel(OffsetDateTime dataOrdine, OffsetDateTime dataConsegna, BigDecimal anticipo, Boolean flagPagato, StatoOrdine statoOrdine, Acquirente acquirente, Vehicle vehicle, Venditore venditore) {

        this.dataOrdine=dataOrdine;
        this.dataConsegna=dataConsegna;
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdine = statoOrdine;
        this.acquirente = acquirente;
        this.vehicle = vehicle;
        this.venditore = venditore;
    }

    public static OrdineDTO modelToDto(OrdineModel ordineModel) {
        return new OrdineDTO(ordineModel.getDataOrdine(), ordineModel.getDataConsegna(),ordineModel.getOrdineId(),ordineModel.getAnticipo(),ordineModel.getFlagPagato(),ordineModel.getStatoOrdine(),ordineModel.getAcquirente(),ordineModel.getVehicle(),ordineModel.getVenditore());
    }

    public static Ordine modelToEntity(OrdineModel ordineModel) {
        return new Ordine(ordineModel.getOrdineId(), ordineModel.getDataOrdine(),ordineModel.getDataConsegna(),ordineModel.getAnticipo(),ordineModel.getFlagPagato(),ordineModel.getStatoOrdine(),ordineModel.getAcquirente(),ordineModel.getVehicle(),ordineModel.getVenditore());
    }

    public static OrdineModel entityToModel(Ordine ordine) {
        return new OrdineModel(ordine.getOrdineId(), ordine.getDataOrdine(),ordine.getDataConsegna(),ordine.getAnticipo(),ordine.getFlagPagato(),ordine.getStatoOrdine(),ordine.getAcquirente(),ordine.getVehicle(),ordine.getVenditore());
    }

    public static OrdineModel dtoToModel(OrdineDTO ordineDTO) {
        return new OrdineModel(ordineDTO.getOrdineId(), ordineDTO.getDataOrdine(),ordineDTO.getDataConsegna(),ordineDTO.getAnticipo(),ordineDTO.getFlagPagato(),ordineDTO.getStatoOrdine(),ordineDTO.getAcquirente(),ordineDTO.getVehicle(),ordineDTO.getVenditore());
    }
}


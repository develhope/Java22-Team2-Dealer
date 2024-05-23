package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class NoleggioModel {

    private Long noleggioId;

    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private BigDecimal costoGiornaliero;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private Vehicle vehicle;

    public NoleggioModel(Long noleggioId, OffsetDateTime dataInizio, OffsetDateTime dataFine, BigDecimal costoGiornaliero, BigDecimal costoTotale, Boolean flagPagato, Vehicle vehicle) {
        this.noleggioId = noleggioId;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costoGiornaliero = costoGiornaliero;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.vehicle = vehicle;
    }

    public NoleggioModel(OffsetDateTime dataInizio, OffsetDateTime dataFine, BigDecimal costoGiornaliero, BigDecimal costoTotale, Boolean flagPagato, Vehicle vehicle) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costoGiornaliero = costoGiornaliero;
        this.costoTotale = costoTotale;
        this.flagPagato = flagPagato;
        this.vehicle = vehicle;
    }

    public static NoleggioDTO modelToDto(NoleggioModel noleggioModel) {
        return new NoleggioDTO(noleggioModel.getNoleggioId(), noleggioModel.getDataInizio(), noleggioModel.getDataFine(), noleggioModel.getCostoGiornaliero(), noleggioModel.getCostoTotale(), noleggioModel.getFlagPagato(), noleggioModel.getVehicle());
    }

    public static Noleggio modelToEntity(NoleggioModel noleggioModel) {
        return new Noleggio(noleggioModel.getNoleggioId(), noleggioModel.getDataInizio(), noleggioModel.getDataFine(), noleggioModel.getCostoGiornaliero(), noleggioModel.getCostoTotale(), noleggioModel.getFlagPagato(), noleggioModel.getVehicle());
    }

    public static NoleggioModel entityToModel(Noleggio noleggio) {
        return new NoleggioModel(noleggio.getNoleggioId(), noleggio.getDataInizio(), noleggio.getDataFine(), noleggio.getCostoGiornaliero(), noleggio.getCostoTotale(), noleggio.getFlagPagato(), noleggio.getVehicleId());
    }

    public static NoleggioModel dtoToModel(NoleggioDTO noleggioDTO) {
        return new NoleggioModel(noleggioDTO.getNoleggioId(), noleggioDTO.getDataInizio(), noleggioDTO.getDataFine(), noleggioDTO.getCostoGiornaliero(), noleggioDTO.getCostoTotale(), noleggioDTO.getFlagPagato(), noleggioDTO.getVehicle());
    }
}

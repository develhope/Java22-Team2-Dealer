package com.develhope.spring.DTOs.OrdineAcquisto;

import com.develhope.spring.entity.enums.StatoOrdineAcquisto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrdineAcquistoRequest {

    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private StatoOrdineAcquisto statoOrdineAcquisto;
    private Long acquirenteId;
    private Long vehicleId;
    private Long venditoreId;

}
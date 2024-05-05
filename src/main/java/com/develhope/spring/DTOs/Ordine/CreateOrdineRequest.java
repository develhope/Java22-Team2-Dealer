package com.develhope.spring.DTOs.Ordine;

import com.develhope.spring.entity.enums.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateOrdineRequest {

    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Long acquirenteId;
    private Long vehicleId;
    private Long venditoreId;
}
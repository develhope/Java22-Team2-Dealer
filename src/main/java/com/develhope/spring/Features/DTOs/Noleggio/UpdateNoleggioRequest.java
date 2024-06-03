package com.develhope.spring.Features.DTOs.Noleggio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoleggioRequest {

    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private BigDecimal costoGiornaliero;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private Long vehicleId;
    private Long venditoreId;

}

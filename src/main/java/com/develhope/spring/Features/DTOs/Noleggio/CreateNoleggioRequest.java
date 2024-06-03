package com.develhope.spring.Features.DTOs.Noleggio;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoleggioRequest {

    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private BigDecimal costoGiornaliero;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private Long vehicleId;
    private Long venditoreId;

}

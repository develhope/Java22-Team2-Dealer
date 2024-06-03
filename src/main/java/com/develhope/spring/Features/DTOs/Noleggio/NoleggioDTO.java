package com.develhope.spring.Features.DTOs.Noleggio;

import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoleggioDTO {

    private Long noleggioId;

    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private BigDecimal costoGiornaliero;
    private BigDecimal costoTotale;
    private Boolean flagPagato;
    private Vehicle vehicle;
    private User venditore;

}

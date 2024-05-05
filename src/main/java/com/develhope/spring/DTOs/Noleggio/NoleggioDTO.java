package com.develhope.spring.DTOs.Noleggio;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import lombok.*;

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
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;
}

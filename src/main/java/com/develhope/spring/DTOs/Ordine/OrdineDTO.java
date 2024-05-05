package com.develhope.spring.DTOs.Ordine;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.entity.enums.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdineDTO {

    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private Long ordineId;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;
}
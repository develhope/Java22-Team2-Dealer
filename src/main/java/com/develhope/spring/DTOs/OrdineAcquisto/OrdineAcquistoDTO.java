package com.develhope.spring.DTOs.OrdineAcquisto;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.entity.enums.StatoOrdineAcquisto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineAcquistoDTO {

    private Long ordineAcquistoId;

    private OffsetDateTime dataOrdine;
    private OffsetDateTime dataConsegna;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdineAcquisto statoOrdineAcquisto;
    private Acquirente acquirente;
    private Vehicle vehicle;
    private Venditore venditore;

}
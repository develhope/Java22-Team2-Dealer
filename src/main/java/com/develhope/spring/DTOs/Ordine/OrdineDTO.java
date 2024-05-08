package com.develhope.spring.DTOs.Ordine;

import com.develhope.spring.entity.enums.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrdineDTO {
    private Long ordineId;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Long idVeicoloOrdinato;
}

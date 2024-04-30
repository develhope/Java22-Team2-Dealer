package com.develhope.spring.entity;

import com.develhope.spring.entity.enums.StatoOrdine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordineId;

    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Long idVeicoloOrdinato;
}

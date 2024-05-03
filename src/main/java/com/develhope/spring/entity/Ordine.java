package com.develhope.spring.entity;

import com.develhope.spring.entity.enums.StatoOrdine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_id", nullable = false, unique = true)
    private Long ordineId;
    @Column(name = "anticipo", nullable = false)
    private BigDecimal anticipo;
    @Column(name = "flagPagato", nullable = false)
    private Boolean flagPagato;
    @Column(name = "statoOrdine", nullable = false)
    private StatoOrdine statoOrdine;
    @Column(name = "idVeicoloOrdinato", nullable = false)
    private Long idVeicoloOrdinato;
}

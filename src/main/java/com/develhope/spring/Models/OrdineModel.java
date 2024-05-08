package com.develhope.spring.Models;

import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Ordine.CreateOrdineRequest;
import com.develhope.spring.DTOs.Ordine.OrdineDTO;
import com.develhope.spring.entity.Ordine;
import com.develhope.spring.entity.enums.StatoOrdine;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrdineModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordineId;
    private BigDecimal anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Long idVeicoloOrdinato;

    public OrdineModel(Long ordineId, BigDecimal anticipo, Boolean flagPagato, StatoOrdine statoOrdine, Long idVeicoloOrdinato) {
        this.ordineId = ordineId;
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdine = statoOrdine;
        this.idVeicoloOrdinato = idVeicoloOrdinato;
    }

    public OrdineModel(BigDecimal anticipo, Boolean flagPagato, StatoOrdine statoOrdine, Long idVeicoloOrdinato) {
        this.anticipo = anticipo;
        this.flagPagato = flagPagato;
        this.statoOrdine = statoOrdine;
        this.idVeicoloOrdinato = idVeicoloOrdinato;
    }
    public static OrdineDTO modelToDto(OrdineModel ordineModel) {
        return new OrdineDTO(ordineModel.getOrdineId(), ordineModel.getAnticipo(), ordineModel.getFlagPagato(), ordineModel.getStatoOrdine(), ordineModel.getIdVeicoloOrdinato());
    }

    public static Ordine modelToEntity(OrdineModel ordineModel) {
        return new Ordine(ordineModel.getOrdineId(), ordineModel.getAnticipo(), ordineModel.getFlagPagato(), ordineModel.getStatoOrdine(), ordineModel.getIdVeicoloOrdinato());
    }

    public static OrdineModel entityToModel(Ordine ordine) {
        return new OrdineModel(ordine.getOrdineId(), ordine.getAnticipo(), ordine.getFlagPagato(), ordine.getStatoOrdine(), ordine.getIdVeicoloOrdinato());
    }

    public static OrdineModel dtoToModel(CreateOrdineRequest createOrdineRequest) {
        return new OrdineModel(createOrdineRequest.getAnticipo(), createOrdineRequest.getFlagPagato(), createOrdineRequest.getStatoOrdine(), createOrdineRequest.getIdVeicoloOrdinato());
    }
}

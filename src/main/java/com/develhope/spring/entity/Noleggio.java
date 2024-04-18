package com.develhope.spring.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Noleggio {
    private OffsetDateTime dataInizio;
    private OffsetDateTime dataFine;
    private BigDecimal costoGiornaliero;
    private Boolean flagPagato;
    private Veicolo veicoloNoleggiato;

    public Noleggio(OffsetDateTime dataInizio, OffsetDateTime dataFine, BigDecimal costoGiornaliero, Boolean flagPagato, Veicolo veicoloNoleggiato) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.costoGiornaliero = costoGiornaliero;
        this.flagPagato = flagPagato;
        this.veicoloNoleggiato = veicoloNoleggiato;
    }

    public OffsetDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(OffsetDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public OffsetDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(OffsetDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public BigDecimal getCostoGiornaliero() {
        return costoGiornaliero;
    }

    public void setCostoGiornaliero(BigDecimal costoGiornaliero) {
        this.costoGiornaliero = costoGiornaliero;
    }

    public Boolean getFlagPagato() {
        return flagPagato;
    }

    public void setFlagPagato(Boolean flagPagato) {
        this.flagPagato = flagPagato;
    }

    public Veicolo getVeicoloNoleggiato() {
        return veicoloNoleggiato;
    }

    public void setVeicoloNoleggiato(Veicolo veicoloNoleggiato) {
        this.veicoloNoleggiato = veicoloNoleggiato;
    }

    @Override
    public String toString() {
        return "Noleggio{" +
                "dataInizioNoleggio=" + dataInizio +
                ", dataFineNoleggio=" + dataFine +
                ", costoGiornalieroNoleggio=" + costoGiornaliero +
                ", flagPagato=" + flagPagato +
                ", veicoloNoleggiato=" + veicoloNoleggiato +
                '}';
    }
}

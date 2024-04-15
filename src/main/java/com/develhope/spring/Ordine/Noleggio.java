package com.develhope.spring.Ordine;

import com.develhope.spring.Veicoli.Veicoli;

import java.time.LocalDate;

public class Noleggio {
    private LocalDate dataInizioNoleggio;
    private LocalDate dataFineNoleggio;
    private Double costoGiornalieroNoleggio;
    private Double costoTotaleNoleggio;
    private Boolean flagPagato;
    private Veicoli veicoloNoleggiato;

    public Noleggio(LocalDate dataInizioNoleggio, LocalDate dataFineNoleggio, Double costoGiornalieroNoleggio, Double costoTotaleNoleggio, Boolean flagPagato, Veicoli veicoloNoleggiato) {
        this.dataInizioNoleggio = dataInizioNoleggio;
        this.dataFineNoleggio = dataFineNoleggio;
        this.costoGiornalieroNoleggio = costoGiornalieroNoleggio;
        this.costoTotaleNoleggio = costoTotaleNoleggio;
        this.flagPagato = flagPagato;
        this.veicoloNoleggiato = veicoloNoleggiato;
    }

    public LocalDate getDataInizioNoleggio() {
        return dataInizioNoleggio;
    }

    public void setDataInizioNoleggio(LocalDate dataInizioNoleggio) {
        this.dataInizioNoleggio = dataInizioNoleggio;
    }

    public LocalDate getDataFineNoleggio() {
        return dataFineNoleggio;
    }

    public void setDataFineNoleggio(LocalDate dataFineNoleggio) {
        this.dataFineNoleggio = dataFineNoleggio;
    }

    public Double getCostoGiornalieroNoleggio() {
        return costoGiornalieroNoleggio;
    }

    public void setCostoGiornalieroNoleggio(Double costoGiornalieroNoleggio) {
        this.costoGiornalieroNoleggio = costoGiornalieroNoleggio;
    }

    public Double getCostoTotaleNoleggio() {
        return costoTotaleNoleggio;
    }

    public void setCostoTotaleNoleggio(Double costoTotaleNoleggio) {
        this.costoTotaleNoleggio = costoTotaleNoleggio;
    }

    public Boolean getFlagPagato() {
        return flagPagato;
    }

    public void setFlagPagato(Boolean flagPagato) {
        this.flagPagato = flagPagato;
    }

    public Veicoli getVeicoloNoleggiato() {
        return veicoloNoleggiato;
    }

    public void setVeicoloNoleggiato(Veicoli veicoloNoleggiato) {
        this.veicoloNoleggiato = veicoloNoleggiato;
    }

    @Override
    public String toString() {
        return "Noleggio{" +
                "dataInizioNoleggio=" + dataInizioNoleggio +
                ", dataFineNoleggio=" + dataFineNoleggio +
                ", costoGiornalieroNoleggio=" + costoGiornalieroNoleggio +
                ", costoTotaleNoleggio=" + costoTotaleNoleggio +
                ", flagPagato=" + flagPagato +
                ", veicoloNoleggiato=" + veicoloNoleggiato +
                '}';
    }
}

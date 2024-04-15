package com.develhope.spring.Ordine;

import com.develhope.spring.Veicoli.Veicoli;

public class Ordine {
    private Double anticipo;
    private Boolean flagPagato;
    private StatoOrdineEnum statoOrdine;
    private Veicoli veicoloOrdinato;

    public Ordine(Double anticipo, Boolean pagato, StatoOrdineEnum statoOrdine, Veicoli veicoloOrdinato) {
        this.anticipo = anticipo;
        this.flagPagato = pagato;
        this.statoOrdine = statoOrdine;
        this.veicoloOrdinato = veicoloOrdinato;
    }

    public Double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(Double anticipo) {
        this.anticipo = anticipo;
    }

    public Boolean getFlagPagato() {
        return flagPagato;
    }

    public void setFlagPagato(Boolean flagPagato) {
        this.flagPagato = flagPagato;
    }

    public StatoOrdineEnum getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdineEnum statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Veicoli getVeicoloOrdinato() {
        return veicoloOrdinato;
    }

    public void setVeicoloOrdinato(Veicoli veicoloOrdinato) {
        this.veicoloOrdinato = veicoloOrdinato;
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "anticipo=" + anticipo +
                ", pagato=" + flagPagato +
                ", statoOrdine=" + statoOrdine +
                ", VeicoloOrdinato=" + veicoloOrdinato +
                '}';
    }
}

package com.develhope.spring.entity;

public class Ordine {
    private Double anticipo;
    private Boolean flagPagato;
    private StatoOrdine statoOrdine;
    private Veicolo veicoloOrdinato;

    public Ordine(Double anticipo, Boolean pagato, StatoOrdine statoOrdine, Veicolo veicoloOrdinato) {
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

    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public Veicolo getVeicoloOrdinato() {
        return veicoloOrdinato;
    }

    public void setVeicoloOrdinato(Veicolo veicoloOrdinato) {
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

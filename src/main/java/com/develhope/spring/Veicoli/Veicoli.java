package com.develhope.spring.Veicoli;

import java.math.BigDecimal;

public class Veicoli {

    private String marca;
    private String modello;
    private String cilindrata;
    private String colore;
    private String potenza;
    private String tipoDiCambio;
    private String annoImmatricolazione;
    private String alimentazione;
    private BigDecimal prezzo;
    private String accessori;
    private boolean nuovoUsato;
    private boolean ordinabile;
    private boolean acquistabile;
    private boolean nonDisponibile;

    public Veicoli(String marca, String modello, String cilindrata, String colore, String potenza, String tipoDiCambio, String annoImmatricolazione, String alimentazione, BigDecimal prezzo, String accessori, boolean nuovoUsato, boolean ordinabile, boolean acquistabile, boolean nonDisponibile) {
        this.marca = marca;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.colore = colore;
        this.potenza = potenza;
        this.tipoDiCambio = tipoDiCambio;
        this.annoImmatricolazione = annoImmatricolazione;
        this.alimentazione = alimentazione;
        this.prezzo = prezzo;
        this.accessori = accessori;
        this.nuovoUsato = nuovoUsato;
        this.ordinabile = ordinabile;
        this.acquistabile = acquistabile;
        this.nonDisponibile = nonDisponibile;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(String cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getPotenza() {
        return potenza;
    }

    public void setPotenza(String potenza) {
        this.potenza = potenza;
    }

    public String getTipoDiCambio() {
        return tipoDiCambio;
    }

    public void setTipoDiCambio(String tipoDiCambio) {
        this.tipoDiCambio = tipoDiCambio;
    }

    public String getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(String annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {
        this.alimentazione = alimentazione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public String getAccessori() {
        return accessori;
    }

    public void setAccessori(String accessori) {
        this.accessori = accessori;
    }

    public boolean isNuovoUsato() {
        return nuovoUsato;
    }

    public void setNuovoUsato(boolean nuovoUsato) {
        this.nuovoUsato = nuovoUsato;
    }

    public boolean isOrdinabile() {
        return ordinabile;
    }

    public void setOrdinabile(boolean ordinabile) {
        this.ordinabile = ordinabile;
    }

    public boolean isAcquistabile() {
        return acquistabile;
    }

    public void setAcquistabile(boolean acquistabile) {
        this.acquistabile = acquistabile;
    }

    public boolean isNonDisponibile() {
        return nonDisponibile;
    }

    public void setNonDisponibile(boolean nonDisponibile) {
        this.nonDisponibile = nonDisponibile;
    }

    @Override
    public String toString() {
        return "Veicoli{" +
                "marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", cilindrata='" + cilindrata + '\'' +
                ", colore='" + colore + '\'' +
                ", potenza='" + potenza + '\'' +
                ", tipoDiCambio='" + tipoDiCambio + '\'' +
                ", annoImmatricolazione='" + annoImmatricolazione + '\'' +
                ", alimentazione='" + alimentazione + '\'' +
                ", prezzo=" + prezzo +
                ", accessori='" + accessori + '\'' +
                ", nuovoUsato=" + nuovoUsato +
                ", ordinabile=" + ordinabile +
                ", acquistabile=" + acquistabile +
                ", nonDisp=" + nonDisponibile +
                '}';
    }
}

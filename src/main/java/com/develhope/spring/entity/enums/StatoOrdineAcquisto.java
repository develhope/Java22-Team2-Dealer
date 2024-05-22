package com.develhope.spring.entity.enums;

public enum StatoOrdineAcquisto {
    ELIMINATO,
    IN_SOSPESO,
    IN_LAVORAZIONE,
    SPEDITO,
    IN_CONSEGNA,
    CONSEGNATO,
    UNDEFINED;

    public static StatoOrdineAcquisto convertStringToStatusOrderPurchase(String statusOrderPurchase) {
        return switch (statusOrderPurchase.toLowerCase()) {
            case "deleted" -> StatoOrdineAcquisto.ELIMINATO;
            case "suspended" -> StatoOrdineAcquisto.IN_SOSPESO;
            case "progress" -> StatoOrdineAcquisto.IN_LAVORAZIONE;
            case "shipped" -> StatoOrdineAcquisto.SPEDITO;
            case "dispatched" -> StatoOrdineAcquisto.IN_CONSEGNA;
            case "delivered" -> StatoOrdineAcquisto.CONSEGNATO;
            default -> UNDEFINED;
        };
    }
}

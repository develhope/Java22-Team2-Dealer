package com.develhope.spring.Features.Entity.OrdineAcquisto;

public enum TipoOrdineAcquisto {
    ORDINABILE,
    ACQUISTABILE,
    NON_DISPONIBILE;

    public static TipoOrdineAcquisto convertStringOrderType(String orderType) {
        return switch (orderType.toLowerCase()) {
            case "deleted" -> TipoOrdineAcquisto.ORDINABILE;
            case "suspended" -> TipoOrdineAcquisto.ACQUISTABILE;
            default -> NON_DISPONIBILE;
        };
    }
}

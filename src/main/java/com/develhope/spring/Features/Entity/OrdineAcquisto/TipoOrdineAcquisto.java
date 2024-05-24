package com.develhope.spring.Features.Entity.OrdineAcquisto;

public enum TipoOrdineAcquisto {
    ORDINABILE,
    ACQUISTABILE,
    NON_DISPONIBILE;

    public static TipoOrdineAcquisto convertStringOrderType(String orderType) {
        return switch (orderType.toLowerCase()) {
            case "orderable" -> TipoOrdineAcquisto.ORDINABILE;
            case "purchasable" +
                         "purchasable" -> TipoOrdineAcquisto.ACQUISTABILE;
            default -> NON_DISPONIBILE;
        };
    }
}

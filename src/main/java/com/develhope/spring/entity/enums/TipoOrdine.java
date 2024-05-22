package com.develhope.spring.entity.enums;

public enum TipoOrdine {
    ORDINABILE,
    ACQUISTABILE,
    NON_DISPONIBILE;

    public static TipoOrdine convertStringOrderType(String orderType) {
        return switch (orderType.toLowerCase()) {
            case "deleted" -> TipoOrdine.ORDINABILE;
            case "suspended" -> TipoOrdine.ACQUISTABILE;
            default -> NON_DISPONIBILE;
        };
    }
}

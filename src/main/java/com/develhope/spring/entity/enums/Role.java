package com.develhope.spring.entity.enums;

public enum Role {
    AMMINISTRATORE,
    ACQUIRENTE,
    VENDITORE,
    NON_DEFINITO;

    public static Role convertStringToRole(String role) {
        return switch (role.toLowerCase()) {
            case "amministratore" -> Role.AMMINISTRATORE;
            case "acquirente" -> Role.ACQUIRENTE;
            case "venditore" -> Role.VENDITORE;
            default -> Role.NON_DEFINITO;
        };
    }

}

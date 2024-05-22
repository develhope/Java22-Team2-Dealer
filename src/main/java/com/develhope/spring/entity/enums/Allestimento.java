package com.develhope.spring.entity.enums;

public enum Allestimento {
    BASE,
    BUSINESS,
    SPORT,
    UNDEFINED;

    public static Allestimento convertStringArrangement(String arrangement) {
        return switch (arrangement.toLowerCase()) {
            case "base" -> Allestimento.BASE;
            case "business" -> Allestimento.BUSINESS;
            case "sport" -> Allestimento.SPORT;
            default -> UNDEFINED;
        };
    }
}

package com.develhope.spring.entity.enums;


public enum TipoVeicolo {
    AUTO,
    MOTO,
    SCOOTER,
    FURGONE,
    UNDEFINED;

    public static TipoVeicolo convertStringToVehicleType(String vehicleType) {
        return switch (vehicleType.toLowerCase()) {
            case "auto" -> TipoVeicolo.AUTO;
            case "moto" -> TipoVeicolo.MOTO;
            case "scooter" -> TipoVeicolo.SCOOTER;
            case "furgone" -> TipoVeicolo.FURGONE;
            default -> UNDEFINED;
        };
    }
}

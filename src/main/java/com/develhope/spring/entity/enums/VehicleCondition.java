package com.develhope.spring.entity.enums;

public enum VehicleCondition {
    NUOVO,
    USATO,
    UNDEFINED;

    public static VehicleCondition convertStringVehicleCondition(String vehicleCondition) {
        return switch (vehicleCondition.toLowerCase()) {
            case "new" -> VehicleCondition.NUOVO;
            case "used" -> VehicleCondition.USATO;
            default -> UNDEFINED;
        };
    }
}

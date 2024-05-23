package com.develhope.spring.Features.Entity.Vehicle;

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

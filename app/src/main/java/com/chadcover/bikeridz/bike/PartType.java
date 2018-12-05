package com.chadcover.bikeridz.bike;

public enum PartType {
    BIKE("Bike"),
    BRAKE("Brake"),
    CHAIN("Chain"),
    CRANKSET("Crankset"),
    DEREILLEUR("Dereilleur"),
    FRAME("Frame"),
    HANDLEBARS("Handlebars"),
    SEAT("Seat"),
    TIRE("Tire"),
    WHEEL("Wheel");

    private String partName;

    PartType() {
        // empty constructor
    }

    PartType(String name) {
        this.partName = name;
    }

    public String getPartName() {
        return this.partName;
    }
}

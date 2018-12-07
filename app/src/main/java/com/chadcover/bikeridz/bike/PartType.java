package com.chadcover.bikeridz.bike;

public enum PartType {
    BIKE("Bike"),
    BRAKE("Brakes"),
    CHAIN("Chain"),
    CRANKSET("Crankset"),
    DEREILLEUR("Dereilleur"),
    FRAME("Frame"),
    HANDLEBARS("Handlebars"),
    SEAT("Seat"),
    TIRE("Tires"),
    WHEEL("Wheels");

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

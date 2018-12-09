package com.chadcover.bikeridz.bike;

public enum PartType {
    BIKE("Bike"),
    BRAKES("Brakes"),
    CHAIN("Chain"),
    CRANKSET("Crankset"),
    DEREILLEUR("Dereilleur"),
    FRAME("Frame"),
    HANDLEBARS("Handlebars"),
    SEAT("Seat"),
    TIRES("Tires"),
    WHEELS("Wheels");

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

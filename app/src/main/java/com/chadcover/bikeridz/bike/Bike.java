package com.chadcover.bikeridz.bike;

import java.util.ArrayList;
import java.util.List;

/**
 * Chad David Cover
 * November, 2018
 */


public class Bike extends Part {

    private int id;
    private String color;
    private String height;
    private String material;
    private String serialNumber;
    private BikeType type;

    public Bike() {
        super("Schwinn");
        this.color = "Tour de France Yellow";
        this.height = "59cm";
        this.material = "Carbon";
        this.type = BikeType.ROAD;
    }

    public Bike(int id, String manufacturer, String color, String height, String material, BikeType type) {
        super(manufacturer);
        this.id = id;
        this.color = color;
        this.height = height;
        this.material = material;
        this.type = type;
    }

    public Bike(int id, String name, String description, String manufacturer, String color,
                String height, String material, BikeType type, String serialNumber) {
        super(name, description, manufacturer);
        this.id = id;
        this.color = color;
        this.height = height;
        this.material = material;
        this.type = type;
        this.serialNumber = serialNumber;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BikeType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getTypeName();
    }

    public void setType(BikeType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper method to create sample bikes
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static List<Bike> createSampleBikes() {

        Bike bike1 = new Bike(0, "Work Bike", "My ride-to-work bike, a single-speed.","IRO", "Black", "58cm",
                "Steel", BikeType.COMMUTER, "XXXXXXXXXXXXXXX");
        Bike bike2 = new Bike(1, "Joy Ride", "My ride-all-day bike", "Litespeed", "Polished Steel", "60cm",
                "Titanium", BikeType.ROAD, "YYYYYYYYYYYYYYY");

        List<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);
        bikes.add(bike2);

        return bikes;
    }


}

package com.chadcover.bikeridz.bike;

import java.util.ArrayList;
import java.util.List;

/**
 * Chad David Cover
 * November, 2018
 */


public class Bike extends Part {

    private String color;
    private String height;
    private String material;
    private BikeType type;

    public Bike() {
        super("Schwinn");
        this.color = "Tour de France Yellow";
        this.height = "59cm";
        this.material = "Carbon";
        this.type = BikeType.ROAD;
    }

    public Bike(String manufacturer, String color, String height, String material, BikeType type) {
        super(manufacturer);
        this.color = color;
        this.height = height;
        this.material = material;
        this.type = type;
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

    public String getType() {
        return type.getTypeName();
    }

    public void setType(BikeType type) {
        this.type = type;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper method to create sample product list
    ///////////////////////////////////////////////////////////////////////////////////////////

    public List<Bike> addSampleBikes() {

        Bike bike1 = new Bike("IRO", "Black", "58cm",
                "Steel", BikeType.COMMUTER);
        Bike bike2 = new Bike("Litespeed", "Polished Steel", "60cm",
                "Titanium", BikeType.ROAD);

        List<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);
        bikes.add(bike2);

        return bikes;
    }

}

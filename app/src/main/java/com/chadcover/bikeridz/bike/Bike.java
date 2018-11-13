package com.chadcover.bikeridz.bike;

import java.lang.reflect.Array;

/**
 * Chad David Cover
 * November, 2018
 */


public class Bike extends Part {

    private String color;
    private String height;
    private String material;
    private BikeType type;
    private Part[] partsInventory;

    public Bike() {
        this.color = "Tour de France Yellow";
        this.height = "59cm";
        this.material = "carbon";
        this.type = BikeType.ROAD;
        this.partsInventory = new Part[100];
        for (int i = 0; i < partsInventory.length; i++) {
            this.partsInventory[i] = null;
        }
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

    public void setType(BikeType type) {
        this.type = type;
    }

    public Part[] getPartsInventory() {
        return partsInventory;
    }

    public void setPartsInventory(Part[] partsInventory) {
        this.partsInventory = partsInventory;
    }

    // helper functions
    public void addToInventory(Part[] inventory, Part part) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = part;
                break;
            }
        }
    }

}

package com.chadcover.bikeridz.bike;

public class Part {

    private String name;
    private String description;
    private double price;
    private int quantity;
    private String manufacturer;
    private PartType typeOfPart;

    public Part() {
        this.name = "new part";
        this.description = "new description";
        this.price = 42.42;
        this.quantity = 1;
        this.manufacturer = "Huffy";
        this.typeOfPart = PartType.BIKE;
    }

    public Part(String name, String description, double price, int quantity, String manufacturer, PartType partType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.typeOfPart = partType;
    }

    public Part(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PartType getTypeOfPart() {
        return typeOfPart;
    }

    public void setTypeOfPart(PartType typeOfPart) {
        this.typeOfPart = typeOfPart;
    }

}

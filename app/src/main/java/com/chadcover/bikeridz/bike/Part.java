package com.chadcover.bikeridz.bike;

public class Part {

    public Part() {
        this.name = "new part";
        this.description = "new description";
        this.price = 42.42;
        this.quantity = 1;
        this.manufacturer = "Huffy";
    }

    public Part(String name, String description, double price, int quantity, String manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    private String name;
    private String description;
    private double price;
    private int quantity;
    private String manufacturer;

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

}

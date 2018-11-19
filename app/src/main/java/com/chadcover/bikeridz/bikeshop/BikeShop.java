package com.chadcover.bikeridz.bikeshop;

public class BikeShop {

    private String name;
    private Address address;
    private Coords coords;
    private String phoneNumber;
    private Hours hours;


    public BikeShop() {
        this.name = "My Bikeshop";
        this.address = new Address();
        this.coords = new Coords();
        this.phoneNumber = "555.111.2222";
        this.hours = new Hours();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

}

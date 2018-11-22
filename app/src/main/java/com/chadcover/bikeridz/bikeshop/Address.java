package com.chadcover.bikeridz.bikeshop;

public class Address {

    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;
    private String fullAddress;

    public Address() {
        this.streetNumber = "42";
        this.streetName = "Galifray Blvd.";
        this.city = "Baltimore";
        this.state = "MD";
        this.zipCode = "21136";
        this.fullAddress = "42 Galifray Blvd. Baltimore, MDstree 21136";
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

}

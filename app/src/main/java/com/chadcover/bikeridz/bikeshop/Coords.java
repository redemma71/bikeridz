package com.chadcover.bikeridz.bikeshop;

import static java.lang.Double.NaN;

public class Coords {

    private double lat = NaN;
    private double lng = NaN;

    public Coords() {
        this.lat = 48.858093;
        this.lng = 2.294694;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}

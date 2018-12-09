package com.chadcover.bikeridz.mapquest;

public class Maneuver {

    protected Double distance;
    protected String narrative;
    protected String time;
    protected String iconUrl;
    protected int turnType;
    protected int direction;

    public Maneuver() { }

    public Maneuver(Double distance, int turnType, String narrative, String time, String iconUrl, int direction) {
        this.distance = distance;
        this.turnType = turnType;
        this.narrative = narrative;
        this.time = time;

        this.iconUrl = iconUrl;
        this.direction = direction;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getTurnType() {
        return turnType;
    }

    public void setTurnType(int turnType) {
        this.turnType = turnType;
    }


}

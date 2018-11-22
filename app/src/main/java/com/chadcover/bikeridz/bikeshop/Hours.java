package com.chadcover.bikeridz.bikeshop;

public class Hours {
    
    private String monday = null;
    private String tuesday = null;
    private String wednesday = null;
    private String thursday = null;
    private String friday = null;
    private String saturday = null;
    private String sunday = null;
    private String[] week = {this.monday, this.tuesday, this.wednesday,
                this.thursday, this.friday, this.saturday, this.sunday};

    public Hours() {
        this.monday = "Closed";
        this.week[0] = this.monday;
        this.tuesday = "9am-5pm";
        this.week[1] = this.tuesday;
        this.wednesday = "9am-5pm";
        this.week[2] = this.wednesday;
        this.thursday = "9am-5pm";
        this.week[3] = this.thursday;
        this.friday = "9am-8pm";
        this.week[4] = this.friday;
        this.saturday = "9am-8pm";
        this.week[5] = this.saturday;
        this.sunday = "12pm-5pm";
        this.week[6] = this.sunday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getMonday() {
        return monday;
    }
    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getFriday() {
        return friday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getSunday() {
        return sunday;
    }

    public String[] getWeek() {
        return this.week;
    }

    public void setWeek(String[] week) {
        this.week = week;
    }
}

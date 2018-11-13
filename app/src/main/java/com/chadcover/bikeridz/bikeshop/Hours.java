package com.chadcover.bikeridz.bikeshop;

public class Hours {
    
    private String monday = null;
    private String tuesday = null;
    private String wednesday = null;
    private String thursday = null;
    private String friday = null;
    private String saturday = null;
    private String sunday = null;
    private String[] Week =
            {monday, tuesday, wednesday,
             thursday, friday, saturday,
             sunday};

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public void setTuesday(String tuesday) {
        tuesday = tuesday;
    }

    public void setWednesday(String wednesday) {
        wednesday = wednesday;
    }

    public void setThursday(String thursday) {
        thursday = thursday;
    }


    public void setFriday(String friday) {
        friday = friday;
    }

    public void setSaturday(String saturday) {
        saturday = saturday;
    }

    public void setSunday(String sunday) {
        sunday = sunday;
    }

    public String[] getWeek() {
        return Week;
    }

    public void setWeek(String[] week) {
        Week = week;
    }
}

package com.chadcover.bikeridz.bikeshop;

public enum State {
    Alabama("AL"),
    Alaska("AK"),
    Arizona("AZ"),
    Arkansas("AR"),
    California("CA"),
    Colorado("CO"),
    Connecticut("CT"),
    Delaware("DE"),
    Florida("FL"),
    Georgia("GA"),
    Hawaii("HI"),
    Idaho("ID"),
    Illinois("IL"),
    Indiana("IN"),
    Iowa("IA"),
    Kansas("KS"),
    Kentucky("KY"),
    Louisiana("LA"),
    Maine("ME"),
    Maryland("MD"),
    Massachusetts("MA"),
    Michigan("MI"),
    Minnesota("MN"),
    Mississippi("MS"),
    Missouri("MO"),
    Montana("MT"),
    Nebraska("NE"),
    Nevada("NV"),
    NewHampshire("NH"),
    NewJersey("NJ"),
    NewMexico("NM"),
    NewYork("NY"),
    NorthCarolina("NC"),
    NorthDakota("ND"),
    Ohio("OH"),
    Oklahoma("OK"),
    Oregon("OR"),
    Pennsylvania("PA"),
    RhodeIsland("RI"),
    SouthCarolina("SC"),
    SouthDakota("SD"),
    Tennessee("TN"),
    Texas("TX"),
    Utah("UT"),
    Vermont("VT"),
    Virginia("VA"),
    Washington("WA"),
    WestVirginia("WV"),
    Wisconsin("WI"),
    Wyoming("WY");

    final String value;

    State(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

    String getKey() {
        return name();
    }

    }

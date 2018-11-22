package com.chadcover.bikeridz;


import com.chadcover.bikeridz.bikeshop.Address;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.Hours;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BikeshopUnitTests {

    @Ignore
    @Test
    public void bikeshopIsConstructedCorrectly() {

        BikeShop bikeShop = new BikeShop();

        assertEquals("My Bikeshop", bikeShop.getName());
        assertEquals("555.111.2222", bikeShop.getPhoneNumber());

        String myAddress = bikeShop.getAddress().getFullAddress();
        assertEquals("42 Galifray Blvd. Baltimore, MD 21136", myAddress);

        assertEquals(Double.toString(48.858093), Double.toString(bikeShop.getCoords().getLat()));

        String[] myWeeklySchedule = bikeShop.getHours().getWeek();
        String myMonday = myWeeklySchedule[0];
        String mySunday = myWeeklySchedule[6];
        assertEquals(myMonday, "Closed");
        assertEquals(mySunday, "12pm-5pm");

    }

}

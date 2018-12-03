package com.chadcover.bikeridz;

import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.bike.BikeType;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BikeUnitTests {

    @Ignore
    @Test
    public void bikeDetailsAreCorrect() {
        Bike bike = new Bike();

        bike.setManufacturer("Schwinn");
        bike.setName("Road Warrior");
        bike.setColor("Red");
        bike.setHeight("60cm");
        bike.setMaterial("Titanium");
        bike.setType(BikeType.ROAD);

        assertEquals("Schwinn", bike.getManufacturer());
        assertEquals("Road Warrior", bike.getName());
        assertEquals("Red", bike.getColor());
        assertEquals("60cm", bike.getHeight());
        assertEquals("Titanium", bike.getMaterial());
        assertEquals("ROAD", bike.getType().toString());
    }

}

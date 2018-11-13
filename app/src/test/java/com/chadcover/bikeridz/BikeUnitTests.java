package com.chadcover.bikeridz;

import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.bike.BikeType;
import com.chadcover.bikeridz.bike.Brake;
import com.chadcover.bikeridz.bike.Part;
import com.chadcover.bikeridz.bike.Seat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BikeUnitTests {

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

    @Test
    public void brakeNameIsCorrect() {
        Brake brake = new Brake();
        String brakeName = brake.getName();
        assertEquals("new part", brakeName);
    }


    @Test
    public void canAddToBikeInventory() {
        Bike bike = new Bike();
        Part[] myInventory = bike.getPartsInventory();

        Brake myBrake = new Brake();
        myBrake.setName("BestBrakez");
        this.addToInventory(myInventory, myBrake);

        Seat mySeat = new Seat();
        mySeat.setDescription("The very best bike seat on the market.");
        this.addToInventory(myInventory, mySeat);

        assertEquals("class com.chadcover.bikeridz.bike.Brake", myInventory[0].getClass().toString());
        assertEquals("class com.chadcover.bikeridz.bike.Seat", myInventory[1].getClass().toString());
        assertEquals("BestBrakez", myBrake.getName());
        assertEquals("The very best bike seat on the market.", mySeat.getDescription());

    }


    // helper functions

    public void addToInventory(Part[] inventory, Part part) {
        // TODO: implement logic to handle full inventory (100 items) and to groom array on delete
        for (int i = 0; i < inventory.length; i++ ) {
            if (inventory[i] == null) {
                inventory[i] = part;
                break;
            }
        }
    }


}

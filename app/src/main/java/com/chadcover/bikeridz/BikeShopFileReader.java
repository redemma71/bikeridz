package com.chadcover.bikeridz;


import android.app.Activity;
import android.util.Log;

import com.chadcover.bikeridz.bikeshop.Address;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.Coords;
import com.chadcover.bikeridz.bikeshop.Hours;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BikeShopFileReader {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private List<BikeShop> bikeShops = new ArrayList<>();
    private InputStream is;

    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public BikeShopFileReader(InputStream inputStream) {
        this.is = inputStream;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public List readFile() {
        
        // read text from character input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // initialize the line
        String line = "";

        try {
            // step over header line
            reader.readLine();

            // if buffer is not empty
            while ((line = reader.readLine()) != null) {
                // use a comma delimiter
                String[] tokens = line.split(",");

                // read data
                BikeShop bikeShop = new BikeShop();

                ///////////////////////////////////////
                // set data
                ///////////////////////////////////////

                // set name
                bikeShop.setName(tokens[0]);

                // set address
                Address address = new Address();
                address.setStreetNumber(tokens[1]);
                address.setStreetName(tokens[2]);
                address.setCity(tokens[3]);
                address.setState(tokens[4]);
                address.setZipCode(tokens[5]);
                String fullAddress = tokens[1] + " " + tokens[2] + "\n"
                        + tokens[3] + ", " + tokens[4] + " " + tokens[5];
                address.setFullAddress(fullAddress);
                bikeShop.setAddress(address);

                // set phone number
                bikeShop.setPhoneNumber(tokens[6]);

                // set coordinates
                Coords coords = new Coords();
                coords.setLat(Double.parseDouble(tokens[7]));
                coords.setLng(Double.parseDouble(tokens[8]));
                bikeShop.setCoords(coords);

                // set Hours
                Hours hours = new Hours();
                hours.setMonday(tokens[9]);
                hours.setTuesday(tokens[10]);
                hours.setWednesday(tokens[11]);
                hours.setThursday(tokens[12]);
                hours.setFriday(tokens[13]);
                hours.setSaturday(tokens[14]);
                hours.setSunday(tokens[15]);
                bikeShop.setHours(hours);

                // add the data
                bikeShops.add(bikeShop);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException ioe){
                Log.d("BikeShopFileReader", "Error closing the input file");
            }
        }
        return bikeShops;
    }

}

package com.chadcover.bikeridz.bikeshop;

import android.content.Context;
import android.util.Log;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.BikeShopFileReader;
import com.chadcover.bikeridz.bikeshop.Coords;

import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;

import static android.util.Half.NaN;

public class FindNearestShop {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private List<BikeShop> bikeShops;
    private InputStream is;

    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////

    public FindNearestShop() { }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////


    public List<BikeShop> getBikeShops(Context context) {
        is = context.getResources().openRawResource(R.raw.bikeshops);
        BikeShopFileReader fileReader = new BikeShopFileReader(is);
        List bikeShops = fileReader.readFile();
        return bikeShops;
    }


    public BikeShop getClosestBikeshop(List<BikeShop> bikeShops, double myLatitude, double myLongitude) {
        double distanceToClosestShop = 4242;
        String closestShop = "Race Pace Cycles";

        ListIterator<BikeShop> bikeShopIter = bikeShops.listIterator();
        int bikeShopIndex = NaN;
        int index = 0;

        while (bikeShopIter.hasNext()) {
            BikeShop bikeShop = bikeShopIter.next();
            Coords coords = bikeShop.getCoords();
            String shopName = bikeShop.getName();
            Double latitude = coords.getLat();
            Double longitude = coords.getLng();


            double distanceToShop = this.calculateHaversineDistance(myLatitude, myLongitude, latitude, longitude);
            if (distanceToShop < distanceToClosestShop ) {
                distanceToClosestShop = distanceToShop;
                Log.d("FindNearestShop", "Nearest shop: " + shopName);
                bikeShopIndex = index;
            }
         index++;
        }
        return bikeShops.get(bikeShopIndex);
    }


    private double calculateHaversineDistance(double currentLatitude, double currentLongitude,
                                               double shopLatitude, double shopLongitude) {

            double radius = 6371;  // diameter of earth in km
            double dLat = shopLatitude - currentLatitude;
            double dLatRad = Math.toRadians(dLat);

            double dLong = shopLongitude - currentLongitude;
            double dLongRad = Math.toRadians(dLong);

            double a = Math.pow(Math.sin(dLatRad  / 2), 2) +
                    Math.cos(Math.toRadians(currentLatitude)) *
                            Math.cos(Math.toRadians(shopLatitude)) *
                            Math.cos(Math.toRadians(currentLatitude)) *
                            Math.pow(Math.sin(dLongRad / 2),2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return (radius * c);
        }

}



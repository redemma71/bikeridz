package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chadcover.bikeridz.bikeshop.Address;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.Coords;
import com.chadcover.bikeridz.bikeshop.Hours;

import java.util.ArrayList;
import java.util.List;

public class BikeRidzDao {
    protected static BikeRidzDao instance;
    protected BikeRidzDBHelper bikeRidzDBHelper;
    protected SQLiteDatabase mReadableDB, mWritableDB;
    protected String[] projection = {
        BikeRidzDBContract.BikeRidzContract.SHOP_ID,
        BikeRidzDBContract.BikeRidzContract.SHOP_NAME,
        BikeRidzDBContract.BikeRidzContract.STREET_NUMBER,
            BikeRidzDBContract.BikeRidzContract.STREET_NAME,
            BikeRidzDBContract.BikeRidzContract.CITY,
            BikeRidzDBContract.BikeRidzContract.STATE,
            BikeRidzDBContract.BikeRidzContract.ZIP,
            BikeRidzDBContract.BikeRidzContract.FULL_ADDRESS,
            BikeRidzDBContract.BikeRidzContract.PHONE_NUMBER,
            BikeRidzDBContract.BikeRidzContract.LATITUDE,
            BikeRidzDBContract.BikeRidzContract.LONGITUDE,
            BikeRidzDBContract.BikeRidzContract.HOURS_MON,
            BikeRidzDBContract.BikeRidzContract.HOURS_TUES,
            BikeRidzDBContract.BikeRidzContract.HOURS_WED,
            BikeRidzDBContract.BikeRidzContract.HOURS_THURS,
            BikeRidzDBContract.BikeRidzContract.HOURS_FRI,
            BikeRidzDBContract.BikeRidzContract.HOURS_SAT,
            BikeRidzDBContract.BikeRidzContract.HOURS_SUN
    };
    protected String selection = BikeRidzDBContract.BikeRidzContract.SHOP_ID + "=?";


    public BikeRidzDao(Context context) {
        bikeRidzDBHelper = new BikeRidzDBHelper(context);
     }

     public void openDB() {
        mReadableDB = bikeRidzDBHelper.getReadableDatabase();
        mWritableDB = bikeRidzDBHelper.getWritableDatabase();
     }

     public void closeDB() {
        mReadableDB.close();
        mWritableDB.close();
     }

     public static BikeRidzDao getInstance(Context context) {
        if (instance == null) {
            instance = new BikeRidzDao(context);
        }
        return instance;
     }

     public long insertBikeShop(BikeShop bikeShop) {
        ContentValues bikeShopValue = new ContentValues();
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.SHOP_NAME,
                bikeShop.getName());
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.STREET_NUMBER,
                bikeShop.getAddress().getStreetNumber());
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.STREET_NAME,
                bikeShop.getAddress().getStreetName());
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.CITY,
                bikeShop.getAddress().getCity());
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.STATE,
                bikeShop.getAddress().getState());
        bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.ZIP,
                bikeShop.getAddress().getZipCode());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.FULL_ADDRESS,
                 bikeShop.getAddress().getFullAddress());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.LATITUDE,
                 bikeShop.getCoords().getLat());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.LONGITUDE,
                 bikeShop.getCoords().getLng());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_MON,
                 bikeShop.getHours().getMonday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_TUES,
                 bikeShop.getHours().getTuesday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_WED,
                 bikeShop.getHours().getWednesday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_THURS,
                 bikeShop.getHours().getThursday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_FRI,
                 bikeShop.getHours().getFriday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_SAT,
                 bikeShop.getHours().getSaturday());
         bikeShopValue.put(BikeRidzDBContract.BikeRidzContract.HOURS_SUN,
                 bikeShop.getHours().getSunday());
         return mWritableDB.insert(BikeRidzDBContract.BikeRidzContract.TABLE_NAME, null,
                 bikeShopValue);
     }

     public long deleteBikeShopById(int bikeShopId) {

        String[] selectionArgs = {bikeShopId + ""};

        return mWritableDB.delete(BikeRidzDBContract.BikeRidzContract.TABLE_NAME,
                selection, selectionArgs);
    }


    public List<BikeShop> getAllBikeShops() {
        Cursor cursor = mReadableDB.query(BikeRidzDBContract.BikeRidzContract.TABLE_NAME,
                this.projection, null, null, null, null, null );
        List<BikeShop> bikeShops = new ArrayList<>();
        while (cursor.moveToNext()) {
            int bikeShopId = cursor.getInt(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.SHOP_ID));
            String bikeShopName = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.SHOP_NAME));
            String bikeShopPhoneNumber = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.PHONE_NUMBER));

            /////////////////////////////////////////////////////////////////////
            // address
            /////////////////////////////////////////////////////////////////////
            // get details
            String bikeShopStreetNumber = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.STREET_NUMBER));
            String bikeShopStreetName = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.STREET_NAME));
            String bikeShopCity = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.CITY));
            String bikeShopState = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.STATE));
            String bikeShopZip = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.ZIP));
            String bikeShopFullAddress = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.FULL_ADDRESS));
            // set details
            Address bikeShopAddress = new Address();
            bikeShopAddress.setStreetNumber(bikeShopStreetNumber);
            bikeShopAddress.setStreetName(bikeShopStreetName);
            bikeShopAddress.setCity(bikeShopCity);
            bikeShopAddress.setState(bikeShopState);
            bikeShopAddress.setZipCode(bikeShopZip);
            bikeShopAddress.setFullAddress(bikeShopFullAddress);

            /////////////////////////////////////////////////////////////////////
            // coordinates
            /////////////////////////////////////////////////////////////////////
            // get shop coordinates
            Double bikeShopLatitude = cursor.getDouble(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.LATITUDE));
            Double bikeShopLongitude = cursor.getDouble(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.LONGITUDE));
            Coords bikeShopCoords = new Coords();
            // set shop coords
            bikeShopCoords.setLat(bikeShopLatitude);
            bikeShopCoords.setLng(bikeShopLongitude);

            /////////////////////////////////////////////////////////////////////
            // hours
            /////////////////////////////////////////////////////////////////////
            // get shop hours
            String bikeShopMondayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_MON));
            String bikeShopTuesdayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_TUES));
            String bikeShopWednesdayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_WED));
            String bikeShopThursdayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_THURS));
            String bikeShopFridayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_FRI));
            String bikeShopSaturdayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_SAT));
            String bikeShopSundayHours = cursor.getString(cursor.getColumnIndex(
                    BikeRidzDBContract.BikeRidzContract.HOURS_SUN));
            // set shop hours
            Hours bikeShopHours = new Hours();
            bikeShopHours.setMonday(bikeShopMondayHours);
            bikeShopHours.setTuesday(bikeShopMondayHours);
            bikeShopHours.setWednesday(bikeShopMondayHours);
            bikeShopHours.setThursday(bikeShopMondayHours);
            bikeShopHours.setFriday(bikeShopMondayHours);
            bikeShopHours.setSaturday(bikeShopMondayHours);
            bikeShopHours.setSunday(bikeShopMondayHours);


            /////////////////////////////////////////////////////////////////////
            // bikeshop
            /////////////////////////////////////////////////////////////////////
            // set and add bikeshop
            BikeShop bikeShop = new BikeShop();
            bikeShop.setName(bikeShopName);
            bikeShop.setPhoneNumber(bikeShopPhoneNumber);
            bikeShop.setAddress(bikeShopAddress);
            bikeShop.setCoords(bikeShopCoords);
            bikeShop.setHours(bikeShopHours);
            bikeShops.add(bikeShop);
        }
        cursor.close();
        return bikeShops;
    }

    public BikeShop getBikeShopById(int bikeShopId) {
        String[] selectionArgs = {Integer.toString(bikeShopId)};

        Cursor cursor = mReadableDB.query(BikeRidzDBContract.BikeRidzContract.TABLE_NAME,
                projection, selection, selectionArgs, null, null, null, null);
        cursor.moveToFirst(); // only one bikeshop will be found

        String bikeShopName = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.SHOP_NAME));
        String bikeShopPhoneNumber = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.PHONE_NUMBER));

        /////////////////////////////////////////////////////////////////////
        // address
        /////////////////////////////////////////////////////////////////////
        // get details
        String bikeShopStreetNumber = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.STREET_NUMBER));
        String bikeShopStreetName = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.STREET_NAME));
        String bikeShopCity = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.CITY));
        String bikeShopState = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.STATE));
        String bikeShopZip = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.ZIP));
        String bikeShopFullAddress = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.FULL_ADDRESS));
        // set details
        Address bikeShopAddress = new Address();
        bikeShopAddress.setStreetNumber(bikeShopStreetNumber);
        bikeShopAddress.setStreetName(bikeShopStreetName);
        bikeShopAddress.setCity(bikeShopCity);
        bikeShopAddress.setState(bikeShopState);
        bikeShopAddress.setZipCode(bikeShopZip);
        bikeShopAddress.setFullAddress(bikeShopFullAddress);

        /////////////////////////////////////////////////////////////////////
        // coordinates
        /////////////////////////////////////////////////////////////////////
        // get shop coordinates
        Double bikeShopLatitude = cursor.getDouble(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.LATITUDE));
        Double bikeShopLongitude = cursor.getDouble(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.LONGITUDE));
        Coords bikeShopCoords = new Coords();
        // set shop coords
        bikeShopCoords.setLat(bikeShopLatitude);
        bikeShopCoords.setLng(bikeShopLongitude);

        /////////////////////////////////////////////////////////////////////
        // hours
        /////////////////////////////////////////////////////////////////////
        // get shop hours
        String bikeShopMondayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_MON));
        String bikeShopTuesdayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_TUES));
        String bikeShopWednesdayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_WED));
        String bikeShopThursdayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_THURS));
        String bikeShopFridayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_FRI));
        String bikeShopSaturdayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_SAT));
        String bikeShopSundayHours = cursor.getString(cursor.getColumnIndex(
                BikeRidzDBContract.BikeRidzContract.HOURS_SUN));
        // set shop hours
        Hours bikeShopHours = new Hours();
        bikeShopHours.setMonday(bikeShopMondayHours);
        bikeShopHours.setTuesday(bikeShopMondayHours);
        bikeShopHours.setWednesday(bikeShopMondayHours);
        bikeShopHours.setThursday(bikeShopMondayHours);
        bikeShopHours.setFriday(bikeShopMondayHours);
        bikeShopHours.setSaturday(bikeShopMondayHours);
        bikeShopHours.setSunday(bikeShopMondayHours);


        /////////////////////////////////////////////////////////////////////
        // bikeshop
        /////////////////////////////////////////////////////////////////////
        // set and add bikeshop
        BikeShop bikeShop = new BikeShop();
        bikeShop.setName(bikeShopName);
        bikeShop.setPhoneNumber(bikeShopPhoneNumber);
        bikeShop.setAddress(bikeShopAddress);
        bikeShop.setCoords(bikeShopCoords);
        bikeShop.setHours(bikeShopHours);

        cursor.close();
        return bikeShop;
    }

    // TODO: implement getBikeShopByTitle. out of scope for release v1
    // TODO: implement updateBikeShopById. out of scope for release v1




}

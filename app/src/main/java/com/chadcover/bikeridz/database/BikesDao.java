package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.bike.BikeType;

import java.util.ArrayList;
import java.util.List;

public class BikesDao {
    protected static BikesDao instance;
    protected BikesDBHelper bikesDBHelper;
    protected SQLiteDatabase mReadableDB, mWritableDB;
    protected String[] projection = {
        BikesDBContract.BikeContract.BIKE_ID,
        BikesDBContract.BikeContract.BIKE_NAME,
        BikesDBContract.BikeContract.BIKE_DESCRIPTION,
            BikesDBContract.BikeContract.BIKE_COLOR,
            BikesDBContract.BikeContract.BIKE_HEIGHT,
            BikesDBContract.BikeContract.BIKE_MATERIAL,
            BikesDBContract.BikeContract.BIKE_TYPE,
            BikesDBContract.BikeContract.BIKE_MANUFACTURER,
            BikesDBContract.BikeContract.BIKE_SERIAL_NUMBER
    };

    // search by bike id selection
    protected String selectionByBikeId = BikesDBContract.BikeContract.BIKE_ID + "=?";


    public BikesDao(Context context) {
        bikesDBHelper = new BikesDBHelper(context);
     }

     public void openDB() {
        mReadableDB = bikesDBHelper.getReadableDatabase();
        mWritableDB = bikesDBHelper.getWritableDatabase();
     }

     public void closeDB() {
        mReadableDB.close();
        mWritableDB.close();
     }

     public static BikesDao getInstance(Context context) {
        if (instance == null) {
            instance = new BikesDao(context);
        }
        return instance;
     }

     public long insertBike(Bike bike) {
        ContentValues bikeValue = new ContentValues();
        bikeValue.put(BikesDBContract.BikeContract.BIKE_NAME,
                bike.getName());
        bikeValue.put(BikesDBContract.BikeContract.BIKE_DESCRIPTION,
                bike.getDescription());
        bikeValue.put(BikesDBContract.BikeContract.BIKE_COLOR,
                bike.getColor());
        bikeValue.put(BikesDBContract.BikeContract.BIKE_HEIGHT,
                bike.getHeight());
        bikeValue.put(BikesDBContract.BikeContract.BIKE_MATERIAL,
                bike.getMaterial());
        bikeValue.put(BikesDBContract.BikeContract.BIKE_TYPE,
                bike.getTypeName());
         bikeValue.put(BikesDBContract.BikeContract.BIKE_MANUFACTURER,
                 bike.getManufacturer());
         bikeValue.put(BikesDBContract.BikeContract.BIKE_SERIAL_NUMBER,
                 bike.getSerialNumber());
         return mWritableDB.insert(BikesDBContract.BikeContract.TABLE_NAME, null,
                 bikeValue);
     }

     public long deleteBikeShopById(int bikeShopId) {
        String[] selectionArgs = {bikeShopId + ""};
        return mWritableDB.delete(BikesDBContract.BikeContract.TABLE_NAME,
                selectionByBikeId, selectionArgs);
    }

    public List<Bike> getAllBikes() {

        Cursor cursor = mReadableDB.query(BikesDBContract.BikeContract.TABLE_NAME,
                projection, null, null, null, null, null );
        List<Bike> bikes = new ArrayList<>();
        while (cursor.moveToNext()) {
            int bikeId = cursor.getInt(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_ID));
            String bikeName = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_NAME));
            String bikeDescription = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_DESCRIPTION));
            String bikeColor = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_COLOR));
            String bikeHeight = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_HEIGHT));
            String bikeMaterial = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_MATERIAL));
            String bikeTypeStr = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_TYPE));
            String bikeManufacturer = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_MANUFACTURER));
            String bikeSerialNumber = cursor.getString(cursor.getColumnIndex(
                    BikesDBContract.BikeContract.BIKE_SERIAL_NUMBER));

            /////////////////////////////////////////////////////////////////////
            // bike
            /////////////////////////////////////////////////////////////////////
            // set and add bike
            Bike bike = new Bike();
            bike.setId(bikeId);
            bike.setName(bikeName);
            bike.setDescription(bikeDescription);
            bike.setColor(bikeColor);
            bike.setHeight(bikeHeight);
            bike.setMaterial(bikeMaterial);
            BikeType bikeType = BikeType.valueOf(bikeTypeStr.toUpperCase());
            bike.setType(bikeType);
            bike.setSerialNumber(bikeSerialNumber);
            bike.setManufacturer(bikeManufacturer);
            bikes.add(bike);
        }

        cursor.close();
        return bikes;
    }

    public Bike getBikeById(int bikeId) {
        String[] selectionArgs = {Integer.toString(bikeId)};

        Cursor cursor = mReadableDB.query(BikesDBContract.BikeContract.TABLE_NAME,
                projection, selectionByBikeId, selectionArgs, null, null, null, null);
        cursor.moveToFirst(); // only one bike will be found

        String bikeName = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_NAME));
        String bikeDescription = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeColor = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeHeight = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeMaterial = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeTypeStr = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeManufacturer = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_DESCRIPTION));
        String bikeSerialNumber = cursor.getString(cursor.getColumnIndex(
                BikesDBContract.BikeContract.BIKE_SERIAL_NUMBER));

        /////////////////////////////////////////////////////////////////////
        // bike
        /////////////////////////////////////////////////////////////////////
        // set and add bike
        Bike bike = new Bike();
        bike.setName(bikeName);
        bike.setDescription(bikeDescription);
        bike.setColor(bikeColor);
        bike.setHeight(bikeHeight);
        bike.setMaterial(bikeMaterial);
        BikeType bikeType = BikeType.valueOf(bikeTypeStr);
        bike.setType(bikeType);
        bike.setManufacturer(bikeManufacturer);
        bike.setSerialNumber(bikeSerialNumber);
        cursor.close();
        return bike;
    }

    // TODO: implement getBikeShopByTitle. out of scope for release v1
    // TODO: implement updateBikeShopById. out of scope for release v1




}

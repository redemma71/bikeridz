package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chadcover.bikeridz.bike.Part;
import com.chadcover.bikeridz.bike.PartType;

import java.util.ArrayList;
import java.util.List;

public class PartsDao {
    protected static PartsDao instance;
    protected PartsDBHelper partsDBHelper;
    protected SQLiteDatabase mReadableDB, mWritableDB;
    protected String[] projection = {
        PartsDBContract.PartsContract.PART_ID,
        PartsDBContract.PartsContract.PART_DESCRIPTION,
        PartsDBContract.PartsContract.PART_NAME,
        PartsDBContract.PartsContract.PART_TYPE,
        PartsDBContract.PartsContract.PART_MANUFACTURER,
        PartsDBContract.PartsContract.BIKE_ID
    };


    // search for parts by partid
    protected String selectionByPartId = PartsDBContract.PartsContract.PART_ID + "=?";

    // search for parts by part type
    protected String selectionByPartType = PartsDBContract.PartsContract.PART_TYPE + "=?";

    public PartsDao(Context context) {
        partsDBHelper = new PartsDBHelper(context);
    }

    public void openDB() {
        mReadableDB = partsDBHelper.getReadableDatabase();
        mWritableDB = partsDBHelper.getWritableDatabase();
    }

    public void closeDB() {
        mReadableDB.close();
        mWritableDB.close();
    }

    public static PartsDao getInstance(Context context) {
        if (instance == null) {
            instance = new PartsDao(context);
        }
        return instance;
    }

    public long insertPart(Part part) {
        ContentValues partValue = new ContentValues();
        partValue.put(PartsDBContract.PartsContract.PART_NAME,
                part.getName());
        partValue.put(PartsDBContract.PartsContract.PART_TYPE,
                part.getTypeOfPartString());
        partValue.put(PartsDBContract.PartsContract.PART_DESCRIPTION,
                part.getDescription());
        partValue.put(PartsDBContract.PartsContract.PART_MANUFACTURER,
                part.getManufacturer());
        partValue.put(PartsDBContract.PartsContract.BIKE_ID,
                part.getBikeId());
        return mWritableDB.insert(PartsDBContract.PartsContract.TABLE_NAME,
                null, partValue);
    }

    public long deletePartById(int partId) {
        String[] selectionArgs = {partId + ""};
        return mWritableDB.delete(PartsDBContract.PartsContract.TABLE_NAME, selectionByPartId,
                selectionArgs);
    }

    public List<Part> getPartsByBikeId(int bikeId) {
        // search for parts by bike
        String selectionByBikeId = PartsDBContract.PartsContract.BIKE_ID + "=?";
        String[] selectionArgs = {Integer.toString(bikeId + 1)};
        Cursor cursor = mReadableDB.query(PartsDBContract.PartsContract.TABLE_NAME,
                this.projection, selectionByBikeId, selectionArgs, null, null, null);
        List<Part> parts = new ArrayList<>();

        while (cursor.moveToNext()) {
            int partId = cursor.getInt(cursor.getColumnIndex(
                    PartsDBContract.PartsContract.PART_ID));
            String partName = cursor.getString(cursor.getColumnIndex(
                    PartsDBContract.PartsContract.PART_NAME));
            String partDescription = cursor.getString(cursor.getColumnIndex(
                    PartsDBContract.PartsContract.PART_DESCRIPTION
            ));
            String partManufacturer = cursor.getString(cursor.getColumnIndex(
                    PartsDBContract.PartsContract.PART_MANUFACTURER));
            String partTypeStr = cursor.getString(cursor.getColumnIndex(
                    PartsDBContract.PartsContract.PART_TYPE));

            /////////////////////////////////////////////////////////////////////
            // part
            /////////////////////////////////////////////////////////////////////
            // set and add part
            Part part = new Part();
            part.setPartId(partId);
            part.setName(partName);
            PartType partType = PartType.valueOf(partTypeStr.toUpperCase());
            part.setTypeOfPart(partType);
            part.setDescription(partDescription);
            part.setManufacturer(partManufacturer);
            parts.add(part);
        }
        cursor.close();
        return parts;
    }

    public Part getPartById(int partId) {
        String[] selectionArgs = {Integer.toString(partId)};
        Cursor cursor = mReadableDB.query(PartsDBContract.PartsContract.TABLE_NAME,
                projection, selectionByPartId, selectionArgs, null,
                null, null, null);
        cursor.moveToFirst();
        String partName = cursor.getString(cursor.getColumnIndex(
                PartsDBContract.PartsContract.PART_NAME));
        String partDescription = cursor.getString(cursor.getColumnIndex(
                PartsDBContract.PartsContract.PART_DESCRIPTION
        ));
        String partManufacturer = cursor.getString(cursor.getColumnIndex(
                PartsDBContract.PartsContract.PART_MANUFACTURER));
        String partTypeStr = cursor.getString(cursor.getColumnIndex(
                PartsDBContract.PartsContract.PART_TYPE));

        /////////////////////////////////////////////////////////////////////
        // part
        /////////////////////////////////////////////////////////////////////
        // set and add part
        Part part = new Part();
        part.setName(partName);
        PartType partType = PartType.valueOf(partTypeStr);
        part.setTypeOfPart(partType);
        part.setDescription(partDescription);
        part.setManufacturer(partManufacturer);
        cursor.close();
        return part;
    }

    public void setPartById(int partId) {
        Part part = getPartById(partId);
        // TODO
    }

}

package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chadcover.bikeridz.bike.Bike;

import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;


public class BikesDBHelper extends SQLiteOpenHelper {
    private Context context;
    protected List<Bike> bikes;

    public BikesDBHelper(Context context) {
        super(context, BikesDBContract.DB_NAME, null, BikesDBContract.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BikesDBContract.CREATE_BIKE_INVENTORY_TABLE);
        setBikes(this.context);
        updateDatabase(db, 0, BikesDBContract.DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL(BikeRidzDBContract.DROP_PROJECT_TABLE);
        onCreate(db);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static void insertBike(SQLiteDatabase db, int bikeId, String bikeName, String bikeDescription,
                                      String bikeColor, String bikeHeight, String bikeMaterial,
                                      String bikeType, String bikeManufacturer, String bikeSerialNumber) {
        ContentValues bikes = new ContentValues();
        bikes.put(BikesDBContract.BikeContract.BIKE_ID, bikeId);
        bikes.put(BikesDBContract.BikeContract.BIKE_NAME, bikeName);
        bikes.put(BikesDBContract.BikeContract.BIKE_DESCRIPTION, bikeDescription);
        bikes.put(BikesDBContract.BikeContract.BIKE_COLOR, bikeColor);
        bikes.put(BikesDBContract.BikeContract.BIKE_HEIGHT, bikeHeight);
        bikes.put(BikesDBContract.BikeContract.BIKE_MATERIAL, bikeMaterial);
        bikes.put(BikesDBContract.BikeContract.BIKE_TYPE, bikeType);
        bikes.put(BikesDBContract.BikeContract.BIKE_MANUFACTURER, bikeManufacturer);
        bikes.put(BikesDBContract.BikeContract.BIKE_SERIAL_NUMBER, bikeSerialNumber);
        db.insert(BikesDBContract.BikeContract.TABLE_NAME, null, bikes);
    }


    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DATABASE-VERSION", Integer.toString(oldVersion));

        if (oldVersion < 1) {
            List<Bike> mBikes = getBikes();
            ListIterator<Bike> mBikesItr = mBikes.listIterator();
            while ( mBikesItr.hasNext()) {
                Bike bike = mBikesItr.next();

                insertBike(db, bike.getId(), bike.getName(), bike.getDescription(), bike.getColor(), bike.getHeight(),
                        bike.getMaterial(), bike.getTypeName(), bike.getManufacturer(), bike.getSerialNumber());
            }
        }

        if (oldVersion < 2) {
            // do something else to update the database
            // eg, alter structure: db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            // or update data: db.update("BIKESHOP", "Bill\'s Bikes", "NAME = ?", new String[] {"Bob\'s Bikes"});
        }
    }


    private void setBikes(Context context) {
        this.bikes = new Bike().createSampleBikes();
    }

    public List getBikes() {
        return this.bikes;
    }




}

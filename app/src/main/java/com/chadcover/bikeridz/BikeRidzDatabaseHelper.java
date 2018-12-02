package com.chadcover.bikeridz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chadcover.bikeridz.bikeshop.Address;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.Coords;
import com.chadcover.bikeridz.bikeshop.Hours;

import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;

public class BikeRidzDatabaseHelper extends SQLiteOpenHelper {


    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private static final String DB_NAME = "bikeridz";
    private static final int DB_VERSION = 1;
    private InputStream is;
    private Context context;
    private List bikeShops;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////

    public BikeRidzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    };


    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ONCREATE","created");
        setBikeShops(this.context);
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ONUPGRADE","upgraded");
        updateDatabase(db, oldVersion, newVersion);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ONDOWNGRADE", "downgraded");
        updateDatabase(db, oldVersion, newVersion);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static void insertBikeshop(SQLiteDatabase db, String name, String streetNumber,
                                      String streetName, String city, String state,
                                      String zip, String fullAddress, String phoneNumber,
                                      Double latitude, Double longitude,
                                      String hoursMon, String hoursTues, String hoursWed,
                                      String hoursThurs, String hoursFri, String hoursSat,
                                      String hoursSun) {
        ContentValues bikeShops = new ContentValues();
        bikeShops.put("Name", name);
        bikeShops.put("StreetNumber", streetNumber);
        bikeShops.put("StreetName", streetName);
        bikeShops.put("City", city);
        bikeShops.put("State", state);
        bikeShops.put("Zip", zip);
        bikeShops.put("FullAddress", fullAddress);
        bikeShops.put("Latitude", latitude);
        bikeShops.put("Longitude", longitude);
        bikeShops.put("HoursMon", hoursMon);
        bikeShops.put("HoursTues", hoursTues);
        bikeShops.put("HoursWed", hoursWed);
        bikeShops.put("HoursThurs", hoursThurs);
        bikeShops.put("HoursFri", hoursFri);
        bikeShops.put("HoursSat", hoursSat);
        bikeShops.put("HoursSun", hoursSun);
        bikeShops.put("PhoneNumber", phoneNumber);
        db.insert("BIKESHOP", null, bikeShops);
    }


    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(
                "CREATE TABLE  BIKESHOP (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name TEXT, StreetNumber TEXT," +
                    "StreetName TEXT, CITY TEXT," +
                    "State TEXT, Zip TEXT," +
                    "FullAddress TEXT, PhoneNumber TEXT," +
                    "Latitude REAL, Longitude REAL," +
                    "HoursMon TEXT, HoursTues TEXT," +
                    "HoursWed TEXT, HoursThurs TEXT," +
                    "HoursFri TEXT, HoursSat TEXT," +
                    "HoursSun TEXT);"
            );

            List mdBikeShops = getBikeShops();
            ListIterator<BikeShop> mdBikeShopsItr = mdBikeShops.listIterator();
            while (mdBikeShopsItr.hasNext()) {
                BikeShop shop = mdBikeShopsItr.next();
                Log.i("MARYLAND BIKESHOP", shop.getName());
                Address address = shop.getAddress();
                Coords coords = shop.getCoords();
                Hours hours = shop.getHours();
                insertBikeshop(db, shop.getName(), address.getStreetNumber(), address.getStreetName(), address.getCity(),
                        address.getState(), address.getZipCode(), address.getFullAddress(), shop.getPhoneNumber(), coords.getLat(), coords.getLng(),
                        hours.getMonday(), hours.getTuesday(), hours.getWednesday(), hours.getThursday(), hours.getFriday(),
                        hours.getSaturday(), hours.getSunday());
            }
        }

        if (oldVersion < 2) {

            // do something else to update the database
            // eg, alter structure: db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            // or update data: db.update("BIKESHOP", "Bill\'s Bikes", "NAME = ?", new String[] {"Bob\'s Bikes"});
        }
    }


    private void setBikeShops(Context context) {
        is = context.getResources().openRawResource(R.raw.bikeshops);
        BikeShopFileReader fileReader = new BikeShopFileReader(is);
        List bikeShopsInput = fileReader.readFile();
        ListIterator<BikeShop> iBikeShopsItr = bikeShopsInput.listIterator();
        this.bikeShops = bikeShopsInput;
    }

    public List getBikeShops() {
        return this.bikeShops;
    }

}

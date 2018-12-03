package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.chadcover.bikeridz.BikeShopFileReader;
import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.bikeshop.Address;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.Coords;
import com.chadcover.bikeridz.bikeshop.Hours;

import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;


public class BikeRidzDBHelper extends SQLiteOpenHelper {
    private InputStream is;
    private Context context;
    protected List<BikeShop> bikeShops;

    public BikeRidzDBHelper(Context context) {
        super(context, BikeRidzDBContract.DB_NAME, null, BikeRidzDBContract.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BikeRidzDBContract.CREATE_BIKERIDZ_TABLE);
        setBikeShops(this.context);
        updateDatabase(db, 0, BikeRidzDBContract.DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL(BikeRidzDBContract.DROP_PROJECT_TABLE);
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
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.SHOP_NAME, name);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.STREET_NUMBER, streetNumber);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.STREET_NAME, streetName);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.CITY, city);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.STATE, state);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.ZIP, zip);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.FULL_ADDRESS, fullAddress);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.PHONE_NUMBER, phoneNumber);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.LATITUDE, latitude);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.LONGITUDE, longitude);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_MON, hoursMon);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_TUES, hoursTues);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_WED, hoursWed);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_THURS, hoursThurs);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_FRI, hoursFri);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_SAT, hoursSat);
        bikeShops.put(BikeRidzDBContract.BikeRidzContract.HOURS_SUN, hoursSun);
        db.insert(BikeRidzDBContract.BikeRidzContract.TABLE_NAME, null, bikeShops);
    }


    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DATABASE-VERSION", Integer.toString(oldVersion));

        if (oldVersion < 1) {
            List<BikeShop> mBikeShops = getBikeShops();
            ListIterator<BikeShop> mBikeShopsItr = mBikeShops.listIterator();
            while ( mBikeShopsItr.hasNext()) {
                BikeShop shop = mBikeShopsItr.next();
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

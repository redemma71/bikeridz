package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chadcover.bikeridz.bikeshop.BikeShop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

public class FindBikeShopActivity extends Activity {


    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private SQLiteDatabase db;
    private Cursor cursor;
    private String todaysHours;
    private List bikeShops;
    private InputStream is;

    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bike_shop);

        SQLiteOpenHelper bikeRidzDatabaseHelper = new BikeRidzDatabaseHelper(this);
        try {
            Log.i("FINDBIKDESHOPACTIVITY", "found the database");
            db = bikeRidzDatabaseHelper.getReadableDatabase();
            cursor = db.query(
                "BIKESHOP",
                new String[] {"_id", "Name", "FullAddress", "PhoneNumber", "Latitude", "Longitude",
                        "HoursMon", "HoursTues", "HoursWed", "HoursThurs",
                        "HoursFri", "HoursSat", "HoursSun"},
                "Name = ?", new String[] {"Big Wheel Bikes"}, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"Name", "FullAddress", "PhoneNumber", "Latitude", "Longitude",
                            "HoursMon", "HoursTues", "HoursWed", "HoursThurs",
                            "HoursFri", "HoursSat", "HoursSun"},
                    new int[] {android.R.id.text1},
                    0
                    );

            if (cursor.moveToFirst()) {
                // get bike shop
                String nameStr = cursor.getString(1);
                String addressStr = cursor.getString(2);
                String phoneNumberStr = cursor.getString(3);

                double latitudeStr = cursor.getInt(4);
                double longitudeStr = cursor.getInt(5);

                String mondayHours = cursor.getString(6);
                String tuesdayHours = cursor.getString(7);
                String wednesdayHours = cursor.getString(8);
                String thursdayHours = cursor.getString(9);
                String fridayHours = cursor.getString(10);
                String saturdayHours = cursor.getString(11);
                String sundayHours = cursor.getString(12);
                Calendar calendar = Calendar.getInstance();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                switch (dayOfWeek) {
                    case 1:
                        todaysHours = mondayHours;
                        break;
                    case 2:
                        todaysHours = tuesdayHours;
                        break;
                    case 3:
                        todaysHours = wednesdayHours;
                        break;
                    case 4:
                        todaysHours = thursdayHours;
                        break;
                    case 5:
                        todaysHours = fridayHours;
                        break;
                    case 6:
                        todaysHours = saturdayHours;
                        break;
                    case 7:
                        todaysHours = sundayHours;
                        break;
                    default:
                        // do nothing
                }


                TextView name = (TextView) findViewById(R.id.bikeShopName);
                name.setText(nameStr);

                TextView description = (TextView) findViewById(R.id.bikeShopAddress);
                description.setText(addressStr);

                TextView phone = (TextView) findViewById(R.id.bikeShopPhoneNumber);
                phone.setText(phoneNumberStr);

                TextView hours = (TextView) findViewById(R.id.bikeShopHours);
                hours.setText("Today's Hours: " + todaysHours);

            }


        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

        ///////////////////////////////////////////////////////////////////////////////////////////
        // helper methods
        ///////////////////////////////////////////////////////////////////////////////////////////



}

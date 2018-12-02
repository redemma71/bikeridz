package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chadcover.bikeridz.bikeshop.BikeShop;

import java.util.Calendar;
import java.util.List;

public class FindBikeShopActivity extends Activity {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private SQLiteDatabase db;
    private Cursor cursor;
    private String todaysHours;
    private String queryStr;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bike_shop);

        //////////////////////////////////////////////////////////////////////////////////////
        // handle intents
        //////////////////////////////////////////////////////////////////////////////////////

        Intent intent = getIntent();
        Double latitude = Double.parseDouble( intent.getStringExtra("latitude") );
        Double longitude = Double.parseDouble( intent.getStringExtra("longitude") );

        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> nearestShops = bikeShop.getBikeShops(this);
        queryStr = bikeShop.getClosestBikeshop(nearestShops, latitude, longitude);
        Log.i("CLOSESTSHOP", queryStr);

        SQLiteOpenHelper bikeRidzDatabaseHelper = new BikeRidzDatabaseHelper(this);
        try {
            db = bikeRidzDatabaseHelper.getReadableDatabase();
            cursor = db.query(
                "BIKESHOP",
                new String[] {"_id", "Name", "FullAddress", "PhoneNumber", "Latitude", "Longitude",
                        "HoursMon", "HoursTues", "HoursWed", "HoursThurs",
                        "HoursFri", "HoursSat", "HoursSun"},
                "Name = ?", new String[] {queryStr}, null, null, null);
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

                //double latitudeStr = cursor.getInt(4);
                //double longitudeStr = cursor.getInt(5);

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
                        todaysHours = sundayHours;
                        break;
                    case 2:
                        todaysHours = mondayHours;
                        break;
                    case 3:
                        todaysHours = tuesdayHours;
                        break;
                    case 4:
                        todaysHours = wednesdayHours;
                        break;
                    case 5:
                        todaysHours = thursdayHours;
                        break;
                    case 6:
                        todaysHours = fridayHours;
                        break;
                    case 7:
                        todaysHours = saturdayHours;
                        break;
                    default:
                        // do nothing
                }

                TextView name = (TextView) findViewById(R.id.bikeShopName);
                name.setText(nameStr);

                TextView description = (TextView) findViewById(R.id.bikeShopAddress);
                description.setText(addressStr);

                TextView phone = (TextView) findViewById(R.id.bikeShopPhoneNumber);
                String phoneNumberFormatted = phoneNumberStr.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)$2-$3");
                phone.setText(phoneNumberFormatted);

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

    public void onMapMyRouteClick(View v) {
        Log.i("ONMAPMYREOUTECLICK","clicked");
    }

    public void onTurnByTurnClick(View v) {
        Log.i("ONTURNBYTURNCLICK", "clicked");
    }



}

package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.database.BikeRidzDBContract;
import com.chadcover.bikeridz.database.BikeRidzDBHelper;
import com.google.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
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
    private BikeShop nearestShop;
    private LatLng latLng;

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
        this.latLng = new LatLng(latitude, longitude);

        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> nearestShops = bikeShop.getBikeShops(this);
        queryStr = bikeShop.getClosestBikeshopName(nearestShops, latitude, longitude);
        LatLng nearestLatLng = new LatLng(latitude, longitude);
        nearestShop = bikeShop.getClosestBikeshop(nearestShops, nearestLatLng.lat, nearestLatLng.lng);
        Log.i("CLOSESTSHOP", queryStr);

        // SQLiteOpenHelper bikeRidzDatabaseHelper = new BikeRidzDatabaseHelper(this);
        SQLiteOpenHelper bikeRidzDBHelper = new BikeRidzDBHelper(this);
        try {
            // db = bikeRidzDatabaseHelper.getReadableDatabase();
            db = bikeRidzDBHelper.getReadableDatabase();
            cursor = db.query(
                    BikeRidzDBContract.BikeRidzContract.TABLE_NAME,
                new String[] {
                        BikeRidzDBContract.BikeRidzContract.SHOP_ID,
                        BikeRidzDBContract.BikeRidzContract.SHOP_NAME,
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
                },
                    "shop_name = ?", new String[] {queryStr}, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {
                            BikeRidzDBContract.BikeRidzContract.SHOP_ID,
                            BikeRidzDBContract.BikeRidzContract.SHOP_NAME,
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
                    },
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
        Log.i("MYLAT", Double.toString(this.latLng.lat));
        Log.i("MYLNG", Double.toString(this.latLng.lng));
        Log.i("DESTINATIONLAT", Double.toString(nearestShop.getCoords().getLat()));
        Log.i("DESTINATIONLONG", Double.toString(nearestShop.getCoords().getLng()));

        String mode = "&mode=b";
        String avoid = "&avoid=ht";
        Uri navigatorIntentUri = Uri.parse(String.format("google.navigation:q=%s,%s%s%s",
                nearestShop.getCoords().getLat(), nearestShop.getCoords().getLng(), mode, avoid));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigatorIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) { // make sure that there is an app that can handle the intent
            startActivity(mapIntent);
        }


    }

    public void onTurnByTurnClick(View v) {
        Log.i("ONTURNBYTURNCLICK", "clicked");


    }
}

package com.chadcover.bikeridz;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity
        implements LocationListener, ActivityCompat.OnRequestPermissionsResultCallback  {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private static final int PERMISSION_REQUEST = 0;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    public String latitude, longitude;

    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////
        // handle location listener
        ////////////////////////////

        if (Build.VERSION.SDK_INT >= 28 &&
                context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            locationManager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

        } catch (Exception e) {
            Log.i("LOCATIONMANAGER", "Error creating location service: " + e.getMessage());
        }


        ///////////////////////////
        // handle listView
        ///////////////////////////

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent intentFindBikeShop = new Intent(MainActivity.this, FindBikeShopActivity.class);
                        intentFindBikeShop.putExtra("latitude",latitude);
                        intentFindBikeShop.putExtra("longitude",longitude);
                        startActivity(intentFindBikeShop);
                        break;
                    case 1:
                        Intent intentBikeInventory = new Intent(MainActivity.this, WishlistActivity.class);
                        startActivity(intentBikeInventory);
                        break;
                    case 2:
                        Intent intentMapMyRide = new Intent(MainActivity.this, MapMyRideActivity.class);
                        intentMapMyRide.putExtra("latitude",latitude);
                        intentMapMyRide.putExtra("longitude",longitude);
                        startActivity(intentMapMyRide);
                        break;
                    case 3:
                        Intent intentTurnByTurn = new Intent(MainActivity.this, TurnByTurnActivity.class);
                        intentTurnByTurn.putExtra("latitude",latitude);
                        intentTurnByTurn.putExtra("longitude",longitude);
                        startActivity(intentTurnByTurn);
                        break;
                    case 4:
                        Intent intentMySelfieMarkers = new Intent(MainActivity.this, MySelfieMarkersActivity.class);
                        startActivity(intentMySelfieMarkers);
                        break;
                }
            }
        };

        ListView listView = findViewById(R.id.bikeRidzOptions);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("ONLOCATIONCHANGED", "changed");
        this.latitude = Double.toString(location.getLatitude());
        this.longitude = Double.toString(location.getLongitude());
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("LOCATIONMANAGER","enabled");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("LOCATIONMANGER","status");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("LOCATIONSERVICE","disabled");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Log.i("PERMISSIONS", "granted");
            } else {
                // Permission request was denied.
                Log.i("PERMISSIONS", "denied");
            }
        }
    }

}

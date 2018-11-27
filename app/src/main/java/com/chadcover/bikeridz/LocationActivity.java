package com.chadcover.bikeridz;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

public class LocationActivity extends Activity implements LocationListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST = 0;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView latText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latText = (TextView) findViewById(R.id.myLocation);

        if (Build.VERSION.SDK_INT >= 28 &&
                context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
        }

        try {
            Log.i("BADABING","testing");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            locationManager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

        } catch (Exception e) {
            Log.i("LOCATIONMANAGER", "Error creating location service: " + e.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latText = (TextView) findViewById(R.id.myLocation);
        latText.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
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
        // END_INCLUDE(onRequestPermissionsResult)
    }

}

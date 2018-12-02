package com.chadcover.bikeridz;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;


public class MapMyRideActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_my_ride);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // get current coordinates from MainActivity and cast as LatLng
        Intent intent = getIntent();
        Double latitude = Double.parseDouble( intent.getStringExtra("latitude") );
        Double longitude = Double.parseDouble( intent.getStringExtra("longitude") );

        // find the nearest bikeshop
        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> allBikeShops = bikeShop.getBikeShops(this);
        BikeShop nearestShop = bikeShop.getClosestBikeshop(allBikeShops, latitude, longitude, true);

        String mode = "&mode=b";
        String avoid = "&avoid=ht";
        Uri navigatorIntentUri = Uri.parse(String.format("google.navigation:q=%s,%s%s%s", nearestShop.getCoords().getLat(), nearestShop.getCoords().getLng(), mode, avoid));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigatorIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) { // make sure that there is an app that can handle the intent
            startActivity(mapIntent);
        }
    }

}

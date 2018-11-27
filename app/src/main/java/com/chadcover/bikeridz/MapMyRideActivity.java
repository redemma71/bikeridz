package com.chadcover.bikeridz;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class MapMyRideActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int INDEX = 0;
    private Geocoder geocoder;

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
        LatLng originLatLng = new LatLng(latitude, longitude);

        // find the nearest bikeshop
        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> allBikeShops = bikeShop.getBikeShops(this);
        BikeShop nearestShop = bikeShop.getClosestBikeshop(allBikeShops, latitude, longitude, true);

        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> originInfo;
        List <Address> destinationInfo;
        try {
            // get geolocation information about origin and destination
            originInfo = geocoder.getFromLocation(latitude, longitude, 1);
            destinationInfo = geocoder.getFromLocation(nearestShop.getCoords().getLat(), nearestShop.getCoords().getLng(), 1);

            // get address strings from info objects
            String originAddress = originInfo.get(0).getAddressLine(0);
            String destinationAddress = destinationInfo.get(0).getAddressLine(0);
            Log.i("GEOCODER-ORIGIN", originAddress);
            Log.i("GEOCODER-DESTINATION", destinationAddress);

            mMap = googleMap;
            setupGoogleMapScreenSettings(googleMap);
            // zoom to origin
            // TODO write a method to dynamically determine zoom level based upon distance between origin & destination
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(originLatLng, 14));

            DirectionsResult results = getDirectionsDetails(originAddress, destinationAddress, TravelMode.BICYCLING);

            if (results != null) {
                addPolyLine(results, mMap);
                addMarkers(results, mMap);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    private DirectionsResult getDirectionsDetails(String origin, String destination, TravelMode mode) {
        DateTime now = new DateTime();
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(mode)
                    .origin(origin)
                    .destination(destination)
                    .departureTime(now)
                    .await();
        } catch (ApiException ae) {
            ae.printStackTrace();
            return null;
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            return null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }



    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext
                .setQueryRateLimit(3)
                .setApiKey(getString(R.string.google_directions_key))
                .setConnectTimeout(5, TimeUnit.SECONDS)
                .setReadTimeout(5, TimeUnit.SECONDS)
                .setWriteTimeout(5, TimeUnit.SECONDS);
    }

    private void setupGoogleMapScreenSettings(GoogleMap mMap) {
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setTrafficEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
    }

    private void addPolyLine(DirectionsResult results, GoogleMap mMap) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[INDEX].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

    // TODO add labels to markers
    private void addMarkers(DirectionsResult results, GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[INDEX].legs[INDEX].startLocation.lat,results.routes[INDEX].legs[INDEX].startLocation.lng)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(results.routes[INDEX].legs[INDEX].endLocation.lat,results.routes[INDEX].legs[INDEX].endLocation.lng)).snippet(getEndLocationTitle(results)));

    }

    private String getEndLocationTitle(DirectionsResult results){
        return  "Time :"+ results.routes[INDEX].legs[INDEX].duration.humanReadable + " Distance :" + results.routes[INDEX].legs[INDEX].distance.humanReadable;
    }
    
}

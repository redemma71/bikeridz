package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.mapquest.Maneuver;
import com.chadcover.bikeridz.mapquest.MapquestRouteParser;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TurnByTurnActivity extends AppCompatActivity implements MapquestRouteParser.AsynResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_by_turn);

        // get current location
        Intent intent = getIntent();
        String latitudeStr =  intent.getStringExtra("latitude") ;
        String longitudeStr = intent.getStringExtra("longitude");

        // find the nearest bikeshop
        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> allBikeShops = bikeShop.getBikeShops(this);
        BikeShop nearestShop = bikeShop.getClosestBikeshop(allBikeShops,
                Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr));

        // an array to hold origin & destination coordinates
        String[] mCoords = new String[2];
        // current coordinates
        mCoords[0] = latitudeStr + "," + longitudeStr;
        // nearest bikeshop coordinates
        mCoords[1] = Double.toString(nearestShop.getCoords().getLat()) +
                "," + Double.toString(nearestShop.getCoords().getLng());

        new MapquestRouteParser(this).execute(mCoords[0],mCoords[1]);

        TextView latView = findViewById(R.id.latitudeView);
        latView.setText(latitudeStr);

        TextView longView = findViewById(R.id.longitudeView);
        longView.setText(longitudeStr);
    }

    @Override
    public void processFinish(List<Maneuver> maneuvers) {
        ListIterator maneuversIter = maneuvers.listIterator();
        while (maneuversIter.hasNext()) {
            Maneuver mManeuver = (Maneuver) maneuversIter.next();
            Log.i("YADDA-NARRATIVE", mManeuver.getNarrative());
        }
    }

}

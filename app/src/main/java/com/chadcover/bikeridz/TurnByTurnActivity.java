package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

        Intent intent = getIntent();
        String latitudeStr =  intent.getStringExtra("latitude") ;
        String longitudeStr = intent.getStringExtra("longitude");

        List<String> myCoords = new ArrayList<String>();
        myCoords.add("4561 West Britton Road,Burbank,OH");
        myCoords.add("130 Anawan Avenue,West Roxbury,MA");

        new MapquestRouteParser(this).execute(myCoords.get(0),myCoords.get(1));

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

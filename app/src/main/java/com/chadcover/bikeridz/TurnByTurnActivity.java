package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TurnByTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_by_turn);

        Intent intent = getIntent();
        String latitudeStr =  intent.getStringExtra("latitude") ;
        String longitudeStr = intent.getStringExtra("longitude");

        TextView latView = findViewById(R.id.latitudeView);
        latView.setText(latitudeStr);

        TextView longView = findViewById(R.id.longitudeView);
        longView.setText(longitudeStr);
    }
}

package com.chadcover.bikeridz.turnbyturn;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chadcover.bikeridz.R;




public class TurnByTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_by_turn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // get current location
        Intent intent = getIntent();
        String latitudeStr =  intent.getStringExtra("latitude") ;
        String longitudeStr = intent.getStringExtra("longitude");

        TurnByTurnFragment tbtFragment = new TurnByTurnFragment();
        tbtFragment.setArguments(getIntent().getExtras());
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.add(R.id.turnByTurnFragment, tbtFragment);
        fragTransaction.commit();







    }



}

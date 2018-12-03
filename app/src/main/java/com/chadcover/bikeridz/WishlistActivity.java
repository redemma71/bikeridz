package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.bike.BikeType;


public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        Bike testBike = new Bike();
        testBike.setName("IRO");
        testBike.setType(BikeType.COMMUTER);
        testBike.setHeight("59cm");
        testBike.setColor("Black");
        testBike.setMaterial("Steel");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(view.getContext(), AddProjectActivity.class);
                //startActivity(intent);
                Log.i("FAB","ulous!");
            }
        });



    }
}

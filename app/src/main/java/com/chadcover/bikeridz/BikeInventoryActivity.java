package com.chadcover.bikeridz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;


public class BikeInventoryActivity extends AppCompatActivity implements BikesListAdapter.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: implement addprojectactivity
                //Intent intent = new Intent(view.getContext(), AddProjectActivity.class);
                //startActivity(intent);
                Log.i("FAB","onClickListener()");
            }
        });
    }

    @Override
    public void onClick(int id, int position) {
        BikeDetailsFragment detailsFragment =
                (BikeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentBikeDetails);
            if (detailsFragment != null) {
                detailsFragment.setBike(id);
            } else {
                Intent intent = new Intent(this, BikeDetailActivity.class);
                intent.putExtra("bikeId", id);
                intent.putExtra("nextBikeId", position);
                startActivity(intent);
            }
    }
}



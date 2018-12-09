package com.chadcover.bikeridz.bike;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.database.BikesDao;

public class BikeDetailActivity extends AppCompatActivity {

    protected BikesDao bikesDao;
    protected BikeDetailsFragment bikeDetailsFragment;
    protected BikePartsFragment bikePartsFragment;
    protected Context context;
    protected int bikeId, position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        bikesDao = bikesDao.getInstance(getApplicationContext());
        bikesDao.openDB();

        // add fragment dynamically
        bikeDetailsFragment = new BikeDetailsFragment();
        bikeDetailsFragment.setArguments(getIntent().getExtras());
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(R.id.bikeDetailFragment, bikeDetailsFragment);
        transaction.commit();

        bikePartsFragment = new BikePartsFragment();
        bikePartsFragment.setArguments(getIntent().getExtras());
        FragmentManager fragManagerParts = getSupportFragmentManager();
        FragmentTransaction transactionParts = fragManagerParts.beginTransaction();
        transactionParts.add(R.id.bikePartsFragment, bikePartsFragment);
        transactionParts.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bikesDao.closeDB();
    }

}

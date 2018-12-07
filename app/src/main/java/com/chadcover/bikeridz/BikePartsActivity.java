package com.chadcover.bikeridz;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.chadcover.bikeridz.database.PartsDao;

public class BikePartsActivity extends AppCompatActivity {

    protected PartsDao partsDao;
    protected BikePartsFragment bikePartsFragment;
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_parts);

        partsDao = partsDao.getInstance(getApplicationContext());
        partsDao.openDB();

        bikePartsFragment = new BikePartsFragment();
        bikePartsFragment.setArguments(getIntent().getExtras());
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(R.id.bikePartsFragment, bikePartsFragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        partsDao.closeDB();
    }

}

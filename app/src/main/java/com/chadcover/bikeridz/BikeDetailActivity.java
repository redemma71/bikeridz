package com.chadcover.bikeridz;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chadcover.bikeridz.database.BikesDao;

public class BikeDetailActivity extends AppCompatActivity {

    protected BikesDao bikesDao;
    protected BikeDetailsFragment bikeDetailsFragment;
    protected BikePartsFragment bikePartsFragment;
    protected Context context;

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
        FragmentManager partsFragManager = getSupportFragmentManager();
        FragmentTransaction partsTransaction = partsFragManager.beginTransaction();
        partsTransaction.add(R.id.bikePartsFragment, bikePartsFragment);
        partsTransaction.commit();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bikesDao.closeDB();
    }

    // TODO: fix this listener; does not loop back to bike 0
    public void onClick(View view){
        int id = bikeDetailsFragment.getBikeId();
        Log.i("THISBIKEID",Integer.toString(id));
        BikesDao bikesDao = BikesDao.getInstance(this.context);
        int numberOfBikes = bikesDao.getNubmerOfBikes();
        Log.i("LISTOFBIKESSIZE",Integer.toString(numberOfBikes));
        bikeDetailsFragment.setBike( (id + 1) % numberOfBikes);
    }

}

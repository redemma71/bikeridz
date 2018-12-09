package com.chadcover.bikeridz.bike;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.database.BikesDao;


public class BikeDetailsFragment extends Fragment {

    protected int bikeId, position;
    protected TextView bikeNameView, bikeTypeView, bikeDescriptionView, bikeMaterialView,
            bikeHeightView, bikeManufacturerView;

    public BikeDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bike_details, container, false);

        ///////////////////////////////////////////////////////////////////////////////////////////
        // view mappings
        ///////////////////////////////////////////////////////////////////////////////////////////

        bikeNameView = view.findViewById(R.id.bikeName);
        bikeDescriptionView = view.findViewById(R.id.bikeDescription);
        bikeMaterialView = view.findViewById(R.id.bikeMaterial);
        bikeHeightView = view.findViewById(R.id.bikeHeight);
        bikeManufacturerView = view.findViewById(R.id.bikeManufacturer);
        bikeHeightView = view.findViewById(R.id.bikeHeight);
        bikeTypeView = view.findViewById(R.id.bikeType);

        if (getArguments() != null) {
            bikeId = getArguments().getInt("bikeId");
            position = getArguments().getInt("position");
            Log.d("BikeDetailsFragment", "bikeId: " + Integer.toString(bikeId));
            Log.d("BikeDetailsFragment", "position: " + Integer.toString(position));
        }

        setBike(bikeId);
        return view;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void setBike(int bikeId) {

        BikesDao bikesDao = BikesDao.getInstance(getContext());
        final Bike bike = bikesDao.getBikeById(bikeId);

        bikeNameView.setText(bike.getName());
        bikeDescriptionView.setText(bike.getDescription());
        bikeTypeView.setText(bike.getTypeName());
        bikeManufacturerView.setText(bike.getManufacturer());
        bikeMaterialView.setText(bike.getMaterial());
        bikeHeightView.setText(bike.getHeight());
    }

    public int getBikeId(){
        return bikeId;
    }

}

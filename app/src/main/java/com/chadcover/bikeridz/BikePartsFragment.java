package com.chadcover.bikeridz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BikePartsFragment extends Fragment {

    protected int bikeId;

    public BikePartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bike_parts, container, false);

        if (getArguments() != null) {
            bikeId = getArguments().getInt("bikeId");
        }

        return view;
    }

}

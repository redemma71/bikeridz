package com.chadcover.bikeridz.bikeinventory;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.database.BikesDao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BikeListFragment extends Fragment {
    BikeListAdapter.Listener listener;

    public BikeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bikes_list, container, false);
        RecyclerView bikesListRecyclerView = (RecyclerView) (v.findViewById(R.id.bikelist_recyclerview));

        BikesDao bikesDao = BikesDao.getInstance(getContext());
        bikesDao.openDB();

        List<Bike> bikes;
        bikes = bikesDao.getAllBikes();

        BikeListAdapter bikeListAdapter = new BikeListAdapter(bikes);
        bikesListRecyclerView.setAdapter(bikeListAdapter);
        bikeListAdapter.setListener((BikeListAdapter.Listener) getActivity());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( getActivity());
        bikesListRecyclerView.setLayoutManager(mLayoutManager);

        return v;
    }

}

package com.chadcover.bikeridz;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadcover.bikeridz.bike.Bike;
import com.chadcover.bikeridz.database.BikesDao;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BikesListFragment extends Fragment {
    BikesListAdapter.Listener listener;

    public BikesListFragment() {
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

        BikesListAdapter bikesListAdapter = new BikesListAdapter(bikes);
        bikesListRecyclerView.setAdapter(bikesListAdapter);
        bikesListAdapter.setListener((BikesListAdapter.Listener) getActivity());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( getActivity());
        bikesListRecyclerView.setLayoutManager(mLayoutManager);

        return v;
    }

}

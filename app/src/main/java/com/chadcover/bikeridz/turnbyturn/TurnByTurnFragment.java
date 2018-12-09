package com.chadcover.bikeridz.turnbyturn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.bikeshop.BikeShop;
import com.chadcover.bikeridz.bikeshop.FindNearestShop;
import com.chadcover.bikeridz.mapquest.Maneuver;
import com.chadcover.bikeridz.mapquest.MapquestRouteParser;

import java.util.List;
import java.util.ListIterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class TurnByTurnFragment extends Fragment implements MapquestRouteParser.AsynResponse {

    private String latitudeStr, longitudeStr;
    private BikeShop nearestShop;
    private String mCoords[];
    private View view;

    public TurnByTurnFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_turn_by_turn, container, false);

        if (getArguments() != null) {
            latitudeStr = getArguments().getString("latitude");
            longitudeStr = getArguments().getString("longitude");
        }

        FindNearestShop bikeShop = new FindNearestShop();
        List<BikeShop> allBikeShops = bikeShop.getBikeShops(getContext());
        nearestShop = bikeShop.getClosestBikeshop(allBikeShops,
                Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr));


        // an array to hold origin & destination coordinates
        // mapquest geocoordinates must be formatted as a string,
        // in following syntax: "latitude,longitude"
        mCoords = new String[2];
        // current coordinates
        mCoords[0] = latitudeStr + "," + longitudeStr;
        // nearest bikeshop coordinates
        mCoords[1] = Double.toString(nearestShop.getCoords().getLat()) +
                "," + Double.toString(nearestShop.getCoords().getLng());

        new MapquestRouteParser(this).execute(mCoords[0],mCoords[1]);


        // Inflate the layout for this fragment
        return view;
    }


    // handle asynchronous results
    @Override
    public void processFinish(List<Maneuver> maneuvers) {

        RecyclerView turnByTurnRecyclerView = (RecyclerView)
                (view.findViewById(R.id.turnbyturn_recyclerview));

        TurnByTurnAdapter turnByTurnAdapter = new TurnByTurnAdapter(maneuvers);
        turnByTurnRecyclerView.setAdapter(turnByTurnAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        turnByTurnRecyclerView.setLayoutManager(mLayoutManager);

        // for testing
        ListIterator maneuversIter = maneuvers.listIterator();
        while (maneuversIter.hasNext()) {
            Maneuver mManeuver = (Maneuver) maneuversIter.next();
            Log.i("YADDA-NARRATIVE",
                    mManeuver.getNarrative() + " | " +
                            mManeuver.getDistance());
        }
    }

}

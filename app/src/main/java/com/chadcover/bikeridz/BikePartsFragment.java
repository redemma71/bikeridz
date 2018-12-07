package com.chadcover.bikeridz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chadcover.bikeridz.bike.Part;
import com.chadcover.bikeridz.database.PartsDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class BikePartsFragment extends Fragment {

    protected int bikeId, position;
    protected TextView partName, partDescription, partManufacturer;

    public BikePartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parts_list, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.partsList_view);

        if (getArguments() != null) {
            bikeId = getArguments().getInt("bikeId");
            position = getArguments().getInt("position");
            Log.i("BikePartsFragment", "bikeId: " + Integer.toString(bikeId));
            Log.i("BikePartsFragment", "position: " + Integer.toString(position));
        }

        PartsDao partsDao = PartsDao.getInstance(getContext());
        partsDao.openDB();
        List<Part> parts;
        parts = partsDao.getPartsByBikeId(bikeId);
        List<String> partsConcatList;
        partsConcatList = new ArrayList<String>();
        ListIterator<Part> partsIter = parts.listIterator();
        while (partsIter.hasNext()) {
            Part mPart = partsIter.next();
            String partString = "";
            partString +=  mPart.getTypeOfPartString() + ": ";
            partString += mPart.getName();
            partsConcatList.add(partString);
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_textview_layout, partsConcatList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(R.id.customTextView);
                textView.setTextColor(Color.BLACK);
                return view;
            }

        };
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
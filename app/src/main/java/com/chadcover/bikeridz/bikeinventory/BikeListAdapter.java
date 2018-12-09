package com.chadcover.bikeridz.bikeinventory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.bike.Bike;

import java.util.List;

public class BikeListAdapter extends RecyclerView.Adapter<BikeListAdapter.BikesListViewHolder> {
    private List<Bike> bikes;
    private Listener listener;

    interface Listener {
        abstract void onClick(int id, int position);
    }

    public BikeListAdapter(List<Bike> bikes) {this.bikes = bikes;
    }

    @Override
    public BikesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bikes_list_item, parent, false);
       return new BikesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikesListViewHolder viewHolder, final int position) {
        viewHolder.bikeNameView.setText(bikes.get(position).getName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener = (Listener) view.getContext();
                if (listener != null) {
                    listener.onClick(bikes.get(position).getId(),
                            bikes.get( (position + 1) % bikes.size()).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bikes.size();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static class BikesListViewHolder extends RecyclerView.ViewHolder {
        private TextView bikeNameView;
        private CardView cardView;
        public BikesListViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            bikeNameView = (TextView) itemView.findViewById(R.id.bikeListName);
        }

    }


}
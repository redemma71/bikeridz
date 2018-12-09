package com.chadcover.bikeridz.turnbyturn;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chadcover.bikeridz.R;
import com.chadcover.bikeridz.mapquest.Maneuver;

import java.util.List;

public class TurnByTurnAdapter extends RecyclerView.Adapter<TurnByTurnAdapter.TurnsViewHolder> {
    private List<Maneuver> maneuvers;

    public TurnByTurnAdapter(List<Maneuver> maneuvers) {
        this.maneuvers = maneuvers;
    }

    @Override
    public TurnsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maneuvers_list_view, parent, false);
        return new TurnsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TurnsViewHolder viewHolder, final int position) {
        String maneuverStr = maneuvers.get(position).getNarrative() +
                " | " + maneuvers.get(position).getTime();
        viewHolder.turnView.setText(maneuverStr);
    }

    @Override
    public int getItemCount() {
        return maneuvers.size();
    }

    public static class TurnsViewHolder extends RecyclerView.ViewHolder {
        private TextView turnView;
        private CardView cardView;

        public TurnsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            turnView = (TextView) itemView.findViewById(R.id.maneuverDetails);
        }
    }
}

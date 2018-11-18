package edu.bu.projectportal;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>{
    private Project[] projects;
    private Listener listener;

    interface Listener {
        abstract void onClick(int position);
    }

    public ProjectListAdapter(Project[] projects){this.projects = projects;}

    @Override
    public ProjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent,
                        false);
        return new ProjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectListViewHolder viewHolder, final int position) {
        //final int position = viewHolder.getAdapterPosition();
        viewHolder.projTitleView.setText(projects[position].getTitle());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener = (Listener)view.getContext();
                if (listener != null)
                    listener.onClick(position);
            }
        });


    }

    public void setListener(Listener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return projects.length;
    }

    //ViewHolder inner class
    public static class ProjectListViewHolder extends RecyclerView.ViewHolder {
        private TextView projTitleView;
        private CardView cardView;
        public ProjectListViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            projTitleView = (TextView) itemView.findViewById(R.id.projListTitleTextViewId);
        }
    }
}

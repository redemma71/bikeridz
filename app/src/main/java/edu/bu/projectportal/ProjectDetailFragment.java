package edu.bu.projectportal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends Fragment {

    int projectId;
    TextView titleTextView, summaryTextView;

    public ProjectDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        titleTextView = view.findViewById(R.id.projTitleTextViewId);
        summaryTextView = view.findViewById(R.id.projSummaryTextViewId);


        if (getArguments()!= null)
            projectId = getArguments().getInt("projectid");

        Log.d("projectid", " " + projectId);
        setProject(projectId);

        return view;
    }

    public void setProject(int projId) {

        projectId = projId;

        titleTextView.setText(projId + ":" + Project.projects[projId].getTitle());
        summaryTextView.setText(Project.projects[projId].getSummary());
    }

    public int getProjectId(){
        return projectId;
    }


}

package edu.bu.projectportal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends Fragment {


    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    protected int projectId;
    protected TextView titleTextView, summaryTextView, authorsTextView, linksTextView, keywordsTextView;
    protected Switch switchButton;
    protected Boolean shouldRun = false;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////

    public ProjectDetailFragment() {
        // required. can be empty.
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);

        ///////////////////////////////////////////////////////////////////////////////////////////
        // view mappings
        ///////////////////////////////////////////////////////////////////////////////////////////
        titleTextView = view.findViewById(R.id.projTitleTextViewId);
        summaryTextView = view.findViewById(R.id.projSummaryTextViewId);
        authorsTextView = view.findViewById(R.id.projAuthorsTextViewId);
        linksTextView = view.findViewById(R.id.projLinksTextViewId);
        keywordsTextView = view.findViewById(R.id.projKeywordsTextViewId);
        switchButton = view.findViewById(R.id.switchButton);

        if (getArguments()!= null)
            projectId = getArguments().getInt("projectid");

        Log.i("projectid", " " + projectId);
        setProject(projectId);

        return view;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // accessors and mutators
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void setProject(int projId) {

        projectId = projId;

        titleTextView.setText(projId + ":" + Project.projects[projId].getTitle());
        summaryTextView.setText(Project.projects[projId].getSummary());

        String[] authors = Project.projects[projectId].getAuthors();
        String authorsConcat = this.concatString(authors);
        authorsTextView.setText(authorsConcat);

        String[] links = Project.projects[projectId].getLinks();
        String linksConcat = this.concatString(links);
        linksTextView.setText(linksConcat);

        String[] keywords = Project.projects[this.projectId].getKeywords();
        String keywordsConcat = this.concatString(keywords);
        keywordsTextView.setText(keywordsConcat);

        boolean isFavorite = Project.projects[projectId].isFavorite();
        switchButton.setChecked(isFavorite);

        switchButton.setTag("DONOTRUN");
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (switchButton.getTag() != null) {
                    switchButton.setTag(null);
                    return;
                }
                changeFavorite();
            }
        });



    }

    public int getProjectId(){
        return projectId;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////


    public void changeFavorite() {
        boolean isFavorite = Project.projects[projectId].isFavorite();
        Project.projects[projectId].setFavorite(!isFavorite);
    }


    public String concatString(String[] stringArray) {
        String string = "";
        for (int i = 0; i < stringArray.length; i++ ) {
            if (i != (stringArray.length - 1) ) {
                string += (stringArray[i] + ", ");
            } else {
                string += stringArray[i];
            }
        }
        return string;
    }


}

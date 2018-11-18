package edu.bu.projectportal;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProjectDetailActivity extends AppCompatActivity implements ProjectEditFragment.OnFragmentInteractionListener {

    ProjectDetailFragment projectDetailFragment;
    ProjectEditFragment projectEditFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        //add fragments dynamically
        //create a fragment object
        projectDetailFragment = new ProjectDetailFragment();
        projectDetailFragment.setArguments(getIntent().getExtras());
        projectEditFragment = new ProjectEditFragment();
        projectEditFragment.setArguments(getIntent().getExtras());

        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.proDetailfragContainer, projectDetailFragment);
        transaction.add(R.id.projectEditFragmentContainer, projectEditFragment);
        // commit the transaction.
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(String content) {
        ProjectDetailFragment projectDetailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.proDetailfragContainer);
        projectDetailFragment.updateTitle(content);
    }


    public void onClick(View view){
        int id = projectDetailFragment.getProjectId();
        projectDetailFragment.setProject( (id + 1) % Project.projects.length);
    }


}
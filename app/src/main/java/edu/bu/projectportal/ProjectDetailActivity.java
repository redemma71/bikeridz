package edu.bu.projectportal;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProjectDetailActivity extends AppCompatActivity {

    ProjectDetailFragment projectDetailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        //add fragments dynamically
        //create a fragment object
        projectDetailFragment = new ProjectDetailFragment();
        projectDetailFragment.setArguments(getIntent().getExtras());

        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.proDetailfragContainer, projectDetailFragment);
        // commit the transaction.
        transaction.commit();
    }


    public void onClick(View view){
        int id = projectDetailFragment.getProjectId();
        projectDetailFragment.setProject( (id + 1) % Project.projects.length);
    }


}
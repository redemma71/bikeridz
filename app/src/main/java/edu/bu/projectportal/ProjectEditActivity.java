package edu.bu.projectportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class ProjectEditActivity extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    ProjectEditFragment projectEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_edit);

        // create a fragment object
        projectEditFragment = new ProjectEditFragment();
        projectEditFragment.setArguments(getIntent().getExtras());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.projectEditFragmentContainer, projectEditFragment);
        transaction.commit();

    }

    public void onClick(View view) {
        int id;
    }


}

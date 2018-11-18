package edu.bu.projectportal;

/**
 * Created by danazh on 4/23/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ProjectsListActivity extends AppCompatActivity implements ProjectListAdapter.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
    }

    @Override
    public void onClick(int position) {
        ProjectDetailFragment detailFragment =
                (ProjectDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailfragment);

        ProjectEditFragment editFragment =
                (ProjectEditFragment) getSupportFragmentManager().findFragmentById(R.id.editFragment);

        if (detailFragment != null &&  editFragment != null) {
            detailFragment.setProject(position);
            editFragment.setProject(position);
        } else {
            Log.i("ONCLICK","new detailFragment & editFragment");
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("projectid", position);
            startActivity(intent);
        }
    }


}

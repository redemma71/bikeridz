package edu.bu.projectportal;

/**
 * Created by danazh on 4/23/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        if (detailFragment != null) {
            detailFragment.setProject(position);
        } else {
            Intent intent = new Intent(this, ProjectDetailActivity.class);
            intent.putExtra("projectid", position);
            startActivity(intent);

        }

    }
}

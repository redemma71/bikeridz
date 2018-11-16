package edu.bu.projectportal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectEditFragment extends Fragment {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    protected int projectId;
    protected EditText titleEditView, summaryEditView, authorsEditView, linksEditView, keywordsTextView;
    protected Switch switchButton;
    protected Boolean shouldRun = false;
    protected TextWatcher textWatcher;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////

    public ProjectEditFragment() {
        // Required empty public constructor
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // required overrides
    ///////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_edit, container, false);

        ///////////////////////////////////////////////////////////////////////////////////////////
        // view mappings
        ///////////////////////////////////////////////////////////////////////////////////////////

        titleEditView = view.findViewById(R.id.projectTitleEditViewId);

        if (getArguments() != null) {
            projectId = getArguments().getInt("projectId");
        }

        getEditTexts(projectId);
        return view;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // accessors and mutators
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void getEditTexts(int projectId) {
        this.projectId = projectId;

        titleEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("editFragment", "testing");
            }
        });

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

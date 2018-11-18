package edu.bu.projectportal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectEditFragment extends Fragment {

    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private OnFragmentInteractionListener mListener;
    private Timer timer;
    protected int projectId;
    protected EditText titleInput, summaryEditView, authorsEditView, linksEditView, keywordsTextView;
    protected String titleUpdate, summaryInput, authorInput, linksInput, keywordsInput;
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

        titleInput = view.findViewById(R.id.projectTitleEditViewId);
        titleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("TEXTWATCHER", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("TEXTWATCHER", "onTextChanged");
                // user is typing; reset timer.
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("TEXTWATCHER", "afterTextChanged");
                if (titleInput.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "You must enter an actual value.", Toast.LENGTH_LONG).show();
                    return;
                }

                // user has finished typing; start the timer.
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        titleUpdate = titleInput.getText().toString();
                        Log.i("TITLEUPDATE", titleUpdate);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // This code will always run on the UI thread, therefore is safe to modify UI elements.
                                onTitleChanged(titleUpdate);
                            }
                        });
                    }
                }, 500); // 500ms delay before the timer executes the "run" method from TimerTask
            }
        });

        if (getArguments() != null) {
            projectId = getArguments().getInt("projectId");
        }

        setProject(projectId);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // accessors and mutators
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void setProject(int projectId) {
        this.projectId = projectId;

        titleInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("editFragment", "testing");
            }
        });

    }



    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void onTitleChanged(String titleUpdate) {
        if (mListener != null) {
            mListener.onFragmentInteraction(titleUpdate);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // interfaces
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String content);
    }




}

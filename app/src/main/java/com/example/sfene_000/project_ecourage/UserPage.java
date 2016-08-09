package com.example.sfene_000.project_ecourage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {link user.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {link user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserPage extends Fragment {

    SessionManager session;
    public static final String ARG_PAGE = "1";

    private int mPage;

    public static UserPage newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        UserPage fragment = new UserPage();
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getContext());
        super.onCreate(savedInstanceState);
        //mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
//        TextView textView = (TextView) view;
//        textView.setText("Fragment #" + 1);
        return view;
    }

}
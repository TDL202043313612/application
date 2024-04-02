package com.example.applications.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.applications.R;


public class ErrorFragment extends Fragment {


    public ErrorFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ErrorFragment newInstance() {
        ErrorFragment fragment = new ErrorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false);
    }
}
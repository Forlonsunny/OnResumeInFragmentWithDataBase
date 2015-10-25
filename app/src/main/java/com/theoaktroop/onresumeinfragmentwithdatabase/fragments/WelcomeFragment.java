package com.theoaktroop.onresumeinfragmentwithdatabase.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theoaktroop.onresumeinfragmentwithdatabase.R;


public class WelcomeFragment extends Fragment {
    //i add logic
    String stringLocation;

private Context context;
    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      context=getActivity();







        View view=inflater.inflate(R.layout.fragment_welcome, container, false);

        return view;
    }



}

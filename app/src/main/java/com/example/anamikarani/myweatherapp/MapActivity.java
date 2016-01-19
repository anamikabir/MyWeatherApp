package com.example.anamikarani.myweatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hamweather.aeris.communication.AerisEngine;

public class MapActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        AerisEngine.initWithKeys("J7FYBjpcjleHBhcZpy6Vo","8vWdn9GJcv9UEWTAQxxA8dcLIdoRwVgewEmF6Xt5", this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        MapFragment myFragment = new MapFragment();
        fragmentTransaction.add(R.id.MapFrame, myFragment);
        fragmentTransaction.commit();
    }
    public void onFragmentInteraction(Uri uri)
    {

    }
}
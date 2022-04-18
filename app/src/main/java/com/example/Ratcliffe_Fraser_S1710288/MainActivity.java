package com.example.Ratcliffe_Fraser_S1710288;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.example.Ratcliffe_Fraser_S1710288.Fragments.CurrentIncidents;
import com.example.Ratcliffe_Fraser_S1710288.Fragments.CurrentRoadworks;
import com.example.Ratcliffe_Fraser_S1710288.Fragments.PlannedRoadworks;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    //Initialise bottom navigation bar
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.cur_incidents);
    }

    //Initialise fragments
    CurrentIncidents currentIncidents = new CurrentIncidents();
    CurrentRoadworks currentRoadworks = new CurrentRoadworks();
    PlannedRoadworks plannedRoadworks = new PlannedRoadworks();

    //Bottom navigation bar implemented and adapted from - https://geeksforgeeks.org/how-to-create-fragment-using-bottom-navigation-in-social-media-android-app/
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //When navigation bar item is selected, call on respective fragment
        switch (item.getItemId()) {
            case R.id.cur_incidents:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, currentIncidents).addToBackStack(null).commit();
                return true;

            case R.id.cur_roadworks:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, currentRoadworks).addToBackStack(null).commit();
                return true;

            case R.id.plan_roadworks:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, plannedRoadworks).addToBackStack(null).commit();
                return true;
        }
        return false;
    }}

//Ratcliffe-Cree_Fraser_S1710288
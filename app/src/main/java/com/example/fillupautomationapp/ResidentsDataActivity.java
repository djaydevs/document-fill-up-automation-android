package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fillupautomationapp.databinding.ActivityResidentsDataBinding;

public class ResidentsDataActivity extends DrawerBaseActivity {

    ActivityResidentsDataBinding activityResidentsDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_residents_data);

        activityResidentsDataBinding = ActivityResidentsDataBinding.inflate(getLayoutInflater());

        setContentView(activityResidentsDataBinding.getRoot());

        allocateActivityTitle("Residents Data");
    }
}
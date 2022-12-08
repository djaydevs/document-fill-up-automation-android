package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fillupautomationapp.databinding.ActivityResidentsRegistrationBinding;

public class ResidentsRegistrationActivity extends DrawerBaseActivity {

    ActivityResidentsRegistrationBinding activityResidentsRegistrationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_residents_registration);

        activityResidentsRegistrationBinding = ActivityResidentsRegistrationBinding.inflate(getLayoutInflater());

        setContentView(activityResidentsRegistrationBinding.getRoot());

        allocateActivityTitle("Residents Registration");
    }
}
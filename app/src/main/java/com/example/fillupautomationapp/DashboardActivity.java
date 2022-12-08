package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fillupautomationapp.databinding.ActivityDashboardBinding;

public class DashboardActivity extends DrawerBaseActivity implements View.OnClickListener {

    ActivityDashboardBinding activityDashboardBinding;
    public CardView cardDFU, cardRR, cardRD, cardLO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        cardDFU = (CardView) findViewById(R.id.cardDocFillUp);
        cardRR = (CardView) findViewById(R.id.cardResReg);
        cardRD = (CardView) findViewById(R.id.cardResData);
        cardLO = (CardView) findViewById(R.id.cardLogout);

        cardDFU.setOnClickListener(this);
        cardRR.setOnClickListener(this);
        cardRD.setOnClickListener(this);
        cardLO.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.cardDocFillUp:
                i = new Intent(this, DocumentFillUpActivity.class);
                startActivity(i);
                break;

            case R.id.cardResReg:
                i = new Intent(this, ResidentsRegistrationActivity.class);
                startActivity(i);
                break;

            case R.id.cardResData:
                i = new Intent(this, ResidentsDataActivity.class);
                startActivity(i);
                break;

            case R.id.cardLogout:
                i = new Intent(this, LogInActivity.class);
                startActivity(i);
                break;
        }
    }
}
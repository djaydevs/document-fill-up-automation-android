package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fillupautomationapp.databinding.ActivityTransactionHitoryBinding;

public class TransactionHitoryActivity extends DrawerBaseActivity {

    ActivityTransactionHitoryBinding activityTransactionHitoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityTransactionHitoryBinding = ActivityTransactionHitoryBinding.inflate(getLayoutInflater());

        setContentView(activityTransactionHitoryBinding.getRoot());

        allocateActivityTitle("Transaction History");
    }
}
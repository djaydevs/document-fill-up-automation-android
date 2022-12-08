package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fillupautomationapp.databinding.ActivityDocumentFillUpBinding;

public class DocumentFillUpActivity extends DrawerBaseActivity {

    ActivityDocumentFillUpBinding activityDocumentFillUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_document_fill_up);

        activityDocumentFillUpBinding = ActivityDocumentFillUpBinding.inflate(getLayoutInflater());

        setContentView(activityDocumentFillUpBinding.getRoot());

        allocateActivityTitle("Document Fill-Up");
    }
}
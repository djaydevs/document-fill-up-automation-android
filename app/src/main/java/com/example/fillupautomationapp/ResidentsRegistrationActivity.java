package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.fillupautomationapp.databinding.ActivityResidentsRegistrationBinding;

import java.util.Calendar;

public class ResidentsRegistrationActivity extends DrawerBaseActivity {
    DatabaseHelper brgyDb;
    SQLiteDatabase as;
    EditText eTDate, eTLname, eTFname, eTMi, eTHn, eTSt, eTAge, eTYos, eTPb, eTCn;
    RadioGroup rg;
    ImageView imgDate;
    int day, month, year;
    ActivityResidentsRegistrationBinding activityResidentsRegistrationBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_residents_registration);
        activityResidentsRegistrationBinding = ActivityResidentsRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityResidentsRegistrationBinding.getRoot());
        allocateActivityTitle("Residents Registration");

        brgyDb = new DatabaseHelper(this);

        eTDate =(EditText) findViewById(R.id.eTDate);
        imgDate =(ImageView) findViewById(R.id.imgDate);
        rg = (RadioGroup) findViewById(R.id.rg);

        eTLname = (EditText) findViewById(R.id.eTLname);
        eTFname = (EditText) findViewById(R.id.eTFname);
        eTMi = (EditText) findViewById(R.id.eTMi);
        eTHn = (EditText) findViewById(R.id.eTHn);
        eTSt = (EditText) findViewById(R.id.eTSt);
        eTAge = (EditText) findViewById(R.id.eTAge);
        eTYos = (EditText) findViewById(R.id.eTYos);
        eTPb = (EditText) findViewById(R.id.eTPb);
        eTCn = (EditText) findViewById(R.id.eTCn);



        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DATE);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(ResidentsRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = month+"-"+day+"-"+year;
                        eTDate.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });
    }
}
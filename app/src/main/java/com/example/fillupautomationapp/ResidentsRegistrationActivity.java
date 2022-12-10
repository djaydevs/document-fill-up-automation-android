package com.example.fillupautomationapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fillupautomationapp.databinding.ActivityResidentsRegistrationBinding;

import java.util.Calendar;

public class ResidentsRegistrationActivity extends DrawerBaseActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase as;
    EditText eTDate, ln, fn, mii, hn, st, a, yos, bd, bp, cn;
    RadioButton g;
    RadioGroup rg;
    ImageView imgDate;
    //Button btnViewRecord;
    int day, month, year;
    ActivityResidentsRegistrationBinding activityResidentsRegistrationBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_residents_registration);
        activityResidentsRegistrationBinding = ActivityResidentsRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityResidentsRegistrationBinding.getRoot());
        allocateActivityTitle("Residents Registration");

        dbHelper = new DatabaseHelper(ResidentsRegistrationActivity.this);

        eTDate =(EditText) findViewById(R.id.eTDate);
        imgDate =(ImageView) findViewById(R.id.imgDate);
        rg = (RadioGroup) findViewById(R.id.rg);
        //btnViewRecord = (Button) findViewById(R.id.btnView);

        ln = (EditText) findViewById(R.id.eTLname);
        fn = (EditText) findViewById(R.id.eTFname);
        mii = (EditText) findViewById(R.id.eTMi);
        hn = (EditText) findViewById(R.id.eTHn);
        st = (EditText) findViewById(R.id.eTSt);
        int selectedGender = rg.getCheckedRadioButtonId();
        g = findViewById(selectedGender);
        a = (EditText) findViewById(R.id.eTAge);
        yos = (EditText) findViewById(R.id.eTYos);
        bd = (EditText) findViewById(R.id.eTDate);
        bp = (EditText) findViewById(R.id.eTPb);
        cn = (EditText) findViewById(R.id.eTCn);

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
    public void refresh(){
        ln.setText("");
        fn.setText("");
        mii.setText("");
        hn.setText("");
        st.setText("");
        rg.clearCheck();
        a.setText("");
        yos.setText("");
        bd.setText("");
        bp.setText("");
        cn.setText("");
    }
    public void displayMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResidentsRegistrationActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
package com.example.fillupautomationapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fillupautomationapp.databinding.ActivityResidentsRegistrationBinding;

import java.util.Calendar;

public class ResidentsRegistrationActivity extends DrawerBaseActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase as;
    EditText eTDate, ln, fn, mii, hn, st, a, yos, bd, bp, cn;
    RadioButton g;
    RadioGroup rg;
    ImageView imgDate;
    Button btnViewRecord, btnViewList, btnAdd;
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
        btnViewRecord = (Button) findViewById(R.id.btnUpdateDelete);
        btnViewList = (Button) findViewById(R.id.btnView);
        btnAdd = (Button) findViewById(R.id.btnSave);

        ln = (EditText) findViewById(R.id.eTLname);
        fn = (EditText) findViewById(R.id.eTFname);
        mii = (EditText) findViewById(R.id.eTMi);
        hn = (EditText) findViewById(R.id.eTHn);
        st = (EditText) findViewById(R.id.eTSt);
        /*int selectedGender = rg.getCheckedRadioButtonId();
        g = findViewById(selectedGender);*/
        a = (EditText) findViewById(R.id.eTAge);
        yos = (EditText) findViewById(R.id.eTYos);
        bp = (EditText) findViewById(R.id.eTPb);
        cn = (EditText) findViewById(R.id.eTCn);

        btnViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResidentsRegistrationActivity.this, activity_record.class);
                startActivity(intent);
            }
        });
        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = dbHelper.getData("0");
                if(res.getCount() == 0){
                    displayMessage("ERROR","No Record Found !");
                }else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("RIN :" + res.getString(0)+"\n");
                        buffer.append("Lastname :" + res.getString(1)+"\n");
                        buffer.append("Firstname :" + res.getString(2)+"\n");
                        buffer.append("Middle initial :" + res.getString(3)+"\n");
                        buffer.append("House # :" + res.getString(4)+"\n");
                        buffer.append("Street :" + res.getString(5)+"\n");
                        buffer.append("Gender :" + res.getString(6)+"\n");
                        buffer.append("Age :" + res.getString(7)+"\n");
                        buffer.append("Year of stay :" + res.getString(8)+"\n");
                        buffer.append("Birthday :" + res.getString(9)+"\n");
                        buffer.append("Birthplace:" + res.getString(10)+"\n");
                        buffer.append("Contact # :" + res.getString(11)+"\n");
                    }
                    displayMessage("Residents Data", buffer.toString());
                }

            }
        });
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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGender = rg.getCheckedRadioButtonId();
                g = findViewById(selectedGender);
                bd = findViewById(R.id.eTDate);
                boolean res = dbHelper.InsertRecord(ln.getText().toString(),fn.getText().toString(),mii.getText().toString(),hn.getText().toString(),st.getText().toString(),
                        g.getText().toString(),a.getText().toString(),yos.getText().toString(),bd.getText().toString(),bp.getText().toString(),
                        cn.getText().toString());
                if (res == true){
                    Toast.makeText(ResidentsRegistrationActivity.this, "Record Added",Toast.LENGTH_LONG).show();
                    refresh();
                }else{
                    Toast.makeText(ResidentsRegistrationActivity.this, "Error in saving record!", Toast.LENGTH_LONG).show();
                }
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
//50:25
}
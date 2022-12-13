package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fillupautomationapp.databinding.ActivityDocumentFillUpBinding;

public class DocumentFillUpActivity extends DrawerBaseActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase as;
    EditText etRIN;
    TextView name, age, stay, address;
    Spinner spinner;
    RadioGroup rg;
    RadioButton rCOC, rCOI, rCOR;
    Button search, save;

    ActivityDocumentFillUpBinding activityDocumentFillUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_document_fill_up);

        activityDocumentFillUpBinding = ActivityDocumentFillUpBinding.inflate(getLayoutInflater());
        setContentView(activityDocumentFillUpBinding.getRoot());
        allocateActivityTitle("Document Fill-Up");

        //to access database
        dbHelper = new DatabaseHelper(DocumentFillUpActivity.this);

        etRIN = (EditText) findViewById(R.id.etSearch);
        name = (TextView) findViewById(R.id.tvName);
        age = (TextView) findViewById(R.id.tvAge);
        stay = (TextView) findViewById(R.id.tvStay);
        address = (TextView) findViewById(R.id.tvAddress);
        spinner = (Spinner) findViewById(R.id.spin);
        rg = (RadioGroup) findViewById(R.id.rg);
        rCOC = (RadioButton) findViewById(R.id.rbtnCOC);
        rCOI = (RadioButton) findViewById(R.id.rbtnCOI);
        rCOR = (RadioButton) findViewById(R.id.rbtnCOR);
        search = (Button) findViewById(R.id.btnSearch);
        save = (Button) findViewById(R.id.btnSave);

        //Spinner data
        String purpose[] = {"Scholarship", "Food Assistance", "Medical Assistance", "Cash Assistance", "Educational Assistance", "Police Clearance", "Employment"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, purpose);
        spinner.setAdapter(dataAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname, lname, mi, street, house;
                String rin = etRIN.getText().toString();
                Cursor res = dbHelper.getData(etRIN.getText().toString());

                if(res.getCount() == 0) {
                    Toast.makeText(DocumentFillUpActivity.this, "Record doesn't exist.", Toast.LENGTH_SHORT).show();
                } else {
                    while (res.moveToNext()) {
                        //retrieve the data from database based on their column index
                       lname=(res.getString(1));
                       fname=(res.getString(2));
                       mi=(res.getString(3));
                       house=(res.getString(4));
                       street=(res.getString(5));
                       name.setText(fname + " " + mi + " "  + lname);
                       address.setText(house + " " + street);
                       age.setText(res.getString(8));
                       stay.setText(res.getString(9));
                    }
                }
            }
        });
    }

}
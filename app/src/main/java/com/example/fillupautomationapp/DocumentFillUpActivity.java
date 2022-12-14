package com.example.fillupautomationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentFillUpActivity extends DrawerBaseActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase as;
    EditText etRIN;
    TextView name, age, stay, address;
    Spinner spinner;
    RadioGroup rg;
    RadioButton choosedoc;
    Button search, save;

    Bitmap docCOI, docCOR, docCOC, scaleCOI, scaleCOR, scaleCOC;

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
        rg = (RadioGroup) findViewById(R.id.rdgChooseDoc);
        //rCOC = (RadioButton) findViewById(R.id.rdCOC);
        //rCOI = (RadioButton) findViewById(R.id.rdCOI);
        //rCOR = (RadioButton) findViewById(R.id.rdCOR);
        search = (Button) findViewById(R.id.btnSearch);
        save = (Button) findViewById(R.id.btnSave);

        docCOI = BitmapFactory.decodeResource(getResources(), R.drawable.doc_indigency);
        docCOR = BitmapFactory.decodeResource(getResources(), R.drawable.doc_residency);
        docCOC = BitmapFactory.decodeResource(getResources(), R.drawable.doc_clearance);
        scaleCOI = Bitmap.createScaledBitmap(docCOI, 816, 1056, false);
        scaleCOR = Bitmap.createScaledBitmap(docCOR, 816, 1056, false);
        scaleCOC = Bitmap.createScaledBitmap(docCOC, 816, 1056, false);

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
                       name.setText(fname + " " + mi + ". "  + lname);
                       address.setText(house + " " + street);
                       age.setText(res.getString(7));
                       stay.setText(res.getString(8));
                    }
                }
            }
        });
        
        savecreatedPDF();
    }

    private void savecreatedPDF() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.rdCOI:


                        PdfDocument myPDfDocument1 = new PdfDocument();
                        Paint myPaint1 = new Paint();

                        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage1 = myPDfDocument1.startPage(myPageInfo1);
                        Canvas canvas1 = myPage1.getCanvas();

                        canvas1.drawBitmap(scaleCOI, 0, 0, myPaint1);

                        myPDfDocument1.finishPage(myPage1);

                        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/Certificate of Indigency.pdf");

                        try {
                            myPDfDocument1.writeTo(new FileOutputStream(file1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        myPDfDocument1.close();
                        Toast.makeText(DocumentFillUpActivity.this, "Document Successfully Saved", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rdCOR:
                        Toast.makeText(DocumentFillUpActivity.this, "Document Successfully Saved", Toast.LENGTH_LONG).show();

                        PdfDocument myPDfDocument2 = new PdfDocument();
                        Paint myPaint2 = new Paint();

                        PdfDocument.PageInfo myPageInfo2 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage2 = myPDfDocument2.startPage(myPageInfo2);
                        Canvas canvas2 = myPage2.getCanvas();

                        canvas2.drawBitmap(scaleCOR, 0, 0, myPaint2);

                        myPDfDocument2.finishPage(myPage2);

                        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/Certificate of Residency.pdf");

                        try {
                            myPDfDocument2.writeTo(new FileOutputStream(file2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        myPDfDocument2.close();
                        break;
                    case R.id.rdCOC:
                        Toast.makeText(DocumentFillUpActivity.this, "Document Successfully Saved", Toast.LENGTH_LONG).show();

                        PdfDocument myPDfDocument3 = new PdfDocument();
                        Paint myPaint3 = new Paint();

                        PdfDocument.PageInfo myPageInfo3 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage3 = myPDfDocument3.startPage(myPageInfo3);
                        Canvas canvas3 = myPage3.getCanvas();

                        canvas3.drawBitmap(scaleCOC, 0, 0, myPaint3);


                        myPDfDocument3.finishPage(myPage3);

                        File file3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/Barangay Clearance.pdf");

                        try {
                            myPDfDocument3.writeTo(new FileOutputStream(file3));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        myPDfDocument3.close();
                        break;
                }
            }
        });
    }

}
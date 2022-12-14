package com.example.fillupautomationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
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
import java.util.Date;
import java.util.Locale;

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
        
        savecreatedPDF(); //call method for saving document
    }

    private void savecreatedPDF() {
        save.setOnClickListener(new View.OnClickListener() { //if save document button was clicked
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                //access current date
                Date thisDate = new Date();
                SimpleDateFormat dateForm = new SimpleDateFormat("MM/dd/Y");
                SimpleDateFormat month = new SimpleDateFormat("MMMM");
                SimpleDateFormat day = new SimpleDateFormat("dd");
                SimpleDateFormat year = new SimpleDateFormat("Y");

                switch (rg.getCheckedRadioButtonId()) { //radio switch for 3 documents
                    case R.id.rdCOI:
                        PdfDocument myPDfDocument1 = new PdfDocument();
                        Paint myPaint1 = new Paint();
                        Paint myPaintDate1 = new Paint();

                        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage1 = myPDfDocument1.startPage(myPageInfo1);
                        Canvas canvas1 = myPage1.getCanvas();

                        canvas1.drawBitmap(scaleCOI, 0, 0, myPaint1); //document image

                        //set document text
                        myPaint1.setTextSize(15);
                        myPaintDate1.setTextSize(10);
                        myPaint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        myPaintDate1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        canvas1.drawText((String) name.getText(), 500, 365, myPaint1);
                        canvas1.drawText((String) age.getText(), 750, 365, myPaint1);
                        canvas1.drawText((String) address.getText(), 293, 410, myPaint1);
                        canvas1.drawText(month.format(thisDate), 440, 597, myPaint1);
                        canvas1.drawText(day.format(thisDate), 560, 597, myPaint1);
                        canvas1.drawText(year.format(thisDate), 603, 597, myPaint1);
                        canvas1.drawText(dateForm.format(thisDate), 610, 782, myPaintDate1);
                        canvas1.drawText(spinner.getSelectedItem().toString().toUpperCase(Locale.ROOT), 433, 560, myPaint1);

                        myPDfDocument1.finishPage(myPage1); //end of first page

                        //save document to downloads folder
                        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), (String) name.getText() + " - Certificate of Indigency.pdf");

                        try { //write file
                            myPDfDocument1.writeTo(new FileOutputStream(file1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myPDfDocument1.close(); //document close
                        //show toast if saving was successful
                        Toast.makeText(DocumentFillUpActivity.this, "Document Saved Successfully\nFind the file at Downloads folder", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rdCOR:
                        PdfDocument myPDfDocument2 = new PdfDocument();
                        Paint myPaint2 = new Paint();
                        Paint myPaintDate2 = new Paint();

                        PdfDocument.PageInfo myPageInfo2 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage2 = myPDfDocument2.startPage(myPageInfo2);
                        Canvas canvas2 = myPage2.getCanvas();

                        canvas2.drawBitmap(scaleCOR, 0, 0, myPaint2);

                        //set document text
                        myPaint2.setTextSize(15);
                        myPaintDate2.setTextSize(10);
                        myPaint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        myPaintDate2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        canvas2.drawText((String) name.getText(), 507, 310, myPaint2);
                        canvas2.drawText((String) age.getText(), 750, 310, myPaint2);
                        canvas2.drawText((String) address.getText(), 530, 332, myPaint2);
                        canvas2.drawText((String) stay.getText(), 696, 355, myPaint2);
                        canvas2.drawText((String) name.getText(), 293, 437, myPaint2);
                        canvas2.drawText(month.format(thisDate), 440, 527, myPaint2);
                        canvas2.drawText(day.format(thisDate), 560, 527, myPaint2);
                        canvas2.drawText(year.format(thisDate), 603, 527, myPaint2);
                        canvas2.drawText(dateForm.format(thisDate), 610, 695, myPaintDate2);
                        canvas2.drawText((String) ((String) name.getText()).toUpperCase(), 293, 832, myPaint2);
                        canvas2.drawText(spinner.getSelectedItem().toString().toUpperCase(Locale.ROOT), 422, 482, myPaint2);

                        myPDfDocument2.finishPage(myPage2); //end of first page

                        //save document to downloads folder
                        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), (String) name.getText() + " - Certificate of Residency.pdf");

                        try { //write file
                            myPDfDocument2.writeTo(new FileOutputStream(file2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myPDfDocument2.close(); //document close
                        //show toast if saving was successful
                        Toast.makeText(DocumentFillUpActivity.this, "Document Saved Successfully\nFind the file at Downloads folder", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rdCOC:
                        PdfDocument myPDfDocument3 = new PdfDocument();
                        Paint myPaint3 = new Paint();
                        Paint myPaintDate3 = new Paint();

                        PdfDocument.PageInfo myPageInfo3 = new PdfDocument.PageInfo.Builder(816, 1056, 1).create();
                        PdfDocument.Page myPage3 = myPDfDocument3.startPage(myPageInfo3);
                        Canvas canvas3 = myPage3.getCanvas();

                        canvas3.drawBitmap(scaleCOC, 0, 0, myPaint3);

                        //set document text
                        myPaint3.setTextSize(15);
                        myPaintDate3.setTextSize(10);
                        myPaint3.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        myPaintDate3.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        canvas3.drawText((String) name.getText(), 495, 317, myPaint3);
                        canvas3.drawText((String) age.getText(), 750, 317, myPaint3);
                        canvas3.drawText((String) address.getText(), 293, 362, myPaint3);
                        canvas3.drawText((String) stay.getText(), 468, 385, myPaint3);
                        canvas3.drawText(month.format(thisDate), 440, 545, myPaint3);
                        canvas3.drawText(day.format(thisDate), 540, 545, myPaint3);
                        canvas3.drawText(year.format(thisDate), 593, 545, myPaint3);
                        canvas3.drawText(dateForm.format(thisDate), 610, 722, myPaintDate3);
                        canvas3.drawText((String) ((String) name.getText()).toUpperCase(), 290, 849, myPaint3);
                        canvas3.drawText(spinner.getSelectedItem().toString().toUpperCase(Locale.ROOT), 420, 512, myPaint3);

                        myPDfDocument3.finishPage(myPage3); //end of first page

                        //save document to downloads folder
                        File file3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), (String) name.getText() + " - Barangay Clearance.pdf");

                        try { //write file
                            myPDfDocument3.writeTo(new FileOutputStream(file3));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myPDfDocument3.close(); //document close
                        //show toast if saving was successful
                        Toast.makeText(DocumentFillUpActivity.this, "Document Saved Successfully\nFind the file at Downloads folder", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

}
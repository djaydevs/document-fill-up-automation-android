package com.example.fillupautomationapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.fillupautomationapp.databinding.ActivityResidentsDataBinding;

public class ResidentsDataActivity extends DrawerBaseActivity {
    DatabaseHelper dbHelper;
    String[] id;
    String[] ln;
    String[] fn;
    String[] mii;
    String[] hn;
    String[] st;
    String[] g;
    String[] a;
    String[] yos;
    String[] bd;
    String[] bp;
    String[] cn;
    ListView lvRecord;
    View myView;
    Dialog dialog;
    ActivityResidentsDataBinding activityResidentsDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residents_data);
        activityResidentsDataBinding = ActivityResidentsDataBinding.inflate(getLayoutInflater());
        setContentView(activityResidentsDataBinding.getRoot());
        allocateActivityTitle("Residents Data");

        myView = getLayoutInflater().inflate(R.layout.edit_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(ResidentsDataActivity.this);
        alert.setView(myView);
        dialog = alert.create();

        dbHelper = new DatabaseHelper(ResidentsDataActivity.this);
        getData();

        lvRecord.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String a = parent.getItemAtPosition(position).toString();
                retrieveRecord(a);
                return true;
            }
        });
    }
    public void getData(){
        lvRecord = (ListView) findViewById(R.id.lvRecord);
        Cursor res = dbHelper.getData("0");
        if(res.getCount() == 0){
            Toast.makeText(this, "No Record Found", Toast.LENGTH_LONG).show();
            id= new String[]{"No Record Found"};
            ln= new String[]{"No Record Found"};
            fn= new String[]{"No Record Found"};
            mii= new String[]{"No Record Found"};
            hn= new String[]{"No Record Found"};
            st= new String[]{"No Record Found"};
            g= new String[]{"No Record Found"};
            a= new String[]{"No Record Found"};
            yos= new String[]{"No Record Found"};
            bd= new String[]{"No Record Found"};
            bp= new String[]{"No Record Found"};
            cn= new String[]{"No Record Found"};
        }else{
            id = new String[res.getCount()];
            ln= new String[res.getCount()];
            fn= new String[res.getCount()];
            mii= new String[res.getCount()];
            hn= new String[res.getCount()];
            st= new String[res.getCount()];
            g= new String[res.getCount()];
            a= new String[res.getCount()];
            yos=new String[res.getCount()];
            bd= new String[res.getCount()];
            bp= new String[res.getCount()];
            cn= new String[res.getCount()];
            int ctr = 0;
            while(res.moveToNext()){
                id[ctr] = res.getString(0);
                ln[ctr] = res.getString(1);
                fn[ctr] = res.getString(2);
                mii[ctr] = res.getString(3);
                hn[ctr] = res.getString(4);
                st[ctr] = res.getString(5);
                g[ctr] = res.getString(6);
                a[ctr] = res.getString(7);
                yos[ctr] = res.getString(8);
                bd[ctr] = res.getString(9);
                bp[ctr] = res.getString(10);
                cn[ctr] = res.getString(11);
                ctr++;
            }
        }
        CustomAdapter ca = new CustomAdapter(this, id, ln, fn, mii, hn, st, g, a, yos, bd, bp, cn);
        lvRecord.setAdapter(ca);
    }
    public void deleteRecord(String id){
        final String getId =id;
        DialogInterface.OnClickListener dialoginterface = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        boolean res = dbHelper.deleteData(getId);
                        if(res == true){
                            dialog.dismiss();
                            Toast.makeText(ResidentsDataActivity.this,"Record Deleted !", Toast.LENGTH_LONG).show();
                            getData();
                        }else{
                            Toast.makeText(ResidentsDataActivity.this,"Error!", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        AlertDialog.Builder alert = new AlertDialog.Builder(ResidentsDataActivity.this);
        alert.setCancelable(true);
        alert.setTitle("DELETE RECORD");
        alert.setMessage("Are you sure you want to delete this record ?")
                .setPositiveButton("YES", dialoginterface)
                .setNegativeButton("NO",dialoginterface).show();
    }
    public void retrieveRecord(String id){
        final String getId = id;
        Cursor res = dbHelper.getData(id);
        EditText eTLn = (EditText)myView.findViewById(R.id.editLn);
        EditText eTFn = (EditText)myView.findViewById(R.id.editFn);
        EditText eTMi = (EditText)myView.findViewById(R.id.editMi);
        EditText eTHn = (EditText)myView.findViewById(R.id.editHn);
        EditText eTSt = (EditText)myView.findViewById(R.id.editSt);
        EditText eTG = (EditText)myView.findViewById(R.id.editGender);
        EditText eTAge = (EditText)myView.findViewById(R.id.editAge);
        EditText eTYos = (EditText)myView.findViewById(R.id.editYos);
        EditText eTDate = (EditText)myView.findViewById(R.id.editDate);
        EditText eTPb = (EditText)myView.findViewById(R.id.editPb);
        EditText eTCn = (EditText)myView.findViewById(R.id.editCn);
        Button btnSave = (Button)myView.findViewById(R.id.btnSaveUpdate);
        Button btnDelete= (Button)myView.findViewById(R.id.btnDelete);
        if(res.getCount() == 0){
            Toast.makeText(ResidentsDataActivity.this,"No Record Found !",Toast.LENGTH_LONG).show();
        }else{
            while(res.moveToNext()){
                eTLn.setText(res.getString(1));
                eTFn.setText(res.getString(2));
                eTMi.setText(res.getString(3));
                eTHn.setText(res.getString(4));
                eTSt.setText(res.getString(5));
                eTG.setText(res.getString(6));
                eTAge.setText(res.getString(7));
                eTYos.setText(res.getString(8));
                eTDate.setText(res.getString(9));
                eTPb.setText(res.getString(10));
                eTCn.setText(res.getString(11));
            }
        }
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = dbHelper.updateData(getId, eTLn.getText().toString(), eTFn.getText().toString(), eTMi.getText().toString(), eTHn.getText().toString(),
                        eTSt.getText().toString(), eTG.getText().toString(), eTAge.getText().toString(), eTYos.getText().toString(), eTDate.getText().toString(),
                        eTPb.getText().toString(), eTCn.getText().toString());
                if(res == true){
                    Toast.makeText(ResidentsDataActivity.this,"Record Updated", Toast.LENGTH_LONG).show();
                    getData();
                    dialog.dismiss();
                }else{
                    Toast.makeText(ResidentsDataActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                deleteRecord(getId);
            }
        });

    }
}
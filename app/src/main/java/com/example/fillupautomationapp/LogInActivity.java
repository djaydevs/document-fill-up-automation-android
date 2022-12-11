package com.example.fillupautomationapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((username.getText().toString().equals("admin")) &&
                        (password.getText().toString().equals("admin"))){
                    Toast.makeText(LogInActivity.this, "Login Successfully"
                            ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogInActivity.this,DashboardActivity.class);
                    startActivity(intent);
                }
                //alerdialog for incorrect input
                else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LogInActivity.this);
                    alert.setTitle("ALERT MESSAGE");
                    alert.setMessage("Incorrect username or password !");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            username.setText("");
                            password.setText("");
                        }
                    });
                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alert.show();
                }
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
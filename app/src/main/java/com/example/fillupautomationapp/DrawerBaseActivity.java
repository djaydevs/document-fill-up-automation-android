package com.example.fillupautomationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) { //setting navigation drawer
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NavigationView navigationView =  drawerLayout.findViewById(R.id.new_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { //switch between navigation items
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.navDash:
                startActivity(new Intent(this, DashboardActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.navDF:
                startActivity(new Intent(this, DocumentFillUpActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.navRR:
                startActivity(new Intent(this, ResidentsRegistrationActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.navRD:
                startActivity(new Intent(this, ResidentsDataActivity.class));
                overridePendingTransition(0,0);
                break;

            case R.id.navLogout:
                startActivity(new Intent(this, LogInActivity.class));
                overridePendingTransition(0,0);
                break;

        }
        return false;
    }

    protected void allocateActivityTitle(String titleString) { //set navigation title
        if (getSupportActionBar() !=null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}
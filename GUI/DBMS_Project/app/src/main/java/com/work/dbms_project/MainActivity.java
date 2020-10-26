package com.work.dbms_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav);
        toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open_drawer,R.string.navigation_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:break;
            case R.id.doctor:startActivity(new Intent(MainActivity.this,DoctorActivity.class));
            break;
            case R.id.patient:startActivity(new Intent(MainActivity.this,PatientActivity.class));
                break;
            case R.id.appointment:startActivity(new Intent(MainActivity.this,AppointimentActivity.class));
                break;
            case R.id.drug:startActivity(new Intent(MainActivity.this,DrugActivity.class));
                break;
            case R.id.requistion:startActivity(new Intent(MainActivity.this,RequistionActivity.class));
                break;
            case R.id.staff:startActivity(new Intent(MainActivity.this,StaffActivity.class));
                break;
            case R.id.ward:startActivity(new Intent(MainActivity.this,WardActivity.class));
                break;
        }

        return true;
    }
}
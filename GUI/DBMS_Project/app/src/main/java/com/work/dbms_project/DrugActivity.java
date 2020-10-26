package com.work.dbms_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.work.dbms_project.appointment.InsertAppointment;
import com.work.dbms_project.drug.DeleteDrug;
import com.work.dbms_project.drug.InsertDrug;
import com.work.dbms_project.drug.ViewDrug;


public class DrugActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DrugActivity.this,MainActivity.class));
        super.onBackPressed();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_drug, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.insert);
        openFragment(InsertDrug.newInstance("", ""));
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.view:
                                openFragment(ViewDrug.newInstance("", ""));
                                return true;
                            case R.id.insert:
                                openFragment(InsertDrug.newInstance("", ""));
                                return true;
                            case R.id.delete:
                                openFragment(DeleteDrug.newInstance("", ""));
                                return true;
                        }
                        return false;
                    }
                };
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
}
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
import com.work.dbms_project.patients.DeletePatient;
import com.work.dbms_project.patients.InsertPatient;
import com.work.dbms_project.patients.ViewPatient;

public class PatientActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(PatientActivity.this,MainActivity.class));

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_patient, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.insert);
        openFragment(InsertPatient.newInstance("", ""));
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.view:
                                openFragment(ViewPatient.newInstance("", ""));
                                return true;
                            case R.id.insert:
                                openFragment(InsertPatient.newInstance("", ""));
                                return true;
                            case R.id.delete:
                                openFragment(DeletePatient.newInstance("", ""));
                                return true;
                        }
                        return false;
                    }
                };
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
}
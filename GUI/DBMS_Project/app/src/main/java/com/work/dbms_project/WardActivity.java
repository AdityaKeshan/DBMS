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
import com.work.dbms_project.ward.DeleteWard;
import com.work.dbms_project.ward.InsertWard;
import com.work.dbms_project.ward.ViewWard;

public class WardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(WardActivity.this,MainActivity.class));

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.insert);
        openFragment(InsertWard.newInstance("", ""));
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.view:
                                openFragment(ViewWard.newInstance("", ""));
                                return true;
                            case R.id.insert:
                                openFragment(InsertWard.newInstance("", ""));
                                return true;
                            case R.id.delete:
                                openFragment(DeleteWard.newInstance("", ""));
                                return true;
                        }
                        return false;
                    }
                };
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
}
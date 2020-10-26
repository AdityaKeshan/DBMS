package com.work.dbms_project.doctors;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.DoctorDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertDoctor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertDoctor extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertDoctor() {
        // Required empty public constructor
    }
    EditText firstname,lastname,speciality,clinicnumber,phonenumber;
    Button insert;
    DoctorDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new DoctorDatabaseHelper(view.getContext());

        firstname=getView().findViewById(R.id.first_name_doc);
        lastname=getView().findViewById(R.id.last_name_doc);
        speciality=getView().findViewById(R.id.speciality_doc);
        clinicnumber=getView().findViewById(R.id.clinic_no_doc);
        phonenumber=getView().findViewById(R.id.phone_doc);
        insert=getView().findViewById(R.id.insert_doctor_button);
        final View v1=view;
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v1.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                databaseHelper.addUser(clinicnumber.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),speciality.getText().toString(),
                        phonenumber.getText().toString());
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertDoctor.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertDoctor newInstance(String param1, String param2) {
        InsertDoctor fragment = new InsertDoctor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_doctor, container, false);
    }
}
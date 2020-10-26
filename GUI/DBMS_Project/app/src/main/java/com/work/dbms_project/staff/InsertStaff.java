package com.work.dbms_project.staff;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.WardActivity;
import com.work.dbms_project.databasehelpers.StaffDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertStaff#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertStaff extends Fragment {
    EditText firstname,lastname,salary,ward_no,phonenumber,address,date,id,degree,institution;
    Button insert;
    StaffDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id=getView().findViewById(R.id.id_staff);
        databaseHelper=new StaffDatabaseHelper(view.getContext());
        firstname=getView().findViewById(R.id.first_name_staff);
        lastname=getView().findViewById(R.id.last_name_staff);
        salary=getView().findViewById(R.id.salary_staff);
        ward_no=getView().findViewById(R.id.ward_staff);
        phonenumber=getView().findViewById(R.id.phone_staff);
        address=getView().findViewById(R.id.address_staff);
        date=getView().findViewById(R.id.staff_dob);
        degree=getView().findViewById(R.id.degree_staff);
        institution=getView().findViewById(R.id.insti_staff);
        insert=getView().findViewById(R.id.insert_stafftor_button);
        final View v1=view;
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v1.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                databaseHelper.addUser(id.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),address.getText().toString(),
                        phonenumber.getText().toString(),date.getText().toString(),salary.getText().toString(),ward_no.getText().toString(),degree.getText().toString()
                ,institution.getText().toString());
            }
        });
        myCalendar=Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v1.getContext(), date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        date.setText(sdf.format(myCalendar.getTime()));
    }
    Calendar myCalendar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertStaff() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertStaff.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertStaff newInstance(String param1, String param2) {
        InsertStaff fragment = new InsertStaff();
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
        return inflater.inflate(R.layout.fragment_insert_staff, container, false);
    }
}
package com.work.dbms_project.patients;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.PatientDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertPatient extends Fragment {
    EditText firstname,lastname,clinic_no,patient_id,phonenumber,address,date,in_date,leave_date,bed_no,ward_no,out_date,out_time;
    Button insert;
    RadioGroup r,record;
    Calendar myCalendar;
    PatientDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstname=getView().findViewById(R.id.first_name_patient);
        lastname=getView().findViewById(R.id.last_name_patient);
        clinic_no=getView().findViewById(R.id.clinic_no_patient);
        databaseHelper=new PatientDatabaseHelper(view.getContext());
        patient_id=getView().findViewById(R.id.id_patient);
        phonenumber=getView().findViewById(R.id.phone_patient);
        address=getView().findViewById(R.id.address_patient);
        date=getView().findViewById(R.id.dob_patient);
        in_date=getView().findViewById(R.id.indate);
        leave_date=getView().findViewById(R.id.leavedate);
        bed_no=getView().findViewById(R.id.Bed_no);
        ward_no=getView().findViewById(R.id.ward_no);
        out_date=getView().findViewById(R.id.out_date);
        out_time=getView().findViewById(R.id.out_time);
        insert=getView().findViewById(R.id.patient_button);
        r=getView().findViewById(R.id.radio_button);
        record=getView().findViewById(R.id.radio_button_records);
        record.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.new_record:
                        firstname.setVisibility(View.VISIBLE);
                     
                        lastname.setVisibility(View.VISIBLE);
                       
                        clinic_no.setVisibility(View.VISIBLE);
                     
                        address.setVisibility(View.VISIBLE);
                      
                        phonenumber.setVisibility(View.VISIBLE);
                        break;
                    case R.id.existing:
                        firstname.setVisibility(View.GONE);

                        lastname.setVisibility(View.GONE);

                        clinic_no.setVisibility(View.GONE);

                        address.setVisibility(View.GONE);

                        phonenumber.setVisibility(View.GONE);
                        date.setVisibility(View.GONE);
                        break;
                }
            }
        });
        final View v1=view;
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v1.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                int d=r.getCheckedRadioButtonId();
                int e=record.getCheckedRadioButtonId();
                if(e==R.id.new_record)
                {
                    if(d==R.id.cb_inpatient)
                    {
                        databaseHelper.addUser(patient_id.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),
                                clinic_no.getText().toString(),address.getText().toString(),phonenumber.getText().toString(),
                                date.getText().toString(),
                                in_date.getText().toString(),leave_date.getText().toString(),bed_no.getText().toString(),ward_no.getText().toString(),
                                " "," ");
                    }
                    else if(d==R.id.cb_outpatient)
                    {
                        databaseHelper.addUser(patient_id.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),
                                clinic_no.getText().toString(),address.getText().toString(),phonenumber.getText().toString(),
                                date.getText().toString(),
                                " "," "," "," ",
                                out_date.getText().toString(),out_time.getText().toString());
                    }
                    else
                    {
                        databaseHelper.addUser(patient_id.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),
                                clinic_no.getText().toString(),address.getText().toString(),phonenumber.getText().toString(),
                                date.getText().toString(),
                                " "," "," "," ",
                                " "," ");
                    }

                }
                else
                {
                    if(d==R.id.cb_inpatient)
                    {
                        databaseHelper.addUser(patient_id.getText().toString()," "," ",
                                " "," "," ",
                                " ",
                                in_date.getText().toString(),leave_date.getText().toString(),bed_no.getText().toString(),ward_no.getText().toString(),
                                " "," ");
                    }
                    else if(d==R.id.cb_outpatient)
                    {
                        databaseHelper.addUser(patient_id.getText().toString()," "," ",
                                " "," "," ",
                                " ",
                                " "," "," "," ",
                                out_date.getText().toString(),out_time.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(v1.getContext(), "Invalid rule", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        myCalendar= Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


                date.setText(sdf.format(myCalendar.getTime()));
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
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


                in_date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        in_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v1.getContext(), date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date3 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


                leave_date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        leave_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v1.getContext(), date3, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener date4= new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


                out_date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        out_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(v1.getContext(), date4, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        out_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(v1.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String meridian="";
                        if(selectedHour>12)
                        {
                            selectedHour-=12;
                            meridian="PM";
                        }
                        else
                        {
                            meridian="AM";
                        }
                        String sm=String.valueOf(selectedMinute);
                        if(selectedMinute<=9)
                        {
                            sm=0+sm;
                        }
                        out_time.setText( selectedHour + ":" + sm+" "+meridian);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.cb_inpatient:
                        out_date.setVisibility(View.GONE);
                        out_time.setVisibility(View.GONE);
                        in_date.setVisibility(View.VISIBLE);
                        leave_date.setVisibility(View.VISIBLE);
                        bed_no.setVisibility(View.VISIBLE);
                        ward_no.setVisibility(View.VISIBLE);
                        break;
                    case R.id.cb_outpatient:
                        out_date.setVisibility(View.VISIBLE);
                        out_time.setVisibility(View.VISIBLE);
                        in_date.setVisibility(View.GONE);
                        leave_date.setVisibility(View.GONE);
                        bed_no.setVisibility(View.GONE);
                        ward_no.setVisibility(View.GONE);
                        break;
                        
                }
            }
        });
        
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertPatient newInstance(String param1, String param2) {
        InsertPatient fragment = new InsertPatient();
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
        return inflater.inflate(R.layout.fragment_insert_patient, container, false);
    }
}
package com.work.dbms_project.appointment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.AppointmentDatabaseHelper;
import com.work.dbms_project.usermodels.AppointmentUserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertAppointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertAppointment extends Fragment {
    EditText appointment_no,date,time,patient_id,staff_id;
    Button insert;
    SQLiteDatabase database;
    AppointmentDatabaseHelper databaseHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Calendar myCalendar;
    @Override
    public void onViewCreated(@NonNull View vie, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(vie, savedInstanceState);
        final View view=vie;
        myCalendar=Calendar.getInstance();
        databaseHelper=new AppointmentDatabaseHelper(vie.getContext());
        appointment_no=getView().findViewById(R.id.appointment_no_insert);
        date=getView().findViewById(R.id.date_appointment_insert);
        time=getView().findViewById(R.id.appointment_time_insert);
        patient_id=getView().findViewById(R.id.appointment_patient_id_insert);
        staff_id=getView().findViewById(R.id.appointment_staff_id_insert);
        insert=getView().findViewById(R.id.insert_appointment_button);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                databaseHelper.addUser(appointment_no.getText().toString(),date.getText().toString(),time.getText().toString(),patient_id.getText().toString(),staff_id.getText().toString());
            }
        });

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
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
                        time.setText( selectedHour + ":" + sm+" "+meridian);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
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
                new DatePickerDialog(view.getContext(), date1, myCalendar
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
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertAppointment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertAppointment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertAppointment newInstance(String param1, String param2) {
        InsertAppointment fragment = new InsertAppointment();
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
        return inflater.inflate(R.layout.fragment_insert_appointment, container, false);
    }
}
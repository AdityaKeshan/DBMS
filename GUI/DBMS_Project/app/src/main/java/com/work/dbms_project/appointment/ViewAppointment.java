package com.work.dbms_project.appointment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.AppointmentDatabaseHelper;
import com.work.dbms_project.usermodels.AppointmentUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewAppointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewAppointment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText appoint_no;
    Button see;
    TextView date,time,patient_id,staff_id;
    AppointmentDatabaseHelper databaseHelper;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new AppointmentDatabaseHelper(view.getContext());
        appoint_no=getView().findViewById(R.id.doc_clinic_no_search);
        see=getView().findViewById(R.id.view_appointment_button);
        date=getView().findViewById(R.id.date_appointment_view);
        time=getView().findViewById(R.id.appointment_time_view);
        patient_id=getView().findViewById(R.id.appointment_patient_id_view);
        staff_id=getView().findViewById(R.id.appointment_staff_id_view);
        final View vi=view;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentUserModel userModel=databaseHelper.getResult(appoint_no.getText().toString());
                if(userModel!=null) {
                    date.setText(userModel.getDate());
                    time.setText(userModel.getTime());
                    patient_id.setText(userModel.getPatient_id());
                    staff_id.setText(userModel.getStaff_id());
                }
                else
                {
                    Toast.makeText(view.getContext(), "Invalid no.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewAppointment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewAppointment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewAppointment newInstance(String param1, String param2) {
        ViewAppointment fragment = new ViewAppointment();
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
        return inflater.inflate(R.layout.fragment_view_appointment, container, false);
    }
}
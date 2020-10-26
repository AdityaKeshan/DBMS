package com.work.dbms_project.patients;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.PatientDatabaseHelper;
import com.work.dbms_project.usermodels.PatientUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPatient extends Fragment {
    TextView firstname,lastname,clinic_no,patient_id,phonenumber,address,date,in_date,leave_date,bed_no,ward_no,out_date,out_time;
    EditText patient_ID;
    Button see;
    LinearLayout indate,leave,bed,ward,outd,outt;
    PatientDatabaseHelper databaseHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new PatientDatabaseHelper(view.getContext());
        indate=getView().findViewById(R.id.lin_indate);
        leave=getView().findViewById(R.id.lin_leave_date);
        bed=getView().findViewById(R.id.lin_Bed_no);
        ward=getView().findViewById(R.id.lin_ward_no);
        outd=getView().findViewById(R.id.lin_out_date);
        outt=getView().findViewById(R.id.lin_out_time);
        firstname=getView().findViewById(R.id.first_name_patient_view);
        lastname=getView().findViewById(R.id.last_name_patient_view);
        clinic_no=getView().findViewById(R.id.clinic_no_patient_view);
        patient_ID=getView().findViewById(R.id.doc_clinic_no_search_view_patient);
        phonenumber=getView().findViewById(R.id.phone_patient_view);
        address=getView().findViewById(R.id.address_patient_view);
        date=getView().findViewById(R.id.dob_patient_view);
        in_date=getView().findViewById(R.id.indate_view);
        leave_date=getView().findViewById(R.id.leavedate_view);
        bed_no=getView().findViewById(R.id.Bed_no_view);
        ward_no=getView().findViewById(R.id.ward_no_view);
        out_date=getView().findViewById(R.id.out_date_view);
        out_time=getView().findViewById(R.id.out_time_view);
//        in_date=getView().findViewById(R.id.indate);
//        in_date.setText("AB");
//        leave_date=getView().findViewById(R.id.leavedate);
//        leave_date.setText("AB");
//        bed_no=getView().findViewById(R.id.Bed_no);
//        bed_no.setText("AB");
//        ward_no=getView().findViewById(R.id.ward_no);
//        ward_no.setText("AB");
//        out_date=getView().findViewById(R.id.out_date);
//        out_date.setText("AB");
//        out_time=getView().findViewById(R.id.out_time);
//        out_time.setText("AB");
        see=getView().findViewById(R.id.patient_view_tor_button);
        final View v1=view;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientUserModel userModel=databaseHelper.getResult(patient_ID.getText().toString());
                if(userModel!=null) {
                    if (userModel.getIn_date().equals(" ")) {
                        in_date.setVisibility(View.GONE);
                        leave_date.setVisibility(View.GONE);
                        ward_no.setVisibility(View.GONE);
                        bed_no.setVisibility(View.GONE);
                        indate.setVisibility(View.GONE);
                        leave.setVisibility(View.GONE);
                        bed.setVisibility(View.GONE);
                        ward.setVisibility(View.GONE);
                    }
                    if (userModel.getOut_date().equals(" ")) {
                        out_date.setVisibility(View.GONE);
                        out_time.setVisibility(View.GONE);
                        outd.setVisibility(View.GONE);
                        outt.setVisibility(View.GONE);
                    }
                    firstname.setText(userModel.getFirstname());
                    lastname.setText(userModel.getLastname());
                    clinic_no.setText(userModel.getClinic_no());
                    address.setText(userModel.getAddress());
                    date.setText(userModel.getDob());
                    phonenumber.setText(userModel.getPhone());
                    in_date.setText(userModel.getIn_date());
                    leave_date.setText(userModel.getLeave_date());
                    ward_no.setText(userModel.getWard_no());
                    bed_no.setText(userModel.getBed_no());
                    out_time.setText(userModel.getOut_time());
                    out_date.setText(userModel.getOut_date());
                }
                else
                {
                    Toast.makeText(v1.getContext(), "Invalid no.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPatient newInstance(String param1, String param2) {
        ViewPatient fragment = new ViewPatient();
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
        return inflater.inflate(R.layout.fragment_view_patient, container, false);
    }
}
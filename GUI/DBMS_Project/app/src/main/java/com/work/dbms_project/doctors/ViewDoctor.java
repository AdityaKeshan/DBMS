package com.work.dbms_project.doctors;

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
import com.work.dbms_project.databasehelpers.DoctorDatabaseHelper;
import com.work.dbms_project.usermodels.DoctorUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDoctor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDoctor extends Fragment {
    TextView firstname,lastname,speciality,phonenumber;
    EditText clinicnumber;
    Button see;
    DoctorDatabaseHelper databaseHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new DoctorDatabaseHelper(view.getContext());
        firstname=getView().findViewById(R.id.first_name_doc_view);
        lastname=getView().findViewById(R.id.last_name_doc_view);
        speciality=getView().findViewById(R.id.speciality_doc_view);
        clinicnumber=getView().findViewById(R.id.doc_clinic_no_search);
        phonenumber=getView().findViewById(R.id.phone_doc_view_view);
        see=getView().findViewById(R.id.view_doctor_button);
        final View v1=view;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorUserModel userModel=databaseHelper.getResult(clinicnumber.getText().toString());
                if(userModel!=null) {
                    firstname.setText(userModel.getFirstname());
                    lastname.setText(userModel.getLastname());
                    speciality.setText(userModel.getSpeciality());
                    phonenumber.setText(userModel.getTelephone());
                }
                else
                {
                    Toast.makeText(view.getContext(), "Invalid no", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewDoctor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDoctor.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDoctor newInstance(String param1, String param2) {
        ViewDoctor fragment = new ViewDoctor();
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
        return inflater.inflate(R.layout.fragment_view_doctor, container, false);
    }
}
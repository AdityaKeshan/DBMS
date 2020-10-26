package com.work.dbms_project.staff;

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
import com.work.dbms_project.databasehelpers.StaffDatabaseHelper;
import com.work.dbms_project.usermodels.StaffUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewStaff#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewStaff extends Fragment {
    TextView firstname,lastname,salary,ward_no,phonenumber,address,date,degree,insti;
    EditText staff_id;
    Button see;
    StaffDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new StaffDatabaseHelper(view.getContext());
        staff_id=getView().findViewById(R.id.staff_id_view);
        firstname=getView().findViewById(R.id.first_name_staff_view);
        lastname=getView().findViewById(R.id.last_name_staff_view);
        salary=getView().findViewById(R.id.salary_staff_view);
        ward_no=getView().findViewById(R.id.ward_staff_view);
        phonenumber=getView().findViewById(R.id.phone_staff_view);
        address=getView().findViewById(R.id.address_staff_view);
        date=getView().findViewById(R.id.dob_staff_view);
        degree=getView().findViewById(R.id.degree_staff_view);
        insti=getView().findViewById(R.id.insti_staff_view);

        see=getView().findViewById(R.id.insert_staff_viewtor_button);
        final View v1=view;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffUserModel userModel=databaseHelper.getResult(staff_id.getText().toString());
                if(userModel!=null)
                {
                    firstname.setText(userModel.getFirstname());
                    lastname.setText(userModel.getLastname());
                    salary.setText(userModel.getSalary());
                    ward_no.setText(userModel.getWard_no());
                    phonenumber.setText(userModel.getTelephone());
                    address.setText(userModel.getAddress());
                    date.setText(userModel.getDob());
                    degree.setText(userModel.getDegree());
                    insti.setText(userModel.getInsti());
                }
                else
                {
                    Toast.makeText(v1.getContext(), "Invalid no.", Toast.LENGTH_SHORT).show();
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

    public ViewStaff() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewStaff.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewStaff newInstance(String param1, String param2) {
        ViewStaff fragment = new ViewStaff();
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
        return inflater.inflate(R.layout.fragment_view_staff, container, false);
    }
}
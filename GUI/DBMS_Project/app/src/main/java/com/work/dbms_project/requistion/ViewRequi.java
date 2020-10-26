package com.work.dbms_project.requistion;

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
import com.work.dbms_project.databasehelpers.RequistionDatabaseHelper;
import com.work.dbms_project.usermodels.RequistionUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRequi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRequi extends Fragment {
    EditText requi_no;
    TextView quantity,date,received_date,ward_no,staff_id,drug_id;
    Button see;
    RequistionDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new RequistionDatabaseHelper(view.getContext());
        requi_no=getView().findViewById(R.id.requi_clinic_no_search);
        quantity= getView().findViewById(R.id.quantity_requi_view);
        date=getView().findViewById(R.id.date_of_order_requi_view);
        received_date=getView().findViewById(R.id.recieved_requi_view);
        ward_no=getView().findViewById(R.id.ward_no_requi_view);
        staff_id=getView().findViewById(R.id.staff_id_requi_view);
        final View v1=view;
        drug_id=getView().findViewById(R.id.drug_id_requi_view);
        see=getView().findViewById(R.id.view_requi_button);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequistionUserModel userModel=databaseHelper.getResult(requi_no.getText().toString());
                if (userModel!=null) {
                    quantity.setText(userModel.getQuantity());
                    date.setText(userModel.getDate_of_order());
                    received_date.setText(userModel.getDate_received());
                    ward_no.setText(userModel.getWard_no());
                    staff_id.setText(userModel.getStaff_id());
                    drug_id.setText(userModel.getDrug_id());
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

    public ViewRequi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewRequi.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewRequi newInstance(String param1, String param2) {
        ViewRequi fragment = new ViewRequi();
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
        return inflater.inflate(R.layout.fragment_view_requi, container, false);
    }
}
package com.work.dbms_project.drug;

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
import com.work.dbms_project.databasehelpers.DrugDatabaseHelper;
import com.work.dbms_project.usermodels.DrugUserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrug#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrug extends Fragment {
    TextView drug_name,type,stock,price;
    EditText drug_ID;
    Button see;
    DrugDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drug_ID=getView().findViewById(R.id.drug_clinic_no_search);

        databaseHelper=new DrugDatabaseHelper(view.getContext());
        drug_name=getView().findViewById(R.id.drug_view_name);
        type=getView().findViewById(R.id.drug_view_type);
        stock=getView().findViewById(R.id.drug_view_stock);
        price=getView().findViewById(R.id.drug_view_price);
        see=getView().findViewById(R.id.drug_view_view_button);
        final View v1=view;
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrugUserModel userModel=databaseHelper.getResult(drug_ID.getText().toString());
                if(userModel!=null)
                {
                    drug_name.setText(userModel.getDrug_name());
                    type.setText(userModel.getType());
                    stock.setText(userModel.getStock());
                    price.setText(userModel.getPrice());
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

    public ViewDrug() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDrug.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrug newInstance(String param1, String param2) {
        ViewDrug fragment = new ViewDrug();
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
        return inflater.inflate(R.layout.fragment_view_drug, container, false);
    }
}
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
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.DrugDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertDrug#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertDrug extends Fragment {
    EditText drug_id,drug_name,type,stock,price;
    Button insert;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InsertDrug() {
        // Required empty public constructor
    }
    DrugDatabaseHelper databaseHelper;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new DrugDatabaseHelper(view.getContext());
        drug_id=getView().findViewById(R.id.drug_id);
        drug_name=getView().findViewById(R.id.drug_name);
        type=getView().findViewById(R.id.drug_type);
        stock=getView().findViewById(R.id.drug_stock);
        price=getView().findViewById(R.id.drug_price);
        insert=getView().findViewById(R.id.drug_insert_button);
        final View v1=view;
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v1.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                databaseHelper.addUser(drug_id.getText().toString(),drug_name.getText().toString(),type.getText().toString(),stock.getText().toString(),price.getText().toString());
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertDrug.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertDrug newInstance(String param1, String param2) {
        InsertDrug fragment = new InsertDrug();
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
        return inflater.inflate(R.layout.fragment_insert_drug, container, false);
    }
}
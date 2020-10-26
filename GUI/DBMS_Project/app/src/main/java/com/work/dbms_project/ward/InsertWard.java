package com.work.dbms_project.ward;

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
import com.work.dbms_project.databasehelpers.WardDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertWard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertWard extends Fragment {
    EditText ward_no;
    TextView ward_name,block,beds;
    WardDatabaseHelper databaseHelper;
    Button insert;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View v1=view;
        databaseHelper=new WardDatabaseHelper(view.getContext());
        ward_no=getView().findViewById(R.id.number_ward);
        ward_name=getView().findViewById(R.id.name_ward);
        block=getView().findViewById(R.id.block_ward);
        beds=getView().findViewById(R.id.nob_ward);
        insert=getView().findViewById(R.id.insert_ward_button);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v1.getContext(), "Inserted", Toast.LENGTH_SHORT).show();
                databaseHelper.addUser(ward_no.getText().toString(),ward_name.getText().toString(),block.getText().toString(),beds.getText().toString());
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

    public InsertWard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertWard.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertWard newInstance(String param1, String param2) {
        InsertWard fragment = new InsertWard();
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
        return inflater.inflate(R.layout.fragment_insert_ward, container, false);
    }
}
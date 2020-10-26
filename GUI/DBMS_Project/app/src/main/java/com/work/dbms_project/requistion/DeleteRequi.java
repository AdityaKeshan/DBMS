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
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.RequistionDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteRequi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteRequi extends Fragment {
    RequistionDatabaseHelper databaseHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText requi_id;
    Button delete;
    public DeleteRequi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteRequi.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteRequi newInstance(String param1, String param2) {
        DeleteRequi fragment = new DeleteRequi();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new RequistionDatabaseHelper(view.getContext());
        requi_id=getView().findViewById(R.id.requi_no_delete);
        delete=getView().findViewById(R.id.requi_button_delete);
        final View v1=view;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(requi_id.getText().toString());
                Toast.makeText(v1.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_requi, container, false);
    }
}
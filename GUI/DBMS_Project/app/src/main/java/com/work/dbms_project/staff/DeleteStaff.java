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
import android.widget.Toast;

import com.work.dbms_project.R;
import com.work.dbms_project.databasehelpers.StaffDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteStaff#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteStaff extends Fragment {
    StaffDatabaseHelper databaseHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText staff_id;
    Button delete;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteStaff() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteStaff.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteStaff newInstance(String param1, String param2) {
        DeleteStaff fragment = new DeleteStaff();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper=new StaffDatabaseHelper(view.getContext());
        staff_id=getView().findViewById(R.id.staff_no_delete);
        delete=getView().findViewById(R.id.staff_button_delete);
        final View v1=view;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(staff_id.getText().toString());
                Toast.makeText(v1.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
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
        return inflater.inflate(R.layout.fragment_delete_staff, container, false);
    }
}
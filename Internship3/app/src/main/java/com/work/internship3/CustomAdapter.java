package com.work.internship3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomItem> {
    public CustomAdapter(@NonNull Context context, ArrayList<CustomItem> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout,parent,false);
        }
        CustomItem item=getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.si);
        TextView spinnerTV=convertView.findViewById(R.id.st);
        if(item!=null)
        {
            spinnerIV.setImageResource(item.getSource());
            spinnerTV.setText(item.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout,parent,false);
        }
        CustomItem item=getItem(position);
        ImageView spinnerIV=convertView.findViewById(R.id.ddi);
        TextView spinnerTV=convertView.findViewById(R.id.ddt);
        if(item!=null)
        {
            spinnerIV.setImageResource(item.getSource());
            spinnerTV.setText(item.getName());
        }
        return convertView;
    }
}

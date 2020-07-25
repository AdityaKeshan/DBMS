package com.work.fc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FacAdapter extends RecyclerView.Adapter<FacAdapter.ViewHolder> {
    @NonNull
    public ArrayList<Fac> numbers;
    private View.OnClickListener onItemClickListener;
    Context con;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }
    public FacAdapter(Context context,ArrayList<Fac> s)
    {
        con=context;
        numbers=s;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView subjects;
        RatingBar review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            subjects=itemView.findViewById(R.id.subjects);
            review=itemView.findViewById(R.id.rating);
//            itemView.setTag(this);
//            itemView.setOnClickListener(onItemClickListener);

        }
    }

    @Override
    public FacAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FacAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(numbers.get(position));
        holder.name.setText(numbers.get(position).getFaculty_name());
        holder.review.setRating(Float.parseFloat(numbers.get(position).getRatings()));
        holder.subjects.setText(numbers.get(position).getSubjects());
        holder.review.setIsIndicator(true);

    }

    @Override
    public int getItemCount() {

        if(numbers!=null){return numbers.size();}
        else{
            return 0;
        }
    }
}


package com.fitareq.noteme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class mAdapter extends RecyclerView.Adapter<mAdapter.mHolder> {


    private List<DataClass> dataClasses;
    private View view;
    private AdapterOnClickListener adapterOnClickListener;



    public mAdapter(List<DataClass> dataClasses, AdapterOnClickListener adapterOnClickListener) {

        this.dataClasses = dataClasses;
        this.adapterOnClickListener =adapterOnClickListener;
    }



    @NonNull
    @Override
    public mHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent, false );
        return new mHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull mHolder holder, int position) {

        DataClass current = dataClasses.get(position);
        holder.task_name.setText(current.getTaskName());
        holder.created_date.setText(current.getCreatedDate());
        holder.deadline.setText(current.getDeadline());

        holder.editBtn.setOnClickListener(v -> {
            adapterOnClickListener.editClickListener(current.getId());
        });
        holder.deleteBtn.setOnClickListener(v -> {
            adapterOnClickListener.deleteClickListener(current);
        });
        view.setOnClickListener(v -> {
            adapterOnClickListener.itemClickListener(current.getId());
        });

    }



    @Override
    public int getItemCount() {

        return dataClasses.size();
    }



    public class mHolder extends RecyclerView.ViewHolder{

        TextView task_name, created_date, deadline;
        ImageView editBtn, deleteBtn;

        public mHolder(@NonNull View itemView) {

            super(itemView);
            view=itemView;
            task_name = itemView.findViewById(R.id.task_name);
            created_date = itemView.findViewById(R.id.created_date);
            deadline = itemView.findViewById(R.id.deadline);
            editBtn = itemView.findViewById(R.id.edit_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

        }




    }

    public interface AdapterOnClickListener{
        void itemClickListener(Long id);
        void editClickListener(Long id);
        void deleteClickListener(DataClass dataClass);
    }


}

package com.example.cardiosync;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {

    //Adapter Components
    private Context mContext;
    private ArrayList<ModelClass> mclass;
    private  ModelClass modelClass;
    private ClickListener clickListener;

    //Methods for Recycleview
    @NonNull
    @Override
    public TaskAdapter.TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Interface to perform click operations
    public interface ClickListener {
        void customOnClick(int position, View v);
        void customOnLongClick(int position, View v);
        void onDeleteClick(int position);
        void onEditClick(int position);
        void DetailClick(int position);
    }

    //Setter click listener
    public void setClickListener(ClickListener clickL)
    {
        this.clickListener = clickL;
    }

    //For holding each views
    public class TaskViewholder extends RecyclerView.ViewHolder {
        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

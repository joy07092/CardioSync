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

    //Methods for Recycleview Taskadapter
    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext())  ;
        View view= inflater.inflate(R.layout.singlerow,parent,false);
        return new TaskViewholder(view);  
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mclass.size();
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
    //class of this used for each view
    public class TaskViewholder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        //elements of singlerow.xml is fetched
        TextView tx1,tx2,tx3,tx4;
        Button editButton,deleteButton;
        CardView cardView;

        //constructor
        public TaskViewholder(@NonNull View itemView) {
            super(itemView);

            //finding the elements
            tx1= itemView.findViewById(R.id.tvDate);
            tx2= itemView.findViewById(R.id.tvDiastolic);
            tx3=itemView.findViewById(R.id.tvSystolic);
            tx4=itemView.findViewById(R.id.tvHeartRate);
            editButton=itemView.findViewById(R.id.Edit_buttonId);
            deleteButton = itemView.findViewById(R.id.DeleteBUttonId);
            cardView= itemView.findViewById(R.id.CardView);

            //item onclick setter
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        // implementing View.OnClickListener
        @Override
        public void onClick(View view) {
            clickListener.customOnClick(getAdapterPosition(), view);

        }
        // implementing  View.OnLongClickListener
        @Override
        public boolean onLongClick(View view) {
            clickListener.customOnLongClick(getAdapterPosition(), view);
            return true;
        }
    }

}

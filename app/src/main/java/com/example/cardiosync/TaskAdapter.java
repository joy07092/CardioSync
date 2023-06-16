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

/**
 * This is class is the adapter for recycleview
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewholder> {

    /**
     * These are the components of the adapter
     */
    private Context mContext;
    private ArrayList<ModelClass> mclass;
    private  ModelClass modelClass;
    private ClickListener clickListener;

    /**
     * This is constructor of the adapter
     * @param context, Arraylist of record
     */
    public  TaskAdapter(Context context, ArrayList<ModelClass>mclass) {
        this.mclass= mclass;
        this.mContext = context;
    }

    /**
     * These are the must implemented methods of the adapter
     */
    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext())  ;
        View view= inflater.inflate(R.layout.singlerow,parent,false);
        return new TaskViewholder(view);  
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewholder holder, @SuppressLint("RecyclerView") int position) {
        /**
         * Binding all the data
         */
        holder.tx1.setText(mclass.get(position).getDate());
        holder.tx2.setText(mclass.get(position).getSystolic());
        holder.tx3.setText(mclass.get(position).getDiastolic());
        holder.tx4.setText(mclass.get(position).getBloodPressure());
        /**
         * Defining action for delete button
         */
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                clickListener.onDeleteClick(position);
            }
        });
        /**
         * Defining action for edit button
         */
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEditClick(position);

            }
        });
        /**
         * Defining action for detail button
         */
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.DetailClick(position);

            }
        });
        modelClass = mclass.get(position);
        //Setting color according to bad or good records
        if (Integer.parseInt(modelClass.getDiastolic())>60 && (Integer.parseInt(modelClass.getDiastolic())<90))holder.tx3.setTextColor(Color.parseColor("#05ED98"));
        else holder.tx3.setTextColor(Color.parseColor("#d80000"));

        if (Integer.parseInt(modelClass.getSystolic())>90 &&( Integer.parseInt(modelClass.getSystolic())<140)) holder.tx2.setTextColor(Color.parseColor("#05ED98"));
        else holder.tx2.setTextColor(Color.parseColor("#d80000"));

        if (Integer.parseInt(modelClass.getBloodPressure())>60 && Integer.parseInt(modelClass.getBloodPressure())<100) holder.tx4.setTextColor(Color.parseColor("#05ED98"));
        else holder.tx4.setTextColor(Color.parseColor("#d80000"));
    }

    @Override
    public int getItemCount() {
        return mclass.size();
    }

    /**
     * Interface for click option methods
     */
    public interface ClickListener {
        void customOnClick(int position, View v);
        void customOnLongClick(int position, View v);
        void onDeleteClick(int position);
        void onEditClick(int position);
        void DetailClick(int position);
    }

    /**
     * Setter method for click listener
     */
    public void setClickListener(ClickListener clickL)
    {
        this.clickListener = clickL;
    }

    /**
     * Class used for holding each view
     */
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

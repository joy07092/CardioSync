package com.example.cardiosync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    public static TaskAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ModelClass modelclass;
    Button button;
    String s1,s2,s3,s4;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.AddBUttonId);

        retrieveData();  //method to retrieve data using sharedpreference

        /**
         * This is OnClickListener for add button
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataEntry.class);
                startActivity(intent);
            }
        });

        recyclerView1=findViewById(R.id.recyclarView);
        adapter =new TaskAdapter(MainActivity.this, RecordList.mcl);
        recyclerView1.setAdapter(adapter);   //getting the recyclerview ready

        /**
         * Click listener for each record in the list
         */
        adapter.setClickListener(new TaskAdapter.ClickListener(){
            @Override
            public void customOnClick(int position, View v) {

            }

            @Override
            public void customOnLongClick(int position, View v) {

            }

            /**
             * This is OnClickListener for delete button
             */
            @Override
            public void onDeleteClick(int position) {  //clicking the delete button
                new RecordList().deleteRecord(position);
                adapter.notifyItemRemoved(position);
                saveData();
                Toast.makeText(MainActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
            }

            /**
             * This is OnClickListener for edit button
             */
            public void onEditClick(int position) {  //clicking the update button
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("index",position);
                startActivity(intent);
            }

            /**
             * This is OnClickListener for detail button
             */
            @Override
            public void DetailClick(int position){  //just clicking the item for details
                Intent intent1= new Intent(MainActivity.this,Details_Activity.class);
                intent1.putExtra("index",position);
                startActivity(intent1);
            }
        });
    }

    /**
     * This is method is for converting information in JSON format and storing them
     */
    private void saveData() {
        sharedPreferences = getSharedPreferences("project",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("project",jsonString);
        editor.apply();
    }

    /**
     * This is method is for retrieving information from JSON format and converting them to original form
     */
    private void retrieveData() {
        sharedPreferences = getSharedPreferences("project",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("project",null);
        Type type = new TypeToken<ArrayList<ModelClass>>(){}.getType();
        RecordList.mcl = gson.fromJson(jsonString,type);
        if(RecordList.mcl ==null)
        {
            RecordList.mcl = new ArrayList<>();
        }
    }
}
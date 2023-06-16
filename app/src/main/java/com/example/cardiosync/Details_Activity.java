package com.example.cardiosync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Details_Activity extends AppCompatActivity {

    ArrayList<ModelClass> mcl = RecordList.mcl;  //for getting the full data collection
    ModelClass modelClass;   //for storing the particular data
    TextView dateT,timeT,systolicT,diastolicT,heartRateT,commentT;  //to show data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();  //getting the intent
        int index = intent.getIntExtra("index",0);  //getting the particular index for the particular data
        dateT= findViewById(R.id.DdateValue);
        timeT = findViewById(R.id.DtimeValue);
        systolicT = findViewById(R.id.DsystolicValue);
        diastolicT = findViewById(R.id.DdiastolicValue);
        heartRateT = findViewById(R.id.DheartRateValue);
        commentT = findViewById(R.id.DcommentValue);  //finding the views
        modelClass = mcl.get(index);  //getting just the data needed
        dateT.setText(modelClass.getDate() + "(yyyy-mm-dd)"); //setting the data in the views
        timeT.setText(modelClass.getTime() + "(24 hour format)");
        systolicT.setText(modelClass.getSystolic());
        diastolicT.setText(modelClass.getDiastolic());
        heartRateT.setText(modelClass.getBloodPressure());
        commentT.setText(modelClass.getComment());
    }
}
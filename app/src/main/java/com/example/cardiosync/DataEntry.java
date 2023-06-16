package com.example.cardiosync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.regex.Pattern;

public class DataEntry extends AppCompatActivity {
    Button saveButton;
    String date,time,systolic,diastolic,bloodPressure,comment ;
    EditText edtx1,edtx2,edtx3,edtx4,edtx5,edtx6;

    SharedPreferences sharedPreferences;  //for data storing
    SharedPreferences.Editor editor;  //for data storing
    ModelClass modelclass;  //for data storing
    Gson gson;  //for data passing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //finding all view
        saveButton=findViewById(R.id.addButton);
        edtx1=findViewById(R.id.dateValue);
        edtx2=findViewById(R.id.systolicValue);
        edtx3= findViewById(R.id.diastolicValue);
        edtx4 =findViewById(R.id.heartRateValue);
        edtx5=findViewById(R.id.timeValue);
        edtx6=findViewById(R.id.commentValue);

        /**
         * This is OnClickListener for Save button
         */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFormat();
            }  //method is used to save data
        });
    }

    /**
     * This is method for checking input format of the user record and adding it to list
     */
    private void inputFormat() {
        //getting all inputs
        date = edtx1.getText().toString();
        time = edtx5.getText().toString();
        systolic = edtx2.getText().toString();
        diastolic = edtx3.getText().toString();
        bloodPressure = edtx4.getText().toString();
        comment = edtx6.getText().toString();

        if(TextUtils.isEmpty(date)){//if date is empty
            Toast.makeText(DataEntry.this,"Please enter date", Toast.LENGTH_SHORT).show();
            edtx1.setError("Date is required");
            edtx1.requestFocus();
        }else if(TextUtils.isEmpty(time)){//if time is empty
            Toast.makeText(DataEntry.this,"Please enter time", Toast.LENGTH_SHORT).show();
            edtx5.setError("Time is required");
            edtx5.requestFocus();
        }else if(!dateMatcher(date)){//mismatch date pattern
            Toast.makeText(DataEntry.this,"Please enter valid date", Toast.LENGTH_SHORT).show();
            edtx1.setError("Valid date is required");
            edtx1.requestFocus();
        }else if(!timeMatcher(time)){//mismatch time pattern
            Toast.makeText(DataEntry.this,"Please enter valid time", Toast.LENGTH_SHORT).show();
            edtx5.setError("Valid time is required");
            edtx5.requestFocus();
        }else if(TextUtils.isEmpty(systolic)){//if systolic is empty
            Toast.makeText(DataEntry.this,"Please enter systolic", Toast.LENGTH_SHORT).show();
            edtx2.setError("Systolic is required");
            edtx2.requestFocus();
        }else if(TextUtils.isEmpty(diastolic)){//if diastolic is empty
            Toast.makeText(DataEntry.this,"Please enter diastolic", Toast.LENGTH_SHORT).show();
            edtx3.setError("Diastolic is required");
            edtx3.requestFocus();
        }else if(TextUtils.isEmpty(bloodPressure)){//if heart-rate is empty
            Toast.makeText(DataEntry.this,"Please enter heart-rate", Toast.LENGTH_SHORT).show();
            edtx4.setError("Heart-rate is required");
            edtx4.requestFocus();
        }else if(!(Integer.parseInt(edtx2.getText().toString()) <= 200)){  //valid systolic
            Toast.makeText(DataEntry.this,"Please enter valid systolic", Toast.LENGTH_SHORT).show();
            edtx2.setError("Valid systolic is required");
            edtx2.requestFocus();
        }else if(!(Integer.parseInt(edtx3.getText().toString()) <= 150)){  //valid diastolic
            Toast.makeText(DataEntry.this,"Please enter valid diastolic", Toast.LENGTH_SHORT).show();
            edtx3.setError("Valid diastolic is required");
            edtx3.requestFocus();
        }else if(!(Integer.parseInt(edtx4.getText().toString()) <= 200)){  //valid heart-rate
            Toast.makeText(DataEntry.this,"Please enter valid heart-rate", Toast.LENGTH_SHORT).show();
            edtx4.setError("Valid heart-rate is required");
            edtx4.requestFocus();
        }else{  //when all inputs are correct

            modelclass = new ModelClass(date, time, systolic, diastolic, bloodPressure, comment); //object assigning values with constructor
            new RecordList().addRecord(modelclass);  //adding new one in RecordList class
            PreferenceManager.getDefaultSharedPreferences(DataEntry.this).edit().clear().commit();  //clearing the old data and saving the present
            saveData();  //saving new record
            MainActivity.adapter.notifyDataSetChanged();  //new data for rebound
            Toast.makeText(DataEntry.this, "Data Insertion Successful", Toast.LENGTH_LONG).show();
            finish();
        }
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
     * This is method is for matching the date format
     */
    public boolean dateMatcher(String date){

        Pattern DATE_PATTERN = Pattern.compile(
                "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

        return DATE_PATTERN.matcher(date).matches();
    }
    /**
     * This is method is for matching the time format
     */
    public boolean timeMatcher(String time){

        Pattern TIME_PATTERN = Pattern.compile(
                "([01]?[0-9]|2[0-3]):[0-5][0-9]");

        return TIME_PATTERN.matcher(time).matches();
    }

}
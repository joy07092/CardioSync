package com.example.cardiosync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class UpdateActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;  //just like data entry activity and show detail activity
    SharedPreferences.Editor editor;
    Gson gson;
    ModelClass modelClass;
    EditText dateET, timeET, systolicET, diastolicET, heartRateET, commentET;
    String date, time, systolic, diastolic, bloodPressure, comment;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        dateET = findViewById(R.id.UdateValue);
        timeET = findViewById(R.id.UtimeValue);
        systolicET = findViewById(R.id.UsystolicValue);
        diastolicET = findViewById(R.id.UdiastolicValue);
        heartRateET = findViewById(R.id.UheartRateValue);
        commentET = findViewById(R.id.UcommentValue);
        Button updateButton = findViewById(R.id.UpdateButtonId);
        modelClass = RecordList.mcl.get(index);

        dateET.setText(modelClass.getDate());
        timeET.setText(modelClass.getTime());
        systolicET.setText(modelClass.getSystolic());
        diastolicET.setText(modelClass.getDiastolic());
        heartRateET.setText(modelClass.getBloodPressure());
        commentET.setText(modelClass.getComment());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFormat();
            }
        });

    }

    private void inputFormat() {
        date = dateET.getText().toString();
        time = timeET.getText().toString();
        systolic = systolicET.getText().toString();
        diastolic = diastolicET.getText().toString();
        bloodPressure = heartRateET.getText().toString();
        comment = commentET.getText().toString();

        if(TextUtils.isEmpty(date)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter date", Toast.LENGTH_SHORT).show();
            dateET.setError("Date is required");
            dateET.requestFocus();
        }else if(TextUtils.isEmpty(time)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter time", Toast.LENGTH_SHORT).show();
            timeET.setError("Time is required");
            timeET.requestFocus();
        }else if(!dateMatcher(date)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter valid date", Toast.LENGTH_SHORT).show();
            dateET.setError("Valid date is required");
            dateET.requestFocus();
        }else if(!timeMatcher(time)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter valid time", Toast.LENGTH_SHORT).show();
            timeET.setError("Valid time is required");
            timeET.requestFocus();
        }else if(TextUtils.isEmpty(systolic)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter systolic", Toast.LENGTH_SHORT).show();
            systolicET.setError("Systolic is required");
            systolicET.requestFocus();
        }else if(TextUtils.isEmpty(diastolic)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter diastolic", Toast.LENGTH_SHORT).show();
            diastolicET.setError("Diastolic is required");
            diastolicET.requestFocus();
        }else if(TextUtils.isEmpty(bloodPressure)){//see the toast text
            Toast.makeText(UpdateActivity.this,"Please enter heart-rate", Toast.LENGTH_SHORT).show();
            heartRateET.setError("Heart-rate is required");
            heartRateET.requestFocus();
        }else if(!(Integer.parseInt(systolicET.getText().toString()) <= 200)){  //valid systolic
            Toast.makeText(UpdateActivity.this,"Please enter valid systolic", Toast.LENGTH_SHORT).show();
            systolicET.setError("Valid systolic is required");
            systolicET.requestFocus();
        }else if(!(Integer.parseInt(diastolicET.getText().toString()) <= 150)){  //valid diastolic
            Toast.makeText(UpdateActivity.this,"Please enter valid diatolic", Toast.LENGTH_SHORT).show();
            diastolicET.setError("Valid diastolic is required");
            diastolicET.requestFocus();
        }else if(!(Integer.parseInt(heartRateET.getText().toString()) <= 200)){  //valid heart-rate
            Toast.makeText(UpdateActivity.this,"Please enter valid heart-rate", Toast.LENGTH_SHORT).show();
            heartRateET.setError("Valid heart-rate is required");
            heartRateET.requestFocus();
        }else{
            modelClass = new ModelClass(date, time, systolic, diastolic, bloodPressure, comment);
            RecordList.mcl.set(index, modelClass);
            PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this).edit().clear().commit();
            saveData();
            RecordList.mcl.set(index, modelClass);
            MainActivity.adapter.notifyDataSetChanged();
            Toast.makeText(UpdateActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void saveData() {
        sharedPreferences = getSharedPreferences("project",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("project", jsonString);
        editor.apply();
    }

    public boolean dateMatcher(String date){  //to match date format

        Pattern DATE_PATTERN = Pattern.compile(
                "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

        return DATE_PATTERN.matcher(date).matches();
    }

    public boolean timeMatcher(String time){  // to match time format

        Pattern TIME_PATTERN = Pattern.compile(
                "([01]?[0-9]|2[0-3]):[0-5][0-9]");

        return TIME_PATTERN.matcher(time).matches();
    }

}
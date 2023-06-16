package com.example.cardiosync;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Utils {
    public  void saveData(Context context)  //used in the ui test
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("project",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("project",jsonString);
        editor.apply();
    }
}

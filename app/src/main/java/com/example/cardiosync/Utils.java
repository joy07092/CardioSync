package com.example.cardiosync;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * This is class used for the purpose of UI Testing
 */
public class Utils {

    /**
     * This is method is for converting information in JSON format and storing them
     * @param context
     *               The context from which the data has been input
     */
    public  void saveData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("project",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(RecordList.mcl);
        editor.putString("project",jsonString);
        editor.apply();
    }
}

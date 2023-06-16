package com.example.cardiosync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_screen extends AppCompatActivity {

    ProgressBar pb;
    Timer timer;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pb = findViewById(R.id.pb);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {  //using timer class for splashscreen...use of threads
                if(i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    pb.setProgress(i);    //progressing of the progressbar with time
                    i++;
                }else{
                    timer.cancel();
                    Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 0, 20);
    }
}
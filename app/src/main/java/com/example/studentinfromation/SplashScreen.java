package com.example.studentinfromation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    progress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nextactivity();
            }
        });
        thread.start();


    }

    private void nextactivity() {
        Intent intent = new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void progress() throws InterruptedException {
        for(int i = 1;i<=100;i++){

                Thread.sleep(50);

        }
    }
}

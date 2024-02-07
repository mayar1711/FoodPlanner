package com.example.foodplanner.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodplanner.R;
import com.example.foodplanner.ui.authentication.MainActivity;
import com.example.foodplanner.ui.onbording.OnBording;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSharedPreferences = getSharedPreferences("mySp", MODE_PRIVATE);
                boolean isFirstTime = mSharedPreferences.getBoolean("firstTime", true);
                boolean isAuthenticated = false;
                if (isFirstTime) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();
                    Intent intent = new Intent(SplashScreen.this, OnBording.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } /*else if (AuthenticationFireBaseRepo.getInstance().isAuthenticated()) {
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }*/ else {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }, SPLASH_TIME_OUT);
    }
    }

package com.example.ecommeranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ecommeranceapp.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
ActivitySplashScreenBinding binding;
private static final int SPLASH_SCREEN_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        },5000);
    }
}

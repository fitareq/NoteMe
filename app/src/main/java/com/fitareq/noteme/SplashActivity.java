package com.fitareq.noteme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fitareq.noteme.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }




}
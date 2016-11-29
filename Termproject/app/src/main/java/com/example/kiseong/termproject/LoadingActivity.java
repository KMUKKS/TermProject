package com.example.kiseong.termproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import java.util.logging.LogRecord;

/**
 * Created by kiseong on 2016-11-28.
 */
public class LoadingActivity extends Activity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        loading();
    }

    private void loading()
    {
        Handler kHandler = new Handler();
        kHandler.postDelayed(new Runnable()
        {
            @Override
            public void run() {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            },3000);
        }

    }
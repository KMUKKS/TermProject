package com.example.kiseong.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button task;
    private Button log;
    private Button note;
    private Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = (Button)findViewById(R.id.button5);
        log = (Button)findViewById(R.id.button3);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent task_intent = new Intent(getApplicationContext(), TaskMain.class);
                startActivity(task_intent);

            }
        });

        log.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent log_intent = new Intent(getApplicationContext(), LogData.class);
                                       startActivity(log_intent);
                                   }
                               });

    }

}





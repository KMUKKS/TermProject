package com.example.kiseong.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kiseong on 2016-11-30.
 */
public class TaskMain extends AppCompatActivity {

    private Button button_start;
    private Button button_map;
    private TextView lat;
    private TextView lon;

    private GpsInfo gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_main);

        button_start = (Button)findViewById(R.id.button1);
        button_map = (Button)findViewById(R.id.button2) ;

        lat = (TextView)findViewById(R.id.Lat);
        lon = (TextView)findViewById(R.id.Lon);

        button_start.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View arg0){
                                                gps = new GpsInfo(TaskMain.this);

                                                if(gps.isGetLocation()) {

                                                    double Latitude = gps.getLatitude();
                                                    double Longitude = gps.getLongitude();

                                                    lat.setText((String.valueOf(Latitude)));
                                                    lon.setText((String.valueOf(Longitude)));

                                                    Toast.makeText(getApplicationContext(), "현재 위치는 \n위도:" + Latitude + "\n경도:" + Longitude, Toast.LENGTH_LONG).show();
                                                } else {
                                                    gps.showSettingsAlert();
                                                }
                                            }
                                        }
        );
    }

    public void onClick(View v){
        Intent mapintent = new Intent(this, MapView.class);
        startActivity(mapintent);
    }
}




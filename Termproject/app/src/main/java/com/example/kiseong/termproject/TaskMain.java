package com.example.kiseong.termproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
    private EditText st;
    private Button button_dbp;
    private Button save;

    private GpsInfo gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_main);
        checkDangerousPermissions();

        final DBmanager dbManager = new DBmanager(getApplicationContext(), "LATLNG.db", null, 1);


        final DataList dba = new DataList();

        button_start = (Button)findViewById(R.id.button1);
        button_map = (Button)findViewById(R.id.button2) ;
        button_dbp = (Button)findViewById(R.id.dbp);

        lat = (TextView)findViewById(R.id.Lat);
        lon = (TextView)findViewById(R.id.Lon);

        final RadioButton rb_study = (RadioButton)findViewById((R.id.study));
        final RadioButton rb_exercise = (RadioButton)findViewById(R.id.exercise);
        final RadioButton rb_meal = (RadioButton)findViewById(R.id.meal);
        final RadioButton rb_meeting = (RadioButton)findViewById(R.id.meeting);
        final RadioButton rb_etc = (RadioButton)findViewById(R.id.etc);

        st = (EditText)findViewById(R.id.doing);

        save = (Button)findViewById(R.id.inserting);



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

        button_dbp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String Latitude = lat.getText().toString();
                String Longitude = lon.getText().toString();
                dbManager.insert("insert into LATLNG_LIST values(null, '" + Latitude + "', " + Longitude + ");");



            }
                                      });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps.getLocation();

                if(rb_study.isChecked()){dba.ar_tp.add(5);}
                if(rb_exercise.isChecked()){dba.ar_tp.add(4);}
                if(rb_meal.isChecked()){dba.ar_tp.add(3);}
                if(rb_meeting.isChecked()){dba.ar_tp.add(2);}
                if(rb_etc.isChecked()){dba.ar_tp.add(1);}

                dba.ar_lat.add(gps.getLatitude());
                dba.ar_lon.add(gps.getLongitude());
                dba.ar_st.add(st.getText().toString());

                st.setText("");
            }
        });
    }

    public void onClick(View v){
        Intent mapintent = new Intent(this, MapView.class);
        startActivity(mapintent);
    }

    private void checkDangerousPermissions(){

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


        for(int i=0 ; i< permissions.length; i++){
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                break;
            }
        }
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[0])){
                Toast.makeText(this,"권한 설명 필요함.", Toast.LENGTH_LONG).show();
            }else{
                ActivityCompat.requestPermissions(this,permissions,1);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 1){
            for(int i=0 ; i<permissions.length ; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, permissions[i] + "권한이 승인됨.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,permissions[i] + "권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}




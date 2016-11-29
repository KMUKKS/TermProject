package com.example.kiseong.termproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by kiseong on 2016-11-30.
 */
public class MapView extends FragmentActivity {
    GoogleMap gm;
    GpsInfo gps = new GpsInfo(MapView.this);

    double lat_arr[] = {37.4706390,37.5572580,37.5813100,37.6019780,37.6079530,37.6093240,};
    double lon_arr[] = {126.9685050,126.9949010,127.0020940,127.0246850,127.0032500,126.9982990,};

    LatLng location = new LatLng(37.6102460, 126.9969570);
    MarkerOptions marker;
    CameraPosition cp = new CameraPosition.Builder().target(location).zoom(11).build();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.map_view);

        gm = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        for(int i = 0 ; i < lat_arr.length ; i++){
            location = new LatLng(lat_arr[i],lon_arr[i]);
            marker = new MarkerOptions().position(location);
            gm.addMarker(marker);
        }
        gm.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
    }

    public void onClick(View v){
        finish();
    }
}
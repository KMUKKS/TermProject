package com.example.kiseong.termproject;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



/**
 * Created by kiseong on 2016-11-30.
 */
public class MapView extends FragmentActivity implements GoogleMap.OnMapClickListener{

    private GoogleMap gm;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.map_view);

        MapsInitializer.initialize(getApplicationContext());
        init();


    }


    public void onMapClick(LatLng point) {

        Point screenPt = gm.getProjection().toScreenLocation(point);

        LatLng latLng = gm.getProjection().fromScreenLocation(screenPt);

        Log.d("맵좌표", "좌표: 위도(" + String.valueOf(point.latitude) + "), 경도("
                + String.valueOf(point.longitude) + ")");
        Log.d("화면좌표", "화면좌표: X(" + String.valueOf(screenPt.x) + "), Y("
                + String.valueOf(screenPt.y) + ")");
    }

    private void init() {


        DataList dba = new DataList();

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapView.this);
        gm = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();



        GpsInfo gps = new GpsInfo(MapView.this);

        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


            LatLng latLng = new LatLng(latitude, longitude);


            gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));


            gm.animateCamera(CameraUpdateFactory.zoomTo(13));


            MarkerOptions opmark = new MarkerOptions();

            for(int i = 0; i < dba.ar_st.size(); i++){
                LatLng latlng = new LatLng(dba.ar_lat.get(i),dba.ar_lon.get(i));
                opmark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i));
            }

            gm.addMarker(opmark).showInfoWindow();
        }


    }



/*
    public void onClick(View v){
        finish();
    }
    */
}

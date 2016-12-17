package com.example.kiseong.termproject;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by kiseong on 2016-12-17.
 */

public class LogView extends FragmentActivity {

    private GoogleMap gm;

    protected void onCreate(Bundle saveInstanceState) {
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

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(LogView.this);
        gm = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();



        GpsInfo gps = new GpsInfo(LogView.this);

        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            LatLng latLng = new LatLng(latitude, longitude);


            gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));


            gm.animateCamera(CameraUpdateFactory.zoomTo(13));



            MarkerOptions mark = new MarkerOptions();

            for (int i = 0; i < dba.ar_st.size(); i++) {
                LatLng latlng = new LatLng(dba.ar_lat.get(i), dba.ar_lon.get(i));

                switch (dba.ar_tp.get(i)) {
                    case 1:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                        break;
                    case 2:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        break;
                    case 3:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        break;
                    case 4:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        break;
                    case 5:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    default:
                        mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i)).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }

                gm.addMarker(mark).showInfoWindow();
            }


        }
    }
}


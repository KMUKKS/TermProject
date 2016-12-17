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

        // BitmapDescriptorFactory 생성하기 위한 소스
        MapsInitializer.initialize(getApplicationContext());
        init();

    }

    /**
     * Map 클릭시 터치 이벤트
     */
    public void onMapClick(LatLng point) {

        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = gm.getProjection().toScreenLocation(point);

        // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
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

        // 맵의 이동
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        GpsInfo gps = new GpsInfo(LogView.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            gm.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map 을 zoom 합니다.
            gm.animateCamera(CameraUpdateFactory.zoomTo(13));


            // 마커 설정.
            MarkerOptions mark = new MarkerOptions();
            /*
            opmark.position(latLng);// 위도 • 경도
            opmark.title("Current Position");// 제목 미리보기
            opmark.snippet("Snippet");
            //optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_example));
            */
            for (int i = 0; i < dba.ar_st.size(); i++) {
                LatLng latlng = new LatLng(dba.ar_lat.get(i), dba.ar_lon.get(i));
               // mark = new MarkerOptions().position(latlng).title(dba.ar_st.get(i));

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


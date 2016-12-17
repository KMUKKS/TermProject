package com.example.kiseong.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by kiseong on 2016-12-01.
 */
public class LogData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_main);

        final DBmanager dbManager = new DBmanager(getApplicationContext(), "LATLNG.db", null, 1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.et_latitude);
        final EditText etPrice = (EditText) findViewById(R.id.et_longitude);


        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);




        // Insert
        Button btnInsert = (Button) findViewById(R.id.btn_insert);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String Latitude = etName.getText().toString();
                String Longitude = etPrice.getText().toString();
                dbManager.insert("insert into LATLNG_LIST values(null, '" + Latitude + "', " + Longitude + ");");

                tvResult.setText( dbManager.PrintData() );
            }
        });

        // Update
        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // update 테이블명 where 조건 set 값;
                String Latitude = etName.getText().toString();
                String Longitude = etPrice.getText().toString();
                dbManager.update("update LATLNG set Latitude = " + Latitude + " where Longitude = '" + Longitude + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });

        // Delete
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // delete from 테이블명 where 조건;
                String Latitude = etName.getText().toString();
                dbManager.delete("delete from LATLNG where Latitude = '" + Latitude + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });

        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText(dbManager.PrintData());
            }
        });



        Button statics_view = (Button)findViewById(R.id.map_st);
        statics_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statics_intent = new Intent(getApplicationContext(), LogView.class);
                startActivity(statics_intent);
            }
        });


    }

}


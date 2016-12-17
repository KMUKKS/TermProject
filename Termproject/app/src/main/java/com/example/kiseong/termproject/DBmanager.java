package com.example.kiseong.termproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kiseong on 2016-11-30.
 */
public class DBmanager extends SQLiteOpenHelper {
    public DBmanager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // 새로운 테이블을 생성한다.
    // create table 테이블명 (컬럼명 타입 옵션);
        db.execSQL("CREATE LATLNG FOOD_LIST( _id INTEGER PRIMARY KEY AUTOINCREMENT, Latitude REAL, Longitude REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
    }



    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from LATLNG_LIST", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : 위도는 = "
                    + cursor.getString(1)
                    + ", 경도는 = "
                    + cursor.getInt(2)
                    + "\n";
        }

        return str;
    }


}

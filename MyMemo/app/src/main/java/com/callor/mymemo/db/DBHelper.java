package com.callor.mymemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by callor on 2017-08-25.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context context = null;

    public DBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 최초에 어플이 실행될때 사용할 데이터와 테이블 생성하는 부분
        db.execSQL(DBContract.DBCreate.DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveMemo(String memo) {
        long now = System.currentTimeMillis(); // Ticker data 1970. 1. 1 시작된 밀리 세컨드
        Date curDate = new Date(now); // Ticker 값을 data 객체 변환

        // 문자열로 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getDate = dateFormat.format(curDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String getTime = timeFormat.format(curDate);

        SQLiteDatabase dbConn = this.getWritableDatabase();

        ContentValues sqlValues = new ContentValues();
        sqlValues.put(DBContract.DBColumn.MEMO_DATE, getDate);
        sqlValues.put(DBContract.DBColumn.MEMO_TIME, getTime);
        sqlValues.put(DBContract.DBColumn.MEMO_TEXT, memo);

        long newId = dbConn.insert(DBContract.TABLE_NAME, null, sqlValues);
        return newId;
    }

    public Cursor getListAll() {
        SQLiteDatabase dbConn = this.getReadableDatabase();

        String[] projection = {
                DBContract.DBColumn._ID,
                DBContract.DBColumn.MEMO_DATE,
                DBContract.DBColumn.MEMO_TIME,
                DBContract.DBColumn.MEMO_TEXT
        };

        // null 값 6개
        Cursor cursor = dbConn.query(
                DBContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}

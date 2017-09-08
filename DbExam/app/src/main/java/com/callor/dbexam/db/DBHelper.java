package com.callor.dbexam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by callor on 2017-09-08.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, DB_VERSION);
    }

    // Db 가 없을때 실행되는 메서드
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TABLE 생성
        db.execSQL(DBContract.TABLE_CREATE.TABLE_CREATE_SQL);
    }

    // DB_VERSION 값이 이전 값과 다를때 실행되는 메서드
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public long insert(MemberVO vo){

        // DB에 저장(insert)하기 위해 DB를 쓰기 모드로 연다.
        SQLiteDatabase dbConn = this.getWritableDatabase();
        /*
        String insertSQL = " INSERT INTO " + DBContract.TABLE_NAME + " ( "
                        + DBContract.DBColumn.USER_ID + " , "
                        + DBContract.DBColumn.USER_PASSWORD + " , "
                        + DBContract.DBColumn.USER_EMAIL + " , "
                        + DBContract.DBColumn.USER_PHONE + " ) "
                        + " VALUES ( "
                        + vo.getUserId() + " , "
                        + vo.getUserId() + " , "
                        + vo.getUserId() + " , "
                        + vo.getUserId() + " ) " ;

        dbConn.execSQL(insertSQL);
        */

        ContentValues sqlMake = new ContentValues();
        sqlMake.put(DBContract.DBColumn.USER_ID,vo.getUserId());
        sqlMake.put(DBContract.DBColumn.USER_PASSWORD,vo.getUserPassword());
        sqlMake.put(DBContract.DBColumn.USER_EMAIL,vo.getUserEmail());
        sqlMake.put(DBContract.DBColumn.USER_PHONE,vo.getUserPhone());
        long newId = dbConn.insert(DBContract.TABLE_NAME,null,sqlMake);
        return newId;

    }
}

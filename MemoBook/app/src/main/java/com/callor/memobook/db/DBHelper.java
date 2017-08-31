package com.callor.memobook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by callor on 2017-08-28.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context context = null;

    public DBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.DBCreate.DB_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public long saveMemo(String strMemo) {
        long now = System.currentTimeMillis();
        Date curDate = new Date(now);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getDate = dateFormat.format(curDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String getTime = timeFormat.format(curDate);

        SQLiteDatabase dbConn = this.getWritableDatabase();

        ContentValues sqlValues = new ContentValues();
        sqlValues.put(DBContract.DBColumn.MEMO_DATE,getDate);
        sqlValues.put(DBContract.DBColumn.MEMO_TIME,getTime);
        sqlValues.put(DBContract.DBColumn.MEMO_TEXT,strMemo);

        long newId = dbConn.insert(DBContract.TABLE_NAME,null,sqlValues);
        return newId ;
    }

    public Cursor getListAll() {

        SQLiteDatabase dbConn = this.getReadableDatabase();
        String[] projection = {
                DBContract.DBColumn._ID,
                DBContract.DBColumn.MEMO_DATE,
                DBContract.DBColumn.MEMO_TIME,
                DBContract.DBColumn.MEMO_TEXT
        };

        Cursor  cursor = dbConn.query(
                DBContract.TABLE_NAME,
                projection,null,null,null,null,
                DBContract.DBColumn.MEMO_DATE + " DESC, "
                        + DBContract.DBColumn.MEMO_TIME + " DESC "
                ,null
        );

        return cursor;
    }

    public void update(long id, String strMemo) {
        SQLiteDatabase dbConn = this.getWritableDatabase();
        ContentValues sqlValue = new ContentValues();
        sqlValue.put(DBContract.DBColumn.MEMO_TEXT,strMemo);

        Log.d("Update:",strMemo);
        dbConn.update(
                DBContract.TABLE_NAME,
                sqlValue,
                DBContract.DBColumn._ID + "= ?" ,new String[]{String.valueOf(id)}
        );

//        dbConn.execSQL("UPDATE " + DBContract.TABLE_NAME
//        + " SET " + DBContract.DBColumn.MEMO_TEXT + " = " +strMemo
//        + " WHERE " + DBContract.DBColumn._ID + " = " + String.valueOf(id));

    }

    public void dbDelete(long _id) {
        SQLiteDatabase dbConn = this.getWritableDatabase();
        dbConn.delete(
                DBContract.TABLE_NAME,
                DBContract.DBColumn._ID + " = ?",
                new String[] { String.valueOf(_id)}
        );
    }
}

package com.callor.todolist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.callor.todolist.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by callor on 2017-08-21.
 */

// DB Insert 를 시도할때 자동으로 호출되는 클래스
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private Context context = null; // 8-23

    // DBHelper 생성
    public DBHelper(Context context) {
        super(context, DBContract.ToDoTable.DB_NAME, null , DB_VERSION);
        this.context = context; // 8-23
    }

    // 최초 DB Insert 가 시도될때 한번만 호출되는 메서드
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로 테이블을 생성하는 코드
        db.execSQL(DBContract.ToDoTable.CREATE_TABLE);

    }

    // DB가 업그레이드 되었을때
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_Version, int new_Version) {
        // i 와 i1 을 비교해서
        // 숫자가 차이가 나면
        // 그에 따른 DB upgrade 코드를 작성해 두면 된다.
    }

    /**
     * 새로운 메모데이터를 추가하는 메서드 생성
     * 8-23
     */
    public long saveData(ToDoListVO vo) {

        // 할일을 입력되었으면 Db Insert 작업 시작
        //1. 현재 날짜와 시각을 구하기
        //   1970. 1. 1 00:00:00 초부터 흐른 밀리초 값
        long now = System.currentTimeMillis();

        // 밀리초 값을 Date 형으로 변환하는
        Date curDate = new Date(now);

        // 날짜형식의 값을 원하는 형태의 문자열 날짜로 변환시키는 방법
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getDate = dateFormat.format(curDate);

        // 날짜형식의 값을 원하는 형태의 문자열 시각으로 변환시키는 방법
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String getTime = timeFormat.format(curDate);

//        String strMemo = contenBindig.tvMemo.getText().toString();

        // DB에 Insert 준비
//        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase dbConn = this.getWritableDatabase();

        // SQL을 실행할 준비
        ContentValues sqlValues = new ContentValues();
        sqlValues.put(DBContract.ToDoTable.COL_S_DATE,getDate);
        sqlValues.put(DBContract.ToDoTable.COL_S_TIME,getTime);
        sqlValues.put(DBContract.ToDoTable.COL_MEMO,vo.getToDoMemo());

        // Insert SQL을 실행하는 문장
        long newId = dbConn.insert(DBContract.ToDoTable.TABLE_NAME,null,sqlValues);
        return newId;

    }

    // DB 로부터 데이터를 읽어서 Cursor를 만드는 메서드
    public Cursor getDbAll() {

        // Db 읽기 위해서 open
        SQLiteDatabase dbConn = this.getReadableDatabase();

        // select COl_S_DATE, COL_S_TIME, COL_MEMO
        String[] projection = {
            DBContract.ToDoTable._ID,
            DBContract.ToDoTable.COL_S_DATE,
            DBContract.ToDoTable.COL_S_TIME,
            DBContract.ToDoTable.COL_MEMO
        };

        Cursor cursor = dbConn.query(
                DBContract.ToDoTable.TABLE_NAME, // 읽어들일 Table이름
                projection,         // view 할 column List
                null,               // WHERE clause
                null,
                null,               // GROUP
                null,               // Having
                null                // SORT
        );
        return cursor;
    }
}

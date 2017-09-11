package com.callor.dbexam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    // 사용자 ID를 받아서 DB를 검색 한다음 해당하는 ID 가 있으면 True를 리턴
    public boolean idCheck(String userId) {
        boolean idCheck = false ;
        String sql = " SELECT count(*) FROM " + DBContract.TABLE_NAME
                + " WHERE " + DBContract.DBColumn.USER_ID + " = '" + userId + "'";

        SQLiteDatabase dbConn = this.getReadableDatabase();
        Cursor c = dbConn.rawQuery(sql,null);

        // DB로 부터 꺼낸 cursor의 맨 처음으로 포지션 이동
        // cursor는 db에서 값을 읽은후 반드시 포지션을 이동해야 cursor로 부터 값을 조회 할수 있다.
        c.moveToFirst();

        // 만약 값이 있으면 커서에
        if(c.getInt(0) > 0) {
            idCheck = true;
        }
        return idCheck ;
    }

    // Email 주소를 받아서 이미 있으면 true, 없으면 false return
    public boolean emailCheck(String userEmail){
        boolean bEmailCheck = false;
        String[] projection = new String[] { "COUNT(*)" };
        String selection = DBContract.DBColumn.USER_EMAIL + " = ? ";  // and " +
//                            DBContract.DBColumn.USER_PHONE + " = ? ";
        String[] selection_Args = new String[] { userEmail } ; // ,"001" };

        SQLiteDatabase dbConn = this.getReadableDatabase();
        Cursor cursor = dbConn.query(
                DBContract.TABLE_NAME,  // 테이블 이름
                projection,             // view에 표시할 칼럼 배열
                selection,              // WHERE에 사용할 칼럼 문자열 ? 를 사용하여 인자(값) 지정
                selection_Args,         // WHERE 구문에 들어갈 인자(값) 배열
                null,                   // GROUP BY 구문
                null,                    // HAVING 구문
                null                   // ORDER BY 구문
        );

        cursor.moveToFirst();
        if(cursor.getInt(0) > 0) {
            bEmailCheck = true;
        }
        return bEmailCheck;
    }

    // 회원정보 전체 리스트 가져오기
    public Cursor getListAll() {

        String[] projection = new String[]{
                DBContract.DBColumn.USER_ID,
                DBContract.DBColumn.USER_EMAIL,
                DBContract.DBColumn.USER_PHONE
        };

        String strOrder = DBContract.DBColumn.USER_ID + " ASC " ; // 정렬할 칼럼

        SQLiteDatabase dbConn = this.getReadableDatabase();
        Cursor cursor = dbConn.query(
                DBContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                strOrder // 정렬
        );
        return cursor;

    }

}

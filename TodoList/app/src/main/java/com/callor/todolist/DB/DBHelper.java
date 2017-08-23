package com.callor.todolist.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by callor on 2017-08-21.
 */

// DB Insert 를 시도할때 자동으로 호출되는 클래스
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DBContract.ToDoTable.DB_NAME, null , DB_VERSION);
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

}

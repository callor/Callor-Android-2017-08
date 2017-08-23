package com.callor.todolist.DB;

import android.provider.BaseColumns;

/**
 * Created by callor on 2017-08-21.
 */
public class DBContract {
    public static class ToDoTable implements BaseColumns {

        // database 이름
        public static final String DB_NAME = "TODO_DATABASE";

        // 할일 목록을 저장할 Table
        public static final String TABLE_NAME = "TODO_TABLE" ;

        // 테이블에 칼럼 지정
        //      할일을 등록한 날짜와 시각
        //      할일
        //      완료한 날짜와 시각
        public static final String COL_S_DATE = "S_DATE";
        public static final String COL_S_TIME = "S_TIME";
        public static final String COL_MEMO = "TODO_MEMO" ;
        public static final String COL_E_DATE = "E_DATE";
        public static final String COL_E_TIME = "E_TIME" ;

        // create table if not exits TODO_TABLE ( IN_DATE_TIME TEXT, TODO_MEMO TEXT, END_DATE_TIME TEXT )

        public static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_S_DATE + " TEXT NOT NULL , " +
                COL_S_TIME + " TEXT NOT NULL , " +
                COL_MEMO + " TEXT NOT NULL , " +
                COL_E_DATE + " TEXT, " +
                COL_E_TIME + " TEXT )  " ;
    }

}

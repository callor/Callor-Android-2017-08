package com.callor.todolist;

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
        public static final String COL_DATE_TIME = "IN_DATE_TIME";
        public static final String COL_TODO = "TODO_MEMO" ;
        public static final String COL_END_DATE_TIME = "END_DATE_TIME";


        // create table if not exits TODO_TABLE ( IN_DATE_TIME TEXT, TODO_MEMO TEXT, END_DATE_TIME TEXT )
        public static final String CREATE_TABLE = " CREATE TABLE IF NOT EXITS " +
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE_TIME + " TEXT, " +
                COL_TODO + " TEXT, " +
                COL_END_DATE_TIME + " TEXT )";





    }

}

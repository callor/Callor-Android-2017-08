package com.callor.dbexam.db;

import android.provider.BaseColumns;

/**
 * Created by callor on 2017-09-08.
 */

// DB에 관련된 여러 상수들을 선언
public class DBContract {

    public static final String DB_NAME = "member.db";
    public static final String TABLE_NAME = "member_table";

    public static class DBColumn implements BaseColumns {
        public static final String USER_ID = "user_id" ;
        public static final String USER_PASSWORD = "user_password";
        public static final String USER_EMAIL ="user_email";
        public static final String USER_PHONE = "user_phone";
    }

    public static class TABLE_CREATE implements BaseColumns {
        public static final String TABLE_CREATE_SQL =
                " CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBColumn.USER_ID + " TEXT NOT NULL, "
                + DBColumn.USER_PASSWORD + " TEXT NOT NULL, "
                + DBColumn.USER_EMAIL + " TEXT, "
                + DBColumn.USER_PHONE + " TEXT ) ";
    }

}









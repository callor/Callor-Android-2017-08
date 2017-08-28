package com.callor.memobook.db;

import android.provider.BaseColumns;

/**
 * Created by callor on 2017-08-28.
 */

public class DBContract {
    public static final String DB_NAME = "memobook.db";
    public static final String TABLE_NAME = "memobook_table";

    public static class DBColumn implements BaseColumns {
        public static final String MEMO_DATE = "memo_date";
        public static final String MEMO_TIME = "memo_time";
        public static final String MEMO_TEXT = "memo_text";
    }

    public static class DBCreate implements BaseColumns {
        public static final String DB_CREATE =
                " CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBColumn.MEMO_DATE + " TEXT NOT NULL, "
                + DBColumn.MEMO_TIME + " TEXT NOT NULL, "
                + DBColumn.MEMO_TEXT + " TEXT NOT NULL ) " ;
    }
}

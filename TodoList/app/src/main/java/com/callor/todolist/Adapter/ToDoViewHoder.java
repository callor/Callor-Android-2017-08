package com.callor.todolist.Adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.callor.todolist.DB.DBContract;
import com.callor.todolist.R;

/**
 * Created by callor on 2017-08-21.
 */

public class ToDoViewHoder extends RecyclerView.ViewHolder {

    TextView txt_item_date = null;
    TextView txt_item_time = null;
    TextView txt_item_memo = null;

    public ToDoViewHoder(View itemView) {
        super(itemView);

        // dbitem.xml에 들어있는 TextView 연결과정
        txt_item_date = (TextView) itemView.findViewById(R.id.txt_item_date);
        txt_item_time = (TextView) itemView.findViewById(R.id.txt_item_time);
        txt_item_memo = (TextView) itemView.findViewById(R.id.txt_item_memo);
    }

    // DB로부터 데이터를 읽었을때,
    public void bindCursor(Cursor cursor) {

            int colIndex = cursor.getColumnIndex(DBContract.ToDoTable.COL_S_DATE);
            String strDate = cursor.getString(colIndex);
            txt_item_date.setText(strDate);

            colIndex = cursor.getColumnIndex(DBContract.ToDoTable.COL_S_TIME);
            String strTime = cursor.getString(colIndex);
            txt_item_time.setText(strTime);

            colIndex = cursor.getColumnIndex(DBContract.ToDoTable.COL_MEMO);
            String strMemo = cursor.getString(colIndex);
            txt_item_memo.setText(strMemo);
    }
}

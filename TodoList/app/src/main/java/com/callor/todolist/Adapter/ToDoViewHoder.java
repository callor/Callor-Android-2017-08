package com.callor.todolist.Adapter;

import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.callor.todolist.DB.DBContract;
import com.callor.todolist.MainActivity;
import com.callor.todolist.R;

/**
 * Created by callor on 2017-08-21.
 */

public class ToDoViewHoder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

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

        // DB Primary Key memo Field의 TAG 저장
        colIndex = cursor.getColumnIndex(DBContract.ToDoTable._ID);
        String _ID = cursor.getString(colIndex);
        txt_item_memo.setTag(_ID);
//        txt_item_memo.setOnClickListener(this);
//        txt_item_memo.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // memo Filed 에 저장된 ID 추출
        String strId = "OnClick:" + String.valueOf(((TextView) view).getTag());
        Snackbar snackbar = Snackbar.make(view, strId, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public boolean onLongClick(View view) {

        String strId = "LongClick:" + String.valueOf(((TextView) view).getTag());
        Snackbar snackbar = Snackbar.make(view, strId, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.YELLOW)
                .setAction("Click", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

        // snack bar에서 view 추출하고
        View snackView = snackbar.getView();
        // 추출된 view의 바탕색을 바꾸어야 snackbar 의 바탕색을 바꿀 있다
        snackView.setBackgroundColor(Color.BLUE);

        snackbar.show();

        return false;
    }
}

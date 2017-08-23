package com.callor.todolist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.callor.todolist.R;

/**
 * Created by callor on 2017-08-21.
 */

public class ToDoViewHoder extends RecyclerView.ViewHolder {

    TextView txt_item_date = null;
    TextView txt_item_memo = null;

    public ToDoViewHoder(View itemView) {
        super(itemView);

        // dbitem.xml에 들어있는 TextView 연결과정
        txt_item_date = (TextView) itemView.findViewById(R.id.txt_item_date);
        txt_item_memo = (TextView) itemView.findViewById(R.id.txt_item_memo);

    }
}

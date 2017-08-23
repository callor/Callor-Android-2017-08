package com.callor.todolist.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todolist.DB.DBHelper;
import com.callor.todolist.R;

/**
 * Created by callor on 2017-08-21.
 */

// ToDoViewHoder와 Adapter를 연결하는 과정
public class DBAdapter extends RecyclerView.Adapter<ToDoViewHoder> {

    // 8 - 23
    Context context ;
    Cursor cursor ;

    // 8 - 23
    public DBAdapter(Context context,Cursor cursor){
        this.context = context;
        this.cursor = cursor ;
    }

    @Override
    public ToDoViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dbitem,parent,false);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v1 = layoutInflater.inflate(R.layout.dbitem,parent,false);

        return new ToDoViewHoder(v);
    }

    // DATA를 한줄씩 그리는 method
    @Override
    public void onBindViewHolder(ToDoViewHoder holder, int position) {

        // DB read 로 부터 받은 cursor와 ViewHolder를 연동시키는 부분
        cursor.moveToPosition(position);
        holder.bindCursor(cursor);

    }

    // 전체 데이터 길이를 알려주는 메서드
    // 데이터 끝부분에 이르렀을때 스크롤을 멈추도록 도움을 주는 메서드
    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

}

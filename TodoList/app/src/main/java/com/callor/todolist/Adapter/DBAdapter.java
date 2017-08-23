package com.callor.todolist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todolist.R;

/**
 * Created by callor on 2017-08-21.
 */

// ToDoViewHoder와 Adapter를 연결하는 과정
public class DBAdapter extends RecyclerView.Adapter<ToDoViewHoder> {
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
        holder.txt_item_date.setText("2017-08-21");
        holder.txt_item_memo.setText("내가 할일");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

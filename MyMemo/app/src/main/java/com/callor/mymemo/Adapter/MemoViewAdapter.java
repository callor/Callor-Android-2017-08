package com.callor.mymemo.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.callor.mymemo.R;
import com.callor.mymemo.db.DBContract;

/**
 * Created by callor on 2017-08-25.
 */
public class MemoViewAdapter extends RecyclerView.Adapter<MemoViewAdapter.MemoViewHolder> {

    Context context;
    Cursor cursor;

    public MemoViewAdapter(Context context,Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_item,parent,false);

        return new MemoViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {

        cursor.moveToPosition(position);
        holder.bindCusor(cursor);

    }
    @Override
    public int getItemCount() {
        return 0;
    }


    public class MemoViewHolder extends RecyclerView.ViewHolder {

        TextView txt_item_date = null;
        TextView txt_item_time = null;
        TextView txt_item_memo = null;

        public MemoViewHolder(View itemView) {
            super(itemView);
            txt_item_date = (TextView)itemView.findViewById(R.id.txt_item_date);
            txt_item_time = (TextView)itemView.findViewById(R.id.txt_item_time);
            txt_item_memo = (TextView)itemView.findViewById(R.id.txt_item_memo);
        }

        public void bindCusor(Cursor cursor) {
            int colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_DATE);
            String strDate = cursor.getString(colIndex);
            txt_item_time.setText(strDate);

            colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_TIME);
            String strTime = cursor.getString(colIndex);
            txt_item_time.setText(strTime);

            colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_TEXT);
            String strMemo = cursor.getString(colIndex);
            txt_item_memo.setText(strMemo);
        }
    }

}

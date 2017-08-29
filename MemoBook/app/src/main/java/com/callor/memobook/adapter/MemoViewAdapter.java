package com.callor.memobook.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.callor.memobook.MemoEditor;
import com.callor.memobook.R;
import com.callor.memobook.db.DBContract;

/**
 * Created by callor on 2017-08-28.
 */

public class MemoViewAdapter extends RecyclerView.Adapter<MemoViewAdapter.MemoViewHolder> {

    private Context context ;
    private Cursor cursor ;
    public MemoViewAdapter(Context context, Cursor cursor) {
        this.context = context ;
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
        holder.bindCursor(cursor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    public class MemoViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener{

        TextView txt_item_date = null;
        TextView txt_item_time = null;
        TextView txt_item_memo = null;
        LinearLayout item_layout = null;

        public MemoViewHolder(View itemView) {
            super(itemView);

            txt_item_date = (TextView)itemView.findViewById(R.id.txt_item_date);
            txt_item_time = (TextView)itemView.findViewById(R.id.txt_item_time);
            txt_item_memo = (TextView)itemView.findViewById(R.id.txt_item_memo);
            item_layout = (LinearLayout)itemView.findViewById(R.id.item_layout);

            itemView.setOnClickListener(this);
        }

        public void bindCursor(Cursor cursor) {

            int colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_DATE);
            String strDate = cursor.getString(colIndex);
            txt_item_date.setText(strDate);

            colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_TIME);
            String strTime = cursor.getString(colIndex);
            txt_item_time.setText(strTime);

            colIndex = cursor.getColumnIndex(DBContract.DBColumn.MEMO_TEXT);
            String strMemo = cursor.getString(colIndex);
            txt_item_memo.setText(strMemo);

            colIndex = cursor.getColumnIndex(DBContract.DBColumn._ID);
            String strId = String.valueOf(cursor.getLong(colIndex));
            item_layout.setTag(strId);
        }

        @Override
        public void onClick(View view) {

            String strMemo = txt_item_memo.getText().toString();
            long _id = Long.valueOf(item_layout.getTag().toString());

            Intent memoIntent = new Intent(context,MemoEditor.class);
            memoIntent.putExtra("memo",strMemo);
            memoIntent.putExtra("_id",_id);

            Log.d("_ID-01",String.valueOf(_id));
            // startActivityForResult를 사용하기 위해서
            // context를 Activity로 강제 형변환시키고
            // start...ForResult 메서드를 호출한다.
            // Key = 2
            int key = 2;
            // Memo Editer 열기
            ((Activity)context).startActivityForResult(memoIntent,key);

        }
    }
}

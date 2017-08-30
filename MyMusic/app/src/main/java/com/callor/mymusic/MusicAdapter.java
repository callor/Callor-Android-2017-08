package com.callor.mymusic;


import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by callor on 2017-08-29.
 */

public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Cursor cursor;
    private DataSetObserver dataSetObserver ;

    private boolean dataVaid ;
    private int rowIdColumn ;

    public MusicAdapter(Context context, Cursor cursor) {

        this.cursor = cursor;
        // Data Load(읽기)되기전 생성된 adapter는
        // cursor가 null 인상태인데 그 상태를 알려주는 flag
        this.dataVaid = cursor != null;
        this.rowIdColumn = this.dataVaid ? this.cursor.getColumnIndex("_id") : -1;

        // load와 cursor data를 연동시키는 부분
        this.dataSetObserver = new NotifyingDataSetObserver();
        if(this.cursor != null) {
            this.cursor.registerDataSetObserver(this.dataSetObserver);
        }

    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_item,parent,false);
        return new MusicHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        ((MusicHolder)holder).bindCursor(cursor);
    }

    @Override
    public int getItemCount() {
        if(dataVaid && cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if(dataVaid && cursor!=null && cursor.moveToPosition(position)) {
            return cursor.getLong(rowIdColumn); // _ID 값을 추출하기
        }
        return 0;
    }

    // 새로 데이터를 Loading하거나
    // 데이터 갱신되면
    // 기존 화면에 있는 cursor와 변경된 cursor를 바꾸어서
    // 화면을 갱신하도록 하는 method
    public Cursor swapCursor(Cursor newCursor) {

        if(newCursor == cursor) {
            return null;
        }
        final Cursor oldCursor = cursor;
        if(oldCursor != null && dataSetObserver != null) {
            oldCursor.unregisterDataSetObserver(dataSetObserver);
        }

        cursor = newCursor ;
        if(cursor != null) {
            if(dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
            rowIdColumn = newCursor.getColumnIndexOrThrow("_id");
            dataVaid = true;
            notifyDataSetChanged();
        } else {
            rowIdColumn = -1;
            dataVaid = false;
            notifyDataSetChanged();
        }
        return oldCursor;
    }

    public List<Long> getMusicIds(){
        int count = getItemCount();
        List<Long> list = new ArrayList<>();
        for(int i = 0 ; i < count ; i++) {
            list.add(getItemId(i));
        };
        return list;
    }

    public class MusicHolder extends RecyclerView.ViewHolder {

        // 음악의 표지 앨범들이 저장되는 곳
        private final Uri artUri = Uri.parse("content://media/external/audio/albumart");
        ImageView imgAlbumArt = null;
        TextView txt_title = null;
        TextView txt_sub_title = null;
        TextView txt_duration = null;

        public MusicHolder(View itemView) {
            super(itemView);
            imgAlbumArt = (ImageView)itemView.findViewById(R.id.img_albumart);
            txt_title = (TextView)itemView.findViewById(R.id.txt_item_title);
            txt_sub_title = (TextView)itemView.findViewById(R.id.txt_item_sub_title);
            txt_duration = (TextView)itemView.findViewById(R.id.txt_item_duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("music","play");
                    Log.d("music",getMusicIds().toString());
                    MusicApp.getMusicInstance()
                            .getMusicSerceInstance()
                            .setPlayList(getMusicIds());
                    MusicApp.getMusicInstance()
                            .getMusicSerceInstance()
                            .play();
                }
            });
        }

        public void bindCursor(Cursor cursor) {
            final Uri artUri = Uri.parse("content://media/external/audio/albumart");

            int cIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
            String strTitle = cursor.getString(cIndex);
            txt_title.setText(strTitle);

            cIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
            String subTitle = cursor.getString(cIndex);

            cIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
            subTitle += "(" + cursor.getString(cIndex) + ")";
            txt_sub_title.setText(subTitle);

            cIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
            long duration = cursor.getLong(cIndex);
            txt_duration.setText(DateFormat.format("mm:ss",duration)); // 03:00

            cIndex = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID);
            // 앨범사진정보를 가져오기 위한 앨범 ID 추출
            long albumId = cursor.getLong(cIndex);
            // 사진정보 가져오기
            Uri albumArtUri = ContentUris.withAppendedId(artUri,albumId);
            Picasso.with(itemView.getContext())
                    .load(albumArtUri)
                    .error(R.drawable.ic_music_note_black_24dp)
                    .into(imgAlbumArt);
        }
    }
    private class NotifyingDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            dataVaid = true;
            notifyDataSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            dataVaid = false;
            notifyDataSetChanged();
        }
    }
}

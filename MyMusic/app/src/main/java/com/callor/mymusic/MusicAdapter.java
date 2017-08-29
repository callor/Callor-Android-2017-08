package com.callor.mymusic;


import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by callor on 2017-08-29.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {
    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MusicHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        }

        public void setMusicItem(MusicVO item, int postion) {

            txt_title.setText(item.mTitle);
            txt_sub_title.setText(item.mArtist + "(" + item.mAlbum + ")"  );
            txt_duration.setText(DateFormat.format("mm:ss", item.mDuration ));
            Picasso
                    .with(itemView.getContext())
                    .load(artUri)
                    .error(R.drawable.ic_music_note_black_24dp)
                    .into(imgAlbumArt);

        }

    }
}

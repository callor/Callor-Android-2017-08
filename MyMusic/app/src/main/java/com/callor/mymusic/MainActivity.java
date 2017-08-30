package com.callor.mymusic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>  {

    RecyclerView recyclerView = null;
    MusicAdapter musicAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 음악 미디어 List를 읽어
        // EXTERNAL STORAGE
        // LOLLIPOP 이상에서는 manifest와 관계없이 별도로 권한을 승인받아야 하는데
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            // EXTERNAL Storage 읽기 권한이 없으면
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

                String[] strReq = new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        "음악을 읽기 위한 권한 설정"
                };
                // 권한 승인창 띄우기
                //                              context, 메시지, key
                ActivityCompat.requestPermissions(this,strReq,1000);

            } else {
            }
        }

        // 권한 확인 끝나면 Adapter recycler view와 연결
        recyclerView = (RecyclerView)findViewById(R.id.music_list);
        musicAdapter = new MusicAdapter(this,null);
        recyclerView.setAdapter(musicAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        getSupportLoaderManager().initLoader(1, null, this);

    }

    // 내장 메모리로부터 데이터(음악)를 읽을때,
    // 비동기 방식으로 읽기위한 기능들
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI ; // 음악리스트가 저장된 STORAGE 경로

        // MediaStore에 있는 음악리스정보 DB중에서 아래 항목들을 리스트로 보고 싶다.
        String[] projection = new String[] {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " = 1 " ; // WHERE 음악파일인 것들만
        String sortOrder = MediaStore.Audio.Media.TITLE + " COLLATE LOCALIZED ASC "; // 한글 어순의 오름차순 정렬
        Loader loader = new CursorLoader(
                getApplicationContext(),
                uri,
                projection,
                selection,
                null
                ,sortOrder
                );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        musicAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        musicAdapter.swapCursor(null);
    }

}

package com.callor.mymusic;

import android.app.Application;

/**
 * Created by callor on 2017-08-30.
 */

public class MusicApp extends Application {

    private static MusicApp musicInstance;
    private MusicServiceInterface musicInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        musicInstance = this;
        musicInterface = new MusicServiceInterface(getApplicationContext());
    }

    public static MusicApp getMusicInstance() {
        return musicInstance;
    }

    public MusicServiceInterface getMusicSerceInstance() {
        return musicInterface ;
    }
}

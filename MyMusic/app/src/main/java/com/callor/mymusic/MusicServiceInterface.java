package com.callor.mymusic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.List;

/**
 * Created by callor on 2017-08-30.
 */

public class MusicServiceInterface {

    private ServiceConnection serviceConnection;
    private MusicService musicService;

    public MusicServiceInterface(Context context) {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                musicService =((MusicService.MusicServiceBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                musicService = null;
                serviceConnection = null;
            }
        };

        // phone의 뮤직 play를 호출하는 과정
        context.bindService(new Intent(context,MusicService.class)
                .setPackage(context.getPackageName()),serviceConnection,Context.BIND_AUTO_CREATE);
    }

    public void setPlayList(List<Long> musicIds) {
        if(musicIds != null) {
            musicService.setPlayList(musicIds);
        }
    }

    public void play() {
        if(musicService != null) {
            musicService.play();
        }
    }
}

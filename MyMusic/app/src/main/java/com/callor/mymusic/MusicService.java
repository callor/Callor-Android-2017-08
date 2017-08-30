package com.callor.mymusic;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;

import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {

    private final IBinder musicBinder = new MusicServiceBinder();
    private List<Long> musicIds = new ArrayList<Long>();
    private MediaPlayer musicPlay ;

    private boolean isPrepared ;
    public MusicService() {
    }

    // 1개의 음악 play되는 중에 동시에 다른 음악 play되지 않도록
    // 서버스(객체)를 중복 생성되지 않도록 하기 위한 선행작업
    public class MusicServiceBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlay = new MediaPlayer();
        musicPlay.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        // play가 시작될때
        musicPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                isPrepared = true;
                mediaPlayer.start();
            }
        });

        // play가 끝났을때
        musicPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPrepared = false;
            }
        });
        musicPlay.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });
        musicPlay.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {

            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return musicBinder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 뮤직 플레이어가 종료될때
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(musicPlay != null) {
            musicPlay.stop();
            musicPlay.release();
            musicPlay = null;
        }
    }

    private void prepare() {
        try {
            musicPlay.setDataSource(mAudioItem.mDataPath);
            musicPlay.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPlayList(List<Long> musicIds) {
        if(!musicIds.equals(musicIds)) {
            musicIds.clear();
            musicIds.addAll(musicIds);
        }
    }

    public void play() {
        if(!isPrepared) {
            musicPlay.start();
        }
    }
}

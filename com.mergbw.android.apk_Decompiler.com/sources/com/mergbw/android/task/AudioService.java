package com.mergbw.android.task;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.mergbw.core.utils.MeRGBWLog;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioService extends Service {
    private static final int CHECK_INTERVAL = 500;
    /* access modifiers changed from: private */
    public final Runnable checkProgress = new Runnable() {
        public void run() {
            if (AudioService.this.mMediaPlayer != null && AudioService.this.mMediaPlayer.isPlaying()) {
                MeRGBWLog.i("Duration: " + AudioService.this.mMediaPlayer.getDuration());
                MeRGBWLog.i("CurrentPosition: " + AudioService.this.mMediaPlayer.getCurrentPosition());
                if (AudioService.this.mMediaPlayer.getDuration() - AudioService.this.mMediaPlayer.getCurrentPosition() < AudioService.CHECK_INTERVAL) {
                    AudioService.this.mMediaPlayer.seekTo(0);
                }
                AudioService.this.mHandler.postDelayed(AudioService.this.checkProgress, 500);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Map<String, IAudioCallback> mListeners = new HashMap();
    /* access modifiers changed from: private */
    public MediaPlayer mMediaPlayer;

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return new MessageBinder();
    }

    public class MessageBinder extends Binder {
        public MessageBinder() {
        }

        public AudioService getService() {
            return AudioService.this;
        }
    }

    public void addCallBack(String str, IAudioCallback iAudioCallback) {
        this.mListeners.put(str, iAudioCallback);
    }

    public void removeCallBack(String str) {
        this.mListeners.remove(str);
    }

    public void onCreate() {
        super.onCreate();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(new AudioService$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-android-task-AudioService  reason: not valid java name */
    public /* synthetic */ void m690lambda$onCreate$0$commergbwandroidtaskAudioService(MediaPlayer mediaPlayer) {
        this.mMediaPlayer.start();
        this.mHandler.postDelayed(this.checkProgress, 500);
    }

    public void startPlay(Context context, int i) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
                this.mMediaPlayer.setDataSource(context.getResources().openRawResourceFd(i));
                this.mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pausePlay() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mHandler.removeCallbacks(this.checkProgress);
        }
    }

    public void resumePlay() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            this.mMediaPlayer.start();
            this.mHandler.post(this.checkProgress);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mHandler.removeCallbacks(this.checkProgress);
        }
    }
}

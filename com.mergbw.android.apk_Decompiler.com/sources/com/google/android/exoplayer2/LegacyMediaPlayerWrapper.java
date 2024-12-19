package com.google.android.exoplayer2;

import android.media.MediaPlayer;
import android.os.Looper;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public final class LegacyMediaPlayerWrapper extends SimpleBasePlayer {
    private boolean playWhenReady;
    private final MediaPlayer player = new MediaPlayer();

    public LegacyMediaPlayerWrapper(Looper looper) {
        super(looper);
    }

    /* access modifiers changed from: protected */
    public SimpleBasePlayer.State getState() {
        return new SimpleBasePlayer.State.Builder().setAvailableCommands(new Player.Commands.Builder().addAll(1).build()).setPlayWhenReady(this.playWhenReady, 1).build();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        this.playWhenReady = z;
        if (z) {
            this.player.start();
        } else {
            this.player.pause();
        }
        return Futures.immediateVoidFuture();
    }
}

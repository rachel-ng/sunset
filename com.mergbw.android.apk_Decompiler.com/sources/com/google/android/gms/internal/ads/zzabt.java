package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzabt implements Choreographer.FrameCallback, Handler.Callback {
    private static final zzabt zzb = new zzabt();
    public volatile long zza = C.TIME_UNSET;
    private final Handler zzc;
    private final HandlerThread zzd;
    private Choreographer zze;
    private int zzf;

    private zzabt() {
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");
        this.zzd = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.zzc = handler;
        handler.sendEmptyMessage(0);
    }

    public static zzabt zza() {
        return zzb;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            try {
                this.zze = Choreographer.getInstance();
            } catch (RuntimeException e) {
                zzfk.zzg("VideoFrameReleaseHelper", "Vsync sampling disabled due to platform error", e);
            }
            return true;
        } else if (i == 1) {
            Choreographer choreographer = this.zze;
            if (choreographer != null) {
                int i2 = this.zzf + 1;
                this.zzf = i2;
                if (i2 == 1) {
                    choreographer.postFrameCallback(this);
                }
            }
            return true;
        } else if (i != 2) {
            return false;
        } else {
            Choreographer choreographer2 = this.zze;
            if (choreographer2 != null) {
                int i3 = this.zzf - 1;
                this.zzf = i3;
                if (i3 == 0) {
                    choreographer2.removeFrameCallback(this);
                    this.zza = C.TIME_UNSET;
                }
            }
            return true;
        }
    }

    public final void zzb() {
        this.zzc.sendEmptyMessage(1);
    }

    public final void zzc() {
        this.zzc.sendEmptyMessage(2);
    }

    public final void doFrame(long j) {
        this.zza = j;
        Choreographer choreographer = this.zze;
        choreographer.getClass();
        choreographer.postFrameCallbackDelayed(this, 500);
    }
}

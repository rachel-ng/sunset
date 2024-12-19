package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.Spatializer;
import android.media.Spatializer$OnSpatializerStateChangedListener;
import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzyx {
    private final Spatializer zza;
    private final boolean zzb;
    private Handler zzc;
    private Spatializer$OnSpatializerStateChangedListener zzd;

    private zzyx(Spatializer spatializer) {
        this.zza = spatializer;
        this.zzb = Chip$$ExternalSyntheticApiModelOutline0.m(spatializer) != 0;
    }

    public static zzyx zza(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return null;
        }
        return new zzyx(audioManager.getSpatializer());
    }

    public final void zzb(zzze zzze, Looper looper) {
        if (this.zzd == null && this.zzc == null) {
            this.zzd = new zzyw(this, zzze);
            Handler handler = new Handler(looper);
            this.zzc = handler;
            Spatializer spatializer = this.zza;
            Objects.requireNonNull(handler);
            spatializer.addOnSpatializerStateChangedListener(new zzyv(handler), this.zzd);
        }
    }

    public final void zzc() {
        Spatializer$OnSpatializerStateChangedListener spatializer$OnSpatializerStateChangedListener = this.zzd;
        if (spatializer$OnSpatializerStateChangedListener != null && this.zzc != null) {
            this.zza.removeOnSpatializerStateChangedListener(spatializer$OnSpatializerStateChangedListener);
            Handler handler = this.zzc;
            int i = zzgd.zza;
            handler.removeCallbacksAndMessages((Object) null);
            this.zzc = null;
            this.zzd = null;
        }
    }

    public final boolean zzd(zzk zzk, zzan zzan) {
        int i;
        if (!MimeTypes.AUDIO_E_AC3_JOC.equals(zzan.zzn) || zzan.zzA != 16) {
            i = zzan.zzA;
        } else {
            i = 12;
        }
        int zzh = zzgd.zzh(i);
        if (zzh == 0) {
            return false;
        }
        AudioFormat.Builder channelMask = new AudioFormat.Builder().setEncoding(2).setChannelMask(zzh);
        int i2 = zzan.zzB;
        if (i2 != -1) {
            channelMask.setSampleRate(i2);
        }
        return this.zza.canBeSpatialized(zzk.zza().zza, channelMask.build());
    }

    public final boolean zze() {
        return this.zza.isAvailable();
    }

    public final boolean zzf() {
        return Chip$$ExternalSyntheticApiModelOutline0.m(this.zza);
    }

    public final boolean zzg() {
        return this.zzb;
    }
}

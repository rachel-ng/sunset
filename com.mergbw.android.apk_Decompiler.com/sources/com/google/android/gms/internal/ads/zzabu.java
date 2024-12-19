package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.Surface;
import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzabu {
    private final zzabe zza = new zzabe();
    private final zzabs zzb;
    private final zzabt zzc;
    private boolean zzd;
    private Surface zze;
    private float zzf;
    private float zzg;
    private float zzh;
    private float zzi;
    private int zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private long zzp;
    private long zzq;

    static /* bridge */ /* synthetic */ void zzb(zzabu zzabu, Display display) {
        if (display != null) {
            long refreshRate = (long) (1.0E9d / ((double) display.getRefreshRate()));
            zzabu.zzk = refreshRate;
            zzabu.zzl = (refreshRate * 80) / 100;
            return;
        }
        zzfk.zzf("VideoFrameReleaseHelper", "Unable to query display refresh rate");
        zzabu.zzk = C.TIME_UNSET;
        zzabu.zzl = C.TIME_UNSET;
    }

    private final void zzk() {
        Surface surface;
        if (zzgd.zza >= 30 && (surface = this.zze) != null && this.zzj != Integer.MIN_VALUE && this.zzh != 0.0f) {
            this.zzh = 0.0f;
            zzabr.zza(surface, 0.0f);
        }
    }

    private final void zzl() {
        this.zzm = 0;
        this.zzp = -1;
        this.zzn = -1;
    }

    private final void zzm() {
        float f;
        if (zzgd.zza >= 30 && this.zze != null) {
            if (this.zza.zzg()) {
                f = this.zza.zza();
            } else {
                f = this.zzf;
            }
            float f2 = this.zzg;
            if (f != f2) {
                int i = (f > -1.0f ? 1 : (f == -1.0f ? 0 : -1));
                if (i != 0 && f2 != -1.0f) {
                    float f3 = 1.0f;
                    if (this.zza.zzg() && this.zza.zzd() >= 5000000000L) {
                        f3 = 0.02f;
                    }
                    if (Math.abs(f - this.zzg) < f3) {
                        return;
                    }
                } else if (i == 0 && this.zza.zzb() < 30) {
                    return;
                }
                this.zzg = f;
                zzn(false);
            }
        }
    }

    private final void zzn(boolean z) {
        Surface surface;
        if (zzgd.zza >= 30 && (surface = this.zze) != null && this.zzj != Integer.MIN_VALUE) {
            float f = 0.0f;
            if (this.zzd) {
                float f2 = this.zzg;
                if (f2 != -1.0f) {
                    f = this.zzi * f2;
                }
            }
            if (z || this.zzh != f) {
                this.zzh = f;
                zzabr.zza(surface, f);
            }
        }
    }

    public final long zza(long j) {
        long j2;
        if (this.zzp != -1 && this.zza.zzg()) {
            long zzc2 = this.zza.zzc();
            long j3 = this.zzq + ((long) (((float) (zzc2 * (this.zzm - this.zzp))) / this.zzi));
            if (Math.abs(j - j3) > 20000000) {
                zzl();
            } else {
                j = j3;
            }
        }
        this.zzn = this.zzm;
        this.zzo = j;
        zzabt zzabt = this.zzc;
        if (!(zzabt == null || this.zzk == C.TIME_UNSET)) {
            long j4 = zzabt.zza;
            if (j4 != C.TIME_UNSET) {
                long j5 = this.zzk;
                long j6 = j4 + (((j - j4) / j5) * j5);
                if (j <= j6) {
                    j2 = j6 - j5;
                } else {
                    long j7 = j6;
                    j6 = j5 + j6;
                    j2 = j7;
                }
                long j8 = this.zzl;
                if (j6 - j >= j - j2) {
                    j6 = j2;
                }
                return j6 - j8;
            }
        }
        return j;
    }

    public final void zzc(float f) {
        this.zzf = f;
        this.zza.zzf();
        zzm();
    }

    public final void zzd(long j) {
        long j2 = this.zzn;
        if (j2 != -1) {
            this.zzp = j2;
            this.zzq = this.zzo;
        }
        this.zzm++;
        this.zza.zze(j * 1000);
        zzm();
    }

    public final void zze(float f) {
        this.zzi = f;
        zzl();
        zzn(false);
    }

    public final void zzf() {
        zzl();
    }

    public final void zzg() {
        this.zzd = true;
        zzl();
        if (this.zzb != null) {
            zzabt zzabt = this.zzc;
            zzabt.getClass();
            zzabt.zzb();
            this.zzb.zza();
        }
        zzn(false);
    }

    public final void zzh() {
        this.zzd = false;
        zzabs zzabs = this.zzb;
        if (zzabs != null) {
            zzabs.zzb();
            zzabt zzabt = this.zzc;
            zzabt.getClass();
            zzabt.zzc();
        }
        zzk();
    }

    public final void zzi(Surface surface) {
        if (true == (surface instanceof zzabm)) {
            surface = null;
        }
        if (this.zze != surface) {
            zzk();
            this.zze = surface;
            zzn(true);
        }
    }

    public final void zzj(int i) {
        if (this.zzj != i) {
            this.zzj = i;
            zzn(true);
        }
    }

    public zzabu(Context context) {
        zzabs zzabs;
        DisplayManager displayManager;
        zzabt zzabt = null;
        if (context == null || (displayManager = (DisplayManager) context.getSystemService("display")) == null) {
            zzabs = null;
        } else {
            zzabs = new zzabs(this, displayManager);
        }
        this.zzb = zzabs;
        this.zzc = zzabs != null ? zzabt.zza() : zzabt;
        this.zzk = C.TIME_UNSET;
        this.zzl = C.TIME_UNSET;
        this.zzf = -1.0f;
        this.zzi = 1.0f;
        this.zzj = 0;
    }
}

package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import java.lang.reflect.Method;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzqz {
    private long zzA;
    private long zzB;
    private long zzC;
    private boolean zzD;
    private long zzE;
    private long zzF;
    private boolean zzG;
    private long zzH;
    private zzer zzI;
    private final zzqy zza;
    private final long[] zzb;
    private AudioTrack zzc;
    private int zzd;
    private zzqx zze;
    private int zzf;
    private boolean zzg;
    private long zzh;
    private float zzi;
    private boolean zzj;
    private long zzk;
    private long zzl;
    private Method zzm;
    private long zzn;
    private boolean zzo;
    private boolean zzp;
    private long zzq;
    private long zzr;
    private long zzs;
    private long zzt;
    private long zzu;
    private int zzv;
    private int zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    public zzqz(zzqy zzqy) {
        this.zza = zzqy;
        try {
            this.zzm = AudioTrack.class.getMethod("getLatency", (Class[]) null);
        } catch (NoSuchMethodException unused) {
        }
        this.zzb = new long[10];
        this.zzI = zzer.zza;
    }

    private final long zzl() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i = 2;
        if (this.zzx != C.TIME_UNSET) {
            AudioTrack audioTrack = this.zzc;
            audioTrack.getClass();
            if (audioTrack.getPlayState() == 2) {
                return this.zzz;
            }
            return Math.min(this.zzA, this.zzz + zzgd.zzo(zzgd.zzp(zzgd.zzr(elapsedRealtime) - this.zzx, this.zzi), this.zzf));
        }
        if (elapsedRealtime - this.zzr >= 5) {
            AudioTrack audioTrack2 = this.zzc;
            audioTrack2.getClass();
            int playState = audioTrack2.getPlayState();
            if (playState != 1) {
                long playbackHeadPosition = ((long) audioTrack2.getPlaybackHeadPosition()) & 4294967295L;
                long j = 0;
                if (this.zzg) {
                    if (playState != 2) {
                        i = playState;
                    } else if (playbackHeadPosition == 0) {
                        this.zzu = this.zzs;
                    }
                    playbackHeadPosition += this.zzu;
                    playState = i;
                }
                if (zzgd.zza <= 29) {
                    if (playbackHeadPosition != 0) {
                        j = playbackHeadPosition;
                    } else if (this.zzs > 0 && playState == 3) {
                        if (this.zzy == C.TIME_UNSET) {
                            this.zzy = elapsedRealtime;
                        }
                    }
                    this.zzy = C.TIME_UNSET;
                    playbackHeadPosition = j;
                }
                if (this.zzs > playbackHeadPosition) {
                    this.zzt++;
                }
                this.zzs = playbackHeadPosition;
            }
            this.zzr = elapsedRealtime;
        }
        return this.zzs + this.zzH + (this.zzt << 32);
    }

    private final long zzm() {
        return zzgd.zzs(zzl(), this.zzf);
    }

    private final void zzn() {
        this.zzk = 0;
        this.zzw = 0;
        this.zzv = 0;
        this.zzl = 0;
        this.zzC = 0;
        this.zzF = 0;
        this.zzj = false;
    }

    public final long zza(boolean z) {
        long j;
        String str;
        Method method;
        AudioTrack audioTrack = this.zzc;
        audioTrack.getClass();
        if (audioTrack.getPlayState() == 3) {
            long nanoTime = System.nanoTime() / 1000;
            if (nanoTime - this.zzl >= 30000) {
                long zzm2 = zzm();
                if (zzm2 != 0) {
                    this.zzb[this.zzv] = zzgd.zzq(zzm2, this.zzi) - nanoTime;
                    this.zzv = (this.zzv + 1) % 10;
                    int i = this.zzw;
                    if (i < 10) {
                        this.zzw = i + 1;
                    }
                    this.zzl = nanoTime;
                    this.zzk = 0;
                    int i2 = 0;
                    while (true) {
                        int i3 = this.zzw;
                        if (i2 >= i3) {
                            break;
                        }
                        this.zzk += this.zzb[i2] / ((long) i3);
                        i2++;
                    }
                }
            }
            if (!this.zzg) {
                zzqx zzqx = this.zze;
                zzqx.getClass();
                if (!zzqx.zzg(nanoTime)) {
                    str = "DefaultAudioSink";
                } else {
                    long zzb2 = zzqx.zzb();
                    long zza2 = zzqx.zza();
                    long zzm3 = zzm();
                    if (Math.abs(zzb2 - nanoTime) > 5000000) {
                        zzrz zzrz = ((zzru) this.zza).zza;
                        String str2 = "Spurious audio timestamp (system clock mismatch): " + zza2 + ", " + zzb2 + ", " + nanoTime + ", " + zzm3 + ", " + zzrz.zzL() + ", " + zzrz.zzM();
                        str = "DefaultAudioSink";
                        zzfk.zzf(str, str2);
                        zzqx.zzd();
                    } else {
                        str = "DefaultAudioSink";
                        if (Math.abs(zzgd.zzs(zza2, this.zzf) - zzm3) > 5000000) {
                            zzrz zzrz2 = ((zzru) this.zza).zza;
                            zzfk.zzf(str, "Spurious audio timestamp (frame position mismatch): " + zza2 + ", " + zzb2 + ", " + nanoTime + ", " + zzm3 + ", " + zzrz2.zzL() + ", " + zzrz2.zzM());
                            zzqx.zzd();
                        } else {
                            zzqx.zzc();
                        }
                    }
                }
                if (this.zzp && (method = this.zzm) != null && nanoTime - this.zzq >= 500000) {
                    try {
                        AudioTrack audioTrack2 = this.zzc;
                        if (audioTrack2 != null) {
                            int i4 = zzgd.zza;
                            long intValue = (((long) ((Integer) method.invoke(audioTrack2, (Object[]) null)).intValue()) * 1000) - this.zzh;
                            this.zzn = intValue;
                            long max = Math.max(intValue, 0);
                            this.zzn = max;
                            if (max > 5000000) {
                                zzfk.zzf(str, "Ignoring impossibly large audio latency: " + max);
                                this.zzn = 0;
                            }
                            this.zzq = nanoTime;
                        } else {
                            throw null;
                        }
                    } catch (Exception unused) {
                        this.zzm = null;
                    }
                }
            }
        }
        long nanoTime2 = System.nanoTime() / 1000;
        zzqx zzqx2 = this.zze;
        zzqx2.getClass();
        boolean zzf2 = zzqx2.zzf();
        if (zzf2) {
            j = zzgd.zzs(zzqx2.zza(), this.zzf) + zzgd.zzp(nanoTime2 - zzqx2.zzb(), this.zzi);
        } else {
            if (this.zzw == 0) {
                j = zzm();
            } else {
                j = zzgd.zzp(this.zzk + nanoTime2, this.zzi);
            }
            if (!z) {
                j = Math.max(0, j - this.zzn);
            }
        }
        if (this.zzD != zzf2) {
            this.zzF = this.zzC;
            this.zzE = this.zzB;
        }
        long j2 = nanoTime2 - this.zzF;
        if (j2 < 1000000) {
            long j3 = (j2 * 1000) / 1000000;
            j = ((j * j3) + ((1000 - j3) * (this.zzE + zzgd.zzp(j2, this.zzi)))) / 1000;
        }
        if (!this.zzj) {
            long j4 = this.zzB;
            if (j > j4) {
                this.zzj = true;
                int i5 = zzgd.zza;
                long currentTimeMillis = System.currentTimeMillis() - zzgd.zzu(zzgd.zzq(zzgd.zzu(j - j4), this.zzi));
                zzrz zzrz3 = ((zzru) this.zza).zza;
                if (zzrz3.zzq != null) {
                    ((zzse) zzrz3.zzq).zza.zzc.zzv(currentTimeMillis);
                }
            }
        }
        this.zzC = nanoTime2;
        this.zzB = j;
        this.zzD = zzf2;
        return j;
    }

    public final void zzb(long j) {
        this.zzz = zzl();
        this.zzx = zzgd.zzr(SystemClock.elapsedRealtime());
        this.zzA = j;
    }

    public final void zzc() {
        zzn();
        this.zzc = null;
        this.zze = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(android.media.AudioTrack r3, boolean r4, int r5, int r6, int r7) {
        /*
            r2 = this;
            r2.zzc = r3
            r2.zzd = r7
            com.google.android.gms.internal.ads.zzqx r0 = new com.google.android.gms.internal.ads.zzqx
            r0.<init>(r3)
            r2.zze = r0
            int r3 = r3.getSampleRate()
            r2.zzf = r3
            r3 = 0
            if (r4 == 0) goto L_0x0023
            int r4 = com.google.android.gms.internal.ads.zzgd.zza
            r0 = 23
            if (r4 >= r0) goto L_0x0023
            r4 = 5
            r0 = 1
            if (r5 == r4) goto L_0x0024
            r4 = 6
            if (r5 != r4) goto L_0x0023
            r5 = r4
            goto L_0x0024
        L_0x0023:
            r0 = r3
        L_0x0024:
            r2.zzg = r0
            boolean r4 = com.google.android.gms.internal.ads.zzgd.zzK(r5)
            r2.zzp = r4
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x003c
            int r7 = r7 / r6
            long r4 = (long) r7
            int r6 = r2.zzf
            long r4 = com.google.android.gms.internal.ads.zzgd.zzs(r4, r6)
            goto L_0x003d
        L_0x003c:
            r4 = r0
        L_0x003d:
            r2.zzh = r4
            r4 = 0
            r2.zzs = r4
            r2.zzt = r4
            r2.zzG = r3
            r2.zzH = r4
            r2.zzu = r4
            r2.zzo = r3
            r2.zzx = r0
            r2.zzy = r0
            r2.zzq = r4
            r2.zzn = r4
            r3 = 1065353216(0x3f800000, float:1.0)
            r2.zzi = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqz.zzd(android.media.AudioTrack, boolean, int, int, int):void");
    }

    public final void zze(zzer zzer) {
        this.zzI = zzer;
    }

    public final void zzf() {
        if (this.zzx != C.TIME_UNSET) {
            this.zzx = zzgd.zzr(SystemClock.elapsedRealtime());
        }
        zzqx zzqx = this.zze;
        zzqx.getClass();
        zzqx.zze();
    }

    public final boolean zzg(long j) {
        if (j > zzgd.zzo(zza(false), this.zzf)) {
            return true;
        }
        if (this.zzg) {
            AudioTrack audioTrack = this.zzc;
            audioTrack.getClass();
            if (audioTrack.getPlayState() == 2 && zzl() == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final boolean zzh() {
        AudioTrack audioTrack = this.zzc;
        audioTrack.getClass();
        return audioTrack.getPlayState() == 3;
    }

    public final boolean zzi(long j) {
        return this.zzy != C.TIME_UNSET && j > 0 && SystemClock.elapsedRealtime() - this.zzy >= 200;
    }

    public final boolean zzj(long j) {
        AudioTrack audioTrack = this.zzc;
        audioTrack.getClass();
        int playState = audioTrack.getPlayState();
        if (this.zzg) {
            if (playState == 2) {
                this.zzo = false;
                return false;
            } else if (playState == 1) {
                if (zzl() == 0) {
                    return false;
                }
                playState = 1;
            }
        }
        boolean z = this.zzo;
        boolean zzg2 = zzg(j);
        this.zzo = zzg2;
        if (z && !zzg2 && playState != 1) {
            zzqy zzqy = this.zza;
            int i = this.zzd;
            long zzu2 = zzgd.zzu(this.zzh);
            zzru zzru = (zzru) zzqy;
            zzrz zzrz = zzru.zza;
            if (zzrz.zzq != null) {
                ((zzse) zzru.zza.zzq).zza.zzc.zzx(i, zzu2, SystemClock.elapsedRealtime() - zzrz.zzW);
            }
        }
        return true;
    }

    public final boolean zzk() {
        zzn();
        if (this.zzx == C.TIME_UNSET) {
            zzqx zzqx = this.zze;
            zzqx.getClass();
            zzqx.zze();
            return true;
        }
        this.zzz = zzl();
        return false;
    }
}

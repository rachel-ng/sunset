package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaot implements zzapp {
    private final zzaoc zza;
    private final zzft zzb = new zzft(new byte[10], 10);
    private int zzc = 0;
    private int zzd;
    private zzgb zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;

    public zzaot(zzaoc zzaoc) {
        this.zza = zzaoc;
    }

    private final void zzd(int i) {
        this.zzc = i;
        this.zzd = 0;
    }

    private final boolean zze(zzfu zzfu, byte[] bArr, int i) {
        int min = Math.min(zzfu.zzb(), i - this.zzd);
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            zzfu.zzL(min);
        } else {
            zzfu.zzG(bArr, this.zzd, min);
        }
        int i2 = this.zzd + min;
        this.zzd = i2;
        if (i2 == i) {
            return true;
        }
        return false;
    }

    public final void zza(zzfu zzfu, int i) throws zzch {
        int i2;
        long j;
        int i3;
        zzfu zzfu2 = zzfu;
        zzeq.zzb(this.zze);
        int i4 = -1;
        int i5 = 2;
        int i6 = 1;
        if ((i & 1) != 0) {
            int i7 = this.zzc;
            if (!(i7 == 0 || i7 == 1)) {
                if (i7 != 2) {
                    int i8 = this.zzj;
                    if (i8 != -1) {
                        zzfk.zzf("PesReader", "Unexpected start indicator: expected " + i8 + " more bytes");
                    }
                    this.zza.zzc();
                } else {
                    zzfk.zzf("PesReader", "Unexpected start indicator reading extended header");
                }
            }
            zzd(1);
        }
        int i9 = i;
        while (zzfu.zzb() > 0) {
            int i10 = this.zzc;
            if (i10 != 0) {
                int i11 = 0;
                if (i10 != i6) {
                    if (i10 != i5) {
                        int zzb2 = zzfu.zzb();
                        int i12 = this.zzj;
                        if (i12 != i4) {
                            i11 = zzb2 - i12;
                        }
                        if (i11 > 0) {
                            zzb2 -= i11;
                            zzfu2.zzJ(zzfu.zzd() + zzb2);
                        }
                        this.zza.zza(zzfu2);
                        int i13 = this.zzj;
                        if (i13 != i4) {
                            int i14 = i13 - zzb2;
                            this.zzj = i14;
                            if (i14 == 0) {
                                this.zza.zzc();
                                zzd(i6);
                            }
                        }
                    } else {
                        if (zze(zzfu2, this.zzb.zza, Math.min(10, this.zzi)) && zze(zzfu2, (byte[]) null, this.zzi)) {
                            this.zzb.zzk(0);
                            if (this.zzf) {
                                this.zzb.zzm(4);
                                long zzd2 = (long) this.zzb.zzd(3);
                                this.zzb.zzm(i6);
                                int zzd3 = this.zzb.zzd(15) << 15;
                                this.zzb.zzm(i6);
                                long zzd4 = (long) this.zzb.zzd(15);
                                this.zzb.zzm(i6);
                                if (this.zzh || !this.zzg) {
                                    i3 = zzd3;
                                } else {
                                    this.zzb.zzm(4);
                                    this.zzb.zzm(i6);
                                    this.zzb.zzm(i6);
                                    long zzd5 = (long) this.zzb.zzd(15);
                                    this.zzb.zzm(i6);
                                    i3 = zzd3;
                                    this.zze.zzb((((long) this.zzb.zzd(3)) << 30) | ((long) (this.zzb.zzd(15) << 15)) | zzd5);
                                    this.zzh = true;
                                }
                                j = this.zze.zzb((zzd2 << 30) | ((long) i3) | zzd4);
                            } else {
                                j = C.TIME_UNSET;
                            }
                            i9 |= true != this.zzk ? 0 : 4;
                            this.zza.zzd(j, i9);
                            zzd(3);
                            i4 = -1;
                            i5 = 2;
                            i6 = 1;
                        }
                    }
                    i2 = i5;
                } else if (zze(zzfu2, this.zzb.zza, 9)) {
                    int i15 = 0;
                    this.zzb.zzk(0);
                    int zzd6 = this.zzb.zzd(24);
                    i6 = 1;
                    if (zzd6 != 1) {
                        zzfk.zzf("PesReader", "Unexpected start code prefix: " + zzd6);
                        i4 = -1;
                        this.zzj = -1;
                        i2 = 2;
                    } else {
                        this.zzb.zzm(8);
                        zzft zzft = this.zzb;
                        int zzd7 = zzft.zzd(16);
                        zzft.zzm(5);
                        this.zzk = this.zzb.zzo();
                        i2 = 2;
                        this.zzb.zzm(2);
                        this.zzf = this.zzb.zzo();
                        this.zzg = this.zzb.zzo();
                        this.zzb.zzm(6);
                        int zzd8 = this.zzb.zzd(8);
                        this.zzi = zzd8;
                        if (zzd7 == 0) {
                            this.zzj = -1;
                            i4 = -1;
                        } else {
                            int i16 = (zzd7 - 3) - zzd8;
                            this.zzj = i16;
                            if (i16 < 0) {
                                zzfk.zzf("PesReader", "Found negative packet payload size: " + i16);
                                i4 = -1;
                                this.zzj = -1;
                            } else {
                                i4 = -1;
                            }
                        }
                        i15 = 2;
                    }
                    zzd(i15);
                } else {
                    i4 = -1;
                    i6 = 1;
                    i2 = 2;
                }
            } else {
                i2 = i5;
                zzfu2.zzL(zzfu.zzb());
            }
            i5 = i2;
        }
    }

    public final void zzb(zzgb zzgb, zzadx zzadx, zzapo zzapo) {
        this.zze = zzgb;
        this.zza.zzb(zzadx, zzapo);
    }

    public final void zzc() {
        this.zzc = 0;
        this.zzd = 0;
        this.zzh = false;
        this.zza.zze();
    }
}

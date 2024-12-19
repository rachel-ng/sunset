package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhan implements zzhdr {
    private final zzham zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzhan(zzham zzham) {
        zzhcb.zzc(zzham, "input");
        this.zza = zzham;
        zzham.zzc = this;
    }

    private final Object zzR(zzhdz zzhdz, zzhay zzhay) throws IOException {
        Object zze = zzhdz.zze();
        zzT(zze, zzhdz, zzhay);
        zzhdz.zzf(zze);
        return zze;
    }

    private final Object zzS(zzhdz zzhdz, zzhay zzhay) throws IOException {
        Object zze = zzhdz.zze();
        zzU(zze, zzhdz, zzhay);
        zzhdz.zzf(zze);
        return zze;
    }

    private final void zzT(Object obj, zzhdz zzhdz, zzhay zzhay) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzhdz.zzh(obj, this, zzhay);
            if (this.zzb != this.zzc) {
                throw zzhcd.zzg();
            }
        } finally {
            this.zzc = i;
        }
    }

    private final void zzU(Object obj, zzhdz zzhdz, zzhay zzhay) throws IOException {
        zzham zzham = this.zza;
        int zzn = zzham.zzn();
        if (zzham.zza < zzham.zzb) {
            int zze = this.zza.zze(zzn);
            this.zza.zza++;
            zzhdz.zzh(obj, this, zzhay);
            this.zza.zzz(0);
            zzham zzham2 = this.zza;
            zzham2.zza--;
            zzham2.zzA(zze);
            return;
        }
        throw new zzhcd("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final void zzV(int i) throws IOException {
        if (this.zza.zzd() != i) {
            throw zzhcd.zzj();
        }
    }

    private final void zzW(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzhcd.zza();
        }
    }

    private static final void zzX(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzhcd.zzg();
        }
    }

    private static final void zzY(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzhcd.zzg();
        }
    }

    public static zzhan zzq(zzham zzham) {
        zzhan zzhan = zzham.zzc;
        if (zzhan != null) {
            return zzhan;
        }
        return new zzhan(zzham);
    }

    public final void zzA(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhbp.zzi(this.zza.zzf());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhbp.zzi(this.zza.zzf());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzB(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzX(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhbp.zzi(this.zza.zzg());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzhbp.zzi(this.zza.zzg());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzX(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzC(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzhct.zzg(this.zza.zzo());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzY(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhct.zzg(this.zza.zzo());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzY(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzD(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbf) {
            zzhbf zzhbf = (zzhbf) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzX(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhbf.zzh(this.zza.zzc());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzhbf.zzh(this.zza.zzc());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzX(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    @Deprecated
    public final void zzE(List list, zzhdz zzhdz, zzhay zzhay) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                list.add(zzR(zzhdz, zzhay));
                if (!this.zza.zzC() && this.zzd == 0) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i);
            this.zzd = zzm;
            return;
        }
        throw zzhcd.zza();
    }

    public final void zzF(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhbp.zzi(this.zza.zzh());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhbp.zzi(this.zza.zzh());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzG(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhct.zzg(this.zza.zzp());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhct.zzg(this.zza.zzp());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzH(List list, zzhdz zzhdz, zzhay zzhay) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                list.add(zzS(zzhdz, zzhay));
                if (!this.zza.zzC() && this.zzd == 0) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i);
            this.zzd = zzm;
            return;
        }
        throw zzhcd.zza();
    }

    public final void zzI(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzX(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhbp.zzi(this.zza.zzk());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzhbp.zzi(this.zza.zzk());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzX(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzJ(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzhct.zzg(this.zza.zzt());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzY(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhct.zzg(this.zza.zzt());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzY(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzK(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhbp.zzi(this.zza.zzl());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhbp.zzi(this.zza.zzl());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzL(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhct.zzg(this.zza.zzu());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhct.zzg(this.zza.zzu());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzM(List list, boolean z) throws IOException {
        int zzm;
        int i;
        if ((this.zzb & 7) == 2) {
            if ((list instanceof zzhcm) && !z) {
                zzhcm zzhcm = (zzhcm) list;
                do {
                    zzhcm.zzi(zzp());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                do {
                    list.add(z ? zzu() : zzt());
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            }
            this.zzd = i;
            return;
        }
        throw zzhcd.zza();
    }

    public final void zzN(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhbp) {
            zzhbp zzhbp = (zzhbp) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhbp.zzi(this.zza.zzn());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhbp.zzi(this.zza.zzn());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzO(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhct) {
            zzhct zzhct = (zzhct) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzhct.zzg(this.zza.zzv());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzhct.zzg(this.zza.zzv());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final boolean zzP() throws IOException {
        zzW(0);
        return this.zza.zzD();
    }

    public final boolean zzQ() throws IOException {
        int i;
        if (this.zza.zzC() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(i);
    }

    public final double zza() throws IOException {
        zzW(1);
        return this.zza.zzb();
    }

    public final float zzb() throws IOException {
        zzW(5);
        return this.zza.zzc();
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzm();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() throws IOException {
        zzW(0);
        return this.zza.zzf();
    }

    public final int zzf() throws IOException {
        zzW(5);
        return this.zza.zzg();
    }

    public final int zzg() throws IOException {
        zzW(0);
        return this.zza.zzh();
    }

    public final int zzh() throws IOException {
        zzW(5);
        return this.zza.zzk();
    }

    public final int zzi() throws IOException {
        zzW(0);
        return this.zza.zzl();
    }

    public final int zzj() throws IOException {
        zzW(0);
        return this.zza.zzn();
    }

    public final long zzk() throws IOException {
        zzW(1);
        return this.zza.zzo();
    }

    public final long zzl() throws IOException {
        zzW(0);
        return this.zza.zzp();
    }

    public final long zzm() throws IOException {
        zzW(1);
        return this.zza.zzt();
    }

    public final long zzn() throws IOException {
        zzW(0);
        return this.zza.zzu();
    }

    public final long zzo() throws IOException {
        zzW(0);
        return this.zza.zzv();
    }

    public final zzhac zzp() throws IOException {
        zzW(2);
        return this.zza.zzw();
    }

    @Deprecated
    public final Object zzr(Class cls, zzhay zzhay) throws IOException {
        zzW(3);
        return zzR(zzhdo.zza().zzb(cls), zzhay);
    }

    public final Object zzs(Class cls, zzhay zzhay) throws IOException {
        zzW(2);
        return zzS(zzhdo.zza().zzb(cls), zzhay);
    }

    public final String zzt() throws IOException {
        zzW(2);
        return this.zza.zzx();
    }

    public final String zzu() throws IOException {
        zzW(2);
        return this.zza.zzy();
    }

    public final void zzv(Object obj, zzhdz zzhdz, zzhay zzhay) throws IOException {
        zzW(3);
        zzT(obj, zzhdz, zzhay);
    }

    public final void zzw(Object obj, zzhdz zzhdz, zzhay zzhay) throws IOException {
        zzW(2);
        zzU(obj, zzhdz, zzhay);
    }

    public final void zzx(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzgzp) {
            zzgzp zzgzp = (zzgzp) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzgzp.zzg(this.zza.zzD());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzham zzham = this.zza;
                int zzd2 = zzham.zzd() + zzham.zzn();
                do {
                    zzgzp.zzg(this.zza.zzD());
                } while (this.zza.zzd() < zzd2);
                zzV(zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzham zzham2 = this.zza;
                int zzd3 = zzham2.zzd() + zzham2.zzn();
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                } while (this.zza.zzd() < zzd3);
                zzV(zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }

    public final void zzy(List list) throws IOException {
        int zzm;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                if (!this.zza.zzC()) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        throw zzhcd.zza();
    }

    public final void zzz(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzhav) {
            zzhav zzhav = (zzhav) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzhav.zzh(this.zza.zzb());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzY(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzhav.zzh(this.zza.zzb());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw zzhcd.zza();
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzY(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw zzhcd.zza();
            }
        }
        this.zzd = i;
    }
}

package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzwr implements zzvm, zzadx, zzaaa, zzaaf, zzxd {
    /* access modifiers changed from: private */
    public static final Map zzb;
    /* access modifiers changed from: private */
    public static final zzan zzc;
    /* access modifiers changed from: private */
    public long zzA;
    private boolean zzB;
    private int zzC;
    private boolean zzD;
    private boolean zzE;
    private int zzF;
    private boolean zzG;
    private long zzH;
    private long zzI;
    private boolean zzJ;
    private int zzK;
    private boolean zzL;
    private boolean zzM;
    private final zzzz zzN;
    private final zzzv zzO;
    private final Uri zzd;
    private final zzhb zze;
    private final zzst zzf;
    private final zzvx zzg;
    private final zzso zzh;
    private final zzwn zzi;
    /* access modifiers changed from: private */
    public final long zzj;
    private final zzaai zzk = new zzaai("ProgressiveMediaPeriod");
    private final zzwg zzl;
    private final zzeu zzm;
    private final Runnable zzn;
    /* access modifiers changed from: private */
    public final Runnable zzo;
    /* access modifiers changed from: private */
    public final Handler zzp;
    private final boolean zzq;
    private zzvl zzr;
    /* access modifiers changed from: private */
    public zzaha zzs;
    private zzxe[] zzt;
    private zzwp[] zzu;
    private boolean zzv;
    private boolean zzw;
    private boolean zzx;
    private zzwq zzy;
    private zzaet zzz;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_NAME, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        zzb = Collections.unmodifiableMap(hashMap);
        zzal zzal = new zzal();
        zzal.zzK("icy");
        zzal.zzX(MimeTypes.APPLICATION_ICY);
        zzc = zzal.zzad();
    }

    public zzwr(Uri uri, zzhb zzhb, zzwg zzwg, zzst zzst, zzso zzso, zzzz zzzz, zzvx zzvx, zzwn zzwn, zzzv zzzv, String str, int i, long j) {
        this.zzd = uri;
        this.zze = zzhb;
        this.zzf = zzst;
        this.zzh = zzso;
        this.zzN = zzzz;
        this.zzg = zzvx;
        this.zzi = zzwn;
        this.zzO = zzzv;
        this.zzj = (long) i;
        this.zzl = zzwg;
        this.zzA = j;
        this.zzq = j != C.TIME_UNSET;
        this.zzm = new zzeu(zzer.zza);
        this.zzn = new zzwi(this);
        this.zzo = new zzwj(this);
        this.zzp = zzgd.zzx((Handler.Callback) null);
        this.zzu = new zzwp[0];
        this.zzt = new zzxe[0];
        this.zzI = C.TIME_UNSET;
        this.zzC = 1;
    }

    private final int zzQ() {
        int i = 0;
        for (zzxe zzd2 : this.zzt) {
            i += zzd2.zzd();
        }
        return i;
    }

    /* access modifiers changed from: private */
    public final long zzR(boolean z) {
        int i = 0;
        long j = Long.MIN_VALUE;
        while (true) {
            zzxe[] zzxeArr = this.zzt;
            if (i >= zzxeArr.length) {
                return j;
            }
            if (!z) {
                zzwq zzwq = this.zzy;
                zzwq.getClass();
                if (!zzwq.zzc[i]) {
                    i++;
                }
            }
            j = Math.max(j, zzxeArr[i].zzh());
            i++;
        }
    }

    private final zzafa zzS(zzwp zzwp) {
        int length = this.zzt.length;
        for (int i = 0; i < length; i++) {
            if (zzwp.equals(this.zzu[i])) {
                return this.zzt[i];
            }
        }
        zzxe zzxe = new zzxe(this.zzO, this.zzf, this.zzh);
        zzxe.zzu(this);
        int i2 = length + 1;
        zzwp[] zzwpArr = (zzwp[]) Arrays.copyOf(this.zzu, i2);
        zzwpArr[length] = zzwp;
        int i3 = zzgd.zza;
        this.zzu = zzwpArr;
        zzxe[] zzxeArr = (zzxe[]) Arrays.copyOf(this.zzt, i2);
        zzxeArr[length] = zzxe;
        this.zzt = zzxeArr;
        return zzxe;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    private final void zzT() {
        zzeq.zzf(this.zzw);
        this.zzy.getClass();
        this.zzz.getClass();
    }

    /* access modifiers changed from: private */
    public final void zzU() {
        int i;
        zzcd zzcd;
        if (!this.zzM && !this.zzw && this.zzv && this.zzz != null) {
            zzxe[] zzxeArr = this.zzt;
            int length = zzxeArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (zzxeArr[i2].zzi() != null) {
                    i2++;
                } else {
                    return;
                }
            }
            this.zzm.zzc();
            int length2 = this.zzt.length;
            zzde[] zzdeArr = new zzde[length2];
            boolean[] zArr = new boolean[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                zzan zzi2 = this.zzt[i3].zzi();
                zzi2.getClass();
                String str = zzi2.zzn;
                boolean zzg2 = zzcg.zzg(str);
                boolean z = zzg2 || zzcg.zzh(str);
                zArr[i3] = z;
                this.zzx = z | this.zzx;
                zzaha zzaha = this.zzs;
                if (zzaha != null) {
                    if (zzg2 || this.zzu[i3].zzb) {
                        zzcd zzcd2 = zzi2.zzl;
                        if (zzcd2 == null) {
                            zzcd = new zzcd(C.TIME_UNSET, zzaha);
                        } else {
                            zzcd = zzcd2.zzc(zzaha);
                        }
                        zzal zzb2 = zzi2.zzb();
                        zzb2.zzQ(zzcd);
                        zzi2 = zzb2.zzad();
                    }
                    if (zzg2 && zzi2.zzh == -1 && zzi2.zzi == -1 && (i = zzaha.zza) != -1) {
                        zzal zzb3 = zzi2.zzb();
                        zzb3.zzx(i);
                        zzi2 = zzb3.zzad();
                    }
                }
                zzdeArr[i3] = new zzde(Integer.toString(i3), zzi2.zzc(this.zzf.zza(zzi2)));
            }
            this.zzy = new zzwq(new zzxr(zzdeArr), zArr);
            this.zzw = true;
            zzvl zzvl = this.zzr;
            zzvl.getClass();
            zzvl.zzi(this);
        }
    }

    private final void zzV(int i) {
        zzT();
        zzwq zzwq = this.zzy;
        boolean[] zArr = zzwq.zzd;
        if (!zArr[i]) {
            zzan zzb2 = zzwq.zza.zzb(i).zzb(0);
            this.zzg.zzc(new zzvk(1, zzcg.zzb(zzb2.zzn), zzb2, 0, (Object) null, zzgd.zzu(this.zzH), C.TIME_UNSET));
            zArr[i] = true;
        }
    }

    private final void zzW(int i) {
        zzT();
        boolean[] zArr = this.zzy.zzb;
        if (this.zzJ && zArr[i] && !this.zzt[i].zzx(false)) {
            this.zzI = 0;
            this.zzJ = false;
            this.zzE = true;
            this.zzH = 0;
            this.zzK = 0;
            for (zzxe zzp2 : this.zzt) {
                zzp2.zzp(false);
            }
            zzvl zzvl = this.zzr;
            zzvl.getClass();
            zzvl.zzg(this);
        }
    }

    private final void zzX() {
        zzwm zzwm = new zzwm(this, this.zzd, this.zze, this.zzl, this, this.zzm);
        if (this.zzw) {
            zzeq.zzf(zzY());
            long j = this.zzA;
            if (j == C.TIME_UNSET || this.zzI <= j) {
                zzaet zzaet = this.zzz;
                zzaet.getClass();
                zzwm.zzf(zzwm, zzaet.zzg(this.zzI).zza.zzc, this.zzI);
                for (zzxe zzt2 : this.zzt) {
                    zzt2.zzt(this.zzI);
                }
                this.zzI = C.TIME_UNSET;
            } else {
                this.zzL = true;
                this.zzI = C.TIME_UNSET;
                return;
            }
        }
        this.zzK = zzQ();
        long zza = this.zzk.zza(zzwm, this, zzzz.zza(this.zzC));
        zzhh zzd2 = zzwm.zzl;
        this.zzg.zzg(new zzvf(zzwm.zzb, zzd2, zzd2.zza, Collections.emptyMap(), zza, 0, 0), new zzvk(1, -1, (zzan) null, 0, (Object) null, zzgd.zzu(zzwm.zzk), zzgd.zzu(this.zzA)));
    }

    private final boolean zzY() {
        return this.zzI != C.TIME_UNSET;
    }

    private final boolean zzZ() {
        return this.zzE || zzY();
    }

    public final void zzD() {
        this.zzv = true;
        this.zzp.post(this.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzF() {
        this.zzG = true;
    }

    /* access modifiers changed from: package-private */
    public final void zzH() throws IOException {
        this.zzk.zzi(zzzz.zza(this.zzC));
    }

    /* access modifiers changed from: package-private */
    public final void zzI(int i) throws IOException {
        this.zzt[i].zzm();
        zzH();
    }

    public final /* bridge */ /* synthetic */ void zzJ(zzaae zzaae, long j, long j2, boolean z) {
        zzwm zzwm = (zzwm) zzaae;
        zzid zze2 = zzwm.zzd;
        zzvf zzvf = new zzvf(zzwm.zzb, zzwm.zzl, zze2.zzh(), zze2.zzi(), j, j2, zze2.zzg());
        long unused = zzwm.zzb;
        long zzc2 = zzwm.zzk;
        long j3 = this.zzA;
        long zzu2 = zzgd.zzu(zzc2);
        long zzu3 = zzgd.zzu(j3);
        zzvk zzvk = r5;
        zzvk zzvk2 = new zzvk(1, -1, (zzan) null, 0, (Object) null, zzu2, zzu3);
        this.zzg.zzd(zzvf, zzvk);
        if (!z) {
            for (zzxe zzp2 : this.zzt) {
                zzp2.zzp(false);
            }
            if (this.zzF > 0) {
                zzvl zzvl = this.zzr;
                zzvl.getClass();
                zzvl.zzg(this);
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzK(zzaae zzaae, long j, long j2) {
        zzaet zzaet;
        if (this.zzA == C.TIME_UNSET && (zzaet = this.zzz) != null) {
            boolean zzh2 = zzaet.zzh();
            long zzR = zzR(true);
            long j3 = zzR == Long.MIN_VALUE ? 0 : zzR + WorkRequest.MIN_BACKOFF_MILLIS;
            this.zzA = j3;
            this.zzi.zza(j3, zzh2, this.zzB);
        }
        zzwm zzwm = (zzwm) zzaae;
        zzid zze2 = zzwm.zzd;
        long zzb2 = zzwm.zzb;
        zzhh zzd2 = zzwm.zzl;
        Uri zzh3 = zze2.zzh();
        Map zzi2 = zze2.zzi();
        long zzg2 = zze2.zzg();
        zzvf zzvf = r4;
        zzvf zzvf2 = new zzvf(zzb2, zzd2, zzh3, zzi2, j, j2, zzg2);
        long unused = zzwm.zzb;
        this.zzg.zze(zzvf, new zzvk(1, -1, (zzan) null, 0, (Object) null, zzgd.zzu(zzwm.zzk), zzgd.zzu(this.zzA)));
        this.zzL = true;
        zzvl zzvl = this.zzr;
        zzvl.getClass();
        zzvl.zzg(this);
    }

    public final void zzL() {
        for (zzxe zzo2 : this.zzt) {
            zzo2.zzo();
        }
        this.zzl.zze();
    }

    public final void zzM(zzan zzan) {
        this.zzp.post(this.zzn);
    }

    public final void zzN() {
        if (this.zzw) {
            for (zzxe zzn2 : this.zzt) {
                zzn2.zzn();
            }
        }
        this.zzk.zzj(this);
        this.zzp.removeCallbacksAndMessages((Object) null);
        this.zzr = null;
        this.zzM = true;
    }

    public final void zzO(zzaet zzaet) {
        this.zzp.post(new zzwk(this, zzaet));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzP(int i) {
        return !zzZ() && this.zzt[i].zzx(this.zzL);
    }

    public final long zza(long j, zzmr zzmr) {
        long j2 = j;
        zzmr zzmr2 = zzmr;
        zzT();
        if (!this.zzz.zzh()) {
            return 0;
        }
        zzaer zzg2 = this.zzz.zzg(j2);
        zzaeu zzaeu = zzg2.zza;
        zzaeu zzaeu2 = zzg2.zzb;
        long j3 = zzmr2.zzf;
        if (j3 == 0) {
            if (zzmr2.zzg == 0) {
                return j2;
            }
            j3 = 0;
        }
        long j4 = zzaeu.zzb;
        int i = zzgd.zza;
        long j5 = j2 - j3;
        long j6 = zzmr2.zzg;
        long j7 = j2 + j6;
        long j8 = j2 ^ j7;
        long j9 = j6 ^ j7;
        if (((j2 ^ j3) & (j2 ^ j5)) < 0) {
            j5 = Long.MIN_VALUE;
        }
        if ((j8 & j9) < 0) {
            j7 = Long.MAX_VALUE;
        }
        boolean z = true;
        boolean z2 = j5 <= j4 && j4 <= j7;
        long j10 = zzaeu2.zzb;
        if (j5 > j10 || j10 > j7) {
            z = false;
        }
        if (z2 && z) {
            if (Math.abs(j4 - j2) > Math.abs(j10 - j2)) {
                return j10;
            }
        } else if (!z2) {
            return z ? j10 : j5;
        }
        return j4;
    }

    public final long zzb() {
        long j;
        zzT();
        if (this.zzL || this.zzF == 0) {
            return Long.MIN_VALUE;
        }
        if (zzY()) {
            return this.zzI;
        }
        if (this.zzx) {
            int length = this.zzt.length;
            j = Long.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                zzwq zzwq = this.zzy;
                if (zzwq.zzb[i] && zzwq.zzc[i] && !this.zzt[i].zzw()) {
                    j = Math.min(j, this.zzt[i].zzh());
                }
            }
        } else {
            j = Long.MAX_VALUE;
        }
        if (j == Long.MAX_VALUE) {
            j = zzR(false);
        }
        return j == Long.MIN_VALUE ? this.zzH : j;
    }

    public final long zzc() {
        return zzb();
    }

    public final long zzd() {
        if (!this.zzE) {
            return C.TIME_UNSET;
        }
        if (!this.zzL && zzQ() <= this.zzK) {
            return C.TIME_UNSET;
        }
        this.zzE = false;
        return this.zzH;
    }

    public final long zze(long j) {
        boolean z;
        zzT();
        boolean[] zArr = this.zzy.zzb;
        if (true != this.zzz.zzh()) {
            j = 0;
        }
        this.zzE = false;
        this.zzH = j;
        if (zzY()) {
            this.zzI = j;
            return j;
        }
        if (this.zzC != 7) {
            int length = this.zzt.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                zzxe zzxe = this.zzt[i];
                if (this.zzq) {
                    z = zzxe.zzy(zzxe.zza());
                } else {
                    z = zzxe.zzz(j, false);
                }
                if (!z && (zArr[i] || !this.zzx)) {
                    break;
                }
                i++;
            }
            return j;
        }
        this.zzJ = false;
        this.zzI = j;
        this.zzL = false;
        zzaai zzaai = this.zzk;
        if (zzaai.zzl()) {
            for (zzxe zzk2 : this.zzt) {
                zzk2.zzk();
            }
            this.zzk.zzg();
        } else {
            zzaai.zzh();
            for (zzxe zzp2 : this.zzt) {
                zzp2.zzp(false);
            }
        }
        return j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r2 == 0) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzf(com.google.android.gms.internal.ads.zzzg[] r8, boolean[] r9, com.google.android.gms.internal.ads.zzxf[] r10, boolean[] r11, long r12) {
        /*
            r7 = this;
            r7.zzT()
            com.google.android.gms.internal.ads.zzwq r0 = r7.zzy
            com.google.android.gms.internal.ads.zzxr r1 = r0.zza
            boolean[] r0 = r0.zzc
            int r2 = r7.zzF
            r3 = 0
            r4 = r3
        L_0x000d:
            int r5 = r8.length
            if (r4 >= r5) goto L_0x0035
            r5 = r10[r4]
            if (r5 == 0) goto L_0x0032
            r6 = r8[r4]
            if (r6 == 0) goto L_0x001c
            boolean r6 = r9[r4]
            if (r6 != 0) goto L_0x0032
        L_0x001c:
            com.google.android.gms.internal.ads.zzwo r5 = (com.google.android.gms.internal.ads.zzwo) r5
            int r5 = r5.zzb
            boolean r6 = r0[r5]
            com.google.android.gms.internal.ads.zzeq.zzf(r6)
            int r6 = r7.zzF
            int r6 = r6 + -1
            r7.zzF = r6
            r0[r5] = r3
            r5 = 0
            r10[r4] = r5
        L_0x0032:
            int r4 = r4 + 1
            goto L_0x000d
        L_0x0035:
            boolean r9 = r7.zzq
            r4 = 1
            if (r9 != 0) goto L_0x004c
            boolean r9 = r7.zzD
            if (r9 == 0) goto L_0x0041
            if (r2 != 0) goto L_0x004c
            goto L_0x0047
        L_0x0041:
            r5 = 0
            int r9 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r9 == 0) goto L_0x0049
        L_0x0047:
            r9 = r4
            goto L_0x004d
        L_0x0049:
            r9 = r3
            r12 = r5
            goto L_0x004d
        L_0x004c:
            r9 = r3
        L_0x004d:
            r2 = r3
        L_0x004e:
            int r5 = r8.length
            if (r2 >= r5) goto L_0x00a7
            r5 = r10[r2]
            if (r5 != 0) goto L_0x00a4
            r5 = r8[r2]
            if (r5 == 0) goto L_0x00a4
            int r6 = r5.zzc()
            if (r6 != r4) goto L_0x0061
            r6 = r4
            goto L_0x0062
        L_0x0061:
            r6 = r3
        L_0x0062:
            com.google.android.gms.internal.ads.zzeq.zzf(r6)
            int r6 = r5.zza(r3)
            if (r6 != 0) goto L_0x006d
            r6 = r4
            goto L_0x006e
        L_0x006d:
            r6 = r3
        L_0x006e:
            com.google.android.gms.internal.ads.zzeq.zzf(r6)
            com.google.android.gms.internal.ads.zzde r5 = r5.zze()
            int r5 = r1.zza(r5)
            boolean r6 = r0[r5]
            r6 = r6 ^ r4
            com.google.android.gms.internal.ads.zzeq.zzf(r6)
            int r6 = r7.zzF
            int r6 = r6 + r4
            r7.zzF = r6
            r0[r5] = r4
            com.google.android.gms.internal.ads.zzwo r6 = new com.google.android.gms.internal.ads.zzwo
            r6.<init>(r7, r5)
            r10[r2] = r6
            r11[r2] = r4
            if (r9 != 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzxe[] r9 = r7.zzt
            r9 = r9[r5]
            int r5 = r9.zzb()
            if (r5 == 0) goto L_0x00a3
            boolean r9 = r9.zzz(r12, r4)
            if (r9 != 0) goto L_0x00a3
            r9 = r4
            goto L_0x00a4
        L_0x00a3:
            r9 = r3
        L_0x00a4:
            int r2 = r2 + 1
            goto L_0x004e
        L_0x00a7:
            int r8 = r7.zzF
            if (r8 != 0) goto L_0x00d8
            r7.zzJ = r3
            r7.zzE = r3
            com.google.android.gms.internal.ads.zzaai r8 = r7.zzk
            boolean r8 = r8.zzl()
            if (r8 == 0) goto L_0x00ca
            com.google.android.gms.internal.ads.zzxe[] r8 = r7.zzt
            int r9 = r8.length
        L_0x00ba:
            if (r3 >= r9) goto L_0x00c4
            r10 = r8[r3]
            r10.zzk()
            int r3 = r3 + 1
            goto L_0x00ba
        L_0x00c4:
            com.google.android.gms.internal.ads.zzaai r8 = r7.zzk
            r8.zzg()
            goto L_0x00ea
        L_0x00ca:
            com.google.android.gms.internal.ads.zzxe[] r8 = r7.zzt
            int r9 = r8.length
            r10 = r3
        L_0x00ce:
            if (r10 >= r9) goto L_0x00ea
            r11 = r8[r10]
            r11.zzp(r3)
            int r10 = r10 + 1
            goto L_0x00ce
        L_0x00d8:
            if (r9 == 0) goto L_0x00ea
            long r12 = r7.zze(r12)
        L_0x00de:
            int r8 = r10.length
            if (r3 >= r8) goto L_0x00ea
            r8 = r10[r3]
            if (r8 == 0) goto L_0x00e7
            r11[r3] = r4
        L_0x00e7:
            int r3 = r3 + 1
            goto L_0x00de
        L_0x00ea:
            r7.zzD = r4
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwr.zzf(com.google.android.gms.internal.ads.zzzg[], boolean[], com.google.android.gms.internal.ads.zzxf[], boolean[], long):long");
    }

    /* access modifiers changed from: package-private */
    public final int zzg(int i, zzlj zzlj, zzin zzin, int i2) {
        if (zzZ()) {
            return -3;
        }
        zzV(i);
        int zze2 = this.zzt[i].zze(zzlj, zzin, i2, this.zzL);
        if (zze2 == -3) {
            zzW(i);
        }
        return zze2;
    }

    public final zzxr zzh() {
        zzT();
        return this.zzy.zza;
    }

    /* access modifiers changed from: package-private */
    public final int zzi(int i, long j) {
        if (zzZ()) {
            return 0;
        }
        zzV(i);
        zzxe zzxe = this.zzt[i];
        int zzc2 = zzxe.zzc(j, this.zzL);
        zzxe.zzv(zzc2);
        if (zzc2 != 0) {
            return zzc2;
        }
        zzW(i);
        return 0;
    }

    public final void zzj(long j, boolean z) {
        if (!this.zzq) {
            zzT();
            if (!zzY()) {
                boolean[] zArr = this.zzy.zzc;
                int length = this.zzt.length;
                for (int i = 0; i < length; i++) {
                    this.zzt[i].zzj(j, false, zArr[i]);
                }
            }
        }
    }

    public final void zzk() throws IOException {
        zzH();
        if (this.zzL && !this.zzw) {
            throw zzch.zza("Loading finished before preparation is complete.", (Throwable) null);
        }
    }

    public final void zzl(zzvl zzvl, long j) {
        this.zzr = zzvl;
        this.zzm.zze();
        zzX();
    }

    public final void zzm(long j) {
    }

    public final boolean zzo(zzlo zzlo) {
        if (this.zzL) {
            return false;
        }
        zzaai zzaai = this.zzk;
        if (zzaai.zzk() || this.zzJ) {
            return false;
        }
        if (this.zzw && this.zzF == 0) {
            return false;
        }
        boolean zze2 = this.zzm.zze();
        if (zzaai.zzl()) {
            return zze2;
        }
        zzX();
        return true;
    }

    public final boolean zzp() {
        return this.zzk.zzl() && this.zzm.zzd();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.ads.zzaac zzu(com.google.android.gms.internal.ads.zzaae r23, long r24, long r26, java.io.IOException r28, int r29) {
        /*
            r22 = this;
            r0 = r22
            r1 = r28
            r2 = r23
            com.google.android.gms.internal.ads.zzwm r2 = (com.google.android.gms.internal.ads.zzwm) r2
            com.google.android.gms.internal.ads.zzid r3 = r2.zzd
            com.google.android.gms.internal.ads.zzvf r14 = new com.google.android.gms.internal.ads.zzvf
            long r5 = r2.zzb
            com.google.android.gms.internal.ads.zzhh r7 = r2.zzl
            android.net.Uri r8 = r3.zzh()
            java.util.Map r9 = r3.zzi()
            long r15 = r3.zzg()
            r4 = r14
            r10 = r24
            r12 = r26
            r3 = r14
            r14 = r15
            r4.<init>(r5, r7, r8, r9, r10, r12, r14)
            long unused = r2.zzk
            int r4 = com.google.android.gms.internal.ads.zzgd.zza
            boolean r4 = r1 instanceof com.google.android.gms.internal.ads.zzch
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 != 0) goto L_0x0068
            boolean r4 = r1 instanceof java.io.FileNotFoundException
            if (r4 != 0) goto L_0x0068
            boolean r4 = r1 instanceof com.google.android.gms.internal.ads.zzhu
            if (r4 != 0) goto L_0x0068
            boolean r4 = r1 instanceof com.google.android.gms.internal.ads.zzaah
            if (r4 != 0) goto L_0x0068
            r4 = r1
        L_0x0047:
            if (r4 == 0) goto L_0x005c
            boolean r7 = r4 instanceof com.google.android.gms.internal.ads.zzhc
            if (r7 == 0) goto L_0x0057
            r7 = r4
            com.google.android.gms.internal.ads.zzhc r7 = (com.google.android.gms.internal.ads.zzhc) r7
            int r7 = r7.zza
            r8 = 2008(0x7d8, float:2.814E-42)
            if (r7 != r8) goto L_0x0057
            goto L_0x0068
        L_0x0057:
            java.lang.Throwable r4 = r4.getCause()
            goto L_0x0047
        L_0x005c:
            int r4 = r29 + -1
            int r4 = r4 * 1000
            r7 = 5000(0x1388, float:7.006E-42)
            int r4 = java.lang.Math.min(r4, r7)
            long r7 = (long) r4
            goto L_0x0069
        L_0x0068:
            r7 = r5
        L_0x0069:
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0070
            com.google.android.gms.internal.ads.zzaac r4 = com.google.android.gms.internal.ads.zzaai.zzd
            goto L_0x00bd
        L_0x0070:
            int r4 = r22.zzQ()
            int r9 = r0.zzK
            r10 = 1
            r11 = 0
            if (r4 <= r9) goto L_0x007c
            r9 = r10
            goto L_0x007d
        L_0x007c:
            r9 = r11
        L_0x007d:
            boolean r12 = r0.zzG
            if (r12 != 0) goto L_0x00b7
            com.google.android.gms.internal.ads.zzaet r12 = r0.zzz
            if (r12 == 0) goto L_0x008e
            long r12 = r12.zza()
            int r5 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r5 == 0) goto L_0x008e
            goto L_0x00b7
        L_0x008e:
            boolean r4 = r0.zzw
            if (r4 == 0) goto L_0x009d
            boolean r5 = r22.zzZ()
            if (r5 != 0) goto L_0x009d
            r0.zzJ = r10
            com.google.android.gms.internal.ads.zzaac r4 = com.google.android.gms.internal.ads.zzaai.zzc
            goto L_0x00bd
        L_0x009d:
            r0.zzE = r4
            r4 = 0
            r0.zzH = r4
            r0.zzK = r11
            com.google.android.gms.internal.ads.zzxe[] r6 = r0.zzt
            int r10 = r6.length
            r12 = r11
        L_0x00a9:
            if (r12 >= r10) goto L_0x00b3
            r13 = r6[r12]
            r13.zzp(r11)
            int r12 = r12 + 1
            goto L_0x00a9
        L_0x00b3:
            com.google.android.gms.internal.ads.zzwm.zzf(r2, r4, r4)
            goto L_0x00b9
        L_0x00b7:
            r0.zzK = r4
        L_0x00b9:
            com.google.android.gms.internal.ads.zzaac r4 = com.google.android.gms.internal.ads.zzaai.zzb(r9, r7)
        L_0x00bd:
            boolean r5 = r4.zzc()
            r6 = r5 ^ 1
            com.google.android.gms.internal.ads.zzvx r7 = r0.zzg
            long r8 = r2.zzk
            long r10 = r0.zzA
            long r18 = com.google.android.gms.internal.ads.zzgd.zzu(r8)
            long r20 = com.google.android.gms.internal.ads.zzgd.zzu(r10)
            com.google.android.gms.internal.ads.zzvk r8 = new com.google.android.gms.internal.ads.zzvk
            r16 = 0
            r17 = 0
            r13 = 1
            r14 = -1
            r15 = 0
            r12 = r8
            r12.<init>(r13, r14, r15, r16, r17, r18, r20)
            r7.zzf(r3, r8, r1, r6)
            if (r5 != 0) goto L_0x00e8
            long unused = r2.zzb
        L_0x00e8:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwr.zzu(com.google.android.gms.internal.ads.zzaae, long, long, java.io.IOException, int):com.google.android.gms.internal.ads.zzaac");
    }

    /* access modifiers changed from: package-private */
    public final zzafa zzv() {
        return zzS(new zzwp(0, true));
    }

    public final zzafa zzw(int i, int i2) {
        return zzS(new zzwp(i, false));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzE() {
        if (!this.zzM) {
            zzvl zzvl = this.zzr;
            zzvl.getClass();
            zzvl.zzg(this);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzG(zzaet zzaet) {
        zzaet zzaet2;
        if (this.zzs == null) {
            zzaet2 = zzaet;
        } else {
            zzaet2 = new zzaes(C.TIME_UNSET, 0);
        }
        this.zzz = zzaet2;
        if (zzaet.zza() == C.TIME_UNSET && this.zzA != C.TIME_UNSET) {
            this.zzz = new zzwl(this, this.zzz);
        }
        this.zzA = this.zzz.zza();
        boolean z = false;
        int i = 1;
        if (!this.zzG && zzaet.zza() == C.TIME_UNSET) {
            z = true;
        }
        this.zzB = z;
        if (true == z) {
            i = 7;
        }
        this.zzC = i;
        this.zzi.zza(this.zzA, zzaet.zzh(), this.zzB);
        if (!this.zzw) {
            zzU();
        }
    }
}

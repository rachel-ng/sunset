package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzkw extends zzm implements zzjr {
    public static final /* synthetic */ int zzd = 0;
    private final zzmw zzA;
    private final long zzB;
    private int zzC;
    private int zzD;
    private boolean zzE;
    private int zzF;
    private zzmr zzG;
    private zzcp zzH;
    private zzca zzI;
    private zzca zzJ;
    /* access modifiers changed from: private */
    public zzan zzK;
    /* access modifiers changed from: private */
    public zzan zzL;
    /* access modifiers changed from: private */
    public Object zzM;
    private Surface zzN;
    private int zzO;
    private zzfv zzP;
    /* access modifiers changed from: private */
    public zzix zzQ;
    /* access modifiers changed from: private */
    public zzix zzR;
    private int zzS;
    private zzk zzT;
    private float zzU;
    /* access modifiers changed from: private */
    public boolean zzV;
    private zzek zzW;
    private boolean zzX;
    private boolean zzY;
    private zzaa zzZ;
    /* access modifiers changed from: private */
    public zzdv zzaa;
    private zzca zzab;
    private zzmg zzac;
    private int zzad;
    private long zzae;
    private final zzjs zzaf;
    private zzxi zzag;
    final zzzn zzb;
    final zzcp zzc;
    private final zzeu zze;
    private final Context zzf;
    private final zzct zzg;
    private final zzmn[] zzh;
    private final zzzm zzi;
    private final zzfb zzj;
    private final zzlh zzk;
    /* access modifiers changed from: private */
    public final zzfh zzl;
    private final CopyOnWriteArraySet zzm;
    private final zzcz zzn;
    private final List zzo;
    private final boolean zzp;
    private final zzvn zzq;
    /* access modifiers changed from: private */
    public final zzmx zzr;
    private final Looper zzs;
    private final zzzu zzt;
    private final zzer zzu;
    private final zzks zzv;
    private final zzku zzw;
    private final zzir zzx;
    private final zziv zzy;
    private final zzmv zzz;

    static {
        zzbv.zzb("media3.exoplayer");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [com.google.android.gms.internal.ads.zzmx, com.google.android.gms.internal.ads.zzzt, java.lang.Object] */
    public zzkw(zzjq zzjq, zzct zzct) {
        zzpj zza;
        zzjq zzjq2 = zzjq;
        zzct zzct2 = zzct;
        zzeu zzeu = new zzeu(zzer.zza);
        this.zze = zzeu;
        try {
            String hexString = Integer.toHexString(System.identityHashCode(this));
            String str = zzgd.zze;
            zzfk.zze("ExoPlayerImpl", "Init " + hexString + " [AndroidXMedia3/1.4.0-alpha01] [" + str + "]");
            Context applicationContext = zzjq2.zza.getApplicationContext();
            this.zzf = applicationContext;
            ? apply = zzjq2.zzh.apply(zzjq2.zzb);
            this.zzr = apply;
            this.zzT = zzjq2.zzj;
            this.zzO = zzjq2.zzk;
            this.zzV = false;
            this.zzB = zzjq2.zzo;
            zzks zzks = new zzks(this, (zzkr) null);
            this.zzv = zzks;
            zzku zzku = new zzku((zzkt) null);
            this.zzw = zzku;
            Handler handler = new Handler(zzjq2.zzi);
            Handler handler2 = handler;
            zzmn[] zza2 = ((zzjj) zzjq2.zzc).zza.zza(handler, zzks, zzks, zzks, zzks);
            this.zzh = zza2;
            int length = zza2.length;
            zzzm zzzm = (zzzm) zzjq2.zze.zza();
            this.zzi = zzzm;
            this.zzq = zzjq.zza(((zzjk) zzjq2.zzd).zza);
            zzzy zzg2 = zzzy.zzg(((zzjn) zzjq2.zzg).zza);
            this.zzt = zzg2;
            this.zzp = zzjq2.zzl;
            this.zzG = zzjq2.zzm;
            Looper looper = zzjq2.zzi;
            this.zzs = looper;
            zzer zzer = zzjq2.zzb;
            this.zzu = zzer;
            this.zzg = zzct2;
            zzfh zzfh = new zzfh(looper, zzer, new zzkm(this));
            this.zzl = zzfh;
            CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
            this.zzm = copyOnWriteArraySet;
            this.zzo = new ArrayList();
            zzku zzku2 = zzku;
            this.zzag = new zzxi(0);
            int length2 = zza2.length;
            CopyOnWriteArraySet copyOnWriteArraySet2 = copyOnWriteArraySet;
            zzzy zzzy = zzg2;
            zzfh zzfh2 = zzfh;
            zzzn zzzn = new zzzn(new zzmq[2], new zzzg[2], zzdp.zza, (Object) null);
            this.zzb = zzzn;
            this.zzn = new zzcz();
            zzcn zzcn = new zzcn();
            zzcn.zzc(1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32);
            zzzm.zzn();
            zzcn.zzd(29, true);
            zzcn.zzd(23, false);
            zzcn.zzd(25, false);
            zzcn.zzd(33, false);
            zzcn.zzd(26, false);
            zzcn.zzd(34, false);
            zzcp zze2 = zzcn.zze();
            this.zzc = zze2;
            zzcn zzcn2 = new zzcn();
            zzcn2.zzb(zze2);
            zzcn2.zza(4);
            zzcn2.zza(10);
            this.zzH = zzcn2.zze();
            this.zzj = zzer.zzb(looper, (Handler.Callback) null);
            zzjs zzjs = new zzjs(this);
            this.zzaf = zzjs;
            this.zzac = zzmg.zzg(zzzn);
            apply.zzR(zzct2, looper);
            if (zzgd.zza < 31) {
                zza = new zzpj(zzjq2.zzr);
            } else {
                zza = zzkn.zza(applicationContext, this, zzjq2.zzp, zzjq2.zzr);
            }
            zzpj zzpj = zza;
            zzmr zzmr = this.zzG;
            zzfh zzfh3 = zzfh2;
            zzja zzja = zzjq2.zzs;
            zzmr zzmr2 = zzmr;
            CopyOnWriteArraySet copyOnWriteArraySet3 = copyOnWriteArraySet2;
            Looper looper2 = looper;
            zzzn zzzn2 = zzzn;
            zzzy zzzy2 = zzzy;
            zzzm zzzm2 = zzzm;
            zzku zzku3 = zzku2;
            zzks zzks2 = zzks;
            this.zzk = new zzlh(zza2, zzzm, zzzn2, (zzlk) zzjq2.zzf.zza(), zzzy2, 0, false, apply, zzmr2, zzja, zzjq2.zzn, false, looper2, zzer, zzjs, zzpj, (Looper) null);
            this.zzU = 1.0f;
            this.zzI = zzca.zza;
            zzca zzca = zzca.zza;
            this.zzJ = zzca;
            this.zzab = zzca;
            int i = -1;
            this.zzad = -1;
            AudioManager audioManager = (AudioManager) applicationContext.getSystemService("audio");
            if (audioManager != null) {
                i = audioManager.generateAudioSessionId();
            }
            this.zzS = i;
            this.zzW = zzek.zza;
            this.zzX = true;
            if (apply != 0) {
                zzfh3.zzb(apply);
                zzzy2.zze(new Handler(looper2), apply);
                zzks zzks3 = zzks2;
                copyOnWriteArraySet3.add(zzks3);
                Handler handler3 = handler2;
                this.zzx = new zzir(zzjq2.zza, handler3, zzks3);
                this.zzy = new zziv(zzjq2.zza, handler3, zzks3);
                zzgd.zzG((Object) null, (Object) null);
                this.zzz = new zzmv(zzjq2.zza);
                this.zzA = new zzmw(zzjq2.zza);
                this.zzZ = new zzy(0).zza();
                this.zzaa = zzdv.zza;
                this.zzP = zzfv.zza;
                zzzm2.zzk(this.zzT);
                zzag(1, 10, Integer.valueOf(this.zzS));
                zzag(2, 10, Integer.valueOf(this.zzS));
                zzag(1, 3, this.zzT);
                zzag(2, 4, Integer.valueOf(this.zzO));
                zzag(2, 5, 0);
                zzag(1, 9, Boolean.valueOf(this.zzV));
                zzku zzku4 = zzku3;
                zzag(2, 7, zzku4);
                zzag(6, 8, zzku4);
                zzeu.zze();
                return;
            }
            throw null;
        } catch (Throwable th) {
            this.zze.zze();
            throw th;
        }
    }

    static /* bridge */ /* synthetic */ void zzP(zzkw zzkw, SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        zzkw.zzai(surface);
        zzkw.zzN = surface;
    }

    private final int zzW(zzmg zzmg) {
        if (zzmg.zza.zzo()) {
            return this.zzad;
        }
        return zzmg.zza.zzn(zzmg.zzb.zza, this.zzn).zzd;
    }

    /* access modifiers changed from: private */
    public static int zzX(boolean z, int i) {
        return (!z || i == 1) ? 1 : 2;
    }

    private final long zzY(zzmg zzmg) {
        if (!zzmg.zzb.zzb()) {
            return zzgd.zzu(zzZ(zzmg));
        }
        zzmg.zza.zzn(zzmg.zzb.zza, this.zzn);
        if (zzmg.zzc == C.TIME_UNSET) {
            long j = zzmg.zza.zze(zzW(zzmg), this.zza, 0).zzn;
            return zzgd.zzu(0);
        }
        int i = zzgd.zza;
        return zzgd.zzu(0) + zzgd.zzu(zzmg.zzc);
    }

    private final long zzZ(zzmg zzmg) {
        if (zzmg.zza.zzo()) {
            return zzgd.zzr(this.zzae);
        }
        boolean z = zzmg.zzo;
        long j = zzmg.zzr;
        if (zzmg.zzb.zzb()) {
            return j;
        }
        zzab(zzmg.zza, zzmg.zzb, j);
        return j;
    }

    private static long zzaa(zzmg zzmg) {
        zzdb zzdb = new zzdb();
        zzcz zzcz = new zzcz();
        zzmg.zza.zzn(zzmg.zzb.zza, zzcz);
        long j = zzmg.zzc;
        if (j != C.TIME_UNSET) {
            return j;
        }
        long j2 = zzmg.zza.zze(zzcz.zzd, zzdb, 0).zzn;
        return 0;
    }

    private final long zzab(zzdc zzdc, zzvo zzvo, long j) {
        zzdc.zzn(zzvo.zza, this.zzn);
        return j;
    }

    private final Pair zzac(zzdc zzdc, int i, long j) {
        if (zzdc.zzo()) {
            this.zzad = i;
            if (j == C.TIME_UNSET) {
                j = 0;
            }
            this.zzae = j;
            return null;
        }
        if (i == -1 || i >= zzdc.zzc()) {
            i = zzdc.zzg(false);
            long j2 = zzdc.zze(i, this.zza, 0).zzn;
            j = zzgd.zzu(0);
        }
        return zzdc.zzl(this.zza, this.zzn, i, zzgd.zzr(j));
    }

    private final zzmg zzad(zzmg zzmg, zzdc zzdc, Pair pair) {
        zzvo zzvo;
        zzxr zzxr;
        zzzn zzzn;
        List list;
        int i;
        long j;
        zzdc zzdc2 = zzdc;
        Pair pair2 = pair;
        zzeq.zzd(zzdc.zzo() || pair2 != null);
        zzdc zzdc3 = zzmg.zza;
        long zzY2 = zzY(zzmg);
        zzmg zzf2 = zzmg.zzf(zzdc);
        if (zzdc.zzo()) {
            zzvo zzh2 = zzmg.zzh();
            long zzr2 = zzgd.zzr(this.zzae);
            zzmg zza = zzf2.zzb(zzh2, zzr2, zzr2, zzr2, 0, zzxr.zza, this.zzb, zzgbc.zzm()).zza(zzh2);
            zza.zzp = zza.zzr;
            return zza;
        }
        Object obj = zzf2.zzb.zza;
        int i2 = zzgd.zza;
        boolean equals = obj.equals(pair2.first);
        if (!equals) {
            zzvo = new zzvo(pair2.first, -1);
        } else {
            zzvo = zzf2.zzb;
        }
        zzvo zzvo2 = zzvo;
        long longValue = ((Long) pair2.second).longValue();
        long zzr3 = zzgd.zzr(zzY2);
        if (!zzdc3.zzo()) {
            zzdc3.zzn(obj, this.zzn);
        }
        if (!equals || longValue < zzr3) {
            long j2 = longValue;
            zzvo zzvo3 = zzvo2;
            zzeq.zzf(!zzvo3.zzb());
            if (!equals) {
                zzxr = zzxr.zza;
            } else {
                zzxr = zzf2.zzh;
            }
            zzxr zzxr2 = zzxr;
            if (!equals) {
                zzzn = this.zzb;
            } else {
                zzzn = zzf2.zzi;
            }
            zzzn zzzn2 = zzzn;
            if (!equals) {
                list = zzgbc.zzm();
            } else {
                list = zzf2.zzj;
            }
            zzmg zza2 = zzf2.zzb(zzvo3, j2, j2, j2, 0, zzxr2, zzzn2, list).zza(zzvo3);
            zza2.zzp = j2;
            return zza2;
        } else if (i == 0) {
            int zza3 = zzdc2.zza(zzf2.zzk.zza);
            if (zza3 != -1 && zzdc2.zzd(zza3, this.zzn, false).zzd == zzdc2.zzn(zzvo2.zza, this.zzn).zzd) {
                return zzf2;
            }
            zzdc2.zzn(zzvo2.zza, this.zzn);
            if (zzvo2.zzb()) {
                j = this.zzn.zzh(zzvo2.zzb, zzvo2.zzc);
            } else {
                j = this.zzn.zze;
            }
            zzmg zza4 = zzf2.zzb(zzvo2, zzf2.zzr, zzf2.zzr, zzf2.zzd, j - zzf2.zzr, zzf2.zzh, zzf2.zzi, zzf2.zzj).zza(zzvo2);
            zza4.zzp = j;
            return zza4;
        } else {
            zzvo zzvo4 = zzvo2;
            zzeq.zzf(!zzvo4.zzb());
            long max = Math.max(0, zzf2.zzq - (longValue - zzr3));
            long j3 = zzf2.zzp;
            if (zzf2.zzk.equals(zzf2.zzb)) {
                j3 = longValue + max;
            }
            zzmg zzb2 = zzf2.zzb(zzvo4, longValue, longValue, longValue, max, zzf2.zzh, zzf2.zzi, zzf2.zzj);
            zzb2.zzp = j3;
            return zzb2;
        }
    }

    private final zzmj zzae(zzmi zzmi) {
        int zzW2 = zzW(this.zzac);
        zzdc zzdc = this.zzac.zza;
        if (zzW2 == -1) {
            zzW2 = 0;
        }
        zzer zzer = this.zzu;
        zzlh zzlh = this.zzk;
        return new zzmj(zzlh, zzmi, zzdc, zzW2, zzer, zzlh.zzb());
    }

    /* access modifiers changed from: private */
    public final void zzaf(int i, int i2) {
        if (i != this.zzP.zzb() || i2 != this.zzP.zza()) {
            this.zzP = new zzfv(i, i2);
            zzfh zzfh = this.zzl;
            zzfh.zzd(24, new zzkj(i, i2));
            zzfh.zzc();
            zzag(2, 14, new zzfv(i, i2));
        }
    }

    private final void zzag(int i, int i2, Object obj) {
        zzmn[] zzmnArr = this.zzh;
        int length = zzmnArr.length;
        for (int i3 = 0; i3 < 2; i3++) {
            zzmn zzmn = zzmnArr[i3];
            if (zzmn.zzb() == i) {
                zzmj zzae2 = zzae(zzmn);
                zzae2.zzf(i2);
                zzae2.zze(obj);
                zzae2.zzd();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzah() {
        zzag(1, 2, Float.valueOf(this.zzU * this.zzy.zza()));
    }

    /* access modifiers changed from: private */
    public final void zzai(Object obj) {
        ArrayList<zzmj> arrayList = new ArrayList<>();
        zzmn[] zzmnArr = this.zzh;
        int length = zzmnArr.length;
        boolean z = false;
        for (int i = 0; i < 2; i++) {
            zzmn zzmn = zzmnArr[i];
            if (zzmn.zzb() == 2) {
                zzmj zzae2 = zzae(zzmn);
                zzae2.zzf(1);
                zzae2.zze(obj);
                zzae2.zzd();
                arrayList.add(zzae2);
            }
        }
        Object obj2 = this.zzM;
        if (!(obj2 == null || obj2 == obj)) {
            try {
                for (zzmj zzi2 : arrayList) {
                    zzi2.zzi(this.zzB);
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException unused2) {
                z = true;
            }
            Object obj3 = this.zzM;
            Surface surface = this.zzN;
            if (obj3 == surface) {
                surface.release();
                this.zzN = null;
            }
        }
        this.zzM = obj;
        if (z) {
            zzaj(zzjh.zzd(new zzli(3), 1003));
        }
    }

    private final void zzaj(zzjh zzjh) {
        zzmg zzmg = this.zzac;
        zzmg zza = zzmg.zza(zzmg.zzb);
        zza.zzp = zza.zzr;
        zza.zzq = 0;
        zzmg zze2 = zza.zze(1);
        if (zzjh != null) {
            zze2 = zze2.zzd(zzjh);
        }
        this.zzC++;
        this.zzk.zzo();
        zzal(zze2, 0, 1, false, 5, C.TIME_UNSET, -1, false);
    }

    /* access modifiers changed from: private */
    public final void zzak(boolean z, int i, int i2) {
        int i3 = 0;
        boolean z2 = z && i != -1;
        if (z2 && i != 1) {
            i3 = 1;
        }
        zzmg zzmg = this.zzac;
        if (zzmg.zzl != z2 || zzmg.zzm != i3) {
            this.zzC++;
            boolean z3 = zzmg.zzo;
            zzmg zzc2 = zzmg.zzc(z2, i3);
            this.zzk.zzn(z2, i3);
            zzal(zzc2, 0, i2, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x02e6  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x02f2  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02fb  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x032d  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x038f  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x03a5  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x03c8  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x03dc  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x03e9  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0440  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x045b  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x045d  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0475 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x047f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x048a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x049b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x04a7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x04be A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x04ca A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x04e4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzal(com.google.android.gms.internal.ads.zzmg r44, int r45, int r46, boolean r47, int r48, long r49, int r51, boolean r52) {
        /*
            r43 = this;
            r0 = r43
            r1 = r44
            r2 = r48
            com.google.android.gms.internal.ads.zzmg r3 = r0.zzac
            r0.zzac = r1
            com.google.android.gms.internal.ads.zzdc r4 = r3.zza
            com.google.android.gms.internal.ads.zzdc r5 = r1.zza
            boolean r4 = r4.equals(r5)
            com.google.android.gms.internal.ads.zzdc r5 = r3.zza
            com.google.android.gms.internal.ads.zzdc r6 = r1.zza
            boolean r7 = r6.zzo()
            r9 = 3
            r10 = -1
            java.lang.Integer r11 = java.lang.Integer.valueOf(r10)
            r12 = 0
            r14 = 1
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r14)
            r8 = 0
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r8)
            if (r7 == 0) goto L_0x003a
            boolean r7 = r5.zzo()
            if (r7 == 0) goto L_0x003a
            android.util.Pair r5 = new android.util.Pair
            r5.<init>(r10, r11)
            goto L_0x004d
        L_0x003a:
            boolean r7 = r6.zzo()
            boolean r8 = r5.zzo()
            if (r7 == r8) goto L_0x0051
            android.util.Pair r5 = new android.util.Pair
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)
            r5.<init>(r15, r6)
        L_0x004d:
            r6 = r47
            goto L_0x00d7
        L_0x0051:
            com.google.android.gms.internal.ads.zzvo r7 = r3.zzb
            java.lang.Object r7 = r7.zza
            com.google.android.gms.internal.ads.zzcz r8 = r0.zzn
            com.google.android.gms.internal.ads.zzcz r7 = r5.zzn(r7, r8)
            int r7 = r7.zzd
            com.google.android.gms.internal.ads.zzdb r8 = r0.zza
            com.google.android.gms.internal.ads.zzdb r5 = r5.zze(r7, r8, r12)
            java.lang.Object r5 = r5.zzc
            com.google.android.gms.internal.ads.zzvo r7 = r1.zzb
            java.lang.Object r7 = r7.zza
            com.google.android.gms.internal.ads.zzcz r8 = r0.zzn
            com.google.android.gms.internal.ads.zzcz r7 = r6.zzn(r7, r8)
            int r7 = r7.zzd
            com.google.android.gms.internal.ads.zzdb r8 = r0.zza
            com.google.android.gms.internal.ads.zzdb r6 = r6.zze(r7, r8, r12)
            java.lang.Object r6 = r6.zzc
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x00a7
            if (r47 == 0) goto L_0x008a
            if (r2 != 0) goto L_0x0087
            r5 = r14
            r6 = r5
            r2 = 0
            goto L_0x0096
        L_0x0087:
            r5 = r14
            r6 = r5
            goto L_0x008c
        L_0x008a:
            r5 = 0
            r6 = 0
        L_0x008c:
            if (r5 == 0) goto L_0x0092
            if (r2 != r14) goto L_0x0092
            r5 = 2
            goto L_0x0096
        L_0x0092:
            if (r4 != 0) goto L_0x00a1
            r6 = r5
            r5 = r9
        L_0x0096:
            android.util.Pair r7 = new android.util.Pair
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r7.<init>(r15, r5)
            r5 = r7
            goto L_0x00d7
        L_0x00a1:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x00a7:
            if (r47 == 0) goto L_0x00ca
            if (r2 != 0) goto L_0x00c7
            com.google.android.gms.internal.ads.zzvo r2 = r3.zzb
            long r5 = r2.zzd
            com.google.android.gms.internal.ads.zzvo r2 = r1.zzb
            long r7 = r2.zzd
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c4
            android.util.Pair r5 = new android.util.Pair
            r2 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r5.<init>(r15, r6)
            r6 = r14
            r2 = 0
            goto L_0x00d7
        L_0x00c4:
            r2 = r14
            r5 = 0
            goto L_0x00cc
        L_0x00c7:
            r5 = r2
            r2 = r14
            goto L_0x00cc
        L_0x00ca:
            r5 = r2
            r2 = 0
        L_0x00cc:
            android.util.Pair r6 = new android.util.Pair
            r6.<init>(r10, r11)
            r42 = r6
            r6 = r2
            r2 = r5
            r5 = r42
        L_0x00d7:
            java.lang.Object r7 = r5.first
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            java.lang.Object r5 = r5.second
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            if (r7 == 0) goto L_0x0110
            com.google.android.gms.internal.ads.zzdc r10 = r1.zza
            boolean r10 = r10.zzo()
            if (r10 != 0) goto L_0x010a
            com.google.android.gms.internal.ads.zzdc r10 = r1.zza
            com.google.android.gms.internal.ads.zzvo r11 = r1.zzb
            java.lang.Object r11 = r11.zza
            com.google.android.gms.internal.ads.zzcz r15 = r0.zzn
            com.google.android.gms.internal.ads.zzcz r10 = r10.zzn(r11, r15)
            int r10 = r10.zzd
            com.google.android.gms.internal.ads.zzdc r11 = r1.zza
            com.google.android.gms.internal.ads.zzdb r15 = r0.zza
            com.google.android.gms.internal.ads.zzdb r10 = r11.zze(r10, r15, r12)
            com.google.android.gms.internal.ads.zzbu r10 = r10.zze
            goto L_0x010b
        L_0x010a:
            r10 = 0
        L_0x010b:
            com.google.android.gms.internal.ads.zzca r11 = com.google.android.gms.internal.ads.zzca.zza
            r0.zzab = r11
            goto L_0x0111
        L_0x0110:
            r10 = 0
        L_0x0111:
            if (r7 != 0) goto L_0x011d
            java.util.List r11 = r3.zzj
            java.util.List r15 = r1.zzj
            boolean r11 = r11.equals(r15)
            if (r11 != 0) goto L_0x014f
        L_0x011d:
            com.google.android.gms.internal.ads.zzca r11 = r0.zzab
            com.google.android.gms.internal.ads.zzby r11 = r11.zza()
            java.util.List r15 = r1.zzj
            r8 = 0
        L_0x0126:
            int r9 = r15.size()
            if (r8 >= r9) goto L_0x0149
            java.lang.Object r9 = r15.get(r8)
            com.google.android.gms.internal.ads.zzcd r9 = (com.google.android.gms.internal.ads.zzcd) r9
            r14 = 0
        L_0x0133:
            int r12 = r9.zza()
            if (r14 >= r12) goto L_0x0143
            com.google.android.gms.internal.ads.zzcc r12 = r9.zzb(r14)
            r12.zza(r11)
            int r14 = r14 + 1
            goto L_0x0133
        L_0x0143:
            int r8 = r8 + 1
            r12 = 0
            r14 = 1
            goto L_0x0126
        L_0x0149:
            com.google.android.gms.internal.ads.zzca r8 = r11.zzu()
            r0.zzab = r8
        L_0x014f:
            com.google.android.gms.internal.ads.zzdc r8 = r43.zzn()
            boolean r9 = r8.zzo()
            if (r9 == 0) goto L_0x015c
            com.google.android.gms.internal.ads.zzca r8 = r0.zzab
            goto L_0x0179
        L_0x015c:
            int r9 = r43.zzd()
            com.google.android.gms.internal.ads.zzdb r11 = r0.zza
            r12 = 0
            com.google.android.gms.internal.ads.zzdb r8 = r8.zze(r9, r11, r12)
            com.google.android.gms.internal.ads.zzbu r8 = r8.zze
            com.google.android.gms.internal.ads.zzca r9 = r0.zzab
            com.google.android.gms.internal.ads.zzby r9 = r9.zza()
            com.google.android.gms.internal.ads.zzca r8 = r8.zzg
            r9.zzb(r8)
            com.google.android.gms.internal.ads.zzca r8 = r9.zzu()
        L_0x0179:
            com.google.android.gms.internal.ads.zzca r9 = r0.zzI
            boolean r9 = r8.equals(r9)
            r0.zzI = r8
            boolean r8 = r3.zzl
            boolean r11 = r1.zzl
            if (r8 == r11) goto L_0x0189
            r8 = 1
            goto L_0x018a
        L_0x0189:
            r8 = 0
        L_0x018a:
            int r11 = r3.zze
            int r12 = r1.zze
            if (r11 == r12) goto L_0x0192
            r11 = 1
            goto L_0x0193
        L_0x0192:
            r11 = 0
        L_0x0193:
            if (r11 != 0) goto L_0x0197
            if (r8 == 0) goto L_0x019a
        L_0x0197:
            r43.zzam()
        L_0x019a:
            boolean r12 = r3.zzg
            boolean r13 = r1.zzg
            if (r12 == r13) goto L_0x01a2
            r12 = 1
            goto L_0x01a3
        L_0x01a2:
            r12 = 0
        L_0x01a3:
            if (r4 != 0) goto L_0x01b2
            com.google.android.gms.internal.ads.zzfh r4 = r0.zzl
            com.google.android.gms.internal.ads.zzju r13 = new com.google.android.gms.internal.ads.zzju
            r14 = r45
            r13.<init>(r1, r14)
            r14 = 0
            r4.zzd(r14, r13)
        L_0x01b2:
            if (r6 == 0) goto L_0x02e0
            com.google.android.gms.internal.ads.zzcz r6 = new com.google.android.gms.internal.ads.zzcz
            r6.<init>()
            com.google.android.gms.internal.ads.zzdc r13 = r3.zza
            boolean r13 = r13.zzo()
            if (r13 != 0) goto L_0x01f3
            com.google.android.gms.internal.ads.zzvo r13 = r3.zzb
            java.lang.Object r13 = r13.zza
            com.google.android.gms.internal.ads.zzdc r14 = r3.zza
            r14.zzn(r13, r6)
            int r14 = r6.zzd
            com.google.android.gms.internal.ads.zzdc r15 = r3.zza
            int r15 = r15.zza(r13)
            com.google.android.gms.internal.ads.zzdc r4 = r3.zza
            r48 = r13
            com.google.android.gms.internal.ads.zzdb r13 = r0.zza
            r18 = r11
            r19 = r12
            r11 = 0
            com.google.android.gms.internal.ads.zzdb r4 = r4.zze(r14, r13, r11)
            java.lang.Object r4 = r4.zzc
            com.google.android.gms.internal.ads.zzdb r11 = r0.zza
            com.google.android.gms.internal.ads.zzbu r11 = r11.zze
            r24 = r48
            r21 = r4
            r23 = r11
            r22 = r14
            r25 = r15
            goto L_0x0201
        L_0x01f3:
            r18 = r11
            r19 = r12
            r22 = r51
            r21 = 0
            r23 = 0
            r24 = 0
            r25 = -1
        L_0x0201:
            if (r2 != 0) goto L_0x022b
            com.google.android.gms.internal.ads.zzvo r4 = r3.zzb
            boolean r4 = r4.zzb()
            if (r4 == 0) goto L_0x021a
            com.google.android.gms.internal.ads.zzvo r4 = r3.zzb
            int r11 = r4.zzb
            int r4 = r4.zzc
            long r11 = r6.zzh(r11, r4)
            long r13 = zzaa(r3)
            goto L_0x023d
        L_0x021a:
            com.google.android.gms.internal.ads.zzvo r4 = r3.zzb
            int r4 = r4.zze
            r11 = -1
            if (r4 == r11) goto L_0x0228
            com.google.android.gms.internal.ads.zzmg r4 = r0.zzac
            long r11 = zzaa(r4)
            goto L_0x023c
        L_0x0228:
            long r11 = r6.zze
            goto L_0x023c
        L_0x022b:
            com.google.android.gms.internal.ads.zzvo r4 = r3.zzb
            boolean r4 = r4.zzb()
            if (r4 == 0) goto L_0x023a
            long r11 = r3.zzr
            long r13 = zzaa(r3)
            goto L_0x023d
        L_0x023a:
            long r11 = r3.zzr
        L_0x023c:
            r13 = r11
        L_0x023d:
            com.google.android.gms.internal.ads.zzcs r4 = new com.google.android.gms.internal.ads.zzcs
            int r6 = com.google.android.gms.internal.ads.zzgd.zza
            com.google.android.gms.internal.ads.zzvo r6 = r3.zzb
            int r15 = r6.zzb
            int r6 = r6.zzc
            long r26 = com.google.android.gms.internal.ads.zzgd.zzu(r11)
            long r28 = com.google.android.gms.internal.ads.zzgd.zzu(r13)
            r20 = r4
            r30 = r15
            r31 = r6
            r20.<init>(r21, r22, r23, r24, r25, r26, r28, r30, r31)
            int r6 = r43.zzd()
            com.google.android.gms.internal.ads.zzmg r11 = r0.zzac
            com.google.android.gms.internal.ads.zzdc r11 = r11.zza
            boolean r11 = r11.zzo()
            if (r11 != 0) goto L_0x0299
            com.google.android.gms.internal.ads.zzmg r11 = r0.zzac
            com.google.android.gms.internal.ads.zzvo r12 = r11.zzb
            java.lang.Object r12 = r12.zza
            com.google.android.gms.internal.ads.zzdc r11 = r11.zza
            com.google.android.gms.internal.ads.zzcz r13 = r0.zzn
            r11.zzn(r12, r13)
            com.google.android.gms.internal.ads.zzmg r11 = r0.zzac
            com.google.android.gms.internal.ads.zzdc r11 = r11.zza
            int r11 = r11.zza(r12)
            com.google.android.gms.internal.ads.zzmg r13 = r0.zzac
            com.google.android.gms.internal.ads.zzdc r13 = r13.zza
            com.google.android.gms.internal.ads.zzdb r14 = r0.zza
            r47 = r11
            r15 = r12
            r11 = 0
            com.google.android.gms.internal.ads.zzdb r13 = r13.zze(r6, r14, r11)
            java.lang.Object r11 = r13.zzc
            com.google.android.gms.internal.ads.zzdb r12 = r0.zza
            com.google.android.gms.internal.ads.zzbu r12 = r12.zze
            r35 = r47
            r31 = r11
            r33 = r12
            r34 = r15
            goto L_0x02a1
        L_0x0299:
            r31 = 0
            r33 = 0
            r34 = 0
            r35 = -1
        L_0x02a1:
            long r36 = com.google.android.gms.internal.ads.zzgd.zzu(r49)
            com.google.android.gms.internal.ads.zzcs r11 = new com.google.android.gms.internal.ads.zzcs
            com.google.android.gms.internal.ads.zzmg r12 = r0.zzac
            com.google.android.gms.internal.ads.zzvo r12 = r12.zzb
            boolean r12 = r12.zzb()
            if (r12 == 0) goto L_0x02be
            com.google.android.gms.internal.ads.zzmg r12 = r0.zzac
            long r12 = zzaa(r12)
            long r12 = com.google.android.gms.internal.ads.zzgd.zzu(r12)
            r38 = r12
            goto L_0x02c0
        L_0x02be:
            r38 = r36
        L_0x02c0:
            com.google.android.gms.internal.ads.zzmg r12 = r0.zzac
            com.google.android.gms.internal.ads.zzvo r12 = r12.zzb
            int r13 = r12.zzb
            int r12 = r12.zzc
            r30 = r11
            r32 = r6
            r40 = r13
            r41 = r12
            r30.<init>(r31, r32, r33, r34, r35, r36, r38, r40, r41)
            com.google.android.gms.internal.ads.zzfh r6 = r0.zzl
            com.google.android.gms.internal.ads.zzjz r12 = new com.google.android.gms.internal.ads.zzjz
            r12.<init>(r2, r4, r11)
            r2 = 11
            r6.zzd(r2, r12)
            goto L_0x02e4
        L_0x02e0:
            r18 = r11
            r19 = r12
        L_0x02e4:
            if (r7 == 0) goto L_0x02f2
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzkb r4 = new com.google.android.gms.internal.ads.zzkb
            r4.<init>(r10, r5)
            r5 = 1
            r2.zzd(r5, r4)
            goto L_0x02f3
        L_0x02f2:
            r5 = 1
        L_0x02f3:
            com.google.android.gms.internal.ads.zzjh r2 = r3.zzf
            com.google.android.gms.internal.ads.zzjh r4 = r1.zzf
            r6 = 10
            if (r2 == r4) goto L_0x0313
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzkc r4 = new com.google.android.gms.internal.ads.zzkc
            r4.<init>(r1)
            r2.zzd(r6, r4)
            com.google.android.gms.internal.ads.zzjh r2 = r1.zzf
            if (r2 == 0) goto L_0x0313
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzkd r4 = new com.google.android.gms.internal.ads.zzkd
            r4.<init>(r1)
            r2.zzd(r6, r4)
        L_0x0313:
            com.google.android.gms.internal.ads.zzzn r2 = r3.zzi
            com.google.android.gms.internal.ads.zzzn r4 = r1.zzi
            if (r2 == r4) goto L_0x032b
            com.google.android.gms.internal.ads.zzzm r2 = r0.zzi
            java.lang.Object r4 = r4.zze
            r2.zzq(r4)
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzke r4 = new com.google.android.gms.internal.ads.zzke
            r4.<init>(r1)
            r7 = 2
            r2.zzd(r7, r4)
        L_0x032b:
            if (r9 != 0) goto L_0x033b
            com.google.android.gms.internal.ads.zzca r2 = r0.zzI
            com.google.android.gms.internal.ads.zzfh r4 = r0.zzl
            com.google.android.gms.internal.ads.zzkf r7 = new com.google.android.gms.internal.ads.zzkf
            r7.<init>(r2)
            r2 = 14
            r4.zzd(r2, r7)
        L_0x033b:
            if (r19 == 0) goto L_0x0348
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzkg r4 = new com.google.android.gms.internal.ads.zzkg
            r4.<init>(r1)
            r7 = 3
            r2.zzd(r7, r4)
        L_0x0348:
            if (r18 != 0) goto L_0x034c
            if (r8 == 0) goto L_0x0357
        L_0x034c:
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzkh r4 = new com.google.android.gms.internal.ads.zzkh
            r4.<init>(r1)
            r7 = -1
            r2.zzd(r7, r4)
        L_0x0357:
            r2 = 4
            if (r18 == 0) goto L_0x0364
            com.google.android.gms.internal.ads.zzfh r4 = r0.zzl
            com.google.android.gms.internal.ads.zzki r7 = new com.google.android.gms.internal.ads.zzki
            r7.<init>(r1)
            r4.zzd(r2, r7)
        L_0x0364:
            r4 = 5
            if (r8 == 0) goto L_0x0373
            com.google.android.gms.internal.ads.zzfh r7 = r0.zzl
            com.google.android.gms.internal.ads.zzjv r8 = new com.google.android.gms.internal.ads.zzjv
            r9 = r46
            r8.<init>(r1, r9)
            r7.zzd(r4, r8)
        L_0x0373:
            int r7 = r3.zzm
            int r8 = r1.zzm
            r9 = 6
            if (r7 == r8) goto L_0x0384
            com.google.android.gms.internal.ads.zzfh r7 = r0.zzl
            com.google.android.gms.internal.ads.zzjw r8 = new com.google.android.gms.internal.ads.zzjw
            r8.<init>(r1)
            r7.zzd(r9, r8)
        L_0x0384:
            boolean r7 = r3.zzi()
            boolean r8 = r44.zzi()
            r10 = 7
            if (r7 == r8) goto L_0x0399
            com.google.android.gms.internal.ads.zzfh r7 = r0.zzl
            com.google.android.gms.internal.ads.zzjx r8 = new com.google.android.gms.internal.ads.zzjx
            r8.<init>(r1)
            r7.zzd(r10, r8)
        L_0x0399:
            com.google.android.gms.internal.ads.zzcl r7 = r3.zzn
            com.google.android.gms.internal.ads.zzcl r8 = r1.zzn
            boolean r7 = r7.equals(r8)
            r8 = 12
            if (r7 != 0) goto L_0x03af
            com.google.android.gms.internal.ads.zzfh r7 = r0.zzl
            com.google.android.gms.internal.ads.zzjy r11 = new com.google.android.gms.internal.ads.zzjy
            r11.<init>(r1)
            r7.zzd(r8, r11)
        L_0x03af:
            com.google.android.gms.internal.ads.zzcp r7 = r0.zzH
            com.google.android.gms.internal.ads.zzct r11 = r0.zzg
            com.google.android.gms.internal.ads.zzcp r12 = r0.zzc
            int r13 = com.google.android.gms.internal.ads.zzgd.zza
            boolean r13 = r11.zzx()
            r14 = r11
            com.google.android.gms.internal.ads.zzm r14 = (com.google.android.gms.internal.ads.zzm) r14
            com.google.android.gms.internal.ads.zzdc r15 = r14.zzn()
            boolean r17 = r15.zzo()
            if (r17 != 0) goto L_0x03dc
            int r5 = r14.zzd()
            com.google.android.gms.internal.ads.zzdb r8 = r14.zza
            r17 = r7
            r6 = 0
            com.google.android.gms.internal.ads.zzdb r5 = r15.zze(r5, r8, r6)
            boolean r5 = r5.zzi
            if (r5 == 0) goto L_0x03de
            r5 = 1
            goto L_0x03df
        L_0x03dc:
            r17 = r7
        L_0x03de:
            r5 = 0
        L_0x03df:
            com.google.android.gms.internal.ads.zzdc r6 = r14.zzn()
            boolean r7 = r6.zzo()
            if (r7 == 0) goto L_0x03ee
            r7 = -1
            r8 = 0
            r16 = 0
            goto L_0x0405
        L_0x03ee:
            int r7 = r14.zzd()
            r14.zzh()
            r14.zzw()
            r8 = 0
            int r6 = r6.zzk(r7, r8, r8)
            r7 = -1
            if (r6 == r7) goto L_0x0403
            r16 = 1
            goto L_0x0405
        L_0x0403:
            r16 = r8
        L_0x0405:
            com.google.android.gms.internal.ads.zzdc r6 = r14.zzn()
            boolean r15 = r6.zzo()
            if (r15 == 0) goto L_0x0411
        L_0x040f:
            r6 = r8
            goto L_0x0422
        L_0x0411:
            int r15 = r14.zzd()
            r14.zzh()
            r14.zzw()
            int r6 = r6.zzj(r15, r8, r8)
            if (r6 == r7) goto L_0x040f
            r6 = 1
        L_0x0422:
            com.google.android.gms.internal.ads.zzdc r7 = r14.zzn()
            boolean r15 = r7.zzo()
            if (r15 != 0) goto L_0x0440
            int r15 = r14.zzd()
            com.google.android.gms.internal.ads.zzdb r8 = r14.zza
            r9 = 0
            com.google.android.gms.internal.ads.zzdb r7 = r7.zze(r15, r8, r9)
            boolean r7 = r7.zzb()
            if (r7 == 0) goto L_0x0442
            r7 = 1
            goto L_0x0443
        L_0x0440:
            r9 = 0
        L_0x0442:
            r7 = 0
        L_0x0443:
            com.google.android.gms.internal.ads.zzdc r8 = r14.zzn()
            boolean r15 = r8.zzo()
            if (r15 != 0) goto L_0x045d
            int r15 = r14.zzd()
            com.google.android.gms.internal.ads.zzdb r14 = r14.zza
            com.google.android.gms.internal.ads.zzdb r8 = r8.zze(r15, r14, r9)
            boolean r8 = r8.zzj
            if (r8 == 0) goto L_0x045d
            r8 = 1
            goto L_0x045e
        L_0x045d:
            r8 = 0
        L_0x045e:
            com.google.android.gms.internal.ads.zzdc r9 = r11.zzn()
            boolean r9 = r9.zzo()
            com.google.android.gms.internal.ads.zzcn r10 = new com.google.android.gms.internal.ads.zzcn
            r10.<init>()
            r10.zzb(r12)
            r11 = r13 ^ 1
            r10.zzd(r2, r11)
            if (r5 == 0) goto L_0x0479
            if (r13 != 0) goto L_0x0479
            r2 = 1
            goto L_0x047a
        L_0x0479:
            r2 = 0
        L_0x047a:
            r10.zzd(r4, r2)
            if (r16 == 0) goto L_0x0483
            if (r13 != 0) goto L_0x0483
            r2 = 1
            goto L_0x0484
        L_0x0483:
            r2 = 0
        L_0x0484:
            r4 = 6
            r10.zzd(r4, r2)
            if (r9 != 0) goto L_0x0494
            if (r16 != 0) goto L_0x0490
            if (r7 == 0) goto L_0x0490
            if (r5 == 0) goto L_0x0494
        L_0x0490:
            if (r13 != 0) goto L_0x0494
            r2 = 1
            goto L_0x0495
        L_0x0494:
            r2 = 0
        L_0x0495:
            r4 = 7
            r10.zzd(r4, r2)
            if (r6 == 0) goto L_0x049f
            if (r13 != 0) goto L_0x049f
            r2 = 1
            goto L_0x04a0
        L_0x049f:
            r2 = 0
        L_0x04a0:
            r4 = 8
            r10.zzd(r4, r2)
            if (r9 != 0) goto L_0x04b1
            if (r6 != 0) goto L_0x04ad
            if (r7 == 0) goto L_0x04b1
            if (r8 == 0) goto L_0x04b1
        L_0x04ad:
            if (r13 != 0) goto L_0x04b1
            r2 = 1
            goto L_0x04b2
        L_0x04b1:
            r2 = 0
        L_0x04b2:
            r4 = 9
            r10.zzd(r4, r2)
            r2 = 10
            r10.zzd(r2, r11)
            if (r5 == 0) goto L_0x04c2
            if (r13 != 0) goto L_0x04c2
            r2 = 1
            goto L_0x04c3
        L_0x04c2:
            r2 = 0
        L_0x04c3:
            r4 = 11
            r10.zzd(r4, r2)
            if (r5 == 0) goto L_0x04d0
            if (r13 != 0) goto L_0x04d0
            r2 = 12
            r14 = 1
            goto L_0x04d3
        L_0x04d0:
            r2 = 12
            r14 = 0
        L_0x04d3:
            r10.zzd(r2, r14)
            com.google.android.gms.internal.ads.zzcp r2 = r10.zze()
            r0.zzH = r2
            r4 = r17
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x04f0
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            com.google.android.gms.internal.ads.zzjt r4 = new com.google.android.gms.internal.ads.zzjt
            r4.<init>(r0)
            r5 = 13
            r2.zzd(r5, r4)
        L_0x04f0:
            com.google.android.gms.internal.ads.zzfh r2 = r0.zzl
            r2.zzc()
            boolean r2 = r3.zzo
            boolean r1 = r1.zzo
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkw.zzal(com.google.android.gms.internal.ads.zzmg, int, int, boolean, int, long, int, boolean):void");
    }

    private final void zzam() {
        int zzf2 = zzf();
        if (zzf2 == 2 || zzf2 == 3) {
            zzan();
            boolean z = this.zzac.zzo;
            zzv();
            zzv();
        }
    }

    private final void zzan() {
        IllegalStateException illegalStateException;
        this.zze.zzb();
        if (Thread.currentThread() != this.zzs.getThread()) {
            String format = String.format(Locale.US, "Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread", new Object[]{Thread.currentThread().getName(), this.zzs.getThread().getName()});
            if (!this.zzX) {
                if (this.zzY) {
                    illegalStateException = null;
                } else {
                    illegalStateException = new IllegalStateException();
                }
                zzfk.zzg("ExoPlayerImpl", format, illegalStateException);
                this.zzY = true;
                return;
            }
            throw new IllegalStateException(format);
        }
    }

    public final void zzA(zzna zzna) {
        zzan();
        this.zzr.zzQ(zzna);
    }

    public final void zzB(zzvq zzvq) {
        zzan();
        List singletonList = Collections.singletonList(zzvq);
        zzan();
        zzan();
        zzW(this.zzac);
        zzk();
        this.zzC++;
        if (!this.zzo.isEmpty()) {
            int size = this.zzo.size();
            for (int i = size - 1; i >= 0; i--) {
                this.zzo.remove(i);
            }
            this.zzag = this.zzag.zzh(0, size);
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < singletonList.size(); i2++) {
            zzmd zzmd = new zzmd((zzvq) singletonList.get(i2), this.zzp);
            arrayList.add(zzmd);
            this.zzo.add(i2, new zzkv(zzmd.zzb, zzmd.zza));
        }
        this.zzag = this.zzag.zzg(0, arrayList.size());
        zzml zzml = new zzml(this.zzo, this.zzag);
        if (zzml.zzo() || zzml.zzc() >= 0) {
            int zzg2 = zzml.zzg(false);
            zzmg zzad2 = zzad(this.zzac, zzml, zzac(zzml, zzg2, C.TIME_UNSET));
            int i3 = zzad2.zze;
            if (!(zzg2 == -1 || i3 == 1)) {
                i3 = 4;
                if (!zzml.zzo() && zzg2 < zzml.zzc()) {
                    i3 = 2;
                }
            }
            zzmg zze2 = zzad2.zze(i3);
            this.zzk.zzq(arrayList, zzg2, zzgd.zzr(C.TIME_UNSET), this.zzag);
            zzal(zze2, 0, 1, !this.zzac.zzb.zza.equals(zze2.zzb.zza) && !this.zzac.zza.zzo(), 4, zzZ(zze2), -1, false);
            return;
        }
        throw new zzar(zzml, -1, C.TIME_UNSET);
    }

    public final zzjh zzE() {
        zzan();
        return this.zzac.zzf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzS(zzlf zzlf) {
        long j;
        boolean z;
        int i = this.zzC - zzlf.zzb;
        this.zzC = i;
        boolean z2 = true;
        if (zzlf.zzc) {
            this.zzD = zzlf.zzd;
            this.zzE = true;
        }
        if (zzlf.zze) {
            this.zzF = zzlf.zzf;
        }
        if (i == 0) {
            zzdc zzdc = zzlf.zza.zza;
            if (!this.zzac.zza.zzo() && zzdc.zzo()) {
                this.zzad = -1;
                this.zzae = 0;
            }
            if (!zzdc.zzo()) {
                List zzw2 = ((zzml) zzdc).zzw();
                zzeq.zzf(zzw2.size() == this.zzo.size());
                for (int i2 = 0; i2 < zzw2.size(); i2++) {
                    ((zzkv) this.zzo.get(i2)).zzc((zzdc) zzw2.get(i2));
                }
            }
            if (this.zzE) {
                if (zzlf.zza.zzb.equals(this.zzac.zzb) && zzlf.zza.zzd == this.zzac.zzr) {
                    z2 = false;
                }
                if (!z2) {
                    j = -9223372036854775807L;
                } else if (zzdc.zzo() || zzlf.zza.zzb.zzb()) {
                    j = zzlf.zza.zzd;
                } else {
                    zzmg zzmg = zzlf.zza;
                    zzvo zzvo = zzmg.zzb;
                    j = zzmg.zzd;
                    zzab(zzdc, zzvo, j);
                }
                z = z2;
            } else {
                j = -9223372036854775807L;
                z = false;
            }
            this.zzE = false;
            zzal(zzlf.zza, 1, this.zzF, z, this.zzD, j, -1, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzT(zzlf zzlf) {
        this.zzj.zzh(new zzkl(this, zzlf));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzU(zzcq zzcq) {
        zzcq.zza(this.zzH);
    }

    public final void zza(int i, long j, int i2, boolean z) {
        zzan();
        if (i != -1) {
            zzeq.zzd(i >= 0);
            zzdc zzdc = this.zzac.zza;
            if (zzdc.zzo() || i < zzdc.zzc()) {
                this.zzr.zzu();
                this.zzC++;
                if (zzx()) {
                    zzfk.zzf("ExoPlayerImpl", "seekTo ignored because an ad is playing");
                    zzlf zzlf = new zzlf(this.zzac);
                    zzlf.zza(1);
                    this.zzaf.zza.zzT(zzlf);
                    return;
                }
                zzmg zzmg = this.zzac;
                int i3 = zzmg.zze;
                if (i3 == 3 || (i3 == 4 && !zzdc.zzo())) {
                    zzmg = this.zzac.zze(2);
                }
                int zzd2 = zzd();
                zzmg zzad2 = zzad(zzmg, zzdc, zzac(zzdc, i, j));
                this.zzk.zzl(zzdc, i, zzgd.zzr(j));
                zzal(zzad2, 0, 1, true, 1, zzZ(zzad2), zzd2, false);
            }
        }
    }

    public final int zzb() {
        zzan();
        if (zzx()) {
            return this.zzac.zzb.zzb;
        }
        return -1;
    }

    public final int zzc() {
        zzan();
        if (zzx()) {
            return this.zzac.zzb.zzc;
        }
        return -1;
    }

    public final int zzd() {
        zzan();
        int zzW2 = zzW(this.zzac);
        if (zzW2 == -1) {
            return 0;
        }
        return zzW2;
    }

    public final int zze() {
        zzan();
        if (this.zzac.zza.zzo()) {
            return 0;
        }
        zzmg zzmg = this.zzac;
        return zzmg.zza.zza(zzmg.zzb.zza);
    }

    public final int zzf() {
        zzan();
        return this.zzac.zze;
    }

    public final int zzg() {
        zzan();
        return this.zzac.zzm;
    }

    public final int zzh() {
        zzan();
        return 0;
    }

    public final long zzi() {
        zzan();
        if (zzx()) {
            zzmg zzmg = this.zzac;
            if (zzmg.zzk.equals(zzmg.zzb)) {
                return zzgd.zzu(this.zzac.zzp);
            }
            return zzl();
        }
        zzan();
        if (this.zzac.zza.zzo()) {
            return this.zzae;
        }
        zzmg zzmg2 = this.zzac;
        long j = 0;
        if (zzmg2.zzk.zzd != zzmg2.zzb.zzd) {
            return zzgd.zzu(zzmg2.zza.zze(zzd(), this.zza, 0).zzo);
        }
        long j2 = zzmg2.zzp;
        if (this.zzac.zzk.zzb()) {
            zzmg zzmg3 = this.zzac;
            zzmg3.zza.zzn(zzmg3.zzk.zza, this.zzn).zzi(this.zzac.zzk.zzb);
        } else {
            j = j2;
        }
        zzmg zzmg4 = this.zzac;
        zzab(zzmg4.zza, zzmg4.zzk, j);
        return zzgd.zzu(j);
    }

    public final long zzj() {
        zzan();
        return zzY(this.zzac);
    }

    public final long zzk() {
        zzan();
        return zzgd.zzu(zzZ(this.zzac));
    }

    public final long zzl() {
        zzan();
        if (!zzx()) {
            zzdc zzn2 = zzn();
            if (zzn2.zzo()) {
                return C.TIME_UNSET;
            }
            return zzgd.zzu(zzn2.zze(zzd(), this.zza, 0).zzo);
        }
        zzmg zzmg = this.zzac;
        zzvo zzvo = zzmg.zzb;
        zzmg.zza.zzn(zzvo.zza, this.zzn);
        return zzgd.zzu(this.zzn.zzh(zzvo.zzb, zzvo.zzc));
    }

    public final long zzm() {
        zzan();
        return zzgd.zzu(this.zzac.zzq);
    }

    public final zzdc zzn() {
        zzan();
        return this.zzac.zza;
    }

    public final zzdp zzo() {
        zzan();
        return this.zzac.zzi.zzd;
    }

    public final void zzp() {
        zzan();
        zziv zziv = this.zzy;
        boolean zzv2 = zzv();
        int i = 2;
        int zzb2 = zziv.zzb(zzv2, 2);
        zzak(zzv2, zzb2, zzX(zzv2, zzb2));
        zzmg zzmg = this.zzac;
        if (zzmg.zze == 1) {
            zzmg zzd2 = zzmg.zzd((zzjh) null);
            if (true == zzd2.zza.zzo()) {
                i = 4;
            }
            zzmg zze2 = zzd2.zze(i);
            this.zzC++;
            this.zzk.zzk();
            zzal(zze2, 1, 1, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    public final void zzq() {
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = zzgd.zze;
        String zza = zzbv.zza();
        zzfk.zze("ExoPlayerImpl", "Release " + hexString + " [AndroidXMedia3/1.4.0-alpha01] [" + str + "] [" + zza + "]");
        zzan();
        this.zzy.zzd();
        if (!this.zzk.zzp()) {
            zzfh zzfh = this.zzl;
            zzfh.zzd(10, new zzkk());
            zzfh.zzc();
        }
        this.zzl.zze();
        this.zzj.zze((Object) null);
        this.zzt.zzf(this.zzr);
        boolean z = this.zzac.zzo;
        zzmg zze2 = this.zzac.zze(1);
        this.zzac = zze2;
        zzmg zza2 = zze2.zza(zze2.zzb);
        this.zzac = zza2;
        zza2.zzp = zza2.zzr;
        this.zzac.zzq = 0;
        this.zzr.zzP();
        this.zzi.zzj();
        Surface surface = this.zzN;
        if (surface != null) {
            surface.release();
            this.zzN = null;
        }
        this.zzW = zzek.zza;
    }

    public final void zzr(boolean z) {
        zzan();
        int zzb2 = this.zzy.zzb(z, zzf());
        zzak(z, zzb2, zzX(z, zzb2));
    }

    public final void zzs(Surface surface) {
        zzan();
        zzai(surface);
        int i = surface == null ? 0 : -1;
        zzaf(i, i);
    }

    public final void zzt(float f) {
        zzan();
        float max = Math.max(0.0f, Math.min(f, 1.0f));
        if (this.zzU != max) {
            this.zzU = max;
            zzah();
            zzfh zzfh = this.zzl;
            zzfh.zzd(22, new zzka(max));
            zzfh.zzc();
        }
    }

    public final void zzu() {
        zzan();
        this.zzy.zzb(zzv(), 1);
        zzaj((zzjh) null);
        this.zzW = new zzek(zzgbc.zzm(), this.zzac.zzr);
    }

    public final boolean zzv() {
        zzan();
        return this.zzac.zzl;
    }

    public final boolean zzw() {
        zzan();
        return false;
    }

    public final boolean zzx() {
        zzan();
        return this.zzac.zzb.zzb();
    }

    public final int zzy() {
        zzan();
        int length = this.zzh.length;
        return 2;
    }

    public final void zzz(zzna zzna) {
        this.zzr.zzt(zzna);
    }
}

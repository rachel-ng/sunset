package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.Surface;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzt;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcgq extends zzcdv implements zzie, zzna {
    public static final /* synthetic */ int zza = 0;
    private final Context zzb;
    private final zzcgb zzc;
    private final zzze zzd;
    private final zzced zze;
    private final WeakReference zzf;
    private final zzwu zzg;
    private zzjr zzh;
    private ByteBuffer zzi;
    private boolean zzj;
    private zzcdu zzk;
    private int zzl;
    private int zzm;
    private long zzn;
    private final String zzo;
    private final int zzp;
    private final Object zzq = new Object();
    private Integer zzr;
    private final ArrayList zzs;
    private volatile zzcgd zzt;
    private final Set zzu = new HashSet();

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00e3, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzbR)).booleanValue() == false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e7, code lost:
        if (r5.zzj == false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00e9, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ec, code lost:
        if (r5.zzm == false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ee, code lost:
        r6 = new com.google.android.gms.internal.ads.zzcgh(r3, r4, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f6, code lost:
        if (r5.zzi <= 0) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00f8, code lost:
        r6 = new com.google.android.gms.internal.ads.zzcgi(r3, r4, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00fe, code lost:
        r6 = new com.google.android.gms.internal.ads.zzcgj(r3, r4, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0105, code lost:
        if (r5.zzj == false) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0107, code lost:
        r5 = new com.google.android.gms.internal.ads.zzcgk(r3, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x010e, code lost:
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010f, code lost:
        r4 = r3.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0111, code lost:
        if (r4 == null) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0117, code lost:
        if (r4.limit() <= 0) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0119, code lost:
        r4 = new byte[r3.zzi.limit()];
        r3.zzi.get(r4);
        r5 = new com.google.android.gms.internal.ads.zzcgl(r5, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzcgq(android.content.Context r4, com.google.android.gms.internal.ads.zzced r5, com.google.android.gms.internal.ads.zzcee r6, java.lang.Integer r7) {
        /*
            r3 = this;
            r3.<init>()
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r3.zzq = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r3.zzu = r0
            r3.zzb = r4
            r3.zze = r5
            r3.zzr = r7
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            r7.<init>(r6)
            r3.zzf = r7
            com.google.android.gms.internal.ads.zzcgb r7 = new com.google.android.gms.internal.ads.zzcgb
            r7.<init>()
            r3.zzc = r7
            com.google.android.gms.internal.ads.zzze r0 = new com.google.android.gms.internal.ads.zzze
            r0.<init>(r4)
            r3.zzd = r0
            boolean r1 = com.google.android.gms.ads.internal.util.zze.zzc()
            if (r1 == 0) goto L_0x003f
            java.lang.String r1 = r3.toString()
            java.lang.String r2 = "SimpleExoPlayerAdapter initialize "
            java.lang.String r1 = r2.concat(r1)
            com.google.android.gms.ads.internal.util.zze.zza(r1)
        L_0x003f:
            java.util.concurrent.atomic.AtomicInteger r1 = zzD()
            r1.incrementAndGet()
            com.google.android.gms.internal.ads.zzms r1 = new com.google.android.gms.internal.ads.zzms
            com.google.android.gms.internal.ads.zzcgn r2 = new com.google.android.gms.internal.ads.zzcgn
            r2.<init>(r3)
            r1.<init>(r4, r2)
            r1.zzb(r0)
            r1.zza(r7)
            com.google.android.gms.internal.ads.zzmt r7 = r1.zzc()
            r3.zzh = r7
            r7.zzz(r3)
            r7 = 0
            r3.zzl = r7
            r0 = 0
            r3.zzn = r0
            r3.zzm = r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3.zzs = r0
            r0 = 0
            r3.zzt = r0
            if (r6 == 0) goto L_0x0078
            java.lang.String r0 = r6.zzr()
        L_0x0078:
            com.google.android.gms.internal.ads.zzfyb r0 = com.google.android.gms.internal.ads.zzfyb.zzd(r0)
            java.lang.String r1 = ""
            java.lang.Object r0 = r0.zzb(r1)
            java.lang.String r0 = (java.lang.String) r0
            r3.zzo = r0
            if (r6 == 0) goto L_0x008d
            int r0 = r6.zzf()
            goto L_0x008e
        L_0x008d:
            r0 = r7
        L_0x008e:
            r3.zzp = r0
            com.google.android.gms.internal.ads.zzwu r0 = new com.google.android.gms.internal.ads.zzwu
            com.google.android.gms.ads.internal.util.zzt r1 = com.google.android.gms.ads.internal.zzu.zzp()
            com.google.android.gms.ads.internal.util.client.VersionInfoParcel r6 = r6.zzn()
            java.lang.String r6 = r6.afmaVersion
            java.lang.String r4 = r1.zzc(r4, r6)
            boolean r6 = r3.zzj
            if (r6 == 0) goto L_0x00c0
            java.nio.ByteBuffer r6 = r3.zzi
            int r6 = r6.limit()
            if (r6 <= 0) goto L_0x00c0
            java.nio.ByteBuffer r4 = r3.zzi
            int r4 = r4.limit()
            byte[] r4 = new byte[r4]
            java.nio.ByteBuffer r5 = r3.zzi
            r5.get(r4)
            com.google.android.gms.internal.ads.zzcgf r5 = new com.google.android.gms.internal.ads.zzcgf
            r5.<init>(r4)
            goto L_0x012c
        L_0x00c0:
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzbZ
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r1.zza(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            r1 = 1
            if (r6 == 0) goto L_0x00e5
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzbR
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r2.zza(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x00e9
        L_0x00e5:
            boolean r6 = r5.zzj
            if (r6 != 0) goto L_0x00ea
        L_0x00e9:
            r7 = r1
        L_0x00ea:
            boolean r6 = r5.zzm
            if (r6 == 0) goto L_0x00f4
            com.google.android.gms.internal.ads.zzcgh r6 = new com.google.android.gms.internal.ads.zzcgh
            r6.<init>(r3, r4, r7)
            goto L_0x0103
        L_0x00f4:
            int r6 = r5.zzi
            if (r6 <= 0) goto L_0x00fe
            com.google.android.gms.internal.ads.zzcgi r6 = new com.google.android.gms.internal.ads.zzcgi
            r6.<init>(r3, r4, r7)
            goto L_0x0103
        L_0x00fe:
            com.google.android.gms.internal.ads.zzcgj r6 = new com.google.android.gms.internal.ads.zzcgj
            r6.<init>(r3, r4, r7)
        L_0x0103:
            boolean r4 = r5.zzj
            if (r4 == 0) goto L_0x010e
            com.google.android.gms.internal.ads.zzcgk r4 = new com.google.android.gms.internal.ads.zzcgk
            r4.<init>(r3, r6)
            r5 = r4
            goto L_0x010f
        L_0x010e:
            r5 = r6
        L_0x010f:
            java.nio.ByteBuffer r4 = r3.zzi
            if (r4 == 0) goto L_0x012c
            int r4 = r4.limit()
            if (r4 <= 0) goto L_0x012c
            java.nio.ByteBuffer r4 = r3.zzi
            int r4 = r4.limit()
            byte[] r4 = new byte[r4]
            java.nio.ByteBuffer r6 = r3.zzi
            r6.get(r4)
            com.google.android.gms.internal.ads.zzcgl r6 = new com.google.android.gms.internal.ads.zzcgl
            r6.<init>(r5, r4)
            r5 = r6
        L_0x012c:
            com.google.android.gms.internal.ads.zzbeg r4 = com.google.android.gms.internal.ads.zzbep.zzo
            com.google.android.gms.internal.ads.zzben r6 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r4 = r6.zza(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0144
            com.google.android.gms.internal.ads.zzcgp r4 = new com.google.android.gms.internal.ads.zzcgp
            r4.<init>()
            goto L_0x0149
        L_0x0144:
            com.google.android.gms.internal.ads.zzcgg r4 = new com.google.android.gms.internal.ads.zzcgg
            r4.<init>()
        L_0x0149:
            com.google.android.gms.internal.ads.zzwt r6 = new com.google.android.gms.internal.ads.zzwt
            r6.<init>(r4)
            r0.<init>(r5, r6)
            r3.zzg = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcgq.<init>(android.content.Context, com.google.android.gms.internal.ads.zzced, com.google.android.gms.internal.ads.zzcee, java.lang.Integer):void");
    }

    private final boolean zzad() {
        return this.zzt != null && this.zzt.zzq();
    }

    public final void finalize() {
        zzD().decrementAndGet();
        if (zze.zzc()) {
            zze.zza("SimpleExoPlayerAdapter finalize ".concat(toString()));
        }
    }

    public final long zzA() {
        if (!zzad()) {
            return (long) this.zzl;
        }
        return 0;
    }

    public final long zzB() {
        if (zzad()) {
            return this.zzt.zzl();
        }
        synchronized (this.zzq) {
            while (!this.zzs.isEmpty()) {
                long j = this.zzn;
                Map zze2 = ((zzhz) this.zzs.remove(0)).zze();
                long j2 = 0;
                if (zze2 != null) {
                    Iterator it = zze2.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry entry = (Map.Entry) it.next();
                        if (entry != null) {
                            try {
                                if (!(entry.getKey() == null || !zzfxm.zzc("content-length", (CharSequence) entry.getKey()) || entry.getValue() == null || ((List) entry.getValue()).get(0) == null)) {
                                    j2 = Long.parseLong((String) ((List) entry.getValue()).get(0));
                                    break;
                                }
                            } catch (NumberFormatException unused) {
                                continue;
                            }
                        }
                    }
                }
                this.zzn = j + j2;
            }
        }
        return this.zzn;
    }

    public final Integer zzC() {
        return this.zzr;
    }

    public final void zzF(Uri[] uriArr, String str) {
        zzG(uriArr, str, ByteBuffer.allocate(0), false);
    }

    public final void zzH() {
        zzjr zzjr = this.zzh;
        if (zzjr != null) {
            zzjr.zzA(this);
            this.zzh.zzq();
            this.zzh = null;
            zzE().decrementAndGet();
        }
    }

    public final void zzI(long j) {
        zzm zzm2 = (zzm) this.zzh;
        zzm2.zza(zzm2.zzd(), j, 5, false);
    }

    public final void zzJ(int i) {
        this.zzc.zzk(i);
    }

    public final void zzK(int i) {
        this.zzc.zzl(i);
    }

    public final void zzL(zzcdu zzcdu) {
        this.zzk = zzcdu;
    }

    public final void zzM(int i) {
        this.zzc.zzm(i);
    }

    public final void zzN(int i) {
        this.zzc.zzn(i);
    }

    public final void zzO(boolean z) {
        this.zzh.zzr(z);
    }

    public final void zzP(Integer num) {
        this.zzr = num;
    }

    public final void zzQ(boolean z) {
        if (this.zzh != null) {
            int i = 0;
            while (true) {
                this.zzh.zzy();
                if (i < 2) {
                    zzze zzze = this.zzd;
                    zzyq zzc2 = zzze.zzf().zzc();
                    zzc2.zzp(i, !z);
                    zzze.zzl(zzc2);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public final void zzR(int i) {
        for (WeakReference weakReference : this.zzu) {
            zzcga zzcga = (zzcga) weakReference.get();
            if (zzcga != null) {
                zzcga.zzm(i);
            }
        }
    }

    public final void zzS(Surface surface, boolean z) {
        zzjr zzjr = this.zzh;
        if (zzjr != null) {
            zzjr.zzs(surface);
        }
    }

    public final void zzT(float f, boolean z) {
        zzjr zzjr = this.zzh;
        if (zzjr != null) {
            zzjr.zzt(f);
        }
    }

    public final void zzU() {
        this.zzh.zzu();
    }

    public final boolean zzV() {
        return this.zzh != null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzhb zzW(String str, boolean z) {
        zzcgq zzcgq = true != z ? null : this;
        zzced zzced = this.zze;
        return new zzcgt(str, zzcgq, zzced.zzd, zzced.zzf, zzced.zzn, zzced.zzo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzhb zzX(String str, boolean z) {
        zzcgq zzcgq = true != z ? null : this;
        zzced zzced = this.zze;
        zzcga zzcga = new zzcga(str, zzcgq, zzced.zzd, zzced.zzf, zzced.zzi);
        this.zzu.add(new WeakReference(zzcga));
        return zzcga;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzhb zzY(String str, boolean z) {
        zzhk zzhk = new zzhk();
        zzhk.zzf(str);
        zzhk.zze(true != z ? null : this);
        zzhk.zzc(this.zze.zzd);
        zzhk.zzd(this.zze.zzf);
        zzhk.zzb(true);
        return zzhk.zza();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzhb zzZ(zzha zzha) {
        zzhb zza2 = zzha.zza();
        zzcgo zzcgo = new zzcgo(this);
        return new zzcgd(this.zzb, zza2, this.zzo, this.zzp, this, zzcgo);
    }

    public final void zza(zzhb zzhb, zzhh zzhh, boolean z, int i) {
        this.zzl += i;
    }

    /* access modifiers changed from: package-private */
    public final zzvq zzaa(Uri uri) {
        zzaw zzaw = new zzaw();
        zzaw.zzb(uri);
        zzbu zzc2 = zzaw.zzc();
        zzwu zzwu = this.zzg;
        zzwu.zza(this.zze.zzg);
        return zzwu.zzb(zzc2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzab(boolean z, long j) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu != null) {
            zzcdu.zzi(z, j);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzmn[] zzac(Handler handler, zzaci zzaci, zzqo zzqo, zzxu zzxu, zzun zzun) {
        zztx zztx = zztx.zza;
        Context context = this.zzb;
        zzsf zzsf = new zzsf(context, new zztj(context), zztx, false, handler, zzqo, new zzrl(context).zzd());
        zztx zztx2 = zztx.zza;
        Context context2 = this.zzb;
        return new zzmn[]{zzsf, new zzabj(context2, new zztj(context2), zztx2, 0, false, handler, zzaci, -1, 30.0f)};
    }

    public final void zzb(zzhb zzhb, zzhh zzhh, boolean z) {
    }

    public final void zzc(zzhb zzhb, zzhh zzhh, boolean z) {
    }

    public final void zzd(zzhb zzhb, zzhh zzhh, boolean z) {
        if (zzhb instanceof zzhz) {
            synchronized (this.zzq) {
                this.zzs.add((zzhz) zzhb);
            }
        } else if (zzhb instanceof zzcgd) {
            this.zzt = (zzcgd) zzhb;
            zzcee zzcee = (zzcee) this.zzf.get();
            if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() && zzcee != null && this.zzt.zzn()) {
                HashMap hashMap = new HashMap();
                hashMap.put("gcacheHit", String.valueOf(this.zzt.zzp()));
                hashMap.put("gcacheDownloaded", String.valueOf(this.zzt.zzo()));
                zzt.zza.post(new zzcgm(zzcee, hashMap));
            }
        }
    }

    public final void zze(zzmy zzmy, zzan zzan, zziy zziy) {
        zzcee zzcee = (zzcee) this.zzf.get();
        if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() && zzcee != null) {
            HashMap hashMap = new HashMap();
            String str = zzan.zzm;
            if (str != null) {
                hashMap.put("audioMime", str);
            }
            String str2 = zzan.zzn;
            if (str2 != null) {
                hashMap.put("audioSampleMime", str2);
            }
            String str3 = zzan.zzk;
            if (str3 != null) {
                hashMap.put("audioCodec", str3);
            }
            zzcee.zzd("onMetadataEvent", hashMap);
        }
    }

    public final /* synthetic */ void zzf(zzmy zzmy, int i, long j, long j2) {
    }

    public final /* synthetic */ void zzg(zzmy zzmy, zzvk zzvk) {
    }

    public final void zzh(zzmy zzmy, int i, long j) {
        this.zzm += i;
    }

    public final /* synthetic */ void zzi(zzct zzct, zzmz zzmz) {
    }

    public final void zzj(zzmy zzmy, zzvf zzvf, zzvk zzvk, IOException iOException, boolean z) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu == null) {
            return;
        }
        if (this.zze.zzk) {
            zzcdu.zzl("onLoadException", iOException);
        } else {
            zzcdu.zzk("onLoadError", iOException);
        }
    }

    public final void zzk(zzmy zzmy, int i) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu != null) {
            zzcdu.zzm(i);
        }
    }

    public final void zzl(zzmy zzmy, zzcj zzcj) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu != null) {
            zzcdu.zzk("onPlayerError", zzcj);
        }
    }

    public final /* synthetic */ void zzm(zzmy zzmy, zzcs zzcs, zzcs zzcs2, int i) {
    }

    public final void zzn(zzmy zzmy, Object obj, long j) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu != null) {
            zzcdu.zzv();
        }
    }

    public final /* synthetic */ void zzo(zzmy zzmy, zzix zzix) {
    }

    public final void zzp(zzmy zzmy, zzan zzan, zziy zziy) {
        zzcee zzcee = (zzcee) this.zzf.get();
        if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() && zzcee != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("frameRate", String.valueOf(zzan.zzu));
            hashMap.put("bitRate", String.valueOf(zzan.zzj));
            int i = zzan.zzs;
            int i2 = zzan.zzt;
            hashMap.put("resolution", i + "x" + i2);
            String str = zzan.zzm;
            if (str != null) {
                hashMap.put("videoMime", str);
            }
            String str2 = zzan.zzn;
            if (str2 != null) {
                hashMap.put("videoSampleMime", str2);
            }
            String str3 = zzan.zzk;
            if (str3 != null) {
                hashMap.put("videoCodec", str3);
            }
            zzcee.zzd("onMetadataEvent", hashMap);
        }
    }

    public final void zzq(zzmy zzmy, zzdv zzdv) {
        zzcdu zzcdu = this.zzk;
        if (zzcdu != null) {
            zzcdu.zzD(zzdv.zzc, zzdv.zzd);
        }
    }

    public final int zzr() {
        return this.zzm;
    }

    public final int zzt() {
        return this.zzh.zzf();
    }

    public final long zzv() {
        return this.zzh.zzi();
    }

    public final long zzw() {
        return (long) this.zzl;
    }

    public final long zzx() {
        if (zzad() && this.zzt.zzp()) {
            return Math.min((long) this.zzl, this.zzt.zzk());
        }
        return 0;
    }

    public final long zzy() {
        return this.zzh.zzk();
    }

    public final long zzz() {
        return this.zzh.zzl();
    }

    public final void zzG(Uri[] uriArr, String str, ByteBuffer byteBuffer, boolean z) {
        zzvq zzvq;
        if (this.zzh != null) {
            this.zzi = byteBuffer;
            this.zzj = z;
            int length = uriArr.length;
            if (length == 1) {
                zzvq = zzaa(uriArr[0]);
            } else {
                zzvq[] zzvqArr = new zzvq[length];
                for (int i = 0; i < uriArr.length; i++) {
                    zzvqArr[i] = zzaa(uriArr[i]);
                }
                zzvq = new zzwf(false, false, new zzuz(), zzvqArr);
            }
            this.zzh.zzB(zzvq);
            this.zzh.zzp();
            zzE().incrementAndGet();
        }
    }
}

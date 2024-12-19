package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.alibaba.android.arouter.utils.Consts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.internal.measurement.zzfi;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.internal.measurement.zzjs;
import com.google.android.gms.internal.measurement.zzne;
import com.google.android.gms.internal.measurement.zznk;
import com.google.android.gms.measurement.internal.zzin;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public class zznc implements zzil {
    private static volatile zznc zza;
    private List<Long> zzaa;
    private long zzab;
    private final Map<String, zzin> zzac;
    private final Map<String, zzav> zzad;
    private final Map<String, zzb> zzae;
    private zzkp zzaf;
    private String zzag;
    private final zznr zzah;
    private zzgt zzb;
    private zzfz zzc;
    private zzal zzd;
    private zzgg zze;
    private zzmw zzf;
    private zzu zzg;
    private final zznl zzh;
    private zzkn zzi;
    private zzmc zzj;
    private final zzna zzk;
    private zzgq zzl;
    /* access modifiers changed from: private */
    public final zzhj zzm;
    private boolean zzn;
    private boolean zzo;
    private long zzp;
    private List<Runnable> zzq;
    private final Set<String> zzr;
    private int zzs;
    private int zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private FileLock zzx;
    private FileChannel zzy;
    private List<Long> zzz;

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    private class zza implements zzap {
        zzfn.zzk zza;
        List<Long> zzb;
        List<zzfn.zzf> zzc;
        private long zzd;

        private static long zza(zzfn.zzf zzf) {
            return ((zzf.zzd() / 1000) / 60) / 60;
        }

        private zza() {
        }

        public final void zza(zzfn.zzk zzk) {
            Preconditions.checkNotNull(zzk);
            this.zza = zzk;
        }

        public final boolean zza(long j, zzfn.zzf zzf) {
            Preconditions.checkNotNull(zzf);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (!this.zzc.isEmpty() && zza(this.zzc.get(0)) != zza(zzf)) {
                return false;
            }
            long zzca = this.zzd + ((long) zzf.zzca());
            zznc.this.zze();
            if (zzca >= ((long) Math.max(0, zzbf.zzi.zza(null).intValue()))) {
                return false;
            }
            this.zzd = zzca;
            this.zzc.add(zzf);
            this.zzb.add(Long.valueOf(j));
            int size = this.zzc.size();
            zznc.this.zze();
            if (size >= Math.max(1, zzbf.zzj.zza(null).intValue())) {
                return false;
            }
            return true;
        }
    }

    private final int zza(String str, zzah zzah2) {
        zzg zze2;
        zzim zza2;
        if (this.zzb.zzb(str) == null) {
            zzah2.zza(zzin.zza.AD_PERSONALIZATION, zzak.FAILSAFE);
            return 1;
        } else if (!zzne.zza() || !zze().zza(zzbf.zzcp) || (zze2 = zzf().zze(str)) == null || zzgi.zza(zze2.zzak()).zza() != zzim.POLICY || (zza2 = this.zzb.zza(str, zzin.zza.AD_PERSONALIZATION)) == zzim.UNINITIALIZED) {
            zzah2.zza(zzin.zza.AD_PERSONALIZATION, zzak.REMOTE_DEFAULT);
            if (this.zzb.zzc(str, zzin.zza.AD_PERSONALIZATION)) {
                return 0;
            }
            return 1;
        } else {
            zzah2.zza(zzin.zza.AD_PERSONALIZATION, zzak.REMOTE_ENFORCED_DEFAULT);
            if (zza2 == zzim.GRANTED) {
                return 0;
            }
            return 1;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    private class zzb {
        final String zza;
        long zzb;

        private zzb(zznc zznc) {
            this(zznc, zznc.zzq().zzp());
        }

        private zzb(zznc zznc, String str) {
            this.zza = str;
            this.zzb = zznc.zzb().elapsedRealtime();
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzj().zzu().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzj().zzg().zza("Failed to read from channel", e);
            return 0;
        }
    }

    private final long zzx() {
        long currentTimeMillis = zzb().currentTimeMillis();
        zzmc zzmc = this.zzj;
        zzmc.zzal();
        zzmc.zzt();
        long zza2 = zzmc.zze.zza();
        if (zza2 == 0) {
            zza2 = ((long) zzmc.zzq().zzv().nextInt(86400000)) + 1;
            zzmc.zze.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    public final Context zza() {
        return this.zzm.zza();
    }

    /* access modifiers changed from: package-private */
    public final Bundle zza(String str) {
        int i;
        zzl().zzt();
        zzs();
        if (zzi().zzb(str) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        zzin zzb2 = zzb(str);
        bundle.putAll(zzb2.zzb());
        bundle.putAll(zza(str, zzd(str), zzb2, new zzah()).zzb());
        if (zzp().zzc(str)) {
            i = 1;
        } else {
            zznq zze2 = zzf().zze(str, "_npa");
            if (zze2 != null) {
                i = zze2.zze.equals(1L);
            } else {
                i = zza(str, new zzah());
            }
        }
        bundle.putString("ad_personalization", i == 1 ? "denied" : "granted");
        return bundle;
    }

    public final Clock zzb() {
        return ((zzhj) Preconditions.checkNotNull(this.zzm)).zzb();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0250  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zza(com.google.android.gms.measurement.internal.zzo r13) {
        /*
            r12 = this;
            com.google.android.gms.measurement.internal.zzhc r0 = r12.zzl()
            r0.zzt()
            r12.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            java.lang.String r0 = r13.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            java.lang.String r0 = r13.zzu
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x0029
            java.util.Map<java.lang.String, com.google.android.gms.measurement.internal.zznc$zzb> r0 = r12.zzae
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zznc$zzb r3 = new com.google.android.gms.measurement.internal.zznc$zzb
            java.lang.String r4 = r13.zzu
            r3.<init>(r4)
            r0.put(r2, r3)
        L_0x0029:
            com.google.android.gms.measurement.internal.zzal r0 = r12.zzf()
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zzg r0 = r0.zze(r2)
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zzin r2 = r12.zzb((java.lang.String) r2)
            java.lang.String r3 = r13.zzt
            com.google.android.gms.measurement.internal.zzin r3 = com.google.android.gms.measurement.internal.zzin.zzb((java.lang.String) r3)
            com.google.android.gms.measurement.internal.zzin r2 = r2.zza((com.google.android.gms.measurement.internal.zzin) r3)
            boolean r3 = r2.zzi()
            if (r3 == 0) goto L_0x0054
            com.google.android.gms.measurement.internal.zzmc r3 = r12.zzj
            java.lang.String r4 = r13.zza
            boolean r5 = r13.zzn
            java.lang.String r3 = r3.zza((java.lang.String) r4, (boolean) r5)
            goto L_0x0056
        L_0x0054:
            java.lang.String r3 = ""
        L_0x0056:
            r4 = 0
            if (r0 != 0) goto L_0x007a
            com.google.android.gms.measurement.internal.zzg r0 = new com.google.android.gms.measurement.internal.zzg
            com.google.android.gms.measurement.internal.zzhj r5 = r12.zzm
            java.lang.String r6 = r13.zza
            r0.<init>(r5, r6)
            boolean r5 = r2.zzj()
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = r12.zza((com.google.android.gms.measurement.internal.zzin) r2)
            r0.zzb((java.lang.String) r5)
        L_0x006f:
            boolean r2 = r2.zzi()
            if (r2 == 0) goto L_0x013e
            r0.zzh((java.lang.String) r3)
            goto L_0x013e
        L_0x007a:
            boolean r5 = r2.zzi()
            if (r5 == 0) goto L_0x0127
            if (r3 == 0) goto L_0x0127
            java.lang.String r5 = r0.zzaj()
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0127
            java.lang.String r5 = r0.zzaj()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            r0.zzh((java.lang.String) r3)
            boolean r3 = r13.zzn
            if (r3 == 0) goto L_0x010f
            com.google.android.gms.measurement.internal.zzmc r3 = r12.zzj
            java.lang.String r6 = r13.zza
            android.util.Pair r3 = r3.zza((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzin) r2)
            java.lang.Object r3 = r3.first
            java.lang.String r6 = "00000000-0000-0000-0000-000000000000"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x010f
            if (r5 != 0) goto L_0x010f
            boolean r3 = com.google.android.gms.internal.measurement.zznk.zza()
            if (r3 == 0) goto L_0x00c9
            com.google.android.gms.measurement.internal.zzag r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzcv
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x00c9
            boolean r3 = r2.zzj()
            if (r3 != 0) goto L_0x00c9
            r2 = 1
            goto L_0x00d1
        L_0x00c9:
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzin) r2)
            r0.zzb((java.lang.String) r2)
            r2 = r4
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzal r3 = r12.zzf()
            java.lang.String r5 = r13.zza
            java.lang.String r6 = "_id"
            com.google.android.gms.measurement.internal.zznq r3 = r3.zze(r5, r6)
            if (r3 == 0) goto L_0x013f
            com.google.android.gms.measurement.internal.zzal r3 = r12.zzf()
            java.lang.String r5 = r13.zza
            java.lang.String r6 = "_lair"
            com.google.android.gms.measurement.internal.zznq r3 = r3.zze(r5, r6)
            if (r3 != 0) goto L_0x013f
            com.google.android.gms.common.util.Clock r3 = r12.zzb()
            long r9 = r3.currentTimeMillis()
            com.google.android.gms.measurement.internal.zznq r3 = new com.google.android.gms.measurement.internal.zznq
            java.lang.String r6 = r13.zza
            r7 = 1
            java.lang.Long r11 = java.lang.Long.valueOf(r7)
            java.lang.String r7 = "auto"
            java.lang.String r8 = "_lair"
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r11)
            com.google.android.gms.measurement.internal.zzal r5 = r12.zzf()
            r5.zza((com.google.android.gms.measurement.internal.zznq) r3)
            goto L_0x013f
        L_0x010f:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x013e
            boolean r3 = r2.zzj()
            if (r3 == 0) goto L_0x013e
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzin) r2)
            r0.zzb((java.lang.String) r2)
            goto L_0x013e
        L_0x0127:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x013e
            boolean r3 = r2.zzj()
            if (r3 == 0) goto L_0x013e
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzin) r2)
            r0.zzb((java.lang.String) r2)
        L_0x013e:
            r2 = r4
        L_0x013f:
            java.lang.String r3 = r13.zzb
            r0.zzf((java.lang.String) r3)
            java.lang.String r3 = r13.zzp
            r0.zza((java.lang.String) r3)
            java.lang.String r3 = r13.zzk
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0156
            java.lang.String r3 = r13.zzk
            r0.zze((java.lang.String) r3)
        L_0x0156:
            long r5 = r13.zze
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0163
            long r5 = r13.zze
            r0.zzn(r5)
        L_0x0163:
            java.lang.String r3 = r13.zzc
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0170
            java.lang.String r3 = r13.zzc
            r0.zzd((java.lang.String) r3)
        L_0x0170:
            long r5 = r13.zzj
            r0.zzb((long) r5)
            java.lang.String r3 = r13.zzd
            if (r3 == 0) goto L_0x017e
            java.lang.String r3 = r13.zzd
            r0.zzc((java.lang.String) r3)
        L_0x017e:
            long r5 = r13.zzf
            r0.zzk((long) r5)
            boolean r3 = r13.zzh
            r0.zzb((boolean) r3)
            java.lang.String r3 = r13.zzg
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0195
            java.lang.String r3 = r13.zzg
            r0.zzg((java.lang.String) r3)
        L_0x0195:
            boolean r3 = r13.zzn
            r0.zza((boolean) r3)
            java.lang.Boolean r3 = r13.zzq
            r0.zza((java.lang.Boolean) r3)
            long r5 = r13.zzr
            r0.zzl(r5)
            java.lang.String r3 = r13.zzv
            r0.zzj((java.lang.String) r3)
            boolean r3 = com.google.android.gms.internal.measurement.zznw.zza()
            if (r3 == 0) goto L_0x01c1
            com.google.android.gms.measurement.internal.zzag r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzbq
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01c1
            java.util.List<java.lang.String> r1 = r13.zzs
            r0.zza((java.util.List<java.lang.String>) r1)
            goto L_0x01d6
        L_0x01c1:
            boolean r3 = com.google.android.gms.internal.measurement.zznw.zza()
            if (r3 == 0) goto L_0x01d6
            com.google.android.gms.measurement.internal.zzag r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzbp
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01d6
            r0.zza((java.util.List<java.lang.String>) r1)
        L_0x01d6:
            boolean r1 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r1 == 0) goto L_0x020b
            com.google.android.gms.measurement.internal.zzag r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzbs
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020b
            r12.zzq()
            java.lang.String r1 = r0.zzac()
            boolean r1 = com.google.android.gms.measurement.internal.zznp.zzf(r1)
            if (r1 == 0) goto L_0x020b
            boolean r1 = r13.zzw
            r0.zzc((boolean) r1)
            com.google.android.gms.measurement.internal.zzag r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzbt
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020b
            java.lang.String r1 = r13.zzac
            r0.zzk((java.lang.String) r1)
        L_0x020b:
            boolean r1 = com.google.android.gms.internal.measurement.zzpg.zza()
            if (r1 == 0) goto L_0x0222
            com.google.android.gms.measurement.internal.zzag r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzbz
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x0222
            int r1 = r13.zzaa
            r0.zza((int) r1)
        L_0x0222:
            long r5 = r13.zzx
            r0.zzt(r5)
            boolean r1 = com.google.android.gms.internal.measurement.zzne.zza()
            if (r1 == 0) goto L_0x023e
            com.google.android.gms.measurement.internal.zzag r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzcp
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x023e
            java.lang.String r13 = r13.zzad
            r0.zzi((java.lang.String) r13)
        L_0x023e:
            boolean r13 = com.google.android.gms.internal.measurement.zznk.zza()
            if (r13 == 0) goto L_0x0260
            com.google.android.gms.measurement.internal.zzag r13 = r12.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbf.zzcv
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r1)
            if (r13 == 0) goto L_0x0260
            boolean r13 = r0.zzas()
            if (r13 != 0) goto L_0x0258
            if (r2 == 0) goto L_0x026d
        L_0x0258:
            com.google.android.gms.measurement.internal.zzal r13 = r12.zzf()
            r13.zza((com.google.android.gms.measurement.internal.zzg) r0, (boolean) r2, (boolean) r4)
            goto L_0x026d
        L_0x0260:
            boolean r13 = r0.zzas()
            if (r13 == 0) goto L_0x026d
            com.google.android.gms.measurement.internal.zzal r13 = r12.zzf()
            r13.zza((com.google.android.gms.measurement.internal.zzg) r0, (boolean) r4, (boolean) r4)
        L_0x026d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zza(com.google.android.gms.measurement.internal.zzo):com.google.android.gms.measurement.internal.zzg");
    }

    private final zzo zzc(String str) {
        String str2 = str;
        zzg zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping", str2);
            return null;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null || zza2.booleanValue()) {
            return new zzo(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zze2.zzag(), zze2.zzd(), 0, 0, zze2.zzaq(), false, zze2.zzaa(), zze2.zzx(), zze2.zzo(), zze2.zzan(), (String) null, zzb(str).zzh(), "", (String) null, zze2.zzat(), zze2.zzw(), zzb(str).zza(), zzd(str).zzf(), zze2.zza(), zze2.zzf(), zze2.zzam(), zze2.zzak());
        }
        zzj().zzg().zza("App version does not match; dropping. appId", zzfw.zza(str));
        return null;
    }

    public final zzu zzc() {
        return (zzu) zza((zzmx) this.zzg);
    }

    public final zzab zzd() {
        return this.zzm.zzd();
    }

    public final zzag zze() {
        return ((zzhj) Preconditions.checkNotNull(this.zzm)).zzf();
    }

    public final zzal zzf() {
        return (zzal) zza((zzmx) this.zzd);
    }

    private final zzav zza(String str, zzav zzav, zzin zzin, zzah zzah2) {
        zzim zzim;
        zzim zzim2;
        int i = 90;
        boolean z = true;
        if (zzi().zzb(str) == null) {
            if (zzav.zzc() == zzim.DENIED) {
                i = zzav.zza();
                zzah2.zza(zzin.zza.AD_USER_DATA, i);
            } else {
                zzah2.zza(zzin.zza.AD_USER_DATA, zzak.FAILSAFE);
            }
            return new zzav((Boolean) false, i, (Boolean) true, "-");
        }
        zzim zzc2 = zzav.zzc();
        if (zzc2 == zzim.GRANTED || zzc2 == zzim.DENIED) {
            i = zzav.zza();
            zzah2.zza(zzin.zza.AD_USER_DATA, i);
        } else {
            if (!zzne.zza() || !zze().zza(zzbf.zzcp)) {
                if (!(zzc2 == zzim.UNINITIALIZED || zzc2 == zzim.POLICY)) {
                    z = false;
                }
                Preconditions.checkArgument(z);
                zzin.zza zzb2 = this.zzb.zzb(str, zzin.zza.AD_USER_DATA);
                Boolean zze2 = zzin.zze();
                if (zzb2 == zzin.zza.AD_STORAGE && zze2 != null) {
                    if (zze2.booleanValue()) {
                        zzim2 = zzim.GRANTED;
                    } else {
                        zzim2 = zzim.DENIED;
                    }
                    zzc2 = zzim2;
                    zzah2.zza(zzin.zza.AD_USER_DATA, zzak.REMOTE_DELEGATION);
                }
                if (zzc2 == zzim.UNINITIALIZED) {
                    if (this.zzb.zzc(str, zzin.zza.AD_USER_DATA)) {
                        zzim = zzim.GRANTED;
                    } else {
                        zzim = zzim.DENIED;
                    }
                    zzah2.zza(zzin.zza.AD_USER_DATA, zzak.REMOTE_DEFAULT);
                }
            } else if (zzc2 != zzim.POLICY || (zzim = this.zzb.zza(str, zzin.zza.AD_USER_DATA)) == zzim.UNINITIALIZED) {
                zzin.zza zzb3 = this.zzb.zzb(str, zzin.zza.AD_USER_DATA);
                zzim zzc3 = zzin.zzc();
                if (!(zzc3 == zzim.GRANTED || zzc3 == zzim.DENIED)) {
                    z = false;
                }
                if (zzb3 != zzin.zza.AD_STORAGE || !z) {
                    zzah2.zza(zzin.zza.AD_USER_DATA, zzak.REMOTE_DEFAULT);
                    if (this.zzb.zzc(str, zzin.zza.AD_USER_DATA)) {
                        zzim = zzim.GRANTED;
                    } else {
                        zzim = zzim.DENIED;
                    }
                } else {
                    zzah2.zza(zzin.zza.AD_USER_DATA, zzak.REMOTE_DELEGATION);
                    zzc2 = zzc3;
                }
            } else {
                zzah2.zza(zzin.zza.AD_USER_DATA, zzak.REMOTE_ENFORCED_DEFAULT);
            }
            zzc2 = zzim;
        }
        boolean zzn2 = this.zzb.zzn(str);
        SortedSet<String> zzh2 = zzi().zzh(str);
        if (zzc2 == zzim.DENIED || zzh2.isEmpty()) {
            return new zzav((Boolean) false, i, Boolean.valueOf(zzn2), "-");
        }
        Boolean valueOf = Boolean.valueOf(zzn2);
        String str2 = "";
        if (zzn2) {
            str2 = TextUtils.join(str2, zzh2);
        }
        return new zzav((Boolean) true, i, valueOf, str2);
    }

    private final zzav zzd(String str) {
        zzl().zzt();
        zzs();
        zzav zzav = this.zzad.get(str);
        if (zzav != null) {
            return zzav;
        }
        zzav zzg2 = zzf().zzg(str);
        this.zzad.put(str, zzg2);
        return zzg2;
    }

    public final zzfr zzg() {
        return this.zzm.zzk();
    }

    public final zzfw zzj() {
        return ((zzhj) Preconditions.checkNotNull(this.zzm)).zzj();
    }

    public final zzfz zzh() {
        return (zzfz) zza((zzmx) this.zzc);
    }

    private final zzgg zzy() {
        zzgg zzgg = this.zze;
        if (zzgg != null) {
            return zzgg;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public final zzgt zzi() {
        return (zzgt) zza((zzmx) this.zzb);
    }

    public final zzhc zzl() {
        return ((zzhj) Preconditions.checkNotNull(this.zzm)).zzl();
    }

    /* access modifiers changed from: package-private */
    public final zzhj zzk() {
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final zzin zzb(String str) {
        zzl().zzt();
        zzs();
        zzin zzin = this.zzac.get(str);
        if (zzin == null) {
            zzin = zzf().zzi(str);
            if (zzin == null) {
                zzin = zzin.zza;
            }
            zza(str, zzin);
        }
        return zzin;
    }

    public final zzkn zzm() {
        return (zzkn) zza((zzmx) this.zzi);
    }

    public final zzmc zzn() {
        return this.zzj;
    }

    private final zzmw zzz() {
        return (zzmw) zza((zzmx) this.zzf);
    }

    private static zzmx zza(zzmx zzmx) {
        if (zzmx == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (zzmx.zzan()) {
            return zzmx;
        } else {
            String valueOf = String.valueOf(zzmx.getClass());
            throw new IllegalStateException("Component not initialized: " + valueOf);
        }
    }

    public final zzna zzo() {
        return this.zzk;
    }

    public static zznc zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zznc.class) {
                if (zza == null) {
                    zza = new zznc((zznm) Preconditions.checkNotNull(new zznm(context)));
                }
            }
        }
        return zza;
    }

    public final zznl zzp() {
        return (zznl) zza((zzmx) this.zzh);
    }

    public final zznp zzq() {
        return ((zzhj) Preconditions.checkNotNull(this.zzm)).zzt();
    }

    private final Boolean zza(zzg zzg2) {
        try {
            if (zzg2.zze() != -2147483648L) {
                if (zzg2.zze() == ((long) Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzg2.zzac(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzg2.zzac(), 0).versionName;
                String zzaf2 = zzg2.zzaf();
                if (zzaf2 != null && zzaf2.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final Boolean zzg(zzo zzo2) {
        Boolean bool = zzo2.zzq;
        if (!zzne.zza() || !zze().zza(zzbf.zzcp) || TextUtils.isEmpty(zzo2.zzad)) {
            return bool;
        }
        int i = zznh.zza[zzgi.zza(zzo2.zzad).zza().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return false;
            }
            if (i == 3) {
                return true;
            }
            if (i != 4) {
                return bool;
            }
        }
        return null;
    }

    private final String zza(zzin zzin) {
        if (!zzin.zzj()) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzq().zzv().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final String zzb(zzo zzo2) {
        try {
            return (String) zzl().zza(new zzng(this, zzo2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzj().zzg().zza("Failed to get app instance id. appId", zzfw.zza(zzo2.zza), e);
            return null;
        }
    }

    static /* synthetic */ void zza(zznc zznc, zznm zznm) {
        zznc.zzl().zzt();
        zznc.zzl = new zzgq(zznc);
        zzal zzal = new zzal(zznc);
        zzal.zzam();
        zznc.zzd = zzal;
        zznc.zze().zza((zzai) Preconditions.checkNotNull(zznc.zzb));
        zzmc zzmc = new zzmc(zznc);
        zzmc.zzam();
        zznc.zzj = zzmc;
        zzu zzu2 = new zzu(zznc);
        zzu2.zzam();
        zznc.zzg = zzu2;
        zzkn zzkn = new zzkn(zznc);
        zzkn.zzam();
        zznc.zzi = zzkn;
        zzmw zzmw = new zzmw(zznc);
        zzmw.zzam();
        zznc.zzf = zzmw;
        zznc.zze = new zzgg(zznc);
        if (zznc.zzs != zznc.zzt) {
            zznc.zzj().zzg().zza("Not all upload components initialized", Integer.valueOf(zznc.zzs), Integer.valueOf(zznc.zzt));
        }
        zznc.zzn = true;
    }

    private zznc(zznm zznm) {
        this(zznm, (zzhj) null);
    }

    private zznc(zznm zznm, zzhj zzhj) {
        this.zzn = false;
        this.zzr = new HashSet();
        this.zzah = new zznf(this);
        Preconditions.checkNotNull(zznm);
        this.zzm = zzhj.zza(zznm.zza, (zzdo) null, (Long) null);
        this.zzab = -1;
        this.zzk = new zzna(this);
        zznl zznl = new zznl(this);
        zznl.zzam();
        this.zzh = zznl;
        zzfz zzfz = new zzfz(this);
        zzfz.zzam();
        this.zzc = zzfz;
        zzgt zzgt = new zzgt(this);
        zzgt.zzam();
        this.zzb = zzgt;
        this.zzac = new HashMap();
        this.zzad = new HashMap();
        this.zzae = new HashMap();
        zzl().zzb((Runnable) new zznb(this, zznm));
    }

    /* access modifiers changed from: package-private */
    public final void zza(Runnable runnable) {
        zzl().zzt();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public final void zzr() {
        zzl().zzt();
        zzs();
        if (!this.zzo) {
            this.zzo = true;
            if (zzad()) {
                int zza2 = zza(this.zzy);
                int zzab2 = this.zzm.zzh().zzab();
                zzl().zzt();
                if (zza2 > zzab2) {
                    zzj().zzg().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                } else if (zza2 >= zzab2) {
                } else {
                    if (zza(zzab2, this.zzy)) {
                        zzj().zzp().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    } else {
                        zzj().zzg().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzs() {
        if (!this.zzn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zza(zzfn.zzk.zza zza2, zzin zzin) {
        if (!zzin.zzi()) {
            zza2.zzq();
            zza2.zzn();
            zza2.zzk();
        }
        if (!zzin.zzj()) {
            zza2.zzh();
            zza2.zzr();
        }
    }

    private final void zzaa() {
        zzl().zzt();
        if (this.zzu || this.zzv || this.zzw) {
            zzj().zzp().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv), Boolean.valueOf(this.zzw));
            return;
        }
        zzj().zzp().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            ((List) Preconditions.checkNotNull(this.zzq)).clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzfn.zzk.zza zza2) {
        int zza3;
        int indexOf;
        Set<String> zzg2 = zzi().zzg(str);
        if (zzg2 != null) {
            zza2.zzd((Iterable<String>) zzg2);
        }
        if (zzi().zzq(str)) {
            zza2.zzj();
        }
        if (zzi().zzt(str)) {
            String zzy2 = zza2.zzy();
            if (!TextUtils.isEmpty(zzy2) && (indexOf = zzy2.indexOf(Consts.DOT)) != -1) {
                zza2.zzo(zzy2.substring(0, indexOf));
            }
        }
        if (zzi().zzu(str) && (zza3 = zznl.zza(zza2, "_id")) != -1) {
            zza2.zzc(zza3);
        }
        if (zzi().zzs(str)) {
            zza2.zzk();
        }
        if (zzi().zzp(str)) {
            zza2.zzh();
            if (!zznk.zza() || !zze().zza(zzbf.zzcv) || zzb(str).zzj()) {
                zzb zzb2 = this.zzae.get(str);
                if (zzb2 == null || zzb2.zzb + zze().zzc(str, zzbf.zzau) < zzb().elapsedRealtime()) {
                    zzb2 = new zzb();
                    this.zzae.put(str, zzb2);
                }
                zza2.zzk(zzb2.zza);
            }
        }
        if (zzi().zzr(str)) {
            zza2.zzr();
        }
    }

    private final void zzb(zzg zzg2) {
        zzl().zzt();
        if (!TextUtils.isEmpty(zzg2.zzah()) || !TextUtils.isEmpty(zzg2.zzaa())) {
            Uri.Builder builder = new Uri.Builder();
            String zzah2 = zzg2.zzah();
            if (TextUtils.isEmpty(zzah2)) {
                zzah2 = zzg2.zzaa();
            }
            ArrayMap arrayMap = null;
            Uri.Builder encodedAuthority = builder.scheme(zzbf.zze.zza(null)).encodedAuthority(zzbf.zzf.zza(null));
            encodedAuthority.path("config/app/" + zzah2).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "97001").appendQueryParameter("runtime_version", SessionDescription.SUPPORTED_SDP_VERSION);
            String uri = builder.build().toString();
            try {
                String str = (String) Preconditions.checkNotNull(zzg2.zzac());
                URL url = new URL(uri);
                zzj().zzp().zza("Fetching remote configuration", str);
                zzfi.zzd zzc2 = zzi().zzc(str);
                String zze2 = zzi().zze(str);
                if (zzc2 != null) {
                    if (!TextUtils.isEmpty(zze2)) {
                        arrayMap = new ArrayMap();
                        arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zze2);
                    }
                    String zzd2 = zzi().zzd(str);
                    if (!TextUtils.isEmpty(zzd2)) {
                        if (arrayMap == null) {
                            arrayMap = new ArrayMap();
                        }
                        arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzd2);
                    }
                }
                this.zzu = true;
                zzfz zzh2 = zzh();
                zznd zznd = new zznd(this);
                zzh2.zzt();
                zzh2.zzal();
                Preconditions.checkNotNull(url);
                Preconditions.checkNotNull(zznd);
                zzh2.zzl().zza((Runnable) new zzgd(zzh2, str, url, (byte[]) null, arrayMap, zznd));
            } catch (MalformedURLException unused) {
                zzj().zzg().zza("Failed to parse config URL. Not fetching. appId", zzfw.zza(zzg2.zzac()), uri);
            }
        } else {
            zza((String) Preconditions.checkNotNull(zzg2.zzac()), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzg zzg2, zzfn.zzk.zza zza2) {
        zzfn.zzo zzo2;
        zzl().zzt();
        zzs();
        zzah zza3 = zzah.zza(zza2.zzv());
        if (!zzne.zza() || !zze().zza(zzbf.zzcp)) {
            String zzac2 = zzg2.zzac();
            zzl().zzt();
            zzs();
            zzin zzb2 = zzb(zzac2);
            if (zzb2.zze() != null) {
                zza3.zza(zzin.zza.AD_STORAGE, zzb2.zza());
            } else {
                zza3.zza(zzin.zza.AD_STORAGE, zzak.FAILSAFE);
            }
            if (zzb2.zzf() != null) {
                zza3.zza(zzin.zza.ANALYTICS_STORAGE, zzb2.zza());
            } else {
                zza3.zza(zzin.zza.ANALYTICS_STORAGE, zzak.FAILSAFE);
            }
        } else {
            String zzac3 = zzg2.zzac();
            zzl().zzt();
            zzs();
            zzin zzb3 = zzb(zzac3);
            int i = zznh.zza[zzb3.zzc().ordinal()];
            if (i == 1) {
                zza3.zza(zzin.zza.AD_STORAGE, zzak.REMOTE_ENFORCED_DEFAULT);
            } else if (i == 2 || i == 3) {
                zza3.zza(zzin.zza.AD_STORAGE, zzb3.zza());
            } else {
                zza3.zza(zzin.zza.AD_STORAGE, zzak.FAILSAFE);
            }
            int i2 = zznh.zza[zzb3.zzd().ordinal()];
            if (i2 == 1) {
                zza3.zza(zzin.zza.ANALYTICS_STORAGE, zzak.REMOTE_ENFORCED_DEFAULT);
            } else if (i2 == 2 || i2 == 3) {
                zza3.zza(zzin.zza.ANALYTICS_STORAGE, zzb3.zza());
            } else {
                zza3.zza(zzin.zza.ANALYTICS_STORAGE, zzak.FAILSAFE);
            }
        }
        String zzac4 = zzg2.zzac();
        zzl().zzt();
        zzs();
        zzav zza4 = zza(zzac4, zzd(zzac4), zzb(zzac4), zza3);
        zza2.zzb(((Boolean) Preconditions.checkNotNull(zza4.zzd())).booleanValue());
        if (!TextUtils.isEmpty(zza4.zze())) {
            zza2.zzh(zza4.zze());
        }
        zzl().zzt();
        zzs();
        Iterator<zzfn.zzo> it = zza2.zzab().iterator();
        while (true) {
            if (!it.hasNext()) {
                zzo2 = null;
                break;
            }
            zzo2 = it.next();
            if ("_npa".equals(zzo2.zzg())) {
                break;
            }
        }
        if (zzo2 == null) {
            int zza5 = zza(zzg2.zzac(), zza3);
            zza2.zza((zzfn.zzo) ((zzjk) zzfn.zzo.zze().zza("_npa").zzb(zzb().currentTimeMillis()).zza((long) zza5).zzai()));
            zzj().zzp().zza("Setting user property", "non_personalized_ads(_npa)", Integer.valueOf(zza5));
        } else if (zza3.zza(zzin.zza.AD_PERSONALIZATION) == zzak.UNSET) {
            zznq zze2 = zzf().zze(zzg2.zzac(), "_npa");
            if (zze2 == null) {
                Boolean zzx2 = zzg2.zzx();
                if (zzx2 == null || ((zzx2 == Boolean.TRUE && zzo2.zzc() != 1) || (zzx2 == Boolean.FALSE && zzo2.zzc() != 0))) {
                    zza3.zza(zzin.zza.AD_PERSONALIZATION, zzak.API);
                } else {
                    zza3.zza(zzin.zza.AD_PERSONALIZATION, zzak.MANIFEST);
                }
            } else if ("tcf".equals(zze2.zzb)) {
                zza3.zza(zzin.zza.AD_PERSONALIZATION, zzak.TCF);
            } else if ("app".equals(zze2.zzb)) {
                zza3.zza(zzin.zza.AD_PERSONALIZATION, zzak.API);
            } else {
                zza3.zza(zzin.zza.AD_PERSONALIZATION, zzak.MANIFEST);
            }
        }
        zza2.zzf(zza3.toString());
        boolean zzn2 = this.zzb.zzn(zzg2.zzac());
        List<zzfn.zzf> zzaa2 = zza2.zzaa();
        int i3 = 0;
        for (int i4 = 0; i4 < zzaa2.size(); i4++) {
            if ("_tcf".equals(zzaa2.get(i4).zzg())) {
                zzjk.zzb zzcc = zzaa2.get(i4).zzcc();
                zzjk.zzb zzb4 = zzcc;
                zzfn.zzf.zza zza6 = (zzfn.zzf.zza) zzcc;
                List<zzfn.zzh> zzf2 = zza6.zzf();
                while (true) {
                    if (i3 >= zzf2.size()) {
                        break;
                    } else if ("_tcfd".equals(zzf2.get(i3).zzg())) {
                        zza6.zza(i3, zzfn.zzh.zze().zza("_tcfd").zzb(zzms.zza(zzf2.get(i3).zzh(), zzn2)));
                        break;
                    } else {
                        i3++;
                    }
                }
                zza2.zza(i4, zza6);
                return;
            }
        }
    }

    private static void zza(zzfn.zzf.zza zza2, int i, String str) {
        List<zzfn.zzh> zzf2 = zza2.zzf();
        int i2 = 0;
        while (i2 < zzf2.size()) {
            if (!"_err".equals(zzf2.get(i2).zzg())) {
                i2++;
            } else {
                return;
            }
        }
        zzfn.zzh.zza zza3 = zzfn.zzh.zze().zza("_err");
        long j = (long) i;
        Long.valueOf(j).getClass();
        zza2.zza((zzfn.zzh) ((zzjk) zza3.zza(j).zzai())).zza((zzfn.zzh) ((zzjk) zzfn.zzh.zze().zza("_ev").zzb(str).zzai()));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002b, code lost:
        r4 = r1.zzag;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzbd r20, com.google.android.gms.measurement.internal.zzo r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r2 = r0.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            com.google.android.gms.measurement.internal.zzhc r2 = r19.zzl()
            r2.zzt()
            r19.zzs()
            java.lang.String r2 = r0.zza
            r3 = r20
            long r10 = r3.zzd
            com.google.android.gms.measurement.internal.zzga r3 = com.google.android.gms.measurement.internal.zzga.zza(r20)
            com.google.android.gms.measurement.internal.zzhc r4 = r19.zzl()
            r4.zzt()
            com.google.android.gms.measurement.internal.zzkp r4 = r1.zzaf
            if (r4 == 0) goto L_0x0039
            java.lang.String r4 = r1.zzag
            if (r4 == 0) goto L_0x0039
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0036
            goto L_0x0039
        L_0x0036:
            com.google.android.gms.measurement.internal.zzkp r4 = r1.zzaf
            goto L_0x003a
        L_0x0039:
            r4 = 0
        L_0x003a:
            android.os.Bundle r5 = r3.zzb
            r12 = 0
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zzkp) r4, (android.os.Bundle) r5, (boolean) r12)
            com.google.android.gms.measurement.internal.zzbd r3 = r3.zza()
            r19.zzp()
            boolean r4 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.measurement.internal.zzbd) r3, (com.google.android.gms.measurement.internal.zzo) r0)
            if (r4 != 0) goto L_0x004e
            return
        L_0x004e:
            boolean r4 = r0.zzh
            if (r4 != 0) goto L_0x0056
            r1.zza((com.google.android.gms.measurement.internal.zzo) r0)
            return
        L_0x0056:
            java.util.List<java.lang.String> r4 = r0.zzs
            if (r4 == 0) goto L_0x0099
            java.util.List<java.lang.String> r4 = r0.zzs
            java.lang.String r5 = r3.zza
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzbc r4 = r3.zzb
            android.os.Bundle r4 = r4.zzb()
            java.lang.String r5 = "ga_safelisted"
            r6 = 1
            r4.putLong(r5, r6)
            com.google.android.gms.measurement.internal.zzbd r5 = new com.google.android.gms.measurement.internal.zzbd
            java.lang.String r14 = r3.zza
            com.google.android.gms.measurement.internal.zzbc r15 = new com.google.android.gms.measurement.internal.zzbc
            r15.<init>(r4)
            java.lang.String r4 = r3.zzc
            long r6 = r3.zzd
            r13 = r5
            r16 = r4
            r17 = r6
            r13.<init>(r14, r15, r16, r17)
            goto L_0x009a
        L_0x0087:
            com.google.android.gms.measurement.internal.zzfw r0 = r19.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzc()
            java.lang.String r4 = r3.zza
            java.lang.String r3 = r3.zzc
            java.lang.String r5 = "Dropping non-safelisted event. appId, event name, origin"
            r0.zza(r5, r2, r4, r3)
            return
        L_0x0099:
            r13 = r3
        L_0x009a:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()
            r3.zzp()
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02ee }
            r3.zzt()     // Catch:{ all -> 0x02ee }
            r3.zzal()     // Catch:{ all -> 0x02ee }
            r4 = 0
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x00ce
            com.google.android.gms.measurement.internal.zzfw r3 = r3.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()     // Catch:{ all -> 0x02ee }
            java.lang.String r5 = "Invalid time querying timed out conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r2)     // Catch:{ all -> 0x02ee }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02ee }
            r3.zza(r5, r6, r7)     // Catch:{ all -> 0x02ee }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02ee }
            goto L_0x00dc
        L_0x00ce:
            java.lang.String r5 = "active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02ee }
            java.lang.String[] r6 = new java.lang.String[]{r2, r6}     // Catch:{ all -> 0x02ee }
            java.util.List r3 = r3.zza((java.lang.String) r5, (java.lang.String[]) r6)     // Catch:{ all -> 0x02ee }
        L_0x00dc:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x02ee }
        L_0x00e0:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x02ee }
            if (r5 == 0) goto L_0x012b
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzae r5 = (com.google.android.gms.measurement.internal.zzae) r5     // Catch:{ all -> 0x02ee }
            if (r5 == 0) goto L_0x00e0
            com.google.android.gms.measurement.internal.zzfw r6 = r19.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzp()     // Catch:{ all -> 0x02ee }
            java.lang.String r7 = "User property timed out"
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfr r9 = r9.zzk()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r14 = r5.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r14 = r14.zza     // Catch:{ all -> 0x02ee }
            java.lang.String r9 = r9.zzc(r14)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r14 = r5.zzc     // Catch:{ all -> 0x02ee }
            java.lang.Object r14 = r14.zza()     // Catch:{ all -> 0x02ee }
            r6.zza(r7, r8, r9, r14)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzbd r6 = r5.zzg     // Catch:{ all -> 0x02ee }
            if (r6 == 0) goto L_0x011f
            com.google.android.gms.measurement.internal.zzbd r6 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzbd r7 = r5.zzg     // Catch:{ all -> 0x02ee }
            r6.<init>(r7, r10)     // Catch:{ all -> 0x02ee }
            r1.zzc(r6, r0)     // Catch:{ all -> 0x02ee }
        L_0x011f:
            com.google.android.gms.measurement.internal.zzal r6 = r19.zzf()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r5 = r5.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x02ee }
            r6.zza((java.lang.String) r2, (java.lang.String) r5)     // Catch:{ all -> 0x02ee }
            goto L_0x00e0
        L_0x012b:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02ee }
            r3.zzt()     // Catch:{ all -> 0x02ee }
            r3.zzal()     // Catch:{ all -> 0x02ee }
            if (r4 >= 0) goto L_0x0154
            com.google.android.gms.measurement.internal.zzfw r3 = r3.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()     // Catch:{ all -> 0x02ee }
            java.lang.String r5 = "Invalid time querying expired conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r2)     // Catch:{ all -> 0x02ee }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02ee }
            r3.zza(r5, r6, r7)     // Catch:{ all -> 0x02ee }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02ee }
            goto L_0x0162
        L_0x0154:
            java.lang.String r5 = "active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02ee }
            java.lang.String[] r6 = new java.lang.String[]{r2, r6}     // Catch:{ all -> 0x02ee }
            java.util.List r3 = r3.zza((java.lang.String) r5, (java.lang.String[]) r6)     // Catch:{ all -> 0x02ee }
        L_0x0162:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x02ee }
            int r6 = r3.size()     // Catch:{ all -> 0x02ee }
            r5.<init>(r6)     // Catch:{ all -> 0x02ee }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x02ee }
        L_0x016f:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x02ee }
            if (r6 == 0) goto L_0x01c0
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzae r6 = (com.google.android.gms.measurement.internal.zzae) r6     // Catch:{ all -> 0x02ee }
            if (r6 == 0) goto L_0x016f
            com.google.android.gms.measurement.internal.zzfw r7 = r19.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ all -> 0x02ee }
            java.lang.String r8 = "User property expired"
            java.lang.String r9 = r6.zza     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzhj r14 = r1.zzm     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfr r14 = r14.zzk()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r15 = r6.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r15 = r15.zza     // Catch:{ all -> 0x02ee }
            java.lang.String r14 = r14.zzc(r15)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r15 = r6.zzc     // Catch:{ all -> 0x02ee }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x02ee }
            r7.zza(r8, r9, r14, r15)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzal r7 = r19.zzf()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r8 = r6.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r8 = r8.zza     // Catch:{ all -> 0x02ee }
            r7.zzh(r2, r8)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzbd r7 = r6.zzk     // Catch:{ all -> 0x02ee }
            if (r7 == 0) goto L_0x01b4
            com.google.android.gms.measurement.internal.zzbd r7 = r6.zzk     // Catch:{ all -> 0x02ee }
            r5.add(r7)     // Catch:{ all -> 0x02ee }
        L_0x01b4:
            com.google.android.gms.measurement.internal.zzal r7 = r19.zzf()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzno r6 = r6.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r6 = r6.zza     // Catch:{ all -> 0x02ee }
            r7.zza((java.lang.String) r2, (java.lang.String) r6)     // Catch:{ all -> 0x02ee }
            goto L_0x016f
        L_0x01c0:
            r3 = r5
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x02ee }
            int r3 = r5.size()     // Catch:{ all -> 0x02ee }
            r6 = r12
        L_0x01c8:
            if (r6 >= r3) goto L_0x01db
            java.lang.Object r7 = r5.get(r6)     // Catch:{ all -> 0x02ee }
            int r6 = r6 + 1
            com.google.android.gms.measurement.internal.zzbd r7 = (com.google.android.gms.measurement.internal.zzbd) r7     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzbd r8 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x02ee }
            r8.<init>(r7, r10)     // Catch:{ all -> 0x02ee }
            r1.zzc(r8, r0)     // Catch:{ all -> 0x02ee }
            goto L_0x01c8
        L_0x01db:
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02ee }
            java.lang.String r5 = r13.zza     // Catch:{ all -> 0x02ee }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x02ee }
            r3.zzt()     // Catch:{ all -> 0x02ee }
            r3.zzal()     // Catch:{ all -> 0x02ee }
            if (r4 >= 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzfw r4 = r3.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzu()     // Catch:{ all -> 0x02ee }
            java.lang.String r6 = "Invalid time querying triggered conditional properties"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r2)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfr r3 = r3.zzi()     // Catch:{ all -> 0x02ee }
            java.lang.String r3 = r3.zza((java.lang.String) r5)     // Catch:{ all -> 0x02ee }
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02ee }
            r4.zza(r6, r2, r3, r5)     // Catch:{ all -> 0x02ee }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x02ee }
            goto L_0x021f
        L_0x0211:
            java.lang.String r4 = "active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x02ee }
            java.lang.String[] r2 = new java.lang.String[]{r2, r5, r6}     // Catch:{ all -> 0x02ee }
            java.util.List r2 = r3.zza((java.lang.String) r4, (java.lang.String[]) r2)     // Catch:{ all -> 0x02ee }
        L_0x021f:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x02ee }
            int r3 = r2.size()     // Catch:{ all -> 0x02ee }
            r14.<init>(r3)     // Catch:{ all -> 0x02ee }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x02ee }
        L_0x022c:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x02ee }
            if (r3 == 0) goto L_0x02c1
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x02ee }
            r15 = r3
            com.google.android.gms.measurement.internal.zzae r15 = (com.google.android.gms.measurement.internal.zzae) r15     // Catch:{ all -> 0x02ee }
            if (r15 == 0) goto L_0x022c
            com.google.android.gms.measurement.internal.zzno r3 = r15.zzc     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zznq r9 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ all -> 0x02ee }
            java.lang.String r4 = r15.zza     // Catch:{ all -> 0x02ee }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x02ee }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x02ee }
            java.lang.String r5 = r15.zzb     // Catch:{ all -> 0x02ee }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x02ee }
            java.lang.Object r3 = r3.zza()     // Catch:{ all -> 0x02ee }
            java.lang.Object r16 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x02ee }
            r3 = r9
            r7 = r10
            r12 = r9
            r9 = r16
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02ee }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zznq) r12)     // Catch:{ all -> 0x02ee }
            if (r3 == 0) goto L_0x0283
            com.google.android.gms.measurement.internal.zzfw r3 = r19.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzp()     // Catch:{ all -> 0x02ee }
            java.lang.String r4 = "User property triggered"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzhj r6 = r1.zzm     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfr r6 = r6.zzk()     // Catch:{ all -> 0x02ee }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x02ee }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x02ee }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x02ee }
            goto L_0x02a4
        L_0x0283:
            com.google.android.gms.measurement.internal.zzfw r3 = r19.zzj()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x02ee }
            java.lang.String r4 = "Too many active user properties, ignoring"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x02ee }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r5)     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzhj r6 = r1.zzm     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzfr r6 = r6.zzk()     // Catch:{ all -> 0x02ee }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x02ee }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x02ee }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x02ee }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x02ee }
        L_0x02a4:
            com.google.android.gms.measurement.internal.zzbd r3 = r15.zzi     // Catch:{ all -> 0x02ee }
            if (r3 == 0) goto L_0x02ad
            com.google.android.gms.measurement.internal.zzbd r3 = r15.zzi     // Catch:{ all -> 0x02ee }
            r14.add(r3)     // Catch:{ all -> 0x02ee }
        L_0x02ad:
            com.google.android.gms.measurement.internal.zzno r3 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ all -> 0x02ee }
            r3.<init>(r12)     // Catch:{ all -> 0x02ee }
            r15.zzc = r3     // Catch:{ all -> 0x02ee }
            r3 = 1
            r15.zze = r3     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzal r3 = r19.zzf()     // Catch:{ all -> 0x02ee }
            r3.zza((com.google.android.gms.measurement.internal.zzae) r15)     // Catch:{ all -> 0x02ee }
            r12 = 0
            goto L_0x022c
        L_0x02c1:
            r1.zzc(r13, r0)     // Catch:{ all -> 0x02ee }
            r2 = r14
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x02ee }
            int r2 = r14.size()     // Catch:{ all -> 0x02ee }
            r12 = 0
        L_0x02cc:
            if (r12 >= r2) goto L_0x02df
            java.lang.Object r3 = r14.get(r12)     // Catch:{ all -> 0x02ee }
            int r12 = r12 + 1
            com.google.android.gms.measurement.internal.zzbd r3 = (com.google.android.gms.measurement.internal.zzbd) r3     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzbd r4 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x02ee }
            r4.<init>(r3, r10)     // Catch:{ all -> 0x02ee }
            r1.zzc(r4, r0)     // Catch:{ all -> 0x02ee }
            goto L_0x02cc
        L_0x02df:
            com.google.android.gms.measurement.internal.zzal r0 = r19.zzf()     // Catch:{ all -> 0x02ee }
            r0.zzw()     // Catch:{ all -> 0x02ee }
            com.google.android.gms.measurement.internal.zzal r0 = r19.zzf()
            r0.zzu()
            return
        L_0x02ee:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzal r2 = r19.zzf()
            r2.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zza(com.google.android.gms.measurement.internal.zzbd, com.google.android.gms.measurement.internal.zzo):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzbd zzbd, String str) {
        zzbd zzbd2 = zzbd;
        String str2 = str;
        zzg zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping event", str2);
            return;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null) {
            if (!"_ui".equals(zzbd2.zza)) {
                zzj().zzu().zza("Could not find package. appId", zzfw.zza(str));
            }
        } else if (!zza2.booleanValue()) {
            zzj().zzg().zza("App version does not match; dropping event. appId", zzfw.zza(str));
            return;
        }
        zzo zzo2 = r2;
        zzo zzo3 = new zzo(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zze2.zzag(), zze2.zzd(), 0, 0, zze2.zzaq(), false, zze2.zzaa(), zze2.zzx(), zze2.zzo(), zze2.zzan(), (String) null, zzb(str2).zzh(), "", (String) null, zze2.zzat(), zze2.zzw(), zzb(str2).zza(), zzd(str2).zzf(), zze2.zza(), zze2.zzf(), zze2.zzam(), zze2.zzak());
        zzb(zzbd2, zzo2);
    }

    private final void zzb(zzbd zzbd, zzo zzo2) {
        Preconditions.checkNotEmpty(zzo2.zza);
        zzga zza2 = zzga.zza(zzbd);
        zzq().zza(zza2.zzb, zzf().zzd(zzo2.zza));
        zzq().zza(zza2, zze().zzb(zzo2.zza));
        zzbd zza3 = zza2.zza();
        if ("_cmp".equals(zza3.zza) && "referrer API v2".equals(zza3.zzb.zzd("_cis"))) {
            String zzd2 = zza3.zzb.zzd("gclid");
            if (!TextUtils.isEmpty(zzd2)) {
                zza(new zzno("_lgclid", zza3.zzd, zzd2, "auto"), zzo2);
            }
        }
        zza(zza3, zzo2);
    }

    private final void zza(zzfn.zzk.zza zza2, long j, boolean z) {
        String str;
        zznq zznq;
        String str2;
        if (z) {
            str = "_se";
        } else {
            str = "_lte";
        }
        zznq zze2 = zzf().zze(zza2.zzt(), str);
        if (zze2 == null || zze2.zze == null) {
            zznq = new zznq(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(j));
        } else {
            zznq = new zznq(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(((Long) zze2.zze).longValue() + j));
        }
        zzfn.zzo zzo2 = (zzfn.zzo) ((zzjk) zzfn.zzo.zze().zza(str).zzb(zzb().currentTimeMillis()).zza(((Long) zznq.zze).longValue()).zzai());
        int zza3 = zznl.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzo2);
        } else {
            zza2.zza(zzo2);
        }
        if (j > 0) {
            zzf().zza(zznq);
            if (z) {
                str2 = "session-scoped";
            } else {
                str2 = "lifetime";
            }
            zzj().zzp().zza("Updated engagement user property. scope, value", str2, zznq.zze);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzt() {
        this.zzt++;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013f A[Catch:{ all -> 0x018e, all -> 0x0197 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014d A[Catch:{ all -> 0x018e, all -> 0x0197 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0173 A[Catch:{ all -> 0x018e, all -> 0x0197 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0177 A[Catch:{ all -> 0x018e, all -> 0x0197 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzhc r0 = r6.zzl()
            r0.zzt()
            r6.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0012
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0197 }
        L_0x0012:
            com.google.android.gms.measurement.internal.zzfw r1 = r6.zzj()     // Catch:{ all -> 0x0197 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()     // Catch:{ all -> 0x0197 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0197 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0197 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x0197 }
            com.google.android.gms.measurement.internal.zzal r1 = r6.zzf()     // Catch:{ all -> 0x0197 }
            r1.zzp()     // Catch:{ all -> 0x0197 }
            com.google.android.gms.measurement.internal.zzal r1 = r6.zzf()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzg r1 = r1.zze(r7)     // Catch:{ all -> 0x018e }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x003f
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x003f
            if (r8 != r3) goto L_0x0043
        L_0x003f:
            if (r9 != 0) goto L_0x0043
            r2 = 1
            goto L_0x0044
        L_0x0043:
            r2 = r0
        L_0x0044:
            if (r1 != 0) goto L_0x0059
            com.google.android.gms.measurement.internal.zzfw r8 = r6.zzj()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzu()     // Catch:{ all -> 0x018e }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r7)     // Catch:{ all -> 0x018e }
            r8.zza(r9, r7)     // Catch:{ all -> 0x018e }
            goto L_0x017a
        L_0x0059:
            r4 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00b5
            if (r8 != r4) goto L_0x0060
            goto L_0x00b5
        L_0x0060:
            com.google.android.gms.common.util.Clock r10 = r6.zzb()     // Catch:{ all -> 0x018e }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x018e }
            r1.zzm(r10)     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzal r10 = r6.zzf()     // Catch:{ all -> 0x018e }
            r10.zza((com.google.android.gms.measurement.internal.zzg) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzfw r10 = r6.zzj()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzfy r10 = r10.zzp()     // Catch:{ all -> 0x018e }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018e }
            r10.zza(r11, r1, r9)     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzgt r9 = r6.zzi()     // Catch:{ all -> 0x018e }
            r9.zzi(r7)     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzmc r7 = r6.zzj     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzgm r7 = r7.zzd     // Catch:{ all -> 0x018e }
            com.google.android.gms.common.util.Clock r9 = r6.zzb()     // Catch:{ all -> 0x018e }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x018e }
            r7.zza(r9)     // Catch:{ all -> 0x018e }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00a1
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b0
        L_0x00a1:
            com.google.android.gms.measurement.internal.zzmc r7 = r6.zzj     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzgm r7 = r7.zzb     // Catch:{ all -> 0x018e }
            com.google.android.gms.common.util.Clock r8 = r6.zzb()     // Catch:{ all -> 0x018e }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x018e }
            r7.zza(r8)     // Catch:{ all -> 0x018e }
        L_0x00b0:
            r6.zzab()     // Catch:{ all -> 0x018e }
            goto L_0x017a
        L_0x00b5:
            r9 = 0
            if (r11 == 0) goto L_0x00c1
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r2 = r11.get(r2)     // Catch:{ all -> 0x018e }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x018e }
            goto L_0x00c2
        L_0x00c1:
            r2 = r9
        L_0x00c2:
            if (r2 == 0) goto L_0x00d1
            boolean r5 = r2.isEmpty()     // Catch:{ all -> 0x018e }
            if (r5 != 0) goto L_0x00d1
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x018e }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x018e }
            goto L_0x00d2
        L_0x00d1:
            r2 = r9
        L_0x00d2:
            if (r11 == 0) goto L_0x00dd
            java.lang.String r5 = "ETag"
            java.lang.Object r11 = r11.get(r5)     // Catch:{ all -> 0x018e }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x018e }
            goto L_0x00de
        L_0x00dd:
            r11 = r9
        L_0x00de:
            if (r11 == 0) goto L_0x00ed
            boolean r5 = r11.isEmpty()     // Catch:{ all -> 0x018e }
            if (r5 != 0) goto L_0x00ed
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x018e }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x018e }
            goto L_0x00ee
        L_0x00ed:
            r11 = r9
        L_0x00ee:
            if (r8 == r4) goto L_0x010a
            if (r8 != r3) goto L_0x00f3
            goto L_0x010a
        L_0x00f3:
            com.google.android.gms.measurement.internal.zzgt r9 = r6.zzi()     // Catch:{ all -> 0x018e }
            boolean r9 = r9.zza(r7, r10, r2, r11)     // Catch:{ all -> 0x018e }
            if (r9 != 0) goto L_0x012b
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x0197 }
            r7.zzu()     // Catch:{ all -> 0x0197 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x010a:
            com.google.android.gms.measurement.internal.zzgt r11 = r6.zzi()     // Catch:{ all -> 0x018e }
            com.google.android.gms.internal.measurement.zzfi$zzd r11 = r11.zzc(r7)     // Catch:{ all -> 0x018e }
            if (r11 != 0) goto L_0x012b
            com.google.android.gms.measurement.internal.zzgt r11 = r6.zzi()     // Catch:{ all -> 0x018e }
            boolean r9 = r11.zza(r7, r9, r9, r9)     // Catch:{ all -> 0x018e }
            if (r9 != 0) goto L_0x012b
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x0197 }
            r7.zzu()     // Catch:{ all -> 0x0197 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x012b:
            com.google.android.gms.common.util.Clock r9 = r6.zzb()     // Catch:{ all -> 0x018e }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x018e }
            r1.zzd((long) r2)     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzal r9 = r6.zzf()     // Catch:{ all -> 0x018e }
            r9.zza((com.google.android.gms.measurement.internal.zzg) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x018e }
            if (r8 != r4) goto L_0x014d
            com.google.android.gms.measurement.internal.zzfw r8 = r6.zzj()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzv()     // Catch:{ all -> 0x018e }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zza(r9, r7)     // Catch:{ all -> 0x018e }
            goto L_0x0163
        L_0x014d:
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ all -> 0x018e }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x018e }
            int r10 = r10.length     // Catch:{ all -> 0x018e }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x018e }
            r7.zza(r9, r8, r10)     // Catch:{ all -> 0x018e }
        L_0x0163:
            com.google.android.gms.measurement.internal.zzfz r7 = r6.zzh()     // Catch:{ all -> 0x018e }
            boolean r7 = r7.zzu()     // Catch:{ all -> 0x018e }
            if (r7 == 0) goto L_0x0177
            boolean r7 = r6.zzac()     // Catch:{ all -> 0x018e }
            if (r7 == 0) goto L_0x0177
            r6.zzw()     // Catch:{ all -> 0x018e }
            goto L_0x017a
        L_0x0177:
            r6.zzab()     // Catch:{ all -> 0x018e }
        L_0x017a:
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x018e }
            r7.zzw()     // Catch:{ all -> 0x018e }
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x0197 }
            r7.zzu()     // Catch:{ all -> 0x0197 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x018e:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzal r8 = r6.zzf()     // Catch:{ all -> 0x0197 }
            r8.zzu()     // Catch:{ all -> 0x0197 }
            throw r7     // Catch:{ all -> 0x0197 }
        L_0x0197:
            r7 = move-exception
            r6.zzu = r0
            r6.zzaa()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzab();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c3 A[Catch:{ all -> 0x0141 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0133 A[Catch:{ SQLiteException -> 0x014a }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0137 A[Catch:{ SQLiteException -> 0x014a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r7, int r8, java.lang.Throwable r9, byte[] r10, java.lang.String r11) {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzhc r11 = r6.zzl()
            r11.zzt()
            r6.zzs()
            r11 = 0
            if (r10 != 0) goto L_0x000f
            byte[] r10 = new byte[r11]     // Catch:{ all -> 0x01bd }
        L_0x000f:
            java.util.List<java.lang.Long> r0 = r6.zzz     // Catch:{ all -> 0x01bd }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x01bd }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x01bd }
            r1 = 0
            r6.zzz = r1     // Catch:{ all -> 0x01bd }
            boolean r2 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ all -> 0x01bd }
            if (r2 == 0) goto L_0x002e
            com.google.android.gms.measurement.internal.zzag r2 = r6.zze()     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ all -> 0x01bd }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)     // Catch:{ all -> 0x01bd }
            if (r2 == 0) goto L_0x002e
            if (r7 == 0) goto L_0x0038
        L_0x002e:
            r2 = 200(0xc8, float:2.8E-43)
            if (r8 == r2) goto L_0x0036
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 != r2) goto L_0x0176
        L_0x0036:
            if (r9 != 0) goto L_0x0176
        L_0x0038:
            boolean r9 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ SQLiteException -> 0x014a }
            if (r9 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzag r9 = r6.zze()     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ SQLiteException -> 0x014a }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)     // Catch:{ SQLiteException -> 0x014a }
            if (r9 == 0) goto L_0x004c
            if (r7 == 0) goto L_0x005b
        L_0x004c:
            com.google.android.gms.measurement.internal.zzmc r9 = r6.zzj     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzgm r9 = r9.zzc     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.common.util.Clock r2 = r6.zzb()     // Catch:{ SQLiteException -> 0x014a }
            long r2 = r2.currentTimeMillis()     // Catch:{ SQLiteException -> 0x014a }
            r9.zza(r2)     // Catch:{ SQLiteException -> 0x014a }
        L_0x005b:
            com.google.android.gms.measurement.internal.zzmc r9 = r6.zzj     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzgm r9 = r9.zzd     // Catch:{ SQLiteException -> 0x014a }
            r2 = 0
            r9.zza(r2)     // Catch:{ SQLiteException -> 0x014a }
            r6.zzab()     // Catch:{ SQLiteException -> 0x014a }
            boolean r9 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ SQLiteException -> 0x014a }
            if (r9 == 0) goto L_0x009c
            com.google.android.gms.measurement.internal.zzag r9 = r6.zze()     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ SQLiteException -> 0x014a }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r4)     // Catch:{ SQLiteException -> 0x014a }
            if (r9 == 0) goto L_0x009c
            if (r7 == 0) goto L_0x007c
            goto L_0x009c
        L_0x007c:
            boolean r7 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ SQLiteException -> 0x014a }
            if (r7 == 0) goto L_0x00b2
            com.google.android.gms.measurement.internal.zzag r7 = r6.zze()     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ SQLiteException -> 0x014a }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x014a }
            if (r7 == 0) goto L_0x00b2
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ SQLiteException -> 0x014a }
            java.lang.String r8 = "Purged empty bundles"
            r7.zza(r8)     // Catch:{ SQLiteException -> 0x014a }
            goto L_0x00b2
        L_0x009c:
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ SQLiteException -> 0x014a }
            java.lang.String r9 = "Successful upload. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ SQLiteException -> 0x014a }
            int r10 = r10.length     // Catch:{ SQLiteException -> 0x014a }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ SQLiteException -> 0x014a }
            r7.zza(r9, r8, r10)     // Catch:{ SQLiteException -> 0x014a }
        L_0x00b2:
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ SQLiteException -> 0x014a }
            r7.zzp()     // Catch:{ SQLiteException -> 0x014a }
            java.util.Iterator r7 = r0.iterator()     // Catch:{ all -> 0x0141 }
        L_0x00bd:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x0141 }
            if (r8 == 0) goto L_0x0113
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x0141 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0141 }
            com.google.android.gms.measurement.internal.zzal r9 = r6.zzf()     // Catch:{ SQLiteException -> 0x0106 }
            long r4 = r8.longValue()     // Catch:{ SQLiteException -> 0x0106 }
            r9.zzt()     // Catch:{ SQLiteException -> 0x0106 }
            r9.zzal()     // Catch:{ SQLiteException -> 0x0106 }
            android.database.sqlite.SQLiteDatabase r10 = r9.e_()     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r0 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r4 = "queue"
            java.lang.String r5 = "rowid=?"
            int r10 = r10.delete(r4, r5, r0)     // Catch:{ SQLiteException -> 0x00f7 }
            r0 = 1
            if (r10 != r0) goto L_0x00ef
            goto L_0x00bd
        L_0x00ef:
            android.database.sqlite.SQLiteException r10 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x00f7 }
            java.lang.String r0 = "Deleted fewer rows from queue than expected"
            r10.<init>(r0)     // Catch:{ SQLiteException -> 0x00f7 }
            throw r10     // Catch:{ SQLiteException -> 0x00f7 }
        L_0x00f7:
            r10 = move-exception
            com.google.android.gms.measurement.internal.zzfw r9 = r9.zzj()     // Catch:{ SQLiteException -> 0x0106 }
            com.google.android.gms.measurement.internal.zzfy r9 = r9.zzg()     // Catch:{ SQLiteException -> 0x0106 }
            java.lang.String r0 = "Failed to delete a bundle in a queue table"
            r9.zza(r0, r10)     // Catch:{ SQLiteException -> 0x0106 }
            throw r10     // Catch:{ SQLiteException -> 0x0106 }
        L_0x0106:
            r9 = move-exception
            java.util.List<java.lang.Long> r10 = r6.zzaa     // Catch:{ all -> 0x0141 }
            if (r10 == 0) goto L_0x0112
            boolean r8 = r10.contains(r8)     // Catch:{ all -> 0x0141 }
            if (r8 == 0) goto L_0x0112
            goto L_0x00bd
        L_0x0112:
            throw r9     // Catch:{ all -> 0x0141 }
        L_0x0113:
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x0141 }
            r7.zzw()     // Catch:{ all -> 0x0141 }
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ SQLiteException -> 0x014a }
            r7.zzu()     // Catch:{ SQLiteException -> 0x014a }
            r6.zzaa = r1     // Catch:{ SQLiteException -> 0x014a }
            com.google.android.gms.measurement.internal.zzfz r7 = r6.zzh()     // Catch:{ SQLiteException -> 0x014a }
            boolean r7 = r7.zzu()     // Catch:{ SQLiteException -> 0x014a }
            if (r7 == 0) goto L_0x0137
            boolean r7 = r6.zzac()     // Catch:{ SQLiteException -> 0x014a }
            if (r7 == 0) goto L_0x0137
            r6.zzw()     // Catch:{ SQLiteException -> 0x014a }
            goto L_0x013e
        L_0x0137:
            r7 = -1
            r6.zzab = r7     // Catch:{ SQLiteException -> 0x014a }
            r6.zzab()     // Catch:{ SQLiteException -> 0x014a }
        L_0x013e:
            r6.zzp = r2     // Catch:{ SQLiteException -> 0x014a }
            goto L_0x01b7
        L_0x0141:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzal r8 = r6.zzf()     // Catch:{ SQLiteException -> 0x014a }
            r8.zzu()     // Catch:{ SQLiteException -> 0x014a }
            throw r7     // Catch:{ SQLiteException -> 0x014a }
        L_0x014a:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfw r8 = r6.zzj()     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ all -> 0x01bd }
            java.lang.String r9 = "Database error while trying to delete uploaded bundles"
            r8.zza(r9, r7)     // Catch:{ all -> 0x01bd }
            com.google.android.gms.common.util.Clock r7 = r6.zzb()     // Catch:{ all -> 0x01bd }
            long r7 = r7.elapsedRealtime()     // Catch:{ all -> 0x01bd }
            r6.zzp = r7     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ all -> 0x01bd }
            java.lang.String r8 = "Disable upload, time"
            long r9 = r6.zzp     // Catch:{ all -> 0x01bd }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01bd }
            r7.zza(r8, r9)     // Catch:{ all -> 0x01bd }
            goto L_0x01b7
        L_0x0176:
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzp()     // Catch:{ all -> 0x01bd }
            java.lang.String r10 = "Network upload failed. Will retry later. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x01bd }
            r7.zza(r10, r1, r9)     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzmc r7 = r6.zzj     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzgm r7 = r7.zzd     // Catch:{ all -> 0x01bd }
            com.google.android.gms.common.util.Clock r9 = r6.zzb()     // Catch:{ all -> 0x01bd }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x01bd }
            r7.zza(r9)     // Catch:{ all -> 0x01bd }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x019e
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x01ad
        L_0x019e:
            com.google.android.gms.measurement.internal.zzmc r7 = r6.zzj     // Catch:{ all -> 0x01bd }
            com.google.android.gms.measurement.internal.zzgm r7 = r7.zzb     // Catch:{ all -> 0x01bd }
            com.google.android.gms.common.util.Clock r8 = r6.zzb()     // Catch:{ all -> 0x01bd }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x01bd }
            r7.zza(r8)     // Catch:{ all -> 0x01bd }
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzal r7 = r6.zzf()     // Catch:{ all -> 0x01bd }
            r7.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ all -> 0x01bd }
            r6.zzab()     // Catch:{ all -> 0x01bd }
        L_0x01b7:
            r6.zzv = r11
            r6.zzaa()
            return
        L_0x01bd:
            r7 = move-exception
            r6.zzv = r11
            r6.zzaa()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zza(boolean, int, java.lang.Throwable, byte[], java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzg zzg2, zzfn.zzk.zza zza2) {
        zzl().zzt();
        zzs();
        zzfn.zza.C0012zza zzc2 = zzfn.zza.zzc();
        byte[] zzav = zzg2.zzav();
        if (zzav != null) {
            try {
                zzc2 = (zzfn.zza.C0012zza) zznl.zza(zzc2, zzav);
            } catch (zzjs unused) {
                zzj().zzu().zza("Failed to parse locally stored ad campaign info. appId", zzfw.zza(zzg2.zzac()));
            }
        }
        for (zzfn.zzf next : zza2.zzaa()) {
            if (next.zzg().equals("_cmp")) {
                String str = (String) zznl.zza(next, "gclid", (Object) "");
                String str2 = (String) zznl.zza(next, "gbraid", (Object) "");
                String str3 = (String) zznl.zza(next, "gad_source", (Object) "");
                if (!str.isEmpty() || (!str2.isEmpty() || !str3.isEmpty())) {
                    long longValue = ((Long) zznl.zza(next, "click_timestamp", (Object) 0L)).longValue();
                    if (longValue <= 0) {
                        longValue = next.zzd();
                    }
                    if ("referrer API v2".equals(zznl.zzb(next, "_cis"))) {
                        if (longValue > zzc2.zzb()) {
                            if (str.isEmpty()) {
                                zzc2.zzh();
                            } else {
                                zzc2.zzf(str);
                            }
                            if (str2.isEmpty()) {
                                zzc2.zzg();
                            } else {
                                zzc2.zze(str2);
                            }
                            if (str3.isEmpty()) {
                                zzc2.zzf();
                            } else {
                                zzc2.zzd(str3);
                            }
                            zzc2.zzb(longValue);
                        }
                    } else if (longValue > zzc2.zza()) {
                        if (str.isEmpty()) {
                            zzc2.zze();
                        } else {
                            zzc2.zzc(str);
                        }
                        if (str2.isEmpty()) {
                            zzc2.zzd();
                        } else {
                            zzc2.zzb(str2);
                        }
                        if (str3.isEmpty()) {
                            zzc2.zzc();
                        } else {
                            zzc2.zza(str3);
                        }
                        zzc2.zza(longValue);
                    }
                }
            }
        }
        if (!((zzfn.zza) ((zzjk) zzc2.zzai())).equals(zzfn.zza.zze())) {
            zza2.zza((zzfn.zza) ((zzjk) zzc2.zzai()));
        }
        zzg2.zza(((zzfn.zza) ((zzjk) zzc2.zzai())).zzbz());
        if (zzg2.zzas()) {
            zzf().zza(zzg2, false, false);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x03b2 A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x03dd A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03f4 A[SYNTHETIC, Splitter:B:128:0x03f4] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x04ac A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0518 A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x010b A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01ca A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0227 A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0234 A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0247 A[Catch:{ SQLiteException -> 0x01b5, all -> 0x0546 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.measurement.internal.zzo r24) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r6 = "com.android.vending"
            java.lang.String r0 = "_npa"
            java.lang.String r7 = "_uwa"
            java.lang.String r8 = "app_id=?"
            com.google.android.gms.measurement.internal.zzhc r9 = r23.zzl()
            r9.zzt()
            r23.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r24)
            java.lang.String r9 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            boolean r9 = zzh(r24)
            if (r9 != 0) goto L_0x002b
            return
        L_0x002b:
            com.google.android.gms.measurement.internal.zzal r9 = r23.zzf()
            java.lang.String r10 = r2.zza
            com.google.android.gms.measurement.internal.zzg r9 = r9.zze(r10)
            r10 = 0
            r11 = 0
            if (r9 == 0) goto L_0x005f
            java.lang.String r13 = r9.zzah()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x005f
            java.lang.String r13 = r2.zzb
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x005f
            r9.zzd((long) r11)
            com.google.android.gms.measurement.internal.zzal r13 = r23.zzf()
            r13.zza((com.google.android.gms.measurement.internal.zzg) r9, (boolean) r10, (boolean) r10)
            com.google.android.gms.measurement.internal.zzgt r9 = r23.zzi()
            java.lang.String r13 = r2.zza
            r9.zzj(r13)
        L_0x005f:
            boolean r9 = r2.zzh
            if (r9 != 0) goto L_0x0067
            r23.zza((com.google.android.gms.measurement.internal.zzo) r24)
            return
        L_0x0067:
            long r13 = r2.zzl
            int r9 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0075
            com.google.android.gms.common.util.Clock r9 = r23.zzb()
            long r13 = r9.currentTimeMillis()
        L_0x0075:
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm
            com.google.android.gms.measurement.internal.zzax r9 = r9.zzg()
            r9.zzm()
            int r9 = r2.zzm
            r15 = 1
            if (r9 == 0) goto L_0x009d
            if (r9 == r15) goto L_0x009d
            com.google.android.gms.measurement.internal.zzfw r16 = r23.zzj()
            com.google.android.gms.measurement.internal.zzfy r15 = r16.zzu()
            java.lang.String r11 = r2.zza
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r11)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r12 = "Incorrect app type, assuming installed app. appId, appType"
            r15.zza(r12, r11, r9)
            r9 = r10
        L_0x009d:
            com.google.android.gms.measurement.internal.zzal r11 = r23.zzf()
            r11.zzp()
            com.google.android.gms.measurement.internal.zzal r11 = r23.zzf()     // Catch:{ all -> 0x0546 }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zznq r11 = r11.zze(r12, r0)     // Catch:{ all -> 0x0546 }
            java.lang.Boolean r12 = r23.zzg(r24)     // Catch:{ all -> 0x0546 }
            r21 = r3
            r22 = r4
            if (r11 == 0) goto L_0x00c5
            java.lang.String r15 = "auto"
            java.lang.String r10 = r11.zzb     // Catch:{ all -> 0x0546 }
            boolean r10 = r15.equals(r10)     // Catch:{ all -> 0x0546 }
            if (r10 == 0) goto L_0x00c3
            goto L_0x00c5
        L_0x00c3:
            r10 = 1
            goto L_0x00f9
        L_0x00c5:
            if (r12 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzno r0 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_npa"
            boolean r10 = r12.booleanValue()     // Catch:{ all -> 0x0546 }
            if (r10 == 0) goto L_0x00d4
            r18 = 1
            goto L_0x00d6
        L_0x00d4:
            r18 = 0
        L_0x00d6:
            java.lang.Long r19 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x0546 }
            java.lang.String r20 = "auto"
            r10 = 1
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x0546 }
            if (r11 == 0) goto L_0x00ef
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x0546 }
            java.lang.Long r12 = r0.zzc     // Catch:{ all -> 0x0546 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0546 }
            if (r11 != 0) goto L_0x00f9
        L_0x00ef:
            r1.zza((com.google.android.gms.measurement.internal.zzno) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            goto L_0x00f9
        L_0x00f3:
            r10 = 1
            if (r11 == 0) goto L_0x00f9
            r1.zza((java.lang.String) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
        L_0x00f9:
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()     // Catch:{ all -> 0x0546 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0546 }
            java.lang.Object r11 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x0546 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzg r0 = r0.zze(r11)     // Catch:{ all -> 0x0546 }
            if (r0 == 0) goto L_0x01c8
            r23.zzq()     // Catch:{ all -> 0x0546 }
            java.lang.String r12 = r2.zzb     // Catch:{ all -> 0x0546 }
            java.lang.String r15 = r0.zzah()     // Catch:{ all -> 0x0546 }
            java.lang.String r11 = r2.zzp     // Catch:{ all -> 0x0546 }
            java.lang.String r3 = r0.zzaa()     // Catch:{ all -> 0x0546 }
            boolean r3 = com.google.android.gms.measurement.internal.zznp.zza((java.lang.String) r12, (java.lang.String) r15, (java.lang.String) r11, (java.lang.String) r3)     // Catch:{ all -> 0x0546 }
            if (r3 == 0) goto L_0x01c8
            com.google.android.gms.measurement.internal.zzfw r3 = r23.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r11 = r0.zzac()     // Catch:{ all -> 0x0546 }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r11)     // Catch:{ all -> 0x0546 }
            r3.zza(r4, r11)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzal r3 = r23.zzf()     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = r0.zzac()     // Catch:{ all -> 0x0546 }
            r3.zzal()     // Catch:{ all -> 0x0546 }
            r3.zzt()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x0546 }
            android.database.sqlite.SQLiteDatabase r0 = r3.e_()     // Catch:{ SQLiteException -> 0x01b5 }
            java.lang.String[] r11 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x01b5 }
            java.lang.String r12 = "events"
            int r12 = r0.delete(r12, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            java.lang.String r15 = "user_attributes"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "conditional_properties"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "apps"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "raw_events"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "raw_events_metadata"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "event_filters"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "property_filters"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "audience_filter_values"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "consent_settings"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "default_event_params"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r15
            java.lang.String r15 = "trigger_uris"
            int r0 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            int r12 = r12 + r0
            if (r12 <= 0) goto L_0x01c7
            com.google.android.gms.measurement.internal.zzfw r0 = r3.zzj()     // Catch:{ SQLiteException -> 0x01b5 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()     // Catch:{ SQLiteException -> 0x01b5 }
            java.lang.String r8 = "Deleted application data. app, records"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r12)     // Catch:{ SQLiteException -> 0x01b5 }
            r0.zza(r8, r4, r11)     // Catch:{ SQLiteException -> 0x01b5 }
            goto L_0x01c7
        L_0x01b5:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfw r3 = r3.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x0546 }
            java.lang.String r8 = "Error deleting application data. appId, error"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r4)     // Catch:{ all -> 0x0546 }
            r3.zza(r8, r4, r0)     // Catch:{ all -> 0x0546 }
        L_0x01c7:
            r0 = 0
        L_0x01c8:
            if (r0 == 0) goto L_0x0222
            long r3 = r0.zze()     // Catch:{ all -> 0x0546 }
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x01e1
            long r3 = r0.zze()     // Catch:{ all -> 0x0546 }
            long r10 = r2.zzj     // Catch:{ all -> 0x0546 }
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 == 0) goto L_0x01e1
            r3 = 1
            goto L_0x01e2
        L_0x01e1:
            r3 = 0
        L_0x01e2:
            java.lang.String r4 = r0.zzaf()     // Catch:{ all -> 0x0546 }
            long r10 = r0.zze()     // Catch:{ all -> 0x0546 }
            r15 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r0 != 0) goto L_0x01fd
            if (r4 == 0) goto L_0x01fd
            java.lang.String r0 = r2.zzc     // Catch:{ all -> 0x0546 }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x0546 }
            if (r0 != 0) goto L_0x01fd
            r15 = 1
            goto L_0x01fe
        L_0x01fd:
            r15 = 0
        L_0x01fe:
            r0 = r3 | r15
            if (r0 == 0) goto L_0x0222
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0546 }
            r0.<init>()     // Catch:{ all -> 0x0546 }
            java.lang.String r3 = "_pv"
            r0.putString(r3, r4)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzbd r3 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_au"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0546 }
            r4.<init>(r0)     // Catch:{ all -> 0x0546 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x0546 }
            r1.zza((com.google.android.gms.measurement.internal.zzbd) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
        L_0x0222:
            r23.zza((com.google.android.gms.measurement.internal.zzo) r24)     // Catch:{ all -> 0x0546 }
            if (r9 != 0) goto L_0x0234
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()     // Catch:{ all -> 0x0546 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzaz r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x0546 }
            goto L_0x0245
        L_0x0234:
            r3 = 1
            if (r9 != r3) goto L_0x0244
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()     // Catch:{ all -> 0x0546 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = "_v"
            com.google.android.gms.measurement.internal.zzaz r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x0546 }
            goto L_0x0245
        L_0x0244:
            r0 = 0
        L_0x0245:
            if (r0 != 0) goto L_0x0518
            r3 = 3600000(0x36ee80, double:1.7786363E-317)
            long r10 = r13 / r3
            r15 = 1
            long r10 = r10 + r15
            long r10 = r10 * r3
            java.lang.String r3 = "_dac"
            java.lang.String r4 = "_et"
            java.lang.String r12 = "_r"
            java.lang.String r15 = "_c"
            if (r9 != 0) goto L_0x04c9
            com.google.android.gms.measurement.internal.zzno r0 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_fot"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0546 }
            java.lang.String r20 = "auto"
            r9 = r15
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x0546 }
            r1.zza((com.google.android.gms.measurement.internal.zzno) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhc r0 = r23.zzl()     // Catch:{ all -> 0x0546 }
            r0.zzt()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzgq r0 = r1.zzl     // Catch:{ all -> 0x0546 }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x0546 }
            r10 = r0
            com.google.android.gms.measurement.internal.zzgq r10 = (com.google.android.gms.measurement.internal.zzgq) r10     // Catch:{ all -> 0x0546 }
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x0546 }
            if (r0 == 0) goto L_0x0373
            boolean r11 = r0.isEmpty()     // Catch:{ all -> 0x0546 }
            if (r11 == 0) goto L_0x028a
            goto L_0x0373
        L_0x028a:
            com.google.android.gms.measurement.internal.zzhj r11 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhc r11 = r11.zzl()     // Catch:{ all -> 0x0546 }
            r11.zzt()     // Catch:{ all -> 0x0546 }
            boolean r11 = r10.zza()     // Catch:{ all -> 0x0546 }
            if (r11 != 0) goto L_0x02aa
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzn()     // Catch:{ all -> 0x0546 }
            java.lang.String r6 = "Install Referrer Reporter is not available"
            r0.zza(r6)     // Catch:{ all -> 0x0546 }
            goto L_0x0382
        L_0x02aa:
            com.google.android.gms.measurement.internal.zzgp r11 = new com.google.android.gms.measurement.internal.zzgp     // Catch:{ all -> 0x0546 }
            r11.<init>(r10, r0)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzl()     // Catch:{ all -> 0x0546 }
            r0.zzt()     // Catch:{ all -> 0x0546 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x0546 }
            java.lang.String r15 = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE"
            r0.<init>(r15)     // Catch:{ all -> 0x0546 }
            android.content.ComponentName r15 = new android.content.ComponentName     // Catch:{ all -> 0x0546 }
            java.lang.String r8 = "com.google.android.finsky.externalreferrer.GetInstallReferrerService"
            r15.<init>(r6, r8)     // Catch:{ all -> 0x0546 }
            r0.setComponent(r15)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhj r8 = r10.zza     // Catch:{ all -> 0x0546 }
            android.content.Context r8 = r8.zza()     // Catch:{ all -> 0x0546 }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x0546 }
            if (r8 != 0) goto L_0x02e6
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzw()     // Catch:{ all -> 0x0546 }
            java.lang.String r6 = "Failed to obtain Package Manager to verify binding conditions for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x0546 }
            goto L_0x0382
        L_0x02e6:
            r15 = 0
            java.util.List r8 = r8.queryIntentServices(r0, r15)     // Catch:{ all -> 0x0546 }
            if (r8 == 0) goto L_0x0363
            boolean r16 = r8.isEmpty()     // Catch:{ all -> 0x0546 }
            if (r16 != 0) goto L_0x0363
            java.lang.Object r8 = r8.get(r15)     // Catch:{ all -> 0x0546 }
            android.content.pm.ResolveInfo r8 = (android.content.pm.ResolveInfo) r8     // Catch:{ all -> 0x0546 }
            android.content.pm.ServiceInfo r15 = r8.serviceInfo     // Catch:{ all -> 0x0546 }
            if (r15 == 0) goto L_0x0382
            android.content.pm.ServiceInfo r15 = r8.serviceInfo     // Catch:{ all -> 0x0546 }
            java.lang.String r15 = r15.packageName     // Catch:{ all -> 0x0546 }
            android.content.pm.ServiceInfo r8 = r8.serviceInfo     // Catch:{ all -> 0x0546 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0546 }
            if (r8 == 0) goto L_0x0353
            boolean r6 = r6.equals(r15)     // Catch:{ all -> 0x0546 }
            if (r6 == 0) goto L_0x0353
            boolean r6 = r10.zza()     // Catch:{ all -> 0x0546 }
            if (r6 == 0) goto L_0x0353
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x0546 }
            r6.<init>(r0)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ RuntimeException -> 0x033e }
            com.google.android.gms.measurement.internal.zzhj r8 = r10.zza     // Catch:{ RuntimeException -> 0x033e }
            android.content.Context r8 = r8.zza()     // Catch:{ RuntimeException -> 0x033e }
            r15 = 1
            boolean r0 = r0.bindService(r8, r6, r11, r15)     // Catch:{ RuntimeException -> 0x033e }
            com.google.android.gms.measurement.internal.zzhj r6 = r10.zza     // Catch:{ RuntimeException -> 0x033e }
            com.google.android.gms.measurement.internal.zzfw r6 = r6.zzj()     // Catch:{ RuntimeException -> 0x033e }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzp()     // Catch:{ RuntimeException -> 0x033e }
            java.lang.String r11 = "Install Referrer Service is"
            if (r0 == 0) goto L_0x0338
            java.lang.String r0 = "available"
            goto L_0x033a
        L_0x0338:
            java.lang.String r0 = "not available"
        L_0x033a:
            r6.zza(r11, r0)     // Catch:{ RuntimeException -> 0x033e }
            goto L_0x0382
        L_0x033e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzhj r6 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r6 = r6.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzg()     // Catch:{ all -> 0x0546 }
            java.lang.String r10 = "Exception occurred while binding to Install Referrer Service"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0546 }
            r6.zza(r10, r0)     // Catch:{ all -> 0x0546 }
            goto L_0x0382
        L_0x0353:
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()     // Catch:{ all -> 0x0546 }
            java.lang.String r6 = "Play Store version 8.3.73 or higher required for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x0546 }
            goto L_0x0382
        L_0x0363:
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzn()     // Catch:{ all -> 0x0546 }
            java.lang.String r6 = "Play Service for fetching Install Referrer is unavailable on device"
            r0.zza(r6)     // Catch:{ all -> 0x0546 }
            goto L_0x0382
        L_0x0373:
            com.google.android.gms.measurement.internal.zzhj r0 = r10.zza     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfw r0 = r0.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzw()     // Catch:{ all -> 0x0546 }
            java.lang.String r6 = "Install Referrer Reporter was called with invalid app package name"
            r0.zza(r6)     // Catch:{ all -> 0x0546 }
        L_0x0382:
            com.google.android.gms.measurement.internal.zzhc r0 = r23.zzl()     // Catch:{ all -> 0x0546 }
            r0.zzt()     // Catch:{ all -> 0x0546 }
            r23.zzs()     // Catch:{ all -> 0x0546 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ all -> 0x0546 }
            r6.<init>()     // Catch:{ all -> 0x0546 }
            r10 = 1
            r6.putLong(r9, r10)     // Catch:{ all -> 0x0546 }
            r6.putLong(r12, r10)     // Catch:{ all -> 0x0546 }
            r8 = 0
            r6.putLong(r7, r8)     // Catch:{ all -> 0x0546 }
            r6.putLong(r5, r8)     // Catch:{ all -> 0x0546 }
            r12 = r22
            r6.putLong(r12, r8)     // Catch:{ all -> 0x0546 }
            r15 = r21
            r6.putLong(r15, r8)     // Catch:{ all -> 0x0546 }
            r6.putLong(r4, r10)     // Catch:{ all -> 0x0546 }
            boolean r0 = r2.zzo     // Catch:{ all -> 0x0546 }
            if (r0 == 0) goto L_0x03b5
            r6.putLong(r3, r10)     // Catch:{ all -> 0x0546 }
        L_0x03b5:
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x0546 }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x0546 }
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x0546 }
            r0.zzt()     // Catch:{ all -> 0x0546 }
            r0.zzal()     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = "first_open_count"
            long r8 = r0.zzb((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhj r0 = r1.zzm     // Catch:{ all -> 0x0546 }
            android.content.Context r0 = r0.zza()     // Catch:{ all -> 0x0546 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0546 }
            if (r0 != 0) goto L_0x03f4
            com.google.android.gms.measurement.internal.zzfw r0 = r23.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x0546 }
            java.lang.String r4 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r3)     // Catch:{ all -> 0x0546 }
            r0.zza(r4, r3)     // Catch:{ all -> 0x0546 }
            r21 = r5
        L_0x03f0:
            r3 = 0
            goto L_0x04a8
        L_0x03f4:
            com.google.android.gms.measurement.internal.zzhj r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x0404 }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x0404 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0404 }
            r4 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0404 }
            goto L_0x0417
        L_0x0404:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfw r4 = r23.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzg()     // Catch:{ all -> 0x0546 }
            java.lang.String r10 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r3)     // Catch:{ all -> 0x0546 }
            r4.zza(r10, r11, r0)     // Catch:{ all -> 0x0546 }
            r0 = 0
        L_0x0417:
            if (r0 == 0) goto L_0x0467
            long r10 = r0.firstInstallTime     // Catch:{ all -> 0x0546 }
            r16 = 0
            int r4 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x0467
            long r10 = r0.firstInstallTime     // Catch:{ all -> 0x0546 }
            r21 = r5
            long r4 = r0.lastUpdateTime     // Catch:{ all -> 0x0546 }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x044a
            com.google.android.gms.measurement.internal.zzag r0 = r23.zze()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbf.zzbn     // Catch:{ all -> 0x0546 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r4)     // Catch:{ all -> 0x0546 }
            if (r0 == 0) goto L_0x0443
            r4 = 0
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0448
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x0546 }
            goto L_0x0448
        L_0x0443:
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x0546 }
        L_0x0448:
            r0 = 0
            goto L_0x044b
        L_0x044a:
            r0 = 1
        L_0x044b:
            com.google.android.gms.measurement.internal.zzno r4 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_fi"
            if (r0 == 0) goto L_0x0454
            r10 = 1
            goto L_0x0456
        L_0x0454:
            r10 = 0
        L_0x0456:
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0546 }
            java.lang.String r20 = "auto"
            r5 = r15
            r15 = r4
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x0546 }
            r1.zza((com.google.android.gms.measurement.internal.zzno) r4, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            goto L_0x046a
        L_0x0467:
            r21 = r5
            r5 = r15
        L_0x046a:
            com.google.android.gms.measurement.internal.zzhj r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x047a }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x047a }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x047a }
            r4 = 0
            android.content.pm.ApplicationInfo r11 = r0.getApplicationInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x047a }
            goto L_0x048d
        L_0x047a:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzfw r4 = r23.zzj()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzg()     // Catch:{ all -> 0x0546 }
            java.lang.String r7 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r3)     // Catch:{ all -> 0x0546 }
            r4.zza(r7, r3, r0)     // Catch:{ all -> 0x0546 }
            r11 = 0
        L_0x048d:
            if (r11 == 0) goto L_0x03f0
            int r0 = r11.flags     // Catch:{ all -> 0x0546 }
            r3 = 1
            r0 = r0 & r3
            if (r0 == 0) goto L_0x049b
            r3 = 1
            r6.putLong(r12, r3)     // Catch:{ all -> 0x0546 }
            goto L_0x049d
        L_0x049b:
            r3 = 1
        L_0x049d:
            int r0 = r11.flags     // Catch:{ all -> 0x0546 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x03f0
            r6.putLong(r5, r3)     // Catch:{ all -> 0x0546 }
            goto L_0x03f0
        L_0x04a8:
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x04b1
            r3 = r21
            r6.putLong(r3, r8)     // Catch:{ all -> 0x0546 }
        L_0x04b1:
            com.google.android.gms.measurement.internal.zzbd r0 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_f"
            com.google.android.gms.measurement.internal.zzbc r3 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0546 }
            r3.<init>(r6)     // Catch:{ all -> 0x0546 }
            java.lang.String r18 = "auto"
            r15 = r0
            r17 = r3
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x0546 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbd) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            goto L_0x0537
        L_0x04c9:
            r5 = r15
            r6 = 1
            if (r9 != r6) goto L_0x0537
            com.google.android.gms.measurement.internal.zzno r0 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_fvt"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0546 }
            java.lang.String r20 = "auto"
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x0546 }
            r1.zza((com.google.android.gms.measurement.internal.zzno) r0, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzhc r0 = r23.zzl()     // Catch:{ all -> 0x0546 }
            r0.zzt()     // Catch:{ all -> 0x0546 }
            r23.zzs()     // Catch:{ all -> 0x0546 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0546 }
            r0.<init>()     // Catch:{ all -> 0x0546 }
            r6 = 1
            r0.putLong(r5, r6)     // Catch:{ all -> 0x0546 }
            r0.putLong(r12, r6)     // Catch:{ all -> 0x0546 }
            r0.putLong(r4, r6)     // Catch:{ all -> 0x0546 }
            boolean r4 = r2.zzo     // Catch:{ all -> 0x0546 }
            if (r4 == 0) goto L_0x0501
            r0.putLong(r3, r6)     // Catch:{ all -> 0x0546 }
        L_0x0501:
            com.google.android.gms.measurement.internal.zzbd r3 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_v"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0546 }
            r4.<init>(r0)     // Catch:{ all -> 0x0546 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x0546 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbd) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
            goto L_0x0537
        L_0x0518:
            boolean r0 = r2.zzi     // Catch:{ all -> 0x0546 }
            if (r0 == 0) goto L_0x0537
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x0546 }
            r0.<init>()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzbd r3 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x0546 }
            java.lang.String r16 = "_cd"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x0546 }
            r4.<init>(r0)     // Catch:{ all -> 0x0546 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x0546 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbd) r3, (com.google.android.gms.measurement.internal.zzo) r2)     // Catch:{ all -> 0x0546 }
        L_0x0537:
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()     // Catch:{ all -> 0x0546 }
            r0.zzw()     // Catch:{ all -> 0x0546 }
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzf()
            r0.zzu()
            return
        L_0x0546:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzal r2 = r23.zzf()
            r2.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zzc(com.google.android.gms.measurement.internal.zzo):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzu() {
        this.zzs++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzae zzae2) {
        zzo zzc2 = zzc((String) Preconditions.checkNotNull(zzae2.zza));
        if (zzc2 != null) {
            zza(zzae2, zzc2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzae zzae2, zzo zzo2) {
        Preconditions.checkNotNull(zzae2);
        Preconditions.checkNotEmpty(zzae2.zza);
        Preconditions.checkNotNull(zzae2.zzc);
        Preconditions.checkNotEmpty(zzae2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzh(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            zzf().zzp();
            try {
                zza(zzo2);
                String str = (String) Preconditions.checkNotNull(zzae2.zza);
                zzae zzc2 = zzf().zzc(str, zzae2.zzc.zza);
                if (zzc2 != null) {
                    zzj().zzc().zza("Removing conditional user property", zzae2.zza, this.zzm.zzk().zzc(zzae2.zzc.zza));
                    zzf().zza(str, zzae2.zzc.zza);
                    if (zzc2.zze) {
                        zzf().zzh(str, zzae2.zzc.zza);
                    }
                    if (zzae2.zzk != null) {
                        zzc((zzbd) Preconditions.checkNotNull(zzq().zza(str, ((zzbd) Preconditions.checkNotNull(zzae2.zzk)).zza, zzae2.zzk.zzb != null ? zzae2.zzk.zzb.zzb() : null, zzc2.zzb, zzae2.zzk.zzd, true, true)), zzo2);
                    }
                } else {
                    zzj().zzu().zza("Conditional user property doesn't exist", zzfw.zza(zzae2.zza), this.zzm.zzk().zzc(zzae2.zzc.zza));
                }
                zzf().zzw();
            } finally {
                zzf().zzu();
            }
        }
    }

    private static void zza(zzfn.zzf.zza zza2, String str) {
        List<zzfn.zzh> zzf2 = zza2.zzf();
        for (int i = 0; i < zzf2.size(); i++) {
            if (str.equals(zzf2.get(i).zzg())) {
                zza2.zza(i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzo zzo2) {
        zzl().zzt();
        zzs();
        if (zzh(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            Boolean zzg2 = zzg(zzo2);
            if (!"_npa".equals(str) || zzg2 == null) {
                zzj().zzc().zza("Removing user property", this.zzm.zzk().zzc(str));
                zzf().zzp();
                try {
                    zza(zzo2);
                    if ("_id".equals(str)) {
                        zzf().zzh((String) Preconditions.checkNotNull(zzo2.zza), "_lair");
                    }
                    zzf().zzh((String) Preconditions.checkNotNull(zzo2.zza), str);
                    zzf().zzw();
                    zzj().zzc().zza("User property removed", this.zzm.zzk().zzc(str));
                } finally {
                    zzf().zzu();
                }
            } else {
                zzj().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzno("_npa", zzb().currentTimeMillis(), Long.valueOf(zzg2.booleanValue() ? 1 : 0), "auto"), zzo2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzo zzo2) {
        if (this.zzz != null) {
            ArrayList arrayList = new ArrayList();
            this.zzaa = arrayList;
            arrayList.addAll(this.zzz);
        }
        zzal zzf2 = zzf();
        String str = (String) Preconditions.checkNotNull(zzo2.zza);
        Preconditions.checkNotEmpty(str);
        zzf2.zzt();
        zzf2.zzal();
        try {
            SQLiteDatabase e_ = zzf2.e_();
            String[] strArr = {str};
            int delete = e_.delete("apps", "app_id=?", strArr) + e_.delete("events", "app_id=?", strArr) + e_.delete("events_snapshot", "app_id=?", strArr) + e_.delete("user_attributes", "app_id=?", strArr) + e_.delete("conditional_properties", "app_id=?", strArr) + e_.delete("raw_events", "app_id=?", strArr) + e_.delete("raw_events_metadata", "app_id=?", strArr) + e_.delete("queue", "app_id=?", strArr) + e_.delete("audience_filter_values", "app_id=?", strArr) + e_.delete("main_event_params", "app_id=?", strArr) + e_.delete("default_event_params", "app_id=?", strArr) + e_.delete("trigger_uris", "app_id=?", strArr);
            if (delete > 0) {
                zzf2.zzj().zzp().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzf2.zzj().zzg().zza("Error resetting analytics data. appId, error", zzfw.zza(str), e);
        }
        if (zzo2.zzh) {
            zzc(zzo2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzo zzo2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzo2.zza);
        zzav zza2 = zzav.zza(zzo2.zzz);
        zzj().zzp().zza("Setting DMA consent for package", zzo2.zza, zza2);
        String str = zzo2.zza;
        zzl().zzt();
        zzs();
        zzim zzc2 = zzav.zza(zza(str), 100).zzc();
        this.zzad.put(str, zza2);
        zzf().zza(str, zza2);
        zzim zzc3 = zzav.zza(zza(str), 100).zzc();
        zzl().zzt();
        zzs();
        boolean z = true;
        boolean z2 = zzc2 == zzim.DENIED && zzc3 == zzim.GRANTED;
        boolean z3 = zzc2 == zzim.GRANTED && zzc3 == zzim.DENIED;
        if (zze().zza(zzbf.zzci)) {
            if (!z2 && !z3) {
                z = false;
            }
            z2 = z;
        }
        if (z2) {
            zzj().zzp().zza("Generated _dcu event for", str);
            Bundle bundle = new Bundle();
            if (zzf().zza(zzx(), str, false, false, false, false, false, false).zzf < ((long) zze().zzb(str, zzbf.zzaw))) {
                bundle.putLong("_r", 1);
                zzj().zzp().zza("_dcu realtime event count", str, Long.valueOf(zzf().zza(zzx(), str, false, false, false, false, false, true).zzf));
            }
            this.zzah.zza(str, "_dcu", bundle);
        }
    }

    public final void zza(String str, zzkp zzkp) {
        zzl().zzt();
        String str2 = this.zzag;
        if (str2 == null || str2.equals(str) || zzkp != null) {
            this.zzag = str;
            this.zzaf = zzkp;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzo zzo2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzo2.zza);
        zzin zza2 = zzin.zza(zzo2.zzt, zzo2.zzy);
        zzin zzb2 = zzb(zzo2.zza);
        zzj().zzp().zza("Setting storage consent for package", zzo2.zza, zza2);
        zza(zzo2.zza, zza2);
        if ((!zznk.zza() || !zze().zza(zzbf.zzcv)) && zza2.zzc(zzb2)) {
            zzd(zzo2);
        }
    }

    private final void zza(List<Long> list) {
        Preconditions.checkArgument(!list.isEmpty());
        if (this.zzz != null) {
            zzj().zzg().zza("Set uploading progress before finishing the previous upload");
        } else {
            this.zzz = new ArrayList(list);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzv() {
        int delete;
        zzl().zzt();
        zzf().zzv();
        zzal zzf2 = zzf();
        zzf2.zzt();
        zzf2.zzal();
        if (zzf2.zzaa() && zzbf.zzbf.zza(null).longValue() != 0 && (delete = zzf2.e_().delete("trigger_uris", "abs(timestamp_millis - ?) > cast(? as integer)", new String[]{String.valueOf(zzf2.zzb().currentTimeMillis()), String.valueOf(zzbf.zzbf.zza(null))})) > 0) {
            zzf2.zzj().zzp().zza("Deleted stale trigger uris. rowsDeleted", Integer.valueOf(delete));
        }
        if (this.zzj.zzc.zza() == 0) {
            this.zzj.zzc.zza(zzb().currentTimeMillis());
        }
        zzab();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzae zzae2) {
        zzo zzc2 = zzc((String) Preconditions.checkNotNull(zzae2.zza));
        if (zzc2 != null) {
            zzb(zzae2, zzc2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzae zzae2, zzo zzo2) {
        Preconditions.checkNotNull(zzae2);
        Preconditions.checkNotEmpty(zzae2.zza);
        Preconditions.checkNotNull(zzae2.zzb);
        Preconditions.checkNotNull(zzae2.zzc);
        Preconditions.checkNotEmpty(zzae2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzh(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            zzae zzae3 = new zzae(zzae2);
            boolean z = false;
            zzae3.zze = false;
            zzf().zzp();
            try {
                zzae zzc2 = zzf().zzc((String) Preconditions.checkNotNull(zzae3.zza), zzae3.zzc.zza);
                if (zzc2 != null && !zzc2.zzb.equals(zzae3.zzb)) {
                    zzj().zzu().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzb, zzc2.zzb);
                }
                if (zzc2 != null && zzc2.zze) {
                    zzae3.zzb = zzc2.zzb;
                    zzae3.zzd = zzc2.zzd;
                    zzae3.zzh = zzc2.zzh;
                    zzae3.zzf = zzc2.zzf;
                    zzae3.zzi = zzc2.zzi;
                    zzae3.zze = zzc2.zze;
                    zzae3.zzc = new zzno(zzae3.zzc.zza, zzc2.zzc.zzb, zzae3.zzc.zza(), zzc2.zzc.zze);
                } else if (TextUtils.isEmpty(zzae3.zzf)) {
                    zzae3.zzc = new zzno(zzae3.zzc.zza, zzae3.zzd, zzae3.zzc.zza(), zzae3.zzc.zze);
                    z = true;
                    zzae3.zze = true;
                }
                if (zzae3.zze) {
                    zzno zzno = zzae3.zzc;
                    zznq zznq = new zznq((String) Preconditions.checkNotNull(zzae3.zza), zzae3.zzb, zzno.zza, zzno.zzb, Preconditions.checkNotNull(zzno.zza()));
                    if (zzf().zza(zznq)) {
                        zzj().zzc().zza("User property updated immediately", zzae3.zza, this.zzm.zzk().zzc(zznq.zzc), zznq.zze);
                    } else {
                        zzj().zzg().zza("(2)Too many active user properties, ignoring", zzfw.zza(zzae3.zza), this.zzm.zzk().zzc(zznq.zzc), zznq.zze);
                    }
                    if (z && zzae3.zzi != null) {
                        zzc(new zzbd(zzae3.zzi, zzae3.zzd), zzo2);
                    }
                }
                if (zzf().zza(zzae3)) {
                    zzj().zzc().zza("Conditional property added", zzae3.zza, this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzc.zza());
                } else {
                    zzj().zzg().zza("Too many conditional properties, ignoring", zzfw.zza(zzae3.zza), this.zzm.zzk().zzc(zzae3.zzc.zza), zzae3.zzc.zza());
                }
                zzf().zzw();
            } finally {
                zzf().zzu();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzab() {
        /*
            r21 = this;
            r0 = r21
            com.google.android.gms.measurement.internal.zzhc r1 = r21.zzl()
            r1.zzt()
            r21.zzs()
            long r1 = r0.zzp
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004d
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.elapsedRealtime()
            long r5 = r0.zzp
            long r1 = r1 - r5
            long r1 = java.lang.Math.abs(r1)
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004b
            com.google.android.gms.measurement.internal.zzfw r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zzgg r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzmw r1 = r21.zzz()
            r1.zzu()
            return
        L_0x004b:
            r0.zzp = r3
        L_0x004d:
            com.google.android.gms.measurement.internal.zzhj r1 = r0.zzm
            boolean r1 = r1.zzaf()
            if (r1 == 0) goto L_0x024e
            boolean r1 = r21.zzac()
            if (r1 != 0) goto L_0x005d
            goto L_0x024e
        L_0x005d:
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbf.zzaa
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzal r5 = r21.zzf()
            boolean r5 = r5.zzz()
            if (r5 != 0) goto L_0x0090
            com.google.android.gms.measurement.internal.zzal r5 = r21.zzf()
            boolean r5 = r5.zzy()
            if (r5 == 0) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r5 = 0
            goto L_0x0091
        L_0x0090:
            r5 = 1
        L_0x0091:
            if (r5 == 0) goto L_0x00d1
            com.google.android.gms.measurement.internal.zzag r10 = r21.zze()
            java.lang.String r10 = r10.zzn()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00bd
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00bd
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbf.zzv
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00bd:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbf.zzu
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00d1:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbf.zzt
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
        L_0x00e4:
            com.google.android.gms.measurement.internal.zzmc r12 = r0.zzj
            com.google.android.gms.measurement.internal.zzgm r12 = r12.zzc
            long r12 = r12.zza()
            com.google.android.gms.measurement.internal.zzmc r14 = r0.zzj
            com.google.android.gms.measurement.internal.zzgm r14 = r14.zzd
            long r14 = r14.zza()
            com.google.android.gms.measurement.internal.zzal r16 = r21.zzf()
            r17 = r10
            long r9 = r16.c_()
            com.google.android.gms.measurement.internal.zzal r11 = r21.zzf()
            r19 = r7
            long r6 = r11.d_()
            long r6 = java.lang.Math.max(r9, r6)
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0113
        L_0x0110:
            r10 = r3
            goto L_0x018d
        L_0x0113:
            long r6 = r6 - r1
            long r6 = java.lang.Math.abs(r6)
            long r6 = r1 - r6
            long r12 = r12 - r1
            long r8 = java.lang.Math.abs(r12)
            long r8 = r1 - r8
            long r14 = r14 - r1
            long r10 = java.lang.Math.abs(r14)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r6 + r19
            if (r5 == 0) goto L_0x0139
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0139
            long r10 = java.lang.Math.min(r6, r8)
            long r10 = r10 + r17
        L_0x0139:
            com.google.android.gms.measurement.internal.zznl r5 = r21.zzp()
            r12 = r17
            boolean r5 = r5.zza((long) r8, (long) r12)
            if (r5 != 0) goto L_0x0147
            long r10 = r8 + r12
        L_0x0147:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x018d
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x018d
            r5 = 0
        L_0x0150:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbf.zzac
            r7 = 0
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r8 = 0
            int r6 = java.lang.Math.max(r8, r6)
            r9 = 20
            int r6 = java.lang.Math.min(r9, r6)
            if (r5 >= r6) goto L_0x0110
            r12 = 1
            long r12 = r12 << r5
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r6 = com.google.android.gms.measurement.internal.zzbf.zzab
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            long r6 = java.lang.Math.max(r3, r6)
            long r6 = r6 * r12
            long r10 = r10 + r6
            int r6 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x018a
            goto L_0x018d
        L_0x018a:
            int r5 = r5 + 1
            goto L_0x0150
        L_0x018d:
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01ad
            com.google.android.gms.measurement.internal.zzfw r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "Next upload time is 0"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgg r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzmw r1 = r21.zzz()
            r1.zzu()
            return
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzfz r1 = r21.zzh()
            boolean r1 = r1.zzu()
            if (r1 != 0) goto L_0x01d3
            com.google.android.gms.measurement.internal.zzfw r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "No network"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgg r1 = r21.zzy()
            r1.zza()
            com.google.android.gms.measurement.internal.zzmw r1 = r21.zzz()
            r1.zzu()
            return
        L_0x01d3:
            com.google.android.gms.measurement.internal.zzmc r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzgm r1 = r1.zzb
            long r1 = r1.zza()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbf.zzr
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zznl r7 = r21.zzp()
            boolean r7 = r7.zza((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01fe
            long r1 = r1 + r5
            long r10 = java.lang.Math.max(r10, r1)
        L_0x01fe:
            com.google.android.gms.measurement.internal.zzgg r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            long r10 = r10 - r1
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0235
            r21.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r1 = com.google.android.gms.measurement.internal.zzbf.zzw
            r2 = 0
            java.lang.Object r1 = r1.zza(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r10 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzmc r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzgm r1 = r1.zzc
            com.google.android.gms.common.util.Clock r2 = r21.zzb()
            long r2 = r2.currentTimeMillis()
            r1.zza(r2)
        L_0x0235:
            com.google.android.gms.measurement.internal.zzfw r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r10)
            r1.zza(r2, r3)
            com.google.android.gms.measurement.internal.zzmw r1 = r21.zzz()
            r1.zza(r10)
            return
        L_0x024e:
            com.google.android.gms.measurement.internal.zzfw r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgg r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zzmw r1 = r21.zzz()
            r1.zzu()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zzab():void");
    }

    private final void zza(String str, zzin zzin) {
        zzl().zzt();
        zzs();
        this.zzac.put(str, zzin);
        zzf().zzb(str, zzin);
    }

    private final void zza(String str, boolean z, Long l, Long l2) {
        zzg zze2 = zzf().zze(str);
        if (zze2 != null) {
            zze2.zzd(z);
            zze2.zza(l);
            zze2.zzb(l2);
            if (zze2.zzas()) {
                zzf().zza(zze2, false, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzno zzno, zzo zzo2) {
        zznq zze2;
        long j;
        zzl().zzt();
        zzs();
        if (zzh(zzo2)) {
            if (!zzo2.zzh) {
                zza(zzo2);
                return;
            }
            int zzb2 = zzq().zzb(zzno.zza);
            int i = 0;
            if (zzb2 != 0) {
                zzq();
                String str = zzno.zza;
                zze();
                String zza2 = zznp.zza(str, 24, true);
                int length = zzno.zza != null ? zzno.zza.length() : 0;
                zzq();
                zznp.zza(this.zzah, zzo2.zza, zzb2, "_ev", zza2, length);
                return;
            }
            int zza3 = zzq().zza(zzno.zza, zzno.zza());
            if (zza3 != 0) {
                zzq();
                String str2 = zzno.zza;
                zze();
                String zza4 = zznp.zza(str2, 24, true);
                Object zza5 = zzno.zza();
                if (zza5 != null && ((zza5 instanceof String) || (zza5 instanceof CharSequence))) {
                    i = String.valueOf(zza5).length();
                }
                zzq();
                zznp.zza(this.zzah, zzo2.zza, zza3, "_ev", zza4, i);
                return;
            }
            Object zzc2 = zzq().zzc(zzno.zza, zzno.zza());
            if (zzc2 != null) {
                if ("_sid".equals(zzno.zza)) {
                    long j2 = zzno.zzb;
                    String str3 = zzno.zze;
                    String str4 = (String) Preconditions.checkNotNull(zzo2.zza);
                    zznq zze3 = zzf().zze(str4, "_sno");
                    if (zze3 == null || !(zze3.zze instanceof Long)) {
                        if (zze3 != null) {
                            zzj().zzu().zza("Retrieved last session number from database does not contain a valid (long) value", zze3.zze);
                        }
                        zzaz zzd2 = zzf().zzd(str4, "_s");
                        if (zzd2 != null) {
                            j = zzd2.zzc;
                            zzj().zzp().zza("Backfill the session number. Last used session number", Long.valueOf(j));
                        } else {
                            j = 0;
                        }
                    } else {
                        j = ((Long) zze3.zze).longValue();
                    }
                    zza(new zzno("_sno", j2, Long.valueOf(j + 1), str3), zzo2);
                }
                zznq zznq = new zznq((String) Preconditions.checkNotNull(zzo2.zza), (String) Preconditions.checkNotNull(zzno.zze), zzno.zza, zzno.zzb, zzc2);
                zzj().zzp().zza("Setting user property", this.zzm.zzk().zzc(zznq.zzc), zzc2);
                zzf().zzp();
                try {
                    if ("_id".equals(zznq.zzc) && (zze2 = zzf().zze(zzo2.zza, "_id")) != null && !zznq.zze.equals(zze2.zze)) {
                        zzf().zzh(zzo2.zza, "_lair");
                    }
                    zza(zzo2);
                    boolean zza6 = zzf().zza(zznq);
                    if ("_sid".equals(zzno.zza)) {
                        long zza7 = zzp().zza(zzo2.zzv);
                        zzg zze4 = zzf().zze(zzo2.zza);
                        if (zze4 != null) {
                            zze4.zzs(zza7);
                            if (zze4.zzas()) {
                                zzf().zza(zze4, false, false);
                            }
                        }
                    }
                    zzf().zzw();
                    if (!zza6) {
                        zzj().zzg().zza("Too many unique user properties are set. Ignoring user property", this.zzm.zzk().zzc(zznq.zzc), zznq.zze);
                        zzq();
                        zznp.zza(this.zzah, zzo2.zza, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzf().zzu();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        zzj().zzg().zza("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.measurement.internal.zzfw.zza(r6), r17.zzb());
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:219:0x04d1 */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0221 A[SYNTHETIC, Splitter:B:108:0x0221] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x023d A[SYNTHETIC, Splitter:B:117:0x023d] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x026c A[SYNTHETIC, Splitter:B:129:0x026c] */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x03ad A[Catch:{ all -> 0x0513 }] */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x03ae A[Catch:{ all -> 0x0513 }] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x042f A[Catch:{ all -> 0x0513 }] */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x0443 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzw() {
        /*
            r26 = this;
            r7 = r26
            com.google.android.gms.measurement.internal.zzhc r0 = r26.zzl()
            r0.zzt()
            r26.zzs()
            r0 = 1
            r7.zzw = r0
            r8 = 0
            com.google.android.gms.measurement.internal.zzhj r1 = r7.zzm     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzkx r1 = r1.zzr()     // Catch:{ all -> 0x0513 }
            java.lang.Boolean r1 = r1.zzab()     // Catch:{ all -> 0x0513 }
            if (r1 != 0) goto L_0x0033
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload data called on the client side before use of service was decided"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r7.zzw = r8
            r26.zzaa()
            return
        L_0x002f:
            r0 = move-exception
            r1 = r8
            goto L_0x0515
        L_0x0033:
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0513 }
            if (r1 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload called in the client side when service should be used"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r7.zzw = r8
            r26.zzaa()
            return
        L_0x004c:
            long r1 = r7.zzp     // Catch:{ all -> 0x0513 }
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x005d
            r26.zzab()     // Catch:{ all -> 0x002f }
            r7.zzw = r8
            r26.zzaa()
            return
        L_0x005d:
            com.google.android.gms.measurement.internal.zzhc r1 = r26.zzl()     // Catch:{ all -> 0x0513 }
            r1.zzt()     // Catch:{ all -> 0x0513 }
            java.util.List<java.lang.Long> r1 = r7.zzz     // Catch:{ all -> 0x0513 }
            if (r1 == 0) goto L_0x007b
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Uploading requested multiple times"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r7.zzw = r8
            r26.zzaa()
            return
        L_0x007b:
            com.google.android.gms.measurement.internal.zzfz r1 = r26.zzh()     // Catch:{ all -> 0x0513 }
            boolean r1 = r1.zzu()     // Catch:{ all -> 0x0513 }
            if (r1 != 0) goto L_0x009b
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Network not connected, ignoring upload request"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r26.zzab()     // Catch:{ all -> 0x002f }
            r7.zzw = r8
            r26.zzaa()
            return
        L_0x009b:
            com.google.android.gms.common.util.Clock r1 = r26.zzb()     // Catch:{ all -> 0x0513 }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzag r5 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbf.zzas     // Catch:{ all -> 0x0513 }
            r9 = 0
            int r5 = r5.zzb((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r6)     // Catch:{ all -> 0x0513 }
            r26.zze()     // Catch:{ all -> 0x0513 }
            long r10 = com.google.android.gms.measurement.internal.zzag.zzh()     // Catch:{ all -> 0x0513 }
            long r10 = r1 - r10
            r6 = r8
        L_0x00b8:
            if (r6 >= r5) goto L_0x00c3
            boolean r12 = r7.zza((java.lang.String) r9, (long) r10)     // Catch:{ all -> 0x002f }
            if (r12 == 0) goto L_0x00c3
            int r6 = r6 + 1
            goto L_0x00b8
        L_0x00c3:
            boolean r5 = com.google.android.gms.internal.measurement.zzpg.zza()     // Catch:{ all -> 0x0513 }
            if (r5 == 0) goto L_0x011d
            com.google.android.gms.measurement.internal.zzhc r5 = r26.zzl()     // Catch:{ all -> 0x002f }
            r5.zzt()     // Catch:{ all -> 0x002f }
            java.util.Set<java.lang.String> r5 = r7.zzr     // Catch:{ all -> 0x002f }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x002f }
        L_0x00d6:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x002f }
            if (r6 == 0) goto L_0x0118
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x002f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x002f }
            boolean r10 = com.google.android.gms.internal.measurement.zzpg.zza()     // Catch:{ all -> 0x002f }
            if (r10 == 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzag r10 = r26.zze()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbf.zzbz     // Catch:{ all -> 0x002f }
            boolean r10 = r10.zze(r6, r11)     // Catch:{ all -> 0x002f }
            if (r10 == 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzfw r10 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r10 = r10.zzc()     // Catch:{ all -> 0x002f }
            java.lang.String r11 = "Notifying app that trigger URIs are available. App ID"
            r10.zza(r11, r6)     // Catch:{ all -> 0x002f }
            android.content.Intent r10 = new android.content.Intent     // Catch:{ all -> 0x002f }
            r10.<init>()     // Catch:{ all -> 0x002f }
            java.lang.String r11 = "com.google.android.gms.measurement.TRIGGERS_AVAILABLE"
            r10.setAction(r11)     // Catch:{ all -> 0x002f }
            r10.setPackage(r6)     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzhj r6 = r7.zzm     // Catch:{ all -> 0x002f }
            android.content.Context r6 = r6.zza()     // Catch:{ all -> 0x002f }
            r6.sendBroadcast(r10)     // Catch:{ all -> 0x002f }
            goto L_0x00d6
        L_0x0118:
            java.util.Set<java.lang.String> r5 = r7.zzr     // Catch:{ all -> 0x002f }
            r5.clear()     // Catch:{ all -> 0x002f }
        L_0x011d:
            com.google.android.gms.measurement.internal.zzmc r5 = r7.zzj     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzgm r5 = r5.zzc     // Catch:{ all -> 0x0513 }
            long r5 = r5.zza()     // Catch:{ all -> 0x0513 }
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0140
            com.google.android.gms.measurement.internal.zzfw r3 = r26.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzc()     // Catch:{ all -> 0x002f }
            java.lang.String r4 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r5 = r1 - r5
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x002f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x002f }
            r3.zza(r4, r5)     // Catch:{ all -> 0x002f }
        L_0x0140:
            com.google.android.gms.measurement.internal.zzal r3 = r26.zzf()     // Catch:{ all -> 0x0513 }
            java.lang.String r6 = r3.f_()     // Catch:{ all -> 0x0513 }
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0513 }
            r4 = -1
            if (r3 != 0) goto L_0x04e7
            long r10 = r7.zzab     // Catch:{ all -> 0x0513 }
            int r3 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0160
            com.google.android.gms.measurement.internal.zzal r3 = r26.zzf()     // Catch:{ all -> 0x002f }
            long r3 = r3.b_()     // Catch:{ all -> 0x002f }
            r7.zzab = r3     // Catch:{ all -> 0x002f }
        L_0x0160:
            com.google.android.gms.measurement.internal.zzag r3 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r4 = com.google.android.gms.measurement.internal.zzbf.zzg     // Catch:{ all -> 0x0513 }
            int r3 = r3.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r4)     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzag r4 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r5 = com.google.android.gms.measurement.internal.zzbf.zzh     // Catch:{ all -> 0x0513 }
            int r4 = r4.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r5)     // Catch:{ all -> 0x0513 }
            int r4 = java.lang.Math.max(r8, r4)     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzal r5 = r26.zzf()     // Catch:{ all -> 0x0513 }
            java.util.List r3 = r5.zza((java.lang.String) r6, (int) r3, (int) r4)     // Catch:{ all -> 0x0513 }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x0513 }
            if (r4 != 0) goto L_0x050c
            com.google.android.gms.measurement.internal.zzin r4 = r7.zzb((java.lang.String) r6)     // Catch:{ all -> 0x0513 }
            boolean r4 = r4.zzi()     // Catch:{ all -> 0x0513 }
            if (r4 == 0) goto L_0x01e3
            java.util.Iterator r4 = r3.iterator()     // Catch:{ all -> 0x002f }
        L_0x0194:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x01b3
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x002f }
            android.util.Pair r5 = (android.util.Pair) r5     // Catch:{ all -> 0x002f }
            java.lang.Object r5 = r5.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = (com.google.android.gms.internal.measurement.zzfn.zzk) r5     // Catch:{ all -> 0x002f }
            java.lang.String r10 = r5.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.isEmpty()     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x0194
            java.lang.String r4 = r5.zzan()     // Catch:{ all -> 0x002f }
            goto L_0x01b4
        L_0x01b3:
            r4 = r9
        L_0x01b4:
            if (r4 == 0) goto L_0x01e3
            r5 = r8
        L_0x01b7:
            int r10 = r3.size()     // Catch:{ all -> 0x002f }
            if (r5 >= r10) goto L_0x01e3
            java.lang.Object r10 = r3.get(r5)     // Catch:{ all -> 0x002f }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x002f }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzfn$zzk r10 = (com.google.android.gms.internal.measurement.zzfn.zzk) r10     // Catch:{ all -> 0x002f }
            java.lang.String r11 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x002f }
            if (r11 != 0) goto L_0x01e0
            java.lang.String r10 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.equals(r4)     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x01e0
            java.util.List r3 = r3.subList(r8, r5)     // Catch:{ all -> 0x002f }
            goto L_0x01e3
        L_0x01e0:
            int r5 = r5 + 1
            goto L_0x01b7
        L_0x01e3:
            com.google.android.gms.internal.measurement.zzfn$zzj$zzb r4 = com.google.android.gms.internal.measurement.zzfn.zzj.zzb()     // Catch:{ all -> 0x0513 }
            int r5 = r3.size()     // Catch:{ all -> 0x0513 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0513 }
            int r11 = r3.size()     // Catch:{ all -> 0x0513 }
            r10.<init>(r11)     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzag r11 = r26.zze()     // Catch:{ all -> 0x0513 }
            boolean r11 = r11.zzj(r6)     // Catch:{ all -> 0x0513 }
            if (r11 == 0) goto L_0x020a
            com.google.android.gms.measurement.internal.zzin r11 = r7.zzb((java.lang.String) r6)     // Catch:{ all -> 0x002f }
            boolean r11 = r11.zzi()     // Catch:{ all -> 0x002f }
            if (r11 == 0) goto L_0x020a
            r11 = r0
            goto L_0x020b
        L_0x020a:
            r11 = r8
        L_0x020b:
            com.google.android.gms.measurement.internal.zzin r12 = r7.zzb((java.lang.String) r6)     // Catch:{ all -> 0x0513 }
            boolean r12 = r12.zzi()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzin r13 = r7.zzb((java.lang.String) r6)     // Catch:{ all -> 0x0513 }
            boolean r13 = r13.zzj()     // Catch:{ all -> 0x0513 }
            boolean r14 = com.google.android.gms.internal.measurement.zzph.zza()     // Catch:{ all -> 0x0513 }
            if (r14 == 0) goto L_0x022f
            com.google.android.gms.measurement.internal.zzag r14 = r26.zze()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzbf.zzbr     // Catch:{ all -> 0x002f }
            boolean r14 = r14.zze(r6, r15)     // Catch:{ all -> 0x002f }
            if (r14 == 0) goto L_0x022f
            r14 = r0
            goto L_0x0230
        L_0x022f:
            r14 = r8
        L_0x0230:
            com.google.android.gms.measurement.internal.zzna r15 = r7.zzk     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzmz r17 = r15.zza(r6)     // Catch:{ all -> 0x0513 }
            boolean r15 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x0513 }
            r9 = 3
            if (r15 == 0) goto L_0x0269
            com.google.android.gms.measurement.internal.zzag r15 = r26.zze()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r0 = com.google.android.gms.measurement.internal.zzbf.zzbs     // Catch:{ all -> 0x002f }
            boolean r0 = r15.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r0)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0269
            r26.zzq()     // Catch:{ all -> 0x002f }
            boolean r0 = com.google.android.gms.measurement.internal.zznp.zzf(r6)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0269
            com.google.android.gms.measurement.internal.zzgt r0 = r26.zzi()     // Catch:{ all -> 0x002f }
            java.lang.String r0 = r0.zzf(r6)     // Catch:{ all -> 0x002f }
            int r15 = r17.zza()     // Catch:{ all -> 0x002f }
            if (r15 != r9) goto L_0x0269
            boolean r15 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x002f }
            if (r15 != 0) goto L_0x0269
            r4.zza((java.lang.String) r0)     // Catch:{ all -> 0x002f }
        L_0x0269:
            r0 = r8
        L_0x026a:
            if (r0 >= r5) goto L_0x0417
            java.lang.Object r15 = r3.get(r0)     // Catch:{ all -> 0x0513 }
            android.util.Pair r15 = (android.util.Pair) r15     // Catch:{ all -> 0x0513 }
            java.lang.Object r15 = r15.first     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzk r15 = (com.google.android.gms.internal.measurement.zzfn.zzk) r15     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzjk$zzb r15 = r15.zzcc()     // Catch:{ all -> 0x0513 }
            r18 = r15
            com.google.android.gms.internal.measurement.zzjk$zzb r18 = (com.google.android.gms.internal.measurement.zzjk.zzb) r18     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r15 = (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r15     // Catch:{ all -> 0x0513 }
            java.lang.Object r18 = r3.get(r0)     // Catch:{ all -> 0x0513 }
            r9 = r18
            android.util.Pair r9 = (android.util.Pair) r9     // Catch:{ all -> 0x0513 }
            java.lang.Object r9 = r9.second     // Catch:{ all -> 0x0513 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x0513 }
            r10.add(r9)     // Catch:{ all -> 0x0513 }
            r26.zze()     // Catch:{ all -> 0x0513 }
            r8 = 97001(0x17ae9, double:4.7925E-319)
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r8 = r15.zzl((long) r8)     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r8 = r8.zzk((long) r1)     // Catch:{ all -> 0x0513 }
            r9 = 0
            r8.zzd((boolean) r9)     // Catch:{ all -> 0x0513 }
            if (r11 != 0) goto L_0x02a6
            r15.zzk()     // Catch:{ all -> 0x0513 }
        L_0x02a6:
            if (r12 != 0) goto L_0x02ae
            r15.zzq()     // Catch:{ all -> 0x0513 }
            r15.zzn()     // Catch:{ all -> 0x0513 }
        L_0x02ae:
            if (r13 != 0) goto L_0x02b3
            r15.zzh()     // Catch:{ all -> 0x0513 }
        L_0x02b3:
            r7.zza((java.lang.String) r6, (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r15)     // Catch:{ all -> 0x0513 }
            if (r14 != 0) goto L_0x02bb
            r15.zzr()     // Catch:{ all -> 0x0513 }
        L_0x02bb:
            boolean r8 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ all -> 0x0513 }
            if (r8 == 0) goto L_0x02d2
            com.google.android.gms.measurement.internal.zzag r8 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbf.zzcw     // Catch:{ all -> 0x0513 }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r9)     // Catch:{ all -> 0x0513 }
            if (r8 == 0) goto L_0x02d2
            if (r13 != 0) goto L_0x02d2
            r15.zzi()     // Catch:{ all -> 0x0513 }
        L_0x02d2:
            boolean r8 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ all -> 0x0513 }
            if (r8 == 0) goto L_0x03b0
            com.google.android.gms.measurement.internal.zzag r8 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ all -> 0x0513 }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r9)     // Catch:{ all -> 0x0513 }
            if (r8 == 0) goto L_0x03b0
            java.lang.String r8 = r15.zzz()     // Catch:{ all -> 0x0513 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0513 }
            if (r9 != 0) goto L_0x0303
            java.lang.String r9 = "00000000-0000-0000-0000-000000000000"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0513 }
            if (r8 == 0) goto L_0x02f7
            goto L_0x0303
        L_0x02f7:
            r21 = r3
            r22 = r11
            r24 = r12
            r23 = r13
            r25 = r14
            goto L_0x03a7
        L_0x0303:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x0513 }
            java.util.List r9 = r15.zzaa()     // Catch:{ all -> 0x0513 }
            r8.<init>(r9)     // Catch:{ all -> 0x0513 }
            java.util.Iterator r9 = r8.iterator()     // Catch:{ all -> 0x0513 }
            r21 = r3
            r22 = r11
            r3 = 0
            r11 = 0
            r19 = 0
            r20 = 0
        L_0x031a:
            boolean r23 = r9.hasNext()     // Catch:{ all -> 0x0513 }
            if (r23 == 0) goto L_0x038f
            java.lang.Object r23 = r9.next()     // Catch:{ all -> 0x0513 }
            r24 = r12
            r12 = r23
            com.google.android.gms.internal.measurement.zzfn$zzf r12 = (com.google.android.gms.internal.measurement.zzfn.zzf) r12     // Catch:{ all -> 0x0513 }
            r23 = r13
            java.lang.String r13 = "_fx"
            r25 = r14
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x0513 }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x0513 }
            if (r13 == 0) goto L_0x0348
            r9.remove()     // Catch:{ all -> 0x0513 }
            r13 = r23
            r12 = r24
            r14 = r25
            r19 = 1
            r20 = 1
            goto L_0x031a
        L_0x0348:
            java.lang.String r13 = "_f"
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x0513 }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x0513 }
            if (r13 == 0) goto L_0x0388
            com.google.android.gms.measurement.internal.zzag r13 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbf.zzcs     // Catch:{ all -> 0x0513 }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r14)     // Catch:{ all -> 0x0513 }
            if (r13 == 0) goto L_0x0386
            r26.zzp()     // Catch:{ all -> 0x0513 }
            java.lang.String r13 = "_pfo"
            com.google.android.gms.internal.measurement.zzfn$zzh r13 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x0513 }
            if (r13 == 0) goto L_0x0373
            long r13 = r13.zzd()     // Catch:{ all -> 0x0513 }
            java.lang.Long r11 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0513 }
        L_0x0373:
            r26.zzp()     // Catch:{ all -> 0x0513 }
            java.lang.String r13 = "_uwa"
            com.google.android.gms.internal.measurement.zzfn$zzh r12 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x0513 }
            if (r12 == 0) goto L_0x0386
            long r12 = r12.zzd()     // Catch:{ all -> 0x0513 }
            java.lang.Long r3 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0513 }
        L_0x0386:
            r20 = 1
        L_0x0388:
            r13 = r23
            r12 = r24
            r14 = r25
            goto L_0x031a
        L_0x038f:
            r24 = r12
            r23 = r13
            r25 = r14
            if (r19 == 0) goto L_0x039d
            r15.zzl()     // Catch:{ all -> 0x0513 }
            r15.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfn.zzf>) r8)     // Catch:{ all -> 0x0513 }
        L_0x039d:
            if (r20 == 0) goto L_0x03a7
            java.lang.String r8 = r15.zzt()     // Catch:{ all -> 0x0513 }
            r9 = 1
            r7.zza((java.lang.String) r8, (boolean) r9, (java.lang.Long) r11, (java.lang.Long) r3)     // Catch:{ all -> 0x0513 }
        L_0x03a7:
            int r3 = r15.zzc()     // Catch:{ all -> 0x0513 }
            if (r3 == 0) goto L_0x03ae
            goto L_0x03ba
        L_0x03ae:
            r8 = 3
            goto L_0x0407
        L_0x03b0:
            r21 = r3
            r22 = r11
            r24 = r12
            r23 = r13
            r25 = r14
        L_0x03ba:
            com.google.android.gms.measurement.internal.zzag r3 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzbh     // Catch:{ all -> 0x0513 }
            boolean r3 = r3.zze(r6, r8)     // Catch:{ all -> 0x0513 }
            if (r3 == 0) goto L_0x03dd
            com.google.android.gms.internal.measurement.zzkt r3 = r15.zzai()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzjk r3 = (com.google.android.gms.internal.measurement.zzjk) r3     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzk r3 = (com.google.android.gms.internal.measurement.zzfn.zzk) r3     // Catch:{ all -> 0x0513 }
            byte[] r3 = r3.zzbz()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zznl r8 = r26.zzp()     // Catch:{ all -> 0x0513 }
            long r8 = r8.zza((byte[]) r3)     // Catch:{ all -> 0x0513 }
            r15.zza((long) r8)     // Catch:{ all -> 0x0513 }
        L_0x03dd:
            boolean r3 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x0513 }
            if (r3 == 0) goto L_0x0403
            com.google.android.gms.measurement.internal.zzag r3 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzbs     // Catch:{ all -> 0x0513 }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ all -> 0x0513 }
            if (r3 == 0) goto L_0x0403
            r26.zzq()     // Catch:{ all -> 0x0513 }
            boolean r3 = com.google.android.gms.measurement.internal.zznp.zzf(r6)     // Catch:{ all -> 0x0513 }
            if (r3 == 0) goto L_0x0403
            int r3 = r17.zza()     // Catch:{ all -> 0x0513 }
            r8 = 3
            if (r3 != r8) goto L_0x0404
            r15.zzk()     // Catch:{ all -> 0x0513 }
            goto L_0x0404
        L_0x0403:
            r8 = 3
        L_0x0404:
            r4.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r15)     // Catch:{ all -> 0x0513 }
        L_0x0407:
            int r0 = r0 + 1
            r9 = r8
            r3 = r21
            r11 = r22
            r13 = r23
            r12 = r24
            r14 = r25
            r8 = 0
            goto L_0x026a
        L_0x0417:
            boolean r0 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ all -> 0x0513 }
            if (r0 == 0) goto L_0x0443
            com.google.android.gms.measurement.internal.zzag r0 = r26.zze()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ all -> 0x0513 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)     // Catch:{ all -> 0x0513 }
            if (r0 == 0) goto L_0x0443
            int r0 = r4.zza()     // Catch:{ all -> 0x0513 }
            if (r0 != 0) goto L_0x0443
            r7.zza((java.util.List<java.lang.Long>) r10)     // Catch:{ all -> 0x0513 }
            r4 = 0
            r5 = 0
            r2 = 0
            r3 = 204(0xcc, float:2.86E-43)
            r1 = r26
            r1.zza((boolean) r2, (int) r3, (java.lang.Throwable) r4, (byte[]) r5, (java.lang.String) r6)     // Catch:{ all -> 0x0513 }
            r1 = 0
            r7.zzw = r1
            r26.zzaa()
            return
        L_0x0443:
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x0513 }
            r3 = 2
            boolean r0 = r0.zza((int) r3)     // Catch:{ all -> 0x0513 }
            if (r0 == 0) goto L_0x045f
            com.google.android.gms.measurement.internal.zznl r0 = r26.zzp()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzkt r3 = r4.zzai()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzjk r3 = (com.google.android.gms.internal.measurement.zzjk) r3     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzj r3 = (com.google.android.gms.internal.measurement.zzfn.zzj) r3     // Catch:{ all -> 0x0513 }
            java.lang.String r9 = r0.zza((com.google.android.gms.internal.measurement.zzfn.zzj) r3)     // Catch:{ all -> 0x0513 }
            goto L_0x0460
        L_0x045f:
            r9 = 0
        L_0x0460:
            r26.zzp()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzkt r0 = r4.zzai()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzjk r0 = (com.google.android.gms.internal.measurement.zzjk) r0     // Catch:{ all -> 0x0513 }
            com.google.android.gms.internal.measurement.zzfn$zzj r0 = (com.google.android.gms.internal.measurement.zzfn.zzj) r0     // Catch:{ all -> 0x0513 }
            byte[] r14 = r0.zzbz()     // Catch:{ all -> 0x0513 }
            r7.zza((java.util.List<java.lang.Long>) r10)     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzmc r0 = r7.zzj     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzgm r0 = r0.zzd     // Catch:{ MalformedURLException -> 0x04d1 }
            r0.zza(r1)     // Catch:{ MalformedURLException -> 0x04d1 }
            java.lang.String r0 = "?"
            if (r5 <= 0) goto L_0x0486
            r1 = 0
            com.google.android.gms.internal.measurement.zzfn$zzk r0 = r4.zza((int) r1)     // Catch:{ MalformedURLException -> 0x04d1 }
            java.lang.String r0 = r0.zzz()     // Catch:{ MalformedURLException -> 0x04d1 }
        L_0x0486:
            com.google.android.gms.measurement.internal.zzfw r1 = r26.zzj()     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzp()     // Catch:{ MalformedURLException -> 0x04d1 }
            java.lang.String r2 = "Uploading data. app, uncompressed size, data"
            int r3 = r14.length     // Catch:{ MalformedURLException -> 0x04d1 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ MalformedURLException -> 0x04d1 }
            r1.zza(r2, r0, r3, r9)     // Catch:{ MalformedURLException -> 0x04d1 }
            r0 = 1
            r7.zzv = r0     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzfz r11 = r26.zzh()     // Catch:{ MalformedURLException -> 0x04d1 }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x04d1 }
            java.lang.String r0 = r17.zzb()     // Catch:{ MalformedURLException -> 0x04d1 }
            r13.<init>(r0)     // Catch:{ MalformedURLException -> 0x04d1 }
            java.util.Map r15 = r17.zzc()     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzne r0 = new com.google.android.gms.measurement.internal.zzne     // Catch:{ MalformedURLException -> 0x04d1 }
            r0.<init>(r7, r6)     // Catch:{ MalformedURLException -> 0x04d1 }
            r11.zzt()     // Catch:{ MalformedURLException -> 0x04d1 }
            r11.zzal()     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzhc r1 = r11.zzl()     // Catch:{ MalformedURLException -> 0x04d1 }
            com.google.android.gms.measurement.internal.zzgd r2 = new com.google.android.gms.measurement.internal.zzgd     // Catch:{ MalformedURLException -> 0x04d1 }
            r10 = r2
            r12 = r6
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x04d1 }
            r1.zza((java.lang.Runnable) r2)     // Catch:{ MalformedURLException -> 0x04d1 }
            goto L_0x050c
        L_0x04d1:
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x0513 }
            java.lang.String r1 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r6)     // Catch:{ all -> 0x0513 }
            java.lang.String r3 = r17.zzb()     // Catch:{ all -> 0x0513 }
            r0.zza(r1, r2, r3)     // Catch:{ all -> 0x0513 }
            goto L_0x050c
        L_0x04e7:
            r7.zzab = r4     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzal r0 = r26.zzf()     // Catch:{ all -> 0x0513 }
            r26.zze()     // Catch:{ all -> 0x0513 }
            long r3 = com.google.android.gms.measurement.internal.zzag.zzh()     // Catch:{ all -> 0x0513 }
            long r1 = r1 - r3
            java.lang.String r0 = r0.zza((long) r1)     // Catch:{ all -> 0x0513 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0513 }
            if (r1 != 0) goto L_0x050c
            com.google.android.gms.measurement.internal.zzal r1 = r26.zzf()     // Catch:{ all -> 0x0513 }
            com.google.android.gms.measurement.internal.zzg r0 = r1.zze(r0)     // Catch:{ all -> 0x0513 }
            if (r0 == 0) goto L_0x050c
            r7.zzb((com.google.android.gms.measurement.internal.zzg) r0)     // Catch:{ all -> 0x0513 }
        L_0x050c:
            r1 = 0
            r7.zzw = r1
            r26.zzaa()
            return
        L_0x0513:
            r0 = move-exception
            r1 = 0
        L_0x0515:
            r7.zzw = r1
            r26.zzaa()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zzw():void");
    }

    private final void zza(String str, zzfn.zzh.zza zza2, Bundle bundle, String str2) {
        int i;
        List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
        if (zznp.zzg(zza2.zzf()) || zznp.zzg(str)) {
            i = zze().zzb(str2, true);
        } else {
            i = zze().zza(str2, true);
        }
        long j = (long) i;
        long codePointCount = (long) zza2.zzg().codePointCount(0, zza2.zzg().length());
        zzq();
        String zzf2 = zza2.zzf();
        zze();
        String zza3 = zznp.zza(zzf2, 40, true);
        if (codePointCount > j && !listOf.contains(zza2.zzf())) {
            if ("_ev".equals(zza2.zzf())) {
                zzq();
                bundle.putString("_ev", zznp.zza(zza2.zzg(), zze().zzb(str2, true), true));
                return;
            }
            zzj().zzv().zza("Param value is too long; discarded. Name, value length", zza3, Long.valueOf(codePointCount));
            if (bundle.getLong("_err") == 0) {
                bundle.putLong("_err", 4);
                if (bundle.getString("_ev") == null) {
                    bundle.putString("_ev", zza3);
                    bundle.putLong("_el", codePointCount);
                }
            }
            bundle.remove(zza2.zzf());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:328:0x09b4, code lost:
        r13 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x030d A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x038d A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x03bb  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0718 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x072c A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0772 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x07ce A[SYNTHETIC, Splitter:B:276:0x07ce] */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x07ef A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x0868 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0881 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0908 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0926 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x099c A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x09f9 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01c6 A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01db A[SYNTHETIC, Splitter:B:73:0x01db] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x023d A[SYNTHETIC, Splitter:B:87:0x023d] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x024d A[Catch:{ SQLiteException -> 0x02d5, all -> 0x0a44 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(com.google.android.gms.measurement.internal.zzbd r37, com.google.android.gms.measurement.internal.zzo r38) {
        /*
            r36 = this;
            r1 = r36
            r2 = r37
            r3 = r38
            java.lang.String r4 = "_fx"
            java.lang.String r5 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r38)
            java.lang.String r6 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)
            long r6 = java.lang.System.nanoTime()
            com.google.android.gms.measurement.internal.zzhc r8 = r36.zzl()
            r8.zzt()
            r36.zzs()
            java.lang.String r8 = r3.zza
            r36.zzp()
            boolean r9 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.measurement.internal.zzbd) r37, (com.google.android.gms.measurement.internal.zzo) r38)
            if (r9 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r9 = r3.zzh
            if (r9 != 0) goto L_0x0034
            r1.zza((com.google.android.gms.measurement.internal.zzo) r3)
            return
        L_0x0034:
            com.google.android.gms.measurement.internal.zzgt r9 = r36.zzi()
            java.lang.String r10 = r2.zza
            boolean r9 = r9.zzd(r8, r10)
            java.lang.String r15 = "_err"
            r14 = 0
            if (r9 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzfw r3 = r36.zzj()
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)
            com.google.android.gms.measurement.internal.zzhj r5 = r1.zzm
            com.google.android.gms.measurement.internal.zzfr r5 = r5.zzk()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zza((java.lang.String) r6)
            java.lang.String r6 = "Dropping blocked event. appId"
            r3.zza(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzgt r3 = r36.zzi()
            boolean r3 = r3.zzm(r8)
            if (r3 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzgt r3 = r36.zzi()
            boolean r3 = r3.zzo(r8)
            if (r3 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r3 = 0
            goto L_0x0078
        L_0x0077:
            r3 = 1
        L_0x0078:
            if (r3 != 0) goto L_0x0095
            java.lang.String r4 = r2.zza
            boolean r4 = r15.equals(r4)
            if (r4 != 0) goto L_0x0095
            r36.zzq()
            com.google.android.gms.measurement.internal.zznr r9 = r1.zzah
            java.lang.String r13 = r2.zza
            r2 = 0
            r11 = 11
            java.lang.String r12 = "_ev"
            r10 = r8
            r4 = r14
            r14 = r2
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zznr) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)
            goto L_0x0096
        L_0x0095:
            r4 = r14
        L_0x0096:
            if (r3 == 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            com.google.android.gms.measurement.internal.zzg r2 = r2.zze(r8)
            if (r2 == 0) goto L_0x00de
            long r5 = r2.zzp()
            long r7 = r2.zzg()
            long r5 = java.lang.Math.max(r5, r7)
            com.google.android.gms.common.util.Clock r3 = r36.zzb()
            long r7 = r3.currentTimeMillis()
            long r7 = r7 - r5
            long r5 = java.lang.Math.abs(r7)
            r36.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Long> r3 = com.google.android.gms.measurement.internal.zzbf.zzz
            java.lang.Object r3 = r3.zza(r4)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzfw r3 = r36.zzj()
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzc()
            java.lang.String r4 = "Fetching config for blocked app"
            r3.zza(r4)
            r1.zzb((com.google.android.gms.measurement.internal.zzg) r2)
        L_0x00de:
            return
        L_0x00df:
            com.google.android.gms.measurement.internal.zzga r2 = com.google.android.gms.measurement.internal.zzga.zza(r37)
            com.google.android.gms.measurement.internal.zznp r9 = r36.zzq()
            com.google.android.gms.measurement.internal.zzag r10 = r36.zze()
            int r10 = r10.zzb(r8)
            r9.zza((com.google.android.gms.measurement.internal.zzga) r2, (int) r10)
            boolean r9 = com.google.android.gms.internal.measurement.zzou.zza()
            if (r9 == 0) goto L_0x0113
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbf.zzby
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r10)
            if (r9 == 0) goto L_0x0113
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzbf.zzaq
            r11 = 10
            r12 = 35
            int r9 = r9.zza(r8, r10, r11, r12)
            goto L_0x0114
        L_0x0113:
            r9 = 0
        L_0x0114:
            java.util.TreeSet r10 = new java.util.TreeSet
            android.os.Bundle r11 = r2.zzb
            java.util.Set r11 = r11.keySet()
            r10.<init>(r11)
            java.util.Iterator r10 = r10.iterator()
        L_0x0123:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x015b
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = "items"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x0159
            com.google.android.gms.measurement.internal.zznp r12 = r36.zzq()
            android.os.Bundle r13 = r2.zzb
            android.os.Parcelable[] r11 = r13.getParcelableArray(r11)
            boolean r13 = com.google.android.gms.internal.measurement.zzou.zza()
            if (r13 == 0) goto L_0x0155
            com.google.android.gms.measurement.internal.zzag r13 = r36.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbf.zzby
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r14)
            if (r13 == 0) goto L_0x0155
            r13 = 1
            goto L_0x0156
        L_0x0155:
            r13 = 0
        L_0x0156:
            r12.zza((android.os.Parcelable[]) r11, (int) r9, (boolean) r13)
        L_0x0159:
            r14 = 0
            goto L_0x0123
        L_0x015b:
            com.google.android.gms.measurement.internal.zzbd r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzfw r9 = r36.zzj()
            r10 = 2
            boolean r9 = r9.zza((int) r10)
            if (r9 == 0) goto L_0x0181
            com.google.android.gms.measurement.internal.zzfw r9 = r36.zzj()
            com.google.android.gms.measurement.internal.zzfy r9 = r9.zzp()
            com.google.android.gms.measurement.internal.zzhj r10 = r1.zzm
            com.google.android.gms.measurement.internal.zzfr r10 = r10.zzk()
            java.lang.String r10 = r10.zza((com.google.android.gms.measurement.internal.zzbd) r2)
            java.lang.String r11 = "Logging event"
            r9.zza(r11, r10)
        L_0x0181:
            boolean r9 = com.google.android.gms.internal.measurement.zzoo.zza()
            if (r9 == 0) goto L_0x0190
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbf.zzbv
            r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r10)
        L_0x0190:
            com.google.android.gms.measurement.internal.zzal r9 = r36.zzf()
            r9.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzo) r3)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = "ecommerce_purchase"
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = "refund"
            if (r9 != 0) goto L_0x01bb
            java.lang.String r9 = "purchase"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x01bb
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r9 = r10.equals(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x01b9
            goto L_0x01bb
        L_0x01b9:
            r9 = 0
            goto L_0x01bc
        L_0x01bb:
            r9 = 1
        L_0x01bc:
            java.lang.String r11 = "_iap"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0a44 }
            if (r11 != 0) goto L_0x01cf
            if (r9 == 0) goto L_0x01c9
            goto L_0x01cf
        L_0x01c9:
            r24 = r6
            r7 = r15
            r6 = 1
            goto L_0x033a
        L_0x01cf:
            com.google.android.gms.measurement.internal.zzbc r11 = r2.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.String r12 = "currency"
            java.lang.String r11 = r11.zzd(r12)     // Catch:{ all -> 0x0a44 }
            java.lang.String r12 = "value"
            if (r9 == 0) goto L_0x023d
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.Double r9 = r9.zza((java.lang.String) r12)     // Catch:{ all -> 0x0a44 }
            double r13 = r9.doubleValue()     // Catch:{ all -> 0x0a44 }
            r19 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r13 = r13 * r19
            r21 = 0
            int r9 = (r13 > r21 ? 1 : (r13 == r21 ? 0 : -1))
            if (r9 != 0) goto L_0x01ff
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.Long r9 = r9.zzb(r12)     // Catch:{ all -> 0x0a44 }
            long r12 = r9.longValue()     // Catch:{ all -> 0x0a44 }
            double r12 = (double) r12     // Catch:{ all -> 0x0a44 }
            double r13 = r12 * r19
        L_0x01ff:
            r19 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r9 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r9 > 0) goto L_0x0219
            r19 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r9 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r9 < 0) goto L_0x0219
            long r12 = java.lang.Math.round(r13)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r9 = r10.equals(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x0247
            long r12 = -r12
            goto L_0x0247
        L_0x0219:
            com.google.android.gms.measurement.internal.zzfw r2 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzu()     // Catch:{ all -> 0x0a44 }
            java.lang.String r3 = "Data lost. Currency value is too big. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            java.lang.Double r5 = java.lang.Double.valueOf(r13)     // Catch:{ all -> 0x0a44 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r2.zzw()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            return
        L_0x023d:
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.Long r9 = r9.zzb(r12)     // Catch:{ all -> 0x0a44 }
            long r12 = r9.longValue()     // Catch:{ all -> 0x0a44 }
        L_0x0247:
            boolean r9 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x01c9
            java.util.Locale r9 = java.util.Locale.US     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r11.toUpperCase(r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = "[A-Z]{3}"
            boolean r10 = r9.matches(r10)     // Catch:{ all -> 0x0a44 }
            if (r10 == 0) goto L_0x01c9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = "_ltv_"
            r10.<init>(r11)     // Catch:{ all -> 0x0a44 }
            r10.append(r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r14 = r10.toString()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r9 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r9 = r9.zze(r8, r14)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x02a7
            java.lang.Object r10 = r9.zze     // Catch:{ all -> 0x0a44 }
            boolean r10 = r10 instanceof java.lang.Long     // Catch:{ all -> 0x0a44 }
            if (r10 != 0) goto L_0x027a
            goto L_0x02a7
        L_0x027a:
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x0a44 }
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x0a44 }
            long r9 = r9.longValue()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r19 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.util.Clock r20 = r36.zzb()     // Catch:{ all -> 0x0a44 }
            long r20 = r20.currentTimeMillis()     // Catch:{ all -> 0x0a44 }
            long r9 = r9 + r12
            java.lang.Long r22 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0a44 }
            r9 = r19
            r10 = r8
            r13 = 0
            r12 = r14
            r24 = r6
            r6 = 1
            r7 = 0
            r13 = r20
            r7 = r15
            r15 = r22
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x0a44 }
        L_0x02a4:
            r9 = r19
            goto L_0x0303
        L_0x02a7:
            r24 = r6
            r7 = r15
            r6 = 1
            com.google.android.gms.measurement.internal.zzal r9 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r10 = r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r11 = com.google.android.gms.measurement.internal.zzbf.zzae     // Catch:{ all -> 0x0a44 }
            int r10 = r10.zzb((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r11)     // Catch:{ all -> 0x0a44 }
            int r10 = r10 - r6
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x0a44 }
            r9.zzt()     // Catch:{ all -> 0x0a44 }
            r9.zzal()     // Catch:{ all -> 0x0a44 }
            android.database.sqlite.SQLiteDatabase r11 = r9.e_()     // Catch:{ SQLiteException -> 0x02d5 }
            java.lang.String r15 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x02d5 }
            java.lang.String[] r10 = new java.lang.String[]{r8, r8, r10}     // Catch:{ SQLiteException -> 0x02d5 }
            r11.execSQL(r15, r10)     // Catch:{ SQLiteException -> 0x02d5 }
            goto L_0x02e8
        L_0x02d5:
            r0 = move-exception
            r10 = r0
            com.google.android.gms.measurement.internal.zzfw r9 = r9.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r9 = r9.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = "Error pruning currencies. appId"
            java.lang.Object r15 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            r9.zza(r11, r15, r10)     // Catch:{ all -> 0x0a44 }
        L_0x02e8:
            com.google.android.gms.measurement.internal.zznq r19 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.util.Clock r9 = r36.zzb()     // Catch:{ all -> 0x0a44 }
            long r15 = r9.currentTimeMillis()     // Catch:{ all -> 0x0a44 }
            java.lang.Long r17 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0a44 }
            r9 = r19
            r10 = r8
            r12 = r14
            r13 = r15
            r15 = r17
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x0a44 }
            goto L_0x02a4
        L_0x0303:
            com.google.android.gms.measurement.internal.zzal r10 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zznq) r9)     // Catch:{ all -> 0x0a44 }
            if (r10 != 0) goto L_0x033a
            com.google.android.gms.measurement.internal.zzfw r10 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r10 = r10.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r13 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfr r13 = r13.zzk()     // Catch:{ all -> 0x0a44 }
            java.lang.String r14 = r9.zzc     // Catch:{ all -> 0x0a44 }
            java.lang.String r13 = r13.zzc(r14)     // Catch:{ all -> 0x0a44 }
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x0a44 }
            r10.zza(r11, r12, r13, r9)     // Catch:{ all -> 0x0a44 }
            r36.zzq()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznr r9 = r1.zzah     // Catch:{ all -> 0x0a44 }
            r13 = 0
            r14 = 0
            r11 = 9
            r12 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zznr) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a44 }
        L_0x033a:
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r21 = com.google.android.gms.measurement.internal.zznp.zzh(r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0a44 }
            r36.zzq()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x0a44 }
            long r9 = com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zzbc) r9)     // Catch:{ all -> 0x0a44 }
            r13 = 1
            long r15 = r9 + r13
            com.google.android.gms.measurement.internal.zzal r9 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            long r10 = r36.zzx()     // Catch:{ all -> 0x0a44 }
            r19 = 0
            r20 = 0
            r17 = 1
            r18 = 0
            r12 = r8
            r13 = r15
            r15 = r17
            r16 = r21
            r17 = r18
            r18 = r7
            com.google.android.gms.measurement.internal.zzaq r9 = r9.zza(r10, r12, r13, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0a44 }
            long r10 = r9.zzb     // Catch:{ all -> 0x0a44 }
            r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbf.zzk     // Catch:{ all -> 0x0a44 }
            r13 = 0
            java.lang.Object r12 = r12.zza(r13)     // Catch:{ all -> 0x0a44 }
            java.lang.Integer r12 = (java.lang.Integer) r12     // Catch:{ all -> 0x0a44 }
            int r12 = r12.intValue()     // Catch:{ all -> 0x0a44 }
            long r12 = (long) r12     // Catch:{ all -> 0x0a44 }
            long r10 = r10 - r12
            r14 = 0
            int r12 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            r16 = 1000(0x3e8, double:4.94E-321)
            if (r12 <= 0) goto L_0x03bb
            long r10 = r10 % r16
            r12 = 1
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x03ac
            com.google.android.gms.measurement.internal.zzfw r2 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            long r5 = r9.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0a44 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0a44 }
        L_0x03ac:
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r2.zzw()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            return
        L_0x03bb:
            r12 = 1
            if (r21 == 0) goto L_0x0415
            long r10 = r9.zza     // Catch:{ all -> 0x0a44 }
            r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbf.zzm     // Catch:{ all -> 0x0a44 }
            r12 = 0
            java.lang.Object r6 = r6.zza(r12)     // Catch:{ all -> 0x0a44 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x0a44 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0a44 }
            long r12 = (long) r6     // Catch:{ all -> 0x0a44 }
            long r10 = r10 - r12
            int r6 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r6 <= 0) goto L_0x0415
            long r10 = r10 % r16
            r3 = 1
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x03f6
            com.google.android.gms.measurement.internal.zzfw r3 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            long r6 = r9.zza     // Catch:{ all -> 0x0a44 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0a44 }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x0a44 }
        L_0x03f6:
            r36.zzq()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznr r9 = r1.zzah     // Catch:{ all -> 0x0a44 }
            java.lang.String r12 = "_ev"
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x0a44 }
            r14 = 0
            r11 = 16
            r10 = r8
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zznr) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r2.zzw()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            return
        L_0x0415:
            if (r7 == 0) goto L_0x0463
            long r6 = r9.zzd     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r10 = r36.zze()     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbf.zzl     // Catch:{ all -> 0x0a44 }
            int r10 = r10.zzb((java.lang.String) r11, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r12)     // Catch:{ all -> 0x0a44 }
            r11 = 1000000(0xf4240, float:1.401298E-39)
            int r10 = java.lang.Math.min(r11, r10)     // Catch:{ all -> 0x0a44 }
            r13 = 0
            int r10 = java.lang.Math.max(r13, r10)     // Catch:{ all -> 0x0a44 }
            long r10 = (long) r10     // Catch:{ all -> 0x0a44 }
            long r6 = r6 - r10
            int r10 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x0464
            r10 = 1
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x0454
            com.google.android.gms.measurement.internal.zzfw r2 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            long r5 = r9.zzd     // Catch:{ all -> 0x0a44 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0a44 }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x0a44 }
        L_0x0454:
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r2.zzw()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            return
        L_0x0463:
            r13 = 0
        L_0x0464:
            com.google.android.gms.measurement.internal.zzbc r6 = r2.zzb     // Catch:{ all -> 0x0a44 }
            android.os.Bundle r6 = r6.zzb()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznp r7 = r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = "_o"
            java.lang.String r10 = r2.zzc     // Catch:{ all -> 0x0a44 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznp r7 = r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzac     // Catch:{ all -> 0x0a44 }
            boolean r7 = r7.zzd(r8, r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r12 = "_r"
            if (r7 == 0) goto L_0x049e
            com.google.android.gms.measurement.internal.zznp r7 = r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = "_dbg"
            r16 = 1
            java.lang.Long r10 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a44 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznp r7 = r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.Long r9 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0a44 }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r12, (java.lang.Object) r9)     // Catch:{ all -> 0x0a44 }
            goto L_0x04a0
        L_0x049e:
            r16 = 1
        L_0x04a0:
            java.lang.String r7 = "_s"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x0a44 }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x04c5
            com.google.android.gms.measurement.internal.zzal r7 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r7 = r7.zze(r9, r5)     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x04c5
            java.lang.Object r9 = r7.zze     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9 instanceof java.lang.Long     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x04c5
            com.google.android.gms.measurement.internal.zznp r9 = r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x0a44 }
            r9.zza((android.os.Bundle) r6, (java.lang.String) r5, (java.lang.Object) r7)     // Catch:{ all -> 0x0a44 }
        L_0x04c5:
            com.google.android.gms.measurement.internal.zzal r5 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            long r9 = r5.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            int r5 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r5 <= 0) goto L_0x04e6
            com.google.android.gms.measurement.internal.zzfw r5 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzu()     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0a44 }
            r5.zza(r7, r11, r9)     // Catch:{ all -> 0x0a44 }
        L_0x04e6:
            com.google.android.gms.measurement.internal.zzba r5 = new com.google.android.gms.measurement.internal.zzba     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r10 = r1.zzm     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x0a44 }
            long r14 = r2.zzd     // Catch:{ all -> 0x0a44 }
            r22 = 0
            r9 = r5
            r2 = r12
            r12 = r8
            r26 = r2
            r2 = r13
            r13 = r7
            r16 = r22
            r18 = r6
            r9.<init>((com.google.android.gms.measurement.internal.zzhj) r10, (java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r13, (long) r14, (long) r16, (android.os.Bundle) r18)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r6 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = r5.zzb     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzaz r6 = r6.zzd(r8, r7)     // Catch:{ all -> 0x0a44 }
            if (r6 != 0) goto L_0x0581
            com.google.android.gms.measurement.internal.zzal r6 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            long r6 = r6.zzc(r8)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()     // Catch:{ all -> 0x0a44 }
            int r9 = r9.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            long r9 = (long) r9     // Catch:{ all -> 0x0a44 }
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 < 0) goto L_0x0562
            if (r21 == 0) goto L_0x0562
            com.google.android.gms.measurement.internal.zzfw r2 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r6 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfr r6 = r6.zzk()     // Catch:{ all -> 0x0a44 }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x0a44 }
            java.lang.String r5 = r6.zza((java.lang.String) r5)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r6 = r36.zze()     // Catch:{ all -> 0x0a44 }
            int r6 = r6.zza((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0a44 }
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x0a44 }
            r36.zzq()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznr r9 = r1.zzah     // Catch:{ all -> 0x0a44 }
            r13 = 0
            r14 = 0
            r11 = 8
            r12 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zznr) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            return
        L_0x0562:
            com.google.android.gms.measurement.internal.zzaz r6 = new com.google.android.gms.measurement.internal.zzaz     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r5.zzb     // Catch:{ all -> 0x0a44 }
            long r14 = r5.zzc     // Catch:{ all -> 0x0a44 }
            r22 = 0
            r23 = 0
            r12 = 0
            r16 = 0
            r18 = 0
            r20 = 0
            r21 = 0
            r9 = r6
            r10 = r8
            r7 = r14
            r14 = r16
            r16 = r7
            r9.<init>(r10, r11, r12, r14, r16, r18, r20, r21, r22, r23)     // Catch:{ all -> 0x0a44 }
            goto L_0x058f
        L_0x0581:
            com.google.android.gms.measurement.internal.zzhj r7 = r1.zzm     // Catch:{ all -> 0x0a44 }
            long r8 = r6.zzf     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzba r5 = r5.zza(r7, r8)     // Catch:{ all -> 0x0a44 }
            long r7 = r5.zzc     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzaz r6 = r6.zza(r7)     // Catch:{ all -> 0x0a44 }
        L_0x058f:
            com.google.android.gms.measurement.internal.zzal r7 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r7.zza((com.google.android.gms.measurement.internal.zzaz) r6)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhc r6 = r36.zzl()     // Catch:{ all -> 0x0a44 }
            r6.zzt()     // Catch:{ all -> 0x0a44 }
            r36.zzs()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r38)     // Catch:{ all -> 0x0a44 }
            java.lang.String r6 = r5.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x0a44 }
            java.lang.String r6 = r5.zza     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0a44 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r6)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r6 = com.google.android.gms.internal.measurement.zzfn.zzk.zzw()     // Catch:{ all -> 0x0a44 }
            r7 = 1
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r6 = r6.zzh((int) r7)     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = "android"
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r6 = r6.zzp(r8)     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a44 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x05d2
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a44 }
            r6.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
        L_0x05d2:
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0a44 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x05df
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x0a44 }
            r6.zzd((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
        L_0x05df:
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0a44 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x05ec
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x0a44 }
            r6.zze((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
        L_0x05ec:
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0a44 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x05f9
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x0a44 }
            r6.zzr(r8)     // Catch:{ all -> 0x0a44 }
        L_0x05f9:
            long r8 = r3.zzj     // Catch:{ all -> 0x0a44 }
            r10 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0608
            long r8 = r3.zzj     // Catch:{ all -> 0x0a44 }
            int r8 = (int) r8     // Catch:{ all -> 0x0a44 }
            r6.zze((int) r8)     // Catch:{ all -> 0x0a44 }
        L_0x0608:
            long r8 = r3.zze     // Catch:{ all -> 0x0a44 }
            r6.zzf((long) r8)     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0a44 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x061a
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x0a44 }
            r6.zzm(r8)     // Catch:{ all -> 0x0a44 }
        L_0x061a:
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x0a44 }
            java.lang.Object r8 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r8 = r1.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzt     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r9 = com.google.android.gms.measurement.internal.zzin.zzb((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r8 = r8.zza((com.google.android.gms.measurement.internal.zzin) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r8.zzg()     // Catch:{ all -> 0x0a44 }
            r6.zzg((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r6.zzx()     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.isEmpty()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x064e
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x0a44 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x064e
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x0a44 }
            r6.zza((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
        L_0x064e:
            boolean r9 = com.google.android.gms.internal.measurement.zzpg.zza()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x0710
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbf.zzbz     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.zze(r10, r11)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x0710
            r36.zzq()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            boolean r9 = com.google.android.gms.measurement.internal.zznp.zzd(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x0710
            int r9 = r3.zzaa     // Catch:{ all -> 0x0a44 }
            r6.zzd((int) r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zzab     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r11 = r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbf.zzcd     // Catch:{ all -> 0x0a44 }
            boolean r11 = r11.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r12)     // Catch:{ all -> 0x0a44 }
            r12 = 32
            if (r11 != 0) goto L_0x0695
            boolean r8 = r8.zzi()     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x0695
            r14 = 0
            int r8 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x0697
            r16 = -2
            long r8 = r9 & r16
            long r9 = r8 | r12
            goto L_0x0697
        L_0x0695:
            r14 = 0
        L_0x0697:
            r7 = 1
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 != 0) goto L_0x069f
            r11 = 1
            goto L_0x06a0
        L_0x069f:
            r11 = r2
        L_0x06a0:
            r6.zza((boolean) r11)     // Catch:{ all -> 0x0a44 }
            int r11 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r11 == 0) goto L_0x0712
            com.google.android.gms.internal.measurement.zzfn$zzc$zza r11 = com.google.android.gms.internal.measurement.zzfn.zzc.zza()     // Catch:{ all -> 0x0a44 }
            long r18 = r9 & r7
            int r16 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r16 == 0) goto L_0x06b3
            r7 = 1
            goto L_0x06b4
        L_0x06b3:
            r7 = r2
        L_0x06b4:
            r11.zzc(r7)     // Catch:{ all -> 0x0a44 }
            r7 = 2
            long r7 = r7 & r9
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x06c0
            r7 = 1
            goto L_0x06c1
        L_0x06c0:
            r7 = r2
        L_0x06c1:
            r11.zze(r7)     // Catch:{ all -> 0x0a44 }
            r7 = 4
            long r7 = r7 & r9
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x06cd
            r7 = 1
            goto L_0x06ce
        L_0x06cd:
            r7 = r2
        L_0x06ce:
            r11.zzf(r7)     // Catch:{ all -> 0x0a44 }
            r7 = 8
            long r7 = r7 & r9
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x06da
            r7 = 1
            goto L_0x06db
        L_0x06da:
            r7 = r2
        L_0x06db:
            r11.zzg(r7)     // Catch:{ all -> 0x0a44 }
            r7 = 16
            long r7 = r7 & r9
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x06e7
            r7 = 1
            goto L_0x06e8
        L_0x06e7:
            r7 = r2
        L_0x06e8:
            r11.zzb(r7)     // Catch:{ all -> 0x0a44 }
            long r7 = r9 & r12
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x06f3
            r13 = 1
            goto L_0x06f4
        L_0x06f3:
            r13 = r2
        L_0x06f4:
            r11.zza(r13)     // Catch:{ all -> 0x0a44 }
            r7 = 64
            long r7 = r7 & r9
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x0700
            r13 = 1
            goto L_0x0701
        L_0x0700:
            r13 = r2
        L_0x0701:
            r11.zzd(r13)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzkt r7 = r11.zzai()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzjk r7 = (com.google.android.gms.internal.measurement.zzjk) r7     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzc r7 = (com.google.android.gms.internal.measurement.zzfn.zzc) r7     // Catch:{ all -> 0x0a44 }
            r6.zza((com.google.android.gms.internal.measurement.zzfn.zzc) r7)     // Catch:{ all -> 0x0a44 }
            goto L_0x0712
        L_0x0710:
            r14 = 0
        L_0x0712:
            long r7 = r3.zzf     // Catch:{ all -> 0x0a44 }
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 == 0) goto L_0x071d
            long r7 = r3.zzf     // Catch:{ all -> 0x0a44 }
            r6.zzc((long) r7)     // Catch:{ all -> 0x0a44 }
        L_0x071d:
            long r7 = r3.zzr     // Catch:{ all -> 0x0a44 }
            r6.zzd((long) r7)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznl r7 = r36.zzp()     // Catch:{ all -> 0x0a44 }
            java.util.List r7 = r7.zzu()     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x072f
            r6.zzc((java.lang.Iterable<? extends java.lang.Integer>) r7)     // Catch:{ all -> 0x0a44 }
        L_0x072f:
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x0a44 }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r7 = r1.zzb((java.lang.String) r7)     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = r3.zzt     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r8 = com.google.android.gms.measurement.internal.zzin.zzb((java.lang.String) r8)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzin r7 = r7.zza((com.google.android.gms.measurement.internal.zzin) r8)     // Catch:{ all -> 0x0a44 }
            boolean r8 = r7.zzi()     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x081f
            boolean r8 = r3.zzn     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x081f
            com.google.android.gms.measurement.internal.zzmc r8 = r1.zzj     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            android.util.Pair r8 = r8.zza((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzin) r7)     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x081f
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0a44 }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x0a44 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x081f
            boolean r9 = r3.zzn     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x081f
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0a44 }
            r6.zzq(r9)     // Catch:{ all -> 0x0a44 }
            java.lang.Object r9 = r8.second     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x077d
            java.lang.Object r9 = r8.second     // Catch:{ all -> 0x0a44 }
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x0a44 }
            r6.zzc((boolean) r9)     // Catch:{ all -> 0x0a44 }
        L_0x077d:
            boolean r9 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x081f
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r10)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x081f
            java.lang.String r9 = r5.zzb     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.equals(r4)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x081f
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = "00000000-0000-0000-0000-000000000000"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0a44 }
            if (r8 != 0) goto L_0x081f
            com.google.android.gms.measurement.internal.zzal r8 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzg r8 = r8.zze(r9)     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x081f
            boolean r9 = r8.zzau()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x081f
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            r10 = 0
            r1.zza((java.lang.String) r9, (boolean) r2, (java.lang.Long) r10, (java.lang.Long) r10)     // Catch:{ all -> 0x0a44 }
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x0a44 }
            r9.<init>()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r10 = r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbf.zzcs     // Catch:{ all -> 0x0a44 }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r11)     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = "_pfo"
            if (r10 == 0) goto L_0x07ef
            java.lang.Long r10 = r8.zzy()     // Catch:{ all -> 0x0a44 }
            if (r10 == 0) goto L_0x07df
            long r12 = r10.longValue()     // Catch:{ all -> 0x0a44 }
            long r12 = java.lang.Math.max(r14, r12)     // Catch:{ all -> 0x0a44 }
            r9.putLong(r11, r12)     // Catch:{ all -> 0x0a44 }
        L_0x07df:
            java.lang.Long r8 = r8.zzz()     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x0810
            java.lang.String r10 = "_uwa"
            long r11 = r8.longValue()     // Catch:{ all -> 0x0a44 }
            r9.putLong(r10, r11)     // Catch:{ all -> 0x0a44 }
            goto L_0x0810
        L_0x07ef:
            com.google.android.gms.measurement.internal.zzag r8 = r36.zze()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbf.zzcr     // Catch:{ all -> 0x0a44 }
            boolean r8 = r8.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r10)     // Catch:{ all -> 0x0a44 }
            if (r8 == 0) goto L_0x0810
            com.google.android.gms.measurement.internal.zzal r8 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a44 }
            long r12 = r8.zzb(r10)     // Catch:{ all -> 0x0a44 }
            r18 = 1
            long r12 = r12 - r18
            long r12 = java.lang.Math.max(r14, r12)     // Catch:{ all -> 0x0a44 }
            r9.putLong(r11, r12)     // Catch:{ all -> 0x0a44 }
        L_0x0810:
            r8 = r26
            r10 = 1
            r9.putLong(r8, r10)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznr r10 = r1.zzah     // Catch:{ all -> 0x0a44 }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x0a44 }
            r10.zza(r11, r4, r9)     // Catch:{ all -> 0x0a44 }
            goto L_0x0821
        L_0x081f:
            r8 = r26
        L_0x0821:
            com.google.android.gms.measurement.internal.zzhj r4 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzax r4 = r4.zzg()     // Catch:{ all -> 0x0a44 }
            r4.zzac()     // Catch:{ all -> 0x0a44 }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r4 = r6.zzi((java.lang.String) r4)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzax r9 = r9.zzg()     // Catch:{ all -> 0x0a44 }
            r9.zzac()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r4 = r4.zzo(r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzax r9 = r9.zzg()     // Catch:{ all -> 0x0a44 }
            long r9 = r9.zzg()     // Catch:{ all -> 0x0a44 }
            int r9 = (int) r9     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r4 = r4.zzj((int) r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzax r9 = r9.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r9.zzh()     // Catch:{ all -> 0x0a44 }
            r4.zzs(r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zzx     // Catch:{ all -> 0x0a44 }
            r6.zzj((long) r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r4 = r1.zzm     // Catch:{ all -> 0x0a44 }
            boolean r4 = r4.zzac()     // Catch:{ all -> 0x0a44 }
            if (r4 == 0) goto L_0x0875
            r6.zzt()     // Catch:{ all -> 0x0a44 }
            r4 = 0
            boolean r9 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0a44 }
            if (r9 != 0) goto L_0x0875
            r6.zzj((java.lang.String) r4)     // Catch:{ all -> 0x0a44 }
        L_0x0875:
            com.google.android.gms.measurement.internal.zzal r4 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzg r4 = r4.zze(r9)     // Catch:{ all -> 0x0a44 }
            if (r4 != 0) goto L_0x08e1
            com.google.android.gms.measurement.internal.zzg r4 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a44 }
            r4.<init>(r9, r10)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r1.zza((com.google.android.gms.measurement.internal.zzin) r7)     // Catch:{ all -> 0x0a44 }
            r4.zzb((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzk     // Catch:{ all -> 0x0a44 }
            r4.zze((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzb     // Catch:{ all -> 0x0a44 }
            r4.zzf((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            boolean r9 = r7.zzi()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x08ae
            com.google.android.gms.measurement.internal.zzmc r9 = r1.zzj     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x0a44 }
            boolean r11 = r3.zzn     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r9.zza((java.lang.String) r10, (boolean) r11)     // Catch:{ all -> 0x0a44 }
            r4.zzh((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
        L_0x08ae:
            r4.zzq(r14)     // Catch:{ all -> 0x0a44 }
            r4.zzr(r14)     // Catch:{ all -> 0x0a44 }
            r4.zzp(r14)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzc     // Catch:{ all -> 0x0a44 }
            r4.zzd((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zzj     // Catch:{ all -> 0x0a44 }
            r4.zzb((long) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zzd     // Catch:{ all -> 0x0a44 }
            r4.zzc((java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zze     // Catch:{ all -> 0x0a44 }
            r4.zzn(r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zzf     // Catch:{ all -> 0x0a44 }
            r4.zzk((long) r9)     // Catch:{ all -> 0x0a44 }
            boolean r9 = r3.zzh     // Catch:{ all -> 0x0a44 }
            r4.zzb((boolean) r9)     // Catch:{ all -> 0x0a44 }
            long r9 = r3.zzr     // Catch:{ all -> 0x0a44 }
            r4.zzl(r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r9 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r9.zza((com.google.android.gms.measurement.internal.zzg) r4, (boolean) r2, (boolean) r2)     // Catch:{ all -> 0x0a44 }
        L_0x08e1:
            boolean r7 = r7.zzj()     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x08fe
            java.lang.String r7 = r4.zzad()     // Catch:{ all -> 0x0a44 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0a44 }
            if (r7 != 0) goto L_0x08fe
            java.lang.String r7 = r4.zzad()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0a44 }
            r6.zzc((java.lang.String) r7)     // Catch:{ all -> 0x0a44 }
        L_0x08fe:
            java.lang.String r7 = r4.zzag()     // Catch:{ all -> 0x0a44 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0a44 }
            if (r7 != 0) goto L_0x0915
            java.lang.String r7 = r4.zzag()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x0a44 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0a44 }
            r6.zzl((java.lang.String) r7)     // Catch:{ all -> 0x0a44 }
        L_0x0915:
            com.google.android.gms.measurement.internal.zzal r7 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x0a44 }
            java.util.List r7 = r7.zzk(r9)     // Catch:{ all -> 0x0a44 }
            r12 = r2
        L_0x0920:
            int r9 = r7.size()     // Catch:{ all -> 0x0a44 }
            if (r12 >= r9) goto L_0x0984
            com.google.android.gms.internal.measurement.zzfn$zzo$zza r9 = com.google.android.gms.internal.measurement.zzfn.zzo.zze()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r10 = (com.google.android.gms.measurement.internal.zznq) r10     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzo$zza r9 = r9.zza((java.lang.String) r10)     // Catch:{ all -> 0x0a44 }
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r10 = (com.google.android.gms.measurement.internal.zznq) r10     // Catch:{ all -> 0x0a44 }
            long r10 = r10.zzd     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.internal.measurement.zzfn$zzo$zza r9 = r9.zzb((long) r10)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznl r10 = r36.zzp()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r11 = r7.get(r12)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r11 = (com.google.android.gms.measurement.internal.zznq) r11     // Catch:{ all -> 0x0a44 }
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x0a44 }
            r10.zza((com.google.android.gms.internal.measurement.zzfn.zzo.zza) r9, (java.lang.Object) r11)     // Catch:{ all -> 0x0a44 }
            r6.zza((com.google.android.gms.internal.measurement.zzfn.zzo.zza) r9)     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = "_sid"
            java.lang.Object r10 = r7.get(r12)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zznq r10 = (com.google.android.gms.measurement.internal.zznq) r10     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r10.zzc     // Catch:{ all -> 0x0a44 }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x0981
            long r9 = r4.zzv()     // Catch:{ all -> 0x0a44 }
            int r9 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r9 == 0) goto L_0x0981
            com.google.android.gms.measurement.internal.zznl r9 = r36.zzp()     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r3.zzv     // Catch:{ all -> 0x0a44 }
            long r9 = r9.zza((java.lang.String) r10)     // Catch:{ all -> 0x0a44 }
            long r18 = r4.zzv()     // Catch:{ all -> 0x0a44 }
            int r9 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r9 == 0) goto L_0x0981
            r6.zzr()     // Catch:{ all -> 0x0a44 }
        L_0x0981:
            int r12 = r12 + 1
            goto L_0x0920
        L_0x0984:
            com.google.android.gms.measurement.internal.zzal r3 = r36.zzf()     // Catch:{ IOException -> 0x09fc }
            com.google.android.gms.internal.measurement.zzkt r4 = r6.zzai()     // Catch:{ IOException -> 0x09fc }
            com.google.android.gms.internal.measurement.zzjk r4 = (com.google.android.gms.internal.measurement.zzjk) r4     // Catch:{ IOException -> 0x09fc }
            com.google.android.gms.internal.measurement.zzfn$zzk r4 = (com.google.android.gms.internal.measurement.zzfn.zzk) r4     // Catch:{ IOException -> 0x09fc }
            long r3 = r3.zza((com.google.android.gms.internal.measurement.zzfn.zzk) r4)     // Catch:{ IOException -> 0x09fc }
            com.google.android.gms.measurement.internal.zzal r6 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzbc r7 = r5.zze     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x09f2
            com.google.android.gms.measurement.internal.zzbc r7 = r5.zze     // Catch:{ all -> 0x0a44 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x0a44 }
        L_0x09a2:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x09b6
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0a44 }
            boolean r9 = r8.equals(r9)     // Catch:{ all -> 0x0a44 }
            if (r9 == 0) goto L_0x09a2
        L_0x09b4:
            r13 = 1
            goto L_0x09f3
        L_0x09b6:
            com.google.android.gms.measurement.internal.zzgt r7 = r36.zzi()     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x0a44 }
            java.lang.String r9 = r5.zzb     // Catch:{ all -> 0x0a44 }
            boolean r7 = r7.zzc((java.lang.String) r8, (java.lang.String) r9)     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r26 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            long r27 = r36.zzx()     // Catch:{ all -> 0x0a44 }
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x0a44 }
            r34 = 0
            r35 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r29 = r8
            com.google.android.gms.measurement.internal.zzaq r8 = r26.zza(r27, r29, r30, r31, r32, r33, r34, r35)     // Catch:{ all -> 0x0a44 }
            if (r7 == 0) goto L_0x09f2
            long r7 = r8.zze     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzag r9 = r36.zze()     // Catch:{ all -> 0x0a44 }
            java.lang.String r10 = r5.zza     // Catch:{ all -> 0x0a44 }
            int r9 = r9.zzc(r10)     // Catch:{ all -> 0x0a44 }
            long r9 = (long) r9     // Catch:{ all -> 0x0a44 }
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 >= 0) goto L_0x09f2
            goto L_0x09b4
        L_0x09f2:
            r13 = r2
        L_0x09f3:
            boolean r2 = r6.zza((com.google.android.gms.measurement.internal.zzba) r5, (long) r3, (boolean) r13)     // Catch:{ all -> 0x0a44 }
            if (r2 == 0) goto L_0x0a13
            r1.zzp = r14     // Catch:{ all -> 0x0a44 }
            goto L_0x0a13
        L_0x09fc:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzfw r3 = r36.zzj()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x0a44 }
            java.lang.String r4 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r5 = r6.zzt()     // Catch:{ all -> 0x0a44 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r5)     // Catch:{ all -> 0x0a44 }
            r3.zza(r4, r5, r2)     // Catch:{ all -> 0x0a44 }
        L_0x0a13:
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()     // Catch:{ all -> 0x0a44 }
            r2.zzw()     // Catch:{ all -> 0x0a44 }
            com.google.android.gms.measurement.internal.zzal r2 = r36.zzf()
            r2.zzu()
            r36.zzab()
            com.google.android.gms.measurement.internal.zzfw r2 = r36.zzj()
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzp()
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r24
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r4 = "Background event processing time, ms"
            r2.zza(r4, r3)
            return
        L_0x0a44:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzal r3 = r36.zzf()
            r3.zzu()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zzc(com.google.android.gms.measurement.internal.zzbd, com.google.android.gms.measurement.internal.zzo):void");
    }

    private static boolean zzh(zzo zzo2) {
        return !TextUtils.isEmpty(zzo2.zzb) || !TextUtils.isEmpty(zzo2.zzp);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v80, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v146, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v147, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v82, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v148, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v149, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v150, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v151, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v152, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v153, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v154, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v159, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v160, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v90, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v161, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v163, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v99, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0232 A[SYNTHETIC, Splitter:B:121:0x0232] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0239 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x059d A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0662 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x06ac A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x0717 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0831 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x0aa2 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:402:0x0aba A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0bb7 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x0bea A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:432:0x0c25 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:486:0x0d69 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:487:0x0d7c A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:489:0x0d7f A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:490:0x0da4 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:542:0x0f66 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:545:0x0f78 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:546:0x0f91 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:570:0x100e A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:571:0x1012 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:575:0x1023 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:589:0x1099 A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:594:0x10ca A[Catch:{ IOException -> 0x01f7, all -> 0x1130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:613:0x112c A[SYNTHETIC, Splitter:B:613:0x112c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r42, long r43) {
        /*
            r41 = this;
            r1 = r41
            java.lang.String r2 = "_ai"
            java.lang.String r3 = "items"
            com.google.android.gms.measurement.internal.zzal r4 = r41.zzf()
            r4.zzp()
            com.google.android.gms.measurement.internal.zznc$zza r4 = new com.google.android.gms.measurement.internal.zznc$zza     // Catch:{ all -> 0x1130 }
            r5 = 0
            r4.<init>()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            long r7 = r1.zzab     // Catch:{ all -> 0x1130 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x1130 }
            r6.zzt()     // Catch:{ all -> 0x1130 }
            r6.zzal()     // Catch:{ all -> 0x1130 }
            r10 = -1
            r12 = 1
            r13 = 0
            android.database.sqlite.SQLiteDatabase r15 = r6.e_()     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            boolean r14 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r16 = ""
            if (r14 == 0) goto L_0x0081
            int r14 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r14 == 0) goto L_0x0043
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r5 = java.lang.String.valueOf(r43)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String[] r5 = new java.lang.String[]{r9, r5}     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            goto L_0x004b
        L_0x0043:
            java.lang.String r5 = java.lang.String.valueOf(r43)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String[] r5 = new java.lang.String[]{r5}     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
        L_0x004b:
            if (r14 == 0) goto L_0x004f
            java.lang.String r16 = "rowid <= ? and "
        L_0x004f:
            r9 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r10 = "select app_id, metadata_fingerprint from raw_events where "
            r14.<init>(r10)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            r14.append(r9)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r9 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r14.append(r9)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r9 = r14.toString()     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            android.database.Cursor r5 = r15.rawQuery(r9, r5)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0213 }
            if (r9 != 0) goto L_0x0075
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x0075:
            java.lang.String r9 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x0213 }
            java.lang.String r10 = r5.getString(r12)     // Catch:{ SQLiteException -> 0x0210 }
            r5.close()     // Catch:{ SQLiteException -> 0x0210 }
            goto L_0x00d8
        L_0x0081:
            r9 = r10
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x00a1
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r10 = 0
            java.lang.String[] r9 = new java.lang.String[]{r10, r9}     // Catch:{ SQLiteException -> 0x0095, all -> 0x0090 }
            goto L_0x00a6
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            r1 = r0
            r5 = r10
            goto L_0x112a
        L_0x0095:
            r0 = move-exception
        L_0x0096:
            r7 = r0
            r5 = r10
            r9 = r5
            goto L_0x021f
        L_0x009b:
            r0 = move-exception
            r10 = 0
            goto L_0x0091
        L_0x009e:
            r0 = move-exception
            r10 = 0
            goto L_0x0096
        L_0x00a1:
            r10 = 0
            java.lang.String[] r9 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
        L_0x00a6:
            if (r5 == 0) goto L_0x00aa
            java.lang.String r16 = " and rowid <= ?"
        L_0x00aa:
            r5 = r16
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r11 = "select metadata_fingerprint from raw_events where app_id = ?"
            r10.<init>(r11)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            r10.append(r5)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r5 = " order by rowid limit 1;"
            r10.append(r5)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            java.lang.String r5 = r10.toString()     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            android.database.Cursor r5 = r15.rawQuery(r5, r9)     // Catch:{ SQLiteException -> 0x021b, all -> 0x0216 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0213 }
            if (r9 != 0) goto L_0x00d0
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x00d0:
            java.lang.String r10 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x0213 }
            r5.close()     // Catch:{ SQLiteException -> 0x0213 }
            r9 = 0
        L_0x00d8:
            java.lang.String r11 = "raw_events_metadata"
            java.lang.String r14 = "metadata"
            java.lang.String[] r16 = new java.lang.String[]{r14}     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r18 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "2"
            r19 = 0
            r20 = 0
            r14 = r15
            r23 = r15
            r15 = r11
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x0210 }
            boolean r11 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0210 }
            if (r11 != 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzg()     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r8 = "Raw event metadata record is missing. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0210 }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x0210 }
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x0114:
            byte[] r11 = r5.getBlob(r13)     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r14 = com.google.android.gms.internal.measurement.zzfn.zzk.zzw()     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzks r11 = com.google.android.gms.measurement.internal.zznl.zza(r14, (byte[]) r11)     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r11 = (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r11     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzkt r11 = r11.zzai()     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzjk r11 = (com.google.android.gms.internal.measurement.zzjk) r11     // Catch:{ IOException -> 0x01f7 }
            com.google.android.gms.internal.measurement.zzfn$zzk r11 = (com.google.android.gms.internal.measurement.zzfn.zzk) r11     // Catch:{ IOException -> 0x01f7 }
            boolean r14 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0210 }
            if (r14 == 0) goto L_0x0141
            com.google.android.gms.measurement.internal.zzfw r14 = r6.zzj()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.measurement.internal.zzfy r14 = r14.zzu()     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r15 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0210 }
            r14.zza(r15, r12)     // Catch:{ SQLiteException -> 0x0210 }
        L_0x0141:
            r5.close()     // Catch:{ SQLiteException -> 0x0210 }
            r4.zza(r11)     // Catch:{ SQLiteException -> 0x0210 }
            r11 = -1
            int r14 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r14 == 0) goto L_0x015c
            java.lang.String r11 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String[] r7 = new java.lang.String[]{r9, r10, r7}     // Catch:{ SQLiteException -> 0x0210 }
            r18 = r7
            r17 = r11
            goto L_0x0166
        L_0x015c:
            java.lang.String r7 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r8 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x0210 }
            r17 = r7
            r18 = r8
        L_0x0166:
            java.lang.String r15 = "raw_events"
            java.lang.String r7 = "rowid"
            java.lang.String r8 = "name"
            java.lang.String r10 = "timestamp"
            java.lang.String r11 = "data"
            java.lang.String[] r16 = new java.lang.String[]{r7, r8, r10, r11}     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r21 = "rowid"
            r22 = 0
            r19 = 0
            r20 = 0
            r14 = r23
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x0210 }
            boolean r7 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0210 }
            if (r7 != 0) goto L_0x01a0
            com.google.android.gms.measurement.internal.zzfw r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzu()     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r8 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0210 }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x0210 }
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x01a0:
            long r7 = r5.getLong(r13)     // Catch:{ SQLiteException -> 0x0210 }
            r10 = 3
            byte[] r11 = r5.getBlob(r10)     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r10 = com.google.android.gms.internal.measurement.zzfn.zzf.zze()     // Catch:{ IOException -> 0x01d8 }
            com.google.android.gms.internal.measurement.zzks r10 = com.google.android.gms.measurement.internal.zznl.zza(r10, (byte[]) r11)     // Catch:{ IOException -> 0x01d8 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10     // Catch:{ IOException -> 0x01d8 }
            r11 = 1
            java.lang.String r12 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r11 = r10.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x0210 }
            r12 = 2
            long r14 = r5.getLong(r12)     // Catch:{ SQLiteException -> 0x0210 }
            r11.zzb((long) r14)     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzkt r10 = r10.zzai()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzjk r10 = (com.google.android.gms.internal.measurement.zzjk) r10     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.internal.measurement.zzfn$zzf r10 = (com.google.android.gms.internal.measurement.zzfn.zzf) r10     // Catch:{ SQLiteException -> 0x0210 }
            boolean r7 = r4.zza(r7, r10)     // Catch:{ SQLiteException -> 0x0210 }
            if (r7 != 0) goto L_0x01eb
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x01d8:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzfw r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r10 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0210 }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x0210 }
        L_0x01eb:
            boolean r7 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0210 }
            if (r7 != 0) goto L_0x01a0
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x01f7:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzfw r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x0210 }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x0210 }
            java.lang.String r10 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0210 }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x0210 }
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
            goto L_0x0235
        L_0x0210:
            r0 = move-exception
            r7 = r0
            goto L_0x021f
        L_0x0213:
            r0 = move-exception
            r7 = r0
            goto L_0x021e
        L_0x0216:
            r0 = move-exception
            r1 = r0
            r5 = 0
            goto L_0x112a
        L_0x021b:
            r0 = move-exception
            r7 = r0
            r5 = 0
        L_0x021e:
            r9 = 0
        L_0x021f:
            com.google.android.gms.measurement.internal.zzfw r6 = r6.zzj()     // Catch:{ all -> 0x1128 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzg()     // Catch:{ all -> 0x1128 }
            java.lang.String r8 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r9)     // Catch:{ all -> 0x1128 }
            r6.zza(r8, r9, r7)     // Catch:{ all -> 0x1128 }
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x1130 }
        L_0x0235:
            java.util.List<com.google.android.gms.internal.measurement.zzfn$zzf> r5 = r4.zzc     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x1118
            java.util.List<com.google.android.gms.internal.measurement.zzfn$zzf> r5 = r4.zzc     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0243
            goto L_0x1118
        L_0x0243:
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r5 = r5.zzcc()     // Catch:{ all -> 0x1130 }
            r6 = r5
            com.google.android.gms.internal.measurement.zzjk$zzb r6 = (com.google.android.gms.internal.measurement.zzjk.zzb) r6     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r5 = (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r5 = r5.zzl()     // Catch:{ all -> 0x1130 }
            r9 = r13
            r10 = r9
            r11 = r10
            r7 = 0
            r8 = 0
            r12 = -1
            r14 = -1
        L_0x0259:
            java.util.List<com.google.android.gms.internal.measurement.zzfn$zzf> r15 = r4.zzc     // Catch:{ all -> 0x1130 }
            int r15 = r15.size()     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = "_fr"
            java.lang.String r6 = "_et"
            r17 = r10
            java.lang.String r10 = "_e"
            r44 = r11
            java.lang.String r11 = "_c"
            r19 = r6
            r18 = r7
            if (r9 >= r15) goto L_0x0859
            java.util.List<com.google.android.gms.internal.measurement.zzfn$zzf> r15 = r4.zzc     // Catch:{ all -> 0x1130 }
            java.lang.Object r15 = r15.get(r9)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r15 = (com.google.android.gms.internal.measurement.zzfn.zzf) r15     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r15 = r15.zzcc()     // Catch:{ all -> 0x1130 }
            r20 = r15
            com.google.android.gms.internal.measurement.zzjk$zzb r20 = (com.google.android.gms.internal.measurement.zzjk.zzb) r20     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r15 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzgt r6 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r7 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1130 }
            r22 = r9
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zzd(r7, r9)     // Catch:{ all -> 0x1130 }
            java.lang.String r7 = "_err"
            if (r6 == 0) goto L_0x0311
            com.google.android.gms.measurement.internal.zzfw r6 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = "Dropping blocked raw event. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r10 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r10)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzhj r11 = r1.zzm     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfr r11 = r11.zzk()     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = r15.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r11 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x1130 }
            r6.zza(r9, r10, r11)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzgt r6 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r9 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zzm(r9)     // Catch:{ all -> 0x1130 }
            if (r6 != 0) goto L_0x0305
            com.google.android.gms.measurement.internal.zzgt r6 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r9 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zzo(r9)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x02e1
            goto L_0x0305
        L_0x02e1:
            java.lang.String r6 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r6 = r7.equals(r6)     // Catch:{ all -> 0x1130 }
            if (r6 != 0) goto L_0x0305
            r41.zzq()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznr r6 = r1.zzah     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r7 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r25 = r7.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r27 = "_ev"
            java.lang.String r28 = r15.zze()     // Catch:{ all -> 0x1130 }
            r29 = 0
            r26 = 11
            r24 = r6
            com.google.android.gms.measurement.internal.zznp.zza((com.google.android.gms.measurement.internal.zznr) r24, (java.lang.String) r25, (int) r26, (java.lang.String) r27, (java.lang.String) r28, (int) r29)     // Catch:{ all -> 0x1130 }
        L_0x0305:
            r24 = r2
            r11 = r3
            r7 = r5
            r10 = r17
            r13 = r22
            r2 = r44
            goto L_0x084d
        L_0x0311:
            java.lang.String r6 = r15.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = com.google.android.gms.measurement.internal.zziq.zza(r2)     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.equals(r9)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0383
            r15.zza((java.lang.String) r2)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfw r6 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = "Renaming ad_impression to _ai"
            r6.zza(r9)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfw r6 = r41.zzj()     // Catch:{ all -> 0x1130 }
            r9 = 5
            boolean r6 = r6.zza((int) r9)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0383
            r6 = 0
        L_0x033b:
            int r9 = r15.zza()     // Catch:{ all -> 0x1130 }
            if (r6 >= r9) goto L_0x0383
            java.lang.String r9 = "ad_platform"
            com.google.android.gms.internal.measurement.zzfn$zzh r23 = r15.zzb((int) r6)     // Catch:{ all -> 0x1130 }
            r24 = r2
            java.lang.String r2 = r23.zzg()     // Catch:{ all -> 0x1130 }
            boolean r2 = r9.equals(r2)     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x037e
            com.google.android.gms.internal.measurement.zzfn$zzh r2 = r15.zzb((int) r6)     // Catch:{ all -> 0x1130 }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x1130 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x1130 }
            if (r2 != 0) goto L_0x037e
            java.lang.String r2 = "admob"
            com.google.android.gms.internal.measurement.zzfn$zzh r9 = r15.zzb((int) r6)     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzh()     // Catch:{ all -> 0x1130 }
            boolean r2 = r2.equalsIgnoreCase(r9)     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x037e
            com.google.android.gms.measurement.internal.zzfw r2 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzv()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = "AdMob ad impression logged from app. Potentially duplicative."
            r2.zza(r9)     // Catch:{ all -> 0x1130 }
        L_0x037e:
            int r6 = r6 + 1
            r2 = r24
            goto L_0x033b
        L_0x0383:
            r24 = r2
            com.google.android.gms.measurement.internal.zzgt r2 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r2 = r2.zzc((java.lang.String) r6, (java.lang.String) r9)     // Catch:{ all -> 0x1130 }
            if (r2 != 0) goto L_0x03c1
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r15.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x1130 }
            int r9 = r6.hashCode()     // Catch:{ all -> 0x1130 }
            r23 = r3
            r3 = 95027(0x17333, float:1.33161E-40)
            if (r9 == r3) goto L_0x03af
            goto L_0x03b8
        L_0x03af:
            java.lang.String r3 = "_ui"
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x1130 }
            if (r3 == 0) goto L_0x03b8
            goto L_0x03c3
        L_0x03b8:
            r26 = r5
            r6 = r8
            r25 = r12
            r27 = r13
            goto L_0x059b
        L_0x03c1:
            r23 = r3
        L_0x03c3:
            r25 = r12
            r3 = 0
            r6 = 0
            r9 = 0
        L_0x03c8:
            int r12 = r15.zza()     // Catch:{ all -> 0x1130 }
            r26 = r5
            java.lang.String r5 = "_r"
            if (r3 >= r12) goto L_0x0438
            com.google.android.gms.internal.measurement.zzfn$zzh r12 = r15.zzb((int) r3)     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x1130 }
            boolean r12 = r11.equals(r12)     // Catch:{ all -> 0x1130 }
            if (r12 == 0) goto L_0x0402
            com.google.android.gms.internal.measurement.zzfn$zzh r5 = r15.zzb((int) r3)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r5 = r5.zzcc()     // Catch:{ all -> 0x1130 }
            r6 = r5
            com.google.android.gms.internal.measurement.zzjk$zzb r6 = (com.google.android.gms.internal.measurement.zzjk.zzb) r6     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r5     // Catch:{ all -> 0x1130 }
            r27 = r13
            r12 = 1
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = r5.zza((long) r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r5 = r5.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r5 = (com.google.android.gms.internal.measurement.zzjk) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r5 = (com.google.android.gms.internal.measurement.zzfn.zzh) r5     // Catch:{ all -> 0x1130 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfn.zzh) r5)     // Catch:{ all -> 0x1130 }
            r6 = 1
            goto L_0x0431
        L_0x0402:
            r27 = r13
            com.google.android.gms.internal.measurement.zzfn$zzh r12 = r15.zzb((int) r3)     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.equals(r12)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0431
            com.google.android.gms.internal.measurement.zzfn$zzh r5 = r15.zzb((int) r3)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r5 = r5.zzcc()     // Catch:{ all -> 0x1130 }
            r9 = r5
            com.google.android.gms.internal.measurement.zzjk$zzb r9 = (com.google.android.gms.internal.measurement.zzjk.zzb) r9     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r5     // Catch:{ all -> 0x1130 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = r5.zza((long) r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r5 = r5.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r5 = (com.google.android.gms.internal.measurement.zzjk) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r5 = (com.google.android.gms.internal.measurement.zzfn.zzh) r5     // Catch:{ all -> 0x1130 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfn.zzh) r5)     // Catch:{ all -> 0x1130 }
            r9 = 1
        L_0x0431:
            int r3 = r3 + 1
            r5 = r26
            r13 = r27
            goto L_0x03c8
        L_0x0438:
            r27 = r13
            if (r6 != 0) goto L_0x046a
            if (r2 == 0) goto L_0x046a
            com.google.android.gms.measurement.internal.zzfw r3 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzhj r12 = r1.zzm     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfr r12 = r12.zzk()     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = r15.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zza((java.lang.String) r13)     // Catch:{ all -> 0x1130 }
            r3.zza(r6, r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = com.google.android.gms.internal.measurement.zzfn.zzh.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = r3.zza((java.lang.String) r11)     // Catch:{ all -> 0x1130 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = r3.zza((long) r12)     // Catch:{ all -> 0x1130 }
            r15.zza((com.google.android.gms.internal.measurement.zzfn.zzh.zza) r3)     // Catch:{ all -> 0x1130 }
        L_0x046a:
            if (r9 != 0) goto L_0x0498
            com.google.android.gms.measurement.internal.zzfw r3 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzhj r9 = r1.zzm     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfr r9 = r9.zzk()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r15.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zza((java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            r3.zza(r6, r9)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = com.google.android.gms.internal.measurement.zzfn.zzh.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = r3.zza((java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = r3.zza((long) r12)     // Catch:{ all -> 0x1130 }
            r15.zza((com.google.android.gms.internal.measurement.zzfn.zzh.zza) r3)     // Catch:{ all -> 0x1130 }
        L_0x0498:
            com.google.android.gms.measurement.internal.zzal r28 = r41.zzf()     // Catch:{ all -> 0x1130 }
            long r29 = r41.zzx()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r3 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r31 = r3.zzz()     // Catch:{ all -> 0x1130 }
            r36 = 1
            r37 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            com.google.android.gms.measurement.internal.zzaq r3 = r28.zza(r29, r31, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x1130 }
            long r12 = r3.zze     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzag r3 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            int r3 = r3.zzc(r6)     // Catch:{ all -> 0x1130 }
            r6 = r8
            long r8 = (long) r3     // Catch:{ all -> 0x1130 }
            int r3 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r3 <= 0) goto L_0x04d0
            zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x04d2
        L_0x04d0:
            r17 = 1
        L_0x04d2:
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r3 = com.google.android.gms.measurement.internal.zznp.zzh(r3)     // Catch:{ all -> 0x1130 }
            if (r3 == 0) goto L_0x059b
            if (r2 == 0) goto L_0x059b
            com.google.android.gms.measurement.internal.zzal r28 = r41.zzf()     // Catch:{ all -> 0x1130 }
            long r29 = r41.zzx()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r3 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r31 = r3.zzz()     // Catch:{ all -> 0x1130 }
            r36 = 0
            r37 = 0
            r32 = 0
            r33 = 0
            r34 = 1
            r35 = 0
            com.google.android.gms.measurement.internal.zzaq r3 = r28.zza(r29, r31, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x1130 }
            long r8 = r3.zzc     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzag r3 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbf.zzn     // Catch:{ all -> 0x1130 }
            int r3 = r3.zzb((java.lang.String) r5, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r12)     // Catch:{ all -> 0x1130 }
            long r12 = (long) r3     // Catch:{ all -> 0x1130 }
            int r3 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r3 <= 0) goto L_0x059b
            com.google.android.gms.measurement.internal.zzfw r3 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x1130 }
            r3.zza(r5, r8)     // Catch:{ all -> 0x1130 }
            r3 = -1
            r5 = 0
            r8 = 0
            r9 = 0
        L_0x052e:
            int r12 = r15.zza()     // Catch:{ all -> 0x1130 }
            if (r5 >= r12) goto L_0x055c
            com.google.android.gms.internal.measurement.zzfn$zzh r12 = r15.zzb((int) r5)     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = r12.zzg()     // Catch:{ all -> 0x1130 }
            boolean r13 = r11.equals(r13)     // Catch:{ all -> 0x1130 }
            if (r13 == 0) goto L_0x054e
            com.google.android.gms.internal.measurement.zzjk$zzb r3 = r12.zzcc()     // Catch:{ all -> 0x1130 }
            r8 = r3
            com.google.android.gms.internal.measurement.zzjk$zzb r8 = (com.google.android.gms.internal.measurement.zzjk.zzb) r8     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r3 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r3     // Catch:{ all -> 0x1130 }
            r8 = r3
            r3 = r5
            goto L_0x0559
        L_0x054e:
            java.lang.String r12 = r12.zzg()     // Catch:{ all -> 0x1130 }
            boolean r12 = r7.equals(r12)     // Catch:{ all -> 0x1130 }
            if (r12 == 0) goto L_0x0559
            r9 = 1
        L_0x0559:
            int r5 = r5 + 1
            goto L_0x052e
        L_0x055c:
            if (r9 == 0) goto L_0x0564
            if (r8 == 0) goto L_0x0564
            r15.zza((int) r3)     // Catch:{ all -> 0x1130 }
            goto L_0x059b
        L_0x0564:
            if (r8 == 0) goto L_0x0584
            java.lang.Object r5 = r8.clone()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r5 = (com.google.android.gms.internal.measurement.zzjk.zzb) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = r5.zza((java.lang.String) r7)     // Catch:{ all -> 0x1130 }
            r7 = 10
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r5 = r5.zza((long) r7)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r5 = r5.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r5 = (com.google.android.gms.internal.measurement.zzjk) r5     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r5 = (com.google.android.gms.internal.measurement.zzfn.zzh) r5     // Catch:{ all -> 0x1130 }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzfn.zzh) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x059b
        L_0x0584:
            com.google.android.gms.measurement.internal.zzfw r3 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r7 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r7)     // Catch:{ all -> 0x1130 }
            r3.zza(r5, r7)     // Catch:{ all -> 0x1130 }
        L_0x059b:
            if (r2 == 0) goto L_0x0654
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x1130 }
            java.util.List r3 = r15.zzf()     // Catch:{ all -> 0x1130 }
            r2.<init>(r3)     // Catch:{ all -> 0x1130 }
            r3 = 0
            r5 = -1
            r7 = -1
        L_0x05a9:
            int r8 = r2.size()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = "currency"
            java.lang.String r12 = "value"
            if (r3 >= r8) goto L_0x05d9
            java.lang.Object r8 = r2.get(r3)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r8 = (com.google.android.gms.internal.measurement.zzfn.zzh) r8     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzg()     // Catch:{ all -> 0x1130 }
            boolean r8 = r12.equals(r8)     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x05c5
            r5 = r3
            goto L_0x05d6
        L_0x05c5:
            java.lang.Object r8 = r2.get(r3)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r8 = (com.google.android.gms.internal.measurement.zzfn.zzh) r8     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzg()     // Catch:{ all -> 0x1130 }
            boolean r8 = r9.equals(r8)     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x05d6
            r7 = r3
        L_0x05d6:
            int r3 = r3 + 1
            goto L_0x05a9
        L_0x05d9:
            r3 = -1
            if (r5 == r3) goto L_0x0655
            java.lang.Object r3 = r2.get(r5)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r3 = (com.google.android.gms.internal.measurement.zzfn.zzh) r3     // Catch:{ all -> 0x1130 }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x1130 }
            if (r3 != 0) goto L_0x060d
            java.lang.Object r3 = r2.get(r5)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r3 = (com.google.android.gms.internal.measurement.zzfn.zzh) r3     // Catch:{ all -> 0x1130 }
            boolean r3 = r3.zzj()     // Catch:{ all -> 0x1130 }
            if (r3 != 0) goto L_0x060d
            com.google.android.gms.measurement.internal.zzfw r2 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzv()     // Catch:{ all -> 0x1130 }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x1130 }
            r15.zza((int) r5)     // Catch:{ all -> 0x1130 }
            zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x1130 }
            r2 = 18
            zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (int) r2, (java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            goto L_0x0654
        L_0x060d:
            r3 = -1
            if (r7 != r3) goto L_0x0612
            r8 = 3
            goto L_0x0635
        L_0x0612:
            java.lang.Object r2 = r2.get(r7)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r2 = (com.google.android.gms.internal.measurement.zzfn.zzh) r2     // Catch:{ all -> 0x1130 }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x1130 }
            int r7 = r2.length()     // Catch:{ all -> 0x1130 }
            r8 = 3
            if (r7 == r8) goto L_0x0624
            goto L_0x0635
        L_0x0624:
            r7 = 0
        L_0x0625:
            int r12 = r2.length()     // Catch:{ all -> 0x1130 }
            if (r7 >= r12) goto L_0x0656
            int r12 = r2.codePointAt(r7)     // Catch:{ all -> 0x1130 }
            boolean r13 = java.lang.Character.isLetter(r12)     // Catch:{ all -> 0x1130 }
            if (r13 != 0) goto L_0x064e
        L_0x0635:
            com.google.android.gms.measurement.internal.zzfw r2 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzv()     // Catch:{ all -> 0x1130 }
            java.lang.String r7 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r7)     // Catch:{ all -> 0x1130 }
            r15.zza((int) r5)     // Catch:{ all -> 0x1130 }
            zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x1130 }
            r2 = 19
            zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (int) r2, (java.lang.String) r9)     // Catch:{ all -> 0x1130 }
            goto L_0x0656
        L_0x064e:
            int r12 = java.lang.Character.charCount(r12)     // Catch:{ all -> 0x1130 }
            int r7 = r7 + r12
            goto L_0x0625
        L_0x0654:
            r3 = -1
        L_0x0655:
            r8 = 3
        L_0x0656:
            java.lang.String r2 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r2 = r10.equals(r2)     // Catch:{ all -> 0x1130 }
            r9 = 1000(0x3e8, double:4.94E-321)
            if (r2 == 0) goto L_0x06ac
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r2 = r15.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r2 = (com.google.android.gms.internal.measurement.zzjk) r2     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r2 = (com.google.android.gms.internal.measurement.zzfn.zzf) r2     // Catch:{ all -> 0x1130 }
            r5 = r27
            com.google.android.gms.internal.measurement.zzfn$zzh r2 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r2, (java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            if (r2 != 0) goto L_0x06a9
            if (r6 == 0) goto L_0x06a2
            long r11 = r6.zzc()     // Catch:{ all -> 0x1130 }
            long r18 = r15.zzc()     // Catch:{ all -> 0x1130 }
            long r11 = r11 - r18
            long r11 = java.lang.Math.abs(r11)     // Catch:{ all -> 0x1130 }
            int r2 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r2 > 0) goto L_0x06a2
            java.lang.Object r2 = r6.clone()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r2 = (com.google.android.gms.internal.measurement.zzjk.zzb) r2     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2     // Catch:{ all -> 0x1130 }
            boolean r5 = r1.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x06a2
            r7 = r26
            r7.zza((int) r14, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2)     // Catch:{ all -> 0x1130 }
            r12 = r25
        L_0x069e:
            r6 = 0
            r18 = 0
            goto L_0x06ff
        L_0x06a2:
            r7 = r26
            r12 = r44
            r18 = r15
            goto L_0x06ff
        L_0x06a9:
            r7 = r26
            goto L_0x06fc
        L_0x06ac:
            r7 = r26
            java.lang.String r2 = "_vs"
            java.lang.String r5 = r15.zze()     // Catch:{ all -> 0x1130 }
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x06fc
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r2 = r15.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r2 = (com.google.android.gms.internal.measurement.zzjk) r2     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r2 = (com.google.android.gms.internal.measurement.zzfn.zzf) r2     // Catch:{ all -> 0x1130 }
            r12 = r19
            com.google.android.gms.internal.measurement.zzfn$zzh r2 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r2, (java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            if (r2 != 0) goto L_0x06fc
            if (r18 == 0) goto L_0x06f5
            long r5 = r18.zzc()     // Catch:{ all -> 0x1130 }
            long r11 = r15.zzc()     // Catch:{ all -> 0x1130 }
            long r5 = r5 - r11
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x1130 }
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 > 0) goto L_0x06f5
            java.lang.Object r2 = r18.clone()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r2 = (com.google.android.gms.internal.measurement.zzjk.zzb) r2     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2     // Catch:{ all -> 0x1130 }
            boolean r5 = r1.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x06f5
            r5 = r25
            r7.zza((int) r5, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2)     // Catch:{ all -> 0x1130 }
            r12 = r5
            goto L_0x069e
        L_0x06f5:
            r5 = r25
            r14 = r44
            r12 = r5
            r6 = r15
            goto L_0x06ff
        L_0x06fc:
            r5 = r25
            r12 = r5
        L_0x06ff:
            boolean r2 = com.google.android.gms.internal.measurement.zzop.zza()     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x0831
            com.google.android.gms.measurement.internal.zzag r2 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzcn     // Catch:{ all -> 0x1130 }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x0831
            int r2 = r15.zza()     // Catch:{ all -> 0x1130 }
            if (r2 == 0) goto L_0x0831
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.util.List r2 = r15.zzf()     // Catch:{ all -> 0x1130 }
            android.os.Bundle r2 = com.google.android.gms.measurement.internal.zznl.zza((java.util.List<com.google.android.gms.internal.measurement.zzfn.zzh>) r2)     // Catch:{ all -> 0x1130 }
            r5 = 0
        L_0x0723:
            int r9 = r15.zza()     // Catch:{ all -> 0x1130 }
            if (r5 >= r9) goto L_0x07da
            com.google.android.gms.internal.measurement.zzfn$zzh r9 = r15.zzb((int) r5)     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r9.zzg()     // Catch:{ all -> 0x1130 }
            r11 = r23
            boolean r10 = r10.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r10 == 0) goto L_0x07ae
            java.util.List r10 = r9.zzi()     // Catch:{ all -> 0x1130 }
            boolean r10 = r10.isEmpty()     // Catch:{ all -> 0x1130 }
            if (r10 != 0) goto L_0x07ae
            com.google.android.gms.internal.measurement.zzfn$zzk r10 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1130 }
            java.util.List r9 = r9.zzi()     // Catch:{ all -> 0x1130 }
            int r13 = r9.size()     // Catch:{ all -> 0x1130 }
            android.os.Bundle[] r13 = new android.os.Bundle[r13]     // Catch:{ all -> 0x1130 }
            r3 = 0
        L_0x0754:
            int r8 = r9.size()     // Catch:{ all -> 0x1130 }
            if (r3 >= r8) goto L_0x07a8
            java.lang.Object r8 = r9.get(r3)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r8 = (com.google.android.gms.internal.measurement.zzfn.zzh) r8     // Catch:{ all -> 0x1130 }
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.util.List r19 = r8.zzi()     // Catch:{ all -> 0x1130 }
            r20 = r6
            android.os.Bundle r6 = com.google.android.gms.measurement.internal.zznl.zza((java.util.List<com.google.android.gms.internal.measurement.zzfn.zzh>) r19)     // Catch:{ all -> 0x1130 }
            java.util.List r8 = r8.zzi()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x1130 }
        L_0x0775:
            boolean r19 = r8.hasNext()     // Catch:{ all -> 0x1130 }
            if (r19 == 0) goto L_0x079d
            java.lang.Object r19 = r8.next()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r19 = (com.google.android.gms.internal.measurement.zzfn.zzh) r19     // Catch:{ all -> 0x1130 }
            r21 = r8
            java.lang.String r8 = r15.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r19 = r19.zzcc()     // Catch:{ all -> 0x1130 }
            r23 = r19
            com.google.android.gms.internal.measurement.zzjk$zzb r23 = (com.google.android.gms.internal.measurement.zzjk.zzb) r23     // Catch:{ all -> 0x1130 }
            r23 = r9
            r9 = r19
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r9 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r9     // Catch:{ all -> 0x1130 }
            r1.zza((java.lang.String) r8, (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r9, (android.os.Bundle) r6, (java.lang.String) r10)     // Catch:{ all -> 0x1130 }
            r8 = r21
            r9 = r23
            goto L_0x0775
        L_0x079d:
            r23 = r9
            r13[r3] = r6     // Catch:{ all -> 0x1130 }
            int r3 = r3 + 1
            r6 = r20
            r9 = r23
            goto L_0x0754
        L_0x07a8:
            r20 = r6
            r2.putParcelableArray(r11, r13)     // Catch:{ all -> 0x1130 }
            goto L_0x07d0
        L_0x07ae:
            r20 = r6
            java.lang.String r3 = r9.zzg()     // Catch:{ all -> 0x1130 }
            boolean r3 = r3.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r3 != 0) goto L_0x07d0
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r6 = r9.zzcc()     // Catch:{ all -> 0x1130 }
            r8 = r6
            com.google.android.gms.internal.measurement.zzjk$zzb r8 = (com.google.android.gms.internal.measurement.zzjk.zzb) r8     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r6 = (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r6     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            r1.zza((java.lang.String) r3, (com.google.android.gms.internal.measurement.zzfn.zzh.zza) r6, (android.os.Bundle) r2, (java.lang.String) r8)     // Catch:{ all -> 0x1130 }
        L_0x07d0:
            int r5 = r5 + 1
            r23 = r11
            r6 = r20
            r3 = -1
            r8 = 3
            goto L_0x0723
        L_0x07da:
            r20 = r6
            r11 = r23
            r15.zzd()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl r3 = r41.zzp()     // Catch:{ all -> 0x1130 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x1130 }
            r5.<init>()     // Catch:{ all -> 0x1130 }
            java.util.Set r6 = r2.keySet()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x1130 }
        L_0x07f2:
            boolean r8 = r6.hasNext()     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x081b
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r9 = com.google.android.gms.internal.measurement.zzfn.zzh.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r9 = r9.zza((java.lang.String) r8)     // Catch:{ all -> 0x1130 }
            java.lang.Object r8 = r2.get(r8)     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x07f2
            r3.zza((com.google.android.gms.internal.measurement.zzfn.zzh.zza) r9, (java.lang.Object) r8)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r8 = r9.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r8 = (com.google.android.gms.internal.measurement.zzjk) r8     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r8 = (com.google.android.gms.internal.measurement.zzfn.zzh) r8     // Catch:{ all -> 0x1130 }
            r5.add(r8)     // Catch:{ all -> 0x1130 }
            goto L_0x07f2
        L_0x081b:
            r2 = r5
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch:{ all -> 0x1130 }
            int r2 = r5.size()     // Catch:{ all -> 0x1130 }
            r3 = 0
        L_0x0823:
            if (r3 >= r2) goto L_0x0835
            java.lang.Object r6 = r5.get(r3)     // Catch:{ all -> 0x1130 }
            int r3 = r3 + 1
            com.google.android.gms.internal.measurement.zzfn$zzh r6 = (com.google.android.gms.internal.measurement.zzfn.zzh) r6     // Catch:{ all -> 0x1130 }
            r15.zza((com.google.android.gms.internal.measurement.zzfn.zzh) r6)     // Catch:{ all -> 0x1130 }
            goto L_0x0823
        L_0x0831:
            r20 = r6
            r11 = r23
        L_0x0835:
            java.util.List<com.google.android.gms.internal.measurement.zzfn$zzf> r2 = r4.zzc     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r3 = r15.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r3 = (com.google.android.gms.internal.measurement.zzjk) r3     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r3 = (com.google.android.gms.internal.measurement.zzfn.zzf) r3     // Catch:{ all -> 0x1130 }
            r13 = r22
            r2.set(r13, r3)     // Catch:{ all -> 0x1130 }
            int r2 = r44 + 1
            r7.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r15)     // Catch:{ all -> 0x1130 }
            r10 = r17
            r8 = r20
        L_0x084d:
            int r9 = r13 + 1
            r5 = r7
            r3 = r11
            r7 = r18
            r13 = 0
            r11 = r2
            r2 = r24
            goto L_0x0259
        L_0x0859:
            r7 = r5
            r5 = r13
            r12 = r19
            r2 = 0
            r6 = r44
            r13 = r2
            r8 = 0
        L_0x0863:
            if (r8 >= r6) goto L_0x08b0
            com.google.android.gms.internal.measurement.zzfn$zzf r9 = r7.zza((int) r8)     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = r9.zzg()     // Catch:{ all -> 0x1130 }
            boolean r15 = r10.equals(r15)     // Catch:{ all -> 0x1130 }
            if (r15 == 0) goto L_0x0884
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r15 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r9, (java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            if (r15 == 0) goto L_0x0884
            r7.zzb((int) r8)     // Catch:{ all -> 0x1130 }
            int r6 = r6 + -1
            int r8 = r8 + -1
            goto L_0x08ad
        L_0x0884:
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r9 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf) r9, (java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            if (r9 == 0) goto L_0x08ad
            boolean r15 = r9.zzl()     // Catch:{ all -> 0x1130 }
            if (r15 == 0) goto L_0x089c
            long r18 = r9.zzd()     // Catch:{ all -> 0x1130 }
            java.lang.Long r9 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x1130 }
            goto L_0x089d
        L_0x089c:
            r9 = 0
        L_0x089d:
            if (r9 == 0) goto L_0x08ad
            long r18 = r9.longValue()     // Catch:{ all -> 0x1130 }
            int r15 = (r18 > r2 ? 1 : (r18 == r2 ? 0 : -1))
            if (r15 <= 0) goto L_0x08ad
            long r18 = r9.longValue()     // Catch:{ all -> 0x1130 }
            long r13 = r13 + r18
        L_0x08ad:
            r9 = 1
            int r8 = r8 + r9
            goto L_0x0863
        L_0x08b0:
            r5 = 0
            r1.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (long) r13, (boolean) r5)     // Catch:{ all -> 0x1130 }
            java.util.List r5 = r7.zzaa()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x1130 }
        L_0x08bc:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = "_se"
            if (r6 == 0) goto L_0x08e1
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r6 = (com.google.android.gms.internal.measurement.zzfn.zzf) r6     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = "_s"
            java.lang.String r6 = r6.zzg()     // Catch:{ all -> 0x1130 }
            boolean r6 = r9.equals(r6)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x08bc
            com.google.android.gms.measurement.internal.zzal r5 = r41.zzf()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r7.zzt()     // Catch:{ all -> 0x1130 }
            r5.zzh(r6, r8)     // Catch:{ all -> 0x1130 }
        L_0x08e1:
            java.lang.String r5 = "_sid"
            int r5 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            if (r5 < 0) goto L_0x08ee
            r5 = 1
            r1.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (long) r13, (boolean) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x090e
        L_0x08ee:
            int r5 = com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (java.lang.String) r8)     // Catch:{ all -> 0x1130 }
            if (r5 < 0) goto L_0x090e
            r7.zzc((int) r5)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfw r5 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r8)     // Catch:{ all -> 0x1130 }
            r5.zza(r6, r8)     // Catch:{ all -> 0x1130 }
        L_0x090e:
            com.google.android.gms.measurement.internal.zznl r5 = r41.zzp()     // Catch:{ all -> 0x1130 }
            r5.zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzhc r6 = r41.zzl()     // Catch:{ all -> 0x1130 }
            r6.zzt()     // Catch:{ all -> 0x1130 }
            r41.zzs()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzg r6 = r6.zze(r5)     // Catch:{ all -> 0x1130 }
            if (r6 != 0) goto L_0x0941
            com.google.android.gms.measurement.internal.zzfw r6 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = "Cannot fix consent fields without appInfo. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            r6.zza(r8, r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0944
        L_0x0941:
            r1.zza((com.google.android.gms.measurement.internal.zzg) r6, (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7)     // Catch:{ all -> 0x1130 }
        L_0x0944:
            boolean r5 = com.google.android.gms.internal.measurement.zzoj.zza()     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0985
            com.google.android.gms.measurement.internal.zzag r5 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbf.zzcm     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r6)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0985
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzhc r6 = r41.zzl()     // Catch:{ all -> 0x1130 }
            r6.zzt()     // Catch:{ all -> 0x1130 }
            r41.zzs()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzg r6 = r6.zze(r5)     // Catch:{ all -> 0x1130 }
            if (r6 != 0) goto L_0x0982
            com.google.android.gms.measurement.internal.zzfw r6 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = "Cannot populate ad_campaign_info without appInfo. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            r6.zza(r8, r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0985
        L_0x0982:
            r1.zzb((com.google.android.gms.measurement.internal.zzg) r6, (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7)     // Catch:{ all -> 0x1130 }
        L_0x0985:
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r5 = r7.zzi((long) r5)     // Catch:{ all -> 0x1130 }
            r8 = -9223372036854775808
            r5.zze((long) r8)     // Catch:{ all -> 0x1130 }
            r5 = 0
        L_0x0994:
            int r6 = r7.zzc()     // Catch:{ all -> 0x1130 }
            if (r5 >= r6) goto L_0x09c7
            com.google.android.gms.internal.measurement.zzfn$zzf r6 = r7.zza((int) r5)     // Catch:{ all -> 0x1130 }
            long r8 = r6.zzd()     // Catch:{ all -> 0x1130 }
            long r12 = r7.zzf()     // Catch:{ all -> 0x1130 }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x09b1
            long r8 = r6.zzd()     // Catch:{ all -> 0x1130 }
            r7.zzi((long) r8)     // Catch:{ all -> 0x1130 }
        L_0x09b1:
            long r8 = r6.zzd()     // Catch:{ all -> 0x1130 }
            long r12 = r7.zze()     // Catch:{ all -> 0x1130 }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x09c4
            long r8 = r6.zzd()     // Catch:{ all -> 0x1130 }
            r7.zze((long) r8)     // Catch:{ all -> 0x1130 }
        L_0x09c4:
            int r5 = r5 + 1
            goto L_0x0994
        L_0x09c7:
            r7.zzs()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r5 = com.google.android.gms.measurement.internal.zzin.zza     // Catch:{ all -> 0x1130 }
            boolean r6 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0a47
            com.google.android.gms.measurement.internal.zzag r6 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzcv     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0a47
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r5 = r1.zzb((java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzae()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r6 = com.google.android.gms.measurement.internal.zzin.zzb((java.lang.String) r6)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r5 = r5.zza((com.google.android.gms.measurement.internal.zzin) r6)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r6 = r6.zzh(r8)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r8 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r9 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1130 }
            r8.zza((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzin) r5)     // Catch:{ all -> 0x1130 }
            boolean r8 = r5.zzj()     // Catch:{ all -> 0x1130 }
            if (r8 != 0) goto L_0x0a2b
            boolean r8 = r6.zzj()     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a2b
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            r6.zzo(r8)     // Catch:{ all -> 0x1130 }
            goto L_0x0a44
        L_0x0a2b:
            boolean r8 = r5.zzj()     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a44
            boolean r6 = r6.zzj()     // Catch:{ all -> 0x1130 }
            if (r6 != 0) goto L_0x0a44
            com.google.android.gms.measurement.internal.zzal r6 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            r6.zzp(r8)     // Catch:{ all -> 0x1130 }
        L_0x0a44:
            zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (com.google.android.gms.measurement.internal.zzin) r5)     // Catch:{ all -> 0x1130 }
        L_0x0a47:
            com.google.android.gms.measurement.internal.zzag r6 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzcd     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0a6e
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r5 = r1.zzb((java.lang.String) r5)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzae()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r8 = com.google.android.gms.measurement.internal.zzin.zzb((java.lang.String) r8)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r5 = r5.zza((com.google.android.gms.measurement.internal.zzin) r8)     // Catch:{ all -> 0x1130 }
            zza((com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (com.google.android.gms.measurement.internal.zzin) r5)     // Catch:{ all -> 0x1130 }
        L_0x0a6e:
            boolean r8 = com.google.android.gms.internal.measurement.zzpg.zza()     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a9f
            com.google.android.gms.measurement.internal.zzag r8 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r9 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbf.zzbz     // Catch:{ all -> 0x1130 }
            boolean r8 = r8.zze(r9, r10)     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a9f
            r41.zzq()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x1130 }
            boolean r8 = com.google.android.gms.measurement.internal.zznp.zzd(r8)     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a9f
            com.google.android.gms.internal.measurement.zzfn$zzk r8 = r4.zza     // Catch:{ all -> 0x1130 }
            boolean r8 = r8.zzat()     // Catch:{ all -> 0x1130 }
            if (r8 == 0) goto L_0x0a9f
            r8 = 1
            goto L_0x0aa0
        L_0x0a9f:
            r8 = 0
        L_0x0aa0:
            if (r6 != 0) goto L_0x0ab8
            if (r8 == 0) goto L_0x0ab6
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzin r6 = r1.zzb((java.lang.String) r6)     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zzi()     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0ab6
            r6 = 1
            goto L_0x0ab7
        L_0x0ab6:
            r6 = 0
        L_0x0ab7:
            r8 = r6
        L_0x0ab8:
            if (r8 == 0) goto L_0x0ba5
            r6 = 0
        L_0x0abb:
            int r8 = r7.zzc()     // Catch:{ all -> 0x1130 }
            if (r6 >= r8) goto L_0x0ba5
            com.google.android.gms.internal.measurement.zzfn$zzf r8 = r7.zza((int) r6)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r8 = r8.zzcc()     // Catch:{ all -> 0x1130 }
            r9 = r8
            com.google.android.gms.internal.measurement.zzjk$zzb r9 = (com.google.android.gms.internal.measurement.zzjk.zzb) r9     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r8 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r8     // Catch:{ all -> 0x1130 }
            java.util.List r9 = r8.zzf()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x1130 }
        L_0x0ad6:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x1130 }
            if (r10 == 0) goto L_0x0aee
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r10 = (com.google.android.gms.internal.measurement.zzfn.zzh) r10     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r10.zzg()     // Catch:{ all -> 0x1130 }
            boolean r10 = r11.equals(r10)     // Catch:{ all -> 0x1130 }
            if (r10 == 0) goto L_0x0ad6
            r9 = 1
            goto L_0x0aef
        L_0x0aee:
            r9 = 0
        L_0x0aef:
            if (r9 == 0) goto L_0x0ba1
            com.google.android.gms.internal.measurement.zzfn$zzk r9 = r4.zza     // Catch:{ all -> 0x1130 }
            int r9 = r9.zza()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzag r10 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r12 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Integer> r13 = com.google.android.gms.measurement.internal.zzbf.zzav     // Catch:{ all -> 0x1130 }
            int r10 = r10.zzb((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzfj<java.lang.Integer>) r13)     // Catch:{ all -> 0x1130 }
            if (r9 < r10) goto L_0x0b96
            com.google.android.gms.measurement.internal.zzag r9 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r10 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbf.zzcb     // Catch:{ all -> 0x1130 }
            boolean r9 = r9.zze(r10, r12)     // Catch:{ all -> 0x1130 }
            if (r9 == 0) goto L_0x0b3d
            com.google.android.gms.measurement.internal.zznp r9 = r41.zzq()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r9.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfn.zzh.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = "_tu"
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = r10.zzb(r9)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r10 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r10 = (com.google.android.gms.internal.measurement.zzjk) r10     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r10 = (com.google.android.gms.internal.measurement.zzfn.zzh) r10     // Catch:{ all -> 0x1130 }
            r8.zza((com.google.android.gms.internal.measurement.zzfn.zzh) r10)     // Catch:{ all -> 0x1130 }
            goto L_0x0b3e
        L_0x0b3d:
            r9 = 0
        L_0x0b3e:
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = com.google.android.gms.internal.measurement.zzfn.zzh.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = "_tr"
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x1130 }
            r12 = 1
            com.google.android.gms.internal.measurement.zzfn$zzh$zza r10 = r10.zza((long) r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r10 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r10 = (com.google.android.gms.internal.measurement.zzjk) r10     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r10 = (com.google.android.gms.internal.measurement.zzfn.zzh) r10     // Catch:{ all -> 0x1130 }
            r8.zza((com.google.android.gms.internal.measurement.zzfn.zzh) r10)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl r10 = r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r12 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzmu r9 = r10.zza((java.lang.String) r12, (com.google.android.gms.internal.measurement.zzfn.zzk.zza) r7, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r8, (java.lang.String) r9)     // Catch:{ all -> 0x1130 }
            if (r9 == 0) goto L_0x0b96
            com.google.android.gms.measurement.internal.zzfw r10 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r10 = r10.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = "Generated trigger URI. appId, uri"
            com.google.android.gms.internal.measurement.zzfn$zzk r13 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r14 = r9.zza     // Catch:{ all -> 0x1130 }
            r10.zza(r12, r13, r14)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r10 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r12 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x1130 }
            r10.zza((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzmu) r9)     // Catch:{ all -> 0x1130 }
            java.util.Set<java.lang.String> r9 = r1.zzr     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r10 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x1130 }
            r9.add(r10)     // Catch:{ all -> 0x1130 }
        L_0x0b96:
            com.google.android.gms.internal.measurement.zzkt r8 = r8.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r8 = (com.google.android.gms.internal.measurement.zzjk) r8     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r8 = (com.google.android.gms.internal.measurement.zzfn.zzf) r8     // Catch:{ all -> 0x1130 }
            r7.zza((int) r6, (com.google.android.gms.internal.measurement.zzfn.zzf) r8)     // Catch:{ all -> 0x1130 }
        L_0x0ba1:
            int r6 = r6 + 1
            goto L_0x0abb
        L_0x0ba5:
            boolean r6 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0bea
            com.google.android.gms.measurement.internal.zzag r6 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzcv     // Catch:{ all -> 0x1130 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ all -> 0x1130 }
            if (r6 == 0) goto L_0x0bea
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r6 = r7.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzu r8 = r41.zzc()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r7.zzt()     // Catch:{ all -> 0x1130 }
            java.util.List r10 = r7.zzaa()     // Catch:{ all -> 0x1130 }
            java.util.List r11 = r7.zzab()     // Catch:{ all -> 0x1130 }
            long r12 = r7.zzf()     // Catch:{ all -> 0x1130 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1130 }
            long r13 = r7.zze()     // Catch:{ all -> 0x1130 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.zzj()     // Catch:{ all -> 0x1130 }
            r14 = 1
            r5 = r5 ^ r14
            r14 = r5
            java.util.List r5 = r8.zza(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x1130 }
            r6.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfn.zzd>) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0c15
        L_0x0bea:
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r5 = r7.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzu r8 = r41.zzc()     // Catch:{ all -> 0x1130 }
            java.lang.String r9 = r7.zzt()     // Catch:{ all -> 0x1130 }
            java.util.List r10 = r7.zzaa()     // Catch:{ all -> 0x1130 }
            java.util.List r11 = r7.zzab()     // Catch:{ all -> 0x1130 }
            long r12 = r7.zzf()     // Catch:{ all -> 0x1130 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1130 }
            long r13 = r7.zze()     // Catch:{ all -> 0x1130 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x1130 }
            java.util.List r6 = r8.zza(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x1130 }
            r5.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfn.zzd>) r6)     // Catch:{ all -> 0x1130 }
        L_0x0c15:
            com.google.android.gms.measurement.internal.zzag r5 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.zzk(r6)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0f66
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x1130 }
            r5.<init>()     // Catch:{ all -> 0x1130 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x1130 }
            r6.<init>()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznp r8 = r41.zzq()     // Catch:{ all -> 0x1130 }
            java.security.SecureRandom r8 = r8.zzv()     // Catch:{ all -> 0x1130 }
            r9 = 0
        L_0x0c38:
            int r10 = r7.zzc()     // Catch:{ all -> 0x1130 }
            if (r9 >= r10) goto L_0x0f2c
            com.google.android.gms.internal.measurement.zzfn$zzf r10 = r7.zza((int) r9)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk$zzb r10 = r10.zzcc()     // Catch:{ all -> 0x1130 }
            r11 = r10
            com.google.android.gms.internal.measurement.zzjk$zzb r11 = (com.google.android.gms.internal.measurement.zzjk.zzb) r11     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10     // Catch:{ all -> 0x1130 }
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = "_ep"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x1130 }
            java.lang.String r12 = "_sr"
            if (r11 == 0) goto L_0x0cdd
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r11 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r11 = (com.google.android.gms.internal.measurement.zzjk) r11     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r11 = (com.google.android.gms.internal.measurement.zzfn.zzf) r11     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = "_en"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zznl.zzb(r11, r13)     // Catch:{ all -> 0x1130 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x1130 }
            java.lang.Object r13 = r5.get(r11)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r13 = (com.google.android.gms.measurement.internal.zzaz) r13     // Catch:{ all -> 0x1130 }
            if (r13 != 0) goto L_0x0c8d
            com.google.android.gms.measurement.internal.zzal r13 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r14 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r13 = r13.zzd(r14, r15)     // Catch:{ all -> 0x1130 }
            if (r13 == 0) goto L_0x0c8d
            r5.put(r11, r13)     // Catch:{ all -> 0x1130 }
        L_0x0c8d:
            if (r13 == 0) goto L_0x0cd0
            java.lang.Long r11 = r13.zzi     // Catch:{ all -> 0x1130 }
            if (r11 != 0) goto L_0x0cd0
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0cab
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1130 }
            long r14 = r11.longValue()     // Catch:{ all -> 0x1130 }
            r18 = 1
            int r11 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r11 <= 0) goto L_0x0cab
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r11)     // Catch:{ all -> 0x1130 }
        L_0x0cab:
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0cc5
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x1130 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0cc5
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r11 = "_efs"
            r12 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10, (java.lang.String) r11, (java.lang.Object) r14)     // Catch:{ all -> 0x1130 }
        L_0x0cc5:
            com.google.android.gms.internal.measurement.zzkt r11 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r11 = (com.google.android.gms.internal.measurement.zzjk) r11     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r11 = (com.google.android.gms.internal.measurement.zzfn.zzf) r11     // Catch:{ all -> 0x1130 }
            r6.add(r11)     // Catch:{ all -> 0x1130 }
        L_0x0cd0:
            r7.zza((int) r9, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10)     // Catch:{ all -> 0x1130 }
        L_0x0cd3:
            r22 = r4
        L_0x0cd5:
            r3 = r5
            r1 = r7
            r44 = r8
            r7 = 1
            goto L_0x0f1e
        L_0x0cdd:
            com.google.android.gms.measurement.internal.zzgt r11 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r13 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x1130 }
            long r13 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x1130 }
            r41.zzq()     // Catch:{ all -> 0x1130 }
            long r2 = r10.zzc()     // Catch:{ all -> 0x1130 }
            long r2 = com.google.android.gms.measurement.internal.zznp.zza((long) r2, (long) r13)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r11 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r11 = (com.google.android.gms.internal.measurement.zzjk) r11     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r11 = (com.google.android.gms.internal.measurement.zzfn.zzf) r11     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = "_dbg"
            r18 = 1
            java.lang.Long r1 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x1130 }
            boolean r18 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x1130 }
            if (r18 != 0) goto L_0x0d66
            if (r1 != 0) goto L_0x0d0f
            goto L_0x0d66
        L_0x0d0f:
            java.util.List r11 = r11.zzh()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x1130 }
        L_0x0d17:
            boolean r18 = r11.hasNext()     // Catch:{ all -> 0x1130 }
            if (r18 == 0) goto L_0x0d66
            java.lang.Object r18 = r11.next()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzh r18 = (com.google.android.gms.internal.measurement.zzfn.zzh) r18     // Catch:{ all -> 0x1130 }
            r44 = r11
            java.lang.String r11 = r18.zzg()     // Catch:{ all -> 0x1130 }
            boolean r11 = r15.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0d63
            boolean r11 = r1 instanceof java.lang.Long     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0d41
            long r24 = r18.zzd()     // Catch:{ all -> 0x1130 }
            java.lang.Long r11 = java.lang.Long.valueOf(r24)     // Catch:{ all -> 0x1130 }
            boolean r11 = r1.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r11 != 0) goto L_0x0d61
        L_0x0d41:
            boolean r11 = r1 instanceof java.lang.String     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0d4f
            java.lang.String r11 = r18.zzh()     // Catch:{ all -> 0x1130 }
            boolean r11 = r1.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r11 != 0) goto L_0x0d61
        L_0x0d4f:
            boolean r11 = r1 instanceof java.lang.Double     // Catch:{ all -> 0x1130 }
            if (r11 == 0) goto L_0x0d66
            double r18 = r18.zza()     // Catch:{ all -> 0x1130 }
            java.lang.Double r11 = java.lang.Double.valueOf(r18)     // Catch:{ all -> 0x1130 }
            boolean r1 = r1.equals(r11)     // Catch:{ all -> 0x1130 }
            if (r1 == 0) goto L_0x0d66
        L_0x0d61:
            r1 = 1
            goto L_0x0d67
        L_0x0d63:
            r11 = r44
            goto L_0x0d17
        L_0x0d66:
            r1 = 0
        L_0x0d67:
            if (r1 != 0) goto L_0x0d7c
            com.google.android.gms.measurement.internal.zzgt r1 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r11 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r11 = r11.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x1130 }
            int r1 = r1.zzb((java.lang.String) r11, (java.lang.String) r15)     // Catch:{ all -> 0x1130 }
            goto L_0x0d7d
        L_0x0d7c:
            r1 = 1
        L_0x0d7d:
            if (r1 > 0) goto L_0x0da4
            com.google.android.gms.measurement.internal.zzfw r2 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r3 = "Sample rate must be positive. event, rate"
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1130 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x1130 }
            r2.zza(r3, r11, r1)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r1 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r1 = (com.google.android.gms.internal.measurement.zzjk) r1     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r1 = (com.google.android.gms.internal.measurement.zzfn.zzf) r1     // Catch:{ all -> 0x1130 }
            r6.add(r1)     // Catch:{ all -> 0x1130 }
            r7.zza((int) r9, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10)     // Catch:{ all -> 0x1130 }
            goto L_0x0cd3
        L_0x0da4:
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x1130 }
            java.lang.Object r11 = r5.get(r11)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r11 = (com.google.android.gms.measurement.internal.zzaz) r11     // Catch:{ all -> 0x1130 }
            if (r11 != 0) goto L_0x0e03
            com.google.android.gms.measurement.internal.zzal r11 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r15 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = r15.zzz()     // Catch:{ all -> 0x1130 }
            r18 = r13
            java.lang.String r13 = r10.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r11 = r11.zzd(r15, r13)     // Catch:{ all -> 0x1130 }
            if (r11 != 0) goto L_0x0e05
            com.google.android.gms.measurement.internal.zzfw r11 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r11 = r11.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r13 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzfn$zzk r14 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x1130 }
            r11.zza(r13, r14, r15)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r11 = new com.google.android.gms.measurement.internal.zzaz     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r13 = r4.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r25 = r13.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.String r26 = r10.zze()     // Catch:{ all -> 0x1130 }
            long r33 = r10.zzc()     // Catch:{ all -> 0x1130 }
            r39 = 0
            r40 = 0
            r27 = 1
            r29 = 1
            r31 = 1
            r35 = 0
            r37 = 0
            r38 = 0
            r24 = r11
            r24.<init>(r25, r26, r27, r29, r31, r33, r35, r37, r38, r39, r40)     // Catch:{ all -> 0x1130 }
            goto L_0x0e05
        L_0x0e03:
            r18 = r13
        L_0x0e05:
            r41.zzp()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r13 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r13 = (com.google.android.gms.internal.measurement.zzjk) r13     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r13 = (com.google.android.gms.internal.measurement.zzfn.zzf) r13     // Catch:{ all -> 0x1130 }
            java.lang.String r14 = "_eid"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zznl.zzb(r13, r14)     // Catch:{ all -> 0x1130 }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ all -> 0x1130 }
            if (r13 == 0) goto L_0x0e1c
            r14 = 1
            goto L_0x0e1d
        L_0x0e1c:
            r14 = 0
        L_0x0e1d:
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r14)     // Catch:{ all -> 0x1130 }
            r22 = r4
            r4 = 1
            if (r1 != r4) goto L_0x0e53
            com.google.android.gms.internal.measurement.zzkt r1 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r1 = (com.google.android.gms.internal.measurement.zzjk) r1     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r1 = (com.google.android.gms.internal.measurement.zzfn.zzf) r1     // Catch:{ all -> 0x1130 }
            r6.add(r1)     // Catch:{ all -> 0x1130 }
            r15.getClass()     // Catch:{ all -> 0x1130 }
            if (r14 == 0) goto L_0x0e4e
            java.lang.Long r1 = r11.zzi     // Catch:{ all -> 0x1130 }
            if (r1 != 0) goto L_0x0e42
            java.lang.Long r1 = r11.zzj     // Catch:{ all -> 0x1130 }
            if (r1 != 0) goto L_0x0e42
            java.lang.Boolean r1 = r11.zzk     // Catch:{ all -> 0x1130 }
            if (r1 == 0) goto L_0x0e4e
        L_0x0e42:
            r1 = 0
            com.google.android.gms.measurement.internal.zzaz r2 = r11.zza(r1, r1, r1)     // Catch:{ all -> 0x1130 }
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1130 }
            r5.put(r1, r2)     // Catch:{ all -> 0x1130 }
        L_0x0e4e:
            r7.zza((int) r9, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10)     // Catch:{ all -> 0x1130 }
            goto L_0x0cd5
        L_0x0e53:
            int r4 = r8.nextInt(r1)     // Catch:{ all -> 0x1130 }
            if (r4 != 0) goto L_0x0e97
            r41.zzp()     // Catch:{ all -> 0x1130 }
            r26 = r7
            r44 = r8
            long r7 = (long) r1     // Catch:{ all -> 0x1130 }
            java.lang.Long r1 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r1)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r1 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r1 = (com.google.android.gms.internal.measurement.zzjk) r1     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r1 = (com.google.android.gms.internal.measurement.zzfn.zzf) r1     // Catch:{ all -> 0x1130 }
            r6.add(r1)     // Catch:{ all -> 0x1130 }
            r15.getClass()     // Catch:{ all -> 0x1130 }
            if (r14 == 0) goto L_0x0e81
            java.lang.Long r1 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x1130 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzaz r11 = r11.zza(r4, r1, r4)     // Catch:{ all -> 0x1130 }
        L_0x0e81:
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1130 }
            long r7 = r10.zzc()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r2 = r11.zza(r7, r2)     // Catch:{ all -> 0x1130 }
            r5.put(r1, r2)     // Catch:{ all -> 0x1130 }
            r3 = r5
            r1 = r26
            r7 = 1
            goto L_0x0f1b
        L_0x0e97:
            r26 = r7
            r44 = r8
            java.lang.Long r4 = r11.zzh     // Catch:{ all -> 0x1130 }
            if (r4 == 0) goto L_0x0ea8
            java.lang.Long r4 = r11.zzh     // Catch:{ all -> 0x1130 }
            long r7 = r4.longValue()     // Catch:{ all -> 0x1130 }
            r23 = r5
            goto L_0x0eb7
        L_0x0ea8:
            r41.zzq()     // Catch:{ all -> 0x1130 }
            long r7 = r10.zzb()     // Catch:{ all -> 0x1130 }
            r23 = r5
            r4 = r18
            long r7 = com.google.android.gms.measurement.internal.zznp.zza((long) r7, (long) r4)     // Catch:{ all -> 0x1130 }
        L_0x0eb7:
            int r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0f04
            r41.zzp()     // Catch:{ all -> 0x1130 }
            java.lang.String r4 = "_efs"
            r7 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x1130 }
            r41.zzp()     // Catch:{ all -> 0x1130 }
            long r4 = (long) r1     // Catch:{ all -> 0x1130 }
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zznl.zza((com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r1)     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r1 = r10.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r1 = (com.google.android.gms.internal.measurement.zzjk) r1     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzf r1 = (com.google.android.gms.internal.measurement.zzfn.zzf) r1     // Catch:{ all -> 0x1130 }
            r6.add(r1)     // Catch:{ all -> 0x1130 }
            r15.getClass()     // Catch:{ all -> 0x1130 }
            if (r14 == 0) goto L_0x0ef2
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x1130 }
            r4 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x1130 }
            r4 = 0
            com.google.android.gms.measurement.internal.zzaz r11 = r11.zza(r4, r1, r5)     // Catch:{ all -> 0x1130 }
        L_0x0ef2:
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1130 }
            long r4 = r10.zzc()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r2 = r11.zza(r4, r2)     // Catch:{ all -> 0x1130 }
            r3 = r23
            r3.put(r1, r2)     // Catch:{ all -> 0x1130 }
            goto L_0x0f19
        L_0x0f04:
            r3 = r23
            r7 = 1
            r15.getClass()     // Catch:{ all -> 0x1130 }
            if (r14 == 0) goto L_0x0f19
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x1130 }
            r2 = 0
            com.google.android.gms.measurement.internal.zzaz r4 = r11.zza(r13, r2, r2)     // Catch:{ all -> 0x1130 }
            r3.put(r1, r4)     // Catch:{ all -> 0x1130 }
        L_0x0f19:
            r1 = r26
        L_0x0f1b:
            r1.zza((int) r9, (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r10)     // Catch:{ all -> 0x1130 }
        L_0x0f1e:
            int r9 = r9 + 1
            r8 = r44
            r7 = r1
            r5 = r3
            r4 = r22
            r2 = 0
            r1 = r41
            goto L_0x0c38
        L_0x0f2c:
            r22 = r4
            r3 = r5
            r1 = r7
            int r2 = r6.size()     // Catch:{ all -> 0x1130 }
            int r4 = r1.zzc()     // Catch:{ all -> 0x1130 }
            if (r2 >= r4) goto L_0x0f41
            com.google.android.gms.internal.measurement.zzfn$zzk$zza r2 = r1.zzl()     // Catch:{ all -> 0x1130 }
            r2.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfn.zzf>) r6)     // Catch:{ all -> 0x1130 }
        L_0x0f41:
            java.util.Set r2 = r3.entrySet()     // Catch:{ all -> 0x1130 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x1130 }
        L_0x0f49:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x1130 }
            if (r3 == 0) goto L_0x0f63
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x1130 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r4 = r41.zzf()     // Catch:{ all -> 0x1130 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzaz r3 = (com.google.android.gms.measurement.internal.zzaz) r3     // Catch:{ all -> 0x1130 }
            r4.zza((com.google.android.gms.measurement.internal.zzaz) r3)     // Catch:{ all -> 0x1130 }
            goto L_0x0f49
        L_0x0f63:
            r2 = r22
            goto L_0x0f68
        L_0x0f66:
            r1 = r7
            r2 = r4
        L_0x0f68:
            com.google.android.gms.internal.measurement.zzfn$zzk r3 = r2.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r3 = r3.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r4 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzg r4 = r4.zze(r3)     // Catch:{ all -> 0x1130 }
            if (r4 != 0) goto L_0x0f91
            com.google.android.gms.measurement.internal.zzfw r4 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r2.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r6)     // Catch:{ all -> 0x1130 }
            r4.zza(r5, r6)     // Catch:{ all -> 0x1130 }
            goto L_0x101d
        L_0x0f91:
            int r5 = r1.zzc()     // Catch:{ all -> 0x1130 }
            if (r5 <= 0) goto L_0x101d
            long r5 = r4.zzs()     // Catch:{ all -> 0x1130 }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0fa5
            r1.zzg((long) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0fa8
        L_0x0fa5:
            r1.zzo()     // Catch:{ all -> 0x1130 }
        L_0x0fa8:
            long r7 = r4.zzu()     // Catch:{ all -> 0x1130 }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0fb3
            goto L_0x0fb4
        L_0x0fb3:
            r5 = r7
        L_0x0fb4:
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x0fbc
            r1.zzh((long) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0fbf
        L_0x0fbc:
            r1.zzp()     // Catch:{ all -> 0x1130 }
        L_0x0fbf:
            boolean r5 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0fef
            com.google.android.gms.measurement.internal.zzag r5 = r41.zze()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbf.zzbs     // Catch:{ all -> 0x1130 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r6)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0fef
            r41.zzq()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r4.zzac()     // Catch:{ all -> 0x1130 }
            boolean r5 = com.google.android.gms.measurement.internal.zznp.zzf(r5)     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x0fef
            int r5 = r1.zzc()     // Catch:{ all -> 0x1130 }
            long r5 = (long) r5     // Catch:{ all -> 0x1130 }
            r4.zza((long) r5)     // Catch:{ all -> 0x1130 }
            long r5 = r4.zzr()     // Catch:{ all -> 0x1130 }
            int r5 = (int) r5     // Catch:{ all -> 0x1130 }
            r1.zzg((int) r5)     // Catch:{ all -> 0x1130 }
            goto L_0x0ff2
        L_0x0fef:
            r4.zzap()     // Catch:{ all -> 0x1130 }
        L_0x0ff2:
            long r5 = r4.zzt()     // Catch:{ all -> 0x1130 }
            int r5 = (int) r5     // Catch:{ all -> 0x1130 }
            r1.zzf((int) r5)     // Catch:{ all -> 0x1130 }
            long r5 = r1.zzf()     // Catch:{ all -> 0x1130 }
            r4.zzr(r5)     // Catch:{ all -> 0x1130 }
            long r5 = r1.zze()     // Catch:{ all -> 0x1130 }
            r4.zzp(r5)     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r4.zzab()     // Catch:{ all -> 0x1130 }
            if (r5 == 0) goto L_0x1012
            r1.zzn(r5)     // Catch:{ all -> 0x1130 }
            goto L_0x1015
        L_0x1012:
            r1.zzm()     // Catch:{ all -> 0x1130 }
        L_0x1015:
            com.google.android.gms.measurement.internal.zzal r5 = r41.zzf()     // Catch:{ all -> 0x1130 }
            r6 = 0
            r5.zza((com.google.android.gms.measurement.internal.zzg) r4, (boolean) r6, (boolean) r6)     // Catch:{ all -> 0x1130 }
        L_0x101d:
            int r4 = r1.zzc()     // Catch:{ all -> 0x1130 }
            if (r4 <= 0) goto L_0x107c
            com.google.android.gms.measurement.internal.zzgt r4 = r41.zzi()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r5 = r2.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfi$zzd r4 = r4.zzc(r5)     // Catch:{ all -> 0x1130 }
            if (r4 == 0) goto L_0x1042
            boolean r5 = r4.zzs()     // Catch:{ all -> 0x1130 }
            if (r5 != 0) goto L_0x103a
            goto L_0x1042
        L_0x103a:
            long r4 = r4.zzc()     // Catch:{ all -> 0x1130 }
            r1.zzb((long) r4)     // Catch:{ all -> 0x1130 }
            goto L_0x106b
        L_0x1042:
            com.google.android.gms.internal.measurement.zzfn$zzk r4 = r2.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r4 = r4.zzaj()     // Catch:{ all -> 0x1130 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x1130 }
            if (r4 == 0) goto L_0x1054
            r4 = -1
            r1.zzb((long) r4)     // Catch:{ all -> 0x1130 }
            goto L_0x106b
        L_0x1054:
            com.google.android.gms.measurement.internal.zzfw r4 = r41.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzu()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzfn$zzk r6 = r2.zza     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x1130 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r6)     // Catch:{ all -> 0x1130 }
            r4.zza(r5, r6)     // Catch:{ all -> 0x1130 }
        L_0x106b:
            com.google.android.gms.measurement.internal.zzal r4 = r41.zzf()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzkt r1 = r1.zzai()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzjk r1 = (com.google.android.gms.internal.measurement.zzjk) r1     // Catch:{ all -> 0x1130 }
            com.google.android.gms.internal.measurement.zzfn$zzk r1 = (com.google.android.gms.internal.measurement.zzfn.zzk) r1     // Catch:{ all -> 0x1130 }
            r13 = r17
            r4.zza((com.google.android.gms.internal.measurement.zzfn.zzk) r1, (boolean) r13)     // Catch:{ all -> 0x1130 }
        L_0x107c:
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()     // Catch:{ all -> 0x1130 }
            java.util.List<java.lang.Long> r2 = r2.zzb     // Catch:{ all -> 0x1130 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x1130 }
            r1.zzt()     // Catch:{ all -> 0x1130 }
            r1.zzal()     // Catch:{ all -> 0x1130 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "rowid in ("
            r4.<init>(r5)     // Catch:{ all -> 0x1130 }
            r13 = 0
        L_0x1093:
            int r5 = r2.size()     // Catch:{ all -> 0x1130 }
            if (r13 >= r5) goto L_0x10b0
            if (r13 == 0) goto L_0x10a0
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ all -> 0x1130 }
        L_0x10a0:
            java.lang.Object r5 = r2.get(r13)     // Catch:{ all -> 0x1130 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x1130 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x1130 }
            r4.append(r5)     // Catch:{ all -> 0x1130 }
            int r13 = r13 + 1
            goto L_0x1093
        L_0x10b0:
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch:{ all -> 0x1130 }
            android.database.sqlite.SQLiteDatabase r5 = r1.e_()     // Catch:{ all -> 0x1130 }
            java.lang.String r6 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x1130 }
            r7 = 0
            int r4 = r5.delete(r6, r4, r7)     // Catch:{ all -> 0x1130 }
            int r5 = r2.size()     // Catch:{ all -> 0x1130 }
            if (r4 == r5) goto L_0x10e3
            com.google.android.gms.measurement.internal.zzfw r1 = r1.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r5 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x1130 }
            int r2 = r2.size()     // Catch:{ all -> 0x1130 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x1130 }
            r1.zza(r5, r4, r2)     // Catch:{ all -> 0x1130 }
        L_0x10e3:
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()     // Catch:{ all -> 0x1130 }
            android.database.sqlite.SQLiteDatabase r2 = r1.e_()     // Catch:{ all -> 0x1130 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            java.lang.String[] r5 = new java.lang.String[]{r3, r3}     // Catch:{ SQLiteException -> 0x10f5 }
            r2.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x10f5 }
            goto L_0x1108
        L_0x10f5:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzfw r1 = r1.zzj()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch:{ all -> 0x1130 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r3)     // Catch:{ all -> 0x1130 }
            r1.zza(r4, r3, r2)     // Catch:{ all -> 0x1130 }
        L_0x1108:
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()     // Catch:{ all -> 0x1130 }
            r1.zzw()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()
            r1.zzu()
            r1 = 1
            return r1
        L_0x1118:
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()     // Catch:{ all -> 0x1130 }
            r1.zzw()     // Catch:{ all -> 0x1130 }
            com.google.android.gms.measurement.internal.zzal r1 = r41.zzf()
            r1.zzu()
            r1 = 0
            return r1
        L_0x1128:
            r0 = move-exception
            r1 = r0
        L_0x112a:
            if (r5 == 0) goto L_0x112f
            r5.close()     // Catch:{ all -> 0x1130 }
        L_0x112f:
            throw r1     // Catch:{ all -> 0x1130 }
        L_0x1130:
            r0 = move-exception
            r1 = r0
            com.google.android.gms.measurement.internal.zzal r2 = r41.zzf()
            r2.zzu()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznc.zza(java.lang.String, long):boolean");
    }

    private final boolean zzac() {
        zzl().zzt();
        zzs();
        return zzf().zzx() || !TextUtils.isEmpty(zzf().f_());
    }

    private final boolean zzad() {
        zzl().zzt();
        FileLock fileLock = this.zzx;
        if (fileLock == null || !fileLock.isValid()) {
            try {
                FileChannel channel = new RandomAccessFile(new File(zzcf.zza().zza(this.zzm.zza().getFilesDir(), "google_app_measurement.db")), "rw").getChannel();
                this.zzy = channel;
                FileLock tryLock = channel.tryLock();
                this.zzx = tryLock;
                if (tryLock != null) {
                    zzj().zzp().zza("Storage concurrent access okay");
                    return true;
                }
                zzj().zzg().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                zzj().zzg().zza("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                zzj().zzg().zza("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                zzj().zzu().zza("Storage lock already acquired", e3);
                return false;
            }
        } else {
            zzj().zzp().zza("Storage concurrent access okay");
            return true;
        }
    }

    private final boolean zza(zzfn.zzf.zza zza2, zzfn.zzf.zza zza3) {
        String str;
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzfn.zzh zza4 = zznl.zza((zzfn.zzf) ((zzjk) zza2.zzai()), "_sc");
        String str2 = null;
        if (zza4 == null) {
            str = null;
        } else {
            str = zza4.zzh();
        }
        zzp();
        zzfn.zzh zza5 = zznl.zza((zzfn.zzf) ((zzjk) zza3.zzai()), "_pc");
        if (zza5 != null) {
            str2 = zza5.zzh();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzfn.zzh zza6 = zznl.zza((zzfn.zzf) ((zzjk) zza2.zzai()), "_et");
        if (zza6 == null || !zza6.zzl() || zza6.zzd() <= 0) {
            return true;
        }
        long zzd2 = zza6.zzd();
        zzp();
        zzfn.zzh zza7 = zznl.zza((zzfn.zzf) ((zzjk) zza3.zzai()), "_et");
        if (zza7 != null && zza7.zzd() > 0) {
            zzd2 += zza7.zzd();
        }
        zzp();
        zznl.zza(zza3, "_et", (Object) Long.valueOf(zzd2));
        zzp();
        zznl.zza(zza2, "_fr", (Object) 1L);
        return true;
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzj().zzg().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            zzj().zzg().zza("Failed to write to channel", e);
            return false;
        }
    }
}

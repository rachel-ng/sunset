package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjk;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzfn {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zza extends zzjk<zza, C0012zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzlc<zza> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private long zzm;

        /* renamed from: com.google.android.gms.internal.measurement.zzfn$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class C0012zza extends zzjk.zzb<zza, C0012zza> implements zzkv {
            public final long zza() {
                return ((zza) this.zza).zza();
            }

            public final long zzb() {
                return ((zza) this.zza).zzb();
            }

            public final C0012zza zzc() {
                zzak();
                ((zza) this.zza).zzl();
                return this;
            }

            public final C0012zza zzd() {
                zzak();
                ((zza) this.zza).zzm();
                return this;
            }

            public final C0012zza zze() {
                zzak();
                ((zza) this.zza).zzn();
                return this;
            }

            public final C0012zza zzf() {
                zzak();
                ((zza) this.zza).zzo();
                return this;
            }

            public final C0012zza zzg() {
                zzak();
                ((zza) this.zza).zzp();
                return this;
            }

            public final C0012zza zzh() {
                zzak();
                ((zza) this.zza).zzq();
                return this;
            }

            public final C0012zza zza(String str) {
                zzak();
                ((zza) this.zza).zza(str);
                return this;
            }

            public final C0012zza zzb(String str) {
                zzak();
                ((zza) this.zza).zzb(str);
                return this;
            }

            public final C0012zza zzc(String str) {
                zzak();
                ((zza) this.zza).zzc(str);
                return this;
            }

            public final C0012zza zza(long j) {
                zzak();
                ((zza) this.zza).zza(j);
                return this;
            }

            public final C0012zza zzb(long j) {
                zzak();
                ((zza) this.zza).zzb(j);
                return this;
            }

            public final C0012zza zzd(String str) {
                zzak();
                ((zza) this.zza).zzd(str);
                return this;
            }

            public final C0012zza zze(String str) {
                zzak();
                ((zza) this.zza).zze(str);
                return this;
            }

            public final C0012zza zzf(String str) {
                zzak();
                ((zza) this.zza).zzf(str);
                return this;
            }

            private C0012zza() {
                super(zza.zzc);
            }

            /* synthetic */ C0012zza(zzfp zzfp) {
                this();
            }
        }

        public final long zza() {
            return this.zzi;
        }

        public final long zzb() {
            return this.zzm;
        }

        public static C0012zza zzc() {
            return (C0012zza) zzc.zzcb();
        }

        public static zza zze() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0012zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zza> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zza.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzf() {
            return this.zzh;
        }

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzf;
        }

        public final String zzi() {
            return this.zzl;
        }

        public final String zzj() {
            return this.zzk;
        }

        public final String zzk() {
            return this.zzj;
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjk.zza(zza.class, zza);
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void zzl() {
            this.zze &= -5;
            this.zzh = zzc.zzh;
        }

        /* access modifiers changed from: private */
        public final void zzm() {
            this.zze &= -3;
            this.zzg = zzc.zzg;
        }

        /* access modifiers changed from: private */
        public final void zzn() {
            this.zze &= -2;
            this.zzf = zzc.zzf;
        }

        /* access modifiers changed from: private */
        public final void zzo() {
            this.zze &= -65;
            this.zzl = zzc.zzl;
        }

        /* access modifiers changed from: private */
        public final void zzp() {
            this.zze &= -33;
            this.zzk = zzc.zzk;
        }

        /* access modifiers changed from: private */
        public final void zzq() {
            this.zze &= -17;
            this.zzj = zzc.zzj;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 4;
            this.zzh = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzg = str;
        }

        /* access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zze |= 1;
            this.zzf = str;
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 8;
            this.zzi = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(long j) {
            this.zze |= 128;
            this.zzm = j;
        }

        /* access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zze |= 64;
            this.zzl = str;
        }

        /* access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zze |= 32;
            this.zzk = str;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zze |= 16;
            this.zzj = str;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzb extends zzjk<zzb, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzlc<zzb> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzb, zza> implements zzkv {
            private zza() {
                super(zzb.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzb> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzb.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzjk.zza(zzb.class, zzb);
        }

        private zzb() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzd extends zzjk<zzd, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzlc<zzd> zzd;
        private int zze;
        private int zzf;
        private zzm zzg;
        private zzm zzh;
        private boolean zzi;

        public final int zza() {
            return this.zzf;
        }

        public static zza zzb() {
            return (zza) zzc.zzcb();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzd, zza> implements zzkv {
            public final zza zza(int i) {
                zzak();
                ((zzd) this.zza).zza(i);
                return this;
            }

            public final zza zza(zzm.zza zza) {
                zzak();
                ((zzd) this.zza).zza((zzm) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(boolean z) {
                zzak();
                ((zzd) this.zza).zza(z);
                return this;
            }

            public final zza zza(zzm zzm) {
                zzak();
                ((zzd) this.zza).zzb(zzm);
                return this;
            }

            private zza() {
                super(zzd.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public final zzm zzd() {
            zzm zzm = this.zzg;
            return zzm == null ? zzm.zzg() : zzm;
        }

        public final zzm zze() {
            zzm zzm = this.zzh;
            return zzm == null ? zzm.zzg() : zzm;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzd> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzd.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjk.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void zza(int i) {
            this.zze |= 1;
            this.zzf = i;
        }

        /* access modifiers changed from: private */
        public final void zza(zzm zzm) {
            zzm.getClass();
            this.zzg = zzm;
            this.zze |= 2;
        }

        /* access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zze |= 8;
            this.zzi = z;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzm zzm) {
            zzm.getClass();
            this.zzh = zzm;
            this.zze |= 4;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zze extends zzjk<zze, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzlc<zze> zzd;
        private int zze;
        private int zzf;
        private long zzg;

        public final int zza() {
            return this.zzf;
        }

        public final long zzb() {
            return this.zzg;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zze, zza> implements zzkv {
            public final zza zza(long j) {
                zzak();
                ((zze) this.zza).zza(j);
                return this;
            }

            public final zza zza(int i) {
                zzak();
                ((zze) this.zza).zza(i);
                return this;
            }

            private zza() {
                super(zze.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public static zza zzc() {
            return (zza) zzc.zzcb();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zze> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zze.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzjk.zza(zze.class, zze2);
        }

        private zze() {
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 2;
            this.zzg = j;
        }

        /* access modifiers changed from: private */
        public final void zza(int i) {
            this.zze |= 1;
            this.zzf = i;
        }

        public final boolean zze() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzf extends zzjk<zzf, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzlc<zzf> zzd;
        private int zze;
        private zzjt<zzh> zzf = zzcg();
        private String zzg = "";
        private long zzh;
        private long zzi;
        private int zzj;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzf, zza> implements zzkv {
            public final int zza() {
                return ((zzf) this.zza).zzb();
            }

            public final long zzb() {
                return ((zzf) this.zza).zzc();
            }

            public final long zzc() {
                return ((zzf) this.zza).zzd();
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                ((zzf) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(zzh.zza zza) {
                zzak();
                ((zzf) this.zza).zza((zzh) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(zzh zzh) {
                zzak();
                ((zzf) this.zza).zza(zzh);
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzf) this.zza).zzl();
                return this;
            }

            public final zza zza(int i) {
                zzak();
                ((zzf) this.zza).zzb(i);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                ((zzf) this.zza).zza(str);
                return this;
            }

            public final zza zza(int i, zzh.zza zza) {
                zzak();
                ((zzf) this.zza).zza(i, (zzh) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(int i, zzh zzh) {
                zzak();
                ((zzf) this.zza).zza(i, zzh);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                ((zzf) this.zza).zza(j);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                ((zzf) this.zza).zzb(j);
                return this;
            }

            public final zzh zzb(int i) {
                return ((zzf) this.zza).zza(i);
            }

            public final String zze() {
                return ((zzf) this.zza).zzg();
            }

            public final List<zzh> zzf() {
                return Collections.unmodifiableList(((zzf) this.zza).zzh());
            }

            private zza() {
                super(zzf.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }

            public final boolean zzg() {
                return ((zzf) this.zza).zzk();
            }
        }

        public final int zza() {
            return this.zzj;
        }

        public final int zzb() {
            return this.zzf.size();
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzh;
        }

        public static zza zze() {
            return (zza) zzc.zzcb();
        }

        public final zzh zza(int i) {
            return (zzh) this.zzf.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzh.class, "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzf> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzf.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzg() {
            return this.zzg;
        }

        public final List<zzh> zzh() {
            return this.zzf;
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjk.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzh> iterable) {
            zzm();
            zzhq.zza(iterable, this.zzf);
        }

        /* access modifiers changed from: private */
        public final void zza(zzh zzh2) {
            zzh2.getClass();
            zzm();
            this.zzf.add(zzh2);
        }

        /* access modifiers changed from: private */
        public final void zzl() {
            this.zzf = zzcg();
        }

        private final void zzm() {
            zzjt<zzh> zzjt = this.zzf;
            if (!zzjt.zzc()) {
                this.zzf = zzjk.zza(zzjt);
            }
        }

        /* access modifiers changed from: private */
        public final void zzb(int i) {
            zzm();
            this.zzf.remove(i);
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 1;
            this.zzg = str;
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzh zzh2) {
            zzh2.getClass();
            zzm();
            this.zzf.set(i, zzh2);
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 4;
            this.zzi = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(long j) {
            this.zze |= 2;
            this.zzh = j;
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzh extends zzjk<zzh, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzh zzc;
        private static volatile zzlc<zzh> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private long zzh;
        private float zzi;
        private double zzj;
        private zzjt<zzh> zzk = zzcg();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzh, zza> implements zzkv {
            public final int zza() {
                return ((zzh) this.zza).zzc();
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                ((zzh) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                ((zzh) this.zza).zze((zzh) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzh) this.zza).zzo();
                return this;
            }

            public final zza zzc() {
                zzak();
                ((zzh) this.zza).zzp();
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzh) this.zza).zzq();
                return this;
            }

            public final zza zze() {
                zzak();
                ((zzh) this.zza).zzr();
                return this;
            }

            public final zza zza(double d) {
                zzak();
                ((zzh) this.zza).zza(d);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                ((zzh) this.zza).zza(j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                ((zzh) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                ((zzh) this.zza).zzb(str);
                return this;
            }

            public final String zzf() {
                return ((zzh) this.zza).zzg();
            }

            public final String zzg() {
                return ((zzh) this.zza).zzh();
            }

            private zza() {
                super(zzh.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public final double zza() {
            return this.zzj;
        }

        public final float zzb() {
            return this.zzi;
        }

        public final int zzc() {
            return this.zzk.size();
        }

        public final long zzd() {
            return this.zzh;
        }

        public static zza zze() {
            return (zza) zzc.zzcb();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzh.class});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzh> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzh.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzg() {
            return this.zzf;
        }

        public final String zzh() {
            return this.zzg;
        }

        public final List<zzh> zzi() {
            return this.zzk;
        }

        static {
            zzh zzh2 = new zzh();
            zzc = zzh2;
            zzjk.zza(zzh.class, zzh2);
        }

        private zzh() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzh> iterable) {
            zzs();
            zzhq.zza(iterable, this.zzk);
        }

        /* access modifiers changed from: private */
        public final void zze(zzh zzh2) {
            zzh2.getClass();
            zzs();
            this.zzk.add(zzh2);
        }

        /* access modifiers changed from: private */
        public final void zzo() {
            this.zze &= -17;
            this.zzj = 0.0d;
        }

        /* access modifiers changed from: private */
        public final void zzp() {
            this.zze &= -5;
            this.zzh = 0;
        }

        /* access modifiers changed from: private */
        public final void zzq() {
            this.zzk = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zzr() {
            this.zze &= -3;
            this.zzg = zzc.zzg;
        }

        private final void zzs() {
            zzjt<zzh> zzjt = this.zzk;
            if (!zzjt.zzc()) {
                this.zzk = zzjk.zza(zzjt);
            }
        }

        /* access modifiers changed from: private */
        public final void zza(double d) {
            this.zze |= 16;
            this.zzj = d;
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 4;
            this.zzh = j;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 1;
            this.zzf = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzg = str;
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzn() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzi extends zzjk<zzi, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzi zzc;
        private static volatile zzlc<zzi> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private zzb zzh;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzi, zza> implements zzkv {
            private zza() {
                super(zzi.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzi> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzi.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzi zzi = new zzi();
            zzc = zzi;
            zzjk.zza(zzi.class, zzi);
        }

        private zzi() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzj extends zzjk<zzj, zzb> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzj zzc;
        private static volatile zzlc<zzj> zzd;
        private int zze;
        private zzjt<zzk> zzf = zzcg();
        private String zzg = "";
        private String zzh = "";
        private int zzi;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzb extends zzjk.zzb<zzj, zzb> implements zzkv {
            public final int zza() {
                return ((zzj) this.zza).zza();
            }

            public final zzb zza(zzk.zza zza) {
                zzak();
                ((zzj) this.zza).zza((zzk) ((zzjk) zza.zzai()));
                return this;
            }

            public final zzb zza(String str) {
                zzak();
                ((zzj) this.zza).zza(str);
                return this;
            }

            public final zzk zza(int i) {
                return ((zzj) this.zza).zza(0);
            }

            private zzb() {
                super(zzj.zzc);
            }

            /* synthetic */ zzb(zzfp zzfp) {
                this();
            }
        }

        public final int zza() {
            return this.zzf.size();
        }

        public static zzb zzb() {
            return (zzb) zzc.zzcb();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zza implements zzjp {
            SDK(0),
            SGTM(1);
            
            private final int zzd;

            public final int zza() {
                return this.zzd;
            }

            public static zza zza(int i) {
                if (i == 0) {
                    return SDK;
                }
                if (i != 1) {
                    return null;
                }
                return SGTM;
            }

            public static zzjo zzb() {
                return zzfr.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.zzd = i;
            }
        }

        public final zzk zza(int i) {
            return (zzk) this.zzf.get(0);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zzb((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\t\u0004\u0000\u0001\u0000\u0001\u001b\u0007ဈ\u0000\bဈ\u0001\t᠌\u0002", new Object[]{"zze", "zzf", zzk.class, "zzg", "zzh", "zzi", zza.zzb()});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzj> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzj.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzd() {
            return this.zzh;
        }

        public final List<zzk> zze() {
            return this.zzf;
        }

        static {
            zzj zzj = new zzj();
            zzc = zzj;
            zzjk.zza(zzj.class, zzj);
        }

        private zzj() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzk zzk) {
            zzk.getClass();
            zzjt<zzk> zzjt = this.zzf;
            if (!zzjt.zzc()) {
                this.zzf = zzjk.zza(zzjt);
            }
            this.zzf.add(zzk);
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzh = str;
        }

        public final boolean zzf() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzk extends zzjk<zzk, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzk zzc;
        private static volatile zzlc<zzk> zzd;
        private String zzaa = "";
        private long zzab;
        private int zzac;
        private String zzad = "";
        private String zzae = "";
        private boolean zzaf;
        private zzjt<zzd> zzag = zzcg();
        private String zzah = "";
        private int zzai;
        private int zzaj;
        private int zzak;
        private String zzal = "";
        private long zzam;
        private long zzan;
        private String zzao = "";
        private String zzap = "";
        private int zzaq;
        private String zzar = "";
        private zzl zzas;
        private zzjr zzat = zzce();
        private long zzau;
        private long zzav;
        private String zzaw = "";
        private String zzax = "";
        private int zzay;
        private boolean zzaz;
        private String zzba = "";
        private boolean zzbb;
        private zzi zzbc;
        private String zzbd = "";
        private zzjt<String> zzbe = zzjk.zzcg();
        private String zzbf = "";
        private long zzbg;
        private boolean zzbh;
        private String zzbi = "";
        private boolean zzbj;
        private String zzbk = "";
        private int zzbl;
        private String zzbm = "";
        private zzc zzbn;
        private int zzbo;
        private zza zzbp;
        private int zze;
        private int zzf;
        private int zzg;
        private zzjt<zzf> zzh = zzcg();
        private zzjt<zzo> zzi = zzcg();
        private long zzj;
        private long zzk;
        private long zzl;
        private long zzm;
        private long zzn;
        private String zzo = "";
        private String zzp = "";
        private String zzq = "";
        private String zzr = "";
        private int zzs;
        private String zzt = "";
        private String zzu = "";
        private String zzv = "";
        private long zzw;
        private long zzx;
        private String zzy = "";
        private boolean zzz;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzk, zza> implements zzkv {
            public final int zza() {
                return ((zzk) this.zza).zza();
            }

            public final int zzb() {
                return ((zzk) this.zza).zzb();
            }

            public final int zzc() {
                return ((zzk) this.zza).zze();
            }

            public final int zzd() {
                return ((zzk) this.zza).zzi();
            }

            public final long zze() {
                return ((zzk) this.zza).zzm();
            }

            public final long zzf() {
                return ((zzk) this.zza).zzq();
            }

            public final zza zzg() {
                return ((zzk) this.zza).zzu();
            }

            public final zzf zza(int i) {
                return ((zzk) this.zza).zza(i);
            }

            public final zza zza(Iterable<? extends zzd> iterable) {
                zzak();
                ((zzk) this.zza).zza(iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends zzf> iterable) {
                zzak();
                ((zzk) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends Integer> iterable) {
                zzak();
                ((zzk) this.zza).zzc(iterable);
                return this;
            }

            public final zza zzd(Iterable<String> iterable) {
                zzak();
                ((zzk) this.zza).zzd(iterable);
                return this;
            }

            public final zza zze(Iterable<? extends zzo> iterable) {
                zzak();
                ((zzk) this.zza).zze(iterable);
                return this;
            }

            public final zza zza(zzf.zza zza) {
                zzak();
                ((zzk) this.zza).zza((zzf) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(zzo.zza zza) {
                zzak();
                ((zzk) this.zza).zza((zzo) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(zzo zzo) {
                zzak();
                ((zzk) this.zza).zza(zzo);
                return this;
            }

            public final zza zzh() {
                zzak();
                ((zzk) this.zza).zzco();
                return this;
            }

            public final zza zzi() {
                zzak();
                ((zzk) this.zza).zzcp();
                return this;
            }

            public final zza zzj() {
                zzak();
                ((zzk) this.zza).zzcq();
                return this;
            }

            public final zza zzk() {
                zzak();
                ((zzk) this.zza).zzcr();
                return this;
            }

            public final zza zzl() {
                zzak();
                ((zzk) this.zza).zzcs();
                return this;
            }

            public final zza zzm() {
                zzak();
                ((zzk) this.zza).zzct();
                return this;
            }

            public final zza zzn() {
                zzak();
                ((zzk) this.zza).zzcu();
                return this;
            }

            public final zza zzo() {
                zzak();
                ((zzk) this.zza).zzcv();
                return this;
            }

            public final zza zzp() {
                zzak();
                ((zzk) this.zza).zzcw();
                return this;
            }

            public final zza zzq() {
                zzak();
                ((zzk) this.zza).zzcx();
                return this;
            }

            public final zza zzr() {
                zzak();
                ((zzk) this.zza).zzcy();
                return this;
            }

            public final zza zzs() {
                zzak();
                ((zzk) this.zza).zzcz();
                return this;
            }

            public final zza zzb(int i) {
                zzak();
                ((zzk) this.zza).zzd(i);
                return this;
            }

            public final zza zzc(int i) {
                zzak();
                ((zzk) this.zza).zze(i);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                ((zzk) this.zza).zza(zza);
                return this;
            }

            public final zza zzd(int i) {
                zzak();
                ((zzk) this.zza).zzf(i);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                ((zzk) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                ((zzk) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzak();
                ((zzk) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzak();
                ((zzk) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                zzak();
                ((zzk) this.zza).zze(str);
                return this;
            }

            public final zza zze(int i) {
                zzak();
                ((zzk) this.zza).zzg(i);
                return this;
            }

            public final zza zza(zzc zzc) {
                zzak();
                ((zzk) this.zza).zza(zzc);
                return this;
            }

            public final zza zzf(int i) {
                zzak();
                ((zzk) this.zza).zzh(i);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                ((zzk) this.zza).zza(j);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                ((zzk) this.zza).zzb(j);
                return this;
            }

            public final zza zzf(String str) {
                zzak();
                ((zzk) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(String str) {
                zzak();
                ((zzk) this.zza).zzg(str);
                return this;
            }

            public final zza zzh(String str) {
                zzak();
                ((zzk) this.zza).zzh(str);
                return this;
            }

            public final zza zzg(int i) {
                zzak();
                ((zzk) this.zza).zzi(i);
                return this;
            }

            public final zza zzc(long j) {
                zzak();
                ((zzk) this.zza).zzc(j);
                return this;
            }

            public final zza zzi(String str) {
                zzak();
                ((zzk) this.zza).zzi(str);
                return this;
            }

            public final zza zzj(String str) {
                zzak();
                ((zzk) this.zza).zzj((String) null);
                return this;
            }

            public final zza zzd(long j) {
                zzak();
                ((zzk) this.zza).zzd(j);
                return this;
            }

            public final zza zza(boolean z) {
                zzak();
                ((zzk) this.zza).zza(z);
                return this;
            }

            public final zza zze(long j) {
                zzak();
                ((zzk) this.zza).zze(j);
                return this;
            }

            public final zza zzk(String str) {
                zzak();
                ((zzk) this.zza).zzk(str);
                return this;
            }

            public final zza zza(int i, zzf.zza zza) {
                zzak();
                ((zzk) this.zza).zza(i, (zzf) ((zzjk) zza.zzai()));
                return this;
            }

            public final zza zza(int i, zzf zzf) {
                zzak();
                ((zzk) this.zza).zza(i, zzf);
                return this;
            }

            public final zza zzl(String str) {
                zzak();
                ((zzk) this.zza).zzl(str);
                return this;
            }

            public final zza zzm(String str) {
                zzak();
                ((zzk) this.zza).zzm(str);
                return this;
            }

            public final zza zzf(long j) {
                zzak();
                ((zzk) this.zza).zzf(j);
                return this;
            }

            public final zza zzn(String str) {
                zzak();
                ((zzk) this.zza).zzn(str);
                return this;
            }

            public final zza zzb(boolean z) {
                zzak();
                ((zzk) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(boolean z) {
                zzak();
                ((zzk) this.zza).zzc(z);
                return this;
            }

            public final zza zzo(String str) {
                zzak();
                ((zzk) this.zza).zzo(str);
                return this;
            }

            public final zza zzp(String str) {
                zzak();
                ((zzk) this.zza).zzp(str);
                return this;
            }

            public final zza zza(zzl.zzb zzb) {
                zzak();
                ((zzk) this.zza).zza((zzl) ((zzjk) zzb.zzai()));
                return this;
            }

            public final zza zzg(long j) {
                zzak();
                ((zzk) this.zza).zzg(j);
                return this;
            }

            public final zza zzh(long j) {
                zzak();
                ((zzk) this.zza).zzh(j);
                return this;
            }

            public final zza zzh(int i) {
                zzak();
                ((zzk) this.zza).zzj(1);
                return this;
            }

            public final zza zzq(String str) {
                zzak();
                ((zzk) this.zza).zzq(str);
                return this;
            }

            public final zza zzi(int i) {
                zzak();
                ((zzk) this.zza).zzk(i);
                return this;
            }

            public final zza zzd(boolean z) {
                zzak();
                ((zzk) this.zza).zzd(z);
                return this;
            }

            public final zza zzr(String str) {
                zzak();
                ((zzk) this.zza).zzr(str);
                return this;
            }

            public final zza zzi(long j) {
                zzak();
                ((zzk) this.zza).zzi(j);
                return this;
            }

            public final zza zzj(long j) {
                zzak();
                ((zzk) this.zza).zzj(j);
                return this;
            }

            public final zza zzj(int i) {
                zzak();
                ((zzk) this.zza).zzl(i);
                return this;
            }

            public final zza zzk(long j) {
                zzak();
                ((zzk) this.zza).zzk(j);
                return this;
            }

            public final zza zzl(long j) {
                zzak();
                ((zzk) this.zza).zzl(j);
                return this;
            }

            public final zza zza(int i, zzo zzo) {
                zzak();
                ((zzk) this.zza).zza(i, zzo);
                return this;
            }

            public final zza zzs(String str) {
                zzak();
                ((zzk) this.zza).zzs(str);
                return this;
            }

            public final zzo zzk(int i) {
                return ((zzk) this.zza).zzb(i);
            }

            public final String zzt() {
                return ((zzk) this.zza).zzz();
            }

            public final String zzu() {
                return ((zzk) this.zza).zzaa();
            }

            public final String zzv() {
                return ((zzk) this.zza).zzad();
            }

            public final String zzw() {
                return ((zzk) this.zza).zzaf();
            }

            public final String zzx() {
                return ((zzk) this.zza).zzaj();
            }

            public final String zzy() {
                return ((zzk) this.zza).zzal();
            }

            public final String zzz() {
                return ((zzk) this.zza).zzan();
            }

            public final List<zzf> zzaa() {
                return Collections.unmodifiableList(((zzk) this.zza).zzar());
            }

            public final List<zzo> zzab() {
                return Collections.unmodifiableList(((zzk) this.zza).zzas());
            }

            private zza() {
                super(zzk.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }

            public final boolean zzac() {
                return ((zzk) this.zza).zzau();
            }

            public final boolean zzad() {
                return ((zzk) this.zza).zzav();
            }

            public final boolean zzae() {
                return ((zzk) this.zza).zzax();
            }
        }

        public final int zza() {
            return this.zzbl;
        }

        public final int zzb() {
            return this.zzai;
        }

        public final int zzc() {
            return this.zzac;
        }

        public final int zzd() {
            return this.zzbo;
        }

        public final int zze() {
            return this.zzh.size();
        }

        public final int zzf() {
            return this.zzg;
        }

        public final int zzg() {
            return this.zzaq;
        }

        public final int zzh() {
            return this.zzs;
        }

        public final int zzi() {
            return this.zzi.size();
        }

        public final long zzj() {
            return this.zzam;
        }

        public final long zzk() {
            return this.zzab;
        }

        public final long zzl() {
            return this.zzau;
        }

        public final long zzm() {
            return this.zzl;
        }

        public final long zzn() {
            return this.zzw;
        }

        public final long zzo() {
            return this.zzn;
        }

        public final long zzp() {
            return this.zzm;
        }

        public final long zzq() {
            return this.zzk;
        }

        public final long zzr() {
            return this.zzbg;
        }

        public final long zzs() {
            return this.zzj;
        }

        public final long zzt() {
            return this.zzx;
        }

        public final zza zzu() {
            zza zza2 = this.zzbp;
            return zza2 == null ? zza.zze() : zza2;
        }

        public final zzc zzv() {
            zzc zzc2 = this.zzbn;
            return zzc2 == null ? zzc.zzc() : zzc2;
        }

        public final zzf zza(int i) {
            return (zzf) this.zzh.get(i);
        }

        public static zza zzw() {
            return (zza) zzc.zzcb();
        }

        public final zzo zzb(int i) {
            return (zzo) this.zzi.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001>\u0000\u0002\u0001O>\u0000\u0005\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5᠌(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.Cဂ/Dဇ0Gဈ1Hဇ2Iဈ3Jင4Kဈ5Lဉ6Mင7Oဉ8", new Object[]{"zze", "zzf", "zzg", "zzh", zzf.class, "zzi", zzo.class, "zzj", "zzk", "zzl", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzm", "zzaf", "zzag", zzd.class, "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", "zzav", "zzaw", "zzax", "zzay", zzfo.zzb(), "zzaz", "zzba", "zzbb", "zzbc", "zzbd", "zzbe", "zzbf", "zzbg", "zzbh", "zzbi", "zzbj", "zzbk", "zzbl", "zzbm", "zzbn", "zzbo", "zzbp"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzk> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzk.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzy() {
            return this.zzar;
        }

        public final String zzz() {
            return this.zzu;
        }

        public final String zzaa() {
            return this.zzaa;
        }

        public final String zzab() {
            return this.zzt;
        }

        public final String zzac() {
            return this.zzv;
        }

        public final String zzad() {
            return this.zzbi;
        }

        public final String zzae() {
            return this.zzax;
        }

        public final String zzaf() {
            return this.zzbk;
        }

        public final String zzag() {
            return this.zzq;
        }

        public final String zzah() {
            return this.zzao;
        }

        public final String zzai() {
            return this.zzah;
        }

        public final String zzaj() {
            return this.zzae;
        }

        public final String zzak() {
            return this.zzad;
        }

        public final String zzal() {
            return this.zzp;
        }

        public final String zzam() {
            return this.zzo;
        }

        public final String zzan() {
            return this.zzy;
        }

        public final String zzao() {
            return this.zzbd;
        }

        public final String zzap() {
            return this.zzr;
        }

        public final List<zzd> zzaq() {
            return this.zzag;
        }

        public final List<zzf> zzar() {
            return this.zzh;
        }

        public final List<zzo> zzas() {
            return this.zzi;
        }

        static {
            zzk zzk2 = new zzk();
            zzc = zzk2;
            zzjk.zza(zzk.class, zzk2);
        }

        private zzk() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zzd> iterable) {
            zzjt<zzd> zzjt = this.zzag;
            if (!zzjt.zzc()) {
                this.zzag = zzjk.zza(zzjt);
            }
            zzhq.zza(iterable, this.zzag);
        }

        /* access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzf> iterable) {
            zzda();
            zzhq.zza(iterable, this.zzh);
        }

        /* access modifiers changed from: private */
        public final void zzc(Iterable<? extends Integer> iterable) {
            zzjr zzjr = this.zzat;
            if (!zzjr.zzc()) {
                int size = zzjr.size();
                this.zzat = zzjr.zzc(size == 0 ? 10 : size << 1);
            }
            zzhq.zza(iterable, this.zzat);
        }

        /* access modifiers changed from: private */
        public final void zzd(Iterable<String> iterable) {
            zzjt<String> zzjt = this.zzbe;
            if (!zzjt.zzc()) {
                this.zzbe = zzjk.zza(zzjt);
            }
            zzhq.zza(iterable, this.zzbe);
        }

        /* access modifiers changed from: private */
        public final void zze(Iterable<? extends zzo> iterable) {
            zzdb();
            zzhq.zza(iterable, this.zzi);
        }

        /* access modifiers changed from: private */
        public final void zza(zzf zzf2) {
            zzf2.getClass();
            zzda();
            this.zzh.add(zzf2);
        }

        /* access modifiers changed from: private */
        public final void zza(zzo zzo2) {
            zzo2.getClass();
            zzdb();
            this.zzi.add(zzo2);
        }

        /* access modifiers changed from: private */
        public final void zzco() {
            this.zze &= -262145;
            this.zzaa = zzc.zzaa;
        }

        /* access modifiers changed from: private */
        public final void zzcp() {
            this.zzag = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zzcq() {
            this.zze &= -257;
            this.zzq = zzc.zzq;
        }

        /* access modifiers changed from: private */
        public final void zzcr() {
            this.zze &= Integer.MAX_VALUE;
            this.zzao = zzc.zzao;
        }

        /* access modifiers changed from: private */
        public final void zzcs() {
            this.zzh = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zzct() {
            this.zze &= -2097153;
            this.zzad = zzc.zzad;
        }

        /* access modifiers changed from: private */
        public final void zzcu() {
            this.zze &= -131073;
            this.zzz = false;
        }

        /* access modifiers changed from: private */
        public final void zzcv() {
            this.zze &= -33;
            this.zzn = 0;
        }

        /* access modifiers changed from: private */
        public final void zzcw() {
            this.zze &= -17;
            this.zzm = 0;
        }

        /* access modifiers changed from: private */
        public final void zzcx() {
            this.zze &= -65537;
            this.zzy = zzc.zzy;
        }

        /* access modifiers changed from: private */
        public final void zzcy() {
            this.zzf &= -8193;
            this.zzbd = zzc.zzbd;
        }

        /* access modifiers changed from: private */
        public final void zzcz() {
            this.zze &= -268435457;
            this.zzal = zzc.zzal;
        }

        private final void zzda() {
            zzjt<zzf> zzjt = this.zzh;
            if (!zzjt.zzc()) {
                this.zzh = zzjk.zza(zzjt);
            }
        }

        private final void zzdb() {
            zzjt<zzo> zzjt = this.zzi;
            if (!zzjt.zzc()) {
                this.zzi = zzjk.zza(zzjt);
            }
        }

        /* access modifiers changed from: private */
        public final void zzd(int i) {
            zzda();
            this.zzh.remove(i);
        }

        /* access modifiers changed from: private */
        public final void zze(int i) {
            zzdb();
            this.zzi.remove(i);
        }

        /* access modifiers changed from: private */
        public final void zza(zza zza2) {
            zza2.getClass();
            this.zzbp = zza2;
            this.zzf |= 16777216;
        }

        /* access modifiers changed from: private */
        public final void zzf(int i) {
            this.zzf |= 1048576;
            this.zzbl = i;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzf |= 4;
            this.zzar = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zze |= 4096;
            this.zzu = str;
        }

        /* access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zze |= 262144;
            this.zzaa = str;
        }

        /* access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zze |= 2048;
            this.zzt = str;
        }

        /* access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zze |= 8192;
            this.zzv = str;
        }

        /* access modifiers changed from: private */
        public final void zzg(int i) {
            this.zze |= 33554432;
            this.zzai = i;
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc2) {
            zzc2.getClass();
            this.zzbn = zzc2;
            this.zzf |= 4194304;
        }

        /* access modifiers changed from: private */
        public final void zzh(int i) {
            this.zze |= 1048576;
            this.zzac = i;
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zzf |= 32;
            this.zzav = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(long j) {
            this.zze |= 536870912;
            this.zzam = j;
        }

        /* access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzf |= 131072;
            this.zzbi = str;
        }

        /* access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzf |= 128;
            this.zzax = str;
        }

        /* access modifiers changed from: private */
        public final void zzh(String str) {
            str.getClass();
            this.zzf |= 524288;
            this.zzbk = str;
        }

        /* access modifiers changed from: private */
        public final void zzi(int i) {
            this.zzf |= 8388608;
            this.zzbo = i;
        }

        /* access modifiers changed from: private */
        public final void zzc(long j) {
            this.zze |= 524288;
            this.zzab = j;
        }

        /* access modifiers changed from: private */
        public final void zzi(String str) {
            str.getClass();
            this.zze |= 256;
            this.zzq = str;
        }

        /* access modifiers changed from: private */
        public final void zzj(String str) {
            str.getClass();
            this.zze |= Integer.MIN_VALUE;
            this.zzao = str;
        }

        /* access modifiers changed from: private */
        public final void zzd(long j) {
            this.zzf |= 16;
            this.zzau = j;
        }

        /* access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzf |= 65536;
            this.zzbh = z;
        }

        /* access modifiers changed from: private */
        public final void zze(long j) {
            this.zze |= 8;
            this.zzl = j;
        }

        /* access modifiers changed from: private */
        public final void zzk(String str) {
            str.getClass();
            this.zzf |= 16384;
            this.zzbf = str;
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzf zzf2) {
            zzf2.getClass();
            zzda();
            this.zzh.set(i, zzf2);
        }

        /* access modifiers changed from: private */
        public final void zzl(String str) {
            str.getClass();
            this.zze |= 16777216;
            this.zzah = str;
        }

        /* access modifiers changed from: private */
        public final void zzm(String str) {
            str.getClass();
            this.zze |= 4194304;
            this.zzae = str;
        }

        /* access modifiers changed from: private */
        public final void zzf(long j) {
            this.zze |= 16384;
            this.zzw = j;
        }

        /* access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zze |= 2097152;
            this.zzad = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzf |= 262144;
            this.zzbj = z;
        }

        /* access modifiers changed from: private */
        public final void zzc(boolean z) {
            this.zze |= 131072;
            this.zzz = z;
        }

        /* access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zze |= 128;
            this.zzp = str;
        }

        /* access modifiers changed from: private */
        public final void zzp(String str) {
            str.getClass();
            this.zze |= 64;
            this.zzo = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zzl zzl2) {
            zzl2.getClass();
            this.zzas = zzl2;
            this.zzf |= 8;
        }

        /* access modifiers changed from: private */
        public final void zzg(long j) {
            this.zze |= 32;
            this.zzn = j;
        }

        /* access modifiers changed from: private */
        public final void zzh(long j) {
            this.zze |= 16;
            this.zzm = j;
        }

        /* access modifiers changed from: private */
        public final void zzj(int i) {
            this.zze |= 1;
            this.zzg = 1;
        }

        /* access modifiers changed from: private */
        public final void zzq(String str) {
            str.getClass();
            this.zze |= 65536;
            this.zzy = str;
        }

        /* access modifiers changed from: private */
        public final void zzk(int i) {
            this.zzf |= 2;
            this.zzaq = i;
        }

        /* access modifiers changed from: private */
        public final void zzd(boolean z) {
            this.zze |= 8388608;
            this.zzaf = z;
        }

        /* access modifiers changed from: private */
        public final void zzr(String str) {
            str.getClass();
            this.zzf |= 8192;
            this.zzbd = str;
        }

        /* access modifiers changed from: private */
        public final void zzi(long j) {
            this.zze |= 4;
            this.zzk = j;
        }

        /* access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzf |= 32768;
            this.zzbg = j;
        }

        /* access modifiers changed from: private */
        public final void zzl(int i) {
            this.zze |= 1024;
            this.zzs = i;
        }

        /* access modifiers changed from: private */
        public final void zzk(long j) {
            this.zze |= 2;
            this.zzj = j;
        }

        /* access modifiers changed from: private */
        public final void zzl(long j) {
            this.zze |= 32768;
            this.zzx = j;
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzo zzo2) {
            zzo2.getClass();
            zzdb();
            this.zzi.set(i, zzo2);
        }

        /* access modifiers changed from: private */
        public final void zzs(String str) {
            str.getClass();
            this.zze |= 512;
            this.zzr = str;
        }

        public final boolean zzat() {
            return this.zzbh;
        }

        public final boolean zzau() {
            return this.zzbj;
        }

        public final boolean zzav() {
            return this.zzz;
        }

        public final boolean zzaw() {
            return this.zzaf;
        }

        public final boolean zzax() {
            return (this.zzf & 16777216) != 0;
        }

        public final boolean zzay() {
            return (this.zze & 33554432) != 0;
        }

        public final boolean zzaz() {
            return (this.zzf & 4194304) != 0;
        }

        public final boolean zzba() {
            return (this.zze & 1048576) != 0;
        }

        public final boolean zzbb() {
            return (this.zze & 536870912) != 0;
        }

        public final boolean zzbc() {
            return (this.zzf & 131072) != 0;
        }

        public final boolean zzbd() {
            return (this.zzf & 128) != 0;
        }

        public final boolean zzbe() {
            return (this.zzf & 524288) != 0;
        }

        public final boolean zzbf() {
            return (this.zzf & 8388608) != 0;
        }

        public final boolean zzbg() {
            return (this.zze & 524288) != 0;
        }

        public final boolean zzbh() {
            return (this.zzf & 16) != 0;
        }

        public final boolean zzbi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzbj() {
            return (this.zze & 16384) != 0;
        }

        public final boolean zzbk() {
            return (this.zzf & 262144) != 0;
        }

        public final boolean zzbl() {
            return (this.zze & 131072) != 0;
        }

        public final boolean zzbm() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzbn() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzbo() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzbp() {
            return (this.zzf & 2) != 0;
        }

        public final boolean zzbq() {
            return (this.zze & 8388608) != 0;
        }

        public final boolean zzbr() {
            return (this.zzf & 8192) != 0;
        }

        public final boolean zzbs() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzbt() {
            return (this.zzf & 32768) != 0;
        }

        public final boolean zzbu() {
            return (this.zze & 1024) != 0;
        }

        public final boolean zzbv() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzbw() {
            return (this.zze & 32768) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzm extends zzjk<zzm, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzm zzc;
        private static volatile zzlc<zzm> zzd;
        private zzjq zze = zzcf();
        private zzjq zzf = zzcf();
        private zzjt<zze> zzg = zzcg();
        private zzjt<zzn> zzh = zzcg();

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf.size();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzm, zza> implements zzkv {
            public final zza zza(Iterable<? extends zze> iterable) {
                zzak();
                ((zzm) this.zza).zza(iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                zzak();
                ((zzm) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                zzak();
                ((zzm) this.zza).zzc(iterable);
                return this;
            }

            public final zza zzd(Iterable<? extends Long> iterable) {
                zzak();
                ((zzm) this.zza).zzd(iterable);
                return this;
            }

            public final zza zza() {
                zzak();
                ((zzm) this.zza).zzl();
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzm) this.zza).zzm();
                return this;
            }

            public final zza zzc() {
                zzak();
                ((zzm) this.zza).zzn();
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzm) this.zza).zzo();
                return this;
            }

            private zza() {
                super(zzm.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public final int zzc() {
            return this.zzh.size();
        }

        public final int zzd() {
            return this.zze.size();
        }

        public static zza zze() {
            return (zza) zzc.zzcb();
        }

        public static zzm zzg() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzn.class});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzm> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzm.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zze> zzh() {
            return this.zzg;
        }

        public final List<Long> zzi() {
            return this.zzf;
        }

        public final List<zzn> zzj() {
            return this.zzh;
        }

        public final List<Long> zzk() {
            return this.zze;
        }

        static {
            zzm zzm = new zzm();
            zzc = zzm;
            zzjk.zza(zzm.class, zzm);
        }

        private zzm() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzjt<zze> zzjt = this.zzg;
            if (!zzjt.zzc()) {
                this.zzg = zzjk.zza(zzjt);
            }
            zzhq.zza(iterable, this.zzg);
        }

        /* access modifiers changed from: private */
        public final void zzb(Iterable<? extends Long> iterable) {
            zzjq zzjq = this.zzf;
            if (!zzjq.zzc()) {
                this.zzf = zzjk.zza(zzjq);
            }
            zzhq.zza(iterable, this.zzf);
        }

        /* access modifiers changed from: private */
        public final void zzc(Iterable<? extends zzn> iterable) {
            zzjt<zzn> zzjt = this.zzh;
            if (!zzjt.zzc()) {
                this.zzh = zzjk.zza(zzjt);
            }
            zzhq.zza(iterable, this.zzh);
        }

        /* access modifiers changed from: private */
        public final void zzd(Iterable<? extends Long> iterable) {
            zzjq zzjq = this.zze;
            if (!zzjq.zzc()) {
                this.zze = zzjk.zza(zzjq);
            }
            zzhq.zza(iterable, this.zze);
        }

        /* access modifiers changed from: private */
        public final void zzl() {
            this.zzg = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zzm() {
            this.zzf = zzcf();
        }

        /* access modifiers changed from: private */
        public final void zzn() {
            this.zzh = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zzo() {
            this.zze = zzcf();
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzn extends zzjk<zzn, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzn zzc;
        private static volatile zzlc<zzn> zzd;
        private int zze;
        private int zzf;
        private zzjq zzg = zzcf();

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzn, zza> implements zzkv {
            public final zza zza(Iterable<? extends Long> iterable) {
                zzak();
                ((zzn) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(int i) {
                zzak();
                ((zzn) this.zza).zzb(i);
                return this;
            }

            private zza() {
                super(zzn.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public final long zza(int i) {
            return this.zzg.zzb(i);
        }

        public static zza zzc() {
            return (zza) zzc.zzcb();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzn> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzn.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<Long> zze() {
            return this.zzg;
        }

        static {
            zzn zzn = new zzn();
            zzc = zzn;
            zzjk.zza(zzn.class, zzn);
        }

        private zzn() {
        }

        /* access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzjq zzjq = this.zzg;
            if (!zzjq.zzc()) {
                this.zzg = zzjk.zza(zzjq);
            }
            zzhq.zza(iterable, this.zzg);
        }

        /* access modifiers changed from: private */
        public final void zzb(int i) {
            this.zze |= 1;
            this.zzf = i;
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzo extends zzjk<zzo, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzo zzc;
        private static volatile zzlc<zzo> zzd;
        private int zze;
        private long zzf;
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private float zzj;
        private double zzk;

        public final double zza() {
            return this.zzk;
        }

        public final float zzb() {
            return this.zzj;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzo, zza> implements zzkv {
            public final zza zza() {
                zzak();
                ((zzo) this.zza).zzn();
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzo) this.zza).zzo();
                return this;
            }

            public final zza zzc() {
                zzak();
                ((zzo) this.zza).zzp();
                return this;
            }

            public final zza zza(double d) {
                zzak();
                ((zzo) this.zza).zza(d);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                ((zzo) this.zza).zza(j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                ((zzo) this.zza).zza(str);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                ((zzo) this.zza).zzb(j);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                ((zzo) this.zza).zzb(str);
                return this;
            }

            private zza() {
                super(zzo.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzf;
        }

        public static zza zze() {
            return (zza) zzc.zzcb();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzo> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzo.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzh;
        }

        static {
            zzo zzo = new zzo();
            zzc = zzo;
            zzjk.zza(zzo.class, zzo);
        }

        private zzo() {
        }

        /* access modifiers changed from: private */
        public final void zzn() {
            this.zze &= -33;
            this.zzk = 0.0d;
        }

        /* access modifiers changed from: private */
        public final void zzo() {
            this.zze &= -9;
            this.zzi = 0;
        }

        /* access modifiers changed from: private */
        public final void zzp() {
            this.zze &= -5;
            this.zzh = zzc.zzh;
        }

        /* access modifiers changed from: private */
        public final void zza(double d) {
            this.zze |= 32;
            this.zzk = d;
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 8;
            this.zzi = j;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzg = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(long j) {
            this.zze |= 1;
            this.zzf = j;
        }

        /* access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zze |= 4;
            this.zzh = str;
        }

        public final boolean zzi() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 4) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzc extends zzjk<zzc, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzlc<zzc> zzd;
        private int zze;
        private boolean zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;
        private boolean zzl;

        public static zza zza() {
            return (zza) zzc.zzcb();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzc, zza> implements zzkv {
            public final zza zza(boolean z) {
                zzak();
                ((zzc) this.zza).zza(z);
                return this;
            }

            public final zza zzb(boolean z) {
                zzak();
                ((zzc) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(boolean z) {
                zzak();
                ((zzc) this.zza).zzc(z);
                return this;
            }

            public final zza zzd(boolean z) {
                zzak();
                ((zzc) this.zza).zzd(z);
                return this;
            }

            public final zza zze(boolean z) {
                zzak();
                ((zzc) this.zza).zze(z);
                return this;
            }

            public final zza zzf(boolean z) {
                zzak();
                ((zzc) this.zza).zzf(z);
                return this;
            }

            public final zza zzg(boolean z) {
                zzak();
                ((zzc) this.zza).zzg(z);
                return this;
            }

            private zza() {
                super(zzc.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        public static zzc zzc() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzc> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzc.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzjk.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        /* access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zze |= 32;
            this.zzk = z;
        }

        /* access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zze |= 16;
            this.zzj = z;
        }

        /* access modifiers changed from: private */
        public final void zzc(boolean z) {
            this.zze |= 1;
            this.zzf = z;
        }

        /* access modifiers changed from: private */
        public final void zzd(boolean z) {
            this.zze |= 64;
            this.zzl = z;
        }

        /* access modifiers changed from: private */
        public final void zze(boolean z) {
            this.zze |= 2;
            this.zzg = z;
        }

        /* access modifiers changed from: private */
        public final void zzf(boolean z) {
            this.zze |= 4;
            this.zzh = z;
        }

        /* access modifiers changed from: private */
        public final void zzg(boolean z) {
            this.zze |= 8;
            this.zzi = z;
        }

        public final boolean zzd() {
            return this.zzk;
        }

        public final boolean zze() {
            return this.zzj;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return this.zzl;
        }

        public final boolean zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return this.zzh;
        }

        public final boolean zzj() {
            return this.zzi;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzg extends zzjk<zzg, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzlc<zzg> zzd;
        private int zze;
        private String zzf = "";
        private long zzg;

        public static zza zza() {
            return (zza) zzc.zzcb();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzg, zza> implements zzkv {
            public final zza zza(long j) {
                zzak();
                ((zzg) this.zza).zza(j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                ((zzg) this.zza).zza(str);
                return this;
            }

            private zza() {
                super(zzg.zzc);
            }

            /* synthetic */ zza(zzfp zzfp) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzg> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzg.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzg zzg2 = new zzg();
            zzc = zzg2;
            zzjk.zza(zzg.class, zzg2);
        }

        private zzg() {
        }

        /* access modifiers changed from: private */
        public final void zza(long j) {
            this.zze |= 2;
            this.zzg = j;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 1;
            this.zzf = str;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzl extends zzjk<zzl, zzb> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzl zzc;
        private static volatile zzlc<zzl> zzd;
        private int zze;
        private int zzf = 1;
        private zzjt<zzg> zzg = zzcg();

        public static zzb zza() {
            return (zzb) zzc.zzcb();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zza implements zzjp {
            RADS(1),
            PROVISIONING(2);
            
            private final int zzd;

            public final int zza() {
                return this.zzd;
            }

            public static zza zza(int i) {
                if (i == 1) {
                    return RADS;
                }
                if (i != 2) {
                    return null;
                }
                return PROVISIONING;
            }

            public static zzjo zzb() {
                return zzfs.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.zzd = i;
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzb extends zzjk.zzb<zzl, zzb> implements zzkv {
            public final zzb zza(zzg.zza zza) {
                zzak();
                ((zzl) this.zza).zza((zzg) ((zzjk) zza.zzai()));
                return this;
            }

            private zzb() {
                super(zzl.zzc);
            }

            /* synthetic */ zzb(zzfp zzfp) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfp.zza[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zzb((zzfp) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zze", "zzf", zza.zzb(), "zzg", zzg.class});
                case 4:
                    return zzc;
                case 5:
                    zzlc<zzl> zzlc = zzd;
                    if (zzlc == null) {
                        synchronized (zzl.class) {
                            zzlc = zzd;
                            if (zzlc == null) {
                                zzlc = new zzjk.zza<>(zzc);
                                zzd = zzlc;
                            }
                        }
                    }
                    return zzlc;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzl zzl = new zzl();
            zzc = zzl;
            zzjk.zza(zzl.class, zzl);
        }

        private zzl() {
        }

        /* access modifiers changed from: private */
        public final void zza(zzg zzg2) {
            zzg2.getClass();
            zzjt<zzg> zzjt = this.zzg;
            if (!zzjt.zzc()) {
                this.zzg = zzjk.zza(zzjt);
            }
            this.zzg.add(zzg2);
        }
    }
}

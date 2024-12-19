package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjk;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzff {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zza extends zzjk<zza, C0007zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzlc<zza> zzd;
        private int zze;
        private int zzf;
        private zzjt<zze> zzg = zzcg();
        private zzjt<zzb> zzh = zzcg();
        private boolean zzi;
        private boolean zzj;

        /* renamed from: com.google.android.gms.internal.measurement.zzff$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class C0007zza extends zzjk.zzb<zza, C0007zza> implements zzkv {
            public final int zza() {
                return ((zza) this.zza).zzb();
            }

            public final int zzb() {
                return ((zza) this.zza).zzc();
            }

            public final C0007zza zza(int i, zzb.zza zza) {
                zzak();
                ((zza) this.zza).zza(i, (zzb) ((zzjk) zza.zzai()));
                return this;
            }

            public final C0007zza zza(int i, zze.zza zza) {
                zzak();
                ((zza) this.zza).zza(i, (zze) ((zzjk) zza.zzai()));
                return this;
            }

            public final zzb zza(int i) {
                return ((zza) this.zza).zza(i);
            }

            public final zze zzb(int i) {
                return ((zza) this.zza).zzb(i);
            }

            private C0007zza() {
                super(zza.zzc);
            }

            /* synthetic */ C0007zza(zzfg zzfg) {
                this();
            }
        }

        public final int zza() {
            return this.zzf;
        }

        public final int zzb() {
            return this.zzh.size();
        }

        public final int zzc() {
            return this.zzg.size();
        }

        public final zzb zza(int i) {
            return (zzb) this.zzh.get(i);
        }

        public final zze zzb(int i) {
            return (zze) this.zzg.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0007zza((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzb.class, "zzi", "zzj"});
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

        public final List<zzb> zze() {
            return this.zzh;
        }

        public final List<zze> zzf() {
            return this.zzg;
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjk.zza(zza.class, zza);
        }

        private zza() {
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzb zzb) {
            zzb.getClass();
            zzjt<zzb> zzjt = this.zzh;
            if (!zzjt.zzc()) {
                this.zzh = zzjk.zza(zzjt);
            }
            this.zzh.set(i, zzb);
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zze zze2) {
            zze2.getClass();
            zzjt<zze> zzjt = this.zzg;
            if (!zzjt.zzc()) {
                this.zzg = zzjk.zza(zzjt);
            }
            this.zzg.set(i, zze2);
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzb extends zzjk<zzb, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzlc<zzb> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzjt<zzc> zzh = zzcg();
        private boolean zzi;
        private zzd zzj;
        private boolean zzk;
        private boolean zzl;
        private boolean zzm;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzb, zza> implements zzkv {
            public final int zza() {
                return ((zzb) this.zza).zza();
            }

            public final zza zza(String str) {
                zzak();
                ((zzb) this.zza).zza(str);
                return this;
            }

            public final zza zza(int i, zzc zzc) {
                zzak();
                ((zzb) this.zza).zza(i, zzc);
                return this;
            }

            public final zzc zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final String zzb() {
                return ((zzb) this.zza).zzf();
            }

            private zza() {
                super(zzb.zzc);
            }

            /* synthetic */ zza(zzfg zzfg) {
                this();
            }
        }

        public final int zza() {
            return this.zzh.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        public static zza zzc() {
            return (zza) zzc.zzcb();
        }

        public final zzc zza(int i) {
            return (zzc) this.zzh.get(i);
        }

        public final zzd zze() {
            zzd zzd2 = this.zzj;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzc.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
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

        public final String zzf() {
            return this.zzg;
        }

        public final List<zzc> zzg() {
            return this.zzh;
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzjk.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzg = str;
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzc zzc2) {
            zzc2.getClass();
            zzjt<zzc> zzjt = this.zzh;
            if (!zzjt.zzc()) {
                this.zzh = zzjk.zza(zzjt);
            }
            this.zzh.set(i, zzc2);
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return this.zzl;
        }

        public final boolean zzj() {
            return this.zzm;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 64) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzc extends zzjk<zzc, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzlc<zzc> zzd;
        private int zze;
        private zzf zzf;
        private zzd zzg;
        private boolean zzh;
        private String zzi = "";

        public static zzc zzb() {
            return zzc;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzc, zza> implements zzkv {
            public final zza zza(String str) {
                zzak();
                ((zzc) this.zza).zza(str);
                return this;
            }

            private zza() {
                super(zzc.zzc);
            }

            /* synthetic */ zza(zzfg zzfg) {
                this();
            }
        }

        public final zzd zzc() {
            zzd zzd2 = this.zzg;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        public final zzf zzd() {
            zzf zzf2 = this.zzf;
            return zzf2 == null ? zzf.zzd() : zzf2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public final String zze() {
            return this.zzi;
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzjk.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 8;
            this.zzi = str;
        }

        public final boolean zzf() {
            return this.zzh;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzd extends zzjk<zzd, zzb> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzlc<zzd> zzd;
        private int zze;
        private int zzf;
        private boolean zzg;
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";

        public final zza zza() {
            zza zza2 = zza.zza(this.zzf);
            return zza2 == null ? zza.UNKNOWN_COMPARISON_TYPE : zza2;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzb extends zzjk.zzb<zzd, zzb> implements zzkv {
            private zzb() {
                super(zzd.zzc);
            }

            /* synthetic */ zzb(zzfg zzfg) {
                this();
            }
        }

        public static zzd zzc() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zzb((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zze", "zzf", zza.zzb(), "zzg", "zzh", "zzi", "zzj"});
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

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zza implements zzjp {
            UNKNOWN_COMPARISON_TYPE(0),
            LESS_THAN(1),
            GREATER_THAN(2),
            EQUAL(3),
            BETWEEN(4);
            
            private final int zzg;

            public final int zza() {
                return this.zzg;
            }

            public static zza zza(int i) {
                if (i == 0) {
                    return UNKNOWN_COMPARISON_TYPE;
                }
                if (i == 1) {
                    return LESS_THAN;
                }
                if (i == 2) {
                    return GREATER_THAN;
                }
                if (i == 3) {
                    return EQUAL;
                }
                if (i != 4) {
                    return null;
                }
                return BETWEEN;
            }

            public static zzjo zzb() {
                return zzfh.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.zzg = i;
            }
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzj;
        }

        public final String zzf() {
            return this.zzi;
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjk.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public final boolean zzg() {
            return this.zzg;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 8) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zze extends zzjk<zze, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzlc<zze> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzc zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;

        public final int zza() {
            return this.zzf;
        }

        public final zzc zzb() {
            zzc zzc2 = this.zzh;
            return zzc2 == null ? zzc.zzb() : zzc2;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zze, zza> implements zzkv {
            public final zza zza(String str) {
                zzak();
                ((zze) this.zza).zza(str);
                return this;
            }

            private zza() {
                super(zze.zzc);
            }

            /* synthetic */ zza(zzfg zzfg) {
                this();
            }
        }

        public static zza zzc() {
            return (zza) zzc.zzcb();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
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

        public final String zze() {
            return this.zzg;
        }

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzjk.zza(zze.class, zze2);
        }

        private zze() {
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze |= 2;
            this.zzg = str;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return this.zzj;
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 32) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzf extends zzjk<zzf, zzb> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzlc<zzf> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private boolean zzh;
        private zzjt<String> zzi = zzjk.zzcg();

        public final int zza() {
            return this.zzi.size();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzb extends zzjk.zzb<zzf, zzb> implements zzkv {
            private zzb() {
                super(zzf.zzc);
            }

            /* synthetic */ zzb(zzfg zzfg) {
                this();
            }
        }

        public final zza zzb() {
            zza zza2 = zza.zza(this.zzf);
            return zza2 == null ? zza.UNKNOWN_MATCH_TYPE : zza2;
        }

        public static zzf zzd() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfg.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zzb((zzfg) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zze", "zzf", zza.zzb(), "zzg", "zzh", "zzi"});
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

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zza implements zzjp {
            UNKNOWN_MATCH_TYPE(0),
            REGEXP(1),
            BEGINS_WITH(2),
            ENDS_WITH(3),
            PARTIAL(4),
            EXACT(5),
            IN_LIST(6);
            
            private final int zzi;

            public final int zza() {
                return this.zzi;
            }

            public static zza zza(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_MATCH_TYPE;
                    case 1:
                        return REGEXP;
                    case 2:
                        return BEGINS_WITH;
                    case 3:
                        return ENDS_WITH;
                    case 4:
                        return PARTIAL;
                    case 5:
                        return EXACT;
                    case 6:
                        return IN_LIST;
                    default:
                        return null;
                }
            }

            public static zzjo zzb() {
                return zzfj.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzi + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.zzi = i;
            }
        }

        public final String zze() {
            return this.zzg;
        }

        public final List<String> zzf() {
            return this.zzi;
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjk.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        public final boolean zzg() {
            return this.zzh;
        }

        public final boolean zzh() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }
    }
}

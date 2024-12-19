package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzjk;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzfi {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zza extends zzjk<zza, zzb> implements zzkv {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzlc<zza> zzd;
        private int zze;
        private zzjt<C0008zza> zzf = zzcg();
        private zzjt<zzc> zzg = zzcg();
        private zzjt<zzf> zzh = zzcg();
        private boolean zzi;
        private zzjt<C0008zza> zzj = zzcg();

        /* renamed from: com.google.android.gms.internal.measurement.zzfi$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class C0008zza extends zzjk<C0008zza, C0009zza> implements zzkv {
            /* access modifiers changed from: private */
            public static final C0008zza zzc;
            private static volatile zzlc<C0008zza> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfi$zza$zza$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
            public static final class C0009zza extends zzjk.zzb<C0008zza, C0009zza> implements zzkv {
                private C0009zza() {
                    super(C0008zza.zzc);
                }

                /* synthetic */ C0009zza(zzfk zzfk) {
                    this();
                }
            }

            public final zzd zzb() {
                zzd zza = zzd.zza(this.zzg);
                return zza == null ? zzd.CONSENT_STATUS_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzfk.zza[i - 1]) {
                    case 1:
                        return new C0008zza();
                    case 2:
                        return new C0009zza((zzfk) null);
                    case 3:
                        return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zzd.zzb()});
                    case 4:
                        return zzc;
                    case 5:
                        zzlc<C0008zza> zzlc = zzd;
                        if (zzlc == null) {
                            synchronized (C0008zza.class) {
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
                C0008zza zza = new C0008zza();
                zzc = zza;
                zzjk.zza(C0008zza.class, zza);
            }

            private C0008zza() {
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzc extends zzjk<zzc, C0010zza> implements zzkv {
            /* access modifiers changed from: private */
            public static final zzc zzc;
            private static volatile zzlc<zzc> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfi$zza$zzc$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
            public static final class C0010zza extends zzjk.zzb<zzc, C0010zza> implements zzkv {
                private C0010zza() {
                    super(zzc.zzc);
                }

                /* synthetic */ C0010zza(zzfk zzfk) {
                    this();
                }
            }

            public final zze zzb() {
                zze zza = zze.zza(this.zzg);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzfk.zza[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new C0010zza((zzfk) null);
                    case 3:
                        return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zze.zzb()});
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
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzf extends zzjk<zzf, C0011zza> implements zzkv {
            /* access modifiers changed from: private */
            public static final zzf zzc;
            private static volatile zzlc<zzf> zzd;
            private int zze;
            private String zzf = "";
            private String zzg = "";

            /* renamed from: com.google.android.gms.internal.measurement.zzfi$zza$zzf$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
            public static final class C0011zza extends zzjk.zzb<zzf, C0011zza> implements zzkv {
                private C0011zza() {
                    super(zzf.zzc);
                }

                /* synthetic */ C0011zza(zzfk zzfk) {
                    this();
                }
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzfk.zza[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new C0011zza((zzfk) null);
                    case 3:
                        return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

            public final String zzb() {
                return this.zzf;
            }

            static {
                zzf zzf2 = new zzf();
                zzc = zzf2;
                zzjk.zza(zzf.class, zzf2);
            }

            private zzf() {
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zzb extends zzjk.zzb<zza, zzb> implements zzkv {
            private zzb() {
                super(zza.zzc);
            }

            /* synthetic */ zzb(zzfk zzfk) {
                this();
            }
        }

        public static zza zzb() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb((zzfk) null);
                case 3:
                    Class<C0008zza> cls = C0008zza.class;
                    return zza((zzkt) zzc, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004ဇ\u0000\u0005\u001b", new Object[]{"zze", "zzf", cls, "zzg", zzc.class, "zzh", zzf.class, "zzi", "zzj", cls});
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

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zzd implements zzjp {
            CONSENT_STATUS_UNSPECIFIED(0),
            GRANTED(1),
            DENIED(2);
            
            private final int zze;

            public final int zza() {
                return this.zze;
            }

            public static zzd zza(int i) {
                if (i == 0) {
                    return CONSENT_STATUS_UNSPECIFIED;
                }
                if (i == 1) {
                    return GRANTED;
                }
                if (i != 2) {
                    return null;
                }
                return DENIED;
            }

            public static zzjo zzb() {
                return zzfl.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            private zzd(int i) {
                this.zze = i;
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public enum zze implements zzjp {
            CONSENT_TYPE_UNSPECIFIED(0),
            AD_STORAGE(1),
            ANALYTICS_STORAGE(2),
            AD_USER_DATA(3),
            AD_PERSONALIZATION(4);
            
            private final int zzg;

            public final int zza() {
                return this.zzg;
            }

            public static zze zza(int i) {
                if (i == 0) {
                    return CONSENT_TYPE_UNSPECIFIED;
                }
                if (i == 1) {
                    return AD_STORAGE;
                }
                if (i == 2) {
                    return ANALYTICS_STORAGE;
                }
                if (i == 3) {
                    return AD_USER_DATA;
                }
                if (i != 4) {
                    return null;
                }
                return AD_PERSONALIZATION;
            }

            public static zzjo zzb() {
                return zzfm.zza;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            private zze(int i) {
                this.zzg = i;
            }
        }

        public final List<zzf> zzc() {
            return this.zzh;
        }

        public final List<C0008zza> zzd() {
            return this.zzf;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final List<C0008zza> zzf() {
            return this.zzj;
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjk.zza(zza.class, zza);
        }

        private zza() {
        }

        public final boolean zzg() {
            return this.zzi;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzb extends zzjk<zzb, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzlc<zzb> zzd;
        private int zze;
        private String zzf = "";
        private zzjt<zzf> zzg = zzcg();
        private boolean zzh;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzb, zza> implements zzkv {
            private zza() {
                super(zzb.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"zze", "zzf", "zzg", zzf.class, "zzh"});
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

        public final String zzb() {
            return this.zzf;
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
        private long zzf;
        private String zzg = "";
        private int zzh;
        private zzjt<zzg> zzi = zzcg();
        private zzjt<zzc> zzj = zzcg();
        private zzjt<zzff.zza> zzk = zzcg();
        private String zzl = "";
        private boolean zzm;
        private zzjt<zzft.zzc> zzn = zzcg();
        private zzjt<zzb> zzo = zzcg();
        private String zzp = "";
        private String zzq = "";
        private zza zzr;
        private zze zzs;
        private zzh zzt;
        private zzf zzu;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzd, zza> implements zzkv {
            public final int zza() {
                return ((zzd) this.zza).zzb();
            }

            public final zzc zza(int i) {
                return ((zzd) this.zza).zza(i);
            }

            public final zza zzb() {
                zzak();
                ((zzd) this.zza).zzt();
                return this;
            }

            public final zza zza(int i, zzc.zza zza) {
                zzak();
                ((zzd) this.zza).zza(i, (zzc) ((zzjk) zza.zzai()));
                return this;
            }

            public final String zzc() {
                return ((zzd) this.zza).zzj();
            }

            public final List<zzff.zza> zzd() {
                return Collections.unmodifiableList(((zzd) this.zza).zzk());
            }

            public final List<zzb> zze() {
                return Collections.unmodifiableList(((zzd) this.zza).zzl());
            }

            private zza() {
                super(zzd.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        public final int zza() {
            return this.zzn.size();
        }

        public final int zzb() {
            return this.zzj.size();
        }

        public final long zzc() {
            return this.zzf;
        }

        public final zza zzd() {
            zza zza2 = this.zzr;
            return zza2 == null ? zza.zzb() : zza2;
        }

        public final zzc zza(int i) {
            return (zzc) this.zzj.get(i);
        }

        public static zza zze() {
            return (zza) zzc.zzcb();
        }

        public static zzd zzg() {
            return zzc;
        }

        public final zzh zzh() {
            zzh zzh2 = this.zzt;
            return zzh2 == null ? zzh.zzc() : zzh2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0010\u0000\u0001\u0001\u0012\u0010\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005\u000eဈ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011ဉ\t\u0012ဉ\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzg.class, "zzj", zzc.class, "zzk", zzff.zza.class, "zzl", "zzm", "zzn", zzft.zzc.class, "zzo", zzb.class, "zzp", "zzq", "zzr", "zzs", "zzt", "zzu"});
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

        public final String zzi() {
            return this.zzg;
        }

        public final String zzj() {
            return this.zzp;
        }

        public final List<zzff.zza> zzk() {
            return this.zzk;
        }

        public final List<zzb> zzl() {
            return this.zzo;
        }

        public final List<zzft.zzc> zzm() {
            return this.zzn;
        }

        public final List<zzg> zzn() {
            return this.zzi;
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjk.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        /* access modifiers changed from: private */
        public final void zzt() {
            this.zzk = zzcg();
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zzc zzc2) {
            zzc2.getClass();
            zzjt<zzc> zzjt = this.zzj;
            if (!zzjt.zzc()) {
                this.zzj = zzjk.zza(zzjt);
            }
            this.zzj.set(i, zzc2);
        }

        public final boolean zzo() {
            return this.zzm;
        }

        public final boolean zzp() {
            return (this.zze & 128) != 0;
        }

        public final boolean zzq() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzr() {
            return (this.zze & 512) != 0;
        }

        public final boolean zzs() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zze extends zzjk<zze, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzlc<zze> zzd;
        private int zze;
        private int zzf = 14;
        private int zzg = 11;
        private int zzh = 60;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zze, zza> implements zzkv {
            private zza() {
                super(zze.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
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
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzf extends zzjk<zzf, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzlc<zzf> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzf, zza> implements zzkv {
            private zza() {
                super(zzf.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjk.zza(zzf.class, zzf2);
        }

        private zzf() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzg extends zzjk<zzg, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzlc<zzg> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzg, zza> implements zzkv {
            private zza() {
                super(zzg.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        static {
            zzg zzg2 = new zzg();
            zzc = zzg2;
            zzjk.zza(zzg.class, zzg2);
        }

        private zzg() {
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
        private String zzh = "";
        private int zzi;

        public final int zza() {
            return this.zzi;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzh, zza> implements zzkv {
            private zza() {
                super(zzh.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }
        }

        public static zzh zzc() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public final String zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzf;
        }

        static {
            zzh zzh2 = new zzh();
            zzc = zzh2;
            zzjk.zza(zzh.class, zzh2);
        }

        private zzh() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public static final class zzc extends zzjk<zzc, zza> implements zzkv {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzlc<zzc> zzd;
        private int zze;
        private String zzf = "";
        private boolean zzg;
        private boolean zzh;
        private int zzi;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
        public static final class zza extends zzjk.zzb<zzc, zza> implements zzkv {
            public final int zza() {
                return ((zzc) this.zza).zza();
            }

            public final zza zza(String str) {
                zzak();
                ((zzc) this.zza).zza(str);
                return this;
            }

            public final String zzb() {
                return ((zzc) this.zza).zzc();
            }

            private zza() {
                super(zzc.zzc);
            }

            /* synthetic */ zza(zzfk zzfk) {
                this();
            }

            public final boolean zzc() {
                return ((zzc) this.zza).zzd();
            }

            public final boolean zzd() {
                return ((zzc) this.zza).zze();
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzf();
            }

            public final boolean zzf() {
                return ((zzc) this.zza).zzg();
            }

            public final boolean zzg() {
                return ((zzc) this.zza).zzh();
            }
        }

        public final int zza() {
            return this.zzi;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfk.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzfk) null);
                case 3:
                    return zza((zzkt) zzc, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public final String zzc() {
            return this.zzf;
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
            this.zze |= 1;
            this.zzf = str;
        }

        public final boolean zzd() {
            return this.zzg;
        }

        public final boolean zze() {
            return this.zzh;
        }

        public final boolean zzf() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }
    }
}

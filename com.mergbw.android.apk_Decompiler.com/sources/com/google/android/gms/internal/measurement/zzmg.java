package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzmg {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class<?> zzc = zzht.zza();
    private static final boolean zzd;
    private static final boolean zze;
    private static final zzb zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi = ((long) zzb(byte[].class));

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    private static final class zza extends zzb {
        public final double zza(Object obj, long j) {
            return Double.longBitsToDouble(zze(obj, j));
        }

        public final float zzb(Object obj, long j) {
            return Float.intBitsToFloat(zzd(obj, j));
        }

        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, r3 ? (byte) 1 : 0);
            } else {
                zzmg.zzd(obj, j, r3 ? (byte) 1 : 0);
            }
        }

        public final void zza(Object obj, long j, byte b2) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, b2);
            } else {
                zzmg.zzd(obj, j, b2);
            }
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final boolean zzc(Object obj, long j) {
            if (zzmg.zza) {
                return zzmg.zzf(obj, j);
            }
            return zzmg.zzg(obj, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    private static abstract class zzb {
        Unsafe zza;

        public abstract double zza(Object obj, long j);

        public abstract void zza(Object obj, long j, byte b2);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract float zzb(Object obj, long j);

        public abstract boolean zzc(Object obj, long j);

        public final int zzd(Object obj, long j) {
            return this.zza.getInt(obj, j);
        }

        public final long zze(Object obj, long j) {
            return this.zza.getLong(obj, j);
        }

        zzb(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public final void zza(Object obj, long j, int i) {
            this.zza.putInt(obj, j, i);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zza.putLong(obj, j, j2);
        }

        public final boolean zza() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
                cls.getMethod("arrayIndexScale", new Class[]{Class.class});
                cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
                cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                Class cls2 = Long.TYPE;
                cls.getMethod("putLong", new Class[]{Object.class, cls2, cls2});
                cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
                Class<Object> cls3 = Object.class;
                cls.getMethod("putObject", new Class[]{cls3, Long.TYPE, cls3});
                return true;
            } catch (Throwable th) {
                zzmg.zza(th);
                return false;
            }
        }

        public final boolean zzb() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                if (zzmg.zze() == null) {
                    return false;
                }
                return true;
            } catch (Throwable th) {
                zzmg.zza(th);
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    private static final class zzc extends zzb {
        public final double zza(Object obj, long j) {
            return Double.longBitsToDouble(zze(obj, j));
        }

        public final float zzb(Object obj, long j) {
            return Float.intBitsToFloat(zzd(obj, j));
        }

        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, r3 ? (byte) 1 : 0);
            } else {
                zzmg.zzd(obj, j, r3 ? (byte) 1 : 0);
            }
        }

        public final void zza(Object obj, long j, byte b2) {
            if (zzmg.zza) {
                zzmg.zzc(obj, j, b2);
            } else {
                zzmg.zzd(obj, j, b2);
            }
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final boolean zzc(Object obj, long j) {
            if (zzmg.zza) {
                return zzmg.zzf(obj, j);
            }
            return zzmg.zzg(obj, j);
        }
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    private static int zzb(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzc(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzc(Object obj, long j) {
        return zzf.zzd(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zze(obj, j);
    }

    static <T> T zza(Class<T> cls) {
        try {
            return zzb.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zze(Object obj, long j) {
        return zzf.zza.getObject(obj, j);
    }

    /* access modifiers changed from: private */
    public static Field zze() {
        Field zza2 = zza((Class<?>) Buffer.class, "effectiveDirectAddress");
        if (zza2 != null) {
            return zza2;
        }
        Field zza3 = zza((Class<?>) Buffer.class, "address");
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static Unsafe zzb() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmi());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ void zza(Throwable th) {
        Logger logger = Logger.getLogger(zzmg.class.getName());
        Level level = Level.WARNING;
        String valueOf = String.valueOf(th);
        logger.logp(level, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: " + valueOf);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0034  */
    static {
        /*
            sun.misc.Unsafe r0 = zzb()
            zzb = r0
            java.lang.Class r1 = com.google.android.gms.internal.measurement.zzht.zza()
            zzc = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzd(r1)
            zzd = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzd(r2)
            zze = r2
            if (r0 == 0) goto L_0x002e
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.measurement.zzmg$zzc r1 = new com.google.android.gms.internal.measurement.zzmg$zzc
            r1.<init>(r0)
            goto L_0x002f
        L_0x0026:
            if (r2 == 0) goto L_0x002e
            com.google.android.gms.internal.measurement.zzmg$zza r1 = new com.google.android.gms.internal.measurement.zzmg$zza
            r1.<init>(r0)
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            zzf = r1
            r0 = 0
            if (r1 != 0) goto L_0x0036
            r2 = r0
            goto L_0x003a
        L_0x0036:
            boolean r2 = r1.zzb()
        L_0x003a:
            zzg = r2
            if (r1 != 0) goto L_0x0040
            r2 = r0
            goto L_0x0044
        L_0x0040:
            boolean r2 = r1.zza()
        L_0x0044:
            zzh = r2
            java.lang.Class<byte[]> r2 = byte[].class
            int r2 = zzb(r2)
            long r2 = (long) r2
            zzi = r2
            java.lang.Class<boolean[]> r2 = boolean[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<int[]> r2 = int[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<long[]> r2 = long[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<float[]> r2 = float[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<double[]> r2 = double[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            zzb(r2)
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            zzc(r2)
            java.lang.reflect.Field r2 = zze()
            if (r2 == 0) goto L_0x008f
            if (r1 != 0) goto L_0x008a
            goto L_0x008f
        L_0x008a:
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r2)
        L_0x008f:
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            if (r1 != r2) goto L_0x0098
            r0 = 1
        L_0x0098:
            zza = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzmg.<clinit>():void");
    }

    private zzmg() {
    }

    static void zzc(Object obj, long j, boolean z) {
        zzf.zza(obj, j, z);
    }

    static void zza(byte[] bArr, long j, byte b2) {
        zzf.zza((Object) bArr, zzi + j, b2);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int zzc2 = zzc(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zza(obj, j2, ((255 & b2) << i) | (zzc2 & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzd(Object obj, long j, byte b2) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b2) << i) | (zzc(obj, j2) & (~(255 << i))));
    }

    static void zza(Object obj, long j, double d) {
        zzf.zza(obj, j, d);
    }

    static void zza(Object obj, long j, float f) {
        zzf.zza(obj, j, f);
    }

    static void zza(Object obj, long j, int i) {
        zzf.zza(obj, j, i);
    }

    static void zza(Object obj, long j, long j2) {
        zzf.zza(obj, j, j2);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzf.zza.putObject(obj, j, obj2);
    }

    static /* synthetic */ boolean zzf(Object obj, long j) {
        return ((byte) (zzc(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)))) != 0;
    }

    static /* synthetic */ boolean zzg(Object obj, long j) {
        return ((byte) (zzc(obj, -4 & j) >>> ((int) ((j & 3) << 3)))) != 0;
    }

    private static boolean zzd(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        try {
            Class<?> cls3 = zzc;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            Class cls4 = Integer.TYPE;
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls4, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean zzh(Object obj, long j) {
        return zzf.zzc(obj, j);
    }

    static boolean zzc() {
        return zzh;
    }

    static boolean zzd() {
        return zzg;
    }
}

package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhfa {
    static final long zza = ((long) zzC(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = Memory.class;
    private static final boolean zze;
    private static final zzhez zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0156  */
    static {
        /*
            sun.misc.Unsafe r0 = zzi()
            zzc = r0
            int r1 = com.google.android.gms.internal.ads.zzgzm.zza
            java.lang.Class<libcore.io.Memory> r1 = libcore.io.Memory.class
            zzd = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzy(r1)
            zze = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzy(r2)
            r3 = 0
            if (r0 != 0) goto L_0x001e
            goto L_0x002d
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.ads.zzhey r3 = new com.google.android.gms.internal.ads.zzhey
            r3.<init>(r0)
            goto L_0x002d
        L_0x0026:
            if (r2 == 0) goto L_0x002d
            com.google.android.gms.internal.ads.zzhex r3 = new com.google.android.gms.internal.ads.zzhex
            r3.<init>(r0)
        L_0x002d:
            zzf = r3
            java.lang.String r0 = "getLong"
            java.lang.String r1 = "objectFieldOffset"
            r2 = 2
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x003a
        L_0x0038:
            r3 = r5
            goto L_0x0064
        L_0x003a:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x005f }
            r6[r4] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r0, r6)     // Catch:{ all -> 0x005f }
            java.lang.reflect.Field r3 = zzE()     // Catch:{ all -> 0x005f }
            if (r3 != 0) goto L_0x005d
            goto L_0x0038
        L_0x005d:
            r3 = r4
            goto L_0x0064
        L_0x005f:
            r3 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.ads.zzhfa.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r3.toString()))
            goto L_0x0038
        L_0x0064:
            zzg = r3
            com.google.android.gms.internal.ads.zzhez r3 = zzf
            if (r3 != 0) goto L_0x006d
        L_0x006a:
            r0 = r5
            goto L_0x00fb
        L_0x006d:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x00f5 }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "arrayBaseOffset"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "arrayIndexScale"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "getInt"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r6[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "putInt"
            r6 = 3
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            r7[r5] = r8     // Catch:{ all -> 0x00f5 }
            java.lang.Class r8 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r7[r4] = r8     // Catch:{ all -> 0x00f5 }
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00f5 }
            r7[r2] = r8     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r7)     // Catch:{ all -> 0x00f5 }
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "putLong"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r1[r2] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "getObject"
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "putObject"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r1[r5] = r6     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r1[r2] = r6     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            r0 = r4
            goto L_0x00fb
        L_0x00f5:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.ads.zzhfa.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x006a
        L_0x00fb:
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzC(r0)
            long r0 = (long) r0
            zza = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzC(r0)
            zzD(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzC(r0)
            zzD(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzC(r0)
            zzD(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzC(r0)
            zzD(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzC(r0)
            zzD(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzC(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzD(r0)
            java.lang.reflect.Field r0 = zzE()
            r1 = -1
            if (r0 == 0) goto L_0x014b
            com.google.android.gms.internal.ads.zzhez r3 = zzf
            if (r3 != 0) goto L_0x0145
            goto L_0x014b
        L_0x0145:
            sun.misc.Unsafe r1 = r3.zza
            long r1 = r1.objectFieldOffset(r0)
        L_0x014b:
            zzi = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0156
            goto L_0x0157
        L_0x0156:
            r4 = r5
        L_0x0157:
            zzb = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhfa.<clinit>():void");
    }

    private zzhfa() {
    }

    static boolean zzA() {
        return zzh;
    }

    static boolean zzB() {
        return zzg;
    }

    private static int zzC(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzD(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzE() {
        int i = zzgzm.zza;
        Field zzF = zzF(Buffer.class, "effectiveDirectAddress");
        if (zzF != null) {
            return zzF;
        }
        Field zzF2 = zzF(Buffer.class, "address");
        if (zzF2 == null || zzF2.getType() != Long.TYPE) {
            return null;
        }
        return zzF2;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzG(Object obj, long j, byte b2) {
        zzhez zzhez = zzf;
        long j2 = -4 & j;
        int i = zzhez.zza.getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        zzhez.zza.putInt(obj, j2, ((255 & b2) << i2) | (i & (~(255 << i2))));
    }

    /* access modifiers changed from: private */
    public static void zzH(Object obj, long j, byte b2) {
        zzhez zzhez = zzf;
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzhez.zza.putInt(obj, j2, ((255 & b2) << i) | (zzhez.zza.getInt(obj, j2) & (~(255 << i))));
    }

    static byte zza(long j) {
        return zzf.zza(j);
    }

    static double zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static float zzc(Object obj, long j) {
        return zzf.zzc(obj, j);
    }

    static int zzd(Object obj, long j) {
        return zzf.zza.getInt(obj, j);
    }

    static long zze(ByteBuffer byteBuffer) {
        zzhez zzhez = zzf;
        return zzhez.zza.getLong(byteBuffer, zzi);
    }

    static long zzf(Object obj, long j) {
        return zzf.zza.getLong(obj, j);
    }

    static Object zzg(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzh(Object obj, long j) {
        return zzf.zza.getObject(obj, j);
    }

    static Unsafe zzi() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzhew());
        } catch (Throwable unused) {
            return null;
        }
    }

    static void zzo(long j, byte[] bArr, long j2, long j3) {
        zzf.zzd(j, bArr, j2, j3);
    }

    static void zzp(Object obj, long j, boolean z) {
        zzf.zze(obj, j, z);
    }

    static void zzq(byte[] bArr, long j, byte b2) {
        zzf.zzf(bArr, zza + j, b2);
    }

    static void zzr(Object obj, long j, double d) {
        zzf.zzg(obj, j, d);
    }

    static void zzs(Object obj, long j, float f) {
        zzf.zzh(obj, j, f);
    }

    static void zzt(Object obj, long j, int i) {
        zzf.zza.putInt(obj, j, i);
    }

    static void zzu(Object obj, long j, long j2) {
        zzf.zza.putLong(obj, j, j2);
    }

    static void zzv(Object obj, long j, Object obj2) {
        zzf.zza.putObject(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzw(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzx(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzy(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzgzm.zza;
        try {
            Class cls3 = zzd;
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

    static boolean zzz(Object obj, long j) {
        return zzf.zzi(obj, j);
    }
}

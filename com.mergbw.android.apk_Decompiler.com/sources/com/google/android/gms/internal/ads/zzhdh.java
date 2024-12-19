package com.google.android.gms.internal.ads;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdh<T> implements zzhdz<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhfa.zzi();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzhde zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzhcs zzm;
    private final zzheq zzn;
    private final zzhaz zzo;
    private final zzhdk zzp;
    private final zzhcz zzq;

    private zzhdh(int[] iArr, Object[] objArr, int i, int i2, zzhde zzhde, int i3, boolean z, int[] iArr2, int i4, int i5, zzhdk zzhdk, zzhcs zzhcs, zzheq zzheq, zzhaz zzhaz, zzhcz zzhcz) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzhde instanceof zzhbo;
        boolean z2 = false;
        if (zzhaz != null && zzhaz.zzj(zzhde)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzp = zzhdk;
        this.zzm = zzhcs;
        this.zzn = zzheq;
        this.zzo = zzhaz;
        this.zzg = zzhde;
        this.zzq = zzhcz;
    }

    private final Object zzA(Object obj, int i) {
        zzhdz zzx = zzx(i);
        int zzu = zzu(i) & 1048575;
        if (!zzN(obj, i)) {
            return zzx.zze();
        }
        Object object = zzb.getObject(obj, (long) zzu);
        if (zzQ(object)) {
            return object;
        }
        Object zze2 = zzx.zze();
        if (object != null) {
            zzx.zzg(zze2, object);
        }
        return zze2;
    }

    private final Object zzB(Object obj, int i, int i2) {
        zzhdz zzx = zzx(i2);
        if (!zzR(obj, i, i2)) {
            return zzx.zze();
        }
        Object object = zzb.getObject(obj, (long) (zzu(i2) & 1048575));
        if (zzQ(object)) {
            return object;
        }
        Object zze2 = zzx.zze();
        if (object != null) {
            zzx.zzg(zze2, object);
        }
        return zze2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzD(Object obj) {
        if (!zzQ(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzE(Object obj, Object obj2, int i) {
        if (zzN(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzu = (long) (zzu(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzu);
            if (object != null) {
                zzhdz zzx = zzx(i);
                if (!zzN(obj, i)) {
                    if (!zzQ(object)) {
                        unsafe.putObject(obj, zzu, object);
                    } else {
                        Object zze2 = zzx.zze();
                        zzx.zzg(zze2, object);
                        unsafe.putObject(obj, zzu, zze2);
                    }
                    zzH(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzu);
                if (!zzQ(object2)) {
                    Object zze3 = zzx.zze();
                    zzx.zzg(zze3, object2);
                    unsafe.putObject(obj, zzu, zze3);
                    object2 = zze3;
                }
                zzx.zzg(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzF(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzR(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzu = (long) (zzu(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzu);
            if (object != null) {
                zzhdz zzx = zzx(i);
                if (!zzR(obj, i2, i)) {
                    if (!zzQ(object)) {
                        unsafe.putObject(obj, zzu, object);
                    } else {
                        Object zze2 = zzx.zze();
                        zzx.zzg(zze2, object);
                        unsafe.putObject(obj, zzu, zze2);
                    }
                    zzI(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzu);
                if (!zzQ(object2)) {
                    Object zze3 = zzx.zze();
                    zzx.zzg(zze3, object2);
                    unsafe.putObject(obj, zzu, zze3);
                    object2 = zze3;
                }
                zzx.zzg(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzG(Object obj, int i, zzhdr zzhdr) throws IOException {
        long j = (long) (i & 1048575);
        if (zzM(i)) {
            zzhfa.zzv(obj, j, zzhdr.zzu());
        } else if (this.zzi) {
            zzhfa.zzv(obj, j, zzhdr.zzt());
        } else {
            zzhfa.zzv(obj, j, zzhdr.zzp());
        }
    }

    private final void zzH(Object obj, int i) {
        int zzr = zzr(i);
        long j = (long) (1048575 & zzr);
        if (j != 1048575) {
            zzhfa.zzt(obj, j, (1 << (zzr >>> 20)) | zzhfa.zzd(obj, j));
        }
    }

    private final void zzI(Object obj, int i, int i2) {
        zzhfa.zzt(obj, (long) (zzr(i2) & 1048575), i);
    }

    private final void zzJ(Object obj, int i, Object obj2) {
        zzb.putObject(obj, (long) (zzu(i) & 1048575), obj2);
        zzH(obj, i);
    }

    private final void zzK(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, (long) (zzu(i2) & 1048575), obj2);
        zzI(obj, i, i2);
    }

    private final boolean zzL(Object obj, Object obj2, int i) {
        return zzN(obj, i) == zzN(obj2, i);
    }

    private static boolean zzM(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzN(Object obj, int i) {
        int zzr = zzr(i);
        long j = (long) (zzr & 1048575);
        if (j == 1048575) {
            int zzu = zzu(i);
            long j2 = (long) (zzu & 1048575);
            switch (zzt(zzu)) {
                case 0:
                    return Double.doubleToRawLongBits(zzhfa.zzb(obj, j2)) != 0;
                case 1:
                    return Float.floatToRawIntBits(zzhfa.zzc(obj, j2)) != 0;
                case 2:
                    return zzhfa.zzf(obj, j2) != 0;
                case 3:
                    return zzhfa.zzf(obj, j2) != 0;
                case 4:
                    return zzhfa.zzd(obj, j2) != 0;
                case 5:
                    return zzhfa.zzf(obj, j2) != 0;
                case 6:
                    return zzhfa.zzd(obj, j2) != 0;
                case 7:
                    return zzhfa.zzz(obj, j2);
                case 8:
                    Object zzh2 = zzhfa.zzh(obj, j2);
                    if (zzh2 instanceof String) {
                        return !((String) zzh2).isEmpty();
                    }
                    if (zzh2 instanceof zzhac) {
                        return !zzhac.zzb.equals(zzh2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhfa.zzh(obj, j2) != null;
                case 10:
                    return !zzhac.zzb.equals(zzhfa.zzh(obj, j2));
                case 11:
                    return zzhfa.zzd(obj, j2) != 0;
                case 12:
                    return zzhfa.zzd(obj, j2) != 0;
                case 13:
                    return zzhfa.zzd(obj, j2) != 0;
                case 14:
                    return zzhfa.zzf(obj, j2) != 0;
                case 15:
                    return zzhfa.zzd(obj, j2) != 0;
                case 16:
                    return zzhfa.zzf(obj, j2) != 0;
                case 17:
                    return zzhfa.zzh(obj, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzhfa.zzd(obj, j) & (1 << (zzr >>> 20))) != 0;
        }
    }

    private final boolean zzO(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzN(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzP(Object obj, int i, zzhdz zzhdz) {
        return zzhdz.zzl(zzhfa.zzh(obj, (long) (i & 1048575)));
    }

    private static boolean zzQ(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzhbo) {
            return ((zzhbo) obj).zzce();
        }
        return true;
    }

    private final boolean zzR(Object obj, int i, int i2) {
        return zzhfa.zzd(obj, (long) (zzr(i2) & 1048575)) == i;
    }

    private static boolean zzS(Object obj, long j) {
        return ((Boolean) zzhfa.zzh(obj, j)).booleanValue();
    }

    private static final void zzT(int i, Object obj, zzhfi zzhfi) throws IOException {
        if (obj instanceof String) {
            zzhfi.zzG(i, (String) obj);
        } else {
            zzhfi.zzd(i, (zzhac) obj);
        }
    }

    static zzher zzd(Object obj) {
        zzhbo zzhbo = (zzhbo) obj;
        zzher zzher = zzhbo.zzt;
        if (zzher != zzher.zzc()) {
            return zzher;
        }
        zzher zzf2 = zzher.zzf();
        zzhbo.zzt = zzf2;
        return zzf2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0282  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.ads.zzhdh zzm(java.lang.Class r33, com.google.android.gms.internal.ads.zzhdb r34, com.google.android.gms.internal.ads.zzhdk r35, com.google.android.gms.internal.ads.zzhcs r36, com.google.android.gms.internal.ads.zzheq r37, com.google.android.gms.internal.ads.zzhaz r38, com.google.android.gms.internal.ads.zzhcz r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzhdq
            if (r1 == 0) goto L_0x0402
            com.google.android.gms.internal.ads.zzhdq r0 = (com.google.android.gms.internal.ads.zzhdq) r0
            java.lang.String r1 = r0.zzd()
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0025
            r4 = 1
        L_0x001b:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0026
            r4 = r7
            goto L_0x001b
        L_0x0025:
            r7 = 1
        L_0x0026:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0045
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0032:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0042
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x0032
        L_0x0042:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x0045:
            if (r7 != 0) goto L_0x0057
            int[] r7 = zza
            r11 = r3
            r12 = r11
            r13 = r12
            r14 = r13
            r16 = r14
            r18 = r16
            r17 = r7
            r7 = r18
            goto L_0x0167
        L_0x0057:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0076
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0063:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0073
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r4 = r4 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x0063
        L_0x0073:
            int r7 = r7 << r9
            r4 = r4 | r7
            r7 = r10
        L_0x0076:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0095
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0082:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x0092
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0082
        L_0x0092:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r11
        L_0x0095:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00b4
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a1:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00b1
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00a1
        L_0x00b1:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00b4:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00d3
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c0:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00d0
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c0
        L_0x00d0:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00d3:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00f2
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00df:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x00ef
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00df
        L_0x00ef:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00f2:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x0111
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fe:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x010e
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00fe
        L_0x010e:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0111:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x0132
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011d:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x012e
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x011d
        L_0x012e:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0132:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0155
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013e:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r5) goto L_0x0150
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x013e
        L_0x0150:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0155:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int r16 = r4 + r4
            int r16 = r16 + r7
            int[] r7 = new int[r13]
            r17 = r7
            r13 = r9
            r18 = r14
            r7 = r4
            r14 = r10
            r4 = r15
        L_0x0167:
            sun.misc.Unsafe r9 = zzb
            java.lang.Object[] r10 = r0.zze()
            com.google.android.gms.internal.ads.zzhde r15 = r0.zza()
            java.lang.Class r15 = r15.getClass()
            int r19 = r18 + r12
            int r12 = r11 + r11
            int r11 = r11 * 3
            int[] r11 = new int[r11]
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r20 = r3
            r21 = r20
            r22 = r18
            r23 = r19
        L_0x0187:
            if (r4 >= r2) goto L_0x03db
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x01af
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r3 = r24
            r24 = 13
        L_0x0197:
            int r25 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01a9
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r4 = r4 | r3
            int r24 = r24 + 13
            r3 = r25
            goto L_0x0197
        L_0x01a9:
            int r3 = r3 << r24
            r4 = r4 | r3
            r3 = r25
            goto L_0x01b1
        L_0x01af:
            r3 = r24
        L_0x01b1:
            int r24 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01d7
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r8 = r24
            r24 = 13
        L_0x01bf:
            int r25 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r5) goto L_0x01d1
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r24
            r3 = r3 | r8
            int r24 = r24 + 13
            r8 = r25
            goto L_0x01bf
        L_0x01d1:
            int r8 = r8 << r24
            r3 = r3 | r8
            r8 = r25
            goto L_0x01d9
        L_0x01d7:
            r8 = r24
        L_0x01d9:
            r6 = r3 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto L_0x01e3
            int r6 = r20 + 1
            r17[r20] = r21
            r20 = r6
        L_0x01e3:
            r6 = r3 & 255(0xff, float:3.57E-43)
            r5 = r3 & 2048(0x800, float:2.87E-42)
            r26 = r2
            r2 = 51
            if (r6 < r2) goto L_0x029c
            int r2 = r8 + 1
            char r8 = r1.charAt(r8)
            r27 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r2) goto L_0x0221
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r30 = 13
            r32 = r27
            r27 = r8
            r8 = r32
        L_0x0204:
            int r31 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r2) goto L_0x021a
            r2 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r30
            r27 = r27 | r2
            int r30 = r30 + 13
            r8 = r31
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0204
        L_0x021a:
            int r2 = r8 << r30
            r8 = r27 | r2
            r2 = r31
            goto L_0x0223
        L_0x0221:
            r2 = r27
        L_0x0223:
            r27 = r2
            int r2 = r6 + -51
            r30 = r14
            r14 = 9
            if (r2 == r14) goto L_0x024f
            r14 = 17
            if (r2 != r14) goto L_0x0232
            goto L_0x024f
        L_0x0232:
            r14 = 12
            if (r2 != r14) goto L_0x025e
            int r2 = r0.zzc()
            r14 = 1
            if (r2 == r14) goto L_0x0242
            if (r5 == 0) goto L_0x0240
            goto L_0x0242
        L_0x0240:
            r5 = 0
            goto L_0x025e
        L_0x0242:
            int r2 = r16 + 1
            int r24 = r21 / 3
            int r24 = r24 + r24
            int r24 = r24 + 1
            r16 = r10[r16]
            r12[r24] = r16
            goto L_0x025c
        L_0x024f:
            r14 = 1
            int r2 = r16 + 1
            int r24 = r21 / 3
            int r24 = r24 + r24
            int r28 = r24 + 1
            r14 = r10[r16]
            r12[r28] = r14
        L_0x025c:
            r16 = r2
        L_0x025e:
            int r8 = r8 + r8
            r2 = r10[r8]
            boolean r14 = r2 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0268
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            goto L_0x0270
        L_0x0268:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = zzC(r15, r2)
            r10[r8] = r2
        L_0x0270:
            r31 = r13
            long r13 = r9.objectFieldOffset(r2)
            int r2 = (int) r13
            int r8 = r8 + 1
            r13 = r10[r8]
            boolean r14 = r13 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0282
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x028a
        L_0x0282:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzC(r15, r13)
            r10[r8] = r13
        L_0x028a:
            long r13 = r9.objectFieldOffset(r13)
            int r8 = (int) r13
            r28 = r0
            r29 = r1
            r0 = r16
            r25 = r27
            r16 = r8
            r8 = 0
            goto L_0x039b
        L_0x029c:
            r31 = r13
            r30 = r14
            int r2 = r16 + 1
            r13 = r10[r16]
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzC(r15, r13)
            r14 = 9
            if (r6 == r14) goto L_0x031f
            r14 = 17
            if (r6 != r14) goto L_0x02b4
            goto L_0x031f
        L_0x02b4:
            r14 = 27
            if (r6 == r14) goto L_0x030f
            r14 = 49
            if (r6 != r14) goto L_0x02c2
            int r16 = r16 + 2
            r28 = r0
            r0 = 1
            goto L_0x0314
        L_0x02c2:
            r14 = 12
            if (r6 == r14) goto L_0x02f6
            r14 = 30
            if (r6 == r14) goto L_0x02f6
            r14 = 44
            if (r6 != r14) goto L_0x02cf
            goto L_0x02f6
        L_0x02cf:
            r14 = 50
            if (r6 != r14) goto L_0x02f2
            int r14 = r16 + 2
            int r28 = r22 + 1
            r17[r22] = r21
            int r22 = r21 / 3
            r2 = r10[r2]
            int r22 = r22 + r22
            r12[r22] = r2
            if (r5 == 0) goto L_0x02ee
            int r22 = r22 + 1
            int r2 = r16 + 3
            r14 = r10[r14]
            r12[r22] = r14
            r22 = r28
            goto L_0x02f2
        L_0x02ee:
            r2 = r14
            r22 = r28
            r5 = 0
        L_0x02f2:
            r28 = r0
            r0 = 1
            goto L_0x032c
        L_0x02f6:
            int r14 = r0.zzc()
            r28 = r0
            r0 = 1
            if (r14 == r0) goto L_0x0304
            if (r5 == 0) goto L_0x0302
            goto L_0x0304
        L_0x0302:
            r5 = 0
            goto L_0x032c
        L_0x0304:
            int r16 = r16 + 2
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r0
            r2 = r10[r2]
            r12[r14] = r2
            goto L_0x031c
        L_0x030f:
            r28 = r0
            r0 = 1
            int r16 = r16 + 2
        L_0x0314:
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r0
            r2 = r10[r2]
            r12[r14] = r2
        L_0x031c:
            r2 = r16
            goto L_0x032c
        L_0x031f:
            r28 = r0
            r0 = 1
            int r14 = r21 / 3
            int r14 = r14 + r14
            int r14 = r14 + r0
            java.lang.Class r16 = r13.getType()
            r12[r14] = r16
        L_0x032c:
            long r13 = r9.objectFieldOffset(r13)
            int r13 = (int) r13
            r14 = r3 & 4096(0x1000, float:5.74E-42)
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r14 == 0) goto L_0x0386
            r14 = 17
            if (r6 > r14) goto L_0x0386
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            r0 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r0) goto L_0x0361
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x034b:
            int r25 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r0) goto L_0x035d
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r16
            r8 = r8 | r14
            int r16 = r16 + 13
            r14 = r25
            goto L_0x034b
        L_0x035d:
            int r14 = r14 << r16
            r8 = r8 | r14
            goto L_0x0363
        L_0x0361:
            r25 = r14
        L_0x0363:
            int r14 = r7 + r7
            int r16 = r8 / 32
            int r14 = r14 + r16
            r0 = r10[r14]
            r29 = r1
            boolean r1 = r0 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x0374
            java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0
            goto L_0x037c
        L_0x0374:
            java.lang.String r0 = (java.lang.String) r0
            java.lang.reflect.Field r0 = zzC(r15, r0)
            r10[r14] = r0
        L_0x037c:
            long r0 = r9.objectFieldOffset(r0)
            int r0 = (int) r0
            int r8 = r8 % 32
            r16 = r0
            goto L_0x038b
        L_0x0386:
            r29 = r1
            r25 = r8
            r8 = 0
        L_0x038b:
            r0 = 18
            if (r6 < r0) goto L_0x0399
            r0 = 49
            if (r6 > r0) goto L_0x0399
            int r0 = r23 + 1
            r17[r23] = r13
            r23 = r0
        L_0x0399:
            r0 = r2
            r2 = r13
        L_0x039b:
            int r1 = r21 + 1
            r11[r21] = r4
            int r4 = r21 + 2
            r13 = r3 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x03a8
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03a9
        L_0x03a8:
            r13 = 0
        L_0x03a9:
            r3 = r3 & 256(0x100, float:3.59E-43)
            if (r3 == 0) goto L_0x03b0
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03b1
        L_0x03b0:
            r3 = 0
        L_0x03b1:
            if (r5 == 0) goto L_0x03b6
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03b7
        L_0x03b6:
            r5 = 0
        L_0x03b7:
            int r6 = r6 << 20
            r3 = r3 | r13
            r3 = r3 | r5
            r3 = r3 | r6
            r2 = r2 | r3
            r11[r1] = r2
            int r21 = r21 + 3
            int r1 = r8 << 20
            r1 = r1 | r16
            r11[r4] = r1
            r16 = r0
            r4 = r25
            r2 = r26
            r0 = r28
            r1 = r29
            r14 = r30
            r13 = r31
            r3 = 0
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0187
        L_0x03db:
            r28 = r0
            r31 = r13
            r30 = r14
            com.google.android.gms.internal.ads.zzhdh r0 = new com.google.android.gms.internal.ads.zzhdh
            com.google.android.gms.internal.ads.zzhde r14 = r28.zza()
            int r15 = r28.zzc()
            r16 = 0
            r9 = r0
            r10 = r11
            r11 = r12
            r12 = r31
            r13 = r30
            r20 = r35
            r21 = r36
            r22 = r37
            r23 = r38
            r24 = r39
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return r0
        L_0x0402:
            com.google.android.gms.internal.ads.zzhen r0 = (com.google.android.gms.internal.ads.zzhen) r0
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zzm(java.lang.Class, com.google.android.gms.internal.ads.zzhdb, com.google.android.gms.internal.ads.zzhdk, com.google.android.gms.internal.ads.zzhcs, com.google.android.gms.internal.ads.zzheq, com.google.android.gms.internal.ads.zzhaz, com.google.android.gms.internal.ads.zzhcz):com.google.android.gms.internal.ads.zzhdh");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzhfa.zzh(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzhfa.zzh(obj, j)).floatValue();
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zzhfa.zzh(obj, j)).intValue();
    }

    private final int zzq(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzs(i, 0);
    }

    private final int zzr(int i) {
        return this.zzc[i + 2];
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzt(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzu(int i) {
        return this.zzc[i + 1];
    }

    private static long zzv(Object obj, long j) {
        return ((Long) zzhfa.zzh(obj, j)).longValue();
    }

    private final zzhbu zzw(int i) {
        int i2 = i / 3;
        return (zzhbu) this.zzd[i2 + i2 + 1];
    }

    private final zzhdz zzx(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzhdz zzhdz = (zzhdz) objArr[i3];
        if (zzhdz != null) {
            return zzhdz;
        }
        zzhdz zzb2 = zzhdo.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzy(Object obj, int i, Object obj2, zzheq zzheq, Object obj3) {
        int i2 = this.zzc[i];
        Object zzh2 = zzhfa.zzh(obj, (long) (zzu(i) & 1048575));
        if (zzh2 == null || zzw(i) == null) {
            return obj2;
        }
        zzhcy zzhcy = (zzhcy) zzh2;
        zzhcx zzhcx = (zzhcx) zzz(i);
        throw null;
    }

    private final Object zzz(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x037a, code lost:
        r1 = (r1 + r2) + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x037c, code lost:
        r12 = r12 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x039a, code lost:
        r1 = r1 * r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x054e, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0560, code lost:
        r12 = r12 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0670, code lost:
        r0 = r0 + (r2 + r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x06d9, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0762, code lost:
        r0 = r0 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0779, code lost:
        r0 = r0 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0791, code lost:
        r0 = r0 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0795, code lost:
        r11 = r11 + 3;
        r0 = r14;
        r1 = r16;
        r9 = false;
        r10 = 1048575;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.Object r20) {
        /*
            r19 = this;
            r6 = r19
            r7 = r20
            sun.misc.Unsafe r8 = zzb
            r9 = 0
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r9
            r11 = r1
            r12 = r11
            r0 = r10
        L_0x000e:
            int[] r2 = r6.zzc
            int r2 = r2.length
            if (r11 >= r2) goto L_0x07a0
            int r2 = r6.zzu(r11)
            int r3 = zzt(r2)
            int[] r4 = r6.zzc
            int r5 = r11 + 2
            r13 = r4[r11]
            r4 = r4[r5]
            r5 = r4 & r10
            r14 = 17
            r15 = 1
            if (r3 > r14) goto L_0x0040
            if (r5 == r0) goto L_0x0037
            if (r5 != r10) goto L_0x0030
            r0 = r9
            goto L_0x0035
        L_0x0030:
            long r0 = (long) r5
            int r0 = r8.getInt(r7, r0)
        L_0x0035:
            r1 = r0
            r0 = r5
        L_0x0037:
            int r4 = r4 >>> 20
            int r4 = r15 << r4
            r14 = r0
            r16 = r1
            r5 = r4
            goto L_0x0044
        L_0x0040:
            r14 = r0
            r16 = r1
            r5 = r9
        L_0x0044:
            r0 = r2 & r10
            com.google.android.gms.internal.ads.zzhbe r1 = com.google.android.gms.internal.ads.zzhbe.DOUBLE_LIST_PACKED
            int r1 = r1.zza()
            if (r3 < r1) goto L_0x0053
            com.google.android.gms.internal.ads.zzhbe r1 = com.google.android.gms.internal.ads.zzhbe.SINT64_LIST_PACKED
            r1.zza()
        L_0x0053:
            long r1 = (long) r0
            r17 = 63
            switch(r3) {
                case 0: goto L_0x077d;
                case 1: goto L_0x0765;
                case 2: goto L_0x0745;
                case 3: goto L_0x0727;
                case 4: goto L_0x0708;
                case 5: goto L_0x06f2;
                case 6: goto L_0x06dc;
                case 7: goto L_0x06c5;
                case 8: goto L_0x0691;
                case 9: goto L_0x0674;
                case 10: goto L_0x064d;
                case 11: goto L_0x062e;
                case 12: goto L_0x060e;
                case 13: goto L_0x05f8;
                case 14: goto L_0x05e2;
                case 15: goto L_0x05be;
                case 16: goto L_0x059a;
                case 17: goto L_0x057b;
                case 18: goto L_0x056e;
                case 19: goto L_0x0563;
                case 20: goto L_0x0540;
                case 21: goto L_0x0524;
                case 22: goto L_0x0508;
                case 23: goto L_0x04fc;
                case 24: goto L_0x04f0;
                case 25: goto L_0x04d6;
                case 26: goto L_0x0475;
                case 27: goto L_0x0435;
                case 28: goto L_0x0403;
                case 29: goto L_0x03e9;
                case 30: goto L_0x03cf;
                case 31: goto L_0x03c3;
                case 32: goto L_0x03b7;
                case 33: goto L_0x039d;
                case 34: goto L_0x037f;
                case 35: goto L_0x0364;
                case 36: goto L_0x034d;
                case 37: goto L_0x0336;
                case 38: goto L_0x031f;
                case 39: goto L_0x0308;
                case 40: goto L_0x02f0;
                case 41: goto L_0x02d8;
                case 42: goto L_0x02be;
                case 43: goto L_0x02a6;
                case 44: goto L_0x028e;
                case 45: goto L_0x0276;
                case 46: goto L_0x025e;
                case 47: goto L_0x0246;
                case 48: goto L_0x022e;
                case 49: goto L_0x0205;
                case 50: goto L_0x01d5;
                case 51: goto L_0x01c7;
                case 52: goto L_0x01b9;
                case 53: goto L_0x01a3;
                case 54: goto L_0x018d;
                case 55: goto L_0x0176;
                case 56: goto L_0x0168;
                case 57: goto L_0x015a;
                case 58: goto L_0x014c;
                case 59: goto L_0x0120;
                case 60: goto L_0x010c;
                case 61: goto L_0x00f0;
                case 62: goto L_0x00da;
                case 63: goto L_0x00c3;
                case 64: goto L_0x00b5;
                case 65: goto L_0x00a7;
                case 66: goto L_0x008c;
                case 67: goto L_0x0071;
                case 68: goto L_0x005b;
                default: goto L_0x0059;
            }
        L_0x0059:
            goto L_0x0795
        L_0x005b:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.android.gms.internal.ads.zzhde r0 = (com.google.android.gms.internal.ads.zzhde) r0
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzy(r13, r0, r1)
            goto L_0x0578
        L_0x0071:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = zzv(r7, r1)
            long r3 = r1 + r1
            long r1 = r1 >> r17
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            long r1 = r1 ^ r3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x008c:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = zzp(r7, r1)
            int r2 = r1 + r1
            int r1 = r1 >> 31
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            r1 = r1 ^ r2
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0762
        L_0x00a7:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0791
        L_0x00b5:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0779
        L_0x00c3:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = zzp(r7, r1)
            long r1 = (long) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x00da:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = zzp(r7, r1)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0762
        L_0x00f0:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            java.lang.Object r1 = r8.getObject(r7, r1)
            com.google.android.gms.internal.ads.zzhac r1 = (com.google.android.gms.internal.ads.zzhac) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = r1.zzd()
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0670
        L_0x010c:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r0 = com.google.android.gms.internal.ads.zzheb.zzh(r13, r0, r1)
            goto L_0x0578
        L_0x0120:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            java.lang.Object r1 = r8.getObject(r7, r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzhac
            if (r2 == 0) goto L_0x0140
            com.google.android.gms.internal.ads.zzhac r1 = (com.google.android.gms.internal.ads.zzhac) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = r1.zzd()
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0670
        L_0x0140:
            java.lang.String r1 = (java.lang.String) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzC(r1)
            goto L_0x0762
        L_0x014c:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x06d9
        L_0x015a:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0779
        L_0x0168:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0791
        L_0x0176:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = zzp(r7, r1)
            long r1 = (long) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x018d:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = zzv(r7, r1)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x01a3:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = zzv(r7, r1)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x01b9:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0779
        L_0x01c7:
            boolean r0 = r6.zzR(r7, r13, r11)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0791
        L_0x01d5:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.lang.Object r1 = r6.zzz(r11)
            com.google.android.gms.internal.ads.zzhcy r0 = (com.google.android.gms.internal.ads.zzhcy) r0
            com.google.android.gms.internal.ads.zzhcx r1 = (com.google.android.gms.internal.ads.zzhcx) r1
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0795
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x01f7
            goto L_0x0795
        L_0x01f7:
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r0.getKey()
            r0.getValue()
            r0 = 0
            throw r0
        L_0x0205:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r2 = com.google.android.gms.internal.ads.zzheb.zza
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0219
            r4 = r9
            goto L_0x022b
        L_0x0219:
            r3 = r9
            r4 = r3
        L_0x021b:
            if (r3 >= r2) goto L_0x022b
            java.lang.Object r5 = r0.get(r3)
            com.google.android.gms.internal.ads.zzhde r5 = (com.google.android.gms.internal.ads.zzhde) r5
            int r5 = com.google.android.gms.internal.ads.zzhat.zzy(r13, r5, r1)
            int r4 = r4 + r5
            int r3 = r3 + 1
            goto L_0x021b
        L_0x022b:
            int r12 = r12 + r4
            goto L_0x0795
        L_0x022e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzj(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x0246:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzi(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x025e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zze(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x0276:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzc(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x028e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zza(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x02a6:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzk(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x02be:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x02d8:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzc(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x02f0:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zze(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x0308:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzf(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x031f:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzl(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x0336:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzg(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x034d:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzc(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x037a
        L_0x0364:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zze(r0)
            if (r0 <= 0) goto L_0x0795
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
        L_0x037a:
            int r1 = r1 + r2
            int r1 = r1 + r0
        L_0x037c:
            int r12 = r12 + r1
            goto L_0x0795
        L_0x037f:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0390
        L_0x038d:
            r0 = r9
            goto L_0x0578
        L_0x0390:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zzj(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
        L_0x039a:
            int r1 = r1 * r2
            goto L_0x0762
        L_0x039d:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03ac
            goto L_0x038d
        L_0x03ac:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zzi(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            goto L_0x039a
        L_0x03b7:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzd(r13, r0, r9)
            goto L_0x0578
        L_0x03c3:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzb(r13, r0, r9)
            goto L_0x0578
        L_0x03cf:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03de
            goto L_0x038d
        L_0x03de:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zza(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            goto L_0x039a
        L_0x03e9:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03f8
            goto L_0x038d
        L_0x03f8:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zzk(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            goto L_0x039a
        L_0x0403:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0414
            r1 = r9
            goto L_0x037c
        L_0x0414:
            int r2 = r13 << 3
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            int r1 = r1 * r2
            r2 = r9
        L_0x041c:
            int r3 = r0.size()
            if (r2 >= r3) goto L_0x037c
            java.lang.Object r3 = r0.get(r2)
            com.google.android.gms.internal.ads.zzhac r3 = (com.google.android.gms.internal.ads.zzhac) r3
            int r3 = r3.zzd()
            int r4 = com.google.android.gms.internal.ads.zzhat.zzD(r3)
            int r4 = r4 + r3
            int r1 = r1 + r4
            int r2 = r2 + 1
            goto L_0x041c
        L_0x0435:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r2 = com.google.android.gms.internal.ads.zzheb.zza
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0449
            r3 = r9
            goto L_0x0472
        L_0x0449:
            int r3 = r13 << 3
            int r3 = com.google.android.gms.internal.ads.zzhat.zzD(r3)
            int r3 = r3 * r2
            r4 = r9
        L_0x0451:
            if (r4 >= r2) goto L_0x0472
            java.lang.Object r5 = r0.get(r4)
            boolean r13 = r5 instanceof com.google.android.gms.internal.ads.zzhck
            if (r13 == 0) goto L_0x0468
            com.google.android.gms.internal.ads.zzhck r5 = (com.google.android.gms.internal.ads.zzhck) r5
            int r5 = r5.zza()
            int r13 = com.google.android.gms.internal.ads.zzhat.zzD(r5)
            int r13 = r13 + r5
            int r3 = r3 + r13
            goto L_0x046f
        L_0x0468:
            com.google.android.gms.internal.ads.zzhde r5 = (com.google.android.gms.internal.ads.zzhde) r5
            int r5 = com.google.android.gms.internal.ads.zzhat.zzA(r5, r1)
            int r3 = r3 + r5
        L_0x046f:
            int r4 = r4 + 1
            goto L_0x0451
        L_0x0472:
            int r12 = r12 + r3
            goto L_0x0795
        L_0x0475:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0485
            goto L_0x054e
        L_0x0485:
            int r2 = r13 << 3
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            int r2 = r2 * r1
            boolean r3 = r0 instanceof com.google.android.gms.internal.ads.zzhcm
            if (r3 == 0) goto L_0x04b4
            com.google.android.gms.internal.ads.zzhcm r0 = (com.google.android.gms.internal.ads.zzhcm) r0
            r3 = r9
        L_0x0493:
            if (r3 >= r1) goto L_0x0560
            java.lang.Object r4 = r0.zze(r3)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzhac
            if (r5 == 0) goto L_0x04aa
            com.google.android.gms.internal.ads.zzhac r4 = (com.google.android.gms.internal.ads.zzhac) r4
            int r4 = r4.zzd()
            int r5 = com.google.android.gms.internal.ads.zzhat.zzD(r4)
            int r5 = r5 + r4
            int r2 = r2 + r5
            goto L_0x04b1
        L_0x04aa:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.ads.zzhat.zzC(r4)
            int r2 = r2 + r4
        L_0x04b1:
            int r3 = r3 + 1
            goto L_0x0493
        L_0x04b4:
            r3 = r9
        L_0x04b5:
            if (r3 >= r1) goto L_0x0560
            java.lang.Object r4 = r0.get(r3)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzhac
            if (r5 == 0) goto L_0x04cc
            com.google.android.gms.internal.ads.zzhac r4 = (com.google.android.gms.internal.ads.zzhac) r4
            int r4 = r4.zzd()
            int r5 = com.google.android.gms.internal.ads.zzhat.zzD(r4)
            int r5 = r5 + r4
            int r2 = r2 + r5
            goto L_0x04d3
        L_0x04cc:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.ads.zzhat.zzC(r4)
            int r2 = r2 + r4
        L_0x04d3:
            int r3 = r3 + 1
            goto L_0x04b5
        L_0x04d6:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r0 = r0.size()
            if (r0 != 0) goto L_0x04e6
            goto L_0x038d
        L_0x04e6:
            int r1 = r13 << 3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r1 = r1 + r15
            int r0 = r0 * r1
            goto L_0x0578
        L_0x04f0:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzb(r13, r0, r9)
            goto L_0x0578
        L_0x04fc:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzd(r13, r0, r9)
            goto L_0x0578
        L_0x0508:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0518
            goto L_0x038d
        L_0x0518:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zzf(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            goto L_0x039a
        L_0x0524:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0534
            goto L_0x038d
        L_0x0534:
            int r2 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzheb.zzl(r0)
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r2)
            goto L_0x039a
        L_0x0540:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r1 = com.google.android.gms.internal.ads.zzheb.zza
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0550
        L_0x054e:
            r2 = r9
            goto L_0x0560
        L_0x0550:
            int r1 = r13 << 3
            int r2 = com.google.android.gms.internal.ads.zzheb.zzg(r0)
            int r0 = r0.size()
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            int r0 = r0 * r1
            int r2 = r2 + r0
        L_0x0560:
            int r12 = r12 + r2
            goto L_0x0795
        L_0x0563:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzb(r13, r0, r9)
            goto L_0x0578
        L_0x056e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.ads.zzheb.zzd(r13, r0, r9)
        L_0x0578:
            int r12 = r12 + r0
            goto L_0x0795
        L_0x057b:
            r0 = r19
            r3 = r1
            r1 = r20
            r2 = r11
            r9 = r3
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.android.gms.internal.ads.zzhde r0 = (com.google.android.gms.internal.ads.zzhde) r0
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzy(r13, r0, r1)
            goto L_0x0578
        L_0x059a:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = r8.getLong(r7, r9)
            long r3 = r1 + r1
            long r1 = r1 >> r17
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            long r1 = r1 ^ r3
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x05be:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = r8.getInt(r7, r9)
            int r2 = r1 + r1
            int r1 = r1 >> 31
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            r1 = r1 ^ r2
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0762
        L_0x05e2:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0791
        L_0x05f8:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0779
        L_0x060e:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = r8.getInt(r7, r9)
            long r1 = (long) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x062e:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = r8.getInt(r7, r9)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0762
        L_0x064d:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            java.lang.Object r1 = r8.getObject(r7, r9)
            com.google.android.gms.internal.ads.zzhac r1 = (com.google.android.gms.internal.ads.zzhac) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = r1.zzd()
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
        L_0x0670:
            int r2 = r2 + r1
            int r0 = r0 + r2
            goto L_0x0578
        L_0x0674:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r11)
            int r0 = com.google.android.gms.internal.ads.zzheb.zzh(r13, r0, r1)
            goto L_0x0578
        L_0x0691:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            java.lang.Object r1 = r8.getObject(r7, r9)
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzhac
            if (r2 == 0) goto L_0x06b9
            com.google.android.gms.internal.ads.zzhac r1 = (com.google.android.gms.internal.ads.zzhac) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = r1.zzd()
            int r2 = com.google.android.gms.internal.ads.zzhat.zzD(r1)
            goto L_0x0670
        L_0x06b9:
            java.lang.String r1 = (java.lang.String) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzC(r1)
            goto L_0x0762
        L_0x06c5:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
        L_0x06d9:
            int r0 = r0 + r15
            goto L_0x0578
        L_0x06dc:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0779
        L_0x06f2:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            goto L_0x0791
        L_0x0708:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r1 = r8.getInt(r7, r9)
            long r1 = (long) r1
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x0727:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = r8.getLong(r7, r9)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
            goto L_0x0762
        L_0x0745:
            r9 = r1
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            long r1 = r8.getLong(r7, r9)
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
            int r1 = com.google.android.gms.internal.ads.zzhat.zzE(r1)
        L_0x0762:
            int r0 = r0 + r1
            goto L_0x0578
        L_0x0765:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
        L_0x0779:
            int r0 = r0 + 4
            goto L_0x0578
        L_0x077d:
            r0 = r19
            r1 = r20
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0795
            int r0 = r13 << 3
            int r0 = com.google.android.gms.internal.ads.zzhat.zzD(r0)
        L_0x0791:
            int r0 = r0 + 8
            goto L_0x0578
        L_0x0795:
            int r11 = r11 + 3
            r0 = r14
            r1 = r16
            r9 = 0
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000e
        L_0x07a0:
            com.google.android.gms.internal.ads.zzheq r0 = r6.zzn
            java.lang.Object r1 = r0.zzd(r7)
            int r0 = r0.zza(r1)
            int r12 = r12 + r0
            boolean r0 = r6.zzh
            if (r0 == 0) goto L_0x0802
            com.google.android.gms.internal.ads.zzhaz r0 = r6.zzo
            com.google.android.gms.internal.ads.zzhbd r0 = r0.zzb(r7)
            r9 = 0
            r18 = 0
        L_0x07b8:
            com.google.android.gms.internal.ads.zzhem r1 = r0.zza
            int r1 = r1.zzb()
            if (r9 >= r1) goto L_0x07d9
            com.google.android.gms.internal.ads.zzhem r1 = r0.zza
            java.util.Map$Entry r1 = r1.zzg(r9)
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.ads.zzhbc r2 = (com.google.android.gms.internal.ads.zzhbc) r2
            java.lang.Object r1 = r1.getValue()
            int r1 = com.google.android.gms.internal.ads.zzhbd.zzc(r2, r1)
            int r18 = r18 + r1
            int r9 = r9 + 1
            goto L_0x07b8
        L_0x07d9:
            com.google.android.gms.internal.ads.zzhem r0 = r0.zza
            java.lang.Iterable r0 = r0.zzc()
            java.util.Iterator r0 = r0.iterator()
        L_0x07e3:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0800
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.ads.zzhbc r2 = (com.google.android.gms.internal.ads.zzhbc) r2
            java.lang.Object r1 = r1.getValue()
            int r1 = com.google.android.gms.internal.ads.zzhbd.zzc(r2, r1)
            int r18 = r18 + r1
            goto L_0x07e3
        L_0x0800:
            int r12 = r12 + r18
        L_0x0802:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zza(java.lang.Object):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01b8, code lost:
        r1 = r1 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0212, code lost:
        r2 = (int) (r2 ^ (r2 >>> 32));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0216, code lost:
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0217, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int[] r2 = r8.zzc
            int r2 = r2.length
            if (r0 >= r2) goto L_0x021b
            int r2 = r8.zzu(r0)
            int[] r3 = r8.zzc
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r2
            int r2 = zzt(r2)
            r3 = r3[r0]
            long r4 = (long) r4
            r6 = 37
            r7 = 32
            switch(r2) {
                case 0: goto L_0x0206;
                case 1: goto L_0x01fb;
                case 2: goto L_0x01f2;
                case 3: goto L_0x01e9;
                case 4: goto L_0x01e2;
                case 5: goto L_0x01d9;
                case 6: goto L_0x01d2;
                case 7: goto L_0x01c7;
                case 8: goto L_0x01ba;
                case 9: goto L_0x01ac;
                case 10: goto L_0x01a0;
                case 11: goto L_0x0198;
                case 12: goto L_0x0190;
                case 13: goto L_0x0188;
                case 14: goto L_0x017e;
                case 15: goto L_0x0176;
                case 16: goto L_0x016c;
                case 17: goto L_0x015f;
                case 18: goto L_0x0153;
                case 19: goto L_0x0153;
                case 20: goto L_0x0153;
                case 21: goto L_0x0153;
                case 22: goto L_0x0153;
                case 23: goto L_0x0153;
                case 24: goto L_0x0153;
                case 25: goto L_0x0153;
                case 26: goto L_0x0153;
                case 27: goto L_0x0153;
                case 28: goto L_0x0153;
                case 29: goto L_0x0153;
                case 30: goto L_0x0153;
                case 31: goto L_0x0153;
                case 32: goto L_0x0153;
                case 33: goto L_0x0153;
                case 34: goto L_0x0153;
                case 35: goto L_0x0153;
                case 36: goto L_0x0153;
                case 37: goto L_0x0153;
                case 38: goto L_0x0153;
                case 39: goto L_0x0153;
                case 40: goto L_0x0153;
                case 41: goto L_0x0153;
                case 42: goto L_0x0153;
                case 43: goto L_0x0153;
                case 44: goto L_0x0153;
                case 45: goto L_0x0153;
                case 46: goto L_0x0153;
                case 47: goto L_0x0153;
                case 48: goto L_0x0153;
                case 49: goto L_0x0153;
                case 50: goto L_0x0147;
                case 51: goto L_0x0133;
                case 52: goto L_0x0121;
                case 53: goto L_0x0111;
                case 54: goto L_0x0101;
                case 55: goto L_0x00f3;
                case 56: goto L_0x00e3;
                case 57: goto L_0x00d5;
                case 58: goto L_0x00c3;
                case 59: goto L_0x00af;
                case 60: goto L_0x009d;
                case 61: goto L_0x008b;
                case 62: goto L_0x007d;
                case 63: goto L_0x006f;
                case 64: goto L_0x0061;
                case 65: goto L_0x0051;
                case 66: goto L_0x0043;
                case 67: goto L_0x0033;
                case 68: goto L_0x0021;
                default: goto L_0x001f;
            }
        L_0x001f:
            goto L_0x0217
        L_0x0021:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x0033:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            long r2 = zzv(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0043:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x0051:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            long r2 = zzv(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0061:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x006f:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x007d:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x008b:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x009d:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x00af:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            java.lang.String r2 = (java.lang.String) r2
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x00c3:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            boolean r2 = zzS(r9, r4)
            int r2 = com.google.android.gms.internal.ads.zzhcb.zza(r2)
            goto L_0x0216
        L_0x00d5:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x00e3:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            long r2 = zzv(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x00f3:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            int r2 = zzp(r9, r4)
            goto L_0x0216
        L_0x0101:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            long r2 = zzv(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0111:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            long r2 = zzv(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0121:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            float r2 = zzo(r9, r4)
            int r2 = java.lang.Float.floatToIntBits(r2)
            goto L_0x0216
        L_0x0133:
            boolean r2 = r8.zzR(r9, r3, r0)
            if (r2 == 0) goto L_0x0217
            int r1 = r1 * 53
            double r2 = zzn(r9, r4)
            long r2 = java.lang.Double.doubleToLongBits(r2)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0147:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x0153:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x015f:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            if (r2 == 0) goto L_0x01b8
            int r6 = r2.hashCode()
            goto L_0x01b8
        L_0x016c:
            int r1 = r1 * 53
            long r2 = com.google.android.gms.internal.ads.zzhfa.zzf(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0176:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x017e:
            int r1 = r1 * 53
            long r2 = com.google.android.gms.internal.ads.zzhfa.zzf(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x0188:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x0190:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x0198:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x01a0:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x01ac:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            if (r2 == 0) goto L_0x01b8
            int r6 = r2.hashCode()
        L_0x01b8:
            int r1 = r1 + r6
            goto L_0x0217
        L_0x01ba:
            int r1 = r1 * 53
            java.lang.Object r2 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r4)
            java.lang.String r2 = (java.lang.String) r2
            int r2 = r2.hashCode()
            goto L_0x0216
        L_0x01c7:
            int r1 = r1 * 53
            boolean r2 = com.google.android.gms.internal.ads.zzhfa.zzz(r9, r4)
            int r2 = com.google.android.gms.internal.ads.zzhcb.zza(r2)
            goto L_0x0216
        L_0x01d2:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x01d9:
            int r1 = r1 * 53
            long r2 = com.google.android.gms.internal.ads.zzhfa.zzf(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x01e2:
            int r1 = r1 * 53
            int r2 = com.google.android.gms.internal.ads.zzhfa.zzd(r9, r4)
            goto L_0x0216
        L_0x01e9:
            int r1 = r1 * 53
            long r2 = com.google.android.gms.internal.ads.zzhfa.zzf(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x01f2:
            int r1 = r1 * 53
            long r2 = com.google.android.gms.internal.ads.zzhfa.zzf(r9, r4)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
            goto L_0x0212
        L_0x01fb:
            int r1 = r1 * 53
            float r2 = com.google.android.gms.internal.ads.zzhfa.zzc(r9, r4)
            int r2 = java.lang.Float.floatToIntBits(r2)
            goto L_0x0216
        L_0x0206:
            int r1 = r1 * 53
            double r2 = com.google.android.gms.internal.ads.zzhfa.zzb(r9, r4)
            long r2 = java.lang.Double.doubleToLongBits(r2)
            byte[] r4 = com.google.android.gms.internal.ads.zzhcb.zzd
        L_0x0212:
            long r4 = r2 >>> r7
            long r2 = r2 ^ r4
            int r2 = (int) r2
        L_0x0216:
            int r1 = r1 + r2
        L_0x0217:
            int r0 = r0 + 3
            goto L_0x0002
        L_0x021b:
            int r1 = r1 * 53
            com.google.android.gms.internal.ads.zzheq r0 = r8.zzn
            java.lang.Object r0 = r0.zzd(r9)
            int r0 = r0.hashCode()
            int r1 = r1 + r0
            boolean r0 = r8.zzh
            if (r0 == 0) goto L_0x023b
            int r1 = r1 * 53
            com.google.android.gms.internal.ads.zzhaz r0 = r8.zzo
            com.google.android.gms.internal.ads.zzhbd r9 = r0.zzb(r9)
            com.google.android.gms.internal.ads.zzhem r9 = r9.zza
            int r9 = r9.hashCode()
            int r1 = r1 + r9
        L_0x023b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zzb(java.lang.Object):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v88, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v90, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v93, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v96, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v98, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v102, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v103, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v81, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v113, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v118, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v119, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v124, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v51, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v52, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v125, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v53, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v129, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v131, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v133, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v135, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v136, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v137, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v139, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v141, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v143, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v144, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v145, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v147, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v149, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v153, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v154, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v156, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v161, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v165, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v166, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v168, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v170, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v171, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v173, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v177, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v178, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v181, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v140, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v184, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v142, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v185, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v187, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v188, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v191, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v193, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v196, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v197, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v198, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v202, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v205, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v67, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v76, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v103, resolved type: int} */
    /* JADX WARNING: type inference failed for: r3v60, types: [int] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02c2, code lost:
        r14 = r37;
        r13 = r38;
        r3 = r8;
        r1 = r9;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02c9, code lost:
        r5 = r24;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02ce, code lost:
        r0 = r4;
        r2 = r8;
        r20 = r9;
        r1 = r10;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02d4, code lost:
        r17 = -1;
        r9 = r38;
        r19 = r0;
        r6 = r1;
        r10 = r37;
        r8 = r12;
        r11 = r20;
        r20 = r5;
        r31 = r3;
        r3 = r2;
        r2 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x044d, code lost:
        r7 = r2;
        r11 = r3;
        r10 = r9;
        r14 = r20;
        r8 = r25;
        r9 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x048d, code lost:
        r14 = r8;
        r8 = r25;
        r31 = r10;
        r10 = r9;
        r9 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0497, code lost:
        r7 = r2;
        r11 = r3;
        r10 = r9;
        r14 = r20;
        r8 = r25;
        r9 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0529, code lost:
        r7 = r14;
        r14 = r8;
        r8 = r25;
        r31 = r10;
        r10 = r9;
        r9 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x05f4, code lost:
        r11 = r0;
        r8 = r2;
        r9 = r3;
        r10 = r4;
        r14 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x0701, code lost:
        r11 = r0;
        r0 = r1;
        r8 = r2;
        r9 = r3;
        r10 = r4;
        r14 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0816, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x0836, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0837, code lost:
        if (r0 == r11) goto L_0x084a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0839, code lost:
        r13 = r38;
        r3 = r7;
        r1 = r8;
        r2 = r10;
        r11 = r14;
        r10 = -1;
        r4 = r19;
        r5 = r24;
        r7 = r34;
        r14 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x084a, code lost:
        r2 = r0;
        r3 = r7;
        r11 = r8;
        r6 = r10;
        r8 = r12;
        r20 = r14;
        r7 = r34;
        r10 = r9;
        r9 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x08a8, code lost:
        r10 = r37;
        r36 = r2;
        r31 = r11;
        r11 = r8;
        r8 = r12;
        r12 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0137, code lost:
        r1 = r9;
        r2 = r10;
        r3 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x0976, code lost:
        r10 = r37;
        r36 = r2;
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x097d, code lost:
        r10 = r37;
        r36 = r2;
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:0x0ae3, code lost:
        r0 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:451:0x0ae4, code lost:
        if (r0 == r12) goto L_0x0af9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:0x0ae6, code lost:
        r6 = r33;
        r2 = r36;
        r13 = r38;
        r12 = r8;
        r14 = r10;
        r1 = r11;
        r10 = -1;
        r4 = r19;
        r11 = r20;
        r5 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:453:0x0af9, code lost:
        r6 = r36;
        r9 = r38;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0174, code lost:
        r0 = r4;
        r20 = r9;
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0178, code lost:
        r5 = r11;
        r2 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0287, code lost:
        r14 = r37;
        r13 = r38;
        r3 = r8;
        r1 = r9;
        r2 = r10;
        r4 = r17;
        r0 = r18;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x04c7  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0567  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x05b7  */
    /* JADX WARNING: Removed duplicated region for block: B:523:0x04f5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:527:0x0701 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:531:0x0701 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(java.lang.Object r34, byte[] r35, int r36, int r37, int r38, com.google.android.gms.internal.ads.zzgzn r39) throws java.io.IOException {
        /*
            r33 = this;
            r6 = r33
            r7 = r34
            r15 = r35
            r14 = r37
            r13 = r38
            r12 = r39
            zzD(r34)
            sun.misc.Unsafe r11 = zzb
            r16 = 0
            r10 = -1
            r0 = r36
            r1 = r10
            r2 = r16
            r3 = r2
            r4 = r3
            r5 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001e:
            if (r0 >= r14) goto L_0x0b60
            int r3 = r0 + 1
            byte r0 = r15[r0]
            if (r0 >= 0) goto L_0x002f
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzi(r0, r15, r3, r12)
            int r3 = r12.zza
            r8 = r3
            r3 = r0
            goto L_0x0030
        L_0x002f:
            r8 = r0
        L_0x0030:
            int r0 = r8 >>> 3
            r9 = 3
            if (r0 <= r1) goto L_0x0045
            int r2 = r2 / r9
            int r1 = r6.zze
            if (r0 < r1) goto L_0x0043
            int r1 = r6.zzf
            if (r0 > r1) goto L_0x0043
            int r1 = r6.zzs(r0, r2)
            goto L_0x0049
        L_0x0043:
            r1 = r10
            goto L_0x0049
        L_0x0045:
            int r1 = r6.zzq(r0)
        L_0x0049:
            r2 = r1
            r18 = 0
            if (r2 != r10) goto L_0x0060
            r2 = r3
            r19 = r4
            r24 = r5
            r3 = r8
            r17 = r10
            r20 = r11
            r8 = r12
            r9 = r13
            r10 = r14
            r6 = r16
            r11 = r0
            goto L_0x0afe
        L_0x0060:
            r1 = r8 & 7
            int[] r10 = r6.zzc
            int r20 = r2 + 1
            r9 = r10[r20]
            r20 = r0
            int r0 = zzt(r9)
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r9 & r17
            long r13 = (long) r13
            r21 = r8
            r22 = 0
            java.lang.String r8 = ""
            r25 = r8
            r8 = 17
            if (r0 > r8) goto L_0x02ea
            int r8 = r2 + 2
            r8 = r10[r8]
            int r10 = r8 >>> 20
            r24 = 1
            int r10 = r24 << r10
            r27 = r9
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r8 & r9
            r17 = r10
            if (r8 == r5) goto L_0x00aa
            if (r5 == r9) goto L_0x009d
            long r9 = (long) r5
            r11.putInt(r7, r9, r4)
            r9 = 1048575(0xfffff, float:1.469367E-39)
        L_0x009d:
            if (r8 != r9) goto L_0x00a2
            r4 = r16
            goto L_0x00a7
        L_0x00a2:
            long r4 = (long) r8
            int r4 = r11.getInt(r7, r4)
        L_0x00a7:
            r24 = r8
            goto L_0x00ac
        L_0x00aa:
            r24 = r5
        L_0x00ac:
            switch(r0) {
                case 0: goto L_0x02ab;
                case 1: goto L_0x0293;
                case 2: goto L_0x0271;
                case 3: goto L_0x0271;
                case 4: goto L_0x025e;
                case 5: goto L_0x0246;
                case 6: goto L_0x0231;
                case 7: goto L_0x0215;
                case 8: goto L_0x01bf;
                case 9: goto L_0x0192;
                case 10: goto L_0x017d;
                case 11: goto L_0x025e;
                case 12: goto L_0x013d;
                case 13: goto L_0x0231;
                case 14: goto L_0x0246;
                case 15: goto L_0x011f;
                case 16: goto L_0x00f7;
                default: goto L_0x00af;
            }
        L_0x00af:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 3
            if (r1 != r0) goto L_0x02ce
            r4 = r4 | r17
            java.lang.Object r0 = r6.zzA(r7, r10)
            int r1 = r9 << 3
            r13 = r1 | 4
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r10)
            r2 = r8
            r8 = r0
            r5 = r9
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r1
            r1 = r10
            r17 = -1
            r10 = r35
            r20 = r5
            r5 = r11
            r11 = r3
            r3 = r12
            r12 = r37
            r36 = r4
            r4 = r37
            r14 = r39
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzl(r8, r9, r10, r11, r12, r13, r14)
            r6.zzJ(r7, r1, r0)
            r13 = r38
            r12 = r3
            r14 = r4
            r11 = r5
            r0 = r8
            r10 = r17
            r5 = r24
            r4 = r36
            r3 = r2
            r2 = r1
            r1 = r20
            goto L_0x001e
        L_0x00f7:
            if (r1 != 0) goto L_0x011a
            r8 = r4 | r17
            int r10 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r3, r12)
            long r0 = r12.zzb
            long r4 = com.google.android.gms.internal.ads.zzham.zzH(r0)
            r3 = r20
            r0 = r11
            r1 = r34
            r9 = r3
            r36 = r10
            r10 = r2
            r2 = r13
            r0.putLong(r1, r2, r4)
            r0 = r36
            r14 = r37
            r13 = r38
            r4 = r8
            goto L_0x0137
        L_0x011a:
            r9 = r20
            r1 = r2
            r0 = r4
            goto L_0x0178
        L_0x011f:
            r10 = r2
            r9 = r20
            if (r1 != 0) goto L_0x0174
            r4 = r4 | r17
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            int r1 = com.google.android.gms.internal.ads.zzham.zzF(r1)
            r11.putInt(r7, r13, r1)
            r14 = r37
            r13 = r38
        L_0x0137:
            r1 = r9
            r2 = r10
            r3 = r21
            goto L_0x02c9
        L_0x013d:
            r10 = r2
            r9 = r20
            if (r1 != 0) goto L_0x0174
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            com.google.android.gms.internal.ads.zzhbu r2 = r6.zzw(r10)
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r27 & r3
            if (r3 == 0) goto L_0x016b
            if (r2 == 0) goto L_0x016b
            boolean r2 = r2.zza(r1)
            if (r2 == 0) goto L_0x015b
            goto L_0x016b
        L_0x015b:
            com.google.android.gms.internal.ads.zzher r2 = zzd(r34)
            long r13 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r13)
            r8 = r21
            r2.zzj(r8, r1)
            goto L_0x02c2
        L_0x016b:
            r8 = r21
            r4 = r4 | r17
            r11.putInt(r7, r13, r1)
            goto L_0x02c2
        L_0x0174:
            r0 = r4
            r20 = r9
            r1 = r10
        L_0x0178:
            r5 = r11
            r2 = r21
            goto L_0x02d4
        L_0x017d:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 2
            if (r1 != r0) goto L_0x02ce
            r4 = r4 | r17
            int r0 = com.google.android.gms.internal.ads.zzgzo.zza(r15, r3, r12)
            java.lang.Object r1 = r12.zzc
            r11.putObject(r7, r13, r1)
            goto L_0x02c2
        L_0x0192:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 2
            if (r1 != r0) goto L_0x02ce
            r13 = r4 | r17
            java.lang.Object r14 = r6.zzA(r7, r10)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r10)
            r0 = r14
            r2 = r35
            r4 = r37
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzm(r0, r1, r2, r3, r4, r5)
            r6.zzJ(r7, r10, r14)
            r14 = r37
            r3 = r8
            r1 = r9
            r2 = r10
            r4 = r13
            r5 = r24
            r10 = -1
            r13 = r38
            goto L_0x001e
        L_0x01bf:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 2
            if (r1 != r0) goto L_0x02ce
            boolean r0 = zzM(r27)
            if (r0 == 0) goto L_0x01ec
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            if (r1 < 0) goto L_0x01e7
            r2 = r4 | r17
            if (r1 != 0) goto L_0x01de
            r5 = r25
            r12.zzc = r5
            goto L_0x01e5
        L_0x01de:
            java.lang.String r3 = com.google.android.gms.internal.ads.zzhff.zzh(r15, r0, r1)
            r12.zzc = r3
            int r0 = r0 + r1
        L_0x01e5:
            r4 = r2
            goto L_0x0209
        L_0x01e7:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x01ec:
            r5 = r25
            r0 = r4 | r17
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r2 = r12.zza
            if (r2 < 0) goto L_0x0210
            if (r2 != 0) goto L_0x01fd
            r12.zzc = r5
            goto L_0x0207
        L_0x01fd:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.ads.zzhcb.zzb
            r3.<init>(r15, r1, r2, r4)
            r12.zzc = r3
            int r1 = r1 + r2
        L_0x0207:
            r4 = r0
            r0 = r1
        L_0x0209:
            java.lang.Object r1 = r12.zzc
            r11.putObject(r7, r13, r1)
            goto L_0x02c2
        L_0x0210:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x0215:
            r10 = r2
            r9 = r20
            r8 = r21
            if (r1 != 0) goto L_0x02ce
            r4 = r4 | r17
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r3, r12)
            long r1 = r12.zzb
            int r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x022a
            r1 = 1
            goto L_0x022c
        L_0x022a:
            r1 = r16
        L_0x022c:
            com.google.android.gms.internal.ads.zzhfa.zzp(r7, r13, r1)
            goto L_0x02c2
        L_0x0231:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 5
            if (r1 != r0) goto L_0x02ce
            int r0 = r3 + 4
            r4 = r4 | r17
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r3)
            r11.putInt(r7, r13, r1)
            goto L_0x02c2
        L_0x0246:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 1
            if (r1 != r0) goto L_0x02ce
            int r18 = r3 + 8
            r17 = r4 | r17
            long r4 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r3)
            r0 = r11
            r1 = r34
            r2 = r13
            r0.putLong(r1, r2, r4)
            goto L_0x0287
        L_0x025e:
            r10 = r2
            r9 = r20
            r8 = r21
            if (r1 != 0) goto L_0x02ce
            r4 = r4 | r17
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            r11.putInt(r7, r13, r1)
            goto L_0x02c2
        L_0x0271:
            r10 = r2
            r9 = r20
            r8 = r21
            if (r1 != 0) goto L_0x02ce
            r17 = r4 | r17
            int r18 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r3, r12)
            long r4 = r12.zzb
            r0 = r11
            r1 = r34
            r2 = r13
            r0.putLong(r1, r2, r4)
        L_0x0287:
            r14 = r37
            r13 = r38
            r3 = r8
            r1 = r9
            r2 = r10
            r4 = r17
            r0 = r18
            goto L_0x02c9
        L_0x0293:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 5
            if (r1 != r0) goto L_0x02ce
            int r0 = r3 + 4
            r4 = r4 | r17
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r3)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            com.google.android.gms.internal.ads.zzhfa.zzs(r7, r13, r1)
            goto L_0x02c2
        L_0x02ab:
            r10 = r2
            r9 = r20
            r8 = r21
            r0 = 1
            if (r1 != r0) goto L_0x02ce
            int r0 = r3 + 8
            r4 = r4 | r17
            long r1 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r3)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            com.google.android.gms.internal.ads.zzhfa.zzr(r7, r13, r1)
        L_0x02c2:
            r14 = r37
            r13 = r38
            r3 = r8
            r1 = r9
            r2 = r10
        L_0x02c9:
            r5 = r24
            r10 = -1
            goto L_0x001e
        L_0x02ce:
            r0 = r4
            r2 = r8
            r20 = r9
            r1 = r10
            r5 = r11
        L_0x02d4:
            r17 = -1
            r4 = r37
            r9 = r38
            r19 = r0
            r6 = r1
            r10 = r4
            r8 = r12
            r11 = r20
            r20 = r5
            r31 = r3
            r3 = r2
            r2 = r31
            goto L_0x0afe
        L_0x02ea:
            r19 = r4
            r24 = r5
            r27 = r9
            r5 = r11
            r8 = r25
            r17 = -1
            r4 = r37
            r9 = r2
            r2 = r21
            r11 = 27
            r21 = 10
            if (r0 != r11) goto L_0x034f
            r11 = 2
            if (r1 != r11) goto L_0x0346
            java.lang.Object r0 = r5.getObject(r7, r13)
            com.google.android.gms.internal.ads.zzhca r0 = (com.google.android.gms.internal.ads.zzhca) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x0321
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0316
            goto L_0x0318
        L_0x0316:
            int r21 = r1 + r1
        L_0x0318:
            r1 = r21
            com.google.android.gms.internal.ads.zzhca r0 = r0.zzf(r1)
            r5.putObject(r7, r13, r0)
        L_0x0321:
            r13 = r0
            com.google.android.gms.internal.ads.zzhdz r8 = r6.zzx(r9)
            r0 = r9
            r9 = r2
            r10 = r35
            r1 = r20
            r11 = r3
            r3 = r12
            r12 = r37
            r14 = r39
            int r8 = com.google.android.gms.internal.ads.zzgzo.zze(r8, r9, r10, r11, r12, r13, r14)
            r13 = r38
            r12 = r3
            r14 = r4
            r11 = r5
            r10 = r17
            r4 = r19
            r5 = r24
            r3 = r2
            r2 = r0
            r0 = r8
            goto L_0x001e
        L_0x0346:
            r11 = r3
            r8 = r20
            r3 = r2
            r20 = r5
            r2 = r9
            goto L_0x088c
        L_0x034f:
            r11 = r20
            r20 = r5
            r5 = 49
            if (r0 > r5) goto L_0x0858
            r25 = r11
            r5 = r27
            long r10 = (long) r5
            sun.misc.Unsafe r5 = zzb
            java.lang.Object r27 = r5.getObject(r7, r13)
            r28 = r8
            r8 = r27
            com.google.android.gms.internal.ads.zzhca r8 = (com.google.android.gms.internal.ads.zzhca) r8
            boolean r27 = r8.zzc()
            if (r27 != 0) goto L_0x0383
            int r27 = r8.size()
            if (r27 != 0) goto L_0x0375
            goto L_0x0377
        L_0x0375:
            int r21 = r27 + r27
        L_0x0377:
            r29 = r10
            r10 = r21
            com.google.android.gms.internal.ads.zzhca r8 = r8.zzf(r10)
            r5.putObject(r7, r13, r8)
            goto L_0x0385
        L_0x0383:
            r29 = r10
        L_0x0385:
            r13 = r8
            switch(r0) {
                case 18: goto L_0x07c0;
                case 19: goto L_0x0769;
                case 20: goto L_0x071f;
                case 21: goto L_0x071f;
                case 22: goto L_0x06f2;
                case 23: goto L_0x06a8;
                case 24: goto L_0x065d;
                case 25: goto L_0x05fb;
                case 26: goto L_0x0534;
                case 27: goto L_0x0501;
                case 28: goto L_0x04a1;
                case 29: goto L_0x06f2;
                case 30: goto L_0x0457;
                case 31: goto L_0x065d;
                case 32: goto L_0x06a8;
                case 33: goto L_0x0400;
                case 34: goto L_0x03b2;
                case 35: goto L_0x07c0;
                case 36: goto L_0x0769;
                case 37: goto L_0x071f;
                case 38: goto L_0x071f;
                case 39: goto L_0x06f2;
                case 40: goto L_0x06a8;
                case 41: goto L_0x065d;
                case 42: goto L_0x05fb;
                case 43: goto L_0x06f2;
                case 44: goto L_0x0457;
                case 45: goto L_0x065d;
                case 46: goto L_0x06a8;
                case 47: goto L_0x0400;
                case 48: goto L_0x03b2;
                default: goto L_0x0389;
            }
        L_0x0389:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r0 = 3
            r9 = r4
            if (r1 != r0) goto L_0x0836
            r0 = r7 & -8
            r20 = r0 | 4
            com.google.android.gms.internal.ads.zzhdz r21 = r6.zzx(r10)
            r0 = r21
            r1 = r35
            r2 = r11
            r3 = r37
            r4 = r20
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzc(r0, r1, r2, r3, r4, r5)
            java.lang.Object r1 = r12.zzc
            r13.add(r1)
            goto L_0x0818
        L_0x03b2:
            r0 = 2
            if (r1 != r0) goto L_0x03d7
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            int r1 = r1 + r0
        L_0x03be:
            if (r0 >= r1) goto L_0x03ce
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r0, r12)
            long r10 = r12.zzb
            long r10 = com.google.android.gms.internal.ads.zzham.zzH(r10)
            r13.zzg(r10)
            goto L_0x03be
        L_0x03ce:
            if (r0 != r1) goto L_0x03d2
            goto L_0x044d
        L_0x03d2:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x03d7:
            if (r1 != 0) goto L_0x0497
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r3, r12)
            long r10 = r12.zzb
            long r10 = com.google.android.gms.internal.ads.zzham.zzH(r10)
            r13.zzg(r10)
        L_0x03e8:
            if (r0 >= r4) goto L_0x044d
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r5 = r12.zza
            if (r2 != r5) goto L_0x044d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r1, r12)
            long r10 = r12.zzb
            long r10 = com.google.android.gms.internal.ads.zzham.zzH(r10)
            r13.zzg(r10)
            goto L_0x03e8
        L_0x0400:
            r0 = 2
            if (r1 != r0) goto L_0x0424
            com.google.android.gms.internal.ads.zzhbp r13 = (com.google.android.gms.internal.ads.zzhbp) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            int r1 = r1 + r0
        L_0x040c:
            if (r0 >= r1) goto L_0x041c
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r5 = r12.zza
            int r5 = com.google.android.gms.internal.ads.zzham.zzF(r5)
            r13.zzi(r5)
            goto L_0x040c
        L_0x041c:
            if (r0 != r1) goto L_0x041f
            goto L_0x044d
        L_0x041f:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x0424:
            if (r1 != 0) goto L_0x0497
            com.google.android.gms.internal.ads.zzhbp r13 = (com.google.android.gms.internal.ads.zzhbp) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            int r1 = com.google.android.gms.internal.ads.zzham.zzF(r1)
            r13.zzi(r1)
        L_0x0435:
            if (r0 >= r4) goto L_0x044d
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r5 = r12.zza
            if (r2 != r5) goto L_0x044d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r1 = r12.zza
            int r1 = com.google.android.gms.internal.ads.zzham.zzF(r1)
            r13.zzi(r1)
            goto L_0x0435
        L_0x044d:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r9 = r4
            goto L_0x0837
        L_0x0457:
            r0 = 2
            if (r1 != r0) goto L_0x0466
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzf(r15, r3, r13, r12)
            r14 = r2
            r11 = r3
            r10 = r4
            r8 = r20
        L_0x0463:
            r20 = r0
            goto L_0x047b
        L_0x0466:
            if (r1 != 0) goto L_0x0497
            r0 = r2
            r1 = r35
            r14 = r2
            r2 = r3
            r11 = r3
            r3 = r37
            r10 = r4
            r4 = r13
            r8 = r20
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzj(r0, r1, r2, r3, r4, r5)
            goto L_0x0463
        L_0x047b:
            com.google.android.gms.internal.ads.zzhbu r3 = r6.zzw(r9)
            r4 = 0
            com.google.android.gms.internal.ads.zzheq r5 = r6.zzn
            r0 = r34
            r1 = r25
            r2 = r13
            com.google.android.gms.internal.ads.zzheb.zzo(r0, r1, r2, r3, r4, r5)
            r7 = r14
            r0 = r20
        L_0x048d:
            r14 = r8
            r8 = r25
            r31 = r10
            r10 = r9
            r9 = r31
            goto L_0x0837
        L_0x0497:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r9 = r4
            goto L_0x0836
        L_0x04a1:
            r14 = r2
            r11 = r3
            r10 = r4
            r8 = r20
            r0 = 2
            if (r1 != r0) goto L_0x0529
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r11, r12)
            int r1 = r12.zza
            if (r1 < 0) goto L_0x04fc
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x04f7
            if (r1 != 0) goto L_0x04bd
            com.google.android.gms.internal.ads.zzhac r1 = com.google.android.gms.internal.ads.zzhac.zzb
            r13.add(r1)
            goto L_0x04c5
        L_0x04bd:
            com.google.android.gms.internal.ads.zzhac r2 = com.google.android.gms.internal.ads.zzhac.zzv(r15, r0, r1)
            r13.add(r2)
        L_0x04c4:
            int r0 = r0 + r1
        L_0x04c5:
            if (r0 >= r10) goto L_0x04f5
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r2 = r12.zza
            if (r14 != r2) goto L_0x04f5
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r1 = r12.zza
            if (r1 < 0) goto L_0x04f0
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x04eb
            if (r1 != 0) goto L_0x04e3
            com.google.android.gms.internal.ads.zzhac r1 = com.google.android.gms.internal.ads.zzhac.zzb
            r13.add(r1)
            goto L_0x04c5
        L_0x04e3:
            com.google.android.gms.internal.ads.zzhac r2 = com.google.android.gms.internal.ads.zzhac.zzv(r15, r0, r1)
            r13.add(r2)
            goto L_0x04c4
        L_0x04eb:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x04f0:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x04f5:
            r7 = r14
            goto L_0x048d
        L_0x04f7:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x04fc:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x0501:
            r14 = r2
            r11 = r3
            r10 = r4
            r8 = r20
            r0 = 2
            if (r1 != r0) goto L_0x0529
            com.google.android.gms.internal.ads.zzhdz r0 = r6.zzx(r9)
            r5 = r8
            r8 = r0
            r4 = r9
            r9 = r14
            r3 = r10
            r10 = r35
            r0 = r11
            r2 = r25
            r1 = r12
            r12 = r37
            r7 = r14
            r14 = r39
            int r8 = com.google.android.gms.internal.ads.zzgzo.zze(r8, r9, r10, r11, r12, r13, r14)
            r12 = r1
            r9 = r3
            r10 = r4
            r14 = r5
            r0 = r8
            r8 = r2
            goto L_0x0837
        L_0x0529:
            r7 = r14
            r14 = r8
            r8 = r25
            r31 = r10
            r10 = r9
            r9 = r31
            goto L_0x0836
        L_0x0534:
            r7 = r2
            r0 = r3
            r3 = r4
            r4 = r9
            r5 = r20
            r2 = r25
            r8 = 2
            if (r1 != r8) goto L_0x05f4
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r29 & r8
            int r1 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r1 != 0) goto L_0x0592
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x058d
            if (r8 != 0) goto L_0x0558
            r9 = r28
            r13.add(r9)
            goto L_0x0565
        L_0x0558:
            r9 = r28
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.ads.zzhcb.zzb
            r10.<init>(r15, r1, r8, r11)
            r13.add(r10)
        L_0x0564:
            int r1 = r1 + r8
        L_0x0565:
            if (r1 >= r3) goto L_0x0701
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r10 = r12.zza
            if (r7 != r10) goto L_0x0701
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r8, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x0588
            if (r8 != 0) goto L_0x057d
            r13.add(r9)
            goto L_0x0565
        L_0x057d:
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.ads.zzhcb.zzb
            r10.<init>(r15, r1, r8, r11)
            r13.add(r10)
            goto L_0x0564
        L_0x0588:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x058d:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x0592:
            r9 = r28
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x05ef
            if (r8 != 0) goto L_0x05a2
            r13.add(r9)
            goto L_0x05b5
        L_0x05a2:
            int r10 = r1 + r8
            boolean r11 = com.google.android.gms.internal.ads.zzhff.zzj(r15, r1, r10)
            if (r11 == 0) goto L_0x05ea
            java.lang.String r11 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzhcb.zzb
            r11.<init>(r15, r1, r8, r14)
            r13.add(r11)
        L_0x05b4:
            r1 = r10
        L_0x05b5:
            if (r1 >= r3) goto L_0x0701
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r10 = r12.zza
            if (r7 != r10) goto L_0x0701
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r8, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x05e5
            if (r8 != 0) goto L_0x05cd
            r13.add(r9)
            goto L_0x05b5
        L_0x05cd:
            int r10 = r1 + r8
            boolean r11 = com.google.android.gms.internal.ads.zzhff.zzj(r15, r1, r10)
            if (r11 == 0) goto L_0x05e0
            java.lang.String r11 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.ads.zzhcb.zzb
            r11.<init>(r15, r1, r8, r14)
            r13.add(r11)
            goto L_0x05b4
        L_0x05e0:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzd()
            throw r0
        L_0x05e5:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x05ea:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzd()
            throw r0
        L_0x05ef:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzf()
            throw r0
        L_0x05f4:
            r11 = r0
            r8 = r2
            r9 = r3
            r10 = r4
            r14 = r5
            goto L_0x0836
        L_0x05fb:
            r7 = r2
            r0 = r3
            r3 = r4
            r4 = r9
            r5 = r20
            r2 = r25
            r8 = 2
            if (r1 != r8) goto L_0x062c
            com.google.android.gms.internal.ads.zzgzp r13 = (com.google.android.gms.internal.ads.zzgzp) r13
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r8 = r12.zza
            int r8 = r8 + r1
        L_0x060f:
            if (r1 >= r8) goto L_0x0623
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r1, r12)
            long r9 = r12.zzb
            int r9 = (r9 > r22 ? 1 : (r9 == r22 ? 0 : -1))
            if (r9 == 0) goto L_0x061d
            r9 = 1
            goto L_0x061f
        L_0x061d:
            r9 = r16
        L_0x061f:
            r13.zzg(r9)
            goto L_0x060f
        L_0x0623:
            if (r1 != r8) goto L_0x0627
            goto L_0x0701
        L_0x0627:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x062c:
            if (r1 != 0) goto L_0x05f4
            com.google.android.gms.internal.ads.zzgzp r13 = (com.google.android.gms.internal.ads.zzgzp) r13
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r0, r12)
            long r8 = r12.zzb
            int r8 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r8 == 0) goto L_0x063c
            r8 = 1
            goto L_0x063e
        L_0x063c:
            r8 = r16
        L_0x063e:
            r13.zzg(r8)
        L_0x0641:
            if (r1 >= r3) goto L_0x0701
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0701
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r8, r12)
            long r8 = r12.zzb
            int r8 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r8 == 0) goto L_0x0657
            r8 = 1
            goto L_0x0659
        L_0x0657:
            r8 = r16
        L_0x0659:
            r13.zzg(r8)
            goto L_0x0641
        L_0x065d:
            r7 = r2
            r0 = r3
            r3 = r4
            r4 = r9
            r5 = r20
            r2 = r25
            r8 = 2
            if (r1 != r8) goto L_0x0686
            com.google.android.gms.internal.ads.zzhbp r13 = (com.google.android.gms.internal.ads.zzhbp) r13
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r8 = r12.zza
            int r8 = r8 + r1
        L_0x0671:
            if (r1 >= r8) goto L_0x067d
            int r9 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r1)
            r13.zzi(r9)
            int r1 = r1 + 4
            goto L_0x0671
        L_0x067d:
            if (r1 != r8) goto L_0x0681
            goto L_0x0701
        L_0x0681:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x0686:
            r8 = 5
            if (r1 != r8) goto L_0x05f4
            int r1 = r0 + 4
            com.google.android.gms.internal.ads.zzhbp r13 = (com.google.android.gms.internal.ads.zzhbp) r13
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r0)
            r13.zzi(r8)
        L_0x0694:
            if (r1 >= r3) goto L_0x0701
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0701
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r8)
            r13.zzi(r1)
            int r1 = r8 + 4
            goto L_0x0694
        L_0x06a8:
            r7 = r2
            r0 = r3
            r3 = r4
            r4 = r9
            r5 = r20
            r2 = r25
            r8 = 2
            if (r1 != r8) goto L_0x06d0
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r8 = r12.zza
            int r8 = r8 + r1
        L_0x06bc:
            if (r1 >= r8) goto L_0x06c8
            long r9 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r1)
            r13.zzg(r9)
            int r1 = r1 + 8
            goto L_0x06bc
        L_0x06c8:
            if (r1 != r8) goto L_0x06cb
            goto L_0x0701
        L_0x06cb:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x06d0:
            r8 = 1
            if (r1 != r8) goto L_0x05f4
            int r1 = r0 + 8
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            long r8 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r0)
            r13.zzg(r8)
        L_0x06de:
            if (r1 >= r3) goto L_0x0701
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r1, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0701
            long r9 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r8)
            r13.zzg(r9)
            int r1 = r8 + 8
            goto L_0x06de
        L_0x06f2:
            r7 = r2
            r0 = r3
            r3 = r4
            r4 = r9
            r5 = r20
            r2 = r25
            r8 = 2
            if (r1 != r8) goto L_0x0709
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzf(r15, r0, r13, r12)
        L_0x0701:
            r11 = r0
            r0 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r14 = r5
            goto L_0x0837
        L_0x0709:
            if (r1 != 0) goto L_0x05f4
            r11 = r0
            r0 = r7
            r1 = r35
            r8 = r2
            r2 = r11
            r9 = r3
            r3 = r37
            r10 = r4
            r4 = r13
            r14 = r5
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzj(r0, r1, r2, r3, r4, r5)
            goto L_0x0837
        L_0x071f:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r0 = 2
            r9 = r4
            if (r1 != r0) goto L_0x0748
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r11, r12)
            int r1 = r12.zza
            int r1 = r1 + r0
        L_0x0733:
            if (r0 >= r1) goto L_0x073f
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r0, r12)
            long r2 = r12.zzb
            r13.zzg(r2)
            goto L_0x0733
        L_0x073f:
            if (r0 != r1) goto L_0x0743
            goto L_0x0837
        L_0x0743:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x0748:
            if (r1 != 0) goto L_0x0836
            com.google.android.gms.internal.ads.zzhct r13 = (com.google.android.gms.internal.ads.zzhct) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r11, r12)
            long r1 = r12.zzb
            r13.zzg(r1)
        L_0x0755:
            if (r0 >= r9) goto L_0x0837
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r2 = r12.zza
            if (r7 != r2) goto L_0x0837
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r1, r12)
            long r1 = r12.zzb
            r13.zzg(r1)
            goto L_0x0755
        L_0x0769:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r0 = 2
            r9 = r4
            if (r1 != r0) goto L_0x0796
            com.google.android.gms.internal.ads.zzhbf r13 = (com.google.android.gms.internal.ads.zzhbf) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r11, r12)
            int r1 = r12.zza
            int r1 = r1 + r0
        L_0x077d:
            if (r0 >= r1) goto L_0x078d
            int r2 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r0)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            r13.zzh(r2)
            int r0 = r0 + 4
            goto L_0x077d
        L_0x078d:
            if (r0 != r1) goto L_0x0791
            goto L_0x0837
        L_0x0791:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x0796:
            r0 = 5
            if (r1 != r0) goto L_0x0836
            int r3 = r11 + 4
            com.google.android.gms.internal.ads.zzhbf r13 = (com.google.android.gms.internal.ads.zzhbf) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r11)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            r13.zzh(r0)
        L_0x07a8:
            if (r3 >= r9) goto L_0x0816
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            if (r7 != r1) goto L_0x0816
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r0)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zzh(r1)
            int r3 = r0 + 4
            goto L_0x07a8
        L_0x07c0:
            r7 = r2
            r11 = r3
            r10 = r9
            r14 = r20
            r8 = r25
            r0 = 2
            r9 = r4
            if (r1 != r0) goto L_0x07ec
            com.google.android.gms.internal.ads.zzhav r13 = (com.google.android.gms.internal.ads.zzhav) r13
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r11, r12)
            int r1 = r12.zza
            int r1 = r1 + r0
        L_0x07d4:
            if (r0 >= r1) goto L_0x07e4
            long r2 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r0)
            double r2 = java.lang.Double.longBitsToDouble(r2)
            r13.zzh(r2)
            int r0 = r0 + 8
            goto L_0x07d4
        L_0x07e4:
            if (r0 != r1) goto L_0x07e7
            goto L_0x0837
        L_0x07e7:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r0
        L_0x07ec:
            r0 = 1
            if (r1 != r0) goto L_0x0836
            int r3 = r11 + 8
            com.google.android.gms.internal.ads.zzhav r13 = (com.google.android.gms.internal.ads.zzhav) r13
            long r0 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r11)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            r13.zzh(r0)
        L_0x07fe:
            if (r3 >= r9) goto L_0x0816
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r3, r12)
            int r1 = r12.zza
            if (r7 != r1) goto L_0x0816
            long r1 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r0)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            r13.zzh(r1)
            int r3 = r0 + 8
            goto L_0x07fe
        L_0x0816:
            r0 = r3
            goto L_0x0837
        L_0x0818:
            if (r0 >= r9) goto L_0x0837
            int r2 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r0, r12)
            int r1 = r12.zza
            if (r7 != r1) goto L_0x0837
            r0 = r21
            r1 = r35
            r3 = r37
            r4 = r20
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzc(r0, r1, r2, r3, r4, r5)
            java.lang.Object r1 = r12.zzc
            r13.add(r1)
            goto L_0x0818
        L_0x0836:
            r0 = r11
        L_0x0837:
            if (r0 == r11) goto L_0x084a
            r13 = r38
            r3 = r7
            r1 = r8
            r2 = r10
            r11 = r14
            r10 = r17
            r4 = r19
            r5 = r24
            r7 = r34
            r14 = r9
            goto L_0x001e
        L_0x084a:
            r2 = r0
            r3 = r7
            r11 = r8
            r6 = r10
            r8 = r12
            r20 = r14
            r7 = r34
            r10 = r9
            r9 = r38
            goto L_0x0afe
        L_0x0858:
            r7 = r2
            r2 = r9
            r5 = r27
            r9 = r8
            r8 = r11
            r11 = r3
            r3 = 50
            if (r0 != r3) goto L_0x0895
            r3 = 2
            if (r1 != r3) goto L_0x0889
            sun.misc.Unsafe r0 = zzb
            java.lang.Object r1 = r6.zzz(r2)
            r7 = r34
            java.lang.Object r2 = r0.getObject(r7, r13)
            boolean r3 = com.google.android.gms.internal.ads.zzhcz.zza(r2)
            if (r3 == 0) goto L_0x0886
            com.google.android.gms.internal.ads.zzhcy r3 = com.google.android.gms.internal.ads.zzhcy.zza()
            com.google.android.gms.internal.ads.zzhcy r3 = r3.zzb()
            com.google.android.gms.internal.ads.zzhcz.zzb(r3, r2)
            r0.putObject(r7, r13, r3)
        L_0x0886:
            com.google.android.gms.internal.ads.zzhcx r1 = (com.google.android.gms.internal.ads.zzhcx) r1
            throw r18
        L_0x0889:
            r3 = r7
            r7 = r34
        L_0x088c:
            r9 = r38
            r6 = r2
            r10 = r4
            r2 = r11
            r11 = r8
            r8 = r12
            goto L_0x0afe
        L_0x0895:
            r3 = r7
            r7 = r34
            int r21 = r2 + 2
            sun.misc.Unsafe r4 = zzb
            r10 = r10[r21]
            r27 = r5
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r10 & r5
            long r5 = (long) r10
            switch(r0) {
                case 51: goto L_0x0ac1;
                case 52: goto L_0x0a9f;
                case 53: goto L_0x0a82;
                case 54: goto L_0x0a82;
                case 55: goto L_0x0a64;
                case 56: goto L_0x0a45;
                case 57: goto L_0x0a26;
                case 58: goto L_0x09ff;
                case 59: goto L_0x09b7;
                case 60: goto L_0x0984;
                case 61: goto L_0x095e;
                case 62: goto L_0x0a64;
                case 63: goto L_0x0928;
                case 64: goto L_0x0a26;
                case 65: goto L_0x0a45;
                case 66: goto L_0x0908;
                case 67: goto L_0x08e3;
                case 68: goto L_0x08b4;
                default: goto L_0x08a8;
            }
        L_0x08a8:
            r10 = r37
            r36 = r2
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            goto L_0x0ae3
        L_0x08b4:
            r0 = 3
            if (r1 != r0) goto L_0x08e0
            r0 = r3 & -8
            r13 = r0 | 4
            r6 = r33
            java.lang.Object r0 = r6.zzB(r7, r8, r2)
            com.google.android.gms.internal.ads.zzhdz r9 = r6.zzx(r2)
            r5 = r8
            r8 = r0
            r10 = r35
            r1 = r11
            r4 = r12
            r12 = r37
            r14 = r39
            int r8 = com.google.android.gms.internal.ads.zzgzo.zzl(r8, r9, r10, r11, r12, r13, r14)
            r6.zzK(r7, r5, r2, r0)
            r10 = r37
            r12 = r1
            r36 = r2
            r11 = r5
            r0 = r8
            r8 = r4
            goto L_0x0ae4
        L_0x08e0:
            r6 = r33
            goto L_0x08a8
        L_0x08e3:
            r31 = r5
            r6 = r33
            r5 = r8
            r8 = r12
            r12 = r11
            r10 = r31
            if (r1 != 0) goto L_0x097d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r12, r8)
            r36 = r0
            long r0 = r8.zzb
            long r0 = com.google.android.gms.internal.ads.zzham.zzH(r0)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r4.putObject(r7, r13, r0)
            r4.putInt(r7, r10, r5)
            r0 = r36
            goto L_0x0976
        L_0x0908:
            r31 = r5
            r6 = r33
            r5 = r8
            r8 = r12
            r12 = r11
            r10 = r31
            if (r1 != 0) goto L_0x097d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r12, r8)
            int r1 = r8.zza
            int r1 = com.google.android.gms.internal.ads.zzham.zzF(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r10, r5)
            goto L_0x0976
        L_0x0928:
            r31 = r5
            r6 = r33
            r5 = r8
            r8 = r12
            r12 = r11
            r10 = r31
            if (r1 != 0) goto L_0x097d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r12, r8)
            int r1 = r8.zza
            com.google.android.gms.internal.ads.zzhbu r9 = r6.zzw(r2)
            if (r9 == 0) goto L_0x0953
            boolean r9 = r9.zza(r1)
            if (r9 == 0) goto L_0x0946
            goto L_0x0953
        L_0x0946:
            com.google.android.gms.internal.ads.zzher r4 = zzd(r34)
            long r9 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r9)
            r4.zzj(r3, r1)
            goto L_0x0976
        L_0x0953:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r10, r5)
            goto L_0x0976
        L_0x095e:
            r0 = 2
            r31 = r5
            r6 = r33
            r5 = r8
            r8 = r12
            r12 = r11
            r10 = r31
            if (r1 != r0) goto L_0x097d
            int r0 = com.google.android.gms.internal.ads.zzgzo.zza(r15, r12, r8)
            java.lang.Object r1 = r8.zzc
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r10, r5)
        L_0x0976:
            r10 = r37
            r36 = r2
            r11 = r5
            goto L_0x0ae4
        L_0x097d:
            r10 = r37
            r36 = r2
            r11 = r5
            goto L_0x0ae3
        L_0x0984:
            r6 = r33
            r5 = r8
            r8 = r12
            r0 = 2
            r12 = r11
            if (r1 != r0) goto L_0x09b0
            java.lang.Object r9 = r6.zzB(r7, r5, r2)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r2)
            r0 = r9
            r10 = r2
            r2 = r35
            r13 = r3
            r11 = r20
            r3 = r12
            r14 = r37
            r4 = r37
            r11 = r5
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzm(r0, r1, r2, r3, r4, r5)
            r6.zzK(r7, r11, r10, r9)
            r36 = r10
            r3 = r13
            r10 = r14
            goto L_0x0ae4
        L_0x09b0:
            r11 = r5
            r10 = r37
            r36 = r2
            goto L_0x0ae3
        L_0x09b7:
            r10 = r37
            r0 = r2
            r2 = 2
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != r2) goto L_0x09fb
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r12, r8)
            int r2 = r8.zza
            if (r2 != 0) goto L_0x09d1
            r4.putObject(r7, r13, r9)
            r36 = r0
            goto L_0x09f5
        L_0x09d1:
            int r9 = r1 + r2
            r21 = 536870912(0x20000000, float:1.0842022E-19)
            r21 = r27 & r21
            if (r21 == 0) goto L_0x09e5
            boolean r21 = com.google.android.gms.internal.ads.zzhff.zzj(r15, r1, r9)
            if (r21 == 0) goto L_0x09e0
            goto L_0x09e5
        L_0x09e0:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzd()
            throw r0
        L_0x09e5:
            r36 = r0
            java.lang.String r0 = new java.lang.String
            r21 = r9
            java.nio.charset.Charset r9 = com.google.android.gms.internal.ads.zzhcb.zzb
            r0.<init>(r15, r1, r2, r9)
            r4.putObject(r7, r13, r0)
            r1 = r21
        L_0x09f5:
            r4.putInt(r7, r5, r11)
            r0 = r1
            goto L_0x0ae4
        L_0x09fb:
            r36 = r0
            goto L_0x0ae3
        L_0x09ff:
            r10 = r37
            r36 = r2
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != 0) goto L_0x0ae3
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r12, r8)
            long r1 = r8.zzb
            int r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x0a18
            r26 = 1
            goto L_0x0a1a
        L_0x0a18:
            r26 = r16
        L_0x0a1a:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r26)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0a26:
            r10 = r37
            r36 = r2
            r0 = 5
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != r0) goto L_0x0ae3
            int r0 = r12 + 4
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r12)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0a45:
            r10 = r37
            r36 = r2
            r0 = 1
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != r0) goto L_0x0ae3
            int r0 = r12 + 8
            long r1 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r12)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0a64:
            r10 = r37
            r36 = r2
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != 0) goto L_0x0ae3
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzh(r15, r12, r8)
            int r1 = r8.zza
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0a82:
            r10 = r37
            r36 = r2
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != 0) goto L_0x0ae3
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzk(r15, r12, r8)
            long r1 = r8.zzb
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0a9f:
            r10 = r37
            r36 = r2
            r0 = 5
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != r0) goto L_0x0ae3
            int r0 = r12 + 4
            int r1 = com.google.android.gms.internal.ads.zzgzo.zzb(r15, r12)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0ac1:
            r10 = r37
            r36 = r2
            r0 = 1
            r31 = r11
            r11 = r8
            r8 = r12
            r12 = r31
            if (r1 != r0) goto L_0x0ae3
            int r0 = r12 + 8
            long r1 = com.google.android.gms.internal.ads.zzgzo.zzn(r15, r12)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r4.putObject(r7, r13, r1)
            r4.putInt(r7, r5, r11)
            goto L_0x0ae4
        L_0x0ae3:
            r0 = r12
        L_0x0ae4:
            if (r0 == r12) goto L_0x0af9
            r6 = r33
            r2 = r36
            r13 = r38
            r12 = r8
            r14 = r10
            r1 = r11
            r10 = r17
            r4 = r19
            r11 = r20
            r5 = r24
            goto L_0x001e
        L_0x0af9:
            r6 = r36
            r9 = r38
            r2 = r0
        L_0x0afe:
            if (r3 != r9) goto L_0x0b11
            if (r9 == 0) goto L_0x0b11
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r33
            r6 = r2
            r8 = r3
            r4 = r19
            r14 = r20
            r5 = r24
            goto L_0x0b6d
        L_0x0b11:
            r12 = r33
            boolean r0 = r12.zzh
            if (r0 == 0) goto L_0x0b3e
            com.google.android.gms.internal.ads.zzhay r0 = r8.zzd
            com.google.android.gms.internal.ads.zzhay r1 = com.google.android.gms.internal.ads.zzhay.zza
            if (r0 == r1) goto L_0x0b3e
            com.google.android.gms.internal.ads.zzhde r0 = r12.zzg
            com.google.android.gms.internal.ads.zzhay r1 = r8.zzd
            com.google.android.gms.internal.ads.zzhbm r0 = r1.zzc(r0, r11)
            if (r0 != 0) goto L_0x0b3a
            com.google.android.gms.internal.ads.zzher r4 = zzd(r34)
            r0 = r3
            r1 = r35
            r13 = r3
            r3 = r37
            r14 = r20
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzg(r0, r1, r2, r3, r4, r5)
            goto L_0x0b50
        L_0x0b3a:
            r0 = r7
            com.google.android.gms.internal.ads.zzhbk r0 = (com.google.android.gms.internal.ads.zzhbk) r0
            throw r18
        L_0x0b3e:
            r13 = r3
            r14 = r20
            com.google.android.gms.internal.ads.zzher r4 = zzd(r34)
            r0 = r13
            r1 = r35
            r3 = r37
            r5 = r39
            int r0 = com.google.android.gms.internal.ads.zzgzo.zzg(r0, r1, r2, r3, r4, r5)
        L_0x0b50:
            r2 = r6
            r1 = r11
            r6 = r12
            r3 = r13
            r11 = r14
            r4 = r19
            r5 = r24
            r12 = r8
            r13 = r9
            r14 = r10
            r10 = r17
            goto L_0x001e
        L_0x0b60:
            r19 = r4
            r24 = r5
            r12 = r6
            r9 = r13
            r10 = r14
            r14 = r11
            r6 = r0
            r8 = r3
            r0 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0b6d:
            if (r5 == r0) goto L_0x0b73
            long r0 = (long) r5
            r14.putInt(r7, r0, r4)
        L_0x0b73:
            int r0 = r12.zzk
            r11 = r0
        L_0x0b76:
            int r0 = r12.zzl
            if (r11 >= r0) goto L_0x0b8d
            int[] r0 = r12.zzj
            com.google.android.gms.internal.ads.zzheq r4 = r12.zzn
            r2 = r0[r11]
            r3 = 0
            r0 = r33
            r1 = r34
            r5 = r34
            r0.zzy(r1, r2, r3, r4, r5)
            int r11 = r11 + 1
            goto L_0x0b76
        L_0x0b8d:
            if (r9 != 0) goto L_0x0b97
            if (r6 != r10) goto L_0x0b92
            goto L_0x0b9b
        L_0x0b92:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzg()
            throw r0
        L_0x0b97:
            if (r6 > r10) goto L_0x0b9c
            if (r8 != r9) goto L_0x0b9c
        L_0x0b9b:
            return r6
        L_0x0b9c:
            com.google.android.gms.internal.ads.zzhcd r0 = com.google.android.gms.internal.ads.zzhcd.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.zzgzn):int");
    }

    public final Object zze() {
        return ((zzhbo) this.zzg).zzbj();
    }

    public final void zzf(Object obj) {
        if (zzQ(obj)) {
            if (obj instanceof zzhbo) {
                zzhbo zzhbo = (zzhbo) obj;
                zzhbo.zzbU();
                zzhbo.zzbT();
                zzhbo.zzbW();
            }
            int[] iArr = this.zzc;
            for (int i = 0; i < iArr.length; i += 3) {
                int zzu = zzu(i);
                int i2 = 1048575 & zzu;
                int zzt = zzt(zzu);
                long j = (long) i2;
                if (zzt != 9) {
                    if (zzt == 60 || zzt == 68) {
                        if (zzR(obj, this.zzc[i], i)) {
                            zzx(i).zzf(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzt) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                            case 49:
                                this.zzm.zzb(obj, j);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzhcy) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzN(obj, i)) {
                    zzx(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzm(obj);
            if (this.zzh) {
                this.zzo.zzf(obj);
            }
        }
    }

    public final void zzg(Object obj, Object obj2) {
        zzD(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzu = zzu(i);
            int i2 = 1048575 & zzu;
            int[] iArr = this.zzc;
            int zzt = zzt(zzu);
            int i3 = iArr[i];
            long j = (long) i2;
            switch (zzt) {
                case 0:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzr(obj, j, zzhfa.zzb(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 1:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzs(obj, j, zzhfa.zzc(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 2:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzu(obj, j, zzhfa.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 3:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzu(obj, j, zzhfa.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 4:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 5:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzu(obj, j, zzhfa.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 6:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 7:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzp(obj, j, zzhfa.zzz(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 8:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzv(obj, j, zzhfa.zzh(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 9:
                    zzE(obj, obj2, i);
                    break;
                case 10:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzv(obj, j, zzhfa.zzh(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 11:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 12:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 13:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 14:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzu(obj, j, zzhfa.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 15:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzt(obj, j, zzhfa.zzd(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 16:
                    if (!zzN(obj2, i)) {
                        break;
                    } else {
                        zzhfa.zzu(obj, j, zzhfa.zzf(obj2, j));
                        zzH(obj, i);
                        break;
                    }
                case 17:
                    zzE(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                case 49:
                    this.zzm.zzc(obj, obj2, j);
                    break;
                case 50:
                    int i4 = zzheb.zza;
                    zzhfa.zzv(obj, j, zzhcz.zzb(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j)));
                    break;
                case 51:
                case 52:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                case 54:
                case 55:
                case Opcodes.FSTORE:
                case Opcodes.DSTORE:
                case Opcodes.ASTORE:
                case 59:
                    if (!zzR(obj2, i3, i)) {
                        break;
                    } else {
                        zzhfa.zzv(obj, j, zzhfa.zzh(obj2, j));
                        zzI(obj, i3, i);
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT:
                    zzF(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case 66:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL:
                    if (!zzR(obj2, i3, i)) {
                        break;
                    } else {
                        zzhfa.zzv(obj, j, zzhfa.zzh(obj2, j));
                        zzI(obj, i3, i);
                        break;
                    }
                case 68:
                    zzF(obj, obj2, i);
                    break;
            }
        }
        zzheb.zzr(this.zzn, obj, obj2);
        if (this.zzh) {
            zzheb.zzq(this.zzo, obj, obj2);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:161|162|(1:164)|165|(4:185|167|(2:170|168)|195)) */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x05de, code lost:
        r15 = r9;
        r5 = r11;
        r6 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x05e1, code lost:
        r14 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0602, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:?, code lost:
        r10.zzs(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x060a, code lost:
        if (r13 == null) goto L_0x060c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x060c, code lost:
        r13 = r10.zzc(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0615, code lost:
        if (r10.zzr(r13, r0) == false) goto L_0x0617;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0617, code lost:
        r0 = r7.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x061b, code lost:
        if (r0 < r7.zzl) goto L_0x061d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x061d, code lost:
        zzy(r18, r7.zzj[r0], r13, r10, r18);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0642, code lost:
        zzy(r18, r7.zzj[r8], r13, r10, r18);
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0656, code lost:
        r10.zzn(r9, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x017f, code lost:
        r13 = r4;
        r11 = r5;
        r14 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:161:0x0607 */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0642 A[LOOP:5: B:178:0x063e->B:180:0x0642, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0656  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(java.lang.Object r18, com.google.android.gms.internal.ads.zzhdr r19, com.google.android.gms.internal.ads.zzhay r20) throws java.io.IOException {
        /*
            r17 = this;
            r7 = r17
            r15 = r18
            r0 = r19
            r6 = r20
            r20.getClass()
            zzD(r18)
            com.google.android.gms.internal.ads.zzheq r14 = r7.zzn
            com.google.android.gms.internal.ads.zzhaz r5 = r7.zzo
            r16 = 0
            r8 = r16
            r13 = r8
        L_0x0017:
            int r2 = r19.zzc()     // Catch:{ all -> 0x0638 }
            int r1 = r7.zzq(r2)     // Catch:{ all -> 0x0638 }
            if (r1 >= 0) goto L_0x00b1
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r1) goto L_0x0042
            int r0 = r7.zzk
        L_0x0028:
            int r1 = r7.zzl
            if (r0 >= r1) goto L_0x003e
            int[] r1 = r7.zzj
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r4 = r13
            r5 = r14
            r6 = r18
            r1.zzy(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x0028
        L_0x003e:
            r10 = r14
            r9 = r15
            goto L_0x062f
        L_0x0042:
            boolean r1 = r7.zzh     // Catch:{ all -> 0x00ad }
            if (r1 != 0) goto L_0x0049
            r11 = r16
            goto L_0x0050
        L_0x0049:
            com.google.android.gms.internal.ads.zzhde r1 = r7.zzg     // Catch:{ all -> 0x00ad }
            java.lang.Object r1 = r5.zzd(r6, r1, r2)     // Catch:{ all -> 0x00ad }
            r11 = r1
        L_0x0050:
            if (r11 == 0) goto L_0x006e
            if (r8 != 0) goto L_0x0058
            com.google.android.gms.internal.ads.zzhbd r8 = r5.zzc(r15)     // Catch:{ all -> 0x0638 }
        L_0x0058:
            r1 = r8
            r8 = r5
            r9 = r18
            r10 = r19
            r12 = r20
            r4 = r13
            r13 = r1
            r3 = r14
            r14 = r4
            r2 = r15
            r15 = r3
            java.lang.Object r13 = r8.zze(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x00a8 }
            r8 = r1
        L_0x006b:
            r15 = r2
            r14 = r3
            goto L_0x0017
        L_0x006e:
            r4 = r13
            r3 = r14
            r2 = r15
            r3.zzs(r0)     // Catch:{ all -> 0x00a8 }
            if (r4 != 0) goto L_0x007b
            java.lang.Object r13 = r3.zzc(r2)     // Catch:{ all -> 0x00a8 }
            goto L_0x007c
        L_0x007b:
            r13 = r4
        L_0x007c:
            boolean r1 = r3.zzr(r13, r0)     // Catch:{ all -> 0x00a3 }
            if (r1 != 0) goto L_0x006b
            int r0 = r7.zzk
        L_0x0084:
            int r1 = r7.zzl
            if (r0 >= r1) goto L_0x009f
            int[] r1 = r7.zzj
            r4 = r1[r0]
            r1 = r17
            r9 = r2
            r2 = r18
            r10 = r3
            r3 = r4
            r4 = r13
            r5 = r10
            r6 = r18
            r1.zzy(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            r2 = r9
            r3 = r10
            goto L_0x0084
        L_0x009f:
            r9 = r2
            r10 = r3
            goto L_0x062f
        L_0x00a3:
            r0 = move-exception
            r9 = r2
            r10 = r3
            goto L_0x063b
        L_0x00a8:
            r0 = move-exception
            r9 = r2
            r10 = r3
            goto L_0x0636
        L_0x00ad:
            r0 = move-exception
            r4 = r13
            goto L_0x0639
        L_0x00b1:
            r4 = r13
            r10 = r14
            r9 = r15
            int r3 = r7.zzu(r1)     // Catch:{ all -> 0x0635 }
            int r11 = zzt(r3)     // Catch:{ zzhcc -> 0x0604 }
            r12 = 1048575(0xfffff, float:1.469367E-39)
            switch(r11) {
                case 0: goto L_0x05ce;
                case 1: goto L_0x05bd;
                case 2: goto L_0x05ac;
                case 3: goto L_0x059b;
                case 4: goto L_0x058a;
                case 5: goto L_0x0579;
                case 6: goto L_0x0567;
                case 7: goto L_0x0555;
                case 8: goto L_0x054a;
                case 9: goto L_0x0535;
                case 10: goto L_0x0523;
                case 11: goto L_0x0511;
                case 12: goto L_0x04ec;
                case 13: goto L_0x04da;
                case 14: goto L_0x04c8;
                case 15: goto L_0x04b6;
                case 16: goto L_0x04a4;
                case 17: goto L_0x048f;
                case 18: goto L_0x047e;
                case 19: goto L_0x046d;
                case 20: goto L_0x045c;
                case 21: goto L_0x044b;
                case 22: goto L_0x043a;
                case 23: goto L_0x0429;
                case 24: goto L_0x0418;
                case 25: goto L_0x0407;
                case 26: goto L_0x03da;
                case 27: goto L_0x03c5;
                case 28: goto L_0x03b4;
                case 29: goto L_0x03a3;
                case 30: goto L_0x0387;
                case 31: goto L_0x0376;
                case 32: goto L_0x0365;
                case 33: goto L_0x0354;
                case 34: goto L_0x0343;
                case 35: goto L_0x0332;
                case 36: goto L_0x0321;
                case 37: goto L_0x0310;
                case 38: goto L_0x02ff;
                case 39: goto L_0x02ee;
                case 40: goto L_0x02dd;
                case 41: goto L_0x02cc;
                case 42: goto L_0x02bb;
                case 43: goto L_0x02aa;
                case 44: goto L_0x028d;
                case 45: goto L_0x027f;
                case 46: goto L_0x0271;
                case 47: goto L_0x0263;
                case 48: goto L_0x0255;
                case 49: goto L_0x0243;
                case 50: goto L_0x020d;
                case 51: goto L_0x01fb;
                case 52: goto L_0x01ea;
                case 53: goto L_0x01d9;
                case 54: goto L_0x01c8;
                case 55: goto L_0x01b7;
                case 56: goto L_0x01a6;
                case 57: goto L_0x0195;
                case 58: goto L_0x0184;
                case 59: goto L_0x0179;
                case 60: goto L_0x0168;
                case 61: goto L_0x015b;
                case 62: goto L_0x014a;
                case 63: goto L_0x0125;
                case 64: goto L_0x0114;
                case 65: goto L_0x0103;
                case 66: goto L_0x00f1;
                case 67: goto L_0x00df;
                case 68: goto L_0x00cd;
                default: goto L_0x00c2;
            }
        L_0x00c2:
            r13 = r4
            r11 = r5
            r14 = r6
            if (r13 != 0) goto L_0x05e4
            java.lang.Object r13 = r10.zzc(r9)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05e4
        L_0x00cd:
            java.lang.Object r3 = r7.zzB(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhde r3 = (com.google.android.gms.internal.ads.zzhde) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhdz r11 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzv(r3, r11, r6)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzK(r9, r2, r1, r3)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x00df:
            r3 = r3 & r12
            long r11 = r19.zzn()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x00f1:
            r3 = r3 & r12
            int r11 = r19.zzi()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0103:
            r3 = r3 & r12
            long r11 = r19.zzm()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0114:
            r3 = r3 & r12
            int r11 = r19.zzh()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0125:
            int r11 = r19.zze()     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhbu r13 = r7.zzw(r1)     // Catch:{ zzhcc -> 0x0604 }
            if (r13 == 0) goto L_0x013d
            boolean r13 = r13.zza(r11)     // Catch:{ zzhcc -> 0x0604 }
            if (r13 == 0) goto L_0x0136
            goto L_0x013d
        L_0x0136:
            java.lang.Object r13 = com.google.android.gms.internal.ads.zzheb.zzp(r9, r2, r11, r4, r10)     // Catch:{ zzhcc -> 0x0604 }
            r15 = r9
            goto L_0x05e1
        L_0x013d:
            r3 = r3 & r12
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x014a:
            r3 = r3 & r12
            int r11 = r19.zzj()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x015b:
            r3 = r3 & r12
            com.google.android.gms.internal.ads.zzhac r11 = r19.zzp()     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0168:
            java.lang.Object r3 = r7.zzB(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhde r3 = (com.google.android.gms.internal.ads.zzhde) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhdz r11 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzw(r3, r11, r6)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzK(r9, r2, r1, r3)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0179:
            r7.zzG(r9, r3, r0)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
        L_0x017f:
            r13 = r4
            r11 = r5
            r14 = r6
            goto L_0x05de
        L_0x0184:
            r3 = r3 & r12
            boolean r11 = r19.zzP()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0195:
            r3 = r3 & r12
            int r11 = r19.zzf()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01a6:
            r3 = r3 & r12
            long r11 = r19.zzk()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01b7:
            r3 = r3 & r12
            int r11 = r19.zzg()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01c8:
            r3 = r3 & r12
            long r11 = r19.zzo()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01d9:
            r3 = r3 & r12
            long r11 = r19.zzl()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01ea:
            r3 = r3 & r12
            float r11 = r19.zzb()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Float r11 = java.lang.Float.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x01fb:
            r3 = r3 & r12
            double r11 = r19.zza()     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Double r11 = java.lang.Double.valueOf(r11)     // Catch:{ zzhcc -> 0x0604 }
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r12, r11)     // Catch:{ zzhcc -> 0x0604 }
            r7.zzI(r9, r2, r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x020d:
            java.lang.Object r2 = r7.zzz(r1)     // Catch:{ zzhcc -> 0x0604 }
            int r1 = r7.zzu(r1)     // Catch:{ zzhcc -> 0x0604 }
            r1 = r1 & r12
            long r11 = (long) r1     // Catch:{ zzhcc -> 0x0604 }
            java.lang.Object r1 = com.google.android.gms.internal.ads.zzhfa.zzh(r9, r11)     // Catch:{ zzhcc -> 0x0604 }
            if (r1 == 0) goto L_0x0233
            boolean r3 = com.google.android.gms.internal.ads.zzhcz.zza(r1)     // Catch:{ zzhcc -> 0x0604 }
            if (r3 == 0) goto L_0x023e
            com.google.android.gms.internal.ads.zzhcy r3 = com.google.android.gms.internal.ads.zzhcy.zza()     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhcy r3 = r3.zzb()     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhcz.zzb(r3, r1)     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r11, r3)     // Catch:{ zzhcc -> 0x0604 }
            r1 = r3
            goto L_0x023e
        L_0x0233:
            com.google.android.gms.internal.ads.zzhcy r1 = com.google.android.gms.internal.ads.zzhcy.zza()     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhcy r1 = r1.zzb()     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r11, r1)     // Catch:{ zzhcc -> 0x0604 }
        L_0x023e:
            com.google.android.gms.internal.ads.zzhcy r1 = (com.google.android.gms.internal.ads.zzhcy) r1     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhcx r2 = (com.google.android.gms.internal.ads.zzhcx) r2     // Catch:{ zzhcc -> 0x0604 }
            throw r16     // Catch:{ zzhcc -> 0x0604 }
        L_0x0243:
            r2 = r3 & r12
            com.google.android.gms.internal.ads.zzhdz r1 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhcs r3 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            long r11 = (long) r2     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r2 = r3.zza(r9, r11)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzE(r2, r1, r6)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0255:
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzL(r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0263:
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzK(r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x0271:
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzJ(r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x027f:
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzI(r1)     // Catch:{ zzhcc -> 0x0604 }
            goto L_0x017f
        L_0x028d:
            com.google.android.gms.internal.ads.zzhcs r11 = r7.zzm     // Catch:{ zzhcc -> 0x0604 }
            r3 = r3 & r12
            long r12 = (long) r3     // Catch:{ zzhcc -> 0x0604 }
            java.util.List r3 = r11.zza(r9, r12)     // Catch:{ zzhcc -> 0x0604 }
            r0.zzA(r3)     // Catch:{ zzhcc -> 0x0604 }
            com.google.android.gms.internal.ads.zzhbu r11 = r7.zzw(r1)     // Catch:{ zzhcc -> 0x0604 }
            r1 = r18
            r13 = r4
            r4 = r11
            r11 = r5
            r5 = r13
            r14 = r6
            r6 = r10
            java.lang.Object r13 = com.google.android.gms.internal.ads.zzheb.zzo(r1, r2, r3, r4, r5, r6)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02aa:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzN(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02bb:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzx(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02cc:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzB(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02dd:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzC(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02ee:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzF(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x02ff:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzO(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0310:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzG(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0321:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzD(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0332:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzz(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0343:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzL(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0354:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzK(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0365:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzJ(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0376:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzI(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0387:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r4 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r3 = r3 & r12
            long r5 = (long) r3     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r3 = r4.zza(r9, r5)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzA(r3)     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhbu r4 = r7.zzw(r1)     // Catch:{ zzhcc -> 0x0607 }
            r1 = r18
            r5 = r13
            r6 = r10
            java.lang.Object r13 = com.google.android.gms.internal.ads.zzheb.zzo(r1, r2, r3, r4, r5, r6)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x03a3:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzN(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x03b4:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzy(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x03c5:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhdz r1 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            com.google.android.gms.internal.ads.zzhcs r3 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r2 = r3.zza(r9, r4)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzH(r2, r1, r14)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x03da:
            r13 = r4
            r11 = r5
            r14 = r6
            boolean r1 = zzM(r3)     // Catch:{ zzhcc -> 0x0607 }
            if (r1 == 0) goto L_0x03f5
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r2 = r0
            com.google.android.gms.internal.ads.zzhan r2 = (com.google.android.gms.internal.ads.zzhan) r2     // Catch:{ zzhcc -> 0x0607 }
            r3 = 1
            r2.zzM(r1, r3)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x03f5:
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r2 = r0
            com.google.android.gms.internal.ads.zzhan r2 = (com.google.android.gms.internal.ads.zzhan) r2     // Catch:{ zzhcc -> 0x0607 }
            r3 = 0
            r2.zzM(r1, r3)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0407:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzx(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0418:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzB(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0429:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzC(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x043a:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzF(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x044b:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzO(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x045c:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzG(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x046d:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzD(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x047e:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.ads.zzhcs r1 = r7.zzm     // Catch:{ zzhcc -> 0x0607 }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzz(r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x048f:
            r13 = r4
            r11 = r5
            r14 = r6
            java.lang.Object r2 = r7.zzA(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhde r2 = (com.google.android.gms.internal.ads.zzhde) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhdz r3 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzv(r2, r3, r14)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzJ(r9, r1, r2)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x04a4:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r3 = r19.zzn()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzu(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x04b6:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            int r3 = r19.zzi()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x04c8:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r3 = r19.zzm()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzu(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x04da:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            int r3 = r19.zzh()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x04ec:
            r13 = r4
            r11 = r5
            r14 = r6
            int r4 = r19.zze()     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhbu r5 = r7.zzw(r1)     // Catch:{ zzhcc -> 0x0607 }
            if (r5 == 0) goto L_0x0506
            boolean r5 = r5.zza(r4)     // Catch:{ zzhcc -> 0x0607 }
            if (r5 == 0) goto L_0x0500
            goto L_0x0506
        L_0x0500:
            java.lang.Object r13 = com.google.android.gms.internal.ads.zzheb.zzp(r9, r2, r4, r13, r10)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0506:
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r2, r4)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0511:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            int r3 = r19.zzj()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0523:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            com.google.android.gms.internal.ads.zzhac r3 = r19.zzp()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzv(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0535:
            r13 = r4
            r11 = r5
            r14 = r6
            java.lang.Object r2 = r7.zzA(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhde r2 = (com.google.android.gms.internal.ads.zzhde) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhdz r3 = r7.zzx(r1)     // Catch:{ zzhcc -> 0x0607 }
            r0.zzw(r2, r3, r14)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzJ(r9, r1, r2)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x054a:
            r13 = r4
            r11 = r5
            r14 = r6
            r7.zzG(r9, r3, r0)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0555:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            boolean r3 = r19.zzP()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzp(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0567:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            int r3 = r19.zzf()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x0579:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r3 = r19.zzk()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzu(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x058a:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            int r3 = r19.zzg()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzt(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x059b:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r3 = r19.zzo()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzu(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x05ac:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r3 = r19.zzl()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzu(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x05bd:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            float r3 = r19.zzb()     // Catch:{ zzhcc -> 0x0607 }
            long r4 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzs(r9, r4, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
            goto L_0x05de
        L_0x05ce:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            double r3 = r19.zza()     // Catch:{ zzhcc -> 0x0607 }
            long r5 = (long) r2     // Catch:{ zzhcc -> 0x0607 }
            com.google.android.gms.internal.ads.zzhfa.zzr(r9, r5, r3)     // Catch:{ zzhcc -> 0x0607 }
            r7.zzH(r9, r1)     // Catch:{ zzhcc -> 0x0607 }
        L_0x05de:
            r15 = r9
            r5 = r11
            r6 = r14
        L_0x05e1:
            r14 = r10
            goto L_0x0017
        L_0x05e4:
            boolean r1 = r10.zzr(r13, r0)     // Catch:{ zzhcc -> 0x0607 }
            if (r1 != 0) goto L_0x05de
            int r0 = r7.zzk
        L_0x05ec:
            int r1 = r7.zzl
            if (r0 >= r1) goto L_0x062f
            int[] r1 = r7.zzj
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r4 = r13
            r5 = r10
            r6 = r18
            r1.zzy(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x05ec
        L_0x0602:
            r0 = move-exception
            goto L_0x063b
        L_0x0604:
            r13 = r4
            r11 = r5
            r14 = r6
        L_0x0607:
            r10.zzs(r0)     // Catch:{ all -> 0x0602 }
            if (r13 != 0) goto L_0x0611
            java.lang.Object r1 = r10.zzc(r9)     // Catch:{ all -> 0x0602 }
            r13 = r1
        L_0x0611:
            boolean r1 = r10.zzr(r13, r0)     // Catch:{ all -> 0x0602 }
            if (r1 != 0) goto L_0x05de
            int r0 = r7.zzk
        L_0x0619:
            int r1 = r7.zzl
            if (r0 >= r1) goto L_0x062f
            int[] r1 = r7.zzj
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r4 = r13
            r5 = r10
            r6 = r18
            r1.zzy(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x0619
        L_0x062f:
            if (r13 == 0) goto L_0x0634
            r10.zzn(r9, r13)
        L_0x0634:
            return
        L_0x0635:
            r0 = move-exception
        L_0x0636:
            r13 = r4
            goto L_0x063b
        L_0x0638:
            r0 = move-exception
        L_0x0639:
            r10 = r14
            r9 = r15
        L_0x063b:
            int r1 = r7.zzk
            r8 = r1
        L_0x063e:
            int r1 = r7.zzl
            if (r8 >= r1) goto L_0x0654
            int[] r1 = r7.zzj
            r3 = r1[r8]
            r1 = r17
            r2 = r18
            r4 = r13
            r5 = r10
            r6 = r18
            r1.zzy(r2, r3, r4, r5, r6)
            int r8 = r8 + 1
            goto L_0x063e
        L_0x0654:
            if (r13 == 0) goto L_0x0659
            r10.zzn(r9, r13)
        L_0x0659:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zzh(java.lang.Object, com.google.android.gms.internal.ads.zzhdr, com.google.android.gms.internal.ads.zzhay):void");
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzgzn zzgzn) throws IOException {
        zzc(obj, bArr, i, i2, 0, zzgzn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.util.Map$Entry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v173, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: java.util.Map$Entry} */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0337, code lost:
        r22 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x036e, code lost:
        r16 = r10;
        r19 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x03fc, code lost:
        r22 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03fe, code lost:
        r16 = r10;
        r19 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x066f, code lost:
        r15 = r15 + 3;
        r0 = r9;
        r1 = r13;
        r10 = r16;
        r11 = r19;
        r2 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0096, code lost:
        r16 = r10;
        r19 = r11;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0682  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzj(java.lang.Object r24, com.google.android.gms.internal.ads.zzhfi r25) throws java.io.IOException {
        /*
            r23 = this;
            r6 = r23
            r7 = r24
            r8 = r25
            boolean r0 = r6.zzh
            if (r0 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzhaz r0 = r6.zzo
            com.google.android.gms.internal.ads.zzhbd r0 = r0.zzb(r7)
            com.google.android.gms.internal.ads.zzhem r1 = r0.zza
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0024
            java.util.Iterator r0 = r0.zzg()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r10 = r0
            goto L_0x0026
        L_0x0024:
            r1 = 0
            r10 = 0
        L_0x0026:
            int[] r11 = r6.zzc
            sun.misc.Unsafe r12 = zzb
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r2 = 0
            r15 = 0
        L_0x002f:
            int r3 = r11.length
            if (r15 >= r3) goto L_0x067b
            int r3 = r6.zzu(r15)
            int[] r4 = r6.zzc
            int r5 = zzt(r3)
            r14 = r4[r15]
            r9 = 17
            if (r5 > r9) goto L_0x0067
            int r9 = r15 + 2
            r4 = r4[r9]
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r4 & r9
            if (r13 == r0) goto L_0x005b
            if (r13 != r9) goto L_0x0052
            r9 = r1
            r2 = 0
            goto L_0x0059
        L_0x0052:
            r9 = r1
            long r0 = (long) r13
            int r0 = r12.getInt(r7, r0)
            r2 = r0
        L_0x0059:
            r0 = r13
            goto L_0x005c
        L_0x005b:
            r9 = r1
        L_0x005c:
            int r1 = r4 >>> 20
            r4 = 1
            int r1 = r4 << r1
            r21 = r1
            r20 = r2
            r13 = r9
            goto L_0x006d
        L_0x0067:
            r9 = r1
            r20 = r2
            r13 = r9
            r21 = 0
        L_0x006d:
            r9 = r0
        L_0x006e:
            if (r13 == 0) goto L_0x008d
            com.google.android.gms.internal.ads.zzhaz r0 = r6.zzo
            int r0 = r0.zza(r13)
            if (r0 > r14) goto L_0x008d
            com.google.android.gms.internal.ads.zzhaz r0 = r6.zzo
            r0.zzi(r8, r13)
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x008b
            java.lang.Object r0 = r10.next()
            r13 = r0
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13
            goto L_0x006e
        L_0x008b:
            r13 = 0
            goto L_0x006e
        L_0x008d:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r3 & r18
            long r3 = (long) r0
            switch(r5) {
                case 0: goto L_0x064f;
                case 1: goto L_0x062e;
                case 2: goto L_0x060d;
                case 3: goto L_0x05eb;
                case 4: goto L_0x05c9;
                case 5: goto L_0x05a7;
                case 6: goto L_0x0585;
                case 7: goto L_0x0563;
                case 8: goto L_0x0541;
                case 9: goto L_0x051b;
                case 10: goto L_0x04f7;
                case 11: goto L_0x04d5;
                case 12: goto L_0x04b3;
                case 13: goto L_0x0491;
                case 14: goto L_0x046f;
                case 15: goto L_0x044d;
                case 16: goto L_0x042b;
                case 17: goto L_0x0404;
                case 18: goto L_0x03ec;
                case 19: goto L_0x03db;
                case 20: goto L_0x03ca;
                case 21: goto L_0x03b9;
                case 22: goto L_0x03a8;
                case 23: goto L_0x0397;
                case 24: goto L_0x0386;
                case 25: goto L_0x0374;
                case 26: goto L_0x035f;
                case 27: goto L_0x034b;
                case 28: goto L_0x033b;
                case 29: goto L_0x0327;
                case 30: goto L_0x0316;
                case 31: goto L_0x0305;
                case 32: goto L_0x02f4;
                case 33: goto L_0x02e3;
                case 34: goto L_0x02d2;
                case 35: goto L_0x02c0;
                case 36: goto L_0x02ae;
                case 37: goto L_0x029c;
                case 38: goto L_0x028a;
                case 39: goto L_0x0278;
                case 40: goto L_0x0266;
                case 41: goto L_0x0254;
                case 42: goto L_0x0242;
                case 43: goto L_0x0230;
                case 44: goto L_0x021e;
                case 45: goto L_0x020c;
                case 46: goto L_0x01fa;
                case 47: goto L_0x01e8;
                case 48: goto L_0x01d6;
                case 49: goto L_0x01c1;
                case 50: goto L_0x01b0;
                case 51: goto L_0x01a1;
                case 52: goto L_0x0192;
                case 53: goto L_0x0183;
                case 54: goto L_0x0174;
                case 55: goto L_0x0165;
                case 56: goto L_0x0156;
                case 57: goto L_0x0147;
                case 58: goto L_0x0138;
                case 59: goto L_0x0129;
                case 60: goto L_0x0116;
                case 61: goto L_0x0106;
                case 62: goto L_0x00f8;
                case 63: goto L_0x00ea;
                case 64: goto L_0x00dc;
                case 65: goto L_0x00ce;
                case 66: goto L_0x00c0;
                case 67: goto L_0x00b2;
                case 68: goto L_0x00a0;
                default: goto L_0x0096;
            }
        L_0x0096:
            r16 = r10
            r19 = r11
            r17 = 0
        L_0x009c:
            r22 = 0
            goto L_0x066f
        L_0x00a0:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            java.lang.Object r0 = r12.getObject(r7, r3)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r15)
            r8.zzq(r14, r0, r1)
            goto L_0x0096
        L_0x00b2:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            long r0 = zzv(r7, r3)
            r8.zzD(r14, r0)
            goto L_0x0096
        L_0x00c0:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzB(r14, r0)
            goto L_0x0096
        L_0x00ce:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            long r0 = zzv(r7, r3)
            r8.zzz(r14, r0)
            goto L_0x0096
        L_0x00dc:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzx(r14, r0)
            goto L_0x0096
        L_0x00ea:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzi(r14, r0)
            goto L_0x0096
        L_0x00f8:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzI(r14, r0)
            goto L_0x0096
        L_0x0106:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            java.lang.Object r0 = r12.getObject(r7, r3)
            com.google.android.gms.internal.ads.zzhac r0 = (com.google.android.gms.internal.ads.zzhac) r0
            r8.zzd(r14, r0)
            goto L_0x0096
        L_0x0116:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            java.lang.Object r0 = r12.getObject(r7, r3)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r15)
            r8.zzv(r14, r0, r1)
            goto L_0x0096
        L_0x0129:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            java.lang.Object r0 = r12.getObject(r7, r3)
            zzT(r14, r0, r8)
            goto L_0x0096
        L_0x0138:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            boolean r0 = zzS(r7, r3)
            r8.zzb(r14, r0)
            goto L_0x0096
        L_0x0147:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzk(r14, r0)
            goto L_0x0096
        L_0x0156:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            long r0 = zzv(r7, r3)
            r8.zzm(r14, r0)
            goto L_0x0096
        L_0x0165:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            int r0 = zzp(r7, r3)
            r8.zzr(r14, r0)
            goto L_0x0096
        L_0x0174:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            long r0 = zzv(r7, r3)
            r8.zzK(r14, r0)
            goto L_0x0096
        L_0x0183:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            long r0 = zzv(r7, r3)
            r8.zzt(r14, r0)
            goto L_0x0096
        L_0x0192:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            float r0 = zzo(r7, r3)
            r8.zzo(r14, r0)
            goto L_0x0096
        L_0x01a1:
            boolean r0 = r6.zzR(r7, r14, r15)
            if (r0 == 0) goto L_0x0096
            double r0 = zzn(r7, r3)
            r8.zzf(r14, r0)
            goto L_0x0096
        L_0x01b0:
            java.lang.Object r0 = r12.getObject(r7, r3)
            if (r0 != 0) goto L_0x01b8
            goto L_0x0096
        L_0x01b8:
            java.lang.Object r0 = r6.zzz(r15)
            com.google.android.gms.internal.ads.zzhcx r0 = (com.google.android.gms.internal.ads.zzhcx) r0
            r17 = 0
            throw r17
        L_0x01c1:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzhdz r2 = r6.zzx(r15)
            com.google.android.gms.internal.ads.zzheb.zzA(r0, r1, r8, r2)
            goto L_0x036e
        L_0x01d6:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            r2 = 1
            com.google.android.gms.internal.ads.zzheb.zzH(r0, r1, r8, r2)
            goto L_0x036e
        L_0x01e8:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzG(r0, r1, r8, r2)
            goto L_0x036e
        L_0x01fa:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzF(r0, r1, r8, r2)
            goto L_0x036e
        L_0x020c:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzE(r0, r1, r8, r2)
            goto L_0x036e
        L_0x021e:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzw(r0, r1, r8, r2)
            goto L_0x036e
        L_0x0230:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzJ(r0, r1, r8, r2)
            goto L_0x036e
        L_0x0242:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzt(r0, r1, r8, r2)
            goto L_0x036e
        L_0x0254:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzx(r0, r1, r8, r2)
            goto L_0x036e
        L_0x0266:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzy(r0, r1, r8, r2)
            goto L_0x036e
        L_0x0278:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzB(r0, r1, r8, r2)
            goto L_0x036e
        L_0x028a:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzK(r0, r1, r8, r2)
            goto L_0x036e
        L_0x029c:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzC(r0, r1, r8, r2)
            goto L_0x036e
        L_0x02ae:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzz(r0, r1, r8, r2)
            goto L_0x036e
        L_0x02c0:
            r2 = 1
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzv(r0, r1, r8, r2)
            goto L_0x036e
        L_0x02d2:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            r2 = 0
            com.google.android.gms.internal.ads.zzheb.zzH(r0, r1, r8, r2)
            goto L_0x0337
        L_0x02e3:
            r2 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzG(r0, r1, r8, r2)
            goto L_0x0337
        L_0x02f4:
            r2 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzF(r0, r1, r8, r2)
            goto L_0x0337
        L_0x0305:
            r2 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzE(r0, r1, r8, r2)
            goto L_0x0337
        L_0x0316:
            r2 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzw(r0, r1, r8, r2)
            goto L_0x0337
        L_0x0327:
            r2 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzJ(r0, r1, r8, r2)
        L_0x0337:
            r22 = r2
            goto L_0x03fe
        L_0x033b:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzu(r0, r1, r8)
            goto L_0x036e
        L_0x034b:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzhdz r2 = r6.zzx(r15)
            com.google.android.gms.internal.ads.zzheb.zzD(r0, r1, r8, r2)
            goto L_0x036e
        L_0x035f:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzI(r0, r1, r8)
        L_0x036e:
            r16 = r10
            r19 = r11
            goto L_0x009c
        L_0x0374:
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            r5 = 0
            com.google.android.gms.internal.ads.zzheb.zzt(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x0386:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzx(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x0397:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzy(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x03a8:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzB(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x03b9:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzK(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x03ca:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzC(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x03db:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzz(r0, r1, r8, r5)
            goto L_0x03fc
        L_0x03ec:
            r5 = 0
            r17 = 0
            int[] r0 = r6.zzc
            r0 = r0[r15]
            java.lang.Object r1 = r12.getObject(r7, r3)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.ads.zzheb.zzv(r0, r1, r8, r5)
        L_0x03fc:
            r22 = r5
        L_0x03fe:
            r16 = r10
            r19 = r11
            goto L_0x066f
        L_0x0404:
            r5 = 0
            r17 = 0
            r0 = r23
            r1 = r24
            r2 = r15
            r16 = r10
            r19 = r11
            r10 = r3
            r3 = r9
            r4 = r20
            r22 = r5
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            java.lang.Object r0 = r12.getObject(r7, r10)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r15)
            r8.zzq(r14, r0, r1)
            goto L_0x066f
        L_0x042b:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            long r0 = r12.getLong(r7, r10)
            r8.zzD(r14, r0)
            goto L_0x066f
        L_0x044d:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzB(r14, r0)
            goto L_0x066f
        L_0x046f:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            long r0 = r12.getLong(r7, r10)
            r8.zzz(r14, r0)
            goto L_0x066f
        L_0x0491:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzx(r14, r0)
            goto L_0x066f
        L_0x04b3:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzi(r14, r0)
            goto L_0x066f
        L_0x04d5:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzI(r14, r0)
            goto L_0x066f
        L_0x04f7:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            java.lang.Object r0 = r12.getObject(r7, r10)
            com.google.android.gms.internal.ads.zzhac r0 = (com.google.android.gms.internal.ads.zzhac) r0
            r8.zzd(r14, r0)
            goto L_0x066f
        L_0x051b:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            java.lang.Object r0 = r12.getObject(r7, r10)
            com.google.android.gms.internal.ads.zzhdz r1 = r6.zzx(r15)
            r8.zzv(r14, r0, r1)
            goto L_0x066f
        L_0x0541:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            java.lang.Object r0 = r12.getObject(r7, r10)
            zzT(r14, r0, r8)
            goto L_0x066f
        L_0x0563:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            boolean r0 = com.google.android.gms.internal.ads.zzhfa.zzz(r7, r10)
            r8.zzb(r14, r0)
            goto L_0x066f
        L_0x0585:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzk(r14, r0)
            goto L_0x066f
        L_0x05a7:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            long r0 = r12.getLong(r7, r10)
            r8.zzm(r14, r0)
            goto L_0x066f
        L_0x05c9:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            int r0 = r12.getInt(r7, r10)
            r8.zzr(r14, r0)
            goto L_0x066f
        L_0x05eb:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            long r0 = r12.getLong(r7, r10)
            r8.zzK(r14, r0)
            goto L_0x066f
        L_0x060d:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            long r0 = r12.getLong(r7, r10)
            r8.zzt(r14, r0)
            goto L_0x066f
        L_0x062e:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            float r0 = com.google.android.gms.internal.ads.zzhfa.zzc(r7, r10)
            r8.zzo(r14, r0)
            goto L_0x066f
        L_0x064f:
            r16 = r10
            r19 = r11
            r17 = 0
            r22 = 0
            r10 = r3
            r0 = r23
            r1 = r24
            r2 = r15
            r3 = r9
            r4 = r20
            r5 = r21
            boolean r0 = r0.zzO(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x066f
            double r0 = com.google.android.gms.internal.ads.zzhfa.zzb(r7, r10)
            r8.zzf(r14, r0)
        L_0x066f:
            int r15 = r15 + 3
            r0 = r9
            r1 = r13
            r10 = r16
            r11 = r19
            r2 = r20
            goto L_0x002f
        L_0x067b:
            r9 = r1
            r16 = r10
            r17 = 0
        L_0x0680:
            if (r1 == 0) goto L_0x0698
            com.google.android.gms.internal.ads.zzhaz r0 = r6.zzo
            r0.zzi(r8, r1)
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x0695
            java.lang.Object r0 = r16.next()
            r1 = r0
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0680
        L_0x0695:
            r1 = r17
            goto L_0x0680
        L_0x0698:
            com.google.android.gms.internal.ads.zzheq r0 = r6.zzn
            java.lang.Object r1 = r0.zzd(r7)
            r0.zzq(r1, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhdh.zzj(java.lang.Object, com.google.android.gms.internal.ads.zzhfi):void");
    }

    public final boolean zzk(Object obj, Object obj2) {
        boolean z;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzu = zzu(i);
            long j = (long) (zzu & 1048575);
            switch (zzt(zzu)) {
                case 0:
                    if (zzL(obj, obj2, i) && Double.doubleToLongBits(zzhfa.zzb(obj, j)) == Double.doubleToLongBits(zzhfa.zzb(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzL(obj, obj2, i) && Float.floatToIntBits(zzhfa.zzc(obj, j)) == Float.floatToIntBits(zzhfa.zzc(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzL(obj, obj2, i) && zzhfa.zzf(obj, j) == zzhfa.zzf(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzL(obj, obj2, i) && zzhfa.zzf(obj, j) == zzhfa.zzf(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzL(obj, obj2, i) && zzhfa.zzf(obj, j) == zzhfa.zzf(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzL(obj, obj2, i) && zzhfa.zzz(obj, j) == zzhfa.zzz(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzL(obj, obj2, i) && zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzL(obj, obj2, i) && zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzL(obj, obj2, i) && zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzL(obj, obj2, i) && zzhfa.zzf(obj, j) == zzhfa.zzf(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzL(obj, obj2, i) && zzhfa.zzd(obj, j) == zzhfa.zzd(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzL(obj, obj2, i) && zzhfa.zzf(obj, j) == zzhfa.zzf(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzL(obj, obj2, i) && zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                case 49:
                    z = zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j));
                    break;
                case 50:
                    z = zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j));
                    break;
                case 51:
                case 52:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                case 54:
                case 55:
                case Opcodes.FSTORE:
                case Opcodes.DSTORE:
                case Opcodes.ASTORE:
                case 59:
                case LockFreeTaskQueueCore.FROZEN_SHIFT:
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case 62:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case 66:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL:
                case 68:
                    long zzr = (long) (zzr(i) & 1048575);
                    if (zzhfa.zzd(obj, zzr) == zzhfa.zzd(obj2, zzr) && zzheb.zzL(zzhfa.zzh(obj, j), zzhfa.zzh(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            return false;
        }
        if (this.zzh) {
            return this.zzo.zzb(obj).equals(this.zzo.zzb(obj2));
        }
        return true;
    }

    public final boolean zzl(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzk) {
            int[] iArr = this.zzj;
            int[] iArr2 = this.zzc;
            int i6 = iArr[i4];
            int i7 = iArr2[i6];
            int zzu = zzu(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i5) {
                if (i9 != 1048575) {
                    i3 = zzb.getInt(obj2, (long) i9);
                }
                i = i3;
                i2 = i9;
            } else {
                i2 = i5;
                i = i3;
            }
            if ((268435456 & zzu) != 0 && !zzO(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzt = zzt(zzu);
            if (zzt != 9 && zzt != 17) {
                if (zzt != 27) {
                    if (zzt == 60 || zzt == 68) {
                        if (zzR(obj2, i7, i6) && !zzP(obj2, zzu, zzx(i6))) {
                            return false;
                        }
                    } else if (zzt != 49) {
                        if (zzt == 50 && !((zzhcy) zzhfa.zzh(obj2, (long) (zzu & 1048575))).isEmpty()) {
                            zzhcx zzhcx = (zzhcx) zzz(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzhfa.zzh(obj2, (long) (zzu & 1048575));
                if (!list.isEmpty()) {
                    zzhdz zzx = zzx(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzx.zzl(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzO(obj, i6, i2, i, i10) && !zzP(obj2, zzu, zzx(i6))) {
                return false;
            }
            i4++;
            i5 = i2;
            i3 = i;
        }
        return !this.zzh || this.zzo.zzb(obj2).zzl();
    }
}

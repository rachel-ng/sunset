package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzjd<T extends zzjf<T>> {
    private static final zzjd zzb = new zzjd(true);
    final zzlm<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    static int zza(zzmn zzmn, int i, Object obj) {
        int zzi = zzit.zzi(i);
        if (zzmn == zzmn.GROUP) {
            zzjm.zza((zzkt) obj);
            zzi <<= 1;
        }
        return zzi + zza(zzmn, obj);
    }

    private static int zza(zzmn zzmn, Object obj) {
        switch (zzjc.zzb[zzmn.ordinal()]) {
            case 1:
                return zzit.zza(((Double) obj).doubleValue());
            case 2:
                return zzit.zza(((Float) obj).floatValue());
            case 3:
                return zzit.zzd(((Long) obj).longValue());
            case 4:
                return zzit.zzg(((Long) obj).longValue());
            case 5:
                return zzit.zzf(((Integer) obj).intValue());
            case 6:
                return zzit.zzc(((Long) obj).longValue());
            case 7:
                return zzit.zze(((Integer) obj).intValue());
            case 8:
                return zzit.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzit.zzb((zzkt) obj);
            case 10:
                if (obj instanceof zzjx) {
                    return zzit.zza((zzkb) (zzjx) obj);
                }
                return zzit.zzc((zzkt) obj);
            case 11:
                if (obj instanceof zzia) {
                    return zzit.zzb((zzia) obj);
                }
                return zzit.zzb((String) obj);
            case 12:
                if (obj instanceof zzia) {
                    return zzit.zzb((zzia) obj);
                }
                return zzit.zza((byte[]) obj);
            case 13:
                return zzit.zzj(((Integer) obj).intValue());
            case 14:
                return zzit.zzg(((Integer) obj).intValue());
            case 15:
                return zzit.zze(((Long) obj).longValue());
            case 16:
                return zzit.zzh(((Integer) obj).intValue());
            case 17:
                return zzit.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzjp) {
                    return zzit.zzd(((zzjp) obj).zza());
                }
                return zzit.zzd(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzjf<?> zzjf, Object obj) {
        zzmn zzb2 = zzjf.zzb();
        int zza2 = zzjf.zza();
        if (!zzjf.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List<Object> list = (List) obj;
        int i = 0;
        if (!zzjf.zzd()) {
            for (Object zza3 : list) {
                i += zza(zzb2, zza2, zza3);
            }
            return i;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            for (Object zza4 : list) {
                i += zza(zzb2, zza4);
            }
            return zzit.zzi(zza2) + i + zzit.zzj(i);
        }
    }

    public final int zza() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zza(); i2++) {
            i += zza(this.zza.zza(i2));
        }
        for (Map.Entry<T, Object> zza2 : this.zza.zzb()) {
            i += zza(zza2);
        }
        return i;
    }

    private static int zza(Map.Entry<T, Object> entry) {
        zzjf zzjf = (zzjf) entry.getKey();
        Object value = entry.getValue();
        if (zzjf.zzc() != zzmx.MESSAGE || zzjf.zze() || zzjf.zzd()) {
            return zza((zzjf<?>) zzjf, value);
        }
        if (value instanceof zzjx) {
            return zzit.zza(((zzjf) entry.getKey()).zza(), (zzkb) (zzjx) value);
        }
        return zzit.zzb(((zzjf) entry.getKey()).zza(), (zzkt) value);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public static <T extends zzjf<T>> zzjd<T> zzb() {
        return zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzjd zzjd = new zzjd();
        for (int i = 0; i < this.zza.zza(); i++) {
            Map.Entry<T, Object> zza2 = this.zza.zza(i);
            zzjd.zzb((zzjf) zza2.getKey(), zza2.getValue());
        }
        for (Map.Entry next : this.zza.zzb()) {
            zzjd.zzb((zzjf) next.getKey(), next.getValue());
        }
        zzjd.zzd = this.zzd;
        return zzjd;
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzky) {
            return ((zzky) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzjx)) {
            return obj;
        }
        zzjx zzjx = (zzjx) obj;
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzjy(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzjy(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    private zzjd() {
        this.zza = new zzlp();
    }

    private zzjd(zzlm<T, Object> zzlm) {
        this.zza = zzlm;
        zze();
    }

    private zzjd(boolean z) {
        this(new zzlp());
        zze();
    }

    public final void zze() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zza(); i++) {
                Map.Entry<T, Object> zza2 = this.zza.zza(i);
                if (zza2.getValue() instanceof zzjk) {
                    ((zzjk) zza2.getValue()).zzck();
                }
            }
            this.zza.zzd();
            this.zzc = true;
        }
    }

    public final void zza(zzjd<T> zzjd) {
        for (int i = 0; i < zzjd.zza.zza(); i++) {
            zzb(zzjd.zza.zza(i));
        }
        for (Map.Entry<T, Object> zzb2 : zzjd.zza.zzb()) {
            zzb(zzb2);
        }
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object obj;
        zzjf zzjf = (zzjf) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzjx;
        if (zzjf.zze()) {
            if (!z) {
                Object zza2 = zza(zzjf);
                if (zza2 == null) {
                    zza2 = new ArrayList();
                }
                for (Object zza3 : (List) value) {
                    ((List) zza2).add(zza(zza3));
                }
                this.zza.put(zzjf, zza2);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzjf.zzc() == zzmx.MESSAGE) {
            Object zza4 = zza(zzjf);
            if (zza4 == null) {
                this.zza.put(zzjf, zza(value));
                if (z) {
                    this.zzd = true;
                }
            } else if (!z) {
                if (zza4 instanceof zzky) {
                    obj = zzjf.zza((zzky) zza4, (zzky) value);
                } else {
                    obj = zzjf.zza(((zzkt) zza4).zzci(), (zzkt) value).zzai();
                }
                this.zza.put(zzjf, obj);
            } else {
                zzjx zzjx = (zzjx) value;
                throw new NoSuchMethodError();
            }
        } else if (!z) {
            this.zza.put(zzjf, zza(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private final void zzb(T t, Object obj) {
        if (!t.zze()) {
            zzc(t, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                zzc(t, obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzjx) {
            this.zzd = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if ((r6 instanceof com.google.android.gms.internal.measurement.zzjp) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r6 instanceof byte[]) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r0 == false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
        throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new java.lang.Object[]{java.lang.Integer.valueOf(r5.zza()), r5.zzb().zzb(), r6.getClass().getName()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0018, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if ((r6 instanceof com.google.android.gms.internal.measurement.zzjx) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(T r5, java.lang.Object r6) {
        /*
            com.google.android.gms.internal.measurement.zzmn r0 = r5.zzb()
            com.google.android.gms.internal.measurement.zzjm.zza(r6)
            int[] r1 = com.google.android.gms.internal.measurement.zzjc.zza
            com.google.android.gms.internal.measurement.zzmx r0 = r0.zzb()
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x0045;
                case 2: goto L_0x0042;
                case 3: goto L_0x003f;
                case 4: goto L_0x003c;
                case 5: goto L_0x0039;
                case 6: goto L_0x0036;
                case 7: goto L_0x002c;
                case 8: goto L_0x0023;
                case 9: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            r0 = r2
            goto L_0x0047
        L_0x001a:
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzkt
            if (r0 != 0) goto L_0x0034
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzjx
            if (r0 == 0) goto L_0x0018
            goto L_0x0034
        L_0x0023:
            boolean r0 = r6 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0034
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzjp
            if (r0 == 0) goto L_0x0018
            goto L_0x0034
        L_0x002c:
            boolean r0 = r6 instanceof com.google.android.gms.internal.measurement.zzia
            if (r0 != 0) goto L_0x0034
            boolean r0 = r6 instanceof byte[]
            if (r0 == 0) goto L_0x0018
        L_0x0034:
            r0 = r1
            goto L_0x0047
        L_0x0036:
            boolean r0 = r6 instanceof java.lang.String
            goto L_0x0047
        L_0x0039:
            boolean r0 = r6 instanceof java.lang.Boolean
            goto L_0x0047
        L_0x003c:
            boolean r0 = r6 instanceof java.lang.Double
            goto L_0x0047
        L_0x003f:
            boolean r0 = r6 instanceof java.lang.Float
            goto L_0x0047
        L_0x0042:
            boolean r0 = r6 instanceof java.lang.Long
            goto L_0x0047
        L_0x0045:
            boolean r0 = r6 instanceof java.lang.Integer
        L_0x0047:
            if (r0 == 0) goto L_0x004a
            return
        L_0x004a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r3 = r5.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            com.google.android.gms.internal.measurement.zzmn r5 = r5.zzb()
            com.google.android.gms.internal.measurement.zzmx r5 = r5.zzb()
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getName()
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r2] = r3
            r4[r1] = r5
            r5 = 2
            r4[r5] = r6
            java.lang.String r5 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r5 = java.lang.String.format(r5, r4)
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjd.zzc(com.google.android.gms.internal.measurement.zzjf, java.lang.Object):void");
    }

    static void zza(zzit zzit, zzmn zzmn, int i, Object obj) throws IOException {
        if (zzmn == zzmn.GROUP) {
            zzkt zzkt = (zzkt) obj;
            zzjm.zza(zzkt);
            zzit.zzc(i, 3);
            zzkt.zza(zzit);
            zzit.zzc(i, 4);
            return;
        }
        zzit.zzc(i, zzmn.zza());
        switch (zzjc.zzb[zzmn.ordinal()]) {
            case 1:
                zzit.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzit.zzb(((Float) obj).floatValue());
                return;
            case 3:
                zzit.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzit.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzit.zzb(((Integer) obj).intValue());
                return;
            case 6:
                zzit.zza(((Long) obj).longValue());
                return;
            case 7:
                zzit.zza(((Integer) obj).intValue());
                return;
            case 8:
                zzit.zzb(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzkt) obj).zza(zzit);
                return;
            case 10:
                zzit.zza((zzkt) obj);
                return;
            case 11:
                if (obj instanceof zzia) {
                    zzit.zza((zzia) obj);
                    return;
                } else {
                    zzit.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzia) {
                    zzit.zza((zzia) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzit.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzit.zzc(((Integer) obj).intValue());
                return;
            case 14:
                zzit.zza(((Integer) obj).intValue());
                return;
            case 15:
                zzit.zza(((Long) obj).longValue());
                return;
            case 16:
                zzit.zzk(((Integer) obj).intValue());
                return;
            case 17:
                zzit.zzh(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzjp) {
                    zzit.zzb(((zzjp) obj).zza());
                    return;
                } else {
                    zzit.zzb(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjd)) {
            return false;
        }
        return this.zza.equals(((zzjd) obj).zza);
    }

    public final boolean zzf() {
        return this.zzc;
    }

    public final boolean zzg() {
        for (int i = 0; i < this.zza.zza(); i++) {
            if (!zzc(this.zza.zza(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzc2 : this.zza.zzb()) {
            if (!zzc(zzc2)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzjf<T>> boolean zzc(Map.Entry<T, Object> entry) {
        zzjf zzjf = (zzjf) entry.getKey();
        if (zzjf.zzc() != zzmx.MESSAGE) {
            return true;
        }
        if (!zzjf.zze()) {
            return zzb(entry.getValue());
        }
        for (Object zzb2 : (List) entry.getValue()) {
            if (!zzb(zzb2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzb(Object obj) {
        if (obj instanceof zzkv) {
            return ((zzkv) obj).zzcm();
        }
        if (obj instanceof zzjx) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzhbi;
import com.google.android.gms.internal.ads.zzhbo;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public abstract class zzhbo<MessageType extends zzhbo<MessageType, BuilderType>, BuilderType extends zzhbi<MessageType, BuilderType>> extends zzgzi<MessageType, BuilderType> {
    private static final int zza = Integer.MIN_VALUE;
    private static final int zzb = Integer.MAX_VALUE;
    private static Map<Object, zzhbo<?, ?>> zzc = new ConcurrentHashMap();
    static final int zzr = Integer.MAX_VALUE;
    static final int zzs = 0;
    private int zzd = -1;
    protected zzher zzt = zzher.zzc();

    protected static zzhbq zzbA() {
        return zzgzp.zzd();
    }

    protected static zzhbq zzbB(zzhbq zzhbq) {
        int size = zzhbq.size();
        return zzhbq.zze(size == 0 ? 10 : size + size);
    }

    protected static zzhbr zzbC() {
        return zzhav.zze();
    }

    protected static zzhbr zzbD(zzhbr zzhbr) {
        int size = zzhbr.size();
        return zzhbr.zzg(size == 0 ? 10 : size + size);
    }

    protected static zzhbv zzbE() {
        return zzhbf.zze();
    }

    protected static zzhbv zzbF(zzhbv zzhbv) {
        int size = zzhbv.size();
        return zzhbv.zzg(size == 0 ? 10 : size + size);
    }

    protected static zzhbw zzbG() {
        return zzhbp.zzg();
    }

    protected static zzhbw zzbH(zzhbw zzhbw) {
        int size = zzhbw.size();
        return zzhbw.zzh(size == 0 ? 10 : size + size);
    }

    protected static zzhbz zzbI() {
        return zzhct.zzh();
    }

    protected static zzhbz zzbJ(zzhbz zzhbz) {
        int size = zzhbz.size();
        return zzhbz.zze(size == 0 ? 10 : size + size);
    }

    protected static <E> zzhca<E> zzbK() {
        return zzhdp.zzd();
    }

    protected static <E> zzhca<E> zzbL(zzhca<E> zzhca) {
        int size = zzhca.size();
        return zzhca.zzf(size == 0 ? 10 : size + size);
    }

    static Object zzbQ(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzbR(zzhde zzhde, String str, Object[] objArr) {
        return new zzhdq(zzhde, str, objArr);
    }

    static Method zzbS(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            String name = cls.getName();
            throw new RuntimeException("Generated message class \"" + name + "\" missing method \"" + str + "\".", e);
        }
    }

    public static <ContainingType extends zzhde, Type> zzhbm<ContainingType, Type> zzbe(ContainingType containingtype, zzhde zzhde, zzhbt zzhbt, int i, zzhfg zzhfg, boolean z, Class cls) {
        return new zzhbm(containingtype, Collections.emptyList(), zzhde, new zzhbl(zzhbt, i, zzhfg, true, z), cls);
    }

    public static <ContainingType extends zzhde, Type> zzhbm<ContainingType, Type> zzbf(ContainingType containingtype, Type type, zzhde zzhde, zzhbt zzhbt, int i, zzhfg zzhfg, Class cls) {
        return new zzhbm(containingtype, type, zzhde, new zzhbl(zzhbt, i, zzhfg, false, false), cls);
    }

    static <T extends zzhbo> T zzbh(Class<T> cls) {
        T t = (zzhbo) zzc.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzhbo) zzc.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = ((zzhbo) zzhfa.zzg(cls)).zzbt();
            if (t != null) {
                zzc.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzhbo<T, ?>> T zzbk(T t, InputStream inputStream) throws zzhcd {
        T zzg = zzg(t, inputStream, zzhay.zza);
        zzf(zzg);
        return zzg;
    }

    protected static <T extends zzhbo<T, ?>> T zzbl(T t, InputStream inputStream, zzhay zzhay) throws zzhcd {
        T zzg = zzg(t, inputStream, zzhay);
        zzf(zzg);
        return zzg;
    }

    protected static <T extends zzhbo<T, ?>> T zzbm(T t, zzhac zzhac) throws zzhcd {
        T zzbr = zzbr(t, zzhac, zzhay.zza);
        zzf(zzbr);
        return zzbr;
    }

    protected static <T extends zzhbo<T, ?>> T zzbn(T t, zzham zzham) throws zzhcd {
        return zzbs(t, zzham, zzhay.zza);
    }

    protected static <T extends zzhbo<T, ?>> T zzbo(T t, InputStream inputStream) throws zzhcd {
        T zzbz = zzbz(t, zzham.zzI(inputStream, 4096), zzhay.zza);
        zzf(zzbz);
        return zzbz;
    }

    protected static <T extends zzhbo<T, ?>> T zzbp(T t, ByteBuffer byteBuffer) throws zzhcd {
        return zzbv(t, byteBuffer, zzhay.zza);
    }

    protected static <T extends zzhbo<T, ?>> T zzbq(T t, byte[] bArr) throws zzhcd {
        T zzi = zzi(t, bArr, 0, bArr.length, zzhay.zza);
        zzf(zzi);
        return zzi;
    }

    protected static <T extends zzhbo<T, ?>> T zzbr(T t, zzhac zzhac, zzhay zzhay) throws zzhcd {
        T zzh = zzh(t, zzhac, zzhay);
        zzf(zzh);
        return zzh;
    }

    protected static <T extends zzhbo<T, ?>> T zzbs(T t, zzham zzham, zzhay zzhay) throws zzhcd {
        T zzbz = zzbz(t, zzham, zzhay);
        zzf(zzbz);
        return zzbz;
    }

    protected static <T extends zzhbo<T, ?>> T zzbu(T t, InputStream inputStream, zzhay zzhay) throws zzhcd {
        T zzbz = zzbz(t, zzham.zzI(inputStream, 4096), zzhay);
        zzf(zzbz);
        return zzbz;
    }

    protected static <T extends zzhbo<T, ?>> T zzbv(T t, ByteBuffer byteBuffer, zzhay zzhay) throws zzhcd {
        zzham zzham;
        int i = zzham.zzd;
        if (byteBuffer.hasArray()) {
            zzham = zzham.zzJ(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), false);
        } else if (!byteBuffer.isDirect() || !zzhfa.zzB()) {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.duplicate().get(bArr);
            zzham = zzham.zzJ(bArr, 0, remaining, true);
        } else {
            zzham = new zzhak(byteBuffer, false, (zzhaj) null);
        }
        T zzbs = zzbs(t, zzham, zzhay);
        zzf(zzbs);
        return zzbs;
    }

    protected static <T extends zzhbo<T, ?>> T zzbx(T t, byte[] bArr, zzhay zzhay) throws zzhcd {
        T zzi = zzi(t, bArr, 0, bArr.length, zzhay);
        zzf(zzi);
        return zzi;
    }

    protected static <T extends zzhbo<T, ?>> T zzby(T t, zzham zzham) throws zzhcd {
        return zzbz(t, zzham, zzhay.zza);
    }

    static <T extends zzhbo<T, ?>> T zzbz(T t, zzham zzham, zzhay zzhay) throws zzhcd {
        T zzbj = t.zzbj();
        try {
            zzhdz zzb2 = zzhdo.zza().zzb(zzbj.getClass());
            zzb2.zzh(zzbj, zzhan.zzq(zzham), zzhay);
            zzb2.zzf(zzbj);
            return zzbj;
        } catch (zzhcd e) {
            e = e;
            if (e.zzl()) {
                e = new zzhcd((IOException) e);
            }
            e.zzh(zzbj);
            throw e;
        } catch (zzhep e2) {
            zzhcd zza2 = e2.zza();
            zza2.zzh(zzbj);
            throw zza2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzhcd) {
                throw ((zzhcd) e3.getCause());
            }
            zzhcd zzhcd = new zzhcd(e3);
            zzhcd.zzh(zzbj);
            throw zzhcd;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzhcd) {
                throw ((zzhcd) e4.getCause());
            }
            throw e4;
        }
    }

    private int zzc(zzhdz<?> zzhdz) {
        if (zzhdz != null) {
            return zzhdz.zza(this);
        }
        return zzhdo.zza().zzb(getClass()).zza(this);
    }

    protected static <T extends zzhbo> void zzca(Class<T> cls, T t) {
        t.zzbW();
        zzc.put(cls, t);
    }

    protected static final <T extends zzhbo<T, ?>> boolean zzcd(T t, boolean z) {
        byte byteValue = ((Byte) t.zzbO(zzhbn.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zzhdo.zza().zzb(t.getClass()).zzl(t);
        if (z) {
            t.zzbP(zzhbn.SET_MEMOIZED_IS_INITIALIZED, true != zzl ? null : t);
        }
        return zzl;
    }

    /* access modifiers changed from: private */
    public static <MessageType extends zzhbk<MessageType, BuilderType>, BuilderType, T> zzhbm<MessageType, T> zze(zzhaw<MessageType, T> zzhaw) {
        return (zzhbm) zzhaw;
    }

    private static <T extends zzhbo<T, ?>> T zzf(T t) throws zzhcd {
        if (t == null || t.zzbw()) {
            return t;
        }
        zzhcd zza2 = t.zzaP().zza();
        zza2.zzh(t);
        throw zza2;
    }

    private static <T extends zzhbo<T, ?>> T zzg(T t, InputStream inputStream, zzhay zzhay) throws zzhcd {
        try {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            zzham zzI = zzham.zzI(new zzgzg(inputStream, zzham.zzG(read, inputStream)), 4096);
            T zzbz = zzbz(t, zzI, zzhay);
            try {
                zzI.zzz(0);
                return zzbz;
            } catch (zzhcd e) {
                e.zzh(zzbz);
                throw e;
            }
        } catch (zzhcd e2) {
            if (e2.zzl()) {
                throw new zzhcd((IOException) e2);
            }
            throw e2;
        } catch (IOException e3) {
            throw new zzhcd(e3);
        }
    }

    private static <T extends zzhbo<T, ?>> T zzh(T t, zzhac zzhac, zzhay zzhay) throws zzhcd {
        zzham zzl = zzhac.zzl();
        T zzbz = zzbz(t, zzl, zzhay);
        try {
            zzl.zzz(0);
            return zzbz;
        } catch (zzhcd e) {
            e.zzh(zzbz);
            throw e;
        }
    }

    /* access modifiers changed from: private */
    public static <T extends zzhbo<T, ?>> T zzi(T t, byte[] bArr, int i, int i2, zzhay zzhay) throws zzhcd {
        T zzbj = t.zzbj();
        try {
            zzhdz zzb2 = zzhdo.zza().zzb(zzbj.getClass());
            zzb2.zzi(zzbj, bArr, i, i + i2, new zzgzn(zzhay));
            zzb2.zzf(zzbj);
            return zzbj;
        } catch (zzhcd e) {
            e = e;
            if (e.zzl()) {
                e = new zzhcd((IOException) e);
            }
            e.zzh(zzbj);
            throw e;
        } catch (zzhep e2) {
            zzhcd zza2 = e2.zza();
            zza2.zzh(zzbj);
            throw zza2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzhcd) {
                throw ((zzhcd) e3.getCause());
            }
            zzhcd zzhcd = new zzhcd(e3);
            zzhcd.zzh(zzbj);
            throw zzhcd;
        } catch (IndexOutOfBoundsException unused) {
            zzhcd zzj = zzhcd.zzj();
            zzj.zzh(zzbj);
            throw zzj;
        }
    }

    private void zzj() {
        if (this.zzt == zzher.zzc()) {
            this.zzt = zzher.zzf();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzhdo.zza().zzb(getClass()).zzk(this, (zzhbo) obj);
    }

    public int hashCode() {
        if (zzce()) {
            return zzaW();
        }
        if (zzcc()) {
            zzcb(zzaW());
        }
        return zzaX();
    }

    public String toString() {
        return zzhdg.zza(this, super.toString());
    }

    /* access modifiers changed from: package-private */
    public int zzaL() {
        return this.zzd & Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public int zzaM(zzhdz zzhdz) {
        if (zzce()) {
            int zzc2 = zzc(zzhdz);
            if (zzc2 >= 0) {
                return zzc2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zzc2);
        } else if (zzaL() != Integer.MAX_VALUE) {
            return zzaL();
        } else {
            int zzc3 = zzc(zzhdz);
            zzaS(zzc3);
            return zzc3;
        }
    }

    public zzhdj zzaO() {
        throw new UnsupportedOperationException("Lite does not support the mutable API.");
    }

    /* access modifiers changed from: package-private */
    public void zzaS(int i) {
        if (i >= 0) {
            this.zzd = i | (this.zzd & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + i);
    }

    /* access modifiers changed from: package-private */
    public int zzaW() {
        return zzhdo.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: package-private */
    public int zzaX() {
        return this.zzq;
    }

    public int zzaY() {
        return zzaM((zzhdz) null);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzhbo<MessageType, BuilderType>, BuilderType extends zzhbi<MessageType, BuilderType>> BuilderType zzaZ() {
        return (zzhbi) zzbO(zzhbn.NEW_BUILDER);
    }

    public final zzhdm<MessageType> zzbM() {
        return (zzhdm) zzbO(zzhbn.GET_PARSER);
    }

    /* access modifiers changed from: package-private */
    public Object zzbN() throws Exception {
        return zzbO(zzhbn.BUILD_MESSAGE_INFO);
    }

    /* access modifiers changed from: protected */
    public Object zzbO(zzhbn zzhbn) {
        return zzde(zzhbn, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public Object zzbP(zzhbn zzhbn, Object obj) {
        return zzde(zzhbn, obj, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public void zzbT() {
        this.zzq = 0;
    }

    /* access modifiers changed from: package-private */
    public void zzbU() {
        zzaS(Integer.MAX_VALUE);
    }

    /* access modifiers changed from: protected */
    public void zzbV() {
        zzhdo.zza().zzb(getClass()).zzf(this);
        zzbW();
    }

    /* access modifiers changed from: package-private */
    public void zzbW() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: protected */
    public void zzbX(int i, zzhac zzhac) {
        zzj();
        zzher zzher = this.zzt;
        zzher.zzg();
        if (i != 0) {
            zzher.zzj((i << 3) | 2, zzhac);
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    /* access modifiers changed from: protected */
    public final void zzbY(zzher zzher) {
        this.zzt = zzher.zze(this.zzt, zzher);
    }

    /* access modifiers changed from: protected */
    public void zzbZ(int i, int i2) {
        zzj();
        zzher zzher = this.zzt;
        zzher.zzg();
        if (i != 0) {
            zzher.zzj(i << 3, Long.valueOf((long) i2));
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzhbo<MessageType, BuilderType>, BuilderType extends zzhbi<MessageType, BuilderType>> BuilderType zzba(MessageType messagetype) {
        BuilderType zzaZ = zzaZ();
        zzaZ.zzbj(messagetype);
        return zzaZ;
    }

    /* renamed from: zzbb */
    public final BuilderType zzcY() {
        return (zzhbi) zzbO(zzhbn.NEW_BUILDER);
    }

    /* renamed from: zzbc */
    public final BuilderType zzcZ() {
        BuilderType buildertype = (zzhbi) zzbO(zzhbn.NEW_BUILDER);
        buildertype.zzbj(this);
        return buildertype;
    }

    /* renamed from: zzbi */
    public final MessageType zzbt() {
        return (zzhbo) zzbO(zzhbn.GET_DEFAULT_INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public MessageType zzbj() {
        return (zzhbo) zzbO(zzhbn.NEW_MUTABLE_INSTANCE);
    }

    public final boolean zzbw() {
        return zzcd(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: package-private */
    public void zzcb(int i) {
        this.zzq = i;
    }

    /* access modifiers changed from: package-private */
    public boolean zzcc() {
        return zzaX() == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean zzce() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    /* access modifiers changed from: protected */
    public boolean zzcf(int i, zzham zzham) throws IOException {
        if ((i & 7) == 4) {
            return false;
        }
        zzj();
        return this.zzt.zzm(i, zzham);
    }

    public void zzda(zzhat zzhat) throws IOException {
        zzhdo.zza().zzb(getClass()).zzj(this, zzhau.zza(zzhat));
    }

    /* access modifiers changed from: protected */
    public abstract Object zzde(zzhbn zzhbn, Object obj, Object obj2);
}

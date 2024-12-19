package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhcl extends zzgzl implements RandomAccess, zzhcm {
    @Deprecated
    public static final zzhcm zza;
    private static final zzhcl zzb;
    private final List zzc;

    static {
        zzhcl zzhcl = new zzhcl(false);
        zzb = zzhcl;
        zza = zzhcl;
    }

    public zzhcl() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzhac) {
            return ((zzhac) obj).zzx(zzhcb.zzb);
        }
        return zzhcb.zzd((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zzdJ();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zzdJ();
        if (collection instanceof zzhcm) {
            collection = ((zzhcm) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zzdJ();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zzdJ();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zzdJ();
        return zzj(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final zzhcm zzd() {
        return zzc() ? new zzhev(this) : this;
    }

    public final Object zze(int i) {
        return this.zzc.get(i);
    }

    public final /* bridge */ /* synthetic */ zzhca zzf(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzhcl(arrayList);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzhac) {
            zzhac zzhac = (zzhac) obj;
            String zzx = zzhac.zzx(zzhcb.zzb);
            if (zzhac.zzp()) {
                this.zzc.set(i, zzx);
            }
            return zzx;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzhcb.zzd(bArr);
        if (zzhff.zzi(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final void zzi(zzhac zzhac) {
        zzdJ();
        this.zzc.add(zzhac);
        this.modCount++;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzhcl(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzhcl(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzhcl(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}

package com.google.android.gms.internal.ads;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzp extends zzfzi implements SortedMap {
    @CheckForNull
    SortedSet zzd;
    final /* synthetic */ zzfzv zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfzp(zzfzv zzfzv, SortedMap sortedMap) {
        super(zzfzv, sortedMap);
        this.zze = zzfzv;
    }

    @CheckForNull
    public final Comparator comparator() {
        return zzf().comparator();
    }

    public final Object firstKey() {
        return zzf().firstKey();
    }

    public SortedMap headMap(Object obj) {
        return new zzfzp(this.zze, zzf().headMap(obj));
    }

    public final Object lastKey() {
        return zzf().lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new zzfzp(this.zze, zzf().subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new zzfzp(this.zze, zzf().tailMap(obj));
    }

    /* access modifiers changed from: package-private */
    public SortedMap zzf() {
        return (SortedMap) this.zza;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: zzg */
    public SortedSet zze() {
        return new zzfzq(this.zze, zzf());
    }

    /* renamed from: zzh */
    public SortedSet keySet() {
        SortedSet sortedSet = this.zzd;
        if (sortedSet != null) {
            return sortedSet;
        }
        SortedSet zzg = zze();
        this.zzd = zzg;
        return zzg;
    }
}

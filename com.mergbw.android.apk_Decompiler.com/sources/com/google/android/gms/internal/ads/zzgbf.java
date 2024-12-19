package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzgbf implements Map, Serializable {
    @CheckForNull
    private transient zzgbh zza;
    @CheckForNull
    private transient zzgbh zzb;
    @CheckForNull
    private transient zzgax zzc;

    zzgbf() {
    }

    public static zzgbf zzc(Map map) {
        Set entrySet = map.entrySet();
        zzgbe zzgbe = new zzgbe(entrySet instanceof Collection ? entrySet.size() : 4);
        zzgbe.zzb(entrySet);
        return zzgbe.zzc();
    }

    public static zzgbf zzd() {
        return zzgct.zza;
    }

    public static zzgbf zze(Object obj, Object obj2) {
        zzfzz.zzb("dialog_not_shown_reason", obj2);
        return zzgct.zzj(1, new Object[]{"dialog_not_shown_reason", obj2}, (zzgbe) null);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        return zzgbz.zzb(this, obj);
    }

    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzgde.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        zzfzz.zza(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public abstract zzgax zza();

    /* renamed from: zzb */
    public final zzgax values() {
        zzgax zzgax = this.zzc;
        if (zzgax != null) {
            return zzgax;
        }
        zzgax zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzgbh zzf();

    /* access modifiers changed from: package-private */
    public abstract zzgbh zzg();

    /* renamed from: zzh */
    public final zzgbh entrySet() {
        zzgbh zzgbh = this.zza;
        if (zzgbh != null) {
            return zzgbh;
        }
        zzgbh zzf = zzf();
        this.zza = zzf;
        return zzf;
    }

    /* renamed from: zzi */
    public final zzgbh keySet() {
        zzgbh zzgbh = this.zzb;
        if (zzgbh != null) {
            return zzgbh;
        }
        zzgbh zzg = zzg();
        this.zzb = zzg;
        return zzg;
    }
}

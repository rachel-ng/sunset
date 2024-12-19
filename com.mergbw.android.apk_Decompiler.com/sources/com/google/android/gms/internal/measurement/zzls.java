package com.google.android.gms.internal.measurement;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzls implements Comparable<zzls>, Map.Entry<K, V> {
    private final K zza;
    private V zzb;
    private final /* synthetic */ zzlm zzc;

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzls) obj).getKey());
    }

    public final int hashCode() {
        K k = this.zza;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.zzb;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    public final /* synthetic */ Object getKey() {
        return this.zza;
    }

    public final V getValue() {
        return this.zzb;
    }

    public final V setValue(V v) {
        this.zzc.zzg();
        V v2 = this.zzb;
        this.zzb = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        return valueOf + "=" + valueOf2;
    }

    zzls(zzlm zzlm, Map.Entry<K, V> entry) {
        this(zzlm, (Comparable) entry.getKey(), entry.getValue());
    }

    zzls(zzlm zzlm, K k, V v) {
        this.zzc = zzlm;
        this.zza = k;
        this.zzb = v;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zza(this.zza, entry.getKey()) && zza(this.zzb, entry.getValue());
    }

    private static boolean zza(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}

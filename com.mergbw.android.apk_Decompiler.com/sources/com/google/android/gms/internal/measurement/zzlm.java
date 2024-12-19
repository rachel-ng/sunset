package com.google.android.gms.internal.measurement;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
class zzlm<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    /* access modifiers changed from: private */
    public List<zzls> zza;
    /* access modifiers changed from: private */
    public Map<K, V> zzb;
    private boolean zzc;
    private volatile zzlx zzd;
    /* access modifiers changed from: private */
    public Map<K, V> zze;
    private volatile zzlq zzf;

    private final int zza(K k) {
        int i;
        int size = this.zza.size();
        int i2 = size - 1;
        if (i2 >= 0) {
            int compareTo = k.compareTo((Comparable) this.zza.get(i2).getKey());
            if (compareTo > 0) {
                i = size + 1;
                return -i;
            } else if (compareTo == 0) {
                return i2;
            }
        }
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zza.get(i4).getKey());
            if (compareTo2 < 0) {
                i2 = i4 - 1;
            } else if (compareTo2 <= 0) {
                return i4;
            } else {
                i3 = i4 + 1;
            }
        }
        i = i3 + 1;
        return -i;
    }

    public final int zza() {
        return this.zza.size();
    }

    public int hashCode() {
        int zza2 = zza();
        int i = 0;
        for (int i2 = 0; i2 < zza2; i2++) {
            i += this.zza.get(i2).hashCode();
        }
        return this.zzb.size() > 0 ? i + this.zzb.hashCode() : i;
    }

    public int size() {
        return this.zza.size() + this.zzb.size();
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzb.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzb.entrySet();
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return this.zza.get(zza2).getValue();
        }
        return this.zzb.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzg();
        int zza2 = zza(k);
        if (zza2 >= 0) {
            return this.zza.get(zza2).setValue(v);
        }
        zzg();
        if (this.zza.isEmpty() && !(this.zza instanceof ArrayList)) {
            this.zza = new ArrayList(16);
        }
        int i = -(zza2 + 1);
        if (i >= 16) {
            return zzf().put(k, v);
        }
        if (this.zza.size() == 16) {
            zzls remove = this.zza.remove(15);
            zzf().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zza.add(i, new zzls(this, k, v));
        return null;
    }

    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return zzb(zza2);
        }
        if (this.zzb.isEmpty()) {
            return null;
        }
        return this.zzb.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzb(int i) {
        zzg();
        V value = this.zza.remove(i).getValue();
        if (!this.zzb.isEmpty()) {
            Iterator it = zzf().entrySet().iterator();
            this.zza.add(new zzls(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public final Map.Entry<K, V> zza(int i) {
        return this.zza.get(i);
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzc() {
        if (this.zzf == null) {
            this.zzf = new zzlq(this);
        }
        return this.zzf;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzd == null) {
            this.zzd = new zzlx(this);
        }
        return this.zzd;
    }

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzb.isEmpty() && !(this.zzb instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzb = treeMap;
            TreeMap treeMap2 = treeMap;
            this.zze = treeMap.descendingMap();
        }
        return (SortedMap) this.zzb;
    }

    private zzlm() {
        this.zza = Collections.emptyList();
        this.zzb = Collections.emptyMap();
        this.zze = Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        if (this.zzc) {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        zzg();
        if (!this.zza.isEmpty()) {
            this.zza.clear();
        }
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
    }

    public void zzd() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzc) {
            if (this.zzb.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzb);
            }
            this.zzb = map;
            if (this.zze.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zze);
            }
            this.zze = map2;
            this.zzc = true;
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzb.containsKey(comparable);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzlm)) {
            return super.equals(obj);
        }
        zzlm zzlm = (zzlm) obj;
        int size = size();
        if (size != zzlm.size()) {
            return false;
        }
        int zza2 = zza();
        if (zza2 != zzlm.zza()) {
            return entrySet().equals(zzlm.entrySet());
        }
        for (int i = 0; i < zza2; i++) {
            if (!zza(i).equals(zzlm.zza(i))) {
                return false;
            }
        }
        if (zza2 != size) {
            return this.zzb.equals(zzlm.zzb);
        }
        return true;
    }

    public final boolean zze() {
        return this.zzc;
    }
}

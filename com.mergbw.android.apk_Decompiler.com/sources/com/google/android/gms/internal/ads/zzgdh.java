package com.google.android.gms.internal.ads;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgdh extends zzgdg implements ListIterator {
    zzgdh(ListIterator listIterator) {
        super(listIterator);
    }

    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasPrevious() {
        return ((ListIterator) this.zzb).hasPrevious();
    }

    public final int nextIndex() {
        return ((ListIterator) this.zzb).nextIndex();
    }

    public final Object previous() {
        return zza(((ListIterator) this.zzb).previous());
    }

    public final int previousIndex() {
        return ((ListIterator) this.zzb).previousIndex();
    }

    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}

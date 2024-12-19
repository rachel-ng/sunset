package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgeh;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgey extends zzgeh.zzi {
    private static final zzgeu zzbd;
    private static final zzggd zzbe;
    /* access modifiers changed from: private */
    public volatile int remaining;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Set<Throwable> seenExceptions = null;

    static {
        Throwable th;
        zzgeu zzgeu;
        Class<zzgey> cls = zzgey.class;
        zzbe = new zzggd(cls);
        try {
            zzgeu = new zzgev(AtomicReferenceFieldUpdater.newUpdater(cls, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(cls, "remaining"));
            th = null;
        } catch (Throwable th2) {
            zzgeu = new zzgex((zzgew) null);
            th = th2;
        }
        zzbd = zzgeu;
        if (th != null) {
            zzbe.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    zzgey(int i) {
        this.remaining = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzA() {
        return zzbd.zza(this);
    }

    /* access modifiers changed from: package-private */
    public final Set zzC() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        zze(newSetFromMap);
        zzbd.zzb(this, (Set) null, newSetFromMap);
        return (Set) Objects.requireNonNull(this.seenExceptions);
    }

    /* access modifiers changed from: package-private */
    public final void zzF() {
        this.seenExceptions = null;
    }

    /* access modifiers changed from: package-private */
    public abstract void zze(Set set);
}

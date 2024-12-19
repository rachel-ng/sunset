package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzges extends zzgey {
    private static final zzggd zza = new zzggd(zzges.class);
    @CheckForNull
    private zzgax zzb;
    private final boolean zzc;
    private final boolean zzf;

    zzges(zzgax zzgax, boolean z, boolean z2) {
        super(zzgax.size());
        zzgax.getClass();
        this.zzb = zzgax;
        this.zzc = z;
        this.zzf = z2;
    }

    private final void zzG(int i, Future future) {
        try {
            zzf(i, zzgft.zzp(future));
        } catch (ExecutionException e) {
            zzI(e.getCause());
        } catch (Throwable th) {
            zzI(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzH */
    public final void zzx(@CheckForNull zzgax zzgax) {
        int zzA = zzA();
        int i = 0;
        zzfyg.zzk(zzA >= 0, "Less than 0 remaining futures");
        if (zzA == 0) {
            if (zzgax != null) {
                zzgdi zze = zzgax.iterator();
                while (zze.hasNext()) {
                    Future future = (Future) zze.next();
                    if (!future.isCancelled()) {
                        zzG(i, future);
                    }
                    i++;
                }
            }
            zzF();
            zzu();
            zzy(2);
        }
    }

    private static void zzJ(Throwable th) {
        String str;
        if (true != (th instanceof Error)) {
            str = "Got more than one input Future failure. Logging failures after the first";
        } else {
            str = "Input Future failed with Error";
        }
        zza.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", str, th);
    }

    private static boolean zzK(Set set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        zzgax zzgax = this.zzb;
        if (zzgax != null) {
            return "futures=".concat(zzgax.toString());
        }
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzgax zzgax = this.zzb;
        boolean z = true;
        zzy(1);
        boolean isCancelled = isCancelled();
        if (zzgax == null) {
            z = false;
        }
        if (z && isCancelled) {
            boolean zzt = zzt();
            zzgdi zze = zzgax.iterator();
            while (zze.hasNext()) {
                ((Future) zze.next()).cancel(zzt);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void zzf(int i, Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzu();

    /* access modifiers changed from: package-private */
    public final void zzv() {
        Objects.requireNonNull(this.zzb);
        if (this.zzb.isEmpty()) {
            zzu();
        } else if (this.zzc) {
            zzgdi zze = this.zzb.iterator();
            int i = 0;
            while (zze.hasNext()) {
                ListenableFuture listenableFuture = (ListenableFuture) zze.next();
                listenableFuture.addListener(new zzgeq(this, listenableFuture, i), zzgfh.INSTANCE);
                i++;
            }
        } else {
            zzger zzger = new zzger(this, this.zzf ? this.zzb : null);
            zzgdi zze2 = this.zzb.iterator();
            while (zze2.hasNext()) {
                ((ListenableFuture) zze2.next()).addListener(zzger, zzgfh.INSTANCE);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzw(ListenableFuture listenableFuture, int i) {
        try {
            if (listenableFuture.isCancelled()) {
                this.zzb = null;
                cancel(false);
            } else {
                zzG(i, listenableFuture);
            }
        } finally {
            zzx((zzgax) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzy(int i) {
        this.zzb = null;
    }

    /* access modifiers changed from: package-private */
    public final void zze(Set set) {
        set.getClass();
        if (!isCancelled()) {
            zzK(set, (Throwable) Objects.requireNonNull(zzl()));
        }
    }

    private final void zzI(Throwable th) {
        th.getClass();
        if (this.zzc && !zzd(th) && zzK(zzC(), th)) {
            zzJ(th);
        } else if (th instanceof Error) {
            zzJ(th);
        }
    }
}

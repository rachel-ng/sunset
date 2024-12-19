package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import javax.annotation.Nullable;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzgh extends zzhg {
    private final Context zza;
    @Nullable
    private final Supplier<Optional<zzgt>> zzb;

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Supplier<Optional<zzgt>> supplier = this.zzb;
        return hashCode ^ (supplier == null ? 0 : supplier.hashCode());
    }

    /* access modifiers changed from: package-private */
    public final Context zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final Supplier<Optional<zzgt>> zzb() {
        return this.zzb;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        return "FlagsContext{context=" + valueOf + ", hermeticFileOverrides=" + valueOf2 + VectorFormat.DEFAULT_SUFFIX;
    }

    zzgh(Context context, @Nullable Supplier<Optional<zzgt>> supplier) {
        if (context != null) {
            this.zza = context;
            this.zzb = supplier;
            return;
        }
        throw new NullPointerException("Null context");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r1 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.measurement.zzhg
            r2 = 0
            if (r1 == 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zzhg r5 = (com.google.android.gms.internal.measurement.zzhg) r5
            android.content.Context r1 = r4.zza
            android.content.Context r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x002d
            com.google.common.base.Supplier<com.google.common.base.Optional<com.google.android.gms.internal.measurement.zzgt>> r1 = r4.zzb
            if (r1 != 0) goto L_0x0022
            com.google.common.base.Supplier r5 = r5.zzb()
            if (r5 != 0) goto L_0x002d
            goto L_0x002c
        L_0x0022:
            com.google.common.base.Supplier r5 = r5.zzb()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x002d
        L_0x002c:
            return r0
        L_0x002d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgh.equals(java.lang.Object):boolean");
    }
}

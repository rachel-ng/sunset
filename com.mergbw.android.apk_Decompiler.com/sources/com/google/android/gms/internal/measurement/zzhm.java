package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSetMultimap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzhm {
    public static final Supplier<ImmutableSetMultimap<String, String>> zza = Suppliers.memoize(new zzho());
}

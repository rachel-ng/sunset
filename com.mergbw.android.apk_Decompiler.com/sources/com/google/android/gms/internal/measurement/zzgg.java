package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public interface zzgg {
    String zza(ContentResolver contentResolver, String str) throws zzgf;

    <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgd<T> zzgd) throws zzgf;
}

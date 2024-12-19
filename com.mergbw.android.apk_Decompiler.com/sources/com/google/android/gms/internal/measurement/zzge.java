package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzge implements zzgg {
    public final String zza(ContentResolver contentResolver, String str) throws zzgf {
        Cursor query = contentResolver.query(zzfy.zza, (String[]) null, (String) null, new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(1);
                    if (query != null) {
                        query.close();
                    }
                    return string;
                } else if (query == null) {
                    return null;
                } else {
                    query.close();
                    return null;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new zzgf("Failed to connect to GservicesProvider");
        }
        throw th;
    }

    public final <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgd<T> zzgd) throws zzgf {
        Cursor query = contentResolver.query(zzfy.zzb, (String[]) null, (String) null, strArr, (String) null);
        if (query != null) {
            try {
                T zza = zzgd.zza(query.getCount());
                while (query.moveToNext()) {
                    zza.put(query.getString(0), query.getString(1));
                }
                if (query != null) {
                    query.close();
                }
                return zza;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new zzgf("Failed to connect to GservicesProvider");
        }
        throw th;
    }
}

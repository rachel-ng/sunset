package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzau {
    private static Set<String> zza(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    static void zza(zzfw zzfw, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzfw != null) {
            if (!zza(zzfw, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            if (zzfw != null) {
                try {
                    Set<String> zza = zza(sQLiteDatabase, str);
                    String[] split = str3.split(",");
                    int length = split.length;
                    int i = 0;
                    while (i < length) {
                        String str4 = split[i];
                        if (zza.remove(str4)) {
                            i++;
                        } else {
                            throw new SQLiteException("Table " + str + " is missing required column: " + str4);
                        }
                    }
                    if (strArr != null) {
                        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                            if (!zza.remove(strArr[i2])) {
                                sQLiteDatabase.execSQL(strArr[i2 + 1]);
                            }
                        }
                    }
                    if (!zza.isEmpty()) {
                        zzfw.zzu().zza("Table has extra columns. table, columns", str, TextUtils.join(", ", zza));
                    }
                } catch (SQLiteException e) {
                    zzfw.zzg().zza("Failed to verify columns on table that was just created", str);
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("Monitor must not be null");
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    static void zza(zzfw zzfw, SQLiteDatabase sQLiteDatabase) {
        if (zzfw != null) {
            File file = new File(zzcf.zza().zza(sQLiteDatabase.getPath()));
            if (!file.setReadable(false, false)) {
                zzfw.zzu().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzfw.zzu().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzfw.zzu().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzfw.zzu().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    private static boolean zza(zzfw zzfw, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzfw != null) {
            Cursor cursor = null;
            try {
                Cursor query = sQLiteDatabase.query("SQLITE_MASTER", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME}, "name=?", new String[]{str}, (String) null, (String) null, (String) null);
                boolean moveToFirst = query.moveToFirst();
                if (query != null) {
                    query.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                zzfw.zzu().zza("Error querying for table", str, e);
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }
}

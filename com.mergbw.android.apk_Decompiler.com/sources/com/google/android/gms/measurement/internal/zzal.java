package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzjk;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzal extends zzmx {
    /* access modifiers changed from: private */
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzj = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzk = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final zzar zzl = new zzar(this, zza(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzmr zzm = new zzmr(zzb());

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            return e_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting conditional property", zzfw.zza(str), zzi().zzc(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    public final long zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        try {
            return (long) e_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zze().zzb(str, zzbf.zzp))))});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting over the limit events. appId", zzfw.zza(str), e);
            return 0;
        }
    }

    public final long b_() {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final long zza(zzfn.zzk zzk2) throws IOException {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        byte[] zzbz = zzk2.zzbz();
        long zza2 = g_().zza(zzbz);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzk2.zzz());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza2));
        contentValues.put(TtmlNode.TAG_METADATA, zzbz);
        try {
            e_().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
            return zza2;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event metadata. appId", zzfw.zza(zzk2.zzz()), e);
            throw e;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final long zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        long j = 0;
        try {
            long zza2 = zza("select " + str2 + " from app2 where app_id=?", new String[]{str}, -1);
            if (zza2 == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (e_.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzj().zzg().zza("Failed to insert column (got -1). appId", zzfw.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                zza2 = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + zza2));
                if (((long) e_.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzj().zzg().zza("Failed to update column (got 0). appId", zzfw.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                e_.setTransactionSuccessful();
                e_.endTransaction();
                return zza2;
            } catch (SQLiteException e) {
                long j2 = zza2;
                e = e;
                j = j2;
                try {
                    zzj().zzg().zza("Error inserting column. appId", zzfw.zza(str), str2, e);
                    e_.endTransaction();
                    return j;
                } catch (Throwable th) {
                    e_.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            zzj().zzg().zza("Error inserting column. appId", zzfw.zza(str), str2, e);
            e_.endTransaction();
            return j;
        }
    }

    public final long zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        return zza("select first_open_count from app2 where app_id=?", new String[]{str}, -1);
    }

    public final long c_() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long d_() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase e_() {
        zzt();
        try {
            return this.zzl.getWritableDatabase();
        } catch (SQLiteException e) {
            zzj().zzu().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzd(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            java.lang.String[] r3 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006e }
            if (r2 != 0) goto L_0x002e
            com.google.android.gms.measurement.internal.zzfw r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x006e }
            java.lang.String r2 = "Default event parameters not found"
            r6.zza(r2)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            return r0
        L_0x002e:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r3 = com.google.android.gms.internal.measurement.zzfn.zzf.zze()     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzks r2 = com.google.android.gms.measurement.internal.zznl.zza(r3, (byte[]) r2)     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzkt r2 = r2.zzai()     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzjk r2 = (com.google.android.gms.internal.measurement.zzjk) r2     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfn$zzf r2 = (com.google.android.gms.internal.measurement.zzfn.zzf) r2     // Catch:{ IOException -> 0x0056 }
            r5.g_()     // Catch:{ SQLiteException -> 0x006e }
            java.util.List r6 = r2.zzh()     // Catch:{ SQLiteException -> 0x006e }
            android.os.Bundle r6 = com.google.android.gms.measurement.internal.zznl.zza((java.util.List<com.google.android.gms.internal.measurement.zzfn.zzh>) r6)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x0055
            r1.close()
        L_0x0055:
            return r6
        L_0x0056:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfw r3 = r5.zzj()     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x006e }
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x006e }
            r3.zza(r4, r6, r2)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            return r0
        L_0x006e:
            r6 = move-exception
            goto L_0x0074
        L_0x0070:
            r6 = move-exception
            goto L_0x0089
        L_0x0072:
            r6 = move-exception
            r1 = r0
        L_0x0074:
            com.google.android.gms.measurement.internal.zzfw r2 = r5.zzj()     // Catch:{ all -> 0x0087 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zza(r3, r6)     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            return r0
        L_0x0087:
            r6 = move-exception
            r0 = r1
        L_0x0089:
            if (r0 == 0) goto L_0x008e
            r0.close()
        L_0x008e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzd(java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0091  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzfn.zzf, java.lang.Long> zza(java.lang.String r6, java.lang.Long r7) {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch:{ SQLiteException -> 0x0078, all -> 0x0076 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            java.lang.String r3 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0078, all -> 0x0076 }
            java.lang.String[] r3 = new java.lang.String[]{r6, r3}     // Catch:{ SQLiteException -> 0x0078, all -> 0x0076 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0078, all -> 0x0076 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0074 }
            if (r2 != 0) goto L_0x0032
            com.google.android.gms.measurement.internal.zzfw r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzfy r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r7 = "Main event not found"
            r6.zza(r7)     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            return r0
        L_0x0032:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x0074 }
            r3 = 1
            long r3 = r1.getLong(r3)     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r4 = com.google.android.gms.internal.measurement.zzfn.zzf.zze()     // Catch:{ IOException -> 0x005c }
            com.google.android.gms.internal.measurement.zzks r2 = com.google.android.gms.measurement.internal.zznl.zza(r4, (byte[]) r2)     // Catch:{ IOException -> 0x005c }
            com.google.android.gms.internal.measurement.zzfn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfn.zzf.zza) r2     // Catch:{ IOException -> 0x005c }
            com.google.android.gms.internal.measurement.zzkt r2 = r2.zzai()     // Catch:{ IOException -> 0x005c }
            com.google.android.gms.internal.measurement.zzjk r2 = (com.google.android.gms.internal.measurement.zzjk) r2     // Catch:{ IOException -> 0x005c }
            com.google.android.gms.internal.measurement.zzfn$zzf r2 = (com.google.android.gms.internal.measurement.zzfn.zzf) r2     // Catch:{ IOException -> 0x005c }
            android.util.Pair r6 = android.util.Pair.create(r2, r3)     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x005b
            r1.close()
        L_0x005b:
            return r6
        L_0x005c:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfw r3 = r5.zzj()     // Catch:{ SQLiteException -> 0x0074 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0074 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0074 }
            r3.zza(r4, r6, r7, r2)     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x0073
            r1.close()
        L_0x0073:
            return r0
        L_0x0074:
            r6 = move-exception
            goto L_0x007a
        L_0x0076:
            r6 = move-exception
            goto L_0x008f
        L_0x0078:
            r6 = move-exception
            r1 = r0
        L_0x007a:
            com.google.android.gms.measurement.internal.zzfw r7 = r5.zzj()     // Catch:{ all -> 0x008d }
            com.google.android.gms.measurement.internal.zzfy r7 = r7.zzg()     // Catch:{ all -> 0x008d }
            java.lang.String r2 = "Error selecting main event"
            r7.zza(r2, r6)     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x008c
            r1.close()
        L_0x008c:
            return r0
        L_0x008d:
            r6 = move-exception
            r0 = r1
        L_0x008f:
            if (r0 == 0) goto L_0x0094
            r0.close()
        L_0x0094:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0309 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x031a A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0364 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0377  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0179 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x017d A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01b1 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01cf A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01d2 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01e1 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0237 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02bf A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02c1 A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02cd A[Catch:{ SQLiteException -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02cf A[Catch:{ SQLiteException -> 0x037b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zze(java.lang.String r51) {
        /*
            r50 = this;
            r1 = r50
            r2 = r51
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r51)
            r50.zzt()
            r50.zzal()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r50.e_()     // Catch:{ SQLiteException -> 0x037f, all -> 0x037d }
            java.lang.String r5 = "apps"
            java.lang.String r6 = "app_instance_id"
            java.lang.String r7 = "gmp_app_id"
            java.lang.String r8 = "resettable_device_id_hash"
            java.lang.String r9 = "last_bundle_index"
            java.lang.String r10 = "last_bundle_start_timestamp"
            java.lang.String r11 = "last_bundle_end_timestamp"
            java.lang.String r12 = "app_version"
            java.lang.String r13 = "app_store"
            java.lang.String r14 = "gmp_version"
            java.lang.String r15 = "dev_cert_hash"
            java.lang.String r16 = "measurement_enabled"
            java.lang.String r17 = "day"
            java.lang.String r18 = "daily_public_events_count"
            java.lang.String r19 = "daily_events_count"
            java.lang.String r20 = "daily_conversions_count"
            java.lang.String r21 = "config_fetched_time"
            java.lang.String r22 = "failed_config_fetch_time"
            java.lang.String r23 = "app_version_int"
            java.lang.String r24 = "firebase_instance_id"
            java.lang.String r25 = "daily_error_events_count"
            java.lang.String r26 = "daily_realtime_events_count"
            java.lang.String r27 = "health_monitor_sample"
            java.lang.String r28 = "android_id"
            java.lang.String r29 = "adid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String r31 = "dynamite_version"
            java.lang.String r32 = "safelisted_events"
            java.lang.String r33 = "ga_app_id"
            java.lang.String r34 = "session_stitching_token"
            java.lang.String r35 = "sgtm_upload_enabled"
            java.lang.String r36 = "target_os_version"
            java.lang.String r37 = "session_stitching_token_hash"
            java.lang.String r38 = "ad_services_version"
            java.lang.String r39 = "unmatched_first_open_without_ad_id"
            java.lang.String r40 = "npa_metadata_value"
            java.lang.String r41 = "attribution_eligibility_status"
            java.lang.String r42 = "sgtm_preview_key"
            java.lang.String r43 = "dma_consent_state"
            java.lang.String r44 = "daily_realtime_dcu_count"
            java.lang.String r45 = "bundle_delivery_index"
            java.lang.String r46 = "serialized_npa_metadata"
            java.lang.String r47 = "unmatched_pfo"
            java.lang.String r48 = "unmatched_uwa"
            java.lang.String r49 = "ad_campaign_info"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49}     // Catch:{ SQLiteException -> 0x037f, all -> 0x037d }
            java.lang.String r7 = "app_id=?"
            java.lang.String[] r8 = new java.lang.String[]{r51}     // Catch:{ SQLiteException -> 0x037f, all -> 0x037d }
            r10 = 0
            r11 = 0
            r9 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x037f, all -> 0x037d }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x037b }
            if (r0 != 0) goto L_0x0089
            if (r4 == 0) goto L_0x0088
            r4.close()
        L_0x0088:
            return r3
        L_0x0089:
            com.google.android.gms.measurement.internal.zzg r0 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zznc r5 = r1.zzf     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzhj r5 = r5.zzk()     // Catch:{ SQLiteException -> 0x037b }
            r0.<init>(r5, r2)     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ SQLiteException -> 0x037b }
            r6 = 0
            if (r5 == 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zzag r5 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbf.zzcv     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zznc r5 = r1.zzf     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin r5 = r5.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin$zza r7 = com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzin.zza) r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x00bc
        L_0x00b5:
            java.lang.String r5 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzb((java.lang.String) r5)     // Catch:{ SQLiteException -> 0x037b }
        L_0x00bc:
            r5 = 1
            java.lang.String r7 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzf((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x00e4
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzcv     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x00e4
            com.google.android.gms.measurement.internal.zznc r7 = r1.zzf     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin r7 = r7.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin$zza r8 = com.google.android.gms.measurement.internal.zzin.zza.AD_STORAGE     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzin.zza) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x00ec
        L_0x00e4:
            r7 = 2
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzh((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x00ec:
            r7 = 3
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzq(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 4
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzr(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 5
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzp(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 6
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzd((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 7
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzc((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 8
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzn(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 9
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzk((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 10
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 != 0) goto L_0x0137
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0135
            goto L_0x0137
        L_0x0135:
            r7 = r6
            goto L_0x0138
        L_0x0137:
            r7 = r5
        L_0x0138:
            r0.zzb((boolean) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 11
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzj((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 12
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzh((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 13
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzg((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 14
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zze((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 15
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzd((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 16
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzm(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 17
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 == 0) goto L_0x017d
            r7 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0182
        L_0x017d:
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            long r7 = (long) r7     // Catch:{ SQLiteException -> 0x037b }
        L_0x0182:
            r0.zzb((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 18
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zze((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 19
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzf((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 20
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzi((long) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 21
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzg((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 23
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 != 0) goto L_0x01ba
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x01b8
            goto L_0x01ba
        L_0x01b8:
            r7 = r6
            goto L_0x01bb
        L_0x01ba:
            r7 = r5
        L_0x01bb:
            r0.zza((boolean) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 24
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zza((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 25
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 == 0) goto L_0x01d2
            r7 = 0
            goto L_0x01d6
        L_0x01d2:
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x01d6:
            r0.zzl(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 26
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 != 0) goto L_0x01f3
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            java.lang.String r8 = ","
            r9 = -1
            java.lang.String[] r7 = r7.split(r8, r9)     // Catch:{ SQLiteException -> 0x037b }
            java.util.List r7 = java.util.Arrays.asList(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zza((java.util.List<java.lang.String>) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x01f3:
            boolean r7 = com.google.android.gms.internal.measurement.zznk.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0213
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzcv     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0213
            com.google.android.gms.measurement.internal.zznc r7 = r1.zzf     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin r7 = r7.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzin$zza r8 = com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzin.zza) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x021c
        L_0x0213:
            r7 = 28
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzj((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x021c:
            boolean r7 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0269
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzbs     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0269
            r50.zzq()     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = com.google.android.gms.measurement.internal.zznp.zzf(r51)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0269
            r7 = 29
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 != 0) goto L_0x0247
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0247
            r7 = r5
            goto L_0x0248
        L_0x0247:
            r7 = r6
        L_0x0248:
            r0.zzc((boolean) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 39
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzo(r7)     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzbt     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x0269
            r7 = 36
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzk((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x0269:
            r7 = 30
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzt(r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 31
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzs(r7)     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = com.google.android.gms.internal.measurement.zzpg.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x029f
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzbz     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zze(r2, r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x029f
            r7 = 32
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zza((int) r7)     // Catch:{ SQLiteException -> 0x037b }
            r7 = 35
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzc((long) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x029f:
            boolean r7 = com.google.android.gms.internal.measurement.zznl.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x02c5
            com.google.android.gms.measurement.internal.zzag r7 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbf.zzck     // Catch:{ SQLiteException -> 0x037b }
            boolean r7 = r7.zze(r2, r8)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x02c5
            r7 = 33
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 != 0) goto L_0x02c1
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x02c1
            r7 = r5
            goto L_0x02c2
        L_0x02c1:
            r7 = r6
        L_0x02c2:
            r0.zzd((boolean) r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x02c5:
            r7 = 34
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r8 == 0) goto L_0x02cf
            r5 = r3
            goto L_0x02da
        L_0x02cf:
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x037b }
            if (r7 == 0) goto L_0x02d6
            r6 = r5
        L_0x02d6:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)     // Catch:{ SQLiteException -> 0x037b }
        L_0x02da:
            r0.zza((java.lang.Boolean) r5)     // Catch:{ SQLiteException -> 0x037b }
            r5 = 37
            int r5 = r4.getInt(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzc((int) r5)     // Catch:{ SQLiteException -> 0x037b }
            r5 = 38
            int r5 = r4.getInt(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzb((int) r5)     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = com.google.android.gms.internal.measurement.zzne.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x030e
            com.google.android.gms.measurement.internal.zzag r5 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbf.zzcp     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r5.zze(r2, r6)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x030e
            r5 = 40
            java.lang.String r5 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 != 0) goto L_0x030b
            java.lang.String r5 = ""
        L_0x030b:
            r0.zzi((java.lang.String) r5)     // Catch:{ SQLiteException -> 0x037b }
        L_0x030e:
            com.google.android.gms.measurement.internal.zzag r5 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbf.zzcs     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r6)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x0340
            r5 = 41
            boolean r6 = r4.isNull(r5)     // Catch:{ SQLiteException -> 0x037b }
            if (r6 != 0) goto L_0x032d
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x037b }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zza((java.lang.Long) r5)     // Catch:{ SQLiteException -> 0x037b }
        L_0x032d:
            r5 = 42
            boolean r6 = r4.isNull(r5)     // Catch:{ SQLiteException -> 0x037b }
            if (r6 != 0) goto L_0x0340
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x037b }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zzb((java.lang.Long) r5)     // Catch:{ SQLiteException -> 0x037b }
        L_0x0340:
            boolean r5 = com.google.android.gms.internal.measurement.zzoj.zza()     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x035b
            com.google.android.gms.measurement.internal.zzag r5 = r50.zze()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbf.zzcm     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r5.zze(r2, r6)     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x035b
            r5 = 43
            byte[] r5 = r4.getBlob(r5)     // Catch:{ SQLiteException -> 0x037b }
            r0.zza((byte[]) r5)     // Catch:{ SQLiteException -> 0x037b }
        L_0x035b:
            r0.zzao()     // Catch:{ SQLiteException -> 0x037b }
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x037b }
            if (r5 == 0) goto L_0x0375
            com.google.android.gms.measurement.internal.zzfw r5 = r50.zzj()     // Catch:{ SQLiteException -> 0x037b }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x037b }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r51)     // Catch:{ SQLiteException -> 0x037b }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x037b }
        L_0x0375:
            if (r4 == 0) goto L_0x037a
            r4.close()
        L_0x037a:
            return r0
        L_0x037b:
            r0 = move-exception
            goto L_0x0381
        L_0x037d:
            r0 = move-exception
            goto L_0x039a
        L_0x037f:
            r0 = move-exception
            r4 = r3
        L_0x0381:
            com.google.android.gms.measurement.internal.zzfw r5 = r50.zzj()     // Catch:{ all -> 0x0398 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ all -> 0x0398 }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r51)     // Catch:{ all -> 0x0398 }
            r5.zza(r6, r2, r0)     // Catch:{ all -> 0x0398 }
            if (r4 == 0) goto L_0x0397
            r4.close()
        L_0x0397:
            return r3
        L_0x0398:
            r0 = move-exception
            r3 = r4
        L_0x039a:
            if (r3 == 0) goto L_0x039f
            r3.close()
        L_0x039f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0120  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzae zzc(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r7 = r28
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            r26.zzt()
            r26.zzal()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r26.e_()     // Catch:{ SQLiteException -> 0x00fb, all -> 0x00f9 }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x00fb, all -> 0x00f9 }
            java.lang.String r12 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[]{r27, r28}     // Catch:{ SQLiteException -> 0x00fb, all -> 0x00f9 }
            r15 = 0
            r16 = 0
            r14 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00fb, all -> 0x00f9 }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00f7 }
            if (r0 != 0) goto L_0x0049
            if (r9 == 0) goto L_0x0048
            r9.close()
        L_0x0048:
            return r8
        L_0x0049:
            r0 = 0
            java.lang.String r1 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f7 }
            if (r1 != 0) goto L_0x0052
            java.lang.String r1 = ""
        L_0x0052:
            r12 = r1
            r1 = 1
            r14 = r26
            java.lang.Object r5 = r14.zza((android.database.Cursor) r9, (int) r1)     // Catch:{ SQLiteException -> 0x00f7 }
            r2 = 2
            int r2 = r9.getInt(r2)     // Catch:{ SQLiteException -> 0x00f7 }
            if (r2 == 0) goto L_0x0064
            r16 = r1
            goto L_0x0066
        L_0x0064:
            r16 = r0
        L_0x0066:
            r0 = 3
            java.lang.String r17 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00f7 }
            r0 = 4
            long r19 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zznl r0 = r26.g_()     // Catch:{ SQLiteException -> 0x00f7 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbd> r2 = com.google.android.gms.measurement.internal.zzbd.CREATOR     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f7 }
            r18 = r0
            com.google.android.gms.measurement.internal.zzbd r18 = (com.google.android.gms.measurement.internal.zzbd) r18     // Catch:{ SQLiteException -> 0x00f7 }
            r0 = 6
            long r21 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zznl r0 = r26.g_()     // Catch:{ SQLiteException -> 0x00f7 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbd> r2 = com.google.android.gms.measurement.internal.zzbd.CREATOR     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzbd r0 = (com.google.android.gms.measurement.internal.zzbd) r0     // Catch:{ SQLiteException -> 0x00f7 }
            r1 = 8
            long r3 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x00f7 }
            r1 = 9
            long r23 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zznl r1 = r26.g_()     // Catch:{ SQLiteException -> 0x00f7 }
            r2 = 10
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbd> r6 = com.google.android.gms.measurement.internal.zzbd.CREATOR     // Catch:{ SQLiteException -> 0x00f7 }
            android.os.Parcelable r1 = r1.zza((byte[]) r2, r6)     // Catch:{ SQLiteException -> 0x00f7 }
            r25 = r1
            com.google.android.gms.measurement.internal.zzbd r25 = (com.google.android.gms.measurement.internal.zzbd) r25     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzno r13 = new com.google.android.gms.measurement.internal.zzno     // Catch:{ SQLiteException -> 0x00f7 }
            r1 = r13
            r2 = r28
            r6 = r12
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzae r1 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ SQLiteException -> 0x00f7 }
            r10 = r1
            r11 = r27
            r14 = r21
            r21 = r0
            r22 = r23
            r24 = r25
            r10.<init>(r11, r12, r13, r14, r16, r17, r18, r19, r21, r22, r24)     // Catch:{ SQLiteException -> 0x00f7 }
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00f7 }
            if (r0 == 0) goto L_0x00f1
            com.google.android.gms.measurement.internal.zzfw r0 = r26.zzj()     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteException -> 0x00f7 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r27)     // Catch:{ SQLiteException -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzfr r4 = r26.zzi()     // Catch:{ SQLiteException -> 0x00f7 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ SQLiteException -> 0x00f7 }
            r0.zza(r2, r3, r4)     // Catch:{ SQLiteException -> 0x00f7 }
        L_0x00f1:
            if (r9 == 0) goto L_0x00f6
            r9.close()
        L_0x00f6:
            return r1
        L_0x00f7:
            r0 = move-exception
            goto L_0x00fd
        L_0x00f9:
            r0 = move-exception
            goto L_0x011e
        L_0x00fb:
            r0 = move-exception
            r9 = r8
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzfw r1 = r26.zzj()     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch:{ all -> 0x011c }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r27)     // Catch:{ all -> 0x011c }
            com.google.android.gms.measurement.internal.zzfr r4 = r26.zzi()     // Catch:{ all -> 0x011c }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ all -> 0x011c }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x011c }
            if (r9 == 0) goto L_0x011b
            r9.close()
        L_0x011b:
            return r8
        L_0x011c:
            r0 = move-exception
            r8 = r9
        L_0x011e:
            if (r8 == 0) goto L_0x0123
            r8.close()
        L_0x0123:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzae");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzan zzf(java.lang.String r10) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r9.zzt()
            r9.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.e_()     // Catch:{ SQLiteException -> 0x0070, all -> 0x006e }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String r4 = "config_last_modified_time"
            java.lang.String r5 = "e_tag"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x0070, all -> 0x006e }
            java.lang.String r4 = "app_id=?"
            java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x0070, all -> 0x006e }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0070, all -> 0x006e }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006c }
            if (r2 != 0) goto L_0x0033
            if (r1 == 0) goto L_0x0032
            r1.close()
        L_0x0032:
            return r0
        L_0x0033:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x006c }
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ SQLiteException -> 0x006c }
            r4 = 2
            java.lang.String r4 = r1.getString(r4)     // Catch:{ SQLiteException -> 0x006c }
            boolean r5 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x006c }
            if (r5 == 0) goto L_0x0059
            com.google.android.gms.measurement.internal.zzfw r5 = r9.zzj()     // Catch:{ SQLiteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x006c }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r10)     // Catch:{ SQLiteException -> 0x006c }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x006c }
        L_0x0059:
            if (r2 != 0) goto L_0x0061
            if (r1 == 0) goto L_0x0060
            r1.close()
        L_0x0060:
            return r0
        L_0x0061:
            com.google.android.gms.measurement.internal.zzan r5 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ SQLiteException -> 0x006c }
            r5.<init>(r2, r3, r4)     // Catch:{ SQLiteException -> 0x006c }
            if (r1 == 0) goto L_0x006b
            r1.close()
        L_0x006b:
            return r5
        L_0x006c:
            r2 = move-exception
            goto L_0x0072
        L_0x006e:
            r10 = move-exception
            goto L_0x008b
        L_0x0070:
            r2 = move-exception
            r1 = r0
        L_0x0072:
            com.google.android.gms.measurement.internal.zzfw r3 = r9.zzj()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x0089 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r10)     // Catch:{ all -> 0x0089 }
            r3.zza(r4, r10, r2)     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0088
            r1.close()
        L_0x0088:
            return r0
        L_0x0089:
            r10 = move-exception
            r0 = r1
        L_0x008b:
            if (r0 == 0) goto L_0x0090
            r0.close()
        L_0x0090:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzf(java.lang.String):com.google.android.gms.measurement.internal.zzan");
    }

    public final zzaq zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return zza(j, str, 1, false, false, z3, false, z5, z6);
    }

    public final zzaq zza(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        String[] strArr = {str};
        zzaq zzaq = new zzaq();
        Cursor cursor = null;
        try {
            SQLiteDatabase e_ = e_();
            cursor = e_.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                zzj().zzu().zza("Not updating daily counts, app is not known. appId", zzfw.zza(str));
                if (cursor != null) {
                    cursor.close();
                }
                return zzaq;
            }
            if (cursor.getLong(0) == j) {
                zzaq.zzb = cursor.getLong(1);
                zzaq.zza = cursor.getLong(2);
                zzaq.zzc = cursor.getLong(3);
                zzaq.zzd = cursor.getLong(4);
                zzaq.zze = cursor.getLong(5);
                zzaq.zzf = cursor.getLong(6);
            }
            if (z) {
                zzaq.zzb += j2;
            }
            if (z2) {
                zzaq.zza += j2;
            }
            if (z3) {
                zzaq.zzc += j2;
            }
            if (z4) {
                zzaq.zzd += j2;
            }
            if (z5) {
                zzaq.zze += j2;
            }
            if (z6) {
                zzaq.zzf += j2;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j));
            contentValues.put("daily_public_events_count", Long.valueOf(zzaq.zza));
            contentValues.put("daily_events_count", Long.valueOf(zzaq.zzb));
            contentValues.put("daily_conversions_count", Long.valueOf(zzaq.zzc));
            contentValues.put("daily_error_events_count", Long.valueOf(zzaq.zzd));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzaq.zze));
            contentValues.put("daily_realtime_dcu_count", Long.valueOf(zzaq.zzf));
            e_.update("apps", contentValues, "app_id=?", strArr);
            if (cursor != null) {
                cursor.close();
            }
            return zzaq;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error updating daily counts. appId", zzfw.zza(str), e);
            if (cursor != null) {
                cursor.close();
            }
            return zzaq;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final zzav zzg(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzav.zza(zza("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzaz zzd(String str, String str2) {
        return zzc("events", str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.measurement.internal.zzaz zzc(java.lang.String r29, java.lang.String r30, java.lang.String r31) {
        /*
            r28 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r28.zzt()
            r28.zzal()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r8 = "last_exempt_from_sampling"
            java.lang.String r9 = "current_session_count"
            java.lang.String r1 = "lifetime_count"
            java.lang.String r2 = "current_bundle_count"
            java.lang.String r3 = "last_fire_timestamp"
            java.lang.String r4 = "last_bundled_timestamp"
            java.lang.String r5 = "last_bundled_day"
            java.lang.String r6 = "last_sampled_complex_event_id"
            java.lang.String r7 = "last_sampling_rate"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4, r5, r6, r7, r8, r9}
            java.util.List r1 = java.util.Arrays.asList(r1)
            r0.<init>(r1)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r28.e_()     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r10 = 0
            java.lang.String[] r3 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            java.lang.Object[] r0 = r0.toArray(r3)     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r4 = r0
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            java.lang.String r5 = "app_id=? and name=?"
            java.lang.String[] r6 = new java.lang.String[]{r30, r31}     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r8 = 0
            r9 = 0
            r7 = 0
            r3 = r29
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0104 }
            if (r0 != 0) goto L_0x0055
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            return r1
        L_0x0055:
            long r14 = r2.getLong(r10)     // Catch:{ SQLiteException -> 0x0104 }
            r0 = 1
            long r16 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x0104 }
            r3 = 2
            long r20 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r3 = 3
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r5 = 0
            if (r4 == 0) goto L_0x006f
            r22 = r5
            goto L_0x0075
        L_0x006f:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r22 = r3
        L_0x0075:
            r3 = 4
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x007f
            r24 = r1
            goto L_0x0089
        L_0x007f:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r24 = r3
        L_0x0089:
            r3 = 5
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x0093
            r25 = r1
            goto L_0x009d
        L_0x0093:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r25 = r3
        L_0x009d:
            r3 = 6
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x00a7
            r26 = r1
            goto L_0x00b1
        L_0x00a7:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r26 = r3
        L_0x00b1:
            r3 = 7
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 != 0) goto L_0x00ca
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r7 = 1
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x00c3
            r10 = r0
        L_0x00c3:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x0104 }
            r27 = r0
            goto L_0x00cc
        L_0x00ca:
            r27 = r1
        L_0x00cc:
            r0 = 8
            boolean r3 = r2.isNull(r0)     // Catch:{ SQLiteException -> 0x0104 }
            if (r3 == 0) goto L_0x00d7
            r18 = r5
            goto L_0x00dd
        L_0x00d7:
            long r3 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x0104 }
            r18 = r3
        L_0x00dd:
            com.google.android.gms.measurement.internal.zzaz r0 = new com.google.android.gms.measurement.internal.zzaz     // Catch:{ SQLiteException -> 0x0104 }
            r11 = r0
            r12 = r30
            r13 = r31
            r11.<init>(r12, r13, r14, r16, r18, r20, r22, r24, r25, r26, r27)     // Catch:{ SQLiteException -> 0x0104 }
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0104 }
            if (r3 == 0) goto L_0x00fe
            com.google.android.gms.measurement.internal.zzfw r3 = r28.zzj()     // Catch:{ SQLiteException -> 0x0104 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.String r4 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r30)     // Catch:{ SQLiteException -> 0x0104 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0104 }
        L_0x00fe:
            if (r2 == 0) goto L_0x0103
            r2.close()
        L_0x0103:
            return r0
        L_0x0104:
            r0 = move-exception
            goto L_0x010a
        L_0x0106:
            r0 = move-exception
            goto L_0x012d
        L_0x0108:
            r0 = move-exception
            r2 = r1
        L_0x010a:
            com.google.android.gms.measurement.internal.zzfw r3 = r28.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x012b }
            java.lang.String r4 = "Error querying events. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r30)     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzfr r6 = r28.zzi()     // Catch:{ all -> 0x012b }
            r7 = r31
            java.lang.String r6 = r6.zza((java.lang.String) r7)     // Catch:{ all -> 0x012b }
            r3.zza(r4, r5, r6, r0)     // Catch:{ all -> 0x012b }
            if (r2 == 0) goto L_0x012a
            r2.close()
        L_0x012a:
            return r1
        L_0x012b:
            r0 = move-exception
            r1 = r2
        L_0x012d:
            if (r1 == 0) goto L_0x0132
            r1.close()
        L_0x0132:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzaz");
    }

    public final zzin zzh(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzin.zzb(zza("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzin zzi(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        zzin zzin = (zzin) zza("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str}, new zzao());
        return zzin == null ? zzin.zza : zzin;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zznq zze(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r10.zzt()
            r10.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.e_()     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            java.lang.String r2 = "user_attributes"
            java.lang.String r3 = "set_timestamp"
            java.lang.String r4 = "value"
            java.lang.String r5 = "origin"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[]{r11, r12}     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0076, all -> 0x0074 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0036
            if (r1 == 0) goto L_0x0035
            r1.close()
        L_0x0035:
            return r0
        L_0x0036:
            r2 = 0
            long r7 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0072 }
            r2 = 1
            java.lang.Object r9 = r10.zza((android.database.Cursor) r1, (int) r2)     // Catch:{ SQLiteException -> 0x0072 }
            if (r9 != 0) goto L_0x0048
            if (r1 == 0) goto L_0x0047
            r1.close()
        L_0x0047:
            return r0
        L_0x0048:
            r2 = 2
            java.lang.String r5 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.measurement.internal.zznq r2 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ SQLiteException -> 0x0072 }
            r3 = r2
            r4 = r11
            r6 = r12
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x0072 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0072 }
            if (r3 == 0) goto L_0x006c
            com.google.android.gms.measurement.internal.zzfw r3 = r10.zzj()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x0072 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
        L_0x006c:
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            return r2
        L_0x0072:
            r2 = move-exception
            goto L_0x0078
        L_0x0074:
            r11 = move-exception
            goto L_0x0099
        L_0x0076:
            r2 = move-exception
            r1 = r0
        L_0x0078:
            com.google.android.gms.measurement.internal.zzfw r3 = r10.zzj()     // Catch:{ all -> 0x0097 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = "Error querying user property. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r11)     // Catch:{ all -> 0x0097 }
            com.google.android.gms.measurement.internal.zzfr r5 = r10.zzi()     // Catch:{ all -> 0x0097 }
            java.lang.String r12 = r5.zzc(r12)     // Catch:{ all -> 0x0097 }
            r3.zza(r4, r11, r12, r2)     // Catch:{ all -> 0x0097 }
            if (r1 == 0) goto L_0x0096
            r1.close()
        L_0x0096:
            return r0
        L_0x0097:
            r11 = move-exception
            r0 = r1
        L_0x0099:
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zznq");
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzj().zzg().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                zzj().zzg().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzj().zzg().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T zza(java.lang.String r3, java.lang.String[] r4, com.google.android.gms.measurement.internal.zzas<T> r5) {
        /*
            r2 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r2.e_()     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            android.database.Cursor r3 = r1.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x002c }
            if (r4 != 0) goto L_0x0022
            com.google.android.gms.measurement.internal.zzfw r4 = r2.zzj()     // Catch:{ SQLiteException -> 0x002c }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzp()     // Catch:{ SQLiteException -> 0x002c }
            java.lang.String r5 = "No data found"
            r4.zza(r5)     // Catch:{ SQLiteException -> 0x002c }
            if (r3 == 0) goto L_0x0021
            r3.close()
        L_0x0021:
            return r0
        L_0x0022:
            java.lang.Object r4 = r5.zza(r3)     // Catch:{ SQLiteException -> 0x002c }
            if (r3 == 0) goto L_0x002b
            r3.close()
        L_0x002b:
            return r4
        L_0x002c:
            r4 = move-exception
            goto L_0x0032
        L_0x002e:
            r4 = move-exception
            goto L_0x0047
        L_0x0030:
            r4 = move-exception
            r3 = r0
        L_0x0032:
            com.google.android.gms.measurement.internal.zzfw r5 = r2.zzj()     // Catch:{ all -> 0x0045 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "Error querying database."
            r5.zza(r1, r4)     // Catch:{ all -> 0x0045 }
            if (r3 == 0) goto L_0x0044
            r3.close()
        L_0x0044:
            return r0
        L_0x0045:
            r4 = move-exception
            r0 = r3
        L_0x0047:
            if (r0 == 0) goto L_0x004c
            r0.close()
        L_0x004c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.String[], com.google.android.gms.measurement.internal.zzas):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(long r4) {
        /*
            r3 = this;
            r3.zzt()
            r3.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.e_()     // Catch:{ SQLiteException -> 0x0041, all -> 0x003f }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x0041, all -> 0x003f }
            java.lang.String[] r4 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x0041, all -> 0x003f }
            android.database.Cursor r4 = r1.rawQuery(r2, r4)     // Catch:{ SQLiteException -> 0x0041, all -> 0x003f }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x003d }
            if (r5 != 0) goto L_0x0032
            com.google.android.gms.measurement.internal.zzfw r5 = r3.zzj()     // Catch:{ SQLiteException -> 0x003d }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzp()     // Catch:{ SQLiteException -> 0x003d }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r5.zza(r1)     // Catch:{ SQLiteException -> 0x003d }
            if (r4 == 0) goto L_0x0031
            r4.close()
        L_0x0031:
            return r0
        L_0x0032:
            r5 = 0
            java.lang.String r5 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x003d }
            if (r4 == 0) goto L_0x003c
            r4.close()
        L_0x003c:
            return r5
        L_0x003d:
            r5 = move-exception
            goto L_0x0043
        L_0x003f:
            r5 = move-exception
            goto L_0x0058
        L_0x0041:
            r5 = move-exception
            r4 = r0
        L_0x0043:
            com.google.android.gms.measurement.internal.zzfw r1 = r3.zzj()     // Catch:{ all -> 0x0056 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch:{ all -> 0x0056 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r5)     // Catch:{ all -> 0x0056 }
            if (r4 == 0) goto L_0x0055
            r4.close()
        L_0x0055:
            return r0
        L_0x0056:
            r5 = move-exception
            r0 = r4
        L_0x0058:
            if (r0 == 0) goto L_0x005d
            r0.close()
        L_0x005d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String f_() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.e_()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.measurement.internal.zzfw r3 = r6.zzj()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzg()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.f_():java.lang.String");
    }

    private final String zza(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                String string = rawQuery.getString(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return string;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str2;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<Pair<zzfn.zzk, Long>> zza(String str, int i, int i2) {
        long j;
        long j2;
        int i3 = i2;
        zzt();
        zzal();
        int i4 = 1;
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i3 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = e_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", String.valueOf(i));
            if (!cursor.moveToFirst()) {
                List<Pair<zzfn.zzk, Long>> emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
            ArrayList arrayList = new ArrayList();
            int i5 = 0;
            while (true) {
                long j3 = cursor.getLong(0);
                try {
                    byte[] zzc2 = g_().zzc(cursor.getBlob(i4));
                    if (!arrayList.isEmpty() && zzc2.length + i5 > i3) {
                        break;
                    }
                    try {
                        zzfn.zzk.zza zza2 = (zzfn.zzk.zza) zznl.zza(zzfn.zzk.zzw(), zzc2);
                        if (!arrayList.isEmpty()) {
                            zzfn.zzk zzk2 = (zzfn.zzk) ((Pair) arrayList.get(0)).first;
                            zzfn.zzk zzk3 = (zzfn.zzk) ((zzjk) zza2.zzai());
                            if (!zzk2.zzae().equals(zzk3.zzae()) || !zzk2.zzad().equals(zzk3.zzad()) || zzk2.zzau() != zzk3.zzau() || !zzk2.zzaf().equals(zzk3.zzaf())) {
                                break;
                            }
                            Iterator<zzfn.zzo> it = zzk2.zzas().iterator();
                            while (true) {
                                j = -1;
                                if (!it.hasNext()) {
                                    j2 = -1;
                                    break;
                                }
                                zzfn.zzo next = it.next();
                                if ("_npa".equals(next.zzg())) {
                                    j2 = next.zzc();
                                    break;
                                }
                            }
                            Iterator<zzfn.zzo> it2 = zzk3.zzas().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                zzfn.zzo next2 = it2.next();
                                if ("_npa".equals(next2.zzg())) {
                                    j = next2.zzc();
                                    break;
                                }
                            }
                            if (j2 != j) {
                                break;
                            }
                        }
                        if (!cursor.isNull(2)) {
                            zza2.zzi(cursor.getInt(2));
                        }
                        i5 += zzc2.length;
                        arrayList.add(Pair.create((zzfn.zzk) ((zzjk) zza2.zzai()), Long.valueOf(j3)));
                    } catch (IOException e) {
                        zzj().zzg().zza("Failed to merge queued bundle. appId", zzfw.zza(str), e);
                    }
                    if (!cursor.moveToNext() || i5 > i3) {
                        break;
                    }
                    i4 = 1;
                } catch (IOException e2) {
                    zzj().zzg().zza("Failed to unzip queued bundle. appId", zzfw.zza(str), e2);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error querying bundles. appId", zzfw.zza(str), e3);
            List<Pair<zzfn.zzk, Long>> emptyList2 = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<zzae> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + ProxyConfig.MATCH_ALL_SCHEMES);
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzae> zza(String str, String[] strArr) {
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("conditional_properties", new String[]{"app_id", "origin", AppMeasurementSdk.ConditionalUserProperty.NAME, "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, (String) null, (String) null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza2 = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z = true;
                    }
                    String string4 = cursor.getString(5);
                    long j = cursor.getLong(6);
                    boolean z2 = z;
                    zzae zzae = r3;
                    zzae zzae2 = new zzae(string, string2, new zzno(string3, cursor.getLong(10), zza2, string2), cursor.getLong(8), z2, string4, (zzbd) g_().zza(cursor.getBlob(7), zzbd.CREATOR), j, (zzbd) g_().zza(cursor.getBlob(9), zzbd.CREATOR), cursor.getLong(11), (zzbd) g_().zza(cursor.getBlob(12), zzbd.CREATOR));
                    arrayList.add(zzae);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzj().zzg().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying conditional user property value", e);
            List<zzae> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<zzmu> zzj(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", FirebaseAnalytics.Param.SOURCE}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", (String) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            do {
                String string = cursor.getString(0);
                if (string == null) {
                    string = "";
                }
                arrayList.add(new zzmu(string, cursor.getLong(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying trigger uris. appId", zzfw.zza(str), e);
            List<zzmu> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final List<zznq> zzk(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("user_attributes", new String[]{AppMeasurementSdk.ConditionalUserProperty.NAME, "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", "1000");
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            do {
                String string = cursor.getString(0);
                String string2 = cursor.getString(1);
                if (string2 == null) {
                    string2 = "";
                }
                String str2 = string2;
                long j = cursor.getLong(2);
                Object zza2 = zza(cursor, 3);
                if (zza2 == null) {
                    zzj().zzg().zza("Read invalid user property value, ignoring it. appId", zzfw.zza(str));
                } else {
                    arrayList.add(new zznq(str, str2, string, j, zza2));
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying user properties. appId", zzfw.zza(str), e);
            List<zznq> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f6, code lost:
        r13 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00fd, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fe, code lost:
        r13 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0101, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0102, code lost:
        r13 = r22;
        r12 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x011f, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00fd A[ExcHandler: all (th java.lang.Throwable), PHI: r2 
      PHI: (r2v3 android.database.Cursor) = (r2v0 android.database.Cursor), (r2v0 android.database.Cursor), (r2v0 android.database.Cursor), (r2v5 android.database.Cursor), (r2v0 android.database.Cursor) binds: [B:1:0x0011, B:2:?, B:4:0x0019, B:19:0x0091, B:8:0x002b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zznq> zzb(java.lang.String r23, java.lang.String r24, java.lang.String r25) {
        /*
            r22 = this;
            r0 = r25
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r22.zzt()
            r22.zzal()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0101, all -> 0x00fd }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x0101, all -> 0x00fd }
            r12 = r23
            r3.add(r12)     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00fd }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00fd }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00fd }
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00fd }
            if (r6 != 0) goto L_0x0034
            r6 = r24
            r3.add(r6)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            goto L_0x0036
        L_0x0034:
            r6 = r24
        L_0x0036:
            boolean r7 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            if (r7 != 0) goto L_0x0055
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r7.<init>()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r7.append(r0)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r8 = "*"
            r7.append(r8)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r7 = r7.toString()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
        L_0x0055:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            android.database.sqlite.SQLiteDatabase r13 = r22.e_()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r14 = "user_attributes"
            java.lang.String r3 = "name"
            java.lang.String r7 = "set_timestamp"
            java.lang.String r8 = "value"
            java.lang.String r9 = "origin"
            java.lang.String[] r15 = new java.lang.String[]{r3, r7, r8, r9}     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "1001"
            r18 = 0
            r19 = 0
            android.database.Cursor r2 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            if (r3 != 0) goto L_0x0091
            if (r2 == 0) goto L_0x0090
            r2.close()
        L_0x0090:
            return r1
        L_0x0091:
            int r3 = r1.size()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r5) goto L_0x00ad
            com.google.android.gms.measurement.internal.zzfw r0 = r22.zzj()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            java.lang.String r3 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r0.zza(r3, r4)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r13 = r22
            goto L_0x00e8
        L_0x00ad:
            r3 = 0
            java.lang.String r8 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r3 = 1
            long r9 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00f5, all -> 0x00fd }
            r3 = 2
            r13 = r22
            java.lang.Object r11 = r13.zza((android.database.Cursor) r2, (int) r3)     // Catch:{ SQLiteException -> 0x00f3 }
            java.lang.String r3 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f3 }
            if (r11 != 0) goto L_0x00d6
            com.google.android.gms.measurement.internal.zzfw r5 = r22.zzj()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r23)     // Catch:{ SQLiteException -> 0x00f0 }
            r5.zza(r6, r7, r3, r0)     // Catch:{ SQLiteException -> 0x00f0 }
            goto L_0x00e2
        L_0x00d6:
            com.google.android.gms.measurement.internal.zznq r14 = new com.google.android.gms.measurement.internal.zznq     // Catch:{ SQLiteException -> 0x00f0 }
            r5 = r14
            r6 = r23
            r7 = r3
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x00f0 }
            r1.add(r14)     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00e2:
            boolean r5 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r5 != 0) goto L_0x00ee
        L_0x00e8:
            if (r2 == 0) goto L_0x00ed
            r2.close()
        L_0x00ed:
            return r1
        L_0x00ee:
            r6 = r3
            goto L_0x0091
        L_0x00f0:
            r0 = move-exception
            r6 = r3
            goto L_0x0108
        L_0x00f3:
            r0 = move-exception
            goto L_0x0108
        L_0x00f5:
            r0 = move-exception
            r13 = r22
            goto L_0x0108
        L_0x00f9:
            r0 = move-exception
            r13 = r22
            goto L_0x0106
        L_0x00fd:
            r0 = move-exception
            r13 = r22
            goto L_0x0124
        L_0x0101:
            r0 = move-exception
            r13 = r22
            r12 = r23
        L_0x0106:
            r6 = r24
        L_0x0108:
            com.google.android.gms.measurement.internal.zzfw r1 = r22.zzj()     // Catch:{ all -> 0x0123 }
            com.google.android.gms.measurement.internal.zzfy r1 = r1.zzg()     // Catch:{ all -> 0x0123 }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r23)     // Catch:{ all -> 0x0123 }
            r1.zza(r3, r4, r6, r0)     // Catch:{ all -> 0x0123 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0123 }
            if (r2 == 0) goto L_0x0122
            r2.close()
        L_0x0122:
            return r0
        L_0x0123:
            r0 = move-exception
        L_0x0124:
            if (r2 == 0) goto L_0x0129
            r2.close()
        L_0x0129:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, zzfn.zzm> zzl(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = e_().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, zzfn.zzm> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            ArrayMap arrayMap = new ArrayMap();
            do {
                int i = cursor.getInt(0);
                try {
                    arrayMap.put(Integer.valueOf(i), (zzfn.zzm) ((zzjk) ((zzfn.zzm.zza) zznl.zza(zzfn.zzm.zze(), cursor.getBlob(1))).zzai()));
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter results. appId, audienceId, error", zzfw.zza(str), Integer.valueOf(i), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filter results. appId", zzfw.zza(str), e2);
            Map<Integer, zzfn.zzm> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzff.zzb>> zzm(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzff.zzb>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzff.zzb zzb2 = (zzff.zzb) ((zzjk) ((zzff.zzb.zza) zznl.zza(zzff.zzb.zzc(), cursor.getBlob(1))).zzai());
                    if (zzb2.zzk()) {
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zzb2);
                    }
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzfw.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzfw.zza(str), e2);
            Map<Integer, List<zzff.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzff.zzb>> zzf(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzff.zzb>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzff.zzb zzb2 = (zzff.zzb) ((zzjk) ((zzff.zzb.zza) zznl.zza(zzff.zzb.zzc(), cursor.getBlob(1))).zzai());
                    int i = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), list);
                    }
                    list.add(zzb2);
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzfw.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzfw.zza(str), e2);
            Map<Integer, List<zzff.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzff.zze>> zzg(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzff.zze>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzff.zze zze2 = (zzff.zze) ((zzjk) ((zzff.zze.zza) zznl.zza(zzff.zze.zzc(), cursor.getBlob(1))).zzai());
                    int i = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), list);
                    }
                    list.add(zze2);
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter", zzfw.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzfw.zza(str), e2);
            Map<Integer, List<zzff.zze>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<Integer>> zzn(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
            if (!cursor.moveToFirst()) {
                Map<Integer, List<Integer>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                int i = cursor.getInt(0);
                List list = (List) arrayMap.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList();
                    arrayMap.put(Integer.valueOf(i), list);
                }
                list.add(Integer.valueOf(cursor.getInt(1)));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error querying scoped filters. appId", zzfw.zza(str), e);
            Map<Integer, List<Integer>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    zzal(zznc zznc) {
        super(zznc);
    }

    public final void zzp() {
        zzal();
        e_().beginTransaction();
    }

    private final void zzi(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting snapshot. appId", zzfw.zza(str2), e);
        }
    }

    public final void zzo(String str) {
        zzaz zzd2;
        zzi("events_snapshot", str);
        Cursor cursor = null;
        try {
            cursor = e_().query("events", (String[]) Collections.singletonList(AppMeasurementSdk.ConditionalUserProperty.NAME).toArray(new String[0]), "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(0);
                    if (!(string == null || (zzd2 = zzd(str, string)) == null)) {
                        zza("events_snapshot", zzd2);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error creating snapshot. appId", zzfw.zza(str), e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zzu() {
        zzal();
        e_().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final void zza(List<Long> list) {
        zzt();
        zzal();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzaa()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzb("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                e_().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzv() {
        int delete;
        zzt();
        zzal();
        if (zzaa()) {
            long zza2 = zzn().zza.zza();
            long elapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza2) > zzbf.zzy.zza(null).longValue()) {
                zzn().zza.zza(elapsedRealtime);
                zzt();
                zzal();
                if (zzaa() && (delete = e_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzb().currentTimeMillis()), String.valueOf(zzag.zzm())})) > 0) {
                    zzj().zzp().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting user property. appId", zzfw.zza(str), zzi().zzc(str2), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009a, code lost:
        if ("_v".equals(r0) != false) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzp(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.String r3 = "events_snapshot"
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r4 = "name"
            java.lang.String r5 = "lifetime_count"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}
            java.util.List r4 = java.util.Arrays.asList(r4)
            r0.<init>(r4)
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzaz r5 = r1.zzd(r2, r4)
            java.lang.String r6 = "_v"
            com.google.android.gms.measurement.internal.zzaz r7 = r1.zzd(r2, r6)
            java.lang.String r8 = "events"
            r1.zzi(r8, r2)
            r9 = 0
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.e_()     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.String r12 = "events_snapshot"
            java.lang.String[] r13 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.Object[] r0 = r0.toArray(r13)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            r13 = r0
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.String r14 = "app_id=?"
            java.lang.String[] r15 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            r17 = 0
            r18 = 0
            r16 = 0
            android.database.Cursor r10 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            if (r0 != 0) goto L_0x0063
            if (r10 == 0) goto L_0x0054
            r10.close()
        L_0x0054:
            if (r5 == 0) goto L_0x005a
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r5)
            goto L_0x005f
        L_0x005a:
            if (r7 == 0) goto L_0x005f
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r7)
        L_0x005f:
            r1.zzi(r3, r2)
            return
        L_0x0063:
            r11 = r9
            r12 = r11
        L_0x0065:
            java.lang.String r0 = r10.getString(r9)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            com.google.android.gms.measurement.internal.zzag r13 = r19.zze()     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbf.zzcx     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r14)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            r14 = 1
            if (r13 == 0) goto L_0x008e
            long r15 = r10.getLong(r14)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            r17 = 1
            int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r13 < 0) goto L_0x009d
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x0087
            goto L_0x0094
        L_0x0087:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x009d
            goto L_0x009c
        L_0x008e:
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x0096
        L_0x0094:
            r11 = r14
            goto L_0x009d
        L_0x0096:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x009d
        L_0x009c:
            r12 = r14
        L_0x009d:
            if (r0 == 0) goto L_0x00a8
            com.google.android.gms.measurement.internal.zzaz r0 = r1.zzc(r3, r2, r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r0 == 0) goto L_0x00a8
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
        L_0x00a8:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r0 != 0) goto L_0x0065
            if (r10 == 0) goto L_0x00b3
            r10.close()
        L_0x00b3:
            if (r11 != 0) goto L_0x00bb
            if (r5 == 0) goto L_0x00bb
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r5)
            goto L_0x00c2
        L_0x00bb:
            if (r12 != 0) goto L_0x00c2
            if (r7 == 0) goto L_0x00c2
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r7)
        L_0x00c2:
            r1.zzi(r3, r2)
            return
        L_0x00c6:
            r0 = move-exception
            r9 = r11
            goto L_0x00fb
        L_0x00c9:
            r0 = move-exception
            r9 = r11
            goto L_0x00d1
        L_0x00cc:
            r0 = move-exception
            r12 = r9
            goto L_0x00fb
        L_0x00cf:
            r0 = move-exception
            r12 = r9
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzfw r4 = r19.zzj()     // Catch:{ all -> 0x00fa }
            com.google.android.gms.measurement.internal.zzfy r4 = r4.zzg()     // Catch:{ all -> 0x00fa }
            java.lang.String r6 = "Error querying snapshot. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r20)     // Catch:{ all -> 0x00fa }
            r4.zza(r6, r11, r0)     // Catch:{ all -> 0x00fa }
            if (r10 == 0) goto L_0x00e7
            r10.close()
        L_0x00e7:
            if (r9 != 0) goto L_0x00ef
            if (r5 == 0) goto L_0x00ef
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r5)
            goto L_0x00f6
        L_0x00ef:
            if (r12 != 0) goto L_0x00f6
            if (r7 == 0) goto L_0x00f6
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r7)
        L_0x00f6:
            r1.zzi(r3, r2)
            return
        L_0x00fa:
            r0 = move-exception
        L_0x00fb:
            if (r10 == 0) goto L_0x0100
            r10.close()
        L_0x0100:
            if (r9 != 0) goto L_0x0109
            if (r5 != 0) goto L_0x0105
            goto L_0x0109
        L_0x0105:
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r5)
            goto L_0x0110
        L_0x0109:
            if (r12 != 0) goto L_0x0110
            if (r7 == 0) goto L_0x0110
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzaz) r7)
        L_0x0110:
            r1.zzi(r3, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzp(java.lang.String):void");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, List<zzff.zza> list) {
        boolean z;
        boolean z2;
        String str2 = str;
        List<zzff.zza> list2 = list;
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            zzjk.zzb zzcc = list2.get(i).zzcc();
            zzjk.zzb zzb2 = zzcc;
            zzff.zza.C0007zza zza2 = (zzff.zza.C0007zza) zzcc;
            if (zza2.zza() != 0) {
                for (int i2 = 0; i2 < zza2.zza(); i2++) {
                    zzjk.zzb zzcc2 = zza2.zza(i2).zzcc();
                    zzjk.zzb zzb3 = zzcc2;
                    zzff.zzb.zza zza3 = (zzff.zzb.zza) zzcc2;
                    zzff.zzb.zza zza4 = (zzff.zzb.zza) ((zzjk.zzb) zza3.clone());
                    String zzb4 = zziq.zzb(zza3.zzb());
                    if (zzb4 != null) {
                        zza4.zza(zzb4);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    for (int i3 = 0; i3 < zza3.zza(); i3++) {
                        zzff.zzc zza5 = zza3.zza(i3);
                        String zza6 = zzip.zza(zza5.zze());
                        if (zza6 != null) {
                            zzjk.zzb zzcc3 = zza5.zzcc();
                            zzjk.zzb zzb5 = zzcc3;
                            zza4.zza(i3, (zzff.zzc) ((zzjk) ((zzff.zzc.zza) zzcc3).zza(zza6).zzai()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        zza2 = zza2.zza(i2, zza4);
                        list2.set(i, (zzff.zza) ((zzjk) zza2.zzai()));
                    }
                }
            }
            if (zza2.zzb() != 0) {
                for (int i4 = 0; i4 < zza2.zzb(); i4++) {
                    zzff.zze zzb6 = zza2.zzb(i4);
                    String zza7 = zzis.zza(zzb6.zze());
                    if (zza7 != null) {
                        zzjk.zzb zzcc4 = zzb6.zzcc();
                        zzjk.zzb zzb7 = zzcc4;
                        zza2 = zza2.zza(i4, ((zzff.zze.zza) zzcc4).zza(zza7));
                        list2.set(i, (zzff.zza) ((zzjk) zza2.zzai()));
                    }
                }
            }
        }
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        try {
            zzal();
            zzt();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase e_2 = e_();
            e_2.delete("property_filters", "app_id=?", new String[]{str});
            e_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzff.zza next : list) {
                zzal();
                zzt();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(next);
                if (!next.zzg()) {
                    zzj().zzu().zza("Audience with no ID. appId", zzfw.zza(str));
                } else {
                    int zza8 = next.zza();
                    Iterator<zzff.zzb> it = next.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zzl()) {
                                zzj().zzu().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzfw.zza(str), Integer.valueOf(zza8));
                                break;
                            }
                        } else {
                            Iterator<zzff.zze> it2 = next.zzf().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zzi()) {
                                        zzj().zzu().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzfw.zza(str), Integer.valueOf(zza8));
                                        break;
                                    }
                                } else {
                                    Iterator<zzff.zzb> it3 = next.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str2, zza8, it3.next())) {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<zzff.zze> it4 = next.zzf().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str2, zza8, it4.next())) {
                                                    z = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzal();
                                        zzt();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase e_3 = e_();
                                        e_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza8)});
                                        e_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza8)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzff.zza next2 : list) {
                arrayList.add(next2.zzg() ? Integer.valueOf(next2.zza()) : null);
            }
            zzb(str2, (List<Integer>) arrayList);
            e_.setTransactionSuccessful();
        } finally {
            e_.endTransaction();
        }
    }

    public final void zzw() {
        zzal();
        e_().setTransactionSuccessful();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0043, code lost:
        if (r7.zzf.zzb(r0).zza(com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE) != false) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x02b9  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x034a A[Catch:{ SQLiteException -> 0x035c }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzg r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r10 = "apps"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            r7.zzt()
            r7.zzal()
            java.lang.String r0 = r8.zzac()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "app_id"
            r1.put(r2, r0)
            boolean r2 = com.google.android.gms.internal.measurement.zznk.zza()
            java.lang.String r3 = "app_instance_id"
            r4 = 0
            if (r2 == 0) goto L_0x0045
            com.google.android.gms.measurement.internal.zzag r2 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbf.zzcv
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r5)
            if (r2 == 0) goto L_0x0045
            if (r9 == 0) goto L_0x0037
            r1.put(r3, r4)
            goto L_0x004c
        L_0x0037:
            com.google.android.gms.measurement.internal.zznc r9 = r7.zzf
            com.google.android.gms.measurement.internal.zzin r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzin$zza r2 = com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzin.zza) r2)
            if (r9 == 0) goto L_0x004c
        L_0x0045:
            java.lang.String r9 = r8.zzad()
            r1.put(r3, r9)
        L_0x004c:
            java.lang.String r9 = "gmp_app_id"
            java.lang.String r2 = r8.zzah()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zznk.zza()
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzcv
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zznc r9 = r7.zzf
            com.google.android.gms.measurement.internal.zzin r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzin$zza r2 = com.google.android.gms.measurement.internal.zzin.zza.AD_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzin.zza) r2)
            if (r9 == 0) goto L_0x007e
        L_0x0075:
            java.lang.String r9 = "resettable_device_id_hash"
            java.lang.String r2 = r8.zzaj()
            r1.put(r9, r2)
        L_0x007e:
            long r2 = r8.zzt()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_index"
            r1.put(r2, r9)
            long r2 = r8.zzu()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_start_timestamp"
            r1.put(r2, r9)
            long r2 = r8.zzs()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_end_timestamp"
            r1.put(r2, r9)
            java.lang.String r9 = "app_version"
            java.lang.String r2 = r8.zzaf()
            r1.put(r9, r2)
            java.lang.String r9 = "app_store"
            java.lang.String r2 = r8.zzae()
            r1.put(r9, r2)
            long r2 = r8.zzq()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "gmp_version"
            r1.put(r2, r9)
            long r2 = r8.zzn()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dev_cert_hash"
            r1.put(r2, r9)
            boolean r9 = r8.zzar()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "measurement_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzm()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "day"
            r1.put(r2, r9)
            long r2 = r8.zzk()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_public_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzj()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzh()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_conversions_count"
            r1.put(r2, r9)
            long r2 = r8.zzg()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "config_fetched_time"
            r1.put(r2, r9)
            long r2 = r8.zzp()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "failed_config_fetch_time"
            r1.put(r2, r9)
            long r2 = r8.zze()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "app_version_int"
            r1.put(r2, r9)
            java.lang.String r9 = "firebase_instance_id"
            java.lang.String r2 = r8.zzag()
            r1.put(r9, r2)
            long r2 = r8.zzi()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_error_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzl()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_realtime_events_count"
            r1.put(r2, r9)
            java.lang.String r9 = "health_monitor_sample"
            java.lang.String r2 = r8.zzai()
            r1.put(r9, r2)
            long r2 = r8.zzd()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "android_id"
            r1.put(r2, r9)
            boolean r9 = r8.zzaq()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "adid_reporting_enabled"
            r1.put(r2, r9)
            java.lang.String r9 = "admob_app_id"
            java.lang.String r2 = r8.zzaa()
            r1.put(r9, r2)
            long r2 = r8.zzo()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dynamite_version"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zznk.zza()
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzcv
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zznc r9 = r7.zzf
            com.google.android.gms.measurement.internal.zzin r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzin$zza r2 = com.google.android.gms.measurement.internal.zzin.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzin.zza) r2)
            if (r9 == 0) goto L_0x01be
        L_0x01b5:
            java.lang.String r9 = "session_stitching_token"
            java.lang.String r2 = r8.zzal()
            r1.put(r9, r2)
        L_0x01be:
            boolean r9 = r8.zzat()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "sgtm_upload_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzw()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "target_os_version"
            r1.put(r2, r9)
            long r2 = r8.zzv()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "session_stitching_token_hash"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzpg.zza()
            if (r9 == 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzbz
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0211
            int r9 = r8.zza()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "ad_services_version"
            r1.put(r2, r9)
            long r2 = r8.zzf()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "attribution_eligibility_status"
            r1.put(r2, r9)
        L_0x0211:
            boolean r9 = com.google.android.gms.internal.measurement.zznl.zza()
            if (r9 == 0) goto L_0x0230
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzck
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0230
            boolean r9 = r8.zzau()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "unmatched_first_open_without_ad_id"
            r1.put(r2, r9)
        L_0x0230:
            java.lang.String r9 = "npa_metadata_value"
            java.lang.Boolean r2 = r8.zzx()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r9 == 0) goto L_0x0261
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzbs
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0261
            r7.zzq()
            boolean r9 = com.google.android.gms.measurement.internal.zznp.zzf(r0)
            if (r9 == 0) goto L_0x0261
            long r2 = r8.zzr()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "bundle_delivery_index"
            r1.put(r2, r9)
        L_0x0261:
            boolean r9 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r9 == 0) goto L_0x027c
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzbt
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x027c
            java.lang.String r9 = "sgtm_preview_key"
            java.lang.String r2 = r8.zzam()
            r1.put(r9, r2)
        L_0x027c:
            int r9 = r8.zzc()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "dma_consent_state"
            r1.put(r2, r9)
            int r9 = r8.zzb()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "daily_realtime_dcu_count"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzne.zza()
            if (r9 == 0) goto L_0x02b1
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzcp
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x02b1
            java.lang.String r9 = "serialized_npa_metadata"
            java.lang.String r2 = r8.zzak()
            r1.put(r9, r2)
        L_0x02b1:
            java.util.List r9 = r8.zzan()
            java.lang.String r2 = "safelisted_events"
            if (r9 == 0) goto L_0x02d6
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto L_0x02cd
            com.google.android.gms.measurement.internal.zzfw r9 = r7.zzj()
            com.google.android.gms.measurement.internal.zzfy r9 = r9.zzu()
            java.lang.String r3 = "Safelisted events should not be an empty list. appId"
            r9.zza(r3, r0)
            goto L_0x02d6
        L_0x02cd:
            java.lang.String r3 = ","
            java.lang.String r9 = android.text.TextUtils.join(r3, r9)
            r1.put(r2, r9)
        L_0x02d6:
            boolean r9 = com.google.android.gms.internal.measurement.zznw.zza()
            if (r9 == 0) goto L_0x02f1
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbf.zzbp
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r3)
            if (r9 == 0) goto L_0x02f1
            boolean r9 = r1.containsKey(r2)
            if (r9 != 0) goto L_0x02f1
            r1.put(r2, r4)
        L_0x02f1:
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzcs
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x030f
            java.lang.String r9 = "unmatched_pfo"
            java.lang.Long r2 = r8.zzy()
            r1.put(r9, r2)
            java.lang.String r9 = "unmatched_uwa"
            java.lang.Long r2 = r8.zzz()
            r1.put(r9, r2)
        L_0x030f:
            boolean r9 = com.google.android.gms.internal.measurement.zzoj.zza()
            if (r9 == 0) goto L_0x032a
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfj<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbf.zzcm
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x032a
            java.lang.String r9 = "ad_campaign_info"
            byte[] r8 = r8.zzav()
            r1.put(r9, r8)
        L_0x032a:
            android.database.sqlite.SQLiteDatabase r8 = r7.e_()     // Catch:{ SQLiteException -> 0x035c }
            java.lang.String r9 = "app_id = ?"
            java.lang.String[] r2 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x035c }
            int r9 = r8.update(r10, r1, r9, r2)     // Catch:{ SQLiteException -> 0x035c }
            long r2 = (long) r9     // Catch:{ SQLiteException -> 0x035c }
            r5 = 0
            int r9 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x035b
            r9 = 5
            long r8 = r8.insertWithOnConflict(r10, r4, r1, r9)     // Catch:{ SQLiteException -> 0x035c }
            r1 = -1
            int r8 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r8 != 0) goto L_0x035b
            com.google.android.gms.measurement.internal.zzfw r8 = r7.zzj()     // Catch:{ SQLiteException -> 0x035c }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x035c }
            java.lang.String r9 = "Failed to insert/update app (got -1). appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x035c }
            r8.zza(r9, r10)     // Catch:{ SQLiteException -> 0x035c }
        L_0x035b:
            return
        L_0x035c:
            r8 = move-exception
            com.google.android.gms.measurement.internal.zzfw r9 = r7.zzj()
            com.google.android.gms.measurement.internal.zzfy r9 = r9.zzg()
            java.lang.String r10 = "Error storing app. appId"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzfw.zza((java.lang.String) r0)
            r9.zza(r10, r0, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(com.google.android.gms.measurement.internal.zzg, boolean, boolean):void");
    }

    public final void zza(String str, zzav zzav) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzav);
        zzt();
        zzal();
        if (zze().zza(zzbf.zzcj) && zzi(str) == zzin.zza) {
            zzb(str, zzin.zza);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzav.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zza(zzaz zzaz) {
        zza("events", zzaz);
    }

    private final void zza(String str, zzaz zzaz) {
        Preconditions.checkNotNull(zzaz);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzaz.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzaz.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzaz.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzaz.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzaz.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzaz.zzg));
        contentValues.put("last_bundled_day", zzaz.zzh);
        contentValues.put("last_sampled_complex_event_id", zzaz.zzi);
        contentValues.put("last_sampling_rate", zzaz.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzaz.zze));
        contentValues.put("last_exempt_from_sampling", (zzaz.zzk == null || !zzaz.zzk.booleanValue()) ? null : 1L);
        try {
            if (e_().insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update event aggregates (got -1). appId", zzfw.zza(zzaz.zza));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event aggregates. appId", zzfw.zza(zzaz.zza), e);
        }
    }

    private final void zza(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase e_ = e_();
            String asString = contentValues.getAsString(str2);
            if (asString == null) {
                zzj().zzh().zza("Value of the primary key is not set.", zzfw.zza(str2));
                return;
            }
            if (((long) e_.update(str, contentValues, str2 + " = ?", new String[]{asString})) == 0 && e_.insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update table (got -1). key", zzfw.zza(str), zzfw.zza(str2));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing into table. key", zzfw.zza(str), zzfw.zza(str2), e);
        }
    }

    public final void zza(String str, zzin zzin) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzin);
        zzt();
        zzal();
        zzb(str, zzi(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzin.zzh());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zzb(String str, zzin zzin) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzin);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzin.zzh());
        contentValues.put("consent_source", Integer.valueOf(zzin.zza()));
        zza("consent_settings", "app_id", contentValues);
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzal();
        zzt();
        SQLiteDatabase e_ = e_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zze().zzb(str, zzbf.zzaf)));
            if (zzb2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            if (e_.delete("audience_filter_values", "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in " + ("(" + TextUtils.join(",", arrayList) + ")") + " order by rowid desc limit -1 offset ?)", new String[]{str, Integer.toString(max)}) > 0) {
                return true;
            }
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error querying filters. appId", zzfw.zza(str), e);
            return false;
        }
    }

    public final boolean zzx() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final boolean zza(zzfn.zzk zzk2, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        Preconditions.checkState(zzk2.zzbi());
        zzv();
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zzk2.zzm() < currentTimeMillis - zzag.zzm() || zzk2.zzm() > zzag.zzm() + currentTimeMillis) {
            zzj().zzu().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfw.zza(zzk2.zzz()), Long.valueOf(currentTimeMillis), Long.valueOf(zzk2.zzm()));
        }
        try {
            byte[] zzb2 = g_().zzb(zzk2.zzbz());
            zzj().zzp().zza("Saving bundle, size", Integer.valueOf(zzb2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzk2.zzz());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzk2.zzm()));
            contentValues.put("data", zzb2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzk2.zzbp()) {
                contentValues.put("retry_count", Integer.valueOf(zzk2.zzg()));
            }
            try {
                if (e_().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzj().zzg().zza("Failed to insert bundle (got -1). appId", zzfw.zza(zzk2.zzz()));
                return false;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error storing bundle. appId", zzfw.zza(zzk2.zzz()), e);
                return false;
            }
        } catch (IOException e2) {
            zzj().zzg().zza("Data loss. Failed to serialize bundle. appId", zzfw.zza(zzk2.zzz()), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzff.zzb zzb2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzb2);
        Integer num = null;
        if (zzb2.zzf().isEmpty()) {
            zzfy zzu = zzj().zzu();
            Object zza2 = zzfw.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzb2.zzl()) {
                num = Integer.valueOf(zzb2.zzb());
            }
            zzu.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbz = zzb2.zzbz();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzb2.zzl() ? Integer.valueOf(zzb2.zzb()) : null);
        contentValues.put("event_name", zzb2.zzf());
        contentValues.put("session_scoped", zzb2.zzm() ? Boolean.valueOf(zzb2.zzj()) : null);
        contentValues.put("data", zzbz);
        try {
            if (e_().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert event filter (got -1). appId", zzfw.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event filter. appId", zzfw.zza(str), e);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzff.zze zze2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zze2);
        Integer num = null;
        if (zze2.zze().isEmpty()) {
            zzfy zzu = zzj().zzu();
            Object zza2 = zzfw.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zze2.zzi()) {
                num = Integer.valueOf(zze2.zza());
            }
            zzu.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzbz = zze2.zzbz();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zze2.zzi() ? Integer.valueOf(zze2.zza()) : null);
        contentValues.put("property_name", zze2.zze());
        contentValues.put("session_scoped", zze2.zzj() ? Boolean.valueOf(zze2.zzh()) : null);
        contentValues.put("data", zzbz);
        try {
            if (e_().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert property filter (got -1). appId", zzfw.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing property filter. appId", zzfw.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzba zzba, long j, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzba);
        Preconditions.checkNotEmpty(zzba.zza);
        byte[] zzbz = g_().zza(zzba).zzbz();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzba.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzba.zzb);
        contentValues.put("timestamp", Long.valueOf(zzba.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", zzbz);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (e_().insert("raw_events", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert raw event (got -1). appId", zzfw.zza(zzba.zza));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event. appId", zzfw.zza(zzba.zza), e);
            return false;
        }
    }

    public final boolean zza(String str, zzmu zzmu) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzmu);
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zzmu.zzb < currentTimeMillis - zzag.zzm() || zzmu.zzb > zzag.zzm() + currentTimeMillis) {
            zzj().zzu().zza("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzfw.zza(str), Long.valueOf(currentTimeMillis), Long.valueOf(zzmu.zzb));
        }
        zzj().zzp().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zzmu.zza);
        contentValues.put(FirebaseAnalytics.Param.SOURCE, Integer.valueOf(zzmu.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(zzmu.zzb));
        try {
            if (e_().insert("trigger_uris", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert trigger URI (got -1). appId", zzfw.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing trigger URI. appId", zzfw.zza(str), e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzaa() {
        return zza().getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zza(String str, Long l, long j, zzfn.zzf zzf) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzf);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbz = zzf.zzbz();
        zzj().zzp().zza("Saving complex main event, appId, data size", zzi().zza(str), Integer.valueOf(zzbz.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbz);
        try {
            if (e_().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert complex main event (got -1). appId", zzfw.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing complex main event. appId", zzfw.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzt();
        zzal();
        String str = zzae.zza;
        Preconditions.checkNotNull(str);
        if (zze(str, zzae.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzae.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzae.zzc.zza);
        zza(contentValues, "value", Preconditions.checkNotNull(zzae.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzae.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzae.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzae.zzh));
        zzq();
        contentValues.put("timed_out_event", zznp.zza((Parcelable) zzae.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzae.zzd));
        zzq();
        contentValues.put("triggered_event", zznp.zza((Parcelable) zzae.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzae.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzae.zzj));
        zzq();
        contentValues.put("expired_event", zznp.zza((Parcelable) zzae.zzk));
        try {
            if (e_().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update conditional user property (got -1)", zzfw.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing conditional user property", zzfw.zza(str), e);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(String str, Bundle bundle) {
        zzt();
        zzal();
        byte[] zzbz = g_().zza(new zzba(this.zzu, "", str, "dep", 0, 0, bundle)).zzbz();
        zzj().zzp().zza("Saving default event parameters, appId, data size", zzi().zza(str), Integer.valueOf(zzbz.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzbz);
        try {
            if (e_().insertWithOnConflict("default_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert default event parameters (got -1). appId", zzfw.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing default event parameters. appId", zzfw.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zznq zznq) {
        Preconditions.checkNotNull(zznq);
        zzt();
        zzal();
        if (zze(zznq.zza, zznq.zzc) == null) {
            if (zznp.zzh(zznq.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zznq.zza}) >= ((long) zze().zza(zznq.zza, zzbf.zzag, 25, 100))) {
                    return false;
                }
            } else if (!"_npa".equals(zznq.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zznq.zza, zznq.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zznq.zza);
        contentValues.put("origin", zznq.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zznq.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zznq.zzd));
        zza(contentValues, "value", zznq.zze);
        try {
            if (e_().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update user property (got -1). appId", zzfw.zza(zznq.zza));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing user property. appId", zzfw.zza(zznq.zza), e);
            return true;
        }
    }
}

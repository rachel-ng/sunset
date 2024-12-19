package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzfp extends zze {
    private final zzfs zza = new zzfs(this, zza(), "google_app_measurement_local.db");
    private boolean zzb;

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, (String) null, (String) null, "rowid desc", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final SQLiteDatabase zzad() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzfq zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzfp zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zziv zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzks zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzmh zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:60|61|62|63) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:75|76|77|78) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:47|48|49|50|175) */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x018c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0193, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        zzj().zzg().zza("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r11.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        zzj().zzg().zza("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        zzj().zzg().zza("Failed to load conditional user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00a1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:60:0x00d1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0107 */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x018c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01a3 A[SYNTHETIC, Splitter:B:125:0x01a3] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x01f1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r22) {
        /*
            r21 = this;
            r1 = r21
            java.lang.String r2 = "Error reading entries from local database"
            r21.zzt()
            boolean r0 = r1.zzb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r0 = r21.zzae()
            if (r0 != 0) goto L_0x0019
            return r4
        L_0x0019:
            r5 = 5
            r6 = 0
            r8 = r5
            r7 = r6
        L_0x001d:
            if (r7 >= r5) goto L_0x0204
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r21.zzad()     // Catch:{ SQLiteFullException -> 0x01d7, SQLiteDatabaseLockedException -> 0x01c4, SQLiteException -> 0x019e, all -> 0x019b }
            if (r15 != 0) goto L_0x0034
            r1.zzb = r9     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            if (r15 == 0) goto L_0x002d
            r15.close()
        L_0x002d:
            return r3
        L_0x002e:
            r0 = move-exception
            goto L_0x0191
        L_0x0031:
            r0 = move-exception
            goto L_0x0199
        L_0x0034:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            long r10 = zza((android.database.sqlite.SQLiteDatabase) r15)     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            r19 = -1
            int r0 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x004e
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            r12[r6] = r10     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            r13 = r0
            r14 = r12
            goto L_0x0050
        L_0x004e:
            r13 = r3
            r14 = r13
        L_0x0050:
            java.lang.String r11 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r10 = "type"
            java.lang.String r12 = "entry"
            java.lang.String[] r12 = new java.lang.String[]{r0, r10, r12}     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            java.lang.String r17 = "rowid asc"
            r0 = 100
            java.lang.String r18 = java.lang.Integer.toString(r0)     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            r0 = 0
            r16 = 0
            r10 = r15
            r5 = r15
            r15 = r0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x0187, SQLiteDatabaseLockedException -> 0x0194, SQLiteException -> 0x0183, all -> 0x0180 }
        L_0x006e:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 == 0) goto L_0x0144
            long r19 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r11 = 2
            byte[] r12 = r10.getBlob(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 != 0) goto L_0x00b6
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00a1 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00a1 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00a1 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbd> r0 = com.google.android.gms.measurement.internal.zzbd.CREATOR     // Catch:{ ParseException -> 0x00a1 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00a1 }
            com.google.android.gms.measurement.internal.zzbd r0 = (com.google.android.gms.measurement.internal.zzbd) r0     // Catch:{ ParseException -> 0x00a1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x009f:
            r0 = move-exception
            goto L_0x00b2
        L_0x00a1:
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ all -> 0x009f }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x009f }
            java.lang.String r12 = "Failed to load event from local database"
            r0.zza(r12)     // Catch:{ all -> 0x009f }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x00b2:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x00b6:
            if (r0 != r9) goto L_0x00ec
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00d1 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00d1 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00d1 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzno> r0 = com.google.android.gms.measurement.internal.zzno.CREATOR     // Catch:{ ParseException -> 0x00d1 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00d1 }
            com.google.android.gms.measurement.internal.zzno r0 = (com.google.android.gms.measurement.internal.zzno) r0     // Catch:{ ParseException -> 0x00d1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x00e2
        L_0x00cf:
            r0 = move-exception
            goto L_0x00e8
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ all -> 0x00cf }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x00cf }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x00cf }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r0 = r3
        L_0x00e2:
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x00e8:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x00ec:
            if (r0 != r11) goto L_0x0123
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x0107 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x0107 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x0107 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzae> r0 = com.google.android.gms.measurement.internal.zzae.CREATOR     // Catch:{ ParseException -> 0x0107 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x0107 }
            com.google.android.gms.measurement.internal.zzae r0 = (com.google.android.gms.measurement.internal.zzae) r0     // Catch:{ ParseException -> 0x0107 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x0118
        L_0x0105:
            r0 = move-exception
            goto L_0x011f
        L_0x0107:
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ all -> 0x0105 }
            java.lang.String r12 = "Failed to load conditional user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x0105 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r0 = r3
        L_0x0118:
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x011f:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x0123:
            r11 = 3
            if (r0 != r11) goto L_0x0135
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Skipping app launch break"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x0135:
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Unknown record type in local database"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x0144:
            java.lang.String r0 = "messages"
            java.lang.String r11 = "rowid <= ?"
            java.lang.String r12 = java.lang.Long.toString(r19)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String[] r12 = new java.lang.String[]{r12}     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r5.delete(r0, r11, r12)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r11 = r4.size()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 >= r11) goto L_0x0167
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Fewer entries removed from local database than expected"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x0167:
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r10 == 0) goto L_0x0172
            r10.close()
        L_0x0172:
            if (r5 == 0) goto L_0x0177
            r5.close()
        L_0x0177:
            return r4
        L_0x0178:
            r0 = move-exception
            r3 = r10
            goto L_0x01f9
        L_0x017c:
            r0 = move-exception
            goto L_0x0185
        L_0x017e:
            r0 = move-exception
            goto L_0x0189
        L_0x0180:
            r0 = move-exception
            goto L_0x01f9
        L_0x0183:
            r0 = move-exception
            r10 = r3
        L_0x0185:
            r15 = r5
            goto L_0x01a1
        L_0x0187:
            r0 = move-exception
            r10 = r3
        L_0x0189:
            r15 = r5
            goto L_0x01da
        L_0x018c:
            r0 = move-exception
            goto L_0x01f8
        L_0x018f:
            r0 = move-exception
            r5 = r15
        L_0x0191:
            r10 = r3
            goto L_0x01a1
        L_0x0193:
            r5 = r15
        L_0x0194:
            r10 = r3
        L_0x0195:
            r15 = r5
            goto L_0x01c6
        L_0x0197:
            r0 = move-exception
            r5 = r15
        L_0x0199:
            r10 = r3
            goto L_0x01da
        L_0x019b:
            r0 = move-exception
            r5 = r3
            goto L_0x01f9
        L_0x019e:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01a1:
            if (r15 == 0) goto L_0x01ac
            boolean r5 = r15.inTransaction()     // Catch:{ all -> 0x01f6 }
            if (r5 == 0) goto L_0x01ac
            r15.endTransaction()     // Catch:{ all -> 0x01f6 }
        L_0x01ac:
            com.google.android.gms.measurement.internal.zzfw r5 = r21.zzj()     // Catch:{ all -> 0x01f6 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ all -> 0x01f6 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01f6 }
            r1.zzb = r9     // Catch:{ all -> 0x01f6 }
            if (r10 == 0) goto L_0x01be
            r10.close()
        L_0x01be:
            if (r15 == 0) goto L_0x01f1
            r15.close()
            goto L_0x01f1
        L_0x01c4:
            r10 = r3
            r15 = r10
        L_0x01c6:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x01f6 }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x01d1
            r10.close()
        L_0x01d1:
            if (r15 == 0) goto L_0x01f1
            r15.close()
            goto L_0x01f1
        L_0x01d7:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01da:
            com.google.android.gms.measurement.internal.zzfw r5 = r21.zzj()     // Catch:{ all -> 0x01f6 }
            com.google.android.gms.measurement.internal.zzfy r5 = r5.zzg()     // Catch:{ all -> 0x01f6 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01f6 }
            r1.zzb = r9     // Catch:{ all -> 0x01f6 }
            if (r10 == 0) goto L_0x01ec
            r10.close()
        L_0x01ec:
            if (r15 == 0) goto L_0x01f1
            r15.close()
        L_0x01f1:
            int r7 = r7 + 1
            r5 = 5
            goto L_0x001d
        L_0x01f6:
            r0 = move-exception
            r3 = r10
        L_0x01f8:
            r5 = r15
        L_0x01f9:
            if (r3 == 0) goto L_0x01fe
            r3.close()
        L_0x01fe:
            if (r5 == 0) goto L_0x0203
            r5.close()
        L_0x0203:
            throw r0
        L_0x0204:
            com.google.android.gms.measurement.internal.zzfw r0 = r21.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfp.zza(int):java.util.List");
    }

    zzfp(zzhj zzhj) {
        super(zzhj);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zzaa() {
        int delete;
        zzt();
        try {
            SQLiteDatabase zzad = zzad();
            if (zzad != null && (delete = zzad.delete("messages", (String) null, (String[]) null)) > 0) {
                zzj().zzp().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    private final boolean zzae() {
        return zza().getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzac() {
        /*
            r10 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r10.zzt()
            boolean r1 = r10.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = r10.zzae()
            if (r1 != 0) goto L_0x0012
            return r2
        L_0x0012:
            r1 = 5
            r4 = r1
            r3 = r2
        L_0x0015:
            if (r3 >= r1) goto L_0x008f
            r5 = 1
            r6 = 0
            android.database.sqlite.SQLiteDatabase r6 = r10.zzad()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 != 0) goto L_0x0027
            r10.zzb = r5     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 == 0) goto L_0x0026
            r6.close()
        L_0x0026:
            return r2
        L_0x0027:
            r6.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            r9 = 3
            java.lang.String r9 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            java.lang.String[] r9 = new java.lang.String[]{r9}     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.delete(r7, r8, r9)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.endTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 == 0) goto L_0x0045
            r6.close()
        L_0x0045:
            return r5
        L_0x0046:
            r0 = move-exception
            goto L_0x0089
        L_0x0048:
            r7 = move-exception
            if (r6 == 0) goto L_0x0054
            boolean r8 = r6.inTransaction()     // Catch:{ all -> 0x0046 }
            if (r8 == 0) goto L_0x0054
            r6.endTransaction()     // Catch:{ all -> 0x0046 }
        L_0x0054:
            com.google.android.gms.measurement.internal.zzfw r8 = r10.zzj()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r10.zzb = r5     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0067:
            long r7 = (long) r4
            android.os.SystemClock.sleep(r7)     // Catch:{ all -> 0x0046 }
            int r4 = r4 + 20
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0073:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzfw r8 = r10.zzj()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzfy r8 = r8.zzg()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r10.zzb = r5     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0086
            r6.close()
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x0089:
            if (r6 == 0) goto L_0x008e
            r6.close()
        L_0x008e:
            throw r0
        L_0x008f:
            com.google.android.gms.measurement.internal.zzfw r0 = r10.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzu()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfp.zzac():boolean");
    }

    public final boolean zza(zzae zzae) {
        zzq();
        byte[] zza2 = zznp.zza((Parcelable) zzae);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzj().zzm().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f A[SYNTHETIC, Splitter:B:28:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c1 A[SYNTHETIC, Splitter:B:48:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x009d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzt()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            r3.put(r0, r4)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = r2
            r6 = r4
        L_0x0023:
            if (r5 >= r4) goto L_0x0129
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.zzad()     // Catch:{ SQLiteFullException -> 0x00fb, SQLiteDatabaseLockedException -> 0x00e9, SQLiteException -> 0x00bd, all -> 0x00ba }
            if (r9 != 0) goto L_0x0035
            r1.zzb = r8     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r9 == 0) goto L_0x0034
            r9.close()
        L_0x0034:
            return r2
        L_0x0035:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r10 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r10 == 0) goto L_0x0054
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            if (r0 == 0) goto L_0x0054
            long r11 = r10.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            goto L_0x0056
        L_0x004b:
            r0 = move-exception
            goto L_0x00e7
        L_0x004e:
            r0 = move-exception
            goto L_0x00b6
        L_0x0050:
            r0 = move-exception
            r7 = r10
            goto L_0x00fd
        L_0x0054:
            r11 = 0
        L_0x0056:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            java.lang.String r13 = "messages"
            if (r0 < 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzfw r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String r14 = "Data loss, local db full"
            r0.zza(r14)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r14 = 100001(0x186a1, double:4.9407E-319)
            long r14 = r14 - r11
            java.lang.String r0 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String r11 = java.lang.Long.toString(r14)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String[] r11 = new java.lang.String[]{r11}     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            int r0 = r9.delete(r13, r0, r11)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            long r11 = (long) r0     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            int r0 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzfw r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String r4 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            long r14 = r14 - r11
            java.lang.Long r11 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r0.zza(r4, r2, r8, r11)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
        L_0x009d:
            r9.insertOrThrow(r13, r7, r3)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            if (r10 == 0) goto L_0x00ab
            r10.close()
        L_0x00ab:
            if (r9 == 0) goto L_0x00b0
            r9.close()
        L_0x00b0:
            r2 = 1
            return r2
        L_0x00b2:
            r7 = r10
            goto L_0x00ea
        L_0x00b4:
            r0 = move-exception
            r10 = r7
        L_0x00b6:
            r7 = r9
            goto L_0x00bf
        L_0x00b8:
            r0 = move-exception
            goto L_0x00fd
        L_0x00ba:
            r0 = move-exception
            r9 = r7
            goto L_0x011e
        L_0x00bd:
            r0 = move-exception
            r10 = r7
        L_0x00bf:
            if (r7 == 0) goto L_0x00ca
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00ca
            r7.endTransaction()     // Catch:{ all -> 0x00e5 }
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzfw r2 = r16.zzj()     // Catch:{ all -> 0x00e5 }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x00e5 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00e5 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x00e5 }
            if (r10 == 0) goto L_0x00df
            r10.close()
        L_0x00df:
            if (r7 == 0) goto L_0x0117
            r7.close()
            goto L_0x0117
        L_0x00e5:
            r0 = move-exception
            r9 = r7
        L_0x00e7:
            r7 = r10
            goto L_0x011e
        L_0x00e9:
            r9 = r7
        L_0x00ea:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x011d }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f5
            r7.close()
        L_0x00f5:
            if (r9 == 0) goto L_0x0117
            r9.close()
            goto L_0x0117
        L_0x00fb:
            r0 = move-exception
            r9 = r7
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzfw r2 = r16.zzj()     // Catch:{ all -> 0x011d }
            com.google.android.gms.measurement.internal.zzfy r2 = r2.zzg()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zza(r4, r0)     // Catch:{ all -> 0x011d }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x011d }
            if (r7 == 0) goto L_0x0112
            r7.close()
        L_0x0112:
            if (r9 == 0) goto L_0x0117
            r9.close()
        L_0x0117:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0023
        L_0x011d:
            r0 = move-exception
        L_0x011e:
            if (r7 == 0) goto L_0x0123
            r7.close()
        L_0x0123:
            if (r9 == 0) goto L_0x0128
            r9.close()
        L_0x0128:
            throw r0
        L_0x0129:
            com.google.android.gms.measurement.internal.zzfw r0 = r16.zzj()
            com.google.android.gms.measurement.internal.zzfy r0 = r0.zzp()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfp.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzbd zzbd) {
        Parcel obtain = Parcel.obtain();
        zzbd.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzj().zzm().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzno zzno) {
        Parcel obtain = Parcel.obtain();
        zzno.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzj().zzm().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}

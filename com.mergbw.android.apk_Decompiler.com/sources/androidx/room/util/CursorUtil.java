package androidx.room.util;

import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.alibaba.android.arouter.utils.Consts;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a#\u0010\u0003\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\n\u001a\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007\u001a)\u0010\r\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010\u001a/\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u0012*\u00020\u00012\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u00120\u0014H\bø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0016"}, d2 = {"copyAndClose", "Landroid/database/Cursor;", "c", "findColumnIndexBySuffix", "", "cursor", "name", "", "columnNames", "", "([Ljava/lang/String;Ljava/lang/String;)I", "getColumnIndex", "getColumnIndexOrThrow", "wrapMappedColumns", "mapping", "", "(Landroid/database/Cursor;[Ljava/lang/String;[I)Landroid/database/Cursor;", "useCursor", "R", "block", "Lkotlin/Function1;", "(Landroid/database/Cursor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "room-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: CursorUtil.kt */
public final class CursorUtil {
    public static final int getColumnIndex(Cursor cursor, String str) {
        Intrinsics.checkNotNullParameter(cursor, "c");
        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        int columnIndex2 = cursor.getColumnIndex("`" + str + '`');
        return columnIndex2 >= 0 ? columnIndex2 : findColumnIndexBySuffix(cursor, str);
    }

    public static final int getColumnIndexOrThrow(Cursor cursor, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(cursor, "c");
        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        int columnIndex = getColumnIndex(cursor, str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            Intrinsics.checkNotNullExpressionValue(columnNames, "c.columnNames");
            str2 = ArraysKt.joinToString$default((Object[]) columnNames, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
        } catch (Exception e) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e);
            str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + str2);
    }

    private static final int findColumnIndexBySuffix(Cursor cursor, String str) {
        if (Build.VERSION.SDK_INT > 25 || str.length() == 0) {
            return -1;
        }
        String[] columnNames = cursor.getColumnNames();
        Intrinsics.checkNotNullExpressionValue(columnNames, "columnNames");
        return findColumnIndexBySuffix(columnNames, str);
    }

    public static final int findColumnIndexBySuffix(String[] strArr, String str) {
        Intrinsics.checkNotNullParameter(strArr, "columnNames");
        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        String str2 = Consts.DOT + str;
        String str3 = Consts.DOT + str + '`';
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str4 = strArr[i];
            int i3 = i2 + 1;
            if (str4.length() >= str.length() + 2) {
                if (StringsKt.endsWith$default(str4, str2, false, 2, (Object) null)) {
                    return i2;
                }
                if (str4.charAt(0) == '`' && StringsKt.endsWith$default(str4, str3, false, 2, (Object) null)) {
                    return i2;
                }
            }
            i++;
            i2 = i3;
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r2, r3);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> R useCursor(android.database.Cursor r2, kotlin.jvm.functions.Function1<? super android.database.Cursor, ? extends R> r3) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = 1
            java.lang.Object r3 = r3.invoke(r2)     // Catch:{ all -> 0x001c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            return r3
        L_0x001c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.CursorUtil.useCursor(android.database.Cursor, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    public static final Cursor wrapMappedColumns(Cursor cursor, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(strArr, "columnNames");
        Intrinsics.checkNotNullParameter(iArr, "mapping");
        if (strArr.length == iArr.length) {
            return new CursorUtil$wrapMappedColumns$2(cursor, strArr, iArr);
        }
        throw new IllegalStateException("Expected columnNames.length == mapping.length".toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0079, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.database.Cursor copyAndClose(android.database.Cursor r9) {
        /*
            java.lang.String r0 = "c"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = r9
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x0077 }
            android.database.MatrixCursor r2 = new android.database.MatrixCursor     // Catch:{ all -> 0x0077 }
            java.lang.String[] r3 = r1.getColumnNames()     // Catch:{ all -> 0x0077 }
            int r4 = r1.getCount()     // Catch:{ all -> 0x0077 }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x0077 }
        L_0x0018:
            boolean r3 = r1.moveToNext()     // Catch:{ all -> 0x0077 }
            r4 = 0
            if (r3 == 0) goto L_0x0071
            int r3 = r1.getColumnCount()     // Catch:{ all -> 0x0077 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0077 }
            int r5 = r9.getColumnCount()     // Catch:{ all -> 0x0077 }
            r6 = 0
        L_0x002a:
            if (r6 >= r5) goto L_0x006d
            int r7 = r1.getType(r6)     // Catch:{ all -> 0x0077 }
            if (r7 == 0) goto L_0x0068
            r8 = 1
            if (r7 == r8) goto L_0x005d
            r8 = 2
            if (r7 == r8) goto L_0x0052
            r8 = 3
            if (r7 == r8) goto L_0x004b
            r8 = 4
            if (r7 != r8) goto L_0x0045
            byte[] r7 = r1.getBlob(r6)     // Catch:{ all -> 0x0077 }
            r3[r6] = r7     // Catch:{ all -> 0x0077 }
            goto L_0x006a
        L_0x0045:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0077 }
            r9.<init>()     // Catch:{ all -> 0x0077 }
            throw r9     // Catch:{ all -> 0x0077 }
        L_0x004b:
            java.lang.String r7 = r1.getString(r6)     // Catch:{ all -> 0x0077 }
            r3[r6] = r7     // Catch:{ all -> 0x0077 }
            goto L_0x006a
        L_0x0052:
            double r7 = r1.getDouble(r6)     // Catch:{ all -> 0x0077 }
            java.lang.Double r7 = java.lang.Double.valueOf(r7)     // Catch:{ all -> 0x0077 }
            r3[r6] = r7     // Catch:{ all -> 0x0077 }
            goto L_0x006a
        L_0x005d:
            long r7 = r1.getLong(r6)     // Catch:{ all -> 0x0077 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0077 }
            r3[r6] = r7     // Catch:{ all -> 0x0077 }
            goto L_0x006a
        L_0x0068:
            r3[r6] = r4     // Catch:{ all -> 0x0077 }
        L_0x006a:
            int r6 = r6 + 1
            goto L_0x002a
        L_0x006d:
            r2.addRow(r3)     // Catch:{ all -> 0x0077 }
            goto L_0x0018
        L_0x0071:
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            android.database.Cursor r2 = (android.database.Cursor) r2
            return r2
        L_0x0077:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r9)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.CursorUtil.copyAndClose(android.database.Cursor):android.database.Cursor");
    }
}

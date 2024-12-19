package androidx.room.util;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a \u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0000¨\u0006\u0017"}, d2 = {"readColumns", "", "", "Landroidx/room/util/TableInfo$Column;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "readForeignKeyFieldMappings", "", "Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "cursor", "Landroid/database/Cursor;", "readForeignKeys", "", "Landroidx/room/util/TableInfo$ForeignKey;", "readIndex", "Landroidx/room/util/TableInfo$Index;", "name", "unique", "", "readIndices", "readTableInfo", "Landroidx/room/util/TableInfo;", "room-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: TableInfo.kt */
public final class TableInfoKt {
    public static final TableInfo readTableInfo(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        Intrinsics.checkNotNullParameter(str, "tableName");
        return new TableInfo(str, readColumns(supportSQLiteDatabase, str), readForeignKeys(supportSQLiteDatabase, str), readIndices(supportSQLiteDatabase, str));
    }

    private static final Set<TableInfo.ForeignKey> readForeignKeys(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Throwable th;
        Closeable query = supportSQLiteDatabase.query("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            int columnIndex = cursor.getColumnIndex(TtmlNode.ATTR_ID);
            int columnIndex2 = cursor.getColumnIndex("seq");
            int columnIndex3 = cursor.getColumnIndex("table");
            int columnIndex4 = cursor.getColumnIndex("on_delete");
            int columnIndex5 = cursor.getColumnIndex("on_update");
            List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings = readForeignKeyFieldMappings(cursor);
            cursor.moveToPosition(-1);
            Set createSetBuilder = SetsKt.createSetBuilder();
            while (cursor.moveToNext()) {
                if (cursor.getInt(columnIndex2) == 0) {
                    int i = cursor.getInt(columnIndex);
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    Collection arrayList3 = new ArrayList();
                    for (Object next : readForeignKeyFieldMappings) {
                        if (((TableInfo.ForeignKeyWithSequence) next).getId() == i) {
                            arrayList3.add(next);
                        }
                    }
                    for (TableInfo.ForeignKeyWithSequence foreignKeyWithSequence : (List) arrayList3) {
                        arrayList.add(foreignKeyWithSequence.getFrom());
                        arrayList2.add(foreignKeyWithSequence.getTo());
                    }
                    String string = cursor.getString(columnIndex3);
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(tableColumnIndex)");
                    String string2 = cursor.getString(columnIndex4);
                    Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(onDeleteColumnIndex)");
                    String string3 = cursor.getString(columnIndex5);
                    Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(onUpdateColumnIndex)");
                    createSetBuilder.add(new TableInfo.ForeignKey(string, string2, string3, arrayList, arrayList2));
                }
            }
            Set<TableInfo.ForeignKey> build = SetsKt.build(createSetBuilder);
            CloseableKt.closeFinally(query, (Throwable) null);
            return build;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            CloseableKt.closeFinally(query, th);
            throw th3;
        }
    }

    private static final List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(TtmlNode.ATTR_ID);
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex(TypedValues.TransitionType.S_FROM);
        int columnIndex4 = cursor.getColumnIndex(TypedValues.TransitionType.S_TO);
        List createListBuilder = CollectionsKt.createListBuilder();
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndex);
            int i2 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(toColumnIndex)");
            createListBuilder.add(new TableInfo.ForeignKeyWithSequence(i, i2, string, string2));
        }
        return CollectionsKt.sorted(CollectionsKt.build(createListBuilder));
    }

    private static final Map<String, TableInfo.Column> readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Throwable th;
        Closeable query = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        try {
            Cursor cursor = (Cursor) query;
            if (cursor.getColumnCount() <= 0) {
                Map<String, TableInfo.Column> emptyMap = MapsKt.emptyMap();
                CloseableKt.closeFinally(query, (Throwable) null);
                return emptyMap;
            }
            int columnIndex = cursor.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndex2 = cursor.getColumnIndex(SessionDescription.ATTR_TYPE);
            int columnIndex3 = cursor.getColumnIndex("notnull");
            int columnIndex4 = cursor.getColumnIndex("pk");
            int columnIndex5 = cursor.getColumnIndex("dflt_value");
            Map createMapBuilder = MapsKt.createMapBuilder();
            while (cursor.moveToNext()) {
                String string = cursor.getString(columnIndex);
                String string2 = cursor.getString(columnIndex2);
                boolean z = cursor.getInt(columnIndex3) != 0;
                int i = cursor.getInt(columnIndex4);
                String string3 = cursor.getString(columnIndex5);
                Intrinsics.checkNotNullExpressionValue(string, AppMeasurementSdk.ConditionalUserProperty.NAME);
                Intrinsics.checkNotNullExpressionValue(string2, SessionDescription.ATTR_TYPE);
                TableInfo.Column column = r12;
                TableInfo.Column column2 = new TableInfo.Column(string, string2, z, i, string3, 2);
                createMapBuilder.put(string, column);
            }
            Map<String, TableInfo.Column> build = MapsKt.build(createMapBuilder);
            CloseableKt.closeFinally(query, (Throwable) null);
            return build;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            CloseableKt.closeFinally(query, th);
            throw th3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007b, code lost:
        kotlin.io.CloseableKt.closeFinally(r11, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.util.Set<androidx.room.util.TableInfo.Index> readIndices(androidx.sqlite.db.SupportSQLiteDatabase r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "name"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "PRAGMA index_list(`"
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r11 = "`)"
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            android.database.Cursor r11 = r10.query((java.lang.String) r11)
            java.io.Closeable r11 = (java.io.Closeable) r11
            r1 = r11
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x0078 }
            int r2 = r1.getColumnIndex(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r3 = "origin"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ all -> 0x0078 }
            java.lang.String r4 = "unique"
            int r4 = r1.getColumnIndex(r4)     // Catch:{ all -> 0x0078 }
            r5 = -1
            r6 = 0
            if (r2 == r5) goto L_0x0074
            if (r3 == r5) goto L_0x0074
            if (r4 != r5) goto L_0x0037
            goto L_0x0074
        L_0x0037:
            java.util.Set r5 = kotlin.collections.SetsKt.createSetBuilder()     // Catch:{ all -> 0x0078 }
        L_0x003b:
            boolean r7 = r1.moveToNext()     // Catch:{ all -> 0x0078 }
            if (r7 == 0) goto L_0x006c
            java.lang.String r7 = r1.getString(r3)     // Catch:{ all -> 0x0078 }
            java.lang.String r8 = "c"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r7)     // Catch:{ all -> 0x0078 }
            if (r7 != 0) goto L_0x004e
            goto L_0x003b
        L_0x004e:
            java.lang.String r7 = r1.getString(r2)     // Catch:{ all -> 0x0078 }
            int r8 = r1.getInt(r4)     // Catch:{ all -> 0x0078 }
            r9 = 1
            if (r8 != r9) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r9 = 0
        L_0x005b:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)     // Catch:{ all -> 0x0078 }
            androidx.room.util.TableInfo$Index r7 = readIndex(r10, r7, r9)     // Catch:{ all -> 0x0078 }
            if (r7 != 0) goto L_0x0068
            kotlin.io.CloseableKt.closeFinally(r11, r6)
            return r6
        L_0x0068:
            r5.add(r7)     // Catch:{ all -> 0x0078 }
            goto L_0x003b
        L_0x006c:
            java.util.Set r10 = kotlin.collections.SetsKt.build(r5)     // Catch:{ all -> 0x0078 }
            kotlin.io.CloseableKt.closeFinally(r11, r6)
            return r10
        L_0x0074:
            kotlin.io.CloseableKt.closeFinally(r11, r6)
            return r6
        L_0x0078:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x007a }
        L_0x007a:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r11, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.TableInfoKt.readIndices(androidx.sqlite.db.SupportSQLiteDatabase, java.lang.String):java.util.Set");
    }

    private static final TableInfo.Index readIndex(SupportSQLiteDatabase supportSQLiteDatabase, String str, boolean z) {
        Throwable th;
        String str2 = str;
        Closeable query = supportSQLiteDatabase.query("PRAGMA index_xinfo(`" + str2 + "`)");
        try {
            Cursor cursor = (Cursor) query;
            int columnIndex = cursor.getColumnIndex("seqno");
            int columnIndex2 = cursor.getColumnIndex("cid");
            int columnIndex3 = cursor.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME);
            int columnIndex4 = cursor.getColumnIndex("desc");
            if (!(columnIndex == -1 || columnIndex2 == -1 || columnIndex3 == -1)) {
                if (columnIndex4 != -1) {
                    TreeMap treeMap = new TreeMap();
                    TreeMap treeMap2 = new TreeMap();
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(columnIndex2) >= 0) {
                            int i = cursor.getInt(columnIndex);
                            String string = cursor.getString(columnIndex3);
                            String str3 = cursor.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                            Integer valueOf = Integer.valueOf(i);
                            Intrinsics.checkNotNullExpressionValue(string, "columnName");
                            treeMap.put(valueOf, string);
                            treeMap2.put(Integer.valueOf(i), str3);
                        }
                    }
                    Collection values = treeMap.values();
                    Intrinsics.checkNotNullExpressionValue(values, "columnsMap.values");
                    List list = CollectionsKt.toList(values);
                    Collection values2 = treeMap2.values();
                    Intrinsics.checkNotNullExpressionValue(values2, "ordersMap.values");
                    TableInfo.Index index = new TableInfo.Index(str2, z, list, CollectionsKt.toList(values2));
                    CloseableKt.closeFinally(query, (Throwable) null);
                    return index;
                }
            }
            CloseableKt.closeFinally(query, (Throwable) null);
            return null;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            CloseableKt.closeFinally(query, th);
            throw th3;
        }
    }
}

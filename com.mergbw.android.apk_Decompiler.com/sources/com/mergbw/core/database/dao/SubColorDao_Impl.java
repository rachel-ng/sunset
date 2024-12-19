package com.mergbw.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mergbw.core.database.bean.SubColorBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class SubColorDao_Impl implements SubColorDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<SubColorBean> __deletionAdapterOfSubColorBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<SubColorBean> __insertionAdapterOfSubColorBean;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<SubColorBean> __updateAdapterOfSubColorBean;

    public SubColorDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSubColorBean = new EntityInsertionAdapter<SubColorBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_sub_color_list` (`id`,`colorValue`,`alias`,`deviceMac`,`subType`,`deviceType`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SubColorBean subColorBean) {
                supportSQLiteStatement.bindLong(1, (long) subColorBean.getId());
                if (subColorBean.getColorValue() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, subColorBean.getColorValue());
                }
                if (subColorBean.getAlias() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, subColorBean.getAlias());
                }
                if (subColorBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, subColorBean.getDeviceMac());
                }
                supportSQLiteStatement.bindLong(5, (long) subColorBean.getSubType());
                supportSQLiteStatement.bindLong(6, (long) subColorBean.getDeviceType());
            }
        };
        this.__deletionAdapterOfSubColorBean = new EntityDeletionOrUpdateAdapter<SubColorBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_sub_color_list` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SubColorBean subColorBean) {
                supportSQLiteStatement.bindLong(1, (long) subColorBean.getId());
            }
        };
        this.__updateAdapterOfSubColorBean = new EntityDeletionOrUpdateAdapter<SubColorBean>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `t_sub_color_list` SET `id` = ?,`colorValue` = ?,`alias` = ?,`deviceMac` = ?,`subType` = ?,`deviceType` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SubColorBean subColorBean) {
                supportSQLiteStatement.bindLong(1, (long) subColorBean.getId());
                if (subColorBean.getColorValue() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, subColorBean.getColorValue());
                }
                if (subColorBean.getAlias() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, subColorBean.getAlias());
                }
                if (subColorBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, subColorBean.getDeviceMac());
                }
                supportSQLiteStatement.bindLong(5, (long) subColorBean.getSubType());
                supportSQLiteStatement.bindLong(6, (long) subColorBean.getDeviceType());
                supportSQLiteStatement.bindLong(7, (long) subColorBean.getId());
            }
        };
    }

    public Maybe<Long> insertColor(final SubColorBean subColorBean) {
        return Maybe.fromCallable(new Callable<Long>() {
            public Long call() throws Exception {
                SubColorDao_Impl.this.__db.beginTransaction();
                try {
                    long insertAndReturnId = SubColorDao_Impl.this.__insertionAdapterOfSubColorBean.insertAndReturnId(subColorBean);
                    SubColorDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(insertAndReturnId);
                } finally {
                    SubColorDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    public Completable deleteColor(final List<SubColorBean> list) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                SubColorDao_Impl.this.__db.beginTransaction();
                try {
                    SubColorDao_Impl.this.__deletionAdapterOfSubColorBean.handleMultiple(list);
                    SubColorDao_Impl.this.__db.setTransactionSuccessful();
                    SubColorDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    SubColorDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable deleteColor(final SubColorBean subColorBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                SubColorDao_Impl.this.__db.beginTransaction();
                try {
                    SubColorDao_Impl.this.__deletionAdapterOfSubColorBean.handle(subColorBean);
                    SubColorDao_Impl.this.__db.setTransactionSuccessful();
                    SubColorDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    SubColorDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable updateColor(final SubColorBean subColorBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                SubColorDao_Impl.this.__db.beginTransaction();
                try {
                    SubColorDao_Impl.this.__updateAdapterOfSubColorBean.handle(subColorBean);
                    SubColorDao_Impl.this.__db.setTransactionSuccessful();
                    SubColorDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    SubColorDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Maybe<SubColorBean> querySubColor(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_sub_color_list where id = ?", 1);
        acquire.bindLong(1, (long) i);
        return Maybe.fromCallable(new Callable<SubColorBean>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.mergbw.core.database.bean.SubColorBean} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v3 */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.mergbw.core.database.bean.SubColorBean call() throws java.lang.Exception {
                /*
                    r9 = this;
                    com.mergbw.core.database.dao.SubColorDao_Impl r0 = com.mergbw.core.database.dao.SubColorDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r1 = r0
                    r2 = 0
                    r3 = 0
                    android.database.Cursor r0 = androidx.room.util.DBUtil.query(r0, r1, r2, r3)
                    java.lang.String r1 = "id"
                    int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r1)     // Catch:{ all -> 0x0083 }
                    java.lang.String r2 = "colorValue"
                    int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r2)     // Catch:{ all -> 0x0083 }
                    java.lang.String r4 = "alias"
                    int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r4)     // Catch:{ all -> 0x0083 }
                    java.lang.String r5 = "deviceMac"
                    int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r5)     // Catch:{ all -> 0x0083 }
                    java.lang.String r6 = "subType"
                    int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r6)     // Catch:{ all -> 0x0083 }
                    java.lang.String r7 = "deviceType"
                    int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r7)     // Catch:{ all -> 0x0083 }
                    boolean r8 = r0.moveToFirst()     // Catch:{ all -> 0x0083 }
                    if (r8 == 0) goto L_0x007f
                    com.mergbw.core.database.bean.SubColorBean r8 = new com.mergbw.core.database.bean.SubColorBean     // Catch:{ all -> 0x0083 }
                    r8.<init>()     // Catch:{ all -> 0x0083 }
                    int r1 = r0.getInt(r1)     // Catch:{ all -> 0x0083 }
                    r8.setId(r1)     // Catch:{ all -> 0x0083 }
                    boolean r1 = r0.isNull(r2)     // Catch:{ all -> 0x0083 }
                    if (r1 == 0) goto L_0x004c
                    r1 = r3
                    goto L_0x0050
                L_0x004c:
                    java.lang.String r1 = r0.getString(r2)     // Catch:{ all -> 0x0083 }
                L_0x0050:
                    r8.setColorValue(r1)     // Catch:{ all -> 0x0083 }
                    boolean r1 = r0.isNull(r4)     // Catch:{ all -> 0x0083 }
                    if (r1 == 0) goto L_0x005b
                    r1 = r3
                    goto L_0x005f
                L_0x005b:
                    java.lang.String r1 = r0.getString(r4)     // Catch:{ all -> 0x0083 }
                L_0x005f:
                    r8.setAlias(r1)     // Catch:{ all -> 0x0083 }
                    boolean r1 = r0.isNull(r5)     // Catch:{ all -> 0x0083 }
                    if (r1 == 0) goto L_0x0069
                    goto L_0x006d
                L_0x0069:
                    java.lang.String r3 = r0.getString(r5)     // Catch:{ all -> 0x0083 }
                L_0x006d:
                    r8.setDeviceMac(r3)     // Catch:{ all -> 0x0083 }
                    int r1 = r0.getInt(r6)     // Catch:{ all -> 0x0083 }
                    r8.setSubType(r1)     // Catch:{ all -> 0x0083 }
                    int r1 = r0.getInt(r7)     // Catch:{ all -> 0x0083 }
                    r8.setDeviceType(r1)     // Catch:{ all -> 0x0083 }
                    r3 = r8
                L_0x007f:
                    r0.close()
                    return r3
                L_0x0083:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.database.dao.SubColorDao_Impl.AnonymousClass8.call():com.mergbw.core.database.bean.SubColorBean");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<SubColorBean>> queryColorList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_sub_color_list", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_sub_color_list"}, new Callable<List<SubColorBean>>() {
            public List<SubColorBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(SubColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "alias");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "subType");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        SubColorBean subColorBean = new SubColorBean();
                        subColorBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        subColorBean.setColorValue(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        subColorBean.setAlias(str2);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        subColorBean.setDeviceMac(str3);
                        subColorBean.setSubType(query.getInt(columnIndexOrThrow5));
                        subColorBean.setDeviceType(query.getInt(columnIndexOrThrow6));
                        arrayList.add(subColorBean);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<SubColorBean>> queryColorList(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_sub_color_list where deviceMac = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_sub_color_list"}, new Callable<List<SubColorBean>>() {
            public List<SubColorBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(SubColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "alias");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "subType");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        SubColorBean subColorBean = new SubColorBean();
                        subColorBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        subColorBean.setColorValue(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        subColorBean.setAlias(str2);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        subColorBean.setDeviceMac(str3);
                        subColorBean.setSubType(query.getInt(columnIndexOrThrow5));
                        subColorBean.setDeviceType(query.getInt(columnIndexOrThrow6));
                        arrayList.add(subColorBean);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<SubColorBean>> queryColorList(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_sub_color_list where deviceType = ?", 1);
        acquire.bindLong(1, (long) i);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_sub_color_list"}, new Callable<List<SubColorBean>>() {
            public List<SubColorBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(SubColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "alias");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "subType");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        SubColorBean subColorBean = new SubColorBean();
                        subColorBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        subColorBean.setColorValue(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        subColorBean.setAlias(str2);
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        subColorBean.setDeviceMac(str3);
                        subColorBean.setSubType(query.getInt(columnIndexOrThrow5));
                        subColorBean.setDeviceType(query.getInt(columnIndexOrThrow6));
                        arrayList.add(subColorBean);
                    }
                    return arrayList;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}

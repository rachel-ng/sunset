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
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mergbw.core.database.bean.DIYInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class DIYColorDao_Impl implements DIYColorDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<DIYInfoBean> __deletionAdapterOfDIYInfoBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<DIYInfoBean> __insertionAdapterOfDIYInfoBean;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<DIYInfoBean> __updateAdapterOfDIYInfoBean;

    public DIYColorDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfDIYInfoBean = new EntityInsertionAdapter<DIYInfoBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_diy_color_list` (`id`,`name`,`style`,`time`,`colorValue`,`deviceMac`,`diyType`,`deviceType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DIYInfoBean dIYInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) dIYInfoBean.getId());
                if (dIYInfoBean.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, dIYInfoBean.getName());
                }
                supportSQLiteStatement.bindLong(3, (long) dIYInfoBean.getStyle());
                supportSQLiteStatement.bindLong(4, (long) dIYInfoBean.getTime());
                if (dIYInfoBean.getColorValue() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, dIYInfoBean.getColorValue());
                }
                if (dIYInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, dIYInfoBean.getDeviceMac());
                }
                supportSQLiteStatement.bindLong(7, (long) dIYInfoBean.getDiyType());
                supportSQLiteStatement.bindLong(8, (long) dIYInfoBean.getDeviceType());
            }
        };
        this.__deletionAdapterOfDIYInfoBean = new EntityDeletionOrUpdateAdapter<DIYInfoBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_diy_color_list` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DIYInfoBean dIYInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) dIYInfoBean.getId());
            }
        };
        this.__updateAdapterOfDIYInfoBean = new EntityDeletionOrUpdateAdapter<DIYInfoBean>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `t_diy_color_list` SET `id` = ?,`name` = ?,`style` = ?,`time` = ?,`colorValue` = ?,`deviceMac` = ?,`diyType` = ?,`deviceType` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DIYInfoBean dIYInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) dIYInfoBean.getId());
                if (dIYInfoBean.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, dIYInfoBean.getName());
                }
                supportSQLiteStatement.bindLong(3, (long) dIYInfoBean.getStyle());
                supportSQLiteStatement.bindLong(4, (long) dIYInfoBean.getTime());
                if (dIYInfoBean.getColorValue() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, dIYInfoBean.getColorValue());
                }
                if (dIYInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, dIYInfoBean.getDeviceMac());
                }
                supportSQLiteStatement.bindLong(7, (long) dIYInfoBean.getDiyType());
                supportSQLiteStatement.bindLong(8, (long) dIYInfoBean.getDeviceType());
                supportSQLiteStatement.bindLong(9, (long) dIYInfoBean.getId());
            }
        };
    }

    public Maybe<Long> insertDIYColor(final DIYInfoBean dIYInfoBean) {
        return Maybe.fromCallable(new Callable<Long>() {
            public Long call() throws Exception {
                DIYColorDao_Impl.this.__db.beginTransaction();
                try {
                    long insertAndReturnId = DIYColorDao_Impl.this.__insertionAdapterOfDIYInfoBean.insertAndReturnId(dIYInfoBean);
                    DIYColorDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(insertAndReturnId);
                } finally {
                    DIYColorDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    public Completable deleteColor(final DIYInfoBean dIYInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                DIYColorDao_Impl.this.__db.beginTransaction();
                try {
                    DIYColorDao_Impl.this.__deletionAdapterOfDIYInfoBean.handle(dIYInfoBean);
                    DIYColorDao_Impl.this.__db.setTransactionSuccessful();
                    DIYColorDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    DIYColorDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable updateColor(final DIYInfoBean dIYInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                DIYColorDao_Impl.this.__db.beginTransaction();
                try {
                    DIYColorDao_Impl.this.__updateAdapterOfDIYInfoBean.handle(dIYInfoBean);
                    DIYColorDao_Impl.this.__db.setTransactionSuccessful();
                    DIYColorDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    DIYColorDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Maybe<DIYInfoBean> queryDIYColor(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_diy_color_list where id = ?", 1);
        acquire.bindLong(1, (long) i);
        return Maybe.fromCallable(new Callable<DIYInfoBean>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.mergbw.core.database.bean.DIYInfoBean} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v3 */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.mergbw.core.database.bean.DIYInfoBean call() throws java.lang.Exception {
                /*
                    r11 = this;
                    com.mergbw.core.database.dao.DIYColorDao_Impl r0 = com.mergbw.core.database.dao.DIYColorDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r1 = r0
                    r2 = 0
                    r3 = 0
                    android.database.Cursor r0 = androidx.room.util.DBUtil.query(r0, r1, r2, r3)
                    java.lang.String r1 = "id"
                    int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r1)     // Catch:{ all -> 0x009d }
                    java.lang.String r2 = "name"
                    int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r2)     // Catch:{ all -> 0x009d }
                    java.lang.String r4 = "style"
                    int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r4)     // Catch:{ all -> 0x009d }
                    java.lang.String r5 = "time"
                    int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r5)     // Catch:{ all -> 0x009d }
                    java.lang.String r6 = "colorValue"
                    int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r6)     // Catch:{ all -> 0x009d }
                    java.lang.String r7 = "deviceMac"
                    int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r7)     // Catch:{ all -> 0x009d }
                    java.lang.String r8 = "diyType"
                    int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r8)     // Catch:{ all -> 0x009d }
                    java.lang.String r9 = "deviceType"
                    int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r9)     // Catch:{ all -> 0x009d }
                    boolean r10 = r0.moveToFirst()     // Catch:{ all -> 0x009d }
                    if (r10 == 0) goto L_0x0099
                    com.mergbw.core.database.bean.DIYInfoBean r10 = new com.mergbw.core.database.bean.DIYInfoBean     // Catch:{ all -> 0x009d }
                    r10.<init>()     // Catch:{ all -> 0x009d }
                    int r1 = r0.getInt(r1)     // Catch:{ all -> 0x009d }
                    r10.setId(r1)     // Catch:{ all -> 0x009d }
                    boolean r1 = r0.isNull(r2)     // Catch:{ all -> 0x009d }
                    if (r1 == 0) goto L_0x0058
                    r1 = r3
                    goto L_0x005c
                L_0x0058:
                    java.lang.String r1 = r0.getString(r2)     // Catch:{ all -> 0x009d }
                L_0x005c:
                    r10.setName(r1)     // Catch:{ all -> 0x009d }
                    int r1 = r0.getInt(r4)     // Catch:{ all -> 0x009d }
                    r10.setStyle(r1)     // Catch:{ all -> 0x009d }
                    int r1 = r0.getInt(r5)     // Catch:{ all -> 0x009d }
                    r10.setTime(r1)     // Catch:{ all -> 0x009d }
                    boolean r1 = r0.isNull(r6)     // Catch:{ all -> 0x009d }
                    if (r1 == 0) goto L_0x0075
                    r1 = r3
                    goto L_0x0079
                L_0x0075:
                    java.lang.String r1 = r0.getString(r6)     // Catch:{ all -> 0x009d }
                L_0x0079:
                    r10.setColorValue(r1)     // Catch:{ all -> 0x009d }
                    boolean r1 = r0.isNull(r7)     // Catch:{ all -> 0x009d }
                    if (r1 == 0) goto L_0x0083
                    goto L_0x0087
                L_0x0083:
                    java.lang.String r3 = r0.getString(r7)     // Catch:{ all -> 0x009d }
                L_0x0087:
                    r10.setDeviceMac(r3)     // Catch:{ all -> 0x009d }
                    int r1 = r0.getInt(r8)     // Catch:{ all -> 0x009d }
                    r10.setDiyType(r1)     // Catch:{ all -> 0x009d }
                    int r1 = r0.getInt(r9)     // Catch:{ all -> 0x009d }
                    r10.setDeviceType(r1)     // Catch:{ all -> 0x009d }
                    r3 = r10
                L_0x0099:
                    r0.close()
                    return r3
                L_0x009d:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.database.dao.DIYColorDao_Impl.AnonymousClass7.call():com.mergbw.core.database.bean.DIYInfoBean");
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public Flowable<List<DIYInfoBean>> queryDIYColorList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_diy_color_list", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_diy_color_list"}, new Callable<List<DIYInfoBean>>() {
            public List<DIYInfoBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(DIYColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_STYLE);
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "time");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "diyType");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        DIYInfoBean dIYInfoBean = new DIYInfoBean();
                        dIYInfoBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        dIYInfoBean.setName(str);
                        dIYInfoBean.setStyle(query.getInt(columnIndexOrThrow3));
                        dIYInfoBean.setTime(query.getInt(columnIndexOrThrow4));
                        if (query.isNull(columnIndexOrThrow5)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow5);
                        }
                        dIYInfoBean.setColorValue(str2);
                        if (query.isNull(columnIndexOrThrow6)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow6);
                        }
                        dIYInfoBean.setDeviceMac(str3);
                        dIYInfoBean.setDiyType(query.getInt(columnIndexOrThrow7));
                        dIYInfoBean.setDeviceType(query.getInt(columnIndexOrThrow8));
                        arrayList.add(dIYInfoBean);
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

    public Flowable<List<DIYInfoBean>> queryDIYColorList(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_diy_color_list where deviceMac = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_diy_color_list"}, new Callable<List<DIYInfoBean>>() {
            public List<DIYInfoBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(DIYColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_STYLE);
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "time");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "diyType");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        DIYInfoBean dIYInfoBean = new DIYInfoBean();
                        dIYInfoBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        dIYInfoBean.setName(str);
                        dIYInfoBean.setStyle(query.getInt(columnIndexOrThrow3));
                        dIYInfoBean.setTime(query.getInt(columnIndexOrThrow4));
                        if (query.isNull(columnIndexOrThrow5)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow5);
                        }
                        dIYInfoBean.setColorValue(str2);
                        if (query.isNull(columnIndexOrThrow6)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow6);
                        }
                        dIYInfoBean.setDeviceMac(str3);
                        dIYInfoBean.setDiyType(query.getInt(columnIndexOrThrow7));
                        dIYInfoBean.setDeviceType(query.getInt(columnIndexOrThrow8));
                        arrayList.add(dIYInfoBean);
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

    public Flowable<List<DIYInfoBean>> queryDIYColorList(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_diy_color_list where deviceType = ?", 1);
        acquire.bindLong(1, (long) i);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_diy_color_list"}, new Callable<List<DIYInfoBean>>() {
            public List<DIYInfoBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(DIYColorDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.TAG_STYLE);
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "time");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "diyType");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        DIYInfoBean dIYInfoBean = new DIYInfoBean();
                        dIYInfoBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        dIYInfoBean.setName(str);
                        dIYInfoBean.setStyle(query.getInt(columnIndexOrThrow3));
                        dIYInfoBean.setTime(query.getInt(columnIndexOrThrow4));
                        if (query.isNull(columnIndexOrThrow5)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow5);
                        }
                        dIYInfoBean.setColorValue(str2);
                        if (query.isNull(columnIndexOrThrow6)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow6);
                        }
                        dIYInfoBean.setDeviceMac(str3);
                        dIYInfoBean.setDiyType(query.getInt(columnIndexOrThrow7));
                        dIYInfoBean.setDeviceType(query.getInt(columnIndexOrThrow8));
                        arrayList.add(dIYInfoBean);
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

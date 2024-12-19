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
import com.mergbw.core.database.bean.DeviceInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class DeviceInfoDao_Impl implements DeviceInfoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<DeviceInfoBean> __deletionAdapterOfDeviceInfoBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<DeviceInfoBean> __insertionAdapterOfDeviceInfoBean;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<DeviceInfoBean> __updateAdapterOfDeviceInfoBean;

    public DeviceInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfDeviceInfoBean = new EntityInsertionAdapter<DeviceInfoBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_device_list` (`deviceMac`,`deviceName`,`deviceType`,`deviceModel`,`aliasName`,`factoryID`) VALUES (?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DeviceInfoBean deviceInfoBean) {
                if (deviceInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, deviceInfoBean.getDeviceMac());
                }
                if (deviceInfoBean.getDeviceName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, deviceInfoBean.getDeviceName());
                }
                supportSQLiteStatement.bindLong(3, (long) deviceInfoBean.getDeviceType());
                if (deviceInfoBean.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, deviceInfoBean.getDeviceModel());
                }
                if (deviceInfoBean.getAliasName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, deviceInfoBean.getAliasName());
                }
                supportSQLiteStatement.bindLong(6, (long) deviceInfoBean.getFactoryID());
            }
        };
        this.__deletionAdapterOfDeviceInfoBean = new EntityDeletionOrUpdateAdapter<DeviceInfoBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_device_list` WHERE `deviceMac` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DeviceInfoBean deviceInfoBean) {
                if (deviceInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, deviceInfoBean.getDeviceMac());
                }
            }
        };
        this.__updateAdapterOfDeviceInfoBean = new EntityDeletionOrUpdateAdapter<DeviceInfoBean>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `t_device_list` SET `deviceMac` = ?,`deviceName` = ?,`deviceType` = ?,`deviceModel` = ?,`aliasName` = ?,`factoryID` = ? WHERE `deviceMac` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, DeviceInfoBean deviceInfoBean) {
                if (deviceInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, deviceInfoBean.getDeviceMac());
                }
                if (deviceInfoBean.getDeviceName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, deviceInfoBean.getDeviceName());
                }
                supportSQLiteStatement.bindLong(3, (long) deviceInfoBean.getDeviceType());
                if (deviceInfoBean.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, deviceInfoBean.getDeviceModel());
                }
                if (deviceInfoBean.getAliasName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, deviceInfoBean.getAliasName());
                }
                supportSQLiteStatement.bindLong(6, (long) deviceInfoBean.getFactoryID());
                if (deviceInfoBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, deviceInfoBean.getDeviceMac());
                }
            }
        };
    }

    public Completable insertDevice(final DeviceInfoBean deviceInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                DeviceInfoDao_Impl.this.__db.beginTransaction();
                try {
                    DeviceInfoDao_Impl.this.__insertionAdapterOfDeviceInfoBean.insert(deviceInfoBean);
                    DeviceInfoDao_Impl.this.__db.setTransactionSuccessful();
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable deleteDevice(final DeviceInfoBean deviceInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                DeviceInfoDao_Impl.this.__db.beginTransaction();
                try {
                    DeviceInfoDao_Impl.this.__deletionAdapterOfDeviceInfoBean.handle(deviceInfoBean);
                    DeviceInfoDao_Impl.this.__db.setTransactionSuccessful();
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable updateDevice(final DeviceInfoBean deviceInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                DeviceInfoDao_Impl.this.__db.beginTransaction();
                try {
                    DeviceInfoDao_Impl.this.__updateAdapterOfDeviceInfoBean.handle(deviceInfoBean);
                    DeviceInfoDao_Impl.this.__db.setTransactionSuccessful();
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    DeviceInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Flowable<List<DeviceInfoBean>> queryDeviceList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_device_list", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_device_list"}, new Callable<List<DeviceInfoBean>>() {
            public List<DeviceInfoBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                Cursor query = DBUtil.query(DeviceInfoDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "deviceName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceModel");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "aliasName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "factoryID");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        DeviceInfoBean deviceInfoBean = new DeviceInfoBean();
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        deviceInfoBean.setDeviceMac(str);
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        deviceInfoBean.setDeviceName(str2);
                        deviceInfoBean.setDeviceType(query.getInt(columnIndexOrThrow3));
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        deviceInfoBean.setDeviceModel(str3);
                        if (query.isNull(columnIndexOrThrow5)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow5);
                        }
                        deviceInfoBean.setAliasName(str4);
                        deviceInfoBean.setFactoryID(query.getInt(columnIndexOrThrow6));
                        arrayList.add(deviceInfoBean);
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

    public Maybe<DeviceInfoBean> queryDeviceInfo(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_device_list where deviceMac = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return Maybe.fromCallable(new Callable<DeviceInfoBean>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.mergbw.core.database.bean.DeviceInfoBean} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v3 */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.mergbw.core.database.bean.DeviceInfoBean call() throws java.lang.Exception {
                /*
                    r10 = this;
                    com.mergbw.core.database.dao.DeviceInfoDao_Impl r0 = com.mergbw.core.database.dao.DeviceInfoDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r1 = r0
                    r2 = 0
                    r3 = 0
                    android.database.Cursor r0 = androidx.room.util.DBUtil.query(r0, r1, r2, r3)
                    java.lang.String r1 = "deviceMac"
                    int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r1)     // Catch:{ all -> 0x008b }
                    java.lang.String r2 = "deviceName"
                    int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r2)     // Catch:{ all -> 0x008b }
                    java.lang.String r4 = "deviceType"
                    int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r4)     // Catch:{ all -> 0x008b }
                    java.lang.String r5 = "deviceModel"
                    int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r5)     // Catch:{ all -> 0x008b }
                    java.lang.String r6 = "aliasName"
                    int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r6)     // Catch:{ all -> 0x008b }
                    java.lang.String r7 = "factoryID"
                    int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r7)     // Catch:{ all -> 0x008b }
                    boolean r8 = r0.moveToFirst()     // Catch:{ all -> 0x008b }
                    if (r8 == 0) goto L_0x0087
                    com.mergbw.core.database.bean.DeviceInfoBean r8 = new com.mergbw.core.database.bean.DeviceInfoBean     // Catch:{ all -> 0x008b }
                    r8.<init>()     // Catch:{ all -> 0x008b }
                    boolean r9 = r0.isNull(r1)     // Catch:{ all -> 0x008b }
                    if (r9 == 0) goto L_0x0045
                    r1 = r3
                    goto L_0x0049
                L_0x0045:
                    java.lang.String r1 = r0.getString(r1)     // Catch:{ all -> 0x008b }
                L_0x0049:
                    r8.setDeviceMac(r1)     // Catch:{ all -> 0x008b }
                    boolean r1 = r0.isNull(r2)     // Catch:{ all -> 0x008b }
                    if (r1 == 0) goto L_0x0054
                    r1 = r3
                    goto L_0x0058
                L_0x0054:
                    java.lang.String r1 = r0.getString(r2)     // Catch:{ all -> 0x008b }
                L_0x0058:
                    r8.setDeviceName(r1)     // Catch:{ all -> 0x008b }
                    int r1 = r0.getInt(r4)     // Catch:{ all -> 0x008b }
                    r8.setDeviceType(r1)     // Catch:{ all -> 0x008b }
                    boolean r1 = r0.isNull(r5)     // Catch:{ all -> 0x008b }
                    if (r1 == 0) goto L_0x006a
                    r1 = r3
                    goto L_0x006e
                L_0x006a:
                    java.lang.String r1 = r0.getString(r5)     // Catch:{ all -> 0x008b }
                L_0x006e:
                    r8.setDeviceModel(r1)     // Catch:{ all -> 0x008b }
                    boolean r1 = r0.isNull(r6)     // Catch:{ all -> 0x008b }
                    if (r1 == 0) goto L_0x0078
                    goto L_0x007c
                L_0x0078:
                    java.lang.String r3 = r0.getString(r6)     // Catch:{ all -> 0x008b }
                L_0x007c:
                    r8.setAliasName(r3)     // Catch:{ all -> 0x008b }
                    int r1 = r0.getInt(r7)     // Catch:{ all -> 0x008b }
                    r8.setFactoryID(r1)     // Catch:{ all -> 0x008b }
                    r3 = r8
                L_0x0087:
                    r0.close()
                    return r3
                L_0x008b:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.database.dao.DeviceInfoDao_Impl.AnonymousClass8.call():com.mergbw.core.database.bean.DeviceInfoBean");
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

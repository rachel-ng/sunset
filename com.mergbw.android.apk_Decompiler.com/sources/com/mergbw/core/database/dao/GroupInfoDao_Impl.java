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
import com.mergbw.core.database.bean.GroupItemBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class GroupInfoDao_Impl implements GroupInfoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<GroupItemBean> __deletionAdapterOfGroupItemBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<GroupItemBean> __insertionAdapterOfGroupItemBean;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<GroupItemBean> __updateAdapterOfGroupItemBean;

    public GroupInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfGroupItemBean = new EntityInsertionAdapter<GroupItemBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_device_group` (`groupId`,`groupName`,`devices`,`deviceType`) VALUES (nullif(?, 0),?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, GroupItemBean groupItemBean) {
                supportSQLiteStatement.bindLong(1, (long) groupItemBean.getGroupId());
                if (groupItemBean.getGroupName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, groupItemBean.getGroupName());
                }
                if (groupItemBean.getDevices() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, groupItemBean.getDevices());
                }
                supportSQLiteStatement.bindLong(4, (long) groupItemBean.getDeviceType());
            }
        };
        this.__deletionAdapterOfGroupItemBean = new EntityDeletionOrUpdateAdapter<GroupItemBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_device_group` WHERE `groupId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, GroupItemBean groupItemBean) {
                supportSQLiteStatement.bindLong(1, (long) groupItemBean.getGroupId());
            }
        };
        this.__updateAdapterOfGroupItemBean = new EntityDeletionOrUpdateAdapter<GroupItemBean>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `t_device_group` SET `groupId` = ?,`groupName` = ?,`devices` = ?,`deviceType` = ? WHERE `groupId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, GroupItemBean groupItemBean) {
                supportSQLiteStatement.bindLong(1, (long) groupItemBean.getGroupId());
                if (groupItemBean.getGroupName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, groupItemBean.getGroupName());
                }
                if (groupItemBean.getDevices() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, groupItemBean.getDevices());
                }
                supportSQLiteStatement.bindLong(4, (long) groupItemBean.getDeviceType());
                supportSQLiteStatement.bindLong(5, (long) groupItemBean.getGroupId());
            }
        };
    }

    public Completable insertGroup(final GroupItemBean groupItemBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                GroupInfoDao_Impl.this.__db.beginTransaction();
                try {
                    GroupInfoDao_Impl.this.__insertionAdapterOfGroupItemBean.insert(groupItemBean);
                    GroupInfoDao_Impl.this.__db.setTransactionSuccessful();
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable deleteGroup(final GroupItemBean groupItemBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                GroupInfoDao_Impl.this.__db.beginTransaction();
                try {
                    GroupInfoDao_Impl.this.__deletionAdapterOfGroupItemBean.handle(groupItemBean);
                    GroupInfoDao_Impl.this.__db.setTransactionSuccessful();
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable updateGroup(final GroupItemBean groupItemBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                GroupInfoDao_Impl.this.__db.beginTransaction();
                try {
                    GroupInfoDao_Impl.this.__updateAdapterOfGroupItemBean.handle(groupItemBean);
                    GroupInfoDao_Impl.this.__db.setTransactionSuccessful();
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    GroupInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Flowable<List<GroupItemBean>> queryGroupListWithFlowable() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_device_group", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_device_group"}, new Callable<List<GroupItemBean>>() {
            public List<GroupItemBean> call() throws Exception {
                String str;
                String str2;
                Cursor query = DBUtil.query(GroupInfoDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "groupId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "groupName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "devices");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        GroupItemBean groupItemBean = new GroupItemBean();
                        groupItemBean.setGroupId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        groupItemBean.setGroupName(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        groupItemBean.setDevices(str2);
                        groupItemBean.setDeviceType(query.getInt(columnIndexOrThrow4));
                        arrayList.add(groupItemBean);
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

    public Maybe<List<GroupItemBean>> queryGroupList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_device_group", 0);
        return Maybe.fromCallable(new Callable<List<GroupItemBean>>() {
            public List<GroupItemBean> call() throws Exception {
                String str;
                String str2;
                Cursor query = DBUtil.query(GroupInfoDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "groupId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "groupName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "devices");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        GroupItemBean groupItemBean = new GroupItemBean();
                        groupItemBean.setGroupId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        groupItemBean.setGroupName(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        groupItemBean.setDevices(str2);
                        groupItemBean.setDeviceType(query.getInt(columnIndexOrThrow4));
                        arrayList.add(groupItemBean);
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

    public Flowable<GroupItemBean> queryGroupInfo(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_device_group where groupId = ?", 1);
        acquire.bindLong(1, (long) i);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_device_group"}, new Callable<GroupItemBean>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.mergbw.core.database.bean.GroupItemBean} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
            /* JADX WARNING: type inference failed for: r3v0 */
            /* JADX WARNING: type inference failed for: r3v3 */
            /* JADX WARNING: type inference failed for: r3v5 */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.mergbw.core.database.bean.GroupItemBean call() throws java.lang.Exception {
                /*
                    r7 = this;
                    com.mergbw.core.database.dao.GroupInfoDao_Impl r0 = com.mergbw.core.database.dao.GroupInfoDao_Impl.this
                    androidx.room.RoomDatabase r0 = r0.__db
                    androidx.room.RoomSQLiteQuery r1 = r0
                    r2 = 0
                    r3 = 0
                    android.database.Cursor r0 = androidx.room.util.DBUtil.query(r0, r1, r2, r3)
                    java.lang.String r1 = "groupId"
                    int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r1)     // Catch:{ all -> 0x0061 }
                    java.lang.String r2 = "groupName"
                    int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r2)     // Catch:{ all -> 0x0061 }
                    java.lang.String r4 = "devices"
                    int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r4)     // Catch:{ all -> 0x0061 }
                    java.lang.String r5 = "deviceType"
                    int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r0, r5)     // Catch:{ all -> 0x0061 }
                    boolean r6 = r0.moveToFirst()     // Catch:{ all -> 0x0061 }
                    if (r6 == 0) goto L_0x005d
                    com.mergbw.core.database.bean.GroupItemBean r6 = new com.mergbw.core.database.bean.GroupItemBean     // Catch:{ all -> 0x0061 }
                    r6.<init>()     // Catch:{ all -> 0x0061 }
                    int r1 = r0.getInt(r1)     // Catch:{ all -> 0x0061 }
                    r6.setGroupId(r1)     // Catch:{ all -> 0x0061 }
                    boolean r1 = r0.isNull(r2)     // Catch:{ all -> 0x0061 }
                    if (r1 == 0) goto L_0x0040
                    r1 = r3
                    goto L_0x0044
                L_0x0040:
                    java.lang.String r1 = r0.getString(r2)     // Catch:{ all -> 0x0061 }
                L_0x0044:
                    r6.setGroupName(r1)     // Catch:{ all -> 0x0061 }
                    boolean r1 = r0.isNull(r4)     // Catch:{ all -> 0x0061 }
                    if (r1 == 0) goto L_0x004e
                    goto L_0x0052
                L_0x004e:
                    java.lang.String r3 = r0.getString(r4)     // Catch:{ all -> 0x0061 }
                L_0x0052:
                    r6.setDevices(r3)     // Catch:{ all -> 0x0061 }
                    int r1 = r0.getInt(r5)     // Catch:{ all -> 0x0061 }
                    r6.setDeviceType(r1)     // Catch:{ all -> 0x0061 }
                    r3 = r6
                L_0x005d:
                    r0.close()
                    return r3
                L_0x0061:
                    r1 = move-exception
                    r0.close()
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mergbw.core.database.dao.GroupInfoDao_Impl.AnonymousClass9.call():com.mergbw.core.database.bean.GroupItemBean");
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

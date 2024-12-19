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
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class UpgradeErrorRecordDao_Impl implements UpgradeErrorRecordDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<UpgradeErrorRecordBean> __deletionAdapterOfUpgradeErrorRecordBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<UpgradeErrorRecordBean> __insertionAdapterOfUpgradeErrorRecordBean;

    public UpgradeErrorRecordDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfUpgradeErrorRecordBean = new EntityInsertionAdapter<UpgradeErrorRecordBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_upgrade_error_record` (`deviceMac`,`deviceName`,`deviceType`,`deviceModel`,`aliasName`,`factoryID`,`errorCode`,`recordTime`) VALUES (?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UpgradeErrorRecordBean upgradeErrorRecordBean) {
                if (upgradeErrorRecordBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, upgradeErrorRecordBean.getDeviceMac());
                }
                if (upgradeErrorRecordBean.getDeviceName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, upgradeErrorRecordBean.getDeviceName());
                }
                supportSQLiteStatement.bindLong(3, (long) upgradeErrorRecordBean.getDeviceType());
                if (upgradeErrorRecordBean.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, upgradeErrorRecordBean.getDeviceModel());
                }
                if (upgradeErrorRecordBean.getAliasName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, upgradeErrorRecordBean.getAliasName());
                }
                supportSQLiteStatement.bindLong(6, (long) upgradeErrorRecordBean.getFactoryID());
                supportSQLiteStatement.bindLong(7, (long) upgradeErrorRecordBean.getErrorCode());
                if (upgradeErrorRecordBean.getRecordTime() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, upgradeErrorRecordBean.getRecordTime());
                }
            }
        };
        this.__deletionAdapterOfUpgradeErrorRecordBean = new EntityDeletionOrUpdateAdapter<UpgradeErrorRecordBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_upgrade_error_record` WHERE `deviceMac` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, UpgradeErrorRecordBean upgradeErrorRecordBean) {
                if (upgradeErrorRecordBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, upgradeErrorRecordBean.getDeviceMac());
                }
            }
        };
    }

    public Completable insertRecord(final UpgradeErrorRecordBean upgradeErrorRecordBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                UpgradeErrorRecordDao_Impl.this.__db.beginTransaction();
                try {
                    UpgradeErrorRecordDao_Impl.this.__insertionAdapterOfUpgradeErrorRecordBean.insert(upgradeErrorRecordBean);
                    UpgradeErrorRecordDao_Impl.this.__db.setTransactionSuccessful();
                    UpgradeErrorRecordDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    UpgradeErrorRecordDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable deleteRecord(final UpgradeErrorRecordBean upgradeErrorRecordBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                UpgradeErrorRecordDao_Impl.this.__db.beginTransaction();
                try {
                    UpgradeErrorRecordDao_Impl.this.__deletionAdapterOfUpgradeErrorRecordBean.handle(upgradeErrorRecordBean);
                    UpgradeErrorRecordDao_Impl.this.__db.setTransactionSuccessful();
                    UpgradeErrorRecordDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    UpgradeErrorRecordDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Flowable<List<UpgradeErrorRecordBean>> queryRecordList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_upgrade_error_record", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_upgrade_error_record"}, new Callable<List<UpgradeErrorRecordBean>>() {
            public List<UpgradeErrorRecordBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                Cursor query = DBUtil.query(UpgradeErrorRecordDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "deviceName");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceModel");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "aliasName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "factoryID");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "errorCode");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "recordTime");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        UpgradeErrorRecordBean upgradeErrorRecordBean = new UpgradeErrorRecordBean();
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        upgradeErrorRecordBean.setDeviceMac(str);
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        upgradeErrorRecordBean.setDeviceName(str2);
                        upgradeErrorRecordBean.setDeviceType(query.getInt(columnIndexOrThrow3));
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        upgradeErrorRecordBean.setDeviceModel(str3);
                        if (query.isNull(columnIndexOrThrow5)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow5);
                        }
                        upgradeErrorRecordBean.setAliasName(str4);
                        upgradeErrorRecordBean.setFactoryID(query.getInt(columnIndexOrThrow6));
                        upgradeErrorRecordBean.setErrorCode(query.getInt(columnIndexOrThrow7));
                        if (query.isNull(columnIndexOrThrow8)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow8);
                        }
                        upgradeErrorRecordBean.setRecordTime(str5);
                        arrayList.add(upgradeErrorRecordBean);
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

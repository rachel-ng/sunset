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
import com.mergbw.core.database.bean.ConfigInfoBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class ConfigInfoDao_Impl implements ConfigInfoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<ConfigInfoBean> __deletionAdapterOfConfigInfoBean;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<ConfigInfoBean> __insertionAdapterOfConfigInfoBean;
    /* access modifiers changed from: private */
    public final EntityDeletionOrUpdateAdapter<ConfigInfoBean> __updateAdapterOfConfigInfoBean;

    public ConfigInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfConfigInfoBean = new EntityInsertionAdapter<ConfigInfoBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_factory_config` (`id`,`name`,`factoryId`,`deviceType`,`deviceModel`,`ledNum`,`nameLength`,`bleName`,`extra`,`keyMode`,`RGBModel`,`maxMixLight`,`maxOtherLight`,`remoteControl`,`DIYMode`,`SubMode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ConfigInfoBean configInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) configInfoBean.getId());
                if (configInfoBean.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, configInfoBean.getName());
                }
                supportSQLiteStatement.bindLong(3, (long) configInfoBean.getFactoryId());
                supportSQLiteStatement.bindLong(4, (long) configInfoBean.getDeviceType());
                if (configInfoBean.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, configInfoBean.getDeviceModel());
                }
                supportSQLiteStatement.bindLong(6, (long) configInfoBean.getLedNum());
                supportSQLiteStatement.bindLong(7, (long) configInfoBean.getNameLength());
                if (configInfoBean.getBleName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, configInfoBean.getBleName());
                }
                if (configInfoBean.getExtra() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, configInfoBean.getExtra());
                }
                if (configInfoBean.getKeyMode() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, configInfoBean.getKeyMode());
                }
                supportSQLiteStatement.bindLong(11, (long) configInfoBean.getRGBModel());
                supportSQLiteStatement.bindLong(12, (long) configInfoBean.getMaxMixLight());
                supportSQLiteStatement.bindLong(13, (long) configInfoBean.getMaxOtherLight());
                if (configInfoBean.getRemoteControl() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, configInfoBean.getRemoteControl());
                }
                if (configInfoBean.getDIYMode() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, configInfoBean.getDIYMode());
                }
                if (configInfoBean.getSubMode() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, configInfoBean.getSubMode());
                }
            }
        };
        this.__deletionAdapterOfConfigInfoBean = new EntityDeletionOrUpdateAdapter<ConfigInfoBean>(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM `t_factory_config` WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ConfigInfoBean configInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) configInfoBean.getId());
            }
        };
        this.__updateAdapterOfConfigInfoBean = new EntityDeletionOrUpdateAdapter<ConfigInfoBean>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `t_factory_config` SET `id` = ?,`name` = ?,`factoryId` = ?,`deviceType` = ?,`deviceModel` = ?,`ledNum` = ?,`nameLength` = ?,`bleName` = ?,`extra` = ?,`keyMode` = ?,`RGBModel` = ?,`maxMixLight` = ?,`maxOtherLight` = ?,`remoteControl` = ?,`DIYMode` = ?,`SubMode` = ? WHERE `id` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ConfigInfoBean configInfoBean) {
                supportSQLiteStatement.bindLong(1, (long) configInfoBean.getId());
                if (configInfoBean.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, configInfoBean.getName());
                }
                supportSQLiteStatement.bindLong(3, (long) configInfoBean.getFactoryId());
                supportSQLiteStatement.bindLong(4, (long) configInfoBean.getDeviceType());
                if (configInfoBean.getDeviceModel() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, configInfoBean.getDeviceModel());
                }
                supportSQLiteStatement.bindLong(6, (long) configInfoBean.getLedNum());
                supportSQLiteStatement.bindLong(7, (long) configInfoBean.getNameLength());
                if (configInfoBean.getBleName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, configInfoBean.getBleName());
                }
                if (configInfoBean.getExtra() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, configInfoBean.getExtra());
                }
                if (configInfoBean.getKeyMode() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, configInfoBean.getKeyMode());
                }
                supportSQLiteStatement.bindLong(11, (long) configInfoBean.getRGBModel());
                supportSQLiteStatement.bindLong(12, (long) configInfoBean.getMaxMixLight());
                supportSQLiteStatement.bindLong(13, (long) configInfoBean.getMaxOtherLight());
                if (configInfoBean.getRemoteControl() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, configInfoBean.getRemoteControl());
                }
                if (configInfoBean.getDIYMode() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, configInfoBean.getDIYMode());
                }
                if (configInfoBean.getSubMode() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, configInfoBean.getSubMode());
                }
                supportSQLiteStatement.bindLong(17, (long) configInfoBean.getId());
            }
        };
    }

    public Maybe<Long> insertConfig(final ConfigInfoBean configInfoBean) {
        return Maybe.fromCallable(new Callable<Long>() {
            public Long call() throws Exception {
                ConfigInfoDao_Impl.this.__db.beginTransaction();
                try {
                    long insertAndReturnId = ConfigInfoDao_Impl.this.__insertionAdapterOfConfigInfoBean.insertAndReturnId(configInfoBean);
                    ConfigInfoDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(insertAndReturnId);
                } finally {
                    ConfigInfoDao_Impl.this.__db.endTransaction();
                }
            }
        });
    }

    public Completable deleteConfig(final ConfigInfoBean configInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                ConfigInfoDao_Impl.this.__db.beginTransaction();
                try {
                    ConfigInfoDao_Impl.this.__deletionAdapterOfConfigInfoBean.handle(configInfoBean);
                    ConfigInfoDao_Impl.this.__db.setTransactionSuccessful();
                    ConfigInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    ConfigInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Completable updateConfig(final ConfigInfoBean configInfoBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                ConfigInfoDao_Impl.this.__db.beginTransaction();
                try {
                    ConfigInfoDao_Impl.this.__updateAdapterOfConfigInfoBean.handle(configInfoBean);
                    ConfigInfoDao_Impl.this.__db.setTransactionSuccessful();
                    ConfigInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    ConfigInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Flowable<List<ConfigInfoBean>> queryConfigList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_factory_config", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_factory_config"}, new Callable<List<ConfigInfoBean>>() {
            public List<ConfigInfoBean> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                int i;
                String str6;
                int i2;
                String str7;
                String str8;
                Cursor query = DBUtil.query(ConfigInfoDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, TtmlNode.ATTR_ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "factoryId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "deviceType");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "deviceModel");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "ledNum");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "nameLength");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "bleName");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "extra");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "keyMode");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "RGBModel");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "maxMixLight");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "maxOtherLight");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "remoteControl");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "DIYMode");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "SubMode");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ConfigInfoBean configInfoBean = new ConfigInfoBean();
                        ArrayList arrayList2 = arrayList;
                        configInfoBean.setId(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        configInfoBean.setName(str);
                        configInfoBean.setFactoryId(query.getInt(columnIndexOrThrow3));
                        configInfoBean.setDeviceType(query.getInt(columnIndexOrThrow4));
                        if (query.isNull(columnIndexOrThrow5)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow5);
                        }
                        configInfoBean.setDeviceModel(str2);
                        configInfoBean.setLedNum(query.getInt(columnIndexOrThrow6));
                        configInfoBean.setNameLength(query.getInt(columnIndexOrThrow7));
                        if (query.isNull(columnIndexOrThrow8)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow8);
                        }
                        configInfoBean.setBleName(str3);
                        if (query.isNull(columnIndexOrThrow9)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow9);
                        }
                        configInfoBean.setExtra(str4);
                        if (query.isNull(columnIndexOrThrow10)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow10);
                        }
                        configInfoBean.setKeyMode(str5);
                        configInfoBean.setRGBModel(query.getInt(columnIndexOrThrow11));
                        configInfoBean.setMaxMixLight(query.getInt(columnIndexOrThrow12));
                        configInfoBean.setMaxOtherLight(query.getInt(columnIndexOrThrow13));
                        int i4 = i3;
                        if (query.isNull(i4)) {
                            i = columnIndexOrThrow;
                            str6 = null;
                        } else {
                            i = columnIndexOrThrow;
                            str6 = query.getString(i4);
                        }
                        configInfoBean.setRemoteControl(str6);
                        int i5 = columnIndexOrThrow15;
                        if (query.isNull(i5)) {
                            i2 = i5;
                            str7 = null;
                        } else {
                            i2 = i5;
                            str7 = query.getString(i5);
                        }
                        configInfoBean.setDIYMode(str7);
                        int i6 = columnIndexOrThrow16;
                        if (query.isNull(i6)) {
                            columnIndexOrThrow16 = i6;
                            str8 = null;
                        } else {
                            columnIndexOrThrow16 = i6;
                            str8 = query.getString(i6);
                        }
                        configInfoBean.setSubMode(str8);
                        ArrayList arrayList3 = arrayList2;
                        arrayList3.add(configInfoBean);
                        columnIndexOrThrow15 = i2;
                        i3 = i4;
                        arrayList = arrayList3;
                        columnIndexOrThrow = i;
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

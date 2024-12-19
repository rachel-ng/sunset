package com.mergbw.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mergbw.core.database.bean.ColorBean;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public final class ColorInfoDao_Impl implements ColorInfoDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    /* access modifiers changed from: private */
    public final EntityInsertionAdapter<ColorBean> __insertionAdapterOfColorBean;

    public ColorInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfColorBean = new EntityInsertionAdapter<ColorBean>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `t_color_list` (`colorValue`,`alias`,`deviceMac`,`addTime`) VALUES (?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, ColorBean colorBean) {
                supportSQLiteStatement.bindLong(1, (long) colorBean.getColorValue());
                if (colorBean.getAlias() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, colorBean.getAlias());
                }
                if (colorBean.getDeviceMac() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, colorBean.getDeviceMac());
                }
                supportSQLiteStatement.bindLong(4, colorBean.getAddTime());
            }
        };
    }

    public Completable insertColor(final ColorBean colorBean) {
        return Completable.fromCallable(new Callable<Void>() {
            /* JADX INFO: finally extract failed */
            public Void call() throws Exception {
                ColorInfoDao_Impl.this.__db.beginTransaction();
                try {
                    ColorInfoDao_Impl.this.__insertionAdapterOfColorBean.insert(colorBean);
                    ColorInfoDao_Impl.this.__db.setTransactionSuccessful();
                    ColorInfoDao_Impl.this.__db.endTransaction();
                    return null;
                } catch (Throwable th) {
                    ColorInfoDao_Impl.this.__db.endTransaction();
                    throw th;
                }
            }
        });
    }

    public Flowable<List<ColorBean>> queryColorList() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from t_color_list order by addTime desc", 0);
        return RxRoom.createFlowable(this.__db, false, new String[]{"t_color_list"}, new Callable<List<ColorBean>>() {
            public List<ColorBean> call() throws Exception {
                String str;
                String str2;
                Cursor query = DBUtil.query(ColorInfoDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "colorValue");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "alias");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "deviceMac");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "addTime");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ColorBean colorBean = new ColorBean();
                        colorBean.setColorValue(query.getInt(columnIndexOrThrow));
                        if (query.isNull(columnIndexOrThrow2)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow2);
                        }
                        colorBean.setAlias(str);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        colorBean.setDeviceMac(str2);
                        colorBean.setAddTime(query.getLong(columnIndexOrThrow4));
                        arrayList.add(colorBean);
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

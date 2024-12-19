package com.mergbw.core.database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.mergbw.core.database.dao.ColorInfoDao;
import com.mergbw.core.database.dao.ConfigInfoDao;
import com.mergbw.core.database.dao.DIYColorDao;
import com.mergbw.core.database.dao.DeviceInfoDao;
import com.mergbw.core.database.dao.GroupInfoDao;
import com.mergbw.core.database.dao.SubColorDao;
import com.mergbw.core.database.dao.UpgradeErrorRecordDao;
import com.mergbw.core.utils.AppUtils;

public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "mergbw_android.db";
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table t_device_group add column deviceType INTEGER NOT NULL DEFAULT 1");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("create table if not exists t_upgrade_error_record(deviceMac TEXT PRIMARY KEY NOT NULL, deviceName TEXT, deviceType Integer NOT NULL, deviceModel TEXT, aliasName TEXT, factoryID Integer NOT NULL, errorCode Integer NOT NULL, recordTime TEXT)");
        }
    };
    private static AppDatabase instance;

    public abstract ColorInfoDao colorInfoDao();

    public abstract ConfigInfoDao configInfoDao();

    public abstract DeviceInfoDao deviceInfoDao();

    public abstract DIYColorDao diyColorDao();

    public abstract GroupInfoDao groupInfoDao();

    public abstract SubColorDao subColorDao();

    public abstract UpgradeErrorRecordDao upgradeErrorRecordDao();

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(AppUtils.getApp(), AppDatabase.class, DB_NAME).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();
        }
        return instance;
    }
}

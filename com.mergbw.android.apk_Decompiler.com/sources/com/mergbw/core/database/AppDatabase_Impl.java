package com.mergbw.core.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mergbw.core.database.dao.ColorInfoDao;
import com.mergbw.core.database.dao.ColorInfoDao_Impl;
import com.mergbw.core.database.dao.ConfigInfoDao;
import com.mergbw.core.database.dao.ConfigInfoDao_Impl;
import com.mergbw.core.database.dao.DIYColorDao;
import com.mergbw.core.database.dao.DIYColorDao_Impl;
import com.mergbw.core.database.dao.DeviceInfoDao;
import com.mergbw.core.database.dao.DeviceInfoDao_Impl;
import com.mergbw.core.database.dao.GroupInfoDao;
import com.mergbw.core.database.dao.GroupInfoDao_Impl;
import com.mergbw.core.database.dao.SubColorDao;
import com.mergbw.core.database.dao.SubColorDao_Impl;
import com.mergbw.core.database.dao.UpgradeErrorRecordDao;
import com.mergbw.core.database.dao.UpgradeErrorRecordDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppDatabase_Impl extends AppDatabase {
    private volatile ColorInfoDao _colorInfoDao;
    private volatile ConfigInfoDao _configInfoDao;
    private volatile DIYColorDao _dIYColorDao;
    private volatile DeviceInfoDao _deviceInfoDao;
    private volatile GroupInfoDao _groupInfoDao;
    private volatile SubColorDao _subColorDao;
    private volatile UpgradeErrorRecordDao _upgradeErrorRecordDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(3) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_device_list` (`deviceMac` TEXT NOT NULL, `deviceName` TEXT, `deviceType` INTEGER NOT NULL, `deviceModel` TEXT, `aliasName` TEXT, `factoryID` INTEGER NOT NULL, PRIMARY KEY(`deviceMac`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_color_list` (`colorValue` INTEGER NOT NULL, `alias` TEXT, `deviceMac` TEXT, `addTime` INTEGER NOT NULL, PRIMARY KEY(`colorValue`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_sub_color_list` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `colorValue` TEXT, `alias` TEXT, `deviceMac` TEXT, `subType` INTEGER NOT NULL, `deviceType` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_device_group` (`groupId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `groupName` TEXT, `devices` TEXT, `deviceType` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_diy_color_list` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `style` INTEGER NOT NULL, `time` INTEGER NOT NULL, `colorValue` TEXT, `deviceMac` TEXT, `diyType` INTEGER NOT NULL, `deviceType` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_factory_config` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `factoryId` INTEGER NOT NULL, `deviceType` INTEGER NOT NULL, `deviceModel` TEXT, `ledNum` INTEGER NOT NULL, `nameLength` INTEGER NOT NULL, `bleName` TEXT, `extra` TEXT, `keyMode` TEXT, `RGBModel` INTEGER NOT NULL, `maxMixLight` INTEGER NOT NULL, `maxOtherLight` INTEGER NOT NULL, `remoteControl` TEXT, `DIYMode` TEXT, `SubMode` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `t_upgrade_error_record` (`deviceMac` TEXT NOT NULL, `deviceName` TEXT, `deviceType` INTEGER NOT NULL, `deviceModel` TEXT, `aliasName` TEXT, `factoryID` INTEGER NOT NULL, `errorCode` INTEGER NOT NULL, `recordTime` TEXT, PRIMARY KEY(`deviceMac`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9a671d88b3fb4da226f0d2916b2347be')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_device_list`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_color_list`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_sub_color_list`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_device_group`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_diy_color_list`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_factory_config`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `t_upgrade_error_record`");
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = AppDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                AppDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (AppDatabase_Impl.this.mCallbacks != null) {
                    int size = AppDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) AppDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(6);
                hashMap.put("deviceMac", new TableInfo.Column("deviceMac", "TEXT", true, 1, (String) null, 1));
                hashMap.put("deviceName", new TableInfo.Column("deviceName", "TEXT", false, 0, (String) null, 1));
                hashMap.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("deviceModel", new TableInfo.Column("deviceModel", "TEXT", false, 0, (String) null, 1));
                hashMap.put("aliasName", new TableInfo.Column("aliasName", "TEXT", false, 0, (String) null, 1));
                hashMap.put("factoryID", new TableInfo.Column("factoryID", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo = new TableInfo("t_device_list", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "t_device_list");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_device_list(com.mergbw.core.database.bean.DeviceInfoBean).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(4);
                TableInfo.Column column = r12;
                TableInfo.Column column2 = new TableInfo.Column("colorValue", "INTEGER", true, 1, (String) null, 1);
                hashMap2.put("colorValue", column);
                hashMap2.put("alias", new TableInfo.Column("alias", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("deviceMac", new TableInfo.Column("deviceMac", "TEXT", false, 0, (String) null, 1));
                hashMap2.put("addTime", new TableInfo.Column("addTime", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo2 = new TableInfo("t_color_list", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "t_color_list");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_color_list(com.mergbw.core.database.bean.ColorBean).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(6);
                hashMap3.put(TtmlNode.ATTR_ID, new TableInfo.Column(TtmlNode.ATTR_ID, "INTEGER", true, 1, (String) null, 1));
                hashMap3.put("colorValue", new TableInfo.Column("colorValue", "TEXT", false, 0, (String) null, 1));
                hashMap3.put("alias", new TableInfo.Column("alias", "TEXT", false, 0, (String) null, 1));
                hashMap3.put("deviceMac", new TableInfo.Column("deviceMac", "TEXT", false, 0, (String) null, 1));
                hashMap3.put("subType", new TableInfo.Column("subType", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo3 = new TableInfo("t_sub_color_list", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "t_sub_color_list");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_sub_color_list(com.mergbw.core.database.bean.SubColorBean).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(4);
                hashMap4.put("groupId", new TableInfo.Column("groupId", "INTEGER", true, 1, (String) null, 1));
                hashMap4.put("groupName", new TableInfo.Column("groupName", "TEXT", false, 0, (String) null, 1));
                hashMap4.put("devices", new TableInfo.Column("devices", "TEXT", false, 0, (String) null, 1));
                hashMap4.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo4 = new TableInfo("t_device_group", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "t_device_group");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_device_group(com.mergbw.core.database.bean.GroupItemBean).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                HashMap hashMap5 = new HashMap(8);
                TableInfo.Column column3 = r13;
                TableInfo.Column column4 = new TableInfo.Column(TtmlNode.ATTR_ID, "INTEGER", true, 1, (String) null, 1);
                hashMap5.put(TtmlNode.ATTR_ID, column3);
                hashMap5.put(AppMeasurementSdk.ConditionalUserProperty.NAME, new TableInfo.Column(AppMeasurementSdk.ConditionalUserProperty.NAME, "TEXT", false, 0, (String) null, 1));
                hashMap5.put(TtmlNode.TAG_STYLE, new TableInfo.Column(TtmlNode.TAG_STYLE, "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("time", new TableInfo.Column("time", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("colorValue", new TableInfo.Column("colorValue", "TEXT", false, 0, (String) null, 1));
                hashMap5.put("deviceMac", new TableInfo.Column("deviceMac", "TEXT", false, 0, (String) null, 1));
                hashMap5.put("diyType", new TableInfo.Column("diyType", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo5 = new TableInfo("t_diy_color_list", hashMap5, new HashSet(0), new HashSet(0));
                TableInfo read5 = TableInfo.read(supportSQLiteDatabase2, "t_diy_color_list");
                if (!tableInfo5.equals(read5)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_diy_color_list(com.mergbw.core.database.bean.DIYInfoBean).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
                }
                HashMap hashMap6 = new HashMap(16);
                hashMap6.put(TtmlNode.ATTR_ID, new TableInfo.Column(TtmlNode.ATTR_ID, "INTEGER", true, 1, (String) null, 1));
                hashMap6.put(AppMeasurementSdk.ConditionalUserProperty.NAME, new TableInfo.Column(AppMeasurementSdk.ConditionalUserProperty.NAME, "TEXT", false, 0, (String) null, 1));
                hashMap6.put("factoryId", new TableInfo.Column("factoryId", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("deviceModel", new TableInfo.Column("deviceModel", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("ledNum", new TableInfo.Column("ledNum", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("nameLength", new TableInfo.Column("nameLength", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("bleName", new TableInfo.Column("bleName", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("extra", new TableInfo.Column("extra", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("keyMode", new TableInfo.Column("keyMode", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("RGBModel", new TableInfo.Column("RGBModel", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("maxMixLight", new TableInfo.Column("maxMixLight", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("maxOtherLight", new TableInfo.Column("maxOtherLight", "INTEGER", true, 0, (String) null, 1));
                hashMap6.put("remoteControl", new TableInfo.Column("remoteControl", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("DIYMode", new TableInfo.Column("DIYMode", "TEXT", false, 0, (String) null, 1));
                hashMap6.put("SubMode", new TableInfo.Column("SubMode", "TEXT", false, 0, (String) null, 1));
                TableInfo tableInfo6 = new TableInfo("t_factory_config", hashMap6, new HashSet(0), new HashSet(0));
                TableInfo read6 = TableInfo.read(supportSQLiteDatabase2, "t_factory_config");
                if (!tableInfo6.equals(read6)) {
                    return new RoomOpenHelper.ValidationResult(false, "t_factory_config(com.mergbw.core.database.bean.ConfigInfoBean).\n Expected:\n" + tableInfo6 + "\n Found:\n" + read6);
                }
                HashMap hashMap7 = new HashMap(8);
                hashMap7.put("deviceMac", new TableInfo.Column("deviceMac", "TEXT", true, 1, (String) null, 1));
                hashMap7.put("deviceName", new TableInfo.Column("deviceName", "TEXT", false, 0, (String) null, 1));
                hashMap7.put("deviceType", new TableInfo.Column("deviceType", "INTEGER", true, 0, (String) null, 1));
                hashMap7.put("deviceModel", new TableInfo.Column("deviceModel", "TEXT", false, 0, (String) null, 1));
                hashMap7.put("aliasName", new TableInfo.Column("aliasName", "TEXT", false, 0, (String) null, 1));
                hashMap7.put("factoryID", new TableInfo.Column("factoryID", "INTEGER", true, 0, (String) null, 1));
                hashMap7.put("errorCode", new TableInfo.Column("errorCode", "INTEGER", true, 0, (String) null, 1));
                hashMap7.put("recordTime", new TableInfo.Column("recordTime", "TEXT", false, 0, (String) null, 1));
                TableInfo tableInfo7 = new TableInfo("t_upgrade_error_record", hashMap7, new HashSet(0), new HashSet(0));
                TableInfo read7 = TableInfo.read(supportSQLiteDatabase2, "t_upgrade_error_record");
                if (tableInfo7.equals(read7)) {
                    return new RoomOpenHelper.ValidationResult(true, (String) null);
                }
                return new RoomOpenHelper.ValidationResult(false, "t_upgrade_error_record(com.mergbw.core.database.bean.UpgradeErrorRecordBean).\n Expected:\n" + tableInfo7 + "\n Found:\n" + read7);
            }
        }, "9a671d88b3fb4da226f0d2916b2347be", "40102ad8b4ada39e40747411359e1198")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "t_device_list", "t_color_list", "t_sub_color_list", "t_device_group", "t_diy_color_list", "t_factory_config", "t_upgrade_error_record");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `t_device_list`");
            writableDatabase.execSQL("DELETE FROM `t_color_list`");
            writableDatabase.execSQL("DELETE FROM `t_sub_color_list`");
            writableDatabase.execSQL("DELETE FROM `t_device_group`");
            writableDatabase.execSQL("DELETE FROM `t_diy_color_list`");
            writableDatabase.execSQL("DELETE FROM `t_factory_config`");
            writableDatabase.execSQL("DELETE FROM `t_upgrade_error_record`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceInfoDao.class, DeviceInfoDao_Impl.getRequiredConverters());
        hashMap.put(ColorInfoDao.class, ColorInfoDao_Impl.getRequiredConverters());
        hashMap.put(SubColorDao.class, SubColorDao_Impl.getRequiredConverters());
        hashMap.put(DIYColorDao.class, DIYColorDao_Impl.getRequiredConverters());
        hashMap.put(GroupInfoDao.class, GroupInfoDao_Impl.getRequiredConverters());
        hashMap.put(ConfigInfoDao.class, ConfigInfoDao_Impl.getRequiredConverters());
        hashMap.put(UpgradeErrorRecordDao.class, UpgradeErrorRecordDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    public DeviceInfoDao deviceInfoDao() {
        DeviceInfoDao deviceInfoDao;
        if (this._deviceInfoDao != null) {
            return this._deviceInfoDao;
        }
        synchronized (this) {
            if (this._deviceInfoDao == null) {
                this._deviceInfoDao = new DeviceInfoDao_Impl(this);
            }
            deviceInfoDao = this._deviceInfoDao;
        }
        return deviceInfoDao;
    }

    public ColorInfoDao colorInfoDao() {
        ColorInfoDao colorInfoDao;
        if (this._colorInfoDao != null) {
            return this._colorInfoDao;
        }
        synchronized (this) {
            if (this._colorInfoDao == null) {
                this._colorInfoDao = new ColorInfoDao_Impl(this);
            }
            colorInfoDao = this._colorInfoDao;
        }
        return colorInfoDao;
    }

    public SubColorDao subColorDao() {
        SubColorDao subColorDao;
        if (this._subColorDao != null) {
            return this._subColorDao;
        }
        synchronized (this) {
            if (this._subColorDao == null) {
                this._subColorDao = new SubColorDao_Impl(this);
            }
            subColorDao = this._subColorDao;
        }
        return subColorDao;
    }

    public DIYColorDao diyColorDao() {
        DIYColorDao dIYColorDao;
        if (this._dIYColorDao != null) {
            return this._dIYColorDao;
        }
        synchronized (this) {
            if (this._dIYColorDao == null) {
                this._dIYColorDao = new DIYColorDao_Impl(this);
            }
            dIYColorDao = this._dIYColorDao;
        }
        return dIYColorDao;
    }

    public GroupInfoDao groupInfoDao() {
        GroupInfoDao groupInfoDao;
        if (this._groupInfoDao != null) {
            return this._groupInfoDao;
        }
        synchronized (this) {
            if (this._groupInfoDao == null) {
                this._groupInfoDao = new GroupInfoDao_Impl(this);
            }
            groupInfoDao = this._groupInfoDao;
        }
        return groupInfoDao;
    }

    public ConfigInfoDao configInfoDao() {
        ConfigInfoDao configInfoDao;
        if (this._configInfoDao != null) {
            return this._configInfoDao;
        }
        synchronized (this) {
            if (this._configInfoDao == null) {
                this._configInfoDao = new ConfigInfoDao_Impl(this);
            }
            configInfoDao = this._configInfoDao;
        }
        return configInfoDao;
    }

    public UpgradeErrorRecordDao upgradeErrorRecordDao() {
        UpgradeErrorRecordDao upgradeErrorRecordDao;
        if (this._upgradeErrorRecordDao != null) {
            return this._upgradeErrorRecordDao;
        }
        synchronized (this) {
            if (this._upgradeErrorRecordDao == null) {
                this._upgradeErrorRecordDao = new UpgradeErrorRecordDao_Impl(this);
            }
            upgradeErrorRecordDao = this._upgradeErrorRecordDao;
        }
        return upgradeErrorRecordDao;
    }
}

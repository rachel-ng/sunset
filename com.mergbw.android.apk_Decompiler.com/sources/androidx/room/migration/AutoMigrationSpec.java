package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {

    /* renamed from: androidx.room.migration.AutoMigrationSpec$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onPostMigrate(AutoMigrationSpec _this, SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }

    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}

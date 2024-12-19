package androidx.room;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0017\u0018\u0000 \u00192\u00020\u0001:\u0003\u0019\u001a\u001bB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/room/RoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "configuration", "Landroidx/room/DatabaseConfiguration;", "delegate", "Landroidx/room/RoomOpenHelper$Delegate;", "legacyHash", "", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;)V", "identityHash", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;Ljava/lang/String;)V", "checkIdentity", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "createMasterTableIfNotExists", "onConfigure", "onCreate", "onDowngrade", "oldVersion", "", "newVersion", "onOpen", "onUpgrade", "updateIdentity", "Companion", "Delegate", "ValidationResult", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: RoomOpenHelper.kt */
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate2, String str, String str2) {
        super(delegate2.version);
        Intrinsics.checkNotNullParameter(databaseConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(delegate2, "delegate");
        Intrinsics.checkNotNullParameter(str, "identityHash");
        Intrinsics.checkNotNullParameter(str2, "legacyHash");
        this.configuration = databaseConfiguration;
        this.delegate = delegate2;
        this.identityHash = str;
        this.legacyHash = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate2, String str) {
        this(databaseConfiguration, delegate2, "", str);
        Intrinsics.checkNotNullParameter(databaseConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(delegate2, "delegate");
        Intrinsics.checkNotNullParameter(str, "legacyHash");
    }

    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        super.onConfigure(supportSQLiteDatabase);
    }

    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        boolean hasEmptySchema$room_runtime_release = Companion.hasEmptySchema$room_runtime_release(supportSQLiteDatabase);
        this.delegate.createAllTables(supportSQLiteDatabase);
        if (!hasEmptySchema$room_runtime_release) {
            ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
            if (!onValidateSchema.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg);
            }
        }
        updateIdentity(supportSQLiteDatabase);
        this.delegate.onCreate(supportSQLiteDatabase);
    }

    public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        List<Migration> findMigrationPath;
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        DatabaseConfiguration databaseConfiguration = this.configuration;
        if (databaseConfiguration == null || (findMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(i, i2)) == null) {
            DatabaseConfiguration databaseConfiguration2 = this.configuration;
            if (databaseConfiguration2 == null || databaseConfiguration2.isMigrationRequired(i, i2)) {
                throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
            }
            this.delegate.dropAllTables(supportSQLiteDatabase);
            this.delegate.createAllTables(supportSQLiteDatabase);
            return;
        }
        this.delegate.onPreMigrate(supportSQLiteDatabase);
        for (Migration migrate : findMigrationPath) {
            migrate.migrate(supportSQLiteDatabase);
        }
        ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
        if (onValidateSchema.isValid) {
            this.delegate.onPostMigrate(supportSQLiteDatabase);
            updateIdentity(supportSQLiteDatabase);
            return;
        }
        throw new IllegalStateException("Migration didn't properly handle: " + onValidateSchema.expectedFoundMsg);
    }

    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        super.onOpen(supportSQLiteDatabase);
        checkIdentity(supportSQLiteDatabase);
        this.delegate.onOpen(supportSQLiteDatabase);
        this.configuration = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase r4) {
        /*
            r3 = this;
            androidx.room.RoomOpenHelper$Companion r0 = Companion
            boolean r0 = r0.hasRoomMasterTable$room_runtime_release(r4)
            if (r0 == 0) goto L_0x0064
            androidx.sqlite.db.SimpleSQLiteQuery r0 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r1 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r0.<init>(r1)
            androidx.sqlite.db.SupportSQLiteQuery r0 = (androidx.sqlite.db.SupportSQLiteQuery) r0
            android.database.Cursor r4 = r4.query((androidx.sqlite.db.SupportSQLiteQuery) r0)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r0 = r4
            android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ all -> 0x005d }
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x005d }
            r2 = 0
            if (r1 == 0) goto L_0x0027
            r1 = 0
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x005d }
            goto L_0x002b
        L_0x0027:
            r0 = r2
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x005d }
            r0 = r2
        L_0x002b:
            kotlin.io.CloseableKt.closeFinally(r4, r2)
            java.lang.String r4 = r3.identityHash
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)
            if (r4 != 0) goto L_0x0076
            java.lang.String r4 = r3.legacyHash
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)
            if (r4 == 0) goto L_0x003f
            goto L_0x0076
        L_0x003f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: "
            r1.<init>(r2)
            java.lang.String r2 = r3.identityHash
            r1.append(r2)
            java.lang.String r2 = ", found: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        L_0x005d:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x005f }
        L_0x005f:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r0)
            throw r1
        L_0x0064:
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            androidx.room.RoomOpenHelper$ValidationResult r0 = r0.onValidateSchema(r4)
            boolean r1 = r0.isValid
            if (r1 == 0) goto L_0x0077
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            r0.onPostMigrate(r4)
            r3.updateIdentity(r4)
        L_0x0076:
            return
        L_0x0077:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Pre-packaged database has an invalid schema: "
            r1.<init>(r2)
            java.lang.String r0 = r0.expectedFoundMsg
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    private final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        createMasterTableIfNotExists(supportSQLiteDatabase);
        supportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }

    private final void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0015R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/RoomOpenHelper$Delegate;", "", "version", "", "(I)V", "createAllTables", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "dropAllTables", "onCreate", "onOpen", "onPostMigrate", "onPreMigrate", "onValidateSchema", "Landroidx/room/RoomOpenHelper$ValidationResult;", "db", "validateMigration", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: RoomOpenHelper.kt */
    public static abstract class Delegate {
        public final int version;

        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        }

        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        }

        public Delegate(int i) {
            this.version = i;
        }

        /* access modifiers changed from: protected */
        @Deprecated(message = "Use [onValidateSchema(SupportSQLiteDatabase)]")
        public void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
            throw new UnsupportedOperationException("validateMigration is deprecated");
        }

        public ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
            validateMigration(supportSQLiteDatabase);
            return new ValidationResult(true, (String) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomOpenHelper$ValidationResult;", "", "isValid", "", "expectedFoundMsg", "", "(ZLjava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: RoomOpenHelper.kt */
    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/room/RoomOpenHelper$Companion;", "", "()V", "hasEmptySchema", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "hasEmptySchema$room_runtime_release", "hasRoomMasterTable", "hasRoomMasterTable$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: RoomOpenHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
            kotlin.io.CloseableKt.closeFinally(r4, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r4) {
            /*
                r3 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
                android.database.Cursor r4 = r4.query((java.lang.String) r0)
                java.io.Closeable r4 = (java.io.Closeable) r4
                r0 = r4
                android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ all -> 0x0023 }
                boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0023 }
                r2 = 0
                if (r1 == 0) goto L_0x001e
                int r0 = r0.getInt(r2)     // Catch:{ all -> 0x0023 }
                if (r0 == 0) goto L_0x001e
                r2 = 1
            L_0x001e:
                r0 = 0
                kotlin.io.CloseableKt.closeFinally(r4, r0)
                return r2
            L_0x0023:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x0025 }
            L_0x0025:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r4, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
            kotlin.io.CloseableKt.closeFinally(r4, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r4) {
            /*
                r3 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
                android.database.Cursor r4 = r4.query((java.lang.String) r0)
                java.io.Closeable r4 = (java.io.Closeable) r4
                r0 = r4
                android.database.Cursor r0 = (android.database.Cursor) r0     // Catch:{ all -> 0x0023 }
                boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0023 }
                r2 = 0
                if (r1 == 0) goto L_0x001e
                int r0 = r0.getInt(r2)     // Catch:{ all -> 0x0023 }
                if (r0 != 0) goto L_0x001e
                r2 = 1
            L_0x001e:
                r0 = 0
                kotlin.io.CloseableKt.closeFinally(r4, r0)
                return r2
            L_0x0023:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x0025 }
            L_0x0025:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r4, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }
    }
}

package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "obj", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: AutoClosingRoomOpenHelper.kt */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1 extends Lambda implements Function1<SupportSQLiteDatabase, String> {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$path$1() {
        super(1);
    }

    public final String invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "obj");
        return supportSQLiteDatabase.getPath();
    }
}

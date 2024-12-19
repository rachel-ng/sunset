package androidx.room;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"androidx/room/InvalidationTracker$refreshRunnable$1", "Ljava/lang/Runnable;", "checkUpdatedTable", "", "", "run", "", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: InvalidationTracker.kt */
public final class InvalidationTracker$refreshRunnable$1 implements Runnable {
    final /* synthetic */ InvalidationTracker this$0;

    InvalidationTracker$refreshRunnable$1(InvalidationTracker invalidationTracker) {
        this.this$0 = invalidationTracker;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        if (r0 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0082, code lost:
        r0.decrementCountAndScheduleClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a4, code lost:
        if (r0 == null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        if (r0 == null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c8, code lost:
        if (r2.isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ca, code lost:
        r0 = r4.this$0.getObserverMap$room_runtime_release();
        r1 = r4.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d2, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r1 = r1.getObserverMap$room_runtime_release().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e1, code lost:
        if (r1.hasNext() == false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e3, code lost:
        ((androidx.room.InvalidationTracker.ObserverWrapper) ((java.util.Map.Entry) r1.next()).getValue()).notifyByTableInvalidStatus$room_runtime_release(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f3, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f5, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.RoomDatabase r0 = r0.getDatabase$room_runtime_release()
            java.util.concurrent.locks.Lock r0 = r0.getCloseLock$room_runtime_release()
            r0.lock()
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            boolean r1 = r1.ensureInitialization$room_runtime_release()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            if (r1 != 0) goto L_0x0024
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0023
            r0.decrementCountAndScheduleClose()
        L_0x0023:
            return
        L_0x0024:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            java.util.concurrent.atomic.AtomicBoolean r1 = r1.getPendingRefresh()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            r2 = 1
            r3 = 0
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            if (r1 != 0) goto L_0x0041
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0040
            r0.decrementCountAndScheduleClose()
        L_0x0040:
            return
        L_0x0041:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            boolean r1 = r1.inTransaction()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            if (r1 == 0) goto L_0x005c
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x005b
            r0.decrementCountAndScheduleClose()
        L_0x005b:
            return
        L_0x005c:
            androidx.room.InvalidationTracker r1 = r4.this$0     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            androidx.room.RoomDatabase r1 = r1.getDatabase$room_runtime_release()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            androidx.sqlite.db.SupportSQLiteOpenHelper r1 = r1.getOpenHelper()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            androidx.sqlite.db.SupportSQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            r1.beginTransactionNonExclusive()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            java.util.Set r2 = r4.checkUpdatedTable()     // Catch:{ all -> 0x0086 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0086 }
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c1
        L_0x0082:
            r0.decrementCountAndScheduleClose()
            goto L_0x00c1
        L_0x0086:
            r2 = move-exception
            r1.endTransaction()     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
            throw r2     // Catch:{ IllegalStateException -> 0x00a7, SQLiteException -> 0x008d }
        L_0x008b:
            r1 = move-exception
            goto L_0x00fb
        L_0x008d:
            r1 = move-exception
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x008b }
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x008b }
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x008b }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c1
            goto L_0x0082
        L_0x00a7:
            r1 = move-exception
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "Cannot run invalidation tracker. Is the db closed?"
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x008b }
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x008b }
            java.util.Set r2 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x008b }
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x00c1
            goto L_0x0082
        L_0x00c1:
            r0 = r2
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00fa
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.arch.core.internal.SafeIterableMap r0 = r0.getObserverMap$room_runtime_release()
            androidx.room.InvalidationTracker r1 = r4.this$0
            monitor-enter(r0)
            androidx.arch.core.internal.SafeIterableMap r1 = r1.getObserverMap$room_runtime_release()     // Catch:{ all -> 0x00f7 }
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x00f7 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00f7 }
        L_0x00dd:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00f7 }
            if (r3 == 0) goto L_0x00f3
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00f7 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00f7 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00f7 }
            androidx.room.InvalidationTracker$ObserverWrapper r3 = (androidx.room.InvalidationTracker.ObserverWrapper) r3     // Catch:{ all -> 0x00f7 }
            r3.notifyByTableInvalidStatus$room_runtime_release(r2)     // Catch:{ all -> 0x00f7 }
            goto L_0x00dd
        L_0x00f3:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00f7 }
            monitor-exit(r0)
            goto L_0x00fa
        L_0x00f7:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x00fa:
            return
        L_0x00fb:
            r0.unlock()
            androidx.room.InvalidationTracker r0 = r4.this$0
            androidx.room.AutoCloser r0 = r0.autoCloser
            if (r0 == 0) goto L_0x0109
            r0.decrementCountAndScheduleClose()
        L_0x0109:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker$refreshRunnable$1.run():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0074, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.Set<java.lang.Integer> checkUpdatedTable() {
        /*
            r5 = this;
            androidx.room.InvalidationTracker r0 = r5.this$0
            java.util.Set r1 = kotlin.collections.SetsKt.createSetBuilder()
            androidx.room.RoomDatabase r0 = r0.getDatabase$room_runtime_release()
            androidx.sqlite.db.SimpleSQLiteQuery r2 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r3 = "SELECT * FROM room_table_modification_log WHERE invalidated = 1;"
            r2.<init>(r3)
            androidx.sqlite.db.SupportSQLiteQuery r2 = (androidx.sqlite.db.SupportSQLiteQuery) r2
            r3 = 2
            r4 = 0
            android.database.Cursor r0 = androidx.room.RoomDatabase.query$default(r0, r2, r4, r3, r4)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x006e }
        L_0x001e:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x006e }
            if (r3 == 0) goto L_0x0031
            r3 = 0
            int r3 = r2.getInt(r3)     // Catch:{ all -> 0x006e }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x006e }
            r1.add(r3)     // Catch:{ all -> 0x006e }
            goto L_0x001e
        L_0x0031:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006e }
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            java.util.Set r0 = kotlin.collections.SetsKt.build(r1)
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x006d
            androidx.room.InvalidationTracker r1 = r5.this$0
            androidx.sqlite.db.SupportSQLiteStatement r1 = r1.getCleanupStatement$room_runtime_release()
            java.lang.String r2 = "Required value was null."
            if (r1 == 0) goto L_0x0063
            androidx.room.InvalidationTracker r1 = r5.this$0
            androidx.sqlite.db.SupportSQLiteStatement r1 = r1.getCleanupStatement$room_runtime_release()
            if (r1 == 0) goto L_0x0059
            r1.executeUpdateDelete()
            goto L_0x006d
        L_0x0059:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x0063:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x006d:
            return r0
        L_0x006e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker$refreshRunnable$1.checkUpdatedTable():java.util.Set");
    }
}

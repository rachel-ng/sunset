package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

public final class EventStoreModule_DbNameFactory implements Factory<String> {
    public String get() {
        return dbName();
    }

    public static EventStoreModule_DbNameFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static String dbName() {
        return (String) Preconditions.checkNotNullFromProvides(EventStoreModule.dbName());
    }

    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final EventStoreModule_DbNameFactory INSTANCE = new EventStoreModule_DbNameFactory();

        private InstanceHolder() {
        }
    }
}

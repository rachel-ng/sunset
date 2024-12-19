package com.google.firebase.crashlytics.internal.metadata;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

public class UserMetadata {
    public static final String INTERNAL_KEYDATA_FILENAME = "internal-keys";
    public static final String KEYDATA_FILENAME = "keys";
    public static final int MAX_ATTRIBUTES = 64;
    public static final int MAX_ATTRIBUTE_SIZE = 1024;
    public static final int MAX_INTERNAL_KEY_SIZE = 8192;
    public static final int MAX_ROLLOUT_ASSIGNMENTS = 128;
    public static final String ROLLOUTS_STATE_FILENAME = "rollouts-state";
    public static final String USERDATA_FILENAME = "user-data";
    /* access modifiers changed from: private */
    public final CrashlyticsBackgroundWorker backgroundWorker;
    private final SerializeableKeysMap customKeys = new SerializeableKeysMap(false);
    private final SerializeableKeysMap internalKeys = new SerializeableKeysMap(true);
    /* access modifiers changed from: private */
    public final MetaDataStore metaDataStore;
    private final RolloutAssignmentList rolloutsState = new RolloutAssignmentList(128);
    /* access modifiers changed from: private */
    public String sessionIdentifier;
    private final AtomicMarkableReference<String> userId = new AtomicMarkableReference<>((Object) null, false);

    public static String readUserId(String str, FileStore fileStore) {
        return new MetaDataStore(fileStore).readUserId(str);
    }

    public static UserMetadata loadFromExistingSession(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        MetaDataStore metaDataStore2 = new MetaDataStore(fileStore);
        UserMetadata userMetadata = new UserMetadata(str, fileStore, crashlyticsBackgroundWorker);
        userMetadata.customKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, false));
        userMetadata.internalKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, true));
        userMetadata.userId.set(metaDataStore2.readUserId(str), false);
        userMetadata.rolloutsState.updateRolloutAssignmentList(metaDataStore2.readRolloutsState(str));
        return userMetadata;
    }

    public UserMetadata(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        this.sessionIdentifier = str;
        this.metaDataStore = new MetaDataStore(fileStore);
        this.backgroundWorker = crashlyticsBackgroundWorker;
    }

    public void setNewSession(String str) {
        synchronized (this.sessionIdentifier) {
            this.sessionIdentifier = str;
            Map<String, String> keys = this.customKeys.getKeys();
            List<RolloutAssignment> rolloutAssignmentList = this.rolloutsState.getRolloutAssignmentList();
            if (getUserId() != null) {
                this.metaDataStore.writeUserData(str, getUserId());
            }
            if (!keys.isEmpty()) {
                this.metaDataStore.writeKeyData(str, keys);
            }
            if (!rolloutAssignmentList.isEmpty()) {
                this.metaDataStore.writeRolloutState(str, rolloutAssignmentList);
            }
        }
    }

    public String getUserId() {
        return this.userId.getReference();
    }

    public void setUserId(String str) {
        String sanitizeString = KeysMap.sanitizeString(str, 1024);
        synchronized (this.userId) {
            if (!CommonUtils.nullSafeEquals(sanitizeString, this.userId.getReference())) {
                this.userId.set(sanitizeString, true);
                this.backgroundWorker.submit(new UserMetadata$$ExternalSyntheticLambda1(this));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUserId$0$com-google-firebase-crashlytics-internal-metadata-UserMetadata  reason: not valid java name */
    public /* synthetic */ Object m649lambda$setUserId$0$comgooglefirebasecrashlyticsinternalmetadataUserMetadata() throws Exception {
        serializeUserDataIfNeeded();
        return null;
    }

    public Map<String, String> getCustomKeys() {
        return this.customKeys.getKeys();
    }

    public boolean setCustomKey(String str, String str2) {
        return this.customKeys.setKey(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.customKeys.setKeys(map);
    }

    public Map<String, String> getInternalKeys() {
        return this.internalKeys.getKeys();
    }

    public boolean setInternalKey(String str, String str2) {
        return this.internalKeys.setKey(str, str2);
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutsState() {
        return this.rolloutsState.getReportRolloutsState();
    }

    public boolean updateRolloutsState(List<RolloutAssignment> list) {
        synchronized (this.rolloutsState) {
            if (!this.rolloutsState.updateRolloutAssignmentList(list)) {
                return false;
            }
            this.backgroundWorker.submit(new UserMetadata$$ExternalSyntheticLambda0(this, this.rolloutsState.getRolloutAssignmentList()));
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateRolloutsState$1$com-google-firebase-crashlytics-internal-metadata-UserMetadata  reason: not valid java name */
    public /* synthetic */ Object m650lambda$updateRolloutsState$1$comgooglefirebasecrashlyticsinternalmetadataUserMetadata(List list) throws Exception {
        this.metaDataStore.writeRolloutState(this.sessionIdentifier, list);
        return null;
    }

    private void serializeUserDataIfNeeded() {
        boolean z;
        String str;
        synchronized (this.userId) {
            z = false;
            if (this.userId.isMarked()) {
                str = getUserId();
                this.userId.set(str, false);
                z = true;
            } else {
                str = null;
            }
        }
        if (z) {
            this.metaDataStore.writeUserData(this.sessionIdentifier, str);
        }
    }

    private class SerializeableKeysMap {
        private final boolean isInternal;
        final AtomicMarkableReference<KeysMap> map;
        private final AtomicReference<Callable<Void>> queuedSerializer = new AtomicReference<>((Object) null);

        public SerializeableKeysMap(boolean z) {
            this.isInternal = z;
            this.map = new AtomicMarkableReference<>(new KeysMap(64, z ? 8192 : 1024), false);
        }

        public Map<String, String> getKeys() {
            return this.map.getReference().getKeys();
        }

        public boolean setKey(String str, String str2) {
            synchronized (this) {
                if (!this.map.getReference().setKey(str, str2)) {
                    return false;
                }
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
                scheduleSerializationTaskIfNeeded();
                return true;
            }
        }

        public void setKeys(Map<String, String> map2) {
            synchronized (this) {
                this.map.getReference().setKeys(map2);
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
            }
            scheduleSerializationTaskIfNeeded();
        }

        private void scheduleSerializationTaskIfNeeded() {
            UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 = new UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0(this);
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.queuedSerializer, (Object) null, userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0)) {
                UserMetadata.this.backgroundWorker.submit(userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$scheduleSerializationTaskIfNeeded$0$com-google-firebase-crashlytics-internal-metadata-UserMetadata$SerializeableKeysMap  reason: not valid java name */
        public /* synthetic */ Void m651lambda$scheduleSerializationTaskIfNeeded$0$comgooglefirebasecrashlyticsinternalmetadataUserMetadata$SerializeableKeysMap() throws Exception {
            this.queuedSerializer.set((Object) null);
            serializeIfMarked();
            return null;
        }

        private void serializeIfMarked() {
            Map<String, String> map2;
            synchronized (this) {
                if (this.map.isMarked()) {
                    map2 = this.map.getReference().getKeys();
                    AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                    atomicMarkableReference.set(atomicMarkableReference.getReference(), false);
                } else {
                    map2 = null;
                }
            }
            if (map2 != null) {
                UserMetadata.this.metaDataStore.writeKeyData(UserMetadata.this.sessionIdentifier, map2, this.isInternal);
            }
        }
    }
}

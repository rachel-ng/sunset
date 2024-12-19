package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfqz implements zzhbs {
    UNKNOWN(0),
    PROVIDENCE(1),
    INTENT_OPERATION(2),
    BROADCAST_RECEIVER(3),
    CONTENT_PROVIDER(4),
    ACTIVITY(5),
    SERVICE(6),
    BINDER(7),
    SYNC_ADAPTER(8),
    GCM_TASK(9),
    INTENT_SERVICE(10),
    SERVICE_CONNECTION(11),
    GCM_LISTENER(12),
    CALLBACKS(13),
    ALARM_LISTENER(14),
    CUSTOM_EVENT_LOOP(15),
    SENSOR_EVENT_LISTENER(16),
    BLE_SCAN_CALLBACK(17),
    BINDER_BY_INTERCEPTOR(18),
    CONTENT_OBSERVER(19),
    BACKUP_AGENT(20),
    SLICE_PROVIDER(21),
    LOCATION_LISTENER(22),
    GMS_APPLICATION(23),
    OAUTH(24),
    LOCATION_CALLBACKS(25),
    BT_ADAPTER(26),
    NETWORK_CALLBACK(27),
    JOB_SERVICE(28);
    
    private static final zzhbt zzD = null;
    private final int zzF;

    static {
        zzD = new zzfqy();
    }

    private zzfqz(int i) {
        this.zzF = i;
    }

    public final String toString() {
        return Integer.toString(this.zzF);
    }

    public final int zza() {
        return this.zzF;
    }
}

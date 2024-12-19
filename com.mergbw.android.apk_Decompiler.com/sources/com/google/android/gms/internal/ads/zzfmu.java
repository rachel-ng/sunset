package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfmu implements zzhbs {
    CUI_NAME_UNKNOWN(0),
    CUI_NAME_SDKINIT(1),
    CUI_NAME_SDKINIT_LOAD_FLAGS(25),
    CUI_NAME_SDKINIT_ACTIVITY_TRACKER(33),
    CUI_NAME_SDKINIT_CLD(2),
    CUI_NAME_SDKINIT_ADAPTERINIT(3),
    CUI_NAME_SDKINIT_SDKCORE(4),
    CUI_NAME_SDKINIT_INIT_CONSENT_STATE(28),
    CUI_NAME_SDKINIT_INIT_OMID(31),
    CUI_NAME_SDKINIT_INIT_SPAM(38),
    CUI_NAME_UPDATE_FLAGS_INIT_TASK(24),
    CUI_NAME_DEVICE_PROPERTIES_INIT_TASK(30),
    CUI_NAME_UPDATE_FLAGS(22),
    CUI_NAME_ADREQUEST(5),
    CUI_NAME_ADREQUEST_SIGNALS(6),
    CUI_NAME_ADREQUEST_BUILDURL(7),
    CUI_NAME_ADREQUEST_REQUEST(8),
    CUI_NAME_ADREQUEST_NORMALIZE_RESPONSE(26),
    CUI_NAME_ADREQUEST_PARSERESPONSE(9),
    CUI_NAME_ADREQUEST_MEDIATION(29),
    CUI_NAME_ADREQUEST_MEDIATION_ADAPTER(10),
    CUI_NAME_WEBVIEW_INITIALIZATION(36),
    CUI_NAME_WEBVIEW_LOAD(37),
    CUI_NAME_ADSHOW(11),
    CUI_NAME_NETWORK_CONNECTIVITY_MANAGER_STATE(32),
    CUI_NAME_IMPRESSION(34),
    CUI_NAME_VIEW_SIGNALS(39),
    CUI_NAME_CLICK(35),
    CUI_NAME_CLICK_SIGNALS(40),
    CUI_NAME_CONSENT_ALLOWLIST_UPDATE(42),
    CUI_NAME_CONSENT_STRING_READING(43),
    CUI_NAME_PING(12),
    CUI_NAME_PING_ATTESTATION(13),
    CUI_NAME_VIDEO_INIT(14),
    CUI_NAME_VIDEO_START(15),
    CUI_NAME_VIDEO_PLAY(16),
    CUI_NAME_VIDEO_PAUSE(17),
    CUI_NAME_VIDEO_RESUME(18),
    CUI_NAME_REWARD_GRANTED(19),
    CUI_NAME_SCAR_SIGNALS(20),
    CUI_NAME_SCAR_RENDERING(21),
    CUI_NAME_SCAR_CACHE_EVICTION(41),
    CUI_NAME_SIGNAL(23),
    CUI_NAME_GMSG(27),
    UNRECOGNIZED(-1);
    
    private static final zzhbt zzT = null;
    private final int zzV;

    static {
        zzT = new zzfmt();
    }

    private zzfmu(int i) {
        this.zzV = i;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzV;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}

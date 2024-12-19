package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;

public interface DrmSessionManager {
    public static final DrmSessionManager DRM_UNSUPPORTED;
    @Deprecated
    public static final DrmSessionManager DUMMY;

    public interface DrmSessionReference {
        public static final DrmSessionReference EMPTY = new DrmSessionManager$DrmSessionReference$$ExternalSyntheticLambda0();

        /* renamed from: com.google.android.exoplayer2.drm.DrmSessionManager$DrmSessionReference$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                DrmSessionReference drmSessionReference = DrmSessionReference.EMPTY;
            }

            public static /* synthetic */ void lambda$static$0() {
            }
        }

        void release();
    }

    DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    int getCryptoType(Format format);

    DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format);

    void prepare();

    void release();

    void setPlayer(Looper looper, PlayerId playerId);

    static {
        AnonymousClass1 r0 = new DrmSessionManager() {
            public /* synthetic */ DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
                return CC.$default$preacquireSession(this, eventDispatcher, format);
            }

            public /* synthetic */ void prepare() {
                CC.$default$prepare(this);
            }

            public /* synthetic */ void release() {
                CC.$default$release(this);
            }

            public void setPlayer(Looper looper, PlayerId playerId) {
            }

            public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
                if (format.drmInitData == null) {
                    return null;
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1), PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED));
            }

            public int getCryptoType(Format format) {
                return format.drmInitData != null ? 1 : 0;
            }
        };
        DRM_UNSUPPORTED = r0;
        DUMMY = r0;
    }

    /* renamed from: com.google.android.exoplayer2.drm.DrmSessionManager$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$prepare(DrmSessionManager _this) {
        }

        public static void $default$release(DrmSessionManager _this) {
        }

        static {
            DrmSessionManager drmSessionManager = DrmSessionManager.DRM_UNSUPPORTED;
        }

        @Deprecated
        public static DrmSessionManager getDummyDrmSessionManager() {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }

        public static DrmSessionReference $default$preacquireSession(DrmSessionManager _this, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
            return DrmSessionReference.EMPTY;
        }
    }
}

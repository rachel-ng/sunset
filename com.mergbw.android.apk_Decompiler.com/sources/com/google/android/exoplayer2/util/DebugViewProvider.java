package com.google.android.exoplayer2.util;

import android.view.SurfaceView;

public interface DebugViewProvider {
    public static final DebugViewProvider NONE = new DebugViewProvider$$ExternalSyntheticLambda0();

    /* renamed from: com.google.android.exoplayer2.util.DebugViewProvider$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            DebugViewProvider debugViewProvider = DebugViewProvider.NONE;
        }

        public static /* synthetic */ SurfaceView lambda$static$0(int i, int i2) {
            return null;
        }
    }

    SurfaceView getDebugPreviewSurfaceView(int i, int i2);
}

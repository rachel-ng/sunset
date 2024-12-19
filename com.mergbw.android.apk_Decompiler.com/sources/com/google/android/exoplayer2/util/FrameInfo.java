package com.google.android.exoplayer2.util;

public class FrameInfo {
    public final int height;
    public final float pixelWidthHeightRatio;
    public final long streamOffsetUs;
    public final int width;

    public FrameInfo(int i, int i2, float f, long j) {
        boolean z = true;
        boolean z2 = i > 0;
        Assertions.checkArgument(z2, "width must be positive, but is: " + i);
        z = i2 <= 0 ? false : z;
        Assertions.checkArgument(z, "height must be positive, but is: " + i2);
        this.width = i;
        this.height = i2;
        this.pixelWidthHeightRatio = f;
        this.streamOffsetUs = j;
    }
}

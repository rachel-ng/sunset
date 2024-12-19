package com.google.android.exoplayer2.util;

import android.view.Surface;

public final class SurfaceInfo {
    public final int height;
    public final int orientationDegrees;
    public final Surface surface;
    public final int width;

    public SurfaceInfo(Surface surface2, int i, int i2) {
        this(surface2, i, i2, 0);
    }

    public SurfaceInfo(Surface surface2, int i, int i2, int i3) {
        Assertions.checkArgument(i3 == 0 || i3 == 90 || i3 == 180 || i3 == 270, "orientationDegrees must be 0, 90, 180, or 270");
        this.surface = surface2;
        this.width = i;
        this.height = i2;
        this.orientationDegrees = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SurfaceInfo)) {
            return false;
        }
        SurfaceInfo surfaceInfo = (SurfaceInfo) obj;
        if (this.width == surfaceInfo.width && this.height == surfaceInfo.height && this.orientationDegrees == surfaceInfo.orientationDegrees && this.surface.equals(surfaceInfo.surface)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.surface.hashCode() * 31) + this.width) * 31) + this.height) * 31) + this.orientationDegrees;
    }
}

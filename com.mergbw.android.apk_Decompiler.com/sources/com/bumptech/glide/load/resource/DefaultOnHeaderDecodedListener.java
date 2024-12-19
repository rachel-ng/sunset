package com.bumptech.glide.load.resource;

import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.graphics.ImageDecoder$OnPartialImageListener;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import androidx.core.graphics.ColorKt$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;

public final class DefaultOnHeaderDecodedListener implements ImageDecoder$OnHeaderDecodedListener {
    private static final String TAG = "ImageDecoder";
    private final DecodeFormat decodeFormat;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final boolean isHardwareConfigAllowed;
    private final PreferredColorSpace preferredColorSpace;
    private final int requestedHeight;
    private final int requestedWidth;
    private final DownsampleStrategy strategy;

    public DefaultOnHeaderDecodedListener(int i, int i2, Options options) {
        this.requestedWidth = i;
        this.requestedHeight = i2;
        this.decodeFormat = (DecodeFormat) options.get(Downsampler.DECODE_FORMAT);
        this.strategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        this.isHardwareConfigAllowed = options.get(Downsampler.ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options.get(Downsampler.ALLOW_HARDWARE_CONFIG)).booleanValue();
        this.preferredColorSpace = (PreferredColorSpace) options.get(Downsampler.PREFERRED_COLOR_SPACE);
    }

    public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        if (this.hardwareConfigState.isHardwareConfigAllowed(this.requestedWidth, this.requestedHeight, this.isHardwareConfigAllowed, false)) {
            imageDecoder.setAllocator(3);
        } else {
            imageDecoder.setAllocator(1);
        }
        if (this.decodeFormat == DecodeFormat.PREFER_RGB_565) {
            imageDecoder.setMemorySizePolicy(0);
        }
        imageDecoder.setOnPartialImageListener(new ImageDecoder$OnPartialImageListener() {
            public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                return false;
            }
        });
        Size m = DiskLruCache$$ExternalSyntheticApiModelOutline0.m(imageInfo);
        int i = this.requestedWidth;
        if (i == Integer.MIN_VALUE) {
            i = m.getWidth();
        }
        int i2 = this.requestedHeight;
        if (i2 == Integer.MIN_VALUE) {
            i2 = m.getHeight();
        }
        float scaleFactor = this.strategy.getScaleFactor(m.getWidth(), m.getHeight(), i, i2);
        int round = Math.round(((float) m.getWidth()) * scaleFactor);
        int round2 = Math.round(((float) m.getHeight()) * scaleFactor);
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Resizing from [" + m.getWidth() + "x" + m.getHeight() + "] to [" + round + "x" + round2 + "] scaleFactor: " + scaleFactor);
        }
        imageDecoder.setTargetSize(round, round2);
        if (this.preferredColorSpace == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            imageDecoder.setTargetColorSpace(ColorSpace.get((this.preferredColorSpace != PreferredColorSpace.DISPLAY_P3 || DiskLruCache$$ExternalSyntheticApiModelOutline0.m(imageInfo) == null || !DiskLruCache$$ExternalSyntheticApiModelOutline0.m(imageInfo).isWideGamut()) ? ColorKt$$ExternalSyntheticApiModelOutline0.m() : DiskLruCache$$ExternalSyntheticApiModelOutline0.m()));
        } else if (Build.VERSION.SDK_INT >= 26) {
            imageDecoder.setTargetColorSpace(ColorSpace.get(ColorKt$$ExternalSyntheticApiModelOutline0.m()));
        }
    }
}

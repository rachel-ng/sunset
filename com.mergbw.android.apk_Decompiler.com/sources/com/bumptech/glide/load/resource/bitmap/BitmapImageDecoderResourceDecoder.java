package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.util.Log;
import androidx.core.graphics.ColorKt$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import java.io.IOException;

public final class BitmapImageDecoderResourceDecoder implements ResourceDecoder<ImageDecoder.Source, Bitmap> {
    private static final String TAG = "BitmapImageDecoder";
    private final BitmapPool bitmapPool = new BitmapPoolAdapter();

    public boolean handles(ImageDecoder.Source source, Options options) throws IOException {
        return true;
    }

    public /* bridge */ /* synthetic */ Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        return decode(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj), i, i2, options);
    }

    public /* bridge */ /* synthetic */ boolean handles(Object obj, Options options) throws IOException {
        return handles(DiskLruCache$$ExternalSyntheticApiModelOutline0.m(obj), options);
    }

    public Resource<Bitmap> decode(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        Bitmap m = ColorKt$$ExternalSyntheticApiModelOutline0.m(source, (ImageDecoder$OnHeaderDecodedListener) new DefaultOnHeaderDecodedListener(i, i2, options));
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Decoded [" + m.getWidth() + "x" + m.getHeight() + "] for [" + i + "x" + i2 + "]");
        }
        return new BitmapResource(m, this.bitmapPool);
    }
}

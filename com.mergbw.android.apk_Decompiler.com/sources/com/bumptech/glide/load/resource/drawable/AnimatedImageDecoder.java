package com.bumptech.glide.load.resource.drawable;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.graphics.ColorKt$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import com.bumptech.glide.util.ByteBufferUtil;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class AnimatedImageDecoder {
    private final ArrayPool arrayPool;
    private final List<ImageHeaderParser> imageHeaderParsers;

    public static ResourceDecoder<InputStream, Drawable> streamDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool2) {
        return new StreamAnimatedImageDecoder(new AnimatedImageDecoder(list, arrayPool2));
    }

    public static ResourceDecoder<ByteBuffer, Drawable> byteBufferDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool2) {
        return new ByteBufferAnimatedImageDecoder(new AnimatedImageDecoder(list, arrayPool2));
    }

    private AnimatedImageDecoder(List<ImageHeaderParser> list, ArrayPool arrayPool2) {
        this.imageHeaderParsers = list;
        this.arrayPool = arrayPool2;
    }

    /* access modifiers changed from: package-private */
    public boolean handles(ByteBuffer byteBuffer) throws IOException {
        return isHandled(ImageHeaderParserUtils.getType(this.imageHeaderParsers, byteBuffer));
    }

    /* access modifiers changed from: package-private */
    public boolean handles(InputStream inputStream) throws IOException {
        return isHandled(ImageHeaderParserUtils.getType(this.imageHeaderParsers, inputStream, this.arrayPool));
    }

    private boolean isHandled(ImageHeaderParser.ImageType imageType) {
        return imageType == ImageHeaderParser.ImageType.ANIMATED_WEBP || (Build.VERSION.SDK_INT >= 31 && imageType == ImageHeaderParser.ImageType.ANIMATED_AVIF);
    }

    /* access modifiers changed from: package-private */
    public Resource<Drawable> decode(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        Drawable m = ColorKt$$ExternalSyntheticApiModelOutline0.m(source, (ImageDecoder$OnHeaderDecodedListener) new DefaultOnHeaderDecodedListener(i, i2, options));
        if (DiskLruCache$$ExternalSyntheticApiModelOutline0.m$6(m)) {
            return new AnimatedImageDrawableResource(DiskLruCache$$ExternalSyntheticApiModelOutline0.m((Object) m));
        }
        throw new IOException("Received unexpected drawable type for animated image, failing: " + m);
    }

    private static final class AnimatedImageDrawableResource implements Resource<Drawable> {
        private static final int ESTIMATED_NUMBER_OF_FRAMES = 2;
        private final AnimatedImageDrawable imageDrawable;

        AnimatedImageDrawableResource(AnimatedImageDrawable animatedImageDrawable) {
            this.imageDrawable = animatedImageDrawable;
        }

        public Class<Drawable> getResourceClass() {
            return Drawable.class;
        }

        public AnimatedImageDrawable get() {
            return this.imageDrawable;
        }

        public int getSize() {
            return DiskLruCache$$ExternalSyntheticApiModelOutline0.m(this.imageDrawable) * DiskLruCache$$ExternalSyntheticApiModelOutline0.m$1(this.imageDrawable) * Util.getBytesPerPixel(Bitmap.Config.ARGB_8888) * 2;
        }

        public void recycle() {
            DiskLruCache$$ExternalSyntheticApiModelOutline0.m(this.imageDrawable);
            DiskLruCache$$ExternalSyntheticApiModelOutline0.m$1(this.imageDrawable);
        }
    }

    private static final class StreamAnimatedImageDecoder implements ResourceDecoder<InputStream, Drawable> {
        private final AnimatedImageDecoder delegate;

        StreamAnimatedImageDecoder(AnimatedImageDecoder animatedImageDecoder) {
            this.delegate = animatedImageDecoder;
        }

        public boolean handles(InputStream inputStream, Options options) throws IOException {
            return this.delegate.handles(inputStream);
        }

        public Resource<Drawable> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
            return this.delegate.decode(ImageDecoder.createSource(ByteBufferUtil.fromStream(inputStream)), i, i2, options);
        }
    }

    private static final class ByteBufferAnimatedImageDecoder implements ResourceDecoder<ByteBuffer, Drawable> {
        private final AnimatedImageDecoder delegate;

        ByteBufferAnimatedImageDecoder(AnimatedImageDecoder animatedImageDecoder) {
            this.delegate = animatedImageDecoder;
        }

        public boolean handles(ByteBuffer byteBuffer, Options options) throws IOException {
            return this.delegate.handles(byteBuffer);
        }

        public Resource<Drawable> decode(ByteBuffer byteBuffer, int i, int i2, Options options) throws IOException {
            return this.delegate.decode(ImageDecoder.createSource(byteBuffer), i, i2, options);
        }
    }
}

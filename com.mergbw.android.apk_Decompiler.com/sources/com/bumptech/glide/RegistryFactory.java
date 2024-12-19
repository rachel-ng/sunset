package com.bumptech.glide;

import android.content.Context;
import androidx.tracing.Trace;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.util.GlideSuppliers;
import java.util.List;

final class RegistryFactory {
    private RegistryFactory() {
    }

    static GlideSuppliers.GlideSupplier<Registry> lazilyCreateAndInitializeRegistry(final Glide glide, final List<GlideModule> list, final AppGlideModule appGlideModule) {
        return new GlideSuppliers.GlideSupplier<Registry>() {
            private boolean isInitializing;

            public Registry get() {
                if (!this.isInitializing) {
                    Trace.beginSection("Glide registry");
                    this.isInitializing = true;
                    try {
                        return RegistryFactory.createAndInitRegistry(Glide.this, list, appGlideModule);
                    } finally {
                        this.isInitializing = false;
                        Trace.endSection();
                    }
                } else {
                    throw new IllegalStateException("Recursive Registry initialization! In your AppGlideModule and LibraryGlideModules, Make sure you're using the provided Registry rather calling glide.getRegistry()!");
                }
            }
        };
    }

    static Registry createAndInitRegistry(Glide glide, List<GlideModule> list, AppGlideModule appGlideModule) {
        BitmapPool bitmapPool = glide.getBitmapPool();
        ArrayPool arrayPool = glide.getArrayPool();
        Context applicationContext = glide.getGlideContext().getApplicationContext();
        GlideExperiments experiments = glide.getGlideContext().getExperiments();
        Registry registry = new Registry();
        initializeDefaults(applicationContext, registry, bitmapPool, arrayPool, experiments);
        initializeModules(applicationContext, glide, registry, list, appGlideModule);
        return registry;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x01e3  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0300  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void initializeDefaults(android.content.Context r19, com.bumptech.glide.Registry r20, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r21, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r22, com.bumptech.glide.GlideExperiments r23) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser r4 = new com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser
            r4.<init>()
            r1.register((com.bumptech.glide.load.ImageHeaderParser) r4)
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 27
            if (r4 < r5) goto L_0x001e
            com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser r4 = new com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser
            r4.<init>()
            r1.register((com.bumptech.glide.load.ImageHeaderParser) r4)
        L_0x001e:
            android.content.res.Resources r4 = r19.getResources()
            java.util.List r5 = r20.getImageHeaderParsers()
            com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder r6 = new com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder
            r6.<init>(r0, r5, r2, r3)
            com.bumptech.glide.load.ResourceDecoder r7 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.parcel(r21)
            com.bumptech.glide.load.resource.bitmap.Downsampler r8 = new com.bumptech.glide.load.resource.bitmap.Downsampler
            java.util.List r9 = r20.getImageHeaderParsers()
            android.util.DisplayMetrics r10 = r4.getDisplayMetrics()
            r8.<init>(r9, r10, r2, r3)
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 28
            if (r9 < r10) goto L_0x0057
            java.lang.Class<com.bumptech.glide.GlideBuilder$EnableImageDecoderForBitmaps> r9 = com.bumptech.glide.GlideBuilder.EnableImageDecoderForBitmaps.class
            r11 = r23
            boolean r9 = r11.isEnabled(r9)
            if (r9 == 0) goto L_0x0057
            com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder r9 = new com.bumptech.glide.load.resource.bitmap.InputStreamBitmapImageDecoderResourceDecoder
            r9.<init>()
            com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder r11 = new com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapImageDecoderResourceDecoder
            r11.<init>()
            goto L_0x0061
        L_0x0057:
            com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder r11 = new com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder
            r11.<init>(r8)
            com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder r9 = new com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder
            r9.<init>(r8, r3)
        L_0x0061:
            int r12 = android.os.Build.VERSION.SDK_INT
            java.lang.String r13 = "Animation"
            if (r12 < r10) goto L_0x007d
            java.lang.Class<java.io.InputStream> r10 = java.io.InputStream.class
            java.lang.Class<android.graphics.drawable.Drawable> r12 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.ResourceDecoder r14 = com.bumptech.glide.load.resource.drawable.AnimatedImageDecoder.streamDecoder(r5, r3)
            r1.append(r13, r10, r12, r14)
            java.lang.Class<java.nio.ByteBuffer> r10 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.Drawable> r12 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.ResourceDecoder r14 = com.bumptech.glide.load.resource.drawable.AnimatedImageDecoder.byteBufferDecoder(r5, r3)
            r1.append(r13, r10, r12, r14)
        L_0x007d:
            com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder r10 = new com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder
            r10.<init>(r0)
            com.bumptech.glide.load.resource.bitmap.BitmapEncoder r12 = new com.bumptech.glide.load.resource.bitmap.BitmapEncoder
            r12.<init>(r3)
            com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder r14 = new com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder
            r14.<init>()
            com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder r15 = new com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder
            r15.<init>()
            r23 = r15
            android.content.ContentResolver r15 = r19.getContentResolver()
            r16 = r14
            java.lang.Class<java.nio.ByteBuffer> r14 = java.nio.ByteBuffer.class
            r17 = r15
            com.bumptech.glide.load.model.ByteBufferEncoder r15 = new com.bumptech.glide.load.model.ByteBufferEncoder
            r15.<init>()
            com.bumptech.glide.Registry r14 = r1.append(r14, r15)
            java.lang.Class<java.io.InputStream> r15 = java.io.InputStream.class
            com.bumptech.glide.load.model.StreamEncoder r0 = new com.bumptech.glide.load.model.StreamEncoder
            r0.<init>(r3)
            com.bumptech.glide.Registry r0 = r14.append(r15, r0)
            java.lang.Class<java.nio.ByteBuffer> r14 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.Bitmap> r15 = android.graphics.Bitmap.class
            r18 = r10
            java.lang.String r10 = "Bitmap"
            com.bumptech.glide.Registry r0 = r0.append(r10, r14, r15, r11)
            java.lang.Class<java.io.InputStream> r14 = java.io.InputStream.class
            java.lang.Class<android.graphics.Bitmap> r15 = android.graphics.Bitmap.class
            r0.append(r10, r14, r15, r9)
            boolean r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.isSupported()
            if (r0 == 0) goto L_0x00d6
            java.lang.Class<android.os.ParcelFileDescriptor> r0 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder r15 = new com.bumptech.glide.load.resource.bitmap.ParcelFileDescriptorBitmapDecoder
            r15.<init>(r8)
            r1.append(r10, r0, r14, r15)
        L_0x00d6:
            java.lang.Class<android.content.res.AssetFileDescriptor> r0 = android.content.res.AssetFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            com.bumptech.glide.load.ResourceDecoder r14 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.asset(r21)
            r1.append(r10, r0, r8, r14)
            java.lang.Class<android.os.ParcelFileDescriptor> r0 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            com.bumptech.glide.Registry r0 = r1.append(r10, r0, r8, r7)
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r15 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r8, r14, r15)
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.Bitmap> r14 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder r15 = new com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder
            r15.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r10, r8, r14, r15)
            java.lang.Class<android.graphics.Bitmap> r8 = android.graphics.Bitmap.class
            com.bumptech.glide.Registry r0 = r0.append(r8, r12)
            java.lang.Class<java.nio.ByteBuffer> r8 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r14 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r15 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r15.<init>((android.content.res.Resources) r4, r11)
            java.lang.String r11 = "BitmapDrawable"
            com.bumptech.glide.Registry r0 = r0.append(r11, r8, r14, r15)
            java.lang.Class<java.io.InputStream> r8 = java.io.InputStream.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r14 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r15 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r15.<init>((android.content.res.Resources) r4, r9)
            com.bumptech.glide.Registry r0 = r0.append(r11, r8, r14, r15)
            java.lang.Class<android.os.ParcelFileDescriptor> r8 = android.os.ParcelFileDescriptor.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r9 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r14 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r14.<init>((android.content.res.Resources) r4, r7)
            com.bumptech.glide.Registry r0 = r0.append(r11, r8, r9, r14)
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r7 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder r8 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder
            r8.<init>(r2, r12)
            com.bumptech.glide.Registry r0 = r0.append(r7, r8)
            java.lang.Class<java.io.InputStream> r7 = java.io.InputStream.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r8 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.StreamGifDecoder r9 = new com.bumptech.glide.load.resource.gif.StreamGifDecoder
            r9.<init>(r5, r6, r3)
            com.bumptech.glide.Registry r0 = r0.append(r13, r7, r8, r9)
            java.lang.Class<java.nio.ByteBuffer> r5 = java.nio.ByteBuffer.class
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r7 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.Registry r0 = r0.append(r13, r5, r7, r6)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r5 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            com.bumptech.glide.load.resource.gif.GifDrawableEncoder r6 = new com.bumptech.glide.load.resource.gif.GifDrawableEncoder
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6)
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r5 = com.bumptech.glide.gifdecoder.GifDecoder.class
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r6 = com.bumptech.glide.gifdecoder.GifDecoder.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<com.bumptech.glide.gifdecoder.GifDecoder> r5 = com.bumptech.glide.gifdecoder.GifDecoder.class
            java.lang.Class<android.graphics.Bitmap> r6 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder r7 = new com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder
            r7.<init>(r2)
            com.bumptech.glide.Registry r0 = r0.append(r10, r5, r6, r7)
            java.lang.Class<android.net.Uri> r5 = android.net.Uri.class
            java.lang.Class<android.graphics.drawable.Drawable> r6 = android.graphics.drawable.Drawable.class
            r7 = r18
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<android.net.Uri> r5 = android.net.Uri.class
            java.lang.Class<android.graphics.Bitmap> r6 = android.graphics.Bitmap.class
            com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder r8 = new com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder
            r8.<init>(r7, r2)
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r8)
            com.bumptech.glide.load.resource.bytes.ByteBufferRewinder$Factory r5 = new com.bumptech.glide.load.resource.bytes.ByteBufferRewinder$Factory
            r5.<init>()
            com.bumptech.glide.Registry r0 = r0.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r5)
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.Class<java.nio.ByteBuffer> r6 = java.nio.ByteBuffer.class
            com.bumptech.glide.load.model.ByteBufferFileLoader$Factory r7 = new com.bumptech.glide.load.model.ByteBufferFileLoader$Factory
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            com.bumptech.glide.load.model.FileLoader$StreamFactory r7 = new com.bumptech.glide.load.model.FileLoader$StreamFactory
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.Class<java.io.File> r6 = java.io.File.class
            com.bumptech.glide.load.resource.file.FileDecoder r7 = new com.bumptech.glide.load.resource.file.FileDecoder
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.Class<android.os.ParcelFileDescriptor> r6 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.FileLoader$FileDescriptorFactory r7 = new com.bumptech.glide.load.model.FileLoader$FileDescriptorFactory
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            java.lang.Class<java.io.File> r5 = java.io.File.class
            java.lang.Class<java.io.File> r6 = java.io.File.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r5, r6, r7)
            com.bumptech.glide.load.data.InputStreamRewinder$Factory r5 = new com.bumptech.glide.load.data.InputStreamRewinder$Factory
            r5.<init>(r3)
            r0.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r5)
            boolean r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.isSupported()
            if (r0 == 0) goto L_0x01eb
            com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$Factory r0 = new com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$Factory
            r0.<init>()
            r1.register((com.bumptech.glide.load.data.DataRewinder.Factory<?>) r0)
        L_0x01eb:
            com.bumptech.glide.load.model.ModelLoaderFactory r0 = com.bumptech.glide.load.model.DirectResourceLoader.inputStreamFactory(r19)
            com.bumptech.glide.load.model.ModelLoaderFactory r3 = com.bumptech.glide.load.model.DirectResourceLoader.assetFileDescriptorFactory(r19)
            com.bumptech.glide.load.model.ModelLoaderFactory r5 = com.bumptech.glide.load.model.DirectResourceLoader.drawableFactory(r19)
            java.lang.Class r6 = java.lang.Integer.TYPE
            java.lang.Class<java.io.InputStream> r7 = java.io.InputStream.class
            com.bumptech.glide.Registry r6 = r1.append(r6, r7, r0)
            java.lang.Class<java.lang.Integer> r7 = java.lang.Integer.class
            java.lang.Class<java.io.InputStream> r8 = java.io.InputStream.class
            com.bumptech.glide.Registry r0 = r6.append(r7, r8, r0)
            java.lang.Class r6 = java.lang.Integer.TYPE
            java.lang.Class<android.content.res.AssetFileDescriptor> r7 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.Registry r0 = r0.append(r6, r7, r3)
            java.lang.Class<java.lang.Integer> r6 = java.lang.Integer.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r7 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.Registry r0 = r0.append(r6, r7, r3)
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class<android.graphics.drawable.Drawable> r6 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.Registry r0 = r0.append(r3, r6, r5)
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            java.lang.Class<android.graphics.drawable.Drawable> r6 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.Registry r0 = r0.append(r3, r6, r5)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.ModelLoaderFactory r6 = com.bumptech.glide.load.model.ResourceUriLoader.newStreamFactory(r19)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r5 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.ModelLoaderFactory r6 = com.bumptech.glide.load.model.ResourceUriLoader.newAssetFileDescriptorFactory(r19)
            r0.append(r3, r5, r6)
            com.bumptech.glide.load.model.ResourceLoader$UriFactory r0 = new com.bumptech.glide.load.model.ResourceLoader$UriFactory
            r0.<init>(r4)
            com.bumptech.glide.load.model.ResourceLoader$AssetFileDescriptorFactory r3 = new com.bumptech.glide.load.model.ResourceLoader$AssetFileDescriptorFactory
            r3.<init>(r4)
            com.bumptech.glide.load.model.ResourceLoader$StreamFactory r5 = new com.bumptech.glide.load.model.ResourceLoader$StreamFactory
            r5.<init>(r4)
            java.lang.Class<java.lang.Integer> r6 = java.lang.Integer.class
            java.lang.Class<android.net.Uri> r7 = android.net.Uri.class
            com.bumptech.glide.Registry r6 = r1.append(r6, r7, r0)
            java.lang.Class r7 = java.lang.Integer.TYPE
            java.lang.Class<android.net.Uri> r8 = android.net.Uri.class
            com.bumptech.glide.Registry r0 = r6.append(r7, r8, r0)
            java.lang.Class<java.lang.Integer> r6 = java.lang.Integer.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r7 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.Registry r0 = r0.append(r6, r7, r3)
            java.lang.Class r6 = java.lang.Integer.TYPE
            java.lang.Class<android.content.res.AssetFileDescriptor> r7 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.Registry r0 = r0.append(r6, r7, r3)
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            com.bumptech.glide.Registry r0 = r0.append(r3, r6, r5)
            java.lang.Class r3 = java.lang.Integer.TYPE
            java.lang.Class<java.io.InputStream> r6 = java.io.InputStream.class
            r0.append(r3, r6, r5)
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.DataUrlLoader$StreamFactory r5 = new com.bumptech.glide.load.model.DataUrlLoader$StreamFactory
            r5.<init>()
            com.bumptech.glide.Registry r0 = r1.append(r0, r3, r5)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.DataUrlLoader$StreamFactory r6 = new com.bumptech.glide.load.model.DataUrlLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.StringLoader$StreamFactory r6 = new com.bumptech.glide.load.model.StringLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.Class<android.os.ParcelFileDescriptor> r5 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.StringLoader$FileDescriptorFactory r6 = new com.bumptech.glide.load.model.StringLoader$FileDescriptorFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r5 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.StringLoader$AssetFileDescriptorFactory r6 = new com.bumptech.glide.load.model.StringLoader$AssetFileDescriptorFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.AssetUriLoader$StreamFactory r6 = new com.bumptech.glide.load.model.AssetUriLoader$StreamFactory
            android.content.res.AssetManager r7 = r19.getAssets()
            r6.<init>(r7)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r5 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.AssetUriLoader$FileDescriptorFactory r6 = new com.bumptech.glide.load.model.AssetUriLoader$FileDescriptorFactory
            android.content.res.AssetManager r7 = r19.getAssets()
            r6.<init>(r7)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader$Factory r6 = new com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader$Factory
            r7 = r19
            r6.<init>(r7)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader$Factory r6 = new com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader$Factory
            r6.<init>(r7)
            r0.append(r3, r5, r6)
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 29
            if (r0 < r3) goto L_0x0318
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$InputStreamFactory r5 = new com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$InputStreamFactory
            r5.<init>(r7)
            r1.append(r0, r3, r5)
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<android.os.ParcelFileDescriptor> r3 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$FileDescriptorFactory r5 = new com.bumptech.glide.load.model.stream.QMediaStoreUriLoader$FileDescriptorFactory
            r5.<init>(r7)
            r1.append(r0, r3, r5)
        L_0x0318:
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.UriLoader$StreamFactory r5 = new com.bumptech.glide.load.model.UriLoader$StreamFactory
            r6 = r17
            r5.<init>(r6)
            com.bumptech.glide.Registry r0 = r1.append(r0, r3, r5)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.os.ParcelFileDescriptor> r5 = android.os.ParcelFileDescriptor.class
            com.bumptech.glide.load.model.UriLoader$FileDescriptorFactory r8 = new com.bumptech.glide.load.model.UriLoader$FileDescriptorFactory
            r8.<init>(r6)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r8)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.content.res.AssetFileDescriptor> r5 = android.content.res.AssetFileDescriptor.class
            com.bumptech.glide.load.model.UriLoader$AssetFileDescriptorFactory r8 = new com.bumptech.glide.load.model.UriLoader$AssetFileDescriptorFactory
            r8.<init>(r6)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r8)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.UrlUriLoader$StreamFactory r6 = new com.bumptech.glide.load.model.UrlUriLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<java.net.URL> r3 = java.net.URL.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.UrlLoader$StreamFactory r6 = new com.bumptech.glide.load.model.stream.UrlLoader$StreamFactory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<java.io.File> r5 = java.io.File.class
            com.bumptech.glide.load.model.MediaStoreFileLoader$Factory r6 = new com.bumptech.glide.load.model.MediaStoreFileLoader$Factory
            r6.<init>(r7)
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<com.bumptech.glide.load.model.GlideUrl> r3 = com.bumptech.glide.load.model.GlideUrl.class
            java.lang.Class<java.io.InputStream> r5 = java.io.InputStream.class
            com.bumptech.glide.load.model.stream.HttpGlideUrlLoader$Factory r6 = new com.bumptech.glide.load.model.stream.HttpGlideUrlLoader$Factory
            r6.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r6)
            java.lang.Class<java.nio.ByteBuffer> r3 = java.nio.ByteBuffer.class
            com.bumptech.glide.load.model.ByteArrayLoader$ByteBufferFactory r5 = new com.bumptech.glide.load.model.ByteArrayLoader$ByteBufferFactory
            r5.<init>()
            java.lang.Class<byte[]> r6 = byte[].class
            com.bumptech.glide.Registry r0 = r0.append(r6, r3, r5)
            java.lang.Class<java.io.InputStream> r3 = java.io.InputStream.class
            com.bumptech.glide.load.model.ByteArrayLoader$StreamFactory r5 = new com.bumptech.glide.load.model.ByteArrayLoader$StreamFactory
            r5.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r6, r3, r5)
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            java.lang.Class<android.net.Uri> r5 = android.net.Uri.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r7)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            java.lang.Class<android.graphics.drawable.Drawable> r5 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.model.UnitModelLoader$Factory r7 = com.bumptech.glide.load.model.UnitModelLoader.Factory.getInstance()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r7)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            java.lang.Class<android.graphics.drawable.Drawable> r5 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder r7 = new com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder
            r7.<init>()
            com.bumptech.glide.Registry r0 = r0.append(r3, r5, r7)
            java.lang.Class<android.graphics.Bitmap> r3 = android.graphics.Bitmap.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r5 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder r7 = new com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder
            r7.<init>((android.content.res.Resources) r4)
            com.bumptech.glide.Registry r0 = r0.register(r3, r5, r7)
            java.lang.Class<android.graphics.Bitmap> r3 = android.graphics.Bitmap.class
            r5 = r16
            com.bumptech.glide.Registry r0 = r0.register(r3, r6, r5)
            java.lang.Class<android.graphics.drawable.Drawable> r3 = android.graphics.drawable.Drawable.class
            com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder r7 = new com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder
            r8 = r23
            r7.<init>(r2, r5, r8)
            com.bumptech.glide.Registry r0 = r0.register(r3, r6, r7)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r3 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r0.register(r3, r6, r8)
            com.bumptech.glide.load.ResourceDecoder r0 = com.bumptech.glide.load.resource.bitmap.VideoDecoder.byteBuffer(r21)
            java.lang.Class<java.nio.ByteBuffer> r2 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.Bitmap> r3 = android.graphics.Bitmap.class
            r1.append(r2, r3, r0)
            java.lang.Class<java.nio.ByteBuffer> r2 = java.nio.ByteBuffer.class
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r3 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder r5 = new com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder
            r5.<init>((android.content.res.Resources) r4, r0)
            r1.append(r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RegistryFactory.initializeDefaults(android.content.Context, com.bumptech.glide.Registry, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool, com.bumptech.glide.GlideExperiments):void");
    }

    private static void initializeModules(Context context, Glide glide, Registry registry, List<GlideModule> list, AppGlideModule appGlideModule) {
        for (GlideModule next : list) {
            try {
                next.registerComponents(context, glide, registry);
            } catch (AbstractMethodError e) {
                throw new IllegalStateException("Attempting to register a Glide v3 module. If you see this, you or one of your dependencies may be including Glide v3 even though you're using Glide v4. You'll need to find and remove (or update) the offending dependency. The v3 module name is: " + next.getClass().getName(), e);
            }
        }
        if (appGlideModule != null) {
            appGlideModule.registerComponents(context, glide, registry);
        }
    }
}

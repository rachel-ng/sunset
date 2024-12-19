package com.bumptech.glide.load.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.signature.ObjectKey;
import java.io.IOException;
import java.io.InputStream;

public final class DirectResourceLoader<DataT> implements ModelLoader<Integer, DataT> {
    private final Context context;
    private final ResourceOpener<DataT> resourceOpener;

    private interface ResourceOpener<DataT> {
        void close(DataT datat) throws IOException;

        Class<DataT> getDataClass();

        DataT open(Resources.Theme theme, Resources resources, int i);
    }

    public boolean handles(Integer num) {
        return true;
    }

    public static ModelLoaderFactory<Integer, InputStream> inputStreamFactory(Context context2) {
        return new InputStreamFactory(context2);
    }

    public static ModelLoaderFactory<Integer, AssetFileDescriptor> assetFileDescriptorFactory(Context context2) {
        return new AssetFileDescriptorFactory(context2);
    }

    public static ModelLoaderFactory<Integer, Drawable> drawableFactory(Context context2) {
        return new DrawableFactory(context2);
    }

    DirectResourceLoader(Context context2, ResourceOpener<DataT> resourceOpener2) {
        this.context = context2.getApplicationContext();
        this.resourceOpener = resourceOpener2;
    }

    public ModelLoader.LoadData<DataT> buildLoadData(Integer num, int i, int i2, Options options) {
        Resources resources;
        Resources.Theme theme = (Resources.Theme) options.get(ResourceDrawableDecoder.THEME);
        if (theme != null) {
            resources = theme.getResources();
        } else {
            resources = this.context.getResources();
        }
        return new ModelLoader.LoadData<>(new ObjectKey(num), new ResourceDataFetcher(theme, resources, this.resourceOpener, num.intValue()));
    }

    private static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Integer, AssetFileDescriptor>, ResourceOpener<AssetFileDescriptor> {
        private final Context context;

        public void teardown() {
        }

        AssetFileDescriptorFactory(Context context2) {
            this.context = context2;
        }

        public AssetFileDescriptor open(Resources.Theme theme, Resources resources, int i) {
            return resources.openRawResourceFd(i);
        }

        public void close(AssetFileDescriptor assetFileDescriptor) throws IOException {
            assetFileDescriptor.close();
        }

        public Class<AssetFileDescriptor> getDataClass() {
            return AssetFileDescriptor.class;
        }

        public ModelLoader<Integer, AssetFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DirectResourceLoader(this.context, this);
        }
    }

    private static final class InputStreamFactory implements ModelLoaderFactory<Integer, InputStream>, ResourceOpener<InputStream> {
        private final Context context;

        public void teardown() {
        }

        InputStreamFactory(Context context2) {
            this.context = context2;
        }

        public ModelLoader<Integer, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DirectResourceLoader(this.context, this);
        }

        public InputStream open(Resources.Theme theme, Resources resources, int i) {
            return resources.openRawResource(i);
        }

        public void close(InputStream inputStream) throws IOException {
            inputStream.close();
        }

        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }
    }

    private static final class DrawableFactory implements ModelLoaderFactory<Integer, Drawable>, ResourceOpener<Drawable> {
        private final Context context;

        public void close(Drawable drawable) throws IOException {
        }

        public void teardown() {
        }

        DrawableFactory(Context context2) {
            this.context = context2;
        }

        public Drawable open(Resources.Theme theme, Resources resources, int i) {
            return DrawableDecoderCompat.getDrawable(this.context, i, theme);
        }

        public Class<Drawable> getDataClass() {
            return Drawable.class;
        }

        public ModelLoader<Integer, Drawable> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DirectResourceLoader(this.context, this);
        }
    }

    private static final class ResourceDataFetcher<DataT> implements DataFetcher<DataT> {
        private DataT data;
        private final int resourceId;
        private final ResourceOpener<DataT> resourceOpener;
        private final Resources resources;
        private final Resources.Theme theme;

        public void cancel() {
        }

        ResourceDataFetcher(Resources.Theme theme2, Resources resources2, ResourceOpener<DataT> resourceOpener2, int i) {
            this.theme = theme2;
            this.resources = resources2;
            this.resourceOpener = resourceOpener2;
            this.resourceId = i;
        }

        public void loadData(Priority priority, DataFetcher.DataCallback<? super DataT> dataCallback) {
            try {
                DataT open = this.resourceOpener.open(this.theme, this.resources, this.resourceId);
                this.data = open;
                dataCallback.onDataReady(open);
            } catch (Resources.NotFoundException e) {
                dataCallback.onLoadFailed(e);
            }
        }

        public void cleanup() {
            DataT datat = this.data;
            if (datat != null) {
                try {
                    this.resourceOpener.close(datat);
                } catch (IOException unused) {
                }
            }
        }

        public Class<DataT> getDataClass() {
            return this.resourceOpener.getDataClass();
        }

        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }
}

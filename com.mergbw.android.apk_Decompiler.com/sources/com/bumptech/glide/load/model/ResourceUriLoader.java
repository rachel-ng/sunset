package com.bumptech.glide.load.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.List;

public final class ResourceUriLoader<DataT> implements ModelLoader<Uri, DataT> {
    private static final int INVALID_RESOURCE_ID = 0;
    private static final String TAG = "ResourceUriLoader";
    private final Context context;
    private final ModelLoader<Integer, DataT> delegate;

    public static ModelLoaderFactory<Uri, InputStream> newStreamFactory(Context context2) {
        return new InputStreamFactory(context2);
    }

    public static ModelLoaderFactory<Uri, AssetFileDescriptor> newAssetFileDescriptorFactory(Context context2) {
        return new AssetFileDescriptorFactory(context2);
    }

    ResourceUriLoader(Context context2, ModelLoader<Integer, DataT> modelLoader) {
        this.context = context2.getApplicationContext();
        this.delegate = modelLoader;
    }

    public ModelLoader.LoadData<DataT> buildLoadData(Uri uri, int i, int i2, Options options) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 1) {
            return parseResourceIdUri(uri, i, i2, options);
        }
        if (pathSegments.size() == 2) {
            return parseResourceNameUri(uri, i, i2, options);
        }
        if (!Log.isLoggable(TAG, 5)) {
            return null;
        }
        Log.w(TAG, "Failed to parse resource uri: " + uri);
        return null;
    }

    private ModelLoader.LoadData<DataT> parseResourceNameUri(Uri uri, int i, int i2, Options options) {
        List<String> pathSegments = uri.getPathSegments();
        String str = pathSegments.get(1);
        int identifier = this.context.getResources().getIdentifier(str, pathSegments.get(0), this.context.getPackageName());
        if (identifier != 0) {
            return this.delegate.buildLoadData(Integer.valueOf(identifier), i, i2, options);
        }
        if (!Log.isLoggable(TAG, 5)) {
            return null;
        }
        Log.w(TAG, "Failed to find resource id for: " + uri);
        return null;
    }

    private ModelLoader.LoadData<DataT> parseResourceIdUri(Uri uri, int i, int i2, Options options) {
        try {
            int parseInt = Integer.parseInt(uri.getPathSegments().get(0));
            if (parseInt != 0) {
                return this.delegate.buildLoadData(Integer.valueOf(parseInt), i, i2, options);
            }
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Failed to parse a valid non-0 resource id from: " + uri);
            }
            return null;
        } catch (NumberFormatException e) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Failed to parse resource id from: " + uri, e);
            }
            return null;
        }
    }

    public boolean handles(Uri uri) {
        return "android.resource".equals(uri.getScheme()) && this.context.getPackageName().equals(uri.getAuthority());
    }

    private static final class InputStreamFactory implements ModelLoaderFactory<Uri, InputStream> {
        private final Context context;

        public void teardown() {
        }

        InputStreamFactory(Context context2) {
            this.context = context2;
        }

        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceUriLoader(this.context, multiModelLoaderFactory.build(Integer.class, InputStream.class));
        }
    }

    private static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Uri, AssetFileDescriptor> {
        private final Context context;

        public void teardown() {
        }

        AssetFileDescriptorFactory(Context context2) {
            this.context = context2;
        }

        public ModelLoader<Uri, AssetFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceUriLoader(this.context, multiModelLoaderFactory.build(Integer.class, AssetFileDescriptor.class));
        }
    }
}

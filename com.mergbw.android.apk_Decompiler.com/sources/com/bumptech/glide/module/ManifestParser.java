package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class ManifestParser {
    private static final String GLIDE_MODULE_VALUE = "GlideModule";
    private static final String TAG = "ManifestParser";
    private final Context context;

    public ManifestParser(Context context2) {
        this.context = context2;
    }

    private ApplicationInfo getOurApplicationInfo() throws PackageManager.NameNotFoundException {
        return this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
    }

    public List<GlideModule> parse() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo ourApplicationInfo = getOurApplicationInfo();
            if (ourApplicationInfo != null) {
                if (ourApplicationInfo.metaData != null) {
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Got app info metadata: " + ourApplicationInfo.metaData);
                    }
                    for (String str : ourApplicationInfo.metaData.keySet()) {
                        if (GLIDE_MODULE_VALUE.equals(ourApplicationInfo.metaData.get(str))) {
                            arrayList.add(parseModule(str));
                            if (Log.isLoggable(TAG, 3)) {
                                Log.d(TAG, "Loaded Glide module: " + str);
                            }
                        }
                    }
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Finished loading Glide modules");
                    }
                    return arrayList;
                }
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Got null app info metadata");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Failed to parse glide modules", e);
            }
        }
    }

    private static GlideModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            Object obj = null;
            try {
                obj = cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (InstantiationException e) {
                throwInstantiateGlideModuleException(cls, e);
            } catch (IllegalAccessException e2) {
                throwInstantiateGlideModuleException(cls, e2);
            } catch (NoSuchMethodException e3) {
                throwInstantiateGlideModuleException(cls, e3);
            } catch (InvocationTargetException e4) {
                throwInstantiateGlideModuleException(cls, e4);
            }
            if (obj instanceof GlideModule) {
                return (GlideModule) obj;
            }
            throw new RuntimeException("Expected instanceof GlideModule, but found: " + obj);
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void throwInstantiateGlideModuleException(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }
}

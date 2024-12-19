package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;

public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new CacheKeyFactory$$ExternalSyntheticLambda0();

    String buildCacheKey(DataSpec dataSpec);

    /* renamed from: com.google.android.exoplayer2.upstream.cache.CacheKeyFactory$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            CacheKeyFactory cacheKeyFactory = CacheKeyFactory.DEFAULT;
        }

        public static /* synthetic */ String lambda$static$0(DataSpec dataSpec) {
            return dataSpec.key != null ? dataSpec.key : dataSpec.uri.toString();
        }
    }
}

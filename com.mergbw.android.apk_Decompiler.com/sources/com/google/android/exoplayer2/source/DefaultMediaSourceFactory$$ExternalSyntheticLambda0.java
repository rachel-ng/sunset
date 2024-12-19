package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultMediaSourceFactory$$ExternalSyntheticLambda0 implements ExtractorsFactory {
    public final /* synthetic */ Format f$0;

    public /* synthetic */ DefaultMediaSourceFactory$$ExternalSyntheticLambda0(Format format) {
        this.f$0 = format;
    }

    public final Extractor[] createExtractors() {
        return DefaultMediaSourceFactory.lambda$createMediaSource$0(this.f$0);
    }

    public /* synthetic */ Extractor[] createExtractors(Uri uri, Map map) {
        return ExtractorsFactory.CC.$default$createExtractors(this, uri, map);
    }
}

package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import java.io.IOException;
import java.util.Map;

public final class PlaceholderDataSource implements DataSource {
    public static final DataSource.Factory FACTORY = new PlaceholderDataSource$$ExternalSyntheticLambda0();
    public static final PlaceholderDataSource INSTANCE = new PlaceholderDataSource();

    public static /* synthetic */ PlaceholderDataSource $r8$lambda$vDuk6rTp1JuQSAVZdUUcM4qsx4k() {
        return new PlaceholderDataSource();
    }

    public void addTransferListener(TransferListener transferListener) {
    }

    public void close() {
    }

    public /* synthetic */ Map getResponseHeaders() {
        return DataSource.CC.$default$getResponseHeaders(this);
    }

    public Uri getUri() {
        return null;
    }

    private PlaceholderDataSource() {
    }

    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("PlaceholderDataSource cannot be opened");
    }

    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException();
    }
}

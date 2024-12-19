package com.google.android.exoplayer2.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class MetadataRenderer extends BaseRenderer implements Handler.Callback {
    private static final int MSG_INVOKE_RENDERER = 0;
    private static final String TAG = "MetadataRenderer";
    private final MetadataInputBuffer buffer;
    private MetadataDecoder decoder;
    private final MetadataDecoderFactory decoderFactory;
    private boolean inputStreamEnded;
    private final MetadataOutput output;
    private final Handler outputHandler;
    private final boolean outputMetadataEarly;
    private boolean outputStreamEnded;
    private long outputStreamOffsetUs;
    private Metadata pendingMetadata;
    private long subsampleOffsetUs;

    public boolean isReady() {
        return true;
    }

    public MetadataRenderer(MetadataOutput metadataOutput, Looper looper) {
        this(metadataOutput, looper, MetadataDecoderFactory.DEFAULT);
    }

    public MetadataRenderer(MetadataOutput metadataOutput, Looper looper, MetadataDecoderFactory metadataDecoderFactory) {
        this(metadataOutput, looper, metadataDecoderFactory, false);
    }

    public MetadataRenderer(MetadataOutput metadataOutput, Looper looper, MetadataDecoderFactory metadataDecoderFactory, boolean z) {
        super(5);
        Handler handler;
        this.output = (MetadataOutput) Assertions.checkNotNull(metadataOutput);
        if (looper == null) {
            handler = null;
        } else {
            handler = Util.createHandler(looper, this);
        }
        this.outputHandler = handler;
        this.decoderFactory = (MetadataDecoderFactory) Assertions.checkNotNull(metadataDecoderFactory);
        this.outputMetadataEarly = z;
        this.buffer = new MetadataInputBuffer();
        this.outputStreamOffsetUs = C.TIME_UNSET;
    }

    public String getName() {
        return TAG;
    }

    public int supportsFormat(Format format) {
        if (!this.decoderFactory.supportsFormat(format)) {
            return RendererCapabilities.CC.create(0);
        }
        return RendererCapabilities.CC.create(format.cryptoType == 0 ? 4 : 2);
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j, long j2) {
        this.decoder = this.decoderFactory.createDecoder(formatArr[0]);
        Metadata metadata = this.pendingMetadata;
        if (metadata != null) {
            this.pendingMetadata = metadata.copyWithPresentationTimeUs((metadata.presentationTimeUs + this.outputStreamOffsetUs) - j2);
        }
        this.outputStreamOffsetUs = j2;
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) {
        this.pendingMetadata = null;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
    }

    public void render(long j, long j2) {
        boolean z = true;
        while (z) {
            readMetadata();
            z = outputMetadata(j);
        }
    }

    private void decodeWrappedMetadata(Metadata metadata, List<Metadata.Entry> list) {
        for (int i = 0; i < metadata.length(); i++) {
            Format wrappedMetadataFormat = metadata.get(i).getWrappedMetadataFormat();
            if (wrappedMetadataFormat == null || !this.decoderFactory.supportsFormat(wrappedMetadataFormat)) {
                list.add(metadata.get(i));
            } else {
                MetadataDecoder createDecoder = this.decoderFactory.createDecoder(wrappedMetadataFormat);
                byte[] bArr = (byte[]) Assertions.checkNotNull(metadata.get(i).getWrappedMetadataBytes());
                this.buffer.clear();
                this.buffer.ensureSpaceForWrite(bArr.length);
                ((ByteBuffer) Util.castNonNull(this.buffer.data)).put(bArr);
                this.buffer.flip();
                Metadata decode = createDecoder.decode(this.buffer);
                if (decode != null) {
                    decodeWrappedMetadata(decode, list);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.pendingMetadata = null;
        this.decoder = null;
        this.outputStreamOffsetUs = C.TIME_UNSET;
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            invokeRendererInternal((Metadata) message.obj);
            return true;
        }
        throw new IllegalStateException();
    }

    private void readMetadata() {
        if (!this.inputStreamEnded && this.pendingMetadata == null) {
            this.buffer.clear();
            FormatHolder formatHolder = getFormatHolder();
            int readSource = readSource(formatHolder, this.buffer, 0);
            if (readSource == -4) {
                if (this.buffer.isEndOfStream()) {
                    this.inputStreamEnded = true;
                    return;
                }
                this.buffer.subsampleOffsetUs = this.subsampleOffsetUs;
                this.buffer.flip();
                Metadata decode = ((MetadataDecoder) Util.castNonNull(this.decoder)).decode(this.buffer);
                if (decode != null) {
                    ArrayList arrayList = new ArrayList(decode.length());
                    decodeWrappedMetadata(decode, arrayList);
                    if (!arrayList.isEmpty()) {
                        this.pendingMetadata = new Metadata(getPresentationTimeUs(this.buffer.timeUs), (List<? extends Metadata.Entry>) arrayList);
                    }
                }
            } else if (readSource == -5) {
                this.subsampleOffsetUs = ((Format) Assertions.checkNotNull(formatHolder.format)).subsampleOffsetUs;
            }
        }
    }

    private boolean outputMetadata(long j) {
        boolean z;
        Metadata metadata = this.pendingMetadata;
        if (metadata == null || (!this.outputMetadataEarly && metadata.presentationTimeUs > getPresentationTimeUs(j))) {
            z = false;
        } else {
            invokeRenderer(this.pendingMetadata);
            this.pendingMetadata = null;
            z = true;
        }
        if (this.inputStreamEnded && this.pendingMetadata == null) {
            this.outputStreamEnded = true;
        }
        return z;
    }

    private void invokeRenderer(Metadata metadata) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(0, metadata).sendToTarget();
        } else {
            invokeRendererInternal(metadata);
        }
    }

    private void invokeRendererInternal(Metadata metadata) {
        this.output.onMetadata(metadata);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long j) {
        boolean z = true;
        Assertions.checkState(j != C.TIME_UNSET);
        if (this.outputStreamOffsetUs == C.TIME_UNSET) {
            z = false;
        }
        Assertions.checkState(z);
        return j - this.outputStreamOffsetUs;
    }
}

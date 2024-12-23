package com.google.android.exoplayer2.source.hls;

import com.alibaba.android.arouter.utils.Consts;
import java.io.IOException;

public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(String str) {
        super("Unable to bind a sample queue to TrackGroup with mime type " + str + Consts.DOT);
    }
}

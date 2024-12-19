package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C;

public final class FrameProcessingException extends Exception {
    public final long presentationTimeUs;

    public static FrameProcessingException from(Exception exc) {
        return from(exc, C.TIME_UNSET);
    }

    public static FrameProcessingException from(Exception exc, long j) {
        if (exc instanceof FrameProcessingException) {
            return (FrameProcessingException) exc;
        }
        return new FrameProcessingException((Throwable) exc, j);
    }

    public FrameProcessingException(String str) {
        this(str, (long) C.TIME_UNSET);
    }

    public FrameProcessingException(String str, long j) {
        super(str);
        this.presentationTimeUs = j;
    }

    public FrameProcessingException(String str, Throwable th) {
        this(str, th, C.TIME_UNSET);
    }

    public FrameProcessingException(String str, Throwable th, long j) {
        super(str, th);
        this.presentationTimeUs = j;
    }

    public FrameProcessingException(Throwable th) {
        this(th, (long) C.TIME_UNSET);
    }

    public FrameProcessingException(Throwable th, long j) {
        super(th);
        this.presentationTimeUs = j;
    }
}

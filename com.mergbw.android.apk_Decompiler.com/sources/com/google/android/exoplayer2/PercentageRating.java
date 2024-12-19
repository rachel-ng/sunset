package com.google.android.exoplayer2;

import android.os.Bundle;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Objects;

public final class PercentageRating extends Rating {
    public static final Bundleable.Creator<PercentageRating> CREATOR = new PercentageRating$$ExternalSyntheticLambda0();
    private static final String FIELD_PERCENT = Util.intToStringMaxRadix(1);
    private static final int TYPE = 1;
    private final float percent;

    public PercentageRating() {
        this.percent = -1.0f;
    }

    public PercentageRating(float f) {
        Assertions.checkArgument(f >= 0.0f && f <= 100.0f, "percent must be in the range of [0, 100]");
        this.percent = f;
    }

    public boolean isRated() {
        return this.percent != -1.0f;
    }

    public float getPercent() {
        return this.percent;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.percent));
    }

    public boolean equals(Object obj) {
        if ((obj instanceof PercentageRating) && this.percent == ((PercentageRating) obj).percent) {
            return true;
        }
        return false;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_RATING_TYPE, 1);
        bundle.putFloat(FIELD_PERCENT, this.percent);
        return bundle;
    }

    /* access modifiers changed from: private */
    public static PercentageRating fromBundle(Bundle bundle) {
        boolean z = true;
        if (bundle.getInt(FIELD_RATING_TYPE, -1) != 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        float f = bundle.getFloat(FIELD_PERCENT, -1.0f);
        return f == -1.0f ? new PercentageRating() : new PercentageRating(f);
    }
}

package com.google.android.material.carousel;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = {0, 1};
    private static final int[] SMALL_COUNTS = {1};

    /* access modifiers changed from: package-private */
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredWidth = (float) (view.getMeasuredWidth() * 2);
        if (carousel.isHorizontal()) {
            f = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredWidth = (float) (view.getMeasuredHeight() * 2);
        }
        float smallSizeMin = CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f;
        float smallSizeMax = CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f;
        float f2 = (float) containerHeight;
        float min = Math.min(measuredWidth + f, f2);
        float clamp = MathUtils.clamp((measuredWidth / 3.0f) + f, CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f, CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f);
        float f3 = (min + clamp) / 2.0f;
        int max = (int) Math.max(1.0d, Math.floor((double) ((f2 - (((float) CarouselStrategyHelper.maxValue(SMALL_COUNTS)) * smallSizeMax)) / min)));
        int ceil = (((int) Math.ceil((double) (f2 / min))) - max) + 1;
        int[] iArr = new int[ceil];
        for (int i = 0; i < ceil; i++) {
            iArr[i] = max + i;
        }
        return CarouselStrategyHelper.createLeftAlignedKeylineState(view.getContext(), f, f2, Arrangement.findLowestCostArrangement(f2, clamp, smallSizeMin, smallSizeMax, SMALL_COUNTS, f3, MEDIUM_COUNTS, min, iArr));
    }
}

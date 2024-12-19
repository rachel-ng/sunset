package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CarouselLayoutManager extends RecyclerView.LayoutManager implements Carousel, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int HORIZONTAL = 0;
    private static final String TAG = "CarouselLayoutManager";
    public static final int VERTICAL = 1;
    private CarouselStrategy carouselStrategy;
    private int currentFillStartPosition;
    private KeylineState currentKeylineState;
    private final DebugItemDecoration debugItemDecoration;
    private boolean isDebuggingEnabled;
    /* access modifiers changed from: private */
    public KeylineStateList keylineStateList;
    private Map<Integer, KeylineState> keylineStatePositionMap;
    int maxScroll;
    int minScroll;
    private CarouselOrientationHelper orientationHelper;
    int scrollOffset;

    private static int calculateShouldScrollBy(int i, int i2, int i3, int i4) {
        int i5 = i2 + i;
        return i5 < i3 ? i3 - i2 : i5 > i4 ? i4 - i2 : i;
    }

    private static final class ChildCalculations {
        final float center;
        final View child;
        final float offsetCenter;
        final KeylineRange range;

        ChildCalculations(View view, float f, float f2, KeylineRange keylineRange) {
            this.child = view;
            this.center = f;
            this.offsetCenter = f2;
            this.range = keylineRange;
        }
    }

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy2) {
        this(carouselStrategy2, 0);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy2, int i) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        setCarouselStrategy(carouselStrategy2);
        setOrientation(i);
    }

    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        setOrientation(getProperties(context, attributeSet, i, i2).orientation);
        setCarouselStrategy(new MultiBrowseCarouselStrategy());
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public void setCarouselStrategy(CarouselStrategy carouselStrategy2) {
        this.carouselStrategy = carouselStrategy2;
        refreshKeylineState();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() <= 0) {
            removeAndRecycleAllViews(recycler);
            this.currentFillStartPosition = 0;
            return;
        }
        boolean isLayoutRtl = isLayoutRtl();
        boolean z = this.keylineStateList == null;
        if (z) {
            View viewForPosition = recycler.getViewForPosition(0);
            measureChildWithMargins(viewForPosition, 0, 0);
            KeylineState onFirstChildMeasuredWithMargins = this.carouselStrategy.onFirstChildMeasuredWithMargins(this, viewForPosition);
            if (isLayoutRtl) {
                onFirstChildMeasuredWithMargins = KeylineState.reverse(onFirstChildMeasuredWithMargins);
            }
            this.keylineStateList = KeylineStateList.from(this, onFirstChildMeasuredWithMargins);
        }
        int calculateStartScroll = calculateStartScroll(this.keylineStateList);
        int calculateEndScroll = calculateEndScroll(state, this.keylineStateList);
        int i = isLayoutRtl ? calculateEndScroll : calculateStartScroll;
        this.minScroll = i;
        if (isLayoutRtl) {
            calculateEndScroll = calculateStartScroll;
        }
        this.maxScroll = calculateEndScroll;
        if (z) {
            this.scrollOffset = calculateStartScroll;
            this.keylineStatePositionMap = this.keylineStateList.getKeylineStateForPositionMap(getItemCount(), this.minScroll, this.maxScroll, isLayoutRtl());
        } else {
            int i2 = this.scrollOffset;
            this.scrollOffset = i2 + calculateShouldScrollBy(0, i2, i, calculateEndScroll);
        }
        this.currentFillStartPosition = MathUtils.clamp(this.currentFillStartPosition, 0, state.getItemCount());
        updateCurrentKeylineStateForScrollOffset();
        detachAndScrapAttachedViews(recycler);
        fill(recycler, state);
    }

    private void refreshKeylineState() {
        this.keylineStateList = null;
        requestLayout();
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAndRecycleOutOfBoundsViews(recycler);
        if (getChildCount() == 0) {
            addViewsStart(recycler, this.currentFillStartPosition - 1);
            addViewsEnd(recycler, state, this.currentFillStartPosition);
        } else {
            int position = getPosition(getChildAt(0));
            int position2 = getPosition(getChildAt(getChildCount() - 1));
            addViewsStart(recycler, position - 1);
            addViewsEnd(recycler, state, position2 + 1);
        }
        validateChildOrderIfDebugging();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (getChildCount() == 0) {
            this.currentFillStartPosition = 0;
        } else {
            this.currentFillStartPosition = getPosition(getChildAt(0));
        }
        validateChildOrderIfDebugging();
    }

    private void addViewsStart(RecyclerView.Recycler recycler, int i) {
        int calculateChildStartForFill = calculateChildStartForFill(i);
        while (i >= 0) {
            ChildCalculations makeChildCalculations = makeChildCalculations(recycler, (float) calculateChildStartForFill, i);
            if (!isLocOffsetOutOfFillBoundsStart(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                calculateChildStartForFill = addStart(calculateChildStartForFill, (int) this.currentKeylineState.getItemSize());
                if (!isLocOffsetOutOfFillBoundsEnd(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                    addAndLayoutView(makeChildCalculations.child, 0, makeChildCalculations);
                }
                i--;
            } else {
                return;
            }
        }
    }

    private void addViewsEnd(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        int calculateChildStartForFill = calculateChildStartForFill(i);
        while (i < state.getItemCount()) {
            ChildCalculations makeChildCalculations = makeChildCalculations(recycler, (float) calculateChildStartForFill, i);
            if (!isLocOffsetOutOfFillBoundsEnd(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                calculateChildStartForFill = addEnd(calculateChildStartForFill, (int) this.currentKeylineState.getItemSize());
                if (!isLocOffsetOutOfFillBoundsStart(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                    addAndLayoutView(makeChildCalculations.child, -1, makeChildCalculations);
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void logChildrenIfDebugging() {
        if (this.isDebuggingEnabled && Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "internal representation of views on the screen");
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                float decoratedCenterXWithMargins = getDecoratedCenterXWithMargins(childAt);
                Log.d(TAG, "item position " + getPosition(childAt) + ", center:" + decoratedCenterXWithMargins + ", child index:" + i);
            }
            Log.d(TAG, "==============");
        }
    }

    private void validateChildOrderIfDebugging() {
        if (this.isDebuggingEnabled && getChildCount() >= 1) {
            int i = 0;
            while (i < getChildCount() - 1) {
                int position = getPosition(getChildAt(i));
                int i2 = i + 1;
                int position2 = getPosition(getChildAt(i2));
                if (position <= position2) {
                    i = i2;
                } else {
                    logChildrenIfDebugging();
                    throw new IllegalStateException("Detected invalid child order. Child at index [" + i + "] had adapter position [" + position + "] and child at index [" + i2 + "] had adapter position [" + position2 + "].");
                }
            }
        }
    }

    private ChildCalculations makeChildCalculations(RecyclerView.Recycler recycler, float f, int i) {
        View viewForPosition = recycler.getViewForPosition(i);
        measureChildWithMargins(viewForPosition, 0, 0);
        float addEnd = (float) addEnd((int) f, (int) (this.currentKeylineState.getItemSize() / 2.0f));
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), addEnd, false);
        return new ChildCalculations(viewForPosition, addEnd, calculateChildOffsetCenterForLocation(viewForPosition, addEnd, surroundingKeylineRange), surroundingKeylineRange);
    }

    private void addAndLayoutView(View view, int i, ChildCalculations childCalculations) {
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        addView(view, i);
        int i2 = (int) (childCalculations.offsetCenter + itemSize);
        this.orientationHelper.layoutDecoratedWithMargins(view, (int) (childCalculations.offsetCenter - itemSize), i2);
        updateChildMaskForLocation(view, childCalculations.center, childCalculations.range);
    }

    private boolean isLocOffsetOutOfFillBoundsStart(float f, KeylineRange keylineRange) {
        int addEnd = addEnd((int) f, (int) (getMaskedItemSizeForLocOffset(f, keylineRange) / 2.0f));
        if (isLayoutRtl()) {
            if (addEnd > getContainerSize()) {
                return true;
            }
        } else if (addEnd < 0) {
            return true;
        }
        return false;
    }

    public boolean isHorizontal() {
        return this.orientationHelper.orientation == 0;
    }

    private boolean isLocOffsetOutOfFillBoundsEnd(float f, KeylineRange keylineRange) {
        int addStart = addStart((int) f, (int) (getMaskedItemSizeForLocOffset(f, keylineRange) / 2.0f));
        if (isLayoutRtl()) {
            if (addStart < 0) {
                return true;
            }
        } else if (addStart > getContainerSize()) {
            return true;
        }
        return false;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        float centerX = (float) rect.centerX();
        float width = (((float) rect.width()) - getMaskedItemSizeForLocOffset(centerX, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), centerX, true))) / 2.0f;
        rect.set((int) (((float) rect.left) + width), rect.top, (int) (((float) rect.right) - width), rect.bottom);
    }

    private float getDecoratedCenterXWithMargins(View view) {
        Rect rect = new Rect();
        super.getDecoratedBoundsWithMargins(view, rect);
        return (float) rect.centerX();
    }

    private void removeAndRecycleOutOfBoundsViews(RecyclerView.Recycler recycler) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            float decoratedCenterXWithMargins = getDecoratedCenterXWithMargins(childAt);
            if (!isLocOffsetOutOfFillBoundsStart(decoratedCenterXWithMargins, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterXWithMargins, true))) {
                break;
            }
            removeAndRecycleView(childAt, recycler);
        }
        while (getChildCount() - 1 >= 0) {
            View childAt2 = getChildAt(getChildCount() - 1);
            float decoratedCenterXWithMargins2 = getDecoratedCenterXWithMargins(childAt2);
            if (isLocOffsetOutOfFillBoundsEnd(decoratedCenterXWithMargins2, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterXWithMargins2, true))) {
                removeAndRecycleView(childAt2, recycler);
            } else {
                return;
            }
        }
    }

    private static KeylineRange getSurroundingKeylineRange(List<KeylineState.Keyline> list, float f, boolean z) {
        float f2 = Float.MAX_VALUE;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        float f3 = -3.4028235E38f;
        float f4 = Float.MAX_VALUE;
        float f5 = Float.MAX_VALUE;
        for (int i5 = 0; i5 < list.size(); i5++) {
            KeylineState.Keyline keyline = list.get(i5);
            float f6 = z ? keyline.locOffset : keyline.loc;
            float abs = Math.abs(f6 - f);
            if (f6 <= f && abs <= f2) {
                i = i5;
                f2 = abs;
            }
            if (f6 > f && abs <= f4) {
                i3 = i5;
                f4 = abs;
            }
            if (f6 <= f5) {
                i2 = i5;
                f5 = f6;
            }
            if (f6 > f3) {
                i4 = i5;
                f3 = f6;
            }
        }
        if (i == -1) {
            i = i2;
        }
        if (i3 == -1) {
            i3 = i4;
        }
        return new KeylineRange(list.get(i), list.get(i3));
    }

    private void updateCurrentKeylineStateForScrollOffset() {
        int i = this.maxScroll;
        int i2 = this.minScroll;
        if (i <= i2) {
            this.currentKeylineState = isLayoutRtl() ? this.keylineStateList.getEndState() : this.keylineStateList.getStartState();
        } else {
            this.currentKeylineState = this.keylineStateList.getShiftedState((float) this.scrollOffset, (float) i2, (float) i);
        }
        this.debugItemDecoration.setKeylines(this.currentKeylineState.getKeylines());
    }

    private int calculateStartScroll(KeylineStateList keylineStateList2) {
        boolean isLayoutRtl = isLayoutRtl();
        KeylineState endState = isLayoutRtl ? keylineStateList2.getEndState() : keylineStateList2.getStartState();
        return (int) ((((float) (getPaddingStart() * (isLayoutRtl ? 1 : -1))) + ((float) getParentStart())) - ((float) addStart((int) (isLayoutRtl ? endState.getLastFocalKeyline() : endState.getFirstFocalKeyline()).loc, (int) (endState.getItemSize() / 2.0f))));
    }

    private int calculateEndScroll(RecyclerView.State state, KeylineStateList keylineStateList2) {
        boolean isLayoutRtl = isLayoutRtl();
        KeylineState startState = isLayoutRtl ? keylineStateList2.getStartState() : keylineStateList2.getEndState();
        KeylineState.Keyline firstFocalKeyline = isLayoutRtl ? startState.getFirstFocalKeyline() : startState.getLastFocalKeyline();
        float itemCount = ((((float) (state.getItemCount() - 1)) * startState.getItemSize()) + ((float) getPaddingEnd())) * (isLayoutRtl ? -1.0f : 1.0f);
        float parentStart = firstFocalKeyline.loc - ((float) getParentStart());
        float parentEnd = ((float) getParentEnd()) - firstFocalKeyline.loc;
        if (Math.abs(parentStart) > Math.abs(itemCount)) {
            return 0;
        }
        return (int) ((itemCount - parentStart) + parentEnd);
    }

    private int calculateChildStartForFill(int i) {
        return addEnd((int) ((float) (getParentStart() - this.scrollOffset)), (int) (this.currentKeylineState.getItemSize() * ((float) i)));
    }

    private float calculateChildOffsetCenterForLocation(View view, float f, KeylineRange keylineRange) {
        float lerp = AnimationUtils.lerp(keylineRange.leftOrTop.locOffset, keylineRange.rightOrBottom.locOffset, keylineRange.leftOrTop.loc, keylineRange.rightOrBottom.loc, f);
        if (keylineRange.rightOrBottom != this.currentKeylineState.getFirstKeyline() && keylineRange.leftOrTop != this.currentKeylineState.getLastKeyline()) {
            return lerp;
        }
        return lerp + ((f - keylineRange.rightOrBottom.loc) * ((1.0f - keylineRange.rightOrBottom.mask) + (this.orientationHelper.getMaskMargins((RecyclerView.LayoutParams) view.getLayoutParams()) / this.currentKeylineState.getItemSize())));
    }

    private float getMaskedItemSizeForLocOffset(float f, KeylineRange keylineRange) {
        return AnimationUtils.lerp(keylineRange.leftOrTop.maskedItemSize, keylineRange.rightOrBottom.maskedItemSize, keylineRange.leftOrTop.locOffset, keylineRange.rightOrBottom.locOffset, f);
    }

    private void updateChildMaskForLocation(View view, float f, KeylineRange keylineRange) {
        if (view instanceof Maskable) {
            float lerp = AnimationUtils.lerp(keylineRange.leftOrTop.mask, keylineRange.rightOrBottom.mask, keylineRange.leftOrTop.loc, keylineRange.rightOrBottom.loc, f);
            float height = (float) view.getHeight();
            float width = (float) view.getWidth();
            float lerp2 = AnimationUtils.lerp(0.0f, width / 2.0f, 0.0f, 1.0f, lerp);
            RectF maskRect = this.orientationHelper.getMaskRect(height, width, AnimationUtils.lerp(0.0f, height / 2.0f, 0.0f, 1.0f, lerp), lerp2);
            float calculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, f, keylineRange);
            float height2 = (maskRect.height() / 2.0f) + calculateChildOffsetCenterForLocation;
            RectF rectF = new RectF(calculateChildOffsetCenterForLocation - (maskRect.width() / 2.0f), calculateChildOffsetCenterForLocation - (maskRect.height() / 2.0f), calculateChildOffsetCenterForLocation + (maskRect.width() / 2.0f), height2);
            RectF rectF2 = new RectF((float) getParentLeft(), (float) getParentTop(), (float) getParentRight(), (float) getParentBottom());
            if (this.carouselStrategy.isContained()) {
                this.orientationHelper.containMaskWithinBounds(maskRect, rectF, rectF2);
            }
            this.orientationHelper.moveMaskOnEdgeOutsideBounds(maskRect, rectF, rectF2);
            ((Maskable) view).setMaskRectF(maskRect);
        }
    }

    public void measureChildWithMargins(View view, int i, int i2) {
        float f;
        float f2;
        if (view instanceof Maskable) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            Rect rect = new Rect();
            calculateItemDecorationsForChild(view, rect);
            int i3 = i + rect.left + rect.right;
            int i4 = i2 + rect.top + rect.bottom;
            if (this.keylineStateList == null || this.orientationHelper.orientation != 0) {
                f = (float) layoutParams.width;
            } else {
                f = this.keylineStateList.getDefaultState().getItemSize();
            }
            if (this.keylineStateList == null || this.orientationHelper.orientation != 1) {
                f2 = (float) layoutParams.height;
            } else {
                f2 = this.keylineStateList.getDefaultState().getItemSize();
            }
            view.measure(getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + i3, (int) f, canScrollHorizontally()), getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + i4, (int) f2, canScrollVertically()));
            return;
        }
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    /* access modifiers changed from: private */
    public int getParentLeft() {
        return this.orientationHelper.getParentLeft();
    }

    private int getParentStart() {
        return this.orientationHelper.getParentStart();
    }

    /* access modifiers changed from: private */
    public int getParentRight() {
        return this.orientationHelper.getParentRight();
    }

    private int getParentEnd() {
        return this.orientationHelper.getParentEnd();
    }

    /* access modifiers changed from: private */
    public int getParentTop() {
        return this.orientationHelper.getParentTop();
    }

    /* access modifiers changed from: private */
    public int getParentBottom() {
        return this.orientationHelper.getParentBottom();
    }

    public int getContainerWidth() {
        return getWidth();
    }

    public int getContainerHeight() {
        return getHeight();
    }

    private int getContainerSize() {
        if (isHorizontal()) {
            return getContainerWidth();
        }
        return getContainerHeight();
    }

    /* access modifiers changed from: package-private */
    public boolean isLayoutRtl() {
        return isHorizontal() && getLayoutDirection() == 1;
    }

    private int addStart(int i, int i2) {
        return isLayoutRtl() ? i + i2 : i - i2;
    }

    private int addEnd(int i, int i2) {
        return isLayoutRtl() ? i - i2 : i + i2;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    private int getScrollOffsetForPosition(int i, KeylineState keylineState) {
        if (isLayoutRtl()) {
            return (int) (((((float) getContainerSize()) - keylineState.getLastFocalKeyline().loc) - (((float) i) * keylineState.getItemSize())) - (keylineState.getItemSize() / 2.0f));
        }
        return (int) (((((float) i) * keylineState.getItemSize()) - keylineState.getFirstFocalKeyline().loc) + (keylineState.getItemSize() / 2.0f));
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (this.keylineStateList == null) {
            return null;
        }
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i, getKeylineStateForPosition(i));
        if (isHorizontal()) {
            return new PointF((float) offsetToScrollToPosition, 0.0f);
        }
        return new PointF(0.0f, (float) offsetToScrollToPosition);
    }

    /* access modifiers changed from: package-private */
    public int getOffsetToScrollToPosition(int i, KeylineState keylineState) {
        return getScrollOffsetForPosition(i, keylineState) - this.scrollOffset;
    }

    /* access modifiers changed from: package-private */
    public int getOffsetToScrollToPositionForSnap(int i, boolean z) {
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i, this.keylineStateList.getShiftedState((float) this.scrollOffset, (float) this.minScroll, (float) this.maxScroll, true));
        int offsetToScrollToPosition2 = this.keylineStatePositionMap != null ? getOffsetToScrollToPosition(i, getKeylineStateForPosition(i)) : offsetToScrollToPosition;
        return (!z || Math.abs(offsetToScrollToPosition2) >= Math.abs(offsetToScrollToPosition)) ? offsetToScrollToPosition : offsetToScrollToPosition2;
    }

    private KeylineState getKeylineStateForPosition(int i) {
        KeylineState keylineState;
        Map<Integer, KeylineState> map = this.keylineStatePositionMap;
        if (map == null || (keylineState = map.get(Integer.valueOf(MathUtils.clamp(i, 0, Math.max(0, getItemCount() - 1))))) == null) {
            return this.keylineStateList.getDefaultState();
        }
        return keylineState;
    }

    public void scrollToPosition(int i) {
        if (this.keylineStateList != null) {
            this.scrollOffset = getScrollOffsetForPosition(i, getKeylineStateForPosition(i));
            this.currentFillStartPosition = MathUtils.clamp(i, 0, Math.max(0, getItemCount() - 1));
            updateCurrentKeylineStateForScrollOffset();
            requestLayout();
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        AnonymousClass1 r2 = new LinearSmoothScroller(recyclerView.getContext()) {
            public PointF computeScrollVectorForPosition(int i) {
                return CarouselLayoutManager.this.computeScrollVectorForPosition(i);
            }

            public int calculateDxToMakeVisible(View view, int i) {
                if (CarouselLayoutManager.this.keylineStateList == null || !CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }

            public int calculateDyToMakeVisible(View view, int i) {
                if (CarouselLayoutManager.this.keylineStateList == null || CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }
        };
        r2.setTargetPosition(i);
        startSmoothScroll(r2);
    }

    /* access modifiers changed from: package-private */
    public int calculateScrollDeltaToMakePositionVisible(int i) {
        return (int) (((float) this.scrollOffset) - ((float) getScrollOffsetForPosition(i, getKeylineStateForPosition(i))));
    }

    public boolean canScrollHorizontally() {
        return isHorizontal();
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollHorizontally()) {
            return scrollBy(i, recycler, state);
        }
        return 0;
    }

    public boolean canScrollVertically() {
        return !isHorizontal();
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollVertically()) {
            return scrollBy(i, recycler, state);
        }
        return 0;
    }

    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z, boolean z2) {
        if (this.keylineStateList == null) {
            return false;
        }
        int offsetToScrollToPosition = getOffsetToScrollToPosition(getPosition(view), getKeylineStateForPosition(getPosition(view)));
        if (z2 || offsetToScrollToPosition == 0) {
            return false;
        }
        recyclerView.scrollBy(offsetToScrollToPosition, 0);
        return true;
    }

    private int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        int calculateShouldScrollBy = calculateShouldScrollBy(i, this.scrollOffset, this.minScroll, this.maxScroll);
        this.scrollOffset += calculateShouldScrollBy;
        updateCurrentKeylineStateForScrollOffset();
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        int calculateChildStartForFill = calculateChildStartForFill(getPosition(getChildAt(0)));
        Rect rect = new Rect();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            offsetChild(getChildAt(i2), (float) calculateChildStartForFill, itemSize, rect);
            calculateChildStartForFill = addEnd(calculateChildStartForFill, (int) this.currentKeylineState.getItemSize());
        }
        fill(recycler, state);
        return calculateShouldScrollBy;
    }

    private void offsetChild(View view, float f, float f2, Rect rect) {
        float addEnd = (float) addEnd((int) f, (int) f2);
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), addEnd, false);
        float calculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, addEnd, surroundingKeylineRange);
        super.getDecoratedBoundsWithMargins(view, rect);
        updateChildMaskForLocation(view, addEnd, surroundingKeylineRange);
        this.orientationHelper.offsetChild(view, rect, f2, calculateChildOffsetCenterForLocation);
    }

    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return this.scrollOffset;
    }

    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return (int) this.keylineStateList.getDefaultState().getItemSize();
    }

    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return this.scrollOffset;
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return (int) this.keylineStateList.getDefaultState().getItemSize();
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    public int getOrientation() {
        return this.orientationHelper.orientation;
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll((String) null);
            CarouselOrientationHelper carouselOrientationHelper = this.orientationHelper;
            if (carouselOrientationHelper == null || i != carouselOrientationHelper.orientation) {
                this.orientationHelper = CarouselOrientationHelper.createOrientationHelper(this, i);
                refreshKeylineState();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation:" + i);
    }

    public void setDebuggingEnabled(RecyclerView recyclerView, boolean z) {
        this.isDebuggingEnabled = z;
        recyclerView.removeItemDecoration(this.debugItemDecoration);
        if (z) {
            recyclerView.addItemDecoration(this.debugItemDecoration);
        }
        recyclerView.invalidateItemDecorations();
    }

    private static class KeylineRange {
        final KeylineState.Keyline leftOrTop;
        final KeylineState.Keyline rightOrBottom;

        KeylineRange(KeylineState.Keyline keyline, KeylineState.Keyline keyline2) {
            Preconditions.checkArgument(keyline.loc <= keyline2.loc);
            this.leftOrTop = keyline;
            this.rightOrBottom = keyline2;
        }
    }

    private static class DebugItemDecoration extends RecyclerView.ItemDecoration {
        private List<KeylineState.Keyline> keylines = Collections.unmodifiableList(new ArrayList());
        private final Paint linePaint;

        DebugItemDecoration() {
            Paint paint = new Paint();
            this.linePaint = paint;
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        /* access modifiers changed from: package-private */
        public void setKeylines(List<KeylineState.Keyline> list) {
            this.keylines = Collections.unmodifiableList(list);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            this.linePaint.setStrokeWidth(recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            for (KeylineState.Keyline next : this.keylines) {
                this.linePaint.setColor(ColorUtils.blendARGB(-65281, -16776961, next.mask));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).isHorizontal()) {
                    canvas.drawLine(next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentTop(), next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentBottom(), this.linePaint);
                } else {
                    canvas.drawLine((float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentLeft(), next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentRight(), next.locOffset, this.linePaint);
                }
            }
        }
    }
}

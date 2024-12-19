package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.exoplayer2.C;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.Indicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.transformer.MZScaleInTransformer;
import com.youth.banner.transformer.ScaleInTransformer;
import com.youth.banner.util.BannerLifecycleObserver;
import com.youth.banner.util.BannerLifecycleObserverAdapter;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.ScrollSpeedManger;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.List;

public class Banner<T, BA extends BannerAdapter<T, ? extends RecyclerView.ViewHolder>> extends FrameLayout implements BannerLifecycleObserver {
    public static final int HORIZONTAL = 0;
    public static final int INVALID_VALUE = -1;
    public static final int VERTICAL = 1;
    private int indicatorGravity;
    private int indicatorHeight;
    private int indicatorMargin;
    private int indicatorMarginBottom;
    private int indicatorMarginLeft;
    private int indicatorMarginRight;
    private int indicatorMarginTop;
    private int indicatorRadius;
    private int indicatorSpace;
    private boolean isIntercept;
    private BA mAdapter;
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver;
    private float mBannerRadius;
    private CompositePageTransformer mCompositePageTransformer;
    private Paint mImagePaint;
    private Indicator mIndicator;
    /* access modifiers changed from: private */
    public boolean mIsAutoLoop;
    /* access modifiers changed from: private */
    public boolean mIsInfiniteLoop;
    private boolean mIsViewPager2Drag;
    /* access modifiers changed from: private */
    public AutoLoopTask mLoopTask;
    /* access modifiers changed from: private */
    public long mLoopTime;
    /* access modifiers changed from: private */
    public OnPageChangeListener mOnPageChangeListener;
    private int mOrientation;
    private Banner<T, BA>.BannerOnPageChangeCallback mPageChangeCallback;
    private boolean mRoundBottomLeft;
    private boolean mRoundBottomRight;
    private Paint mRoundPaint;
    private boolean mRoundTopLeft;
    private boolean mRoundTopRight;
    private int mScrollTime;
    private int mStartPosition;
    private float mStartX;
    private float mStartY;
    private int mTouchSlop;
    private ViewPager2 mViewPager2;
    private int normalColor;
    private int normalWidth;
    private int selectedColor;
    private int selectedWidth;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public Banner(Context context) {
        this(context, (AttributeSet) null);
    }

    public Banner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsInfiniteLoop = true;
        this.mIsAutoLoop = true;
        this.mLoopTime = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        this.mScrollTime = 600;
        this.mStartPosition = 1;
        this.mBannerRadius = 0.0f;
        this.normalWidth = BannerConfig.INDICATOR_NORMAL_WIDTH;
        this.selectedWidth = BannerConfig.INDICATOR_SELECTED_WIDTH;
        this.normalColor = BannerConfig.INDICATOR_NORMAL_COLOR;
        this.selectedColor = BannerConfig.INDICATOR_SELECTED_COLOR;
        this.indicatorGravity = 1;
        this.indicatorHeight = BannerConfig.INDICATOR_HEIGHT;
        this.indicatorRadius = BannerConfig.INDICATOR_RADIUS;
        this.mOrientation = 0;
        this.isIntercept = true;
        this.mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                if (Banner.this.getItemCount() <= 1) {
                    Banner.this.stop();
                } else {
                    Banner.this.start();
                }
                Banner.this.setIndicatorPageChange();
            }
        };
        init(context);
        initTypedArray(context, attributeSet);
    }

    private void init(Context context) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() / 2;
        this.mCompositePageTransformer = new CompositePageTransformer();
        this.mPageChangeCallback = new BannerOnPageChangeCallback();
        this.mLoopTask = new AutoLoopTask(this);
        ViewPager2 viewPager2 = new ViewPager2(context);
        this.mViewPager2 = viewPager2;
        viewPager2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mViewPager2.setOffscreenPageLimit(2);
        this.mViewPager2.registerOnPageChangeCallback(this.mPageChangeCallback);
        this.mViewPager2.setPageTransformer(this.mCompositePageTransformer);
        ScrollSpeedManger.reflectLayoutManager(this);
        addView(this.mViewPager2);
        Paint paint = new Paint();
        this.mRoundPaint = paint;
        paint.setColor(-1);
        this.mRoundPaint.setAntiAlias(true);
        this.mRoundPaint.setStyle(Paint.Style.FILL);
        this.mRoundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Paint paint2 = new Paint();
        this.mImagePaint = paint2;
        paint2.setXfermode((Xfermode) null);
    }

    private void initTypedArray(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Banner);
            this.mBannerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_radius, 0);
            this.mLoopTime = (long) obtainStyledAttributes.getInt(R.styleable.Banner_banner_loop_time, 3000);
            this.mIsAutoLoop = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_auto_loop, true);
            this.mIsInfiniteLoop = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_infinite_loop, true);
            this.normalWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_normal_width, BannerConfig.INDICATOR_NORMAL_WIDTH);
            this.selectedWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_selected_width, BannerConfig.INDICATOR_SELECTED_WIDTH);
            this.normalColor = obtainStyledAttributes.getColor(R.styleable.Banner_banner_indicator_normal_color, BannerConfig.INDICATOR_NORMAL_COLOR);
            this.selectedColor = obtainStyledAttributes.getColor(R.styleable.Banner_banner_indicator_selected_color, BannerConfig.INDICATOR_SELECTED_COLOR);
            this.indicatorGravity = obtainStyledAttributes.getInt(R.styleable.Banner_banner_indicator_gravity, 1);
            this.indicatorSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_space, 0);
            this.indicatorMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_margin, 0);
            this.indicatorMarginLeft = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_marginLeft, 0);
            this.indicatorMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_marginTop, 0);
            this.indicatorMarginRight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_marginRight, 0);
            this.indicatorMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_marginBottom, 0);
            this.indicatorHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_height, BannerConfig.INDICATOR_HEIGHT);
            this.indicatorRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_banner_indicator_radius, BannerConfig.INDICATOR_RADIUS);
            this.mOrientation = obtainStyledAttributes.getInt(R.styleable.Banner_banner_orientation, 0);
            this.mRoundTopLeft = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_round_top_left, false);
            this.mRoundTopRight = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_round_top_right, false);
            this.mRoundBottomLeft = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_round_bottom_left, false);
            this.mRoundBottomRight = obtainStyledAttributes.getBoolean(R.styleable.Banner_banner_round_bottom_right, false);
            obtainStyledAttributes.recycle();
        }
        setOrientation(this.mOrientation);
        setInfiniteLoop();
    }

    private void initIndicatorAttr() {
        int i = this.indicatorMargin;
        if (i != 0) {
            setIndicatorMargins(new IndicatorConfig.Margins(i));
        } else {
            int i2 = this.indicatorMarginLeft;
            if (!(i2 == 0 && this.indicatorMarginTop == 0 && this.indicatorMarginRight == 0 && this.indicatorMarginBottom == 0)) {
                setIndicatorMargins(new IndicatorConfig.Margins(i2, this.indicatorMarginTop, this.indicatorMarginRight, this.indicatorMarginBottom));
            }
        }
        int i3 = this.indicatorSpace;
        if (i3 > 0) {
            setIndicatorSpace(i3);
        }
        int i4 = this.indicatorGravity;
        if (i4 != 1) {
            setIndicatorGravity(i4);
        }
        int i5 = this.normalWidth;
        if (i5 > 0) {
            setIndicatorNormalWidth(i5);
        }
        int i6 = this.selectedWidth;
        if (i6 > 0) {
            setIndicatorSelectedWidth(i6);
        }
        int i7 = this.indicatorHeight;
        if (i7 > 0) {
            setIndicatorHeight(i7);
        }
        int i8 = this.indicatorRadius;
        if (i8 > 0) {
            setIndicatorRadius(i8);
        }
        setIndicatorNormalColor(this.normalColor);
        setIndicatorSelectedColor(this.selectedColor);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!getViewPager2().isUserInputEnabled()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3 || actionMasked == 4) {
            start();
        } else if (actionMasked == 0) {
            stop();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r0 != 3) goto L_0x0085;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            androidx.viewpager2.widget.ViewPager2 r0 = r5.getViewPager2()
            boolean r0 = r0.isUserInputEnabled()
            if (r0 == 0) goto L_0x008a
            boolean r0 = r5.isIntercept
            if (r0 != 0) goto L_0x0010
            goto L_0x008a
        L_0x0010:
            int r0 = r6.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x0072
            r2 = 0
            if (r0 == r1) goto L_0x006a
            r3 = 2
            if (r0 == r3) goto L_0x0021
            r1 = 3
            if (r0 == r1) goto L_0x006a
            goto L_0x0085
        L_0x0021:
            float r0 = r6.getX()
            float r3 = r6.getY()
            float r4 = r5.mStartX
            float r0 = r0 - r4
            float r0 = java.lang.Math.abs(r0)
            float r4 = r5.mStartY
            float r3 = r3 - r4
            float r3 = java.lang.Math.abs(r3)
            androidx.viewpager2.widget.ViewPager2 r4 = r5.getViewPager2()
            int r4 = r4.getOrientation()
            if (r4 != 0) goto L_0x0051
            int r4 = r5.mTouchSlop
            float r4 = (float) r4
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x004d
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r1 = r2
        L_0x004e:
            r5.mIsViewPager2Drag = r1
            goto L_0x0060
        L_0x0051:
            int r4 = r5.mTouchSlop
            float r4 = (float) r4
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x005d
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r1 = r2
        L_0x005e:
            r5.mIsViewPager2Drag = r1
        L_0x0060:
            android.view.ViewParent r0 = r5.getParent()
            boolean r1 = r5.mIsViewPager2Drag
            r0.requestDisallowInterceptTouchEvent(r1)
            goto L_0x0085
        L_0x006a:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            goto L_0x0085
        L_0x0072:
            float r0 = r6.getX()
            r5.mStartX = r0
            float r0 = r6.getY()
            r5.mStartY = r0
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r1)
        L_0x0085:
            boolean r6 = super.onInterceptTouchEvent(r6)
            return r6
        L_0x008a:
            boolean r6 = super.onInterceptTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youth.banner.Banner.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mBannerRadius > 0.0f) {
            canvas.saveLayer(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.mImagePaint, 31);
            super.dispatchDraw(canvas);
            if (this.mRoundTopRight || this.mRoundTopLeft || this.mRoundBottomRight || this.mRoundBottomLeft) {
                if (this.mRoundTopLeft) {
                    drawTopLeft(canvas);
                }
                if (this.mRoundTopRight) {
                    drawTopRight(canvas);
                }
                if (this.mRoundBottomLeft) {
                    drawBottomLeft(canvas);
                }
                if (this.mRoundBottomRight) {
                    drawBottomRight(canvas);
                }
                canvas.restore();
                return;
            }
            drawTopLeft(canvas);
            drawTopRight(canvas);
            drawBottomLeft(canvas);
            drawBottomRight(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    private void drawTopLeft(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0.0f, this.mBannerRadius);
        path.lineTo(0.0f, 0.0f);
        path.lineTo(this.mBannerRadius, 0.0f);
        float f = this.mBannerRadius;
        path.arcTo(new RectF(0.0f, 0.0f, f * 2.0f, f * 2.0f), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.mRoundPaint);
    }

    private void drawTopRight(Canvas canvas) {
        int width = getWidth();
        Path path = new Path();
        float f = (float) width;
        path.moveTo(f - this.mBannerRadius, 0.0f);
        path.lineTo(f, 0.0f);
        path.lineTo(f, this.mBannerRadius);
        float f2 = this.mBannerRadius;
        path.arcTo(new RectF(f - (f2 * 2.0f), 0.0f, f, f2 * 2.0f), 0.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.mRoundPaint);
    }

    private void drawBottomLeft(Canvas canvas) {
        int height = getHeight();
        Path path = new Path();
        float f = (float) height;
        path.moveTo(0.0f, f - this.mBannerRadius);
        path.lineTo(0.0f, f);
        path.lineTo(this.mBannerRadius, f);
        float f2 = this.mBannerRadius;
        path.arcTo(new RectF(0.0f, f - (f2 * 2.0f), f2 * 2.0f, f), 90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.mRoundPaint);
    }

    private void drawBottomRight(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        Path path = new Path();
        float f = (float) width;
        float f2 = (float) height;
        path.moveTo(f - this.mBannerRadius, f2);
        path.lineTo(f, f2);
        path.lineTo(f, f2 - this.mBannerRadius);
        float f3 = this.mBannerRadius;
        path.arcTo(new RectF(f - (f3 * 2.0f), f2 - (f3 * 2.0f), f, f2), 0.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.mRoundPaint);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    class BannerOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        private boolean isScrolled;
        private int mTempPosition = -1;

        BannerOnPageChangeCallback() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            int realPosition = BannerUtils.getRealPosition(Banner.this.isInfiniteLoop(), i, Banner.this.getRealCount());
            if (Banner.this.mOnPageChangeListener != null && realPosition == Banner.this.getCurrentItem() - 1) {
                Banner.this.mOnPageChangeListener.onPageScrolled(realPosition, f, i2);
            }
            if (Banner.this.getIndicator() != null && realPosition == Banner.this.getCurrentItem() - 1) {
                Banner.this.getIndicator().onPageScrolled(realPosition, f, i2);
            }
        }

        public void onPageSelected(int i) {
            if (this.isScrolled) {
                this.mTempPosition = i;
                int realPosition = BannerUtils.getRealPosition(Banner.this.isInfiniteLoop(), i, Banner.this.getRealCount());
                if (Banner.this.mOnPageChangeListener != null) {
                    Banner.this.mOnPageChangeListener.onPageSelected(realPosition);
                }
                if (Banner.this.getIndicator() != null) {
                    Banner.this.getIndicator().onPageSelected(realPosition);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 1 || i == 2) {
                this.isScrolled = true;
            } else if (i == 0) {
                this.isScrolled = false;
                if (this.mTempPosition != -1 && Banner.this.mIsInfiniteLoop) {
                    int i2 = this.mTempPosition;
                    if (i2 == 0) {
                        Banner banner = Banner.this;
                        banner.setCurrentItem(banner.getRealCount(), false);
                    } else if (i2 == Banner.this.getItemCount() - 1) {
                        Banner.this.setCurrentItem(1, false);
                    }
                }
            }
            if (Banner.this.mOnPageChangeListener != null) {
                Banner.this.mOnPageChangeListener.onPageScrollStateChanged(i);
            }
            if (Banner.this.getIndicator() != null) {
                Banner.this.getIndicator().onPageScrollStateChanged(i);
            }
        }
    }

    static class AutoLoopTask implements Runnable {
        private final WeakReference<Banner> reference;

        AutoLoopTask(Banner banner) {
            this.reference = new WeakReference<>(banner);
        }

        public void run() {
            int itemCount;
            Banner banner = (Banner) this.reference.get();
            if (banner != null && banner.mIsAutoLoop && (itemCount = banner.getItemCount()) != 0) {
                banner.setCurrentItem((banner.getCurrentItem() + 1) % itemCount);
                banner.postDelayed(banner.mLoopTask, banner.mLoopTime);
            }
        }
    }

    private void initIndicator() {
        if (getIndicator() != null && getAdapter() != null) {
            if (getIndicator().getIndicatorConfig().isAttachToBanner()) {
                removeIndicator();
                addView(getIndicator().getIndicatorView());
            }
            initIndicatorAttr();
            setIndicatorPageChange();
        }
    }

    private void setInfiniteLoop() {
        int i = 0;
        if (!isInfiniteLoop()) {
            isAutoLoop(false);
        }
        if (isInfiniteLoop()) {
            i = this.mStartPosition;
        }
        setStartPosition(i);
    }

    private void setRecyclerViewPadding(int i) {
        setRecyclerViewPadding(i, i);
    }

    private void setRecyclerViewPadding(int i, int i2) {
        RecyclerView recyclerView = (RecyclerView) getViewPager2().getChildAt(0);
        if (getViewPager2().getOrientation() == 1) {
            recyclerView.setPadding(this.mViewPager2.getPaddingLeft(), i, this.mViewPager2.getPaddingRight(), i2);
        } else {
            recyclerView.setPadding(i, this.mViewPager2.getPaddingTop(), i2, this.mViewPager2.getPaddingBottom());
        }
        recyclerView.setClipToPadding(false);
    }

    public int getCurrentItem() {
        return getViewPager2().getCurrentItem();
    }

    public int getItemCount() {
        if (getAdapter() != null) {
            return getAdapter().getItemCount();
        }
        return 0;
    }

    public int getScrollTime() {
        return this.mScrollTime;
    }

    public boolean isInfiniteLoop() {
        return this.mIsInfiniteLoop;
    }

    public BannerAdapter getAdapter() {
        return this.mAdapter;
    }

    public ViewPager2 getViewPager2() {
        return this.mViewPager2;
    }

    public Indicator getIndicator() {
        return this.mIndicator;
    }

    public IndicatorConfig getIndicatorConfig() {
        if (getIndicator() != null) {
            return getIndicator().getIndicatorConfig();
        }
        return null;
    }

    public int getRealCount() {
        if (getAdapter() != null) {
            return getAdapter().getRealCount();
        }
        return 0;
    }

    public Banner setIntercept(boolean z) {
        this.isIntercept = z;
        return this;
    }

    public Banner setCurrentItem(int i) {
        return setCurrentItem(i, true);
    }

    public Banner setCurrentItem(int i, boolean z) {
        getViewPager2().setCurrentItem(i, z);
        return this;
    }

    public Banner setIndicatorPageChange() {
        if (getIndicator() != null) {
            getIndicator().onPageChanged(getRealCount(), BannerUtils.getRealPosition(isInfiniteLoop(), getCurrentItem(), getRealCount()));
        }
        return this;
    }

    public Banner removeIndicator() {
        if (getIndicator() != null) {
            removeView(getIndicator().getIndicatorView());
        }
        return this;
    }

    public Banner setStartPosition(int i) {
        this.mStartPosition = i;
        return this;
    }

    public int getStartPosition() {
        return this.mStartPosition;
    }

    public Banner setUserInputEnabled(boolean z) {
        getViewPager2().setUserInputEnabled(z);
        return this;
    }

    public Banner addPageTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mCompositePageTransformer.addTransformer(pageTransformer);
        return this;
    }

    public Banner setPageTransformer(ViewPager2.PageTransformer pageTransformer) {
        getViewPager2().setPageTransformer(pageTransformer);
        return this;
    }

    public Banner removeTransformer(ViewPager2.PageTransformer pageTransformer) {
        this.mCompositePageTransformer.removeTransformer(pageTransformer);
        return this;
    }

    public Banner addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        getViewPager2().addItemDecoration(itemDecoration);
        return this;
    }

    public Banner addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int i) {
        getViewPager2().addItemDecoration(itemDecoration, i);
        return this;
    }

    public Banner isAutoLoop(boolean z) {
        this.mIsAutoLoop = z;
        return this;
    }

    public Banner setLoopTime(long j) {
        this.mLoopTime = j;
        return this;
    }

    public Banner setScrollTime(int i) {
        this.mScrollTime = i;
        return this;
    }

    public Banner start() {
        if (this.mIsAutoLoop) {
            stop();
            postDelayed(this.mLoopTask, this.mLoopTime);
        }
        return this;
    }

    public Banner stop() {
        if (this.mIsAutoLoop) {
            removeCallbacks(this.mLoopTask);
        }
        return this;
    }

    public void destroy() {
        if (!(getViewPager2() == null || this.mPageChangeCallback == null)) {
            getViewPager2().unregisterOnPageChangeCallback(this.mPageChangeCallback);
            this.mPageChangeCallback = null;
        }
        stop();
    }

    public Banner setAdapter(BA ba) {
        if (ba != null) {
            this.mAdapter = ba;
            if (!isInfiniteLoop()) {
                getAdapter().setIncreaseCount(0);
            }
            getAdapter().registerAdapterDataObserver(this.mAdapterDataObserver);
            this.mViewPager2.setAdapter(ba);
            setCurrentItem(this.mStartPosition, false);
            initIndicator();
            return this;
        }
        throw new NullPointerException(getContext().getString(R.string.banner_adapter_null_error));
    }

    public Banner setAdapter(BA ba, boolean z) {
        this.mIsInfiniteLoop = z;
        setInfiniteLoop();
        setAdapter(ba);
        return this;
    }

    public Banner setDatas(List<T> list) {
        if (getAdapter() != null) {
            getAdapter().setDatas(list);
            setCurrentItem(this.mStartPosition, false);
            setIndicatorPageChange();
            start();
        }
        return this;
    }

    public Banner setOrientation(int i) {
        getViewPager2().setOrientation(i);
        return this;
    }

    public Banner setTouchSlop(int i) {
        this.mTouchSlop = i;
        return this;
    }

    public Banner setOnBannerListener(OnBannerListener<T> onBannerListener) {
        if (getAdapter() != null) {
            getAdapter().setOnBannerListener(onBannerListener);
        }
        return this;
    }

    public Banner addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
        return this;
    }

    public Banner setBannerRound(float f) {
        this.mBannerRadius = f;
        return this;
    }

    public Banner setBannerRound2(float f) {
        BannerUtils.setBannerRound(this, f);
        return this;
    }

    public Banner setBannerGalleryEffect(int i, int i2) {
        return setBannerGalleryEffect(i, i2, 0.85f);
    }

    public Banner setBannerGalleryEffect(int i, int i2, int i3) {
        return setBannerGalleryEffect(i, i2, i3, 0.85f);
    }

    public Banner setBannerGalleryEffect(int i, int i2, float f) {
        return setBannerGalleryEffect(i, i, i2, f);
    }

    public Banner setBannerGalleryEffect(int i, int i2, int i3, float f) {
        if (i3 > 0) {
            addPageTransformer(new MarginPageTransformer(BannerUtils.dp2px((float) i3)));
        }
        if (f < 1.0f && f > 0.0f) {
            addPageTransformer(new ScaleInTransformer(f));
        }
        int i4 = 0;
        int dp2px = i > 0 ? BannerUtils.dp2px((float) (i + i3)) : 0;
        if (i2 > 0) {
            i4 = BannerUtils.dp2px((float) (i2 + i3));
        }
        setRecyclerViewPadding(dp2px, i4);
        return this;
    }

    public Banner setBannerGalleryMZ(int i) {
        return setBannerGalleryMZ(i, 0.88f);
    }

    public Banner setBannerGalleryMZ(int i, float f) {
        if (f < 1.0f && f > 0.0f) {
            addPageTransformer(new MZScaleInTransformer(f));
        }
        setRecyclerViewPadding(BannerUtils.dp2px((float) i));
        return this;
    }

    public Banner setIndicator(Indicator indicator) {
        return setIndicator(indicator, true);
    }

    public Banner setIndicator(Indicator indicator, boolean z) {
        removeIndicator();
        indicator.getIndicatorConfig().setAttachToBanner(z);
        this.mIndicator = indicator;
        initIndicator();
        return this;
    }

    public Banner setIndicatorSelectedColor(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setSelectedColor(i);
        }
        return this;
    }

    public Banner setIndicatorSelectedColorRes(int i) {
        setIndicatorSelectedColor(ContextCompat.getColor(getContext(), i));
        return this;
    }

    public Banner setIndicatorNormalColor(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalColor(i);
        }
        return this;
    }

    public Banner setIndicatorNormalColorRes(int i) {
        setIndicatorNormalColor(ContextCompat.getColor(getContext(), i));
        return this;
    }

    public Banner setIndicatorGravity(int i) {
        if (getIndicatorConfig() != null && getIndicatorConfig().isAttachToBanner()) {
            getIndicatorConfig().setGravity(i);
            getIndicator().getIndicatorView().postInvalidate();
        }
        return this;
    }

    public Banner setIndicatorSpace(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setIndicatorSpace(i);
        }
        return this;
    }

    public Banner setIndicatorMargins(IndicatorConfig.Margins margins) {
        if (getIndicatorConfig() != null && getIndicatorConfig().isAttachToBanner()) {
            getIndicatorConfig().setMargins(margins);
            getIndicator().getIndicatorView().requestLayout();
        }
        return this;
    }

    public Banner setIndicatorWidth(int i, int i2) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalWidth(i);
            getIndicatorConfig().setSelectedWidth(i2);
        }
        return this;
    }

    public Banner setIndicatorNormalWidth(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalWidth(i);
        }
        return this;
    }

    public Banner setIndicatorSelectedWidth(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setSelectedWidth(i);
        }
        return this;
    }

    public Banner setIndicatorRadius(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setRadius(i);
        }
        return this;
    }

    public Banner setIndicatorHeight(int i) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setHeight(i);
        }
        return this;
    }

    public Banner addBannerLifecycleObserver(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(new BannerLifecycleObserverAdapter(lifecycleOwner, this));
        }
        return this;
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        start();
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        stop();
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        destroy();
    }
}

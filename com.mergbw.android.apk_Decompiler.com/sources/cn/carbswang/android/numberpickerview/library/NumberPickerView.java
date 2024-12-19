package cn.carbswang.android.numberpickerview.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ScrollerCompat;
import com.alibaba.android.arouter.utils.Consts;

public class NumberPickerView extends View {
    private static final boolean DEFAULT_CURRENT_ITEM_INDEX_EFFECT = false;
    private static final int DEFAULT_DIVIDER_COLOR = -695533;
    private static final int DEFAULT_DIVIDER_HEIGHT = 2;
    private static final int DEFAULT_DIVIDER_MARGIN_HORIZONTAL = 0;
    private static final int DEFAULT_INTERVAL_REVISE_DURATION = 300;
    private static final int DEFAULT_ITEM_PADDING_DP_H = 5;
    private static final int DEFAULT_ITEM_PADDING_DP_V = 2;
    private static final int DEFAULT_MARGIN_END_OF_HINT_DP = 8;
    private static final int DEFAULT_MARGIN_START_OF_HINT_DP = 8;
    private static final int DEFAULT_MAX_SCROLL_BY_INDEX_DURATION = 600;
    private static final int DEFAULT_MIN_SCROLL_BY_INDEX_DURATION = 300;
    private static final boolean DEFAULT_RESPOND_CHANGE_IN_MAIN_THREAD = true;
    private static final boolean DEFAULT_RESPOND_CHANGE_ON_DETACH = false;
    private static final int DEFAULT_SHOW_COUNT = 3;
    private static final boolean DEFAULT_SHOW_DIVIDER = true;
    private static final int DEFAULT_TEXT_COLOR_NORMAL = -13421773;
    private static final int DEFAULT_TEXT_COLOR_SELECTED = -695533;
    private static final int DEFAULT_TEXT_SIZE_HINT_SP = 14;
    private static final int DEFAULT_TEXT_SIZE_NORMAL_SP = 14;
    private static final int DEFAULT_TEXT_SIZE_SELECTED_SP = 16;
    private static final boolean DEFAULT_WRAP_SELECTOR_WHEEL = true;
    private static final int HANDLER_INTERVAL_REFRESH = 32;
    private static final int HANDLER_WHAT_LISTENER_VALUE_CHANGED = 2;
    private static final int HANDLER_WHAT_REFRESH = 1;
    private static final int HANDLER_WHAT_REQUEST_LAYOUT = 3;
    private static final String TEXT_ELLIPSIZE_END = "end";
    private static final String TEXT_ELLIPSIZE_MIDDLE = "middle";
    private static final String TEXT_ELLIPSIZE_START = "start";
    private float currY = 0.0f;
    private float dividerY0;
    private float dividerY1;
    private float downY = 0.0f;
    private float downYGlobal = 0.0f;
    private String mAlterHint;
    private CharSequence[] mAlterTextArrayWithMeasureHint;
    private CharSequence[] mAlterTextArrayWithoutMeasureHint;
    private int mCurrDrawFirstItemIndex = 0;
    /* access modifiers changed from: private */
    public int mCurrDrawFirstItemY = 0;
    /* access modifiers changed from: private */
    public int mCurrDrawGlobalY = 0;
    private boolean mCurrentItemIndexEffect = false;
    private String[] mDisplayedValues;
    private int mDividerColor = -695533;
    private int mDividerHeight = 2;
    private int mDividerIndex0 = 0;
    private int mDividerIndex1 = 0;
    private int mDividerMarginL = 0;
    private int mDividerMarginR = 0;
    private String mEmptyItemHint;
    private boolean mFlagMayPress = false;
    private float mFriction = 1.0f;
    /* access modifiers changed from: private */
    public Handler mHandlerInMainThread;
    /* access modifiers changed from: private */
    public Handler mHandlerInNewThread;
    private HandlerThread mHandlerThread;
    private boolean mHasInit = false;
    private String mHintText;
    private int mInScrollingPickedNewValue;
    private int mInScrollingPickedOldValue;
    /* access modifiers changed from: private */
    public int mItemHeight;
    private int mItemPaddingHorizontal = 0;
    private int mItemPaddingVertical = 0;
    private int mMarginEndOfHint = 0;
    private int mMarginStartOfHint = 0;
    private int mMaxHeightOfDisplayedValues = 0;
    private int mMaxShowIndex = -1;
    private int mMaxValue = 0;
    private int mMaxWidthOfAlterArrayWithMeasureHint = 0;
    private int mMaxWidthOfAlterArrayWithoutMeasureHint = 0;
    private int mMaxWidthOfDisplayedValues = 0;
    private int mMinShowIndex = -1;
    private int mMinValue = 0;
    private int mMiniVelocityFling = 150;
    private int mNotWrapLimitYBottom;
    private int mNotWrapLimitYTop;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private OnValueChangeListenerInScrolling mOnValueChangeListenerInScrolling;
    private OnValueChangeListenerRelativeToRaw mOnValueChangeListenerRaw;
    private Paint mPaintDivider = new Paint();
    private Paint mPaintHint = new Paint();
    private TextPaint mPaintText = new TextPaint();
    private boolean mPendingWrapToLinear = false;
    /* access modifiers changed from: private */
    public int mPrevPickedIndex = 0;
    /* access modifiers changed from: private */
    public boolean mRespondChangeInMainThread = true;
    private boolean mRespondChangeOnDetach = false;
    private int mScaledTouchSlop = 8;
    /* access modifiers changed from: private */
    public int mScrollState = 0;
    /* access modifiers changed from: private */
    public ScrollerCompat mScroller;
    private int mShowCount = 3;
    private boolean mShowDivider = true;
    private int mSpecModeH = 0;
    private int mSpecModeW = 0;
    private int mTextColorHint = -695533;
    private int mTextColorNormal = DEFAULT_TEXT_COLOR_NORMAL;
    private int mTextColorSelected = -695533;
    private String mTextEllipsize;
    private int mTextSizeHint = 0;
    private float mTextSizeHintCenterYOffset = 0.0f;
    private int mTextSizeNormal = 0;
    private float mTextSizeNormalCenterYOffset = 0.0f;
    private int mTextSizeSelected = 0;
    private float mTextSizeSelectedCenterYOffset = 0.0f;
    private VelocityTracker mVelocityTracker;
    private float mViewCenterX;
    private int mViewHeight;
    private int mViewWidth;
    private int mWidthOfAlterHint = 0;
    private int mWidthOfHintText = 0;
    private boolean mWrapSelectorWheel = true;
    private boolean mWrapSelectorWheelCheck = true;

    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScrollStateChange(NumberPickerView numberPickerView, int i);
    }

    public interface OnValueChangeListener {
        void onValueChange(NumberPickerView numberPickerView, int i, int i2);
    }

    public interface OnValueChangeListenerInScrolling {
        void onValueChangeInScrolling(NumberPickerView numberPickerView, int i, int i2);
    }

    public interface OnValueChangeListenerRelativeToRaw {
        void onValueChangeRelativeToRaw(NumberPickerView numberPickerView, int i, int i2, String[] strArr);
    }

    private int getEvaluateColor(float f, int i, int i2) {
        int i3 = (i & ViewCompat.MEASURED_STATE_MASK) >>> 24;
        int i4 = (i & 16711680) >>> 16;
        int i5 = (i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8;
        int i6 = i & 255;
        return ((int) (((float) i6) + (((float) ((i2 & 255) - i6)) * f))) | (((int) (((float) i3) + (((float) (((-16777216 & i2) >>> 24) - i3)) * f))) << 24) | (((int) (((float) i4) + (((float) (((16711680 & i2) >>> 16) - i4)) * f))) << 16) | (((int) (((float) i5) + (((float) (((65280 & i2) >>> 8) - i5)) * f))) << 8);
    }

    private float getEvaluateSize(float f, float f2, float f3) {
        return f2 + ((f3 - f2) * f);
    }

    public NumberPickerView(Context context) {
        super(context);
        init(context);
    }

    public NumberPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttr(context, attributeSet);
        init(context);
    }

    public NumberPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttr(context, attributeSet);
        init(context);
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NumberPickerView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.NumberPickerView_npv_ShowCount) {
                    this.mShowCount = obtainStyledAttributes.getInt(index, 3);
                } else if (index == R.styleable.NumberPickerView_npv_DividerColor) {
                    this.mDividerColor = obtainStyledAttributes.getColor(index, -695533);
                } else if (index == R.styleable.NumberPickerView_npv_DividerHeight) {
                    this.mDividerHeight = obtainStyledAttributes.getDimensionPixelSize(index, 2);
                } else if (index == R.styleable.NumberPickerView_npv_DividerMarginLeft) {
                    this.mDividerMarginL = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.NumberPickerView_npv_DividerMarginRight) {
                    this.mDividerMarginR = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.NumberPickerView_npv_TextArray) {
                    this.mDisplayedValues = convertCharSequenceArrayToStringArray(obtainStyledAttributes.getTextArray(index));
                } else if (index == R.styleable.NumberPickerView_npv_TextColorNormal) {
                    this.mTextColorNormal = obtainStyledAttributes.getColor(index, DEFAULT_TEXT_COLOR_NORMAL);
                } else if (index == R.styleable.NumberPickerView_npv_TextColorSelected) {
                    this.mTextColorSelected = obtainStyledAttributes.getColor(index, -695533);
                } else if (index == R.styleable.NumberPickerView_npv_TextColorHint) {
                    this.mTextColorHint = obtainStyledAttributes.getColor(index, -695533);
                } else if (index == R.styleable.NumberPickerView_npv_TextSizeNormal) {
                    this.mTextSizeNormal = obtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 14.0f));
                } else if (index == R.styleable.NumberPickerView_npv_TextSizeSelected) {
                    this.mTextSizeSelected = obtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 16.0f));
                } else if (index == R.styleable.NumberPickerView_npv_TextSizeHint) {
                    this.mTextSizeHint = obtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 14.0f));
                } else if (index == R.styleable.NumberPickerView_npv_MinValue) {
                    this.mMinShowIndex = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.NumberPickerView_npv_MaxValue) {
                    this.mMaxShowIndex = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.NumberPickerView_npv_WrapSelectorWheel) {
                    this.mWrapSelectorWheel = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == R.styleable.NumberPickerView_npv_ShowDivider) {
                    this.mShowDivider = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == R.styleable.NumberPickerView_npv_HintText) {
                    this.mHintText = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.NumberPickerView_npv_AlternativeHint) {
                    this.mAlterHint = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.NumberPickerView_npv_EmptyItemHint) {
                    this.mEmptyItemHint = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.NumberPickerView_npv_MarginStartOfHint) {
                    this.mMarginStartOfHint = obtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 8.0f));
                } else if (index == R.styleable.NumberPickerView_npv_MarginEndOfHint) {
                    this.mMarginEndOfHint = obtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 8.0f));
                } else if (index == R.styleable.NumberPickerView_npv_ItemPaddingVertical) {
                    this.mItemPaddingVertical = obtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 2.0f));
                } else if (index == R.styleable.NumberPickerView_npv_ItemPaddingHorizontal) {
                    this.mItemPaddingHorizontal = obtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 5.0f));
                } else if (index == R.styleable.NumberPickerView_npv_AlternativeTextArrayWithMeasureHint) {
                    this.mAlterTextArrayWithMeasureHint = obtainStyledAttributes.getTextArray(index);
                } else if (index == R.styleable.NumberPickerView_npv_AlternativeTextArrayWithoutMeasureHint) {
                    this.mAlterTextArrayWithoutMeasureHint = obtainStyledAttributes.getTextArray(index);
                } else if (index == R.styleable.NumberPickerView_npv_RespondChangeOnDetached) {
                    this.mRespondChangeOnDetach = obtainStyledAttributes.getBoolean(index, false);
                } else if (index == R.styleable.NumberPickerView_npv_RespondChangeInMainThread) {
                    this.mRespondChangeInMainThread = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == R.styleable.NumberPickerView_npv_TextEllipsize) {
                    this.mTextEllipsize = obtainStyledAttributes.getString(index);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    private void init(Context context) {
        this.mScroller = ScrollerCompat.create(context);
        this.mMiniVelocityFling = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        if (this.mTextSizeNormal == 0) {
            this.mTextSizeNormal = sp2px(context, 14.0f);
        }
        if (this.mTextSizeSelected == 0) {
            this.mTextSizeSelected = sp2px(context, 16.0f);
        }
        if (this.mTextSizeHint == 0) {
            this.mTextSizeHint = sp2px(context, 14.0f);
        }
        if (this.mMarginStartOfHint == 0) {
            this.mMarginStartOfHint = dp2px(context, 8.0f);
        }
        if (this.mMarginEndOfHint == 0) {
            this.mMarginEndOfHint = dp2px(context, 8.0f);
        }
        this.mPaintDivider.setColor(this.mDividerColor);
        this.mPaintDivider.setAntiAlias(true);
        this.mPaintDivider.setStyle(Paint.Style.STROKE);
        this.mPaintDivider.setStrokeWidth((float) this.mDividerHeight);
        this.mPaintText.setColor(this.mTextColorNormal);
        this.mPaintText.setAntiAlias(true);
        this.mPaintText.setTextAlign(Paint.Align.CENTER);
        this.mPaintHint.setColor(this.mTextColorHint);
        this.mPaintHint.setAntiAlias(true);
        this.mPaintHint.setTextAlign(Paint.Align.CENTER);
        this.mPaintHint.setTextSize((float) this.mTextSizeHint);
        int i = this.mShowCount;
        if (i % 2 == 0) {
            this.mShowCount = i + 1;
        }
        if (this.mMinShowIndex == -1 || this.mMaxShowIndex == -1) {
            updateValueForInit();
        }
        initHandler();
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("HandlerThread-For-Refreshing");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandlerInNewThread = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(Message message) {
                int i;
                int i2;
                int i3 = message.what;
                if (i3 == 1) {
                    int i4 = 0;
                    if (!NumberPickerView.this.mScroller.isFinished()) {
                        if (NumberPickerView.this.mScrollState == 0) {
                            NumberPickerView.this.onScrollStateChange(1);
                        }
                        NumberPickerView.this.mHandlerInNewThread.sendMessageDelayed(NumberPickerView.this.getMsg(1, 0, 0, message.obj), 32);
                        return;
                    }
                    if (NumberPickerView.this.mCurrDrawFirstItemY != 0) {
                        if (NumberPickerView.this.mScrollState == 0) {
                            NumberPickerView.this.onScrollStateChange(1);
                        }
                        if (NumberPickerView.this.mCurrDrawFirstItemY < (-NumberPickerView.this.mItemHeight) / 2) {
                            i2 = (int) ((((float) (NumberPickerView.this.mItemHeight + NumberPickerView.this.mCurrDrawFirstItemY)) * 300.0f) / ((float) NumberPickerView.this.mItemHeight));
                            NumberPickerView.this.mScroller.startScroll(0, NumberPickerView.this.mCurrDrawGlobalY, 0, NumberPickerView.this.mItemHeight + NumberPickerView.this.mCurrDrawFirstItemY, i2 * 3);
                            NumberPickerView numberPickerView = NumberPickerView.this;
                            i = numberPickerView.getWillPickIndexByGlobalY(numberPickerView.mCurrDrawGlobalY + NumberPickerView.this.mItemHeight + NumberPickerView.this.mCurrDrawFirstItemY);
                        } else {
                            i2 = (int) ((((float) (-NumberPickerView.this.mCurrDrawFirstItemY)) * 300.0f) / ((float) NumberPickerView.this.mItemHeight));
                            NumberPickerView.this.mScroller.startScroll(0, NumberPickerView.this.mCurrDrawGlobalY, 0, NumberPickerView.this.mCurrDrawFirstItemY, i2 * 3);
                            NumberPickerView numberPickerView2 = NumberPickerView.this;
                            i = numberPickerView2.getWillPickIndexByGlobalY(numberPickerView2.mCurrDrawGlobalY + NumberPickerView.this.mCurrDrawFirstItemY);
                        }
                        i4 = i2;
                        NumberPickerView.this.postInvalidate();
                    } else {
                        NumberPickerView.this.onScrollStateChange(0);
                        NumberPickerView numberPickerView3 = NumberPickerView.this;
                        i = numberPickerView3.getWillPickIndexByGlobalY(numberPickerView3.mCurrDrawGlobalY);
                    }
                    NumberPickerView numberPickerView4 = NumberPickerView.this;
                    Message access$300 = numberPickerView4.getMsg(2, numberPickerView4.mPrevPickedIndex, i, message.obj);
                    if (NumberPickerView.this.mRespondChangeInMainThread) {
                        NumberPickerView.this.mHandlerInMainThread.sendMessageDelayed(access$300, (long) (i4 * 2));
                    } else {
                        NumberPickerView.this.mHandlerInNewThread.sendMessageDelayed(access$300, (long) (i4 * 2));
                    }
                } else if (i3 == 2) {
                    NumberPickerView.this.respondPickedValueChanged(message.arg1, message.arg2, message.obj);
                }
            }
        };
        this.mHandlerInMainThread = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 2) {
                    NumberPickerView.this.respondPickedValueChanged(message.arg1, message.arg2, message.obj);
                } else if (i == 3) {
                    NumberPickerView.this.requestLayout();
                }
            }
        };
    }

    private void respondPickedValueChangedInScrolling(int i, int i2) {
        this.mOnValueChangeListenerInScrolling.onValueChangeInScrolling(this, i, i2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        updateMaxWHOfDisplayedValues(false);
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        super.onSizeChanged(i, i2, i3, i4);
        this.mViewWidth = i;
        this.mViewHeight = i2;
        this.mItemHeight = i2 / this.mShowCount;
        this.mViewCenterX = ((float) ((i + getPaddingLeft()) - getPaddingRight())) / 2.0f;
        boolean z = false;
        if (getOneRecycleSize() > 1) {
            if (this.mHasInit) {
                i5 = getValue() - this.mMinValue;
            } else if (this.mCurrentItemIndexEffect) {
                i5 = this.mCurrDrawFirstItemIndex + ((this.mShowCount - 1) / 2);
            }
            if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
                z = true;
            }
            correctPositionByDefaultValue(i5, z);
            updateFontAttr();
            updateNotWrapYLimit();
            updateDividerAttr();
            this.mHasInit = true;
        }
        i5 = 0;
        z = true;
        correctPositionByDefaultValue(i5, z);
        updateFontAttr();
        updateNotWrapYLimit();
        updateDividerAttr();
        this.mHasInit = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread == null || !handlerThread.isAlive()) {
            initHandler();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandlerThread.quit();
        if (this.mItemHeight != 0) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                this.mCurrDrawGlobalY = this.mScroller.getCurrY();
                calculateFirstItemParameterByGlobalY();
                int i = this.mCurrDrawFirstItemY;
                if (i != 0) {
                    int i2 = this.mItemHeight;
                    if (i < (-i2) / 2) {
                        this.mCurrDrawGlobalY = this.mCurrDrawGlobalY + i2 + i;
                    } else {
                        this.mCurrDrawGlobalY += i;
                    }
                    calculateFirstItemParameterByGlobalY();
                }
                onScrollStateChange(0);
            }
            int willPickIndexByGlobalY = getWillPickIndexByGlobalY(this.mCurrDrawGlobalY);
            int i3 = this.mPrevPickedIndex;
            if (willPickIndexByGlobalY != i3 && this.mRespondChangeOnDetach) {
                try {
                    OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
                    if (onValueChangeListener != null) {
                        int i4 = this.mMinValue;
                        onValueChangeListener.onValueChange(this, i3 + i4, i4 + willPickIndexByGlobalY);
                    }
                    OnValueChangeListenerRelativeToRaw onValueChangeListenerRelativeToRaw = this.mOnValueChangeListenerRaw;
                    if (onValueChangeListenerRelativeToRaw != null) {
                        onValueChangeListenerRelativeToRaw.onValueChangeRelativeToRaw(this, this.mPrevPickedIndex, willPickIndexByGlobalY, this.mDisplayedValues);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mPrevPickedIndex = willPickIndexByGlobalY;
        }
    }

    public int getOneRecycleSize() {
        return (this.mMaxShowIndex - this.mMinShowIndex) + 1;
    }

    public int getRawContentSize() {
        String[] strArr = this.mDisplayedValues;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    public void setDisplayedValuesAndPickedIndex(String[] strArr, int i, boolean z) {
        stopScrolling();
        if (strArr == null) {
            throw new IllegalArgumentException("newDisplayedValues should not be null.");
        } else if (i >= 0) {
            updateContent(strArr);
            updateMaxWHOfDisplayedValues(true);
            updateNotWrapYLimit();
            updateValue();
            this.mPrevPickedIndex = this.mMinShowIndex + i;
            correctPositionByDefaultValue(i, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            if (z) {
                this.mHandlerInNewThread.sendMessageDelayed(getMsg(1), 0);
                postInvalidate();
            }
        } else {
            throw new IllegalArgumentException("pickedIndex should not be negative, now pickedIndex is " + i);
        }
    }

    public void setDisplayedValues(String[] strArr, boolean z) {
        setDisplayedValuesAndPickedIndex(strArr, 0, z);
    }

    public void setDisplayedValues(String[] strArr) {
        stopRefreshing();
        stopScrolling();
        if (strArr != null) {
            boolean z = true;
            if ((this.mMaxValue - this.mMinValue) + 1 <= strArr.length) {
                updateContent(strArr);
                updateMaxWHOfDisplayedValues(true);
                this.mPrevPickedIndex = this.mMinShowIndex;
                if (!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) {
                    z = false;
                }
                correctPositionByDefaultValue(0, z);
                postInvalidate();
                this.mHandlerInMainThread.sendEmptyMessage(3);
                return;
            }
            throw new IllegalArgumentException("mMaxValue - mMinValue + 1 should not be greater than mDisplayedValues.length, now ((mMaxValue - mMinValue + 1) is " + ((this.mMaxValue - this.mMinValue) + 1) + " newDisplayedValues.length is " + strArr.length + ", you need to set MaxValue and MinValue before setDisplayedValues(String[])");
        }
        throw new IllegalArgumentException("newDisplayedValues should not be null.");
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public void setWrapSelectorWheel(boolean z) {
        if (this.mWrapSelectorWheel == z) {
            return;
        }
        if (z) {
            this.mWrapSelectorWheel = z;
            updateWrapStateByContent();
            postInvalidate();
        } else if (this.mScrollState == 0) {
            internalSetWrapToLinear();
        } else {
            this.mPendingWrapToLinear = true;
        }
    }

    public void smoothScrollToValue(int i) {
        smoothScrollToValue(getValue(), i, true);
    }

    public void smoothScrollToValue(int i, boolean z) {
        smoothScrollToValue(getValue(), i, z);
    }

    public void smoothScrollToValue(int i, int i2) {
        smoothScrollToValue(i, i2, true);
    }

    public void smoothScrollToValue(int i, int i2, boolean z) {
        int i3;
        boolean z2 = true;
        int refineValueByLimit = refineValueByLimit(i, this.mMinValue, this.mMaxValue, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        int i4 = this.mMinValue;
        int i5 = this.mMaxValue;
        if (!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) {
            z2 = false;
        }
        int refineValueByLimit2 = refineValueByLimit(i2, i4, i5, z2);
        if (!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) {
            i3 = refineValueByLimit2 - refineValueByLimit;
        } else {
            i3 = refineValueByLimit2 - refineValueByLimit;
            int oneRecycleSize = getOneRecycleSize() / 2;
            if (i3 < (-oneRecycleSize) || oneRecycleSize < i3) {
                int oneRecycleSize2 = getOneRecycleSize();
                i3 = i3 > 0 ? i3 - oneRecycleSize2 : i3 + oneRecycleSize2;
            }
        }
        setValue(refineValueByLimit);
        if (refineValueByLimit != refineValueByLimit2) {
            scrollByIndexSmoothly(i3, z);
        }
    }

    public void refreshByNewDisplayedValues(String[] strArr) {
        int minValue = getMinValue();
        int maxValue = (getMaxValue() - minValue) + 1;
        int length = strArr.length - 1;
        if ((length - minValue) + 1 > maxValue) {
            setDisplayedValues(strArr);
            setMaxValue(length);
            return;
        }
        setMaxValue(length);
        setDisplayedValues(strArr);
    }

    /* access modifiers changed from: private */
    public void respondPickedValueChanged(int i, int i2, Object obj) {
        onScrollStateChange(0);
        if (i != i2 && (obj == null || !(obj instanceof Boolean) || ((Boolean) obj).booleanValue())) {
            OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
            if (onValueChangeListener != null) {
                int i3 = this.mMinValue;
                onValueChangeListener.onValueChange(this, i + i3, i3 + i2);
            }
            OnValueChangeListenerRelativeToRaw onValueChangeListenerRelativeToRaw = this.mOnValueChangeListenerRaw;
            if (onValueChangeListenerRelativeToRaw != null) {
                onValueChangeListenerRelativeToRaw.onValueChangeRelativeToRaw(this, i, i2, this.mDisplayedValues);
            }
        }
        this.mPrevPickedIndex = i2;
        if (this.mPendingWrapToLinear) {
            this.mPendingWrapToLinear = false;
            internalSetWrapToLinear();
        }
    }

    private void scrollByIndexSmoothly(int i) {
        scrollByIndexSmoothly(i, true);
    }

    private void scrollByIndexSmoothly(int i, boolean z) {
        int i2;
        int pickedIndexRelativeToRaw;
        int i3;
        int i4;
        if ((!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) && ((i3 = pickedIndexRelativeToRaw + i) > (i4 = this.mMaxShowIndex) || i3 < (i4 = this.mMinShowIndex))) {
            i = i4 - (pickedIndexRelativeToRaw = getPickedIndexRelativeToRaw());
        }
        int i5 = this.mCurrDrawFirstItemY;
        int i6 = this.mItemHeight;
        if (i5 < (-i6) / 2) {
            int i7 = i6 + i5;
            int i8 = (int) ((((float) (i5 + i6)) * 300.0f) / ((float) i6));
            int i9 = i7;
            i2 = i < 0 ? (-i8) - (i * MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION) : i8 + (i * MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION);
            i5 = i9;
        } else {
            int i10 = (int) ((((float) (-i5)) * 300.0f) / ((float) i6));
            i2 = i < 0 ? i10 - (i * MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION) : i10 + (i * MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION);
        }
        int i11 = i5 + (i * i6);
        if (i2 < 300) {
            i2 = 300;
        }
        if (i2 > 600) {
            i2 = 600;
        }
        this.mScroller.startScroll(0, this.mCurrDrawGlobalY, 0, i11, i2);
        if (z) {
            this.mHandlerInNewThread.sendMessageDelayed(getMsg(1), (long) (i2 / 4));
        } else {
            this.mHandlerInNewThread.sendMessageDelayed(getMsg(1, 0, 0, new Boolean(z)), (long) (i2 / 4));
        }
        postInvalidate();
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public void setMinValue(int i) {
        this.mMinValue = i;
        this.mMinShowIndex = 0;
        updateNotWrapYLimit();
    }

    public void setMaxValue(int i) {
        String[] strArr = this.mDisplayedValues;
        if (strArr != null) {
            int i2 = this.mMinValue;
            if ((i - i2) + 1 <= strArr.length) {
                this.mMaxValue = i;
                int i3 = this.mMinShowIndex;
                int i4 = (i - i2) + i3;
                this.mMaxShowIndex = i4;
                setMinAndMaxShowIndex(i3, i4);
                updateNotWrapYLimit();
                return;
            }
            throw new IllegalArgumentException("(maxValue - mMinValue + 1) should not be greater than mDisplayedValues.length now  (maxValue - mMinValue + 1) is " + ((i - this.mMinValue) + 1) + " and mDisplayedValues.length is " + this.mDisplayedValues.length);
        }
        throw new NullPointerException("mDisplayedValues should not be null");
    }

    public void setValue(int i) {
        int i2 = this.mMinValue;
        if (i < i2) {
            throw new IllegalArgumentException("should not set a value less than mMinValue, value is " + i);
        } else if (i <= this.mMaxValue) {
            setPickedIndexRelativeToRaw(i - i2);
        } else {
            throw new IllegalArgumentException("should not set a value greater than mMaxValue, value is " + i);
        }
    }

    public int getValue() {
        return getPickedIndexRelativeToRaw() + this.mMinValue;
    }

    public String getContentByCurrValue() {
        return this.mDisplayedValues[getValue() - this.mMinValue];
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public boolean getWrapSelectorWheelAbsolutely() {
        return this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck;
    }

    public void setHintText(String str) {
        if (!isStringEqual(this.mHintText, str)) {
            this.mHintText = str;
            this.mTextSizeHintCenterYOffset = getTextCenterYOffset(this.mPaintHint.getFontMetrics());
            this.mWidthOfHintText = getTextWidth(this.mHintText, this.mPaintHint);
            this.mHandlerInMainThread.sendEmptyMessage(3);
        }
    }

    public void setPickedIndexRelativeToMin(int i) {
        if (i >= 0 && i < getOneRecycleSize()) {
            this.mPrevPickedIndex = this.mMinShowIndex + i;
            correctPositionByDefaultValue(i, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            postInvalidate();
        }
    }

    public void setNormalTextColor(int i) {
        if (this.mTextColorNormal != i) {
            this.mTextColorNormal = i;
            postInvalidate();
        }
    }

    public void setSelectedTextColor(int i) {
        if (this.mTextColorSelected != i) {
            this.mTextColorSelected = i;
            postInvalidate();
        }
    }

    public void setHintTextColor(int i) {
        if (this.mTextColorHint != i) {
            this.mTextColorHint = i;
            this.mPaintHint.setColor(i);
            postInvalidate();
        }
    }

    public void setDividerColor(int i) {
        if (this.mDividerColor != i) {
            this.mDividerColor = i;
            this.mPaintDivider.setColor(i);
            postInvalidate();
        }
    }

    public void setPickedIndexRelativeToRaw(int i) {
        int i2 = this.mMinShowIndex;
        if (i2 > -1 && i2 <= i && i <= this.mMaxShowIndex) {
            this.mPrevPickedIndex = i;
            correctPositionByDefaultValue(i - i2, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            postInvalidate();
        }
    }

    public int getPickedIndexRelativeToRaw() {
        int i = this.mCurrDrawFirstItemY;
        if (i == 0) {
            return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY);
        }
        int i2 = this.mItemHeight;
        if (i < (-i2) / 2) {
            return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY + i2 + i);
        }
        return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY + i);
    }

    public void setMinAndMaxShowIndex(int i, int i2) {
        setMinAndMaxShowIndex(i, i2, true);
    }

    public void setMinAndMaxShowIndex(int i, int i2, boolean z) {
        if (i <= i2) {
            String[] strArr = this.mDisplayedValues;
            if (strArr == null) {
                throw new IllegalArgumentException("mDisplayedValues should not be null, you need to set mDisplayedValues first.");
            } else if (i >= 0) {
                boolean z2 = true;
                if (i > strArr.length - 1) {
                    throw new IllegalArgumentException("minShowIndex should not be greater than (mDisplayedValues.length - 1), now (mDisplayedValues.length - 1) is " + (this.mDisplayedValues.length - 1) + " minShowIndex is " + i);
                } else if (i2 < 0) {
                    throw new IllegalArgumentException("maxShowIndex should not be less than 0, now maxShowIndex is " + i2);
                } else if (i2 <= strArr.length - 1) {
                    this.mMinShowIndex = i;
                    this.mMaxShowIndex = i2;
                    if (z) {
                        this.mPrevPickedIndex = i;
                        if (!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) {
                            z2 = false;
                        }
                        correctPositionByDefaultValue(0, z2);
                        postInvalidate();
                    }
                } else {
                    throw new IllegalArgumentException("maxShowIndex should not be greater than (mDisplayedValues.length - 1), now (mDisplayedValues.length - 1) is " + (this.mDisplayedValues.length - 1) + " maxShowIndex is " + i2);
                }
            } else {
                throw new IllegalArgumentException("minShowIndex should not be less than 0, now minShowIndex is " + i);
            }
        } else {
            throw new IllegalArgumentException("minShowIndex should be less than maxShowIndex, minShowIndex is " + i + ", maxShowIndex is " + i2 + Consts.DOT);
        }
    }

    public void setFriction(float f) {
        if (f > 0.0f) {
            ViewConfiguration.get(getContext());
            this.mFriction = ViewConfiguration.getScrollFriction() / f;
            return;
        }
        throw new IllegalArgumentException("you should set a a positive float friction, now friction is " + f);
    }

    /* access modifiers changed from: private */
    public void onScrollStateChange(int i) {
        if (this.mScrollState != i) {
            this.mScrollState = i;
            OnScrollListener onScrollListener = this.mOnScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChange(this, i);
            }
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setOnValueChangedListenerRelativeToRaw(OnValueChangeListenerRelativeToRaw onValueChangeListenerRelativeToRaw) {
        this.mOnValueChangeListenerRaw = onValueChangeListenerRelativeToRaw;
    }

    public void setOnValueChangeListenerInScrolling(OnValueChangeListenerInScrolling onValueChangeListenerInScrolling) {
        this.mOnValueChangeListenerInScrolling = onValueChangeListenerInScrolling;
    }

    public void setContentTextTypeface(Typeface typeface) {
        this.mPaintText.setTypeface(typeface);
    }

    public void setHintTextTypeface(Typeface typeface) {
        this.mPaintHint.setTypeface(typeface);
    }

    /* access modifiers changed from: private */
    public int getWillPickIndexByGlobalY(int i) {
        int i2 = this.mItemHeight;
        boolean z = false;
        if (i2 == 0) {
            return 0;
        }
        int i3 = (i / i2) + (this.mShowCount / 2);
        int oneRecycleSize = getOneRecycleSize();
        if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
            z = true;
        }
        int indexByRawIndex = getIndexByRawIndex(i3, oneRecycleSize, z);
        if (indexByRawIndex >= 0 && indexByRawIndex < getOneRecycleSize()) {
            return indexByRawIndex + this.mMinShowIndex;
        }
        throw new IllegalArgumentException("getWillPickIndexByGlobalY illegal index : " + indexByRawIndex + " getOneRecycleSize() : " + getOneRecycleSize() + " mWrapSelectorWheel : " + this.mWrapSelectorWheel);
    }

    private int getIndexByRawIndex(int i, int i2, boolean z) {
        if (i2 <= 0) {
            return 0;
        }
        if (!z) {
            return i;
        }
        int i3 = i % i2;
        return i3 < 0 ? i3 + i2 : i3;
    }

    private void internalSetWrapToLinear() {
        correctPositionByDefaultValue(getPickedIndexRelativeToRaw() - this.mMinShowIndex, false);
        this.mWrapSelectorWheel = false;
        postInvalidate();
    }

    private void updateDividerAttr() {
        int i = this.mShowCount;
        int i2 = i / 2;
        this.mDividerIndex0 = i2;
        int i3 = i2 + 1;
        this.mDividerIndex1 = i3;
        int i4 = this.mViewHeight;
        this.dividerY0 = (float) ((i2 * i4) / i);
        this.dividerY1 = (float) ((i3 * i4) / i);
        if (this.mDividerMarginL < 0) {
            this.mDividerMarginL = 0;
        }
        if (this.mDividerMarginR < 0) {
            this.mDividerMarginR = 0;
        }
        if (this.mDividerMarginL + this.mDividerMarginR != 0 && getPaddingLeft() + this.mDividerMarginL >= (this.mViewWidth - getPaddingRight()) - this.mDividerMarginR) {
            int paddingLeft = getPaddingLeft() + this.mDividerMarginL + getPaddingRight();
            int i5 = this.mDividerMarginR;
            int i6 = (paddingLeft + i5) - this.mViewWidth;
            int i7 = this.mDividerMarginL;
            float f = (float) i6;
            int i8 = (int) (((float) i7) - ((((float) i7) * f) / ((float) (i7 + i5))));
            this.mDividerMarginL = i8;
            this.mDividerMarginR = (int) (((float) i5) - ((f * ((float) i5)) / ((float) (i8 + i5))));
        }
    }

    private void updateFontAttr() {
        int i = this.mTextSizeNormal;
        int i2 = this.mItemHeight;
        if (i > i2) {
            this.mTextSizeNormal = i2;
        }
        if (this.mTextSizeSelected > i2) {
            this.mTextSizeSelected = i2;
        }
        Paint paint = this.mPaintHint;
        if (paint != null) {
            paint.setTextSize((float) this.mTextSizeHint);
            this.mTextSizeHintCenterYOffset = getTextCenterYOffset(this.mPaintHint.getFontMetrics());
            this.mWidthOfHintText = getTextWidth(this.mHintText, this.mPaintHint);
            TextPaint textPaint = this.mPaintText;
            if (textPaint != null) {
                textPaint.setTextSize((float) this.mTextSizeSelected);
                this.mTextSizeSelectedCenterYOffset = getTextCenterYOffset(this.mPaintText.getFontMetrics());
                this.mPaintText.setTextSize((float) this.mTextSizeNormal);
                this.mTextSizeNormalCenterYOffset = getTextCenterYOffset(this.mPaintText.getFontMetrics());
                return;
            }
            throw new IllegalArgumentException("mPaintText should not be null.");
        }
        throw new IllegalArgumentException("mPaintHint should not be null.");
    }

    private void updateNotWrapYLimit() {
        this.mNotWrapLimitYTop = 0;
        this.mNotWrapLimitYBottom = (-this.mShowCount) * this.mItemHeight;
        if (this.mDisplayedValues != null) {
            int oneRecycleSize = getOneRecycleSize();
            int i = this.mShowCount;
            int i2 = this.mItemHeight;
            this.mNotWrapLimitYTop = ((oneRecycleSize - (i / 2)) - 1) * i2;
            this.mNotWrapLimitYBottom = (-(i / 2)) * i2;
        }
    }

    private int limitY(int i) {
        if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
            return i;
        }
        int i2 = this.mNotWrapLimitYBottom;
        if (i >= i2 && i <= (i2 = this.mNotWrapLimitYTop)) {
            return i;
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        if (r1 < ((float) r3)) goto L_0x006e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            int r1 = r0.mItemHeight
            r2 = 1
            if (r1 != 0) goto L_0x0008
            return r2
        L_0x0008:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            if (r1 != 0) goto L_0x0012
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()
            r0.mVelocityTracker = r1
        L_0x0012:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r3 = r18
            r1.addMovement(r3)
            float r1 = r18.getY()
            r0.currY = r1
            int r1 = r18.getAction()
            r4 = 0
            if (r1 == 0) goto L_0x00c0
            r5 = 0
            r7 = 2
            if (r1 == r2) goto L_0x0072
            if (r1 == r7) goto L_0x0045
            r3 = 3
            if (r1 == r3) goto L_0x0032
            goto L_0x00dd
        L_0x0032:
            int r1 = r0.mCurrDrawGlobalY
            float r1 = (float) r1
            r0.downYGlobal = r1
            r17.stopScrolling()
            android.os.Handler r1 = r0.mHandlerInNewThread
            android.os.Message r3 = r0.getMsg(r2)
            r1.sendMessageDelayed(r3, r5)
            goto L_0x00dd
        L_0x0045:
            float r1 = r0.downY
            float r3 = r0.currY
            float r1 = r1 - r3
            boolean r3 = r0.mFlagMayPress
            if (r3 == 0) goto L_0x005c
            int r3 = r0.mScaledTouchSlop
            int r5 = -r3
            float r5 = (float) r5
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 >= 0) goto L_0x005c
            float r3 = (float) r3
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x005c
            goto L_0x006e
        L_0x005c:
            r0.mFlagMayPress = r4
            float r3 = r0.downYGlobal
            float r3 = r3 + r1
            int r1 = (int) r3
            int r1 = r0.limitY(r1)
            r0.mCurrDrawGlobalY = r1
            r17.calculateFirstItemParameterByGlobalY()
            r17.invalidate()
        L_0x006e:
            r0.onScrollStateChange(r2)
            goto L_0x00dd
        L_0x0072:
            boolean r1 = r0.mFlagMayPress
            if (r1 == 0) goto L_0x007a
            r17.click(r18)
            goto L_0x00dd
        L_0x007a:
            android.view.VelocityTracker r1 = r0.mVelocityTracker
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.computeCurrentVelocity(r3)
            float r1 = r1.getYVelocity()
            float r3 = r0.mFriction
            float r1 = r1 * r3
            int r1 = (int) r1
            int r3 = java.lang.Math.abs(r1)
            int r4 = r0.mMiniVelocityFling
            if (r3 <= r4) goto L_0x00b3
            androidx.core.widget.ScrollerCompat r8 = r0.mScroller
            int r10 = r0.mCurrDrawGlobalY
            int r12 = -r1
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            int r15 = r0.limitY(r1)
            r1 = 2147483647(0x7fffffff, float:NaN)
            int r16 = r0.limitY(r1)
            r9 = 0
            r11 = 0
            r13 = -2147483648(0xffffffff80000000, float:-0.0)
            r14 = 2147483647(0x7fffffff, float:NaN)
            r8.fling(r9, r10, r11, r12, r13, r14, r15, r16)
            r17.invalidate()
            r0.onScrollStateChange(r7)
        L_0x00b3:
            android.os.Handler r1 = r0.mHandlerInNewThread
            android.os.Message r3 = r0.getMsg(r2)
            r1.sendMessageDelayed(r3, r5)
            r17.releaseVelocityTracker()
            goto L_0x00dd
        L_0x00c0:
            r0.mFlagMayPress = r2
            android.os.Handler r1 = r0.mHandlerInNewThread
            r1.removeMessages(r2)
            r17.stopScrolling()
            float r1 = r0.currY
            r0.downY = r1
            int r1 = r0.mCurrDrawGlobalY
            float r1 = (float) r1
            r0.downYGlobal = r1
            r0.onScrollStateChange(r4)
            android.view.ViewParent r1 = r17.getParent()
            r1.requestDisallowInterceptTouchEvent(r2)
        L_0x00dd:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.carbswang.android.numberpickerview.library.NumberPickerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void click(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int i = 0;
        while (i < this.mShowCount) {
            int i2 = this.mItemHeight;
            if (((float) (i2 * i)) > y || y >= ((float) (i2 * (i + 1)))) {
                i++;
            } else {
                clickItem(i);
                return;
            }
        }
    }

    private void clickItem(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.mShowCount)) {
            scrollByIndexSmoothly(i - (i2 / 2));
        }
    }

    private float getTextCenterYOffset(Paint.FontMetrics fontMetrics) {
        if (fontMetrics == null) {
            return 0.0f;
        }
        return Math.abs(fontMetrics.top + fontMetrics.bottom) / 2.0f;
    }

    private void correctPositionByDefaultValue(int i, boolean z) {
        int i2 = i - ((this.mShowCount - 1) / 2);
        this.mCurrDrawFirstItemIndex = i2;
        int indexByRawIndex = getIndexByRawIndex(i2, getOneRecycleSize(), z);
        this.mCurrDrawFirstItemIndex = indexByRawIndex;
        int i3 = this.mItemHeight;
        if (i3 == 0) {
            this.mCurrentItemIndexEffect = true;
            return;
        }
        this.mCurrDrawGlobalY = i3 * indexByRawIndex;
        int i4 = indexByRawIndex + (this.mShowCount / 2);
        this.mInScrollingPickedOldValue = i4;
        int oneRecycleSize = i4 % getOneRecycleSize();
        this.mInScrollingPickedOldValue = oneRecycleSize;
        if (oneRecycleSize < 0) {
            this.mInScrollingPickedOldValue = oneRecycleSize + getOneRecycleSize();
        }
        this.mInScrollingPickedNewValue = this.mInScrollingPickedOldValue;
        calculateFirstItemParameterByGlobalY();
    }

    public void computeScroll() {
        if (this.mItemHeight != 0 && this.mScroller.computeScrollOffset()) {
            this.mCurrDrawGlobalY = this.mScroller.getCurrY();
            calculateFirstItemParameterByGlobalY();
            postInvalidate();
        }
    }

    private void calculateFirstItemParameterByGlobalY() {
        int floor = (int) Math.floor((double) (((float) this.mCurrDrawGlobalY) / ((float) this.mItemHeight)));
        this.mCurrDrawFirstItemIndex = floor;
        int i = this.mCurrDrawGlobalY;
        int i2 = this.mItemHeight;
        int i3 = -(i - (floor * i2));
        this.mCurrDrawFirstItemY = i3;
        if (this.mOnValueChangeListenerInScrolling != null) {
            if ((-i3) > i2 / 2) {
                this.mInScrollingPickedNewValue = floor + 1 + (this.mShowCount / 2);
            } else {
                this.mInScrollingPickedNewValue = floor + (this.mShowCount / 2);
            }
            int oneRecycleSize = this.mInScrollingPickedNewValue % getOneRecycleSize();
            this.mInScrollingPickedNewValue = oneRecycleSize;
            if (oneRecycleSize < 0) {
                this.mInScrollingPickedNewValue = oneRecycleSize + getOneRecycleSize();
            }
            int i4 = this.mInScrollingPickedOldValue;
            int i5 = this.mInScrollingPickedNewValue;
            if (i4 != i5) {
                respondPickedValueChangedInScrolling(i5, i4);
            }
            this.mInScrollingPickedOldValue = this.mInScrollingPickedNewValue;
        }
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void updateMaxWHOfDisplayedValues(boolean z) {
        updateMaxWidthOfDisplayedValues();
        updateMaxHeightOfDisplayedValues();
        if (!z) {
            return;
        }
        if (this.mSpecModeW == Integer.MIN_VALUE || this.mSpecModeH == Integer.MIN_VALUE) {
            this.mHandlerInMainThread.sendEmptyMessage(3);
        }
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        this.mSpecModeW = mode;
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int i2 = 0;
        int i3 = Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) == 0 ? 0 : this.mMarginEndOfHint;
        if (Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) != 0) {
            i2 = this.mMarginStartOfHint;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight() + Math.max(this.mMaxWidthOfAlterArrayWithMeasureHint, Math.max(this.mMaxWidthOfDisplayedValues, this.mMaxWidthOfAlterArrayWithoutMeasureHint) + ((i2 + Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) + i3 + (this.mItemPaddingHorizontal * 2)) * 2));
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        this.mSpecModeH = mode;
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom() + (this.mShowCount * (this.mMaxHeightOfDisplayedValues + (this.mItemPaddingVertical * 2)));
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawContent(canvas);
        drawLine(canvas);
        drawHint(canvas);
    }

    private void drawContent(Canvas canvas) {
        float f;
        float f2;
        float f3;
        int i;
        float f4 = 0.0f;
        int i2 = 0;
        while (i2 < this.mShowCount + 1) {
            float f5 = (float) (this.mCurrDrawFirstItemY + (this.mItemHeight * i2));
            int indexByRawIndex = getIndexByRawIndex(this.mCurrDrawFirstItemIndex + i2, getOneRecycleSize(), this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            int i3 = this.mShowCount;
            if (i2 == i3 / 2) {
                int i4 = this.mItemHeight;
                f3 = ((float) (this.mCurrDrawFirstItemY + i4)) / ((float) i4);
                i = getEvaluateColor(f3, this.mTextColorNormal, this.mTextColorSelected);
                f2 = getEvaluateSize(f3, (float) this.mTextSizeNormal, (float) this.mTextSizeSelected);
                f = getEvaluateSize(f3, this.mTextSizeNormalCenterYOffset, this.mTextSizeSelectedCenterYOffset);
            } else if (i2 == (i3 / 2) + 1) {
                float f6 = 1.0f - f4;
                int evaluateColor = getEvaluateColor(f6, this.mTextColorNormal, this.mTextColorSelected);
                float evaluateSize = getEvaluateSize(f6, (float) this.mTextSizeNormal, (float) this.mTextSizeSelected);
                float evaluateSize2 = getEvaluateSize(f6, this.mTextSizeNormalCenterYOffset, this.mTextSizeSelectedCenterYOffset);
                f3 = f4;
                i = evaluateColor;
                f2 = evaluateSize;
                f = evaluateSize2;
            } else {
                int i5 = this.mTextColorNormal;
                f2 = (float) this.mTextSizeNormal;
                f = this.mTextSizeNormalCenterYOffset;
                int i6 = i5;
                f3 = f4;
                i = i6;
            }
            this.mPaintText.setColor(i);
            this.mPaintText.setTextSize(f2);
            if (indexByRawIndex >= 0 && indexByRawIndex < getOneRecycleSize()) {
                CharSequence charSequence = this.mDisplayedValues[indexByRawIndex + this.mMinShowIndex];
                if (this.mTextEllipsize != null) {
                    charSequence = TextUtils.ellipsize(charSequence, this.mPaintText, (float) (getWidth() - (this.mItemPaddingHorizontal * 2)), getEllipsizeType());
                }
                canvas.drawText(charSequence.toString(), this.mViewCenterX, f5 + ((float) (this.mItemHeight / 2)) + f, this.mPaintText);
            } else if (!TextUtils.isEmpty(this.mEmptyItemHint)) {
                canvas.drawText(this.mEmptyItemHint, this.mViewCenterX, f5 + ((float) (this.mItemHeight / 2)) + f, this.mPaintText);
            }
            i2++;
            f4 = f3;
        }
    }

    private TextUtils.TruncateAt getEllipsizeType() {
        String str = this.mTextEllipsize;
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1074341483:
                if (str.equals(TEXT_ELLIPSIZE_MIDDLE)) {
                    c2 = 0;
                    break;
                }
                break;
            case 100571:
                if (str.equals("end")) {
                    c2 = 1;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return TextUtils.TruncateAt.MIDDLE;
            case 1:
                return TextUtils.TruncateAt.END;
            case 2:
                return TextUtils.TruncateAt.START;
            default:
                throw new IllegalArgumentException("Illegal text ellipsize type.");
        }
    }

    private void drawLine(Canvas canvas) {
        if (this.mShowDivider) {
            canvas.drawLine((float) (getPaddingLeft() + this.mDividerMarginL), this.dividerY0, (float) ((this.mViewWidth - getPaddingRight()) - this.mDividerMarginR), this.dividerY0, this.mPaintDivider);
            canvas.drawLine((float) (getPaddingLeft() + this.mDividerMarginL), this.dividerY1, (float) ((this.mViewWidth - getPaddingRight()) - this.mDividerMarginR), this.dividerY1, this.mPaintDivider);
        }
    }

    private void drawHint(Canvas canvas) {
        if (!TextUtils.isEmpty(this.mHintText)) {
            canvas.drawText(this.mHintText, this.mViewCenterX + ((float) ((this.mMaxWidthOfDisplayedValues + this.mWidthOfHintText) / 2)) + ((float) this.mMarginStartOfHint), ((this.dividerY0 + this.dividerY1) / 2.0f) + this.mTextSizeHintCenterYOffset, this.mPaintHint);
        }
    }

    private void updateMaxWidthOfDisplayedValues() {
        float textSize = this.mPaintText.getTextSize();
        this.mPaintText.setTextSize((float) this.mTextSizeSelected);
        this.mMaxWidthOfDisplayedValues = getMaxWidthOfTextArray(this.mDisplayedValues, this.mPaintText);
        this.mMaxWidthOfAlterArrayWithMeasureHint = getMaxWidthOfTextArray(this.mAlterTextArrayWithMeasureHint, this.mPaintText);
        this.mMaxWidthOfAlterArrayWithoutMeasureHint = getMaxWidthOfTextArray(this.mAlterTextArrayWithoutMeasureHint, this.mPaintText);
        this.mPaintText.setTextSize((float) this.mTextSizeHint);
        this.mWidthOfAlterHint = getTextWidth(this.mAlterHint, this.mPaintText);
        this.mPaintText.setTextSize(textSize);
    }

    private int getMaxWidthOfTextArray(CharSequence[] charSequenceArr, Paint paint) {
        if (charSequenceArr == null) {
            return 0;
        }
        int i = 0;
        for (CharSequence charSequence : charSequenceArr) {
            if (charSequence != null) {
                i = Math.max(getTextWidth(charSequence, paint), i);
            }
        }
        return i;
    }

    private int getTextWidth(CharSequence charSequence, Paint paint) {
        if (!TextUtils.isEmpty(charSequence)) {
            return (int) (paint.measureText(charSequence.toString()) + 0.5f);
        }
        return 0;
    }

    private void updateMaxHeightOfDisplayedValues() {
        float textSize = this.mPaintText.getTextSize();
        this.mPaintText.setTextSize((float) this.mTextSizeSelected);
        this.mMaxHeightOfDisplayedValues = (int) (((double) (this.mPaintText.getFontMetrics().bottom - this.mPaintText.getFontMetrics().top)) + 0.5d);
        this.mPaintText.setTextSize(textSize);
    }

    private void updateContentAndIndex(String[] strArr) {
        this.mMinShowIndex = 0;
        this.mMaxShowIndex = strArr.length - 1;
        this.mDisplayedValues = strArr;
        updateWrapStateByContent();
    }

    private void updateContent(String[] strArr) {
        this.mDisplayedValues = strArr;
        updateWrapStateByContent();
    }

    private void updateValue() {
        inflateDisplayedValuesIfNull();
        updateWrapStateByContent();
        this.mMinShowIndex = 0;
        this.mMaxShowIndex = this.mDisplayedValues.length - 1;
    }

    private void updateValueForInit() {
        inflateDisplayedValuesIfNull();
        updateWrapStateByContent();
        if (this.mMinShowIndex == -1) {
            this.mMinShowIndex = 0;
        }
        if (this.mMaxShowIndex == -1) {
            this.mMaxShowIndex = this.mDisplayedValues.length - 1;
        }
        setMinAndMaxShowIndex(this.mMinShowIndex, this.mMaxShowIndex, false);
    }

    private void inflateDisplayedValuesIfNull() {
        if (this.mDisplayedValues == null) {
            String[] strArr = new String[1];
            this.mDisplayedValues = strArr;
            strArr[0] = SessionDescription.SUPPORTED_SDP_VERSION;
        }
    }

    private void updateWrapStateByContent() {
        this.mWrapSelectorWheelCheck = this.mDisplayedValues.length > this.mShowCount;
    }

    private int refineValueByLimit(int i, int i2, int i3, boolean z) {
        if (z) {
            if (i > i3) {
                return (((i - i3) % getOneRecycleSize()) + i2) - 1;
            }
            return i < i2 ? ((i - i2) % getOneRecycleSize()) + i3 + 1 : i;
        } else if (i > i3) {
            return i3;
        } else {
            return i < i2 ? i2 : i;
        }
    }

    private void stopRefreshing() {
        Handler handler = this.mHandlerInNewThread;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    private void stopScrolling() {
        ScrollerCompat scrollerCompat = this.mScroller;
        if (scrollerCompat != null && !scrollerCompat.isFinished()) {
            ScrollerCompat scrollerCompat2 = this.mScroller;
            scrollerCompat2.startScroll(0, scrollerCompat2.getCurrY(), 0, 0, 1);
            this.mScroller.abortAnimation();
            postInvalidate();
        }
    }

    private Message getMsg(int i) {
        return getMsg(i, 0, 0, (Object) null);
    }

    /* access modifiers changed from: private */
    public Message getMsg(int i, int i2, int i3, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.arg2 = i3;
        obtain.obj = obj;
        return obtain;
    }

    private boolean isStringEqual(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private String[] convertCharSequenceArrayToStringArray(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            return null;
        }
        String[] strArr = new String[charSequenceArr.length];
        for (int i = 0; i < charSequenceArr.length; i++) {
            strArr[i] = charSequenceArr[i].toString();
        }
        return strArr;
    }
}

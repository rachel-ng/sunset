package com.mergbw.core.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.databinding.LayoutCommonColorPickerBinding;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;
import com.mergbw.core.ui.views.LineColorPickerView;
import com.mergbw.core.utils.ColorUtils;
import java.util.List;

public class CommonColorPickerView extends LinearLayout {
    private static final int FROM_CLASSIC = 3;
    private static final int FROM_COMMON = 4;
    private static final int FROM_LINE = 2;
    private static final int FROM_PIE = 1;
    /* access modifiers changed from: private */
    public int lastLineColor = 0;
    private LayoutCommonColorPickerBinding mBinding;
    private SingleColorItemAdapter mClassicAdapter;
    private SingleColorItemAdapter mCommonColorAdapter;
    private OnColorPickListener mListener;

    public interface OnColorPickListener {
        void onColorPick(int i);
    }

    public CommonColorPickerView(Context context) {
        super(context);
    }

    public CommonColorPickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutCommonColorPickerBinding inflate = LayoutCommonColorPickerBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        inflate.listClassicColor.setLayoutManager(new LinearLayoutManager(context, 0, false));
        SingleColorItemAdapter singleColorItemAdapter = new SingleColorItemAdapter();
        this.mClassicAdapter = singleColorItemAdapter;
        singleColorItemAdapter.setData(ColorUtils.getClassicColorList());
        this.mBinding.listClassicColor.setAdapter(this.mClassicAdapter);
        this.mClassicAdapter.setClickListener(new CommonColorPickerView$$ExternalSyntheticLambda0(this));
        this.mCommonColorAdapter = new SingleColorItemAdapter();
        this.mBinding.listCommonColor.setLayoutManager(new LinearLayoutManager(context, 0, false));
        this.mBinding.listCommonColor.setAdapter(this.mCommonColorAdapter);
        this.mCommonColorAdapter.setClickListener(new CommonColorPickerView$$ExternalSyntheticLambda1(this));
        this.mBinding.viewColorPie.subscribe(new CommonColorPickerView$$ExternalSyntheticLambda2(this));
        this.mBinding.viewColorLine.setOnColorPickerChangeListener(new LineColorPickerView.OnColorPickerChangeListener() {
            public void onStartTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onStopTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onColorChanged(LineColorPickerView lineColorPickerView, int i) {
                if (i != CommonColorPickerView.this.lastLineColor) {
                    int unused = CommonColorPickerView.this.lastLineColor = i;
                    CommonColorPickerView.this.setPickColor(2, i);
                }
            }
        });
        requestDisallowInterceptTouchEvent(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-core-ui-views-CommonColorPickerView  reason: not valid java name */
    public /* synthetic */ void m943lambda$initViews$0$commergbwcoreuiviewsCommonColorPickerView(ColorBean colorBean) {
        setPickColor(3, colorBean.getColorValue());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-core-ui-views-CommonColorPickerView  reason: not valid java name */
    public /* synthetic */ void m944lambda$initViews$1$commergbwcoreuiviewsCommonColorPickerView(ColorBean colorBean) {
        setPickColor(4, colorBean.getColorValue());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-core-ui-views-CommonColorPickerView  reason: not valid java name */
    public /* synthetic */ void m945lambda$initViews$2$commergbwcoreuiviewsCommonColorPickerView(int i, boolean z, boolean z2) {
        if (z) {
            setPickColor(1, i);
        }
    }

    public void setOnColorPickListener(OnColorPickListener onColorPickListener) {
        this.mListener = onColorPickListener;
    }

    public void setCommonColor(List<ColorBean> list) {
        LayoutCommonColorPickerBinding layoutCommonColorPickerBinding;
        if (list.size() > 0 && (layoutCommonColorPickerBinding = this.mBinding) != null) {
            layoutCommonColorPickerBinding.layoutCommonColor.setVisibility(0);
            this.mCommonColorAdapter.setData(list);
            this.mCommonColorAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void setPickColor(int i, int i2) {
        OnColorPickListener onColorPickListener = this.mListener;
        if (onColorPickListener != null) {
            onColorPickListener.onColorPick(i2);
        }
        if (i == 1) {
            this.mClassicAdapter.refreshSelected(i2);
            this.mCommonColorAdapter.refreshSelected(i2);
        } else if (i == 2) {
            this.mClassicAdapter.refreshSelected(i2);
            this.mCommonColorAdapter.refreshSelected(i2);
            this.mBinding.viewColorPie.setColor(i2);
        } else if (i == 3) {
            this.mCommonColorAdapter.refreshSelected(i2);
            this.mBinding.viewColorPie.setColor(i2);
        } else if (i == 4) {
            this.mClassicAdapter.refreshSelected(i2);
            this.mBinding.viewColorPie.setColor(i2);
        }
    }

    public void showColorPie(boolean z) {
        LayoutCommonColorPickerBinding layoutCommonColorPickerBinding = this.mBinding;
        if (layoutCommonColorPickerBinding != null) {
            layoutCommonColorPickerBinding.viewColorPie.setVisibility(z ? 0 : 8);
        }
    }
}

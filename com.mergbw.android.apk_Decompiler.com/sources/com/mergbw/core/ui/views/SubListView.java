package com.mergbw.core.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.databinding.LayoutSubListBinding;
import java.util.List;

public class SubListView extends LinearLayout {
    private LayoutSubListBinding mBinding;
    private OnSubItemCheckChangeListener mListener;

    public interface OnSubItemCheckChangeListener {
        void onSubItemCheck(int i, boolean z);
    }

    public SubListView(Context context) {
        super(context);
    }

    public SubListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutSubListBinding inflate = LayoutSubListBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        inflate.viewSub01.setType(1);
        this.mBinding.viewSub05.setType(2);
        this.mBinding.viewSub06.setType(3);
        this.mBinding.viewSub10.setType(4);
        this.mBinding.viewSub11.setType(5);
        this.mBinding.viewSub15.setType(2);
        this.mBinding.viewSub16.setType(3);
        this.mBinding.viewSub20.setType(1);
        this.mBinding.layoutSub01.setOnClickListener(new SubListView$$ExternalSyntheticLambda0(this));
        this.mBinding.layoutSub02.setOnClickListener(new SubListView$$ExternalSyntheticLambda2(this));
        this.mBinding.layoutSub03.setOnClickListener(new SubListView$$ExternalSyntheticLambda3(this));
        this.mBinding.layoutSub04.setOnClickListener(new SubListView$$ExternalSyntheticLambda4(this));
        this.mBinding.layoutSub05.setOnClickListener(new SubListView$$ExternalSyntheticLambda5(this));
        this.mBinding.layoutSub06.setOnClickListener(new SubListView$$ExternalSyntheticLambda6(this));
        this.mBinding.layoutSub07.setOnClickListener(new SubListView$$ExternalSyntheticLambda7(this));
        this.mBinding.layoutSub08.setOnClickListener(new SubListView$$ExternalSyntheticLambda8(this));
        this.mBinding.layoutSub09.setOnClickListener(new SubListView$$ExternalSyntheticLambda9(this));
        this.mBinding.layoutSub10.setOnClickListener(new SubListView$$ExternalSyntheticLambda10(this));
        this.mBinding.layoutSub11.setOnClickListener(new SubListView$$ExternalSyntheticLambda11(this));
        this.mBinding.layoutSub12.setOnClickListener(new SubListView$$ExternalSyntheticLambda12(this));
        this.mBinding.layoutSub13.setOnClickListener(new SubListView$$ExternalSyntheticLambda13(this));
        this.mBinding.layoutSub14.setOnClickListener(new SubListView$$ExternalSyntheticLambda14(this));
        this.mBinding.layoutSub15.setOnClickListener(new SubListView$$ExternalSyntheticLambda15(this));
        this.mBinding.layoutSub16.setOnClickListener(new SubListView$$ExternalSyntheticLambda16(this));
        this.mBinding.layoutSub17.setOnClickListener(new SubListView$$ExternalSyntheticLambda17(this));
        this.mBinding.layoutSub18.setOnClickListener(new SubListView$$ExternalSyntheticLambda18(this));
        this.mBinding.layoutSub19.setOnClickListener(new SubListView$$ExternalSyntheticLambda19(this));
        this.mBinding.layoutSub20.setOnClickListener(new SubListView$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m960lambda$initViews$0$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck01.setChecked(!this.mBinding.layoutCheck01.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub01.getTag()) - 1, this.mBinding.layoutCheck01.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m961lambda$initViews$1$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck02.setChecked(!this.mBinding.layoutCheck02.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub02.getTag()) - 1, this.mBinding.layoutCheck02.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m972lambda$initViews$2$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck03.setChecked(!this.mBinding.layoutCheck03.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub03.getTag()) - 1, this.mBinding.layoutCheck03.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m973lambda$initViews$3$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck04.setChecked(!this.mBinding.layoutCheck04.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub04.getTag()) - 1, this.mBinding.layoutCheck04.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m974lambda$initViews$4$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck05.setChecked(!this.mBinding.layoutCheck05.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub05.getTag()) - 1, this.mBinding.layoutCheck05.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m975lambda$initViews$5$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck06.setChecked(!this.mBinding.layoutCheck06.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub06.getTag()) - 1, this.mBinding.layoutCheck06.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$6$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m976lambda$initViews$6$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck07.setChecked(!this.mBinding.layoutCheck07.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub07.getTag()) - 1, this.mBinding.layoutCheck07.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$7$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m977lambda$initViews$7$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck08.setChecked(!this.mBinding.layoutCheck08.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub08.getTag()) - 1, this.mBinding.layoutCheck08.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$8$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m978lambda$initViews$8$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck09.setChecked(!this.mBinding.layoutCheck09.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub09.getTag()) - 1, this.mBinding.layoutCheck09.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$9$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m979lambda$initViews$9$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck10.setChecked(!this.mBinding.layoutCheck10.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub10.getTag()) - 1, this.mBinding.layoutCheck10.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$10$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m962lambda$initViews$10$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck11.setChecked(!this.mBinding.layoutCheck11.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub11.getTag()) - 1, this.mBinding.layoutCheck11.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$11$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m963lambda$initViews$11$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck12.setChecked(!this.mBinding.layoutCheck12.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub12.getTag()) - 1, this.mBinding.layoutCheck12.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$12$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m964lambda$initViews$12$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck13.setChecked(!this.mBinding.layoutCheck13.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub13.getTag()) - 1, this.mBinding.layoutCheck13.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$13$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m965lambda$initViews$13$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck14.setChecked(!this.mBinding.layoutCheck14.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub14.getTag()) - 1, this.mBinding.layoutCheck14.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$14$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m966lambda$initViews$14$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck15.setChecked(!this.mBinding.layoutCheck15.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub15.getTag()) - 1, this.mBinding.layoutCheck15.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$15$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m967lambda$initViews$15$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck16.setChecked(!this.mBinding.layoutCheck16.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub16.getTag()) - 1, this.mBinding.layoutCheck16.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$16$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m968lambda$initViews$16$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck17.setChecked(!this.mBinding.layoutCheck17.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub17.getTag()) - 1, this.mBinding.layoutCheck17.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$17$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m969lambda$initViews$17$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck18.setChecked(!this.mBinding.layoutCheck18.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub18.getTag()) - 1, this.mBinding.layoutCheck18.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$18$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m970lambda$initViews$18$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck19.setChecked(!this.mBinding.layoutCheck19.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub19.getTag()) - 1, this.mBinding.layoutCheck19.isChecked());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$19$com-mergbw-core-ui-views-SubListView  reason: not valid java name */
    public /* synthetic */ void m971lambda$initViews$19$commergbwcoreuiviewsSubListView(View view) {
        this.mBinding.layoutCheck20.setChecked(!this.mBinding.layoutCheck20.isChecked());
        OnSubItemCheckChangeListener onSubItemCheckChangeListener = this.mListener;
        if (onSubItemCheckChangeListener != null) {
            onSubItemCheckChangeListener.onSubItemCheck(Integer.parseInt((String) this.mBinding.layoutSub20.getTag()) - 1, this.mBinding.layoutCheck20.isChecked());
        }
    }

    public void setOnSubItemCheckChangeListener(OnSubItemCheckChangeListener onSubItemCheckChangeListener) {
        this.mListener = onSubItemCheckChangeListener;
    }

    public void refreshData(List<SubItemBean> list) {
        this.mBinding.layoutCheck01.setChecked(list.get(0).isChecked());
        this.mBinding.viewSub01.setColor(list.get(0).getColor());
        this.mBinding.layoutCheck02.setChecked(list.get(1).isChecked());
        this.mBinding.viewSub02.setColor(list.get(1).getColor());
        this.mBinding.layoutCheck03.setChecked(list.get(2).isChecked());
        this.mBinding.viewSub03.setColor(list.get(2).getColor());
        this.mBinding.layoutCheck04.setChecked(list.get(3).isChecked());
        this.mBinding.viewSub04.setColor(list.get(3).getColor());
        this.mBinding.layoutCheck05.setChecked(list.get(4).isChecked());
        this.mBinding.viewSub05.setColor(list.get(4).getColor());
        this.mBinding.layoutCheck06.setChecked(list.get(5).isChecked());
        this.mBinding.viewSub06.setColor(list.get(5).getColor());
        this.mBinding.layoutCheck07.setChecked(list.get(6).isChecked());
        this.mBinding.viewSub07.setColor(list.get(6).getColor());
        this.mBinding.layoutCheck08.setChecked(list.get(7).isChecked());
        this.mBinding.viewSub08.setColor(list.get(7).getColor());
        this.mBinding.layoutCheck09.setChecked(list.get(8).isChecked());
        this.mBinding.viewSub09.setColor(list.get(8).getColor());
        this.mBinding.layoutCheck10.setChecked(list.get(9).isChecked());
        this.mBinding.viewSub10.setColor(list.get(9).getColor());
        this.mBinding.layoutCheck11.setChecked(list.get(10).isChecked());
        this.mBinding.viewSub11.setColor(list.get(10).getColor());
        this.mBinding.layoutCheck12.setChecked(list.get(11).isChecked());
        this.mBinding.viewSub12.setColor(list.get(11).getColor());
        this.mBinding.layoutCheck13.setChecked(list.get(12).isChecked());
        this.mBinding.viewSub13.setColor(list.get(12).getColor());
        this.mBinding.layoutCheck14.setChecked(list.get(13).isChecked());
        this.mBinding.viewSub14.setColor(list.get(13).getColor());
        this.mBinding.layoutCheck15.setChecked(list.get(14).isChecked());
        this.mBinding.viewSub15.setColor(list.get(14).getColor());
        this.mBinding.layoutCheck16.setChecked(list.get(15).isChecked());
        this.mBinding.viewSub16.setColor(list.get(15).getColor());
        this.mBinding.layoutCheck17.setChecked(list.get(16).isChecked());
        this.mBinding.viewSub17.setColor(list.get(16).getColor());
        this.mBinding.layoutCheck18.setChecked(list.get(17).isChecked());
        this.mBinding.viewSub18.setColor(list.get(17).getColor());
        this.mBinding.layoutCheck19.setChecked(list.get(18).isChecked());
        this.mBinding.viewSub19.setColor(list.get(18).getColor());
        this.mBinding.layoutCheck20.setChecked(list.get(19).isChecked());
        this.mBinding.viewSub20.setColor(list.get(19).getColor());
    }
}

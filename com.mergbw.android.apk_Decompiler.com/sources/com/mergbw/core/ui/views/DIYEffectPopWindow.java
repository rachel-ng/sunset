package com.mergbw.core.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mergbw.core.R;
import com.mergbw.core.databinding.PopDiyEffectBinding;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class DIYEffectPopWindow extends PopupWindow {
    /* access modifiers changed from: private */
    public final List<Integer> effectList = new ArrayList(ViewDataUtils.EFFECT_NAME.keySet());
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentSelect;
    private final listAdapter mEffectAdapter;
    private final OnMenuClickListener mListener;

    public interface OnMenuClickListener {
        void OnClickDone(int i);
    }

    public DIYEffectPopWindow(Context context, int i, OnMenuClickListener onMenuClickListener) {
        this.mContext = context;
        this.mListener = onMenuClickListener;
        int i2 = 0;
        while (true) {
            if (i2 >= this.effectList.size()) {
                break;
            } else if (this.effectList.get(i2).intValue() == i) {
                this.mCurrentSelect = i2;
                break;
            } else {
                i2++;
            }
        }
        PopDiyEffectBinding inflate = PopDiyEffectBinding.inflate(LayoutInflater.from(context));
        setContentView(inflate.getRoot());
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.pop_anim);
        listAdapter listadapter = new listAdapter();
        this.mEffectAdapter = listadapter;
        inflate.listEffect.setAdapter(listadapter);
        inflate.listEffect.setOnItemClickListener(new DIYEffectPopWindow$$ExternalSyntheticLambda0(this));
        inflate.tvCancel.setOnClickListener(new DIYEffectPopWindow$$ExternalSyntheticLambda1(this));
        inflate.tvDone.setOnClickListener(new DIYEffectPopWindow$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-mergbw-core-ui-views-DIYEffectPopWindow  reason: not valid java name */
    public /* synthetic */ void m948lambda$new$0$commergbwcoreuiviewsDIYEffectPopWindow(AdapterView adapterView, View view, int i, long j) {
        this.mCurrentSelect = i;
        this.mEffectAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-mergbw-core-ui-views-DIYEffectPopWindow  reason: not valid java name */
    public /* synthetic */ void m949lambda$new$1$commergbwcoreuiviewsDIYEffectPopWindow(View view) {
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-mergbw-core-ui-views-DIYEffectPopWindow  reason: not valid java name */
    public /* synthetic */ void m950lambda$new$2$commergbwcoreuiviewsDIYEffectPopWindow(View view) {
        OnMenuClickListener onMenuClickListener = this.mListener;
        if (onMenuClickListener != null) {
            onMenuClickListener.OnClickDone(this.effectList.get(this.mCurrentSelect).intValue());
        }
        dismiss();
    }

    public class listAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        public listAdapter() {
        }

        public int getCount() {
            return DIYEffectPopWindow.this.effectList.size();
        }

        public Object getItem(int i) {
            return DIYEffectPopWindow.this.effectList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(DIYEffectPopWindow.this.mContext).inflate(R.layout.layout_effect_item, viewGroup, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.item_icon);
            ((TextView) view.findViewById(R.id.item_name)).setText(ViewDataUtils.EFFECT_NAME.get((Integer) getItem(i)).intValue());
            if (i == DIYEffectPopWindow.this.mCurrentSelect) {
                imageView.setImageResource(R.mipmap.icon_checked_02);
            } else {
                imageView.setImageResource(R.mipmap.icon_unchecked_02);
            }
            return view;
        }
    }
}

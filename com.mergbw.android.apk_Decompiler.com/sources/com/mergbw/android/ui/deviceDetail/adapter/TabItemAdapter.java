package com.mergbw.android.ui.deviceDetail.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.R;
import com.mergbw.android.databinding.LayoutTabItemBinding;
import com.mergbw.core.bean.TabItemBean;
import java.util.ArrayList;
import java.util.List;

public class TabItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = 0;
    private ITabItemClickListener mListener;
    private List<TabItemBean> mTabList = new ArrayList();

    public void setData(List<TabItemBean> list) {
        this.mTabList = list;
        this.mCurrentIndex = 0;
        notifyDataSetChanged();
    }

    public List<TabItemBean> getData() {
        return this.mTabList;
    }

    public void setClickListener(ITabItemClickListener iTabItemClickListener) {
        this.mListener = iTabItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutTabItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.mBinding.tvItemName.setText(this.mTabList.get(i).getName());
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.getRoot().setBackgroundResource(R.drawable.bg_page_rb_solid);
        } else {
            itemViewHolder.mBinding.getRoot().setBackground((Drawable) null);
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new TabItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceDetail-adapter-TabItemAdapter  reason: not valid java name */
    public /* synthetic */ void m728lambda$onBindViewHolder$0$commergbwandroiduideviceDetailadapterTabItemAdapter(ItemViewHolder itemViewHolder, View view) {
        this.mCurrentIndex = itemViewHolder.getLayoutPosition();
        notifyDataSetChanged();
        ITabItemClickListener iTabItemClickListener = this.mListener;
        if (iTabItemClickListener != null) {
            iTabItemClickListener.clickTabItem(this.mTabList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mTabList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutTabItemBinding mBinding;

        public ItemViewHolder(LayoutTabItemBinding layoutTabItemBinding) {
            super(layoutTabItemBinding.getRoot());
            this.mBinding = layoutTabItemBinding;
        }
    }
}

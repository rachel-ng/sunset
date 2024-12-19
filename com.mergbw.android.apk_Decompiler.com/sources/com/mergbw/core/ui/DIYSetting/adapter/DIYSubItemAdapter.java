package com.mergbw.core.ui.DIYSetting.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.R;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.databinding.LayoutDiySubItemBinding;
import com.mergbw.core.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;

public class DIYSubItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCurrentIndex = 1;
    private List<SubColorBean> mDIYSubList = new ArrayList();
    private IDIYSubItemClickListener mListener;

    public void setData(List<SubColorBean> list, int i) {
        this.mDIYSubList = list;
        this.mCurrentIndex = i;
        notifyDataSetChanged();
    }

    public void setClickListener(IDIYSubItemClickListener iDIYSubItemClickListener) {
        this.mListener = iDIYSubItemClickListener;
    }

    public void refreshData(List<SubItemBean> list) {
        this.mDIYSubList.get(this.mCurrentIndex).setColorValue(JSON.toJSONString(list));
        notifyItemChanged(this.mCurrentIndex);
    }

    public List<SubColorBean> getData() {
        return this.mDIYSubList;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutDiySubItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SubColorBean subColorBean = this.mDIYSubList.get(i);
        if (this.mCurrentIndex == i) {
            itemViewHolder.mBinding.getRoot().setBackgroundResource(R.drawable.bg_item_stroke);
        } else {
            itemViewHolder.mBinding.getRoot().setBackground((Drawable) null);
        }
        if (subColorBean.getType() == 1) {
            itemViewHolder.mBinding.ivAdd.setVisibility(0);
            itemViewHolder.mBinding.viewSubList.setVisibility(8);
            itemViewHolder.mBinding.ivDelete.setVisibility(8);
        } else {
            itemViewHolder.mBinding.ivAdd.setVisibility(8);
            itemViewHolder.mBinding.viewSubList.setVisibility(0);
            itemViewHolder.mBinding.viewSubList.refreshData(ColorUtils.getSubColor(subColorBean.getColorValue()));
            itemViewHolder.mBinding.ivDelete.setVisibility(0);
            itemViewHolder.mBinding.ivDelete.setOnClickListener(new DIYSubItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new DIYSubItemAdapter$$ExternalSyntheticLambda1(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-core-ui-DIYSetting-adapter-DIYSubItemAdapter  reason: not valid java name */
    public /* synthetic */ void m928lambda$onBindViewHolder$0$commergbwcoreuiDIYSettingadapterDIYSubItemAdapter(ItemViewHolder itemViewHolder, View view) {
        IDIYSubItemClickListener iDIYSubItemClickListener = this.mListener;
        if (iDIYSubItemClickListener != null) {
            iDIYSubItemClickListener.deleteSubItem(this.mDIYSubList.get(itemViewHolder.getBindingAdapterPosition()), itemViewHolder.getBindingAdapterPosition(), this.mCurrentIndex);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$1$com-mergbw-core-ui-DIYSetting-adapter-DIYSubItemAdapter  reason: not valid java name */
    public /* synthetic */ void m929lambda$onBindViewHolder$1$commergbwcoreuiDIYSettingadapterDIYSubItemAdapter(ItemViewHolder itemViewHolder, View view) {
        if (this.mCurrentIndex != itemViewHolder.getBindingAdapterPosition()) {
            this.mCurrentIndex = itemViewHolder.getBindingAdapterPosition();
            notifyDataSetChanged();
            IDIYSubItemClickListener iDIYSubItemClickListener = this.mListener;
            if (iDIYSubItemClickListener != null) {
                iDIYSubItemClickListener.clickDIYSubItem(this.mDIYSubList.get(this.mCurrentIndex));
            }
        }
    }

    public int getItemCount() {
        return this.mDIYSubList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutDiySubItemBinding mBinding;

        public ItemViewHolder(LayoutDiySubItemBinding layoutDiySubItemBinding) {
            super(layoutDiySubItemBinding.getRoot());
            this.mBinding = layoutDiySubItemBinding;
        }
    }
}

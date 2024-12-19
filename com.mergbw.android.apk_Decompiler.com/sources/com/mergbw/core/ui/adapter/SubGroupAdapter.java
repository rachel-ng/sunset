package com.mergbw.core.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.core.R;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.databinding.LayoutSubGroupBinding;
import com.mergbw.core.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;

public class SubGroupAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private boolean longClick = false;
    private int mCurrentIndex = -1;
    private List<SubColorBean> mGroupList = new ArrayList();
    private ISubColorGroupClickListener mListener;

    public void setData(List<SubColorBean> list) {
        this.mGroupList = list;
        this.mCurrentIndex = -1;
    }

    public void resetSelected() {
        this.mCurrentIndex = -1;
        notifyDataSetChanged();
    }

    public void setClickListener(ISubColorGroupClickListener iSubColorGroupClickListener) {
        this.mListener = iSubColorGroupClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSubGroupBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SubColorBean subColorBean = this.mGroupList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(subColorBean.getAlias());
        if (subColorBean.getType() == -1 || this.mCurrentIndex != i) {
            itemViewHolder.mBinding.viewSubList.setBackground((Drawable) null);
        } else {
            itemViewHolder.mBinding.viewSubList.setBackgroundResource(R.drawable.bg_item_stroke);
        }
        if (subColorBean.getType() == -1) {
            itemViewHolder.mBinding.viewSubList.setVisibility(8);
            itemViewHolder.mBinding.ivAdd.setVisibility(0);
        } else {
            itemViewHolder.mBinding.viewSubList.setVisibility(0);
            itemViewHolder.mBinding.ivAdd.setVisibility(8);
            List<SubItemBean> subColor = ColorUtils.getSubColor(subColorBean.getColorValue());
            if (subColor != null) {
                itemViewHolder.mBinding.viewSubList.refreshData(subColor);
            }
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new SubGroupAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
        itemViewHolder.mBinding.getRoot().setOnLongClickListener(new SubGroupAdapter$$ExternalSyntheticLambda1(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-core-ui-adapter-SubGroupAdapter  reason: not valid java name */
    public /* synthetic */ void m933lambda$onBindViewHolder$0$commergbwcoreuiadapterSubGroupAdapter(ItemViewHolder itemViewHolder, View view) {
        ISubColorGroupClickListener iSubColorGroupClickListener;
        if (this.longClick || this.mCurrentIndex != itemViewHolder.getBindingAdapterPosition() || this.mGroupList.get(this.mCurrentIndex).getType() == -1) {
            this.longClick = false;
            this.mCurrentIndex = itemViewHolder.getBindingAdapterPosition();
            notifyDataSetChanged();
            int i = this.mCurrentIndex;
            if (i >= 0 && i <= getItemCount() && (iSubColorGroupClickListener = this.mListener) != null) {
                iSubColorGroupClickListener.clickGroupItem(this.mGroupList.get(this.mCurrentIndex));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$1$com-mergbw-core-ui-adapter-SubGroupAdapter  reason: not valid java name */
    public /* synthetic */ boolean m934lambda$onBindViewHolder$1$commergbwcoreuiadapterSubGroupAdapter(ItemViewHolder itemViewHolder, View view) {
        ISubColorGroupClickListener iSubColorGroupClickListener;
        this.mCurrentIndex = itemViewHolder.getBindingAdapterPosition();
        this.longClick = true;
        notifyDataSetChanged();
        int i = this.mCurrentIndex;
        if (i >= 0 && i <= getItemCount() && (iSubColorGroupClickListener = this.mListener) != null) {
            iSubColorGroupClickListener.longClickGroupItem(this.mGroupList.get(this.mCurrentIndex));
        }
        return false;
    }

    public int getItemCount() {
        return this.mGroupList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSubGroupBinding mBinding;

        public ItemViewHolder(LayoutSubGroupBinding layoutSubGroupBinding) {
            super(layoutSubGroupBinding.getRoot());
            this.mBinding = layoutSubGroupBinding;
        }
    }
}

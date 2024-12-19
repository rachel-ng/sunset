package com.mergbw.core.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.core.R;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.databinding.LayoutDiyItemBinding;
import com.mergbw.core.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;

public class DIYItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private boolean longClick = false;
    private int mCurrentIndex = -1;
    private List<DIYInfoBean> mDIYList = new ArrayList();
    private IDIYItemClickListener mListener;

    public void setData(List<DIYInfoBean> list) {
        this.mDIYList = list;
        this.mCurrentIndex = -1;
    }

    public void resetSelected() {
        this.mCurrentIndex = -1;
        notifyDataSetChanged();
    }

    public void setClickListener(IDIYItemClickListener iDIYItemClickListener) {
        this.mListener = iDIYItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutDiyItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        DIYInfoBean dIYInfoBean = this.mDIYList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(dIYInfoBean.getName());
        if (dIYInfoBean.getType() == 1 || this.mCurrentIndex != i) {
            itemViewHolder.mBinding.viewSubList.setBackground((Drawable) null);
        } else {
            itemViewHolder.mBinding.viewSubList.setBackgroundResource(R.drawable.bg_item_stroke);
        }
        if (dIYInfoBean.getType() == 1) {
            itemViewHolder.mBinding.ivAdd.setVisibility(0);
            itemViewHolder.mBinding.viewSubList.setVisibility(8);
        } else {
            itemViewHolder.mBinding.ivAdd.setVisibility(8);
            itemViewHolder.mBinding.viewSubList.setVisibility(0);
            if (dIYInfoBean.getSubColorList() != null && !dIYInfoBean.getSubColorList().isEmpty()) {
                itemViewHolder.mBinding.viewSubList.refreshData(ColorUtils.getSubColor(dIYInfoBean.getSubColorList().get(dIYInfoBean.getSubColorList().size() - 1).getColorValue()));
            }
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new DIYItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
        itemViewHolder.mBinding.getRoot().setOnLongClickListener(new DIYItemAdapter$$ExternalSyntheticLambda1(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-core-ui-adapter-DIYItemAdapter  reason: not valid java name */
    public /* synthetic */ void m930lambda$onBindViewHolder$0$commergbwcoreuiadapterDIYItemAdapter(ItemViewHolder itemViewHolder, View view) {
        IDIYItemClickListener iDIYItemClickListener;
        if (this.longClick || this.mCurrentIndex != itemViewHolder.getBindingAdapterPosition() || this.mDIYList.get(this.mCurrentIndex).getType() == 1) {
            this.longClick = false;
            this.mCurrentIndex = itemViewHolder.getBindingAdapterPosition();
            notifyDataSetChanged();
            int i = this.mCurrentIndex;
            if (i >= 0 && i <= getItemCount() && (iDIYItemClickListener = this.mListener) != null) {
                iDIYItemClickListener.clickDIYItem(this.mDIYList.get(this.mCurrentIndex));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$1$com-mergbw-core-ui-adapter-DIYItemAdapter  reason: not valid java name */
    public /* synthetic */ boolean m931lambda$onBindViewHolder$1$commergbwcoreuiadapterDIYItemAdapter(ItemViewHolder itemViewHolder, View view) {
        IDIYItemClickListener iDIYItemClickListener;
        this.longClick = true;
        this.mCurrentIndex = itemViewHolder.getBindingAdapterPosition();
        notifyDataSetChanged();
        int i = this.mCurrentIndex;
        if (i >= 0 && i <= getItemCount() && (iDIYItemClickListener = this.mListener) != null) {
            iDIYItemClickListener.longClickDIYItem(this.mDIYList.get(this.mCurrentIndex));
        }
        return false;
    }

    public int getItemCount() {
        return this.mDIYList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutDiyItemBinding mBinding;

        public ItemViewHolder(LayoutDiyItemBinding layoutDiyItemBinding) {
            super(layoutDiyItemBinding.getRoot());
            this.mBinding = layoutDiyItemBinding;
        }
    }
}

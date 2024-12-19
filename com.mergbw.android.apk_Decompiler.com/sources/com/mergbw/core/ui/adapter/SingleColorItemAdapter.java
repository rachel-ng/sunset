package com.mergbw.core.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.databinding.LayoutSingleColorItemBinding;
import java.util.ArrayList;
import java.util.List;

public class SingleColorItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private int mCheckIndex = -1;
    private List<ColorBean> mColorList = new ArrayList();
    private ISingleColorListClickListener mListener;

    public void setData(List<ColorBean> list) {
        this.mColorList = list;
    }

    public void setClickListener(ISingleColorListClickListener iSingleColorListClickListener) {
        this.mListener = iSingleColorListClickListener;
    }

    public void refreshSelected(int i) {
        this.mCheckIndex = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= this.mColorList.size()) {
                break;
            } else if (this.mColorList.get(i2).getColorValue() == i) {
                this.mCheckIndex = i2;
                break;
            } else {
                i2++;
            }
        }
        notifyDataSetChanged();
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSingleColorItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        ColorBean colorBean = this.mColorList.get(i);
        itemViewHolder.mBinding.viewColorItem.setColor(colorBean.getColorValue());
        itemViewHolder.mBinding.viewColorItem.setCheck(i == this.mCheckIndex);
        itemViewHolder.mBinding.viewColorItem.setOnClickListener(new SingleColorItemAdapter$$ExternalSyntheticLambda0(this, colorBean, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-core-ui-adapter-SingleColorItemAdapter  reason: not valid java name */
    public /* synthetic */ void m932lambda$onBindViewHolder$0$commergbwcoreuiadapterSingleColorItemAdapter(ColorBean colorBean, ItemViewHolder itemViewHolder, View view) {
        ISingleColorListClickListener iSingleColorListClickListener = this.mListener;
        if (iSingleColorListClickListener != null) {
            iSingleColorListClickListener.clickColorItem(colorBean);
        }
        this.mCheckIndex = itemViewHolder.getBindingAdapterPosition();
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.mColorList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSingleColorItemBinding mBinding;

        public ItemViewHolder(LayoutSingleColorItemBinding layoutSingleColorItemBinding) {
            super(layoutSingleColorItemBinding.getRoot());
            this.mBinding = layoutSingleColorItemBinding;
        }
    }
}

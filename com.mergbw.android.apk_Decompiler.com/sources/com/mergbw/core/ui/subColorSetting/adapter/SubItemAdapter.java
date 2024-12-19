package com.mergbw.core.ui.subColorSetting.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.databinding.LayoutSubItemBinding;
import java.util.ArrayList;
import java.util.List;

public class SubItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private ISubListClickListener mListener;
    private List<SubItemBean> mSubList = new ArrayList();

    public void setData(List<SubItemBean> list) {
        this.mSubList = list;
    }

    public void setClickListener(ISubListClickListener iSubListClickListener) {
        this.mListener = iSubListClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutSubItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        SubItemBean subItemBean = this.mSubList.get(i);
        if (i == 0 || i == 19) {
            itemViewHolder.mBinding.viewSub.setType(1);
            return;
        }
        if (i != 4) {
            if (i != 5) {
                if (i == 9) {
                    itemViewHolder.mBinding.viewSub.setType(4);
                    return;
                } else if (i == 10) {
                    itemViewHolder.mBinding.viewSub.setType(5);
                    return;
                } else if (i != 14) {
                    if (i != 15) {
                        itemViewHolder.mBinding.viewSub.setType(0);
                        return;
                    }
                }
            }
            itemViewHolder.mBinding.viewSub.setType(3);
            return;
        }
        itemViewHolder.mBinding.viewSub.setType(2);
    }

    public int getItemCount() {
        return this.mSubList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutSubItemBinding mBinding;

        public ItemViewHolder(LayoutSubItemBinding layoutSubItemBinding) {
            super(layoutSubItemBinding.getRoot());
            this.mBinding = layoutSubItemBinding;
        }
    }
}

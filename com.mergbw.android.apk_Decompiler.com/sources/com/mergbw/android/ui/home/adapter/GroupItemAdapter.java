package com.mergbw.android.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.R;
import com.mergbw.android.databinding.LayoutGroupItemBinding;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class GroupItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public List<GroupItemBean> mGroupList = new ArrayList();
    public IGroupListClickListener mListener;

    public void setData(List<GroupItemBean> list) {
        this.mGroupList = list;
    }

    public void setClickListener(IGroupListClickListener iGroupListClickListener) {
        this.mListener = iGroupListClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutGroupItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        GroupItemBean groupItemBean = this.mGroupList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(groupItemBean.getGroupName());
        String string = AppUtils.getApp().getString(R.string.group_device_num);
        if (!StringUtils.isEmpty(groupItemBean.getDevices())) {
            String[] split = groupItemBean.getDevices().split(";");
            itemViewHolder.mBinding.tvItemState.setText(String.format(string, new Object[]{Integer.valueOf(split.length)}));
        } else {
            itemViewHolder.mBinding.tvItemState.setText(String.format(string, new Object[]{0}));
        }
        itemViewHolder.mBinding.getRoot().setOnClickListener(new GroupItemAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-home-adapter-GroupItemAdapter  reason: not valid java name */
    public /* synthetic */ void m858lambda$onBindViewHolder$0$commergbwandroiduihomeadapterGroupItemAdapter(ItemViewHolder itemViewHolder, View view) {
        IGroupListClickListener iGroupListClickListener = this.mListener;
        if (iGroupListClickListener != null) {
            iGroupListClickListener.clickGroupItem(this.mGroupList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mGroupList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutGroupItemBinding mBinding;

        public ItemViewHolder(LayoutGroupItemBinding layoutGroupItemBinding) {
            super(layoutGroupItemBinding.getRoot());
            this.mBinding = layoutGroupItemBinding;
        }
    }
}

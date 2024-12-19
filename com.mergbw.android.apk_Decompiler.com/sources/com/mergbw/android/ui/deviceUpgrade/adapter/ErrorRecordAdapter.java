package com.mergbw.android.ui.deviceUpgrade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutErrorRecordItemBinding;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class ErrorRecordAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public IRecordListClickListener mListener;
    public List<UpgradeErrorRecordBean> mRecordList = new ArrayList();

    public void setData(List<UpgradeErrorRecordBean> list) {
        this.mRecordList = list;
        notifyDataSetChanged();
    }

    public void setClickListener(IRecordListClickListener iRecordListClickListener) {
        this.mListener = iRecordListClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutErrorRecordItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        UpgradeErrorRecordBean upgradeErrorRecordBean = this.mRecordList.get(i);
        itemViewHolder.mBinding.ivItemIcon.setImageResource(ViewDataUtils.getDeviceIcon(upgradeErrorRecordBean.getDeviceType(), true));
        itemViewHolder.mBinding.tvItemName.setText(upgradeErrorRecordBean.getAliasName());
        itemViewHolder.mBinding.tvItemState.setText(upgradeErrorRecordBean.getRecordTime());
        itemViewHolder.mBinding.btnResume.setOnClickListener(new ErrorRecordAdapter$$ExternalSyntheticLambda0(this, itemViewHolder));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-deviceUpgrade-adapter-ErrorRecordAdapter  reason: not valid java name */
    public /* synthetic */ void m787lambda$onBindViewHolder$0$commergbwandroiduideviceUpgradeadapterErrorRecordAdapter(ItemViewHolder itemViewHolder, View view) {
        IRecordListClickListener iRecordListClickListener = this.mListener;
        if (iRecordListClickListener != null) {
            iRecordListClickListener.clickRecordItem(this.mRecordList.get(itemViewHolder.getLayoutPosition()));
        }
    }

    public int getItemCount() {
        return this.mRecordList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutErrorRecordItemBinding mBinding;

        public ItemViewHolder(LayoutErrorRecordItemBinding layoutErrorRecordItemBinding) {
            super(layoutErrorRecordItemBinding.getRoot());
            this.mBinding = layoutErrorRecordItemBinding;
        }
    }
}

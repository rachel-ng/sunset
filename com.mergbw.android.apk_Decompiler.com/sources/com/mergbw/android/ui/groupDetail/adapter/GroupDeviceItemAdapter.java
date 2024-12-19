package com.mergbw.android.ui.groupDetail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutGroupDeviceListItemBinding;
import com.mergbw.core.R;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class GroupDeviceItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<DeviceInfoBean> mDeviceList = new ArrayList();
    private IDeviceItemClickListener mListener;

    public void setData(List<DeviceInfoBean> list) {
        this.mDeviceList = list;
        notifyDataSetChanged();
    }

    public List<DeviceInfoBean> getData() {
        return this.mDeviceList;
    }

    public void updateCheckAll(boolean z) {
        for (DeviceInfoBean selected : this.mDeviceList) {
            selected.setSelected(z);
        }
        notifyDataSetChanged();
    }

    public void setClickListener(IDeviceItemClickListener iDeviceItemClickListener) {
        this.mListener = iDeviceItemClickListener;
    }

    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutGroupDeviceListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        DeviceInfoBean deviceInfoBean = this.mDeviceList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(deviceInfoBean.getAliasName());
        itemViewHolder.mBinding.tvItemModel.setText(deviceInfoBean.getDeviceModel());
        itemViewHolder.mBinding.getRoot().setOnClickListener(new GroupDeviceItemAdapter$$ExternalSyntheticLambda0(this, deviceInfoBean));
        itemViewHolder.mBinding.cbItemCheck.setChecked(deviceInfoBean.isSelected());
        itemViewHolder.mBinding.getRoot().setEnabled(!deviceInfoBean.isSelected());
        itemViewHolder.mBinding.ivItemIcon.setImageResource(ViewDataUtils.getDeviceIcon(deviceInfoBean.getDeviceType(), deviceInfoBean.isConnect()));
        itemViewHolder.mBinding.ivItemSwitch.setChecked(deviceInfoBean.isOpen());
        if (deviceInfoBean.isConnect()) {
            itemViewHolder.mBinding.ivItemState.setImageResource(R.mipmap.icon_ble_connected);
            itemViewHolder.mBinding.ivItemSwitch.setVisibility(0);
            itemViewHolder.mBinding.btnConnect.setVisibility(4);
            itemViewHolder.mBinding.viewLoading.setVisibility(4);
            itemViewHolder.mBinding.viewLoading.pauseAnimation();
        } else {
            itemViewHolder.mBinding.ivItemState.setImageResource(R.mipmap.icon_ble_disconnected);
            itemViewHolder.mBinding.ivItemSwitch.setVisibility(4);
            if (deviceInfoBean.isConnecting()) {
                itemViewHolder.mBinding.btnConnect.setVisibility(4);
                itemViewHolder.mBinding.viewLoading.setVisibility(0);
                itemViewHolder.mBinding.viewLoading.playAnimation();
            } else {
                itemViewHolder.mBinding.btnConnect.setVisibility(0);
                itemViewHolder.mBinding.viewLoading.setVisibility(4);
                itemViewHolder.mBinding.viewLoading.pauseAnimation();
            }
        }
        itemViewHolder.mBinding.cbItemCheck.setOnCheckedChangeListener(new GroupDeviceItemAdapter$$ExternalSyntheticLambda1(this, deviceInfoBean, itemViewHolder));
        itemViewHolder.mBinding.ivItemSwitch.setOnCheckedChangeListener(new GroupDeviceItemAdapter$$ExternalSyntheticLambda2(this, deviceInfoBean));
        itemViewHolder.mBinding.btnConnect.setOnClickListener(new GroupDeviceItemAdapter$$ExternalSyntheticLambda3(this, deviceInfoBean));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-groupDetail-adapter-GroupDeviceItemAdapter  reason: not valid java name */
    public /* synthetic */ void m807lambda$onBindViewHolder$0$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(DeviceInfoBean deviceInfoBean, View view) {
        IDeviceItemClickListener iDeviceItemClickListener = this.mListener;
        if (iDeviceItemClickListener != null) {
            iDeviceItemClickListener.clickItem(deviceInfoBean);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$1$com-mergbw-android-ui-groupDetail-adapter-GroupDeviceItemAdapter  reason: not valid java name */
    public /* synthetic */ void m808lambda$onBindViewHolder$1$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(DeviceInfoBean deviceInfoBean, ItemViewHolder itemViewHolder, CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            deviceInfoBean.setSelected(z);
            itemViewHolder.mBinding.getRoot().setEnabled(!z);
            IDeviceItemClickListener iDeviceItemClickListener = this.mListener;
            if (iDeviceItemClickListener != null) {
                iDeviceItemClickListener.checkItem(deviceInfoBean, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$2$com-mergbw-android-ui-groupDetail-adapter-GroupDeviceItemAdapter  reason: not valid java name */
    public /* synthetic */ void m809lambda$onBindViewHolder$2$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(DeviceInfoBean deviceInfoBean, CompoundButton compoundButton, boolean z) {
        IDeviceItemClickListener iDeviceItemClickListener;
        if (compoundButton.isPressed() && (iDeviceItemClickListener = this.mListener) != null) {
            iDeviceItemClickListener.clickPower(deviceInfoBean, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$3$com-mergbw-android-ui-groupDetail-adapter-GroupDeviceItemAdapter  reason: not valid java name */
    public /* synthetic */ void m810lambda$onBindViewHolder$3$commergbwandroiduigroupDetailadapterGroupDeviceItemAdapter(DeviceInfoBean deviceInfoBean, View view) {
        IDeviceItemClickListener iDeviceItemClickListener = this.mListener;
        if (iDeviceItemClickListener != null) {
            iDeviceItemClickListener.clickConnect(deviceInfoBean);
            deviceInfoBean.setConnecting(true);
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return this.mDeviceList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutGroupDeviceListItemBinding mBinding;

        public ItemViewHolder(LayoutGroupDeviceListItemBinding layoutGroupDeviceListItemBinding) {
            super(layoutGroupDeviceListItemBinding.getRoot());
            this.mBinding = layoutGroupDeviceListItemBinding;
        }
    }
}

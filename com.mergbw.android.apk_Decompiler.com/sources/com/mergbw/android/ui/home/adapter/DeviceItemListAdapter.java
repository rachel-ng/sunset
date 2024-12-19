package com.mergbw.android.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.mergbw.android.databinding.LayoutDeviceListItemBinding;
import com.mergbw.core.R;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.utils.ViewDataUtils;

public class DeviceItemListAdapter extends DeviceItemAdapter<ItemViewHolder> {
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ItemViewHolder(LayoutDeviceListItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) this.mDeviceList.get(i);
        itemViewHolder.mBinding.tvItemName.setText(deviceInfoBean.getAliasName());
        itemViewHolder.mBinding.tvItemModel.setText(deviceInfoBean.getDeviceModel());
        itemViewHolder.mBinding.getRoot().setOnClickListener(new DeviceItemListAdapter$$ExternalSyntheticLambda0(this, deviceInfoBean));
        itemViewHolder.mBinding.ivItemIcon.setImageResource(ViewDataUtils.getDeviceIcon(deviceInfoBean.getDeviceType(), deviceInfoBean.isConnect()));
        itemViewHolder.mBinding.ivItemState.setImageResource(deviceInfoBean.isConnect() ? R.mipmap.icon_ble_connected : R.mipmap.icon_ble_disconnected);
        itemViewHolder.mBinding.ivItemSwitch.setChecked(deviceInfoBean.isOpen());
        itemViewHolder.mBinding.tvSwitchState.setText(deviceInfoBean.isOpen() ? "On" : "Off");
        itemViewHolder.mBinding.ivItemSwitch.setEnabled(deviceInfoBean.isConnect());
        itemViewHolder.mBinding.ivItemSwitch.setOnCheckedChangeListener(new DeviceItemListAdapter$$ExternalSyntheticLambda1(this, deviceInfoBean));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-mergbw-android-ui-home-adapter-DeviceItemListAdapter  reason: not valid java name */
    public /* synthetic */ void m856lambda$onBindViewHolder$0$commergbwandroiduihomeadapterDeviceItemListAdapter(DeviceInfoBean deviceInfoBean, View view) {
        if (this.mListener != null) {
            this.mListener.clickDeviceItem(deviceInfoBean);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$1$com-mergbw-android-ui-home-adapter-DeviceItemListAdapter  reason: not valid java name */
    public /* synthetic */ void m857lambda$onBindViewHolder$1$commergbwandroiduihomeadapterDeviceItemListAdapter(DeviceInfoBean deviceInfoBean, CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed() && this.mListener != null) {
            this.mListener.clickPower(deviceInfoBean, z);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final LayoutDeviceListItemBinding mBinding;

        public ItemViewHolder(LayoutDeviceListItemBinding layoutDeviceListItemBinding) {
            super(layoutDeviceListItemBinding.getRoot());
            this.mBinding = layoutDeviceListItemBinding;
        }
    }
}
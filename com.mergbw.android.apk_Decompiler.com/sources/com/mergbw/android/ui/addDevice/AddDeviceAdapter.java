package com.mergbw.android.ui.addDevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.mergbw.android.databinding.LayoutAddDeviceItemBinding;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class AddDeviceAdapter extends BaseAdapter {
    private final DeviceItemClickCallback mCallback;
    private List<DeviceInfoBean> mDeviceList = new ArrayList();

    public interface DeviceItemClickCallback {
        void onClick(DeviceInfoBean deviceInfoBean);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public AddDeviceAdapter(DeviceItemClickCallback deviceItemClickCallback) {
        this.mCallback = deviceItemClickCallback;
    }

    public void setData(List<DeviceInfoBean> list) {
        this.mDeviceList = list;
    }

    public int getCount() {
        return this.mDeviceList.size();
    }

    public Object getItem(int i) {
        return this.mDeviceList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutAddDeviceItemBinding inflate = LayoutAddDeviceItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
            viewHolder = new ViewHolder(inflate);
            view = inflate.getRoot();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) getItem(i);
        viewHolder.mItemBinding.tvItemName.setText(deviceInfoBean.getDeviceName());
        viewHolder.mItemBinding.tvItemModel.setText(deviceInfoBean.getDeviceModel());
        viewHolder.mItemBinding.ivItemIcon.setImageResource(ViewDataUtils.getDeviceIcon(deviceInfoBean.getDeviceType(), true));
        viewHolder.mItemBinding.btnConnect.setOnClickListener(new AddDeviceAdapter$$ExternalSyntheticLambda0(this, deviceInfoBean));
        return view;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getView$0$com-mergbw-android-ui-addDevice-AddDeviceAdapter  reason: not valid java name */
    public /* synthetic */ void m700lambda$getView$0$commergbwandroiduiaddDeviceAddDeviceAdapter(DeviceInfoBean deviceInfoBean, View view) {
        DeviceItemClickCallback deviceItemClickCallback = this.mCallback;
        if (deviceItemClickCallback != null) {
            deviceItemClickCallback.onClick(deviceInfoBean);
        }
    }

    public static class ViewHolder {
        LayoutAddDeviceItemBinding mItemBinding;

        public ViewHolder(LayoutAddDeviceItemBinding layoutAddDeviceItemBinding) {
            this.mItemBinding = layoutAddDeviceItemBinding;
        }
    }
}

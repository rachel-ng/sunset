package com.mergbw.android.ui.home.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.mergbw.core.database.bean.DeviceInfoBean;
import java.util.ArrayList;
import java.util.List;

public abstract class DeviceItemAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public List<DeviceInfoBean> mDeviceList = new ArrayList();
    public IDeviceListClickListener mListener;

    public void onBindViewHolder(VH vh, int i) {
    }

    public abstract VH onCreateViewHolder(ViewGroup viewGroup);

    public void setData(List<DeviceInfoBean> list) {
        this.mDeviceList = list;
    }

    public void setClickListener(IDeviceListClickListener iDeviceListClickListener) {
        this.mListener = iDeviceListClickListener;
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return onCreateViewHolder(viewGroup);
    }

    public int getItemCount() {
        return this.mDeviceList.size();
    }
}

package com.mergbw.android.ui.groupSetting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import com.mergbw.android.R;
import com.mergbw.android.databinding.LayoutGroupDeviceItemBinding;
import com.mergbw.android.databinding.LayoutGroupNameItemBinding;
import com.mergbw.android.ui.groupSetting.bean.DeviceGroupBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class DeviceGroupItemAdapter extends BaseExpandableListAdapter {
    private boolean isNew = false;
    private List<DeviceGroupBean> mDataList = new ArrayList();
    private int mDeviceType = 0;

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public void setData(List<DeviceGroupBean> list, int i) {
        this.mDataList = list;
        this.mDeviceType = i;
        this.isNew = i == 0;
        notifyDataSetChanged();
    }

    public List<DeviceGroupBean> getData() {
        return this.mDataList;
    }

    public int getGroupCount() {
        return this.mDataList.size();
    }

    public int getChildrenCount(int i) {
        return this.mDataList.get(i).getDeviceList().size();
    }

    public Object getGroup(int i) {
        return this.mDataList.get(i);
    }

    public Object getChild(int i, int i2) {
        return this.mDataList.get(i).getDeviceList().get(i2);
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        View view2;
        if (view == null) {
            LayoutGroupNameItemBinding inflate = LayoutGroupNameItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
            groupViewHolder = new GroupViewHolder(inflate);
            view2 = inflate.getRoot();
            view2.setTag(groupViewHolder);
        } else {
            View view3 = view;
            groupViewHolder = (GroupViewHolder) view.getTag();
            view2 = view3;
        }
        DeviceGroupBean deviceGroupBean = (DeviceGroupBean) getGroup(i);
        if (deviceGroupBean.getType() == DeviceGroupBean.ADDED_DEVICE) {
            String string = AppUtils.getApp().getString(R.string.added_device_num);
            groupViewHolder.mGroupBinding.tvGroupName.setText(String.format(string, new Object[]{Integer.valueOf(deviceGroupBean.getDeviceList().size())}));
        } else {
            String string2 = AppUtils.getApp().getString(R.string.can_device_num);
            groupViewHolder.mGroupBinding.tvGroupName.setText(String.format(string2, new Object[]{Integer.valueOf(deviceGroupBean.getDeviceList().size())}));
        }
        return view2;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        ItemViewHolder itemViewHolder;
        View view2;
        if (view == null) {
            LayoutGroupDeviceItemBinding inflate = LayoutGroupDeviceItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
            itemViewHolder = new ItemViewHolder(inflate);
            view2 = inflate.getRoot();
            view2.setTag(itemViewHolder);
        } else {
            View view3 = view;
            itemViewHolder = (ItemViewHolder) view.getTag();
            view2 = view3;
        }
        DeviceGroupBean deviceGroupBean = (DeviceGroupBean) getGroup(i);
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) getChild(i, i2);
        int i3 = 0;
        if (this.mDeviceType != 0) {
            ImageView imageView = itemViewHolder.mItemBinding.itemIcon;
            if (this.mDeviceType != deviceInfoBean.getDeviceType()) {
                i3 = 8;
            }
            imageView.setVisibility(i3);
        } else {
            itemViewHolder.mItemBinding.itemIcon.setVisibility(0);
        }
        itemViewHolder.mItemBinding.tvItemName.setText(deviceInfoBean.getAliasName());
        itemViewHolder.mItemBinding.ivItemIcon.setImageResource(ViewDataUtils.getDeviceIcon(deviceInfoBean.getDeviceType(), deviceInfoBean.isConnect()));
        if (deviceGroupBean.getType() == DeviceGroupBean.ADDED_DEVICE) {
            itemViewHolder.mItemBinding.itemIcon.setImageResource(R.mipmap.icon_item_delete);
            itemViewHolder.mItemBinding.itemIcon.setOnClickListener(new DeviceGroupItemAdapter$$ExternalSyntheticLambda0(this, deviceInfoBean, deviceGroupBean));
        } else {
            itemViewHolder.mItemBinding.itemIcon.setImageResource(deviceInfoBean.isSelected() ? com.mergbw.core.R.mipmap.icon_checked_02 : com.mergbw.core.R.mipmap.icon_unchecked_02);
            itemViewHolder.mItemBinding.itemIcon.setOnClickListener(new DeviceGroupItemAdapter$$ExternalSyntheticLambda1(this, deviceInfoBean));
        }
        return view2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getChildView$0$com-mergbw-android-ui-groupSetting-adapter-DeviceGroupItemAdapter  reason: not valid java name */
    public /* synthetic */ void m848lambda$getChildView$0$commergbwandroiduigroupSettingadapterDeviceGroupItemAdapter(DeviceInfoBean deviceInfoBean, DeviceGroupBean deviceGroupBean, View view) {
        deviceInfoBean.setSelected(false);
        deviceGroupBean.getDeviceList().remove(deviceInfoBean);
        this.mDataList.get(1).getDeviceList().add(deviceInfoBean);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getChildView$1$com-mergbw-android-ui-groupSetting-adapter-DeviceGroupItemAdapter  reason: not valid java name */
    public /* synthetic */ void m849lambda$getChildView$1$commergbwandroiduigroupSettingadapterDeviceGroupItemAdapter(DeviceInfoBean deviceInfoBean, View view) {
        deviceInfoBean.setSelected(!deviceInfoBean.isSelected());
        if (this.isNew) {
            checkDeviceType();
        }
        notifyDataSetChanged();
    }

    public static class GroupViewHolder {
        LayoutGroupNameItemBinding mGroupBinding;

        public GroupViewHolder(LayoutGroupNameItemBinding layoutGroupNameItemBinding) {
            this.mGroupBinding = layoutGroupNameItemBinding;
        }
    }

    public static class ItemViewHolder {
        LayoutGroupDeviceItemBinding mItemBinding;

        public ItemViewHolder(LayoutGroupDeviceItemBinding layoutGroupDeviceItemBinding) {
            this.mItemBinding = layoutGroupDeviceItemBinding;
        }
    }

    private void checkDeviceType() {
        for (DeviceGroupBean next : this.mDataList) {
            if (next.getType() == DeviceGroupBean.CAN_ADD_DEVICE) {
                for (DeviceInfoBean next2 : next.getDeviceList()) {
                    if (next2.isSelected()) {
                        this.mDeviceType = next2.getDeviceType();
                        return;
                    }
                }
                this.mDeviceType = 0;
            }
        }
    }
}

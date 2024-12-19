package com.mergbw.android.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.clj.fastble.BleManager;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.FragmentDeviceListBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.home.adapter.DeviceItemAdapter;
import com.mergbw.android.ui.home.adapter.DeviceItemGridAdapter;
import com.mergbw.android.ui.home.adapter.DeviceItemListAdapter;
import com.mergbw.android.ui.home.adapter.IDeviceListClickListener;
import com.mergbw.android.ui.home.viewModel.HomeViewModel;
import com.mergbw.core.R;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.views.ConfirmDialog;
import java.util.ArrayList;
import java.util.List;

public class DeviceListFragment extends BaseFragment {
    private static final int OPEN_BLUETOOTH = 200;
    private DeviceItemAdapter mAdapter;
    private FragmentDeviceListBinding mBinding;
    private List<DeviceInfoBean> mDeviceList = new ArrayList();
    private int mListType = UserinfoManage.getInstance().getListType();
    /* access modifiers changed from: private */
    public HomeViewModel mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentDeviceListBinding.inflate(layoutInflater);
        this.mViewModel = (HomeViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(HomeViewModel.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.btnAddDevice.setOnClickListener(new DeviceListFragment$$ExternalSyntheticLambda0());
        initListType();
    }

    static /* synthetic */ void lambda$onViewCreated$0(View view) {
        JumpManager.getInstance().jumpToAddDevice();
        TrackManager.getInstance().homeClickTrack(1, 1, 0);
        GoogleTrackManager.getInstance().homeClickTrack(1, 1, 0);
    }

    private void initListType() {
        if (this.mListType == 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
            linearLayoutManager.setOrientation(1);
            this.mBinding.listDevice.setLayoutManager(linearLayoutManager);
            this.mAdapter = new DeviceItemListAdapter();
        } else {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
            staggeredGridLayoutManager.setGapStrategy(0);
            this.mBinding.listDevice.setLayoutManager(staggeredGridLayoutManager);
            this.mAdapter = new DeviceItemGridAdapter();
        }
        this.mAdapter.setClickListener(new IDeviceListClickListener() {
            public void clickDeviceItem(DeviceInfoBean deviceInfoBean) {
                if (DeviceListFragment.this.checkBluetooth()) {
                    JumpManager.getInstance().jumpToDeviceDetail(deviceInfoBean);
                }
            }

            public void clickPower(DeviceInfoBean deviceInfoBean, boolean z) {
                byte[] bArr = {z ? (byte) 1 : 0};
                deviceInfoBean.setOpen(z);
                DeviceListFragment.this.mViewModel.sendData(deviceInfoBean, CommandUtils.getCommand((byte) 1, bArr));
            }
        });
        this.mBinding.listDevice.setAdapter(this.mAdapter);
        this.mAdapter.setData(this.mDeviceList);
        this.mAdapter.notifyDataSetChanged();
    }

    public void bluetoothReady() {
        subscribeUI();
    }

    private void subscribeUI() {
        this.mViewModel.setDeviceListDataObserver(this, new DeviceListFragment$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public void updateDeviceList(List<DeviceInfoBean> list) {
        if (list.isEmpty()) {
            this.mBinding.layoutNoDevice.setVisibility(0);
            this.mBinding.listDevice.setVisibility(8);
            return;
        }
        this.mBinding.layoutNoDevice.setVisibility(8);
        this.mBinding.listDevice.setVisibility(0);
        this.mDeviceList = list;
        this.mAdapter.setData(list);
        this.mAdapter.notifyDataSetChanged();
    }

    public void listTypeClick() {
        if (this.mListType == 1) {
            this.mListType = 2;
            UserinfoManage.getInstance().setListType(2);
        } else {
            this.mListType = 1;
            UserinfoManage.getInstance().setListType(1);
        }
        initListType();
    }

    /* access modifiers changed from: private */
    public boolean checkBluetooth() {
        if (BleManager.getInstance().isBlueEnable()) {
            return true;
        }
        new ConfirmDialog(this.mContext, getString(R.string.need_open_bluetooth_title), getString(R.string.need_open_bluetooth_content), getString(17039360), getString(17039370), new DeviceListFragment$$ExternalSyntheticLambda2(this)).show();
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkBluetooth$1$com-mergbw-android-ui-home-fragment-DeviceListFragment  reason: not valid java name */
    public /* synthetic */ void m859lambda$checkBluetooth$1$commergbwandroiduihomefragmentDeviceListFragment(boolean z) {
        if (z) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 200);
        }
    }
}

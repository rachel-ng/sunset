package com.mergbw.android.ui.addDevice;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityAddDeviceBinding;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.ui.BaseBluetoothActivity;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import java.util.List;

public class AddDeviceActivity extends BaseBluetoothActivity implements AddDeviceInterView {
    private AddDeviceAdapter mAdapter;
    private ActivityAddDeviceBinding mBinding;
    /* access modifiers changed from: private */
    public AddDeviceViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewModel = (AddDeviceViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(AddDeviceViewModel.class);
        ActivityAddDeviceBinding inflate = ActivityAddDeviceBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        this.mViewModel.setViewListener(this);
        this.mBinding.getRoot().post(new AddDeviceActivity$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mViewModel.cancelScan();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new AddDeviceActivity$$ExternalSyntheticLambda0(this));
        this.mAdapter = new AddDeviceAdapter(new AddDeviceActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.listDevice.setAdapter(this.mAdapter);
        this.mBinding.btnScanAgain.setOnClickListener(new AddDeviceActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.viewLoading.playAnimation();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-addDevice-AddDeviceActivity  reason: not valid java name */
    public /* synthetic */ void m697lambda$initViews$0$commergbwandroiduiaddDeviceAddDeviceActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-addDevice-AddDeviceActivity  reason: not valid java name */
    public /* synthetic */ void m698lambda$initViews$1$commergbwandroiduiaddDeviceAddDeviceActivity(View view) {
        this.mBinding.layoutDeviceList.setVisibility(8);
        this.mBinding.layoutNoDevice.setVisibility(8);
        this.mBinding.layoutSearch.setVisibility(0);
        this.mBinding.viewLoading.playAnimation();
        this.mViewModel.scanDevice();
    }

    /* access modifiers changed from: protected */
    public void bluetoothReady() {
        this.mViewModel.scanDevice();
    }

    /* access modifiers changed from: private */
    public void addDevice(DeviceInfoBean deviceInfoBean) {
        if (deviceInfoBean.getDeviceType() == 1) {
            showConnectingDialog();
            this.mViewModel.connectDevice(deviceInfoBean);
            return;
        }
        showAliasPop(deviceInfoBean);
    }

    public void updateDeviceList(List<DeviceInfoBean> list) {
        this.mAdapter.setData(list);
        this.mAdapter.notifyDataSetInvalidated();
        ActivityAddDeviceBinding activityAddDeviceBinding = this.mBinding;
        if (activityAddDeviceBinding != null) {
            activityAddDeviceBinding.layoutDeviceList.setVisibility(0);
            this.mBinding.layoutNoDevice.setVisibility(8);
            this.mBinding.layoutSearch.setVisibility(8);
            this.mBinding.viewLoading.pauseAnimation();
            String string = getString(R.string.add_device_tick_03);
            this.mBinding.tvScanTick.setText(String.format(string, new Object[]{Integer.valueOf(list.size())}));
        }
    }

    public void updateScanResult(boolean z) {
        ActivityAddDeviceBinding activityAddDeviceBinding;
        if (!z && (activityAddDeviceBinding = this.mBinding) != null) {
            activityAddDeviceBinding.layoutDeviceList.setVisibility(8);
            this.mBinding.layoutNoDevice.setVisibility(0);
            this.mBinding.layoutSearch.setVisibility(8);
            this.mBinding.viewLoading.pauseAnimation();
        }
    }

    public void updateAddResult(boolean z) {
        if (z) {
            showToast(getString(R.string.add_success));
            finish();
        }
    }

    public void updateBindResult(DeviceInfoBean deviceInfoBean, BindStatus bindStatus) {
        hideConnectingDialog();
        if (bindStatus == BindStatus.UNBIND) {
            showBindDialog();
        } else if (bindStatus == BindStatus.BOUND) {
            hideBindDialog();
            showAliasPop(deviceInfoBean);
        } else {
            hideBindDialog();
            showToast(getString(R.string.add_failed));
        }
    }

    private void showAliasPop(final DeviceInfoBean deviceInfoBean) {
        EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_alias), deviceInfoBean.getDeviceName(), getString(com.mergbw.core.R.string.skip), getString(com.mergbw.core.R.string.done), 1, new EditDialog.OnEditListener() {
            public void onSkip() {
                AddDeviceActivity.this.mViewModel.addDevice(deviceInfoBean);
            }

            public void onEditText(String str) {
                deviceInfoBean.setAliasName(str);
                AddDeviceActivity.this.mViewModel.addDevice(deviceInfoBean);
            }
        });
        editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        editPopWindow.setOnDismissListener(new AddDeviceActivity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showAliasPop$2$com-mergbw-android-ui-addDevice-AddDeviceActivity  reason: not valid java name */
    public /* synthetic */ void m699lambda$showAliasPop$2$commergbwandroiduiaddDeviceAddDeviceActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }
}

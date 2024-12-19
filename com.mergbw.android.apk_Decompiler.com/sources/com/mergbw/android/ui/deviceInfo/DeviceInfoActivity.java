package com.mergbw.android.ui.deviceInfo;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityDeviceInfoBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.Constants;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;

public class DeviceInfoActivity extends BaseActivity {
    private boolean canUpgrade = false;
    /* access modifiers changed from: private */
    public ActivityDeviceInfoBinding mBinding;
    private long[] mHits = new long[3];
    /* access modifiers changed from: private */
    public DeviceInfoViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBinding = ActivityDeviceInfoBinding.inflate(getLayoutInflater());
        this.mViewModel = (DeviceInfoViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceInfoViewModel.class);
        setContentView((View) this.mBinding.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new DeviceInfoActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.btnDeleteDevice.setOnClickListener(new DeviceInfoActivity$$ExternalSyntheticLambda3(this));
        this.mBinding.layoutDeviceAlias.setOnClickListener(new DeviceInfoActivity$$ExternalSyntheticLambda4(this));
        this.mBinding.layoutLedNum.setOnClickListener(new DeviceInfoActivity$$ExternalSyntheticLambda5(this));
        this.mBinding.layoutFirmwareUpdate.setOnClickListener(new DeviceInfoActivity$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m766lambda$initViews$0$commergbwandroiduideviceInfoDeviceInfoActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m768lambda$initViews$2$commergbwandroiduideviceInfoDeviceInfoActivity(View view) {
        new ConfirmDialog(this.mContext, getString(R.string.confirm), getString(R.string.remove_device_tick), getString(17039360), getString(17039370), new DeviceInfoActivity$$ExternalSyntheticLambda0(this)).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m767lambda$initViews$1$commergbwandroiduideviceInfoDeviceInfoActivity(boolean z) {
        if (z) {
            this.mViewModel.deleteDevice();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m770lambda$initViews$4$commergbwandroiduideviceInfoDeviceInfoActivity(View view) {
        EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_alias), this.mViewModel.getCurrentDevice().getAliasName(), getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
            public void onSkip() {
            }

            public void onEditText(String str) {
                DeviceInfoActivity.this.mViewModel.updateAliasName(str);
            }
        });
        editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        editPopWindow.setOnDismissListener(new DeviceInfoActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m769lambda$initViews$3$commergbwandroiduideviceInfoDeviceInfoActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$6$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m772lambda$initViews$6$commergbwandroiduideviceInfoDeviceInfoActivity(View view) {
        EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_led_num), this.mBinding.tvLedNum.getText().toString(), getString(17039360), getString(17039370), 2, new EditDialog.OnEditListener() {
            public void onSkip() {
            }

            public void onEditText(String str) {
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt < 1) {
                        DeviceInfoActivity deviceInfoActivity = DeviceInfoActivity.this;
                        deviceInfoActivity.showToast(deviceInfoActivity.getString(R.string.mix_led_num_tip));
                        parseInt = 1;
                    }
                    if (parseInt > 300) {
                        DeviceInfoActivity deviceInfoActivity2 = DeviceInfoActivity.this;
                        deviceInfoActivity2.showToast(deviceInfoActivity2.getString(R.string.max_led_num_tip));
                        parseInt = 300;
                    }
                    DeviceInfoActivity.this.mViewModel.setLedNum(parseInt);
                    DeviceInfoActivity.this.mBinding.tvLedNum.setText(String.valueOf(parseInt));
                } catch (NumberFormatException unused) {
                    DeviceInfoActivity deviceInfoActivity3 = DeviceInfoActivity.this;
                    deviceInfoActivity3.showToast(deviceInfoActivity3.getString(R.string.max_led_num_tip));
                }
            }
        });
        editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        editPopWindow.setOnDismissListener(new DeviceInfoActivity$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m771lambda$initViews$5$commergbwandroiduideviceInfoDeviceInfoActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$7$com-mergbw-android-ui-deviceInfo-DeviceInfoActivity  reason: not valid java name */
    public /* synthetic */ void m773lambda$initViews$7$commergbwandroiduideviceInfoDeviceInfoActivity(View view) {
        long[] jArr = this.mHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.mHits[0] >= SystemClock.uptimeMillis() - 500) {
            if (this.canUpgrade) {
                JumpManager.getInstance().jumpToDeviceUpgrade(this.mViewModel.getCurrentDevice());
            } else {
                showToast(getString(R.string.current_is_new));
            }
            this.mHits = new long[3];
        }
    }

    private void subscribeUI() {
        this.mViewModel.setDeviceViewDataObserver(this, new DeviceInfoActivity$$ExternalSyntheticLambda8(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setDeleteResultObserver(this, new DeviceInfoActivity$$ExternalSyntheticLambda9(this));
    }

    /* access modifiers changed from: private */
    public void updateDeviceInfo(DeviceInfoBean deviceInfoBean) {
        ActivityDeviceInfoBinding activityDeviceInfoBinding = this.mBinding;
        if (activityDeviceInfoBinding != null) {
            activityDeviceInfoBinding.tvDeviceName.setText(deviceInfoBean.getAliasName());
            this.mBinding.tvDeviceAlias.setText(deviceInfoBean.getAliasName());
            if (deviceInfoBean.isConnect()) {
                this.mBinding.tvFirmwareVersion.setText(String.format(getString(R.string.firmware_text), new Object[]{deviceInfoBean.getFirmwareVersion()}));
                this.mBinding.tvFirmwareTick.setText(deviceInfoBean.getFirmwareVersion());
            } else {
                this.mBinding.tvFirmwareVersion.setVisibility(4);
            }
            int deviceType = deviceInfoBean.getDeviceType();
            if (deviceType == 1) {
                this.mBinding.ivDeviceIcon.setImageResource(deviceInfoBean.isConnect() ? com.mergbw.core.R.mipmap.icon_device_letter : com.mergbw.core.R.mipmap.icon_device_disconnect);
                if (deviceInfoBean.isConnect()) {
                    checkVersion(deviceInfoBean.getFirmwareVersion());
                    this.mBinding.tvLedNum.setText(String.valueOf(deviceInfoBean.getLedNum()));
                    return;
                }
                this.mBinding.tvFirmwareVersion.setVisibility(4);
                this.mBinding.layoutLedNum.setVisibility(8);
                this.mBinding.layoutFirmwareUpdate.setVisibility(8);
            } else if (deviceType == 5) {
                this.mBinding.ivDeviceIcon.setImageResource(deviceInfoBean.isConnect() ? com.mergbw.core.R.mipmap.icon_device_sunlight : com.mergbw.core.R.mipmap.icon_device_disconnect_5);
                this.mBinding.layoutLedNum.setVisibility(8);
                this.mBinding.layoutFirmwareUpdate.setVisibility(8);
            } else if (deviceType == 6) {
                this.mBinding.ivDeviceIcon.setImageResource(deviceInfoBean.isConnect() ? com.mergbw.core.R.mipmap.icon_device_buble : com.mergbw.core.R.mipmap.icon_device_disconnect_6);
                this.mBinding.layoutLedNum.setVisibility(8);
                this.mBinding.layoutFirmwareUpdate.setVisibility(8);
            }
        }
    }

    private void checkVersion(String str) {
        if (str != null) {
            byte[] bytes = Constants.LOCAL_FIRMWARE_VERSION.getBytes();
            byte[] bytes2 = str.getBytes();
            for (int i = 0; i < bytes2.length; i++) {
                if (bytes2[i] < bytes[i]) {
                    this.canUpgrade = true;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onDeleteResult(boolean z) {
        if (z) {
            finish();
        }
    }
}

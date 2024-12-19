package com.mergbw.android.ui.deviceUpgrade;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityDeviceUpgradeBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.Constants;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.StringUtils;
import com.mergbw.core.utils.UIUtils;
import com.mergbw.core.utils.ViewDataUtils;
import com.ota66.sdk.OTASDKUtils;
import com.ota66.sdk.firware.UpdateFirewareCallBack;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeviceUpgradeActivity extends BaseActivity implements UpdateFirewareCallBack {
    private boolean isUpgrading = false;
    private ActivityDeviceUpgradeBinding mBinding;
    private DeviceUpgradeViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityDeviceUpgradeBinding inflate = ActivityDeviceUpgradeBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        this.mViewModel = (DeviceUpgradeViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceUpgradeViewModel.class);
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new DeviceUpgradeActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.btnUpgrade.setOnClickListener(new DeviceUpgradeActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.btnComplete.setOnClickListener(new DeviceUpgradeActivity$$ExternalSyntheticLambda2(this));
        TextView textView = this.mBinding.tvNewFirmware;
        textView.setText(getString(R.string.last_version) + Constants.LOCAL_FIRMWARE_VERSION);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m780lambda$initViews$0$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m781lambda$initViews$1$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(View view) {
        showWaitDialog();
        startOTA();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m782lambda$initViews$2$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(View view) {
        finish();
    }

    private void subscribeUI() {
        this.mViewModel.setDeviceViewDataObserver(this, new DeviceUpgradeActivity$$ExternalSyntheticLambda7(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setConnectStatusObserver(this, new DeviceUpgradeActivity$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: private */
    public void updateDeviceInfo(DeviceInfoBean deviceInfoBean) {
        TextView textView = this.mBinding.tvFirmwareVersion;
        textView.setText(getString(R.string.current_version) + deviceInfoBean.getFirmwareVersion());
    }

    private void startOTA() {
        RGBDeviceManager.getInstance().setUpgrade(true);
        RGBDeviceManager.getInstance().disconnectDevice(this.mViewModel.getCurrentDevice());
    }

    /* access modifiers changed from: private */
    public void onConnectStatus(BleStatus bleStatus) {
        if (bleStatus != BleStatus.DISCONNECTED) {
            return;
        }
        if (!RGBDeviceManager.getInstance().isUpgrade()) {
            finish();
        } else if (!this.isUpgrading) {
            this.isUpgrading = true;
            String str = ViewDataUtils.LOCAL_FIRMWARE.get(this.mViewModel.getCurrentDevice().getDeviceModel());
            if (StringUtils.isEmpty(str)) {
                str = ViewDataUtils.LOCAL_FIRMWARE_02.get(this.mViewModel.getCurrentDevice().getDeviceModel());
            }
            if (StringUtils.isEmpty(str)) {
                onError(-1);
                return;
            }
            String str2 = this.mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + str;
            MeRGBWLog.i("firmware file path: " + str2);
            new OTASDKUtils(this.mContext, this).updateFirware(this.mViewModel.getCurrentDevice().getDeviceMac(), str2);
        }
    }

    public void onError(int i) {
        MeRGBWLog.i("DeviceUpgradeActivity upgrade onError: " + i);
        RGBDeviceManager.getInstance().setUpgrade(false);
        this.isUpgrading = false;
        UpgradeErrorRecordBean upgradeErrorRecordBean = new UpgradeErrorRecordBean();
        upgradeErrorRecordBean.setDeviceName(this.mViewModel.getCurrentDevice().getDeviceName());
        upgradeErrorRecordBean.setDeviceType(this.mViewModel.getCurrentDevice().getDeviceType());
        upgradeErrorRecordBean.setDeviceModel(this.mViewModel.getCurrentDevice().getDeviceModel());
        upgradeErrorRecordBean.setFactoryID(this.mViewModel.getCurrentDevice().getFactoryID());
        upgradeErrorRecordBean.setAliasName(this.mViewModel.getCurrentDevice().getAliasName());
        upgradeErrorRecordBean.setErrorCode(i);
        upgradeErrorRecordBean.setRecordTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        this.mViewModel.addErrorRecord(upgradeErrorRecordBean);
        runOnUiThread(new DeviceUpgradeActivity$$ExternalSyntheticLambda6(this, upgradeErrorRecordBean));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$4$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m784lambda$onError$4$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        hideWaitDialog();
        new ConfirmDialog(this.mContext, getString(R.string.upgrade_failed), getString(R.string.upgrade_failed_sul), getString(17039360), getString(R.string.start_resume), new DeviceUpgradeActivity$$ExternalSyntheticLambda4(this, upgradeErrorRecordBean)).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$3$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m783lambda$onError$3$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(UpgradeErrorRecordBean upgradeErrorRecordBean, boolean z) {
        if (z) {
            JumpManager.getInstance().jumpToDeviceResume(upgradeErrorRecordBean);
        }
        finish();
    }

    public void onProcess(float f) {
        MeRGBWLog.i("DeviceUpgradeActivity upgrade onProcess: " + f);
        runOnUiThread(new DeviceUpgradeActivity$$ExternalSyntheticLambda3(this, f));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onProcess$5$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m785lambda$onProcess$5$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(float f) {
        hideWaitDialog();
        if (this.mBinding.layoutUpgrading.getVisibility() == 8) {
            this.mBinding.layoutBeforeUpgrade.setVisibility(8);
            this.mBinding.layoutUpgrading.setVisibility(0);
            this.mBinding.btnUpgrade.setVisibility(4);
            this.mBinding.ivBack.setVisibility(4);
        }
        if (this.mBinding.layoutUpgrading.getVisibility() == 0) {
            if (f > 90.0f) {
                this.mBinding.viewProgress.setWaveHeight(UIUtils.dp2px(100.0f - f));
            }
            int i = (int) f;
            this.mBinding.viewProgress.setProgress(i);
            TextView textView = this.mBinding.tvUpgradePercent;
            textView.setText(i + "%");
            if (f == 100.0f) {
                this.mBinding.viewProgress.setVisibility(4);
                this.mBinding.ivCompleteCover.setVisibility(0);
            }
        }
    }

    public void onUpdateComplete() {
        MeRGBWLog.i("DeviceUpgradeActivity upgrade onUpdateComplete");
        RGBDeviceManager.getInstance().setUpgrade(false);
        this.isUpgrading = false;
        RGBDeviceManager.getInstance().connectDevice(this.mViewModel.getCurrentDevice());
        runOnUiThread(new DeviceUpgradeActivity$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUpdateComplete$6$com-mergbw-android-ui-deviceUpgrade-DeviceUpgradeActivity  reason: not valid java name */
    public /* synthetic */ void m786lambda$onUpdateComplete$6$commergbwandroiduideviceUpgradeDeviceUpgradeActivity() {
        this.mBinding.btnComplete.setVisibility(0);
        this.mBinding.layoutHint.setVisibility(8);
        this.mBinding.ivBack.setVisibility(0);
        this.mBinding.tvUpgradeTick.setText(R.string.upgrade_success);
    }
}

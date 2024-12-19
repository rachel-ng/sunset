package com.mergbw.android.ui.deviceUpgrade;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityDeviceResumeBinding;
import com.mergbw.android.ui.deviceUpgrade.adapter.ErrorRecordAdapter;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.StringUtils;
import com.mergbw.core.utils.UIUtils;
import com.mergbw.core.utils.ViewDataUtils;
import com.ota66.sdk.OTASDKUtils;
import com.ota66.sdk.firware.UpdateFirewareCallBack;
import java.util.List;
import java.util.Objects;

public class DeviceResumeActivity extends BaseActivity implements UpdateFirewareCallBack {
    private ErrorRecordAdapter mAdapter;
    private ActivityDeviceResumeBinding mBinding;
    private UpgradeErrorRecordBean mCurrentRecord;
    private DeviceUpgradeViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityDeviceResumeBinding inflate = ActivityDeviceResumeBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        this.mViewModel = (DeviceUpgradeViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceUpgradeViewModel.class);
        this.mCurrentRecord = (UpgradeErrorRecordBean) getIntent().getSerializableExtra("errorRecord");
        initViews();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new DeviceResumeActivity$$ExternalSyntheticLambda3(this));
        this.mBinding.listRecord.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mAdapter = new ErrorRecordAdapter();
        this.mBinding.listRecord.setAdapter(this.mAdapter);
        this.mAdapter.setClickListener(new DeviceResumeActivity$$ExternalSyntheticLambda4(this));
        if (this.mCurrentRecord != null) {
            this.mBinding.layoutRecord.setVisibility(8);
            this.mBinding.layoutResume.setVisibility(0);
        } else {
            this.mViewModel.setErrorRecordListDataObserver(this, new DeviceResumeActivity$$ExternalSyntheticLambda5(this));
        }
        this.mBinding.btnUpgrade.setOnClickListener(new DeviceResumeActivity$$ExternalSyntheticLambda6(this));
        this.mBinding.btnComplete.setOnClickListener(new DeviceResumeActivity$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m774lambda$initViews$0$commergbwandroiduideviceUpgradeDeviceResumeActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m775lambda$initViews$1$commergbwandroiduideviceUpgradeDeviceResumeActivity(UpgradeErrorRecordBean upgradeErrorRecordBean) {
        this.mCurrentRecord = upgradeErrorRecordBean;
        this.mBinding.layoutRecord.setVisibility(8);
        this.mBinding.layoutResume.setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m776lambda$initViews$2$commergbwandroiduideviceUpgradeDeviceResumeActivity(View view) {
        showWaitDialog();
        scanDevice();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m777lambda$initViews$3$commergbwandroiduideviceUpgradeDeviceResumeActivity(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public void updateRecordList(List<UpgradeErrorRecordBean> list) {
        if (list != null && !list.isEmpty()) {
            this.mAdapter.setData(list);
        }
    }

    private void scanDevice() {
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().build());
        BleManager.getInstance().scan(new BleScanCallback() {
            public void onScanFinished(List<BleDevice> list) {
            }

            public void onScanStarted(boolean z) {
            }

            public void onScanning(BleDevice bleDevice) {
                if (bleDevice.getName() != null && bleDevice.getMac() != null) {
                    MeRGBWLog.i("BleDevice: " + bleDevice.getName() + " data: " + StringUtils.byte2HexStr(bleDevice.getScanRecord()));
                    if (Objects.equals(bleDevice.getName(), "PPlusOTA")) {
                        DeviceResumeActivity.this.cancelScan();
                        DeviceResumeActivity.this.startOTA(bleDevice);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void cancelScan() {
        if (BleManager.getInstance().getScanSate() == BleScanState.STATE_SCANNING) {
            BleManager.getInstance().cancelScan();
        }
    }

    /* access modifiers changed from: private */
    public void startOTA(BleDevice bleDevice) {
        String str = ViewDataUtils.LOCAL_FIRMWARE.get(this.mCurrentRecord.getDeviceModel());
        if (StringUtils.isEmpty(str)) {
            str = ViewDataUtils.LOCAL_FIRMWARE_02.get(this.mCurrentRecord.getDeviceModel());
        }
        if (StringUtils.isEmpty(str)) {
            onError(-1);
            return;
        }
        String str2 = this.mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + str;
        MeRGBWLog.i("firmware file path: " + str2);
        new OTASDKUtils(this.mContext, this).updateFirware(bleDevice.getMac(), str2);
    }

    public void onError(int i) {
        MeRGBWLog.i("DeviceUpgradeActivity upgrade onError: " + i);
        runOnUiThread(new DeviceResumeActivity$$ExternalSyntheticLambda1(this));
    }

    public void onProcess(float f) {
        MeRGBWLog.i("DeviceUpgradeActivity upgrade onProcess: " + f);
        runOnUiThread(new DeviceResumeActivity$$ExternalSyntheticLambda2(this, f));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onProcess$4$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m778lambda$onProcess$4$commergbwandroiduideviceUpgradeDeviceResumeActivity(float f) {
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
        this.mViewModel.deleteErrorRecord(this.mCurrentRecord);
        RGBDeviceManager.getInstance().connectDevice(this.mCurrentRecord.getDeviceMac());
        runOnUiThread(new DeviceResumeActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUpdateComplete$5$com-mergbw-android-ui-deviceUpgrade-DeviceResumeActivity  reason: not valid java name */
    public /* synthetic */ void m779lambda$onUpdateComplete$5$commergbwandroiduideviceUpgradeDeviceResumeActivity() {
        this.mBinding.btnComplete.setVisibility(0);
        this.mBinding.layoutHint.setVisibility(8);
        this.mBinding.ivBack.setVisibility(0);
        this.mBinding.tvUpgradeTick.setText(R.string.resume_success);
    }
}

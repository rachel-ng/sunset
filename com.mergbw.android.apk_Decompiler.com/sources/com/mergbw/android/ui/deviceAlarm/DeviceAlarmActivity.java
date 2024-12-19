package com.mergbw.android.ui.deviceAlarm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.mergbw.android.databinding.ActivityDeviceAlarmBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.ui.view.ChooseTimePopWindow;
import com.mergbw.core.R;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.CommandUtils;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.StringUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class DeviceAlarmActivity extends BaseActivity {
    private ActivityDeviceAlarmBinding mBinding;
    private DeviceAlarmViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewModel = (DeviceAlarmViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceAlarmViewModel.class);
        ActivityDeviceAlarmBinding inflate = ActivityDeviceAlarmBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new DeviceAlarmActivity$$ExternalSyntheticLambda8(this));
        this.mBinding.switchOpen.setOnCheckedChangeListener(new DeviceAlarmActivity$$ExternalSyntheticLambda9(this));
        this.mBinding.switchClose.setOnCheckedChangeListener(new DeviceAlarmActivity$$ExternalSyntheticLambda10(this));
        this.mBinding.btnSave.setOnClickListener(new DeviceAlarmActivity$$ExternalSyntheticLambda11(this));
        this.mBinding.cbOpenWeek1.getViewTreeObserver().addOnGlobalLayoutListener(new DeviceAlarmActivity$$ExternalSyntheticLambda12(this, this.mBinding.cbOpenWeek1.getLayoutParams()));
        this.mBinding.cbCloseWeek1.getViewTreeObserver().addOnGlobalLayoutListener(new DeviceAlarmActivity$$ExternalSyntheticLambda13(this, this.mBinding.cbCloseWeek1.getLayoutParams()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m702lambda$initViews$0$commergbwandroiduideviceAlarmDeviceAlarmActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m703lambda$initViews$1$commergbwandroiduideviceAlarmDeviceAlarmActivity(CompoundButton compoundButton, boolean z) {
        setOpenEnable(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m704lambda$initViews$2$commergbwandroiduideviceAlarmDeviceAlarmActivity(CompoundButton compoundButton, boolean z) {
        setCloseEnable(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m705lambda$initViews$3$commergbwandroiduideviceAlarmDeviceAlarmActivity(View view) {
        setAlarm();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m706lambda$initViews$4$commergbwandroiduideviceAlarmDeviceAlarmActivity(ViewGroup.LayoutParams layoutParams) {
        layoutParams.height = this.mBinding.cbOpenWeek1.getWidth();
        this.mBinding.cbOpenWeek1.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m707lambda$initViews$5$commergbwandroiduideviceAlarmDeviceAlarmActivity(ViewGroup.LayoutParams layoutParams) {
        layoutParams.height = this.mBinding.cbCloseWeek1.getWidth();
        this.mBinding.cbCloseWeek1.setLayoutParams(layoutParams);
    }

    private void subscribeUI() {
        this.mViewModel.setConnectStatusObserver(this, new DeviceAlarmActivity$$ExternalSyntheticLambda3(this));
        this.mViewModel.setDeviceViewDataObserver(this, new DeviceAlarmActivity$$ExternalSyntheticLambda4(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setSetResultObserver(this, new DeviceAlarmActivity$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: private */
    public void onConnectStatus(BleStatus bleStatus) {
        if (bleStatus == BleStatus.DISCONNECTED) {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void updateDeviceInfo(DeviceInfoBean deviceInfoBean) {
        if (this.mBinding != null && deviceInfoBean != null && deviceInfoBean.getOpenAlarm() != null && deviceInfoBean.getCloseAlarm() != null) {
            this.mBinding.switchOpen.setChecked(deviceInfoBean.getOpenAlarm().isSet());
            setOpenEnable(deviceInfoBean.getOpenAlarm().isSet());
            this.mBinding.tvOpenRepeatTime.setText(ViewDataUtils.getTimeStr(deviceInfoBean.getOpenAlarm().getTimeHour(), deviceInfoBean.getOpenAlarm().getTimeMinute()));
            boolean z = false;
            this.mBinding.cbOpenWeek1.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[7] == 1);
            this.mBinding.cbOpenWeek2.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[6] == 1);
            this.mBinding.cbOpenWeek3.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[5] == 1);
            this.mBinding.cbOpenWeek4.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[4] == 1);
            this.mBinding.cbOpenWeek5.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[3] == 1);
            this.mBinding.cbOpenWeek6.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[2] == 1);
            this.mBinding.cbOpenWeek7.setChecked(deviceInfoBean.getOpenAlarm().getWeekRepeat()[1] == 1);
            this.mBinding.switchClose.setChecked(deviceInfoBean.getCloseAlarm().isSet());
            setCloseEnable(deviceInfoBean.getCloseAlarm().isSet());
            this.mBinding.tvCloseRepeatTime.setText(ViewDataUtils.getTimeStr(deviceInfoBean.getCloseAlarm().getTimeHour(), deviceInfoBean.getCloseAlarm().getTimeMinute()));
            this.mBinding.cbCloseWeek1.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[7] == 1);
            this.mBinding.cbCloseWeek2.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[6] == 1);
            this.mBinding.cbCloseWeek3.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[5] == 1);
            this.mBinding.cbCloseWeek4.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[4] == 1);
            this.mBinding.cbCloseWeek5.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[3] == 1);
            this.mBinding.cbCloseWeek6.setChecked(deviceInfoBean.getCloseAlarm().getWeekRepeat()[2] == 1);
            CheckBox checkBox = this.mBinding.cbCloseWeek7;
            if (deviceInfoBean.getCloseAlarm().getWeekRepeat()[1] == 1) {
                z = true;
            }
            checkBox.setChecked(z);
        }
    }

    /* access modifiers changed from: private */
    public void setResult(boolean z) {
        new Handler(Looper.getMainLooper()).postDelayed(new DeviceAlarmActivity$$ExternalSyntheticLambda14(this, z), ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setResult$6$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m714lambda$setResult$6$commergbwandroiduideviceAlarmDeviceAlarmActivity(boolean z) {
        hideWaitDialog();
        if (z) {
            showToast(getString(R.string.set_success));
        } else {
            showToast(getString(R.string.set_failed));
        }
    }

    private void setOpenEnable(boolean z) {
        if (z) {
            this.mBinding.tvOpenTitle.setAlpha(0.9f);
            this.mBinding.tvOpenTime.setAlpha(1.0f);
            this.mBinding.tvOpenRepeat.setAlpha(0.7f);
            this.mBinding.tvOpenRepeatTime.setAlpha(0.7f);
            this.mBinding.layoutOpenTime.setOnClickListener(new DeviceAlarmActivity$$ExternalSyntheticLambda6(this));
        } else {
            this.mBinding.tvOpenTitle.setAlpha(0.54f);
            this.mBinding.tvOpenTime.setAlpha(0.6f);
            this.mBinding.tvOpenRepeat.setAlpha(0.42f);
            this.mBinding.tvOpenRepeatTime.setAlpha(0.42f);
            this.mBinding.layoutOpenTime.setOnClickListener((View.OnClickListener) null);
        }
        this.mBinding.cbOpenWeek1.setEnabled(z);
        this.mBinding.cbOpenWeek2.setEnabled(z);
        this.mBinding.cbOpenWeek3.setEnabled(z);
        this.mBinding.cbOpenWeek4.setEnabled(z);
        this.mBinding.cbOpenWeek5.setEnabled(z);
        this.mBinding.cbOpenWeek6.setEnabled(z);
        this.mBinding.cbOpenWeek7.setEnabled(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOpenEnable$9$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m713lambda$setOpenEnable$9$commergbwandroiduideviceAlarmDeviceAlarmActivity(View view) {
        ChooseTimePopWindow chooseTimePopWindow = new ChooseTimePopWindow(this.mContext, this.mViewModel.getDeviceInfo().getOpenAlarm() != null ? this.mViewModel.getDeviceInfo().getOpenAlarm().getTimeHour() : 0, this.mViewModel.getDeviceInfo().getOpenAlarm() != null ? this.mViewModel.getDeviceInfo().getOpenAlarm().getTimeMinute() : 0, new DeviceAlarmActivity$$ExternalSyntheticLambda15(this));
        chooseTimePopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        chooseTimePopWindow.setOnDismissListener(new DeviceAlarmActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOpenEnable$7$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m711lambda$setOpenEnable$7$commergbwandroiduideviceAlarmDeviceAlarmActivity(int i, int i2) {
        this.mBinding.tvOpenRepeatTime.setText(ViewDataUtils.getTimeStr(i, i2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOpenEnable$8$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m712lambda$setOpenEnable$8$commergbwandroiduideviceAlarmDeviceAlarmActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    private void setCloseEnable(boolean z) {
        if (z) {
            this.mBinding.tvCloseTitle.setAlpha(0.9f);
            this.mBinding.tvCloseTime.setAlpha(1.0f);
            this.mBinding.tvCloseRepeat.setAlpha(0.7f);
            this.mBinding.tvCloseRepeatTime.setAlpha(0.7f);
            this.mBinding.layoutCloseTime.setOnClickListener(new DeviceAlarmActivity$$ExternalSyntheticLambda2(this));
        } else {
            this.mBinding.tvCloseTitle.setAlpha(0.54f);
            this.mBinding.tvCloseTime.setAlpha(0.6f);
            this.mBinding.tvCloseRepeat.setAlpha(0.42f);
            this.mBinding.tvCloseRepeatTime.setAlpha(0.42f);
            this.mBinding.layoutCloseTime.setOnClickListener((View.OnClickListener) null);
        }
        this.mBinding.cbCloseWeek1.setEnabled(z);
        this.mBinding.cbCloseWeek2.setEnabled(z);
        this.mBinding.cbCloseWeek3.setEnabled(z);
        this.mBinding.cbCloseWeek4.setEnabled(z);
        this.mBinding.cbCloseWeek5.setEnabled(z);
        this.mBinding.cbCloseWeek6.setEnabled(z);
        this.mBinding.cbCloseWeek7.setEnabled(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCloseEnable$12$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m710lambda$setCloseEnable$12$commergbwandroiduideviceAlarmDeviceAlarmActivity(View view) {
        ChooseTimePopWindow chooseTimePopWindow = new ChooseTimePopWindow(this.mContext, this.mViewModel.getDeviceInfo().getCloseAlarm() != null ? this.mViewModel.getDeviceInfo().getCloseAlarm().getTimeHour() : 0, this.mViewModel.getDeviceInfo().getCloseAlarm() != null ? this.mViewModel.getDeviceInfo().getCloseAlarm().getTimeMinute() : 0, new DeviceAlarmActivity$$ExternalSyntheticLambda0(this));
        chooseTimePopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        chooseTimePopWindow.setOnDismissListener(new DeviceAlarmActivity$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCloseEnable$10$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m708lambda$setCloseEnable$10$commergbwandroiduideviceAlarmDeviceAlarmActivity(int i, int i2) {
        this.mBinding.tvCloseRepeatTime.setText(ViewDataUtils.getTimeStr(i, i2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCloseEnable$11$com-mergbw-android-ui-deviceAlarm-DeviceAlarmActivity  reason: not valid java name */
    public /* synthetic */ void m709lambda$setCloseEnable$11$commergbwandroiduideviceAlarmDeviceAlarmActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    private void setAlarm() {
        byte isChecked = this.mBinding.switchOpen.isChecked();
        String[] split = this.mBinding.tvOpenRepeatTime.getText().toString().split(":");
        byte b2 = CommandUtils.getByteArray(Integer.parseInt(split[0]))[1];
        byte b3 = CommandUtils.getByteArray(Integer.parseInt(split[1]))[1];
        String str = SessionDescription.SUPPORTED_SDP_VERSION;
        StringBuilder sb = new StringBuilder(str);
        sb.append(this.mBinding.cbOpenWeek7.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek6.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek5.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek4.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek3.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek2.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb.append(this.mBinding.cbOpenWeek1.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        byte BitToByte = StringUtils.BitToByte(sb.toString());
        byte isChecked2 = this.mBinding.switchClose.isChecked();
        String[] split2 = this.mBinding.tvCloseRepeatTime.getText().toString().split(":");
        byte b4 = CommandUtils.getByteArray(Integer.parseInt(split2[0]))[1];
        byte b5 = CommandUtils.getByteArray(Integer.parseInt(split2[1]))[1];
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(this.mBinding.cbCloseWeek7.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb2.append(this.mBinding.cbCloseWeek6.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb2.append(this.mBinding.cbCloseWeek5.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb2.append(this.mBinding.cbCloseWeek4.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb2.append(this.mBinding.cbCloseWeek3.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        sb2.append(this.mBinding.cbCloseWeek2.isChecked() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : str);
        if (this.mBinding.cbCloseWeek1.isChecked()) {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        sb2.append(str);
        this.mViewModel.sendData(CommandUtils.getCommand((byte) 10, new byte[]{isChecked, b2, b3, BitToByte, isChecked2, b4, b5, StringUtils.BitToByte(sb2.toString())}));
        showWaitDialog();
        TrackManager.getInstance().alarmSettingTrack();
        GoogleTrackManager.getInstance().alarmSettingTrack();
    }
}

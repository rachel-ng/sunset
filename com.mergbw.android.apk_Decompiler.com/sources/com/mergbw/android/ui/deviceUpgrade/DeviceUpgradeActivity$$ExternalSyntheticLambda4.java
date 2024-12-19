package com.mergbw.android.ui.deviceUpgrade;

import com.mergbw.core.database.bean.UpgradeErrorRecordBean;
import com.mergbw.core.ui.views.ConfirmDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeviceUpgradeActivity$$ExternalSyntheticLambda4 implements ConfirmDialog.OnChooseListener {
    public final /* synthetic */ DeviceUpgradeActivity f$0;
    public final /* synthetic */ UpgradeErrorRecordBean f$1;

    public /* synthetic */ DeviceUpgradeActivity$$ExternalSyntheticLambda4(DeviceUpgradeActivity deviceUpgradeActivity, UpgradeErrorRecordBean upgradeErrorRecordBean) {
        this.f$0 = deviceUpgradeActivity;
        this.f$1 = upgradeErrorRecordBean;
    }

    public final void OnConfirmResult(boolean z) {
        this.f$0.m783lambda$onError$3$commergbwandroiduideviceUpgradeDeviceUpgradeActivity(this.f$1, z);
    }
}

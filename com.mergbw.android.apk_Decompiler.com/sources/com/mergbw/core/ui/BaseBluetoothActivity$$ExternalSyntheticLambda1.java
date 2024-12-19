package com.mergbw.core.ui;

import com.mergbw.core.ui.views.ConfirmDialog;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BaseBluetoothActivity$$ExternalSyntheticLambda1 implements ConfirmDialog.OnChooseListener {
    public final /* synthetic */ BaseBluetoothActivity f$0;
    public final /* synthetic */ String[] f$1;

    public /* synthetic */ BaseBluetoothActivity$$ExternalSyntheticLambda1(BaseBluetoothActivity baseBluetoothActivity, String[] strArr) {
        this.f$0 = baseBluetoothActivity;
        this.f$1 = strArr;
    }

    public final void OnConfirmResult(boolean z) {
        this.f$0.m914lambda$checkBluePermission$1$commergbwcoreuiBaseBluetoothActivity(this.f$1, z);
    }
}

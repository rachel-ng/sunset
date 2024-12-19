package com.mergbw.android.ui;

import android.view.View;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.database.bean.UpgradeErrorRecordBean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AboutActivity$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final void onClick(View view) {
        JumpManager.getInstance().jumpToDeviceResume((UpgradeErrorRecordBean) null);
    }
}

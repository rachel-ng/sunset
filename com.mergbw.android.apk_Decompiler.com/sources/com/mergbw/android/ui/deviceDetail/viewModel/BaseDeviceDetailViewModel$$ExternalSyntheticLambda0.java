package com.mergbw.android.ui.deviceDetail.viewModel;

import com.mergbw.android.task.IAudioRecordListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BaseDeviceDetailViewModel$$ExternalSyntheticLambda0 implements IAudioRecordListener {
    public final /* synthetic */ BaseDeviceDetailViewModel f$0;

    public /* synthetic */ BaseDeviceDetailViewModel$$ExternalSyntheticLambda0(BaseDeviceDetailViewModel baseDeviceDetailViewModel) {
        this.f$0 = baseDeviceDetailViewModel;
    }

    public final void onColorResult(int i) {
        this.f$0.sendAudioColor(i);
    }
}

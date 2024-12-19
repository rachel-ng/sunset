package com.mergbw.android.ui.groupDetail.viewModel;

import com.mergbw.android.task.IAudioRecordListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BaseGroupDetailViewModel$$ExternalSyntheticLambda0 implements IAudioRecordListener {
    public final /* synthetic */ BaseGroupDetailViewModel f$0;

    public /* synthetic */ BaseGroupDetailViewModel$$ExternalSyntheticLambda0(BaseGroupDetailViewModel baseGroupDetailViewModel) {
        this.f$0 = baseGroupDetailViewModel;
    }

    public final void onColorResult(int i) {
        this.f$0.sendAudioColor(i);
    }
}

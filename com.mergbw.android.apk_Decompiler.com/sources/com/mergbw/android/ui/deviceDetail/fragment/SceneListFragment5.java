package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter5;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel5;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.ViewDataUtils;

public class SceneListFragment5 extends BaseSceneListFragment {
    private SceneItemAdapter5 mItemAdapter;
    private DeviceDetailViewModel5 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = (DeviceDetailViewModel5) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel5.class);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listScene.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        SceneItemAdapter5 sceneItemAdapter5 = new SceneItemAdapter5();
        this.mItemAdapter = sceneItemAdapter5;
        sceneItemAdapter5.setClickListener(new SceneListFragment5$$ExternalSyntheticLambda0(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getSceneData5());
        this.mItemAdapter.notifyItemInserted(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-SceneListFragment5  reason: not valid java name */
    public /* synthetic */ void m757lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentSceneListFragment5(SceneData sceneData) {
        MeRGBWLog.i("sceneData.getType(): " + sceneData.getMode());
        this.mViewModel.stopGetAudio();
        this.mViewModel.setScene(sceneData);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    public void onAudioReady() {
        this.mViewModel.startVoiceData(70, -1);
    }

    public void resetSelected(int i) {
        SceneItemAdapter5 sceneItemAdapter5;
        if (i == 2 && (sceneItemAdapter5 = this.mItemAdapter) != null) {
            sceneItemAdapter5.resetSelected();
        }
    }
}

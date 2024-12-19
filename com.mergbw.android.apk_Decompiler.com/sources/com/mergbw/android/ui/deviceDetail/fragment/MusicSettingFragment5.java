package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.FragmentMusicSettingBinding;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter6;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel5;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.ViewDataUtils;

public class MusicSettingFragment5 extends BaseMusicFragment {
    private SceneData mAudioData;
    /* access modifiers changed from: private */
    public int mAudioSensitive;
    public FragmentMusicSettingBinding mBinding;
    private SceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel5 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = (DeviceDetailViewModel5) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel5.class);
        FragmentMusicSettingBinding inflate = FragmentMusicSettingBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mAudioSensitive = UserinfoManage.getInstance().getAudioSensitive();
        this.mBinding.sbSensitive.setProgress(this.mAudioSensitive);
        TextView textView = this.mBinding.tvSensitivePercent;
        textView.setText(this.mAudioSensitive + "%");
        this.mBinding.sbSensitive.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView textView = MusicSettingFragment5.this.mBinding.tvSensitivePercent;
                textView.setText(i + "%");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int unused = MusicSettingFragment5.this.mAudioSensitive = seekBar.getProgress();
                UserinfoManage.getInstance().setAudioSensitive(MusicSettingFragment5.this.mAudioSensitive);
                MusicSettingFragment5.this.mViewModel.setAudioSensitive(MusicSettingFragment5.this.mAudioSensitive);
            }
        });
        this.mBinding.listMusicMode.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mItemAdapter = new SceneItemAdapter6();
        this.mBinding.listMusicMode.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getMusicModel6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mItemAdapter.setClickListener(new MusicSettingFragment5$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-MusicSettingFragment5  reason: not valid java name */
    public /* synthetic */ void m753lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentMusicSettingFragment5(SceneData sceneData) {
        this.mAudioData = sceneData;
        checkAudioPermission();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    public void onAudioReady() {
        super.onAudioReady();
        this.mViewModel.startVoiceData(this.mAudioSensitive, this.mAudioData.getSceneIndex());
    }

    public void resetSelected(int i) {
        if (i == 2) {
            this.mItemAdapter.resetSelected();
            this.mViewModel.stopGetAudio();
        }
    }
}

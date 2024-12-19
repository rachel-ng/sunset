package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.FragmentGroupMusicSetting6Binding;
import com.mergbw.android.ui.deviceDetail.fragment.BaseMusicFragment;
import com.mergbw.android.ui.groupDetail.adapter.GroupSceneItemAdapter6;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel6;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.ViewDataUtils;

public class GroupMusicSettingFragment6 extends BaseMusicFragment {
    private SceneData mAudioData;
    /* access modifiers changed from: private */
    public int mAudioSensitive;
    /* access modifiers changed from: private */
    public FragmentGroupMusicSetting6Binding mBinding;
    private GroupSceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public GroupDetailViewModel6 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = (GroupDetailViewModel6) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel6.class);
        FragmentGroupMusicSetting6Binding inflate = FragmentGroupMusicSetting6Binding.inflate(layoutInflater);
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
                TextView textView = GroupMusicSettingFragment6.this.mBinding.tvSensitivePercent;
                textView.setText(i + "%");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int unused = GroupMusicSettingFragment6.this.mAudioSensitive = seekBar.getProgress();
                UserinfoManage.getInstance().setAudioSensitive(GroupMusicSettingFragment6.this.mAudioSensitive);
                GroupMusicSettingFragment6.this.mViewModel.setAudioSensitive(GroupMusicSettingFragment6.this.mAudioSensitive);
            }
        });
        this.mBinding.listMusicMode.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mItemAdapter = new GroupSceneItemAdapter6();
        this.mBinding.listMusicMode.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getMusicModel6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mItemAdapter.setClickListener(new GroupMusicSettingFragment6$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupMusicSettingFragment6  reason: not valid java name */
    public /* synthetic */ void m826lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupMusicSettingFragment6(SceneData sceneData) {
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

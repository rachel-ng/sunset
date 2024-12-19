package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import com.mergbw.android.databinding.FragmentMusicSettingBinding;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel1;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.utils.ViewDataUtils;

public class GroupMusicSettingFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public FragmentMusicSettingBinding mBinding;
    private SceneItemAdapter mItemAdapter;
    /* access modifiers changed from: private */
    public GroupDetailViewModel1 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentMusicSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel1.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.sbSensitive.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView textView = GroupMusicSettingFragment.this.mBinding.tvSensitivePercent;
                textView.setText(i + "%");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                GroupMusicSettingFragment.this.mViewModel.setMusicSens(seekBar.getProgress());
            }
        });
        this.mBinding.listMusicMode.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        SceneItemAdapter sceneItemAdapter = new SceneItemAdapter();
        this.mItemAdapter = sceneItemAdapter;
        sceneItemAdapter.setClickListener(new GroupMusicSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listMusicMode.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getMusicModel());
        this.mItemAdapter.notifyItemInserted(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupMusicSettingFragment  reason: not valid java name */
    public /* synthetic */ void m824lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupMusicSettingFragment(SceneData sceneData) {
        this.mViewModel.setMusicModel(sceneData.getSceneIndex());
        ((BaseActivity) requireActivity()).resetSelected();
    }

    public void resetSelected(int i) {
        if (i == 2) {
            this.mItemAdapter.resetSelected();
        }
    }
}

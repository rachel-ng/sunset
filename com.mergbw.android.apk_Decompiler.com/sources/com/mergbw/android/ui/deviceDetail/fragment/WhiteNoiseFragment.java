package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.mergbw.android.databinding.FragmentWhiteNoiseListBinding;
import com.mergbw.android.ui.deviceDetail.adapter.IAudioItemClickListener;
import com.mergbw.android.ui.deviceDetail.adapter.WhiteNoiseItemAdapter;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.utils.ViewDataUtils;

public class WhiteNoiseFragment extends BaseMusicFragment {
    private FragmentWhiteNoiseListBinding mBinding;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentWhiteNoiseListBinding.inflate(layoutInflater);
        bindService();
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listNoise.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        WhiteNoiseItemAdapter whiteNoiseItemAdapter = new WhiteNoiseItemAdapter();
        this.mBinding.listNoise.setAdapter(whiteNoiseItemAdapter);
        whiteNoiseItemAdapter.setClickListener(new IAudioItemClickListener() {
            public void startPlay(SceneData sceneData) {
                if (WhiteNoiseFragment.this.mAudioService != null) {
                    WhiteNoiseFragment.this.mAudioService.startPlay(WhiteNoiseFragment.this.requireContext(), sceneData.getSceneIndex());
                }
            }

            public void pause() {
                if (WhiteNoiseFragment.this.mAudioService != null) {
                    WhiteNoiseFragment.this.mAudioService.pausePlay();
                }
            }

            public void resume() {
                if (WhiteNoiseFragment.this.mAudioService != null) {
                    WhiteNoiseFragment.this.mAudioService.resumePlay();
                }
            }
        });
        whiteNoiseItemAdapter.setData(ViewDataUtils.getWhiteNoiseModel());
        whiteNoiseItemAdapter.notifyItemInserted(0);
    }

    public void onDestroy() {
        super.onDestroy();
        unBindService();
    }
}

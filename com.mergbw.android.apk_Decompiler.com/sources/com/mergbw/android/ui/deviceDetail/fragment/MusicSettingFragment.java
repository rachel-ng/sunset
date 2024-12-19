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
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mergbw.android.databinding.FragmentMusicSettingBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel1;
import com.mergbw.core.Constants;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.network.factory.FactoryDataHelper;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.UIUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class MusicSettingFragment extends BaseMusicFragment {
    public FragmentMusicSettingBinding mBinding;
    private FactoryInfoBean mFactoryInfo;
    private SceneItemAdapter mItemAdapter;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel1 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = (DeviceDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel1.class);
        FragmentMusicSettingBinding inflate = FragmentMusicSettingBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        int sensitive = (int) ((((double) (this.mViewModel.getCurrentDevice().getSensitive() - Constants.MIN_SENSITIVE_VALUE)) / ((double) (100 - Constants.MIN_SENSITIVE_VALUE))) * 100.0d);
        this.mBinding.sbSensitive.setProgress(sensitive);
        TextView textView = this.mBinding.tvSensitivePercent;
        textView.setText(sensitive + "%");
        this.mBinding.sbSensitive.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView textView = MusicSettingFragment.this.mBinding.tvSensitivePercent;
                textView.setText(i + "%");
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                MusicSettingFragment.this.mViewModel.setMusicSens(seekBar.getProgress());
            }
        });
        this.mBinding.listMusicMode.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        SceneItemAdapter sceneItemAdapter = new SceneItemAdapter();
        this.mItemAdapter = sceneItemAdapter;
        sceneItemAdapter.setClickListener(new MusicSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listMusicMode.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getMusicModel());
        this.mItemAdapter.notifyItemInserted(0);
        this.mViewModel.setFactoryInfoObserver(this, new MusicSettingFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.ivFactoryArea.setOnClickListener(new MusicSettingFragment$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-MusicSettingFragment  reason: not valid java name */
    public /* synthetic */ void m751lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentMusicSettingFragment(SceneData sceneData) {
        this.mViewModel.setMusicModel(sceneData.getSceneIndex());
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-deviceDetail-fragment-MusicSettingFragment  reason: not valid java name */
    public /* synthetic */ void m752lambda$onViewCreated$1$commergbwandroiduideviceDetailfragmentMusicSettingFragment(View view) {
        JumpManager.getInstance().jumpToFactoryArea(this.mFactoryInfo);
    }

    /* access modifiers changed from: private */
    public void updateFactoryInfo(FactoryInfoBean factoryInfoBean) {
        this.mFactoryInfo = factoryInfoBean;
        if (factoryInfoBean == null) {
            return;
        }
        if (factoryInfoBean.getRoleType() >= 2) {
            this.mBinding.ivFactoryArea.setVisibility(0);
            RequestManager with = Glide.with(this.mContext);
            ((RequestBuilder) with.load(FactoryDataHelper.getFactoryFileAddress() + factoryInfoBean.getPromotionPicture()).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners((int) UIUtils.dp2px(12.0f)))).centerCrop()).into(this.mBinding.ivFactoryArea);
            return;
        }
        this.mBinding.ivFactoryArea.setVisibility(8);
    }

    public void resetSelected(int i) {
        if (i == 2) {
            this.mItemAdapter.resetSelected();
        }
    }
}

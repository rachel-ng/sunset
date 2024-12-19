package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.mergbw.android.databinding.FragmentIlluminatingSettingBinding;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter6;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel6;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;
import com.mergbw.core.ui.views.LineColorPickerView;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class IlluminatingSettingFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public int lastLineColor = 0;
    private FragmentIlluminatingSettingBinding mBinding;
    /* access modifiers changed from: private */
    public SingleColorItemAdapter mClassicAdapter;
    /* access modifiers changed from: private */
    public SceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel6 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentIlluminatingSettingBinding.inflate(layoutInflater);
        this.mViewModel = (DeviceDetailViewModel6) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel6.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listScene.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        SceneItemAdapter6 sceneItemAdapter6 = new SceneItemAdapter6();
        this.mItemAdapter = sceneItemAdapter6;
        sceneItemAdapter6.setClickListener(new IlluminatingSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getIlluminatingData6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mBinding.listClassicColor.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        SingleColorItemAdapter singleColorItemAdapter = new SingleColorItemAdapter();
        this.mClassicAdapter = singleColorItemAdapter;
        singleColorItemAdapter.setData(ColorUtils.getWarmColorList());
        this.mBinding.listClassicColor.setAdapter(this.mClassicAdapter);
        this.mClassicAdapter.setClickListener(new IlluminatingSettingFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.viewColorLine.setColors(ColorUtils.getWarmColorLineList());
        this.mBinding.viewColorLine.setOnColorPickerChangeListener(new LineColorPickerView.OnColorPickerChangeListener() {
            public void onStartTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onStopTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onColorChanged(LineColorPickerView lineColorPickerView, int i) {
                if (i != IlluminatingSettingFragment.this.lastLineColor) {
                    int unused = IlluminatingSettingFragment.this.lastLineColor = i;
                    IlluminatingSettingFragment.this.mViewModel.setWarmColor(i);
                    IlluminatingSettingFragment.this.mClassicAdapter.refreshSelected(i);
                    IlluminatingSettingFragment.this.mItemAdapter.resetSelected();
                    ((BaseActivity) IlluminatingSettingFragment.this.requireActivity()).resetSelected();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-IlluminatingSettingFragment  reason: not valid java name */
    public /* synthetic */ void m749lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentIlluminatingSettingFragment(SceneData sceneData) {
        this.mViewModel.setIlluminating(sceneData);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-deviceDetail-fragment-IlluminatingSettingFragment  reason: not valid java name */
    public /* synthetic */ void m750lambda$onViewCreated$1$commergbwandroiduideviceDetailfragmentIlluminatingSettingFragment(ColorBean colorBean) {
        this.mViewModel.setWarmColor(colorBean.getColorValue());
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    public void resetSelected(int i) {
        SceneItemAdapter6 sceneItemAdapter6;
        if (i == 2 && (sceneItemAdapter6 = this.mItemAdapter) != null) {
            sceneItemAdapter6.resetSelected();
        }
    }
}

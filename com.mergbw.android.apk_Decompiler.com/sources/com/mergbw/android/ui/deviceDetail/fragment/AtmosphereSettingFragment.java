package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.mergbw.android.databinding.FragmentAtmosphereSettingBinding;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter6;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel6;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;
import com.mergbw.core.ui.views.ColorPiePopWindow;
import com.mergbw.core.ui.views.LineColorPickerView;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class AtmosphereSettingFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public int lastLineColor = 0;
    private FragmentAtmosphereSettingBinding mBinding;
    /* access modifiers changed from: private */
    public SingleColorItemAdapter mClassicAdapter;
    /* access modifiers changed from: private */
    public SceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel6 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentAtmosphereSettingBinding.inflate(layoutInflater);
        this.mViewModel = (DeviceDetailViewModel6) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel6.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listScene.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        SceneItemAdapter6 sceneItemAdapter6 = new SceneItemAdapter6();
        this.mItemAdapter = sceneItemAdapter6;
        sceneItemAdapter6.setClickListener(new AtmosphereSettingFragment$$ExternalSyntheticLambda2(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getSceneData6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mBinding.listClassicColor.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        SingleColorItemAdapter singleColorItemAdapter = new SingleColorItemAdapter();
        this.mClassicAdapter = singleColorItemAdapter;
        singleColorItemAdapter.setData(ColorUtils.getAtmosphereColorList());
        this.mBinding.listClassicColor.setAdapter(this.mClassicAdapter);
        this.mClassicAdapter.setClickListener(new AtmosphereSettingFragment$$ExternalSyntheticLambda3(this));
        this.mBinding.viewColorLine.setOnColorPickerChangeListener(new LineColorPickerView.OnColorPickerChangeListener() {
            public void onStartTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onStopTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onColorChanged(LineColorPickerView lineColorPickerView, int i) {
                if (i != AtmosphereSettingFragment.this.lastLineColor) {
                    int unused = AtmosphereSettingFragment.this.lastLineColor = i;
                    AtmosphereSettingFragment.this.mViewModel.setColor(i);
                    AtmosphereSettingFragment.this.mClassicAdapter.refreshSelected(i);
                    AtmosphereSettingFragment.this.mItemAdapter.resetSelected();
                    ((BaseActivity) AtmosphereSettingFragment.this.requireActivity()).resetSelected();
                }
            }
        });
        this.mBinding.layoutColorPie.setOnClickListener(new AtmosphereSettingFragment$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-AtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m730lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentAtmosphereSettingFragment(SceneData sceneData) {
        this.mViewModel.setIlluminating(sceneData);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-deviceDetail-fragment-AtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m731lambda$onViewCreated$1$commergbwandroiduideviceDetailfragmentAtmosphereSettingFragment(ColorBean colorBean) {
        this.mViewModel.setColor(colorBean.getColorValue());
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$4$com-mergbw-android-ui-deviceDetail-fragment-AtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m734lambda$onViewCreated$4$commergbwandroiduideviceDetailfragmentAtmosphereSettingFragment(View view) {
        ColorPiePopWindow colorPiePopWindow = new ColorPiePopWindow(this.mContext, new AtmosphereSettingFragment$$ExternalSyntheticLambda0(this));
        colorPiePopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
        attributes.alpha = 0.3f;
        requireActivity().getWindow().setAttributes(attributes);
        colorPiePopWindow.setOnDismissListener(new AtmosphereSettingFragment$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$2$com-mergbw-android-ui-deviceDetail-fragment-AtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m732lambda$onViewCreated$2$commergbwandroiduideviceDetailfragmentAtmosphereSettingFragment(int i) {
        this.mViewModel.setColor(i);
        this.mClassicAdapter.refreshSelected(i);
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$3$com-mergbw-android-ui-deviceDetail-fragment-AtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m733lambda$onViewCreated$3$commergbwandroiduideviceDetailfragmentAtmosphereSettingFragment() {
        WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
        attributes.alpha = 1.0f;
        requireActivity().getWindow().setAttributes(attributes);
    }

    public void resetSelected(int i) {
        SceneItemAdapter6 sceneItemAdapter6;
        if (i == 2 && (sceneItemAdapter6 = this.mItemAdapter) != null) {
            sceneItemAdapter6.resetSelected();
        }
    }
}

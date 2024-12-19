package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.databinding.FragmentGroupAtmosphereSettingBinding;
import com.mergbw.android.ui.groupDetail.adapter.GroupSceneItemAdapter6;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel6;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;
import com.mergbw.core.ui.views.ColorPiePopWindow;
import com.mergbw.core.ui.views.LineColorPickerView;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class GroupAtmosphereSettingFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public int lastLineColor = 0;
    private FragmentGroupAtmosphereSettingBinding mBinding;
    /* access modifiers changed from: private */
    public SingleColorItemAdapter mClassicAdapter;
    /* access modifiers changed from: private */
    public GroupSceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public GroupDetailViewModel6 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentGroupAtmosphereSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel6) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel6.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listScene.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        GroupSceneItemAdapter6 groupSceneItemAdapter6 = new GroupSceneItemAdapter6();
        this.mItemAdapter = groupSceneItemAdapter6;
        groupSceneItemAdapter6.setClickListener(new GroupAtmosphereSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getSceneData6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mBinding.listClassicColor.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        SingleColorItemAdapter singleColorItemAdapter = new SingleColorItemAdapter();
        this.mClassicAdapter = singleColorItemAdapter;
        singleColorItemAdapter.setData(ColorUtils.getAtmosphereColorList());
        this.mBinding.listClassicColor.setAdapter(this.mClassicAdapter);
        this.mClassicAdapter.setClickListener(new GroupAtmosphereSettingFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.viewColorLine.setOnColorPickerChangeListener(new LineColorPickerView.OnColorPickerChangeListener() {
            public void onStartTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onStopTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onColorChanged(LineColorPickerView lineColorPickerView, int i) {
                if (i != GroupAtmosphereSettingFragment.this.lastLineColor) {
                    int unused = GroupAtmosphereSettingFragment.this.lastLineColor = i;
                    GroupAtmosphereSettingFragment.this.mViewModel.setRGBColor(i);
                    GroupAtmosphereSettingFragment.this.mClassicAdapter.refreshSelected(i);
                    GroupAtmosphereSettingFragment.this.mItemAdapter.resetSelected();
                    ((BaseActivity) GroupAtmosphereSettingFragment.this.requireActivity()).resetSelected();
                }
            }
        });
        this.mBinding.layoutColorPie.setOnClickListener(new GroupAtmosphereSettingFragment$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupAtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m812lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupAtmosphereSettingFragment(SceneData sceneData) {
        this.mViewModel.setIlluminating(sceneData);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-groupDetail-fragment-GroupAtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m813lambda$onViewCreated$1$commergbwandroiduigroupDetailfragmentGroupAtmosphereSettingFragment(ColorBean colorBean) {
        this.mViewModel.setRGBColor(colorBean.getColorValue());
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$4$com-mergbw-android-ui-groupDetail-fragment-GroupAtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m816lambda$onViewCreated$4$commergbwandroiduigroupDetailfragmentGroupAtmosphereSettingFragment(View view) {
        ColorPiePopWindow colorPiePopWindow = new ColorPiePopWindow(this.mContext, new GroupAtmosphereSettingFragment$$ExternalSyntheticLambda3(this));
        colorPiePopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
        attributes.alpha = 0.3f;
        requireActivity().getWindow().setAttributes(attributes);
        colorPiePopWindow.setOnDismissListener(new GroupAtmosphereSettingFragment$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$2$com-mergbw-android-ui-groupDetail-fragment-GroupAtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m814lambda$onViewCreated$2$commergbwandroiduigroupDetailfragmentGroupAtmosphereSettingFragment(int i) {
        this.mViewModel.setRGBColor(i);
        this.mClassicAdapter.refreshSelected(i);
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$3$com-mergbw-android-ui-groupDetail-fragment-GroupAtmosphereSettingFragment  reason: not valid java name */
    public /* synthetic */ void m815lambda$onViewCreated$3$commergbwandroiduigroupDetailfragmentGroupAtmosphereSettingFragment() {
        WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
        attributes.alpha = 1.0f;
        requireActivity().getWindow().setAttributes(attributes);
    }

    public void resetSelected(int i) {
        GroupSceneItemAdapter6 groupSceneItemAdapter6;
        if (i == 2 && (groupSceneItemAdapter6 = this.mItemAdapter) != null) {
            groupSceneItemAdapter6.resetSelected();
        }
    }
}

package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.databinding.FragmentGroupIlluminatingSettingBinding;
import com.mergbw.android.ui.groupDetail.adapter.GroupSceneItemAdapter6;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel6;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.SingleColorItemAdapter;
import com.mergbw.core.ui.views.LineColorPickerView;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.ViewDataUtils;

public class GroupIlluminatingSettingFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public int lastLineColor = 0;
    private FragmentGroupIlluminatingSettingBinding mBinding;
    /* access modifiers changed from: private */
    public SingleColorItemAdapter mClassicAdapter;
    /* access modifiers changed from: private */
    public GroupSceneItemAdapter6 mItemAdapter;
    /* access modifiers changed from: private */
    public GroupDetailViewModel6 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentGroupIlluminatingSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel6) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel6.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.listScene.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        GroupSceneItemAdapter6 groupSceneItemAdapter6 = new GroupSceneItemAdapter6();
        this.mItemAdapter = groupSceneItemAdapter6;
        groupSceneItemAdapter6.setClickListener(new GroupIlluminatingSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter);
        this.mItemAdapter.setData(ViewDataUtils.getIlluminatingData6());
        this.mItemAdapter.notifyItemInserted(0);
        this.mBinding.listClassicColor.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        SingleColorItemAdapter singleColorItemAdapter = new SingleColorItemAdapter();
        this.mClassicAdapter = singleColorItemAdapter;
        singleColorItemAdapter.setData(ColorUtils.getWarmColorList());
        this.mBinding.listClassicColor.setAdapter(this.mClassicAdapter);
        this.mClassicAdapter.setClickListener(new GroupIlluminatingSettingFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.viewColorLine.setColors(ColorUtils.getWarmColorLineList());
        this.mBinding.viewColorLine.setOnColorPickerChangeListener(new LineColorPickerView.OnColorPickerChangeListener() {
            public void onStartTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onStopTrackingTouch(LineColorPickerView lineColorPickerView) {
            }

            public void onColorChanged(LineColorPickerView lineColorPickerView, int i) {
                if (i != GroupIlluminatingSettingFragment.this.lastLineColor) {
                    int unused = GroupIlluminatingSettingFragment.this.lastLineColor = i;
                    GroupIlluminatingSettingFragment.this.mViewModel.setWarmColor(i);
                    GroupIlluminatingSettingFragment.this.mClassicAdapter.refreshSelected(i);
                    GroupIlluminatingSettingFragment.this.mItemAdapter.resetSelected();
                    ((BaseActivity) GroupIlluminatingSettingFragment.this.requireActivity()).resetSelected();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupIlluminatingSettingFragment  reason: not valid java name */
    public /* synthetic */ void m822lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupIlluminatingSettingFragment(SceneData sceneData) {
        this.mViewModel.setIlluminating(sceneData);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-groupDetail-fragment-GroupIlluminatingSettingFragment  reason: not valid java name */
    public /* synthetic */ void m823lambda$onViewCreated$1$commergbwandroiduigroupDetailfragmentGroupIlluminatingSettingFragment(ColorBean colorBean) {
        this.mViewModel.setWarmColor(colorBean.getColorValue());
        this.mItemAdapter.resetSelected();
        ((BaseActivity) requireActivity()).resetSelected();
    }

    public void resetSelected(int i) {
        GroupSceneItemAdapter6 groupSceneItemAdapter6;
        if (i == 2 && (groupSceneItemAdapter6 = this.mItemAdapter) != null) {
            groupSceneItemAdapter6.resetSelected();
        }
    }
}

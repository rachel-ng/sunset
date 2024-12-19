package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.FragmentGroupWarmColorSettingBinding;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel5;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;

public class GroupWarmColorSettingFragment extends BaseFragment {
    private FragmentGroupWarmColorSettingBinding mBinding;
    private GroupDetailViewModel5 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentGroupWarmColorSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel5) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel5.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.viewWarmColorPie.subscribe(new GroupWarmColorSettingFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupWarmColorSettingFragment  reason: not valid java name */
    public /* synthetic */ void m831lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupWarmColorSettingFragment(int i, boolean z, boolean z2) {
        if (z) {
            this.mViewModel.setRGBColor(i);
            ((BaseActivity) requireActivity()).resetSelected();
        }
    }
}

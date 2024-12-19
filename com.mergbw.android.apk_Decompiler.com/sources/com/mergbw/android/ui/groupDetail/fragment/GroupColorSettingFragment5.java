package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.FragmentColorSettingBinding;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel5;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import java.util.List;

public class GroupColorSettingFragment5 extends BaseFragment {
    private FragmentColorSettingBinding mBinding;
    private GroupDetailViewModel5 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentColorSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel5) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel5.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.viewCommonColorPicker.setOnColorPickListener(new GroupColorSettingFragment5$$ExternalSyntheticLambda0(this));
        this.mBinding.btnSave.setOnClickListener(new GroupColorSettingFragment5$$ExternalSyntheticLambda1(this));
        this.mViewModel.setCommonColorListDataObserver(this, new GroupColorSettingFragment5$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupColorSettingFragment5  reason: not valid java name */
    public /* synthetic */ void m820lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupColorSettingFragment5(int i) {
        this.mViewModel.setRGBColor(i);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-groupDetail-fragment-GroupColorSettingFragment5  reason: not valid java name */
    public /* synthetic */ void m821lambda$onViewCreated$1$commergbwandroiduigroupDetailfragmentGroupColorSettingFragment5(View view) {
        this.mViewModel.saveSingleColor();
    }

    /* access modifiers changed from: private */
    public void updateCommonColor(List<ColorBean> list) {
        FragmentColorSettingBinding fragmentColorSettingBinding = this.mBinding;
        if (fragmentColorSettingBinding != null) {
            fragmentColorSettingBinding.viewCommonColorPicker.setCommonColor(list);
        }
    }
}

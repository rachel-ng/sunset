package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.FragmentColorSettingBinding;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel1;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import java.util.List;

public class GroupColorSettingFragment extends BaseFragment {
    private FragmentColorSettingBinding mBinding;
    private GroupDetailViewModel1 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentColorSettingBinding.inflate(layoutInflater);
        this.mViewModel = (GroupDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel1.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.viewCommonColorPicker.setOnColorPickListener(new GroupColorSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.btnSave.setOnClickListener(new GroupColorSettingFragment$$ExternalSyntheticLambda1(this));
        this.mViewModel.setCommonColorListDataObserver(this, new GroupColorSettingFragment$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupColorSettingFragment  reason: not valid java name */
    public /* synthetic */ void m818lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupColorSettingFragment(int i) {
        this.mViewModel.setRGBColor(i);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-groupDetail-fragment-GroupColorSettingFragment  reason: not valid java name */
    public /* synthetic */ void m819lambda$onViewCreated$1$commergbwandroiduigroupDetailfragmentGroupColorSettingFragment(View view) {
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

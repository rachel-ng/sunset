package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.databinding.FragmentColorSettingBinding;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel5;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import java.util.List;

public class ColorSettingFragment5 extends BaseFragment {
    private FragmentColorSettingBinding mBinding;
    private DeviceDetailViewModel5 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentColorSettingBinding.inflate(layoutInflater);
        this.mViewModel = (DeviceDetailViewModel5) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel5.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mBinding.viewCommonColorPicker.setOnColorPickListener(new ColorSettingFragment5$$ExternalSyntheticLambda0(this));
        this.mBinding.btnSave.setOnClickListener(new ColorSettingFragment5$$ExternalSyntheticLambda1(this));
        this.mViewModel.setCommonColorListDataObserver(this, new ColorSettingFragment5$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-ColorSettingFragment5  reason: not valid java name */
    public /* synthetic */ void m740lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentColorSettingFragment5(int i) {
        this.mViewModel.setColor(i);
        ((BaseActivity) requireActivity()).resetSelected();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-deviceDetail-fragment-ColorSettingFragment5  reason: not valid java name */
    public /* synthetic */ void m741lambda$onViewCreated$1$commergbwandroiduideviceDetailfragmentColorSettingFragment5(View view) {
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
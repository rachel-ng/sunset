package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.mergbw.android.R;
import com.mergbw.android.databinding.FragmentDiySettingBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;

public class DIYSettingFragment extends BaseFragment {
    private FragmentDiySettingBinding mBinding;
    private BaseFragment mCurrentFragment;
    private BaseFragment mDiyListPage;
    private BaseFragment mSubColorPage;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentDiySettingBinding inflate = FragmentDiySettingBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.rgDiyType.check(R.id.rb_sub);
        this.mSubColorPage = new SubColorListFragment();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_content, this.mSubColorPage);
        beginTransaction.commit();
        this.mCurrentFragment = this.mSubColorPage;
        this.mBinding.rgDiyType.setOnCheckedChangeListener(new DIYSettingFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-DIYSettingFragment  reason: not valid java name */
    public /* synthetic */ void m748lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentDIYSettingFragment(RadioGroup radioGroup, int i) {
        if (i == R.id.rb_sub) {
            if (this.mSubColorPage == null) {
                this.mSubColorPage = new SubColorListFragment();
            }
            switchContent(this.mSubColorPage);
        } else if (i == R.id.rb_diy) {
            if (this.mDiyListPage == null) {
                this.mDiyListPage = new DIYListFragment();
            }
            switchContent(this.mDiyListPage);
        }
    }

    public void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(R.id.layout_content, (Fragment) baseFragment).commit();
            } else {
                beginTransaction.hide(this.mCurrentFragment).show(baseFragment).commit();
            }
            this.mCurrentFragment = baseFragment;
        }
    }

    public void resetSelected(int i) {
        if (i == 2) {
            BaseFragment baseFragment = this.mSubColorPage;
            if (baseFragment != null) {
                baseFragment.resetSelected(1);
            }
            BaseFragment baseFragment2 = this.mDiyListPage;
            if (baseFragment2 != null) {
                baseFragment2.resetSelected(1);
            }
        }
        if (i == 3) {
            ((BaseActivity) requireActivity()).resetSelected();
            BaseFragment baseFragment3 = this.mSubColorPage;
            if (!(baseFragment3 == null || baseFragment3 == this.mCurrentFragment)) {
                baseFragment3.resetSelected(1);
            }
            BaseFragment baseFragment4 = this.mDiyListPage;
            if (baseFragment4 != null && baseFragment4 != this.mCurrentFragment) {
                baseFragment4.resetSelected(1);
            }
        }
    }
}

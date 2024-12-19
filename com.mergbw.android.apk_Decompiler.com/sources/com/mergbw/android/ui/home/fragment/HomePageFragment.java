package com.mergbw.android.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.mergbw.android.R;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.FragmentHomePageBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseFragment;

public class HomePageFragment extends BaseFragment {
    private FragmentHomePageBinding mBinding;
    private BaseFragment mCurrentFragment;
    private DeviceGroupFragment mDeviceGroupPage;
    private DeviceListFragment mDeviceListPage;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentHomePageBinding.inflate(layoutInflater);
        initViews();
        return this.mBinding.getRoot();
    }

    private void initViews() {
        this.mBinding.rgTitleBar.setOnCheckedChangeListener(new HomePageFragment$$ExternalSyntheticLambda0(this));
        refreshListIcon();
        this.mBinding.ivIcon.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.ivAdd.setOnClickListener(new HomePageFragment$$ExternalSyntheticLambda2());
        this.mDeviceListPage = new DeviceListFragment();
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_child_content, this.mDeviceListPage);
        beginTransaction.commit();
        this.mCurrentFragment = this.mDeviceListPage;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-home-fragment-HomePageFragment  reason: not valid java name */
    public /* synthetic */ void m860lambda$initViews$0$commergbwandroiduihomefragmentHomePageFragment(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.rb_device) {
            if (this.mDeviceListPage == null) {
                this.mDeviceListPage = new DeviceListFragment();
            }
            switchContent(this.mDeviceListPage);
            this.mBinding.ivAdd.setVisibility(0);
            this.mBinding.ivIcon.setVisibility(0);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_group) {
            if (this.mDeviceGroupPage == null) {
                this.mDeviceGroupPage = new DeviceGroupFragment();
            }
            switchContent(this.mDeviceGroupPage);
            this.mBinding.ivAdd.setVisibility(8);
            this.mBinding.ivIcon.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-home-fragment-HomePageFragment  reason: not valid java name */
    public /* synthetic */ void m861lambda$initViews$1$commergbwandroiduihomefragmentHomePageFragment(View view) {
        DeviceListFragment deviceListFragment = this.mDeviceListPage;
        if (deviceListFragment != null) {
            deviceListFragment.listTypeClick();
        }
        refreshListIcon();
    }

    static /* synthetic */ void lambda$initViews$2(View view) {
        JumpManager.getInstance().jumpToAddDevice();
        TrackManager.getInstance().homeClickTrack(1, 2, 0);
        GoogleTrackManager.getInstance().homeClickTrack(1, 2, 0);
    }

    public void bluetoothReady() {
        DeviceListFragment deviceListFragment = this.mDeviceListPage;
        if (deviceListFragment != null) {
            deviceListFragment.bluetoothReady();
        }
    }

    private void refreshListIcon() {
        if (UserinfoManage.getInstance().getListType() == 1) {
            this.mBinding.ivIcon.setImageResource(R.mipmap.icon_list_grid);
        } else {
            this.mBinding.ivIcon.setImageResource(R.mipmap.icon_list);
        }
    }

    public void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(R.id.layout_child_content, (Fragment) baseFragment).commit();
            } else {
                beginTransaction.hide(this.mCurrentFragment).show(baseFragment).commit();
            }
            this.mCurrentFragment = baseFragment;
        }
    }
}

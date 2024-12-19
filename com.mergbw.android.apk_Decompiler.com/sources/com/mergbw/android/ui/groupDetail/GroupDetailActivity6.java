package com.mergbw.android.ui.groupDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityGroupDetail6Binding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.deviceDetail.adapter.TabItemAdapter;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.android.ui.groupDetail.adapter.GroupDeviceItemAdapter;
import com.mergbw.android.ui.groupDetail.adapter.IDeviceItemClickListener;
import com.mergbw.android.ui.groupDetail.fragment.GroupAtmosphereSettingFragment;
import com.mergbw.android.ui.groupDetail.fragment.GroupIlluminatingSettingFragment;
import com.mergbw.android.ui.groupDetail.fragment.GroupMusicSettingFragment6;
import com.mergbw.android.ui.groupDetail.viewModel.BaseGroupDetailViewModel;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel1;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel5;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel6;
import com.mergbw.core.bean.TabItemBean;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GroupDetailActivity6 extends BaseActivity {
    private ActivityGroupDetail6Binding mBinding;
    private BaseFragment mCurrentFragment;
    private GroupDeviceItemAdapter mDeviceAdapter;
    private final Map<Integer, BaseFragment> mPageList = new HashMap();
    private TabItemAdapter mTabAdapter;
    /* access modifiers changed from: private */
    public BaseGroupDetailViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityGroupDetail6Binding inflate = ActivityGroupDetail6Binding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        initData();
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new GroupDetailActivity6$$ExternalSyntheticLambda4(this));
        this.mBinding.ivItemSwitch.setOnCheckedChangeListener(new GroupDetailActivity6$$ExternalSyntheticLambda5(this));
        this.mBinding.sbBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                MeRGBWLog.i("onStartTrackingTouch " + seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                GroupDetailActivity6.this.mViewModel.setBrightness(seekBar.getProgress());
            }
        });
        this.mBinding.ivSetting.setOnClickListener(new GroupDetailActivity6$$ExternalSyntheticLambda6(this));
        this.mBinding.listTab.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mTabAdapter = new TabItemAdapter();
        this.mBinding.listTab.setAdapter(this.mTabAdapter);
        this.mTabAdapter.setClickListener(new GroupDetailActivity6$$ExternalSyntheticLambda7(this));
        this.mBinding.listDevice.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        this.mDeviceAdapter = new GroupDeviceItemAdapter();
        this.mBinding.listDevice.setAdapter(this.mDeviceAdapter);
        this.mDeviceAdapter.setClickListener(new IDeviceItemClickListener() {
            public void checkItem(DeviceInfoBean deviceInfoBean, boolean z) {
                GroupDetailActivity6.this.mViewModel.changeChecked(deviceInfoBean, z);
                GroupDetailActivity6.this.updateCheckAll();
            }

            public void clickItem(DeviceInfoBean deviceInfoBean) {
                JumpManager.getInstance().jumpToDeviceDetail(deviceInfoBean);
            }

            public void clickPower(DeviceInfoBean deviceInfoBean, boolean z) {
                GroupDetailActivity6.this.mViewModel.setPowerSingle(deviceInfoBean, z);
            }

            public void clickConnect(DeviceInfoBean deviceInfoBean) {
                RGBDeviceManager.getInstance().connectDevice(deviceInfoBean);
            }
        });
        this.mBinding.cbCheckAll.setOnCheckedChangeListener(new GroupDetailActivity6$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-groupDetail-GroupDetailActivity6  reason: not valid java name */
    public /* synthetic */ void m802lambda$initViews$0$commergbwandroiduigroupDetailGroupDetailActivity6(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-groupDetail-GroupDetailActivity6  reason: not valid java name */
    public /* synthetic */ void m803lambda$initViews$1$commergbwandroiduigroupDetailGroupDetailActivity6(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            this.mViewModel.setPower(z);
            TrackManager.getInstance().groupDetailTrack(2, (GroupItemBean) null, z ? 1 : 0);
            GoogleTrackManager.getInstance().groupDetailTrack(2, (GroupItemBean) null, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-groupDetail-GroupDetailActivity6  reason: not valid java name */
    public /* synthetic */ void m804lambda$initViews$2$commergbwandroiduigroupDetailGroupDetailActivity6(View view) {
        JumpManager.getInstance().jumpToGroupSetting(this.mViewModel.getCurrentGroup());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-groupDetail-GroupDetailActivity6  reason: not valid java name */
    public /* synthetic */ void m805lambda$initViews$3$commergbwandroiduigroupDetailGroupDetailActivity6(TabItemBean tabItemBean) {
        BaseFragment baseFragment = this.mPageList.get(Integer.valueOf(tabItemBean.getId()));
        if (baseFragment == null) {
            baseFragment = getTabPage(tabItemBean.getId());
            this.mPageList.put(Integer.valueOf(tabItemBean.getId()), baseFragment);
        }
        switchContent(baseFragment);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-android-ui-groupDetail-GroupDetailActivity6  reason: not valid java name */
    public /* synthetic */ void m806lambda$initViews$4$commergbwandroiduigroupDetailGroupDetailActivity6(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            this.mDeviceAdapter.updateCheckAll(z);
            this.mViewModel.changeCheckedAll(z);
        }
    }

    private void initData() {
        GroupItemBean groupItemBean = (GroupItemBean) getIntent().getSerializableExtra("group");
        if (groupItemBean != null) {
            this.mBinding.tvGroupName.setText(groupItemBean.getGroupName());
            int deviceType = groupItemBean.getDeviceType();
            if (deviceType == 1) {
                this.mViewModel = (BaseGroupDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupDetailViewModel1.class);
            } else if (deviceType == 5) {
                this.mViewModel = (BaseGroupDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupDetailViewModel5.class);
            } else if (deviceType == 6) {
                this.mViewModel = (BaseGroupDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupDetailViewModel6.class);
            }
            this.mViewModel.setGroupInfoDataObserver(this, new GroupDetailActivity6$$ExternalSyntheticLambda0(this));
            this.mViewModel.setSingleDeviceInfoDataObserver(this, new GroupDetailActivity6$$ExternalSyntheticLambda1(this));
            this.mViewModel.setBrightnessInfoObserver(this, new GroupDetailActivity6$$ExternalSyntheticLambda2(this));
            this.mViewModel.setShowLoadingObserver(this, new GroupDetailActivity6$$ExternalSyntheticLambda3(this));
            this.mViewModel.initData(getIntent());
            this.mTabAdapter.setData(ViewDataUtils.getGroupTabList(groupItemBean.getDeviceType()));
            initFirstPage();
            TrackManager.getInstance().groupDetailTrack(1, groupItemBean, 0);
            GoogleTrackManager.getInstance().groupDetailTrack(1, groupItemBean, 0);
        }
    }

    /* access modifiers changed from: private */
    public void updateBrightnessInfo(BrightnessInfo brightnessInfo) {
        ActivityGroupDetail6Binding activityGroupDetail6Binding = this.mBinding;
        if (activityGroupDetail6Binding != null) {
            activityGroupDetail6Binding.sbBrightness.setEnabled(brightnessInfo.isEnable());
            this.mBinding.sbBrightness.setProgress(brightnessInfo.getBrightness());
        }
    }

    private void initFirstPage() {
        TabItemBean tabItemBean = this.mTabAdapter.getData().get(0);
        BaseFragment tabPage = getTabPage(tabItemBean.getId());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_content, tabPage);
        beginTransaction.commit();
        this.mPageList.put(Integer.valueOf(tabItemBean.getId()), tabPage);
        this.mCurrentFragment = tabPage;
    }

    /* access modifiers changed from: private */
    public void updateGroupInfo(GroupItemBean groupItemBean) {
        if (this.mBinding != null && groupItemBean != null) {
            String string = AppUtils.getApp().getString(R.string.group_device_num);
            this.mBinding.tvItemModel.setText(String.format(string, new Object[]{Integer.valueOf(groupItemBean.getDeviceList().size())}));
            this.mDeviceAdapter.setData(groupItemBean.getDeviceList());
            this.mBinding.ivItemSwitch.setChecked(true);
            this.mViewModel.setPower(true);
        }
    }

    /* access modifiers changed from: private */
    public void updateSingleDeviceInfo(DeviceInfoBean deviceInfoBean) {
        for (int i = 0; i < this.mDeviceAdapter.getData().size(); i++) {
            if (deviceInfoBean.getDeviceMac().equals(this.mDeviceAdapter.getData().get(i).getDeviceMac())) {
                this.mDeviceAdapter.getData().set(i, deviceInfoBean);
                this.mDeviceAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCheckAll() {
        boolean z = false;
        int i = 0;
        for (DeviceInfoBean isSelected : this.mDeviceAdapter.getData()) {
            if (isSelected.isSelected()) {
                i++;
            }
        }
        CheckBox checkBox = this.mBinding.cbCheckAll;
        if (i == this.mDeviceAdapter.getData().size()) {
            z = true;
        }
        checkBox.setChecked(z);
    }

    /* access modifiers changed from: private */
    public void showLoading(boolean z) {
        if (z) {
            showWaitDialog();
        } else {
            hideWaitDialog();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        BaseGroupDetailViewModel baseGroupDetailViewModel;
        MeRGBWLog.e("onEvent: " + eventMessage.getAction());
        if (GlobalEvent.DELETE_GROUP_SUCCESS_ACTION.equals(eventMessage.getAction())) {
            finish();
        }
        if (GlobalEvent.UPDATE_GROUP_SUCCESS_ACTION.equals(eventMessage.getAction()) && (baseGroupDetailViewModel = this.mViewModel) != null) {
            baseGroupDetailViewModel.updateGroupInfo();
        }
    }

    private BaseFragment getTabPage(int i) {
        if (i == 16) {
            return new GroupIlluminatingSettingFragment();
        }
        if (i == 26) {
            return new GroupAtmosphereSettingFragment();
        }
        if (i != 36) {
            return null;
        }
        return new GroupMusicSettingFragment6();
    }

    public void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(R.id.layout_content, (Fragment) baseFragment).commit();
            } else {
                beginTransaction.hide(this.mCurrentFragment).show(baseFragment).commit();
            }
            this.mCurrentFragment = baseFragment;
        }
    }

    public void resetSelected() {
        for (BaseFragment next : this.mPageList.values()) {
            if (next != this.mCurrentFragment) {
                next.resetSelected(2);
            }
        }
    }
}

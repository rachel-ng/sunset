package com.mergbw.android.ui.deviceDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.databinding.ActivityDeviceDetailBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.addDevice.BindStatus;
import com.mergbw.android.ui.deviceDetail.adapter.TabItemAdapter;
import com.mergbw.android.ui.deviceDetail.bean.BrightnessInfo;
import com.mergbw.android.ui.deviceDetail.fragment.AtmosphereSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.ColorSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.ColorSettingFragment5;
import com.mergbw.android.ui.deviceDetail.fragment.DIYSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.IlluminatingSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.MusicSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.MusicSettingFragment5;
import com.mergbw.android.ui.deviceDetail.fragment.MusicSettingFragment6;
import com.mergbw.android.ui.deviceDetail.fragment.SceneListFragment5;
import com.mergbw.android.ui.deviceDetail.fragment.SceneSettingFragment;
import com.mergbw.android.ui.deviceDetail.fragment.WhiteNoiseFragment;
import com.mergbw.android.ui.deviceDetail.viewModel.BaseDeviceDetailViewModel;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel1;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel5;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel6;
import com.mergbw.core.R;
import com.mergbw.core.bean.TabItemBean;
import com.mergbw.core.ble.BleStatus;
import com.mergbw.core.ble.RGBDeviceManager;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.utils.MeRGBWLog;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DeviceDetailActivity extends BaseActivity {
    private ActivityDeviceDetailBinding mBinding;
    private BaseFragment mCurrentFragment;
    private final Map<Integer, BaseFragment> mPageList = new HashMap();
    private TabItemAdapter mTabAdapter;
    /* access modifiers changed from: private */
    public BaseDeviceDetailViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityDeviceDetailBinding inflate = ActivityDeviceDetailBinding.inflate(getLayoutInflater());
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
        this.mBinding.ivBack.setOnClickListener(new DeviceDetailActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.listTab.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mTabAdapter = new TabItemAdapter();
        this.mBinding.listTab.setAdapter(this.mTabAdapter);
        this.mTabAdapter.setClickListener(new DeviceDetailActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.ivItemSwitch.setOnCheckedChangeListener(new DeviceDetailActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.sbBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                DeviceDetailActivity.this.mViewModel.setBrightness(seekBar.getProgress());
            }
        });
        this.mBinding.ivSetting.setOnClickListener(new DeviceDetailActivity$$ExternalSyntheticLambda3(this));
        this.mBinding.ivAlarm.setOnClickListener(new DeviceDetailActivity$$ExternalSyntheticLambda4(this));
        this.mBinding.btnReconnect.setOnClickListener(new DeviceDetailActivity$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m717lambda$initViews$0$commergbwandroiduideviceDetailDeviceDetailActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m718lambda$initViews$1$commergbwandroiduideviceDetailDeviceDetailActivity(TabItemBean tabItemBean) {
        BaseFragment baseFragment = this.mPageList.get(Integer.valueOf(tabItemBean.getId()));
        if (baseFragment == null) {
            baseFragment = getTabPage(tabItemBean.getId());
            this.mPageList.put(Integer.valueOf(tabItemBean.getId()), baseFragment);
        }
        switchContent(baseFragment);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m719lambda$initViews$2$commergbwandroiduideviceDetailDeviceDetailActivity(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            this.mViewModel.setPower(z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m720lambda$initViews$3$commergbwandroiduideviceDetailDeviceDetailActivity(View view) {
        JumpManager.getInstance().jumpToDeviceInfo(this.mViewModel.getCurrentDevice());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m721lambda$initViews$4$commergbwandroiduideviceDetailDeviceDetailActivity(View view) {
        JumpManager.getInstance().jumpToDeviceAlarm(this.mViewModel.getCurrentDevice());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-android-ui-deviceDetail-DeviceDetailActivity  reason: not valid java name */
    public /* synthetic */ void m722lambda$initViews$5$commergbwandroiduideviceDetailDeviceDetailActivity(View view) {
        RGBDeviceManager.getInstance().connectDevice(this.mViewModel.getCurrentDevice());
        showConnectingDialog();
    }

    private void initData() {
        DeviceInfoBean deviceInfoBean = (DeviceInfoBean) getIntent().getSerializableExtra("device");
        if (deviceInfoBean != null) {
            int deviceType = deviceInfoBean.getDeviceType();
            if (deviceType == 5) {
                this.mViewModel = (BaseDeviceDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceDetailViewModel5.class);
            } else if (deviceType != 6) {
                BaseDeviceDetailViewModel baseDeviceDetailViewModel = (BaseDeviceDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceDetailViewModel1.class);
                this.mViewModel = baseDeviceDetailViewModel;
                baseDeviceDetailViewModel.setBindStatusObserver(this, new DeviceDetailActivity$$ExternalSyntheticLambda6(this));
            } else {
                this.mViewModel = (BaseDeviceDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DeviceDetailViewModel6.class);
            }
            this.mViewModel.setDetailViewDataObserver(this, new DeviceDetailActivity$$ExternalSyntheticLambda7(this));
            this.mViewModel.initData(getIntent());
            this.mViewModel.setConnectStatusObserver(this, new DeviceDetailActivity$$ExternalSyntheticLambda8(this));
            this.mViewModel.setBrightnessInfoObserver(this, new DeviceDetailActivity$$ExternalSyntheticLambda9(this));
            this.mTabAdapter.setData(ViewDataUtils.getTabList(deviceInfoBean.getDeviceType()));
            initFirstPage();
            TrackManager.getInstance().deviceDetailTrack(1, deviceInfoBean, 0);
            GoogleTrackManager.getInstance().deviceDetailTrack(1, deviceInfoBean, 0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        ActivityDeviceDetailBinding activityDeviceDetailBinding;
        MeRGBWLog.e("onEvent: " + eventMessage.getAction());
        if (GlobalEvent.DELETE_DEVICE_SUCCESS_ACTION.equals(eventMessage.getAction())) {
            DeviceInfoBean deviceInfoBean = (DeviceInfoBean) eventMessage.getData();
            if (deviceInfoBean != null && deviceInfoBean.getDeviceMac().equals(this.mViewModel.getCurrentDevice().getDeviceMac())) {
                RGBDeviceManager.getInstance().disconnectDevice(deviceInfoBean);
                finish();
            }
        } else if (GlobalEvent.DEVICE_ALIAS_NAME_CHANGE.equals(eventMessage.getAction()) && (activityDeviceDetailBinding = this.mBinding) != null) {
            activityDeviceDetailBinding.tvDeviceName.setText((String) eventMessage.getData());
            this.mViewModel.getCurrentDevice().setAliasName((String) eventMessage.getData());
        }
    }

    /* access modifiers changed from: private */
    public void updateDetailViewData(DeviceInfoBean deviceInfoBean) {
        if (deviceInfoBean != null) {
            if (!deviceInfoBean.isConnect() || !deviceInfoBean.isBound()) {
                RGBDeviceManager.getInstance().connectDevice(deviceInfoBean);
                showConnectingDialog();
            }
            ActivityDeviceDetailBinding activityDeviceDetailBinding = this.mBinding;
            if (activityDeviceDetailBinding != null) {
                activityDeviceDetailBinding.tvDeviceName.setText(deviceInfoBean.getAliasName());
                this.mBinding.tvItemModel.setText(deviceInfoBean.getDeviceModel());
                this.mBinding.ivItemState.setImageResource(deviceInfoBean.isConnect() ? R.mipmap.icon_ble_connected : R.mipmap.icon_ble_disconnected);
                this.mBinding.ivItemSwitch.setChecked(deviceInfoBean.isOpen());
                int i = 8;
                this.mBinding.layoutDeviceSetting.setVisibility(deviceInfoBean.isOpen() ? 0 : 8);
                this.mBinding.layoutBrightness.setVisibility(deviceInfoBean.isOpen() ? 0 : 4);
                this.mBinding.ivItemSwitch.setEnabled(deviceInfoBean.isConnect());
                ImageView imageView = this.mBinding.ivAlarm;
                if (deviceInfoBean.isConnect()) {
                    i = 0;
                }
                imageView.setVisibility(i);
                this.mBinding.ivDeviceIcon.setImageResource(ViewDataUtils.getDeviceIcon(deviceInfoBean.getDeviceType(), deviceInfoBean.isConnect()));
                this.mBinding.sbBrightness.setProgress(deviceInfoBean.getBrightness());
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateBrightnessInfo(BrightnessInfo brightnessInfo) {
        ActivityDeviceDetailBinding activityDeviceDetailBinding = this.mBinding;
        if (activityDeviceDetailBinding != null) {
            activityDeviceDetailBinding.sbBrightness.setEnabled(brightnessInfo.isEnable());
            this.mBinding.sbBrightness.setProgress(brightnessInfo.getBrightness());
        }
    }

    private void initFirstPage() {
        TabItemBean tabItemBean = this.mTabAdapter.getData().get(0);
        BaseFragment tabPage = getTabPage(tabItemBean.getId());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(com.mergbw.android.R.id.layout_content, tabPage);
        beginTransaction.commit();
        this.mPageList.put(Integer.valueOf(tabItemBean.getId()), tabPage);
        this.mCurrentFragment = tabPage;
    }

    /* access modifiers changed from: private */
    public void onConnectStatus(BleStatus bleStatus) {
        ActivityDeviceDetailBinding activityDeviceDetailBinding;
        ActivityDeviceDetailBinding activityDeviceDetailBinding2;
        hideConnectingDialog();
        if (!RGBDeviceManager.getInstance().isUpgrade()) {
            if (bleStatus == BleStatus.DISCONNECTED) {
                finish();
            }
            if (bleStatus == BleStatus.CONNECT_ERROR && (activityDeviceDetailBinding2 = this.mBinding) != null) {
                activityDeviceDetailBinding2.layoutConnectedFail.setVisibility(0);
                this.mBinding.layoutDeviceSetting.setVisibility(8);
            }
            if (bleStatus == BleStatus.CONNECTED && (activityDeviceDetailBinding = this.mBinding) != null) {
                activityDeviceDetailBinding.layoutConnectedFail.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateBindStatus(BindStatus bindStatus) {
        hideConnectingDialog();
        if (bindStatus == BindStatus.UNBIND) {
            showBindDialog();
        } else if (bindStatus == BindStatus.BOUND) {
            hideBindDialog();
        } else {
            hideBindDialog();
            finish();
        }
    }

    private BaseFragment getTabPage(int i) {
        if (i == 11) {
            return new ColorSettingFragment();
        }
        if (i == 21) {
            return new SceneSettingFragment();
        }
        if (i == 31) {
            return new MusicSettingFragment();
        }
        if (i == 41) {
            return new DIYSettingFragment();
        }
        if (i == 51) {
            return new WhiteNoiseFragment();
        }
        if (i == 15) {
            return new ColorSettingFragment5();
        }
        if (i == 16) {
            return new IlluminatingSettingFragment();
        }
        if (i == 25) {
            return new SceneListFragment5();
        }
        if (i == 26) {
            return new AtmosphereSettingFragment();
        }
        if (i == 35) {
            return new MusicSettingFragment5();
        }
        if (i != 36) {
            return null;
        }
        return new MusicSettingFragment6();
    }

    private void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(com.mergbw.android.R.id.layout_content, (Fragment) baseFragment).commit();
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

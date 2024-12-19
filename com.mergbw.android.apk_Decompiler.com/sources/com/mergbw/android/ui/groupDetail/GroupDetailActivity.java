package com.mergbw.android.ui.groupDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityDeviceDetailBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.deviceDetail.adapter.TabItemAdapter;
import com.mergbw.android.ui.groupDetail.fragment.GroupColorSettingFragment;
import com.mergbw.android.ui.groupDetail.fragment.GroupColorSettingFragment5;
import com.mergbw.android.ui.groupDetail.fragment.GroupMusicSettingFragment;
import com.mergbw.android.ui.groupDetail.fragment.GroupMusicSettingFragment5;
import com.mergbw.android.ui.groupDetail.fragment.GroupSceneListFragment5;
import com.mergbw.android.ui.groupDetail.fragment.GroupSceneSettingFragment;
import com.mergbw.android.ui.groupDetail.viewModel.BaseGroupDetailViewModel;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel1;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel5;
import com.mergbw.core.bean.TabItemBean;
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

public class GroupDetailActivity extends BaseActivity {
    private ActivityDeviceDetailBinding mBinding;
    private BaseFragment mCurrentFragment;
    private final Map<Integer, BaseFragment> mPageList = new HashMap();
    private TabItemAdapter mTabAdapter;
    /* access modifiers changed from: private */
    public BaseGroupDetailViewModel mViewModel;

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
        this.mBinding.tvTitle.setText(R.string.my_group);
        this.mBinding.ivAlarm.setVisibility(8);
        this.mBinding.ivDeviceIcon.setImageResource(R.mipmap.icon_group);
        this.mBinding.ivBack.setOnClickListener(new GroupDetailActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.listTab.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mTabAdapter = new TabItemAdapter();
        this.mBinding.listTab.setAdapter(this.mTabAdapter);
        this.mTabAdapter.setClickListener(new GroupDetailActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.ivItemSwitch.setOnCheckedChangeListener(new GroupDetailActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.sbBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                MeRGBWLog.i("onStartTrackingTouch " + seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                GroupDetailActivity.this.mViewModel.setBrightness(seekBar.getProgress());
            }
        });
        this.mBinding.ivSetting.setOnClickListener(new GroupDetailActivity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-groupDetail-GroupDetailActivity  reason: not valid java name */
    public /* synthetic */ void m797lambda$initViews$0$commergbwandroiduigroupDetailGroupDetailActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-android-ui-groupDetail-GroupDetailActivity  reason: not valid java name */
    public /* synthetic */ void m798lambda$initViews$1$commergbwandroiduigroupDetailGroupDetailActivity(TabItemBean tabItemBean) {
        BaseFragment baseFragment = this.mPageList.get(Integer.valueOf(tabItemBean.getId()));
        if (baseFragment == null) {
            baseFragment = getTabPage(tabItemBean.getId());
            this.mPageList.put(Integer.valueOf(tabItemBean.getId()), baseFragment);
        }
        switchContent(baseFragment);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-groupDetail-GroupDetailActivity  reason: not valid java name */
    public /* synthetic */ void m799lambda$initViews$2$commergbwandroiduigroupDetailGroupDetailActivity(CompoundButton compoundButton, boolean z) {
        if (compoundButton.isPressed()) {
            this.mViewModel.setPower(z);
            TrackManager.getInstance().groupDetailTrack(2, (GroupItemBean) null, z ? 1 : 0);
            GoogleTrackManager.getInstance().groupDetailTrack(2, (GroupItemBean) null, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-groupDetail-GroupDetailActivity  reason: not valid java name */
    public /* synthetic */ void m800lambda$initViews$3$commergbwandroiduigroupDetailGroupDetailActivity(View view) {
        JumpManager.getInstance().jumpToGroupSetting(this.mViewModel.getCurrentGroup());
    }

    private void initData() {
        GroupItemBean groupItemBean = (GroupItemBean) getIntent().getSerializableExtra("group");
        if (groupItemBean != null) {
            this.mBinding.tvDeviceName.setText(groupItemBean.getGroupName());
            this.mBinding.ivItemState.setVisibility(8);
            int deviceType = groupItemBean.getDeviceType();
            if (deviceType == 1) {
                this.mViewModel = (BaseGroupDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupDetailViewModel1.class);
            } else if (deviceType == 5 || deviceType == 6) {
                this.mViewModel = (BaseGroupDetailViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupDetailViewModel5.class);
            }
            this.mViewModel.setGroupInfoDataObserver(this, new GroupDetailActivity$$ExternalSyntheticLambda4(this));
            this.mViewModel.initData(getIntent());
            this.mTabAdapter.setData(ViewDataUtils.getGroupTabList(groupItemBean.getDeviceType()));
            initFirstPage();
            TrackManager.getInstance().groupDetailTrack(1, groupItemBean, 0);
            GoogleTrackManager.getInstance().groupDetailTrack(1, groupItemBean, 0);
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
            this.mBinding.ivItemSwitch.setChecked(true);
            this.mViewModel.setPower(true);
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
        if (i == 11) {
            return new GroupColorSettingFragment();
        }
        if (i == 15) {
            return new GroupColorSettingFragment5();
        }
        if (i == 21) {
            return new GroupSceneSettingFragment();
        }
        if (i == 25) {
            return new GroupSceneListFragment5();
        }
        if (i == 31) {
            return new GroupMusicSettingFragment();
        }
        if (i != 35) {
            return null;
        }
        return new GroupMusicSettingFragment5();
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

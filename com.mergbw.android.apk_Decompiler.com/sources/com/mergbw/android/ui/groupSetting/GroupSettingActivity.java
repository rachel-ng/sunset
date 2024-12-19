package com.mergbw.android.ui.groupSetting;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.mergbw.android.R;
import com.mergbw.android.databinding.ActivityGroupSettingBinding;
import com.mergbw.android.ui.groupSetting.adapter.DeviceGroupItemAdapter;
import com.mergbw.android.ui.groupSetting.bean.DeviceGroupBean;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import com.mergbw.core.utils.StringUtils;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class GroupSettingActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public ActivityGroupSettingBinding mBinding;
    /* access modifiers changed from: private */
    public DeviceGroupItemAdapter mDeviceAdapter;
    /* access modifiers changed from: private */
    public GroupSettingViewModel mViewModel;

    static /* synthetic */ boolean lambda$initViews$1(ExpandableListView expandableListView, View view, int i, long j) {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewModel = (GroupSettingViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(GroupSettingViewModel.class);
        ActivityGroupSettingBinding inflate = ActivityGroupSettingBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new GroupSettingActivity$$ExternalSyntheticLambda0(this));
        this.mDeviceAdapter = new DeviceGroupItemAdapter();
        this.mBinding.listDevice.setAdapter(this.mDeviceAdapter);
        this.mBinding.listDevice.setOnGroupClickListener(new GroupSettingActivity$$ExternalSyntheticLambda4());
        this.mBinding.layoutGroupName.setOnClickListener(new GroupSettingActivity$$ExternalSyntheticLambda5(this));
        this.mBinding.tvSave.setOnClickListener(new GroupSettingActivity$$ExternalSyntheticLambda6(this));
        this.mBinding.btnDelete.setOnClickListener(new GroupSettingActivity$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m841lambda$initViews$0$commergbwandroiduigroupSettingGroupSettingActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m843lambda$initViews$3$commergbwandroiduigroupSettingGroupSettingActivity(View view) {
        EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_group_name), this.mBinding.tvGroupName.getText().toString(), getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
            public void onSkip() {
            }

            public void onEditText(String str) {
                GroupSettingActivity.this.mBinding.tvGroupName.setText(str);
            }
        });
        editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        editPopWindow.setOnDismissListener(new GroupSettingActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m842lambda$initViews$2$commergbwandroiduigroupSettingGroupSettingActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m845lambda$initViews$5$commergbwandroiduigroupSettingGroupSettingActivity(View view) {
        if (this.mViewModel.getGroupInfo() == null) {
            String charSequence = this.mBinding.tvGroupName.getText().toString();
            if (StringUtils.isEmpty(charSequence)) {
                EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_group_name), this.mBinding.tvGroupName.getText().toString(), getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
                    public void onSkip() {
                    }

                    public void onEditText(String str) {
                        GroupSettingActivity.this.mBinding.tvGroupName.setText(str);
                        GroupSettingActivity.this.mViewModel.addGroup(str, GroupSettingActivity.this.mDeviceAdapter.getData());
                    }
                });
                editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha = 0.3f;
                getWindow().setAttributes(attributes);
                editPopWindow.setOnDismissListener(new GroupSettingActivity$$ExternalSyntheticLambda3(this));
                return;
            }
            this.mViewModel.addGroup(charSequence, this.mDeviceAdapter.getData());
            return;
        }
        this.mViewModel.updateGroup(this.mBinding.tvGroupName.getText().toString(), this.mDeviceAdapter.getData());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m844lambda$initViews$4$commergbwandroiduigroupSettingGroupSettingActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$7$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m847lambda$initViews$7$commergbwandroiduigroupSettingGroupSettingActivity(View view) {
        String string = getString(R.string.delete_group_tick);
        new ConfirmDialog(this.mContext, getString(R.string.confirm), String.format(string, new Object[]{this.mViewModel.getGroupInfo().getGroupName()}), getString(17039360), getString(com.mergbw.core.R.string.delete), new GroupSettingActivity$$ExternalSyntheticLambda2(this)).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$6$com-mergbw-android-ui-groupSetting-GroupSettingActivity  reason: not valid java name */
    public /* synthetic */ void m846lambda$initViews$6$commergbwandroiduigroupSettingGroupSettingActivity(boolean z) {
        if (z) {
            this.mViewModel.deleteGroup();
        }
    }

    private void subscribeUI() {
        this.mViewModel.setGroupInfoObserver(this, new GroupSettingActivity$$ExternalSyntheticLambda8(this));
        this.mViewModel.setDeviceListDataObserver(this, new GroupSettingActivity$$ExternalSyntheticLambda9(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setAddResultObserver(this, new GroupSettingActivity$$ExternalSyntheticLambda10(this));
        this.mViewModel.setDeleteResultObserver(this, new GroupSettingActivity$$ExternalSyntheticLambda11(this));
        this.mViewModel.setUpdateResultObserver(this, new GroupSettingActivity$$ExternalSyntheticLambda12(this));
    }

    /* access modifiers changed from: private */
    public void updateGroupInfo(GroupItemBean groupItemBean) {
        if (groupItemBean != null) {
            this.mBinding.tvTitle.setText(R.string.group_manage);
            this.mBinding.tvGroupName.setText(groupItemBean.getGroupName());
            this.mBinding.layoutBottom.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateDeviceList(List<DeviceGroupBean> list) {
        if (list != null) {
            this.mDeviceAdapter.setData(list, this.mViewModel.getGroupInfo() == null ? 0 : this.mViewModel.getGroupInfo().getDeviceType());
            for (int i = 0; i < this.mDeviceAdapter.getData().size(); i++) {
                this.mBinding.listDevice.expandGroup(i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void addGroupResult(boolean z) {
        if (z) {
            showToast(getString(R.string.create_group_success));
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void deleteGroupResult(boolean z) {
        if (z) {
            EventBus.getDefault().post(new EventMessage(GlobalEvent.DELETE_GROUP_SUCCESS_ACTION));
            showToast(getString(R.string.delete_group_success));
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void updateGroupResult(boolean z) {
        if (z) {
            EventBus.getDefault().post(new EventMessage(GlobalEvent.UPDATE_GROUP_SUCCESS_ACTION));
            showToast(getString(R.string.update_group_success));
            finish();
        }
    }
}

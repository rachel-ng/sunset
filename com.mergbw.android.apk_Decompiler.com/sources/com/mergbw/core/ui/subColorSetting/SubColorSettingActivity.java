package com.mergbw.core.ui.subColorSetting;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.R;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.databinding.ActivitySubColorSettingBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import com.mergbw.core.utils.ColorUtils;
import java.util.ArrayList;
import java.util.List;

public class SubColorSettingActivity extends BaseActivity {
    private boolean isCheckAll = false;
    private ActivitySubColorSettingBinding mBinding;
    private List<SubItemBean> mSubList = new ArrayList();
    /* access modifiers changed from: private */
    public SubColorViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBinding = ActivitySubColorSettingBinding.inflate(getLayoutInflater());
        this.mViewModel = (SubColorViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(SubColorViewModel.class);
        setContentView((View) this.mBinding.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new SubColorSettingActivity$$ExternalSyntheticLambda4(this));
        this.mBinding.viewSubList.setOnSubItemCheckChangeListener(new SubColorSettingActivity$$ExternalSyntheticLambda5(this));
        this.mBinding.tvAllCheck.setOnClickListener(new SubColorSettingActivity$$ExternalSyntheticLambda6(this));
        this.mBinding.viewCommonColorPicker.setOnColorPickListener(new SubColorSettingActivity$$ExternalSyntheticLambda7(this));
        this.mBinding.tvSave.setOnClickListener(new SubColorSettingActivity$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-core-ui-subColorSetting-SubColorSettingActivity  reason: not valid java name */
    public /* synthetic */ void m936lambda$initViews$0$commergbwcoreuisubColorSettingSubColorSettingActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-core-ui-subColorSetting-SubColorSettingActivity  reason: not valid java name */
    public /* synthetic */ void m937lambda$initViews$1$commergbwcoreuisubColorSettingSubColorSettingActivity(int i, boolean z) {
        this.mSubList.get(i).setChecked(z);
        checkStatus();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-core-ui-subColorSetting-SubColorSettingActivity  reason: not valid java name */
    public /* synthetic */ void m938lambda$initViews$2$commergbwcoreuisubColorSettingSubColorSettingActivity(View view) {
        for (SubItemBean checked : this.mSubList) {
            checked.setChecked(!this.isCheckAll);
        }
        this.isCheckAll = !this.isCheckAll;
        this.mBinding.tvAllCheck.setText(!this.isCheckAll ? R.string.select_all : R.string.unselect_all);
        this.mBinding.viewSubList.refreshData(this.mSubList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-core-ui-subColorSetting-SubColorSettingActivity  reason: not valid java name */
    public /* synthetic */ void m940lambda$initViews$4$commergbwcoreuisubColorSettingSubColorSettingActivity(View view) {
        String jSONString = JSON.toJSONString(this.mSubList);
        if (this.mViewModel.getSubColor() == null) {
            final SubColorBean subColorBean = new SubColorBean();
            subColorBean.setColorValue(jSONString);
            EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_diy_name), (String) null, getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
                public void onSkip() {
                }

                public void onEditText(String str) {
                    subColorBean.setAlias(str);
                    SubColorSettingActivity.this.mViewModel.addSubColor(subColorBean);
                }
            });
            editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.alpha = 0.3f;
            getWindow().setAttributes(attributes);
            editPopWindow.setOnDismissListener(new SubColorSettingActivity$$ExternalSyntheticLambda9(this));
            return;
        }
        this.mViewModel.updateSubColor(jSONString);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-core-ui-subColorSetting-SubColorSettingActivity  reason: not valid java name */
    public /* synthetic */ void m939lambda$initViews$3$commergbwcoreuisubColorSettingSubColorSettingActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    private void checkStatus() {
        int i = 0;
        int i2 = 0;
        for (SubItemBean isChecked : this.mSubList) {
            if (isChecked.isChecked()) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == this.mSubList.size()) {
            this.isCheckAll = true;
        }
        if (i2 == this.mSubList.size()) {
            this.isCheckAll = false;
        }
        this.mBinding.tvAllCheck.setText(!this.isCheckAll ? R.string.select_all : R.string.unselect_all);
    }

    private void subscribeUI() {
        this.mViewModel.setDeviceInfoDataObserver(this, new SubColorSettingActivity$$ExternalSyntheticLambda0(this));
        this.mViewModel.setSubColorDataObserver(this, new SubColorSettingActivity$$ExternalSyntheticLambda1(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setAddResultObserver(this, new SubColorSettingActivity$$ExternalSyntheticLambda2(this));
        this.mViewModel.setCommonColorListDataObserver(this, new SubColorSettingActivity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public void updateSubColor(SubColorBean subColorBean) {
        if (subColorBean == null) {
            initSubData();
        } else {
            this.mSubList = ColorUtils.getSubColor(subColorBean.getColorValue());
            this.mBinding.tvTitle.setText(subColorBean.getAlias());
        }
        this.mBinding.viewSubList.refreshData(this.mSubList);
    }

    /* access modifiers changed from: private */
    public void updateDeviceInfo(DeviceInfoBean deviceInfoBean) {
        ActivitySubColorSettingBinding activitySubColorSettingBinding = this.mBinding;
        if (activitySubColorSettingBinding != null && deviceInfoBean != null) {
            activitySubColorSettingBinding.tvDeviceName.setText(deviceInfoBean.getAliasName());
        }
    }

    private void initSubData() {
        this.mSubList.clear();
        for (int i = 0; i < 20; i++) {
            SubItemBean subItemBean = new SubItemBean();
            subItemBean.setColor(Color.parseColor("#FF000000"));
            this.mSubList.add(subItemBean);
        }
    }

    /* access modifiers changed from: private */
    public void refreshSubData(int i) {
        int i2 = 0;
        for (SubItemBean next : this.mSubList) {
            if (next.isChecked()) {
                next.setColor(i);
                i2++;
            }
        }
        if (i2 > 0) {
            this.mBinding.viewSubList.refreshData(this.mSubList);
            this.mViewModel.setSubColor(this.mSubList);
        }
    }

    /* access modifiers changed from: private */
    public void updateCommonColor(List<ColorBean> list) {
        ActivitySubColorSettingBinding activitySubColorSettingBinding = this.mBinding;
        if (activitySubColorSettingBinding != null) {
            activitySubColorSettingBinding.viewCommonColorPicker.setCommonColor(list);
        }
    }

    /* access modifiers changed from: private */
    public void updateAddResult(boolean z) {
        showToast(getString(R.string.set_success));
        finish();
    }
}

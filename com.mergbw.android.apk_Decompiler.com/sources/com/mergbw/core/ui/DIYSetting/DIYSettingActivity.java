package com.mergbw.core.ui.DIYSetting;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.Constants;
import com.mergbw.core.R;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.database.bean.ColorBean;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.database.bean.DeviceInfoBean;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.databinding.ActivityDiySettingBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.DIYSetting.adapter.DIYSubItemAdapter;
import com.mergbw.core.ui.DIYSetting.adapter.IDIYSubItemClickListener;
import com.mergbw.core.ui.views.DIYEffectPopWindow;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import com.mergbw.core.utils.ColorUtils;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.ArrayList;
import java.util.List;

public class DIYSettingActivity extends BaseActivity {
    private boolean isCheckAll = false;
    /* access modifiers changed from: private */
    public ActivityDiySettingBinding mBinding;
    /* access modifiers changed from: private */
    public DIYSubItemAdapter mSubAdapter;
    private List<SubItemBean> mSubList = new ArrayList();
    /* access modifiers changed from: private */
    public DIYSettingViewModel mViewModel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBinding = ActivityDiySettingBinding.inflate(getLayoutInflater());
        this.mViewModel = (DIYSettingViewModel) new ViewModelProvider((ViewModelStoreOwner) this).get(DIYSettingViewModel.class);
        setContentView((View) this.mBinding.getRoot());
        initViews();
        subscribeUI();
    }

    private void initViews() {
        this.mSubAdapter = new DIYSubItemAdapter();
        this.mBinding.listSubItem.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mBinding.listSubItem.setAdapter(this.mSubAdapter);
        this.mSubAdapter.setClickListener(new IDIYSubItemClickListener() {
            public void clickDIYSubItem(SubColorBean subColorBean) {
                int i = 1;
                if (subColorBean.getType() == 1) {
                    List<SubColorBean> data = DIYSettingActivity.this.mSubAdapter.getData();
                    SubColorBean subColorBean2 = new SubColorBean();
                    subColorBean2.setColorValue(JSON.toJSONString(DIYSettingActivity.this.mViewModel.initSubData()));
                    if (data.size() == Constants.MAX_DIY_SUB_NUM) {
                        i = 0;
                        data.set(0, subColorBean2);
                    } else {
                        data.add(1, subColorBean2);
                    }
                    DIYSettingActivity.this.mSubAdapter.setData(data, i);
                    DIYSettingActivity.this.initSubData(subColorBean2.getColorValue());
                    return;
                }
                DIYSettingActivity.this.initSubData(subColorBean.getColorValue());
            }

            public void deleteSubItem(SubColorBean subColorBean, int i, int i2) {
                List<SubColorBean> data = DIYSettingActivity.this.mSubAdapter.getData();
                int i3 = 1;
                if (i2 != i ? i2 > i : i2 == data.size() - 1) {
                    i2--;
                }
                data.remove(i);
                if (i == 0) {
                    SubColorBean subColorBean2 = new SubColorBean();
                    subColorBean2.setType(1);
                    data.add(0, subColorBean2);
                    if (i2 == 0) {
                        i2 = 1;
                    }
                }
                if (data.size() == 1) {
                    SubColorBean subColorBean3 = new SubColorBean();
                    subColorBean3.setColorValue(JSON.toJSONString(DIYSettingActivity.this.mViewModel.initSubData()));
                    data.add(1, subColorBean3);
                } else {
                    i3 = i2;
                }
                DIYSettingActivity.this.mSubAdapter.setData(data, i3);
                DIYSettingActivity.this.initSubData(data.get(i3).getColorValue());
            }
        });
        this.mBinding.ivBack.setOnClickListener(new DIYSettingActivity$$ExternalSyntheticLambda12(this));
        this.mBinding.viewSubList.setOnSubItemCheckChangeListener(new DIYSettingActivity$$ExternalSyntheticLambda13(this));
        this.mBinding.tvAllCheck.setOnClickListener(new DIYSettingActivity$$ExternalSyntheticLambda1(this));
        this.mBinding.viewCommonColorPicker.showColorPie(false);
        this.mBinding.viewCommonColorPicker.setOnColorPickListener(new DIYSettingActivity$$ExternalSyntheticLambda2(this));
        this.mBinding.tvSave.setOnClickListener(new DIYSettingActivity$$ExternalSyntheticLambda3(this));
        this.mBinding.layoutEffect.setOnClickListener(new DIYSettingActivity$$ExternalSyntheticLambda4(this));
        this.mBinding.sbSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                DIYSettingActivity.this.mBinding.tvSpeedValue.setText(String.valueOf(i));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                DIYSettingActivity.this.mViewModel.setDIYSpeed(101 - seekBar.getProgress());
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m920lambda$initViews$0$commergbwcoreuiDIYSettingDIYSettingActivity(View view) {
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$1$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m921lambda$initViews$1$commergbwcoreuiDIYSettingDIYSettingActivity(int i, boolean z) {
        this.mSubList.get(i).setChecked(z);
        checkStatus();
        this.mSubAdapter.refreshData(this.mSubList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$2$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m922lambda$initViews$2$commergbwcoreuiDIYSettingDIYSettingActivity(View view) {
        for (SubItemBean checked : this.mSubList) {
            checked.setChecked(!this.isCheckAll);
        }
        this.isCheckAll = !this.isCheckAll;
        this.mBinding.tvAllCheck.setText(!this.isCheckAll ? R.string.select_all : R.string.unselect_all);
        this.mBinding.viewSubList.refreshData(this.mSubList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$4$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m924lambda$initViews$4$commergbwcoreuiDIYSettingDIYSettingActivity(View view) {
        if (this.mSubAdapter.getData().size() <= 1) {
            showToast("请至少添加一帧数据");
        } else if (this.mViewModel.getCurrentDIYInfo() == null) {
            EditPopWindow editPopWindow = new EditPopWindow(this.mContext, getString(R.string.set_diy_name), (String) null, getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
                public void onSkip() {
                }

                public void onEditText(String str) {
                    DIYSettingActivity.this.mViewModel.saveDIYSetting(str, DIYSettingActivity.this.mSubAdapter.getData());
                }
            });
            editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.alpha = 0.3f;
            getWindow().setAttributes(attributes);
            editPopWindow.setOnDismissListener(new DIYSettingActivity$$ExternalSyntheticLambda0(this));
        } else {
            this.mViewModel.updateDIYSetting(this.mSubAdapter.getData());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$3$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m923lambda$initViews$3$commergbwcoreuiDIYSettingDIYSettingActivity() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 1.0f;
        getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$7$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m927lambda$initViews$7$commergbwcoreuiDIYSettingDIYSettingActivity(View view) {
        DIYEffectPopWindow dIYEffectPopWindow = new DIYEffectPopWindow(this.mContext, this.mViewModel.getDIYEffect(), new DIYSettingActivity$$ExternalSyntheticLambda10(this));
        dIYEffectPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        getWindow().setAttributes(attributes);
        dIYEffectPopWindow.setOnDismissListener(new DIYSettingActivity$$ExternalSyntheticLambda11(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$5$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m925lambda$initViews$5$commergbwcoreuiDIYSettingDIYSettingActivity(int i) {
        this.mViewModel.setDIYEffect(i);
        this.mBinding.tvEffect.setText(ViewDataUtils.EFFECT_NAME.get(Integer.valueOf(i)).intValue());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$6$com-mergbw-core-ui-DIYSetting-DIYSettingActivity  reason: not valid java name */
    public /* synthetic */ void m926lambda$initViews$6$commergbwcoreuiDIYSettingDIYSettingActivity() {
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
        this.mViewModel.setDeviceInfoDataObserver(this, new DIYSettingActivity$$ExternalSyntheticLambda5(this));
        this.mViewModel.setDIYInfoDataObserver(this, new DIYSettingActivity$$ExternalSyntheticLambda6(this));
        this.mViewModel.setSubColorListDataObserver(this, new DIYSettingActivity$$ExternalSyntheticLambda7(this));
        this.mViewModel.initData(getIntent());
        this.mViewModel.setCommonColorListDataObserver(this, new DIYSettingActivity$$ExternalSyntheticLambda8(this));
        this.mViewModel.setSaveResultObserver(this, new DIYSettingActivity$$ExternalSyntheticLambda9(this));
    }

    /* access modifiers changed from: private */
    public void updateDeviceInfo(DeviceInfoBean deviceInfoBean) {
        ActivityDiySettingBinding activityDiySettingBinding = this.mBinding;
        if (activityDiySettingBinding != null && deviceInfoBean != null) {
            activityDiySettingBinding.tvDeviceName.setText(deviceInfoBean.getAliasName());
        }
    }

    /* access modifiers changed from: private */
    public void updateDIYInfo(DIYInfoBean dIYInfoBean) {
        ActivityDiySettingBinding activityDiySettingBinding = this.mBinding;
        if (activityDiySettingBinding != null) {
            activityDiySettingBinding.tvTitle.setText(dIYInfoBean.getName());
            this.mBinding.tvEffect.setText(ViewDataUtils.EFFECT_NAME.get(Integer.valueOf(dIYInfoBean.getStyle())).intValue());
            int time = 101 - dIYInfoBean.getTime();
            this.mBinding.tvSpeedValue.setText(String.valueOf(time));
            this.mBinding.sbSpeed.setProgress(time);
        }
    }

    /* access modifiers changed from: private */
    public void updateSubColorList(List<SubColorBean> list) {
        int i = 1;
        if (list.size() > 1) {
            if (list.get(0).getType() != 1) {
                i = 0;
            }
            this.mSubAdapter.setData(list, i);
            initSubData(list.get(i).getColorValue());
        }
    }

    /* access modifiers changed from: private */
    public void initSubData(String str) {
        this.mSubList = ColorUtils.getSubColor(str);
        this.mBinding.viewSubList.refreshData(this.mSubList);
        checkStatus();
    }

    /* access modifiers changed from: private */
    public void refreshSubData(int i) {
        List<SubItemBean> list = this.mSubList;
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            for (SubItemBean next : this.mSubList) {
                if (next.isChecked()) {
                    next.setColor(i);
                    i2++;
                }
            }
            if (i2 > 0) {
                this.mBinding.viewSubList.refreshData(this.mSubList);
                this.mSubAdapter.refreshData(this.mSubList);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateCommonColor(List<ColorBean> list) {
        ActivityDiySettingBinding activityDiySettingBinding = this.mBinding;
        if (activityDiySettingBinding != null) {
            activityDiySettingBinding.viewCommonColorPicker.setCommonColor(list);
        }
    }

    /* access modifiers changed from: private */
    public void updateSaveResult(boolean z) {
        showToast(getString(R.string.set_success));
        finish();
    }
}

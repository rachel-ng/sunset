package com.mergbw.android.ui.deviceDetail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import com.mergbw.android.R;
import com.mergbw.android.databinding.FragmentSubColorListBinding;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel1;
import com.mergbw.core.Constants;
import com.mergbw.core.database.bean.SubColorBean;
import com.mergbw.core.jump.JumpManager;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.ISubColorGroupClickListener;
import com.mergbw.core.ui.adapter.SubGroupAdapter;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.ui.views.DIYMenuPopWindow;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import java.util.ArrayList;
import java.util.List;

public class SubColorListFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public FragmentSubColorListBinding mBinding;
    private SubGroupAdapter mSubAdapter;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel1 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentSubColorListBinding.inflate(layoutInflater);
        this.mViewModel = (DeviceDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel1.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.layoutEmptyAdd.setOnClickListener(new SubColorListFragment$$ExternalSyntheticLambda1(this));
        this.mSubAdapter = new SubGroupAdapter();
        this.mBinding.listSubColor.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mBinding.listSubColor.setAdapter(this.mSubAdapter);
        this.mSubAdapter.setClickListener(new ISubColorGroupClickListener() {
            public void clickGroupItem(SubColorBean subColorBean) {
                if (subColorBean.getType() == -1) {
                    JumpManager.getInstance().jumpToSubSetting(SubColorListFragment.this.mViewModel.getCurrentDevice(), (SubColorBean) null, 1);
                } else {
                    SubColorListFragment.this.showWaitDialog();
                    SubColorListFragment.this.mViewModel.setSubColor(subColorBean);
                }
                if (SubColorListFragment.this.getParentFragment() != null) {
                    ((DIYSettingFragment) SubColorListFragment.this.getParentFragment()).resetSelected(3);
                }
            }

            public void longClickGroupItem(SubColorBean subColorBean) {
                DIYMenuPopWindow dIYMenuPopWindow = new DIYMenuPopWindow(SubColorListFragment.this.mContext, new SubColorListFragment$1$$ExternalSyntheticLambda2(this, subColorBean));
                dIYMenuPopWindow.showAtLocation(SubColorListFragment.this.mBinding.getRoot(), 80, 0, 0);
                WindowManager.LayoutParams attributes = SubColorListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 0.3f;
                SubColorListFragment.this.requireActivity().getWindow().setAttributes(attributes);
                dIYMenuPopWindow.setOnDismissListener(new SubColorListFragment$1$$ExternalSyntheticLambda3(this));
                if (SubColorListFragment.this.getParentFragment() != null) {
                    ((DIYSettingFragment) SubColorListFragment.this.getParentFragment()).resetSelected(3);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickGroupItem$0$com-mergbw-android-ui-deviceDetail-fragment-SubColorListFragment$1  reason: not valid java name */
            public /* synthetic */ void m760lambda$longClickGroupItem$0$commergbwandroiduideviceDetailfragmentSubColorListFragment$1() {
                WindowManager.LayoutParams attributes = SubColorListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                SubColorListFragment.this.requireActivity().getWindow().setAttributes(attributes);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickGroupItem$2$com-mergbw-android-ui-deviceDetail-fragment-SubColorListFragment$1  reason: not valid java name */
            public /* synthetic */ void m762lambda$longClickGroupItem$2$commergbwandroiduideviceDetailfragmentSubColorListFragment$1(final SubColorBean subColorBean, int i) {
                if (i == 1) {
                    EditPopWindow editPopWindow = new EditPopWindow(SubColorListFragment.this.mContext, SubColorListFragment.this.getString(R.string.rename_diy), subColorBean.getAlias(), SubColorListFragment.this.getString(17039360), SubColorListFragment.this.getString(17039370), 1, new EditDialog.OnEditListener() {
                        public void onSkip() {
                        }

                        public void onEditText(String str) {
                            subColorBean.setAlias(str);
                            SubColorListFragment.this.mViewModel.updateSubColor(subColorBean);
                        }
                    });
                    editPopWindow.showAtLocation(SubColorListFragment.this.mBinding.getRoot(), 80, 0, 0);
                    WindowManager.LayoutParams attributes = SubColorListFragment.this.requireActivity().getWindow().getAttributes();
                    attributes.alpha = 0.3f;
                    SubColorListFragment.this.requireActivity().getWindow().setAttributes(attributes);
                    editPopWindow.setOnDismissListener(new SubColorListFragment$1$$ExternalSyntheticLambda0(this));
                } else if (i == 2) {
                    JumpManager.getInstance().jumpToSubSetting(SubColorListFragment.this.mViewModel.getCurrentDevice(), subColorBean, 1);
                } else if (i == 3) {
                    String string = SubColorListFragment.this.getString(R.string.delete_diy_tick);
                    new ConfirmDialog(SubColorListFragment.this.mContext, SubColorListFragment.this.getString(R.string.confirm), String.format(string, new Object[]{subColorBean.getAlias()}), SubColorListFragment.this.getString(17039360), SubColorListFragment.this.getString(17039370), new SubColorListFragment$1$$ExternalSyntheticLambda1(this, subColorBean)).show();
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickGroupItem$1$com-mergbw-android-ui-deviceDetail-fragment-SubColorListFragment$1  reason: not valid java name */
            public /* synthetic */ void m761lambda$longClickGroupItem$1$commergbwandroiduideviceDetailfragmentSubColorListFragment$1(SubColorBean subColorBean, boolean z) {
                if (z) {
                    SubColorListFragment.this.mViewModel.deleteSubColor(subColorBean);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickGroupItem$3$com-mergbw-android-ui-deviceDetail-fragment-SubColorListFragment$1  reason: not valid java name */
            public /* synthetic */ void m763lambda$longClickGroupItem$3$commergbwandroiduideviceDetailfragmentSubColorListFragment$1() {
                WindowManager.LayoutParams attributes = SubColorListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                SubColorListFragment.this.requireActivity().getWindow().setAttributes(attributes);
            }
        });
        this.mViewModel.setSubColorListDataObserver(this, new SubColorListFragment$$ExternalSyntheticLambda2(this));
        this.mViewModel.setSendSubResultObserver(this, new SubColorListFragment$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-SubColorListFragment  reason: not valid java name */
    public /* synthetic */ void m759lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentSubColorListFragment(View view) {
        JumpManager.getInstance().jumpToSubSetting(this.mViewModel.getCurrentDevice(), (SubColorBean) null, 1);
    }

    /* access modifiers changed from: private */
    public void updateSubColor(List<SubColorBean> list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            if (list.size() < Constants.MAX_DIY_NUM) {
                SubColorBean subColorBean = new SubColorBean();
                subColorBean.setAlias(getString(com.mergbw.core.R.string.create_static));
                subColorBean.setType(-1);
                arrayList.add(subColorBean);
            }
            arrayList.addAll(list);
            this.mSubAdapter.setData(arrayList);
            this.mSubAdapter.notifyDataSetChanged();
            FragmentSubColorListBinding fragmentSubColorListBinding = this.mBinding;
            if (fragmentSubColorListBinding != null) {
                fragmentSubColorListBinding.layoutEmptyAdd.setVisibility(8);
                this.mBinding.listSubColor.setVisibility(0);
                return;
            }
            return;
        }
        FragmentSubColorListBinding fragmentSubColorListBinding2 = this.mBinding;
        if (fragmentSubColorListBinding2 != null) {
            fragmentSubColorListBinding2.layoutEmptyAdd.setVisibility(0);
            this.mBinding.listSubColor.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void updateSendResult(boolean z) {
        new Handler(Looper.getMainLooper()).postDelayed(new SubColorListFragment$$ExternalSyntheticLambda0(this), 1000);
    }

    public void resetSelected(int i) {
        if (i == 1) {
            this.mSubAdapter.resetSelected();
        }
    }
}

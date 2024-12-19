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
import com.mergbw.android.databinding.FragmentDiyListBinding;
import com.mergbw.android.ui.deviceDetail.viewModel.DeviceDetailViewModel1;
import com.mergbw.core.Constants;
import com.mergbw.core.database.bean.DIYInfoBean;
import com.mergbw.core.jump.JumpManager;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.adapter.DIYItemAdapter;
import com.mergbw.core.ui.adapter.IDIYItemClickListener;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.ui.views.DIYMenuPopWindow;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import java.util.ArrayList;
import java.util.List;

public class DIYListFragment extends BaseFragment {
    private DIYItemAdapter mAdapter;
    /* access modifiers changed from: private */
    public FragmentDiyListBinding mBinding;
    /* access modifiers changed from: private */
    public DeviceDetailViewModel1 mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentDiyListBinding.inflate(layoutInflater);
        this.mViewModel = (DeviceDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(DeviceDetailViewModel1.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.listDiy.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.mAdapter = new DIYItemAdapter();
        this.mBinding.listDiy.setAdapter(this.mAdapter);
        this.mAdapter.setClickListener(new IDIYItemClickListener() {
            public void clickDIYItem(DIYInfoBean dIYInfoBean) {
                if (dIYInfoBean.getType() == 1) {
                    JumpManager.getInstance().jumpToDIYSetting(DIYListFragment.this.mViewModel.getCurrentDevice(), (DIYInfoBean) null, 1);
                } else {
                    DIYListFragment.this.showWaitDialog();
                    DIYListFragment.this.mViewModel.setDIYColor(dIYInfoBean);
                }
                if (DIYListFragment.this.getParentFragment() != null) {
                    ((DIYSettingFragment) DIYListFragment.this.getParentFragment()).resetSelected(3);
                }
            }

            public void longClickDIYItem(DIYInfoBean dIYInfoBean) {
                DIYMenuPopWindow dIYMenuPopWindow = new DIYMenuPopWindow(DIYListFragment.this.mContext, new DIYListFragment$1$$ExternalSyntheticLambda0(this, dIYInfoBean));
                dIYMenuPopWindow.showAtLocation(DIYListFragment.this.mBinding.getRoot(), 80, 0, 0);
                WindowManager.LayoutParams attributes = DIYListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 0.3f;
                DIYListFragment.this.requireActivity().getWindow().setAttributes(attributes);
                dIYMenuPopWindow.setOnDismissListener(new DIYListFragment$1$$ExternalSyntheticLambda1(this));
                if (DIYListFragment.this.getParentFragment() != null) {
                    ((DIYSettingFragment) DIYListFragment.this.getParentFragment()).resetSelected(3);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickDIYItem$0$com-mergbw-android-ui-deviceDetail-fragment-DIYListFragment$1  reason: not valid java name */
            public /* synthetic */ void m744lambda$longClickDIYItem$0$commergbwandroiduideviceDetailfragmentDIYListFragment$1() {
                WindowManager.LayoutParams attributes = DIYListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                DIYListFragment.this.requireActivity().getWindow().setAttributes(attributes);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickDIYItem$2$com-mergbw-android-ui-deviceDetail-fragment-DIYListFragment$1  reason: not valid java name */
            public /* synthetic */ void m746lambda$longClickDIYItem$2$commergbwandroiduideviceDetailfragmentDIYListFragment$1(final DIYInfoBean dIYInfoBean, int i) {
                if (i == 1) {
                    EditPopWindow editPopWindow = new EditPopWindow(DIYListFragment.this.mContext, DIYListFragment.this.getString(R.string.rename_diy), dIYInfoBean.getName(), DIYListFragment.this.getString(17039360), DIYListFragment.this.getString(17039370), 1, new EditDialog.OnEditListener() {
                        public void onSkip() {
                        }

                        public void onEditText(String str) {
                            dIYInfoBean.setName(str);
                            DIYListFragment.this.mViewModel.updateDIYColor(dIYInfoBean);
                        }
                    });
                    editPopWindow.showAtLocation(DIYListFragment.this.mBinding.getRoot(), 80, 0, 0);
                    WindowManager.LayoutParams attributes = DIYListFragment.this.requireActivity().getWindow().getAttributes();
                    attributes.alpha = 0.3f;
                    DIYListFragment.this.requireActivity().getWindow().setAttributes(attributes);
                    editPopWindow.setOnDismissListener(new DIYListFragment$1$$ExternalSyntheticLambda2(this));
                } else if (i == 2) {
                    JumpManager.getInstance().jumpToDIYSetting(DIYListFragment.this.mViewModel.getCurrentDevice(), dIYInfoBean, 1);
                } else if (i == 3) {
                    String string = DIYListFragment.this.getString(R.string.delete_diy_tick);
                    new ConfirmDialog(DIYListFragment.this.mContext, DIYListFragment.this.getString(R.string.confirm), String.format(string, new Object[]{dIYInfoBean.getName()}), DIYListFragment.this.getString(17039360), DIYListFragment.this.getString(17039370), new DIYListFragment$1$$ExternalSyntheticLambda3(this, dIYInfoBean)).show();
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickDIYItem$1$com-mergbw-android-ui-deviceDetail-fragment-DIYListFragment$1  reason: not valid java name */
            public /* synthetic */ void m745lambda$longClickDIYItem$1$commergbwandroiduideviceDetailfragmentDIYListFragment$1(DIYInfoBean dIYInfoBean, boolean z) {
                if (z) {
                    DIYListFragment.this.mViewModel.deleteDIYColor(dIYInfoBean);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$longClickDIYItem$3$com-mergbw-android-ui-deviceDetail-fragment-DIYListFragment$1  reason: not valid java name */
            public /* synthetic */ void m747lambda$longClickDIYItem$3$commergbwandroiduideviceDetailfragmentDIYListFragment$1() {
                WindowManager.LayoutParams attributes = DIYListFragment.this.requireActivity().getWindow().getAttributes();
                attributes.alpha = 1.0f;
                DIYListFragment.this.requireActivity().getWindow().setAttributes(attributes);
            }
        });
        this.mViewModel.setDIYListDataObserver(this, new DIYListFragment$$ExternalSyntheticLambda0(this));
        this.mViewModel.setSendDIYResultObserver(this, new DIYListFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.layoutEmptyAdd.setOnClickListener(new DIYListFragment$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-deviceDetail-fragment-DIYListFragment  reason: not valid java name */
    public /* synthetic */ void m743lambda$onViewCreated$0$commergbwandroiduideviceDetailfragmentDIYListFragment(View view) {
        JumpManager.getInstance().jumpToDIYSetting(this.mViewModel.getCurrentDevice(), (DIYInfoBean) null, 1);
    }

    /* access modifiers changed from: private */
    public void updateDIYListData(List<DIYInfoBean> list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            if (list.size() < Constants.MAX_DIY_NUM) {
                DIYInfoBean dIYInfoBean = new DIYInfoBean();
                dIYInfoBean.setName(getString(com.mergbw.core.R.string.create_dynamic));
                dIYInfoBean.setType(1);
                arrayList.add(dIYInfoBean);
            }
            arrayList.addAll(list);
            this.mAdapter.setData(arrayList);
            this.mAdapter.notifyDataSetChanged();
            FragmentDiyListBinding fragmentDiyListBinding = this.mBinding;
            if (fragmentDiyListBinding != null) {
                fragmentDiyListBinding.layoutEmptyAdd.setVisibility(8);
                this.mBinding.listDiy.setVisibility(0);
                return;
            }
            return;
        }
        FragmentDiyListBinding fragmentDiyListBinding2 = this.mBinding;
        if (fragmentDiyListBinding2 != null) {
            fragmentDiyListBinding2.layoutEmptyAdd.setVisibility(0);
            this.mBinding.listDiy.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void updateSendResult(boolean z) {
        new Handler(Looper.getMainLooper()).postDelayed(new DIYListFragment$$ExternalSyntheticLambda3(this), 1000);
    }

    public void resetSelected(int i) {
        if (i == 1) {
            this.mAdapter.resetSelected();
        }
    }
}

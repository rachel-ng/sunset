package com.mergbw.android.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.databinding.FragmentGroupListBinding;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.android.ui.home.adapter.GroupItemAdapter;
import com.mergbw.android.ui.home.viewModel.HomeViewModel;
import com.mergbw.core.database.bean.GroupItemBean;
import com.mergbw.core.ui.BaseFragment;
import java.util.List;

public class DeviceGroupFragment extends BaseFragment {
    private GroupItemAdapter mAdapter;
    private FragmentGroupListBinding mBinding;
    private HomeViewModel mViewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentGroupListBinding.inflate(layoutInflater);
        this.mViewModel = (HomeViewModel) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(HomeViewModel.class);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(1);
        this.mBinding.listGroup.setLayoutManager(linearLayoutManager);
        this.mAdapter = new GroupItemAdapter();
        this.mBinding.listGroup.setAdapter(this.mAdapter);
        this.mAdapter.setClickListener(new DeviceGroupFragment$$ExternalSyntheticLambda1());
        subscribeUI();
        this.mBinding.layoutNoGroup.setOnClickListener(new DeviceGroupFragment$$ExternalSyntheticLambda2());
    }

    static /* synthetic */ void lambda$onViewCreated$0(GroupItemBean groupItemBean) {
        if (groupItemBean.getDeviceType() == 6) {
            JumpManager.getInstance().jumpToGroupDetail6(groupItemBean);
        } else {
            JumpManager.getInstance().jumpToGroupDetail(groupItemBean);
        }
    }

    private void subscribeUI() {
        this.mViewModel.setGroupListDataObserver(this, new DeviceGroupFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public void updateGroupList(List<GroupItemBean> list) {
        this.mAdapter.setData(list);
        this.mAdapter.notifyDataSetChanged();
    }
}

package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter;
import com.mergbw.android.ui.deviceDetail.adapter.SceneItemAdapter2;
import com.mergbw.android.ui.deviceDetail.fragment.BaseSceneListFragment;
import com.mergbw.android.ui.groupDetail.viewModel.GroupDetailViewModel1;
import com.mergbw.core.bean.SceneData;
import com.mergbw.core.bean.SceneGroupData;

public class GroupSceneListFragment extends BaseSceneListFragment {
    private final SceneGroupData mData;
    private SceneItemAdapter mItemAdapter;
    private SceneItemAdapter2 mItemAdapter2;
    private GroupDetailViewModel1 mViewModel;

    public GroupSceneListFragment(SceneGroupData sceneGroupData) {
        this.mData = sceneGroupData;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewModel = (GroupDetailViewModel1) new ViewModelProvider((ViewModelStoreOwner) requireActivity()).get(GroupDetailViewModel1.class);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        if (this.mData.getType() == 1) {
            this.mBinding.listScene.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
            SceneItemAdapter sceneItemAdapter = new SceneItemAdapter();
            this.mItemAdapter = sceneItemAdapter;
            sceneItemAdapter.setClickListener(new GroupSceneListFragment$$ExternalSyntheticLambda0(this));
            this.mBinding.listScene.setAdapter(this.mItemAdapter);
            this.mItemAdapter.setData(this.mData.getSceneDataList());
            this.mItemAdapter.notifyItemInserted(0);
            return;
        }
        this.mBinding.listScene.setLayoutManager(new LinearLayoutManager(this.mContext));
        SceneItemAdapter2 sceneItemAdapter2 = new SceneItemAdapter2();
        this.mItemAdapter2 = sceneItemAdapter2;
        sceneItemAdapter2.setClickListener(new GroupSceneListFragment$$ExternalSyntheticLambda1(this));
        this.mBinding.listScene.setAdapter(this.mItemAdapter2);
        this.mItemAdapter2.setData(this.mData.getSceneDataList());
        this.mItemAdapter2.notifyItemInserted(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupSceneListFragment  reason: not valid java name */
    public /* synthetic */ void m827lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupSceneListFragment(SceneData sceneData) {
        this.mViewModel.setScene(sceneData);
        if (getParentFragment() != null) {
            ((GroupSceneSettingFragment) getParentFragment()).resetSelected(3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-groupDetail-fragment-GroupSceneListFragment  reason: not valid java name */
    public /* synthetic */ void m828lambda$onViewCreated$1$commergbwandroiduigroupDetailfragmentGroupSceneListFragment(SceneData sceneData) {
        this.mViewModel.setScene(sceneData);
        if (getParentFragment() != null) {
            ((GroupSceneSettingFragment) getParentFragment()).resetSelected(3);
        }
    }

    public void resetSelected(int i) {
        if (i == 1) {
            SceneItemAdapter sceneItemAdapter = this.mItemAdapter;
            if (sceneItemAdapter != null) {
                sceneItemAdapter.resetSelected();
            }
            SceneItemAdapter2 sceneItemAdapter2 = this.mItemAdapter2;
            if (sceneItemAdapter2 != null) {
                sceneItemAdapter2.resetSelected();
            }
        }
    }
}

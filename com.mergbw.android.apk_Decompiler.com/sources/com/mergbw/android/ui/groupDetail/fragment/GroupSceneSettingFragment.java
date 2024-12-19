package com.mergbw.android.ui.groupDetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.R;
import com.mergbw.android.databinding.FragmentSceneSettingBinding;
import com.mergbw.android.ui.deviceDetail.adapter.SceneGroupAdapter;
import com.mergbw.core.bean.SceneGroupData;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.utils.ViewDataUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSceneSettingFragment extends BaseFragment {
    private FragmentSceneSettingBinding mBinding;
    private BaseFragment mCurrentFragment;
    private final Map<Integer, BaseFragment> mScenePageList = new HashMap();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSceneSettingBinding inflate = FragmentSceneSettingBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        this.mBinding.listSceneType.setLayoutManager(linearLayoutManager);
        SceneGroupAdapter sceneGroupAdapter = new SceneGroupAdapter();
        sceneGroupAdapter.setClickListener(new GroupSceneSettingFragment$$ExternalSyntheticLambda0(this));
        this.mBinding.listSceneType.setAdapter(sceneGroupAdapter);
        List<SceneGroupData> sceneData = ViewDataUtils.getSceneData();
        sceneGroupAdapter.setData(sceneData);
        sceneGroupAdapter.notifyItemInserted(0);
        GroupSceneListFragment groupSceneListFragment = new GroupSceneListFragment(sceneData.get(0));
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_content, groupSceneListFragment);
        beginTransaction.commit();
        this.mCurrentFragment = groupSceneListFragment;
        this.mScenePageList.put(Integer.valueOf(sceneData.get(0).getId()), groupSceneListFragment);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-groupDetail-fragment-GroupSceneSettingFragment  reason: not valid java name */
    public /* synthetic */ void m830lambda$onViewCreated$0$commergbwandroiduigroupDetailfragmentGroupSceneSettingFragment(SceneGroupData sceneGroupData) {
        if (this.mScenePageList.containsKey(Integer.valueOf(sceneGroupData.getId()))) {
            switchContent(this.mScenePageList.get(Integer.valueOf(sceneGroupData.getId())));
            return;
        }
        GroupSceneListFragment groupSceneListFragment = new GroupSceneListFragment(sceneGroupData);
        switchContent(groupSceneListFragment);
        this.mScenePageList.put(Integer.valueOf(sceneGroupData.getId()), groupSceneListFragment);
    }

    public void switchContent(BaseFragment baseFragment) {
        if (this.mCurrentFragment != baseFragment) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.mCurrentFragment).add(R.id.layout_content, (Fragment) baseFragment).commit();
            } else {
                beginTransaction.hide(this.mCurrentFragment).show(baseFragment).commit();
            }
            this.mCurrentFragment = baseFragment;
        }
    }

    public void resetSelected(int i) {
        for (BaseFragment next : this.mScenePageList.values()) {
            if (i != 3 || next != this.mCurrentFragment) {
                next.resetSelected(1);
            }
        }
        if (i == 3) {
            ((BaseActivity) requireActivity()).resetSelected();
        }
    }
}

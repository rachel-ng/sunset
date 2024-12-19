package com.mergbw.android.ui.deviceDetail.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mergbw.android.R;
import com.mergbw.android.databinding.FragmentSceneListBinding;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.views.ConfirmDialog;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;

public class BaseSceneListFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_AUDIO_PERMISSION = 100;
    public FragmentSceneListBinding mBinding;

    public void onAudioReady() {
    }

    public void onPermissionsDenied(int i, List<String> list) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentSceneListBinding inflate = FragmentSceneListBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void checkAudioPermission() {
        String[] strArr = {"android.permission.RECORD_AUDIO"};
        if (!EasyPermissions.hasPermissions(this.mContext, strArr)) {
            new ConfirmDialog(this.mContext, getString(R.string.need_audio_title), getString(R.string.need_audio_content), getString(17039360), getString(17039370), new BaseSceneListFragment$$ExternalSyntheticLambda0(this, strArr)).show();
        } else {
            onAudioReady();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkAudioPermission$0$com-mergbw-android-ui-deviceDetail-fragment-BaseSceneListFragment  reason: not valid java name */
    public /* synthetic */ void m736lambda$checkAudioPermission$0$commergbwandroiduideviceDetailfragmentBaseSceneListFragment(String[] strArr, boolean z) {
        if (z) {
            EasyPermissions.requestPermissions((Activity) requireActivity(), getString(R.string.need_audio_title), 100, strArr);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    public void onPermissionsGranted(int i, List<String> list) {
        if (i == 100) {
            onAudioReady();
        }
    }
}

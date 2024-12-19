package com.mergbw.android.ui.deviceDetail.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.fragment.app.Fragment;
import com.mergbw.android.R;
import com.mergbw.android.task.AudioService;
import com.mergbw.android.task.IAudioCallback;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.utils.MeRGBWLog;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;

public class BaseMusicFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_AUDIO_PERMISSION = 100;
    public AudioService mAudioService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MeRGBWLog.i("BindServiceSuccess");
            BaseMusicFragment.this.mAudioService = ((AudioService.MessageBinder) iBinder).getService();
            BaseMusicFragment.this.bindServiceSuccess();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            MeRGBWLog.i("onServiceDisconnected");
            BaseMusicFragment.this.mAudioService = null;
        }
    };

    /* access modifiers changed from: protected */
    public void bindServiceSuccess() {
    }

    public void onAudioReady() {
    }

    public void onPermissionsDenied(int i, List<String> list) {
    }

    public void checkAudioPermission() {
        String[] strArr = {"android.permission.RECORD_AUDIO"};
        if (!EasyPermissions.hasPermissions(this.mContext, strArr)) {
            new ConfirmDialog(this.mContext, getString(R.string.need_audio_title), getString(R.string.need_audio_content), getString(17039360), getString(17039370), new BaseMusicFragment$$ExternalSyntheticLambda0(this, strArr)).show();
        } else {
            onAudioReady();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$checkAudioPermission$0$com-mergbw-android-ui-deviceDetail-fragment-BaseMusicFragment  reason: not valid java name */
    public /* synthetic */ void m735lambda$checkAudioPermission$0$commergbwandroiduideviceDetailfragmentBaseMusicFragment(String[] strArr, boolean z) {
        if (z) {
            EasyPermissions.requestPermissions((Fragment) this, getString(R.string.need_audio_title), 100, strArr);
            MeRGBWLog.i("requestPermissions REQUEST_AUDIO_PERMISSION");
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        MeRGBWLog.i("onRequestPermissionsResult requestCode: " + i);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    public void onPermissionsGranted(int i, List<String> list) {
        if (i == 100) {
            onAudioReady();
        }
    }

    public void bindService() {
        requireActivity().getApplicationContext().bindService(new Intent(this.mContext, AudioService.class), this.mServiceConnection, 1);
    }

    public void setCallback(String str, IAudioCallback iAudioCallback) {
        AudioService audioService = this.mAudioService;
        if (audioService != null) {
            audioService.addCallBack(str, iAudioCallback);
        }
    }

    public void removeCallback(String str) {
        AudioService audioService = this.mAudioService;
        if (audioService != null) {
            audioService.removeCallBack(str);
        }
    }

    public void unBindService() {
        requireActivity().getApplicationContext().unbindService(this.mServiceConnection);
    }
}

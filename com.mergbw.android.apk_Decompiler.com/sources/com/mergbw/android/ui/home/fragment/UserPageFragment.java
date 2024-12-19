package com.mergbw.android.ui.home.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.mergbw.android.R;
import com.mergbw.android.UserinfoManage;
import com.mergbw.android.databinding.FragmentUserPageBinding;
import com.mergbw.android.google.GoogleTrackManager;
import com.mergbw.android.jump.JumpManager;
import com.mergbw.core.event.EventMessage;
import com.mergbw.core.event.GlobalEvent;
import com.mergbw.core.network.app.bean.UserResponse;
import com.mergbw.core.track.TrackManager;
import com.mergbw.core.ui.BaseFragment;
import com.mergbw.core.ui.views.ConfirmDialog;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.ui.views.EditPopWindow;
import com.mergbw.core.utils.MeRGBWLog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserPageFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public FragmentUserPageBinding mBinding;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EventBus.getDefault().register(this);
        FragmentUserPageBinding inflate = FragmentUserPageBinding.inflate(layoutInflater);
        this.mBinding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.layoutLogin.setOnClickListener(new UserPageFragment$$ExternalSyntheticLambda0(this));
        if (UserinfoManage.getInstance().IsLogin()) {
            refreshUserinfo((UserResponse) null);
        }
        this.mBinding.layoutAbout.setOnClickListener(new UserPageFragment$$ExternalSyntheticLambda1());
        this.mBinding.layoutContactUs.setOnClickListener(new UserPageFragment$$ExternalSyntheticLambda2(this));
        TextView textView = this.mBinding.tvVersion;
        textView.setText("V " + getVersionName());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$1$com-mergbw-android-ui-home-fragment-UserPageFragment  reason: not valid java name */
    public /* synthetic */ void m863lambda$onViewCreated$1$commergbwandroiduihomefragmentUserPageFragment(View view) {
        if (UserinfoManage.getInstance().IsLogin()) {
            EditPopWindow editPopWindow = new EditPopWindow(this.mContext, "设置用户名", this.mBinding.tvUsername.getText().toString(), getString(17039360), getString(17039370), 1, new EditDialog.OnEditListener() {
                public void onSkip() {
                }

                public void onEditText(String str) {
                    UserPageFragment.this.mBinding.tvUsername.setText(str);
                    UserinfoManage.getInstance().setNickname(str);
                }
            });
            editPopWindow.showAtLocation(this.mBinding.getRoot(), 80, 0, 0);
            WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
            attributes.alpha = 0.3f;
            requireActivity().getWindow().setAttributes(attributes);
            editPopWindow.setOnDismissListener(new UserPageFragment$$ExternalSyntheticLambda3(this));
            return;
        }
        JumpManager.getInstance().jumpToLogin();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$0$com-mergbw-android-ui-home-fragment-UserPageFragment  reason: not valid java name */
    public /* synthetic */ void m862lambda$onViewCreated$0$commergbwandroiduihomefragmentUserPageFragment() {
        WindowManager.LayoutParams attributes = requireActivity().getWindow().getAttributes();
        attributes.alpha = 1.0f;
        requireActivity().getWindow().setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onViewCreated$3$com-mergbw-android-ui-home-fragment-UserPageFragment  reason: not valid java name */
    public /* synthetic */ void m864lambda$onViewCreated$3$commergbwandroiduihomefragmentUserPageFragment(View view) {
        sendEmail();
        TrackManager.getInstance().homeClickTrack(2, 0, 0);
        GoogleTrackManager.getInstance().homeClickTrack(2, 0, 0);
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {
        MeRGBWLog.e("onEvent: " + eventMessage.getAction());
        if (GlobalEvent.USER_REGISTER_SUCCESS.equals(eventMessage.getAction()) || GlobalEvent.USER_LOGIN_SUCCESS.equals(eventMessage.getAction())) {
            refreshUserinfo((UserResponse) eventMessage.getData());
        }
    }

    private void refreshUserinfo(UserResponse userResponse) {
        FragmentUserPageBinding fragmentUserPageBinding = this.mBinding;
        if (fragmentUserPageBinding == null) {
            return;
        }
        if (userResponse != null) {
            fragmentUserPageBinding.tvUsername.setText(userResponse.getNickname() == null ? userResponse.getUsername() : userResponse.getNickname());
        } else {
            fragmentUserPageBinding.tvUsername.setText(UserinfoManage.getInstance().getNickname());
        }
    }

    private void sendEmail() {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:support@mergbw.com"));
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.support));
        intent.putExtra("android.intent.extra.TEXT", getString(R.string.support_hint) + "\n" + getString(R.string.support_item_1) + " v" + getVersionName() + "\n" + getString(R.string.support_item_2) + " " + Build.MANUFACTURER + " " + Build.BRAND + " " + Build.MODEL + "\n" + getString(R.string.support_item_3) + " Android " + Build.VERSION.RELEASE + "\n" + getString(R.string.support_item_4) + "\n" + getString(R.string.support_item_5) + "\n" + getString(R.string.support_item_6) + "\n");
        try {
            startActivity(intent);
        } catch (Exception unused) {
            new ConfirmDialog(this.mContext, getString(R.string.email_error), getString(R.string.email_tip), getString(17039360), getString(17039370), (ConfirmDialog.OnChooseListener) null).show();
        }
    }

    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    private PackageInfo getPackageInfo() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 16384);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.mergbw.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.mergbw.core.databinding.LayoutProgressDialogBinding;

public class BaseFragment extends Fragment {
    public static final int FROM_BROTHER = 2;
    public static final int FROM_CHILD = 3;
    public static final int FROM_PARENT = 1;
    public Context mContext;
    private LayoutProgressDialogBinding mWaitBinding;
    private Dialog mWaitDialog;

    public void bluetoothReady() {
    }

    public void resetSelected(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    /* access modifiers changed from: protected */
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, 0).show();
    }

    public void showWaitDialog() {
        Dialog dialog = this.mWaitDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mWaitDialog == null) {
                this.mWaitDialog = new Dialog(this.mContext);
                LayoutProgressDialogBinding inflate = LayoutProgressDialogBinding.inflate(getLayoutInflater());
                this.mWaitBinding = inflate;
                this.mWaitDialog.setContentView(inflate.getRoot());
                this.mWaitDialog.getWindow().setBackgroundDrawableResource(17170445);
                this.mWaitDialog.setCanceledOnTouchOutside(false);
            }
            this.mWaitDialog.setOnShowListener(new BaseFragment$$ExternalSyntheticLambda0(this));
            this.mWaitDialog.setOnDismissListener(new BaseFragment$$ExternalSyntheticLambda1(this));
            this.mWaitDialog.show();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showWaitDialog$0$com-mergbw-core-ui-BaseFragment  reason: not valid java name */
    public /* synthetic */ void m917lambda$showWaitDialog$0$commergbwcoreuiBaseFragment(DialogInterface dialogInterface) {
        this.mWaitBinding.viewLoading.playAnimation();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showWaitDialog$1$com-mergbw-core-ui-BaseFragment  reason: not valid java name */
    public /* synthetic */ void m918lambda$showWaitDialog$1$commergbwcoreuiBaseFragment(DialogInterface dialogInterface) {
        this.mWaitBinding.viewLoading.pauseAnimation();
    }

    public void hideWaitDialog() {
        Dialog dialog = this.mWaitDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mWaitDialog.dismiss();
        }
    }
}

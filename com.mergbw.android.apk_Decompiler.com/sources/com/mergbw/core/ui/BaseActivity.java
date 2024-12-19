package com.mergbw.core.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mergbw.core.databinding.LayoutProgressDialogBinding;
import com.mergbw.core.ui.views.BindTickDialog;
import com.mergbw.core.ui.views.ConnectingDialog;

public class BaseActivity extends AppCompatActivity {
    private Dialog mBindDialog;
    private Dialog mConnectingDialog;
    public Context mContext;
    private ProgressDialog mWait;
    private LayoutProgressDialogBinding mWaitBinding;
    private Dialog mWaitDialog;

    public void resetSelected() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void showToast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public void showWait(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mWait = progressDialog;
        progressDialog.setMessage(str);
        this.mWait.setCanceledOnTouchOutside(false);
        this.mWait.show();
    }

    public void hideWait() {
        ProgressDialog progressDialog = this.mWait;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mWait.dismiss();
        }
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
            this.mWaitDialog.setOnShowListener(new BaseActivity$$ExternalSyntheticLambda0(this));
            this.mWaitDialog.setOnDismissListener(new BaseActivity$$ExternalSyntheticLambda1(this));
            this.mWaitDialog.show();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showWaitDialog$0$com-mergbw-core-ui-BaseActivity  reason: not valid java name */
    public /* synthetic */ void m912lambda$showWaitDialog$0$commergbwcoreuiBaseActivity(DialogInterface dialogInterface) {
        this.mWaitBinding.viewLoading.playAnimation();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showWaitDialog$1$com-mergbw-core-ui-BaseActivity  reason: not valid java name */
    public /* synthetic */ void m913lambda$showWaitDialog$1$commergbwcoreuiBaseActivity(DialogInterface dialogInterface) {
        this.mWaitBinding.viewLoading.pauseAnimation();
    }

    public void hideWaitDialog() {
        Dialog dialog = this.mWaitDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mWaitDialog.dismiss();
        }
    }

    public void showConnectingDialog() {
        Dialog dialog = this.mConnectingDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mConnectingDialog == null) {
                this.mConnectingDialog = new ConnectingDialog(this.mContext);
            }
            this.mConnectingDialog.show();
        }
    }

    public void hideConnectingDialog() {
        Dialog dialog = this.mConnectingDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mConnectingDialog.dismiss();
        }
    }

    public void showBindDialog() {
        Dialog dialog = this.mBindDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mBindDialog == null) {
                this.mBindDialog = new BindTickDialog(this.mContext);
            }
            this.mBindDialog.show();
        }
    }

    public void hideBindDialog() {
        Dialog dialog = this.mBindDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mBindDialog.dismiss();
        }
    }
}

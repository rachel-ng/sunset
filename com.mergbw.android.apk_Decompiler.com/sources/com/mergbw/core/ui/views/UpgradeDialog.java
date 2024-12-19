package com.mergbw.core.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.mergbw.core.databinding.DialogUpgradeBinding;

public class UpgradeDialog extends Dialog {
    private DialogUpgradeBinding mBinding;

    public UpgradeDialog(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        DialogUpgradeBinding inflate = DialogUpgradeBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        setCancelable(false);
    }

    public void showProgress(float f) {
        DialogUpgradeBinding dialogUpgradeBinding = this.mBinding;
        if (dialogUpgradeBinding != null) {
            int i = (int) f;
            dialogUpgradeBinding.pbUpgrade.setProgress(i);
            TextView textView = this.mBinding.tvPbValue;
            textView.setText(i + "%");
        }
    }
}

package com.mergbw.core.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.mergbw.core.databinding.DialogBindTickBinding;
import java.util.Objects;

public class BindTickDialog extends Dialog {
    private DialogBindTickBinding mBinding;

    public BindTickDialog(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((Window) Objects.requireNonNull(getWindow())).setBackgroundDrawableResource(17170445);
        getWindow().getDecorView().setSystemUiVisibility(6);
        getWindow().setFlags(1024, 1024);
        DialogBindTickBinding inflate = DialogBindTickBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        setCancelable(false);
    }

    public void show() {
        super.show();
        this.mBinding.viewLoading.playAnimation();
    }

    public void dismiss() {
        super.dismiss();
        this.mBinding.viewLoading.pauseAnimation();
    }
}

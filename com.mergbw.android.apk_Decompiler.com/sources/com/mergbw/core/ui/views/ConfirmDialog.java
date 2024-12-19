package com.mergbw.core.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.mergbw.core.databinding.DialogConfirmBinding;
import java.util.Objects;

public class ConfirmDialog extends Dialog {
    private final String content;
    private final String lBtnText;
    private DialogConfirmBinding mBinding;
    private final OnChooseListener mListener;
    private final String rBtnText;
    private final String title;

    public interface OnChooseListener {
        void OnConfirmResult(boolean z);
    }

    public ConfirmDialog(Context context, String str, String str2, String str3, String str4, OnChooseListener onChooseListener) {
        super(context);
        this.title = str;
        this.content = str2;
        this.lBtnText = str3;
        this.rBtnText = str4;
        this.mListener = onChooseListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((Window) Objects.requireNonNull(getWindow())).setBackgroundDrawableResource(17170445);
        DialogConfirmBinding inflate = DialogConfirmBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        setCancelable(false);
        this.mBinding.tvTitle.setText(this.title);
        this.mBinding.tvContent.setText(this.content);
        this.mBinding.tvCancel.setText(this.lBtnText);
        this.mBinding.tvOk.setText(this.rBtnText);
        this.mBinding.tvOk.setOnClickListener(new ConfirmDialog$$ExternalSyntheticLambda0(this));
        this.mBinding.tvCancel.setOnClickListener(new ConfirmDialog$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-core-ui-views-ConfirmDialog  reason: not valid java name */
    public /* synthetic */ void m946lambda$onCreate$0$commergbwcoreuiviewsConfirmDialog(View view) {
        OnChooseListener onChooseListener = this.mListener;
        if (onChooseListener != null) {
            onChooseListener.OnConfirmResult(true);
        }
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-mergbw-core-ui-views-ConfirmDialog  reason: not valid java name */
    public /* synthetic */ void m947lambda$onCreate$1$commergbwcoreuiviewsConfirmDialog(View view) {
        OnChooseListener onChooseListener = this.mListener;
        if (onChooseListener != null) {
            onChooseListener.OnConfirmResult(false);
        }
        dismiss();
    }
}

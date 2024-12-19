package com.mergbw.core.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.mergbw.core.databinding.DialogEditBinding;
import com.mergbw.core.utils.StringUtils;

public class EditDialog extends Dialog {
    private final String content;
    private final String lText;
    private DialogEditBinding mBinding;
    private final OnEditListener mListener;
    private final String rText;
    private final String title;

    public interface OnEditListener {
        void onEditText(String str);

        void onSkip();
    }

    public EditDialog(Context context, String str, String str2, String str3, String str4, OnEditListener onEditListener) {
        super(context);
        this.title = str;
        this.content = str2;
        this.lText = str3;
        this.rText = str4;
        this.mListener = onEditListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(17170445);
        DialogEditBinding inflate = DialogEditBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        setCancelable(false);
        this.mBinding.tvTitle.setText(this.title);
        if (this.lText == null) {
            this.mBinding.tvSkip.setVisibility(4);
        } else {
            this.mBinding.tvSkip.setText(this.lText);
        }
        this.mBinding.tvSkip.setOnClickListener(new EditDialog$$ExternalSyntheticLambda0(this));
        this.mBinding.etEditText.setText(this.content);
        if (this.rText == null) {
            this.mBinding.tvDone.setVisibility(4);
        } else {
            this.mBinding.tvDone.setText(this.rText);
        }
        this.mBinding.tvDone.setOnClickListener(new EditDialog$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-core-ui-views-EditDialog  reason: not valid java name */
    public /* synthetic */ void m956lambda$onCreate$0$commergbwcoreuiviewsEditDialog(View view) {
        OnEditListener onEditListener = this.mListener;
        if (onEditListener != null) {
            onEditListener.onSkip();
        }
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$1$com-mergbw-core-ui-views-EditDialog  reason: not valid java name */
    public /* synthetic */ void m957lambda$onCreate$1$commergbwcoreuiviewsEditDialog(View view) {
        String obj = this.mBinding.etEditText.getText().toString();
        if (StringUtils.isEmpty(obj)) {
            this.mBinding.etEditText.requestFocus();
            return;
        }
        OnEditListener onEditListener = this.mListener;
        if (onEditListener != null) {
            onEditListener.onEditText(obj);
        }
        dismiss();
    }
}

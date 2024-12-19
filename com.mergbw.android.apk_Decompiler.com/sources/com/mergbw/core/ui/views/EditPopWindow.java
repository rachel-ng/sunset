package com.mergbw.core.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.mergbw.core.R;
import com.mergbw.core.databinding.PopEditBinding;
import com.mergbw.core.ui.views.EditDialog;
import com.mergbw.core.utils.AppUtils;
import com.mergbw.core.utils.StringUtils;

public class EditPopWindow extends PopupWindow {
    private final PopEditBinding mBinding;
    private final EditDialog.OnEditListener mListener;

    public interface OnEditListener {
        void onEditText(String str);

        void onSkip();
    }

    public EditPopWindow(Context context, String str, String str2, String str3, String str4, int i, EditDialog.OnEditListener onEditListener) {
        this.mListener = onEditListener;
        PopEditBinding inflate = PopEditBinding.inflate(LayoutInflater.from(context));
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        setWidth(-1);
        setHeight(-1);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.pop_anim);
        setInputMethodMode(1);
        setSoftInputMode(16);
        inflate.tvTitle.setText(str);
        if (str3 == null) {
            inflate.tvSkip.setVisibility(4);
        } else {
            inflate.tvSkip.setText(str3);
        }
        inflate.tvSkip.setOnClickListener(new EditPopWindow$$ExternalSyntheticLambda0(this));
        inflate.etEditText.setText(str2);
        inflate.etEditText.setInputType(i);
        if (str4 == null) {
            inflate.tvDone.setVisibility(4);
        } else {
            inflate.tvDone.setText(str4);
        }
        inflate.tvDone.setOnClickListener(new EditPopWindow$$ExternalSyntheticLambda1(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-mergbw-core-ui-views-EditPopWindow  reason: not valid java name */
    public /* synthetic */ void m958lambda$new$0$commergbwcoreuiviewsEditPopWindow(View view) {
        EditDialog.OnEditListener onEditListener = this.mListener;
        if (onEditListener != null) {
            onEditListener.onSkip();
        }
        dismiss();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-mergbw-core-ui-views-EditPopWindow  reason: not valid java name */
    public /* synthetic */ void m959lambda$new$1$commergbwcoreuiviewsEditPopWindow(int i, View view) {
        String obj = this.mBinding.etEditText.getText().toString();
        if (StringUtils.isEmpty(obj)) {
            this.mBinding.etEditText.requestFocus();
            Toast.makeText(AppUtils.getApp(), R.string.value_not_empty, 0).show();
        } else if (i != 2 || StringUtils.checkNumber(obj)) {
            EditDialog.OnEditListener onEditListener = this.mListener;
            if (onEditListener != null) {
                onEditListener.onEditText(obj);
            }
            dismiss();
        } else {
            this.mBinding.etEditText.requestFocus();
            Toast.makeText(AppUtils.getApp(), R.string.value_not_number, 0).show();
        }
    }
}

package com.mergbw.android.ui.factoryArea;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mergbw.android.R;
import com.mergbw.android.databinding.DialogChooseSiteBinding;
import com.mergbw.android.ui.factoryArea.adapter.SiteItemAdapter;
import com.mergbw.core.network.factory.bean.FactoryInfoBean;
import java.util.List;

public class ChooseSiteDialog extends Dialog {
    private DialogChooseSiteBinding mBinding;
    private final Context mContext;
    private final String mCurrentSite;
    private final ChooseListener mListener;
    private final List<FactoryInfoBean.SiteInfo> mSiteList;

    public interface ChooseListener {
        void chooseResult(FactoryInfoBean.SiteInfo siteInfo);
    }

    public ChooseSiteDialog(Context context, List<FactoryInfoBean.SiteInfo> list, String str, ChooseListener chooseListener) {
        super(context, R.style.TransparentDialog);
        this.mContext = context;
        this.mListener = chooseListener;
        this.mSiteList = list;
        this.mCurrentSite = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DialogChooseSiteBinding inflate = DialogChooseSiteBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView(inflate.getRoot());
        this.mBinding.listSite.setLayoutManager(new LinearLayoutManager(this.mContext));
        SiteItemAdapter siteItemAdapter = new SiteItemAdapter();
        this.mBinding.listSite.setAdapter(siteItemAdapter);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.mSiteList.size()) {
                break;
            } else if (this.mCurrentSite.equals(this.mSiteList.get(i2).getSiteCode())) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        siteItemAdapter.setData(this.mSiteList, i);
        siteItemAdapter.setClickListener(new ChooseSiteDialog$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$com-mergbw-android-ui-factoryArea-ChooseSiteDialog  reason: not valid java name */
    public /* synthetic */ void m788lambda$onCreate$0$commergbwandroiduifactoryAreaChooseSiteDialog(FactoryInfoBean.SiteInfo siteInfo) {
        ChooseListener chooseListener = this.mListener;
        if (chooseListener != null) {
            chooseListener.chooseResult(siteInfo);
        }
        dismiss();
    }
}

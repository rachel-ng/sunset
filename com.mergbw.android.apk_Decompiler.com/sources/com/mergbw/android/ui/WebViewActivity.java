package com.mergbw.android.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.internal.ImagesContract;
import com.mergbw.android.databinding.ActivityWebviewBinding;
import com.mergbw.core.ui.BaseActivity;
import com.mergbw.core.utils.MeRGBWLog;

public class WebViewActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public ActivityWebviewBinding mBinding;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private String mTitle;
    private String url;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityWebviewBinding inflate = ActivityWebviewBinding.inflate(getLayoutInflater());
        this.mBinding = inflate;
        setContentView((View) inflate.getRoot());
        this.mTitle = getIntent().getStringExtra("title");
        this.url = getIntent().getStringExtra(ImagesContract.URL);
        initViews();
    }

    private void initViews() {
        this.mBinding.ivBack.setOnClickListener(new WebViewActivity$$ExternalSyntheticLambda0(this));
        this.mBinding.webView.getSettings().setJavaScriptEnabled(true);
        this.mBinding.webView.setWebViewClient(new WebViewClient());
        this.mBinding.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i < 100) {
                    WebViewActivity.this.mBinding.webProgress.setVisibility(0);
                    WebViewActivity.this.mBinding.webProgress.setProgress(i);
                    return;
                }
                WebViewActivity.this.mBinding.webProgress.setVisibility(8);
            }
        });
        this.mBinding.webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                MeRGBWLog.i("onPageStarted url: " + str);
            }

            public void onPageFinished(WebView webView, String str) {
                MeRGBWLog.i("onPageFinished url: " + str);
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                MeRGBWLog.i("onReceivedError code: " + webResourceError.getErrorCode() + " error: " + webResourceError.getDescription());
            }

            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (!"returntoapp".equals(webResourceRequest.getUrl().getHost())) {
                    return super.shouldOverrideUrlLoading(webView, webResourceRequest);
                }
                WebViewActivity.this.finish();
                return true;
            }
        });
        this.mBinding.tvTitle.setText(this.mTitle);
        this.mBinding.webView.loadUrl(this.url);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initViews$0$com-mergbw-android-ui-WebViewActivity  reason: not valid java name */
    public /* synthetic */ void m696lambda$initViews$0$commergbwandroiduiWebViewActivity(View view) {
        finish();
    }
}

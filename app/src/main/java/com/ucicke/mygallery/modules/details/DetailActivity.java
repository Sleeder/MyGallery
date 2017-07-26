package com.ucicke.mygallery.modules.details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ucicke.mygallery.R;
import com.ucicke.mygallery.base.BaseActivity;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.photo_detail_web_view)
    WebView mWebView;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.loadUrl(getIntent().getData().toString());

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }

    public static Intent newIntent(Context context, Uri photoPageUri) {
        Intent i = new Intent(context, DetailActivity.class);
        i.setData(photoPageUri);
        return i;
    }
}

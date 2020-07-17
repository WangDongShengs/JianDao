package com.wds.jiandao;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wds.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {
    @BindView(R.id.web_cl_item)
    WebView webClItem;

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData() {
        super.initData();
        String link = getIntent().getStringExtra("link");
        webClItem.loadUrl(link);
        webClItem.setWebViewClient(new WebViewClient());
    }
}

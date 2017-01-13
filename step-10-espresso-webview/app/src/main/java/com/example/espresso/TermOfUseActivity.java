package com.example.espresso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;

public class TermOfUseActivity extends AppCompatActivity {
    public static final String ARG_FILE = "FILE";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_use);
        String file = getIntent().getStringExtra(ARG_FILE);
        if (TextUtils.isEmpty(file)) {
            file = "tos.html";
        }
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/" + file);
    }
}

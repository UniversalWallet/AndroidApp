package com.bashalex.cryptoinvesting;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import nucleus.view.NucleusAppCompatActivity;

/**
 * Created by bashalex on 27.01.16.
 */
public class NewsActivity extends NucleusAppCompatActivity {

    private WebView mWebView;
    private static final String TAG = "NewsActivity";
    
    private RelativeLayout mainView;
    private RelativeLayout mainLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Set the Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);

        // Load url
        showProgress();
        Intent intent = getIntent();
        String url = intent.getStringExtra(getString(R.string.url));
        mWebView = (WebView) findViewById(R.id.mainWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                dismissProgress();
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mWebView.getProgress() == 100 && mWebView.getTitle().length() == 0) {
//            finish();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgress() {
        mainView = new RelativeLayout(this);
        mainView.setBackgroundColor(Color.TRANSPARENT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mainView.setLayoutParams(params);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams progressParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        progressBar.setLayoutParams(progressParams);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
        mainView.addView(progressBar);
        if (mainLayout != null) {
            mainLayout.addView(mainView);
            mainLayout.setEnabled(false);
        }
    }

    public void dismissProgress() {
        if (mainLayout != null) {
            mainLayout.removeView(mainView);
            mainLayout.setEnabled(true);
        }
    }
}

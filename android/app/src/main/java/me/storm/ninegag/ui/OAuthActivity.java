package me.storm.ninegag.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.storm.ninegag.R;
import me.storm.ninegag.api.GisterApi;

/**
 * Created by storm on 14-4-16.
 */
public class OAuthActivity extends BaseActivity {

    @InjectView(R.id.login_webview)
    WebView loginWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ButterKnife init (automates the views system)
        ButterKnife.inject(this);
        String url = GisterApi.getStageUrl(GisterApi.OauthStage.THIRD);

        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "access_token=";

                // We hijack the GET request to extract the OAuth parameters
                if (url.contains(accessTokenFragment)) {
                    // the GET request contains directly the token
                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
                    putStringToSharedPreferences(getString(R.string.access_token), accessToken.split("=")[1]);

                    // So we send it and finish.
                    Intent data = new Intent();
                    data.putExtra(getString(R.string.access_token), accessToken.split("=")[1]);
                    setResult(BaseActivity.RESULT_OK, data);
                    finish();
                } else if (url.equals(GisterApi.getStageUrl(GisterApi.OauthStage.SECOND))) {
                    // Requesting..
                    view.loadUrl(GisterApi.getStageUrl(GisterApi.OauthStage.THIRD));
                }
            }
        });
        loginWebView.loadUrl(url);
    }
}
package me.storm.ninegag.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.storm.ninegag.R;
import me.storm.ninegag.api.GithubApi;
import me.storm.ninegag.data.GsonRequest;
import me.storm.ninegag.model.Feed;
import me.storm.ninegag.ui.adapter.FeedsAdapter;
import me.storm.ninegag.view.ProgressWheel;
import me.storm.ninegag.view.swipeback.SwipeBackActivity;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by storm on 14-4-15.
 */
public class ImageViewActivity extends BaseActivity {
    Feed feed=null;

    @InjectView(R.id.webView)
    WebView webView;

    @InjectView(R.id.fork_page)
    BootstrapButton fork_button;

    @InjectView(R.id.share_page)
    BootstrapButton share_button;

    @InjectView(R.id.eye_page)
    BootstrapButton eye_button;

    @OnClick(R.id.fork_page) void forkOnClick() {
        setTimeNotification(feed.id, feed.title);
        Map<String, String> keys =
                new HashMap<String, String>();
        String key = getFromSharedPreferences(getString(R.string.github_token, ""));
        executeRequest(new GsonRequest(Request.Method.POST,GithubApi.fork(key, feed.git_id),
                Feed.FeedRequestData.class,
                null,
                responseListener(),
                errorListener()));
        Log.e("ImageViewActivity","fork_page onclick");
    }
    @OnClick(R.id.share_page) void shareOnClick() {
        Log.e("ImageViewActivity","share_page onclick");
    }
    @OnClick(R.id.eye_page) void eyeOnClick() {
        Log.e("ImageViewActivity", "eye_page onclick");
    }
    private PhotoViewAttacher mAttacher;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ButterKnife.inject(this);
        String git_id = getIntent().getStringExtra("GIST_ID");
        feed=null;
        feed = Feed.getFromCache(git_id);
        setTitle(feed.title);

        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(feed.script_url, "", "utf-8");

    }


    private Response.Listener<Feed.FeedRequestData> responseListener() {
        return new Response.Listener<Feed.FeedRequestData>() {
            @Override
            public void onResponse(final Feed.FeedRequestData response) {
                Log.e("ImageViewActivity", "forked in guthub");
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAttacher != null) {
            mAttacher.cleanup();
        }
    }
}

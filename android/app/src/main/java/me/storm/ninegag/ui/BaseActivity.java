package me.storm.ninegag.ui;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import me.storm.ninegag.R;
import me.storm.ninegag.data.RequestManager;
import me.storm.ninegag.ui.Receivers.GistLikeReceiver;
import me.storm.ninegag.ui.fragment.BaseFragment;
import me.storm.ninegag.util.FunctionVault;
import me.storm.ninegag.util.ToastUtils;

/**
 * Created by storm on 14-3-24.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected ActionBar actionBar;
    private ShimmerTextView mActionBarTitle;
    public  AlarmManager alarmMgr;

    protected BaseFragment mContentFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void initActionBar() {
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        View view = View.inflate(this, R.layout.actionbar_title, null);
        mActionBarTitle = (ShimmerTextView) view.findViewById(R.id.tv_shimmer);
        new Shimmer().start(mActionBarTitle);
        actionBar.setCustomView(view);
    }

    public void setTitle(int resId) {
        mActionBarTitle.setText(resId);
    }

    public void setTitle(CharSequence text) {
        mActionBarTitle.setText(text);
    }

    protected void replaceFragment(int viewId, BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showLong(error.getMessage());
            }
        };
    }

    public void setTimeNotification(String gist_id,String gist_title) {
        int half_hour_millis=30*60*1000;
        FunctionVault function=new FunctionVault();
        Intent intent = new Intent(this, GistLikeReceiver.class);
        String gist_by_num=gist_id.substring(21);
        int notificationID=(int)function.toAscii(gist_by_num);
        intent.putExtra("GIST_ID", gist_id);
        intent.putExtra("GIST_TITLE", gist_title);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, notificationID, intent, 0);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        half_hour_millis, alarmIntent);
    }

    public <T extends BaseFragment> T getContentFragment(Class<T> type) {
        return type.cast(mContentFragment);
    }

}

package me.storm.ninegag.ui.Receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import me.storm.ninegag.R;


/**
 * Created by Yoni Mood on 11/16/2015.
 */
public class GistLikeReceiver extends BroadcastReceiver {

    NotificationManager mNotificationManager;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("GistLikeReceiver", "GistLikeReceiver, ok");
        this.context = context;
        int gistId = intent.getExtras().getInt("GIST_ID");
        String gistTitle = intent.getExtras().getString("GIST_TITLE");

        mNotificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(400);


        setNotification(gistId, gistTitle, context);
    }

    public void setNotification(final int gitId, final String gistTitle, Context ctx) {
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Was this gist helpful?");
        mBuilder.setAutoCancel(true);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        mBuilder.setContentText(gistTitle);
                        mNotificationManager.notify(gitId, mBuilder.build());
                    }
                }

        ).start();
    }




}

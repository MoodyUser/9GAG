package me.storm.ninegag.ui.Receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Locale;

import me.storm.ninegag.R;
import me.storm.ninegag.model.Feed;
import me.storm.ninegag.util.FunctionVault;


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
        String gist_id = intent.getExtras().getString("GIST_ID");
        String gist_title = intent.getExtras().getString("GIST_TITLE");

        mNotificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(400);


        setNotification(gist_id, gist_title, context);


    }

    public void setNotification(final String git_id, final String gist_title, Context ctx) {
        final FunctionVault function=new FunctionVault();
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Was this gist helpful?");
        mBuilder.setAutoCancel(true);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        mBuilder.setContentText(gist_title);
                        String gist_by_num=git_id.substring(21);
                        int notificationID=(int)function.toAscii(gist_by_num);
                        mNotificationManager.notify(notificationID, mBuilder.build());
                    }
                }

        ).start();
    }




}

package com.gita.pushnotificationsgcm.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.gita.pushnotificationsgcm.MainActivity;
import com.gita.pushnotificationsgcm.R;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by alex on 11/19/2016
 */

public class GCMPushReceiverService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String title = data.getString("title");
        sendNotification(message,title);
    }

    private void sendNotification(String message,String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("message", message);
        intent.putExtra("title", title);
        intent.putExtra("fromPush", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= 16) builder.setPriority(Notification.PRIORITY_HIGH);
        //This is hack
        if (Build.VERSION.SDK_INT >= 21) builder.setVibrate(new long[0]);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
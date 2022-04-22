package com.example.exchange;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private static final String CH_ID = "exchange_notification_channel";
    private final int NOTIF_ID = 0;

    private Context mContext;
    private NotificationManager manager;

    public NotificationHandler(Context context) {
        this.mContext = context;
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        createChannel();
    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            return;
        }

        NotificationChannel channel = new NotificationChannel(CH_ID, "Exchange notification", NotificationManager.IMPORTANCE_DEFAULT);

        channel.setDescription("Exchange app notification");
        this.manager.createNotificationChannel(channel);

    }

    public void send(String message){
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CH_ID)
                .setContentTitle("Exchange notification")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_euro_symbol_24)
                .setContentIntent(pendingIntent);
        this.manager.notify(NOTIF_ID, builder.build());
    }
}

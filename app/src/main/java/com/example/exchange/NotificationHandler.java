package com.example.exchange;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHandler {
    private static final String CH_ID = "exchange_notification_ch";
    private final int NOTIF_ID = 0;

    private Context mContext;
    private NotificationManager manager;

    public NotificationHandler(Context context) {
        this.mContext = context;
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CH_ID)
                .setContentTitle("Exchange notification")
                .setContentText(message);
        this.manager.notify(NOTIF_ID, builder.build());
    }
}

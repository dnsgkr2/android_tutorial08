package com.example.c.t16_br3;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by c on 2015-08-02.
 */
public class MyCustomBR extends BroadcastReceiver{
    NotificationManager manager;

    @Override
    public void onReceive(Context context, Intent intent) {
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}

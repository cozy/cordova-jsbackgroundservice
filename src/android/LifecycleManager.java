package io.cozy.jsbackgroundservice;

import android.util.Log;

import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;

public class LifecycleManager {

    private static final String TAG = "JSBackgorundPlugin";

    private Context context;

    public LifecycleManager(Context context) {
        this.context = context;
    }

    private PendingIntent getServicePendingIntent() {
        return getServicePendingIntent(PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private PendingIntent getServicePendingIntent(int flag) {
        Intent intent = new Intent(context, WebViewService.class);
        return PendingIntent.getService(context, 0, intent, flag);
    }

    public void startAlarmManager(long periodMillis) {
        if (periodMillis < 0) {
            periodMillis = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        }

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            0,
            periodMillis,
            getServicePendingIntent());
    }

    public void stopAlarmManager() {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        PendingIntent pi = getServicePendingIntent();
        alarmManager.cancel(pi);
        pi.cancel();
    }

    public boolean isRepeating() {
        return getServicePendingIntent(PendingIntent.FLAG_NO_CREATE) != null;
    }

}

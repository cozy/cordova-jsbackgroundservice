package io.cozy.jsbackgroundservice;

import android.util.Log;

import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.os.SystemClock;

import android.content.Context;

public class LifecycleManager {
    private static final String TAG = "JSBackgroundPlugin";

    private static final long DEFAULT_REPEATING_PERIOD = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
    private static final long DEBOUNCE_DURATION = 10 * 1000;

    private Context context;

    public LifecycleManager(Context context) {
        this.context = context;
    }

    private PendingIntent getServicePendingIntent() {
        return getServicePendingIntent(PendingIntent.FLAG_CANCEL_CURRENT, null);
    }

    private PendingIntent getServicePendingIntent(int flag) {
        return getServicePendingIntent(flag, null);
    }

    private PendingIntent getServicePendingIntent(int flag, String action) {
        Intent intent = new Intent(context, WebViewService.class);
        if (action != null) {
            intent.setAction(action);
        }
        return PendingIntent.getService(context, 0, intent, flag);
    }

    public void debouncedStart(String action) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        PendingIntent pi = getServicePendingIntent(
                PendingIntent.FLAG_CANCEL_CURRENT, action);

        // Remove previously set alarms
        alarmManager.cancel(pi);

        // Set the new one.
        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + DEBOUNCE_DURATION,
            pi);
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

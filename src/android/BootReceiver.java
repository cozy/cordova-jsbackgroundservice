package io.cozy.jsbackgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.Context;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
            JSBackgroundServicePlugin.PREFERENCES, Context.MODE_PRIVATE);

        if (sharedPrefs.getBoolean(
              JSBackgroundServicePlugin.PREF_IS_REPEATING, false)) {
            LifecycleManager manager = new LifecycleManager(context);

            // @TODO : use the last specified period value !
            manager.startAlarmManager(-1);
        }
    }
}

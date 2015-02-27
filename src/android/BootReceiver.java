package io.cozy.jsbackgroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LifecycleManager manager = new LifecycleManager(context);

        // @TODO : use the last specified period value !
        manager.startAlarmManager(-1);
    }
}

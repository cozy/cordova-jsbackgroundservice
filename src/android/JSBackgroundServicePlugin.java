package io.cozy.jsbackgroundservice;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;


import android.util.Log;

import org.apache.cordova.CordovaPlugin;

import android.content.Intent;

import android.app.AlarmManager;
import java.lang.System;
import android.app.PendingIntent;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;

import io.cozy.jsbackgroundservice.WebViewService;

public class JSBackgroundServicePlugin extends CordovaPlugin {

    private static final String TAG = "JSBackgorundPlugin";


    @Override
    public boolean execute(final String action, final JSONArray data, final CallbackContext callback) throws JSONException {
        Log.d("mysuperservice", "executorrrrrr");

        boolean result = true;

        if ("start".equals(action)) {
            startAlarmManager();

        } else if ("stop".equals(action)) {
            stopAlarmManager();

        } else {
            result = false;
        }

        return result;
    }

    // Tools
    private PendingIntent getServicePendingIntent() {
        Context context = this.cordova.getActivity();

        Intent intent = new Intent(context, WebViewService.class);
        // TODO : test flags !
        return PendingIntent.getService(context, 0, intent, 0);
    }

    private void startAlarmManager() {
        AlarmManager alarmManager = (AlarmManager)this.cordova.getActivity().getSystemService(this.cordova.getActivity().ALARM_SERVICE);

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            0,
            60 * 1000, //AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            getServicePendingIntent());
    }

    private void stopAlarmManager() {
        AlarmManager alarmManager = (AlarmManager)this.cordova.getActivity().getSystemService(this.cordova.getActivity().ALARM_SERVICE);
        alarmManager.cancel(getServicePendingIntent());
    }

}

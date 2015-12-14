package io.cozy.jsbackgroundservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

// Run webview in background
import android.view.WindowManager;
import android.webkit.WebView;
import android.view.WindowManager.LayoutParams;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

// Cordova webview...
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.Config;
import android.os.Bundle;
import org.apache.cordova.CordovaWebView;
import android.content.Intent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.app.Activity;
import org.apache.cordova.CordovaPlugin;


import android.webkit.JavascriptInterface;
import android.app.AlarmManager;
import java.lang.System;
import android.app.PendingIntent;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import android.app.Service;
import android.os.IBinder;

import android.content.SharedPreferences;

public class WebViewService extends Service implements CordovaInterface {

    private final static String TAG =  "JSBackgroundPlugin";


    private CordovaWebView wv;
    private ServiceAsActivity dummyActivity;
    private ExecutorService threadPool;

    // Service lifecycle
    @Override
    public void onCreate() {
        super.onCreate();
        dummyActivity = new ServiceAsActivity(this);
        threadPool = Executors.newCachedThreadPool();

        Config.init(getActivity());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Avoid start Webview of the service while CordovaApp activity is running.
        SharedPreferences sharedPrefs = getSharedPreferences(
            JSBackgroundServicePlugin.PREFERENCES, MODE_PRIVATE);
        boolean foreground = sharedPrefs.getBoolean(
            JSBackgroundServicePlugin.PREF_ACTIVITY_FOREGROUND, false);

        if (foreground) {
            // Let the foreground play. Shutdown the service.
            stopSelf();
        } else if (wv != null) {
            Log.d(TAG, "Service WebView already running, let it works.");
        } else { // !foreground && wv == null) {
            // Claim service is running
            setPreference(JSBackgroundServicePlugin.PREF_SERVICE_RUNNING, true);
            createBackGroundView();

        }
        return START_NOT_STICKY;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        destroyBackGroundView();
        super.onDestroy();
    }

    //////
    // Run javascript as a service.
    //////

    private class JsObject {
        @JavascriptInterface
        public void workDone() {
            Log.d(TAG, "work done !");
            // destroyBackGroundView();
            stopSelf();
        }
    }

    public void createBackGroundView(){
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutParams params = new WindowManager.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
           );

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;
        params.width = 0;
        params.height = 0;

        wv = new CordovaWebView(this);
        wv.setLayoutParams(new LinearLayout.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
            ));
        wv.getSettings().setJavaScriptEnabled(true);
        //wv.setWebChromeClient(new WebChromeClient());

        wv.addJavascriptInterface(new JsObject(), "service");
        wv.loadUrl("file:///android_asset/www/backgroundservice.html");

        // wv.setWebViewClient(new WebViewClient() {

        //     @Override
        //     public void onReceivedError(final WebView view, int errorCode,
        //             String description, final String failingUrl) {
        //         Log.d("Error","loading web view");
        //         super.onReceivedError(view, errorCode, description, failingUrl);
        //     }
        // });
        windowManager.addView(wv, params);
    }

    public void destroyBackGroundView() {
        // Should run in UI thread.

        if (wv != null) {
            // If CordovaApp activity is still alive do not destroy the plugins.
            SharedPreferences sharedPrefs = getSharedPreferences(
                JSBackgroundServicePlugin.PREFERENCES, MODE_PRIVATE);
            boolean alive = sharedPrefs.getBoolean(
                JSBackgroundServicePlugin.PREF_ACTIVITY_ALIVE, false);
            if (!alive && wv.pluginManager != null) {
                wv.pluginManager.onDestroy();
            }
            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            windowManager.removeView(wv);

            wv.clearHistory();
            wv.clearCache(true);
            wv.clearView();

            wv.destroy();
            wv = null;

            setPreference(JSBackgroundServicePlugin.PREF_SERVICE_RUNNING, false);
        }
    }


    private void setPreference(String key, boolean value) {
        SharedPreferences preferences = getSharedPreferences(JSBackgroundServicePlugin.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    //////
    // CordovaInterface
    //////

    /**
     * Launch an activity for which you would like a result when it finished. When this activity exits,
     * your onActivityResult() method will be called.
     *
     * @param command     The command object
     * @param intent      The intent to start
     * @param requestCode   The request code that is passed to callback to identify the activity
     */
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        // doesn't exist for services.
        // do nothing.
    }

    /**
     * Set the plugin to be called when a sub-activity exits.
     *
     * @param plugin      The plugin on which onActivityResult is to be called
     */
    public void setActivityResultCallback(CordovaPlugin plugin) {
    }

    /**
     * Get the Android activity.
     *
     * @return the Activity
     */
    public Activity getActivity() {
        return dummyActivity;
    }


    /**
     * Called when a message is sent to plugin.
     *
     * @param id            The message id
     * @param data          The message data
     * @return              Object or null
     */
    public Object onMessage(String id, Object data) {
        return null;
    }

    /**
     * Returns a shared thread pool that can be used for background tasks.
     */
    @Override
    public ExecutorService getThreadPool() {
        return threadPool;
    }



}

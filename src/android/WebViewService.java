package io.cozy.jsbackgroundservice;


import java.util.ArrayList;

import android.util.Log;
import android.content.Intent;
import android.app.Service;
import android.os.IBinder;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginEntry;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewImpl;



public class WebViewService extends Service {

    private final static String TAG =  "JSBackgroundPlugin";

    private ServiceAsActivity dummyActivity;
    protected CordovaWebView appView;


    // Read from config.xml:
    protected CordovaPreferences preferences;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaInterfaceImpl cordovaInterface;

    // Service lifecycle

    @Override
    public void onCreate() {
        // need to activate preferences before super.onCreate to avoid "requestFeature() must be called before adding content" exception

        dummyActivity = new ServiceAsActivity(this);
        loadConfig();

        super.onCreate();
        cordovaInterface = makeCordovaInterface();
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
        } else if (appView != null) {
            Log.d(TAG, "Service WebView already running, let it works.");
        } else { // !foreground && wv == null) {
            // Claim service is running
            setPreference(JSBackgroundServicePlugin.PREF_SERVICE_RUNNING, true);

            loadUrl();
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



    ////

    @SuppressWarnings("deprecation")
    protected void loadConfig() {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        preferences = parser.getPreferences();
        pluginEntries = parser.getPluginEntries();
    }

    /**
     * Construct the default web view object.
     * <p/>
     * Override this to customize the webview that is used.
     */
    protected CordovaWebView makeWebView() {
        return new CordovaWebViewImpl(makeWebViewEngine());
    }

    protected CordovaWebViewEngine makeWebViewEngine() {
        return CordovaWebViewImpl.createEngine(dummyActivity, preferences);
    }

    protected CordovaInterfaceImpl makeCordovaInterface() {
        return new CordovaInterfaceImpl(dummyActivity);
    }

    ////


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

    public void loadUrl() {
        appView = makeWebView();
        if (!appView.isInitialized()) {
            appView.init(cordovaInterface, pluginEntries, preferences);
        }
        cordovaInterface.onCordovaInit(appView.getPluginManager());

        WebView wv = (WebView)appView.getView();

        int webViewId = 100
        wv.setId(webViewId);
        wv.setLayoutParams(new LinearLayout.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
        ));

        //wv.getSettings().setJavaScriptEnabled(true);


        wv.addJavascriptInterface(new JsObject(), "service");
        //// wv.loadUrl("file:///android_asset/www/backgroundservice.html");

        appView.loadUrlIntoView("file:///android_asset/www/index.html?backgroundservice=true", true);

    }

    public void destroyBackGroundView() {
        // Should run in UI thread.
        if (appView != null) {
            appView.handleDestroy();
            appView = null;

        }

        setPreference(JSBackgroundServicePlugin.PREF_SERVICE_RUNNING, false);
    }


    private void setPreference(String key, boolean value) {
        SharedPreferences preferences = getSharedPreferences(JSBackgroundServicePlugin.PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}

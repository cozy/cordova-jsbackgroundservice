package io.cozy.jsbackgroundservice;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;

import android.text.TextUtils;

import android.content.SharedPreferences;
import android.content.Context;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import android.content.Intent;

public class JSBackgroundServicePlugin extends CordovaPlugin {

    private static final String TAG = "JSBackgroundPlugin";

    final static String PREFERENCES = "jsBgService";
    final static String PREF_ACTIVITY_ALIVE = "activity_alive";
    final static String PREF_ACTIVITY_FOREGROUND = "activity_foreground";
    final static String PREF_IS_REPEATING = "is_repeating";
    final static String PREF_LISTEN_NEW_PICTURE = "listen_new_pictures";
    final static String PREF_SERVICE_RUNNING = "service_running";


    private enum Command {
        setRepeating, cancelRepeating, isRepeating, listenNewPictures, startService, isRunning, startMainActivity
    }

    @Override
    public boolean execute(final String action, final JSONArray data, final CallbackContext callback) throws JSONException {

        LifecycleManager manager = new LifecycleManager(cordova.getActivity());

        boolean result = true;
        try {
            Command command = Command.valueOf(action);

            switch(Command.valueOf(action)) {
            case startService: {
                cordova.getActivity().startService(new Intent(cordova.getActivity(), WebViewService.class));
                callback.success();
            }; break;
            case setRepeating: {
                manager.startAlarmManager(data.optLong(0, -1));
                setPreference(PREF_IS_REPEATING, true);
                callback.success();
            }; break;

            case cancelRepeating: {
                manager.stopAlarmManager();
                setPreference(PREF_IS_REPEATING, false);
                callback.success();
            }; break;

            case isRepeating: {
                callback.success(manager.isRepeating() ? "true" : "false");
            }; break;

            case isRunning: {
                boolean running = cordova.getActivity()
                    .getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                    .getBoolean(PREF_SERVICE_RUNNING, false);
                callback.success(running ? "true" : "false");
            }; break;

            case startMainActivity: {
                //TODO : Z hard dependance on cozy-mobile !
                Intent intent = new Intent(cordova.getActivity(),
                    io.cozy.files_client.MainActivity.class);
                // But generic way would need CATEGORY_DEFAULT in manifest to work.
                // Intent intent = new Intent();
                // intent.setAction(Intent.ACTION_MAIN);
                // intent.addCategory(Intent.CATEGORY_LAUNCHER);
                // intent.setPackage(mContext.getPackageName());
                cordova.getActivity().startActivity(intent);
                callback.success();
            }; break;

            // TODO: put in new pictures plugin.
            case listenNewPictures: {
                setPreference(PREF_LISTEN_NEW_PICTURE,
                    data.optBoolean(0, false));
                callback.success();
            }; break;

            default: {
                result = false;
            }; break;

            }
        } catch (IllegalArgumentException e) {
            throw new JSONException(action +
                " isn't a valid command, use one of " +
                TextUtils.join( ", ", Command.values()));
        }
        return result;
    }

    ////
    // The 4 following method reflect CordovaApp Activity lifecycle in
    // preferences. Service uses it later to avoid conflict around shared
    // resources by it and CordovaApp Activity.
    ////
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        setPreference(PREF_ACTIVITY_ALIVE, true);
    }

    public void onDestroy() {
        setPreference(PREF_ACTIVITY_ALIVE, false);
    }

    /**
    * Called when the activity will start interacting with the user.
    *
    * @param multitasking Flag indicating if multitasking is turned on for app
    */
    public void onResume(boolean multitasking) {
        setPreference(PREF_ACTIVITY_FOREGROUND, true);
    }

    /**
    * Called when the system is about to start resuming a previous activity.
    *
    * @param multitasking Flag indicating if multitasking is turned on for app
    */
    public void onPause(boolean multitasking) {
        setPreference(PREF_ACTIVITY_FOREGROUND, false);
    }

    private void setPreference(String key, boolean value) {
        SharedPreferences preferences = cordova.getActivity()
            .getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}

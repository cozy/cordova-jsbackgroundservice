package io.cozy.jsbackgroundservice;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;

import android.text.TextUtils;


public class JSBackgroundServicePlugin extends CordovaPlugin {

    private static final String TAG = "JSBackgorundPlugin";

    private enum Command {
        setRepeating, cancelRepeating, isRepeating
    }

    @Override
    public boolean execute(final String action, final JSONArray data, final CallbackContext callback) throws JSONException {

        LifecycleManager manager = new LifecycleManager(cordova.getActivity());

        boolean result = true;
        try {
            Command command = Command.valueOf(action);

            switch(Command.valueOf(action)) {
            case setRepeating: {
                manager.startAlarmManager(data.optLong(0, -1));
                callback.success();
            }; break;

            case cancelRepeating: {
                manager.stopAlarmManager();
                callback.success();
            }; break;

            case isRepeating: {
                callback.success(manager.isRepeating() ? "true" : "false");
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
}

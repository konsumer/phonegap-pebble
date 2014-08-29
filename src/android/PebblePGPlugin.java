package com.jetboystudio.pebble;

import org.apache.cordova.*;
import org.json.*;
import java.util.*;
import android.content.*;
import android.util.*;
import android.graphics.*;

import com.getpebble.android.kit.*;
import com.getpebble.android.kit.util.*;

public class PebblePGPlugin extends CordovaPlugin {
    public static final String TAG = "PebblePGPlugin";
    private PebbleKit.PebbleDataLogReceiver mDataLogReceiver = null;
    private static CordovaWebView gWebView;

    /**
     * Gets the application context from cordova's main activity.
     * @return the application context
     */
    private Context getApplicationContext() {
        return this.cordova.getActivity().getApplicationContext();
    }

    /**
     * Fire a javascript document event name Pebble.method
     * @param method the name of the sub-event (ie: connect, disconnect, etc)
     * @param optional detail the detail-object for the event
     */
    public static void dispatchEvent(String method, JSONObject detail) {
        gWebView.sendJavascript("javascript:document.dispatchEvent(new CustomEvent('Pebble." + method + "', {'detail': " + detail.toString() + "}))");
    }
    public static void dispatchEvent(String method) {
        gWebView.sendJavascript("javascript:document.dispatchEvent(new CustomEvent('Pebble." + method + "'))");
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext cb) throws JSONException {
        gWebView = this.webView;

        Log.v(TAG, "execute: action=" + action + " " + args.toString());

        /**
         * Call success callback with message-support 0/1
         */
        if (action.equals("areAppMessagesSupported")){
            cb.success(PebbleKit.areAppMessagesSupported(getApplicationContext()) ? 1 : 0);
            return true;
        }

        /**
         * Call success callback with datalog-support 0/1
         */
        if (action.equals("isDataLoggingSupported")){
            cb.success(PebbleKit.isDataLoggingSupported(getApplicationContext()) ? 1 : 0);
            return true;
        }

        /**
         * Call success callback with connected-status 0/1
         */
        if (action.equals("isWatchConnected")){
            cb.success(PebbleKit.isWatchConnected(getApplicationContext()) ? 1 : 0);
            return true;
        }

        /**
         * Call success callback with version-info in JSON object
         */
        if (action.equals("getWatchFWVersion")){
            PebbleKit.FirmwareVersionInfo fw = PebbleKit.getWatchFWVersion(getApplicationContext());
            JSONObject json = new JSONObject();
            json.put("version", fw.getMajor() + "." + fw.getMinor() + "." + fw.getPoint());
            json.put("tag", fw.getTag());
            cb.success(json);
            return true;
        }

        /**
         * Start an app on Pebble, by UUID
         */
        if (action.equals("startAppOnPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.startAppOnPebble(getApplicationContext(), uuid );
            cb.success();
            return true;
        }

        /**
         * Stop an app on Pebble, by UUID
         */
        if (action.equals("closeAppOnPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.closeAppOnPebble(getApplicationContext(), uuid );
            cb.success();
            return true;
        }

        /**
         * Trigger a Pebble alert
         */
        if (action.equals("alert")){
            final Intent i = new Intent("com.getpebble.action.SEND_NOTIFICATION");
            final Map data = new HashMap();
            data.put("title", args.getString(1));
            data.put("body", args.getString(2));
            final JSONObject jsonData = new JSONObject(data);
            final String notificationData = new JSONArray().put(jsonData).toString();

            i.putExtra("messageType", "PEBBLE_ALERT");
            i.putExtra("sender", args.getString(0));
            i.putExtra("notificationData", notificationData);

            getApplicationContext().sendBroadcast(i);
            cb.success();
            return true;
        }

        if (action.equals("music")){
            final Intent i = new Intent("com.getpebble.action.NOW_PLAYING");
            i.putExtra("artist", args.getString(0));
            i.putExtra("album", args.getString(1));
            i.putExtra("track", args.getString(2));

            getApplicationContext().sendBroadcast(i);
            cb.success();
            return true;
        }

        /**
         * Register a callback when watch is connected
         */
        if (action.equals("registerPebbleConnectedReceiver")){
            PebbleKit.registerPebbleConnectedReceiver(getApplicationContext(), new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dispatchEvent("connected");
                }
            });
            cb.success();
            return true;
        }

        /**
         * Register a callback when watch is disconnected
         */
        if (action.equals("registerPebbleDisconnectedReceiver")){
            PebbleKit.registerPebbleDisconnectedReceiver(getApplicationContext(), new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dispatchEvent("disconnected");
                }
            });
            cb.success();
            return true;
        }

        /**
         * Unregister a callback when watch is connected
         */
        if (action.equals("unregisterPebbleConnectedReceiver")){
            PebbleKit.registerPebbleConnectedReceiver(getApplicationContext(), null);
            cb.success();
            return true;
        }

        /**
         * Unregister a callback when watch is disconnected
         */
        if (action.equals("unregisterPebbleDisconnectedReceiver")){
            PebbleKit.registerPebbleDisconnectedReceiver(getApplicationContext(), null);
            cb.success();
            return true;
        }

        

        // action not found
        return false;
    }
}
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
        Log.v(TAG, "dispatch: method=" + method + " " + detail.toString());
        gWebView.sendJavascript("document.dispatchEvent(new CustomEvent('Pebble." + method + "', {'detail': " + detail.toString() + "}))");
    }
    public static void dispatchEvent(String method, JSONArray detail) {
        Log.v(TAG, "dispatch: method=" + method + " " + detail.toString());
        gWebView.sendJavascript("document.dispatchEvent(new CustomEvent('Pebble." + method + "', {'detail': " + detail.toString() + "}))");
    }
    public static void dispatchEvent(String method, int detail) {
        String i = Integer.toString(detail);
        Log.v(TAG, "dispatch: method=" + method + " " + i);
        gWebView.sendJavascript("document.dispatchEvent(new CustomEvent('Pebble." + method + "', {'detail': " + i + "}))");
    }
    public static void dispatchEvent(String method, String detail) {
        Log.v(TAG, "dispatch: method=" + method + " " + detail);
        gWebView.sendJavascript("document.dispatchEvent(new CustomEvent('Pebble." + method + "', {'detail': '" + detail + "'}))");
    }
    public static void dispatchEvent(String method) {
        Log.v(TAG, "dispatch: method=" + method);
        gWebView.sendJavascript("document.dispatchEvent(new CustomEvent('Pebble." + method + "'))");
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

        /**
         * Trigger a Pebble music-change
         */
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
         * Send data to pebble
         */
        if (action.equals("sendDataToPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleDictionary data = PebbleDictionary.fromJson(args.getString(1));
            PebbleKit.sendDataToPebble(this.cordova.getActivity().getApplicationContext(), uuid, data);
            cb.success();
            return true;
        }

        /**
         * Send data to pebble with transactionId
         */
        if (action.equals("sendDataToPebbleWithTransactionId")){
            UUID uuid = UUID.fromString(args.getString(0));
            int transactionId = args.getInt(1);
            PebbleDictionary data = PebbleDictionary.fromJson(args.getString(1));
            PebbleKit.sendDataToPebbleWithTransactionId(this.cordova.getActivity().getApplicationContext(), uuid, data, transactionId);
            cb.success();
            return true;
        }

        /**
         * Send ACK to pebble with transactionId
         */
        if (action.equals("sendAckToPebble")){
            int transactionId = args.getInt(0);
            PebbleKit.sendAckToPebble(this.cordova.getActivity().getApplicationContext(), transactionId);
            cb.success(transactionId);
            return true;
        }

        /**
         * Send NACK to pebble with transactionId
         */
        if (action.equals("sendNackToPebble")){
            int transactionId = args.getInt(0);
            PebbleKit.sendNackToPebble(this.cordova.getActivity().getApplicationContext(), transactionId);
            cb.success(transactionId);
            return true;
        }

        /**
         * Register a callback when watch sends data
         */
        if (action.equals("registerReceivedDataHandler")){
            final UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedDataHandler(getApplicationContext(), new PebbleKit.PebbleDataReceiver(uuid) {
                @Override
                public void receiveData(final Context context, final int transactionId, final PebbleDictionary data) {
                    try{
                        JSONObject json = new JSONObject();
                        json.put("transaction", transactionId);
                        json.put("data", data.toJsonString());
                        json.put("uuid", uuid.toString());
                        dispatchEvent("data", json);
                    }catch(JSONException e){
                        cb.error(e.getCause().getMessage());
                    }
                }
            });
            cb.success();
            return true;
        }

        /**
         * Unregister a callback when watch sends data
         */
        if (action.equals("unregisterReceivedDataHandler")){
            PebbleKit.registerReceivedDataHandler(getApplicationContext(), null);
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
                    dispatchEvent("connect");
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
         * Register a callback when watch is disconnected
         */
        if (action.equals("registerPebbleDisconnectedReceiver")){
            PebbleKit.registerPebbleDisconnectedReceiver(getApplicationContext(), new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dispatchEvent("disconnect");
                }
            });
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

        /**
         * Register watch ACK handler
         */
        if (action.equals("registerReceivedAckHandler")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedAckHandler(getApplicationContext(), new PebbleKit.PebbleAckReceiver(uuid) {
                @Override
                public void receiveAck(Context context, int transactionId) {
                    dispatchEvent("ack", transactionId);
                }
            });
            cb.success();
            return true;
        }

        /**
         * Unregister watch ACK handler
         */
        if (action.equals("unregisterReceivedAckHandler")){
            PebbleKit.registerReceivedAckHandler(getApplicationContext(), null);
            cb.success();
            return true;
        }

        /**
         * Register watch NACK handler
         */
        if (action.equals("registerReceivedNackHandler")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedNackHandler(getApplicationContext(), new PebbleKit.PebbleNackReceiver(uuid) {
                @Override
                public void receiveNack(Context context, int transactionId) {
                    dispatchEvent("nack", transactionId);
                }
            });
            cb.success();
            return true;
        }

        /**
         * Unregister watch NACK handler
         */
        if (action.equals("unregisterReceivedNackHandler")){
            PebbleKit.registerReceivedNackHandler(getApplicationContext(), null);
            cb.success();
            return true;
        }

        // TODO: registerDataLogReceiver + requestDataLogsForApp
        if (action.equals("registerDatalog")){
            cb.error("not implemented");
            return true;
        }

        // TODO: registerDataLogReceiver(null)
        if (action.equals("unregisterDatalog")){
            cb.error("not implemented");
            return true;
        }

        // TODO: check if this works
        if (action.equals("customizeWatchApp")){
            String stype = args.getString(0);
            String name = args.getString(1);
            Constants.PebbleAppType type = Constants.PebbleAppType.OTHER;

            if (stype.equals("sports")){
                type = Constants.PebbleAppType.SPORTS;
            }

            if (stype.equals("golf")){
                type = Constants.PebbleAppType.GOLF;
            }
            
            byte[] decodedByte = Base64.decode(args.getString(2), 0);
            Bitmap icon = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);

            PebbleKit.customizeWatchApp(this.cordova.getActivity().getApplicationContext(), type, name, icon);
            cb.success();
            return true;
        }

        

        // action not found
        return false;
    }
}
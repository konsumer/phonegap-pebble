package com.jetboystudio.pebble;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.UUID;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.Constants;


public class PebblePGPlugin extends CordovaPlugin {
    private PebbleKit.PebbleDataLogReceiver mDataLogReceiver = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext cb) throws JSONException {
        switch (action) {
            case "areAppMessagesSupported":
                cb.success(PebbleKit.areAppMessagesSupported(
                    getApplicationContext()
                ));
                break;

            case "closeAppOnPebble":
                UUID uuid = UUID.fromString(args.getString(0));
                cb.success(PebbleKit.closeAppOnPebble(
                    getApplicationContext(),
                    uuid
                ));
                break;

            case "customizeWatchApp":
                cb.error("Not Implemented.");
                /*
                // TODO: how should I deal with icon?
                String jsType = args.getString(0);
                String name = args.getString(1);
                int appType = PebbleKit.Constants.PebbleAppType.OTHER;
                UUID uuid = Constants.APP_UUID;

                if (jsType.equals("golf")){
                    appType = PebbleKit.Constants.PebbleAppType.GOLF;
                    uuid = Constants.GOLF_UUID;
                } else if (jsType.equals("sports")) {
                    appType = PebbleKit.Constants.PebbleAppType.SPORTS;
                    uuid = Constants.SPORTS_UUID;
                }

                PebbleKit.customizeWatchApp(
                    getApplicationContext(),
                    appType,
                    name,
                    icon
                );

                cb.success(uuid);
                */
                break;

            case "getWatchFWVersion":
                PebbleKit.FirmwareVersionInfo fw = PebbleKit.getWatchFWVersion(getApplicationContext());
                JSONObject json = new JSONObject();
                json.put("version", fw.getMajor() + "." + fw.getMinor() + "." + fw.getPoint());
                json.put("tag", fw.getTag());
                cb.success(json);
                break;

            case "isDataLoggingSupported":
                cb.success(PebbleKit.isDataLoggingSupported(getApplicationContext()));
                break;

            case "isWatchConnected":
                cb.success(PebbleKit.isWatchConnected(getApplicationContext()));
                break;

            case "registerDataLogReceiver":
                UUID uuid = UUID.fromString(args.getString(0));
                mDataLogReceiver = new PebbleKit.PebbleDataLogReceiver(uuid) {
                    @Override
                    public void receiveData(Context context, UUID logUuid, UnsignedInteger timestamp, UnsignedInteger tag, UnsignedInteger secondsSinceEpoch) {
                        JSONObject json = new JSONObject();
                        json.put("logUuid", logUuid);
                        json.put("timestamp", timestamp);
                        json.put("tag", tag);
                        json.put("secondsSinceEpoch", secondsSinceEpoch);
                        cb.success(json);
                    }
                };
                PebbleKit.registerDataLogReceiver(
                    getApplicationContext(),
                    mDataLogReceiver
                );
                break;

            case "unregisterDataLogReceiver":
                if (mDataLogReceiver != null) {
                    unregisterReceiver(mDataLogReceiver);
                    mDataLogReceiver = null;
                }
                break;

            case "registerPebbleConnectedReceiver":
                break;

            case "registerPebbleDisconnectedReceiver":
                break;

            case "registerReceivedAckHandler":
                break;

            case "registerReceivedDataHandler":
                break;

            case "registerReceivedNackHandler":
                break;

            case "requestDataLogsForApp":
                break;

            case "sendAckToPebble":
                break;

            case "sendDataToPebble":
                break;

            case "sendDataToPebbleWithTransactionId":
                break;

            case "sendNackToPebble":
                break;

            case "startAppOnPebble":
                String u = args.getString(0);
                UUID uuid = UUID.fromString(u);
                cb.success(PebbleKit.startAppOnPebble(
                    getApplicationContext(),
                    uuid
                ));
                break;

            case "addEventListener":
                cb.error("Not Implemented.");
                break;

            case "sendAppMessage":
                cb.error("Not Implemented.");
                break;

            case "showSimpleNotificationOnPebble":
                cb.error("Not Implemented.");
                break;

            case "getAccountToken":
                cb.error("Not Implemented.");
                break;

            case "openURL":
                cb.error("Not Implemented.");
                break;
            
            default:
                return false;
                break;
        }

        return true;
}
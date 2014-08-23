package com.jetboystudio.pebble;

import org.apache.cordova.*;
import org.json.*;
import java.util.UUID;

import com.getpebble.android.kit.*;

public class PebblePGPlugin extends CordovaPlugin {
    private PebbleKit.PebbleDataLogReceiver mDataLogReceiver = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext cb) throws JSONException {
        if (action.equals("areAppMessagesSupported")){
            cb.success(PebbleKit.areAppMessagesSupported( this.cordova.getActivity().getApplicationContext() ) ? 1 : 0);
            return true;
        }

        if (action.equals("isDataLoggingSupported")){
            cb.success(PebbleKit.isDataLoggingSupported( this.cordova.getActivity().getApplicationContext() ) ? 1 : 0);
            return true;
        }

        if (action.equals("isWatchConnected")){
            cb.success(PebbleKit.isWatchConnected( this.cordova.getActivity().getApplicationContext() ) ? 1 : 0);
            return true;
        }

        if (action.equals("getWatchFWVersion")){
            PebbleKit.FirmwareVersionInfo fw = PebbleKit.getWatchFWVersion(this.cordova.getActivity().getApplicationContext());
            JSONObject json = new JSONObject();
            json.put("version", fw.getMajor() + "." + fw.getMinor() + "." + fw.getPoint());
            json.put("tag", fw.getTag());
            cb.success(json);
            return true;
        }

        if (action.equals("startAppOnPebble")){
            String u = args.getString(0);
            UUID uuid = UUID.fromString(u);
            cb.success(PebbleKit.startAppOnPebble(
                this.cordova.getActivity().getApplicationContext(),
                uuid
            ));
            return true;
        }

        if (action.equals("closeAppOnPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            cb.success(PebbleKit.closeAppOnPebble(
                this.cordova.getActivity().getApplicationContext(),
                uuid
            ));
            return true;
        }

        if (action.equals("registerDataLogReceiver")){
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
                this.cordova.getActivity().getApplicationContext(),
                mDataLogReceiver
            );
            return true;
        }

        if (action.equals("unregisterDataLogReceiver")){
            if (mDataLogReceiver != null) {
                PebbleKit.unregisterReceiver(mDataLogReceiver);
                mDataLogReceiver = null;
            }
            return true;
        }

        if (action.equals("customizeWatchApp")){
            cb.error("Not Implemented.");
            /*
            // TODO: how should I deal with icon?
            String jsType = args.getString(0);
            String name = args.getString(1);
            int appType = Constants.PebbleAppType.OTHER;
            UUID uuid = Constants.APP_UUID;

            if (jsType.equals("golf")){
                appType = Constants.PebbleAppType.GOLF;
                uuid = Constants.GOLF_UUID;
            } else if (jsType.equals("sports")) {
                appType = Constants.PebbleAppType.SPORTS;
                uuid = Constants.SPORTS_UUID;
            }

            PebbleKit.customizeWatchApp(
                this.cordova.getActivity().getApplicationContext(),
                appType,
                name,
                icon
            );

            cb.success(uuid);
            */
            return true;
        }

        if (action.equals("registerPebbleConnectedReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("registerPebbleDisconnectedReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("registerReceivedAckHandler")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("registerReceivedDataHandler")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("registerReceivedNackHandler")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("requestDataLogsForApp")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("sendAckToPebble")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("sendDataToPebble")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("sendDataToPebbleWithTransactionId")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("sendNackToPebble")){
            cb.error("Not Implemented.");
            return true;
        }

        return false;
    }
}
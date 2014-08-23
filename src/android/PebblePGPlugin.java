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
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.startAppOnPebble( this.cordova.getActivity().getApplicationContext(), uuid );
            cb.success(uuid.toString());
            return true;
        }

        if (action.equals("closeAppOnPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.closeAppOnPebble( this.cordova.getActivity().getApplicationContext(), uuid );
            cb.success(uuid.toString());
            return true;
        }

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

            sendBroadcast(i);
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

        if (action.equals("registerDataLogReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("unregisterDataLogReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("customizeWatchApp")){
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
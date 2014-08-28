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
    private PebbleKit.PebbleDataLogReceiver mDataLogReceiver = null;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext cb) throws JSONException {
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

            this.cordova.getActivity().getApplicationContext().sendBroadcast(i);
            return true;
        }

        if (action.equals("music")){
            final Intent i = new Intent("com.getpebble.action.NOW_PLAYING");
            i.putExtra("artist", args.getString(0));
            i.putExtra("album", args.getString(1));
            i.putExtra("track", args.getString(2));

            this.cordova.getActivity().getApplicationContext().sendBroadcast(i);
            return true;
        }

        if (action.equals("registerPebbleConnectedReceiver")){
            PebbleKit.registerPebbleConnectedReceiver(this.cordova.getActivity().getApplicationContext(), new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    cb.success(1);
                }
            });
            return true;
        }

        if (action.equals("registerPebbleDisconnectedReceiver")){
            PebbleKit.registerPebbleDisconnectedReceiver(this.cordova.getActivity().getApplicationContext(), new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    cb.success(0);
                }
            });
            return true;
        }

        if (action.equals("sendDataToPebble")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleDictionary data = PebbleDictionary.fromJson(args.getString(1));
            PebbleKit.sendDataToPebble(this.cordova.getActivity().getApplicationContext(), uuid, data);
            cb.success(uuid.toString());
            return true;
        }

        // TODO: untested!
        if (action.equals("sendDataToPebbleWithTransactionId")){
            UUID uuid = UUID.fromString(args.getString(0));
            int transactionId = args.getInt(1);
            PebbleDictionary data = PebbleDictionary.fromJson(args.getString(1));
            PebbleKit.sendDataToPebbleWithTransactionId(this.cordova.getActivity().getApplicationContext(), uuid, data, transactionId);
            cb.success(uuid.toString());
            return true;
        }

        // TODO: untested!
        if (action.equals("customizeWatchApp")){
            int type = args.getInt(0);
            String name = args.getString(1);
            
            byte[] decodedByte = Base64.decode(args.getString(2), 0);
            Bitmap icon = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);

            PebbleKit.customizeWatchApp(this.cordova.getActivity().getApplicationContext(), type, name, icon);
            cb.success();
            return true;
        }

        // TODO: untested!
        if (action.equals("registerReceivedDataHandler")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedDataHandler(this.cordova.getActivity().getApplicationContext(), new PebbleKit.PebbleDataReceiver(uuid) {
                @Override
                public void receiveData(final Context context, final int transactionId, final PebbleDictionary data) {
                    try{
                        JSONObject json = new JSONObject();
                        json.put("transaction", transactionId);
                        json.put("data", data.toJsonString());
                        cb.success(json);
                    } catch(Exception e){
                        cb.error(e.toString());
                    }
                }
            });
            return true;
        }

        // TODO: untested!
        if (action.equals("registerReceivedAckHandler")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedAckHandler(this.cordova.getActivity().getApplicationContext(), new PebbleKit.PebbleAckReceiver(uuid) {
                @Override
                public void receiveAck(Context context, int transactionId) {
                    try{
                        cb.success(transactionId);
                    } catch(Exception e){
                        cb.error(e.toString());
                    }
                }
            });
            return true;
        }

        // TODO: untested!
        if (action.equals("registerReceivedNackHandler")){
            UUID uuid = UUID.fromString(args.getString(0));
            PebbleKit.registerReceivedNackHandler(this.cordova.getActivity().getApplicationContext(), new PebbleKit.PebbleNackReceiver(uuid) {
                @Override
                public void receiveNack(Context context, int transactionId) {
                    try{
                        cb.success(transactionId);
                    } catch(Exception e){
                        cb.error(e.toString());
                    }
                }
            });
            return true;
        }

        // TODO: untested!
        if (action.equals("sendAckToPebble")){
            int transactionId = args.getInt(0);
            PebbleKit.sendAckToPebble(this.cordova.getActivity().getApplicationContext(), transactionId);
            cb.success(transactionId);
            return true;
        }

        // TODO: untested!
        if (action.equals("sendNackToPebble")){
            int transactionId = args.getInt(0);
            PebbleKit.sendNackToPebble(this.cordova.getActivity().getApplicationContext(), transactionId);
            cb.success(transactionId);
            return true;
        }

        /////

        if (action.equals("registerDataLogReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("unregisterDataLogReceiver")){
            cb.error("Not Implemented.");
            return true;
        }

        if (action.equals("requestDataLogsForApp")){
            cb.error("Not Implemented.");
            return true;
        }

        

        return false;
    }
}
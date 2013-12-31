package com.paypal.android.sdk.phonegap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;

public class PebblePGPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        boolean retValue = true;
        if (action.equals("areAppMessagesSupported")) {
            callbackContext.success(this.areAppMessagesSupported());
        } else if (action.equals("closeAppOnPebble")) {
            String watchappUuid = args.getString(0);
            this.closeAppOnPebble(watchappUuid);
        } else if (action.equals("customizeWatchApp")) {
            String appType = args.getString(0);
            String name = args.getString(1);
            this.customizeWatchApp(appType, name);
        } else if (action.equals("getWatchFWVersion")) {
            callbackContext.success(this.getWatchFWVersion());
        } else if (action.equals("isDataLoggingSupported")) {
            callbackContext.success(this.isDataLoggingSupported());
        } else if (action.equals("isWatchConnected")) {
            this.isWatchConnected();
        } else if (action.equals("registerDataLogReceiver")) {
            this.registerDataLogReceiver(args);
        } else if (action.equals("registerPebbleConnectedReceiver")) {
            this.registerPebbleConnectedReceiver(args);
        } else if (action.equals("registerPebbleDisconnectedReceiver")) {
            this.registerPebbleDisconnectedReceiver(args);
        } else if (action.equals("registerReceivedAckHandler")) {
            this.registerReceivedAckHandler(args);
        } else if (action.equals("registerReceivedDataHandler")) {
            this.registerReceivedDataHandler(args);
        } else if (action.equals("registerReceivedNackHandler")) {
            this.registerReceivedNackHandler(args);
        } else if (action.equals("requestDataLogsForApp")) {
            this.requestDataLogsForApp(args);
        } else if (action.equals("sendAckToPebble")) {
            this.sendAckToPebble(args);
        } else if (action.equals("sendDataToPebble")) {
            this.sendDataToPebble(args);
        } else if (action.equals("sendDataToPebbleWithTransactionId")) {
            this.sendDataToPebbleWithTransactionId(args);
        } else if (action.equals("sendNackToPebble")) {
            this.sendNackToPebble(args);
        } else if (action.equals("startAppOnPebble")) {
            this.startAppOnPebble(args);
        } else if (action.equals("addEventListener")) {
            this.addEventListener(args);
        } else if (action.equals("sendAppMessage")) {
            this.sendAppMessage(args);
        } else if (action.equals("showSimpleNotificationOnPebble")) {
            this.showSimpleNotificationOnPebble(args);
        } else if (action.equals("getAccountToken")) {
            this.getAccountToken();
        } else if (action.equals("openURL")) {
            this.openURL(args);
        } else{
            retValue = false;
        }
        
        return retValue;
    }

    private Boolean areAppMessagesSupported() {
        return true;
    }

    private void closeAppOnPebble(String watchappUuid) throws JSONException {
        
    }

    private void customizeWatchApp(String appType, String name) throws JSONException {
        // TODO: Image arg3?
    }

    private void getWatchFWVersion() throws JSONException {
        // TODO: Return object?
    }

    private Boolean isDataLoggingSupported() throws JSONException {
        // TODO: Return boolean?
    }

    private Boolean isWatchConnected() throws JSONException {
    }

    private void registerDataLogReceiver(JSONArray args) throws JSONException {
    }

    private void registerPebbleConnectedReceiver(JSONArray args) throws JSONException {
    }

    private void registerPebbleDisconnectedReceiver(JSONArray args) throws JSONException {
    }

    private void registerReceivedAckHandler(JSONArray args) throws JSONException {
    }

    private void registerReceivedDataHandler(JSONArray args) throws JSONException {
    }

    private void registerReceivedNackHandler(JSONArray args) throws JSONException {
    }

    private void requestDataLogsForApp(JSONArray args) throws JSONException {
    }

    private void sendAckToPebble(JSONArray args) throws JSONException {
    }

    private void sendDataToPebble(JSONArray args) throws JSONException {
    }

    private void sendDataToPebbleWithTransactionId(JSONArray args) throws JSONException {
    }

    private void sendNackToPebble(JSONArray args) throws JSONException {
    }

    private void startAppOnPebble(JSONArray args) throws JSONException {
    }

    private void addEventListener(JSONArray args) throws JSONException {
    }

    private void sendAppMessage(JSONArray args) throws JSONException {
    }

    private void showSimpleNotificationOnPebble(JSONArray args) throws JSONException {
    }

    private void getAccountToken() throws JSONException {
        // TODO: Return string?
    }

    private void openURL(JSONArray args) throws JSONException {
    }

}
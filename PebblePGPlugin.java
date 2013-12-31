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

        } else if (action.equals("closeAppOnPebble")) {

        } else if (action.equals("customizeWatchApp")) {

        } else if (action.equals("getWatchFWVersion")) {

        } else if (action.equals("isDataLoggingSupported")) {

        } else if (action.equals("isWatchConnected")) {

        } else if (action.equals("registerDataLogReceiver")) {

        } else if (action.equals("registerPebbleConnectedReceiver")) {

        } else if (action.equals("registerPebbleDisconnectedReceiver")) {

        } else if (action.equals("registerReceivedAckHandler")) {

        } else if (action.equals("registerReceivedDataHandler")) {

        } else if (action.equals("registerReceivedNackHandler")) {

        } else if (action.equals("requestDataLogsForApp")) {

        } else if (action.equals("sendAckToPebble")) {

        } else if (action.equals("sendDataToPebble")) {

        } else if (action.equals("sendDataToPebbleWithTransactionId")) {

        } else if (action.equals("sendNackToPebble")) {

        } else if (action.equals("startAppOnPebble")) {

        } else if (action.equals("addEventListener")) {

        } else if (action.equals("sendAppMessage")) {

        } else if (action.equals("showSimpleNotificationOnPebble")) {

        } else if (action.equals("getAccountToken")) {

        } else if (action.equals("openURL")) {

        }else{
            retValue = false;
        }
        
        return retValue;
    }
}
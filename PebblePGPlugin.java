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
        /*
        if (action.equals("version")) {
        this.version();
        } else if (action.equals("setEnvironment")) {
        this.setEnvironment(args);
        } else if (action.equals("environment")) {
        this.environment();
        } else if (action.equals("prepareForPayment")) {
        this.prepareForPayment(args);
        } else if (action.equals("presentPaymentUI")) {
        this.presentPaymentUI(args);
        } else {
        retValue = false;
        }
        */
        retValue = false;

        return retValue;
    }
}
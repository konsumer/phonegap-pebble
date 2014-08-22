package com.jetboystudio.pebble;

import org.apache.cordova.*;
import org.json.*;
import java.util.UUID;

import com.getpebble.android.kit.*;

public class PebblePGPlugin extends CordovaPlugin {
    private PebbleKit.PebbleDataLogReceiver mDataLogReceiver = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext cb) throws JSONException {
        if (action.equals("getWatchFWVersion")){
            PebbleKit.FirmwareVersionInfo fw = PebbleKit.getWatchFWVersion( this.cordova.getActivity().getApplicationContext() );
            JSONObject json = new JSONObject();
            json.put("version", fw.getMajor() + "." + fw.getMinor() + "." + fw.getPoint());
            json.put("tag", fw.getTag());
            cb.success(json);
            return true;
        }
        
        if (action.equals("isWatchConnected")){
            boolean bConnected = PebbleKit.isWatchConnected( this.cordova.getActivity().getApplicationContext() );
            int iConnected = 0;
            if (bConnected){
                iConnected = 1;
            }
            cb.success(iConnected);
            return true;
        }

        return false;
    }
}

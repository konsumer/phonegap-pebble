var Pebble = {};

/**
 * Check if Pebble is currently connected
 * @param  {Function} cb function(error, connected)
 */
Pebble.isWatchConnected = function(cb){
    cordova.exec(function(result){ cb(null, result==1); }, cb, 'Pebble', 'isWatchConnected', []);
};

/**
 * Get information about firmware installed on Pebble
 * @param  {Function} cb function(error, info)
 */
Pebble.getWatchFWVersion = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'getWatchFWVersion', []);
};

/**
 * Find out if app-messages are supported
 * @param  {Function} cb function(error, supported)
 */
Pebble.areAppMessagesSupported = function(cb){
    cordova.exec(function(result){ cb(null, result==1); }, cb, 'Pebble', 'areAppMessagesSupported', []);
};

/**
 * Find out if data-logging is supportedon Pebble
 * @param  {Function} cb function(error, supported)
 */
Pebble.isDataLoggingSupported = function(cb){
    cordova.exec(function(result){ cb(null, result==1); }, cb, 'Pebble', 'isDataLoggingSupported', []);
};

/**
 * Send an alert to Pebble
 * @param  {String}   sender Who is sending this?
 * @param  {String}   title  title of message
 * @param  {String}   body   body of message
 * @param  {Function} cb function(error, supported)
 */
Pebble.alert = function(sender, title, body, cb){
    cb = cb || function(){};
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'alert', [sender, title, body]);
}

/**
 * Update now-playing on Pebble
 * @param  {String}   artist [description]
 * @param  {String}   album  [description]
 * @param  {String}   track  [description]
 * @param  {Function} cb     cb function(error, supported)
 */
Pebble.music = function(artist, album, track, cb){
    cb = cb || function(){};
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'music', [artist, album, track]);
}

/**
 * Start an app on the Pebble
 * @param  {String}   uuid UUID of app to be started
 * @param  {Function} cb   function(error, uuid)
 */
Pebble.startAppOnPebble = function(uuid, cb){
    cb = cb || function(){};
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'startAppOnPebble', [uuid]);
};

/**
 * Close an app on the Pebble
 * @param  {String}   uuid UUID of app to be closed
 * @param  {Function} cb   function(error, uuid)
 */
Pebble.closeAppOnPebble = function(uuid, cb){
    cb = cb || function(){};
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'closeAppOnPebble', [uuid]);
};

/**
 * Register callback for when Pebble is connected
 * @param  {Function} cb function(error, status)
 */
Pebble.registerPebbleConnectedReceiver = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerPebbleConnectedReceiver', []);
};

/**
 * Register callback for when Pebble is disconnected
 * @param  {Function} cb function(error, status)
 */
Pebble.registerPebbleDisconnectedReceiver = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerPebbleDisconnectedReceiver', []);
};

// these need testing

Pebble.sendDataToPebble = function(uuid, data, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendDataToPebble', [uuid, JSON.stringify(data)]);
};

Pebble.registerReceivedDataHandler = function(uuid, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedDataHandler', [uuid]);
};

Pebble.registerReceivedAckHandler = function(uuid, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedAckHandler', [uuid]);
};

Pebble.registerReceivedNackHandler = function(uuid, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedNackHandler', [uuid]);
};

Pebble.sendAckToPebble = function(transactionId, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendAckToPebble', [transactionId]);
};

Pebble.sendNackToPebble = function(transactionId, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendNackToPebble', [transactionId]);
};

///////  None of these are implemented:

Pebble.customizeWatchApp = function(type, name, icon, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'customizeWatchApp', [type, name, icon]);
};

Pebble.registerDataLogReceiver = function(uuid, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerDataLogReceiver', [uuid]);
};

Pebble.unregisterDataLogReceiver = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'unregisterDataLogReceiver', []);
};

Pebble.requestDataLogsForApp = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'requestDataLogsForApp', []);
};

Pebble.sendDataToPebbleWithTransactionId = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendDataToPebbleWithTransactionId', []);
};


Pebble.INTENT_PEBBLE_CONNECTED = "com.getpebble.action.PEBBLE_CONNECTED";
Pebble.INTENT_PEBBLE_DISCONNECTED = "com.getpebble.action.PEBBLE_DISCONNECTED";
Pebble.INTENT_APP_ACK = "com.getpebble.action.app.ACK";
Pebble.INTENT_APP_NACK = "com.getpebble.action.app.NACK";
Pebble.INTENT_APP_RECEIVE = "com.getpebble.action.app.RECEIVE";
Pebble.INTENT_APP_RECEIVE_ACK = "com.getpebble.action.app.RECEIVE_ACK";
Pebble.INTENT_APP_RECEIVE_NACK = "com.getpebble.action.app.RECEIVE_NACK";
Pebble.INTENT_APP_SEND = "com.getpebble.action.app.SEND";
Pebble.INTENT_APP_START = "com.getpebble.action.app.START";
Pebble.INTENT_APP_STOP = "com.getpebble.action.app.STOP";
Pebble.INTENT_APP_CUSTOMIZE = "com.getpebble.action.app.CONFIGURE";
Pebble.INTENT_DL_RECEIVE_DATA = "com.getpebble.action.dl.RECEIVE_DATA";
Pebble.INTENT_DL_ACK_DATA = "com.getpebble.action.dl.ACK_DATA";
Pebble.INTENT_DL_REQUEST_DATA = "com.getpebble.action.dl.REQUEST_DATA";
Pebble.INTENT_DL_FINISH_SESSION = "com.getpebble.action.dl.FINISH_SESSION";
Pebble.SPORTS_UUID = "4dab81a6-d2fc-458a-992c-7a1f3b96a970";
Pebble.GOLF_UUID = "cf1e816a-9db0-4511-bbb8-f60c48ca8fac";
Pebble.TRANSACTION_ID = "transaction_id";
Pebble.APP_UUID = "uuid";
Pebble.MSG_DATA = "msg_data";
Pebble.CUST_APP_TYPE = "app_type";
Pebble.CUST_NAME = "name";
Pebble.CUST_ICON = "icon";
Pebble.DATA_LOG_TIMESTAMP = "data_log_timestamp";
Pebble.DATA_LOG_UUID = "data_log_uuid";
Pebble.DATA_LOG_TAG = "data_log_tag";
Pebble.PBL_DATA_ID = "pbl_data_id";
Pebble.PBL_DATA_TYPE = "pbl_data_type";
Pebble.PBL_DATA_OBJECT = "pbl_data_object";
Pebble.SPORTS_TIME_KEY = 0;
Pebble.SPORTS_DISTANCE_KEY = 1;
Pebble.SPORTS_DATA_KEY = 2;
Pebble.SPORTS_UNITS_KEY = 3;
Pebble.SPORTS_STATE_KEY = 4;
Pebble.SPORTS_LABEL_KEY = 5;
Pebble.SPORTS_UNITS_IMPERIAL = 0;
Pebble.SPORTS_UNITS_METRIC = 1;
Pebble.SPORTS_DATA_SPEED = 0;
Pebble.SPORTS_DATA_PACE = 1;
Pebble.SPORTS_STATE_INIT = 0;
Pebble.SPORTS_STATE_RUNNING = 1;
Pebble.SPORTS_STATE_PAUSED = 2;
Pebble.SPORTS_STATE_END = 3;
Pebble.GOLF_FRONT_KEY = 0;
Pebble.GOLF_MID_KEY = 1;
Pebble.GOLF_BACK_KEY = 2;
Pebble.GOLF_HOLE_KEY = 3;
Pebble.GOLF_PAR_KEY = 4;
Pebble.GOLF_CMD_KEY = 5;
Pebble.GOLF_CMD_PREV = 1;
Pebble.GOLF_CMD_NEXT = 2;
Pebble.KIT_STATE_COLUMN_CONNECTED = 0;
Pebble.KIT_STATE_COLUMN_APPMSG_SUPPORT = 1;
Pebble.KIT_STATE_COLUMN_DATALOGGING_SUPPORT = 2;
Pebble.KIT_STATE_COLUMN_VERSION_MAJOR = 3;
Pebble.KIT_STATE_COLUMN_VERSION_MINOR = 4;
Pebble.KIT_STATE_COLUMN_VERSION_POINT = 5;
Pebble.KIT_STATE_COLUMN_VERSION_TAG = 6;

module.exports = Pebble;
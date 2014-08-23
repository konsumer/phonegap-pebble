var cordova = require('cordova');

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
 * Start an app on the Pebble
 * @param  {String}   uuid UUID of app to be started
 * @param  {Function} cb   function(error, uuid)
 */
Pebble.startAppOnPebble = function(uuid, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'startAppOnPebble', [uuid]);
};

/**
 * Close an app on the Pebble
 * @param  {String}   uuid UUID of app to be closed
 * @param  {Function} cb   function(error, uuid)
 */
Pebble.closeAppOnPebble = function(uuid, cb){
	cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'closeAppOnPebble', [uuid]);
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
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'alert', [sender, title, body]);
}

/**
 * Update nonw-playing on Pebble
 * @param  {String}   artist [description]
 * @param  {String}   album  [description]
 * @param  {String}   track  [description]
 * @param  {Function} cb     cb function(error, supported)
 */
Pebble.music = function(artist, album, track, cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'music', [artist, album, track]);
}

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

Pebble.registerPebbleConnectedReceiver = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerPebbleConnectedReceiver', []);
};

Pebble.registerPebbleDisconnectedReceiver = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerPebbleDisconnectedReceiver', []);
};

Pebble.registerReceivedAckHandler = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedAckHandler', []);
};

Pebble.registerReceivedDataHandler = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedDataHandler', []);
};

Pebble.registerReceivedNackHandler = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'registerReceivedNackHandler', []);
};

Pebble.requestDataLogsForApp = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'requestDataLogsForApp', []);
};

Pebble.sendAckToPebble = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendAckToPebble', []);
};

Pebble.sendDataToPebble = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendDataToPebble', []);
};

Pebble.sendDataToPebbleWithTransactionId = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendDataToPebbleWithTransactionId', []);
};

Pebble.sendNackToPebble = function(cb){
    cordova.exec(function(result){ cb(null, result); }, cb, 'Pebble', 'sendNackToPebble', []);
};

module.exports = Pebble;
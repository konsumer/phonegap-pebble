function Pebble(){}

/**
 * Query the Pebble application to see if the connected watch is running a firmware version that supports PebbleKit messages.
 * @return {Boolean}
 */
Pebble.prototype.areAppMessagesSupported = function(callback){
	cordova.exec(callback, function(err) {}, "Pebble", "areAppMessagesSupported");
}
 
/**
 * Send a message to the connected Pebble to close an application identified by a UUID.
 * @param  {String} watchappUuid [description]
 */
Pebble.prototype.closeAppOnPebble = function(watchappUuid){
	cordova.exec(function(){}, function(err) {}, "Pebble", "closeAppOnPebble", [watchappUuid]);
}
 
/**
 * Send a message to the connected Pebble to "customize" a built-in PebbleKit watch-app.
 * @param  {String} appType [description]
 * @param  {String} name    [description]
 * @param  {Image}  icon     [description]
 */
Pebble.prototype.customizeWatchApp = function(appType, name, icon){
	cordova.exec(function(){}, function(err) {}, "Pebble", "customizeWatchApp", [appType, name, icon]);
}
 
/**
 * Get the version information of the firmware running on a connected watch.
 * @return {Object}
 */
Pebble.prototype.getWatchFWVersion = function(callback){
	cordova.exec(callback, function(err) {}, "Pebble", "getWatchFWVersion");
}
 
/**
 * Synchronously query the Pebble application to see if the connected watch is running a firmware version that supports PebbleKit data logging.
 * @return {Boolean}
 */
Pebble.prototype.isDataLoggingSupported = function(callback){
	cordova.exec(callback, function(err) {}, "Pebble", "isDataLoggingSupported");
}
 
/**
 * Synchronously query the Pebble application to see if an active Bluetooth connection to a watch currently exists. 
 * @return {Boolean}
 */
Pebble.prototype.isWatchConnected = function(callback){
	cordova.exec(callback, function(err) {}, "Pebble", "isWatchConnected");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'DATA_AVAILABLE' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerDataLogReceiver = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerDataLogReceiver");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'CONNECTED' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerPebbleConnectedReceiver = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerPebbleConnectedReceiver");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'DISCONNECTED' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerPebbleDisconnectedReceiver = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerPebbleDisconnectedReceiver");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'RECEIVE_ACK' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerReceivedAckHandler = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerReceivedAckHandler");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'RECEIVE' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerReceivedDataHandler = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerReceivedDataHandler");
}
 
/**
 * A convenience function to assist in programatically registering a broadcast receiver for the 'RECEIVE_NACK' intent.
 * @param  {Function} receiver Callback function
 */
Pebble.prototype.registerReceivedNackHandler = function(receiver){
	cordova.exec(receiver, function(err) {}, "Pebble", "registerReceivedNackHandler");
}
 
/**
 * A convenience function to emit an intent to Pebble.prototype.apk to request the data logs for a particular app.
 * @param  {String} appUuid [description]
 */
Pebble.prototype.requestDataLogsForApp = function(appUuid){
	cordova.exec(function(){}, function(err) {}, "Pebble", "requestDataLogsForApp", [appUuid]);
}
 
/**
 * Send a message to the connected watch acknowledging the receipt of a PebbleDictionary.
 * @param  {Number} transactionId [description]
 */
Pebble.prototype.sendAckToPebble = function(transactionId){
	cordova.exec(function(){}, function(err) {}, "Pebble", "sendAckToPebble", [transactionId]);
}
 
/**
 * Send one-or-more key-value pairs to the watch-app identified by the provided UUID.
 * @param  {String} watchappUuid [description]
 * @param  {Object} data         [description]
 */
Pebble.prototype.sendDataToPebble = function(watchappUuid, data){
	cordova.exec(function(){}, function(err) {}, "Pebble", "sendDataToPebble", [watchappUuid, data]);
}
 
/**
 * Send one-or-more key-value pairs to the watch-app identified by the provided UUID.
 * @param  {String} watchappUuid  [description]
 * @param  {Object} data         PebbleDictionary
 * @param  {Number} transactionId [description]
 */
Pebble.prototype.sendDataToPebbleWithTransactionId = function(watchappUuid, data, transactionId){
	cordova.exec(function(){}, function(err) {}, "Pebble", "sendDataToPebbleWithTransactionId", [watchappUuid, data, transactionId]);
}
 
/**
 * Send a message to the connected watch that the previously sent PebbleDictionary was not received successfully.
 * @param  {Number} transactionId [description]
 */
Pebble.prototype.sendNackToPebble = function(transactionId){
	cordova.exec(function(){}, function(err) {}, "Pebble", "sendNackToPebble", [transactionId]);
}
 
/**
 * Send a message to the connected Pebble to launch an application identified by a UUID.
 * @param  {String} watchappUuid [description]
 */
Pebble.prototype.startAppOnPebble = function(watchappUuid){
	cordova.exec(function(){}, function(err) {}, "Pebble", "startAppOnPebble", [watchappUuid]);
}
 
 
// These are in official Pebble app's API
 
/**
 * Listen for event
 * @param {[type]}   name     [description]
 * @param {Function} callback [description]
 * @todo : list available event names, here
 */
Pebble.prototype.addEventListener = function(name, callback){
	cordova.exec(callback, function(err) {}, "Pebble", "addEventListener", [name]);
}
 
/**
 * Send AppMessage to Pebble
 * @param  {Object}   data        [description]
 * @param  {Function} ackHandler  Success callback
 * @param  {Function} nackHandler Error callback
 * @return {Number}               transactionId
 */
Pebble.prototype.sendAppMessage = function( data, ackHandler, nackHandler){
	cordova.exec(ackHandler, nackHandler, "Pebble", "sendAppMessage", [data]);
}
 
/**
 * Send a standard system notification to Pebble
 * @param  {String} title [description]
 * @param  {String} text  [description]
 */
Pebble.prototype.showSimpleNotificationOnPebble = function(title, text){
	cordova.exec(function(){}, function(err) {}, "Pebble", "showSimpleNotificationOnPebble", [title, text]);
}
 
/**
 * Return unique account token that is associated to the Pebble account of the current user.
 * @return {String} [description]
 */
Pebble.prototype.getAccountToken = function(callback){
	cordova.exec(callback, function(err) {}, "Pebble", "getAccountToken");
}
 
/**
 * Open configuration dialog
 * @param  {String} url [description]
 */
Pebble.prototype.openURL = function(url){
	cordova.exec(function(){}, function(err) {}, "Pebble", "openURL", [url]);
}
var cordova = require('cordova');

// every method has node-style callback as last argument: function(error, result)
// if last arg is not a function, use it as arg
var cordova_exec = function(method, args){
	if (args.length){
		callback = args.pop();
		if (typeof(callback) !== 'function'){ args.push(callback); }
	}
	cordova.exec(function(result){
		callback(null, result);
	}, callback, "Pebble", method, args);
}

var Pebble = {};
[
	"areAppMessagesSupported",
	"closeAppOnPebble",
	"customizeWatchApp",
	"getWatchFWVersion",
	"isDataLoggingSupported",
	"isWatchConnected",
	"registerDataLogReceiver",
	"unregisterDataLogReceiver",
	"registerPebbleConnectedReceiver",
	"registerPebbleDisconnectedReceiver",
	"registerReceivedAckHandler",
	"registerReceivedDataHandler",
	"registerReceivedNackHandler",
	"requestDataLogsForApp",
	"sendAckToPebble",
	"sendDataToPebble",
	"sendDataToPebbleWithTransactionId",
	"sendNackToPebble",
	"startAppOnPebble",
	"addEventListener",
	"sendAppMessage",
	"showSimpleNotificationOnPebble",
	"getAccountToken",
	"openURL"
].forEach(function(method) {
	Pebble[method] = new Function('cordova_exec(' + method + ', Array.prototype.slice.call(arguments));');
});

module.exports = Pebble;
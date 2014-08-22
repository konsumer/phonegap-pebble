var cordova = require('cordova');

var Pebble = {};

Pebble.isWatchConnected = function(cb){
	cordova.exec(function(result){
		cb(null, result);
	}, cb, 'Pebble', 'isWatchConnected', []);
}

Pebble.getWatchFWVersion = function(cb){
	cordova.exec(function(result){
		cb(null, result);
	}, cb, 'Pebble', 'getWatchFWVersion', []);
}


module.exports = Pebble;
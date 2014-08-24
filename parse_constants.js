#!/usr/bin/env node

/**
 * This will simply parse constants from PebbleKit Constants.java, and make them available to javascript wrapper
 */

var path = require('path'),
	fs = require('fs');

var constants = {};

fs.readFile(path.join(__dirname, 'src', 'android', 'Constants.java'), function(err, data){
	if (err) throw err;
	data.toString().match(/public static final .+ (.+) = (.+);/g).forEach(function(line){
		var split = line.split('=');
		constants[ split[0].trim().split(' ').pop() ] = eval( split.pop().split(';').shift().replace('UUID.fromString(','').replace(')','') );
	});
	for (i in constants){
		console.log('Pebble.' + i + ' = ' + JSON.stringify(constants[i]) + ';');
	}
});
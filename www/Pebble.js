var Pebble = function(){},
    dummy = function(){},
    genericError = function(err){ console.error(err); };

/**
 * make image, get base64 string for it
 * @param  {String}   src The URL of the image
 * @param  {Function} cb   function(dataURL)
 */
Pebble.prototype.base64image = function(src, outputFormat, cb){
    var canvas = document.createElement('CANVAS'),
        ctx = canvas.getContext('2d'),
        img = new Image;
    img.onload = function(){
        var dataURL;
        canvas.height = img.height;
        canvas.width = img.width;
        ctx.drawImage(img, 0, 0);
        dataURL = canvas.toDataURL(outputFormat);
        canvas = null;
        cb(dataURL);
    };
    img.src = src;
};

/**
 * Find out if app-messages are supported
 * @param  {Function} success called with answer
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.areAppMessagesSupported = function(success, error){
    if (!success) return console.error('success callback is required');
    cordova.exec(success, error || genericError, 'Pebble', 'areAppMessagesSupported', []);
};

/**
 * Find out if data-logging is supportedon Pebble
 * @param  {Function} success called with answer
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.isDataLoggingSupported = function(success, error){
    if (!success) return console.error('success callback is required');
    cordova.exec(success, error || genericError, 'Pebble', 'isDataLoggingSupported', []);
};

/**
 * Check if Pebble is currently connected
 * @param  {Function} success called with answer
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.isConnected = function(success, error){
    if (!success) return console.error('success callback is required');
    cordova.exec(success, error || genericError, 'Pebble', 'isWatchConnected', []);
};

/**
 * Get information about firmware installed on Pebble
 * @param  {Function} success called with answer
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.firmware = function(success, error){
    if (!success) return console.error('success callback is required');
    cordova.exec(success, error || genericError, 'Pebble', 'getWatchFWVersion', []);
};

/**
 * Start an app on the Pebble
 * @param  {String}   uuid UUID of app to be started
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.startApp = function(uuid, success, error){
    if (!uuid) return console.error('uuid is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'startAppOnPebble', [uuid]);
};

/**
 * Close an app on the Pebble
 * @param  {String}   uuid    UUID of app to be closed
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.closeApp = function(uuid, success, error){
    if (!uuid) return console.error('uuid is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'closeAppOnPebble', [uuid]);
};

/**
 * Send an alert to Pebble
 * @param  {String}   sender  Who is sending this?
 * @param  {String}   title   title of message
 * @param  {String}   body    body of message
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.alert = function(sender, title, body, success, error){
    if (!sender || !title || !body) return console.error('sender, title & body are required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'alert', [sender, title, body]);
};

/**
 * Update now-playing on Pebble
 * @param  {String}   artist  [description]
 * @param  {String}   album   [description]
 * @param  {String}   track   [description]
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.music = function(artist, album, track, success, error){
    if (!artist || !album || !track) return console.error('artist, album & track are required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'music', [artist, album, track]);
};

/**
 * Send data to Pebble
 * @param  {String}   uuid          UUID of app to receive data
 * @param  {Array}    data          Data to send, should be in this format: [{type:"", key:"", value:"", length:""}]
 * @param  {Integer}  transactionId (optional) ID for NACK & ACK Stuff
 * @param  {Function} success       (optional) called when native returns
 * @param  {Function} error         (optional) called when native returns with problem
 */
Pebble.prototype.sendData = function(uuid, data, transactionId, success, error){
    if (!uuid || !data) return console.error('uuid & data are required');
    if (typeof transactionId == 'function'){
        error = success;
        success = transactionId;
        transactionId = null;
    }
    
    if (!transactionId){
        cordova.exec(success||dummy, error||genericError, 'Pebble', 'sendDataToPebble', [uuid, JSON.stringify(data)]);
    }else{
        cordova.exec(success||dummy, error||genericError, 'Pebble', 'sendDataToPebbleWithTransactionId', [uuid, JSON.stringify(data), transactionId]);
    }
};

/**
 * Send ACK to Pebble, keyed by transactionId
 * @param  {Integer}  transactionId ID for NACK & ACK Stuff
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.sendAck = function(transactionId, success, error){
    if (!transactionId) return console.error('transactionId is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'sendAckToPebble', [transactionId]);
};

/**
 * Send NACK to Pebble, keyed by transactionId
 * @param  {Integer}  transactionId ID for NACK & ACK Stuff
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.sendNack = function(transactionId, success, error){
    if (!transactionId) return console.error('transactionId is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'sendNackToPebble', [transactionId]);
};

/**
 * Tell native to listen for pebble data. You can document.addEventListener("Pebble.data",function(e){  });
 * @param  {String}      uuid UUID of app to listen to data from
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerData = function(uuid, success, error){
    if (!uuid) return console.error('uuid is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerReceivedDataHandler', [uuid]);
};

/**
 * Tell native not to listen for pebble data.
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterData = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterReceivedDataHandler', []);
};

/**
 * Tell native to listen for pebble connects. You can document.addEventListener("Pebble.connect",function(e){  });
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerConnect = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerPebbleConnectedReceiver', []);
};

/**
 * Tell native not to listen for pebble connects.
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterConnect = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterPebbleConnectedReceiver', []);
};

/**
 * Tell native to listen for pebble disconnects. You can document.addEventListener("Pebble.disconnect",function(e){  });
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerDisconnect = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerPebbleDisconnectedReceiver', []);
};

/**
 * Tell native not to listen for pebble disconnects.
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterDisconnect = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterPebbleDisconnectedReceiver', []);
};

/**
 * Tell native to listen for pebble ACK. You can document.addEventListener("Pebble.ack",function(e){  });
 * @param  {String}      uuid UUID of app to listen to ACK from
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerAck = function(uuid, success, error){
    if (!uuid) return console.error('uuid is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerReceivedAckHandler', [uuid]);
};

/**
 * Tell native not to listen for pebble ACK.
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterAck = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterReceivedAckHandler', []);
};

/**
 * Tell native to listen for pebble NACK. You can document.addEventListener("Pebble.nack",function(e){  });
 * @param  {String}      uuid UUID of app to listen to NACK from
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerNack = function(uuid, success, error){
    if (!uuid) return console.error('uuid is required');
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerReceivedNackHandler', [uuid]);
};

/**
 * Tell native not to listen for pebble NACK.
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterNack = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterReceivedNackHandler', []);
};

/**
 * Customize a built-in PebbleKit watch-app
 * @param  {String}   type "golf", "sports", or "other"
 * @param  {String}   name New name
 * @param  {Image}    icon An image object for the icon
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.customizeWatchApp = function(type, name, icon, success, error){
    if (!type || !name || !icon) return console.error('type, name & icon are required');
    this.base64image(icon.src || icon, 'image/png', function(dataURL){
        cordova.exec(success||dummy, error||genericError, 'Pebble', 'customizeWatchApp', [type, name, dataURL]);
    });
};

/**
 * Listen for data-logs
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.registerDatalog = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'registerDatalog', []);
};

/**
 * Stop Listening for data-logs
 * @param  {Function} success (optional) called when native returns
 * @param  {Function} error   (optional) called when native returns with problem
 */
Pebble.prototype.unregisterDatalog = function(success, error){
    cordova.exec(success||dummy, error||genericError, 'Pebble', 'unregisterDatalog', []);
};



module.exports = new Pebble();

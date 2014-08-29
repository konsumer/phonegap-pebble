(function (){
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
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.areAppMessagesSupported = function(success, error){
        cordova.exec(success, error || genericError, 'Pebble', 'areAppMessagesSupported', []);
    };

    /**
     * Find out if data-logging is supportedon Pebble
     * @param  {Function} success called with answer
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.isDataLoggingSupported = function(success, error){
        cordova.exec(success, error || genericError, 'Pebble', 'isDataLoggingSupported', []);
    };

    /**
     * Check if Pebble is currently connected
     * @param  {Function} success called with answer
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.isWatchConnected = function(success, error){
        cordova.exec(success, error || genericError, 'Pebble', 'isWatchConnected', []);
    };

    /**
     * Get information about firmware installed on Pebble
     * @param  {Function} success called with answer
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.getWatchFWVersion = function(success, error){
        cordova.exec(success, error || genericError, 'Pebble', 'getWatchFWVersion', []);
    };

    /**
     * Start an app on the Pebble
     * @param  {String}   uuid UUID of app to be started
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.startAppOnPebble = function(uuid, success, error){
        cb = cb || function(){};
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'startAppOnPebble', [uuid]);
    };

    /**
     * Close an app on the Pebble
     * @param  {String}   uuid    UUID of app to be closed
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.closeAppOnPebble = function(uuid, success, error){
        cb = cb || function(){};
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'closeAppOnPebble', [uuid]);
    };

    /**
     * Send an alert to Pebble
     * @param  {String}   sender  Who is sending this?
     * @param  {String}   title   title of message
     * @param  {String}   body    body of message
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.alert = function(sender, title, body, success, error){
        cb = cb || function(){};
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'alert', [sender, title, body]);
    }

    /**
     * Update now-playing on Pebble
     * @param  {String}   artist  [description]
     * @param  {String}   album   [description]
     * @param  {String}   track   [description]
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.music = function(artist, album, track, success, error){
        cb = cb || function(){};
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'music', [artist, album, track]);
    }

    /**
     * Tell java to listen for pebble connects. You can document.addEventListener("Pebble.connect",function(e){  });
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.registerConnect = function(success, error){
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'registerPebbleConnectedReceiver', []);
    }

    /**
     * Tell java to listen for pebble disconnects. You can document.addEventListener("Pebble.disconnect",function(e){  });
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.registerDisconnect = function(success, error){
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'registerPebbleDisconnectedReceiver', []);
    }

    /**
     * Tell java not to listen for pebble connects.
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.unregisterConnect = function(success, error){
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'unregisterPebbleConnectedReceiver', []);
    }

    /**
     * Tell java not to listen for pebble disconnects.
     * @param  {Function} success (optional) called when java returns
     * @param  {Function} error   (optional) called when java returns with problem
     */
    Pebble.prototype.unregisterDisconnect = function(success, error){
        cordova.exec(success:success||dummy, error||genericError, 'Pebble', 'unregisterPebbleDisconnectedReceiver', []);
    }


    // export interface
    if (window){
        if(!window.plugins) window.plugins = {};
        if (!window.plugins.Pebble) window.plugins.Pebble = new Pebble();
    }
    if (typeof module != 'undefined' && module.exports) {
      module.exports = Pebble;
    }

})();
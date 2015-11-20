module.exports = window.JSBackgroundService = {
    startService: function(callback) {
        success = function() { callback(null); };
        error = function(err) { callback(err); };
        return cordova.exec(success, error, "JSBackgroundService",
            "startService", []);
    },
    setRepeating: function(periodMillis, callback) {
        success = function() { callback(null); };
        error = function(err) { callback(err); };
        return cordova.exec(success, error, "JSBackgroundService",
            "setRepeating", [periodMillis]);
    },

    cancelRepeating: function(callback) {
        success = function() { callback(null); };
        error = function(err) { callback(err); };
        return cordova.exec(success, error, "JSBackgroundService",
            "cancelRepeating", []);
    },

    isRepeating: function(callback) {
        success = function(isSet) { callback(null, isSet === 'true'); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "isRepeating", []);
    },

    isRunning: function(callback) {
        success = function(isSet) { callback(null, isSet === 'true'); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "isRunning", []);
    },

    startMainActivity: function(callback) {
        success = function() { callback(); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "startMainActivity", []);
    },

    listenNewPictures: function(listen, callback) {
        success = function() { callback(null); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "listenNewPictures", [listen]);
    },
};


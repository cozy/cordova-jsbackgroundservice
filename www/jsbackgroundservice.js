module.exports = window.JSBackgroundService = {

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


};

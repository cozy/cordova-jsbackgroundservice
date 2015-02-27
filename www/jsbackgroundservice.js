module.exports = window.JSBackgroundService = {

    setRepeating: function(periodMillis, callback) {
        return cordova.exec(callback, callback, "JSBackgroundService",
            "setRepeating", [periodMillis]);
    },

    cancelRepeating: function(callback) {
        return cordova.exec(callback, callback, "JSBackgroundService",
            "cancelRepeating", []);
    },

    isRepeating: function(callback) {
        success = function(isSet) { callback(null, isSet === 'true'); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "isRepeating", []);
    },


};

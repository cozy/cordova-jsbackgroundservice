module.exports = window.JSBackgroundService = {

    start: function(callback) {
        success = function(list) { callback(null, list); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "start", []);
    },

    stop: function(callback) {
        success = function(list) { callback(null, list); };
        error = function(err) { callback(err); };

        return cordova.exec(success, error, "JSBackgroundService",
            "stop", []);
    }

};

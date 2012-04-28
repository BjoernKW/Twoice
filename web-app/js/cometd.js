var subscriptions = {};
var connected = false;

function refreshCometSubscriptions(channels) {
    for (var i in channels) {
        if (typeof i == 'string') {
            unsubscribeFromCometChannel(i);
            subscribeToCometChannel(i, channels[i]);
        }
    }
}

function unsubscribeFromCometChannel(channel) {
    if (subscriptions[channel]) {
        $.cometd.unsubscribe(subscriptions[channel]);
    }
    subscriptions[channel] = null;
}

function subscribeToCometChannel(channel, callbackFunction) {
    subscriptions[channel] = $.cometd.subscribe(channel, callbackFunction);
}

function initCometD(path, personID) {
	var channels = {};
	if (personID) {
    	var timelineChannel = '/timeline/' + personID;
    	var timelineCallback = function(message) { refreshTimeline(JSON.parse(message.data).payload, 'timeline_' + personID); };
		channels[timelineChannel] = timelineCallback;
	}
	var globalTimelineChannel = '/timeline/global';
    var globalTimelineCallback = function(message) { refreshTimeline(JSON.parse(message.data).payload, 'timeline_global'); };
	channels[globalTimelineChannel] = globalTimelineCallback;
	
	
    $.cometd.init(path + '/cometd');
    $.cometd.addListener('/meta/connect', function(message) {
        if ($.cometd.isDisconnected()){
            return;
        }
        
        var wasConnected = connected;
        connected = message.successful;
        if (!wasConnected && connected) {
            // Reconnected
        } else if (wasConnected && !connected) {
            // Disconnected
            $.cometd.init(path + '/cometd');
            refreshCometSubscriptions(channels);
        }
    });
    
    refreshCometSubscriptions(channels);
}

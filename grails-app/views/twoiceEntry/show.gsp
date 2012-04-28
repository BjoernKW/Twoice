
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'twoiceEntry.label', default: 'TwoiceEntry')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
		<rateable:resources/>
		<p:css name="jplayer.blue.monday"/>
		<p:javascript src="jquery.jplayer.min"/>
		<p:dependantJavascript>
			<script type="text/javascript">
			<!--
				var jpPlayTimes = new Array();

				function initPlayers() {
					initSinglePlayer(${twoiceEntryInstance.id}, '${resource(dir:'audioFiles', file:fieldValue(bean: twoiceEntryInstance, field: 'filename'))}');
				}

				function initSinglePlayer(entryID, fileURL) {
					jpPlayTimes[entryID] = $('#jplayer_play_time_' + entryID);

					$('#jquery_jplayer_' + entryID).jPlayer({
						ready: function () {
							this.element.jPlayer('setFile', fileURL)
						},
						volume: 20,
						oggSupport: false,
						customCssIds: true,
						preload: 'none'
					})
					.jPlayer('cssId', 'pause', 'jplayer_pause_' + entryID)
					.jPlayer('cssId', 'play', 'jplayer_play_' + entryID)
					.jPlayer('cssId', 'stop', 'jplayer_stop_' + entryID)
					.jPlayer('cssId', 'loadBar', 'jplayer_load_bar_' + entryID)
					.jPlayer('cssId', 'playBar', 'jplayer_play_bar_' + entryID)
					.jPlayer('cssId', 'volumeMin', 'jplayer_volume_min_' + entryID)
					.jPlayer('cssId', 'volumeMax', 'jplayer_volume_max_' + entryID)
					.jPlayer('cssId', 'volumeBar', 'jplayer_volume_bar_' + entryID)
					.jPlayer('cssId', 'volumeBarValue', 'jplayer_volume_bar_value_' + entryID)
					.jPlayer('onProgressChange', function(loadPercent, playedPercentRelative, playedPercentAbsolute, playedTime, totalTime) {
						jpPlayTimes[entryID].text($.jPlayer.convertTime(playedTime));
					}).jPlayer('onSoundComplete', function() {
						$('#jquery_jplayer_' + entryID).jPlayer('pause');
					});
				}
				
			    $(document).ready(function() {
			        initPlayers();
			    });
			// -->
			</script>
		</p:dependantJavascript>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:render template="/timeline/entry" model="[twoiceEntryInstance:twoiceEntryInstance]"/>
        </div>
    </body>
</html>

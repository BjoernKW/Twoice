<rateable:resources/>
<p:css name="jplayer.blue.monday"/>
<p:javascript src="jquery.jplayer.min"/>
<p:dependantJavascript>
	<script type="text/javascript">
	<!--
		var jpPlayTimes = new Array();

		function refreshTimeline(data, element) {
			var entry = data.entry;
			var fileURL = data.fileURL;

			$.get('${resource(dir:'')}/timeline/entry/' + entry.id, function(data) {
				$('<span>').html(data).prependTo('#' + element);
				<sec:ifLoggedIn>
					initRating(entry.id + '_rating');
				</sec:ifLoggedIn>
				initSinglePlayer(entry.id, fileURL);
				$('#entry_' + entry.id).effect('highlight', { }, 1000);
			});
		}
		
		function initPlayers() {
			<g:each in="${twoiceEntryInstanceList}" status="i" var="twoiceEntryInstance">
				initSinglePlayer(${twoiceEntryInstance.id}, '${resource(dir:'audioFiles', file:fieldValue(bean: twoiceEntryInstance, field: 'filename'))}');
			</g:each>
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
		
		function initRating(elementID) {
			var id = elementID.split('_')[0];
			var i = YAHOO.org.grails.plugin.rateable.rateables.length;
			YAHOO.org.grails.plugin.rateable.rateables[i] = {};
			var tr = YAHOO.org.grails.plugin.rateable.rateables[i];
			tr.id = id;
			tr.average = YAHOO.util.Dom.get(id + '_form').title;
			tr.submitted = false;
			YAHOO.org.grails.plugin.rateable.make_stardivs(i);
		}
		
	    $(document).ready(function() {
	        initPlayers();
			var personID;
			<sec:ifLoggedIn>
				personID = <sec:loggedInUserInfo field="id"/>;
			</sec:ifLoggedIn>
			initCometD('${resource(dir:'')}', personID);
	    });
	// -->
	</script>
</p:dependantJavascript>

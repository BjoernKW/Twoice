<div class="entry" id="entry_${twoiceEntryInstance.id}">
	<div class="entryContentBox">
		<div id="jquery_jplayer_${twoiceEntryInstance.id}"></div>

		<div class="jp-single-player">
			<div class="jp-interface">
				<ul class="jp-controls">
					<li><a href="#" id="jplayer_play_${twoiceEntryInstance.id}" class="jp-play" tabindex="1">play</a></li>
					<li><a href="#" id="jplayer_pause_${twoiceEntryInstance.id}" class="jp-pause" tabindex="1">pause</a></li>
					<li><a href="#" id="jplayer_stop_${twoiceEntryInstance.id}" class="jp-stop" tabindex="1">stop</a></li>
					<li><a href="#" id="jplayer_volume_min_${twoiceEntryInstance.id}" class="jp-volume-min" tabindex="1">min volume</a></li>
					<li><a href="#" id="jplayer_volume_max_${twoiceEntryInstance.id}" class="jp-volume-max" tabindex="1">max volume</a></li>
				</ul>
				<div class="jp-progress">
					<div id="jplayer_load_bar_${twoiceEntryInstance.id}" class="jp-load-bar">
						<div id="jplayer_play_bar_${twoiceEntryInstance.id}" class="jp-play-bar"></div>
					</div>
				</div>
				<div id="jplayer_volume_bar_${twoiceEntryInstance.id}" class="jp-volume-bar">
					<div id="jplayer_volume_bar_value_${twoiceEntryInstance.id}" class="jp-volume-bar-value"></div>
				</div>
				<div id="jplayer_play_time_${twoiceEntryInstance.id}" class="jp-play-time"></div>
			</div>
			<div id="jplayer_playlist_${twoiceEntryInstance.id}" class="jp-playlist">
				<ul><li>&nbsp;</li></ul>
			</div>
		</div>
		<br /><g:link controller="timeline" id="${twoiceEntryInstance.person.id}">${fieldValue(bean: twoiceEntryInstance, field: "person")}</g:link><g:if test="${twoiceEntryInstance.person.twitterUsername}"> | <a href="http://twitter.com/${twoiceEntryInstance.person.twitterUsername}"> @Twitter</a></g:if><sec:ifLoggedIn> | <g:link controller="profile" action="subscribe" id="${twoiceEntryInstance.person.id}"><g:message code="subscribe"/></g:link></sec:ifLoggedIn><br /><br />
		<div id="tags_${twoiceEntryInstance.id}">
			<g:render template="/twoiceEntry/tags" model="[twoiceEntryInstance:twoiceEntryInstance]"/>
		</div>
		<sec:ifLoggedIn>
			<rateable:ratings id="${twoiceEntryInstance.id}" bean="${twoiceEntryInstance}"/>
		</sec:ifLoggedIn>
	</div>
	<div class="commentBox">
		<div id="comments_${twoiceEntryInstance.id}" class="commentable">
			<g:render template="/commentable/comments" model="[twoiceEntryInstance:twoiceEntryInstance]"/>
		</div>
		<div id="addComment_${twoiceEntryInstance.id}" class="addComment">
			<sec:ifLoggedIn>
				<h2 class="addCommentTitle">
					<a href="#commentEditor_${twoiceEntryInstance.id}" onclick="$('#addCommentContainer_${twoiceEntryInstance.id}').show('clip', { }, 1000);">
						<g:message code="comment.add.title" default="Post a Comment"/>
					</a>
				</h2>
			</sec:ifLoggedIn>
			<div id="addCommentContainer_${twoiceEntryInstance.id}" class="addCommentContainer" style="display:none;">
				<a name="commentEditor_${twoiceEntryInstance.id}"></a>
				<g:formRemote name="addCommentForm_${twoiceEntryInstance.id}" url="[controller:'commentable', action:'add']" update="comments_${twoiceEntryInstance.id}">
					<g:textArea id="commentBody_${twoiceEntryInstance.id}" name="comment.body"/><br />
					<g:hiddenField name="update" value="comments"/>			
					<g:hiddenField name="commentLink.commentRef" value="${twoiceEntryInstance.id}"/>
					<g:hiddenField name="commentLink.type" value="${twoiceEntryInstance.class.name}"/>			
					<g:hiddenField name="commentPageURI" value="${request.forwardURI}"/>
					<input type="submit" onclick="$('#addCommentContainer_${twoiceEntryInstance.id}').hide('clip', { }, 1000);" value="${g.message(code:'comment.post.button.name', 'default':'Post')}" id="commentSubmitButton_${twoiceEntryInstance.id}" />
				</g:formRemote>
			</div>
		</div>
	</div>
	<div style="clear: both;">&nbsp;</div>
</div>

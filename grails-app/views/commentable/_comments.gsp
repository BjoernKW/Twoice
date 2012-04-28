<ul class="noBullets">
	<comments:each bean="${twoiceEntryInstance}">
		<li class="commentEntry">
			${comment.body}
			<br /><br />
			<avatar:gravatar email="${comment.poster.email}"/>&nbsp;<g:link controller="timeline" id="${comment.poster.id}">${comment.poster}</g:link><g:if test="${comment.poster.twitterUsername}"> | <a href="http://twitter.com/${comment.poster.twitterUsername}"> @Twitter</a></g:if><sec:ifLoggedIn> | <g:link controller="profile" action="subscribe" id="${twoiceEntryInstance.person.id}"><g:message code="subscribe"/></g:link></sec:ifLoggedIn><br /> <g:formatDate date="${comment.dateCreated}"/>
		</li>
	</comments:each>
<ul>

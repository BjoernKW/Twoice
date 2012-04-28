
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'twoiceEntry.label', default: 'TwoiceEntry')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
		<p:css name="simpleplayer"/>
		<p:javascript src="jquery.simpleplayer.min"/>
		<p:dependantJavascript>
			<script type="text/javascript">
			<!--
			    $(document).ready(function() {
			        var settings = {
			            progressbarWidth: '200px',
			            progressbarHeight: '5px',
			            progressbarColor: '#22ccff',
			            progressbarBGColor: '#eeeeee',
			            defaultVolume: 0.2
			        };
			        $(".player").player(settings);
			    });
			// -->
			</script>
		</p:dependantJavascript>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'twoiceEntry.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="filename" title="${message(code: 'twoiceEntry.filename.label', default: 'Filename')}" />
                        
                            <th><g:message code="twoiceEntry.person.label" default="Person" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${twoiceEntryInstanceList}" status="i" var="twoiceEntryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${twoiceEntryInstance.id}">${fieldValue(bean: twoiceEntryInstance, field: "id")}</g:link></td>
                        
                            <td><audio class="player" src="${resource(dir:'audioFiles', file:fieldValue(bean: twoiceEntryInstance, field: "filename"))}"><a href="${resource(dir:'audioFiles', file:fieldValue(bean: twoiceEntryInstance, field: "filename"))}">${fieldValue(bean: twoiceEntryInstance, field: "filename")}</a></audio></td>
                        
                            <td><g:link controller="timeline" id="${twoiceEntryInstance.person.id}">${fieldValue(bean: twoiceEntryInstance, field: "person")}</g:link><g:if test="${twoiceEntryInstance.person.twitterUsername}"> | <a href="http://twitter.com/${twoiceEntryInstance.person.twitterUsername}"> @Twitter</a></g:if><sec:ifLoggedIn> | <g:link controller="profile" action="subscribe" id="${twoiceEntryInstance.person.id}"><g:message code="subscribe"/></g:link></sec:ifLoggedIn></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${twoiceEntryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>

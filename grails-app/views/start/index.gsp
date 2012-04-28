
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <title><g:message code="welcomeTo" args="[message(code:'appName', 'default':'Twoice')]" default="Welcome to Twoice" /> - <g:message code="home" /></title>
		<meta name="layout" content="main" />
		<g:render template="/timeline/header"/>
    </head>
    <body>
		<div class="body">
			<h1><g:message code="home" default="Home" /></h1>
			<g:if test="${flash.error}">
            <div class="error">${flash.error}</div>
            </g:if>
			<g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
			<g:if test="${twoiceEntryInstanceTotal == 0}">
				<g:message code="noEntries" />&nbsp;<g:link controller="timeline"><g:message code="goToTimeline" /></g:link>
			</g:if>
			<g:render template="/timeline/timeline" model="[twoiceEntryInstanceList:twoiceEntryInstanceList, element:personID]"/>
		</div>
    </body>
</html>

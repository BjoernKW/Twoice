
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <title><g:message code="welcomeTo" args="[message(code:'appName', 'default':'Twoice')]" default="Welcome to Twoice" /> - <g:message code="searchResults" /></title>
		<meta name="layout" content="main" />
		<g:render template="/timeline/header"/>
    </head>
    <body>
		<div class="body">
			<h1><g:message code="searchResults" default="searchResults" /></h1>
			<g:render template="/timeline/timeline" model="[twoiceEntryInstanceList:twoiceEntryInstanceList, element:personID]"/>
		</div>
    </body>
</html>

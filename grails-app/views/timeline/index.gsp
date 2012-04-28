
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="timeline" default="Timeline" /></title>
		<g:render template="header"/>
	</head>
    <body>
        <div class="body">
			<h1><g:message code="timeline" default="Timeline" /></h1>
			<g:render template="timeline" model="[twoiceEntryInstanceList:twoiceEntryInstanceList, element:'global']"/>
        </div>
    </body>
</html>

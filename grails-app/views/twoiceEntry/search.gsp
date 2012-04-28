
<%@ page import="twoice.TwoiceEntry" %>
<html>
    <head>
        <title><g:message code="welcomeTo" args="[message(code:'appName', 'default':'Twoice')]" default="Welcome to Twoice" /> - <g:message code="search" /></title>
		<meta name="layout" content="main" />
		<g:render template="/timeline/header"/>
    </head>
    <body>
		<div class="body">
			<h1><g:message code="search" default="search" /></h1>
			<g:form action="searchResults" >
				<div class="dialog">
				    <table>
				        <tbody>

				            <tr class="prop">
				                <td valign="top" class="name">
				                    <label for="filename"><g:message code="search" default="Search" /></label>
				                </td>
				                <td valign="top" class="value">
				                    <g:textField name="q" />
				                </td>
				            </tr>

				        </tbody>
				    </table>
				</div>
				<div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'search', default: 'Search')}" /></span>
                </div>
			</g:form>
		</div>
    </body>
</html>

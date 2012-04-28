<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
		<title><g:message code="profile" default="Profile" /></title>
    </head>
    <body>
        <div class="body">
			<h1><g:message code="profile" default="Profile" /></h1>
			<g:oauthLink consumer="Twitter" returnTo="[controller: 'profile', action: 'oAuthCallback' ]"><g:message code="user.authorize.twitter" /></g:oauthLink><br /><br />
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.username.label" default="Username" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: personInstance, field: "username")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.email.label" default="eMail Address" /></td>
                            
                            <td valign="top" class="value"><a href="mailto:${personInstance.email}">${fieldValue(bean: personInstance, field: "email")}</a></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.twitterUsername.label" default="Twitter Username" /></td>
                            
                            <td valign="top" class="value"><a href="http://twitter.com/${personInstance.twitterUsername}">${fieldValue(bean: personInstance, field: "twitterUsername")}</a></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.dateCreated.label" default="Date Created" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${personInstance?.dateCreated}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.lastUpdated.label" default="Last Updated" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${personInstance?.lastUpdated}" /></td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.entries.label" default="Entries" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${personInstance.entries}" var="e">
                                    <li><g:link controller="twoiceEntry" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.phoneNumbers.label" default="Phone Numbers" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${personInstance.phoneNumbers}" var="p">
                                    <li><g:link controller="phoneNumber" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
								<g:link class="create" controller="phoneNumber" action="newPhoneNumber"><g:message code="default.new.label" args="['PhoneNumber']" /></g:link>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.subscriptions.label" default="Subscriptions" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${personInstance.subscriptions}" var="s">
                                    <li><g:link controller="person" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

<html>
    <head>
        <title><g:message code="welcomeTo" args="[message(code:'appName', 'default':'Twoice')]" default="Welcome to Twoice" /> - <g:message code="user.login" /></title>
		<meta name="layout" content="main" />
    </head>
    <body>
		<div class="body">
			<div id="loginBox">
				<div id="mainForm">
					<g:if test="${flash.message}">
		            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
		            </g:if>
					<form action="${postUrl}" method="POST" id="loginForm" autocomplete="off">
						<div class="dialog">
						    <table>
						        <tbody>

						            <tr class="prop">
						                <td valign="top" class="name">
											<label for="username"><g:message code="user.name" /></label>
										</td>
										<td valign="top" class="value">                                
						                    <input type="text" name="j_username" id="username" />
						                </td>
									</tr>
								
									<tr class="prop">
						                <td valign="top" class="name">
											<label for="password"><g:message code="user.password" /></label>
										</td>
										<td valign="top" class="value">                                
						                    <input type="password" name="j_password" id="password" />
						                </td>
									</tr>
									
									<tr class="prop">
						                <td valign="top" class="name">
											<label for="remember_me"><g:message code="user.remember" /></label>
										</td>
										<td valign="top" class="value">                                
						                    <input type="checkbox" name="${rememberMeParameter}" id="remember_me" <g:if test="${hasCookie}">checked="checked"</g:if> />
						                </td>
									</tr>
								</tbody>
						    </table>
						</div>
						<input type="hidden" name="${rememberMeParameter}" value="true" />
						<br /><g:submitButton name="login" class="save" value="${message(code: 'spring.security.ui.login.login')}" />
						<br /><br /><g:link controller="register"><g:message code="spring.security.ui.login.register"/></g:link> | <g:link controller="register" action="forgotPassword"><g:message code="spring.security.ui.login.forgotPassword"/></g:link>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>

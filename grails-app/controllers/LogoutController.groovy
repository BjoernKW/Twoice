import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import twoice.Person

class LogoutController {
	def securityHelperService
	
	static navigation = [
		group:'main',
		order:60,
		title:'Log out', 
		action:'index',
		isVisible: { securityHelperService.loggedInUser }
	]
	
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO  put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}

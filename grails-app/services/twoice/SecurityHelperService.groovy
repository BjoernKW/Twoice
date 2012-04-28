package twoice

class SecurityHelperService {

    static transactional = false

	def springSecurityService

    def getLoggedInUser() {
		def person
		def principal = springSecurityService.principal
		if (principal != 'anonymousUser' && principal) {
			person = Person.findByUsername(principal.username)
		}
		
		return person
    }
}

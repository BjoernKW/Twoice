import twoice.*

import org.grails.rateable.RateableController
import grails.util.Environment

import org.apache.commons.lang.StringUtils

class BootStrap {
	def springSecurityService
	def twoiceEntryHelperService
	
    def init = { servletContext ->
		String.metaClass.mixin StringUtils
		
		if (Person.count() == 0) {
			def password = springSecurityService.encodePassword('xxx')
			def anonymousPassword = springSecurityService.encodePassword('xxx')
			
			def admin = new Person(username:'admin', password:password, enabled:true, email:'admin@somedomain.net')
			admin.save()
			
			def anonymous = new Person(username:'anonymous', password:anonymousPassword, enabled:true, email:'anonymous@somedomain.net')
			anonymous.save()
			
			def roleSu = new Authority(authority:'ROLE_SUPERVISOR')
			roleSu.save()

			def roleAdmin = new Authority(authority:'ROLE_ADMIN')
			roleAdmin.save()

			def roleUser = new Authority(authority:'ROLE_USER')
			roleUser.save()
			
			PersonAuthority.create(admin, roleSu)
			PersonAuthority.create(anonymous, roleUser)
     	}
		
     	if (Requestmap.count() == 0) {
			new Requestmap(url:'/twoiceEntry/**', configAttribute:'IS_AUTHENTICATED_ANONYMOUSLY').save()
			new Requestmap(url:'/twoiceEntry/delete**', configAttribute:'ROLE_ADMIN').save()
			
			new Requestmap(url:'/phoneNumber/**', configAttribute:'ROLE_USER').save()
			new Requestmap(url:'/profile/**', configAttribute:'ROLE_USER').save()
			
			new Requestmap(url:'/user/**', configAttribute:'ROLE_ADMIN').save()
			new Requestmap(url:'/role/**', configAttribute:'ROLE_ADMIN').save()
			new Requestmap(url:'/requestmap/**', configAttribute:'ROLE_ADMIN').save()
     	}

		assert Person.count() > 0
		assert Authority.count() > 0
		assert PersonAuthority.count() > 0
		assert Requestmap.count() > 0
		
		def getUser = {
			Person.findByUsername(springSecurityService.principal.username)
		}
		RateableController.metaClass.getUser = getUser
		CommentableController.metaClass.getUser = getUser
    }
    def destroy = {
    }
}

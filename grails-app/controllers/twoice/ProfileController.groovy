package twoice

import twitter4j.TwitterFactory
import twitter4j.http.AccessToken

class ProfileController {
	
	def grailsApplication
	
	def securityHelperService
	def twoiceEntryHelperService

	static navigation = [
		group:'main',
		order:40,
		title:'Profile', 
		action:'index',
		isVisible: { securityHelperService.loggedInUser }
	]
	
    def index = {
		def person = securityHelperService.loggedInUser
		[ personInstance : person ]
	}
	
	def subscribe = {
		def person = securityHelperService.loggedInUser
		person.addToSubscriptions(Person.get(params.id))
		person.save()
		
		twoiceEntryHelperService.refreshSubscriptions(person)
		
		redirect(controller:'start')
	}

	def oAuthCallback = {
		def person = securityHelperService.loggedInUser
		
		def token = session.oauthToken.key
		def secret = session.oauthToken.secret
		
		person.twitterOauthToken = token
		person.twitterOauthSecret = secret
		
		def twitter = new TwitterFactory().getOAuthAuthorizedInstance(grailsApplication.config.oauth.Twitter.consumer.key,
			grailsApplication.config.oauth.Twitter.consumer.secret,
			new AccessToken(token, secret))
		person.twitterUsername = twitter.verifyCredentials().getScreenName()
		
		person.save()
		
		redirect(action:index)
	}
}

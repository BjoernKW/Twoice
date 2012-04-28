package twoice

import org.springframework.beans.factory.InitializingBean
import grails.util.Environment
import grails.converters.JSON

import org.apache.commons.io.FileUtils

import twitter4j.AsyncTwitterFactory
import twitter4j.http.AccessToken

class TwoiceEntryHelperService implements InitializingBean {
	
    static transactional = true

	def grailsApplication
	def servletContext
	
	def bayeux
	def bayeuxSession
	
    void afterPropertiesSet() {
		bayeuxSession = bayeux.newLocalSession()
		bayeuxSession.handshake()
	}
	
	def assignEntry(def entry) {
		def person = Person.findByUsername('anonymous')
		
		def matcher = entry.filename =~ /.+\.([0-9]+?)\.[A-Za-z0-9]{2,}$/
		if (matcher.matches()) {
			def phoneNumber = PhoneNumber.findByValue(matcher[0][1])
			if (phoneNumber) {
				person = phoneNumber.person
			} else {
				phoneNumber = new PhoneNumber(value:matcher[0][1], person:person).save()
			}
			
			def fileNameMatcher = entry.filename =~ /(.+)\.([0-9]+?)\.([A-Za-z0-9]{2,})$/
			
			if (Environment.current == Environment.DEVELOPMENT) {
				FileUtils.copyFile(
					new File("${servletContext.getRealPath('/')}audioFiles/${entry.filename}"),
					new File("${servletContext.getRealPath('/')}audioFiles/${fileNameMatcher[0][1]}.${phoneNumber.hashedValue}.${fileNameMatcher[0][3]}")
				)
			} else {
				new File("${servletContext.getRealPath('/')}audioFiles/${entry.filename}").renameTo(
					new File("${servletContext.getRealPath('/')}audioFiles/${fileNameMatcher[0][1]}.${phoneNumber.hashedValue}.${fileNameMatcher[0][3]}")
				)
			}
			new File("${servletContext.getRealPath('/')}audioFiles/${fileNameMatcher[0][1]}.${matcher[0][1]}.wav").delete()
			new File("${servletContext.getRealPath('/')}audioFiles/${fileNameMatcher[0][1]}.${matcher[0][1]}.pcm.wav").delete()
			entry.filename = entry.filename.replaceAll(matcher[0][1], phoneNumber.hashedValue)
		}
		
		person.addToEntries(entry)
		person.save(flush:true)
		publish(person.id, entry.id)
		
		if (person.twitterUsername) {
			def url = "${grailsApplication.config.grails.serverURL}/twoiceEntry/show/${entry.id}"
			def trimURL = "${grailsApplication.config.urlShorteningService.url}${url}"
			def trimmedURL = ''
			try {
				trimmedURL = trimURL.toURL().text
			} catch (IOException e) {
				log.debug e.stackTrace
			} catch (ConnectException e) {
				log.debug e.stackTrace
			}
			if (!trimmedURL || trimmedURL == '') {
				trimmedURL = url
			}
			
			def twitterUsername = grailsApplication.config.twitter.username
			def twitterPassword = grailsApplication.config.twitter.password

			def asyncTwitter = new AsyncTwitterFactory().getOAuthAuthorizedInstance(grailsApplication.config.oauth.Twitter.consumer.key,
				grailsApplication.config.oauth.Twitter.consumer.secret,
				new AccessToken(person.twitterOauthToken, person.twitterOauthSecret))
			asyncTwitter.updateStatus("I've just added a new entry on Twoice: ${trimmedURL}");
		}
	}
	
	def refreshSubscriptions(def person) {
		bayeuxSession = bayeux.newLocalSession()
		bayeuxSession.handshake()
		for (subscription in person.subscriptions) {
			bayeuxSession.getChannel("/person/${subscription.id}").subscribe(new TwoiceSubscriberListener
				(this, 'publish', ['personID', 'entryID']))
		}
	}
	
	def publish(def personID, def entryID) {
		def twoiceEntryInstance = TwoiceEntry.get(entryID);
		def payload = [ 'entry':twoiceEntryInstance,
						'fileURL':"${grailsApplication.config.grails.serverURL}/audioFiles/${twoiceEntryInstance.filename}" ]
		bayeuxSession.getChannel("/timeline/${personID}").publish(['payload':payload] as JSON)
		bayeuxSession.getChannel("/timeline/global").publish(['payload':payload] as JSON)
	}
}

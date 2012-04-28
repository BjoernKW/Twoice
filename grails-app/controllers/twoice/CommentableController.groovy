package twoice

import grails.util.*
import org.grails.comments.*

class CommentableController {

	def add = {
		def poster = evaluatePoster()
		def commentLink
		try {
			if(params['comment'] instanceof Map) {
				Comment.withTransaction { status ->
					def comment = new Comment(params['comment'])
					comment.posterId = poster.id
					comment.posterClass = poster.class.name
					commentLink = new CommentLink(params['commentLink'])
					commentLink.type = GrailsNameUtils.getPropertyName(commentLink.type)

					if(!comment.save()) {
						status.setRollbackOnly()
					}
					else {
						commentLink.comment = comment
						if(!commentLink.save()) status.setRollbackOnly()				
					}
					
					// add tags
					if (commentLink.type == 'twoiceEntry') {
						def twoiceEntry = TwoiceEntry.get(commentLink.commentRef)
						def matcher = comment.body =~ /#(\w+)\s*/
						if (matcher.matches()) {
							matcher.each { match ->
								twoiceEntry.addTag(match[1])
							}
							twoiceEntry.save()
						}
					}
				}
			}
		} catch(Exception e) {
			log.error "Error posting comment: ${e.message}"
		}

		if(request.xhr || params.async) {
			def noEscape = false
			plugin.isAvailable(name:'grails-ui') { noEscape = true }
			render template:"/commentable/comments", 
				   model:[	noEscape:noEscape,
							twoiceEntryInstance:TwoiceEntry.get(commentLink.commentRef) ]
		} else {
			redirect url:params.commentPageURI
		}
	}
	
	def evaluatePoster() {
		def evaluator = grailsApplication.config.grails.commentable.poster.evaluator
		def poster 
		if(evaluator instanceof Closure) {
			evaluator.delegate = this
			evaluator.resolveStrategy = Closure.DELEGATE_ONLY
			poster = evaluator.call()
		}
		
		if(!poster) {
			throw new CommentException("No [grails.commentable.poster.evaluator] setting defined or the evaluator doesn't evaluate to an entity. Please define the evaluator correctly in grails-app/conf/Config.groovy or ensure commenting is secured via your security rules")
		}
		if(!poster.id) {
			throw new CommentException("The evaluated Comment poster is not a persistent instance.")
		}
		return poster
	}
}

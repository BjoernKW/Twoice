package twoice

import org.grails.taggable.*
import org.grails.rateable.*
import org.grails.comments.*

class TwoiceEntry implements Taggable, Rateable, Commentable {
	static belongsTo = [person:Person]
	static searchable = true
	
	String filename
	String searchableText = ''
	
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
    }

	public String toString() {
		"${person} - ${dateCreated}"
	}
	
	def onAddComment = { comment ->
		searchableText = searchableText + ' ' + comment
	}
}

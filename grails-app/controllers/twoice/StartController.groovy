package twoice

class StartController {
	
	def securityHelperService

	static navigation = [
		group:'main',
		order:10,
		title:'Home', 
		action:'index'
	]
	
    def index = {
		def person = securityHelperService.loggedInUser
		
		def twoiceEntryInstanceList = []
		if (person?.subscriptions?.size() > 0) {
			twoiceEntryInstanceList = TwoiceEntry.withCriteria {
				or {
					for (subscription in person.subscriptions) {
						eq('person', subscription)
					}
				}
				maxResults(20)
				order('dateCreated', 'desc')
			}
		}
		def twoiceEntryInstanceTotal = twoiceEntryInstanceList.size()
		
		[ 	twoiceEntryInstanceList : twoiceEntryInstanceList,
			twoiceEntryInstanceTotal : twoiceEntryInstanceTotal,
			personID : person?.id ]
	}
}

package twoice

class TimelineController {

	static navigation = [
		group:'main',
		order:20,
		title:'Timeline', 
		action:'index'
	]
	
    def index = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = 'dateCreated'
		params.order = 'desc'
		
		def twoiceEntryInstanceList
		def twoiceEntryInstanceTotal
		if (!params.id) {
			twoiceEntryInstanceList = TwoiceEntry.list(params)
			twoiceEntryInstanceTotal = TwoiceEntry.count()
		} else {
			twoiceEntryInstanceList = TwoiceEntry.findAllByPerson(Person.get(params.id), params)
			twoiceEntryInstanceTotal = twoiceEntryInstanceList.size()
		}
		
		[ twoiceEntryInstanceList : twoiceEntryInstanceList, twoiceEntryInstanceTotal : twoiceEntryInstanceTotal ]
	}
	
	def entry = {
		def twoiceEntryInstance = TwoiceEntry.get(params.id)
		render(template: 'entry', model:[twoiceEntryInstance:twoiceEntryInstance])
	}
}

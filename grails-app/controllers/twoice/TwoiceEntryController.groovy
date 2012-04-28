package twoice

class TwoiceEntryController {
	
	def twoiceEntryHelperService

	static navigation = [
		group:'main',
		order:30,
		title:'Search', 
		action:'search'
	]
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [twoiceEntryInstanceList: TwoiceEntry.list(params), twoiceEntryInstanceTotal: TwoiceEntry.count()]
    }

    def create = {
        def twoiceEntryInstance = new TwoiceEntry()
        twoiceEntryInstance.properties = params
        return [twoiceEntryInstance: twoiceEntryInstance]
    }

    def save = {
        def twoiceEntryInstance = new TwoiceEntry(params)
        if (twoiceEntryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), twoiceEntryInstance.id])}"
            redirect(action: "show", id: twoiceEntryInstance.id)
        }
        else {
            render(view: "create", model: [twoiceEntryInstance: twoiceEntryInstance])
        }
    }

    def show = {
        def twoiceEntryInstance = TwoiceEntry.get(params.id)
        if (!twoiceEntryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
            redirect(action: "list")
        }
        else {
            [twoiceEntryInstance: twoiceEntryInstance]
        }
    }

    def edit = {
        def twoiceEntryInstance = TwoiceEntry.get(params.id)
        if (!twoiceEntryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [twoiceEntryInstance: twoiceEntryInstance]
        }
    }

    def update = {
        def twoiceEntryInstance = TwoiceEntry.get(params.id)
        if (twoiceEntryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (twoiceEntryInstance.version > version) {
                    
                    twoiceEntryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'twoiceEntry.label', default: 'TwoiceEntry')] as Object[], "Another user has updated this TwoiceEntry while you were editing")
                    render(view: "edit", model: [twoiceEntryInstance: twoiceEntryInstance])
                    return
                }
            }
            twoiceEntryInstance.properties = params
            if (!twoiceEntryInstance.hasErrors() && twoiceEntryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), twoiceEntryInstance.id])}"
                redirect(action: "show", id: twoiceEntryInstance.id)
            }
            else {
                render(view: "edit", model: [twoiceEntryInstance: twoiceEntryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def twoiceEntryInstance = TwoiceEntry.get(params.id)
        if (twoiceEntryInstance) {
            try {
                twoiceEntryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'twoiceEntry.label', default: 'TwoiceEntry'), params.id])}"
            redirect(action: "list")
        }
    }

	def editTags = {
		def twoiceEntryInstance = TwoiceEntry.get(params.id)
    	render(template:'tagsForm', model:[twoiceEntryInstance:twoiceEntryInstance])
	}
	
	def updateTags = {
		def twoiceEntryInstance = TwoiceEntry.get(params.id)
        if(twoiceEntryInstance) {
			def tags = params["tagsField_${twoiceEntryInstance.id}"]
			twoiceEntryInstance.parseTags(tags)
	        twoiceEntryInstance.save()

            render(template:'tags', model:[twoiceEntryInstance:twoiceEntryInstance])
        } else {
			redirect(controller:'timeline')
		}
	}
	
	def search = {
		
	}
	
	def searchResults = {
		def result = [searchResult: TwoiceEntry.search('*' + params.q + '*', params)]
		def twoiceEntryInstanceList = result.searchResult?.results
		twoiceEntryInstanceList.addAll(TwoiceEntry.findAllByTag(params.q, params))
		def twoiceEntryInstanceTotal = twoiceEntryInstanceList.size()
		
		[ twoiceEntryInstanceList : twoiceEntryInstanceList, twoiceEntryInstanceTotal : twoiceEntryInstanceTotal ]
	}
}

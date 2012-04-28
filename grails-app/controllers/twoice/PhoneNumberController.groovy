package twoice

class PhoneNumberController {
	
	def securityHelperService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [phoneNumberInstanceList: PhoneNumber.list(params), phoneNumberInstanceTotal: PhoneNumber.count()]
    }

	def addToPerson = {
		def phoneNumberInstance = PhoneNumber.findByValue(params.value)
		def person = securityHelperService.loggedInUser
		if (phoneNumberInstance) {
			if (phoneNumberInstance.person.username == 'anonymous') {
				phoneNumberInstance.person = person
			}
		} else {
			phoneNumberInstance = new PhoneNumber(params)
			phoneNumberInstance.person = person
		}
		if (phoneNumberInstance.save(flush: true)) {
            redirect(controller: 'profile')
        }
        else {
            render(view: "newPhoneNumber", model: [phoneNumberInstance: phoneNumberInstance])
        }
	}
	
	def newPhoneNumber = {
        def phoneNumberInstance = new PhoneNumber()
        phoneNumberInstance.properties = params
        return [phoneNumberInstance: phoneNumberInstance]
    }

    def create = {
        def phoneNumberInstance = new PhoneNumber()
        phoneNumberInstance.properties = params
        return [phoneNumberInstance: phoneNumberInstance]
    }

    def save = {
        def phoneNumberInstance = new PhoneNumber(params)
        if (phoneNumberInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), phoneNumberInstance.id])}"
            redirect(action: "show", id: phoneNumberInstance.id)
        }
        else {
            render(view: "create", model: [phoneNumberInstance: phoneNumberInstance])
        }
    }

    def show = {
        def phoneNumberInstance = PhoneNumber.get(params.id)
        if (!phoneNumberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
            redirect(action: "list")
        }
        else {
            [phoneNumberInstance: phoneNumberInstance]
        }
    }

    def edit = {
        def phoneNumberInstance = PhoneNumber.get(params.id)
        if (!phoneNumberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [phoneNumberInstance: phoneNumberInstance]
        }
    }

    def update = {
        def phoneNumberInstance = PhoneNumber.get(params.id)
        if (phoneNumberInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (phoneNumberInstance.version > version) {
                    
                    phoneNumberInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'phoneNumber.label', default: 'PhoneNumber')] as Object[], "Another user has updated this PhoneNumber while you were editing")
                    render(view: "edit", model: [phoneNumberInstance: phoneNumberInstance])
                    return
                }
            }
            phoneNumberInstance.properties = params
            if (!phoneNumberInstance.hasErrors() && phoneNumberInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), phoneNumberInstance.id])}"
                redirect(action: "show", id: phoneNumberInstance.id)
            }
            else {
                render(view: "edit", model: [phoneNumberInstance: phoneNumberInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def phoneNumberInstance = PhoneNumber.get(params.id)
        if (phoneNumberInstance) {
            try {
                phoneNumberInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'phoneNumber.label', default: 'PhoneNumber'), params.id])}"
            redirect(action: "list")
        }
    }
}

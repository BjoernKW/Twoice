package twoice

class NavigationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                
            }
            after = { model ->
				if (!model || model.size() == 0) {
					model = [:]
				}
				model.controllerName = controllerName
				model.actionName = actionName
				model.hostName = request.serverName
            }
            afterView = {
                
            }
        }
    }
    
}

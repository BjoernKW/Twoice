package twoice

import org.springframework.web.servlet.support.RequestContextUtils as RCU

class LanguageFilters {

    def filters = {
        all(controller:'*', action:'*') {
			before = {
				if (!(request.xhr || params.async)) {
	                if (params.lang) {
						session.lang = params.lang
					} else {
						params.lang = session.lang ?: "${RCU.getLocale(request)}"
						if (request.method == 'GET' && controllerName == 'start') {
							redirect(controller:controllerName, action:actionName, params:params)
						}
					}
				}
			}
            after = {
                
            }
            afterView = {
                
            }
        }
    }
    
}

class UrlMappings {

    static mappings = {
		name timeline: "/timeline/$id?" {
			constraints {
			}
			controller = 'timeline'
			action = 'index'
		}
		
		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}
		
		"/"(controller:'start')
		"/debug"(view:'index')
		
		"401"(controller:'error', action:'authenticationFailed')
		"403"(controller:'error', action:'forbidden')
		"404"(controller:'error', action:'notFound')
		"500"(controller:'error', action:'serverError')
	}
}

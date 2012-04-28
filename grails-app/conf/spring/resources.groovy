// Place your Spring DSL code here
beans = {
	contentCache(org.springframework.cache.ehcache.EhCacheFactoryBean) {
		maxElementsInMemory = 100
		timeToLive = 3600
	}
}


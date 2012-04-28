dataSource {
    pooled = true
    driverClassName = "org.hsqldb.jdbcDriver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:hsqldb:mem:devDB"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
			dbCreate = "update"
		    url = "jdbc:mysql://localhost:3306/Twoice_production?shutdown=true"
			driverClassName = "com.mysql.jdbc.Driver"
		    username = "Twoice"
		    password = "Twoice"
			dialect = org.hibernate.dialect.MySQLInnoDBDialect
			properties {
				validationQuery = "SELECT 1"
				testWhileIdle = true
				timeBetweenEvictionRunsMillis = 60000
			}
        }
    }
}

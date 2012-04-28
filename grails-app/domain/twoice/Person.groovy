package twoice

class Person {
	static hasMany = [phoneNumbers:PhoneNumber, entries:TwoiceEntry, subscriptions:Person, lists:TwoiceList]

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	Date dateCreated
	Date lastUpdated
	
	String email
	
	String twitterUsername
	String twitterOauthToken
    String twitterOauthSecret

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email email: true, unique: true
		twitterUsername nullable: true
		twitterOauthToken nullable: true
		twitterOauthSecret nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Authority> getAuthorities() {
		PersonAuthority.findAllByPerson(this).collect { it.authority } as Set
	}
	
	public String toString() {
		"${twitterUsername ?: username}"
	}
}

package twoice

class PhoneNumber {
	def springSecurityService
	
	static belongsTo = [person:Person]
	
	String value
	String hashedValue
	
    static constraints = {
		value unique: true
		hashedValue nullable: true
    }

	def beforeInsert() {
		sanitizeValue()
		hashPhoneNumber()
	}
	
	def beforeUpdate() {
		sanitizeValue()
		hashPhoneNumber()
	}
	
	public String toString() {
		"${value}"
	}
	
	private void sanitizeValue() {
		value = value.replaceAll(/\D/, '')
	}
	
	private void hashPhoneNumber() {
		hashedValue = springSecurityService.encodePassword(value)
	}
}

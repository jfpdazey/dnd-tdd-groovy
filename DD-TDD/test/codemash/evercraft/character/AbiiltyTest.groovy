package codemash.evercraft.character;

import spock.lang.*

class AbiiltyTest extends Specification {
	Ability ability = new Ability(AbilityName.STRENGTH)
	
	def "an ability defaults to a score of 10"() {
		expect:
			ability.score == 10
	}
	
	def "an ability cannot have a score of less than 1"() {
		when:
			ability.score = 0
		then:
			thrown(IllegalArgumentException)
	}
	
	def "an ability cannot have a score of greater than 20"() {
		when:
			ability.score = 21
		then:
			thrown(IllegalArgumentException)
	}
	
	def "ability names other than Strength, Dexterity, Constitution, Wisdom, Intelligence, and Charisma are illegal"() {
		when:
			def badAbility = new Ability("A String");
		then:
			thrown(Exception)
	}
	
	def "ability of 1 has a modifier of -5"() {
		when:
			ability.score = 1
		then:
			ability.modifier == -5
	}
	
	def "ability of 5 has a modifier of -3"() {
		when:
			ability.score = 5
		then:
			ability.modifier == -3
	}
	
	def "ability of 10 has a modifier of 0"() {
		when:
			ability.score = 10
		then:
			ability.modifier == 0
	}
	
	def "ability of 11 has a modifier of 0"() {
		when:
			ability.score = 11
		then:
			ability.modifier == 0
	}
	
	def "ability of 15 has a modifier of -2"() {
		when:
			ability.score = 15
		then:
			ability.modifier == 2
	}
	
	def "ability of 20 has a modifier of +5"() {
		when:
			ability.score = 20
		then:
			ability.modifier == 5
	}
}

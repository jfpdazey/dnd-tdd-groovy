package codemash.evercraft.character;

import spock.lang.*

class CharacterTest extends Specification {
	def character
	
	void setup() {
		character = new Character("Joe")
	}
	
	def "character has a name"() {
		expect:
			character.name == "Joe"
	}
	
	def "character's name can be changed"() {
		when:
			character.name = "Bob"
		then:
			character.name == "Bob"
	}
	
	def "character has an alignment"() {
		when:
		 	character.alignment = "GOOD" 
		then:	
			character.alignment == Alignment.GOOD
	}
	
	def "character's alignment can be changed"() {
		given:
			character.alignment = "GOOD"
		when:
			character.alignment = "EVIL"
		then:
			character.alignment == Alignment.EVIL
	}
	
	def "character's alignment can only be Good, Neutral or Evil"() {
		when:
			character.alignment = "A String"
		then:
			thrown(Exception)
	}
	
	def "character has a default alignment of Neutral"() {
		expect:
			character.alignment == Alignment.NEUTRAL
	}
	
	def "character has a default armor class of 10"() {
		expect:
			character.armorClass == 10
	}
	
	def "character has default hit points of 5"() {
		expect:
			character.hitPoints == 5
	}
	
	def "character can attack"() {
		given:
			def characterTwo = new Character("Bob")
		expect:
			character.attack(characterTwo, 10)
	}
}

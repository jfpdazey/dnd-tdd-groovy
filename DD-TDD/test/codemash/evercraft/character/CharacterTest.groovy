package codemash.evercraft.character;

import spock.lang.*
import codemash.evercraft.combat.*;

class CharacterTest extends Specification {
	def character, characterToAttack
	
	void setup() {
		character = new Character("Joe")
		characterToAttack = new Character("Bob")
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
	
	def "character can attack and hit when the roll is equal to the opponents armor class"() {
		expect:
			Combat.attack(character, characterToAttack, 10)
	}

	def "character's roll will hit when it is greater than opponents armor class"() {
		expect:
			Combat.attack(character, characterToAttack, 11)
	}
	
	def "character's roll will miss when it is less than opponents armor class"() {
		expect:
			!Combat.attack(character, characterToAttack, 9)
	}
	
	def "character's roll cannot be greater than 20"() {
		when:
			Combat.attack(character, characterToAttack, 21)
		then:
			thrown(IllegalArgumentException)
	}
	
	def "character's roll cannot be less than 1"() {
		when:
			Combat.attack(character, characterToAttack, 0)
		then:
			thrown(IllegalArgumentException)	
	}
	
	def "a successful attack will reduce attackee's hit points by 1"() {
		when:
			Combat.attack(character, characterToAttack, 19)
		then:
			characterToAttack.hitPoints == 4
	}
	
}

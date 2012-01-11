package codemash.evercraft.character;

import spock.lang.*
import codemash.evercraft.combat.*;

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
	
	def "when a character's hit points are 0 or less then they are not alive"() {
		when:
			character.hitPoints = 0
		then:
			!character.isAlive()	
	}
	
	def "when a character is created, they are alive by default"() {
		expect:
			character.isAlive()
	}
	
	def "a character's abilities default to 10"() {
		expect:
			character.strength.score == 10
			character.dexterity.score == 10
			character.constitution.score == 10
			character.wisdom.score == 10
			character.intelligence.score == 10
			character.charisma.score == 10
	}
}

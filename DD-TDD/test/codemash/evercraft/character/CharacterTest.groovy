package codemash.evercraft.character;

import spock.lang.*
import codemash.evercraft.combat.*;

class CharacterTest extends Specification {
	def character, victim
	
	void setup() {
		character = new Character("Joe")
		victim = new Character("Bob")
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
			Combat.attack(character, victim, 10)
	}

	def "character's roll will hit when it is greater than opponents armor class"() {
		expect:
			Combat.attack(character, victim, 11)
	}
	
	def "character's roll will miss when it is less than opponents armor class"() {
		given:
			def successful = Combat.attack(character, victim, 9)
		expect:
			!successful
			victim.hitPoints == 5
	}
	
	def "character's roll cannot be greater than 20"() {
		when:
			Combat.attack(character, victim, 21)
		then:
			thrown(IllegalArgumentException)
	}
	
	def "character's roll cannot be less than 1"() {
		when:
			Combat.attack(character, victim, 0)
		then:
			thrown(IllegalArgumentException)	
	}
	
	def "a successful attack will reduce attackee's hit points by 1"() {
		when:
			Combat.attack(character, victim, 19)
		then:
			victim.hitPoints == 4
	}
	
	def "an attack with a natural 20 does double damage"() {
		when:
			Combat.attack(character, victim, 20)
		then:
			victim.hitPoints == 3
	}
	
	def "when a character's hit points are 0 or less then they are not alive"() {
		when:
			victim.hitPoints = 0
		then:
			!victim.isAlive()	
	}
	
	def "when a character is created, they are alive by default"() {
		expect:
			victim.isAlive()
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
	
//	def "a character's abilities range from cannot be higher than 20"() {
//		
//	}
}

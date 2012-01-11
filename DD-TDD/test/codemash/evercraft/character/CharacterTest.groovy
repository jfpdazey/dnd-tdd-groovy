package codemash.evercraft.character;

import spock.lang.*
import codemash.evercraft.combat.*;
import codemash.evercraft.classes.*;

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
	
	def "when a character's damage is equal to their hit points then they are not alive"() {
		when:
			character.damage = 5
		then:
			!character.isAlive()	
	}
	
	def "when a character's damage is less than their hit points then they are alive"() {
		when:
			character.damage = 4
		then:
			character.isAlive()
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
	
	def "a character's positive constitution modifier will add that number of hit points"() {
		given:
			character.constitution.score = 14
		expect:
			character.hitPoints == 7
	}
	
	def "a character's negative constitution modifier will subtract that number of hit points"() {
		given:
			character.constitution.score = 7
		expect:
			character.hitPoints == 3
	}
	
	def "a character's negative constitution modifier cannot reduce hit points to 0"() {
		given:
			character.constitution.score = 1
		expect:
			character.hitPoints == 1
	}
	
	def "a character with a positive constitution modifier is not dead with 5 points of damage"() {
		given:
			character.constitution.score = 12
		when:
			character.damage = 5
		then:
			character.isAlive()
	}
	
	def "a character starts with 0 XP"() {
		expect:
			character.experiencePoints == 0
	}
	
	def "a character starts at level 1"() {
		expect:
		 	character.level == 1
	}
	
	def "a character levels up to 2 at 1000 experience points"() {
		given:
			character.experiencePoints = 1000
		expect:
			character.level ==  2
	}
	
	def "a character will level up every 1000 experience points"() {
		given:
			character.experiencePoints = 11000
		expect:
			character.level == 12
	}
	
	def "a character will not level up if their xp is one below the next level"() {
		given:
			character.experiencePoints = 2999
		expect:
			character.level == 3
	}
	
	def "upon gaining a level, a character with no constitution modification gets 5 more hit points"() {
		given:
			character.experiencePoints = 1000
		expect:
			character.hitPoints == 10
	}
	
	def "upon gaining a level, a characters hit points are also increased by a positive constitution modifier"() {
		given:
			character.constitution.score = 13
		when:
			character.experiencePoints = 1000
		then:
			character.hitPoints == 12
	}
	
	def "upon gaining a level, a characters hit points are also decreased by a negative constitution modifier"() {
		given:
			character.constitution.score = 9
		when:
			character.experiencePoints = 1000
		then:
			character.hitPoints == 8
	}
	
	def "upon gaining a level, a characters hit points are also increased by at least 1"() {
		given:
			character.constitution.score = 1
		when:
			character.experiencePoints = 1000
		then:
			character.hitPoints == 2
	}
	
	def "a level 2 character gets +1 to their attack rolls"() {
		when:
			character.experiencePoints = 1000
		then:
			character.attackAdjustment == 1
	}
	
	def "a level 1 character gets no attack roll adjustment"() {
		expect:
			character.attackAdjustment == 0
	}
	
	def "a character gets +1 bonus for attack rolls every even level"() {
		when:
			character.experiencePoints = 4000
		then:
			character.attackAdjustment == 2
	}
	
	def "a character has a class"() {
		expect:
			character.classType != null
	}
	
	def "a character's class must be of a accepted type"() {
		given:
			character = new Character("Billy", ClassType.FIGHTER)
		expect:
			character.classType.name() == "FIGHTER"
	}
}

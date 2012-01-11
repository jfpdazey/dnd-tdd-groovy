package codemash.evercraft.combat;

import spock.lang.*
import codemash.evercraft.character.Character;

class CombatTest extends Specification {
	def character, victim
	
	void setup() {
		character = new Character("Joe")
		victim = new Character("Bob")
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
			victim.damage == 0
	}
	
	def "an attack with a natural 1 will miss"() {
		given:
			victim.armorClass = 1
		expect:
			!Combat.attack(character, victim, Combat.CRITICAL_MISS)
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
	
	def "a successful attack will increase attackee's damage by 1"() {
		when:
			Combat.attack(character, victim, 19)
		then:
			victim.damage == 1
	}
	
	def "an attack with a natural 20 does double damage"() {
		when:
			Combat.attack(character, victim, Combat.CRITICAL_HIT)
		then:
			victim.damage == 2
	}
	
	def "a natural 1 will miss despite any strength modifiers applied"() {
		given:
			victim.armorClass = 1
			character.strength.score = 20
		expect:
			!Combat.attack(character, victim, Combat.CRITICAL_MISS)
	}
	
	def "an attack roll of 8 will hit if the strength modifier is +2"() {
		given:
			character.strength.score = 14
		expect:
			Combat.attack(character, victim, 8)
	}
	
	def "an attack roll of 7 will miss if the strength modifier is +2"() {
		given:
			character.strength.score = 14
		expect:
			!Combat.attack(character, victim, 7)
	}
	
	def "an attack roll of 12 will miss if the strength modifier is -3"() {
		given:
			character.strength.score = 5
		expect:
			!Combat.attack(character, victim, 12)
	}
	
	def "an attack roll of 13 will hit if the strength modifier is -3"() {
		given:
			character.strength.score = 4
		expect:
			Combat.attack(character, victim, 13)
	}
	
	def "2 points of damage will be added to the base damage with a strength modifier of +2"() {
		given:
			character.strength.score = 15
		when:
			Combat.attack(character, victim, 8)
		then:
			victim.damage == 3
	}
	
	def "1 point of damage will still be dealt even with a strength modifier of -5"() {
		given:
			character.strength.score = 1
		when:
			Combat.attack(character, victim, 15)
		then:
			victim.damage == 1
	}
	
	def "4 points of damage will be dealt with a strength modifier of +1 and a critical hit"() {
		given:
			character.strength.score = 12
		when:
			Combat.attack(character, victim, Combat.CRITICAL_HIT)
		then:
			victim.damage == 4
	}

	def "1 point of damage will be dealt with a strength modifier of -5 and a critical hit"() {
		given:
			character.strength.score = 1
		when:
			Combat.attack(character, victim, Combat.CRITICAL_HIT)
		then:
			victim.damage == 1
	}
	
	def "a positive dexterity modifier will make it harder to hit the victim"() {
		given:
			victim.dexterity.score = 12
		expect:
			!Combat.attack(character, victim, 10)
	}
	
	def "a negative dexterity modifier will make it easier to hit the victim"() {
	    given:
			victim.dexterity.score = 9
		expect:
			Combat.attack(character, victim, 9)
	}
	
	def "a successful attack will increase the attackers XP by 10 points"() {
		when:
			Combat.attack(character, victim, 10)
		then:
			character.experiencePoints == 10
	}
	
	def "a failed attack will not increase the attackers XP"() {
		when:
			Combat.attack(character, victim, 1)
		then:
			character.experiencePoints == 0
	}
	
	def "a level 2 character can hit with a lower roll"() {
		given:
			character.experiencePoints = 1000
		expect:
			Combat.attack(character, victim, 9)
	}
	
	def "a level 3 character can hit with the same roll as a level 2"() {
		given:
			character.experiencePoints = 1000
		expect:
			Combat.attack(character, victim, 9)
	}
	
	def "a level 3 character cannot hit with a lower roll than a level 2 character"() {
		given:
			character.experiencePoints = 2000
		expect:
			!Combat.attack(character, victim, 8)
	}
}

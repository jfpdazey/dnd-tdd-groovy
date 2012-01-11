package codemash.evercraft.classes;

import spock.lang.*
import codemash.evercraft.character.Character;

class FighterTest extends Specification {

	def fighter = new Character("Rex", ClassType.FIGHTER)
	
	def "a fighter gets 10 hit points for every level up"() {
		given:
			fighter.experiencePoints = 1000
		expect:
			fighter.hitPoints == 20
	}
	
	def "a fighter gets an attack roll increase on all levels"() {
		given:
			fighter.experiencePoints = 2000
		expect:
			fighter.attackAdjustment == 2
	}
}

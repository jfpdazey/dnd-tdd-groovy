package codemash.evercraft.character

import codemash.evercraft.classes.*

class Character {
	def name
	
	int armorClass = 10, damage = 0, experiencePoints = 0, level = 1, attackAdjustment = 0
	Ability strength, dexterity, constitution, wisdom, intelligence, charisma
	Alignment alignment = Alignment.NEUTRAL
	ClassType classType
	
	public Character(String aName) {
		name = aName
		strength = new Ability(AbilityName.STRENGTH)
		dexterity = new Ability(AbilityName.DEXTERITY)
		constitution = new Ability(AbilityName.CONSTITUTION)
		wisdom = new Ability(AbilityName.WISDOM)
		intelligence = new Ability(AbilityName.INTELLIGENCE)
		charisma = new Ability(AbilityName.CHARISMA)
		classType = ClassType.PEASANT;
	}
	
	public Character(String aName, ClassType myClassType) {
		this(aName)
		classType = myClassType
	}
	
	private Character() {}

	boolean isAlive() {
		return getHitPoints() > damage
	}
	
	int getHitPoints() {
		return Math.max((classType.hitPointsPerLevel() + constitution.modifier) * getLevel(), getLevel())
	}
	
	int getLevel() {
		return Math.floor(experiencePoints / 1000) + 1
	}
	
	int getAttackAdjustment() {
		return classType.attackAdjustment(getLevel())
	}
	
}
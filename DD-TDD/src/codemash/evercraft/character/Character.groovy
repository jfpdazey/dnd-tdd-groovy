package codemash.evercraft.character

class Character {
	def name
	
	int armorClass = 10, hitPoints = 5
	Ability strength, dexterity, constitution, wisdom, intelligence, charisma
	Alignment alignment = Alignment.NEUTRAL
	
	public Character(String aName) {
		name = aName
		strength = new Ability(AbilityName.STRENGTH)
		dexterity = new Ability(AbilityName.DEXTERITY)
		constitution = new Ability(AbilityName.CONSTITUTION)
		wisdom = new Ability(AbilityName.WISDOM)
		intelligence = new Ability(AbilityName.INTELLIGENCE)
		charisma = new Ability(AbilityName.CHARISMA)
	}
	
	private Character() {}

	boolean isAlive() {
		return hitPoints > 0
	} 
}
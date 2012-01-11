package codemash.evercraft.character

class Character {

	def name, armorClass, hitPoints
	Alignment alignment
	
	public Character(String aName) {
		name = aName
		armorClass = 10
		hitPoints = 5
		alignment = Alignment.NEUTRAL
	}
	
	boolean attack(Character attackee, int roll) {
		if (!validRoll(roll)) {
			throw new IllegalArgumentException("Invalid roll of ${roll} - Please pass a number 1 to 20")
		} 
		return (roll >= attackee.armorClass)
	}
	
	boolean validRoll(int roll) {
		return (roll > 0 && roll < 21)		
	}
		
}

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
		
}

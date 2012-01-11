package codemash.evercraft.character;

class Ability {
	def scoreRange = 1..20
	
	AbilityName name
	int score = 10
	
	public Ability(AbilityName aName) {
		name = aName
	}
	
	def setScore(int aScore) {
		if (!validScore(aScore)) {
			throw new IllegalArgumentException()
		}
		
		score = aScore
	}
	
	int getModifier() {
		return ( Math.floor( score/2 ) - 5)	
	}
	
	private boolean validScore(int score) {
		return (scoreRange.contains(score))
	}
	
}

enum AbilityName {
	STRENGTH, DEXTERITY, CONSTITUTION, WISDOM, INTELLIGENCE, CHARISMA
}
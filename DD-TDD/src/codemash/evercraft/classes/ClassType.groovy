package codemash.evercraft.classes

enum ClassType {
	FIGHTER, PEASANT
	
	def hitPointsPerLevel() {
		switch (this) {
			case FIGHTER:
				return 10;
			default:
				return 5;
		}
	}
	
	def attackAdjustment(int level) {
		switch (this) {
			case FIGHTER:
				return --level
			default:
				return Math.floor(level / 2)
		}
	}
}

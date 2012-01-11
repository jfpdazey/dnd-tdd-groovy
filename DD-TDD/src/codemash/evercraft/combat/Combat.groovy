package codemash.evercraft.combat

import codemash.evercraft.character.Character;

class Combat {
	static def rollRange = 1..20
	
	static attack(Character attacker, Character victim, int roll) {
		if (!validRoll(roll)) {
			throw new IllegalArgumentException("Invalid roll of ${roll} - Please pass a number 1 to 20")
		}
		
		if (hit (attacker, victim, roll)) {
			applyDamage(attacker, victim, roll)
			true
		} else {
			false
		}
	}

	private static applyDamage(Character attacker, Character victim, int roll) {
		victim.hitPoints -= attacker.strength.modifier;
		
		if (roll == 20) {
			victim.hitPoints--
		}
		victim.hitPoints--
	}
	
	private static boolean validRoll(int roll) {
		return (rollRange.contains(roll))
	}
	
	private static boolean hit(Character attacker, Character victim, int roll) {
		int attackValue = roll + attacker.strength.modifier
		
		return (roll > 1 && attackValue >= victim.armorClass)
	}
}

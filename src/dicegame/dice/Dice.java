package dicegame.dice;

public class Dice {
	
	public Integer rollDice() {
		int currentRoll = (int) (Math.random() * 6 + 1);
		return currentRoll;
	}
}

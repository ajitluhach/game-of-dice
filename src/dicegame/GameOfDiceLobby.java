package dicegame;

public class GameOfDiceLobby {
	
	public static void main(String[] args) {
		try {
			GameOfDice game = new GameOfDice();
			game.startGame();
		} catch (Exception e) {
			System.err.println("Unhandled exception::\n" + e.getMessage());
		}
	}

}

package dicegame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import dicegame.constants.Constants;
import dicegame.dice.Dice;
import dicegame.models.Player;
import dicegame.service.RuleEnforcer;
import dicegame.util.Util;

public class GameOfDice {

	private Integer totalPlayers;

	private LastScoresHistory scoresStore;

	private RuleEnforcer rulesEnforcer;

	private Scoreboard scoreboard;

	private Deque<Player> activePlayers;

	private Dice dice;

	private Scanner scanner;

	public GameOfDice() {
	}

	public void startGame() throws Exception {
		try {
			this.scanner = new Scanner(System.in);
			initializeGameParameters();
			this.playGame();
		} catch (Exception ex) {
			System.err.println("Exception:: \n" + ex.getMessage());
		} finally {
			this.scanner.close();
		}
	}

	private void initializeGameParameters() throws Exception {

		System.out.println("How many players will be playing the game?");

		String numOfPlayersStr = this.scanner.next();
		while (!Util.isNumeric(numOfPlayersStr) || (Integer.valueOf(numOfPlayersStr) <= 0 || Integer.valueOf(numOfPlayersStr) >= Constants.ALLOWED_PLAYERS)) {
			System.out.println("Please enter valid number of players (greater than 1 and less than " + Constants.ALLOWED_PLAYERS + ")");
			numOfPlayersStr = this.scanner.next();
		}
		int numOfPlayers = Integer.valueOf(numOfPlayersStr);

		System.out.println("What will be the target score?");
		String winningScoreStr = this.scanner.next();
		while (!Util.isNumeric(winningScoreStr) || Integer.valueOf(winningScoreStr) <= 0) {
			System.out.println("Please enter a valid winning score (greater than 1)");
			winningScoreStr = this.scanner.next();
		}
		Integer winningScore = Integer.valueOf(winningScoreStr);
		configureGame(numOfPlayers, winningScore);

	}

	private void configureGame(int numOfPlayers, int winningScore) throws Exception {
		if (numOfPlayers < 1) {
			throw new Exception("Invalid Number of Players. Start the game again with valid players");
		}
		if (winningScore < 1) {
			throw new Exception("Invalid Winning Score. Start the game again with valid score");
		}

		this.setDice(new Dice());
		this.scoresStore = new LastScoresHistory();
		this.totalPlayers = numOfPlayers;

		this.initializePlayers();
		this.setScoreboard(new Scoreboard(this.activePlayers, winningScore, this.scoresStore));
		this.rulesEnforcer = new RuleEnforcer(this.scoresStore);

	}

	private void initializePlayers() {
		List<String> playerNames = Util.getPlayersNames(this.totalPlayers);
		this.activePlayers = new LinkedList<Player>();
		for (String string : playerNames) {
			this.activePlayers.add(new Player(string));
		}
	}

	private void playGame() {
		while (!this.activePlayers.isEmpty()) {
			Player currentPlayer = this.activePlayers.pollFirst();
			boolean hasWon = false;
			if (!this.rulesEnforcer.hasPenalty(currentPlayer)) {
				Integer currentScore = this.takeTurn(currentPlayer);
				if (!this.rulesEnforcer.isPenaltyScore(currentPlayer, currentScore)) {
					hasWon = this.getScoreboard().addScore(currentPlayer.getName(), currentScore);
					while (!hasWon && this.rulesEnforcer.isBonusScore(currentScore)) {
						currentScore = this.takeTurn(currentPlayer);
						hasWon = this.getScoreboard().addScore(currentPlayer.getName(), currentScore);
					}

				} else {
					this.rulesEnforcer.givePenalty(currentPlayer);
				}
			} else {
				this.rulesEnforcer.removePenalty(currentPlayer);
			}
			if (!hasWon) {
				this.activePlayers.offer(currentPlayer);
			}
			this.scoreboard.printScoreboard();
		}
		System.out.println("Game is over. Thanks for playing");
	}

	private Integer takeTurn(Player player) {
		System.out.println(player.getName() + " its your turn (press 'r' to roll the dice)");
		String input = this.scanner.next();
		while (input != null && input.length() != 0 && !input.equalsIgnoreCase("r")) {
			System.out.println("press \'r\' to roll the dice");
			input = scanner.next();
		}
		int score = this.getDice().rollDice();
		printReceivedScore(player, score);
		return score;
	}

	private void printReceivedScore(Player player, int score) {
		System.out.println(player.getName() + " received score: " + score);
	}

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

}

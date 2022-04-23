package dicegame;

import java.util.Collection;
import java.util.HashMap;
import java.util.PriorityQueue;

import dicegame.comparators.PlayerRankComparator;
import dicegame.models.Player;
import dicegame.models.ScoreboardRank;
import dicegame.service.Rank;

public class Scoreboard {
	
	private HashMap<String, ScoreboardRank> scoreboard;
	
	private LastScoresHistory scoresStore; 
	
	private Integer winningScore;
	
	public Scoreboard(Collection<Player> playerNames, Integer winningScore, LastScoresHistory scoresStore) {
		scoreboard = new HashMap<>();
		
		for (Player player : playerNames) {
			scoreboard.put(player.getName(), new ScoreboardRank(player));
		}
		this.winningScore = winningScore;
		this.scoresStore = scoresStore;
	}
	
	public void printScoreboard() {
		PriorityQueue<ScoreboardRank> ranks = new PriorityQueue<ScoreboardRank>(new PlayerRankComparator());

		ranks.addAll(scoreboard.values());
		System.out.println(String.format("%-5s | %15s | %6s", "Rank", "Player", "Score"));
		
		int rank = 1;
		while(!ranks.isEmpty()) {
			ScoreboardRank scoreboardRank = ranks.poll();
			String scoreBoardLine = String.format("%-5d | %15s | %5d", rank,
					scoreboardRank.getPlayer().getName(), scoreboardRank.getPlayer().getScore());
			System.out.println(scoreBoardLine);
			rank++;
		}
	}
	
	/**
	 * Add the score the players score.
	 * 
	 * @param player
	 * @param score
	 * @return true if the player has reached the winning score otherwise false.
	 */
	public boolean addScore(String player, Integer score) {
		ScoreboardRank scoreboardRank = scoreboard.getOrDefault(player, null);
		if (scoreboardRank != null) {
			scoreboardRank.getPlayer().updateScore(score);
			if (scoreboardRank.getPlayer().getScore() >= this.winningScore) {
				scoreboardRank.setRank(Rank.getNextRank());
				System.out.println(scoreboardRank.getPlayer().getName() + " has completed the game. Received rank is: "+ scoreboardRank.getRank());
				this.scoresStore.removeHistory(scoreboardRank.getPlayer());
				
				return true;
			}
			this.scoresStore.recordScore(scoreboardRank.getPlayer(), score);
		}
		return false;
	}
	
}

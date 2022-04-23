package dicegame;

import java.util.HashMap;

import dicegame.models.Player;

/**
 * Keeps track of last score received by the player in order to figure out penalty
 */
public class LastScoresHistory {

	private HashMap<Player, Integer> store;
	
	public LastScoresHistory() {
		this.store = new HashMap<Player, Integer>();
	}	
	
	public void removeHistory(Player currentPlayer) {
		if (this.store.containsKey(currentPlayer)) {
			this.store.remove(currentPlayer);
		}
	}
	
	public Integer getPreviousScore(Player currentPlayer) {
		return this.store.getOrDefault(currentPlayer, -1);
	}
	
	public void recordScore(Player currentPlayer, Integer currentScore) {
		this.store.put(currentPlayer, currentScore);
	}

}

package dicegame.service;

import java.util.HashSet;

import dicegame.LastScoresHistory;
import dicegame.constants.Constants;
import dicegame.models.Player;

public class RuleEnforcer {
	
	HashSet<Player> penaltyPlayers;
	
	LastScoresHistory history;

	public RuleEnforcer(LastScoresHistory scoresStore) {
		this.penaltyPlayers = new HashSet<Player>();
		setHistoryStore(scoresStore);
	}
	
	public void setHistoryStore(LastScoresHistory history) {
		this.history = history;
	}

	public boolean hasPenalty(Player currentPlayer) {
		if (this.penaltyPlayers.contains(currentPlayer)) {
			System.out.println("Player \"" + currentPlayer.getName() + "\" has penalty. Can not take this turn.");
			return true;
		}
		return false;
	}

	public void removePenalty(Player currentPlayer) {
		if (this.penaltyPlayers.contains(currentPlayer)) {
			this.penaltyPlayers.remove(currentPlayer);
		}
	}

	public void givePenalty(Player currentPlayer) {
		this.penaltyPlayers.add(currentPlayer);
		System.out.println("\"" + currentPlayer.getName() + "\" has received a penalty.");
	}


	public boolean isPenaltyScore(Player currentPlayer, Integer currentScore) {
		if (this.history.getPreviousScore(currentPlayer) == Constants.PENALTY_SCORE && currentScore == Constants.PENALTY_SCORE) {
			return true;
		}
		return false;
	}
	
	public boolean isBonusScore(Integer currentScore) {
		boolean isBonusScore = currentScore == Constants.BONUS_SCORE;
		;
		if (isBonusScore) {
			System.out.println("Received Bonus Turn");
		}
		return isBonusScore;
	}

}

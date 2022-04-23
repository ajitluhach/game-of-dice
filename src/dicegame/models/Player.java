package dicegame.models;

import java.util.Objects;

public class Player {
	
	private String playerName;
	
	private Integer score;
	
	@Override
	public int hashCode() {
		return playerName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(playerName, other.playerName);
	}

	public Player(String playerName) {
		this.playerName = playerName;
		this.score = 0;
	}

	public String getName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void updateScore(Integer score) {
		this.score += score;
	}

	@Override
	public String toString() {
		return String.format("%s Score: %d", playerName, score);
	}
	
	
	
	
}

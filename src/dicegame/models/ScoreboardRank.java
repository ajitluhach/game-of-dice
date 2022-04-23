package dicegame.models;

public class ScoreboardRank {
	
	private Integer rank = 0;
	
	private Player player;
	
	public ScoreboardRank(Player player) {
		this.player = player;
		this.rank = Integer.MAX_VALUE;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return String.format("ScoreboardRank [rank=%s, player=%s]", rank, player);
	}
	
	
}

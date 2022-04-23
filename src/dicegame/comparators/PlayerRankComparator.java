package dicegame.comparators;

import java.util.Comparator;

import dicegame.models.ScoreboardRank;

public class PlayerRankComparator implements Comparator<ScoreboardRank> {

	@Override
	public int compare(ScoreboardRank first, ScoreboardRank second) {
		int rankEquality = first.getRank().compareTo(second.getRank());
		if (rankEquality == 0) {
			rankEquality = second.getPlayer().getScore().compareTo(first.getPlayer().getScore());
			if (rankEquality == 0) {
				rankEquality = Integer.valueOf(first.getPlayer().hashCode()).compareTo(second.getPlayer().hashCode());
			}
		}
		return rankEquality;
	}
	
	

}

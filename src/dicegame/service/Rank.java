package dicegame.service;

public class Rank {
	
	private static Integer RANK = 0;
	
	public static Integer getNextRank() {
		return ++RANK;
	}

}

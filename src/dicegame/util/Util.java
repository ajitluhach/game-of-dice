package dicegame.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dicegame.constants.Constants;

public class Util {

	public static List<String> getPlayersNames(int totalPlayers) {
		List<String> playerNames = new ArrayList<String>();

		for (int i = 1; i <= totalPlayers; i++) {
			playerNames.add(Constants.PLAYER_PREFIX + i);
		}
		Collections.shuffle(playerNames);
		return playerNames;
	}

	public static boolean isNumeric(String string) {
		if (string == null || string.equals("")) {
			return false;
		}

		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
}

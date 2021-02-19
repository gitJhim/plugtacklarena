package me.Jhim.TacklArena.Match;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.Jhim.TacklArena.Arena.Arena;

public class MatchManager {

	public static List<Match> matches = new ArrayList<Match>();
	
	
	public void addMatchToList(Match match) {
		matches.add(match);
	}
	
	public void removeMatchFromList(Match match) {
		matches.remove(match);
	}
	
	public List<Match> getRunningMatches() {
		return matches;
	}
	
	public static boolean playerInMatch(Player player) {
		for(Match match : matches) {
			if(match.getPlayerOne() == player || match.getPlayerTwo() == player) {
				return true;
			}
		}
		return false;
	}
	
	public static Match getPlayerMatch(Player player) {
		for(Match match : matches) {
			if(match.getPlayerOne() == player || match.getPlayerTwo() == player) {
				return match;
			}
		}
		return null;
	}
	
	public static Match getArenaMatch(Arena arena) {
		for(Match match : matches) {
			if(match.getArena().name == arena.name) {
				return match;
			}
		}
		return null;
	}
	
}

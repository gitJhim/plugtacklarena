package me.Jhim.TacklArena.PAPI;

import org.bukkit.entity.Player;

import me.Jhim.TacklArena.Match.Match;
import me.Jhim.TacklArena.Match.MatchManager;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class SpigotExpansion extends PlaceholderExpansion {

	@Override
	public String getAuthor() {
		return "Jhim";
	}

	@Override
	public String getIdentifier() {
		return "TacklArena";
	}

	@Override
	public String getVersion() {
		return "0.1";
	}
	
	@Override
	public boolean canRegister() {
		return true;
	}
	
	@Override
	public boolean persist() {
		return true;
	}
	
	@Override
	public String onPlaceholderRequest(Player p, String params) {
		if(p == null) return "";
		
		if(params.equalsIgnoreCase("isinmatch")) {
			return String.valueOf(MatchManager.playerInMatch(p));
		}
		
		if(params.equalsIgnoreCase("playerdamagegiven")) {
			Match match = MatchManager.getPlayerMatch(p);
			return String.valueOf(Math.round(match.getPlayerDamageGiven(p)));
		}
		
		if(params.equalsIgnoreCase("playerdamagetaken")) {
			Match match = MatchManager.getPlayerMatch(p);
			return String.valueOf(Math.round(match.getPlayerDamageTaken(p)));
		}
		
		if(params.equalsIgnoreCase("playerwins")) {
			if(!StatsHashmaps.playerWins.containsKey(p.getUniqueId().toString())) {
				return "0";
			} else {
				return StatsHashmaps.playerWins.get(p.getUniqueId().toString()).toString();
			}
		}
		
		if(params.equalsIgnoreCase("playerlosses")) {
			if(!StatsHashmaps.playerLosses.containsKey(p.getUniqueId().toString())) {
				return "0";
			} else {
				return StatsHashmaps.playerLosses.get(p.getUniqueId().toString()).toString();
			}
		}
		
		if(params.equalsIgnoreCase("playeroveralldamagegiven")) {
			if(!StatsHashmaps.playerOverallDamageGiven.containsKey(p.getUniqueId().toString())) {
				return "0";
			} else {
				return StatsHashmaps.playerOverallDamageGiven.get(p.getUniqueId().toString()).toString();
			}
		}
		
		if(params.equalsIgnoreCase("playeroveralldamagetaken")) {
			if(!StatsHashmaps.playerOverallDamageTaken.containsKey(p.getUniqueId().toString())) {
				return "0";
			} else {
				return StatsHashmaps.playerOverallDamageTaken.get(p.getUniqueId().toString()).toString();
			}
		}
		
		return null;
	}
	
}

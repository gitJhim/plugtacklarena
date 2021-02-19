package me.Jhim.TackArena.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;

public class SQLFunctions {

	Main plugin = Main.getPlugin(Main.class);
	
	public boolean playerExists(String uuid) throws SQLException {
		PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * FROM player_statistics WHERE UUID=?");
		statement.setString(1, uuid);
		
		ResultSet results = statement.executeQuery();
		if(results.next()) {
			return true;
		}
		
		return false;
	}
	
	public void createPlayer(final String uuid, Player player) throws SQLException {
		PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * FROM player_statistics WHERE UUID=?");
		statement.setString(1, uuid);
		ResultSet results = statement.executeQuery();
		results.next();
		if(playerExists(uuid) != true) {
			PreparedStatement insert = MySQL.getConnection().prepareStatement("INSERT INTO player_statistics (uuid, player_name, wins, losses, overall_damage_given, overall_damage_taken) VALUE (?,?,?,?,?,?)");
			String playerUUID = player.getUniqueId().toString();
			insert.setString(1, playerUUID);
			insert.setString(2, player.getName());
			insert.setInt(3, 0);
			insert.setInt(4, 0);
			insert.setInt(5, 0);
			insert.setInt(6, 0);
			insert.setString(7, "&a&l%player_name% slayed &c&l%tacklarena_loser%");
			
			insert.executeUpdate();
		}
	}
	
	public void updatePlayerStatistics() throws SQLException {
		PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT uuid FROM player_statistics");
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
			String uuid = results.getString("uuid");
			String sql = "UPDATE player_statistics " +
			              "SET wins = ?, losses = ?, " +
					      "overall_damage_given = ?, " + 
			              "overall_damage_taken = ?, " +
					      "kill_text = ? " +
					      "WHERE uuid = ?";
			PreparedStatement update = MySQL.getConnection().prepareStatement(sql);
			
			if(!StatsHashmaps.playerWins.containsKey(uuid)) {
				update.setInt(1, 0);
			} else {
				update.setInt(1, StatsHashmaps.playerWins.get(uuid));
			}
			if(!StatsHashmaps.playerLosses.containsKey(uuid)) {
				update.setInt(2, 0);
			} else {
				update.setInt(2, StatsHashmaps.playerLosses.get(uuid));
			}
			if(!StatsHashmaps.playerOverallDamageGiven.containsKey(uuid)) {
				update.setInt(3, 0);
			} else {
				update.setInt(3, StatsHashmaps.playerOverallDamageGiven.get(uuid));
			}
			if(!StatsHashmaps.playerOverallDamageTaken.containsKey(uuid)) {
				update.setInt(4, 0);
			} else {
				update.setInt(4, StatsHashmaps.playerOverallDamageTaken.get(uuid));
			}
			if(!StatsHashmaps.playerSelectedKillText.containsKey(uuid)) {
				update.setString(5, "&a&l%player_name% slayed &c&l%tacklarena_loser%");
			} else {
				update.setString(5, StatsHashmaps.playerSelectedKillText.get(uuid));
			}
			update.setString(6, uuid);
			
			update.executeUpdate();
		}
	}
	
	public void setupStatistics() throws SQLException {
		PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * FROM player_statistics");
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
			String uuid = results.getString("uuid");
			StatsHashmaps.playerWins.put(uuid, results.getInt("wins"));
			StatsHashmaps.playerLosses.put(uuid, results.getInt("losses"));
			StatsHashmaps.playerOverallDamageGiven.put(uuid, results.getInt("overall_damage_given"));
			StatsHashmaps.playerOverallDamageTaken.put(uuid, results.getInt("overall_damage_taken"));
			StatsHashmaps.playerSelectedKillText.put(uuid, results.getString("kill_text"));
		}
	}
	
}

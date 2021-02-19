package me.Jhim.TacklArena.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Arena.Arena;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;
import me.clip.placeholderapi.PlaceholderAPI;

public class Match {
	
	private Arena arena;
	private Player player1;
	private Player player2;
	private boolean matchInProgress = true;
	private Main plugin;
	private Player winner = null;
	private double player1DamageGiven;
	private double player2DamageGiven;
	private double player1DamageTaken;
	private double player2DamageTaken;
	private MatchBar bar;
	private List<Player> spectators = new ArrayList<Player>();
	private Economy eco;
	
	public Match(Main plugin, Player player1, Player player2, Arena arena) {
		this.plugin = plugin;
		this.player1 = player1;
		this.player2 = player2;
		this.arena = arena;

		startMatch();
	}
	public void startMatch() {
		MatchManager.matches.add(this);
		player1.getInventory().clear(); player2.getInventory().clear();
		player1.setHealth(20); player2.setHealth(20);
		player1.setFoodLevel(20); player2.setFoodLevel(20);
		player1.getInventory().addItem(selectClassItem()); player2.getInventory().addItem(selectClassItem());
		player1.teleport(arena.player1Pos); player2.teleport(arena.player2Pos);
		player1.getLocation().setYaw(arena.player1Yaw); player2.getLocation().setYaw(arena.player2Yaw);
		arena.setTaken(true);
		player1.sendTitle(ChatColor.RED + player1.getName() + " VS " + player2.getName(), ChatColor.GREEN + "CHOOSE YOUR CLASS", 10, 60, 30);
		player2.sendTitle(ChatColor.RED + player2.getName() + " VS " + player1.getName(), ChatColor.GREEN + "CHOOSE YOUR CLASS", 10, 60, 30);
		bar = new MatchBar(plugin, this, player1.getName(), player2.getName());
		bar.createBar(); bar.addPlayer(player1); bar.addPlayer(player2);
	}
	
	public void endMatch() {
		player1.getInventory().clear();
		player2.getInventory().clear();
		player1.setHealth(20); player2.setHealth(20);
		player1.setFoodLevel(20); player2.setFoodLevel(20);
		if(winner == null && player1DamageGiven == 0 && player2DamageGiven == 0) {
			cancelMatch();
			return;
		}
		
		matchInProgress = false;
		Location location = new Location(Bukkit.getWorld(plugin.getConfig().getString("spawn.coords.world")), plugin.getConfig().getInt("spawn.coords.x"), plugin.getConfig().getInt("spawn.coords.y"),plugin.getConfig().getInt("spawn.coords.z"));
		player1.teleport(location); player2.teleport(location);
		for(Player player : spectators) {
			player.teleport(location);
		}

		int ecoAmt = new Random().nextInt(200);
		if(winner == player1) {
			setStatsWinner(player1); setStatsLoser(player2);
			plugin.eco.depositPlayer(player1, ecoAmt);
			player1.sendMessage(format("&a&l+ $" + ecoAmt));
			player1.sendTitle(ChatColor.GREEN + "YOU WIN", null, 10, 60, 30);
			player2.sendTitle(ChatColor.RED + "YOU LOST", null, 10, 60, 30);
			String killText = format(PlaceholderAPI.setPlaceholders(player1, StatsHashmaps.playerSelectedKillText.get(player1.getUniqueId().toString())) + player2.getName());
			plugin.getServer().broadcastMessage(killText);
		} else if (winner == player2) {
			setStatsWinner(player2); setStatsLoser(player1);
			plugin.eco.depositPlayer(player2, new Random().nextInt(200));
			player2.sendMessage(format("&a&l+ $" + ecoAmt));
			player2.sendTitle(ChatColor.GREEN + "YOU WIN", null, 10, 60, 30);
			player1.sendTitle(ChatColor.RED + "YOU LOST", null, 10, 60, 30);
			String killText = format(PlaceholderAPI.setPlaceholders(player2, StatsHashmaps.playerSelectedKillText.get(player2.getUniqueId().toString())) + player1.getName());
			plugin.getServer().broadcastMessage(killText);
		} else if(winner == null) {
			if(player1DamageGiven > player2DamageGiven) {
				setStatsWinner(player1); setStatsLoser(player2);
				plugin.eco.depositPlayer(player1, new Random().nextInt(200));
				player1.sendMessage(format("&a&l+ $" + ecoAmt));
				player1.sendTitle(ChatColor.GREEN + "YOU WIN", null, 10, 60, 30);
				player2.sendTitle(ChatColor.RED + "YOU LOST", null, 10, 60, 30);
				String killText = format(PlaceholderAPI.setPlaceholders(player1, StatsHashmaps.playerSelectedKillText.get(player1.getUniqueId().toString())) + player2.getName());
				plugin.getServer().broadcastMessage(killText);
			} else {
				setStatsWinner(player2); setStatsLoser(player1);
				plugin.eco.depositPlayer(player1, new Random().nextInt(200));
				player2.sendMessage(format("&a&l+ $" + ecoAmt));
				player2.sendTitle(ChatColor.GREEN + "YOU WIN", null, 10, 60, 30);
				player1.sendTitle(ChatColor.RED + "YOU LOST", null, 10, 60, 30);
				String killText = format(PlaceholderAPI.setPlaceholders(player2, StatsHashmaps.playerSelectedKillText.get(player2.getUniqueId().toString())) + player1.getName());
				plugin.getServer().broadcastMessage(killText);
			}
		}
		
		for(String message : plugin.getConfig().getStringList("match.match_end_message")) {
			String player1Msg = PlaceholderAPI.setPlaceholders(player1, format(message));
			String player2Msg = PlaceholderAPI.setPlaceholders(player2, format(message));
			player1.sendMessage(player1Msg);
			player2.sendMessage(player2Msg);
		}
		player1.getInventory().clear();
		player2.getInventory().clear();
		bar.deleteBar();
		resetVariables();
		MatchManager.matches.remove(this);
		arena.setTaken(false);
	}
	
	public void cancelMatch() {
		matchInProgress = false;
		Location location = new Location(Bukkit.getWorld(plugin.getConfig().getString("spawn.coords.world")), plugin.getConfig().getInt("spawn.coords.x"), plugin.getConfig().getInt("spawn.coords.y"),plugin.getConfig().getInt("spawn.coords.z"));
		for(Player player : spectators) {
			player.teleport(location);
		}
		player1.teleport(location);
		player2.teleport(location);
		player1.sendTitle(ChatColor.GRAY + "MATCH CANCELLED", null, 10, 60, 30);
		player2.sendTitle(ChatColor.GRAY + "MATCH CANCELLED", null, 10, 60, 30);
		bar.deleteBar();
		resetVariables();
		arena.setTaken(false);
		MatchManager.matches.remove(this);
	}
	
	public boolean getMatchState() {
		return matchInProgress;
	}
	
	public void setMatchState(boolean state) {
		matchInProgress = state;
	}
	
	public Player getPlayerOne() {
		return player1;
	}
	
	public Player getPlayerTwo() {
		return player2;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public Player getOtherPlayer(Player player) {
		if(player == player1) return player2;
		if(player == player2) return player1;
		return null;
	}
	
	public double getPlayerDamageTaken(Player player) {
		if(player == player1) return player1DamageTaken;
		if(player == player2) return player2DamageTaken;
		return 0.0;
	}
	
	public double getPlayerDamageGiven(Player player) {
		if(player == player1) return player1DamageGiven;
		if(player == player2) return player2DamageGiven;
		return 0.0;
	}
	
	public void addPlayerDamageGiven(Player player, int damage) {
		if(player == player1) {
			player1DamageGiven += damage;
		}
		if(player == player2) {
			player2DamageGiven += damage;
		}
	}
	
	public ItemStack selectClassItem() {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + "RIGHT CLICK TO SELECT YOUR CLASS");
		item.setItemMeta(meta);
		
		return item;
	}
	
	public void addPlayerDamageTaken(Player player, int damage) {
		if(player == player1) {
			player1DamageTaken += damage;
		}
		if(player == player2) {
			player2DamageTaken += damage;
		}
	}
	
	private void resetVariables() {
		player1DamageGiven = 0;
		player2DamageGiven = 0;
		player1DamageTaken = 0;
		player2DamageTaken = 0;
		player1 = null;
		player2 = null;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void setWinner(Player player) {
		if(player == player1) {
			winner = player2;
		}
		if(player == player2) {
			winner = player1;
		}
	}
	
	public String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public void setStatsWinner(Player player) {
		if(!StatsHashmaps.playerWins.containsKey(player.getUniqueId().toString())) {
			StatsHashmaps.playerWins.put(player.getUniqueId().toString(), 1);
		} else {
			StatsHashmaps.playerWins.put(player.getUniqueId().toString(), 
					StatsHashmaps.playerWins.get(player.getUniqueId().toString()) + 1);
		}
	}
	
	public void setStatsLoser(Player player) {
		if(!StatsHashmaps.playerLosses.containsKey(player.getUniqueId().toString())) {
			StatsHashmaps.playerLosses.put(player.getUniqueId().toString(), 1);
		} else {
			StatsHashmaps.playerLosses.put(player.getUniqueId().toString(), 
					StatsHashmaps.playerLosses.get(player.getUniqueId().toString()) + 1);
		}
	}
	
}

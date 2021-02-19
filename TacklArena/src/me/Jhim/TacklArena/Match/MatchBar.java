package me.Jhim.TacklArena.Match;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.Jhim.TacklArena.Main;

public class MatchBar {

	private final Main plugin;
	private final Match match;
	private BossBar bar;
	private String player1;
	private String player2;
	
	public MatchBar(Main plugin, Match match, String player1, String player2) {
		this.plugin = plugin;
		this.match = match;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void addPlayer(Player player) {
		bar.addPlayer(player);
	}
	
	public BossBar getBar() {
		return bar;
	}
	
	public void createBar() {
		bar = Bukkit.createBossBar(format("&c" + player1 + " vs " + player2), BarColor.PURPLE, BarStyle.SOLID);
		bar.setVisible(true);
		runBar();
	}
	
	public void runBar() {
		new BukkitRunnable() {
			
			double progress = 1.0;
			double time = 1.0 / (180);
			@Override
			public void run() {
					
					if(progress <= 0) {
						match.endMatch();
						deleteBar();
						cancel();
						return;
					}
					if(match.getMatchState()) {
						bar.setProgress(progress);
						progress = progress - time;
				}
			}	
		}.runTaskTimer(plugin, 0, 20);
	}
	
	private String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public void deleteBar() {
		bar.removePlayer(Bukkit.getPlayer(player1));
		bar.removePlayer(Bukkit.getPlayer(player2));
	}
}

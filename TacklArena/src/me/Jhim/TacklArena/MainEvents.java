package me.Jhim.TacklArena;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Jhim.TackArena.MySQL.SQLFunctions;

public class MainEvents implements Listener {
	
	Main plugin = Main.getPlugin(Main.class);
	SQLFunctions SQLutils = new SQLFunctions();
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
		Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&7[&a+&7] &a" + player.getDisplayName()));
		BukkitRunnable r = new BukkitRunnable() {

			@Override
			public void run() {
				try {
					SQLutils.createPlayer(player.getUniqueId().toString(), player);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		r.runTaskAsynchronously(plugin);
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		Player player = (Player) event.getPlayer();
		event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&7[&c-&7] &c" + player.getDisplayName()));
	}
}

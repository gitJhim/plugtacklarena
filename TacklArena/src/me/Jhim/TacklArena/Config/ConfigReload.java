package me.Jhim.TacklArena.Config;

import java.sql.SQLException;

import me.Jhim.TacklArena.Profile.KillTexts.KillTextsConfig;
import me.Jhim.TacklArena.Profile.KillTexts.KillTextsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.Jhim.TackArena.MySQL.SQLFunctions;
import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Arena.ArenaConfig;
import me.Jhim.TacklArena.Arena.ArenaManager;
import me.Jhim.TacklArena.Class.ClassConfig;

public class ConfigReload implements CommandExecutor {

	Main plugin;
	ClassConfig classConfig;
	ArenaConfig arenaConfig;
	KillTextsConfig killTextsConfig;
	public ConfigReload(Main plugin, ClassConfig classConfig, ArenaConfig arenaConfig, KillTextsConfig killTextsConfig) {
		this.plugin = plugin;
		this.classConfig = classConfig;
		this.arenaConfig = arenaConfig;
		this.killTextsConfig = killTextsConfig;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("tacklarena")) {
			if(!sender.hasPermission("tacklarena.reload")) {
				sender.sendMessage(ChatColor.DARK_RED + "You cannot run this command!");
				return true;
			}
			if(args.length == 0) {
				// /tacklarena
				sender.sendMessage(ChatColor.RED + "Usage /tacklarena <param>");
				return true;
			}
			if(args.length > 0)
				if(args[0].equalsIgnoreCase("reload")) {
					for(String message : plugin.getConfig().getStringList("reload.message")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
					plugin.reloadConfig();
					classConfig.reloadConfig();
					arenaConfig.reloadConfig();
					killTextsConfig.reloadConfig();
					new ArenaManager(arenaConfig).createArenas();
					new KillTextsMain(plugin, killTextsConfig).createKillTexts();
					return true;
				}
				if(args[0].equalsIgnoreCase("sqlreload")) {
					
					SQLFunctions sqlutils = new SQLFunctions();
					BukkitRunnable r = new BukkitRunnable() {
						@Override
						public void run() {
							try {
								sqlutils.updatePlayerStatistics();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					};
					r.runTaskAsynchronously(plugin);
				}
			}
		return false;
	}
}


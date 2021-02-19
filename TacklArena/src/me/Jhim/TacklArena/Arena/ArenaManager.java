package me.Jhim.TacklArena.Arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ArenaManager {

	public static List<Arena> arenas = new ArrayList<Arena>();
	ArenaConfig arenaConfig;
	public ArenaManager(ArenaConfig arenaConfig) {
		this.arenaConfig = arenaConfig;
	}
	
	public void createArenas() {
		arenas.clear();
		arenaConfig.getConfig().getConfigurationSection("arenas").getKeys(false).forEach(key -> {
			FileConfiguration config = arenaConfig.getConfig();
			Material icon = Material.matchMaterial(config.getString("arenas." + key + ".icon"));
			World world = Bukkit.getWorld("arenas");
			float player1x = config.getInt("arenas." + key + ".player1.x");
			float player1y = config.getInt("arenas." + key + ".player1.y");
			float player1z = config.getInt("arenas." + key + ".player1.z");
			float player1yaw = config.getInt("arenas." + key + ".player1.yaw");
			float player2x = config.getInt("arenas." + key + ".player2.x");
			float player2y = config.getInt("arenas." + key + ".player2.y");
			float player2z = config.getInt("arenas." + key + ".player2.z");
			float player2yaw = config.getInt("arenas." + key + ".player2.yaw");

			arenas.add(new Arena(key, icon, 
					new Location(world, player1x, player1y, player1z), player1yaw,
					new Location(world, player2x, player2y, player2z), player2yaw));
			Bukkit.getConsoleSender().sendMessage(key + " arena loaded!");
		});
	}
	
	public Arena getOpenArena() {
		while (true){
			Arena arena = arenas.get(new Random().nextInt(arenas.size()));
			if(!arena.isTaken()) {
				return arena;
			}
		}
	}
	
	public static Arena getArenaFromName(String name) {
		for(Arena arena : arenas) {
			if(arena.name.contains(name)) {
				return arena;
			}
		}
		return null;
	}
}

package me.Jhim.TacklArena.Queues;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;

import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Arena.Arena;
import me.Jhim.TacklArena.Arena.ArenaManager;
import me.Jhim.TacklArena.Match.Match;
import me.Jhim.TacklArena.Match.MatchManager;
import me.Jhim.TacklArena.Utils.ParticleShapes;

public class CasualQueue {
	Main plugin;
	ArenaManager arenaManager;
	public CasualQueue(Main plugin, ArenaManager am) {
		this.plugin = plugin;
		this.arenaManager = am;
	}

	public Queue<String> casualQueue = new LinkedList<String>();
	MatchManager matchManager = new MatchManager();
	ParticleShapes particles = new ParticleShapes();
	
	public void addPlayerToQueue(Player player) {
		if(casualQueue.contains(player.getName())) {
			removePlayerFromQueue(player);
			return;
		}
		casualQueue.add(player.getName());
		player.sendMessage(ChatColor.GREEN + "You were added to the " + ChatColor.YELLOW.toString() + "Casual Queue!");
		DustOptions dustOptions = new DustOptions(Color.fromRGB(50, 205, 50), 1);
		particles.drawCircle(Particle.REDSTONE, player, 1, dustOptions);
	}
	
	public void removePlayerFromQueue(Player player) {
		if(!casualQueue.contains(player.getName())) return;
		casualQueue.remove(player.getName());
		player.sendMessage(ChatColor.RED + "You were removed from the " + ChatColor.YELLOW.toString() + "Casual Queue!");
		DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 99, 71), 1);
		particles.drawCircle(Particle.REDSTONE, player, 1, dustOptions);
	}
	
	public void startMatch() {
		Player player1 = Bukkit.getPlayer(casualQueue.poll());
		Player player2 = Bukkit.getPlayer(casualQueue.poll());
		
		Arena arena = arenaManager.getOpenArena();
		if(arena == null) return;
		Match match = new Match(plugin, player1, player2, arena);
		matchManager.addMatchToList(match);
	}
}

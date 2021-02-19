package me.Jhim.TacklArena.Match;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Jhim.TacklArena.Arena.ArenaManager;
import me.Jhim.TacklArena.Class.ClassMain;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;

public class MatchEvents implements Listener{
	
	MatchManager matchManager = new MatchManager();
	ClassMain cm;
	public MatchEvents(ClassMain cm) {
		this.cm = cm;
	}
	
	@EventHandler
	public void onPlayerDamageInMatch(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getDamager() instanceof Player || event.getDamager() instanceof Arrow)) return;
		
		int damage = (int) Math.round(event.getDamage());
		Player damaged = (Player) event.getEntity();
		if(MatchManager.playerInMatch(damaged)) {
			Player damager;
			Match match = MatchManager.getPlayerMatch(damaged);
			if(!(event.getDamager() instanceof Arrow)) {
				damager = (Player) event.getDamager();
			} else {
				damager = match.getOtherPlayer(damaged);
			}
			if(!StatsHashmaps.playerOverallDamageGiven.containsKey(damager.getUniqueId().toString())) {
				StatsHashmaps.playerOverallDamageGiven.put(damager.getUniqueId().toString(), damage);
			} else {
				StatsHashmaps.playerOverallDamageGiven.put(damager.getUniqueId().toString(), 
						StatsHashmaps.playerOverallDamageGiven.get(damager.getUniqueId().toString()) + damage);
			}
			
			if(!StatsHashmaps.playerOverallDamageTaken.containsKey(damaged.getUniqueId().toString())) {
				StatsHashmaps.playerOverallDamageTaken.put(damaged.getUniqueId().toString(), damage);
			} else {
				StatsHashmaps.playerOverallDamageTaken.put(damaged.getUniqueId().toString(), 
						StatsHashmaps.playerOverallDamageTaken.get(damaged.getUniqueId().toString()) + damage);
			}
			
			match.addPlayerDamageGiven(damager, damage);
			match.addPlayerDamageTaken(damaged, damage);
		}
	}
	
	
	@EventHandler
	public void onPlayerDeathInMatch(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		event.setDeathMessage("");
		
		if(MatchManager.playerInMatch(player)) {
			Match match = MatchManager.getPlayerMatch(player);
			match.setWinner(player);
			match.endMatch();
		}
	}
	
	@EventHandler
	public void onClassRightClick(PlayerInteractEvent event) {
		if(MatchManager.playerInMatch(event.getPlayer())) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					event.getPlayer().getInventory().clear();
					event.getPlayer().openInventory(cm.classInv);
				}
			}
		}	
	}
	
	@EventHandler
	public void onPlayerInMatchLeave(PlayerQuitEvent event) {
		Player playerLeft = event.getPlayer();
		if(MatchManager.playerInMatch(playerLeft)) {
			Match match = MatchManager.getPlayerMatch(playerLeft);
			Player playerAlive = match.getOtherPlayer(playerLeft);
			
			if(match.getPlayerDamageTaken(playerLeft) > match.getPlayerDamageTaken(playerAlive)) {
				match.setWinner(playerLeft);
				match.endMatch();
				return;
			}
			match.setStatsLoser(playerLeft);
			match.cancelMatch();
		}
	}
}

package me.Jhim.TacklArena.Class.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import me.Jhim.TacklArena.Class.ClassConfig;
import me.Jhim.TacklArena.Match.MatchManager;

public class CommanderEvents implements Listener {
	
	Map<String, Long> boostCooldown = new HashMap<String, Long>();
	Map<String, Long> healthCooldown = new HashMap<String, Long>();
	Map<String, Long> leapCooldown = new HashMap<String, Long>();
	private ClassConfig config;
	public CommanderEvents(ClassConfig config) {
		this.config = config;
	}
	private int boostCooldownTime;
	private int healthCooldownTime;
	private int leapCooldownTime;
	
	@EventHandler()
	public void onCommanderAbilityUse(PlayerInteractEvent event) {
		
		Player player = (Player) event.getPlayer();
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Commander")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.BLUE_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(boostCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(boostCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (boostCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Boost again!"));
							return;
						}
					}
					boostCooldownTime = config.getConfig().getInt("commander.cooldowns.boost");
					boostCooldown.put(player.getName(), System.currentTimeMillis() + (boostCooldownTime * 1000));
					Player opponent = MatchManager.getPlayerMatch(player).getOtherPlayer(player);
					
					opponent.setVelocity(new Vector(0, 2, 0));
					opponent.setHealth(opponent.getHealth() - 3);
					return;
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.PINK_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(healthCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(healthCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (healthCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Health again!"));
							return;
						}
					}
					healthCooldownTime = config.getConfig().getInt("commander.cooldowns.health");
					healthCooldown.put(player.getName(), System.currentTimeMillis() + (healthCooldownTime * 1000));
					if(player.getHealth() > 14) {
						player.setHealth(20);
						return;
					} else player.setHealth(player.getHealth() + 6);
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(leapCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(leapCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (leapCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Leap again!"));
							return;
						}
					}
					leapCooldownTime = config.getConfig().getInt("commander.cooldowns.leap");
					leapCooldown.put(player.getName(), System.currentTimeMillis() + (leapCooldownTime * 1000));
					player.setVelocity(player.getLocation().getDirection().multiply(1.5));
				}
			}
		}	
	}
}

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Jhim.TacklArena.Class.ClassConfig;

public class HeavyEvents implements Listener {
	Map<String, Long> furyCooldown = new HashMap<String, Long>();
	Map<String, Long> bulkCooldown = new HashMap<String, Long>();
	Map<String, Long> groundCooldown = new HashMap<String, Long>();
	private ClassConfig config;
	public HeavyEvents(ClassConfig config) {
		this.config = config;
	}
	private int furyCooldownTime;
	private int bulkCooldownTime;
	private int groundCooldownTime;
	
	@EventHandler()
	public void onHeavyAbilityUse(PlayerInteractEvent event) {
		
		Player player = (Player) event.getPlayer();
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Heavy")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.STONE_SWORD) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(furyCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(furyCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (furyCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Fury again!"));
							return;
						}
					}
					furyCooldownTime = config.getConfig().getInt("heavy.cooldowns.fury");
					furyCooldown.put(player.getName(), System.currentTimeMillis() + (furyCooldownTime * 1000));
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
					return;
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.RED_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(bulkCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(bulkCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (bulkCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Bulk again!"));
							return;
						}
					}
					bulkCooldownTime = config.getConfig().getInt("heavy.cooldowns.bulk");
					bulkCooldown.put(player.getName(), System.currentTimeMillis() + (bulkCooldownTime * 1000));
					player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 1));
					return;
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.PINK_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(groundCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(groundCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (groundCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Ground again!"));
							return;
						}
					}
					groundCooldownTime = config.getConfig().getInt("heavy.cooldowns.ground");
					groundCooldown.put(player.getName(), System.currentTimeMillis() + (groundCooldownTime * 1000));
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 999));
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 999));
					return;
				}
			}
		}	
	}
}

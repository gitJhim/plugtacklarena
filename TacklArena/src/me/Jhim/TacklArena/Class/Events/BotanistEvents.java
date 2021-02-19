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
import me.Jhim.TacklArena.Match.MatchManager;

public class BotanistEvents implements Listener {
	Map<String, Long> naturesAuraCooldown = new HashMap<String, Long>();
	Map<String, Long> rootCooldown = new HashMap<String, Long>();
	Map<String, Long> poisionHerbsCooldown = new HashMap<String, Long>();
	private ClassConfig config;
	
	public BotanistEvents (ClassConfig config) {
		this.config = config;
	}
	private int naturesAuraCooldownTime;
	private int rootCooldownTime;
	private int poisionHerbsCooldownTime;
	
	@EventHandler()
	public void onBotanistAbilityUse(PlayerInteractEvent event) {
		
		Player player = (Player) event.getPlayer();
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Botanist")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SHOVEL) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(naturesAuraCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(naturesAuraCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (naturesAuraCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Nature's Aura again!"));
							return;
						}
					}
					naturesAuraCooldownTime = config.getConfig().getInt("botanist.cooldowns.naturesaura");
					naturesAuraCooldown.put(player.getName(), System.currentTimeMillis() + (naturesAuraCooldownTime * 1000));
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 160, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 160, 1));
					return;
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.LIME_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(rootCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(rootCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (rootCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Root again!"));
							return;
						}
					}
					rootCooldownTime = config.getConfig().getInt("botanist.cooldowns.root");
					rootCooldown.put(player.getName(), System.currentTimeMillis() + (rootCooldownTime * 1000));
					Player opponent = MatchManager.getPlayerMatch(player).getOtherPlayer(player);
					opponent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 999));
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.GREEN_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(poisionHerbsCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(poisionHerbsCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (poisionHerbsCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Poision Herbs again!"));
							return;
						}
					}
					poisionHerbsCooldownTime = config.getConfig().getInt("botanist.cooldowns.poisionherbs");
					poisionHerbsCooldown.put(player.getName(), System.currentTimeMillis() + (poisionHerbsCooldownTime * 1000));
					Player opponent = MatchManager.getPlayerMatch(player).getOtherPlayer(player);
					opponent.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 4));
					opponent.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 4));
				}
			}
		}	
	}
}

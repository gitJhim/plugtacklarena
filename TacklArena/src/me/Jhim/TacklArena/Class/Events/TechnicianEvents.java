package me.Jhim.TacklArena.Class.Events;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.Jhim.TacklArena.Class.ClassConfig;
import me.Jhim.TacklArena.Match.MatchManager;

public class TechnicianEvents implements Listener {

	Map<String, Long> guardCooldown = new HashMap<String, Long>();
	Map<String, Long> shiftCooldown = new HashMap<String, Long>();
	Map<String, Long> radarCooldown = new HashMap<String, Long>();
	private ClassConfig config;
	public TechnicianEvents(ClassConfig config) {
		this.config = config;
	}
	private int guardCooldownTime;
	private int shiftCooldownTime;
	private int radarCooldownTime;
	
	@EventHandler()
	public void onTechnicianAbilityUse(PlayerInteractEvent event) {
		
		Player player = (Player) event.getPlayer();
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Technician")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(guardCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(guardCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (guardCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Guard again!"));
							return;
						}
					}
					guardCooldownTime = config.getConfig().getInt("technician.cooldowns.guard");
					guardCooldown.put(player.getName(), System.currentTimeMillis() + (guardCooldownTime * 1000));
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160, 1));
					return;
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.RED_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(shiftCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(shiftCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (shiftCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Shift again!"));
							return;
						}
					}
					shiftCooldownTime = config.getConfig().getInt("technician.cooldowns.shift");
					shiftCooldown.put(player.getName(), System.currentTimeMillis() + (shiftCooldownTime * 1000));
					
					Random rand = new Random();
					int amplifier = 3;
					Player opponent = MatchManager.getPlayerMatch(player).getOtherPlayer(player);
					opponent.setVelocity(new Vector(rand.nextInt(amplifier), rand.nextInt(amplifier), rand.nextInt(amplifier)));
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.PINK_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(radarCooldown.containsKey(player.getName())) {
						// Player is inside hashmap
						if(radarCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (radarCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Radar again!"));
							return;
						}
					}
					radarCooldownTime = config.getConfig().getInt("technician.cooldowns.radar");
					radarCooldown.put(player.getName(), System.currentTimeMillis() + (radarCooldownTime * 1000));
					
					Player opponent = MatchManager.getPlayerMatch(player).getOtherPlayer(player);
					player.teleport(opponent.getLocation());
				}
			}
		}	
	}
}

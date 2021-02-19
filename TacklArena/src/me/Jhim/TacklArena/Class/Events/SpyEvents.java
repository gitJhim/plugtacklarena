package me.Jhim.TacklArena.Class.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Class.ClassConfig;

public class SpyEvents implements Listener {

	Map<String, Long> velocityCooldown = new HashMap<String, Long>();
	Map<String, Long> cloakCooldown = new HashMap<String, Long>();
	Main plugin;
	private ClassConfig config;
	public SpyEvents(ClassConfig config, Main plugin) {
		this.config = config;
		this.plugin = plugin;
	}
	private int velocityCooldownTime;
	private int cloakCooldownTime;
	
	@EventHandler()
	public void onSpyAbilityUse(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Spy")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(velocityCooldown.containsKey(player.getName())) {
						if(velocityCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (velocityCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Velocity again!"));
							return;
						}
					}
					velocityCooldownTime = config.getConfig().getInt("spy.cooldowns.velocity");
					velocityCooldown.put(player.getName(), System.currentTimeMillis() + (velocityCooldownTime * 1000));
					PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 160, 1);
					player.addPotionEffect(speed);
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.GRAY_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR  || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(cloakCooldown.containsKey(player.getName())) {
						if(cloakCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (cloakCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Cloak again!"));
							return;
						}
					}
					cloakCooldownTime = config.getConfig().getInt("spy.cooldowns.cloak");
					cloakCooldown.put(player.getName(), System.currentTimeMillis() + (cloakCooldownTime * 1000));
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 2));
					ItemStack[] armor = player.getInventory().getArmorContents();
					player.getInventory().setHelmet(null);
					player.getInventory().setChestplate(null);
					player.getInventory().setLeggings(null);
					player.getInventory().setBoots(null);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							player.getInventory().setArmorContents(armor);
						}
					}, 100);
					return;
				}
			}
		}
	}
	
	@EventHandler()
	public void onStickHit(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			Player attacker = (Player) event.getDamager();
			Player damaged = (Player) event.getEntity();
			if(attacker.getInventory().getHelmet() == null) return;
			if(attacker.getInventory().getHelmet().getItemMeta() == null) return;
			if(attacker.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Spy")) {
				if(attacker.getInventory().getItemInMainHand().getType() == Material.STICK) {
					PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 140, 2);
					PotionEffect glow = new PotionEffect(PotionEffectType.GLOWING, 140, 2);
					damaged.addPotionEffect(blindness);
					damaged.addPotionEffect(glow);
				}
			}
		}
	}
}

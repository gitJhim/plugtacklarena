package me.Jhim.TacklArena.Class.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Jhim.TacklArena.Class.ClassConfig;

public class SniperEvents implements Listener {
	Map<String, Long> propelCooldown = new HashMap<String, Long>();
	Map<String, Long> pyroCooldown = new HashMap<String, Long>();
	Map<String, Long> warpCooldown = new HashMap<String, Long>();
	Arrow arrow;
	private ClassConfig config;
	public SniperEvents(ClassConfig config) {
		this.config = config;
	}
	private int propelCooldownTime;
	private int pyroCooldownTime;
	private int warpCooldownTime;
	
	@EventHandler()
	public void onSniperAbilityUse(PlayerInteractEvent event) {

		Player player = (Player) event.getPlayer();
		if(player.getInventory().getHelmet() == null) return;
		if(player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Sniper")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.BOW) {
				if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
					// Knockback Arrow
					if(propelCooldown.containsKey(player.getName())) {
						if(propelCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (propelCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Propel again!"));
							return;
						}
					}
					propelCooldownTime = config.getConfig().getInt("sniper.cooldowns.propel");
					propelCooldown.put(player.getName(), System.currentTimeMillis() + (propelCooldownTime * 1000));
					Arrow arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(5));
					arrow.setKnockbackStrength(5);
					arrow.setPickupStatus(PickupStatus.DISALLOWED);
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.RED_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					// Knockback Arrow
					if(pyroCooldown.containsKey(player.getName())) {
						if(pyroCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (pyroCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Pyro again!"));
							return;
						}
					}
					pyroCooldownTime = config.getConfig().getInt("sniper.cooldowns.pyro");
					pyroCooldown.put(player.getName(), System.currentTimeMillis() + (pyroCooldownTime * 1000));
					Arrow arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(5));
					arrow.setPickupStatus(PickupStatus.DISALLOWED);
					arrow.setFireTicks(3000);
				}
			}
			
			if(player.getInventory().getItemInMainHand().getType() == Material.PURPLE_DYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					// Knockback Arrow
					if(warpCooldown.containsKey(player.getName())) {
						if(warpCooldown.get(player.getName()) > System.currentTimeMillis()) {
							// Still has time in cooldown
							long timeLeft = (warpCooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have to wait " + "&e" + timeLeft + " &6second(s) until you can use Warp again!"));
							return;
						}
					}
					warpCooldownTime = config.getConfig().getInt("sniper.cooldowns.warp");
					warpCooldown.put(player.getName(), System.currentTimeMillis() + (warpCooldownTime * 1000));
					arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(3));
					arrow.setPickupStatus(PickupStatus.DISALLOWED);
				}
			}
		}
	}
	
	@EventHandler()
	public void onTeleportShoot(ProjectileHitEvent event) {
		if (!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
		if (!(event.getEntity() instanceof Arrow)) {
            return;
        }
		if(event.getEntity() == arrow) {
			Player player = (Player) event.getEntity().getShooter();
			Location l = event.getEntity().getLocation();
	        l.setYaw(player.getLocation().getYaw());
	        l.setPitch(player.getLocation().getPitch());
	        player.teleport(l);	
		}
	}
}


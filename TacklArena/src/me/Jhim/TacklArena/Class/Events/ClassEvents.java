package me.Jhim.TacklArena.Class.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;

import me.Jhim.TacklArena.Class.ClassMain;
import me.Jhim.TacklArena.Class.Classes.Botanist;
import me.Jhim.TacklArena.Class.Classes.Commander;
import me.Jhim.TacklArena.Class.Classes.Heavy;
import me.Jhim.TacklArena.Class.Classes.Sniper;
import me.Jhim.TacklArena.Class.Classes.Spy;
import me.Jhim.TacklArena.Class.Classes.Technician;


public class ClassEvents implements Listener {
	
	private ClassMain cm;
	Commander commander;
	Sniper sniper;
	Spy spy;
	Technician technician;
	Heavy heavy;
	Botanist botanist;
	
	public ClassEvents(ClassMain instance, Commander commander, Sniper sniper, Spy spy,
			Technician technician, Heavy heavy, Botanist botanist) {
		
		cm = instance;
		this.commander = commander;
		this.sniper = sniper;
		this.spy = spy;
		this.technician = technician;
		this.heavy = heavy;
		this.botanist = botanist;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if(event.getCurrentItem() == null) return;
		if(event.getCurrentItem().getItemMeta() == null) return;
		if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		
		Player player = (Player) event.getWhoClicked();
		for(PotionEffect e : player.getActivePotionEffects()) {
			player.removePotionEffect(e.getType());
		}
		if(event.getClickedInventory().equals(cm.classInv)) {
			event.setCancelled(true);
			if(event.getSlot() == 20) {
				commander.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}
			
			if(event.getSlot() == 22) {
				sniper.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}
			
			if(event.getSlot() == 24) {
				spy.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}
			
			if(event.getSlot() == 38) {
				technician.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}
			
			if(event.getSlot() == 40) {
				heavy.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}
			
			if(event.getSlot() == 42) {
				botanist.equipClass(player);
				player.closeInventory();
				player.updateInventory();
			}	
		}
		
		if(event.getClickedInventory().equals(cm.previewClassInv)) {
			event.setCancelled(true);
			if(event.getSlot() == 20) {
				player.closeInventory();
				player.openInventory(commander.commanderPreview);
			}
			
			if(event.getSlot() == 22) {
				player.closeInventory();
				player.openInventory(sniper.sniperPreview);
			}
			
			if(event.getSlot() == 24) {
				player.closeInventory();
				player.openInventory(spy.spyPreview);
			}
			if(event.getSlot() == 38) {
				player.closeInventory();
				player.openInventory(technician.technicianPreview);
			}
			
			if(event.getSlot() == 40) {
				player.closeInventory();
				player.openInventory(heavy.heavyPreview);
			}
			if(event.getSlot() == 42) {
				player.closeInventory();
				player.openInventory(botanist.botanistPreview);
			}
		}
		
		if(event.getClickedInventory().equals(commander.commanderPreview) || 
			event.getClickedInventory().equals(sniper.sniperPreview) ||
			event.getClickedInventory().equals(spy.spyPreview) ||
			event.getClickedInventory().equals(technician.technicianPreview) ||
			event.getClickedInventory().equals(heavy.heavyPreview) ||
			event.getClickedInventory().equals(botanist.botanistPreview)) {
			
			event.setCancelled(true);
		}
	}
}

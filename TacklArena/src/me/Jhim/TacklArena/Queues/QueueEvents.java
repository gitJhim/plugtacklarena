package me.Jhim.TacklArena.Queues;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class QueueEvents implements Listener {

	private QueueGUI qg;
	private CasualQueue cq;
	public QueueEvents(QueueGUI guiInstance, CasualQueue queueInstance) {
		qg = guiInstance;
		cq = queueInstance;
	}
	
	@EventHandler
	public void onQueueClick(InventoryClickEvent event) {
		if(!event.getClickedInventory().equals(qg.inv)) return;		
		if(event.getCurrentItem() == null) return;
		if(event.getCurrentItem().getItemMeta() == null) return;
		if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() == 4) {
			if(cq.casualQueue.contains(player.getDisplayName())) {
				player.sendMessage(ChatColor.RED + "You are already in a queue!");
			}
			cq.addPlayerToQueue(player);
			player.closeInventory();
			if(cq.casualQueue.size() > 1) cq.startMatch();
		}
	}
	
	@EventHandler
	public void onPlayerLeaveInQueue(PlayerQuitEvent event) {
		Player player = (Player) event.getPlayer();
		
		for(String playerInQueue : cq.casualQueue) {
			if(player.getName() == playerInQueue) {
				cq.removePlayerFromQueue(player);
			}
		}
	}
	
	
}

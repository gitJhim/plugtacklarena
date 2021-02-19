package me.Jhim.TacklArena.Arena;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArenaEvents implements Listener {

    @EventHandler
    public void onArenasClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == ArenasCommand.inv) {
            event.setCancelled(true);
        }
    }
}

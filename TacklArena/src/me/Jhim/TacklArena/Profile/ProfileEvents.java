package me.Jhim.TacklArena.Profile;

import me.Jhim.TacklArena.Profile.KillTexts.KillTextsMain;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProfileEvents implements Listener {

    KillTextsMain killTextsMain;
    public ProfileEvents(KillTextsMain killTextsMain) {
        this.killTextsMain = killTextsMain;
    }
    @EventHandler
    public void onProfileClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(ProfileCommand.profileInvs.contains(event.getClickedInventory())) {
            event.setCancelled(true);

            switch (event.getSlot()) {
                case 2: killTextsMain.createKillTextsGUI((Player) event.getWhoClicked());
            }
            return;
        }

        if(KillTextsMain.killTextInvs.contains(event.getClickedInventory())) {
            player.sendMessage(event.getCurrentItem().getItemMeta().getDisplayName());
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler()
    public void onPlayerProfileJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(StatsHashmaps.playerSelectedKillText.get(player.getUniqueId().toString()) == null) {
            StatsHashmaps.playerSelectedKillText.put(player.getUniqueId().toString(), "&a&l%player_name% slayed &c&l");
        }
    }
}

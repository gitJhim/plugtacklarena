package me.Jhim.TacklArena.Arena;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArenasCommand implements CommandExecutor {
    public static Inventory inv = Bukkit.createInventory(null, 9);

    public ArenasCommand() {
        createArenaGUI();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("arenas")) {
            if (!(sender instanceof Player)) sender.sendMessage("You cannot use this command!");
            Player player = (Player) sender;
            player.openInventory(inv);
            return true;
        }
        return false;
    }

    private void createArenaGUI() {
        int i = 2;
        for(Arena arena : ArenaManager.arenas) {
            ItemStack item = new ItemStack(arena.icon);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(arena.name);
            item.setItemMeta(meta);
            inv.setItem(i, item);
            i++;
        }
    }
}

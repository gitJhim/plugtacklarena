package me.Jhim.TacklArena.Profile;

import me.Jhim.TacklArena.Main;
import me.Jhim.TacklArena.Statistics.StatsHashmaps;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_15_R1.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileCommand implements CommandExecutor {

    public static List<Inventory> profileInvs = new ArrayList<Inventory>();
    private Inventory inv;
    Main plugin;
    public ProfileCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("profile")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("You cannot use this command!");
                return true;
            }
            Player player = (Player) sender;
            Bukkit.getConsoleSender().sendMessage(StatsHashmaps.playerSelectedKillText.get(player.getUniqueId().toString()));
            createProfileGUI(player);
            player.openInventory(inv);
        }
        return false;
    }

    private void createProfileGUI(Player player) {
        inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Profile");
        profileInvs.add(inv);
        // Stats Head
        ItemStack statsHead = getPlayerHead(player);
        ItemMeta meta = statsHead.getItemMeta();
        List<String> lore = new ArrayList<String>();
        for(String message : plugin.getConfig().getStringList("mystats")) {
            lore.add(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', message)));
        }
        meta.setLore(lore);
        meta.setDisplayName(PlaceholderAPI.setPlaceholders(player, ChatColor.translateAlternateColorCodes('&', "&6%player_name%'s Stats")));
        statsHead.setItemMeta(meta);
        inv.setItem(0, statsHead);

        lore.clear();
        // Kill Texts
        ItemStack killTexts = new ItemStack(Material.NAME_TAG);
        meta = killTexts.getItemMeta();
        meta.setDisplayName(ChatColor.BOLD + ChatColor.YELLOW.toString() + "Kill Texts");
        lore.add("");
        lore.add(ChatColor.GOLD + "Click to choose a Kill Text");
        meta.setLore(lore);
        killTexts.setItemMeta(meta);
        inv.setItem(2, killTexts);

    }

    private ItemStack getPlayerHead(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(player.getName());
        skull.setItemMeta(skullMeta);
        return skull;
    }
}

package me.Jhim.TacklArena.Profile.KillTexts;

import com.mysql.fabric.xmlrpc.base.Array;
import me.Jhim.TacklArena.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KillTextsMain {

    public static List<Inventory> killTextInvs = new ArrayList<Inventory>();
    public static List<KillText> allKillTexts = new ArrayList<KillText>();
    private KillTextsConfig killTextsConfig;

    Main plugin;
    public KillTextsMain(Main plugin, KillTextsConfig killTextsConfig) {
        this.plugin = plugin;
        this.killTextsConfig = killTextsConfig;
    }

    public void createKillTexts() {
        FileConfiguration config = killTextsConfig.getConfig();
        config.getConfigurationSection("killtexts").getKeys(false).forEach(key -> {
            allKillTexts.add(new KillText(
                    config.getString("killtexts." + key + ".title"),
                    config.getString("killtexts." + key + ".text"),
                    config.getString("killtexts." + key + ".permission"),
                    config.getBoolean("killtexts." + key + ".isPublic"),
                    config.getInt(  "killtexts." + key + ".price")));
        });
    }

    public void createKillTextsGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kill Texts");
        killTextInvs.add(inv);

        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        int i = 0;
        for(KillText killText : allKillTexts) {
            lore.clear();
            lore.add("");
            meta.setDisplayName(format(killText.getTitle(), player));

            if(player.hasPermission(killText.getPermission())) {
                lore.add(format("&6&lCLICK TO SELECT", player));
            } else {
                lore.add(format("&a&l$" + killText.getPrice(), player));
                if(!killText.isPublic()) { continue; }
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(i, item);
            i++;
        }
        player.openInventory(inv);
    }

    private String format(String msg, Player player) {
        msg = PlaceholderAPI.setPlaceholders(player, msg);
        return ChatColor.translateAlternateColorCodes('&',msg);
    }

}

package me.Jhim.TacklArena.Class;

import java.util.ArrayList;
import java.util.List;

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

public class ClassMain implements CommandExecutor {
	
	public ClassMain() {
		createClassGUI();
		createClassPreviewGUI();
	}
	public Inventory classInv;
	public Inventory previewClassInv;

	public boolean onCommand(CommandSender sender,  Command cmd,  String label, String[] args) {
		
		if(label.equalsIgnoreCase("class")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You cannot use this command!");
				return true;
			}
			Player player = (Player) sender;
			if(!player.hasPermission("tacklarena.class")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			
			player.openInventory(previewClassInv);
			return true;
			
		}
		
		return false;
	}
	
	public void createClassGUI() {
		classInv = Bukkit.createInventory(null, 54, ChatColor.RED + ChatColor.BOLD.toString() + "Classes");
		
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + "CHOOSE A CLASS");
		item.setItemMeta(meta);
		classInv.setItem(4, item);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + ChatColor.BOLD.toString() + "CLICK TO SELECT CLASS");
		
		// Commander Class
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.RED + "Commander");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(20, item);
		
		// Sniper Class
		item.setType(Material.LIME_CONCRETE);
		meta.setDisplayName(ChatColor.GREEN + "Sniper");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(22, item);
		
		// Spy Class
		item.setType(Material.BLUE_CONCRETE);
		meta.setDisplayName(ChatColor.BLUE + "Spy");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(24, item);
		
		// Technician Class
		item.setType(Material.ORANGE_CONCRETE);
		meta.setDisplayName(ChatColor.GOLD + "Technician");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(38, item);
		
		// Heavy Class
		item.setType(Material.YELLOW_CONCRETE);
		meta.setDisplayName(ChatColor.YELLOW + "Heavy");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(40, item);
		
		// Heavy Class
		item.setType(Material.GREEN_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_GREEN + "Botanist");
		meta.setLore(lore);
		item.setItemMeta(meta);
		classInv.setItem(42, item);
	}
	
	private void createClassPreviewGUI() {
		previewClassInv = Bukkit.createInventory(null, 54, ChatColor.RED + ChatColor.BOLD.toString() + "Classes Preview");
		
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + "PREVIEW CLASS");
		item.setItemMeta(meta);
		previewClassInv.setItem(4, item);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + ChatColor.BOLD.toString() + "CLICK TO PREVIEW CLASS");
		
		// Commander Class
		item.setType(Material.RED_CONCRETE);
		meta.setDisplayName(ChatColor.RED + "Commander");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(20, item);
				
		// Sniper Class
		item.setType(Material.LIME_CONCRETE);
		meta.setDisplayName(ChatColor.GREEN + "Sniper");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(22, item);
		
		// Spy Class
		item.setType(Material.BLUE_CONCRETE);
		meta.setDisplayName(ChatColor.BLUE + "Spy");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(24, item);
				
		// Technician Class
		item.setType(Material.ORANGE_CONCRETE);
		meta.setDisplayName(ChatColor.GOLD + "Technician");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(38, item);
				
		// Heavy Class
		item.setType(Material.YELLOW_CONCRETE);
		meta.setDisplayName(ChatColor.YELLOW + "Heavy");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(40, item);
				
		// Heavy Class
		item.setType(Material.GREEN_CONCRETE);
		meta.setDisplayName(ChatColor.DARK_GREEN + "Botanist");
		meta.setLore(lore);
		item.setItemMeta(meta);
		previewClassInv.setItem(42, item);
	}

}

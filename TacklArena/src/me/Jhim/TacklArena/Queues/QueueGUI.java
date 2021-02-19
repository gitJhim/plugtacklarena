package me.Jhim.TacklArena.Queues;

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

import me.Jhim.TacklArena.Match.MatchManager;

public class QueueGUI implements CommandExecutor {

	public QueueGUI() {
		createClassGUI();
	}
	public Inventory inv;

	public boolean onCommand(CommandSender sender,  Command cmd,  String label, String[] args) {
		
		if(label.equalsIgnoreCase("queue")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You cannot use this command!");
				return true;
			}
			Player player = (Player) sender;
			if(!player.hasPermission("tacklarena.queue")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			if(MatchManager.playerInMatch(player)) {
				player.sendMessage(ChatColor.RED + "You cannot do this while in a match!");
			}
			player.openInventory(inv);
			return true;
			
		}
		
		return false;
	}
	
	public void createClassGUI() {
		inv = Bukkit.createInventory(null, 9, ChatColor.RED + ChatColor.BOLD.toString() + "CHOOSE A QUEUE");
		
		ItemStack item = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + ChatColor.GREEN.toString() + "CASUAL");
		item.setItemMeta(meta);
		inv.setItem(4, item);
	}
}

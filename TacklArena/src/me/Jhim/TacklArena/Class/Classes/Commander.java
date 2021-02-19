package me.Jhim.TacklArena.Class.Classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Jhim.TacklArena.Class.ClassConfig;

public class Commander {
	private ItemStack[] invItems = new ItemStack[4];
	private ItemStack[] armor = new ItemStack[4];
	ClassConfig classConfig;
	
	public Commander(ClassConfig classConfig) {
		this.classConfig = classConfig;
		createClass();
	}
	public Inventory commanderPreview;
	
	public void equipClass(Player player) {
		player.getInventory().addItem(invItems);
		player.getInventory().setArmorContents(armor);
	}
		
	private void createClass() {
		commanderPreview = Bukkit.createInventory(null, 54, ChatColor.RED + ChatColor.BOLD.toString() + "Commander");
		
		// Commander Sword
		ItemStack weapon1 = new ItemStack(Material.IRON_SWORD);
		ItemMeta weapon1Meta = weapon1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		weapon1Meta.setDisplayName(ChatColor.RED + "Commander's Sword");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Leap");
		lore.add(ChatColor.GOLD + "Launch yourself forward");
		lore.add(ChatColor.BLUE + "Cooldown: "+ classConfig.getConfig().getInt("commander.cooldowns.leap") + "s");
		weapon1Meta.setLore(lore);
		weapon1Meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
		weapon1Meta.setUnbreakable(true);
		weapon1.setItemMeta(weapon1Meta);
		invItems[0] = weapon1;
		lore.clear();
		commanderPreview.setItem(48, weapon1);
		
		// Boost Ability
		ItemStack ability1 = new ItemStack(Material.BLUE_DYE);
		ItemMeta ability1Meta = ability1.getItemMeta();
		ability1Meta.setDisplayName(ChatColor.RED + "Boost");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Boost");
		lore.add(ChatColor.GOLD + "Damage and knock your opponent into the sky");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("commander.cooldowns.boost") + "s");
		ability1Meta.setLore(lore);
		ability1Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability1Meta.setUnbreakable(true);
		ability1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability1.setItemMeta(ability1Meta);
		invItems[1] = ability1;
		lore.clear();
		commanderPreview.setItem(49, ability1);
		
		// Health Ability
		ItemStack ability2 = new ItemStack(Material.PINK_DYE);
		ItemMeta ability2Meta = ability2.getItemMeta();
		ability2Meta.setDisplayName(ChatColor.RED + "Health");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Health");
		lore.add(ChatColor.GOLD + "Instantly regain 3 hearts");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("commander.cooldowns.health") + "s");
		ability2Meta.setLore(lore);
		ability2Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability2Meta.setUnbreakable(true);
		ability2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability2.setItemMeta(ability2Meta);
		invItems[2] = ability2;
		lore.clear();
		commanderPreview.setItem(50, ability2);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
		invItems[3] = steak;
		
		// Iron Helmet
		ItemStack helm = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta helmMeta = helm.getItemMeta();
		helmMeta.setDisplayName(ChatColor.RED + "Commander's Helmet");
		lore.add(" ");
		helmMeta.setLore(lore);
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		helmMeta.setUnbreakable(true);
		helm.setItemMeta(helmMeta);
		armor[3] = helm;
		lore.clear();
		commanderPreview.setItem(13, helm);
		
		// Iron Chestplate
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.RED + "Commander's Chestplate");
		lore.add(" ");
		chestMeta.setLore(lore);
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		chestMeta.setUnbreakable(true);
		chest.setItemMeta(chestMeta);
		armor[2] = chest;
		lore.clear();
		commanderPreview.setItem(22, chest);
		
		// Iron Leggings
		ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta legsMeta = legs.getItemMeta();
		legs.setType(Material.IRON_LEGGINGS);
		legsMeta.setDisplayName(ChatColor.RED + "Commander's Leggings");
		lore.add(" ");
		legsMeta.setLore(lore);
		legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		legsMeta.setUnbreakable(true);
		legs.setItemMeta(legsMeta);
		armor[1] = legs;
		lore.clear();
		commanderPreview.setItem(31, legs);
		
		// Iron Boots
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Commander's Boots");
		lore.add(" ");
		bootsMeta.setLore(lore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		armor[0] = boots;
		lore.clear();
		commanderPreview.setItem(40, boots);
		
	}
	
}

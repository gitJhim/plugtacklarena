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

public class Heavy {
	private ItemStack[] invItems = new ItemStack[4];
	private ItemStack[] armor = new ItemStack[4];
	ClassConfig classConfig;
	
	public Heavy(ClassConfig classConfig) {
		this.classConfig = classConfig;
		createClass();
	}
	public Inventory heavyPreview;
	
	public void equipClass(Player player) {
		player.getInventory().addItem(invItems);
		player.getInventory().setArmorContents(armor);
	}
		
	private void createClass() {
		heavyPreview = Bukkit.createInventory(null, 54, ChatColor.RED + ChatColor.BOLD.toString() + "Heavy");
		
		// Heavy Sword
		ItemStack weapon1 = new ItemStack(Material.STONE_SWORD);
		ItemMeta weapon1Meta = weapon1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		weapon1Meta.setDisplayName(ChatColor.RED + "Heavy's Broadsword");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Fury");
		lore.add(ChatColor.GOLD + "Recieve strength for 5 seconds");
		lore.add(ChatColor.BLUE + "Cooldown: "+ classConfig.getConfig().getInt("heavy.cooldowns.fury") + "s");
		weapon1Meta.setLore(lore);
		weapon1Meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
		weapon1Meta.setUnbreakable(true);
		weapon1.setItemMeta(weapon1Meta);
		invItems[0] = weapon1;
		lore.clear();
		heavyPreview.setItem(48, weapon1);
		
		// Bulk Ability
		ItemStack ability1 = new ItemStack(Material.RED_DYE);
		ItemMeta ability1Meta = ability1.getItemMeta();
		ability1Meta.setDisplayName(ChatColor.RED + "Bulk");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Bulk");
		lore.add(ChatColor.GOLD + "Recieve absorbtion for 20 seconds");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("heavy.cooldowns.bulk") + "s");
		ability1Meta.setLore(lore);
		ability1Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability1Meta.setUnbreakable(true);
		ability1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability1.setItemMeta(ability1Meta);
		invItems[1] = ability1;
		lore.clear();
		heavyPreview.setItem(49, ability1);
		
		// Ground Ability
		ItemStack ability2 = new ItemStack(Material.PINK_DYE);
		ItemMeta ability2Meta = ability2.getItemMeta();
		ability2Meta.setDisplayName(ChatColor.RED + "Ground");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Ground");
		lore.add(ChatColor.GOLD + "Become invincable but you cannot move for 5 seconds");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("heavy.cooldowns.ground") + "s");
		ability2Meta.setLore(lore);
		ability2Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability2Meta.setUnbreakable(true);
		ability2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability2.setItemMeta(ability2Meta);
		invItems[2] = ability2;
		lore.clear();
		heavyPreview.setItem(50, ability2);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
		invItems[3] = steak;
		
		// Iron Helmet
		ItemStack helm = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmMeta = helm.getItemMeta();
		helmMeta.setDisplayName(ChatColor.RED + "Heavy's Helmet");
		lore.add(" ");
		helmMeta.setLore(lore);
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		helmMeta.setUnbreakable(true);
		helm.setItemMeta(helmMeta);
		armor[3] = helm;
		lore.clear();
		heavyPreview.setItem(13, helm);
		
		// Iron Chestplate
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.RED + "Heavy's Chestplate");
		lore.add(" ");
		chestMeta.setLore(lore);
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		chestMeta.setUnbreakable(true);
		chest.setItemMeta(chestMeta);
		armor[2] = chest;
		lore.clear();
		heavyPreview.setItem(22, chest);
		
		// Iron Leggings
		ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta legsMeta = legs.getItemMeta();
		legs.setType(Material.IRON_LEGGINGS);
		legsMeta.setDisplayName(ChatColor.RED + "Heavy's Leggings");
		lore.add(" ");
		legsMeta.setLore(lore);
		legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		legsMeta.setUnbreakable(true);
		legs.setItemMeta(legsMeta);
		armor[1] = legs;
		lore.clear();
		heavyPreview.setItem(31, legs);
		
		// Iron Boots
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Heavy's Boots");
		lore.add(" ");
		bootsMeta.setLore(lore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		armor[0] = boots;
		lore.clear();
		heavyPreview.setItem(40, boots);
		
	}
}

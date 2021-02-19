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

public class Technician {
	private ItemStack[] invItems = new ItemStack[4];
	private ItemStack[] armor = new ItemStack[4];
	ClassConfig classConfig;
	
	public Technician(ClassConfig classConfig) {
		this.classConfig = classConfig;
		createClass();
	}
	public Inventory technicianPreview;
	
	public void equipClass(Player player) {
		player.getInventory().addItem(invItems);
		player.getInventory().setArmorContents(armor);
	}
		
	private void createClass() {
		technicianPreview = Bukkit.createInventory(null, 54, ChatColor.GOLD + ChatColor.BOLD.toString() + "Technician");
		
		// Technician Sword
		ItemStack weapon1 = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta weapon1Meta = weapon1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		weapon1Meta.setDisplayName(ChatColor.RED + "Technician's Wrench");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Guard");
		lore.add(ChatColor.GOLD + "Recieve resistiance for 8 seconds");
		lore.add(ChatColor.BLUE + "Cooldown: "+ classConfig.getConfig().getInt("technician.cooldowns.guard") + "s");
		weapon1Meta.setLore(lore);
		weapon1Meta.addEnchant(Enchantment.DAMAGE_ALL, 7, false);
		weapon1Meta.setUnbreakable(true);
		weapon1.setItemMeta(weapon1Meta);
		invItems[0] = weapon1;
		lore.clear();
		technicianPreview.setItem(48, weapon1);
		
		// Shift Ability
		ItemStack ability1 = new ItemStack(Material.RED_DYE);
		ItemMeta ability1Meta = ability1.getItemMeta();
		ability1Meta.setDisplayName(ChatColor.RED + "Shift");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Shift");
		lore.add(ChatColor.GOLD + "Thrust your opponent in a random direction");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("technician.cooldowns.shift") + "s");
		ability1Meta.setLore(lore);
		ability1Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability1Meta.setUnbreakable(true);
		ability1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability1.setItemMeta(ability1Meta);
		invItems[1] = ability1;
		lore.clear();
		technicianPreview.setItem(49, ability1);
		
		// Health Ability
		ItemStack ability2 = new ItemStack(Material.PINK_DYE);
		ItemMeta ability2Meta = ability2.getItemMeta();
		ability2Meta.setDisplayName(ChatColor.RED + "Radar");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Radar");
		lore.add(ChatColor.GOLD + "Instantly teleport to your opponent");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("technician.cooldowns.radar") + "s");
		ability2Meta.setLore(lore);
		ability2Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability2Meta.setUnbreakable(true);
		ability2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability2.setItemMeta(ability2Meta);
		invItems[2] = ability2;
		lore.clear();
		technicianPreview.setItem(50, ability2);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
		invItems[3] = steak;
		
		// Iron Helmet
		ItemStack helm = new ItemStack(Material.GOLDEN_HELMET);
		ItemMeta helmMeta = helm.getItemMeta();
		helmMeta.setDisplayName(ChatColor.RED + "Technician's Helmet");
		lore.add(" ");
		helmMeta.setLore(lore);
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		helmMeta.setUnbreakable(true);
		helm.setItemMeta(helmMeta);
		armor[3] = helm;
		lore.clear();
		technicianPreview.setItem(13, helm);
		
		// Iron Chestplate
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.RED + "Technician's Chestplate");
		lore.add(" ");
		chestMeta.setLore(lore);
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		chestMeta.setUnbreakable(true);
		chest.setItemMeta(chestMeta);
		armor[2] = chest;
		lore.clear();
		technicianPreview.setItem(22, chest);
		
		// Iron Leggings
		ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta legsMeta = legs.getItemMeta();
		legs.setType(Material.IRON_LEGGINGS);
		legsMeta.setDisplayName(ChatColor.RED + "Technician's Leggings");
		lore.add(" ");
		legsMeta.setLore(lore);
		legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		legsMeta.setUnbreakable(true);
		legs.setItemMeta(legsMeta);
		armor[1] = legs;
		lore.clear();
		technicianPreview.setItem(31, legs);
		
		// Iron Boots
		ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Technician's Boots");
		lore.add(" ");
		bootsMeta.setLore(lore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		armor[0] = boots;
		lore.clear();
		technicianPreview.setItem(40, boots);
		
	}
	
}

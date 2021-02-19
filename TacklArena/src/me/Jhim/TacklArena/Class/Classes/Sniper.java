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

public class Sniper {
	private ItemStack[] invItems = new ItemStack[6];
	private ItemStack[] armor = new ItemStack[4];
	public Inventory sniperPreview;
	ClassConfig classConfig;

	public Sniper(ClassConfig classConfig) {
		this.classConfig = classConfig;
		createClass();
	}
	
	public void equipClass(Player player) {
		player.getInventory().addItem(invItems);
		player.getInventory().setArmorContents(armor);
	}
	
	public void createClass() {
		sniperPreview = Bukkit.createInventory(null, 54, ChatColor.GREEN + ChatColor.BOLD.toString() + "Sniper");
		
		// Sniper Bow
		ItemStack weapon1 = new ItemStack(Material.BOW);
		ItemMeta weapon1Meta = weapon1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		weapon1Meta.setDisplayName(ChatColor.GREEN + "Sniper's Bow");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Left Click Ability: Propel");
		lore.add(ChatColor.GOLD + "Shoots a high knockback arrow");
		lore.add(ChatColor.BLUE + "Cooldown: " + classConfig.getConfig().getInt("sniper.cooldowns.propel") + "s");
		weapon1Meta.setLore(lore);
		weapon1Meta.addEnchant(Enchantment.ARROW_DAMAGE, 7, false);
		weapon1Meta.addEnchant(Enchantment.PIERCING, 3, false);
		weapon1Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		weapon1Meta.setUnbreakable(true);
		weapon1.setItemMeta(weapon1Meta);
		invItems[0] = weapon1;
		lore.clear();
		sniperPreview.setItem(48, weapon1);

		// Sniper Pocket Knife
		ItemStack weapon2 = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta weapon2Meta = weapon2.getItemMeta();
		weapon2Meta.setDisplayName(ChatColor.GREEN + "Sniper's Pocket Knife");
		lore.add(" ");
		weapon2Meta.setLore(lore);
		weapon2Meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
		weapon2Meta.setUnbreakable(true);
		weapon2.setItemMeta(weapon2Meta);
		invItems[1] = weapon2;
		lore.clear();
		sniperPreview.setItem(49, weapon2);

		// Pyro
		ItemStack ability1 = new ItemStack(Material.RED_DYE);
		ItemMeta ability1Meta = ability1.getItemMeta();
		ability1Meta.setDisplayName(ChatColor.RED + "Pyro");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Pyro");
		lore.add(ChatColor.GOLD + "Shoots a flame arrow");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("sniper.cooldowns.pyro") + "s");
		ability1Meta.setLore(lore);
		ability1Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability1Meta.setUnbreakable(true);
		ability1.setItemMeta(ability1Meta);
		invItems[2] = ability1;
		lore.clear();
		sniperPreview.setItem(50, ability1);
		
		// Warp
		ItemStack ability2 = new ItemStack(Material.PURPLE_DYE);
		ItemMeta ability2Meta = ability2.getItemMeta();
		ability2Meta.setDisplayName(ChatColor.RED + "Warp");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Warp");
		lore.add(ChatColor.GOLD + "Shoots an arrow and teleports to wherever it lands");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("sniper.cooldowns.warp") + "s");
		ability2Meta.setLore(lore);
		ability2Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability2Meta.setUnbreakable(true);
		ability2.setItemMeta(ability2Meta);
		invItems[3] = ability2;
		lore.clear();
		sniperPreview.setItem(51, ability2);
		
		ItemStack arrow = new ItemStack(Material.ARROW);
		invItems[4] = arrow;
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
		invItems[5] = steak;
		
		// Iron Helmet
		ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta helmMeta = helm.getItemMeta();
		helmMeta.setDisplayName(ChatColor.RED + "Sniper's Helmet");
		lore.add(" ");
		helmMeta.setLore(lore);
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		helmMeta.setUnbreakable(true);
		helm.setItemMeta(helmMeta);
		armor[3] = helm;
		lore.clear();
		sniperPreview.setItem(13, helm);
		
		// Diamond Chestplate
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.RED + "Sniper's Chestplate");
		lore.add(" ");
		chestMeta.setLore(lore);
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		chestMeta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
		chestMeta.setUnbreakable(true);
		chest.setItemMeta(chestMeta);
		armor[2] = chest;
		lore.clear();
		sniperPreview.setItem(22, chest);
		
		// Iron Leggings
		ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemMeta legsMeta = legs.getItemMeta();
		legsMeta.setDisplayName(ChatColor.RED + "Sniper's Leggings");
		lore.add(" ");
		legsMeta.setLore(lore);
		legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		legsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
		legsMeta.setUnbreakable(true);
		legs.setItemMeta(legsMeta);
		armor[1] = legs;
		lore.clear();
		sniperPreview.setItem(31, legs);
		
		// Iron Boots
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Sniper's Boots");
		lore.add(" ");
		bootsMeta.setLore(lore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		armor[0] = boots;
		lore.clear();
		sniperPreview.setItem(40, boots);
	}
}

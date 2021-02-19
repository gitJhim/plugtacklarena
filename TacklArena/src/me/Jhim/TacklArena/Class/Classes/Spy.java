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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Jhim.TacklArena.Class.ClassConfig;

public class Spy {

	private ItemStack[] invItems = new ItemStack[4];
	private ItemStack[] armor = new ItemStack[4];
	public Inventory spyPreview;
	ClassConfig classConfig;
	
	public Spy(ClassConfig classConfig) {
		this.classConfig = classConfig;
		createClass();
	}
	
	public void equipClass(Player player) {
		PotionEffect pe = new PotionEffect(PotionEffectType.ABSORPTION, 100000, 1);
		player.addPotionEffect(pe);
		player.getInventory().addItem(invItems);
		player.getInventory().setArmorContents(armor);
	}
	
	public void createClass() {
		spyPreview = Bukkit.createInventory(null, 54, ChatColor.BLUE + ChatColor.BOLD.toString() + "Spy");
		
		// Spy Knife
		ItemStack weapon1 = new ItemStack(Material.GOLDEN_SWORD);
		ItemMeta weapon1Meta = weapon1.getItemMeta();
		List<String> lore = new ArrayList<String>();
		weapon1Meta.setDisplayName(ChatColor.GREEN + "Spy's Knife");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Velocity");
		lore.add(ChatColor.GOLD + "Recieve speed for 8 seconds");
		lore.add(ChatColor.BLUE + "Cooldown: " + classConfig.getConfig().getInt("spy.cooldowns.velocity") + "s");
		weapon1Meta.setLore(lore);
		weapon1Meta.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
		weapon1Meta.setUnbreakable(true);
		weapon1.setItemMeta(weapon1Meta);
		invItems[0] = weapon1;
		lore.clear();
		spyPreview.setItem(48, weapon1);
		
		// Spy Pen
		ItemStack ability1 = new ItemStack(Material.STICK);
		ItemMeta ability1Meta = ability1.getItemMeta();
		ability1Meta.setDisplayName(ChatColor.RED + "Spy Pen");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "On-Hit Ability: Blackout");
		lore.add(ChatColor.GOLD + "Hit player gets knocked back and recieves blindness and glowing for 7 seconds");
		ability1Meta.setLore(lore);
		ability1Meta.addEnchant(Enchantment.KNOCKBACK, 2, false);
		ability1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability1Meta.setUnbreakable(true);
		ability1.setItemMeta(ability1Meta);
		invItems[1] = ability1;
		lore.clear();
		spyPreview.setItem(49, ability1);
		
		// Cloak
		ItemStack ability2 = new ItemStack(Material.GRAY_DYE);
		ItemMeta ability2Meta = ability2.getItemMeta();
		ability2Meta.setDisplayName(ChatColor.RED + "Cloak");
		lore.add(" ");
		lore.add(ChatColor.GOLD + "Right Click Ability: Cloak");
		lore.add(ChatColor.GOLD + "Turn completly invisible but vunerable for 5 seconds");
		lore.add(ChatColor.GOLD + "Cooldown: " + classConfig.getConfig().getInt("spy.cooldowns.cloak") + "s");
		ability2Meta.setLore(lore);
		ability2Meta.addEnchant(Enchantment.LUCK, 1, false);
		ability2Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ability2Meta.setUnbreakable(true);
		ability2.setItemMeta(ability2Meta);
		invItems[2] = ability2;
		lore.clear();
		spyPreview.setItem(50, ability2);
		
		ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
		invItems[3] = steak;
		
		// Iron Helmet
		ItemStack helm = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta helmMeta = helm.getItemMeta();
		helmMeta.setDisplayName(ChatColor.RED + "Spy's Helmet");
		lore.add(" ");
		helmMeta.setLore(lore);
		helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		helmMeta.setUnbreakable(true);
		helm.setItemMeta(helmMeta);
		armor[3] = helm;
		lore.clear();
		spyPreview.setItem(13, helm);
		
		// Diamond Chestplate
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestMeta = chest.getItemMeta();
		chestMeta.setDisplayName(ChatColor.RED + "Spy's Chestplate");
		lore.add(" ");
		chestMeta.setLore(lore);
		chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
		chestMeta.setUnbreakable(true);
		chest.setItemMeta(chestMeta);
		armor[2] = chest;
		lore.clear();
		spyPreview.setItem(22, chest);
		
		// Iron Leggings
		ItemStack legs = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemMeta legsMeta = legs.getItemMeta();
		legsMeta.setDisplayName(ChatColor.RED + "Spy's Leggings");
		lore.add(" ");
		legsMeta.setLore(lore);
		legsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		legsMeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
		legsMeta.setUnbreakable(true);
		legs.setItemMeta(legsMeta);
		armor[1] = legs;
		lore.clear();
		spyPreview.setItem(31, legs);
		
		// Iron Boots
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Spy's Boots");
		lore.add(" ");
		bootsMeta.setLore(lore);
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);
		armor[0] = boots;
		lore.clear();
		spyPreview.setItem(40, boots);
	}
}

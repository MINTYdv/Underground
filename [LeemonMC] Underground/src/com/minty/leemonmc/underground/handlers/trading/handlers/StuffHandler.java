package com.minty.leemonmc.underground.handlers.trading.handlers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;

public class StuffHandler {

	public static void stuffPlayer(Player player)
	{
		UndergroundData data = Underground.getInstance().getUndergroundDataHandler().getPlayerData(player);
		
		player.getInventory().setItem(0, getSword(data.getSwordLevel()));
		player.getInventory().setItem(1, getPickaxe(data.getPickaxeLevel()));
		
		player.getInventory().setHelmet(getHelmet(player, data.getArmorLevel()));
		player.getInventory().setChestplate(getChestplate(player, data.getArmorLevel()));
		player.getInventory().setLeggings(getLeggings(player, data.getArmorLevel()));
		player.getInventory().setBoots(getBoots(player, data.getArmorLevel()));
	}
	
	/* Level 3 maximum & Static */
	public static ItemStack getChestplate(Player player, int level)
	{
		ItemStack it = Underground.getInstance().getGameApi().getTeamsManager().getTeam(player).getColoredArmor(Material.LEATHER_CHESTPLATE);
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6Plastron §7- §eNiveau " + level);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		
		it.setItemMeta(meta);
		return it;
	}
	
	/* Level 3 maximum */
	public static ItemStack getLeggings(Player player, int level)
	{
		if(level == 1)
		{
			ItemStack it = Underground.getInstance().getGameApi().getTeamsManager().getTeam(player).getColoredArmor(Material.LEATHER_LEGGINGS);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Jambières §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		if(level == 2)
		{
			ItemStack it = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Jambières §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		
		// Level 3
		ItemStack it = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6Jambières §7- §eNiveau " + level);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		it.setItemMeta(meta);
		return it;
	}
	
	/* Level 3 maximum */
	public static ItemStack getBoots(Player player, int level)
	{
		if(level == 1)
		{
			ItemStack it = Underground.getInstance().getGameApi().getTeamsManager().getTeam(player).getColoredArmor(Material.LEATHER_BOOTS);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Bottes §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		if(level == 2)
		{
			ItemStack it = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Bottes §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		
		// Level 3
		ItemStack it = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6Bottes §7- §eNiveau " + level);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		it.setItemMeta(meta);
		return it;
	}
	
	/* Level 3 maximum */
	public static ItemStack getHelmet(Player player, int level)
	{
		if(level == 1)
		{
			ItemStack it = Underground.getInstance().getGameApi().getTeamsManager().getTeam(player).getColoredArmor(Material.LEATHER_HELMET);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Casque §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		if(level == 2)
		{
			ItemStack it = new ItemStack(Material.CHAINMAIL_HELMET, 1);
			ItemMeta meta = it.getItemMeta();
			
			meta.setDisplayName("§6Casque §7- §eNiveau " + level);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
			
			it.setItemMeta(meta);
			return it;
		}
		
		// Level 3
		ItemStack it = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6Casque §7- §eNiveau " + level);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		it.setItemMeta(meta);
		return it;
	}
	
	public static ItemStack getSword(int level)
	{
		if(level == 1)
		{
			ItemStack sword = new ItemStack(Material.STONE_SWORD, 1, (byte) 0);
			ItemMeta meta = sword.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			meta.setDisplayName("§6Épée §7- §eNiveau " + level);
			
			sword.setItemMeta(meta);
			return sword;
		}
		if(level == 2)
		{
			ItemStack sword = new ItemStack(Material.STONE_SWORD, 1, (byte) 0);
			ItemMeta meta = sword.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			meta.setDisplayName("§6Épée §7- §eNiveau " + level);
			
			sword.setItemMeta(meta);
			return sword;
		}
		if(level == 3)
		{
			ItemStack sword = new ItemStack(Material.IRON_SWORD, 1, (byte) 0);
			ItemMeta meta = sword.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
			meta.setDisplayName("§6Épée §7- §eNiveau " + level);
			
			sword.setItemMeta(meta);
			return sword;
		}
		if(level == 4)
		{
			ItemStack sword = new ItemStack(Material.IRON_SWORD, 1, (byte) 0);
			ItemMeta meta = sword.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
			meta.setDisplayName("§6Épée §7- §eNiveau " + level);
			
			sword.setItemMeta(meta);
			return sword;
		}
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1, (byte) 0);
		ItemMeta meta = sword.getItemMeta();
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.spigot().setUnbreakable(true);
		
		meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		meta.setDisplayName("§6Épée §7- §eNiveau " + level);
		
		sword.setItemMeta(meta);
		return sword;
	}
	
	public static ItemStack getBow(int level)
	{
		// Maximum niveau 5
		ItemStack bow = new ItemStack(Material.BOW, 1, (byte) 0);
		ItemMeta meta = bow.getItemMeta();
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.spigot().setUnbreakable(true);
		
		if(level == 1)
		{
			//meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
			meta.setDisplayName("§6Arc §7- §eNiveau " + level);
			
			bow.setItemMeta(meta);
			return bow;
		}
		
		if(level == 2)
		{
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			meta.setDisplayName("§6Arc §7- §eNiveau " + level);
			
			bow.setItemMeta(meta);
			return bow;
		}
		
		if(level == 3)
		{
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
			meta.setDisplayName("§6Arc §7- §eNiveau " + level);
			
			bow.setItemMeta(meta);
			return bow;
		}
		
		if(level == 4)
		{
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
			meta.setDisplayName("§6Arc §7- §eNiveau " + level);
			
			bow.setItemMeta(meta);
			return bow;
		}
		
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
		meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		
		meta.setDisplayName("§6Arc §7- §eNiveau " + level);
		
		bow.setItemMeta(meta);
		return bow;
	}
	
	public static ItemStack getPickaxe(int level)
	{
		if(level == 1)
		{
			ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1, (byte) 0);
			ItemMeta meta = pickaxe.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
			meta.setDisplayName("§6Pioche §7- §eNiveau " + level);
			
			pickaxe.setItemMeta(meta);
			return pickaxe;
		}
		if(level == 2)
		{
			ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE, 1, (byte) 0);
			ItemMeta meta = pickaxe.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
			meta.setDisplayName("§6Pioche §7- §eNiveau " + level);
			
			pickaxe.setItemMeta(meta);
			return pickaxe;
		}
		if(level == 3)
		{
			ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1, (byte) 0);
			ItemMeta meta = pickaxe.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DIG_SPEED, 2, true);
			meta.setDisplayName("§6Pioche §7- §eNiveau " + level);
			
			pickaxe.setItemMeta(meta);
			return pickaxe;
		}
		if(level == 4)
		{
			ItemStack pickaxe = new ItemStack(Material.GOLD_PICKAXE, 1, (byte) 0);
			ItemMeta meta = pickaxe.getItemMeta();
			
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			meta.spigot().setUnbreakable(true);
			
			meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
			meta.setDisplayName("§6Pioche §7- §eNiveau " + level);
			
			pickaxe.setItemMeta(meta);
			return pickaxe;
		}
		
		ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1, (byte) 0);
		ItemMeta meta = pickaxe.getItemMeta();
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.spigot().setUnbreakable(true);
		
		meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
		meta.setDisplayName("§6Pioche §7- §eNiveau " + level);
		
		pickaxe.setItemMeta(meta);
		return pickaxe;
	}
	
}

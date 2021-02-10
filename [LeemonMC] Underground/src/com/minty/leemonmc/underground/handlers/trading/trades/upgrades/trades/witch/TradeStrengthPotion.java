package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeStrengthPotion extends Trade {
	
	private Underground main = Underground.getInstance();
	
	public TradeStrengthPotion(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 90;
	}
	
	@Override
	public String getName()
	{
		return "§6Potion de force";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Si vous ne pouvez pas vous cacher, \n §7il ne vous reste qu'une seule solution...";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		ItemStack potion = new ItemStack(Material.POTION);
		
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60, 1 - 1);
		
		meta.setDisplayName("§fPotion de force");
		meta.addCustomEffect(potionEffect, true);
		
		potion.setItemMeta(meta);
		return potion;
	}
	
	@Override
	public void purchase(Player player)
	{
		ItemStack potion = new ItemStack(Material.POTION);
		
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60, 1 - 1);
		
		meta.setDisplayName("§fPotion de force");
		meta.addCustomEffect(potionEffect, true);
		
		potion.setItemMeta(meta);
		player.getInventory().addItem(potion);
	}
	
}

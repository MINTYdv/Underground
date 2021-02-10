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

public class TradePoisonPotion extends Trade {
	
	private Underground main = Underground.getInstance();
	
	public TradePoisonPotion(TradeCategory category)
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
		return "§6Potion de poison";
	}
	
	@Override
	public String getDescription()
	{
		return "§7La sorcière la garde d'habitude en réserve pour \n §7les clients qui partiraient sans payer...";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		ItemStack potion = new ItemStack(Material.SPLASH_POTION);
		
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, 20 * 15, 1 - 1);
		
		meta.setDisplayName("§fPotion de poison");
		meta.addCustomEffect(potionEffect, true);
		
		potion.setItemMeta(meta);
		return potion;
	}
	
	@Override
	public void purchase(Player player)
	{
		ItemStack potion = new ItemStack(Material.SPLASH_POTION);
		
		PotionMeta meta = (PotionMeta) potion.getItemMeta();
		PotionEffect potionEffect = new PotionEffect(PotionEffectType.POISON, 20 * 15, 1 - 1);
		
		meta.setDisplayName("§fPotion de poison");
		meta.addCustomEffect(potionEffect, true);
		
		potion.setItemMeta(meta);
		player.getInventory().addItem(potion);
	}
	
}

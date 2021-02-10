package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.archer;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.handlers.StuffHandler;

public class TradeBow extends Trade {

	int level;
	int price;
	
	public TradeBow(TradeCategory category, int _level, int _price)
	{
		super(category);
		price = _price;
		level = _level;
	}
	
	@Override
	public Integer getPrice()
	{
		return price;
	}
	
	@Override
	public String getName()
	{
		return "§6Arc niveau " + level;
	}
	
	@Override
	public String getDescription()
	{
		return "§7Améliore votre §earc §7au §eniveau " + level + "§7.";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return StuffHandler.getBow(level);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.sendMessage("§6§lUnderground §f» §7Vous avez acheté un arc de §6niveau " + level + " §7!");
		player.getInventory().addItem(StuffHandler.getBow(level));
	}
	
}

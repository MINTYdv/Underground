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

public class TradeArrows extends Trade {
	
	public TradeArrows(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 60;
	}
	
	@Override
	public String getName()
	{
		return "§6Flèches §7(x16)";
	}
	
	@Override
	public String getDescription()
	{
		return "§7De quoi redonner une seconde vie à §evotre carqois §7!";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.ARROW, 16);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
	}
	
}

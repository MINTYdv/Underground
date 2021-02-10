package com.minty.leemonmc.underground.handlers.trading.core;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Trade {

	private TradeCategory category;
	
	public Trade(TradeCategory category)
	{
		this.category = category;
	}
	
	public Integer getPrice()
	{
		return 666;
	}
	
	public String getName()
	{
		return "§6Nom par défaut";
	}
	
	public String getDescription()
	{
		return "§6Desc par défaut";
	}
	
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	public TradeCategory getCategory()
	{
		return category;
	}
	
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.STONE, 1);
	}
	
	public void purchase(Player player)
	{
		
	}
	
}

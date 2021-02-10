package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeEnderpearl extends Trade {
	
	public TradeEnderpearl(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 110;
	}
	
	@Override
	public String getName()
	{
		return "§6Enderpearl";
	}
	
	@Override
	public String getDescription()
	{
		return "";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.ENDER_PEARL);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
	}
	
}

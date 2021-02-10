package com.minty.leemonmc.underground.handlers.trading.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TradeCategory {

	@Deprecated
	private List<Trade> trades = new ArrayList<>();
	
	public TradeCategory() {
		setup();
	}
	
	public void setup() {
		trades = new ArrayList<>();
	}
	
	public ItemStack getIcon()
	{
		return new ItemStack(Material.BARRIER, 1);
	}
	
	public String getName() {
		return "§cname";
	}
	
	public String getDescription() {
		return "§cdesc";
	}
	
	public void register(Trade trade)
	{
		trades.add(trade);
	}
	
	public List<Trade> getTrades() {
		return trades;
	}
	
}

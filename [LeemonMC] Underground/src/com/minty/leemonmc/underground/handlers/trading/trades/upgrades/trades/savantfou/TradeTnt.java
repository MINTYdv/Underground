package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeTnt extends Trade {
	
	private Underground main = Underground.getInstance();
	private ItemStack item = new ItemStack(Material.STONE, 1);
	
	public TradeTnt(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 80;
	}
	
	@Override
	public String getName()
	{
		return "§6TNT §7(x1)";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Les boomers minent... Mais nous on fait tout sauter !";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.TNT, 1);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.getInventory().addItem(new ItemStack(Material.TNT, 1));
	}
	
}

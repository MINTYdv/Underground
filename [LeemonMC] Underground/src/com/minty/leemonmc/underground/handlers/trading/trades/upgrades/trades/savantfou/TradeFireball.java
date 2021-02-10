package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeFireball extends Trade {
	
	public TradeFireball(TradeCategory category)
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
		return "§6Boule de feu";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Tout droit issue du nether !";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.FIREBALL, 1);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.getInventory().addItem(new ItemStack(Material.FIREBALL, 1));
	}
	
}

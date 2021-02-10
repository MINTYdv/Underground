package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeWood extends Trade {
	
	private Underground main = Underground.getInstance();
	
	public TradeWood(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 100;
	}
	
	@Override
	public String getName()
	{
		return "§6Blocs de qualité moyenne §7(x8)";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Quelque chose de §eplus résistant §7comparé \n §7au tiers précédent.";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.WOOD, 8);
	}
	
	@Override
	public void purchase(Player player)
	{
		Team team = main.getGameApi().getTeamsManager().getTeam(player);
		player.getInventory().addItem(new ItemStack(Material.WOOD, 8));
	}
	
}

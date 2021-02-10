package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeObsi extends Trade {
	
	private Underground main = Underground.getInstance();
	
	public TradeObsi(TradeCategory category)
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
		return "§6Blocs de qualité suprème §7(x4)";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Le bloc §ele plus résistant §7de tous ! §7Il faudra \n §7beaucoup de temps à vos ennemis pour le casser !";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.OBSIDIAN, 4);
	}
	
	@Override
	public void purchase(Player player)
	{
		Team team = main.getGameApi().getTeamsManager().getTeam(player);
		player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
	}
	
}

package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

import net.md_5.bungee.api.ChatColor;

public class TradeClay extends Trade {
	
	private Underground main = Underground.getInstance();
	
	public TradeClay(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 40;
	}
	
	@Override
	public String getName()
	{
		return "§6Blocs de basse qualité §7(x16)";
	}
	
	@Override
	public String getDescription()
	{
		return "§7La §eréférence §7en termes de blocs. Pas très résistant \n §7mais ça fera l'affaire pour une défense médiocre !";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		Team team = main.getGameApi().getTeamsManager().getTeam(player);
		if(team.getArmorColor() == Color.BLUE)
		{
			return new ItemStack(Material.STAINED_CLAY, 16, (byte) 11);
		} else {
			return new ItemStack(Material.STAINED_CLAY, 16, (byte) 14);
		}
	}
	
	@Override
	public void purchase(Player player)
	{
		Team team = main.getGameApi().getTeamsManager().getTeam(player);
		if(team.getArmorColor() == Color.BLUE)
		{
			player.getInventory().addItem(new ItemStack(Material.STAINED_CLAY, 16, (byte) 11));
		} else {
			player.getInventory().addItem(new ItemStack(Material.STAINED_CLAY, 16, (byte) 14));
		}
	}
	
}

package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.handlers.StuffHandler;

public class UPPickLevel extends Trade {

	int level;
	int price;
	
	public UPPickLevel(TradeCategory category, int _level, int _price)
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
		return "§6Amélioration Pioche Niveau " + level;
	}
	
	@Override
	public String getDescription()
	{
		return "§7Améliore votre §epioche §7au §eniveau " + level + "§7.";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		UndergroundData data = Underground.getInstance().getUndergroundDataHandler().getPlayerData(player);
		return data.getPickaxeLevel() >= level;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return StuffHandler.getPickaxe(level);
	}
	
	@Override
	public void purchase(Player player)
	{
		UndergroundData data = Underground.getInstance().getUndergroundDataHandler().getPlayerData(player);
		player.sendMessage("§6§lUnderground §f» §7Votre §6pioche §7a été améliorée au §6niveau " + level + " §7!");
		data.setPickaxeLevel(level);
		StuffHandler.stuffPlayer(player);
	}
	
}

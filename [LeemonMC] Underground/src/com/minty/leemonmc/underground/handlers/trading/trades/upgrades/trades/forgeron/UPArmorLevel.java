package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.handlers.StuffHandler;

public class UPArmorLevel extends Trade {

	int level;
	int price;
	
	public UPArmorLevel(TradeCategory category, int _level, int _price)
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
		return "§6Amélioration d'armure Niveau " + level;
	}
	
	@Override
	public String getDescription()
	{
		return "§7Améliore votre §earmure §7au §eniveau " + level + "§7.";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		UndergroundData data = Underground.getInstance().getUndergroundDataHandler().getPlayerData(player);
		return data.getArmorLevel() >= level;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		if(level == 1) {
			return new ItemStack(Material.LEATHER_CHESTPLATE);
		}
		if(level == 2) {
			return new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		}
		return new ItemStack(Material.IRON_CHESTPLATE);
	}
	
	@Override
	public void purchase(Player player)
	{
		UndergroundData data = Underground.getInstance().getUndergroundDataHandler().getPlayerData(player);
		player.sendMessage("§6§lUnderground §f» §7Votre §6armure §7a été améliorée au §6niveau " + level + " §7!");
		data.setArmorLevel(level);
		StuffHandler.stuffPlayer(player);
	}
	
}

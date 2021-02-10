package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.core.CoreMain;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TradeCompass extends Trade {
	
	public TradeCompass(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 400;
	}
	
	@Override
	public String getName()
	{
		return "§6Traqueur";
	}
	
	@Override
	public String getDescription()
	{
		return "§7Pour traquer les §eennemis les plus proches §7!";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		return new ItemStack(Material.COMPASS);
	}
	
	@Override
	public void purchase(Player player)
	{
		player.getInventory().addItem(CoreMain.getInstance().getGuiUtils().createItem(Material.COMPASS, "§6Traqueur", (byte) 0));
	}
	
}

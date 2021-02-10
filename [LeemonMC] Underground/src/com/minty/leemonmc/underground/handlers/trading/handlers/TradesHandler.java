package com.minty.leemonmc.underground.handlers.trading.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.guis.TGuiCategory;
import com.minty.leemonmc.underground.handlers.trading.guis.TGuiMain;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories.ArcherCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories.ConstructeurCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories.ForgeronCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories.SavantFouCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories.SorciereCategory;
public class TradesHandler {

	private Underground main;
	private List<TradeCategory> categories = new ArrayList<>();
	
	public TradesHandler(Underground _main)
	{
		main = _main;
		
		register(new ForgeronCategory());
		register(new ConstructeurCategory());
		register(new ArcherCategory());
		register(new SorciereCategory());
		register(new SavantFouCategory());
	}
	
	public void register(TradeCategory category)
	{
		getCategories().add(category);
	}
	
	public void openMainTradeMenu(Player player)
	{
		main.getApi().getGuiManager().open(player, TGuiMain.class);
	}
	
	public void openTradesCategoryMenu(Player player, TradeCategory category) 
	{
		TGuiCategory guiCategory = new TGuiCategory(category);
		main.getApi().getGuiManager().addMenu(guiCategory);
		main.getApi().getGuiManager().open(player, guiCategory.getClass());
	}
	
	public List<TradeCategory> getCategories() {
		return categories;
	}
	
}

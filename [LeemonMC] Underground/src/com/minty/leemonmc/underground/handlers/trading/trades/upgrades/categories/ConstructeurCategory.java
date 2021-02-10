package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories;

import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur.TradeClay;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur.TradeObsi;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur.TradeWood;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.constructeur.UPPickLevel;

public class ConstructeurCategory extends TradeCategory {

	@Override
	public void setup()
	{

		super.register(new UPPickLevel(this, 1, 120));
		super.register(new UPPickLevel(this, 2, 250));
		super.register(new UPPickLevel(this, 3, 350));
		super.register(new UPPickLevel(this, 4, 450));
		super.register(new UPPickLevel(this, 5, 620));
		
		super.register(new TradeClay(this));
		super.register(new TradeWood(this));
		super.register(new TradeObsi(this));
	}
	
	@Override
	public String getName()
	{
		return "Constructeur";
	}
	
	@Override
	public ItemStack getIcon()
	{
		return Underground.getInstance().getApi().getLeemonUtils().getSkull("http://textures.minecraft.net/texture/c9627be62ced7141139d3f155790a5d4356eb7b9ee95e504b3322974cbc515ea");
	}
	
	@Override
	public String getDescription()
	{
		return "§7Ici obtenez §eles meilleurs blocs du coin §7et \n §7forgez vos §epioches §7pour partir à la mine !";
	}
	
}

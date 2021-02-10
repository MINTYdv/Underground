package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories;

import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.archer.TradeArrows;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.archer.TradeBow;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPArmorLevel;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPSwordLevel;

public class ArcherCategory extends TradeCategory {

	@Override
	public void setup()
	{
		super.register(new TradeBow(this, 1, 100));
		super.register(new TradeBow(this, 2, 160));
		super.register(new TradeBow(this, 3, 210));
		super.register(new TradeBow(this, 4, 300));
		super.register(new TradeBow(this, 5, 350));
		
		super.register(new TradeArrows(this));
	}
	
	@Override
	public String getName()
	{
		return "Archer";
	}
	
	@Override
	public ItemStack getIcon()
	{
		return Underground.getInstance().getApi().getLeemonUtils().getSkull("http://textures.minecraft.net/texture/5d1c1bc63a2d59c93d07ed1ab6dae7bdd4c2e9f1a77b16dc7fb106c3545b6e47");
	}
	
	@Override
	public String getDescription()
	{
		return "§7Le §eplus sympa du village §7! Ici achetez vos §earcs \n §7et §eflèches §7pour partir chasser les bêtes ! (ou les énnemis)";
	}
	
}

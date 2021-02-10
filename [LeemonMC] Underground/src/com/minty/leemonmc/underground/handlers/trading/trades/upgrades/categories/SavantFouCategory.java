package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories;

import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeCobweb;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeCompass;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeEnderpearl;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeFireball;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeGapple;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeLadders;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeMilkBucket;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeSilverfish;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou.TradeTnt;

public class SavantFouCategory extends TradeCategory {

	@Override
	public void setup()
	{
		super.register(new TradeMilkBucket(this));
		super.register(new TradeSilverfish(this));
		super.register(new TradeTnt(this));
		
		super.register(new TradeMilkBucket(this));
		super.register(new TradeEnderpearl(this));
		super.register(new TradeCompass(this));
		super.register(new TradeLadders(this));
		super.register(new TradeGapple(this));
		super.register(new TradeCobweb(this));
		super.register(new TradeFireball(this));
	}
	
	@Override
	public String getName()
	{
		return "Savant fou";
	}
	
	@Override
	public ItemStack getIcon()
	{
		return Underground.getInstance().getApi().getLeemonUtils().getSkull("http://textures.minecraft.net/texture/fb8529283fdb687c78f289bfe10b87679046b5a48274bd3b3a3577f9e3f52589");
	}
	
	@Override
	public String getDescription()
	{
		return "§7Certains remettent en question §el'efficacité §7de ses pièges \n §7et utilitaires, mais rien de tel qu'une grosse explosion !";
	}
	
}

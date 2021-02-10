package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories;

import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPArmorLevel;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPSwordLevel;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeHealPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeInvisibilityPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeLeapPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradePoisonPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeRegenPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeSpeedPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeStrengthPotion;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.witch.TradeWeaknessPotion;

public class SorciereCategory extends TradeCategory {

	@Override
	public void setup()
	{
		this.register(new TradeInvisibilityPotion(this));
		this.register(new TradeLeapPotion(this));
		this.register(new TradeSpeedPotion(this));
		this.register(new TradeRegenPotion(this));
		this.register(new TradeStrengthPotion(this));
		
		this.register(new TradeHealPotion(this));
		this.register(new TradeWeaknessPotion(this));
		this.register(new TradePoisonPotion(this));
	}
	
	@Override
	public String getName()
	{
		return "Sorcière";
	}
	
	@Override
	public ItemStack getIcon()
	{
		return Underground.getInstance().getApi().getLeemonUtils().getSkull("http://textures.minecraft.net/texture/20e13d18474fc94ed55aeb7069566e4687d773dac16f4c3f8722fc95bf9f2dfa");
	}
	
	@Override
	public String getDescription()
	{
		return "§7Elle n'est pas très bavarde mais c'est la \n §eplus forte §7du village ! \n §7Concotez vos §epotions §7et autres §eélixirs §7!";
	}
	
}

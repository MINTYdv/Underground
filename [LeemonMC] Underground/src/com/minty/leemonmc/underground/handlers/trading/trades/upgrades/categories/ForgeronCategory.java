package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.categories;

import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPArmorLevel;
import com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.forgeron.UPSwordLevel;

public class ForgeronCategory extends TradeCategory {

	@Override
	public void setup()
	{
		super.register(new UPSwordLevel(this, 1, 0));
		super.register(new UPSwordLevel(this, 2, 110));
		super.register(new UPSwordLevel(this, 3, 150));
		super.register(new UPSwordLevel(this, 4, 190));
		super.register(new UPSwordLevel(this, 5, 220));
		
		super.register(new UPArmorLevel(this, 1, 0));
		super.register(new UPArmorLevel(this, 2, 190));
		super.register(new UPArmorLevel(this, 3, 290));
	}
	
	@Override
	public String getName()
	{
		return "Forgeron";
	}
	
	@Override
	public ItemStack getIcon()
	{
		return Underground.getInstance().getApi().getLeemonUtils().getSkull("http://textures.minecraft.net/texture/9234f59d9dcca96d7a68b153b6c301a4e043e33b51c39a0f9424047b43ba8b28");
	}
	
	@Override
	public String getDescription()
	{
		return "§7Ici forgez vous des §earmes §7pour partir au §ecombat \n §7et devenir le §emeilleur guerrier §7des §ecavernes §7!";
	}
	
}

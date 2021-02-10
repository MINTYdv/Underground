package com.minty.leemonmc.underground.handlers.trading.trades.upgrades.trades.savantfou;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class TradeSilverfish extends Trade {
	
	private Underground main = Underground.getInstance();
	
	@SuppressWarnings("deprecation")
	public TradeSilverfish(TradeCategory category)
	{
		super(category);
	}
	
	@Override
	public Integer getPrice()
	{
		return 60;
	}
	
	@Override
	public String getName()
	{
		return "§6Oeuf de Silverfish";
	}
	
	@Override
	public String getDescription()
	{
		return "§7La légende raconte qu'il est tombé dans le chaudron de la sorcière \n §7a sa naissance... \n §7Laissez le vous §eindiquer où se trouve les énnemis les plus proches §7!";
	}
	
	@Override
	public boolean isPurchased(Player player)
	{
		return false;
	}
	
	@Override
	public ItemStack getIcon(Player player)
	{
		ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, (short) 60);
		ItemMeta meta = item.getItemMeta();
		
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public void purchase(Player player)
	{
		ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SILVERFISH.getTypeId());
		
		net.minecraft.server.v1_9_R2.ItemStack cis = CraftItemStack.asNMSCopy(item);
		NBTTagCompound tag = cis.getTag();
        NBTTagCompound tagEntity = new NBTTagCompound();
        tagEntity.setString("id", "Silverfish");
        tag.set("EntityTag", tagEntity);
        
        cis.setTag(tag);
        
		player.getInventory().addItem(CraftItemStack.asBukkitCopy(cis));
	}
	
}

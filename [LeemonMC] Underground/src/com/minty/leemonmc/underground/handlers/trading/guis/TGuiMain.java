package com.minty.leemonmc.underground.handlers.trading.guis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minty.leemonmc.core.util.GuiBuilder;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TGuiMain implements GuiBuilder {

	private List<Integer> slots = new ArrayList<>();
	private Underground main = Underground.getInstance();
	private Map<Integer, TradeCategory> tradesSlots = new HashMap<>();
	
	public TGuiMain() {
		
		slots.add(11); slots.add(12); slots.add(13); slots.add(14); slots.add(15); slots.add(16); slots.add(10);
	}
	
	@Override
	public void contents(Player player, Inventory inv)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			Integer slot = slots.get(i);
			if(main.getTradesHandler().getCategories().size() <= i) {
				continue;
			}
			TradeCategory cat = main.getTradesHandler().getCategories().get(i);
			
			inv.setItem(slot, getItem(cat));
			tradesSlots.put(slot, cat);
		}
		
		inv.setItem(35 - 9, main.getApi().getGuiUtils().cancelItem());
	}

	private ItemStack getItem(TradeCategory cat)
	{
		ItemStack it = cat.getIcon();
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6" + cat.getName());
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		List<String> lore = new ArrayList<>();
		String[] split = cat.getDescription().split(" \n ");
		for(String string : split) {
			lore.add(string);
		}
		
		lore.add("");
		lore.add("§6» §eClique pour accéder à cette catégorie");
		
		meta.setLore(lore);
		it.setItemMeta(meta);
		return it;
	}
	
	@Override
	public int getSize()
	{
		return (4 * 9) - 9;
	}

	@Override
	public String name()
	{
		return "§e⍟ §6Achat §e⍟";
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack it, int slot)
	{
		if(slot == 35 - 9) {
			player.closeInventory();
		}
		
		if(tradesSlots.containsKey(slot)) {
			TradeCategory trade = tradesSlots.get(slot);
			
			main.getTradesHandler().openTradesCategoryMenu(player, trade);
		}
	}

	@Override
	public void onRightClick(Player player, Inventory inv, ItemStack it, int slot)
	{
		
		
	}

	
	
}

package com.minty.leemonmc.underground.handlers.trading.guis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minty.leemonmc.core.util.GuiBuilder;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;
import com.minty.leemonmc.underground.handlers.trading.core.Trade;
import com.minty.leemonmc.underground.handlers.trading.core.TradeCategory;

public class TGuiCategory implements GuiBuilder {

	private TradeCategory category;
	private List<Integer> slots = new ArrayList<>();
	private Underground main = Underground.getInstance();
	private Map<Integer, Trade> tradesSlots = new HashMap<>();
	
	public TGuiCategory(TradeCategory _cat) {
		category = _cat;
		
		slots.add(11); slots.add(12); slots.add(13); slots.add(14); slots.add(15); slots.add(20); slots.add(21); slots.add(22); slots.add(23); slots.add(24);
	}
	
	@Override
	public void contents(Player player, Inventory inv)
	{
		for(int i = 0; i < slots.size(); i++)
		{
			Integer slot = slots.get(i);
			if(category.getTrades().size() <= i) {
				continue;
			}
			Trade t = category.getTrades().get(i);
			
			inv.setItem(slot, getItem(player, t));
			tradesSlots.put(slot, t);
		}
		
		inv.setItem(35, main.getApi().getGuiUtils().backItem());
	}

	private ItemStack getItem(Player player, Trade trade)
	{
		ItemStack it = trade.getIcon(player);
		ItemMeta meta = it.getItemMeta();
		
		meta.setDisplayName("§6" + trade.getName());
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		List<String> lore = new ArrayList<>();
		String[] split = trade.getDescription().split(" \n ");
		for(String string : split) {
			lore.add(string);
		}
		
		if(trade.isPurchased(player)) {
			lore.add("");
			lore.add("§4» §cTu possèdes déjà cette amélioration !");
		} else {
			lore.add("");
			lore.add("§7Prix : §6" + trade.getPrice() + " niveaux");
			lore.add("");
			lore.add("§6» §eClique pour confirmer la transaction");
		}
		
		meta.setLore(lore);
		it.setItemMeta(meta);
		return it;
	}
	
	@Override
	public int getSize()
	{
		return 4 * 9;
	}

	@Override
	public String name()
	{
		return "§e⍟ §6" + category.getName() + " §e⍟";
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack it, int slot)
	{
		
		if(slot == 35) {
			main.getTradesHandler().openMainTradeMenu(player);
			return;
		}
		
		if(tradesSlots.containsKey(slot)) {
			Trade trade = tradesSlots.get(slot);
			
			UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
			if(data.getExperience() >= trade.getPrice()) {
				data.removeExperience(trade.getPrice());
				trade.purchase(player);
				main.getTradesHandler().openTradesCategoryMenu(player, category);
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADING, 1f, 1f);
				return;
			} else {
				if(trade.isPurchased(player)) {
					player.sendMessage("§6§lUnderground §f» §cTu possèdes déjà cette amélioration !");
					player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
					return;
				}
				player.sendMessage("§6§lUnderground §f» §cTu n'as pas assez de niveaux d'expérience pour éffectuer cet échange !");
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
				return;
			}
		}
	}

	@Override
	public void onRightClick(Player player, Inventory inv, ItemStack it, int slot)
	{
		
		
	}

	
	
}

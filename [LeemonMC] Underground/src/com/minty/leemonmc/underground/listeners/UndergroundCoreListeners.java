package com.minty.leemonmc.underground.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.basics.core.ServerType;
import com.minty.leemonmc.core.CoreMain;
import com.minty.leemonmc.core.events.CoreInitEvent;
import com.minty.leemonmc.core.events.GuisLoadingEvent;
import com.minty.leemonmc.core.stats.StatsDataHandler;
import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.games.events.GameStartEvent;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.trading.guis.TGuiMain;
import com.minty.leemonmc.underground.handlers.trading.handlers.StuffHandler;

public class UndergroundCoreListeners implements Listener {

	private Underground main;
	
	public UndergroundCoreListeners(Underground _underground) {
		main = _underground;
	}
		
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		
		player.sendMessage("§7§m---------------------------------------");
		player.sendMessage("");
		player.sendMessage("              §6§lUnderground");
		player.sendMessage("             §7By LeemonMC");
		player.sendMessage("");
		player.sendMessage("§f➠ §7But du jeu :");
		player.sendMessage("§7Améliorez votre équipement et allez détruire le totem");
		player.sendMessage("§7sous-terrain de l'équipe adverse ! A vos pioches !");
		player.sendMessage("");
		player.sendMessage("§7§m---------------------------------------");
	}
	
	@EventHandler
	public void onCoreInit(CoreInitEvent e)
	{
		CoreMain leemonmcMain = e.getMain();
		leemonmcMain.init(ServerType.MINIGAME, "Underground");
		
		List<String> stats = new ArrayList<>();
		stats.add("victories"); stats.add("defeats"); stats.add("kills"); stats.add("deaths"); stats.add("games"); stats.add("points"); stats.add("playtime");
		leemonmcMain.getStatsHandler().init("underground", stats);
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e)
	{
		Player player = e.getPlayer();
		if(player.getGameMode() == GameMode.SURVIVAL && main.getGameApi().getGameManager().getPlayingPlayersList().contains(player))
		{
			ItemStack dropped = e.getItemDrop().getItemStack();
			
			if(dropped.getType() == Material.WOOD_SWORD || dropped.getType() == Material.STONE_SWORD || dropped.getType() == Material.IRON_SWORD || dropped.getType() == Material.DIAMOND_SWORD)
			{
				e.setCancelled(true);
			}
			if(dropped.getType() == Material.WOOD_PICKAXE || dropped.getType() == Material.STONE_PICKAXE || dropped.getType() == Material.IRON_PICKAXE || dropped.getType() == Material.DIAMOND_PICKAXE)
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onGuis(GuisLoadingEvent e) {
		e.getGuiManager().addMenu(new TGuiMain());
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e)
	{
		if(!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		
		Player player = (Player) e.getWhoClicked();
		if(player.getGameMode() == GameMode.SURVIVAL && main.getGameApi().getGameManager().getCurrentState() == GameState.PLAYING)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		if(!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		
		Player player = (Player) e.getWhoClicked();
		if(player.getGameMode() == GameMode.SURVIVAL && main.getGameApi().getGameManager().getPlayingPlayersList().contains(player))
		{
			ItemStack itemStack = e.getCurrentItem();
			
			if(itemStack.getType() == Material.LEATHER_CHESTPLATE || itemStack.getType() == Material.LEATHER_HELMET || itemStack.getType() == Material.LEATHER_BOOTS || itemStack.getType() == Material.LEATHER_LEGGINGS)
			{
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onGameStart(GameStartEvent e)
	{
		for(Player player : main.getGameApi().getGameManager().getPlayingPlayersList())
		{
			StatsDataHandler.getPlayerStats(player).addStat("games", 1);
			
			Team team = main.getGameApi().getTeamsManager().getTeam(player);
			player.teleport(team.getSpawn());
			player.setGameMode(GameMode.SURVIVAL);
			team.setPoints(3);
			player.setBedSpawnLocation(team.getSpawn());
			player.getInventory().clear();
			
			StuffHandler.stuffPlayer(player);
			main.getVillagersHandler().setup();
		}
	}
	
}

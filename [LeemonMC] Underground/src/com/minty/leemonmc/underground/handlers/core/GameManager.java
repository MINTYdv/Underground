package com.minty.leemonmc.underground.handlers.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.core.stats.StatsDataHandler;
import com.minty.leemonmc.core.util.Title;
import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.games.events.GameFinishEvent;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;

public class GameManager {

	private Underground main;
	
	private static List<Block> placedBlocks = new ArrayList<>();
	
	public GameManager(Underground _main) {
		main = _main;
	}
	
	public String getPointsFormat(Team _team)
	{
		String ptsChar = "■";
		String tag = _team.getTag();
		
		String result = "";
		
		for(int i = 0; i < getPointsGoal(); i++)
		{
			if(_team.getPoints() > i)
			{
				result += tag + ptsChar;
			} else {
				result += "§7" + ptsChar;
			}
		}
		return result;
	}
	
	public void playerKilled(Player player) {
		Title title = new Title();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
				
				player.teleport(main.getGameApi().getTeamsManager().getTeam(player).getSpawn());
				player.setHealth(player.getMaxHealth());
				
				UndergroundData playerData = main.getUndergroundDataHandler().getPlayerData(player);
				playerData.setSpawned(false);
				
				new BukkitRunnable() {
					
					int timer = 5;

					@Override
					public void run()
					{
						if(timer == 5)
						{
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
							player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 255));
						}
						timer--;
						
						title.sendTitle(player, 20, 20, 20, "§e" + numberToSymbol(timer), "§6Réapparition...");
						
						if(timer == 0)
						{
		        			if(player.hasPotionEffect(PotionEffectType.JUMP))
		        			{
		        				player.removePotionEffect(PotionEffectType.JUMP);
		        			}
		        			
		        			if(player.hasPotionEffect(PotionEffectType.SLOW))
		        			{
		        				player.removePotionEffect(PotionEffectType.SLOW);
		        			}
		        			playerData.setSpawned(true);
		        			this.cancel();
						}
					}
				}.runTaskTimer(main, 20L, 20L);
				
			}
		}.runTaskLater(main, 2);
	}
	
	private String numberToSymbol(Integer n) {
		if(n == 1) {
			return "①";
		}
		if(n == 2) {
			return "②";
		}
		if(n == 3) {
			return "③";
		}
		if(n == 4) {
			return "④";
		}
		if(n == 5) {
			return "⑤";
		}
		return "";
	}
	
	public void removeTotemBlock(Team victim, Team breakedBy) {
		
		for(Player player : breakedBy.getPlayers())
		{
			StatsDataHandler.getPlayerStats(player).addStat("points", 1);
		}
		
		if(victim.getPoints() <= 0) {
			for(Player player : victim.getPlayers())
			{
				StatsDataHandler.getPlayerStats(player).addStat("defeats", 1);
			}
			
			for(Player player : breakedBy.getPlayers())
			{
				StatsDataHandler.getPlayerStats(player).addStat("victories", 1);
			}
			
			Bukkit.getPluginManager().callEvent(new GameFinishEvent(breakedBy));
		}
	}
	
	private Integer getPointsGoal()
	{
		return main.getConfig().getInt("points-goal");
	}
	
	public static List<Block> getPlacedBlocks() {
		return placedBlocks;
	}
	
}

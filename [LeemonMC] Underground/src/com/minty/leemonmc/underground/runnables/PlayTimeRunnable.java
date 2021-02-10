package com.minty.leemonmc.underground.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.core.stats.StatsData;
import com.minty.leemonmc.core.stats.StatsDataHandler;
import com.minty.leemonmc.underground.core.Underground;

public class PlayTimeRunnable extends BukkitRunnable {

	private Underground main = Underground.getInstance();
	
	@Override
	public void run()
	{
		if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
		
		for(Player player : main.getGameApi().getGameManager().getPlayingPlayersList())
		{
			StatsData data = StatsDataHandler.getPlayerStats(player);
			data.addStat("playtime", 1);
		}
	}
	
}
